/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.general.contributions;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 * @author Aurelien Pupier : introduce databinding
 *
 */
public class ShowPageLabelContribution implements
IExtensibleGridPropertySectionContribution {

    private Form element;
    private TransactionalEditingDomain editingDomain;
    private EMFDataBindingContext dataBindingContext;

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
     */
    public void createControl(Composite composite,
            TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection extensibleGridPropertySection) {
        //		composite.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false,2,1));
        composite.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
        composite.setLayout(new GridLayout(3, false));
        if(dataBindingContext != null){
            dataBindingContext.dispose();
        }
        dataBindingContext = new EMFDataBindingContext();

        Button enableLabel = widgetFactory.createButton(composite, "", SWT.CHECK); //$NON-NLS-1$
        enableLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,false));
        enableLabel.setToolTipText(Messages.GeneralSection_EnableLabel_tooltip);
        dataBindingContext.bindValue(SWTObservables.observeSelection(enableLabel), EMFEditObservables.observeValue(editingDomain, element, FormPackage.Literals.FORM__SHOW_PAGE_LABEL));

        enableLabel.setSelection(element.getShowPageLabel() == null || element.getShowPageLabel());

        GridData gData = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        gData.widthHint = 250;

        ExpressionViewer pageLabelExpresssionViewer = new ExpressionViewer(composite, SWT.BORDER, widgetFactory, FormPackage.Literals.FORM__PAGE_LABEL);
        final Expression pageLabel = element.getPageLabel();
        if(pageLabel == null){
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.FORM__PAGE_LABEL, ExpressionFactory.eINSTANCE.createExpression()));
        }
        if(ModelHelper.isAnEntryPageFlowOnAPool(element)){
            pageLabelExpresssionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{
        			ExpressionConstants.CONSTANT_TYPE,
        			ExpressionConstants.PARAMETER_TYPE,
        			ExpressionConstants.SCRIPT_TYPE
            }));
        }

        pageLabelExpresssionViewer.setInput(element);
        dataBindingContext.bindValue(
                ViewerProperties.singleSelection().observe(pageLabelExpresssionViewer),
                EMFEditProperties.value(editingDomain, FormPackage.Literals.FORM__PAGE_LABEL).observe(element));

        pageLabelExpresssionViewer.setMessage(Messages.GeneralSection_EnablePageLabel_tooltip);
        pageLabelExpresssionViewer.getControl().setLayoutData(gData);

        Button allowHTMLButton = widgetFactory.createButton(composite, Messages.GeneralSection_allowHTML, SWT.CHECK);
        dataBindingContext.bindValue(SWTObservables.observeSelection(allowHTMLButton), EMFEditObservables.observeValue(editingDomain, element, FormPackage.Literals.FORM__ALLOW_HTML_IN_PAGE_LABEL));

        /*Enable/disable the combo for the text and the allow html*/
        dataBindingContext.bindValue(SWTObservables.observeEnabled(pageLabelExpresssionViewer.getControl()), SWTObservables.observeSelection(enableLabel));
        dataBindingContext.bindValue(SWTObservables.observeEnabled(allowHTMLButton), SWTObservables.observeSelection(enableLabel));
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#getLabel()
     */
    public String getLabel() {
        return Messages.GeneralSection_EnablePageLabel;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof Form;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#refresh()
     */
    public void refresh() {

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setEObject(EObject object) {
        element = (Form) object;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#setSelection(org.eclipse.jface.viewers.ISelection)
     */
    public void setSelection(ISelection selection) {
        // NOTHING
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    public void dispose() {
        if(dataBindingContext!=null) {
            dataBindingContext.dispose();
        }
    }

}
