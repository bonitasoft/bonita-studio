/**
 *
 */
package org.bonitasoft.studio.condition.ui.expression;

import java.util.Collections;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.condition.ui.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.decision.DecisionTableLine;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * @author aurelie zara
 */
public class ComparisonExpressionProvider implements IExpressionProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressions(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Set<Expression> getExpressions(final EObject context) {
        return Collections.emptySet();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionType()
     */
    @Override
    public String getExpressionType() {
        return ExpressionConstants.CONDITION_TYPE;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getIcon(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public Image getIcon(final Expression expression) {
        return Pics.getImage("balance.png");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeIcon()
     */
    @Override
    public Image getTypeIcon() {
        return Pics.getImage("balance.png");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getProposalLabel(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public String getProposalLabel(final Expression expression) {
        return expression.getName();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(final EObject context) {
        return context instanceof SequenceFlow || context instanceof DecisionTableLine;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeLabel()
     */
    @Override
    public String getTypeLabel() {
        return Messages.comparisonType;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionEditor(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public IExpressionEditor getExpressionEditor(final Expression expression, final EObject context) {
        if (context != null) {
            return new ComparisonExpressionEditor(context);
        }
        return null;
    }

}
