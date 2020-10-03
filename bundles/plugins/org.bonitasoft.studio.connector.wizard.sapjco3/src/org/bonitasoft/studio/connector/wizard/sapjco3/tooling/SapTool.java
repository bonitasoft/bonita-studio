/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.tooling;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bonitasoft.studio.connector.wizard.sapjco3.i18n.Messages;

import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRecord;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.Environment;

/**
 * @author Maxence Raoux
 */
public class SapTool {

    public static final String SINGLE = "single";
    public static final String TABLE = "table";
    public static final String STRUCTURE = "structure";
    public static final String INPUT_PREFIX = "input_";
    public static final String OUTPUT_PREFIX = "output_";
    public static final String OUTPUT_SUFFIX = "_output";

    public Boolean userWantToUseIt;
    public Boolean connectionOK;
    public String selectedFunction;
    public String connectionMessage;

    private List<SapFunction> allFunctions;
    private List<String> allGroups;
    private List<String> destinationProperties;
    private SAPMonoDestinationDataProvider destinationProvider;
    private Properties destinationData;
    private String destinationName;
    private JCoDestination destination;

    public SapTool(String destinationName) {
        allFunctions = new ArrayList<>();
        allGroups = new ArrayList<>();
        destinationData = new Properties();
        this.destinationName = destinationName;
        this.selectedFunction = "";
        connectionOK = false;
        userWantToUseIt = true;
        connectionMessage = "";
        destinationProperties = new ArrayList<>();
    }

    public void addConnectionProperties(String propertieName, String propertieValue) {
        if (propertieName != null && propertieValue != null) {
            destinationData.setProperty(propertieName, propertieValue);
        }
    }

    public void removeConnectionProperties(String propertieName) {
        if (destinationData.containsKey(propertieName)) {
            destinationData.remove(propertieName);
        }
    }

    public void cleanConnectionProperties() {
        destinationData = new Properties();
    }

    public void setDistanationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Throwable connect() {
        try {
            if (destinationProvider != null) {
                disconnect();
            }
            destinationProvider = new SAPMonoDestinationDataProvider(destinationName);
            Environment.registerDestinationDataProvider(destinationProvider);
            destinationProvider.changeProperties(destinationData);
            destination = JCoDestinationManager.getDestination(destinationProvider.getDestinationName());
            JCoContext.begin(destination);
            return null;
        } catch (RuntimeException | JCoException e) {
            return e;
        }
    }

    public void disconnect() {
        try {
            Environment.unregisterDestinationDataProvider(destinationProvider);
            JCoContext.end(destination);
            destinationProvider = null;
        } catch (JCoException e) {
            e.printStackTrace();
        }
    }

    public void tryConnect() {
        final Throwable e = connect();
        if (e != null) {
            connectionOK = false;
            connectionMessage = e.getMessage();
            disconnect();
            return;
        }
        try {
            JCoRepository repository = destination.getRepository();
            repository.getFunctionTemplate("RFC_FUNCTION_SEARCH").getFunction();
        } catch (JCoException e2) {
            connectionOK = false;
            connectionMessage = e2.getMessage();
            disconnect();
            return;
        }
        connectionOK = true;
        connectionMessage = Messages.connectionSuccess;
        disconnect();
    }

    private JCoFunction createFunction(String functionName) {
        try {
            JCoRepository repository = destination.getRepository();
            return repository.getFunctionTemplate(functionName).getFunction();
        } catch (JCoException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SapFunction getFunction(String functionName) {
        for (SapFunction function : getAllFunctions()) {
            if (function.getName().equals(functionName)) {
                return function;
            }
        }
        return null;
    }

    public List<SapFunction> getAllFunctions() {
        if (allFunctions.isEmpty()) {
            try {
                connect();
                final JCoFunction function = createFunction("RFC_FUNCTION_SEARCH");
                if (function != null) {
                    final JCoParameterList inputParameterList = function.getImportParameterList();
                    inputParameterList.setValue("FUNCNAME", "*");
                    function.execute(destination);
                    final JCoTable sapFunctionTable = function.getTableParameterList().getTable(0);
                    do {
                        allFunctions.add(new SapFunction(sapFunctionTable.getString(0), sapFunctionTable.getString(1),
                                sapFunctionTable.getString(4)));
                    } while (sapFunctionTable.nextRow());
                }
                disconnect();
            } catch (JCoException e) {
                disconnect();
                allFunctions = new ArrayList<>();
            }
        }
        return allFunctions;
    }

    public List<String> getAllGroups() {
        if (allGroups.isEmpty()) {
            for (SapFunction sapFunction : getAllFunctions()) {
                if (!allGroups.contains(sapFunction.getGroup())) {
                    allGroups.add(sapFunction.getGroup());
                }
            }
        }
        return allGroups;
    }

    public boolean isFunction(String functionName) {
        if (functionName == null || functionName.isEmpty()) {
            return false;
        }
        for (SapFunction function : getAllFunctions()) {
            if (function.getName().equals(functionName)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isGroup(String groupName) {
        if (groupName == null || groupName.isEmpty()) {
            return false;
        }
        return getAllGroups().contains(groupName);
    }

    private void createFieldList(List<SapFunctionField> l,
            JCoParameterList parameters) {
        if (parameters != null) {
            for (JCoField jCoField : parameters) {
                SapFunctionField sapFunctionField = new SapFunctionField(
                        jCoField, null);
                if (sapFunctionField.isStructure()) {
                    createFieldList(sapFunctionField.getFieldsList(), jCoField.getStructure(), sapFunctionField);
                } else if (sapFunctionField.isTable()) {

                    createFieldList(sapFunctionField.getFieldsList(), jCoField.getTable(), sapFunctionField);
                }
                l.add(sapFunctionField);
            }
        }
    }

    private void createFieldList(List<SapFunctionField> listeFields,
            JCoRecord jCoRecord, SapFunctionField parentField) {
        if (jCoRecord != null) {
            for (JCoField jCoField : jCoRecord) {
                SapFunctionField sapFunctionField = new SapFunctionField(jCoField, parentField);
                if (sapFunctionField.isStructure()) {
                    createFieldList(sapFunctionField.getFieldsList(), jCoField.getStructure(), sapFunctionField);
                } else if (sapFunctionField.isTable()) {
                    createFieldList(sapFunctionField.getFieldsList(), jCoField.getTable(), sapFunctionField);
                }
                listeFields.add(sapFunctionField);
            }
        }
    }

    private List<SapFunctionField> getAllFunctionInputsFields(
            String functionName) {
        List<SapFunctionField> l = new ArrayList<>();
        this.connect();
        JCoFunction function = createFunction(functionName);
        if (function != null) {
            createFieldList(l, function.getImportParameterList());
            createFieldList(l, function.getTableParameterList());
        }
        this.disconnect();
        return l;
    }

    private List<SapFunctionField> getAllFunctionOutputFields(
            String functionName) {
        final List<SapFunctionField> l = new ArrayList<>();
        this.connect();
        JCoFunction function = createFunction(functionName);
        if (function != null) {
            createFieldList(l, function.getExportParameterList());
            createFieldList(l, function.getTableParameterList());
        }
        this.disconnect();
        return l;
    }

    public List<SapFunctionField> getFunctionFieldsInputFiltered(
            String functionName, Boolean single, Boolean table,
            Boolean structure) {
        final List<SapFunctionField> listeField = getAllFunctionInputsFields(functionName);
        final List<SapFunctionField> listeFilteredFields = new ArrayList<>();
        for (SapFunctionField sapFunctionField : listeField) {
            if (single
                    && (!sapFunctionField.isStructure() && !sapFunctionField
                            .isTable())) {
                listeFilteredFields.add(sapFunctionField);
            }
            if (table && sapFunctionField.isTable()) {
                listeFilteredFields.add(sapFunctionField);
            }
            if (structure && sapFunctionField.isStructure()) {
                listeFilteredFields.add(sapFunctionField);
            }
        }
        return listeFilteredFields;
    }

    public List<SapFunctionField> getFunctionFieldsOutputFiltered(
            String functionName, Boolean single, Boolean table,
            Boolean structure) {
        final List<SapFunctionField> listeField = getAllFunctionOutputFields(functionName);
        final List<SapFunctionField> listeFilteredFields = new ArrayList<>();
        for (SapFunctionField sapFunctionField : listeField) {
            if (single && (!sapFunctionField.isStructure() && !sapFunctionField.isTable())) {
                listeFilteredFields.add(sapFunctionField);
            }
            if (table && sapFunctionField.isTable()) {
                listeFilteredFields.add(sapFunctionField);
            }
            if (structure && sapFunctionField.isStructure()) {
                listeFilteredFields.add(sapFunctionField);
            }
        }
        return listeFilteredFields;
    }

    private List<SapFunctionField> getAllFunctionFields(String functionName) {
        List<SapFunctionField> l = new ArrayList<>();
        this.connect();
        JCoFunction function = createFunction(functionName);
        if (function != null) {
            JCoParameterList inputImport = function.getImportParameterList();
            JCoParameterList inputTable = function.getTableParameterList();
            createFieldList(l, inputImport);
            createFieldList(l, inputTable);
        }
        this.disconnect();
        return l;
    }

    public List<SapFunctionField> getMandatoryField(String functionName) {
        return getMandatoryFieldRec(getAllFunctionFields(functionName));
    }

    private List<SapFunctionField> getMandatoryFieldRec(
            List<SapFunctionField> listeField) {
        List<SapFunctionField> mandatoryFields = new ArrayList<>();
        for (SapFunctionField sapFunctionField : listeField) {
            if (sapFunctionField.isStructure() && !sapFunctionField.isOptionnal()) {
                mandatoryFields.addAll(getMandatoryFieldRec(sapFunctionField
                        .getFieldsList()));
            } else if (!sapFunctionField.isStructure() && !sapFunctionField.isTable() && !sapFunctionField.isOptionnal()) {
                mandatoryFields.add(sapFunctionField);
            }
        }
        return mandatoryFields;
    }

    public List<String> getFonctionsNames() {
        final List<String> l = new ArrayList<>();
        List<SapFunction> fonctionList = getAllFunctions();
        for (SapFunction sapFunction : fonctionList) {
            l.add(sapFunction.getName());
        }
        return l;
    }

    public List<String> getFonctionsNamesByGroupe(String group) {
        if (group.isEmpty() || !isGroup(group)) {
            return getFonctionsNames();
        }
        final List<String> functionByGroup = new ArrayList<>();
        for (SapFunction sapFunction : allFunctions) {
            if (sapFunction.getGroup().equals(group)) {
                functionByGroup.add(sapFunction.getName());
            }
        }
        return functionByGroup;
    }

    public List<String> getConnectionPropertiesName() {
        if (!destinationProperties.isEmpty() || (!connectionOK || !userWantToUseIt)) {
            return destinationProperties;
        }
        destinationProperties = new ArrayList<>();
        destinationProperties.add(DestinationDataProvider.JCO_ALIAS_USER);
        destinationProperties.add(DestinationDataProvider.JCO_AUTH_METHOD);
        destinationProperties.add(DestinationDataProvider.JCO_AUTH_TYPE);
        destinationProperties.add(DestinationDataProvider.JCO_AUTH_TYPE_CONFIGURED_USER);
        destinationProperties.add(DestinationDataProvider.JCO_AUTH_TYPE_CURRENT_USER);
        destinationProperties.add(DestinationDataProvider.JCO_CODEPAGE);
        destinationProperties.add(DestinationDataProvider.JCO_CPIC_TRACE);
        destinationProperties.add(DestinationDataProvider.JCO_DEST);
        destinationProperties.add(DestinationDataProvider.JCO_EXPIRATION_PERIOD);
        destinationProperties.add(DestinationDataProvider.JCO_EXPIRATION_TIME);
        destinationProperties.add(DestinationDataProvider.JCO_GETSSO2);
        destinationProperties.add(DestinationDataProvider.JCO_GWHOST);
        destinationProperties.add(DestinationDataProvider.JCO_GWSERV);
        destinationProperties.add(DestinationDataProvider.JCO_LCHECK);
        destinationProperties.add(DestinationDataProvider.JCO_MAX_GET_TIME);
        destinationProperties.add(DestinationDataProvider.JCO_MSHOST);
        destinationProperties.add(DestinationDataProvider.JCO_MSSERV);
        destinationProperties.add(DestinationDataProvider.JCO_MYSAPSSO2);
        destinationProperties.add(DestinationDataProvider.JCO_PCS);
        destinationProperties.add(DestinationDataProvider.JCO_PEAK_LIMIT);
        destinationProperties.add(DestinationDataProvider.JCO_POOL_CAPACITY);
        destinationProperties.add(DestinationDataProvider.JCO_REPOSITORY_DEST);
        destinationProperties.add(DestinationDataProvider.JCO_REPOSITORY_PASSWD);
        destinationProperties.add(DestinationDataProvider.JCO_REPOSITORY_SNC);
        destinationProperties.add(DestinationDataProvider.JCO_REPOSITORY_USER);
        destinationProperties.add(DestinationDataProvider.JCO_SAPROUTER);
        destinationProperties.add(DestinationDataProvider.JCO_SNC_LIBRARY);
        destinationProperties.add(DestinationDataProvider.JCO_SNC_MODE);
        destinationProperties.add(DestinationDataProvider.JCO_SNC_MYNAME);
        destinationProperties.add(DestinationDataProvider.JCO_SNC_PARTNERNAME);
        destinationProperties.add(DestinationDataProvider.JCO_SNC_QOP);
        destinationProperties.add(DestinationDataProvider.JCO_TPHOST);
        destinationProperties.add(DestinationDataProvider.JCO_TPNAME);
        destinationProperties.add(DestinationDataProvider.JCO_TRACE);
        destinationProperties.add(DestinationDataProvider.JCO_TYPE);
        destinationProperties.add(DestinationDataProvider.JCO_USE_SAPGUI);
        destinationProperties.add(DestinationDataProvider.JCO_USER_ID);
        destinationProperties.add(DestinationDataProvider.JCO_X509CERT);
        return destinationProperties;
    }

}
