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
package org.bonitasoft.studio.properties.sections.general;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.EMFFeatureLabelProvider;
import org.bonitasoft.studio.common.jface.EMFListFeatureTreeContentProvider;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;


/**
 * @author Mickael Istria
 * @author Aurelien Pupier
 * @author Romain Bioteau
 */
public class MessageFlowContribution implements IExtensibleGridPropertySectionContribution {


    private TransactionalEditingDomain editingDomain;

    private ComboViewer chooseEventCombo;

    private MessageFlow messageFlow;

    private Message previousEvent;




    /**
     * @param generalExtensibleGridPropertySection
     * @param iWorkbenchPart
     */
    public MessageFlowContribution() {

    }

    @Override
    public void createControl(final Composite mainComposite, TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection extensibleGridPropertySection) {


        mainComposite.setLayout(new RowLayout());

        RowData rd = new RowData(100, 25);
        chooseEventCombo = new ComboViewer(mainComposite, SWT.READ_ONLY);
        chooseEventCombo.getCombo().setLayoutData(rd);
        chooseEventCombo.setContentProvider(new EMFListFeatureTreeContentProvider(ProcessPackage.Literals.THROW_MESSAGE_EVENT__EVENTS));
        chooseEventCombo.setLabelProvider(new EMFFeatureLabelProvider(ProcessPackage.Literals.ELEMENT__NAME));
        chooseEventCombo.setInput(messageFlow.getSource());

        if(messageFlow != null && chooseEventCombo !=null && !chooseEventCombo.getCombo().isDisposed()  && messageFlow.getName() != null){
            if(!(messageFlow.getSource() == null || messageFlow.getTarget() == null)){
                chooseEventCombo.setInput(messageFlow.getSource());

                Message foundEvent = ModelHelper.findEvent(messageFlow.getSource(), messageFlow.getTarget().getEvent());
                if(foundEvent != null){
                    chooseEventCombo.setSelection(new StructuredSelection(foundEvent));
                }
                previousEvent = foundEvent ;
            }
        }

        chooseEventCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                Message eventObject = (Message) ((StructuredSelection)event.getSelection()).getFirstElement();
                CompoundCommand cc = new CompoundCommand() ;
                cc.append(new SetCommand(editingDomain, messageFlow, ProcessPackage.Literals.ELEMENT__NAME, eventObject.getName()));
                cc.append(new SetCommand(editingDomain, messageFlow.getTarget(), ProcessPackage.Literals.ABSTRACT_CATCH_MESSAGE_EVENT__EVENT, eventObject.getName()));
                cc.append(new SetCommand(editingDomain,eventObject, ProcessPackage.Literals.MESSAGE__TARGET_ELEMENT_EXPRESSION,ExpressionHelper.createConstantExpression(messageFlow.getTarget().getName(), String.class.getName())));
                cc.append(new SetCommand(editingDomain,eventObject, ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION,ExpressionHelper.createConstantExpression(ModelHelper.getParentProcess(messageFlow.getTarget()).getName(),String.class.getName())));

                if(previousEvent != null && !previousEvent.equals(eventObject)){
                    cc.append(new SetCommand(editingDomain,previousEvent, ProcessPackage.Literals.MESSAGE__TARGET_ELEMENT_EXPRESSION,null));
                    cc.append(new SetCommand(editingDomain,previousEvent, ProcessPackage.Literals.MESSAGE__TARGET_PROCESS_EXPRESSION,null));
                }
                editingDomain.getCommandStack().execute(cc) ;
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
        return Messages.messageFlowEventChoice;
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
        return eObject instanceof MessageFlow;
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
        messageFlow  =  (MessageFlow)object ;
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

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {

    }


}
