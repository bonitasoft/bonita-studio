/**
 * 
 */
package org.bonitasoft.studio.expression.editor.autocompletion;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.fieldassist.IContentProposal;

/**
 * @author Romain Bioteau
 *
 */
public class ExpressionProposal implements IContentProposal {


	private String content;
	private String label;
	private String description;
	private final Expression expression ;

	public ExpressionProposal(Expression expression, IExpressionProposalLabelProvider labelProvider) {
		Assert.isNotNull(expression);
		Assert.isNotNull(labelProvider);
		this.expression = expression ;
		content = labelProvider.getContent(expression) ;
		description = labelProvider.getDescription(expression);
		label = labelProvider.getText(expression) +" -- "+description ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
	 */
	@Override
	public String getContent() {
		return content;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
	 */
	@Override
	public int getCursorPosition() {
		return label.length();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
	 */
	@Override
	public String getDescription() {
		return null;
	}

	public Object getExpression() {
		return expression;
	}

}
