/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.message.wizards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.LineTableCreator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CorrelationTypeActive;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier - integrate Correlation andf use databinding
 * @author Aurelie Zara -integrate MessageContent Id and validation
 */
public class AddMessageEventWizardPage extends WizardPage implements
        IWizardPage {

    private final ThrowMessageEvent element;
    private final Message originalMessage;
    private String throwEvent;
    private GridData gd;

    private ExpressionViewer elementExpressionViewer;
    private ExpressionViewer processExpressionViewer;

    private final Message workingCopyMessage;
    protected DataBindingContext databindingContext;
    protected boolean allowSortOnCorrelation = true;
    private CatchMessageEventNamesExpressionNatureProvider catchEventNatureProvider;

    private WizardPageSupport pageSupport;

    protected MainProcess diagram;
    private Text nameText;

    /**
     * @param performer
     * @param pageName
     */
    protected AddMessageEventWizardPage(final MainProcess diagram,
            final ThrowMessageEvent element, final Message originalMessage,
            Message workingCopyMessage) {
        super(Messages.messageEventAddWizardPageName,
                Messages.messageEventAddWizardPageTitle, Pics.getWizban());
        setDescription(Messages.messageEventAddWizardPageDesc);
        this.element = element;
        this.originalMessage = originalMessage;
        if (originalMessage != null) {
            if (originalMessage.getCorrelation() == null) {
                originalMessage.setCorrelation(ProcessFactory.eINSTANCE
                        .createCorrelation());
            }
        }
        if (workingCopyMessage == null) {
            workingCopyMessage = ProcessFactory.eINSTANCE.createMessage();
        }
        if (workingCopyMessage.getCorrelation() == null) {
            workingCopyMessage.setCorrelation(ProcessFactory.eINSTANCE
                    .createCorrelation());
        }
        this.workingCopyMessage = workingCopyMessage;
        this.diagram = diagram;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        databindingContext = new DataBindingContext();

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .spacing(15, 10).create());

        createNameLine(composite);

        createDescriptionLine(composite);

        createProcessExpressionViewer(composite);

        createComboEventLine(composite);

        createTableFolder(composite);

        pageSupport = WizardPageSupport.create(this, databindingContext);
        setControl(composite);
    }

    private void createTableFolder(final Composite composite) {
        final TabFolder messageAndCorrelationFolder = new TabFolder(composite,
                SWT.NONE);
        messageAndCorrelationFolder.setLayout(GridLayoutFactory.fillDefaults()
                .create());
        messageAndCorrelationFolder.setLayoutData(GridDataFactory
                .fillDefaults().grab(true, true).span(2, 1).create());

        final TabItem messageContentTableItem = new TabItem(
                messageAndCorrelationFolder, SWT.NONE);
        messageContentTableItem.setText(Messages.addMessageContent);
        final Composite messageContentComposite = new Composite(
                messageAndCorrelationFolder, SWT.NONE);
        createMessageContentComposite(messageContentComposite);
        messageContentTableItem.setControl(messageContentComposite);

        final TabItem correlationTableItem = new TabItem(
                messageAndCorrelationFolder, SWT.NONE);
        correlationTableItem.setText(Messages.correlation);
        final Composite correlationComposite = new Composite(
                messageAndCorrelationFolder, SWT.NONE);
        createcorrelationComposite(correlationComposite);
        correlationTableItem.setControl(correlationComposite);

    }

    private void createMessageContentComposite(final Composite composite) {
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10)
                .create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true)
                .create());

        final Label messageContentDescriptionLabel = new Label(composite,
                SWT.NONE | SWT.WRAP);
        messageContentDescriptionLabel.setLayoutData(GridDataFactory
                .fillDefaults().grab(true, false).indent(12, 0).create());
        messageContentDescriptionLabel
                .setText(Messages.addMessageContentDescription);
        final List<String> captions = new ArrayList<>(2);
        captions.add(Messages.messageContentID);
        captions.add(Messages.expressionName);

        final ExpressionCollectionViewer ecv = new ExpressionCollectionViewer(composite, 0, false, 2, true, captions, false,
                false);
        ecv.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        ecv.setAddRowLabel(Messages.addMessageContentButton);
        ecv.setRemoveRowLabel(Messages.removeMessageContent);
        ecv.setLineTableCreator(new LineTableCreator() {

            @Override
            public ListExpression createListExpressionForNewLineInTable(final int size) {
                final ListExpression rowExp = ExpressionFactory.eINSTANCE.createListExpression();
                final EList<Expression> expressions = rowExp.getExpressions();
                for (int i = 0; i < size; i++) {
                    final Expression cellExpression = ExpressionFactory.eINSTANCE.createExpression();
                    if (i == 0) {
                        cellExpression.setReturnTypeFixed(true);
                    }
                    expressions.add(cellExpression);
                }
                return rowExp;
            }
        });
        addMessageContentFilters(ecv);
        ecv.setContext(element);
        ecv.setInput(element);

        final TableExpression messageContent = getMessageContentTable();
        ecv.setSelection(messageContent);

        ecv.addModifyListener(new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final IStatus status = AddMessageEventWizardPage.this
                        .validateId(workingCopyMessage.getMessageContent(),
                                Messages.addMessageContent);
                updateValidationStatus(status);
            }
        });
        ecv.setLayoutData(GridDataFactory.fillDefaults().grab(true, true)
                .create());
    }

    private void addMessageContentFilters(final ExpressionCollectionViewer ecv) {
        final List<ViewerFilter> filters = new ArrayList<>(2);
        filters.add(new AvailableExpressionTypeFilter(
                new String[] { ExpressionConstants.CONSTANT_TYPE }) {

        });
        filters.add(new AvailableExpressionTypeFilter(new String[] {
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.VARIABLE_TYPE }));
        ecv.setViewerFilters(filters);
    }

    private void createcorrelationComposite(final Composite composite) {
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10)
                .create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true)
                .create());
        final Label correlationDescriptionLabel = new Label(composite, SWT.NONE
                | SWT.WRAP);
        correlationDescriptionLabel.setLayoutData(GridDataFactory
                .fillDefaults().grab(true, false).indent(12, 0).create());
        correlationDescriptionLabel.setText(Messages.correlationDescription);
        final Button useCorrelationCheckbox = new Button(composite, SWT.CHECK);
        useCorrelationCheckbox.setLayoutData(GridDataFactory.fillDefaults()
                .grab(false, false).indent(12, 0).create());

        final ControlDecoration correlationHelp = new ControlDecoration(
                useCorrelationCheckbox, SWT.LEFT | SWT.CENTER);
        correlationHelp.setImage(PlatformUI.getWorkbench().getSharedImages()
                .getImage(ISharedImages.IMG_OBJS_INFO_TSK));
        correlationHelp.setDescriptionText(Messages.correlationKeyHelp);

        useCorrelationCheckbox.setText(Messages.useCorrelationkeys);
        useCorrelationCheckbox.setSelection(false);
        useCorrelationCheckbox.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                if (!useCorrelationCheckbox.getSelection()) {
                    workingCopyMessage.getCorrelation().setCorrelationType(
                            CorrelationTypeActive.INACTIVE);
                } else {
                    workingCopyMessage.getCorrelation().setCorrelationType(
                            CorrelationTypeActive.KEYS);
                    updateValidationStatus(AddMessageEventWizardPage.this
                            .hasAtLeastOneCorrelation(workingCopyMessage
                                    .getCorrelation()
                                    .getCorrelationAssociation()));
                }
            }
        });
        if (CorrelationTypeActive.KEYS.equals(workingCopyMessage
                .getCorrelation().getCorrelationType())) {
            useCorrelationCheckbox.setSelection(true);
            updateValidationStatus(AddMessageEventWizardPage.this
                    .hasAtLeastOneCorrelation(workingCopyMessage
                            .getCorrelation().getCorrelationAssociation()));
        } else {
            useCorrelationCheckbox.setSelection(false);
        }

        final Composite correlationComposite = new Composite(composite,
                SWT.NONE);
        correlationComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        correlationComposite.setLayout(GridLayoutFactory.fillDefaults()
                .create());

        final List<String> captions = new ArrayList<>(2);
        captions.add(Messages.correlationKey);
        captions.add(Messages.correlationValue);

        final ExpressionCollectionViewer ecv = new ExpressionCollectionViewer(
                correlationComposite, 5, false, 2, true, captions, false, true);
        ecv.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .hint(SWT.DEFAULT, 140).create());

        ecv.setAddRowLabel(Messages.AddCorrelation);
        ecv.setRemoveRowLabel(Messages.removeCorrelation);
        final List<ViewerFilter> filters = new ArrayList<>(2);
        filters.add(new AvailableExpressionTypeFilter(
                new String[] { ExpressionConstants.CONSTANT_TYPE }));
        filters.add(new AvailableExpressionTypeFilter(new String[] {
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.VARIABLE_TYPE })); // Second column has
                                                                                                                                                                                                                            // everything except
                                                                                                                                                                                                                            // Form field and
                                                                                                                                                                                                                            // simulation type
        ecv.setViewerFilters(filters);
        ecv.setContext(element);
        ecv.setInput(element);

        final TableExpression correlationAssociation = getCorrelationTable();
        ecv.setSelection(correlationAssociation);
        ecv.addModifyListener(new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final IStatus status = AddMessageEventWizardPage.this.validateId(
                        workingCopyMessage.getCorrelation()
                                .getCorrelationAssociation(),
                        Messages.correlation);
                updateValidationStatus(status);
            }
        });

        final ISWTObservableValue observeSelectionUseCorrelation = SWTObservables
                .observeSelection(useCorrelationCheckbox);
        databindingContext.bindValue(
                SWTObservables.observeEnabled(ecv.getViewer().getControl()),
                observeSelectionUseCorrelation);
        databindingContext.bindValue(
                SWTObservables.observeEnabled(ecv.getAddRowButton()),
                observeSelectionUseCorrelation);
        databindingContext.bindValue(
                SWTObservables.observeEnabled(ecv.getRemoveRowButton()),
                observeSelectionUseCorrelation);
    }

    protected TableExpression getCorrelationTable() {
        TableExpression correlationAssociation = workingCopyMessage
                .getCorrelation().getCorrelationAssociation();
        if (correlationAssociation == null) {
            workingCopyMessage.getCorrelation().setCorrelationAssociation(
                    ExpressionFactory.eINSTANCE.createTableExpression());
            correlationAssociation = workingCopyMessage.getCorrelation()
                    .getCorrelationAssociation();
        }
        return correlationAssociation;
    }

    protected void updateValidationStatus(final IStatus status) {
        if (status.isOK()) {
            setErrorMessage(null);
            final Iterator<?> it = databindingContext.getValidationStatusProviders()
                    .iterator();
            while (it.hasNext()) {
                final ValidationStatusProvider provider = (ValidationStatusProvider) it
                        .next();
                final IStatus iStatus = (IStatus) provider
                        .getValidationStatus().getValue();
                if (!iStatus.isOK()) {
                    setErrorMessage(iStatus.getMessage());
                } else {
                    setPageComplete(true);
                }
            }
            setPageComplete(isPageComplete());
        } else {
            setErrorMessage(status.getMessage());
            setPageComplete(false);
        }
    }

    protected TableExpression getMessageContentTable() {
        TableExpression messageContent = workingCopyMessage.getMessageContent();
        if (messageContent == null) {
            workingCopyMessage.setMessageContent(ExpressionFactory.eINSTANCE
                    .createTableExpression());
            messageContent = workingCopyMessage.getMessageContent();
        }
        return messageContent;
    }

    protected IStatus hasAtLeastOneCorrelation(final TableExpression expTable) {
        if (expTable == null) {
            return ValidationStatus.error(Messages.oneCorrelationAtLeastNeeded);
        } else if (expTable.getExpressions().isEmpty()) {
            return ValidationStatus.error(Messages.oneCorrelationAtLeastNeeded);
        }
        return Status.OK_STATUS;
    }

    protected IStatus validateId(final TableExpression expTable, final String tableName) {
        final Set<String> ids = new HashSet<>();
        String duplicateId = null;

        for (final ListExpression row : expTable.getExpressions()) {
            if (row.getExpressions().size() > 0) {
                final Expression expr = row.getExpressions().get(0);
                final Expression value = row.getExpressions().get(1);
                if (ExpressionConstants.CONSTANT_TYPE.equals(expr.getType())) {
                    final String id = expr.getName();
                    if (id != null && ids.contains(id)) {
                        duplicateId = id;
                        return ValidationStatus.error(Messages.bind(
                                Messages.dublicateIdErrorMessage, duplicateId,
                                tableName));
                    } else {
                        if (ids != null && id != null && !id.isEmpty()) {
                            ids.add(id);
                        }
                        if (id != null && !id.isEmpty()
                                && value.getName() == null) {
                            return ValidationStatus.error(Messages.bind(
                                    Messages.valueShouldBeDefined, id));
                        }
                        if ((id == null || id.isEmpty())
                                && value.getName() != null) {
                            return ValidationStatus
                                    .error(Messages.bind(
                                            Messages.idShouldBeDefined,
                                            value.getName()));
                        }
                    }
                }
            }
            if (tableName.equals(Messages.correlation)) {
                if (CorrelationTypeActive.KEYS.equals(workingCopyMessage
                        .getCorrelation().getCorrelationType())) {
                    if (ids.isEmpty()) {
                        return ValidationStatus
                                .error(Messages.oneCorrelationAtLeastNeeded);
                    }
                }
            }
            if (elementExpressionViewer == null
                    || elementExpressionViewer.getTextControl().getText()
                            .isEmpty()) {
                return ValidationStatus.error(Messages.eventNameLabel + " "
                        + Messages.isMandatory);
            }
            if (processExpressionViewer == null
                    || processExpressionViewer.getTextControl().getText()
                            .isEmpty()) {
                return ValidationStatus.error(Messages.processNameLabel + " "
                        + Messages.isMandatory);
            }
            if (nameText.getText() == null || nameText.getText().isEmpty()) {
                return ValidationStatus.error(Messages.emptyName);
            }
        }

        return Status.OK_STATUS;
    }

    private void createComboEventLine(final Composite composite) {
        final Label eventNameLabel = new Label(composite, SWT.NONE);
        eventNameLabel.setText(Messages.eventNameLabel + " *");
        eventNameLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());

        elementExpressionViewer = new ExpressionViewer(composite, SWT.BORDER,
                ProcessPackage.Literals.MESSAGE__TARGET_ELEMENT_EXPRESSION);
        elementExpressionViewer.getControl().setLayoutData(gd);
        elementExpressionViewer.addFilter(new AvailableExpressionTypeFilter(
                new String[] { ExpressionConstants.CONSTANT_TYPE,
                        ExpressionConstants.SCRIPT_TYPE,
                        ExpressionConstants.PARAMETER_TYPE,
                        ExpressionConstants.VARIABLE_TYPE }));
        elementExpressionViewer.setMessage(Messages.targetEventMessageHint);
        elementExpressionViewer.setMandatoryField(Messages.eventNameLabel,
                databindingContext);
        elementExpressionViewer.setContext(element);
        catchEventNatureProvider = new CatchMessageEventNamesExpressionNatureProvider();
        catchEventNatureProvider.setThrowMessage(element);
        elementExpressionViewer
                .setExpressionNatureProvider(catchEventNatureProvider);

        if (workingCopyMessage.getTargetElementExpression() == null) {
            final Expression createExpression = ExpressionFactory.eINSTANCE
                    .createExpression();
            createExpression.setReturnTypeFixed(true);
            createExpression.setReturnType(String.class.getName());
            workingCopyMessage.setTargetElementExpression(createExpression);
        }
        elementExpressionViewer.setInput(workingCopyMessage);

        refreshTargetEventContent();

        databindingContext
                .bindValue(
                        ViewersObservables
                                .observeSingleSelection(elementExpressionViewer),
                        EMFObservables
                                .observeValue(
                                        workingCopyMessage,
                                        ProcessPackage.Literals.MESSAGE__TARGET_ELEMENT_EXPRESSION));

    }

    private void createProcessExpressionViewer(final Composite composite) {
        final Label processNameLabel = new Label(composite, SWT.NONE);
        processNameLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        processNameLabel.setText(Messages.processNameLabel + " *");

        processExpressionViewer = new ExpressionViewer(composite, SWT.BORDER,
                ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        processExpressionViewer.getControl().setLayoutData(gd);
        processExpressionViewer.addFilter(new AvailableExpressionTypeFilter(
                new String[] { ExpressionConstants.CONSTANT_TYPE,
                        ExpressionConstants.SCRIPT_TYPE,
                        ExpressionConstants.PARAMETER_TYPE,
                        ExpressionConstants.VARIABLE_TYPE }));
        processExpressionViewer.setMandatoryField(Messages.processNameLabel,
                databindingContext);
        processExpressionViewer.setMessage(Messages.targetProcessMessageHint);
        final IExpressionNatureProvider provider = new ProcessNamesExpressionNatureProviderForMessage();
        processExpressionViewer.setExpressionNatureProvider(provider);
        processExpressionViewer.setContext(element);
        if (workingCopyMessage.getTargetProcessExpression() == null) {
            final Expression createExpression = ExpressionFactory.eINSTANCE
                    .createExpression();
            createExpression.setReturnTypeFixed(true);
            createExpression.setReturnType(String.class.getName());
            workingCopyMessage.setTargetProcessExpression(createExpression);
        }
        processExpressionViewer.setInput(workingCopyMessage);

        databindingContext
                .bindValue(
                        ViewersObservables
                                .observeSingleSelection(processExpressionViewer),
                        EMFObservables
                                .observeValue(
                                        workingCopyMessage,
                                        ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION));
        processExpressionViewer
                .addSelectionChangedListener(new ISelectionChangedListener() {

                    @Override
                    public void selectionChanged(final SelectionChangedEvent event) {
                        refreshTargetEventContent();
                    }
                });
    }

    private Text createDescriptionLine(final Composite composite) {
        final Label descLabel = new Label(composite, SWT.NONE);
        descLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.TOP).create());
        descLabel.setText(Messages.dataDescriptionLabel);
        final Text descText = new Text(composite, SWT.MULTI | SWT.BORDER);
        gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd.heightHint = 45;
        descText.setLayoutData(gd);
        databindingContext.bindValue(SWTObservables.observeDelayedValue(200,
                SWTObservables.observeText(descText, SWT.Modify)),
                EMFObservables.observeValue(workingCopyMessage,
                        ProcessPackage.Literals.ELEMENT__DOCUMENTATION));
        return descText;
    }

    private Text createNameLine(final Composite composite) {
        final Label nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        nameLabel.setText(Messages.dataNameLabel);

        nameText = new Text(composite, SWT.BORDER);
        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .create());

        final IValidator nameValidator = new IValidator() {

            @Override
            public IStatus validate(final Object arg0) {
                if (arg0 instanceof String) {
                    final String s = (String) arg0;
                    if (s == null || s.isEmpty()) {
                        return ValidationStatus.error(Messages.emptyName);
                    } else {
                        final List<Message> events = ModelHelper.getAllItemsOfType(
                                ModelHelper.getMainProcess(element),
                                ProcessPackage.eINSTANCE.getMessage());
                        for (final Message ev : events) {
                            if (!ev.equals(originalMessage)
                                    && ev.getName().equals(s)) {
                                return ValidationStatus
                                        .error(Messages.messageEventAddWizardNameAlreadyExists);
                            }
                        }
                    }
                }
                return ValidationStatus.ok();
            }
        };

        final UpdateValueStrategy uvs = new UpdateValueStrategy(/*
                                                                 * UpdateValueStrategy.
                                                                 * POLICY_CONVERT
                                                                 */);
        uvs.setBeforeSetValidator(nameValidator);
        databindingContext.bindValue(SWTObservables.observeDelayedValue(200,
                SWTObservables.observeText(nameText, SWT.Modify)),
                EMFObservables.observeValue(workingCopyMessage,
                        ProcessPackage.Literals.ELEMENT__NAME),
                uvs, null);
        return nameText;
    }

    /**
     * Refresh target combo content
     */
    private void refreshTargetEventContent() {
        if (processExpressionViewer.getSelection() != null
                && !processExpressionViewer.getSelection().isEmpty()) {
            final Expression procName = (Expression) ((StructuredSelection) processExpressionViewer
                    .getSelection()).getFirstElement();
            if (procName.getType().equals(ExpressionConstants.CONSTANT_TYPE)) {
                final AbstractProcess proc = getProcessOnDiagram(
                        ModelHelper.getMainProcess(element),
                        procName.getContent());
                final DiagramRepositoryStore store = RepositoryManager
                        .getInstance().getRepositoryStore(
                                DiagramRepositoryStore.class);
                final List<AbstractProcess> processes = store
                        .findProcesses(procName.getContent());
                if (proc != null) {
                    processes.add(proc);
                }
                catchEventNatureProvider.setFoundProcesses(processes);
            } else {
                catchEventNatureProvider.setFoundProcesses(null);
            }

            elementExpressionViewer.updateAutocompletionProposals();
        }
    }

    private AbstractProcess getProcessOnDiagram(final MainProcess mainProcess,
            final String procName) {
        for (final AbstractProcess proc : ModelHelper.getAllProcesses(mainProcess)) {
            if (proc.getName().equals(procName)) {
                return proc;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (pageSupport != null) {
            pageSupport.dispose();
        }
        if (databindingContext != null) {
            databindingContext.dispose();
        }
    }

    public String getThrowEvent() {
        return throwEvent;
    }

}
