/**
 * 
 */
package org.bonitasoft.studio.groovy.contentassist;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.Variable;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.eclipse.codeassist.DocumentSourceBuffer;
import org.codehaus.groovy.eclipse.codeassist.requestor.CompletionNodeFinder;
import org.codehaus.groovy.eclipse.codeassist.requestor.ContentAssistContext;
import org.codehaus.groovy.eclipse.codeassist.requestor.GroovyCompletionProposalComputer;
import org.codehaus.groovy.eclipse.core.ISourceBuffer;
import org.codehaus.groovy.eclipse.core.util.ExpressionFinder;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jface.text.IDocument;

public class CustomGroovyCompletionProposalComputer extends GroovyCompletionProposalComputer {

	@Override
	public ContentAssistContext createContentAssistContext(GroovyCompilationUnit gunit, int invocationOffset,
			IDocument document) {
		String fullCompletionText = findCompletionText(document, invocationOffset);
		String[] completionExpressions = findCompletionExpression(fullCompletionText);
		final String completionExpression;
		if (completionExpressions == null || "@".equals(fullCompletionText)) {
			completionExpression = "";
		} else if (completionExpressions[1] == null) {
			completionExpression = completionExpressions[0];
		} else {
			completionExpression = completionExpressions[1];
		}
		int completionEnd = findCompletionEnd(document, invocationOffset);
		int supportingNodeEnd = findSupportingNodeEnd(gunit, invocationOffset, fullCompletionText);

		CompletionNodeFinder finder = new CompletionNodeFinder(invocationOffset, completionEnd, supportingNodeEnd,
				completionExpression, fullCompletionText);
		ContentAssistContext context = finder.findContentAssistContext(gunit);
		ASTNode node = context.completionNode;
		if(node instanceof VariableExpression) {
			Variable declaredVariable = gunit.getModuleNode().getStatementBlock().getVariableScope().getDeclaredVariable(((VariableExpression) node).getName());
			if(declaredVariable !=null ) {
				((VariableExpression) node).setAccessedVariable(declaredVariable);
			}
		}
		return context;
	}

	private String[] findCompletionExpression(String completionText) {
		return new ExpressionFinder().splitForCompletion(completionText);
	}

	private int findCompletionEnd(IDocument doc, int offset) {
		ISourceBuffer buffer = new DocumentSourceBuffer(doc);
		return new ExpressionFinder().findTokenEnd(buffer, offset);
	}
}
