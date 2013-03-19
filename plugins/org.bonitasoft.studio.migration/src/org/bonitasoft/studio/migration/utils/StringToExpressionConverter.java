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
package org.bonitasoft.studio.migration.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Model;
import org.w3c.dom.Document;

/**
 * @author Romain Bioteau
 *
 */
public class StringToExpressionConverter {

	private static final String FORM_FIELD_PREFIX = "field_";
	private static final String GROOVY_SUFFIX = "}";
	private static final String GROOVY_PREFIX = "${";

	private Model model;
	private Map<String,Instance> data = new HashMap<String, Instance>();
	private Map<String,Instance> widget = new HashMap<String, Instance>();
	private boolean useSimulationDataScope = false;
	private Instance container;

	public StringToExpressionConverter(Model model, Instance container) {
		Assert.isNotNull(model);
		this.model = model ;
		for(Instance data : model.getAllInstances("process.Data")){
			if(isInScope(container,data) && data.get("dataType") != null){
				this.data.put((String) data.get("name"),data);
			}
		}
		for(Instance widget : model.getAllInstances("form.Widget")){
			if(isInScope(container,widget)){
				this.widget.put(FORM_FIELD_PREFIX+widget.get("name"),widget);
			}
		}
	}


	public static boolean isInScope(Instance container, Instance element) {
		Instance current=element;
		while(current!= null && !container.equals(current.getContainer())){
			current = current.getContainer();
		}
		return current != null && container.equals(current.getContainer());
	}


	public Instance parseOperation(Instance groovyScriptInstance,String returnType,boolean fixedReturnType) {
		String expressionScript = groovyScriptInstance.get("exprScript");
		final String inputScript = groovyScriptInstance.get("inputScript");
		final Instance variable = groovyScriptInstance.get("setVar");
		final boolean allowHTML = groovyScriptInstance.get("allowHTMLInValues");
		final String setVarScript = groovyScriptInstance.get("setVarScript");

		if(expressionScript == null || expressionScript.trim().isEmpty()){
			if(setVarScript != null && !setVarScript.trim().isEmpty()){
				Instance widget = getParentWidget(groovyScriptInstance);
				String widgetName = setVarScript;
				if(widget != null){
					 widgetName = widget.get("name");
				}
				expressionScript = "${"+FORM_FIELD_PREFIX+widgetName+"}";
			}
		}

		Instance operation = model.newInstance("expression.Operation");
		final Instance actionExpression = parse(expressionScript, returnType, fixedReturnType);
		operation.set("rightOperand", actionExpression);
		final Instance operator = model.newInstance("expression.Operator");
		operator.set("type", ExpressionConstants.ASSIGNMENT_OPERATOR);
		operation.set("operator", operator);

		Instance leftOperand = null;
		if(setVarScript != null){
			Instance dataInstance= null;
			for(String dataName : data.keySet()){
				if(setVarScript.equals(dataName)){
					dataInstance = data.get(dataName);
				}
			}
			if(dataInstance != null){
				String dataReturnType = getDataReturnType(dataInstance);
				leftOperand = parse("${"+setVarScript+"}", dataReturnType, true);
				actionExpression.set("returnType", dataReturnType);
			}
		}
		if(leftOperand == null){
			leftOperand = createExpressionInstance(model, "", "", String.class.getName(), String.class.getName(), false);
		}
		operation.set("leftOperand", leftOperand);
		return operation;
	}

	private Instance getParentWidget(Instance groovyScriptInstance) {
		Instance current = groovyScriptInstance;
		while (current != null && !current.instanceOf("form.Widget")) {
			current = current.getContainer();
		}
		return current;
	}


	public Instance parse(String stringToParse,String returnType,boolean fixedReturnType) {
		if(returnType == null || returnType.isEmpty()){//Default return type is String
			returnType = String.class.getName();
		}
		if(stringToParse == null || stringToParse.isEmpty()){ //Returns an empty expression
			return createExpressionInstance(model,null, null, returnType, ExpressionConstants.CONSTANT_TYPE, fixedReturnType);
		}
		stringToParse = stringToParse.trim();
		final String expressionType = guessExpressionType(stringToParse);
		String content = stringToParse;
		if(isAGroovyString(content)){
			content = content.substring(2,content.length()-1);
		}
		if(ExpressionConstants.SCRIPT_TYPE.equals(expressionType)){
			final Instance expression = createExpressionInstance(model,"migratedScript", content, returnType, ExpressionConstants.SCRIPT_TYPE, fixedReturnType);
			resolveScriptDependencies(expression);
			return expression;
		}else{
			final Instance exp = createExpressionInstance(model,content, content, returnType, expressionType, fixedReturnType);
			if(ExpressionConstants.VARIABLE_TYPE.equals(expressionType) || ExpressionConstants.SIMULATION_VARIABLE_TYPE.equals(expressionType)){
				resolveDataDependencies(exp);
			}else if(ExpressionConstants.FORM_FIELD_TYPE.equals(expressionType)){
				resolveWidgetDependencies(exp);
			}
			return exp;
		}
	}

	public void resolveScriptDependencies(Instance expression) {
		resolveDataDependencies(expression);
		resolveWidgetDependencies(expression);
	}

	public void resolveWidgetDependencies(Instance expression) {
		final String content = expression.get("content");
		for(String widgetName : widget.keySet()){
			if(content.contains(widgetName)){
				int index = content.indexOf(widgetName);
				boolean validPrefix = false;
				boolean validSuffix = false;
				if(index > 0){
					String prefix = content.substring(index-1,index);
					if(!Character.isLetter(prefix.toCharArray()[0])){
						validPrefix = true;
					}
				}else if(index == 0){
					validPrefix=true;
				}
				if(index + widgetName.length() < content.length()-1){
					String suffix = content.substring(index+widgetName.length(),index+widgetName.length()+1);
					if(!Character.isLetter(suffix.toCharArray()[0])){
						validSuffix = true;
					}
				}else if(index + widgetName.length() == content.trim().length()){
					validSuffix = true;
				}
				if(validPrefix && validSuffix){
					Instance dependencyInstance = createFormFieldDependencyInstance(widget.get(widgetName));
					expression.add("referencedElements", dependencyInstance);
				}
			}
		}
	}

	public void resolveDataDependencies(Instance expression) {
		final String content = expression.get("content");
		for(String dataName : data.keySet()){
			if(content.contains(dataName)){
				int index = content.indexOf(dataName);
				boolean validPrefix = false;
				boolean validSuffix = false;
				if(index > 0){
					String prefix = content.substring(index-1,index);
					char previousChar = prefix.toCharArray()[0];
					if(!Character.isLetter(previousChar) &&  '_' != previousChar){
						validPrefix = true;
					}
				}else if(index == 0){
					validPrefix=true;
				}
				if(index + dataName.length() < content.length()-1){
					String suffix = content.substring(index+dataName.length(),index+dataName.length()+1);
					if(!Character.isLetter(suffix.toCharArray()[0])){
						validSuffix = true;
					}
				}else if(index+dataName.length() == content.trim().length()){
					validSuffix = true;
				}
				if(validPrefix && validSuffix){
					Instance dependencyInstance = createVariableDependencyInstance(data.get(dataName));
					List<Instance> instList = expression.get("referencedElements");
					if(!dependancyAlreadyExists(instList, dependencyInstance)){
						expression.add("referencedElements", dependencyInstance);
					}
				}
			}
		}
	}

	/** Check an Instance already exist in a Instance list
	 * 
	 * @param instList
	 * @param dependencyInstance
	 * @return
	 */
	private boolean dependancyAlreadyExists(List<Instance> instList, Instance dependencyInstance) {
		String dataName = dependencyInstance.get("name");
		for(Instance instance : instList){
			if(instance.get("name").equals(dataName)){
				return true;
			}
		}
		return false;
	}


	private Instance createFormFieldDependencyInstance(Instance widgetInstance) {
		return widgetInstance.copy();
	}

	private Instance createVariableDependencyInstance(Instance dataInstance) {
		return dataInstance.copy();
	}

	private String guessExpressionType(String stringToParse) {
		if(isAGroovyString(stringToParse)){
			final String groovyScript = stringToParse.substring(2,stringToParse.length()-1);
			if(data.containsKey(groovyScript) && useSimulationDataScope){
				return ExpressionConstants.SIMULATION_VARIABLE_TYPE;
			}if(data.containsKey(groovyScript) && !useSimulationDataScope){
				return ExpressionConstants.VARIABLE_TYPE;
			}else if(widget.containsKey(groovyScript)){
				return ExpressionConstants.FORM_FIELD_TYPE;
			}
			return ExpressionConstants.SCRIPT_TYPE;
		}else{
			return ExpressionConstants.CONSTANT_TYPE;
		}
	}

	private boolean isAGroovyString(String stringToParse) {
		return stringToParse.startsWith(GROOVY_PREFIX) && stringToParse.endsWith(GROOVY_SUFFIX);
	}

	public static Instance createExpressionInstance(Model model,String name, String content,String returnType,String expresisonType,boolean fixedReturnType){
		final Instance instance = model.newInstance("expression.Expression");
		instance.set("name", name);
		instance.set("content", content);
		instance.set("returnType", returnType);
		instance.set("returnTypeFixed", fixedReturnType);
		instance.set("type", expresisonType);
		if(ExpressionConstants.SCRIPT_TYPE.equals(expresisonType)){
			instance.set("interpreter", ExpressionConstants.GROOVY);
		}
		return instance;
	}

	public static String getDataReturnType(Instance data) {
		final Instance dataype = data.get("dataType");
		 if(dataype.instanceOf("process.IntegerType")){
			return Integer.class.getName();
		}else if(dataype.instanceOf("process.BooleanType")){
			return Boolean.class.getName();
		}else if(dataype.instanceOf("process.DoubleType")){
			return Double.class.getName();
		}else if(dataype.instanceOf("process.FloatType")){
			return Float.class.getName();
		}else if(dataype.instanceOf("process.EnumType")){
			return String.class.getName();
		}else if(dataype.instanceOf("process.JavaType")){
			final String returnType = data.get("className");
			if(returnType != null && !returnType.isEmpty()){
				return returnType;
			}
		}else if(dataype.instanceOf("process.LongType")){
			return Long.class.getName();
		}else if(dataype.instanceOf("process.XMLType")){
			return Document.class.getName();
		}else if(dataype.instanceOf("process.DateType")){
			return Date.class.getName();
		}
		return String.class.getName();
	}


	public static int getStatusForExpression(Instance expression) {
		return ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type")) ? IStatus.WARNING : IStatus.OK;
	}


	public void setUseSimulationDataScope(boolean useSimulationDataScope) {
		this.useSimulationDataScope  = useSimulationDataScope ;
		if(useSimulationDataScope){
			data.clear();
			for(Instance data : model.getAllInstances("simulation.SimulationData")){
				this.data.put((String) data.get("name"),data);
			}
		}else{
			for(Instance data : model.getAllInstances("process.Data")){
				this.data.put((String) data.get("name"),data);
			}
		}
	}


}
