/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.IFrameWidget;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.properties.sections.general.ExpressionNotEmptyValidator;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier
 * @author Baptiste Mesta
 *
 */
public class ShowLabelGridPropertySectionContribution implements IExtensibleGridPropertySectionContribution {

    private Widget element;
    private TransactionalEditingDomain editingDomain;
    private EMFDataBindingContext dataBindingContext;

    /*
     * (non-Javadoc)
     *
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #createControl(org.eclipse.swt.widgets.Composite,
     * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory,
     * org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
     */
    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory, final ExtensibleGridPropertySection extensibleGridPropertySection) {

        composite.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
        composite.setLayout(new GridLayout(3, false));
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
        dataBindingContext = new EMFDataBindingContext();
        Button enableLabel = null;

        if (!(element instanceof FormButton)) {
            /* Create the checkbox to hide/show the label */
            enableLabel = widgetFactory.createButton(composite, "", SWT.CHECK); //$NON-NLS-1$
            enableLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
            enableLabel.setToolTipText(Messages.GeneralSection_EnableLabel_tooltip);
            dataBindingContext.bindValue(SWTObservables.observeSelection(enableLabel),
                    EMFEditObservables.observeValue(editingDomain, element, FormPackage.Literals.WIDGET__SHOW_DISPLAY_LABEL));
        }

        final ExpressionViewer displayLabelViewer = new ExpressionViewer(composite, SWT.BORDER, widgetFactory,editingDomain, FormPackage.Literals.WIDGET__DISPLAY_LABEL);
        displayLabelViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{
                ExpressionConstants.CONSTANT_TYPE,
                ExpressionConstants.I18N_TYPE,
                ExpressionConstants.SCRIPT_TYPE,
                ExpressionConstants.PARAMETER_TYPE,
                ExpressionConstants.VARIABLE_TYPE
        })) ;
        Expression displayLabelExpression = element.getDisplayLabel();
        if(displayLabelExpression == null){
            displayLabelExpression = ExpressionFactory.eINSTANCE.createExpression();
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, element, FormPackage.Literals.WIDGET__DISPLAY_LABEL, displayLabelExpression));
        }

        // add validator on the show label field when it is a form button - expression can't be empty
        if (element instanceof FormButton) {
            displayLabelViewer.addExpressionValidator(new ExpressionNotEmptyValidator());
        }

        displayLabelViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(300, SWT.DEFAULT).create());

        displayLabelViewer.setInput(element);
        dataBindingContext.bindValue(
                ViewerProperties.singleSelection().observe(displayLabelViewer),
                EMFEditProperties.value(editingDomain, FormPackage.Literals.WIDGET__DISPLAY_LABEL).observe(element));

        if (!(element instanceof FormButton)) {

            /* Create the chekbox to allow HTML for the label or not */
            final Button allowHtmlButton = widgetFactory.createButton(composite, Messages.GeneralSection_allowHTML, SWT.CHECK);
            dataBindingContext.bindValue(SWTObservables.observeSelection(allowHtmlButton),
                    EMFEditObservables.observeValue(editingDomain, element, FormPackage.Literals.WIDGET__ALLOW_HTML_FOR_DISPLAY_LABEL));
            dataBindingContext.bindValue(SWTObservables.observeEnabled(allowHtmlButton), SWTObservables.observeSelection(enableLabel));

            /* Enable/disable the combo for the text and the allow html */
            dataBindingContext.bindValue(SWTObservables.observeEnabled(displayLabelViewer.getControl()), SWTObservables.observeSelection(enableLabel));
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#getLabel()
     */
    public String getLabel() {
        return Messages.GeneralSection_EnableLabel;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    public boolean isRelevantFor(final EObject eObject) {
        return eObject instanceof Widget && !(eObject instanceof IFrameWidget) && !(eObject instanceof HiddenWidget) && !(eObject instanceof MessageInfo) && !(eObject instanceof HtmlWidget);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#refresh()
     */
    public void refresh() {
    }

    /*
     * (non-Javadoc)
     *
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #setEObject(org.eclipse.emf.ecore.EObject)
     */
    public void setEObject(final EObject object) {
        element = (Widget) object;

    }

    /*
     * (non-Javadoc)
     *
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #setEditingDomain(org.eclipse.emf.transaction.TransactionalEditingDomain)
     */
    public void setEditingDomain(final TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution
     * #setSelection(org.eclipse.jface.viewers.ISelection)
     */
    public void setSelection(final ISelection selection) {
        // NOTHING
    }

    /*
     * (non-Javadoc)
     *
     * @see org.bonitasoft.studio.common.properties.
     * IExtensibleGridPropertySectionContribution#dispose()
     */
    public void dispose() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
    }

}
