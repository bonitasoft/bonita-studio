/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.custom.migration.connector;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class UpdateCMISConnectorVersionCustomMigration extends CustomMigration {

    public static final String WEBSERVICES_OBJECT_SERVICE = "wsObjectServiceUrl";
    public static final String WEBSERVICES_REPOSITORY_SERVICE = "wsRepositoryServiceUrl";
    public static final String WEBSERVICES_VERSIONING_SERVICE = "wsVersioningServiceUrl";
    public static final String WEBSERVICES_NAVIGATION_SERVICE = "wsNavigationServiceUrl";
    private static final String VERSIONING_SERVICE_WSDL = "VersioningService?wsdl";
    private static final String OBJECT_SERVICE_WSDL = "ObjectService?wsdl";
    private static final String REPOSITORY_SERVICE_WSDL = "RepositoryService?wsdl";
    private static final String NAVIGATION_SERVICE_WSDL = "NavigationService?wsdl";

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for (final Instance connectorInstance : model.getAllInstances("process.Connector")) {
            final String defId = connectorInstance.get("definitionId");
            final String defVersion = connectorInstance.get("definitionVersion");
            if (isProvidedCMISConnectorDef(defId)) {
                if(defVersion.equals("1.0.0")) {
                    connectorInstance.set("definitionVersion", "2.0.1");
                    final Instance connectorConfigInstance = connectorInstance.get("configuration");
                    if (connectorConfigInstance != null) {
                        connectorConfigInstance.set("version", "2.0.1");
                        if (isWebserviceBinding(connectorConfigInstance) && isSupportedURLExpression(connectorConfigInstance)) {
                            addServiceURL(connectorConfigInstance, model, WEBSERVICES_OBJECT_SERVICE,
                                    createURLValueExpression(model, connectorConfigInstance, OBJECT_SERVICE_WSDL));
                            addServiceURL(connectorConfigInstance, model, WEBSERVICES_REPOSITORY_SERVICE,
                                    createURLValueExpression(model, connectorConfigInstance, REPOSITORY_SERVICE_WSDL));
                            if (defId.equals("cmis-deleteversionofdocument")) {
                                addServiceURL(connectorConfigInstance, model, WEBSERVICES_VERSIONING_SERVICE,
                                        createURLValueExpression(model, connectorConfigInstance, VERSIONING_SERVICE_WSDL));
                                addServiceURL(connectorConfigInstance, model, WEBSERVICES_NAVIGATION_SERVICE,
                                        createURLValueExpression(model, connectorConfigInstance, NAVIGATION_SERVICE_WSDL));
                            }
                            final Instance urlExpression = getURLExpression(connectorConfigInstance);
                            model.delete(urlExpression);
                        }
                    }
                    if(defId.equals("cmis-downloaddocument")){
                        updateConnectorOuputName(connectorInstance);
                    }
                }
            }
        }
    }

    private boolean isSupportedURLExpression(final Instance connectorConfigInstance) {
        final Instance urlExpression = getURLExpression(connectorConfigInstance);
        return isConstantExpression(urlExpression) || isVariableExpression(urlExpression);
    }

    private Instance createURLValueExpression(final Model model, final Instance connectorConfigInstance, final String wsdlPath) {
        final Instance urlExpression = getURLExpression(connectorConfigInstance);
        if (isConstantExpression(urlExpression)) {
            return transformConstantExpression(model, wsdlPath, urlExpression);
        } else if (isVariableExpression(urlExpression)) {
            return transformVariableExpression(model, wsdlPath, urlExpression);
        }
        return null;
    }

    private Instance transformVariableExpression(final Model model, final String wsdlPath, final Instance urlExpression) {
        final String variableName = urlExpression.get("content");
        final Instance scriptExpressionInstance = StringToExpressionConverter.createExpressionInstance(model, wsdlPath,
                variableName + " + \"/" + wsdlPath + "\"",
                String.class.getName(),
                ExpressionConstants.SCRIPT_TYPE, true);

        final Instance dependency = createExpressionDependencyFrom(model, urlExpression);
        if (dependency != null) {
            scriptExpressionInstance.add("referencedElements", dependency);
        }
        return scriptExpressionInstance;
    }

    private Instance createExpressionDependencyFrom(final Model model, final Instance urlExpression) {
        final List<Instance> dependencies = urlExpression.get("referencedElements");
        if (!dependencies.isEmpty()) {
            return dependencies.get(0).copy();
        }
        return null;
    }

    private boolean isVariableExpression(final Instance urlExpression) {
        final String expType = urlExpression.get("type");
        return ExpressionConstants.VARIABLE_TYPE.equals(expType) ||
                ExpressionConstants.PARAMETER_TYPE.equals(expType);
    }

    private Instance transformConstantExpression(final Model model, final String wsdlPath, final Instance urlExpression) {
        String serverUrl = urlExpression.get("content");
        if (!serverUrl.endsWith("/")) {
            serverUrl = serverUrl + "/";
        }
        final String newInputValue = serverUrl + wsdlPath;
        return StringToExpressionConverter.createExpressionInstance(model, newInputValue, newInputValue, String.class.getName(),
                ExpressionConstants.CONSTANT_TYPE, true);
    }

    private boolean isConstantExpression(final Instance urlExpression) {
        return ExpressionConstants.CONSTANT_TYPE.equals(urlExpression.get("type"));
    }

    private Instance getURLExpression(final Instance connectorConfigInstance) {
        final Instance urlParameter = getConnectorParameter("url", connectorConfigInstance);
        final Instance urlExpression = urlParameter.get("expression");
        return urlExpression;
    }

    private void addServiceURL(final Instance connectorConfigInstance, final Model model, final String newInputName, final Instance valueExpression) {
        final Instance newParameter = model.newInstance("connectorconfiguration.ConnectorParameter");
        newParameter.set("key", newInputName);
        newParameter.set("expression", valueExpression);
        connectorConfigInstance.add("parameters", newParameter);
    }

    private boolean isWebserviceBinding(final Instance connectorConfigInstance) {
        final Instance bindingParameter = getConnectorParameter("binding_type", connectorConfigInstance);
        if (bindingParameter != null) {
            final Instance exp = bindingParameter.get("expression");
            final String bindingValue = exp.get("content");
            return bindingValue.equals("webservices");
        }
        return false;
    }

    private Instance getConnectorParameter(final String inputName, final Instance connectorConfiguration) {
        final List<Instance> connectorParameterInstances = connectorConfiguration.get("parameters");
        for (final Instance connectorParameter : connectorParameterInstances) {
            final String input = connectorParameter.get("key");
            if (inputName.equals(input)) {
                return connectorParameter;
            }
        }
        return null;
    }

    private void updateConnectorOuputName(final Instance connectorInstance) {
        final List<Instance> outputOperationInstances = connectorInstance.get("outputs");
        for (final Instance operation : outputOperationInstances) {
            final Instance rightOperandExpression = operation.get("rightOperand");
            String expContent = rightOperandExpression.get("content");
            if (expContent.contains("documentOuput")) {
                expContent = expContent.replace("documentOuput", "documentOutput");
                rightOperandExpression.set("content", expContent);
            }
            final List<Instance> referencedElementInstances = rightOperandExpression.get("referencedElements");
            for (final Instance ref : referencedElementInstances) {
                try {
                    final String name = ref.get("name");
                    if ("documentOuput".equals(name)) {
                        ref.set("name", "documentOutput");
                        break;
                    }
                } catch (final IllegalArgumentException ex) {
                    continue;
                }
            }
        }
    }

    private boolean isProvidedCMISConnectorDef(final String defId) {
        return defId.equals("cmis-createfolder") ||
                defId.equals("cmis-deletedocument") ||
                defId.equals("cmis-deletefolder") ||
                defId.equals("cmis-deleteversionofdocument") ||
                defId.equals("cmis-downloaddocument") ||
                defId.equals("cmis-uploadnewdocument") ||
                defId.equals("cmis-uploadnewversionofdocument");
    }

}
