/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.emf.tools;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * @author Romain Bioteau
 *
 */
public class ExpressionHelper {

	public static Expression createExpressionFromEnumType(EnumType type){
		final Expression generatedExp = ExpressionFactory.eINSTANCE.createExpression();
		generatedExp.setInterpreter(ExpressionConstants.GROOVY);
		generatedExp.setType(ExpressionConstants.SCRIPT_TYPE);
		generatedExp.setName(type.getName() + " values");
		final StringBuilder script = new StringBuilder("[");
		for(String lit : type.getLiterals()){
			script.append("\""+lit+"\"");
			script.append(",");
		}
		if(script.length() > 1){
			script.delete(script.length()-1, script.length());
		}
		script.append("]");
		generatedExp.setContent(script.toString());
		generatedExp.setReturnType(List.class.getName());
		return generatedExp;
	}

	public static Expression createGroovyScriptExpression(String expressionContent,String returnType) {
		final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
		exp.setName("ExpressionForEvaluation");
		exp.setInterpreter(ExpressionConstants.GROOVY);
		exp.setType(ExpressionConstants.SCRIPT_TYPE);
		exp.setContent(expressionContent);
		exp.setReturnType(returnType);
		return  exp;
	}

	public static Expression createExpressionFromDocument(Document document) {
		final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
		exp.setName(document.getName());
		exp.setType(ExpressionConstants.CONSTANT_TYPE);
		exp.setContent(document.getName());
		exp.setReturnType(String.class.getName());
		return  exp;
	}

	public static Operation createDocumentOperation(String targetDocName, Widget widget) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation() ;
		Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
		assignment.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR) ;
		action.setOperator(assignment) ;
		Expression storageExpression = ExpressionFactory.eINSTANCE.createExpression();
		storageExpression.setContent(targetDocName) ;
		storageExpression.setName(targetDocName) ;
		storageExpression.setType(ExpressionConstants.CONSTANT_TYPE) ;
		storageExpression.setReturnType(ExpressionConstants.DOCUMENT_VALUE_RETURN_TYPE) ;
		action.setLeftOperand(storageExpression) ;

		Expression actionExpression = ExpressionFactory.eINSTANCE.createExpression();
		actionExpression.setContent("field_"+widget.getName()) ;
		actionExpression.setName("field_"+widget.getName()) ;
		actionExpression.setType(ExpressionConstants.FORM_FIELD_TYPE) ;
		actionExpression.setReturnType(ExpressionConstants.DOCUMENT_VALUE_RETURN_TYPE) ;
		actionExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(widget)) ;
		action.setRightOperand(actionExpression) ;
		return action;
	}

	public static Expression createConstantExpression(String content, String returnClassName) {
		final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
		exp.setType(ExpressionConstants.CONSTANT_TYPE);
		exp.setName(content);
		exp.setContent(content);
		exp.setReturnType(returnClassName);
		return  exp;
	}

	public static Expression createConstantExpression(String name,String content, String returnClassName) {
		final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
		exp.setType(ExpressionConstants.CONSTANT_TYPE);
		exp.setName(name);
		exp.setContent(content);
		exp.setReturnType(returnClassName);
		return  exp;
	}

	public static EObject createDependencyFromEObject(EObject dependency) {
		if(dependency instanceof Widget){
			Widget widgetDependency = (Widget) FormFactory.eINSTANCE.create(dependency.eClass());
			widgetDependency.setName(((Widget) dependency).getName());
			return widgetDependency;
		}
		if(dependency instanceof Data){
			Data dataDependency = (Data) ProcessFactory.eINSTANCE.create(dependency.eClass());
			dataDependency.setName(((Data) dependency).getName());
			dataDependency.setDatasourceId(((Data) dependency).getDatasourceId());
			dataDependency.setDataType(EcoreUtil.copy(((Data) dependency).getDataType()));
			return dataDependency;
		}
		if(dependency instanceof Document){
			Document documentDependency = (Document) ProcessFactory.eINSTANCE.create(dependency.eClass());
			documentDependency.setName(((Document) dependency).getName());
			return documentDependency;
		}
		if(dependency instanceof SearchIndex){
			SearchIndex searchIndexDependency = (SearchIndex) ProcessFactory.eINSTANCE.create(dependency.eClass());
			Expression nameExpression = EcoreUtil.copy(((SearchIndex) dependency).getName());
			nameExpression.getReferencedElements().clear();
			searchIndexDependency.setName(nameExpression);
			return searchIndexDependency;
		}
		return EcoreUtil.copy(dependency);
	}
}