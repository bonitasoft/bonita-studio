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
package org.bonitasoft.studio.properties.sections.appearance;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationAttachmentEditPart;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


/**
 * @author Mickael Istria
 * @author Aurelien Pupier
 * @author Romain Bioteau
 */
public class AutomaticOrManualTransitionContribution implements IExtensibleGridPropertySectionContribution {


    private TransactionalEditingDomain editingDomain;

    //private  Button conditionVisibility;
    private  boolean automatic = true;
    private Button automaticOrManulRoutingButton;
    private GraphicalEditPart editPart;

    private final ExtensibleGridPropertySection section;




    /**
     * @param generalExtensibleGridPropertySection
     * @param iWorkbenchPart
     */
    public AutomaticOrManualTransitionContribution(ExtensibleGridPropertySection generalExtensibleGridPropertySection) {
        section = generalExtensibleGridPropertySection ;
    }

    @Override
    public void createControl(final Composite mainComposite, TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection extensibleGridPropertySection) {
        mainComposite.setLayout(new RowLayout());
        /* Create a checkbox to select name or condition to display on diagram */
        automaticOrManulRoutingButton = widgetFactory.createButton(mainComposite, "", SWT.CHECK); //$NON-NLS-1$
        automaticOrManulRoutingButton.setSelection(automatic);

        ControlDecoration cd = new ControlDecoration(automaticOrManulRoutingButton, SWT.LEFT);
        cd.setImage(Pics.getImage(PicsConstants.hint));
        cd.setShowOnlyOnFocus(false);
        cd.setDescriptionText(Messages.avoidElementHint);
        automaticOrManulRoutingButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                automatic = automaticOrManulRoutingButton.getSelection() ;
                for(Object editPart : section.getInput()){
                    if(editPart instanceof EditPart){
                        toggleTransitionRoutingMode((EditPart) editPart);
                    }
                }
            }

            /**
             * @param cc
             *            The composite command use to hide/show the edtiparts
             * @param editPart
             *            The EditPart to which it is applied.
             */
            private void toggleTransitionRoutingMode(final EditPart editPart) {
                if (editPart instanceof SequenceFlowEditPart
                        || editPart instanceof MessageFlowEditPart
                        || editPart instanceof TextAnnotationAttachmentEditPart) {

                    try{
                        OperationHistoryFactory.getOperationHistory().execute(new AbstractEMFOperation(editingDomain, "Set Closest Distance") { //$NON-NLS-1$

                            @Override
                            protected IStatus doExecute(IProgressMonitor arg0, IAdaptable arg1)
                                    throws ExecutionException {
                                final RoutingStyle style = (RoutingStyle) ((View) editPart.getModel()).getStyle(NotationPackage.Literals.ROUTING_STYLE);

                                if(((ConnectionEditPart) editPart).getSource()!= null && (IGraphicalEditPart) ((ConnectionEditPart) editPart).getTarget() != null){
                                    EObject targetElem = ((IGraphicalEditPart) ((ConnectionEditPart) editPart).getTarget()).resolveSemanticElement() ;
                                    EObject sourceElem = ((IGraphicalEditPart)((ConnectionEditPart) editPart).getSource()).resolveSemanticElement() ;
                                    if(targetElem.eContainer() != null
                                            && targetElem.eContainer() instanceof Lane
                                            && sourceElem.eContainer() != null
                                            && sourceElem.eContainer() instanceof Lane
                                            && !sourceElem.eContainer().equals(targetElem.eContainer())){

                                        style.setClosestDistance(automatic);
                                        style.setAvoidObstructions(false);
                                    }else{
                                        style.setClosestDistance(automatic);
                                        style.setAvoidObstructions(automatic);
                                    }
                                }
                                return Status.OK_STATUS;
                            }

                        }, null, null);
                    } catch (ExecutionException e) {
                        BonitaStudioLog.error(e);
                    }


                } else {
                    // If the selection is on the label, get the parent
                    // (SequenceFlowEditPart)
                    if (editPart instanceof SequenceFlowNameEditPart || editPart instanceof MessageFlowLabelEditPart) {
                        toggleTransitionRoutingMode(editPart.getParent());
                    }
                }
            }


        });


    }


    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.avoidElement;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof SequenceFlow || eObject instanceof MessageFlow || eObject instanceof TextAnnotationAttachment;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#refresh()
     */
    @Override
    public void refresh() {
        refreshConditionCheckBox();
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setEObject(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setEObject(EObject object) {

    }

    private void refreshConditionCheckBox() {
        if(automaticOrManulRoutingButton != null && !automaticOrManulRoutingButton.isDisposed()){
            automaticOrManulRoutingButton.setSelection((getClosestDistance()));
        }
    }

    private boolean getClosestDistance() {
        final RoutingStyle style = (RoutingStyle) ((View) editPart.getModel())
                .getStyle(NotationPackage.Literals.ROUTING_STYLE);

        if(style != null){
            return style.isClosestDistance() ;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    @Override
    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setSelection(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setSelection(ISelection selection) {
        if(((IStructuredSelection)selection).getFirstElement() instanceof GraphicalEditPart){
            editPart = (GraphicalEditPart) ((IStructuredSelection)selection).getFirstElement() ;
        }

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {

    }


}
