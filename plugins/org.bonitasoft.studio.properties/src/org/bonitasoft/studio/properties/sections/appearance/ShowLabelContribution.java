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

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.Gateway;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ToggleConnectionLabelsRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


/**
 * @author Romain Bioteau
 */
public class ShowLabelContribution implements IExtensibleGridPropertySectionContribution {



    private  boolean showLabel = true;
    private Button showLabelButton;
    private final ExtensibleGridPropertySection section;

    /**
     * @param generalExtensibleGridPropertySection
     * @param iWorkbenchPart
     */
    public ShowLabelContribution(ExtensibleGridPropertySection generalExtensibleGridPropertySection) {
        section = generalExtensibleGridPropertySection;
    }

    @Override
    public void createControl(final Composite mainComposite, TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection extensibleGridPropertySection) {
        /* Create a checkbox to select name or condition to display on diagram */
        mainComposite.setLayout(new RowLayout()) ;
        showLabelButton = widgetFactory.createButton(mainComposite, "", SWT.CHECK);

        showLabelButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                showLabel = showLabelButton.getSelection() ;
                EditPart ep = (EditPart) section.getInput().get(0) ;
                for(Object o : section.getInput()){
                    if(o instanceof IGraphicalEditPart){
                        toggleLabelVisibility((EditPart) o);
                    }
                }
                ep.getRoot().refresh();
            }

            /**
             * @param cc
             *            The composite command use to hide/show the edtiparts
             * @param editPart
             *            The EditPart to which it is applied.
             */
            private void toggleLabelVisibility(EditPart editPart) {

                if (editPart instanceof AbstractBorderedShapeEditPart) {

                    AbstractBorderedShapeEditPart borderItem = (AbstractBorderedShapeEditPart) editPart ;
                    Request req = new ToggleConnectionLabelsRequest(showLabel) ;
                    borderItem.performRequest(req) ;

                } else if (editPart instanceof AbstractBorderItemEditPart) {

                    AbstractBorderItemEditPart borderItem = (AbstractBorderItemEditPart) editPart ;
                    Request req = new ToggleConnectionLabelsRequest(showLabel) ;
                    borderItem.performRequest(req) ;

                } else if(editPart instanceof ConnectionEditPart){
                    ConnectionEditPart borderItem = (ConnectionEditPart) editPart ;
                    Request req = new ToggleConnectionLabelsRequest(showLabel) ;
                    borderItem.performRequest(req) ;
                }else  {
                    if (editPart instanceof ITextAwareEditPart) {
                        toggleLabelVisibility(editPart.getParent());
                    }
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });

        Object sel =  section.getInput().get(0) ;
        if(sel != null){
            if(sel instanceof ITextAwareEditPart){
                showLabel = ((View)((IGraphicalEditPart) sel).getModel()).isVisible() ;
                showLabelButton.setSelection(showLabel);
            }else{
                for(Object child : ((EditPart)sel).getChildren()){
                    if(child instanceof ITextAwareEditPart){
                        showLabel = ((View)((IGraphicalEditPart) child).getModel()).isVisible() ;
                        showLabelButton.setSelection(showLabel);
                    }
                }
            }
        }

    }


    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.showLabel;
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
        return eObject instanceof Event || eObject instanceof Gateway || eObject instanceof BoundaryEvent || eObject instanceof Connection;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution#refresh()
     */
    @Override
    public void refresh() {

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


    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.properties.sections.general.
     * IExtenstibleGridPropertySectionContribution
     * #setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    @Override
    public void setEditingDomain(TransactionalEditingDomain editingDomain) {

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

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {

    }


}
