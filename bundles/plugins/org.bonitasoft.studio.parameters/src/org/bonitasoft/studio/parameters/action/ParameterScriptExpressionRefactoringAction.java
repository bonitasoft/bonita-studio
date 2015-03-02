/*******************************************************************************
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.refactoring.core.AbstractScriptExpressionRefactoringAction;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain
 * 
 */
public class ParameterScriptExpressionRefactoringAction extends AbstractScriptExpressionRefactoringAction<ParameterRefactorPair> {

    public ParameterScriptExpressionRefactoringAction(List<ParameterRefactorPair> pairsToRefactor, List<Expression> scriptExpressions,
            List<Expression> refactoredScriptExpression, CompoundCommand compoundCommand, EditingDomain domain, RefactoringOperationType operationType) {
        super(pairsToRefactor, scriptExpressions, refactoredScriptExpression, compoundCommand, domain, operationType);
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.common.refactoring.AbstractScriptExpressionRefactoringAction#getReferencedObjectInScriptsOperation(org.bonitasoft.studio.model.
     * expression.Expression)
     */
    @Override
    protected Map<EObject, EObject> getReferencedObjectInScriptsOperation(Expression expr) {
    	Map<EObject, EObject> res = new HashMap<EObject, EObject>();
    	for (EObject object : expr.getReferencedElements()) {
    		for(ParameterRefactorPair pairToRefactor : pairsToRefactor){
    			if (object instanceof Parameter && ((Parameter) object).getName().equals(pairToRefactor.getOldValueName())) {
    				res.put(object, pairToRefactor.getNewValue());
    			}
    		}
    	}
    	return res;
    }

}
