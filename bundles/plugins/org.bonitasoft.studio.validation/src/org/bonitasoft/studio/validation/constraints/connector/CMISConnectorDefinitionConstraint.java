package org.bonitasoft.studio.validation.constraints.connector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;

public class CMISConnectorDefinitionConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraints.cmisConnectorConfigurationConstraint";
    private static final String ATOM_PUB = "atompub";
    private static final String WEB_SERVICES = "webservices";
    private static Set<String> CMIS_DEF_IDS;
    static {
        CMIS_DEF_IDS = new HashSet<String>();
        CMIS_DEF_IDS.add("cmis-createfolder");
        CMIS_DEF_IDS.add("cmis-deletefolder");
        CMIS_DEF_IDS.add("cmis-deletedocument");
        CMIS_DEF_IDS.add("cmis-deleteversionofdocument");
        CMIS_DEF_IDS.add("cmis-downloaddocument");
        CMIS_DEF_IDS.add("cmis-uploadnewdocument");
        CMIS_DEF_IDS.add("cmis-uploadnewversionofdocument");
    }

    @Override
    protected IStatus performLiveValidation(final IValidationContext context) {
        return null;
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final Connector connector = (Connector)context.getTarget();
        final ConnectorConfiguration configuration = connector.getConfiguration();
        if (isCMISConnectorDefinition(connector)) {
            final ConnectorParameter bindingTypeParam = getParameter("binding_type", configuration);
            if (bindingTypeParam == null) {
                return context.createFailureStatus("binding_type parameter not set");
            }
            final String type = getBindingType(bindingTypeParam);
            if (ATOM_PUB.equals(type)) {
                return validateAtomPubConfiguration(configuration, context);
            } else if (WEB_SERVICES.equals(type)) {
                return validateWebserviceConfiguration(configuration, context);
            }
            return context.createFailureStatus("Unknown binding_type parameter");
        }

        return context.createSuccessStatus();
    }

    private IStatus validateWebserviceConfiguration(final ConnectorConfiguration configuration, final IValidationContext context) {
        final List<ConnectorParameter> serviceUrlParams = getAllParametersContaining("ServiceUrl", configuration);
        final List<ConnectorParameter> serviceEdpointUrlParams = getAllParametersContaining("ServiceEndpointUrl", configuration);

        if (serviceUrlParams.isEmpty()
                && serviceEdpointUrlParams.isEmpty()) {
            return context.createFailureStatus(Messages.bind(Messages.cmisConnectorWebserviceConfigMissingUrl, ((Element) context.getTarget()).getName()));
        }

        return context.createSuccessStatus();
    }


    private IStatus validateAtomPubConfiguration(final ConnectorConfiguration configuration, final IValidationContext context) {
        final ConnectorParameter urlParam = getParameter("url", configuration);
        if (urlParam == null
                || !isAnExpressionWithContent((Expression) urlParam.getExpression())) {
            return context.createFailureStatus(Messages.bind(Messages.cmisConnectorAtomPubConfigMissingUrl, ((Element) context.getTarget()).getName()));
        }
        return context.createSuccessStatus();
    }

    protected boolean isAnExpressionWithContent(final Expression expression) {
        return expression != null
                && expression.getContent() != null
                && !expression.getContent().isEmpty();
    }

    protected String getBindingType(final ConnectorParameter bindingTypeParam) {
        final Expression expression = (Expression) bindingTypeParam.getExpression();
        if (expression != null) {
            return expression.getContent();
        }
        return null;
    }

    private ConnectorParameter getParameter(final String key, final ConnectorConfiguration configuration) {
        for (final ConnectorParameter param : configuration.getParameters()) {
            if (key.equals(param.getKey())) {
                return param;
            }
        }
        return null;
    }

    private List<ConnectorParameter> getAllParametersContaining(final String containing, final ConnectorConfiguration configuration) {
        final List<ConnectorParameter> result = new ArrayList<ConnectorParameter>();
        for (final ConnectorParameter param : configuration.getParameters()) {
            if (param.getKey().contains(containing)
                    && isAnExpressionWithContent((Expression) param.getExpression())) {
                result.add(param);
            }
        }
        return result;
    }

    private boolean isCMISConnectorDefinition(final Connector connector) {
        final String definitionId = connector.getDefinitionId();
        return CMIS_DEF_IDS.contains(definitionId);
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

}
