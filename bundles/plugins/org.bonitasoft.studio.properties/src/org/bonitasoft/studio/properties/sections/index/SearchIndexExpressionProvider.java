/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
/**
 *
 */
package org.bonitasoft.studio.properties.sections.index;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.properties.Activator;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

public class SearchIndexExpressionProvider implements IExpressionProvider {

    @Override
    public Set<Expression> getExpressions(final EObject context) {
        final Set<Expression> result = new HashSet<>();
        final Pool pool = (Pool) ModelHelper.getParentProcess(context);
        if (pool != null) {
            for (final SearchIndex searchIndex : pool.getSearchIndexes()) {
                if (!searchIndex.getName().getContent().trim().isEmpty()) {
                    result.add(createExpression(searchIndex));
                }
            }
        }
        return result;
    }

    private Expression createExpression(final SearchIndex searchIndex) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(getExpressionType());
        exp.setName(searchIndex.getName().getContent());
        exp.setContent(searchIndex.getName().getContent());
        exp.setType(getExpressionType());
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(searchIndex));
        return exp;
    }

    @Override
    public String getExpressionType() {
        return ExpressionConstants.SEARCH_INDEX_TYPE;
    }

    @Override
    public Image getIcon(final Expression expression) {
        if (expression.getReferencedElements().isEmpty()) {
            return null;
        }
        return getTypeIcon();
    }

    @Override
    public Image getTypeIcon() {
        return Pics.getImage("SearchIndex.gif", Activator.getDefault());
    }

    @Override
    public String getProposalLabel(final Expression expression) {
        return expression.getName();
    }

    @Override
    public boolean isRelevantFor(final EObject context) {
        return ModelHelper.getParentProcess(context) instanceof Pool;
    }

    @Override
    public String getTypeLabel() {
        return Messages.searchIndexTypeLabel;
    }

    @Override
    public IExpressionEditor getExpressionEditor(final Expression expression, final EObject context) {
        return null;
    }

}
