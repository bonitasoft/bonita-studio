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
package org.bonitasoft.studio.properties.sections.callActivity;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.jface.MessageDialogWithLink;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.jface.databinding.CustomEMFEditObservables;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.progress.IProgressService;

public class InputParametersMappingSection extends AbstractBonitaDescriptionSection {

    private FetchContractOperation fetchContractOperation;
    private IProgressService progressService;
    private ISharedImages sharedImages;
    private CallActivitySelectionProvider selectionProvider;
    private CallActivityHelper callActivityHelper;

    private MagicComposite inputMappingControl;

    private Composite mainComposite;

    private EMFDataBindingContext dbc;

    @Inject
    public InputParametersMappingSection(RepositoryAccessor repositoryAccessor,
            FetchContractOperation fetchContractOperation,
            IProgressService progressService,
            ISharedImages sharedImages,
            CallActivitySelectionProvider selectionProvider) {
        this.fetchContractOperation = fetchContractOperation;
        this.progressService = progressService;
        this.sharedImages = sharedImages;
        this.selectionProvider = selectionProvider;
        this.callActivityHelper = new CallActivityHelper(repositoryAccessor, selectionProvider);
    }


    private void updateMappings() {
        final CallActivity callActivity = (CallActivity) selectionProvider.getAdapter(EObject.class);
        for (final InputMapping input : callActivity.getInputMappings()) {
            addInputMappingLine(inputMappingControl, input);
        }
        mainComposite.getParent().layout();
    }

    private void doCreateControls(final Composite mainComposite) {
        final GridLayout gridLayout = new GridLayout(3, false);
        mainComposite.setLayout(gridLayout);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Composite buttonComposite = getWidgetFactory().createComposite(mainComposite);
        GridDataFactory.fillDefaults().span(3, 1).applyTo(buttonComposite);
        GridLayoutFactory.swtDefaults().numColumns(2).applyTo(buttonComposite);
        final Button fetchContactButton = getWidgetFactory().createButton(buttonComposite, Messages.fetchContract, SWT.FLAT);
        GridDataFactory.swtDefaults().indent(10, 0).applyTo(fetchContactButton);
        fetchContactButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                try {
                    fetchContractOperation.setCallActivity((CallActivity) getEObject());
                    progressService.run(true, false, fetchContractOperation);
                    for (final ContractInput input : fetchContractOperation.getResult()) {
                        createInputMapping(null, InputMappingAssignationType.CONTRACT_INPUT, input.getName());
                    }
                } catch (final InvocationTargetException ex) {
                    MessageDialogWithLink.openWarning(mainComposite.getShell(), Messages.fetchContract,
                            ex.getTargetException().getMessage());
                } catch (final InterruptedException e1) {
                    BonitaStudioLog.error(e1);
                }
                refreshScrolledComposite(mainComposite);
            }
        });
        addInputMappingControl(mainComposite);

    }

    protected void refreshScrolledComposite(final Composite parent) {
        parent.getParent().getParent().layout(true, true);
        getTabbedPropertySheetPage().resizeScrolledComposite();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#createContent(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createContent(Composite parent) {
        dbc = new EMFDataBindingContext();
        mainComposite = getWidgetFactory().createComposite(parent);
        doCreateControls(mainComposite);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (dbc != null) {
            dbc.dispose();
        }
    }

    private void addInputMappingControl(final Composite parent) {
        final Composite composite = getWidgetFactory().createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        inputMappingControl = new MagicComposite(composite, SWT.NONE);
        inputMappingControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        getWidgetFactory().adapt(inputMappingControl);
        inputMappingControl.setLayout(GridLayoutFactory.fillDefaults().numColumns(4).margins(0, 0).create());

        final Label sourceParameterLabel = getWidgetFactory().createLabel(inputMappingControl, Messages.dataFromRootProcess);
        sourceParameterLabel.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).indent(15, 0).create());

        final Label targetParameterLabel = getWidgetFactory().createLabel(inputMappingControl, Messages.dataInCalledProcess);
        targetParameterLabel.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).create());

        final IObservableValue inputMappibngsObservable = CustomEMFEditObservables.observeDetailValue(Realm.getDefault(),
                ViewersObservables.observeSingleSelection(selectionProvider),
                ProcessPackage.Literals.CALL_ACTIVITY__INPUT_MAPPINGS);
        dbc.bindValue(SWTObservables.observeVisible(sourceParameterLabel), inputMappibngsObservable,
                neverUpdateValueStrategy().create(), updateValueStrategy().withConverter(hideIfEmpty()).create());
        dbc.bindValue(SWTObservables.observeVisible(targetParameterLabel), inputMappibngsObservable,
                neverUpdateValueStrategy().create(), updateValueStrategy().withConverter(hideIfEmpty()).create());

        final Button addLineButton = getWidgetFactory().createButton(composite, Messages.Add, SWT.FLAT);
        addLineButton.setLayoutData(
                GridDataFactory.swtDefaults().hint(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).indent(15, 0).create());
        addLineButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_ADD_INPUT);
        addLineButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                createInputMapping(null, InputMappingAssignationType.CONTRACT_INPUT, null);
                refreshScrolledComposite(parent);
            }
        });

    }

    private IConverter hideIfEmpty() {
        return new Converter(List.class, Boolean.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject instanceof List) {
                    return !((List<?>) fromObject).isEmpty();
                }
                return Boolean.FALSE;
            }
        };
    }

    protected void createInputMapping(final Data source, final InputMappingAssignationType assignationType,
            final String target) {
        final InputMapping mapping = ProcessFactory.eINSTANCE.createInputMapping();
        mapping.setProcessSource(
                source == null ? ExpressionHelper.createConstantExpression("", String.class.getName())
                        : ExpressionHelper.createVariableExpression(source));
        if (target != null) {
            mapping.setSubprocessTarget(target);
        }
        mapping.setAssignationType(assignationType);
        final CallActivity callActivity = (CallActivity) selectionProvider.getAdapter(EObject.class);
        getEditingDomain().getCommandStack()
                .execute(new AddCommand(getEditingDomain(), callActivity.getInputMappings(), mapping));
        addInputMappingLine(inputMappingControl, mapping);
    }

    protected void addInputMappingLine(final Composite outputMappingControl, final InputMapping mapping) {
        final ExpressionViewer srcCombo = createInputMappingExpressionViewer(outputMappingControl, mapping);
        final Combo assignationTypeCombo = createInputMappingAssignedToCombo(outputMappingControl, mapping);
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
                            new SetCommand(getEditingDomain(), mapping,
                                    ProcessPackage.Literals.INPUT_MAPPING__ASSIGNATION_TYPE, newValue));
                    targetCombo.removeAll();
                    updateAvailableValuesInputMappingTargetCombo(targetCombo, newValue);
                }
            }
        });

        final Button deleteButton = getWidgetFactory().createButton(outputMappingControl, null, SWT.FLAT);
        deleteButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_DELETE_INPUT);
        deleteButton.setImage(sharedImages.getImage(ISharedImages.IMG_TOOL_DELETE));
        deleteButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final CallActivity callActivity = (CallActivity) selectionProvider.getAdapter(EObject.class);
                final RemoveCommand command = new RemoveCommand(getEditingDomain(), callActivity.getInputMappings(),
                        mapping);
                getEditingDomain().getCommandStack().execute(command);
                srcCombo.getControl().setData(MagicComposite.HIDDEN, true);
                srcCombo.getControl().setVisible(false);
                assignationTypeCombo.setData(MagicComposite.HIDDEN, true);
                assignationTypeCombo.setVisible(false);
                targetCombo.setData(MagicComposite.HIDDEN, true);
                targetCombo.setVisible(false);
                deleteButton.setData(MagicComposite.HIDDEN, true);
                deleteButton.setVisible(false);
                getTabbedPropertySheetPage().resizeScrolledComposite();
                refreshScrolledComposite(mainComposite);
            }
        });
    }

    protected CCombo createInputMappingTargetCombo(final Composite outputMappingControl, final InputMapping mapping) {
        final CCombo targetCombo = getWidgetFactory().createCCombo(outputMappingControl, SWT.BORDER);
        final InputMappingAssignationType assignationType = mapping.getAssignationType();
        updateAvailableValuesInputMappingTargetCombo(targetCombo, assignationType);
        final GridData layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false);
        targetCombo.setLayoutData(layoutData);
        targetCombo.addListener(SWT.Modify, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                getEditingDomain().getCommandStack().execute(
                        new SetCommand(getEditingDomain(), mapping, ProcessPackage.Literals.INPUT_MAPPING__SUBPROCESS_TARGET,
                                targetCombo.getText()));
            }
        });
        if (mapping.getSubprocessTarget() != null) {
            targetCombo.setText(mapping.getSubprocessTarget());
        }
        targetCombo.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_CALLEDTARGET);
        return targetCombo;
    }

    protected void updateAvailableValuesInputMappingTargetCombo(final CCombo targetCombo,
            final InputMappingAssignationType assignationType) {
        if (InputMappingAssignationType.DATA == assignationType) {
            for (final Data subprocessData : callActivityHelper.getCallActivityData()) {
                targetCombo.add(subprocessData.getName());
            }
        } else {
            for (final String contractInputOfCalledActivity : callActivityHelper.getCallActivityContractInput()) {
                targetCombo.add(contractInputOfCalledActivity);
            }
        }
    }

    protected Combo createInputMappingAssignedToCombo(final Composite outputMappingControl, final InputMapping mapping) {
        final Combo assignationTypeCombo = new Combo(outputMappingControl, SWT.BORDER | SWT.READ_ONLY);
        getWidgetFactory().adapt(assignationTypeCombo);
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
        assignationTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
        assignationTypeCombo.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                SWTBotConstants.SWTBOT_ID_CALLACTIVITY_MAPPING_INPUT_ASSIGNATIONTYPE);
        return assignationTypeCombo;
    }

    private ExpressionViewer createInputMappingExpressionViewer(final Composite outputMappingControl,
            final InputMapping mapping) {
        final ExpressionViewer srcCombo = new ExpressionViewer(outputMappingControl, SWT.BORDER, getWidgetFactory());
        srcCombo.getControl().setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false)
                .hint(250, SWT.DEFAULT).create());
        srcCombo.addFilter(
                new AvailableExpressionTypeFilter(ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE,
                        ExpressionConstants.SCRIPT_TYPE, ExpressionConstants.PARAMETER_TYPE,
                        ExpressionConstants.DOCUMENT_TYPE));

        dbc.bindValue(ViewersObservables.observeInput(srcCombo),
                ViewersObservables.observeSingleSelection(selectionProvider));
        if (mapping.getProcessSource() == null) {
            getEditingDomain().getCommandStack().execute(
                    SetCommand.create(getEditingDomain(), mapping, ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE,
                            ExpressionHelper.createConstantExpression("", String.class.getName())));
        }
        dbc.bindValue(ViewersObservables.observeSingleSelection(srcCombo),
                EMFObservables.observeValue(mapping, ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE));
        return srcCombo;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.callActivity.ParametersMappingSection#getSectionDescription()
     */
    @Override
    public String getSectionDescription() {
        return Messages.dataToSendSectionDescription;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        ISelection current = selectionProvider.getSelection();
        selectionProvider.setSelection(selection);

        if (!Objects.equals(current, selectionProvider.getSelection())) {
            BusyIndicator.showWhile(Display.getDefault(), () -> {
                /* Dispose and then redraw */
                for (final Control c : mainComposite.getChildren()) {
                    c.dispose();
                }
                doCreateControls(mainComposite);
                /* Fill with existing in/out mappings */
                updateMappings();
            });
        }
    }
}
