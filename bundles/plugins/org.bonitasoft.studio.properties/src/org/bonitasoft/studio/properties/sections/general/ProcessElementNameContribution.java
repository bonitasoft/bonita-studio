/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.general;

import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.forbiddenCharactersValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.minMaxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.utf8InputValidator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.diagram.Identifier;
import org.bonitasoft.studio.common.diagram.dialog.OpenNameAndVersionDialog;
import org.bonitasoft.studio.common.diagram.dialog.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.diagram.dialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractNamePropertySectionContribution;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolEditPart;
import org.bonitasoft.studio.diagram.custom.refactoring.ProcessNamingTools;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.operation.RenameDiagramOperation;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Mickael Istria
 * @author Romain Bioteau
 */
public class ProcessElementNameContribution extends AbstractNamePropertySectionContribution {

    protected ISWTObservableValue observable;
    private UpdateValueStrategy labelModelToTargetUpdate;
    private UpdateValueStrategy labelTargetToModelUpdate;
    private boolean bindingInitialized = false;
    private ProcessNamingTools processNamingTools;

    private final NotificationListener updateMessage = new NotificationListener() {

        @Override
        public void notifyChanged(final Notification notification) {
            final List<AbstractCatchMessageEvent> messages = ModelHelper.getAllItemsOfType(element,
                    ProcessPackage.eINSTANCE.getAbstractCatchMessageEvent());
            for (final AbstractCatchMessageEvent m : messages) {
                final String eventName = m.getEvent();
                final Message event = ModelHelper.findEvent(ModelHelper.getMainProcess(element), eventName);
                if (event != null) {
                    EMFModelUpdater<EObject> updater = new EMFModelUpdater<>().from(event.getTargetProcessExpression());
                    Expression newExpression = ExpressionHelper.createConstantExpression(element.getName(),
                            String.class.getName());
                    newExpression.setReturnTypeFixed(event.getTargetProcessExpression().isReturnTypeFixed());
                    updater.editWorkingCopy(newExpression);
                    editingDomain.getCommandStack().execute(updater.createUpdateCommand(editingDomain));
                }
            }
        }
    };

    /**
     * @param tabbedPropertySheetPage
     */
    public ProcessElementNameContribution(final TabbedPropertySheetPage tabbedPropertySheetPage) {
        super(tabbedPropertySheetPage);
    }

    /*
     * (non-Javadoc)
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.GeneralSection_Name;
    }

    /*
     * (non-Javadoc)
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(final EObject object) {
        if (object instanceof Lane) {
            element = ModelHelper.getParentProcess(object);
        } else {
            element = (Element) object;
        }

        if (element instanceof MainProcess) {
            updateBindings();
        }

        if (element instanceof Pool) {
            activateNameListener();
        }

    }

    protected void activateNameListener() {
        if (editingDomain != null) {
            DiagramEventBroker.getInstance(editingDomain).addNotificationListener(element,
                    ProcessPackage.eINSTANCE.getElement_Name(), updateMessage);
        }
    }

    /*
     * (non-Javadoc)
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(final ISelection selection) {
        if (((StructuredSelection) selection).getFirstElement() instanceof LaneEditPart) {
            this.selection = new StructuredSelection(
                    ((LaneEditPart) ((StructuredSelection) selection)
                            .getFirstElement()).getParent());
        }
        this.selection = selection;
    }

    @Override
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        super.setEditingDomain(editingDomain);
        processNamingTools = new ProcessNamingTools(editingDomain);
    }

    private void updateBindings() {
        if (bindingInitialized) {
            if (text != null && !text.isDisposed()) {
                final int start = text.getSelection().x;
                context.dispose();
                context = new EMFDataBindingContext();
                context.bindValue(observable,
                        EMFEditObservables.observeValue(editingDomain, element, ProcessPackage.Literals.ELEMENT__NAME),
                        labelTargetToModelUpdate, labelModelToTargetUpdate);
                text.setSelection(start);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        if (element instanceof Pool) {
            deactivateNameListener();
        }
    }

    protected void deactivateNameListener() {
        if (editingDomain != null) {
            DiagramEventBroker.getInstance(editingDomain).removeNotificationListener(element,
                    ProcessPackage.eINSTANCE.getElement_Name(), updateMessage);
        }
    }

    @Override
    protected void createBinding(final EMFDataBindingContext context) {
        observable = SWTObservables.observeDelayedValue(250, SWTObservables.observeText(text, SWT.Modify));

        final IObservableValue nameObservable = EMFEditObservables.observeValue(editingDomain, element,
                ProcessPackage.Literals.ELEMENT__NAME);
        nameObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                updatePropertyTabTitle();
                updatePartName((String) event.diff.getNewValue(), Display.getDefault());
            }
        });
        context.bindValue(observable,
                nameObservable);
        final MultiValidator validationStatusProvider = nameValidationStatusProvider(observable);
        context.addValidationStatusProvider(validationStatusProvider);
        ControlDecorationSupport.create(validationStatusProvider, SWT.LEFT);
        bindingInitialized = true;
    }

    protected MultiValidator nameValidationStatusProvider(final IObservableValue nameTextObservable) {
        return new MultiValidator() {

            @Override
            protected IStatus validate() {
                return multiValidator()
                        .addValidator(minMaxLengthValidator(Messages.name, element instanceof SequenceFlow ? 0 : 1, 255))
                        .addValidator(utf8InputValidator(Messages.name))
                        .addValidator(forbiddenCharactersValidator(Messages.name, '#', '%', '$'))
                        .addValidator(ValidatorFactory.reservedRESTAPIKeywordsValidator()).create()
                        .validate(nameTextObservable.getValue());
            }
        };
    }

    protected void updatePartName(final String name, final Display display) {
        if (selection != null && !selection.isEmpty()) {
            final ITextAwareEditPart textAwareEditPart = getTextAwareEditPart((IStructuredSelection) selection);
            if (textAwareEditPart != null) {
                textAwareEditPart.setLabelText(name);
                display.asyncExec(refreshPoolEditPart(textAwareEditPart));
            }
        }
    }

    protected Runnable refreshPoolEditPart(final ITextAwareEditPart textAwareEditPart) {
        return new Runnable() {

            @Override
            public void run() {
                final EditPart poolEp = getPoolEditPart(textAwareEditPart);
                if (poolEp != null) {
                    poolEp.refresh();
                }
            }
        };
    }

    protected EditPart getPoolEditPart(final EditPart textAwareEditPart) {
        EditPart poolEp = textAwareEditPart;
        while (poolEp != null && !(poolEp instanceof CustomPoolEditPart)) {
            if (poolEp.getParent() instanceof EditPart) {
                poolEp = poolEp.getParent();
            } else {
                poolEp = null;
            }
        }
        return poolEp;
    }

    protected ITextAwareEditPart getTextAwareEditPart(final IStructuredSelection selection) {
        final Object ep = selection.getFirstElement();
        ITextAwareEditPart textAwareEditPart = null;
        if (ep instanceof IGraphicalEditPart && element.equals(((IGraphicalEditPart) ep).resolveSemanticElement())) {
            if (ep instanceof ITextAwareEditPart) {
                textAwareEditPart = (ITextAwareEditPart) ep;
            }
            for (final Object child : ((IGraphicalEditPart) ep).getChildren()) {
                if (child instanceof ITextAwareEditPart) {
                    textAwareEditPart = (ITextAwareEditPart) child;
                }
            }
        }
        return textAwareEditPart;
    }

    @Override
    protected void editProcessNameAndVersion() {
        if (element instanceof Pool) {
            editSinglePoolNameAndVersion((Pool) element);
        } else {
            editDiagramAndPoolNameAndVersion();
        }
    }

    protected void editDiagramAndPoolNameAndVersion() {
        final MainProcess diagram = ModelHelper.getMainProcess(element);
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        final OpenNameAndVersionForDiagramDialog nameDialog = new OpenNameAndVersionForDiagramDialog(
                Display.getDefault().getActiveShell(), diagram,
                diagramStore);
        if (nameDialog.open() == Dialog.OK) {
            final DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .getActiveEditor();
            final MainProcess newProcess = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement();
            editingDomain.getCommandStack().execute(
                    SetCommand.create(editingDomain, newProcess, ProcessPackage.Literals.ABSTRACT_PROCESS__AUTHOR,
                            System.getProperty("user.name", "Unknown")));
            final String oldName = newProcess.getName();
            final String oldVersion = newProcess.getVersion();
            if (new Identifier(oldName, oldVersion).equals(nameDialog.getIdentifier())) {
                renamePoolsOnly(nameDialog, editor, newProcess);
            } else {
                editor.doSave(Repository.NULL_PROGRESS_MONITOR);
                renameDiagramAndPool(nameDialog, editor, newProcess);
            }
        }
    }

    protected void renameDiagramAndPool(final OpenNameAndVersionForDiagramDialog nameDialog, final DiagramEditor editor,
            final MainProcess newProcess) {
        final RenameDiagramOperation renameDiagramOperation = new RenameDiagramOperation();
        final Identifier identifier = nameDialog.getIdentifier();
        renameDiagramOperation.setDiagramToDuplicate(newProcess);
        renameDiagramOperation.setNewDiagramName(identifier.getName());
        renameDiagramOperation.setNewDiagramVersion(identifier.getVersion());
        renameDiagramOperation.setPoolsRenamed(nameDialog.getPools());
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        try {
            service.run(false, false, renameDiagramOperation);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected void editSinglePoolNameAndVersion(final Pool pool) {
        final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        final OpenNameAndVersionDialog dialog1 = new OpenNameAndVersionDialog(Display.getDefault().getActiveShell(), pool,
                diagramStore);
        if (dialog1.open() == Dialog.OK) {
            final String oldPoolName = element.getName();
            final String oldVersion = ((Pool) element).getVersion();
            final Identifier identifier = dialog1.getIdentifier();
            processNamingTools.proceedForPools(element, identifier.getName(), oldPoolName, oldVersion,
                    identifier.getVersion());
        }
    }

    protected void renamePoolsOnly(final OpenNameAndVersionForDiagramDialog nameDialog, final DiagramEditor editor,
            final MainProcess newProcess) {
        editor.doSave(Repository.NULL_PROGRESS_MONITOR);
        final Identifier identifier = nameDialog.getIdentifier();
        processNamingTools.changeProcessNameAndVersion(newProcess, identifier.getName(), identifier.getVersion());
        for (final ProcessesNameVersion pnv : nameDialog.getPools()) {
            processNamingTools.changeProcessNameAndVersion(pnv.getAbstractProcess(), pnv.getNewName(), pnv.getNewVersion());
        }
        try {
            final ICommandService service = PlatformUI.getWorkbench().getService(ICommandService.class);
            final org.eclipse.core.commands.Command c = service.getCommand("org.eclipse.ui.file.save");
            if (c.isEnabled()) {
                c.executeWithChecks(new ExecutionEvent());
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

}
