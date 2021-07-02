/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.wizard.office.validation;

import static org.bonitasoft.studio.common.predicate.ConnectorParameterPredicates.withInputName;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.jface.databinding.validator.TypedValidator;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.wizard.office.templating.TableExpressionWithoutLazyLoadedRefs;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author aurelie
 */
public class LazyLoadedBusinessObjectReferenceConstraint extends AbstractLiveValidationMarkerConstraint {

    private final static String CONNECTOR_ID = "document-templating";
    private final static String REPLACEMENT_PARAMETER_ID = "replacements";

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint#performBatchValidation(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final Connector connector = (Connector) context.getTarget();
        if (isDocumentTemplatingConnector(connector)) {
            final ConnectorConfiguration configuration = connector.getConfiguration();
            final ConnectorParameter replacementParameter = getReplacementConnectorParameter(configuration);
            final AbstractExpression expression = replacementParameter.getExpression();
            if (expression instanceof TableExpression) {
                final TypedValidator<TableExpression, IStatus> validator = new TableExpressionWithoutLazyLoadedRefs(
                        getBusinessObjectRepositoryStore(), true);
                final IStatus status = validator.validate(expression);
                return status.getSeverity() == 0 ? context.createSuccessStatus()
                        : context.createFailureStatus(status.getMessage());
            }
        }
        return context.createSuccessStatus();
    }

    protected BusinessObjectModelRepositoryStore getBusinessObjectRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint#getConstraintId()
     */
    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.ex.constraint.officeConnectorConstraint";
    }

    private boolean isDocumentTemplatingConnector(final Connector connector) {
        return CONNECTOR_ID.equals(connector.getDefinitionId());
    }

    private ConnectorParameter getReplacementConnectorParameter(final ConnectorConfiguration configuration) {
        return configuration.getParameters().stream()
                .filter(withInputName(REPLACEMENT_PARAMETER_ID))
                .findFirst()
                .orElse(null);
    }

}
