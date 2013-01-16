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

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.eclipse.core.runtime.Assert;
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

	public StringToExpressionConverter(Model model) {
		Assert.isNotNull(model);
		this.model = model ;
		for(Instance data : model.getAllInstances("process.Data")){
			this.data.put((String) data.get("name"),data);
		}
		for(Instance widget : model.getAllInstances("form.Widget")){
			this.widget.put(FORM_FIELD_PREFIX+widget.get("name"),widget);
		}
	}

	public Instance parse(String stringToParse,String returnType,boolean fixedReturnType) {
		if(returnType == null || returnType.isEmpty()){//Default return type is String
			returnType = String.class.getName();
		}
		if(stringToParse == null || stringToParse.isEmpty()){ //Returns an empty expression
			return createExpressionInstance(model,null, null, returnType, ExpressionConstants.CONSTANT_TYPE, fixedReturnType);
		}

		final String expressionType = guessExpressionType(stringToParse);
		String content = stringToParse;
		if(isAGroovyString(content)){
			content = content.substring(2,content.length()-1);
		}
		if(ExpressionConstants.SCRIPT_TYPE.equals(expressionType)){
			final Instance expression = createExpressionInstance(model,"migratedScript", content, returnType, ExpressionConstants.SCRIPT_TYPE, fixedReturnType);
			resolveScriptDependencies(expression,content);
			return expression;
		}else{
			final Instance exp = createExpressionInstance(model,content, content, returnType, expressionType, fixedReturnType);
			if(ExpressionConstants.VARIABLE_TYPE.equals(expressionType)){
				resolveDataDependencies(exp, content);
			}else if(ExpressionConstants.FORM_FIELD_TYPE.equals(expressionType)){
				resolveWidgetDependencies(exp, content);
			}
			return exp;
		}
	}

	private void resolveScriptDependencies(Instance expression, String groovyScript) {
		resolveDataDependencies(expression,groovyScript);
		resolveWidgetDependencies(expression,groovyScript);
	}

	private void resolveWidgetDependencies(Instance expression, String groovyScript) {
		for(String widgetName : widget.keySet()){
			if(groovyScript.contains(widgetName)){
				int index = groovyScript.indexOf(widgetName);
				boolean validPrefix = false;
				boolean validSuffix = false;
				if(index > 0){
					String prefix = groovyScript.substring(index-1,index);
					if(!Character.isLetter(prefix.toCharArray()[0])){
						validPrefix = true;
					}
				}else if(index == 0){
					validPrefix=true;
				}
				if(index < groovyScript.length()-1){
					String suffix = groovyScript.substring(index+widgetName.length(),index+widgetName.length()+1);
					if(!Character.isLetter(suffix.toCharArray()[0])){
						validSuffix = true;
					}
				}else if(index == groovyScript.length()-1){
					validSuffix = true;
				}
				if(validPrefix && validSuffix){
					Instance dependencyInstance = createFormFieldDependencyInstance(widget.get(widgetName));
					expression.add(ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS, dependencyInstance);
				}
			}
		}
	}

	private void resolveDataDependencies(Instance expression, String groovyScript) {
		for(String dataName : data.keySet()){
			if(groovyScript.contains(dataName)){
				int index = groovyScript.indexOf(dataName);
				boolean validPrefix = false;
				boolean validSuffix = false;
				if(index > 0){
					String prefix = groovyScript.substring(index-1,index);
					if(!Character.isLetter(prefix.toCharArray()[0])){
						validPrefix = true;
					}
				}else if(index == 0){
					validPrefix=true;
				}
				if(index + dataName.length() < groovyScript.length()-1){
					String suffix = groovyScript.substring(index+dataName.length(),index+dataName.length()+1);
					if(!Character.isLetter(suffix.toCharArray()[0])){
						validSuffix = true;
					}
				}else if(index+dataName.length() == groovyScript.length()){
					validSuffix = true;
				}
				if(validPrefix && validSuffix){
					Instance dependencyInstance = createVariableDependencyInstance(data.get(dataName));
					expression.add("referencedElements", dependencyInstance);
				}
			}
		}
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
			if(data.containsKey(groovyScript)){
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
		if(dataype.instanceOf("process.StringType")){
			return String.class.getName();
		}else if(dataype.instanceOf("process.IntegerType")){
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
		}
		return String.class.getName();
	}


}
