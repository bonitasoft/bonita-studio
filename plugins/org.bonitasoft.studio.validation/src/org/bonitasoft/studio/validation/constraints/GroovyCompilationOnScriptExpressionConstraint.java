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
package org.bonitasoft.studio.validation.constraints;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.codehaus.groovy.eclipse.core.compiler.GroovySnippetCompiler;
import org.codehaus.groovy.eclipse.core.model.GroovyProjectFacade;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.jdt.core.compiler.CategorizedProblem;
import org.eclipse.jdt.internal.compiler.CompilationResult;

/**
 * @author Romain Bioteau
 *
 */
public class GroovyCompilationOnScriptExpressionConstraint  extends AbstractLiveValidationMarkerConstraint {

	private static final String CONSTRAINT_ID = "org.bonitasoft.studio.validation.constraint.groovyCompilationFailure";

	@Override
	protected IStatus performLiveValidation(IValidationContext context) {
		return null;
	}

	@Override
	protected IStatus performBatchValidation(IValidationContext context) {
		final EObject eObj = context.getTarget();
		if (eObj instanceof Expression 
				&& ExpressionConstants.SCRIPT_TYPE.equals(((Expression) eObj).getType()) 
				&& ExpressionConstants.GROOVY.equals(((Expression) eObj).getInterpreter()))  {
			final Expression expression = (Expression) eObj;
			final String scriptText = expression.getContent();
			final GroovySnippetCompiler compiler = new GroovySnippetCompiler(new GroovyProjectFacade(RepositoryManager.getInstance().getCurrentRepository().getJavaProject()));
			final CompilationResult result = compiler.compileForErrors(scriptText, null);
			CategorizedProblem[] problems =  result.getAllProblems();
			if(problems != null && problems.length > 0){
				StringBuilder sb = new StringBuilder();
				for(CategorizedProblem problem : problems){
					sb.append(problem.getMessage());
					sb.append(", ");
				}
				sb.delete(sb.length()-2, sb.length());
				return context.createFailureStatus(new Object[] { Messages.bind(Messages.groovyCompilationProblem,expression.getName(),sb.toString())});
			}else{
				return  context.createSuccessStatus();
			}
		}
		return context.createSuccessStatus();
	}

	@Override
	protected String getConstraintId() {
		return CONSTRAINT_ID;
	}

}
