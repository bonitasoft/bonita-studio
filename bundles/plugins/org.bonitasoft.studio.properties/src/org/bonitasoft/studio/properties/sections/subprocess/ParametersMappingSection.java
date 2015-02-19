/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.subprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.jface.EMFFeatureLabelProvider;
import org.bonitasoft.studio.common.properties.EObjectSelectionProviderSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier - correct refresh bug 1712
 */
public class ParametersMappingSection extends EObjectSelectionProviderSection {

    /**
     *
     */
    private static final String SUBPROCESS_MAPPING_DATA_PREFIX = "sub_";
    private Composite inputMappingControl;
    private Composite outputMappingControl;
    private Composite parent;
    private TabbedPropertySheetPage tabbedPropertySheetPage;
    private final DiagramRepositoryStore diagramStore;

    public ParametersMappingSection(){
        diagramStore = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class) ;
    }

    @Override
    public void refresh() {
        super.refresh();

        /*Dispose and then redraw*/
        for(final Control c: parent.getChildren()){
            c.dispose();
        }
        doCreateControls(parent);
        /*Fill with existing in/out mappings*/
        updateInputMappings();
        updateOutputMappings();
        /*layout with potential add of in/out mappings*/
        parent.layout();
        parent.getParent().layout();

        parent.layout();
    }

    /**
     *
     */
    private void updateOutputMappings() {
        for (final OutputMapping mapping : getCallActivity().getOutputMappings()) {
            addOutputMappingLine(outputMappingControl, mapping);
        }
        parent.layout();
        parent.getParent().layout();
    }

    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
        tabbedPropertySheetPage = aTabbedPropertySheetPage;
        super.createControls(parent, aTabbedPropertySheetPage);
        final Composite mainComposite = tabbedPropertySheetPage.getWidgetFactory().createComposite(parent);
        doCreateControls(mainComposite);
    }

    /**
     * @param parent
     */
    private void doCreateControls(final Composite parent) {
        this.parent = parent;
        final GridLayout gridLayout = new GridLayout(3, false);
        parent.setLayout(gridLayout);
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Composite buttonComposite = getWidgetFactory().createComposite(parent);
        GridDataFactory.fillDefaults().span(3,1).applyTo(buttonComposite);
        GridLayoutFactory.swtDefaults().numColumns(2).applyTo(buttonComposite);
        final Button automapButton = getWidgetFactory().createButton(buttonComposite, Messages.autoMap, SWT.FLAT);
        final Label label = getWidgetFactory().createLabel(buttonComposite, Messages.autoMap_description);
        label.setFont(BonitaStudioFontRegistry.getCommentsFont());
        automapButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                automapSubProcess(parent);
            }
        });
        addInputMappingControl(parent);
        final Composite line = new Composite(parent, SWT.BORDER);
        GridDataFactory.swtDefaults().hint(2, SWT.DEFAULT).align(SWT.LEFT, SWT.FILL).applyTo(line);
        addOutputMappingControl(parent);
    }

    /**
     * @param parent
     *
     */
    protected void automapSubProcess(final Composite parent) {
        final List<String> subprocessData = getCallActivityData();
        final Map<String, DataType> subprocessTypes = getSubprocessDataTypes();
        final List<Data> accessibleData = ModelHelper.getAccessibleData(getCallActivity(),false);
        final List<Data> mappedOutputData = new ArrayList<Data>();
        final List<Data> mappedInputData = new ArrayList<Data>();
        for(final OutputMapping existingMapping : getCallActivity().getOutputMappings()){
            mappedOutputData.add(existingMapping.getProcessTarget());
        }
        for(final InputMapping existingMapping : getCallActivity().getInputMappings()){
            final EList<EObject> referencedElements = existingMapping.getProcessSource().getReferencedElements();
            if (!referencedElements.isEmpty()) {
                mappedInputData.add((Data) referencedElements.get(0));
            }
        }
        for (final Data data : accessibleData) {
            if (!mappedOutputData.contains(data) || !mappedInputData.contains(data)) {
                String subprocessDataString = null;
                if (subprocessData.contains(data.getName())) {
                    subprocessDataString = data.getName();
                } else if (subprocessData.contains(SUBPROCESS_MAPPING_DATA_PREFIX + data.getName())) {
                    subprocessDataString = SUBPROCESS_MAPPING_DATA_PREFIX + data.getName();
                }
                if (subprocessDataString != null) {
                    final DataType dataType = subprocessTypes.get(subprocessDataString);
                    if (dataType != null && dataType.getName().equals(data.getDataType().getName())) {
                        if (!mappedInputData.contains(data)) {
                            createInputMapping(data, subprocessDataString);
                        }
                        if (!mappedOutputData.contains(data)) {
                            createOutputMapping(data, subprocessDataString);
                        }
                    }
                }
            }
        }

        refreshScrolledComposite(parent);
    }

    /**
     * @param parent
     */
    private void addOutputMappingControl(final Composite parent) {
        final Composite outputComposite = getWidgetFactory().createComposite(parent, SWT.NONE);
        final GridLayout layout = new GridLayout(1, false);
        outputComposite.setLayout(layout);
        outputComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true));

        outputMappingControl = new MagicComposite(outputComposite, SWT.NONE);
        outputMappingControl.setBackground(parent.getBackground());
        outputMappingControl.setLayout(new GridLayout(4, false));

        getWidgetFactory().createLabel(outputMappingControl, Messages.targetParameter);
        Composite filler = getWidgetFactory().createComposite(outputMappingControl);//a filler to take the place
        GridDataFactory.swtDefaults().hint(1, 1).applyTo(filler);
        getWidgetFactory().createLabel(outputMappingControl, Messages.sourceParameter);
        filler = getWidgetFactory().createComposite(outputMappingControl);//a filler to take the place
        GridDataFactory.swtDefaults().hint(1, 1).applyTo(filler);

        final Button addLineButton = new Button(outputComposite, SWT.FLAT);
        addLineButton.setText("+");
        addLineButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                createOutputMapping(null,null);
                refreshScrolledComposite(parent);
            }
        });

    }
    /**
     * @param parent
     */
    private void refreshScrolledComposite(final Composite parent) {
        parent.getParent().getParent().layout(true ,true);
        tabbedPropertySheetPage.resizeScrolledComposite();
    }
    private void createOutputMapping(final Data target, final String source) {
        final OutputMapping outputMapping = ProcessFactory.eINSTANCE.createOutputMapping();
        if(target != null){
            outputMapping.setProcessTarget(target);
        }
        if(source != null){
            outputMapping.setSubprocessSource(source);
        }
        getEditingDomain().getCommandStack().execute(new AddCommand(getEditingDomain(), getCallActivity().getOutputMappings(), outputMapping));
        addOutputMappingLine(outputMappingControl, outputMapping);
    }
    /**
     * @param outputMappingControl
     * @param object
     */
    protected void addOutputMappingLine(final Composite outputMappingControl, final OutputMapping mapping) {
        final CCombo subprocessSourceCombo = createSubprocessSourceCombo(outputMappingControl, mapping);
        final Label assignToLabel = getWidgetFactory().createLabel(outputMappingControl, Messages.assignTo);
        final ComboViewer processTargetCombo = createProcessTargetCombo(outputMappingControl, mapping);

        // TODO populate combo
        final Button deleteButton = new Button(outputMappingControl, SWT.FLAT);
        deleteButton.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_DELETE));
        deleteButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                final RemoveCommand command = new RemoveCommand(getEditingDomain(), getCallActivity().getOutputMappings(), mapping);
                getEditingDomain().getCommandStack().execute(command);
                processTargetCombo.getControl().setData(MagicComposite.HIDDEN, true);
                processTargetCombo.getControl().setVisible(false);
                assignToLabel.setData(MagicComposite.HIDDEN, true);
                assignToLabel.setVisible(false);
                subprocessSourceCombo.setData(MagicComposite.HIDDEN, true);
                subprocessSourceCombo.setVisible(false);
                deleteButton.setData(MagicComposite.HIDDEN, true);
                deleteButton.setVisible(false);
                parent.layout();
                parent.getParent().layout();
                tabbedPropertySheetPage.resizeScrolledComposite();
            }
        });
    }

	private CCombo createSubprocessSourceCombo(final Composite outputMappingControl, final OutputMapping mapping) {
		final CCombo subprocessSourceCombo = getWidgetFactory().createCCombo(outputMappingControl, SWT.BORDER);
        for (final String subprocessData : getCallActivityData()) {
            subprocessSourceCombo.add(subprocessData);
        }
        final GridData layoutData = new GridData(SWT.FILL, SWT.DEFAULT, true, false);
        subprocessSourceCombo.setLayoutData(layoutData);
        subprocessSourceCombo.addListener(SWT.Modify, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                getEditingDomain().getCommandStack()
                .execute(
                        new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.OUTPUT_MAPPING__SUBPROCESS_SOURCE, subprocessSourceCombo
                                .getText()));
            }
        });
        if (mapping.getSubprocessSource() != null) {
            subprocessSourceCombo.setText(mapping.getSubprocessSource());
        }
		return subprocessSourceCombo;
	}

	private ComboViewer createProcessTargetCombo(final Composite outputMappingControl, final OutputMapping mapping) {
		final ComboViewer processTargetCombo = new ComboViewer(getWidgetFactory().createCCombo(outputMappingControl, SWT.READ_ONLY | SWT.BORDER));
        processTargetCombo.setContentProvider(new IStructuredContentProvider() {
            @Override
            public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public Object[] getElements(final Object inputElement) {
                return ModelHelper.getAccessibleData(getCallActivity()).toArray();
            }
        });
        processTargetCombo.setLabelProvider(new EMFFeatureLabelProvider(ProcessPackage.Literals.ELEMENT__NAME));
        processTargetCombo.addPostSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.OUTPUT_MAPPING__PROCESS_TARGET, ((IStructuredSelection) event
                                .getSelection()).getFirstElement()));
            }
        });
        processTargetCombo.setInput(getCallActivity());
        processTargetCombo.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        if (mapping.getProcessTarget() != null) {
            processTargetCombo.setSelection(new StructuredSelection(mapping.getProcessTarget()));
        }
		return processTargetCombo;
	}

    /**
     * @param parent
     */
    private void addInputMappingControl(final Composite parent) {
        final Composite outputComposite = getWidgetFactory().createComposite(parent);
        outputComposite.setLayout(new GridLayout(1, false));
        outputComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true));

        inputMappingControl = new MagicComposite(outputComposite, SWT.NONE);
        inputMappingControl.setBackground(parent.getBackground());
        inputMappingControl.setLayout(new GridLayout(4, false));

        getWidgetFactory().createLabel(inputMappingControl, Messages.sourceParameter);
        Composite filler = getWidgetFactory().createComposite(inputMappingControl);//a filler to take the place
        GridDataFactory.swtDefaults().hint(1, 1).applyTo(filler);
        getWidgetFactory().createLabel(inputMappingControl, Messages.targetParameter);
        filler = getWidgetFactory().createComposite(inputMappingControl);//a filler to take the place
        GridDataFactory.swtDefaults().hint(1, 1).applyTo(filler);

        final Button addLineButton = new Button(outputComposite, SWT.FLAT);
        addLineButton.setText("+");
        addLineButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                createInputMapping(null,null);
                refreshScrolledComposite(parent);
            }

        });

    }
    private void createInputMapping(final Data source, final String target) {
        final InputMapping mapping = ProcessFactory.eINSTANCE.createInputMapping();
        if(source != null){
            mapping.setProcessSource(ExpressionHelper.createVariableExpression(source));
        }
        if(target != null){
            mapping.setSubprocessTarget(target);
        }
        getEditingDomain().getCommandStack().execute(new AddCommand(getEditingDomain(), getCallActivity().getInputMappings(), mapping));
        addInputMappingLine(inputMappingControl, mapping);
    }

    /**
     *
     */
    private void updateInputMappings() {
        for (final InputMapping input : getCallActivity().getInputMappings()) {
            addInputMappingLine(inputMappingControl, input);
        }
        parent.layout();
        parent.getParent().layout();
    }

    /**
     * @param outputMappingControl
     * @param object
     */
    protected void addInputMappingLine(final Composite outputMappingControl, final InputMapping mapping) {
        final ComboViewer srcCombo = createInputMappingSourceCombo(outputMappingControl, mapping);

        final Label assignToLabel = getWidgetFactory().createLabel(outputMappingControl, Messages.assignTo);
        final CCombo targetCombo = getWidgetFactory().createCCombo(outputMappingControl, SWT.BORDER);
        for (final String subprocessData : getCallActivityData()) {
            targetCombo.add(subprocessData);
        }
        final GridData layoutData = new GridData(SWT.FILL, SWT.DEFAULT, true, false);
        targetCombo.setLayoutData(layoutData);
        targetCombo.addListener(SWT.Modify, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.INPUT_MAPPING__SUBPROCESS_TARGET, targetCombo.getText()));
            }
        });
        if (mapping.getSubprocessTarget() != null) {
            targetCombo.setText(mapping.getSubprocessTarget());
        }

        // TODO populate combo
        final Button deleteButton = new Button(outputMappingControl, SWT.FLAT);
        deleteButton.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_DELETE));
        deleteButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                final RemoveCommand command = new RemoveCommand(getEditingDomain(), getCallActivity().getInputMappings(), mapping);
                getEditingDomain().getCommandStack().execute(command);
                srcCombo.getControl().setData(MagicComposite.HIDDEN, true);
                srcCombo.getControl().setVisible(false);
                assignToLabel.setData(MagicComposite.HIDDEN, true);
                assignToLabel.setVisible(false);
                targetCombo.setData(MagicComposite.HIDDEN, true);
                targetCombo.setVisible(false);
                deleteButton.setData(MagicComposite.HIDDEN, true);
                deleteButton.setVisible(false);
                parent.layout();
                parent.getParent().layout();
                tabbedPropertySheetPage.resizeScrolledComposite();
            }
        });
    }

	private ComboViewer createInputMappingSourceCombo(final Composite outputMappingControl, final InputMapping mapping) {
		final ComboViewer srcCombo = new ComboViewer(getWidgetFactory().createCCombo(outputMappingControl, SWT.READ_ONLY | SWT.BORDER));
        srcCombo.setComparer(new IElementComparer() {

            @Override
            public int hashCode(final Object element) {
                return element.hashCode();
            }

            @Override
            public boolean equals(final Object a, final Object b) {
                return EcoreUtil.equals((EObject) a, (EObject) b);
            }
        });
        srcCombo.setContentProvider(new InputDataMappingContentProvider(getEObjectObservable(), ExpressionEditorService.getInstance().getExpressionProvider(
                ExpressionConstants.VARIABLE_TYPE)));
        srcCombo.setLabelProvider(new EMFFeatureLabelProvider(ProcessPackage.Literals.ELEMENT__NAME));
        srcCombo.addPostSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE, ((IStructuredSelection) event
                                .getSelection()).getFirstElement()));
            }
        });
        srcCombo.setInput(getCallActivity());
        srcCombo.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        if (mapping.getProcessSource() != null) {
            srcCombo.setSelection(new StructuredSelection(mapping.getProcessSource()));
        }
		return srcCombo;
	}

    /**
     * @return
     */
    private List<String> getCallActivityData() {
        final List<String> res = new ArrayList<String>();
        String subprocessName = null;
        final CallActivity sub= getCallActivity();
        if(sub.getCalledActivityName() != null
                && sub.getCalledActivityName().getContent() != null){
            subprocessName = sub.getCalledActivityName().getContent();
        }
        String subprocessVersion = null;
        if(sub.getCalledActivityVersion() != null
                && sub.getCalledActivityVersion().getContent() != null){
            subprocessVersion = sub.getCalledActivityVersion().getContent();
        }
        if(subprocessName != null){
            final AbstractProcess subProcess = ModelHelper.findProcess(subprocessName, subprocessVersion,diagramStore.getAllProcesses());

            if (subProcess != null) {
                for (final Data data : subProcess.getData()) {
                    res.add(data.getName());
                }
            }
        }
        return res;
    }
    /**
     * @return
     */
    private Map<String, DataType> getSubprocessDataTypes() {
        final Map<String, DataType> res = new HashMap<String, DataType>();
        String subprocessName = null;
        final CallActivity sub= getCallActivity();
        if(sub.getCalledActivityName() != null
                && sub.getCalledActivityName().getContent() != null){
            subprocessName = sub.getCalledActivityName().getContent();
        }
        String subprocessVersion = null;
        if(sub.getCalledActivityVersion() != null
                && sub.getCalledActivityVersion().getContent() != null){
            subprocessVersion = sub.getCalledActivityVersion().getContent();
        }
        if(subprocessName != null){
            final AbstractProcess subProcess = ModelHelper.findProcess(subprocessName, subprocessVersion, diagramStore.getAllProcesses());
            if (subProcess != null) {
                for (final Data data : subProcess.getData()) {
                    res.put(data.getName(),data.getDataType());
                }
            }
        }
        return res;
    }



    /**
     *
     */
    private CallActivity getCallActivity() {
        return (CallActivity) getEObject();
    }

    @Override
    public String getSectionDescription() {
        return Messages.parametersMappingSectionDescription;
    }

}
