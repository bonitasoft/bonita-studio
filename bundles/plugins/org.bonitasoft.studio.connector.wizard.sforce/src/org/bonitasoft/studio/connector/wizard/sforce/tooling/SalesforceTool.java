/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.wizard.sforce.tooling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.DescribeGlobalResult;
import com.sforce.soap.partner.DescribeGlobalSObjectResult;
import com.sforce.soap.partner.DescribeSObjectResult;
import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.GetUserInfoResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.PicklistEntry;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

/**
 * @author Maxence Raoux
 * @author Charles Souillard
 *
 */
public class SalesforceTool {

    private PartnerConnection connection;
    private ConnectorConfig lastConfig;
    private static SalesforceTool INSTANCE;

    public static SalesforceTool getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SalesforceTool();
        }
        return INSTANCE;
    }

    public void initialize(final String username,
            final String password_securityToken) throws ConnectionException {

        final ConnectorConfig config = new ConnectorConfig();
        config.setUsername(username);
        config.setPassword(password_securityToken);

        connection = Connector.newConnection(config);
    }

    public void initialize(final String username,
            final String password_securityToken, final String authEndpoint,
            final String serviceEndpoint, final String restEndpoint,
            final String proxyHost, final int proxyPort,
            final String proxyUsername, final String proxyPassword,
            final int connectionTimeout, final int readTimeout)
                    throws ConnectionException {

        final ConnectorConfig config = new ConnectorConfig();
        config.setUsername(username);
        config.setPassword(password_securityToken);
        if (authEndpoint != null && !authEndpoint.isEmpty()) {
            config.setAuthEndpoint(authEndpoint);
        }
        if (proxyHost != null && !proxyHost.isEmpty()) {
            config.setProxy(proxyHost, proxyPort);
        }
        if (proxyPassword != null && !proxyPassword.isEmpty()) {
            config.setProxyPassword(proxyPassword);
        }
        if (proxyUsername != null && !proxyUsername.isEmpty()) {
            config.setProxyUsername(proxyUsername);
        }
        if (connectionTimeout != 0) {
            config.setConnectionTimeout(connectionTimeout);
        }
        if (readTimeout != 0) {
            config.setReadTimeout(readTimeout);
        }
        if (restEndpoint != null && !restEndpoint.isEmpty()) {
            config.setRestEndpoint(restEndpoint);
        }
        if (serviceEndpoint != null && !serviceEndpoint.isEmpty()) {
            config.setServiceEndpoint(serviceEndpoint);
        }

        connection = Connector.newConnection(config);
        lastConfig = config;
    }

    public GetUserInfoResult getUserInfo() throws NoConnectionException, ConnectionException {
        if (connection == null) {
            throw new NoConnectionException(
                    "No connection is established to the server. Please call initialize first.");
        }
        try {
            return connection.getUserInfo();
        } catch(final ConnectionException ce){
            reconnect();
            return connection.getUserInfo();
        }
    }

    public void logout() throws ConnectionException {
        try {
            if (connection != null) {
                connection.logout();
            }
        } finally {
            connection = null;
        }
    }

    public boolean isLogged() {
        return connection != null;
    }

    public DescribeGlobalSObjectResult[] getDescribeGlobalResult() throws ConnectionException {
        try {
            return getDescribeGlobalResultWithoutRetry();
        } catch (final ConnectionException ce){
            BonitaStudioLog.error(ce);
            reconnect();
            return getDescribeGlobalResultWithoutRetry();
        }
    }

    private DescribeGlobalSObjectResult[] getDescribeGlobalResultWithoutRetry() throws ConnectionException {
        final DescribeGlobalResult describeGlobalResult = connection.describeGlobal();
        return describeGlobalResult.getSobjects();
    }

    public DescribeSObjectResult getSObjectDescription(final String sObjectName) throws ConnectionException, NoSObjectFoundException {
        try {
            return getSObjectDescriptionWithoutRetry(sObjectName);
        } catch (final ConnectionException ce){
            BonitaStudioLog.error(ce);
            reconnect();
            return getSObjectDescriptionWithoutRetry(sObjectName);
        }
    }

    private DescribeSObjectResult getSObjectDescriptionWithoutRetry(
            final String sObjectName) throws ConnectionException,
            NoSObjectFoundException {
        final DescribeSObjectResult describeSObjectResult = connection.describeSObject(sObjectName);
        if (describeSObjectResult == null) {
            throw new NoSObjectFoundException(sObjectName,
                    "No object found with name: " + sObjectName);
        }
        return describeSObjectResult;
    }

    public Map<String, String> getSObjectDetails(
            final DescribeSObjectResult sObjectResult) {
        final Map<String, String> details = new HashMap<String, String>();
        details.put("keyPrefix", sObjectResult.getKeyPrefix());
        details.put("label", sObjectResult.getLabel());
        details.put("labelPlural", sObjectResult.getLabelPlural());
        details.put("name", sObjectResult.getName());
        details.put("urlDetail", sObjectResult.getUrlDetail());
        details.put("urlEdit", sObjectResult.getUrlEdit());
        details.put("urlNew", sObjectResult.getUrlNew());
        details.put("activateable",
                Boolean.toString(sObjectResult.getActivateable()));
        details.put("createable",
                Boolean.toString(sObjectResult.getCreateable()));
        details.put("custom", Boolean.toString(sObjectResult.getCustom()));
        details.put("customSetting",
                Boolean.toString(sObjectResult.getCustomSetting()));
        details.put("deletable", Boolean.toString(sObjectResult.getDeletable()));
        details.put("deprecatedAndHidden",
                Boolean.toString(sObjectResult.getDeprecatedAndHidden()));
        details.put("layoutable",
                Boolean.toString(sObjectResult.getLayoutable()));
        details.put("mergeable", Boolean.toString(sObjectResult.getMergeable()));
        details.put("queryable", Boolean.toString(sObjectResult.getQueryable()));
        details.put("replicateable",
                Boolean.toString(sObjectResult.getReplicateable()));
        details.put("retrieveable",
                Boolean.toString(sObjectResult.getRetrieveable()));
        details.put("searchable",
                Boolean.toString(sObjectResult.getSearchable()));
        details.put("triggerable",
                Boolean.toString(sObjectResult.getTriggerable()));
        details.put("undeletable",
                Boolean.toString(sObjectResult.getUndeletable()));
        details.put("updateable",
                Boolean.toString(sObjectResult.getUpdateable()));
        return details;
    }

    public Field[] getSObjectFields(final DescribeSObjectResult sObjectResult) {
        final Field[] fields = sObjectResult.getFields();
        if (fields != null) {
            return fields;
        }
        return new Field[0];
    }

    public int getNumberOfFields(final DescribeSObjectResult sObjectResult) {
        return sObjectResult.getFields().length;
    }

    public List<String> getFieldNames(final DescribeSObjectResult sObjectResult) {
        final Field[] fields = sObjectResult.getFields();
        if (fields == null) {
            return Collections.emptyList();
        }

        final List<String> fieldNames = new ArrayList<String>();
        for (final Field field : fields) {
            final String name = field.getName();
            fieldNames.add(name);
        }
        Collections.sort(fieldNames);
        return fieldNames;
    }

    public Map<String, String> getFieldDetails(final Field field) {
        final Map<String, String> details = new HashMap<String, String>();
        details.put("calculatedFormula", field.getCalculatedFormula());
        details.put("controllerName", field.getControllerName());
        details.put("defaultValueFormula", field.getDefaultValueFormula());
        details.put("inlineHelpText", field.getInlineHelpText());
        details.put("label", field.getLabel());
        details.put("name", field.getName());
        details.put("relationshipName", field.getRelationshipName());
        details.put("autoNumber", Boolean.toString(field.getAutoNumber()));
        details.put("byteLength", Integer.toString(field.getByteLength()));
        details.put("calculated", Boolean.toString(field.getCalculated()));
        details.put("caseSensitive", Boolean.toString(field.getCaseSensitive()));
        details.put("createable", Boolean.toString(field.getCreateable()));
        details.put("custom", Boolean.toString(field.getCustom()));
        details.put("defaultedOnCreate",
                Boolean.toString(field.getDefaultedOnCreate()));
        details.put("dependentPicklist",
                Boolean.toString(field.getDependentPicklist()));
        details.put("deprecatedAndHidden",
                Boolean.toString(field.getDeprecatedAndHidden()));
        details.put("digits", Integer.toString(field.getDigits()));
        details.put("externalId", Boolean.toString(field.getExternalId()));
        details.put("filterable", Boolean.toString(field.getFilterable()));
        details.put("groupable", Boolean.toString(field.getGroupable()));
        details.put("htmlFormatted", Boolean.toString(field.getHtmlFormatted()));
        details.put("idLookup", Boolean.toString(field.getIdLookup()));
        details.put("length", Integer.toString(field.getLength()));
        details.put("nameField", Boolean.toString(field.getNameField()));
        details.put("namePointing", Boolean.toString(field.getNamePointing()));
        details.put("nillable", Boolean.toString(field.getNillable()));
        details.put("precision", Integer.toString(field.getPrecision()));
        details.put("relationshipOrder",
                Integer.toString(field.getRelationshipOrder()));
        details.put("restrictedPicklist",
                Boolean.toString(field.getRestrictedPicklist()));
        details.put("scale", Integer.toString(field.getScale()));
        details.put("sortable", Boolean.toString(field.getSortable()));
        details.put("unique", Boolean.toString(field.getUnique()));
        details.put("updateable", Boolean.toString(field.getUpdateable()));
        details.put("writeRequiresMasterRead",
                Boolean.toString(field.getWriteRequiresMasterRead()));
        return details;
    }

    public boolean isPickListValues(final Field field) {
        final PicklistEntry[] picklistValues = field.getPicklistValues();
        if (picklistValues != null && picklistValues.length > 0) {
            return true;
        }
        return false;
    }

    public boolean isReferenceTos(final Field field) {
        final String[] referenceTos = field.getReferenceTo();
        if (referenceTos != null && referenceTos.length > 0) {
            return true;
        }
        return false;
    }

    public List<String> getPickListValues(final Field field) {
        final PicklistEntry[] picklistValues = field.getPicklistValues();
        if (!isPickListValues(field)) {
            return Collections.emptyList();
        }
        final List<String> pickListValues = new ArrayList<String>();
        for (int j = 0; j < picklistValues.length; j++) {
            if (picklistValues[j].getLabel() != null) {
                pickListValues.add(picklistValues[j].getLabel());
            } else {
                pickListValues.add(picklistValues[j].getValue());
            }
        }
        Collections.sort(pickListValues);
        return pickListValues;
    }

    public String getDefaultPickListValue(final Field field) {
        final PicklistEntry[] picklistValues = field.getPicklistValues();
        if (!isPickListValues(field)) {
            return null;
        }
        for (int j = 0; j < picklistValues.length; j++) {
            if (picklistValues[j].getDefaultValue()) {
                if (picklistValues[j].getLabel() != null) {
                    return picklistValues[j].getLabel();
                } else {
                    return picklistValues[j].getValue();
                }
            }
        }
        return null;
    }

    public boolean hasDefaultPickListValue(final Field field) {
        final PicklistEntry[] picklistValues = field.getPicklistValues();
        if (!isPickListValues(field)) {
            return false;
        }
        for (int j = 0; j < picklistValues.length; j++) {
            if (picklistValues[j].getDefaultValue()) {
                return true;
            }
        }
        return false;
    }

    public List<String> getReferencesTo(final Field field) {
        if (!isReferenceTos(field)) {
            return Collections.emptyList();
        }
        final List<String> referenceTos = new ArrayList<String>();
        for (final String referenceTo : field.getReferenceTo()) {
            referenceTos.add(referenceTo);
        }
        Collections.sort(referenceTos);
        return referenceTos;
    }

    public void reconnect() throws ConnectionException {
        logout();
        if (lastConfig != null) {
            final ConnectorConfig config = new ConnectorConfig();
            config.setUsername(lastConfig.getUsername());
            config.setPassword(lastConfig.getPassword());
            connection = Connector.newConnection(config);
        }
    }

    private QueryResult makeQuery(final String query) throws ConnectionException {
        try {
            return connection.query(query);
        } catch(final ConnectionException ce){
            BonitaStudioLog.error(ce);
            reconnect();
            return connection.query(query);
        }
    }

    public List<String> getMandatoryFields(final String sObjectName) {
        List<String> mandatoryFields = new ArrayList<String>();
        try {
            mandatoryFields = getMandatoryFieldsWithoutRetry(sObjectName);
        } catch (final ConnectionException e) {
            BonitaStudioLog.error(e);
            try {
                mandatoryFields = getMandatoryFieldsWithoutRetry(sObjectName);
            } catch (final ConnectionException e1) {
                BonitaStudioLog.error(e1);
            } catch (final NoSObjectFoundException e1) {
                BonitaStudioLog.error(e1);
            }
        } catch (final NoSObjectFoundException e) {
            BonitaStudioLog.error(e);
        }
        return mandatoryFields;
    }

    private List<String> getMandatoryFieldsWithoutRetry(final String sObjectName) throws ConnectionException,
    NoSObjectFoundException {
        final List<String> mandatoryFields = new ArrayList<String>();
        if (isLogged()) {
            final DescribeSObjectResult sObjectResult = getSObjectDescription(sObjectName);
            for (final Field sObjectField : sObjectResult.getFields()) {
                if (!sObjectField.isNillable()
                        && !sObjectField.isDefaultedOnCreate()) {
                    mandatoryFields.add(sObjectField.getName());
                }
            }
        }
        return mandatoryFields;
    }

    public List<String> getAllSForceObjects() {
        final List<String> objectsSForce = new ArrayList<String>();
        try {
            getAllSForceObjectWithoutRetry(objectsSForce);
        } catch (final ConnectionException e) {
            BonitaStudioLog.error(e);
            try{
                reconnect();
                getAllSForceObjectWithoutRetry(objectsSForce);
            } catch(final ConnectionException ce){
                BonitaStudioLog.error(ce);
            }
        }
        return objectsSForce;
    }

    private void getAllSForceObjectWithoutRetry(final List<String> objectsSForce)
            throws ConnectionException {
        if (isLogged()) {
            final DescribeGlobalSObjectResult[] globalSObjectResults = getDescribeGlobalResult();
            for (final DescribeGlobalSObjectResult globalSObjectResult : globalSObjectResults) {
                objectsSForce.add(globalSObjectResult.getName());
            }
        }
    }

    public List<String> getFields(final String sObjectName) {
        final List<String> fieldsObjectSForce = new ArrayList<String>();
        try {
            if (isLogged()) {
                final DescribeSObjectResult sObjectResult = getSObjectDescription(sObjectName);
                final Field[] sObjectFields = getSObjectFields(sObjectResult);
                for (final Field sObjectField : sObjectFields) {
                    fieldsObjectSForce.add(sObjectField.getName());
                }
            }
        } catch (final ConnectionException e) {
            BonitaStudioLog.error(e);
        } catch (final NoSObjectFoundException e) {
            BonitaStudioLog.error(e);
        }
        return fieldsObjectSForce;
    }

    public List<String> getPickValues(final String sObjectName, final String fieldName) {
        List<String> pickValuesFields = new ArrayList<String>();
        try {
            if (isLogged()) {
                final DescribeSObjectResult sObjectResult = getSObjectDescription(sObjectName);
                final Field[] sObjectFields = getSObjectFields(sObjectResult);
                for (final Field sObjectField : sObjectFields) {
                    if (sObjectField.getName().equals(fieldName)) {
                        if (isPickListValues(sObjectField)) {
                            pickValuesFields = getPickListValues(sObjectField);
                        }
                        return pickValuesFields;
                    }
                }
            }
        } catch (final ConnectionException e) {
            BonitaStudioLog.error(e);
        } catch (final NoSObjectFoundException e) {
            BonitaStudioLog.error(e);
        }
        return pickValuesFields;
    }

    public HashMap<String, String> getObjectIdAndName(final String objectName) {
        final HashMap<String, String> result = new HashMap<String, String>();
        try {
            if (isLogged()) {
                final List<String> fields = getFields(objectName);
                String query = "";
                String fieldsName = "";
                if (fields.contains("Name")) {
                    fieldsName = "Name";
                    query = "SELECT Id, " + fieldsName + " FROM " + objectName;
                } else if (fields.size() >= 2) {
                    fieldsName = fields.get(2);
                    query = "SELECT Id, " + fieldsName + " FROM " + objectName;
                } else {
                    query = "SELECT Id FROM " + objectName;
                }
                final QueryResult qResult = makeQuery(query);
                final SObject[] results = qResult.getRecords();
                for (int i = 0; i < results.length; i++) {
                    if (!fieldsName.equals("")) {
                        result.put(results[i].getId(),
                                results[i].getField(fieldsName).toString());
                    }
                }
            }
        } catch (final ConnectionException e) {
            BonitaStudioLog.error(e);
        }
        return result;
    }
}
