/**
 * 
 */
package org.bonitasoft.studio.properties.form.sections.general.contributions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.AbstractRefactorOperation;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @author Aurelie Zara
 *
 */
public class RemoveWidgetReferencesOperation extends AbstractRefactorOperation {
	
	private Widget widgetToRemove;
	private EObject container;

	public RemoveWidgetReferencesOperation(EObject container,Widget widgetToRemove){
		this.container = container;
	}
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		 monitor.beginTask(Messages.removingWidgetReferences, IProgressMonitor.UNKNOWN);
		 EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(container);
		 List<Expression> expressions = ModelHelper.getAllItemsOfType(container,ExpressionPackage.Literals.EXPRESSION);
			for (Expression exp : expressions){
				if (ExpressionConstants.FORM_FIELD_TYPE.equals(exp.getType()) && exp.getName().equals(widgetToRemove.getName())){
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
	}
}
