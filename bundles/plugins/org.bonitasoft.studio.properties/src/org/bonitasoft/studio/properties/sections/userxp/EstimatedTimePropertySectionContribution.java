/**
 * Copyright (C) 2009-2010 BonitaSoft S.A.
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

package org.bonitasoft.studio.properties.sections.userxp;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.common.widgets.DurationComposite;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 *
 */
public class EstimatedTimePropertySectionContribution implements
IExtensibleGridPropertySectionContribution {

    //TODO: Use DataBinding between a date and several spinners based on this date

    protected Text text;
    protected TransactionalEditingDomain editingDomain;
    protected ISelection selection;
    protected TabbedPropertySheetPage tabbedPropertySheetPage ;
    private EObject activity;


    private final ModifyListener updateConsitionListener = new ModifyListener() {

        @Override
        public void modifyText(ModifyEvent e) {
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, activity, ProcessPackage.Literals.ACTIVITY__DURATION,String.valueOf(durationWidget.getDuration())));
        }
    };


    private DurationComposite durationWidget;

    public EstimatedTimePropertySectionContribution() {

    }



    protected void refreshWidget() {
        if( durationWidget != null && !durationWidget.isDisposed()){
            if(((Activity)activity).getDuration() != null){
                long parseLong = Long.parseLong(((Activity)activity).getDuration());
				if(durationWidget.getDuration() != parseLong){
					durationWidget.setDuration(parseLong);
				}
            }
        }
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
        return eObject instanceof Activity
                && !(eObject instanceof ReceiveTask)
                && !(eObject instanceof SendTask);
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#refresh()
     */
    @Override
    public void refresh() {
    	
    }

    /*
     * (non-Javadoc)
     * 
     * @seeorg.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
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
     * #createControl(org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory)
     */
    @Override
    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
            final ExtensibleGridPropertySection page) {

        composite.setLayoutData(new GridData());
        composite.setLayout(new GridLayout(2, false));


        durationWidget =new DurationComposite(composite, false, false, true, true, true, false, widgetFactory) ;


        final ControlDecoration controlDecoration = new ControlDecoration(durationWidget, SWT.LEFT );
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
        controlDecoration.setDescriptionText(Messages.executionTimeHint);
        if(activity != null && ((Activity)activity).getDuration() != null){
        	   long parseLong = Long.parseLong(((Activity)activity).getDuration());
               durationWidget.setDuration(parseLong);
        }else{
               durationWidget.setDuration(0L);
        }
     
        durationWidget.addModifyListener(updateConsitionListener) ;

        refreshWidget() ;

    }

    @Override
    public void dispose() {

    }

    @Override
    public String getLabel() {
        return  Messages.estimatadExecutionTimeLabel;
    }

    @Override
    public void setEObject(EObject object) {
        activity = object ;
    }

    @Override
    public void setSelection(ISelection selection) {
        // TODO Auto-generated method stub

    }

}
