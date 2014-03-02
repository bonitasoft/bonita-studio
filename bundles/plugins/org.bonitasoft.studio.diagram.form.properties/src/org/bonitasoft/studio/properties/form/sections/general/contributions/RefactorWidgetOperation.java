/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.general.contributions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.AbstractRefactorOperation;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.refactoring.BonitaGroovyRefactoringAction;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jdt.core.JavaModelException;


/**
 * @author Romain Bioteau
 *
 */
public class RefactorWidgetOperation extends AbstractRefactorOperation  {

	private final Widget widget;
	private final String newName;
	private  Widget widgetCopy;
	private EditingDomain editingDomain;
	private boolean canExecute = true;

	public RefactorWidgetOperation(Widget widget, String newName) {
		this.widget = widget;
		this.newName = newName;
		this.widgetCopy= (Widget) FormFactory.eINSTANCE.create(widget.eClass()); 
		widgetCopy.setName(newName);
		editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(widget);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		monitor.beginTask(Messages.updatingWidgetReferences, IProgressMonitor.UNKNOWN);
		if (canExecute){
			final List<Expression> expressions =  ModelHelper.getAllItemsOfType(ModelHelper.getPageFlow(widget), ExpressionPackage.Literals.EXPRESSION);
			List<Expression> expressionsList = new ArrayList<Expression>();
			for (Expression exp:expressions){
				if (!ModelHelper.isAExpressionReferencedElement(exp)){
					expressionsList.add(exp);
				}
			}
			
			if (cc==null){
				cc = new CompoundCommand();
			}
			cc.append(SetCommand.create(editingDomain,widget,ProcessPackage.Literals.ELEMENT__NAME, newName));
			for(Expression exp : expressionsList){
				String fieldExpressionName = exp.getName();
				String oldExpressionName = WidgetHelper.FIELD_PREFIX+widget.getName();
				if(ExpressionConstants.FORM_FIELD_TYPE.equals(exp.getType()) && fieldExpressionName.equals(oldExpressionName)){
					//update name and content
					cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__NAME,WidgetHelper.FIELD_PREFIX+newName));
					cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, WidgetHelper.FIELD_PREFIX+newName));
					cc.append(RemoveCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS  , exp.getReferencedElements()));
					cc.append(AddCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,ExpressionHelper.createDependencyFromEObject(widgetCopy)));
				}
			}
			editingDomain.getCommandStack().execute(cc);
		} else {
			cc.dispose();
		}
	}

	public  void refactorReferencesInScripts(){
		List<Expression> scriptsToRefactor = ModelHelper.findAllScriptAndConditionsExpressionWithReferencedElement(ModelHelper.getPageFlow(widget), widget);
		if (!scriptsToRefactor.isEmpty() && cc!=null){
			try {
				BonitaGroovyRefactoringAction action = new BonitaGroovyRefactoringAction(widget.getName(), newName,scriptsToRefactor , cc, editingDomain,BonitaGroovyRefactoringAction.REFACTOR_OPERATION);
				action.run(null);
				canExecute = action.getStatus();
				if (canExecute){
					for (Expression script:scriptsToRefactor){
						Object referencedObject = getWidgetToRemove(script);
						cc.append(RemoveCommand.create(editingDomain, script, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS  , referencedObject));
						cc.append(AddCommand.create(editingDomain, script, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS  ,EcoreUtil.copy(widgetCopy)));
					}
				}
			} catch (JavaModelException e) {
				BonitaStudioLog.error(e);
			}
		} else {
			canExecute = true;
		}
	}

	public EObject getWidgetToRemove(Expression expr){
		for (EObject object:expr.getReferencedElements()){
			if (object instanceof Widget && ((Widget) object).getName().equals(widget.getName())){
				return object;
			}
		}
		return null;
	}


}
