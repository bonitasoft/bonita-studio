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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.jface.EMFFeatureLabelProvider;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
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
public class ParametersMappingSection extends AbstractBonitaDescriptionSection {

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
        diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class) ;
    }

    @Override
    public void refresh() {
        super.refresh();

        /*Dispose and then redraw*/
        for(Control c: parent.getChildren()){
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
        for (OutputMapping mapping : getSubProcess().getOutputMappings()) {
            addOutputMappingLine(outputMappingControl, mapping);
        }
        parent.layout();
        parent.getParent().layout();
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        tabbedPropertySheetPage = aTabbedPropertySheetPage;
        super.createControls(parent, aTabbedPropertySheetPage);
        Composite mainComposite = tabbedPropertySheetPage.getWidgetFactory().createComposite(parent);
        doCreateControls(mainComposite);
    }

    /**
     * @param parent
     */
    private void doCreateControls(final Composite parent) {
        this.parent = parent;
        GridLayout gridLayout = new GridLayout(3, false);
        parent.setLayout(gridLayout);
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        Composite buttonComposite = getWidgetFactory().createComposite(parent);
        GridDataFactory.fillDefaults().span(3,1).applyTo(buttonComposite);
        GridLayoutFactory.swtDefaults().numColumns(2).applyTo(buttonComposite);
        Button automapButton = getWidgetFactory().createButton(buttonComposite, Messages.autoMap, SWT.FLAT);
        Label label = getWidgetFactory().createLabel(buttonComposite, Messages.autoMap_description);
        label.setFont(BonitaStudioFontRegistry.getCommentsFont());
        automapButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                automapSubProcess(parent);
            }
        });
        addInputMappingControl(parent);
        Composite line = new Composite(parent, SWT.BORDER);
        GridDataFactory.swtDefaults().hint(2, SWT.DEFAULT).align(SWT.LEFT, SWT.FILL).applyTo(line);
        addOutputMappingControl(parent);
    }

    /**
     * @param parent
     * 
     */
    protected void automapSubProcess(Composite parent) {
        List<String> subprocessData = getSubprocessData();
        Map<String, DataType> subprocessTypes = getSubprocessDataTypes();
        List<Data> accessibleData = ModelHelper.getAccessibleData(getSubProcess(),false);
        List<Data> mappedOutputData = new ArrayList<Data>();
        List<Data> mappedInputData = new ArrayList<Data>();
        for(OutputMapping existingMapping : getSubProcess().getOutputMappings()){
            mappedOutputData.add(existingMapping.getProcessTarget());
        }
        for(InputMapping existingMapping : getSubProcess().getInputMappings()){
            mappedInputData.add(existingMapping.getProcessSource());
        }
        for (Data data : accessibleData) {
            if (!mappedOutputData.contains(data) || !mappedInputData.contains(data)) {
                String subprocessDataString = null;
                if (subprocessData.contains(data.getName())) {
                    subprocessDataString = data.getName();
                } else if (subprocessData.contains(SUBPROCESS_MAPPING_DATA_PREFIX + data.getName())) {
                    subprocessDataString = SUBPROCESS_MAPPING_DATA_PREFIX + data.getName();
                }
                if (subprocessDataString != null) {
                    DataType dataType = subprocessTypes.get(subprocessDataString);
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
        Composite outputComposite = getWidgetFactory().createComposite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(1, false);
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

        Button addLineButton = new Button(outputComposite, SWT.FLAT);
        addLineButton.setText("+");
        addLineButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
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
    private void createOutputMapping(Data target, String source) {
        OutputMapping outputMapping = ProcessFactory.eINSTANCE.createOutputMapping();
        if(target != null){
            outputMapping.setProcessTarget(target);
        }
        if(source != null){
            outputMapping.setSubprocessSource(source);
        }
        getEditingDomain().getCommandStack().execute(new AddCommand(getEditingDomain(), getSubProcess().getOutputMappings(), outputMapping));
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
            public void handleEvent(Event event) {
                RemoveCommand command = new RemoveCommand(getEditingDomain(), getSubProcess().getOutputMappings(), mapping);
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
        for (String subprocessData : getSubprocessData()) {
            subprocessSourceCombo.add(subprocessData);
        }
        GridData layoutData = new GridData(SWT.FILL, SWT.DEFAULT, true, false);
        subprocessSourceCombo.setLayoutData(layoutData);
        subprocessSourceCombo.addListener(SWT.Modify, new Listener() {
            @Override
            public void handleEvent(Event event) {
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
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return ModelHelper.getAccessibleData(getSubProcess()).toArray();
            }
        });
        processTargetCombo.setLabelProvider(new EMFFeatureLabelProvider(ProcessPackage.Literals.ELEMENT__NAME));
        processTargetCombo.addPostSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.OUTPUT_MAPPING__PROCESS_TARGET, ((IStructuredSelection) event
                                .getSelection()).getFirstElement()));
            }
        });
        processTargetCombo.setInput(getSubProcess());
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
        Composite outputComposite = getWidgetFactory().createComposite(parent);
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

        Button addLineButton = new Button(outputComposite, SWT.FLAT);
        addLineButton.setText("+");
        addLineButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(Event event) {
                createInputMapping(null,null);
                refreshScrolledComposite(parent);
            }

        });

    }
    private void createInputMapping(Data source, String target) {
        InputMapping mapping = ProcessFactory.eINSTANCE.createInputMapping();
        if(source != null){
            mapping.setProcessSource(source);
        }
        if(target != null){
            mapping.setSubprocessTarget(target);
        }
        getEditingDomain().getCommandStack().execute(new AddCommand(getEditingDomain(), getSubProcess().getInputMappings(), mapping));
        addInputMappingLine(inputMappingControl, mapping);
    }

    /**
     * 
     */
    private void updateInputMappings() {
        for (InputMapping input : getSubProcess().getInputMappings()) {
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
        for (String subprocessData : getSubprocessData()) {
            targetCombo.add(subprocessData);
        }
        GridData layoutData = new GridData(SWT.FILL, SWT.DEFAULT, true, false);
        targetCombo.setLayoutData(layoutData);
        targetCombo.addListener(SWT.Modify, new Listener() {
            @Override
            public void handleEvent(Event event) {
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
            public void handleEvent(Event event) {
                RemoveCommand command = new RemoveCommand(getEditingDomain(), getSubProcess().getInputMappings(), mapping);
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
        srcCombo.setContentProvider(new IStructuredContentProvider() {
            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return ModelHelper.getAccessibleData(getSubProcess()).toArray();
            }
        });
        srcCombo.setLabelProvider(new EMFFeatureLabelProvider(ProcessPackage.Literals.ELEMENT__NAME));
        srcCombo.addPostSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE, ((IStructuredSelection) event
                                .getSelection()).getFirstElement()));
            }
        });
        srcCombo.setInput(getSubProcess());
        srcCombo.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        if (mapping.getProcessSource() != null) {
            srcCombo.setSelection(new StructuredSelection(mapping.getProcessSource()));
        }
		return srcCombo;
	}

    /**
     * @return
     */
    private List<String> getSubprocessData() {
        List<String> res = new ArrayList<String>();
        String subprocessName = null;
        CallActivity sub= getSubProcess();
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
            AbstractProcess subProcess = ModelHelper.findProcess(subprocessName, subprocessVersion,diagramStore.getAllProcesses());
     
            if (subProcess != null) {
                for (Data data : subProcess.getData()) {
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
        Map<String, DataType> res = new HashMap<String, DataType>();
        String subprocessName = null;
        CallActivity sub= getSubProcess();
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
            AbstractProcess subProcess = ModelHelper.findProcess(subprocessName, subprocessVersion, diagramStore.getAllProcesses());
            if (subProcess != null) {
                for (Data data : subProcess.getData()) {
                    res.put(data.getName(),data.getDataType());
                }
            }
        }
        return res;
    }



    /**
     * 
     */
    private CallActivity getSubProcess() {
        return (CallActivity) getEObject();
    }

    @Override
    public String getSectionDescription() {
        return Messages.parametersMappingSectionDescription;
    }

}
