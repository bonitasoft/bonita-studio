/**
 * 
 */
package org.bonitasoft.studio.data.operation;

import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 * 
 */
public class DataScriptExpressionRefactoringAction extends AbstractScriptExpressionRefactoringAction {

    public DataScriptExpressionRefactoringAction(EObject newValue, String oldName, String newName, List<Expression> scriptExpressions,
            List<Expression> updatedGroovyScriptExpressions, CompoundCommand cc, EditingDomain domain, RefactoringOperationType operationKind) {
        super(newValue, oldName, newName, scriptExpressions, updatedGroovyScriptExpressions, cc, domain, operationKind);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.refactoring.AbstractScriptExpressionRefactoringAction#getReferencedObjectInScriptsOperation(org.bonitasoft.studio.model.
     * expression.Expression)
     */
    @Override
    protected EObject getReferencedObjectInScriptsOperation(Expression expr) {
        for (EObject object : expr.getReferencedElements()) {
            if (object instanceof Data && ((Data) object).getName().equals(getOldName())) {
                return object;
            }
        }
        return null;
    }

}
