/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.properties.sections.message;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 */
public class CatchEventEventSelectionContribution implements
        IExtensibleGridPropertySectionContribution {

    private ComboViewer combo;
    private AbstractCatchMessageEvent eObject;
    private TransactionalEditingDomain editingDomain;
    private IGraphicalEditPart messageEventPart;
    private String oldEventName;
    private final NotificationListener updateSourceEventListener = new NotificationListener() {

        @Override
        public void notifyChanged(final Notification notification) {
            final String eventName = eObject.getEvent();
            final Message event = ModelHelper.findEvent(eObject, eventName);
            if (event != null) {
                EMFModelUpdater<EObject> updater = new EMFModelUpdater<>().from(event.getTargetElementExpression());
                Expression newExpression = ExpressionHelper.createConstantExpression(eObject.getName(),
                        String.class.getName());
                newExpression.setReturnTypeFixed(event.getTargetElementExpression().isReturnTypeFixed());
                updater.editWorkingCopy(newExpression);
                editingDomain.getCommandStack().execute(updater.createUpdateCommand(editingDomain));
            }
        }
    };

    @Override
    public void createControl(final Composite composite,
            final TabbedPropertySheetWidgetFactory widgetFactory,
            final ExtensibleGridPropertySection extensibleGridPropertySection) {
        combo = new ComboViewer(composite, SWT.NONE);
        combo.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        combo.setLabelProvider(new EventLabelProvider());
        combo.setContentProvider(ArrayContentProvider.getInstance());
        combo.addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                if (element instanceof EventObject) {
                    final ThrowMessageEvent source = ((Message) element).getSource();
                    if (eObject != null) {
                        if (source != null) {
                            final AbstractProcess parentProcessOfSourceMessage = ModelHelper.getParentProcess(source);
                            if (parentProcessOfSourceMessage != null
                                    && parentProcessOfSourceMessage.equals(ModelHelper.getParentProcess(eObject))) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        });
        final List<Message> events = retrievePossibleMessageEvents();

        combo.setInput(events);
        if (eObject.getEvent() != null) {
            combo.getCombo().setText(eObject.getEvent());
            oldEventName = combo.getCombo().getText();
        }
        combo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent arg0) {
                handleSelectionChanged();
            }
        });

        combo.getCombo().addListener(SWT.Modify, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                handleSelectionChanged();
            }
        });
    }

    private List<Message> retrievePossibleMessageEvents() {
        final List<Message> events = new ArrayList<>();
        ModelHelper.findAllEvents(ModelHelper.getMainProcess(eObject), events);

        // remove messages that source(throwMessage) are on the same process as eObject(catchMessage)
        final AbstractProcess parentProcess = ModelHelper.getParentProcess(eObject);
        final List<Message> eventsToRemove = retrieveMessageEventsFromThePool(events, parentProcess);
        events.removeAll(eventsToRemove);
        return events;
    }

    private List<Message> retrieveMessageEventsFromThePool(final List<Message> events, final AbstractProcess parentProcess) {
        final List<Message> eventsToRemove = new ArrayList<>();
        for (final Message message : events) {
            if (ModelHelper.getParentProcess(message).equals(parentProcess)) {
                eventsToRemove.add(message);
            }
        }
        return eventsToRemove;
    }

    protected void handleSelectionChanged() {
        final String eventName = combo.getCombo().getText();

        final Message event = ModelHelper.findEvent(eObject, eventName);

        final DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();

        final boolean eventsOnSameDiagram = event != null && event.eResource().equals(eObject.eResource());

        // Delete old
        if ((event == null || eventsOnSameDiagram) && eObject.getIncomingMessag() != null) {
            MessageFlowFactory.removeMessageFlow(editingDomain, eObject, editor.getDiagramEditPart());
        }

        // Set
        final CompoundCommand cc = new CompoundCommand();
        final SetCommand setCommand = new SetCommand(editingDomain, eObject,
                ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT, eventName);
        cc.append(setCommand);
        editingDomain.getCommandStack().execute(setCommand);

        //Update event reference

        for (final AbstractCatchMessageEvent ev : ModelHelper.getAllCatchEvent(ModelHelper.getMainProcess(eObject))) {
            if (ev.getEvent() != null && eventName != null && ev.getEvent().equals(eventName)) {
                if (!ev.equals(eObject)) {
                    final SetCommand cmd = new SetCommand(editingDomain, ev,
                            ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT, null);
                    editingDomain.getCommandStack().execute(cmd);
                }
                if (oldEventName != null) {
                    final Message oldEvent = ModelHelper.findEvent(ModelHelper.getMainProcess(eObject), oldEventName);
                    SetCommand cmd = new SetCommand(editingDomain, oldEvent,
                            ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION, null);
                    editingDomain.getCommandStack().execute(cmd);
                    cmd = new SetCommand(editingDomain, oldEvent, ProcessPackage.Literals.MESSAGE__TARGET_ELEMENT_EXPRESSION,
                            null);
                    editingDomain.getCommandStack().execute(cmd);
                }
                MessageFlowFactory.removeMessageFlow(editingDomain, ev, editor.getDiagramEditPart());

            }
        }

        // Add new
        if (eventsOnSameDiagram) {
            final AbstractCatchMessageEvent catchMessage = (AbstractCatchMessageEvent) messageEventPart
                    .resolveSemanticElement();
            final String procName = ModelHelper.getParentProcess(catchMessage).getName();
            SetCommand cmd = new SetCommand(editingDomain, event, ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION,
                    ExpressionHelper.createConstantExpression(procName, String.class.getName()));
            editingDomain.getCommandStack().execute(cmd);
            cmd = new SetCommand(editingDomain, event, ProcessPackage.Literals.MESSAGE__TARGET_ELEMENT_EXPRESSION,
                    ExpressionHelper.createConstantExpression(
                            catchMessage.getName(), String.class.getName()));
            editingDomain.getCommandStack().execute(cmd);
            MessageFlowFactory.createMessageFlow(editingDomain, event, (ThrowMessageEvent) event.eContainer(),
                    (AbstractCatchMessageEvent) messageEventPart.resolveSemanticElement(), editor.getDiagramEditPart());
        }

        oldEventName = eventName;

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {
        deactivateNameListener();
    }

    protected void deactivateNameListener() {
        if (editingDomain != null) {
            DiagramEventBroker.getInstance(editingDomain).removeNotificationListener(eObject,
                    ProcessPackage.eINSTANCE.getElement_Name(),
                    updateSourceEventListener);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.selectMessageEventLabel;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore
     * .EObject)
     */
    @Override
    public boolean isRelevantFor(final EObject eObject) {
        return eObject instanceof AbstractCatchMessageEvent;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#refresh()
     */
    @Override
    public void refresh() {
        if (combo != null && !combo.getCombo().isDisposed()) {
            oldEventName = combo.getCombo().getText();
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.
     * EObject)
     */
    @Override
    public void setEObject(final EObject object) {
        eObject = (AbstractCatchMessageEvent) object;
        activateNameListener();
    }

    private void activateNameListener() {
        DiagramEventBroker.getInstance(editingDomain).addNotificationListener(eObject,
                ProcessPackage.eINSTANCE.getElement_Name(), updateSourceEventListener);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.
     * transaction.TransactionalEditingDomain
     * )
     */
    @Override
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setSelection(org.eclipse.jface.
     * viewers.ISelection)
     */
    @Override
    public void setSelection(final ISelection selection) {
        final Object sel = ((StructuredSelection) selection).getFirstElement();
        if (sel instanceof IGraphicalEditPart) {
            messageEventPart = (IGraphicalEditPart) sel;
        }

    }

}
