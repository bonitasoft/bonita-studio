/**
 * 
 */
package org.bonitasoft.studio.expression.editor.comparison;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;


/**
 * @author aurelie zara
 *
 */
public class ComparisonExpressionProvider implements IExpressionProvider {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressions(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public Set<Expression> getExpressions(EObject context) {
		Set<Expression> exprs = new HashSet<Expression>();
		if (context instanceof SequenceFlow) {
			SequenceFlow sequenceFlow = (SequenceFlow)context;
			Expression expr = sequenceFlow.getCondition();
			if (expr !=null){
				exprs.add(expr);
			}
		}
			return exprs;
	}
	
	

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionType()
	 */
	@Override
	public String getExpressionType() {
		// TODO Auto-generated method stub
		return ExpressionConstants.CONDITION_TYPE;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getIcon(org.bonitasoft.studio.model.expression.Expression)
	 */
	@Override
	public Image getIcon(Expression expression) {
		return Pics.getImage("balance.png");
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeIcon()
	 */
	@Override
	public Image getTypeIcon() {
		return Pics.getImage("balance.png");
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getProposalLabel(org.bonitasoft.studio.model.expression.Expression)
	 */
	@Override
	public String getProposalLabel(Expression expression) {
		return expression.getName();
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#isRelevantFor(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public boolean isRelevantFor(EObject context) {
		if (context instanceof SequenceFlow){
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeLabel()
	 */
	@Override
	public String getTypeLabel() {
		// TODO Auto-generated method stub
		return Messages.comparisonType;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionEditor(org.bonitasoft.studio.model.expression.Expression)
	 */
	@Override
	public IExpressionEditor getExpressionEditor(Expression expression,EObject context) {
		return new ComparisonExpressionEditor(context.eResource(),context);
	}

}
