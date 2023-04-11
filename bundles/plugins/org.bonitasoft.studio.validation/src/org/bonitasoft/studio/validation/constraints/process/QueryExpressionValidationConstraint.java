/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/

package org.bonitasoft.studio.validation.constraints.process;

import java.util.List;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.engine.bdm.model.QueryParameter;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.validation.ValidationPlugin;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class QueryExpressionValidationConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.ex.constraint.businessobject.checkQueryExpression";

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final EObject eObj = context.getTarget();
        if (eObj instanceof Expression && ExpressionConstants.QUERY_TYPE.equals(((Expression) eObj).getType())) {
            final Expression queryExpression = (Expression) eObj;
            final String queryName = queryExpression.getName();
            if (queryName.indexOf(".") == 0) {
                return context.createFailureStatus(Messages.bind(Messages.invalidQueryExpression, queryName));
            }
            final String[] split = queryName.split("\\.");
            if (split.length != 2) {
                return context.createFailureStatus(Messages.bind(Messages.invalidQueryExpression, queryName));
            }
            final String simpleBOName = split[0];
            final BusinessObject businessObject = getBusinessObject(simpleBOName);
            if (businessObject == null) {
                return context.createFailureStatus(
                        Messages.bind(Messages.queryReferencedAnUnexistingBusinessObject, simpleBOName, queryName));
            }
            final String namedQuery = split[1];
            final Query q = getQuery(businessObject, namedQuery);
            if (q == null) {
                return context.createFailureStatus(
                        Messages.bind(Messages.queryNotExistsInBusinessObject, namedQuery, simpleBOName));
            }

            final String returnType = queryExpression.getReturnType();
            if (!q.getReturnType().equals(returnType)) {
                return context.createFailureStatus(Messages.bind(Messages.queryReturnTypeIsInvalid,
                        new String[] { queryName, returnType, q.getReturnType() }));
            }

            for (final EObject queryExpressionParam : queryExpression.getReferencedElements()) {
                if (queryExpressionParam instanceof Expression) {
                    final Expression parameterExpression = (Expression) queryExpressionParam;
                    final String paramName = parameterExpression.getName();
                    if ((paramName.equals(BDMQueryUtil.START_INDEX_PARAM_NAME)
                            || paramName.equals(BDMQueryUtil.MAX_RESULTS_PARAM_NAME))
                            && returnType.equals(List.class.getName())) {
                        continue;
                    }
                    QueryParameter correspondingParam = null;
                    for (final QueryParameter p : q.getQueryParameters()) {
                        if (p.getName().equals(paramName)) {
                            correspondingParam = p;
                        }
                    }
                    if (correspondingParam == null) {
                        return context
                                .createFailureStatus(Messages.bind(Messages.queryParameterDoesNotExistInQueryDefinition,
                                        new String[] { queryName, paramName }));
                    } else {
                        if (!correspondingParam.getClassName().equals(parameterExpression.getReturnType())) {
                            return context.createFailureStatus(Messages.bind(Messages.queryParameterReturnTypeIsInvalid,
                                    new String[] { queryName, paramName, parameterExpression.getReturnType(),
                                            correspondingParam.getClassName() }));
                        }
                    }
                }
            }

            for (final QueryParameter p : q.getQueryParameters()) {
                boolean found = false;
                for (final EObject queryExpressionParam : queryExpression.getReferencedElements()) {
                    final Expression parameterExpression = (Expression) queryExpressionParam;
                    final String paramName = parameterExpression.getName();
                    if (p.getName().equals(paramName)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return context.createFailureStatus(Messages.bind(Messages.queryParameterDoesNotExistInExpression,
                            new String[] { queryName, p.getName() }));
                }
            }

        }
        return context.createSuccessStatus();
    }

    protected Query getQuery(final BusinessObject businessObject, final String namedQuery) {
        for (final Query q : BDMQueryUtil.createProvidedQueriesForBusinessObject(businessObject)) {
            if (namedQuery.equals(q.getName())) {
                return q;
            }
        }
        for (final Query q : businessObject.getQueries()) {
            if (namedQuery.equals(q.getName())) {
                return q;
            }
        }
        return null;
    }

    protected BusinessObject getBusinessObject(final String simpleBOName) {
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = getBusinessObjectDefinitionStore();
        BusinessObjectModelFileStore fStore = store.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        try {
            BusinessObjectModel  businessObjectModel = fStore.getContent();
            for (final BusinessObject bo : businessObjectModel.getBusinessObjects()) {
                if (simpleBOName.equals(NamingUtils.getSimpleName(bo.getQualifiedName()))) {
                    return bo;
                }
            }
        } catch (ReadFileStoreException e) {
          BonitaStudioLog.warning(e.getMessage(), ValidationPlugin.PLUGIN_ID);
        }
        return null;
    }

    protected BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> getBusinessObjectDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

}
