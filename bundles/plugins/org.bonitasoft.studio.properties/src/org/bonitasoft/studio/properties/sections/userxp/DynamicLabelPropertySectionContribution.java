/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.properties.AbstractPropertySectionContribution;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.DefaultExpressionNameResolver;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Mickael Istria
 *
 */
public class DynamicLabelPropertySectionContribution extends AbstractPropertySectionContribution {

    private ExpressionViewer expressionViewer;
    private EMFDataBindingContext dataBindingContext;
    private final static int MAX_LENGTH = 255;
    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory, org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection)
     */
    @Override
    public void createControl(final Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory, final ExtensibleGridPropertySection extensibleGridPropertySection) {
        composite.setLayout(new GridLayout(1, true));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        final CLabel label = widgetFactory.createCLabel(composite, Messages.bind(Messages.warningDisplayLabelMaxLength, MAX_LENGTH, MAX_LENGTH));
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        label.setLayout(GridLayoutFactory.fillDefaults().spacing(0,10).create());
        label.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK));
        expressionViewer = new ExpressionViewer(composite,SWT.BORDER,widgetFactory,editingDomain, ProcessPackage.Literals.FLOW_ELEMENT__DYNAMIC_LABEL);
        expressionViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
        expressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[]{ExpressionConstants.CONSTANT_TYPE,ExpressionConstants.VARIABLE_TYPE,ExpressionConstants.PARAMETER_TYPE,ExpressionConstants.SCRIPT_TYPE}));
        expressionViewer.setExpressionNameResolver(new DefaultExpressionNameResolver("displayName"));
        expressionViewer.setInput(eObject) ;
        expressionViewer.setMessage(Messages.dynamicLabelHint) ;
        expressionViewer.addExpressionValidator(new ExpressionLengthValidator(MAX_LENGTH));
        refreshDataBindingContext();
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#dispose()
     */
    @Override
    public void dispose() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
    }

    protected void refreshDataBindingContext() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
        dataBindingContext = new EMFDataBindingContext();
        if (eObject != null && expressionViewer != null) {

            Expression selection = ((FlowElement) eObject).getDynamicLabel() ;
            if(selection == null){
                selection = ExpressionFactory.eINSTANCE.createExpression() ;
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, ProcessPackage.Literals.FLOW_ELEMENT__DYNAMIC_LABEL, selection)) ;
            }
            dataBindingContext.bindValue(ViewerProperties.singleSelection().observe(expressionViewer), EMFEditProperties.value(editingDomain, ProcessPackage.Literals.FLOW_ELEMENT__DYNAMIC_LABEL).observe(eObject));
            //expressionViewer.setSelection(new StructuredSelection(selection)) ;
        }
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#getLabel()
     */
    @Override
    public String getLabel() {
        return Messages.dynamicLabelLabel;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(final EObject eObject) {
        return eObject instanceof FlowElement;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution#refresh()
     */
    @Override
    public void refresh() {
    }



}
