/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * 
 * @author Baptiste Mesta
 *
 */
public class ImageWidgetInitialValueContribution extends InitialValueContribution implements IExtensibleGridPropertySectionContribution {

	private Button browse;

	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof ImageWidget;
	}

	@Override
	protected void doCreateControl(
			TabbedPropertySheetWidgetFactory widgetFactory) {
		super.doCreateControl(widgetFactory);
		browse = widgetFactory.createButton(composite, Messages.Browse, SWT.FLAT);
		browse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				final AbstractProcess parentProcess = ModelHelper.getParentProcess(widget);
				if(parentProcess != null){
					SelectFileStoreWizard selectImageFileStorWizard = new SelectFileStoreWizard(editingDomain, parentProcess, ((ImageWidget) widget).getImgPath().getContent());
					WizardDialog wd = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), selectImageFileStorWizard);
					if(wd.open() == IDialogConstants.OK_ID){
						Expression expression = (Expression) ((IStructuredSelection) expressionViewer.getSelection()).getFirstElement();
						if(expression != null){
							CompoundCommand cc = new CompoundCommand();
							cc.append(SetCommand.create(editingDomain, expression, ExpressionPackage.Literals.EXPRESSION__CONTENT, selectImageFileStorWizard.getSelectedFilePath()));
							cc.append(SetCommand.create(editingDomain, expression, ExpressionPackage.Literals.EXPRESSION__NAME, selectImageFileStorWizard.getSelectedFilePath()));
							cc.append(SetCommand.create(editingDomain, expression, ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.CONSTANT_TYPE));
							cc.append(SetCommand.create(editingDomain, expression, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
							editingDomain.getCommandStack().execute(cc);
						} else {
							expression = ExpressionFactory.eINSTANCE.createExpression();
							expression.setContent(selectImageFileStorWizard.getSelectedFilePath());
							expression.setName(selectImageFileStorWizard.getSelectedFilePath());
							expression.setReturnType(String.class.getName());
							expression.setType(ExpressionConstants.CONSTANT_TYPE);
						}
						expressionViewer.setSelection(new StructuredSelection(expression));
					}
				}
			}
		});
		
	}

	protected GridLayout getCompositeLayout() {
		GridLayout layout = new GridLayout(2, false);
        layout.marginHeight = MARGIN_HEIGHT;
        layout.marginWidth = MARGIN_WIDTH;
		return layout;
	}

	protected void updateViewerInput(){
		if(expressionViewer != null && !expressionViewer.getControl().isDisposed()){
			Expression input = ((ImageWidget)widget).getImgPath() ;
			if(input == null){
				input = ExpressionFactory.eINSTANCE.createExpression() ;
				editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, widget, FormPackage.Literals.IMAGE_WIDGET__IMG_PATH, input));
			}
			expressionViewer.setEditingDomain(editingDomain) ;
			expressionViewer.setInput(widget) ;
			dataBindingContext.bindValue(
					ViewersObservables.observeSingleSelection(expressionViewer),
					EMFEditProperties.value(editingDomain, FormPackage.Literals.IMAGE_WIDGET__IMG_PATH).observe(input));
			
			UpdateValueStrategy updateValue = new UpdateValueStrategy(){
				@Override
				public Object convert(Object value) {
					if(value instanceof Boolean){
						return !Boolean.valueOf((Boolean) value) ;
					}
					return super.convert(value);
				}
			};	
			
			dataBindingContext.bindValue(
					WidgetProperties.enabled().observe(browse), 
					EMFEditObservables.observeValue(editingDomain,widget, FormPackage.Literals.IMAGE_WIDGET__IS_ADOCUMENT), 
					updateValue,
					updateValue);		
			
			expressionViewer.setSelection(new StructuredSelection(input)) ;
			
			}
	}

	@Override
	protected AvailableExpressionTypeFilter getExpressionViewerFilter() {
		AvailableExpressionTypeFilter filter = super.getExpressionViewerFilter();
		if(!ModelHelper.isInEntryPageFlowOnAPool(widget)){
			filter.getContentTypes().add(ExpressionConstants.DOCUMENT_REF_TYPE);
		}
		return filter;
	}


}
