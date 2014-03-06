/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.custom.migration.connector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connectors.extension.IConnectorDefinitionMapper;
import org.bonitasoft.studio.importer.bar.BarImporterPlugin;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Model;
import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 *
 */
public class Connector5Descriptor {

	

	private static final String NAME ="name"; //String
	private static final String DOCUMENTATION ="documentation"; //String
	private static final String CONNECTOR_ID ="connectorId"; //String
	private static final String EVENT ="event"; //enum
	private static final String ERROR_HANDLE = "ignoreErrors"; //boolean
	private static final String PARAMETERS = "parameters"; //List of Parameter Intsance
	private static final String PARAMETER_KEY = "key"; //String
	private static final String PARAMETER_VALUE = "value"; //Object
	private static final String OUTPUTS ="outputs"; //List of OutputParameterMapping Instance
	private static final String OUTPUT_EXPRESSION = "valueExpression";
	private static final String OUTPUT_DATA = "targetData";


	private static final String DEFINITION_ID = "definitionId";
	private static final String DEFINITION_VERSION = "definitionVersion";
	
	//legacy connector event
	private static final String INSTANCE_ON_FINISH = "instanceOnFinish";
	private static final String AUTOMATIC_ON_EXIT = "automaticOnExit";
	private static final String TASK_ON_FINISH = "taskOnFinish";
	private static final String TASK_ON_READY = "taskOnReady";
	private static final String AUTOMATIC_ON_ENTER = "automaticOnEnter";
	private static final String TASK_ON_START = "taskOnStart";
	
	private static final String CONNECTOR_CONFIGURATION = "configuration";

	private String uuid;
	private String name;
	private String connectorId;
	private String documentation;
	private String event;
	private boolean ignoreErrors;
	private Map<String, Object> inputs = new HashMap<String,Object>();
	private Map<String, Object> outputs = new HashMap<String,Object>();
	private IConnectorDefinitionMapper definitionMapper;
	private EClass containerType;

	public Connector5Descriptor(Instance connectorInstance) {
		this.uuid = connectorInstance.getUuid();
		this.name = connectorInstance.get(NAME);
		this.connectorId = connectorInstance.get(CONNECTOR_ID);
		this.documentation = connectorInstance.get(DOCUMENTATION);
		this.event = connectorInstance.get(EVENT);
		this.ignoreErrors = connectorInstance.get(ERROR_HANDLE);
		final List<Instance> parameters =  connectorInstance.get(PARAMETERS);
		for(Instance parameter : parameters){
			String key = parameter.get(PARAMETER_KEY);
			Object value = parameter.get(PARAMETER_VALUE);
			inputs.put(key, value);
		}
		final List<Instance> outputMapping =  connectorInstance.get(OUTPUTS);
		for(Instance output : outputMapping){
			try{
				Instance data = output.get(OUTPUT_DATA);
				String value = output.get(OUTPUT_EXPRESSION);
				if(data != null){
					outputs.put((String) data.get("name"), value);
				}else{
					Instance container = connectorInstance.getContainer();
					if(container.instanceOf("form.Widget")){
						String id = WidgetHelper.FIELD_PREFIX+container.get("name");
						outputs.put(id, value);
					}
				}
			} catch(IllegalArgumentException e){
				BonitaStudioLog.warning("The connector "+ connectorId+"/"+name+" doesn't provide the expected feature for outputs.", BarImporterPlugin.PLUGIN_ID);
			}
		}
		definitionMapper = ConnectorIdToDefinitionMapping.getInstance().getDefinitionMapper(connectorId);
		
		this.containerType = connectorInstance.getContainer().getType().getEClass();
	}
	
	public String getLegacyConnectorID(){
		return connectorId;
	}

	public boolean canBeMigrated(){
		return definitionMapper != null
				|| isBonitaSetVarConnector() && isOnActivity() && onEnterStartOrFinish();
	}
	
	private final static Set<String> eventAllowedForBonitaSetVariable = new HashSet<String>(Arrays.asList(
		TASK_ON_READY,
		TASK_ON_START,
		TASK_ON_FINISH,
		AUTOMATIC_ON_EXIT,
		AUTOMATIC_ON_ENTER));
	
	private boolean onEnterStartOrFinish() {
		return eventAllowedForBonitaSetVariable.contains(event);
	}

	private boolean isOnActivity() {
		//we can't use the isSuperTypeOF directly on EClass because they belong of different model version.
		//So compare only the name
		final String activityEclassName = ProcessPackage.Literals.ACTIVITY.getName();
		if(activityEclassName.equals(containerType.getName())){
			return true;
		}
		for(EClass superType : containerType.getEAllSuperTypes()){
			if(activityEclassName.equals(superType.getName())){
				return true;
			}
		}
		return false;
	}

	public boolean appliesTo(Instance connectorInstance){
		return uuid != null && uuid.equals(connectorInstance.getUuid());
	}

	public void migrate(Model model,Instance connectorInstance,StringToExpressionConverter converter){
		Assert.isTrue(canBeMigrated());
		if(isBonitaSetVarConnector()){
			convertToAnOperation(model, connectorInstance, converter);
		} else {
			populateNewConnectorInstance(model, connectorInstance, converter);
		}		
	}

	private void populateNewConnectorInstance(Model model, Instance connectorInstance, StringToExpressionConverter converter) {
		connectorInstance.set(NAME, name);
		connectorInstance.set(DOCUMENTATION, documentation);
		connectorInstance.set(DEFINITION_ID, getDefinitionId());
		connectorInstance.set(DEFINITION_VERSION, getDefinitionVersion());
		connectorInstance.set(ERROR_HANDLE, ignoreErrors);
		connectorInstance.set(EVENT, toConnectorEvent(event));
		connectorInstance.set(CONNECTOR_CONFIGURATION,getConnectorConfiguration(model,converter));
		addOutputs(model,connectorInstance,converter);
	}

	private void convertToAnOperation(Model model, Instance connectorInstance, StringToExpressionConverter converter) {
		if("SetVariable".equals(connectorId)){
			final String variableToSet = (String)inputs.get("setVariableName");
			final String varValue = (String)inputs.get("setValue");				
			createOperation(model, connectorInstance, converter, variableToSet,	varValue);			
		} else if("SetVariables".equals(connectorId)){
			final List<List<String>> inputToParse = (List<List<String>>) inputs.get("setVariables");
			for (List<String> line : inputToParse) {
				final String variableToSet = line.get(0);
				final String varValue = line.get(1);
				createOperation(model, connectorInstance, converter, variableToSet,	varValue);
			}
		}
		
		model.delete(connectorInstance);
	}

	private void createOperation(Model model, Instance connectorInstance,
			StringToExpressionConverter converter, final String variableToSet,
			final String varValue) {
		Instance operationContainerInstance = connectorInstance.getContainer();
		Instance originalVariable = getOriginalVariable(model, variableToSet);
		
		// set variable dependency of left operand
		String returnType=Object.class.getName();
		Instance leftOperand=null;
		if(originalVariable!=null){
			returnType=StringToExpressionConverter.getDataReturnType(originalVariable);
			leftOperand = StringToExpressionConverter.createExpressionInstanceWithDependency(model, variableToSet, variableToSet, returnType, ExpressionConstants.VARIABLE_TYPE, true, originalVariable );
		}else{
			leftOperand = StringToExpressionConverter.createExpressionInstance(model, variableToSet, variableToSet, returnType, ExpressionConstants.VARIABLE_TYPE, true);
		}
		Instance rightOperand = converter.parse(varValue, returnType, false);
		Instance operatorInstance = model.newInstance("expression.Operator");
		operatorInstance.set("type", ExpressionConstants.ASSIGNMENT_OPERATOR);
		Instance operationInstance = model.newInstance("expression.Operation");
		operationInstance.set("leftOperand", leftOperand);
		operationInstance.set("rightOperand", rightOperand);
		operationInstance.set("operator", operatorInstance);
		operationContainerInstance.add("operations", operationInstance);
	}
	
	private Instance getOriginalVariable(final Model model, final String variableToSet) {
		for(Instance variable : model.getAllInstances("process.Data")){
			String varName = (String) variable.get("name");
			if(varName!=null && varName.equals(variableToSet)){
				return variable;
			}
		}
		return null;
	}

	public boolean isBonitaSetVarConnector() {
		return connectorId != null && ("SetVariable".equals(connectorId) || "SetVariables".equals(connectorId));
	}

	private void addOutputs(Model model, Instance connectorInstance, StringToExpressionConverter converter) {
		for(Entry<String, Object> output : outputs.entrySet()){
			
			final String outputValue = output.getValue().toString();
			final String outputType = isAnOutputConnector(outputValue)? ExpressionConstants.CONNECTOR_OUTPUT_TYPE : ExpressionConstants.SCRIPT_TYPE;
			final String returnType = definitionMapper.getOutputReturnType(outputValue);
			
			final Instance operation = converter.parseOperation(returnType, false, (String) output.getValue(), output.getKey(), outputType);
			connectorInstance.add(OUTPUTS, operation);
		}
	}

	private boolean isAnOutputConnector(String outputVAlueTmp){
			if(definitionMapper.getOutputReturnType(outputVAlueTmp)!=null){
				return true;
			}
		return false;
	}

	private String getDefinitionVersion() {
		return definitionMapper.getDefinitionVersion();
	}

	private String getDefinitionId() {
		return definitionMapper.getDefinitionId();
	}

	private Instance getConnectorConfiguration(Model model, StringToExpressionConverter converter) {
		final Instance configuration = model.newInstance("connectorconfiguration.ConnectorConfiguration");
		configuration.set("definitionId",getDefinitionId());
		configuration.set("version",getDefinitionVersion());
		final Map<String,Object> additionalInputs = definitionMapper.getAdditionalInputs(inputs);
		final Map<String,Object> allInput = new HashMap<String, Object>(inputs);
		allInput.putAll(additionalInputs);
		for(Entry<String, Object> input : allInput.entrySet()){
			final String parameterKeyFor = getParameterKeyFor(input.getKey());
			if(parameterKeyFor != null){
				final Instance parameter = model.newInstance("connectorconfiguration.ConnectorParameter");
				parameter.set("key", parameterKeyFor);
				parameter.set("expression", getParameterExpressionFor(model,parameterKeyFor,converter,definitionMapper.transformParameterValue(parameterKeyFor,input.getValue(),inputs),getReturnType(parameterKeyFor)));
				configuration.add("parameters", parameter);
			}else{
				if(BonitaStudioLog.isLoggable(IStatus.OK)){
					BonitaStudioLog.debug(input.getKey() +" not mapped for "+getDefinitionId(), BarImporterPlugin.PLUGIN_ID);
				}
			}
		}
		return configuration;
	}


	private String getReturnType(String inputName) {
		return definitionMapper.getInputReturnType(inputName);
	}
	

	private Instance getParameterExpressionFor(Model model,String input,StringToExpressionConverter converter, Object value, String returnType) {
		if(value instanceof String || value instanceof Boolean || value instanceof Number){
			String type = definitionMapper.getExpectedExpresstionType(input,value);
			if(type == null){
				return converter.parse(value.toString(), returnType, true);
			}else{
				return converter.parse(value.toString(), returnType, true,type);
			}
		}else if(value instanceof List){
			List<Object> listValue = (List<Object>) value;
			if(!listValue.isEmpty()){
				Instance expression = null;
				Object row = listValue.get(0);
				if(row instanceof List){
					expression = model.newInstance("expression.TableExpression");
					addRow(model, converter, expression, row);
					for(int i= 1 ; i<listValue.size();i++){
						row = listValue.get(i);
						addRow(model, converter, expression, row);
					}
				}else{
					expression = model.newInstance("expression.ListExpression");
					for(int i= 0 ; i<listValue.size();i++){
						Object v = listValue.get(i);
						expression.add("expressions", converter.parse(v.toString(), String.class.getName(), false));
					}
				}
				return expression;
			}
		}else{
			if(BonitaStudioLog.isLoggable(IStatus.OK)){
				String valueString = "null";
				if(value != null){
					valueString = value.toString();
				}
				BonitaStudioLog.debug(input +" value "+valueString+" cannot be transform to an expression", BarImporterPlugin.PLUGIN_ID);
			}
		}
		return null;
	}

	protected void addRow(Model model, StringToExpressionConverter converter,
			Instance expression, Object row) {
		Instance listExpression = model.newInstance("expression.ListExpression");
		for(Object v : (List<Object>) row){
			listExpression.add("expressions", converter.parse(v.toString(), String.class.getName(), false));
		}
		expression.add("expressions", listExpression);
	}

	private String getParameterKeyFor(String key) {
		return definitionMapper.getParameterKeyFor(key);
	}

	private String toConnectorEvent(String legacyEvent) {
		if(!INSTANCE_ON_FINISH.equals(legacyEvent) 
				&& !AUTOMATIC_ON_EXIT.equals(legacyEvent)
				&& !TASK_ON_FINISH.equals(legacyEvent)){
			return ConnectorEvent.ON_ENTER.name();
		}
		return ConnectorEvent.ON_FINISH.name();
	}
	
	public String getReportChangeMessage(){
		if(isBonitaSetVarConnector()){
				if(TASK_ON_READY.equals(event)
						|| TASK_ON_START.equals(event)
						|| AUTOMATIC_ON_ENTER.equals(event)){
					return NLS.bind(Messages.connectorMigrationSetVarConnectorOnEnterOrOnStartDescription, name);
				} else {
					return NLS.bind(Messages.connectorMigrationSetVarConnectorOnFinishDescription,name);
				}
		} else {
			return Messages.connectorMigrationDescription;
		}
	}



}
