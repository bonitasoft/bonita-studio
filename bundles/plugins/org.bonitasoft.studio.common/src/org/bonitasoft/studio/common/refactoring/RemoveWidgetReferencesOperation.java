/**
 * 
 */
package org.bonitasoft.studio.common.refactoring;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.AbstractRefactorOperation;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jdt.core.JavaModelException;

/**
 * @author Aurelie Zara
 *
 */
public class RemoveWidgetReferencesOperation extends AbstractRefactorOperation {

	private Widget widgetToRemove;
	private EObject container;
	private List<Expression> scriptExpressions;
	private boolean canExecute=true;
	private EditingDomain editingDomain;

	public RemoveWidgetReferencesOperation(EObject container,Widget widgetToRemove){
		this.container = container;
		this.scriptExpressions = new ArrayList<Expression>();
		this.widgetToRemove=widgetToRemove;
		editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(widgetToRemove);
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
	InterruptedException {
		monitor.beginTask(Messages.removingWidgetReferences, IProgressMonitor.UNKNOWN);
		if (canExecute){
			List<Expression> expressions = ModelHelper.getAllItemsOfType(container,ExpressionPackage.Literals.EXPRESSION);
			for (Expression exp : expressions){
				if (ExpressionConstants.FORM_FIELD_TYPE.equals(exp.getType()) && exp.getName().equals(WidgetHelper.FIELD_PREFIX+widgetToRemove.getName())){
					//update name and content
					cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__NAME,""));
					cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
					//update return type
					cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
					cc.append(SetCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__TYPE, ExpressionConstants.CONSTANT_TYPE));
					//update referenced data
					cc.append(RemoveCommand.create(editingDomain, exp, ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,exp.getReferencedElements()));
				}
			}
			editingDomain.getCommandStack().execute(cc);
		} else {
			cc.dispose();
		}
	}

	public void updateReferencesInScripts(){
		scriptExpressions = ModelHelper.findAllScriptAndConditionsExpressionWithReferencedElement(ModelHelper.getPageFlow(widgetToRemove),widgetToRemove);
		if (!scriptExpressions.isEmpty() && !widgetToRemove.getName().equals(EMPTY_VALUE)){
			BonitaGroovyRefactoringAction renameAction;
			try {
				renameAction = new BonitaGroovyRefactoringAction(WidgetHelper.FIELD_PREFIX+widgetToRemove.getName(),EMPTY_VALUE,scriptExpressions,cc,editingDomain,BonitaGroovyRefactoringAction.REMOVE_OPERATION);
				renameAction.run(null);
				canExecute = renameAction.getStatus();
				if (canExecute){
					for (Expression expr:scriptExpressions){
						EObject reference = getReferencedObjectInScriptsOperation(expr);
						if (reference!=null) {
							cc.append(RemoveCommand.create(editingDomain,expr,ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,reference));
						}
					}

				}
			}
			catch (JavaModelException e) {
				BonitaStudioLog.error(e);
			}	
		}

	}


	public EObject getReferencedObjectInScriptsOperation(Expression expr){
		for (EObject reference:expr.getReferencedElements()){
			if (reference instanceof Widget){
				if (((Widget)reference).getName().equals(widgetToRemove.getName())){
					return reference;
				}
			}
		}
		return null;
	}

	public boolean isCanExecute() {
		return canExecute;
	}

	public void setCanExecute(boolean canExecute) {
		this.canExecute = canExecute;
	}
	
	
}