/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.properties.sections.subprocess;

import javax.inject.Inject;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.jface.EMFFeatureLabelProvider;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.properties.EObjectSelectionProviderSection;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier - correct refresh bug 1712
 */
public class ParametersMappingSection extends EObjectSelectionProviderSection {

    private final CallActivityHelper callAcitivtyHelper;
    private Composite inputMappingControl;
    private Composite outputMappingControl;
    private Composite parent;
    private TabbedPropertySheetWidgetFactory widgetFactory;
    private final ExpressionEditorService expressionEditorService;
    private final ISharedImages sharedImages;

    @Inject
    public ParametersMappingSection(final ExpressionEditorService expressionEditorService, final ISharedImages sharedImages,
            final RepositoryAccessor repositoryAccessor) {
        this.expressionEditorService = expressionEditorService;
        this.sharedImages = sharedImages;
        callAcitivtyHelper = new CallActivityHelper(repositoryAccessor);
    }

    @Override
    public void refresh() {
        super.refresh();

        /* Dispose and then redraw */
        for (final Control c : parent.getChildren()) {
            c.dispose();
        }
        doCreateControls(parent);
        /* Fill with existing in/out mappings */
        updateInputMappings();
        updateOutputMappings();
        /* layout with potential add of in/out mappings */
        parent.layout();
        parent.getParent().layout();

        parent.layout();
    }

    private void updateOutputMappings() {
        for (final OutputMapping mapping : getCallActivity().getOutputMappings()) {
            addOutputMappingLine(outputMappingControl, mapping);
        }
        parent.layout();
        parent.getParent().layout();
    }

    @Override
    protected void createContent(final Composite parent) {
        final Composite mainComposite = widgetFactory.createComposite(parent);
        doCreateControls(mainComposite);
    }

    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
        widgetFactory = aTabbedPropertySheetPage.getWidgetFactory();
        super.createControls(parent, aTabbedPropertySheetPage);
    }

    /**
     * @param parent
     */
    private void doCreateControls(final Composite parent) {
        this.parent = parent;
        final GridLayout gridLayout = new GridLayout(3, false);
        parent.setLayout(gridLayout);
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Composite buttonComposite = widgetFactory.createComposite(parent);
        GridDataFactory.fillDefaults().span(3, 1).applyTo(buttonComposite);
        GridLayoutFactory.swtDefaults().numColumns(2).applyTo(buttonComposite);
        final Button automapButton = widgetFactory.createButton(buttonComposite, Messages.autoMap, SWT.FLAT);
        final Label label = widgetFactory.createLabel(buttonComposite, Messages.autoMap_description);
        label.setFont(BonitaStudioFontRegistry.getCommentsFont());
        automapButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final CallActivityMapper mapper = callAcitivtyHelper.findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(getCallActivity());
                for (final Data data : mapper.getInputMappingToCreate()) {
                    createInputMapping(data, InputMappingAssignationType.CONTRACT_INPUT, data.getName());
                }
                for (final Data data : mapper.getOutputMappingToCreate()) {
                    createOutputMapping(data, data.getName());
                }
                refreshScrolledComposite(parent);
            }
        });
        addInputMappingControl(parent);
        final Composite line = new Composite(parent, SWT.BORDER);
        GridDataFactory.swtDefaults().hint(2, SWT.DEFAULT).align(SWT.LEFT, SWT.FILL).applyTo(line);
        addOutputMappingControl(parent);
    }

    /**
     * @param parent
     */
    private void addOutputMappingControl(final Composite parent) {
        final Composite outputComposite = widgetFactory.createComposite(parent, SWT.NONE);
        final GridLayout layout = new GridLayout(1, false);
        outputComposite.setLayout(layout);
        outputComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, true));

        outputMappingControl = new MagicComposite(outputComposite, SWT.NONE);
        outputMappingControl.setBackground(parent.getBackground());
        outputMappingControl.setLayout(new GridLayout(4, false));

        widgetFactory.createLabel(outputMappingControl, Messages.targetParameterForOutput);
        Composite filler = widgetFactory.createComposite(outputMappingControl);//a filler to take the place
        GridDataFactory.swtDefaults().hint(1, 1).applyTo(filler);
        widgetFactory.createLabel(outputMappingControl, Messages.sourceParameter);
        filler = widgetFactory.createComposite(outputMappingControl);//a filler to take the place
        GridDataFactory.swtDefaults().hint(1, 1).applyTo(filler);

        final Button addLineButton = new Button(outputComposite, SWT.FLAT);
        addLineButton.setText("+");
        addLineButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_ADD_OUTPUT);
        addLineButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                createOutputMapping(null, null);
                refreshScrolledComposite(parent);
            }
        });

    }

    /**
     * @param parent
     */
    private void refreshScrolledComposite(final Composite parent) {
        parent.getParent().getParent().layout(true, true);
        getTabbedPropertySheetPage().resizeScrolledComposite();
    }

    protected void createOutputMapping(final Data target, final String source) {
        final OutputMapping outputMapping = ProcessFactory.eINSTANCE.createOutputMapping();
        if (target != null) {
            outputMapping.setProcessTarget(target);
        }
        if (source != null) {
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
        final Label assignToLabel = widgetFactory.createLabel(outputMappingControl, Messages.assignTo);
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
                getTabbedPropertySheetPage().resizeScrolledComposite();
            }
        });
    }

    private CCombo createSubprocessSourceCombo(final Composite outputMappingControl, final OutputMapping mapping) {
        final CCombo subprocessSourceCombo = widgetFactory.createCCombo(outputMappingControl, SWT.BORDER);
        for (final String subprocessData : callAcitivtyHelper.getCallActivityData(getCallActivity())) {
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
        final ComboViewer processTargetCombo = new ComboViewer(widgetFactory.createCCombo(outputMappingControl, SWT.READ_ONLY | SWT.BORDER));
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
        final Composite outputComposite = widgetFactory.createComposite(parent);
        outputComposite.setLayout(new GridLayout(1, false));
        outputComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, true));

        inputMappingControl = new MagicComposite(outputComposite, SWT.NONE);
        inputMappingControl.setBackground(parent.getBackground());
        inputMappingControl.setLayout(new GridLayout(4, false));

        widgetFactory.createLabel(inputMappingControl, Messages.sourceParameter);
        Composite filler = widgetFactory.createComposite(inputMappingControl);//a filler to take the place
        GridDataFactory.swtDefaults().hint(1, 1).applyTo(filler);
        widgetFactory.createLabel(inputMappingControl, Messages.targetParameterForInput);
        filler = widgetFactory.createComposite(inputMappingControl);//a filler to take the place
        GridDataFactory.swtDefaults().hint(1, 1).applyTo(filler);

        final Button addLineButton = new Button(outputComposite, SWT.FLAT);
        addLineButton.setText("+");
        addLineButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_ADD_INPUT);
        addLineButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                createInputMapping(null, InputMappingAssignationType.CONTRACT_INPUT, null);
                refreshScrolledComposite(parent);
            }
        });

    }

    protected void createInputMapping(final Data source, final InputMappingAssignationType assignationType, final String target) {
        final InputMapping mapping = ProcessFactory.eINSTANCE.createInputMapping();
        if (source != null) {
            mapping.setProcessSource(ExpressionHelper.createVariableExpression(source));
        }
        if (target != null) {
            mapping.setSubprocessTarget(target);
        }
        mapping.setAssignationType(assignationType);
        getEditingDomain().getCommandStack().execute(new AddCommand(getEditingDomain(), getCallActivity().getInputMappings(), mapping));
        addInputMappingLine(inputMappingControl, mapping);
    }

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
        final CCombo assignationTypeCombo = createInputMappingAssignedToCombo(outputMappingControl, mapping);
        final CCombo targetCombo = createInputMappingTargetCombo(outputMappingControl, mapping);

        assignationTypeCombo.addListener(SWT.Modify, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                InputMappingAssignationType newValue;
                if (Messages.assignToContractInput.equals(assignationTypeCombo.getText())) {
                    newValue = InputMappingAssignationType.CONTRACT_INPUT;
                } else {
                    newValue = InputMappingAssignationType.DATA;
                }
                if (newValue != mapping.getAssignationType()) {
                    getEditingDomain().getCommandStack().execute(
                            new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.INPUT_MAPPING__ASSIGNATION_TYPE, newValue));
                    targetCombo.removeAll();
                    updateAvailableValuesInputMappingTargetCombo(targetCombo, newValue);
                }
            }
        });

        // TODO populate combo
        final Button deleteButton = new Button(outputMappingControl, SWT.FLAT);
        deleteButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_DELETE_INPUT);
        deleteButton.setImage(sharedImages.getImage(ISharedImages.IMG_TOOL_DELETE));
        deleteButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final RemoveCommand command = new RemoveCommand(getEditingDomain(), getCallActivity().getInputMappings(), mapping);
                getEditingDomain().getCommandStack().execute(command);
                srcCombo.getControl().setData(MagicComposite.HIDDEN, true);
                srcCombo.getControl().setVisible(false);
                assignationTypeCombo.setData(MagicComposite.HIDDEN, true);
                assignationTypeCombo.setVisible(false);
                targetCombo.setData(MagicComposite.HIDDEN, true);
                targetCombo.setVisible(false);
                deleteButton.setData(MagicComposite.HIDDEN, true);
                deleteButton.setVisible(false);
                parent.layout();
                parent.getParent().layout();
                getTabbedPropertySheetPage().resizeScrolledComposite();
            }
        });
    }

    protected CCombo createInputMappingTargetCombo(final Composite outputMappingControl, final InputMapping mapping) {
        final CCombo targetCombo = widgetFactory.createCCombo(outputMappingControl, SWT.BORDER);
        final InputMappingAssignationType assignationType = mapping.getAssignationType();
        updateAvailableValuesInputMappingTargetCombo(targetCombo, assignationType);
        final GridData layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false);
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
        targetCombo.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_CALLEDTARGET);
        return targetCombo;
    }

    protected void updateAvailableValuesInputMappingTargetCombo(final CCombo targetCombo, final InputMappingAssignationType assignationType) {
        if (InputMappingAssignationType.DATA == assignationType) {
            for (final String subprocessData : callAcitivtyHelper.getCallActivityData(getCallActivity())) {
                targetCombo.add(subprocessData);
            }
        } else {
            for (final String contractInputOfCalledActivity : callAcitivtyHelper.getCallActivityContractInput(getCallActivity())) {
                targetCombo.add(contractInputOfCalledActivity);
            }
        }
    }

    protected CCombo createInputMappingAssignedToCombo(final Composite outputMappingControl, final InputMapping mapping) {
        final CCombo assignationTypeCombo = widgetFactory.createCCombo(outputMappingControl, SWT.BORDER | SWT.READ_ONLY);
        assignationTypeCombo.setItems(new String[] { Messages.assignToContractInput, Messages.assignToData });
        switch (mapping.getAssignationType()) {
            case CONTRACT_INPUT:
                assignationTypeCombo.setText(Messages.assignToContractInput);
                break;
            case DATA:
                assignationTypeCombo.setText(Messages.assignToData);
                break;
            default:
                assignationTypeCombo.setText(Messages.assignToContractInput);
                break;
        }
        assignationTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        assignationTypeCombo.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_ASSIGNATIONTYPE);
        return assignationTypeCombo;
    }

    private ComboViewer createInputMappingSourceCombo(final Composite outputMappingControl, final InputMapping mapping) {
        final ComboViewer srcCombo = new ComboViewer(widgetFactory.createCCombo(outputMappingControl, SWT.READ_ONLY | SWT.BORDER));
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
        srcCombo.setContentProvider(new InputDataMappingContentProvider(getEObjectObservable(), expressionEditorService
                .getExpressionProvider(ExpressionConstants.VARIABLE_TYPE)));
        srcCombo.setLabelProvider(new EMFFeatureLabelProvider(ExpressionPackage.Literals.EXPRESSION__NAME));
        srcCombo.addPostSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE, ((IStructuredSelection) event
                                .getSelection()).getFirstElement()));
            }
        });
        final IObservableList observeDetailList = CustomEMFEditObservables.observeDetailList(Realm.getDefault(), getEObjectObservable(),
                ProcessPackage.Literals.DATA_AWARE__DATA);
        observeDetailList.addListChangeListener(new IListChangeListener() {

            @Override
            public void handleListChange(final ListChangeEvent event) {
                srcCombo.setInput(observeDetailList);
            }
        });
        srcCombo.setInput(observeDetailList);

        srcCombo.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        if (mapping.getProcessSource() != null) {
            srcCombo.setSelection(new StructuredSelection(mapping.getProcessSource()));
        }
        srcCombo.getCCombo().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_SOURCEDATA);
        return srcCombo;
    }

    protected CallActivity getCallActivity() {
        return (CallActivity) getEObject();
    }

    @Override
    public String getSectionDescription() {
        return Messages.parametersMappingSectionDescription;
    }

    /**
     * Override to have public visibility in order to be able to set Editiing Domain in tests
     * Sets the editingDomain.
     *
     * @param editingDomain The editingDomain to set.
     */
    @Override
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        super.setEditingDomain(editingDomain);
    }

}
