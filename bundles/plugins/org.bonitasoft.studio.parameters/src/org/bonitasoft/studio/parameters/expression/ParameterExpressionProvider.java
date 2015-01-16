/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.expression;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.parameters.expression.editor.ParameterEditor;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ParameterExpressionProvider implements IExpressionProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressions(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Set<Expression> getExpressions(final EObject context) {
        final Set<Expression> result = new HashSet<Expression>();
        if (context instanceof EObject) {
            final AbstractProcess process = ModelHelper.getParentProcess(context);
            if (process != null) {
                for (final Parameter p : process.getParameters()) {
                    result.add(ExpressionHelper.createParameterExpression(p));
                }
            }
        }
        return result;
    }



    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionType()
     */
    @Override
    public String getExpressionType() {
        return ExpressionConstants.PARAMETER_TYPE;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getIcon(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public Image getIcon(final Expression expression) {
        return Pics.getImage(PicsConstants.parameter);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeIcon()
     */
    @Override
    public Image getTypeIcon() {
        return Pics.getImage(PicsConstants.parameter);
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
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeLabel()
     */
    @Override
    public String getTypeLabel() {
        return Messages.parameters;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionEditor(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public IExpressionEditor getExpressionEditor(final Expression expression, final EObject context) {
        return new ParameterEditor();
    }

}
