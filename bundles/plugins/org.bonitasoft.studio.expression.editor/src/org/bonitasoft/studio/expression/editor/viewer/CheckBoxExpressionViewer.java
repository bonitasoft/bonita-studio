/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.viewer;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.widgets.MagicComposite;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Romain Bioteau
 *
 */
public class CheckBoxExpressionViewer extends ExpressionViewer implements ExpressionConstants {

	private MagicComposite mc;
	private Button checkBoxControl;


	public CheckBoxExpressionViewer(Composite composite,int style,  EReference expressionReference) {
		super(composite, style, expressionReference);
	}

	@Override
	protected void createControl(Composite composite, int style, TabbedPropertySheetWidgetFactory widgetFactory) {
		mc = new MagicComposite(composite, SWT.INHERIT_DEFAULT);
		mc.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

		if(widgetFactory != null){
			checkBoxControl = widgetFactory.createButton(mc,"", SWT.CHECK) ;
		}else{
			checkBoxControl = new Button(mc, SWT.CHECK) ;
		}
		checkBoxControl.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(SWT.DEFAULT, 30).create());

		if(widgetFactory != null){
			control = widgetFactory.createComposite(mc, SWT.INHERIT_DEFAULT) ;
		}else{
			control = new Composite(mc, SWT.INHERIT_DEFAULT) ;
		}
		control.addDisposeListener(disposeListener) ;
		control.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		control.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(0, 0).create()) ;
		createTextControl(style, widgetFactory);

		createToolbar(style, widgetFactory);
		if ((style & SWT.BORDER) != 0){//Not in a table
			createSwitchEditorControl(widgetFactory);
		}
		mc.show(checkBoxControl);
		mc.hide(control);
	}


	private void createSwitchEditorControl(TabbedPropertySheetWidgetFactory widgetFactory) {
		((GridLayout) control.getLayout()).numColumns++;
		final Link switchControl = new Link(mc, SWT.NONE);
		switchControl.setText(Messages.switchEditor);
		switchControl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switchEditorType();
			}
		});
	}

	protected void switchEditorType() {
		if(!control.isVisible()){
			mc.hide(checkBoxControl);
			mc.show(control);
			bindExpression();
		}else{
			if(MessageDialog.openQuestion(mc.getShell(), Messages.eraseExpressionTitle,Messages.eraseExpressionMsg)){
				mc.hide(control);
				mc.show(checkBoxControl);
				//Reset checkbox to false
				final Expression falseExp = ExpressionFactory.eINSTANCE.createExpression();
				falseExp.setName(Boolean.FALSE.toString());
				falseExp.setContent(Boolean.FALSE.toString());
				falseExp.setReturnType(Boolean.class.getName());
				falseExp.setType(ExpressionConstants.CONSTANT_TYPE);
				updateSelection(falseExp);
				bindExpression();
			}
		}
		mc.layout(true, true);
	}

	@Override
	public Control getControl() {
		return mc;
	}

	@Override
	protected void bindExpression() {
		if(control.isVisible()){
			super.bindExpression();
		}else{
			if(expressionBinding != null && externalDataBindingContext != null){
				externalDataBindingContext.removeBinding(expressionBinding);
				expressionBinding.dispose();
			}
			if(internalDataBindingContext != null){
				internalDataBindingContext.dispose();
			}
			internalDataBindingContext = new EMFDataBindingContext() ;
			IObservableValue nameObservable = null ;
			if(editingDomain != null){
				nameObservable = EMFEditObservables.observeValue(editingDomain, selectedExpression, ExpressionPackage.Literals.EXPRESSION__NAME);
			}else{
				nameObservable = EMFObservables.observeValue(selectedExpression, ExpressionPackage.Literals.EXPRESSION__NAME);
			}

			UpdateValueStrategy targetToModelNameStrategy = new UpdateValueStrategy() ;
			targetToModelNameStrategy.setConverter(new Converter(Boolean.class,String.class) {

				@Override
				public Object convert(Object fromObject) {
					String input  = ((Boolean) fromObject).toString() ;
					updateContentType(ExpressionConstants.CONSTANT_TYPE) ;
					updateContent(getContentFromInput(input)) ;
					refresh() ;
					return  input;
				}
			}) ;


			UpdateValueStrategy modelToTargetNameStrategy = new UpdateValueStrategy() ;
			modelToTargetNameStrategy.setConverter(new Converter(String.class,Boolean.class) {

				@Override
				public Object convert(Object fromObject) {
					if(fromObject != null){
						String input  = fromObject.toString() ;
						if(input.equalsIgnoreCase(Boolean.TRUE.toString())){
							return true;
						}
					}
					return false;
				}
			}) ;

			internalDataBindingContext.bindValue(
					SWTObservables.observeSelection(checkBoxControl),
					nameObservable,
					targetToModelNameStrategy,
					modelToTargetNameStrategy) ;
		}
	}

	@Override
	protected void internalRefresh(Object element) {
		super.internalRefresh(element);
		final String description = getMessage(IStatus.INFO);
		if(description != null){
			checkBoxControl.setToolTipText(description);
		}
	}

	@Override
	public void setSelection(ISelection selection) {
		final Expression exp = (Expression) ((IStructuredSelection) getSelection()).getFirstElement();
		if(ExpressionConstants.CONSTANT_TYPE.equals(exp.getType())){
			if(!checkBoxControl.isVisible()){
				mc.hide(control);
				mc.show(checkBoxControl);
				mc.layout(true, true);
			}
		}else{
			if(!control.isVisible()){
				mc.hide(checkBoxControl);
				mc.show(control);
				mc.layout(true, true);
			}
		}
		super.setSelection(selection);

	}
}
