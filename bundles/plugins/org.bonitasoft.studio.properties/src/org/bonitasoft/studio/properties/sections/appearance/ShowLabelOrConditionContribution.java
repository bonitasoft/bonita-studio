///**
// * Copyright (C) 2009 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.properties.sections.appearance;
//
//import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
//import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
//import org.bonitasoft.studio.model.process.SequenceFlow;
//import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowConditionEditPart;
//import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
//import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowNameEditPart;
//import org.bonitasoft.studio.properties.i18n.Messages;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.transaction.TransactionalEditingDomain;
//import org.eclipse.gef.EditPart;
//import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
//import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
//import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
//import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
//import org.eclipse.gmf.runtime.diagram.ui.requests.ChangePropertyValueRequest;
//import org.eclipse.gmf.runtime.notation.View;
//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.layout.RowLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
//
//
///**
// * @author Romain Bioteau
// */
//public class ShowLabelOrConditionContribution implements IExtensibleGridPropertySectionContribution {
//
//
//	private Button showLabelButton;
//	private ExtensibleGridPropertySection section;
//	private Button showConditionButton;
//	private Button hideLabelButton;
//
//	/**
//	 * @param generalExtensibleGridPropertySection
//	 * @param iWorkbenchPart
//	 */
//	public ShowLabelOrConditionContribution(ExtensibleGridPropertySection generalExtensibleGridPropertySection) {
//		section = generalExtensibleGridPropertySection;
//	}
//
//	public void createControl(final Composite mainComposite, TabbedPropertySheetWidgetFactory widgetFactory,
//			ExtensibleGridPropertySection extensibleGridPropertySection) {
//		/* Create a checkbox to select name or condition to display on diagram */
//		mainComposite.setLayout(new RowLayout()) ;
//		showLabelButton = widgetFactory.createButton(mainComposite, Messages.showNameLabel, SWT.RADIO);
//		showLabelButton.addSelectionListener(new SelectionAdapter() {
//
//			public void widgetSelected(SelectionEvent e) {
//				if(showLabelButton.getSelection()){
//					EditPart ep = (EditPart) section.getInput().get(0) ;
//					for(Object o : section.getInput() ){
//						if(o instanceof IGraphicalEditPart){
//							hideAllLabel((EditPart) o) ;
//							displayNameLabel((EditPart) o) ;
//						}
//					}
//					ep.getRoot().refresh() ;
//				}
//			}
//
//		});
//
//
//		showConditionButton = widgetFactory.createButton(mainComposite, Messages.showCondition, SWT.RADIO);
//		showConditionButton.addSelectionListener(new SelectionAdapter() {
//
//			public void widgetSelected(SelectionEvent e) {
//				if(showConditionButton.getSelection()){
//					EditPart ep = (EditPart) section.getInput().get(0) ;
//					for(Object o : section.getInput() ){
//						if(o instanceof IGraphicalEditPart){
//							hideAllLabel((EditPart) o) ;
//							displayConditionLabel((EditPart) o) ;
//						}
//					}
//					ep.getRoot().refresh() ;
//				}
//			}
//
//		});
//
//		hideLabelButton = widgetFactory.createButton(mainComposite, Messages.hideAllLabels, SWT.RADIO);
//		hideLabelButton.addSelectionListener(new SelectionAdapter() {
//
//			public void widgetSelected(SelectionEvent e) {
//				if(hideLabelButton.getSelection()){
//					EditPart ep = (EditPart) section.getInput().get(0) ;
//					for(Object o : section.getInput() ){
//						if(o instanceof IGraphicalEditPart){
//							hideAllLabel((EditPart) o) ;
//						}
//					}
//					ep.getRoot().refresh() ;
//				}
//			}
//
//		});
//
//		
//		for(Object sel :  section.getInput()){
//			for(Object child : ((EditPart)sel).getChildren()){
//				if(child instanceof SequenceFlowNameEditPart){
//					showLabelButton.setSelection(((View)((IGraphicalEditPart) child).getModel()).isVisible());
//				}else if(child instanceof SequenceFlowConditionEditPart){
//					showConditionButton.setSelection(((View)((IGraphicalEditPart) child).getModel()).isVisible());
//				}
//			}
//			if(!showLabelButton.getSelection() && !showConditionButton.getSelection()){
//				hideLabelButton.setSelection(true) ;
//			}else{
//				hideLabelButton.setSelection(false) ;
//			}
//		}
//
//	}
//
//	protected void hideAllLabel(EditPart editPart) {
//		if (editPart instanceof SequenceFlowEditPart) {
//			for (Object child : ((SequenceFlowEditPart) editPart).getChildren()) {
//
//				if (child instanceof SequenceFlowNameEditPart) {
//					ChangePropertyValueRequest req = new ChangePropertyValueRequest(DiagramUIMessages.Command_hideLabel_Label, Properties.ID_ISVISIBLE, false) ;
//					((GraphicalEditPart) child).performRequest(req) ;
//				} 
//
//				if (child instanceof SequenceFlowConditionEditPart) {
//					ChangePropertyValueRequest req = new ChangePropertyValueRequest(DiagramUIMessages.Command_hideLabel_Label, Properties.ID_ISVISIBLE, false) ;
//					((GraphicalEditPart) child).performRequest(req) ;
//				} 
//			}
//			editPart.refresh() ;
//		} else {
//			// If the selection is on the label, get the parent
//			// (SequenceFlowEditPart)
//			if (editPart instanceof SequenceFlowNameEditPart || editPart instanceof SequenceFlowConditionEditPart) {
//				hideAllLabel(editPart.getParent());
//			}
//		}
//
//	}
//
//	private void displayConditionLabel(EditPart editPart) {
//
//		if (editPart instanceof SequenceFlowEditPart) {
//			for (Object child : ((SequenceFlowEditPart) editPart).getChildren()) {
//
//				if (child instanceof SequenceFlowConditionEditPart) {
//					ChangePropertyValueRequest req = new ChangePropertyValueRequest(DiagramUIMessages.Command_hideLabel_Label, Properties.ID_ISVISIBLE, true) ;
//					((SequenceFlowConditionEditPart) child).performRequest(req) ;
//				} 
//				((EditPart) child).refresh() ;
//			}
//			editPart.refresh() ;
//		} else {
//			// If the selection is on the label, get the parent
//			// (SequenceFlowEditPart)
//			if (editPart instanceof SequenceFlowNameEditPart || editPart instanceof SequenceFlowConditionEditPart) {
//				displayConditionLabel(editPart.getParent());
//			}
//		}
//	}
//
//	private void displayNameLabel(EditPart editPart) {
//
//		if (editPart instanceof SequenceFlowEditPart) {
//			for (Object child : ((SequenceFlowEditPart) editPart).getChildren()) {
//
//				if (child instanceof SequenceFlowNameEditPart) {
//					ChangePropertyValueRequest req = new ChangePropertyValueRequest(DiagramUIMessages.Command_hideLabel_Label, Properties.ID_ISVISIBLE, true) ;
//					((SequenceFlowNameEditPart) child).performRequest(req) ;
//				} 
//				((EditPart) child).refresh() ;
//			}
//			editPart.refresh() ;
//		} else {
//			// If the selection is on the label, get the parent
//			// (SequenceFlowEditPart)
//			if (editPart instanceof SequenceFlowNameEditPart || editPart instanceof SequenceFlowConditionEditPart) {
//				displayNameLabel(editPart.getParent());
//			}
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.bonitasoft.studio.properties.sections.general.
//	 * IExtenstibleGridPropertySectionContribution#getLabel()
//	 */
//	public String getLabel() {
//		return Messages.labelVisibility;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.bonitasoft.studio.properties.sections.general.
//	 * IExtenstibleGridPropertySectionContribution
//	 * #isRelevantFor(org.eclipse.emf.ecore.EObject)
//	 */
//	public boolean isRelevantFor(EObject eObject) {
//		return eObject instanceof SequenceFlow ;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.bonitasoft.studio.properties.sections.general.
//	 * IExtenstibleGridPropertySectionContribution#refresh()
//	 */
//	public void refresh() {
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.bonitasoft.studio.properties.sections.general.
//	 * IExtenstibleGridPropertySectionContribution
//	 * #setEObject(org.eclipse.emf.ecore.EObject)
//	 */
//	public void setEObject(EObject object) {
//
//	}
//
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.bonitasoft.studio.properties.sections.general.
//	 * IExtenstibleGridPropertySectionContribution
//	 * #setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
//	 */
//	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.bonitasoft.studio.properties.sections.general.
//	 * IExtenstibleGridPropertySectionContribution
//	 * #setSelection(org.eclipse.jface.viewers.ISelection)
//	 */
//	public void setSelection(ISelection selection) {	
//
//	}
//
//	/* (non-Javadoc)
//	 * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
//	 */
//	public void dispose() {
//
//	}
//
//
//}
