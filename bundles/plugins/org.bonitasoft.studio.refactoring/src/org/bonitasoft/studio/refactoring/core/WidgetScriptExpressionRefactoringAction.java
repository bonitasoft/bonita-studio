/**
 * 
 */
package org.bonitasoft.studio.refactoring.core;

import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 * 
 */
public class WidgetScriptExpressionRefactoringAction extends AbstractScriptExpressionRefactoringAction {

    public WidgetScriptExpressionRefactoringAction(EObject newValue, String oldName, String newName, List<Expression> groovyScriptExpressions,
            List<Expression> updatedGroovyScriptExpressions, CompoundCommand cc, EditingDomain domain, RefactoringOperationType operationKind) {
        super(newValue, oldName, newName, groovyScriptExpressions, updatedGroovyScriptExpressions, cc, domain, operationKind);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.refactoring.BonitaGroovyRefactoringAction#getReferencedObjectInScriptsOperation(org.bonitasoft.studio.model.expression.
     * Expression)
     */
    @Override
    protected EObject getReferencedObjectInScriptsOperation(Expression expr) {
        for (EObject reference : expr.getReferencedElements()) {
            if (reference instanceof Widget) {
                if (((Widget) reference).getName().equals(getOldName())) {
                    return reference;
                }
            }
        }
        return null;
    }

}
