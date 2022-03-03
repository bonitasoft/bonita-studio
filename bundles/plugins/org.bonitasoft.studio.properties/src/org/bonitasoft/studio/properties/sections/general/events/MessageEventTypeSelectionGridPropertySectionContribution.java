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
package org.bonitasoft.studio.properties.sections.general.events;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.diagram.custom.BonitaNodesElementTypeResolver;
import org.bonitasoft.studio.model.process.MessageEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 *
 */
public class MessageEventTypeSelectionGridPropertySectionContribution
	implements IExtensibleGridPropertySectionContribution {

    public static final String MESSAGE_EVENT_TYPE__START = Messages.eventType_messageStart;
    public static final String MESSAGE_EVENT_TYPE__INTERMEDIATE_CATCH = Messages.eventType_intermediateCatch;
    private static final String MESSAGE_EVENT_TYPE__END = Messages.eventType_messageEnd;
    private static final String MESSAGE_EVENT_TYPE__INTERMEDIATE_THROW = Messages.eventType_intermediateThrow;
    private Combo combo;
    private MessageEvent event;
    private GraphicalEditPart node;
    private TabbedPropertySheetPage tabbedPropertySheetPage;

    public MessageEventTypeSelectionGridPropertySectionContribution(TabbedPropertySheetPage tabbedPropertySheetPage) {
	this.tabbedPropertySheetPage = tabbedPropertySheetPage;
    }

    public void refresh() {
	if (event != null && combo != null) {
	    if (event.eClass().equals(ProcessPackage.Literals.START_MESSAGE_EVENT)) {
		combo.setText(MESSAGE_EVENT_TYPE__START);
	    } else if (event.eClass().equals(ProcessPackage.Literals.INTERMEDIATE_CATCH_MESSAGE_EVENT)) {
		combo.setText(MESSAGE_EVENT_TYPE__INTERMEDIATE_CATCH);
	    } else if (event.eClass().equals(ProcessPackage.Literals.INTERMEDIATE_THROW_MESSAGE_EVENT)) {
		combo.setText(MESSAGE_EVENT_TYPE__INTERMEDIATE_THROW);
	    } else if (event.eClass().equals(ProcessPackage.Literals.END_MESSAGE_EVENT)) {
		combo.setText(MESSAGE_EVENT_TYPE__END);
	    }
	}
    }

    /**
     * @param aTabbedPropertySheetPage
     * @param parent
     */
    private void createEventSelectionCombo(Composite parent, TabbedPropertySheetWidgetFactory widgetFactory) {
	combo = new Combo(parent, SWT.READ_ONLY);
	combo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
	combo.add(MESSAGE_EVENT_TYPE__START);
	combo.add(MESSAGE_EVENT_TYPE__INTERMEDIATE_THROW);
	combo.add(MESSAGE_EVENT_TYPE__INTERMEDIATE_CATCH);
	combo.add(MESSAGE_EVENT_TYPE__END);
	combo.addModifyListener(new ModifyListener() {

	    /**
	     * @return
	     */
	    private EClass getTargetEClass() {
		if (combo.getText().equals(MESSAGE_EVENT_TYPE__INTERMEDIATE_CATCH)) {
		    return ProcessPackage.Literals.INTERMEDIATE_CATCH_MESSAGE_EVENT;
		} else if (combo.getText().equals(MESSAGE_EVENT_TYPE__START)) {
		    return ProcessPackage.Literals.START_MESSAGE_EVENT;
		} else if (combo.getText().equals(MESSAGE_EVENT_TYPE__INTERMEDIATE_THROW)) {
		    return ProcessPackage.Literals.INTERMEDIATE_THROW_MESSAGE_EVENT;
		} else if (combo.getText().equals(MESSAGE_EVENT_TYPE__END)) {
		    return ProcessPackage.Literals.END_MESSAGE_EVENT;
		}
		return ProcessPackage.Literals.INTERMEDIATE_CATCH_MESSAGE_EVENT;
	    }

	    /**
	     * @return
	     */
	    private boolean toBeConverted() {
		return !event.eClass().equals(getTargetEClass());
	    }

	    public void modifyText(ModifyEvent e) {
		if (toBeConverted()) {
		    EClass targetEClass = getTargetEClass();
		    IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
			    .getActiveEditor();
		    if (editor instanceof ProcessDiagramEditor) {
			node = (GraphicalEditPart) ((IStructuredSelection) ((ProcessDiagramEditor) editor)
				.getDiagramGraphicalViewer().getSelection()).getFirstElement();
			GMFTools.convert(targetEClass, node, new BonitaNodesElementTypeResolver(),
				GMFTools.PROCESS_DIAGRAM);
			tabbedPropertySheetPage.selectionChanged(editor,
				((IStructuredSelection) ((ProcessDiagramEditor) editor).getDiagramGraphicalViewer()
					.getSelection()));
		    }
		}

	    }

	});
    }

    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
	    ExtensibleGridPropertySection page) {
	createEventSelectionCombo(composite, widgetFactory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#getLabel()
     */
    public String getLabel() {
	return Messages.messageEventType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.
     * ecore.EObject)
     */
    public boolean isRelevantFor(EObject eObject) {
	return eObject instanceof MessageEvent && !(eObject instanceof ReceiveTask) && !(eObject instanceof SendTask);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.
     * EObject)
     */
    public void setEObject(EObject object) {
	this.event = (MessageEvent) object;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.
     * transaction.TransactionalEditingDomain)
     */
    public void setEditingDomain(TransactionalEditingDomain editingDomain) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#setSelection(org.eclipse.jface.
     * viewers.ISelection)
     */
    public void setSelection(ISelection selection) {
	if (((IStructuredSelection) selection).getFirstElement() instanceof GraphicalEditPart)
	    this.node = (GraphicalEditPart) ((IStructuredSelection) selection).getFirstElement();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#dispose()
     */
    public void dispose() {

    }

}