/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.message;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.EMFListFeatureTreeContentProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.properties.sections.message.commands.DeleteMessageCommand;
import org.bonitasoft.studio.properties.sections.message.wizards.UpdateMessageEventWizard;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @author Romain Bioteau
 */
public abstract class ThrowEventSection extends AbstractBonitaDescriptionSection {

    public static final int LIST_HEIGHT = 170;
    private FilteredTree filteredTree;
    private Button removeEventButton;
    private Button updateEventButton;

    private final NotificationListener notificationListener = new NotificationListener() {

        @Override
        public void notifyChanged(final Notification notification) {
            refresh();
        }
    };
    protected TabbedPropertySheetPage aTabbedPropertySheetPage;
    private Button addEventButton;

    @Override
    public void refresh() {
        super.refresh();
        if (filteredTree != null) {
            if (getEObject() instanceof Node) {
                final Node node = (Node)getEObject();
                filteredTree.getViewer().setInput(node.getElement());
            } else {
                if(getEObject() != null && getEObject() instanceof ThrowMessageEvent) {
                    filteredTree.getViewer().setInput(getEObject());
                }
            }
        }
        updateButtons();
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        if(getEObject() != null && getEObject() instanceof ConnectableElement){
            DiagramEventBroker.getInstance(getEditingDomain()).addNotificationListener(getEObject(),ProcessPackage.eINSTANCE.getConnectableElement_Connectors(),notificationListener);
        }
    }

    @Override
    protected void createContent(final Composite parent) {
        doCreateControls(parent);
    }


    private void doCreateControls(final Composite parent) {
        final Composite mainComposite = getWidgetFactory().createPlainComposite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(20, 20).create());

        final Composite buttonsComposite = getWidgetFactory().createPlainComposite(mainComposite, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());
        addEventButton = createAddEventButton(buttonsComposite);
        updateEventButton = createUpdateConnectorButton(buttonsComposite);
        removeEventButton = createRemoveConnectorButton(buttonsComposite);

        filteredTree = new FilteredTree(mainComposite, SWT.BORDER | SWT.MULTI, new PatternFilter(), true);
        getWidgetFactory().adapt(filteredTree);
        filteredTree.setLayoutData(new GridData(300, LIST_HEIGHT));
        filteredTree.getViewer().setContentProvider(new EMFListFeatureTreeContentProvider(ProcessPackage.Literals.THROW_MESSAGE_EVENT__EVENTS));
        filteredTree.getViewer().setLabelProvider(new EventLabelProvider());
        filteredTree.getViewer().addDoubleClickListener(new IDoubleClickListener() {
            @Override
            public void doubleClick(final DoubleClickEvent event) {
                updateEventAction();
            }
        });
        filteredTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                updateButtons();
            }
        });


    }

    /**
     *
     */
    private void updateButtons() {
        final ITreeSelection selection = (ITreeSelection)filteredTree.getViewer().getSelection();
        if(!removeEventButton.isDisposed()) {
            removeEventButton.setEnabled(selection.size() > 0);
        }

        if(!updateEventButton.isDisposed()) {
            updateEventButton.setEnabled(selection.size() == 1);
        }

        if(eObject instanceof SendTask){
            if(!addEventButton.isDisposed()) {
                addEventButton.setEnabled(((SendTask) eObject).getEvents().isEmpty());
            }
        }

    }


    private Button createUpdateConnectorButton(final Composite buttonsComposite) {
        final Button updateButton = getWidgetFactory().createButton(buttonsComposite, Messages.updateConnector, SWT.FLAT);
        updateButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        updateButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                updateEventAction();
            }
        });
        return updateButton;
    }

    private Button createRemoveConnectorButton(final Composite buttonsComposite) {
        final Button removeButton = getWidgetFactory().createButton(buttonsComposite, Messages.removeConnector, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        removeButton.addListener(SWT.Selection, new Listener() {

            @Override
            @SuppressWarnings("unchecked")
            public void handleEvent(final Event event) {
                final ITreeSelection selection = (ITreeSelection)filteredTree.getViewer().getSelection();
                if (MessageDialog.openConfirm(buttonsComposite.getShell(), Messages.removeEventConfirmTitle, createMessage(selection))) {
                    final DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
                    MessageFlowFactory.removeMessageFlow(getEditingDomain(),selection.toList(), getThrowMessageEvent(),editor.getDiagramEditPart()) ;
                    final IUndoableOperation operation = new DeleteMessageCommand(getEditingDomain(), (ThrowMessageEvent)getEObject(), selection.toList(), ThrowEventSection.this);
                    try {
                        OperationHistoryFactory.getOperationHistory().execute(operation, new NullProgressMonitor(), null);
                    } catch (final ExecutionException e) {
                        BonitaStudioLog.error(e);
                    }


                    refresh();
                }
            }

            private String createMessage(final ITreeSelection selection) {
                final StringBuilder sb = new StringBuilder();
                sb.append(Messages.removeEventConfirmMessage);
                for (final Object item : selection.toList()) {
                    final Message event = (Message)item;
                    sb.append('\n');
                    sb.append(event.getName());
                    sb.append(", "); //$NON-NLS-1$
                }
                sb.delete(sb.length() - 2, sb.length() - 1);
                sb.append('.');
                return sb.toString();
            }
        });
        return removeButton;
    }



    private Button createAddEventButton(final Composite buttonsComposite) {
        final Button addMessageEventButton = getWidgetFactory().createButton(buttonsComposite, Messages.addConnector, SWT.FLAT);
        addMessageEventButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(85, SWT.DEFAULT).create());
        addMessageEventButton.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                new WizardDialog(ThrowEventSection.this.getPart().getSite().getShell(),
                        createMessageEventWizard(ModelHelper.getMainProcess(getEObject()),null)).open();
                refresh();
            }
        });
        addMessageEventButton.setToolTipText(Messages.connectorAdd_tooltip);
        return addMessageEventButton;
    }

    protected abstract UpdateMessageEventWizard createMessageEventWizard(MainProcess diagram,Message message);

    /**
     * @return
     */
    protected Message getSelectedEvent() {
        return (Message)  ((IStructuredSelection)filteredTree.getViewer().getSelection()).getFirstElement();
    }

    /**
     * @return
     */
    protected ThrowMessageEvent getThrowMessageEvent() {
        return (ThrowMessageEvent)getEObject();
    }

    /**
     *
     */
    private void updateEventAction() {
        final ITreeSelection selection = (ITreeSelection)filteredTree.getViewer().getSelection();
        if (selection.size() == 1) {
            new WizardDialog(Display.getCurrent().getActiveShell(),
                    createMessageEventWizard(ModelHelper.getMainProcess(getEObject()),(Message)selection.getFirstElement())).open();
            refresh();


        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if(getEObject() != null){
            DiagramEventBroker.getInstance(getEditingDomain()).removeNotificationListener(getEObject(),notificationListener);
            for(final Message event : ((ThrowMessageEvent)getEObject()).getEvents()){
                DiagramEventBroker.getInstance(getEditingDomain()).removeNotificationListener(event,notificationListener);
            }
        }
    }

    @Override
    public String getSectionDescription() {
        return Messages.messagesSectionDescription;
    }

}
