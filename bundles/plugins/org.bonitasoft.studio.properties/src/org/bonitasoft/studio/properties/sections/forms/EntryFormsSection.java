/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms;


import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;


/**
 * @author Aurelien Pupier
 */
public class EntryFormsSection extends AbstractFormsSection {

    protected Button pageFlowRadio;
    protected Button skipRadio;
    protected Button autoLoginCheckbox;
    protected DataBindingContext thisContext;
    private EObject lastEObject;
    private ControlDecoration checkBoxWarning;

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        if(lastEObject == null || lastEObject != null && !lastEObject.equals(getEObject())){
            refreshDataBinding();
        }
    }

    @Override
    public PageFlow getPageFlow() {
        return (PageFlow)super.getPageFlow();
    }

    protected Object getPageFlowType(){
        return null ;
    }

    protected void refreshDataBinding() {
        if(thisContext != null){
            thisContext.dispose();
        }
        thisContext = new DataBindingContext();

        showOrHideComposite(pageFlowComposite, true);

        final IObservableValue isAutoLogin = EMFEditObservables.observeValue(getEditingDomain(), getPageFlow(),
                ProcessPackage.Literals.PROCESS_APPLICATION__AUTO_LOGIN);

        context.bindValue(SWTObservables.observeSelection(autoLoginCheckbox), isAutoLogin);
        activateAutoLoginWarning();
        final boolean visible = getPageFlow() instanceof AbstractProcess;
        autoLoginCheckbox.setVisible(visible);
    }

    /** Show a warning icon when the auto-login check box is selected
     *
     */
    private void activateAutoLoginWarning(){
        if(autoLoginCheckbox.getSelection()){
            checkBoxWarning.show();
        }else{
            checkBoxWarning.hide();
        }
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.forms.AbstractFormsSection#getFormFeature()
     */
    @Override
    protected EReference getPageFlowFormFeature() {
        return ProcessPackage.Literals.PAGE_FLOW__FORM;
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.forms.AbstractFormsSection#createRadioButtons(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Composite createRadioButtons(final Composite parent) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.forms.AbstractFormsSection#getPageFlowTransitionsFeature()
     */
    @Override
    protected EReference getPageFlowTransitionsFeature() {
        return ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_TRANSITIONS;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.forms.AbstractFormsSection#getPageFlowTypeFeature()
     */
    @Override
    protected EAttribute getPageFlowTypeFeature() {
        return ProcessPackage.Literals.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.properties.sections.forms.AbstractFormsSection#getPageFlowRedirectionURLFeature()
     */
    @Override
    protected EReference getPageFlowRedirectionURLFeature() {
        return ProcessPackage.Literals.PAGE_FLOW__ENTRY_REDIRECTION_URL;
    }


    /**
     * @param templates
     */
    private void createAutoLoginPart(final Composite parent) {
        final Composite autologinComposite = getWidgetFactory().createComposite(parent);
        autologinComposite.setLayout(new GridLayout(4, false));
        autologinComposite.setLayoutData(GridDataFactory.fillDefaults().span(4, 1).create());

        autoLoginCheckbox = getWidgetFactory().createButton(autologinComposite, "", SWT.CHECK);
        autoLoginCheckbox.setLayoutData(GridDataFactory.fillDefaults().indent(20, 0).create());
        autoLoginCheckbox.setText(Messages.ResourceSection_AutoLogin);

        //Auto-login checkBox tooltip
        final ControlDecoration checkBoxToolTip =  new ControlDecoration(autoLoginCheckbox, SWT.LEFT);
        checkBoxToolTip.setDescriptionText(Messages.ResourceSection_AutoLoginTooltip);
        checkBoxToolTip.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK));
        checkBoxToolTip.show();

        // Auto-login warning when checkBox selected
        checkBoxWarning = new ControlDecoration(autoLoginCheckbox, SWT.RIGHT);
        checkBoxWarning.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK));
        checkBoxWarning.setDescriptionText("An anonymous username and password must be defined in the authentification configuration") ;
        checkBoxWarning.setShowOnlyOnFocus(false) ;

        autoLoginCheckbox.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                activateAutoLoginWarning();
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void dispose() {
        super.dispose();
        if(thisContext != null){
            thisContext.dispose();
        }
    }

    @Override
    public String getSectionDescription() {
        return Messages.sectionDescriptionEntryForm;
    }

    @Override
    protected void createContent(final Composite parent) {
        super.createContent(parent);
        createAutoLoginPart(pageFlowComposite);
    }



}
