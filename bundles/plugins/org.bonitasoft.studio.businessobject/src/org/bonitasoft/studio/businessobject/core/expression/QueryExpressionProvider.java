/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.core.expression;

import static org.bonitasoft.studio.common.predicate.ExpressionPredicates.withName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.engine.bdm.model.QueryParameter;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.expression.model.BusinessObjectExpressionQuery;
import org.bonitasoft.studio.businessobject.core.expression.model.QueryExpressionModel;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.expression.QueryExpressionEditor;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

import com.google.common.collect.Iterables;

/**
 * @author Romain Bioteau
 */
public class QueryExpressionProvider implements IExpressionProvider {

    @Override
    public Set<Expression> getExpressions(final EObject context) {
        if (context instanceof BusinessObjectData) {
            String className = ((BusinessObjectData) context).getClassName();
            BusinessObject businessObject = getBusinessObjectModel().getBusinessObjects().stream()
                    .filter(bo -> Objects.equals(bo.getQualifiedName(), className))
                    .findFirst()
                    .orElse(null);
            return businessObject != null ? getExpressions(businessObject).stream().collect(Collectors.toSet())
                    : Collections.emptySet();
        }
        return Collections.emptySet();
    }

    public QueryExpressionModel buildQueryExpressionModel() {
        final BusinessObjectModel businessObjectModel = getBusinessObjectModel();
        final QueryExpressionModel queryExpressionModel = new QueryExpressionModel();
        if (businessObjectModel != null) {
            for (final BusinessObject bo : businessObjectModel.getBusinessObjects()) {
                final BusinessObjectExpressionQuery boQuery = new BusinessObjectExpressionQuery(bo.getQualifiedName());
                final List<Expression> expressions = getExpressions(bo);
                if (!expressions.isEmpty()) {
                    boQuery.getQueryExpressions().addAll(expressions);
                    queryExpressionModel.getBusinessObjects().add(boQuery);
                }
            }
        }
        return queryExpressionModel;
    }

    protected List<Expression> getExpressions(final BusinessObject bo) {
        final List<Expression> result = new ArrayList<>();
        for (final Query q : BDMQueryUtil.createProvidedQueriesForBusinessObject(bo)) {
            result.add(createExpression(bo, q));
        }
        for (final Query q : bo.getQueries()) {
            final Expression queryExpression = createExpression(bo, q);
            if (Iterables.find(result, withName(queryExpression.getName()), null) == null) {
                result.add(queryExpression);
            }
        }
        return result;
    }

    private Expression createExpression(final BusinessObject bo, final Query q) {
        final Expression query = ExpressionFactory.eINSTANCE.createExpression();
        final String queryName = toQueryName(bo, q);
        query.setName(queryName);
        query.setContent(q.getContent());
        query.setReturnType(q.getReturnType());
        query.setType(getExpressionType());
        for (final QueryParameter param : q.getQueryParameters()) {
            query.getReferencedElements().add(createQueryParameterExpression(param, ""));
        }
        if (List.class.getName().equals(q.getReturnType())) {// Add pagination parameters
            final QueryParameter startIndexParam = new QueryParameter(BDMQueryUtil.START_INDEX_PARAM_NAME,
                    Integer.class.getName());
            query.getReferencedElements().add(createQueryParameterExpression(startIndexParam, "0"));
            final QueryParameter maxResultsParam = new QueryParameter(BDMQueryUtil.MAX_RESULTS_PARAM_NAME,
                    Integer.class.getName());
            query.getReferencedElements().add(createQueryParameterExpression(maxResultsParam, "20"));
        }
        return query;
    }

    private String toQueryName(final BusinessObject bo, final Query q) {
        final String name = NamingUtils.getSimpleName(bo.getQualifiedName());
        return name + "." + q.getName();
    }

    private Expression createQueryParameterExpression(final QueryParameter param, final String defaultValue) {
        final Expression queryParameter = ExpressionFactory.eINSTANCE.createExpression();
        queryParameter.setName(param.getName());
        queryParameter.setContent(param.getName());
        queryParameter.setType("QUERY_PARAM_TYPE");
        queryParameter.setReturnTypeFixed(true);
        queryParameter.setReturnType(param.getClassName());
        final Expression constantExpression = ExpressionHelper.createConstantExpression(defaultValue,
                param.getClassName());
        constantExpression.setReturnTypeFixed(true);
        queryParameter.getReferencedElements().add(constantExpression);
        return queryParameter;
    }

    protected BusinessObjectModel getBusinessObjectModel() {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = RepositoryManager
                .getInstance()
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore fileStore = repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME,
                true);
        try {
            return fileStore != null ? fileStore.getContent() : null;
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.warning(e.getMessage(), BusinessObjectPlugin.PLUGIN_ID);
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionType()
     */
    @Override
    public String getExpressionType() {
        return ExpressionConstants.QUERY_TYPE;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getIcon(org.bonitasoft.studio.model.expression.
     * Expression)
     */
    @Override
    public Image getIcon(final Expression expression) {
        return Pics.getImage("query.png", BusinessObjectPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeIcon()
     */
    @Override
    public Image getTypeIcon() {
        return Pics.getImage("query.png", BusinessObjectPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getProposalLabel(org.bonitasoft.studio.model.
     * expression.Expression)
     */
    @Override
    public String getProposalLabel(final Expression expression) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(final EObject context) {
        final QueryExpressionModel buildQueryExpressionModel = buildQueryExpressionModel();
        return !buildQueryExpressionModel.getBusinessObjects().isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeLabel()
     */
    @Override
    public String getTypeLabel() {
        return Messages.queryExpressionLabel;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionEditor(org.bonitasoft.studio.model.
     * expression.Expression,
     * org.eclipse.emf.ecore.EObject)
     */
    @Override
    public IExpressionEditor getExpressionEditor(final Expression expression, final EObject context) {
        return new QueryExpressionEditor(this);
    }

}
