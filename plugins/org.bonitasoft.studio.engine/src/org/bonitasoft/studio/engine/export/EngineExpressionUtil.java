/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.core.operation.LeftOperand;
import org.bonitasoft.engine.core.operation.LeftOperandBuilder;
import org.bonitasoft.engine.core.operation.OperationBuilder;
import org.bonitasoft.engine.core.operation.OperatorType;
import org.bonitasoft.engine.exception.InvalidExpressionException;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.condition.conditionModel.Operation_Compare;
import org.bonitasoft.studio.condition.conditionModel.Operation_NotUnary;
import org.bonitasoft.studio.condition.conditionModel.Unary_Operation;
import org.bonitasoft.studio.condition.conditionModel.util.ConditionModelSwitch;
import org.bonitasoft.studio.condition.ui.internal.ConditionModelActivator;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.export.switcher.ExpressionConditionModelSwitch;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.resource.XtextResourceSetProvider;
import org.eclipse.xtext.util.StringInputStream;

import com.google.inject.Injector;

/**
 * @author Romain Bioteau
 */
public class EngineExpressionUtil {

	public static org.bonitasoft.engine.core.operation.Operation createOperation(final Operation operation) {
		final OperationBuilder builder = new OperationBuilder();
		builder.createNewInstance();
		builder.setType(OperatorType.valueOf(operation.getOperator().getType()));
		builder.setOperator(operation.getOperator().getExpression());
		final EList<String> operatorInputTypes = operation.getOperator().getInputTypes();
		if (!operatorInputTypes.isEmpty()) {
			builder.setOperatorInputType(operatorInputTypes.get(0));
		}
		builder.setRightOperand(createExpression(operation.getRightOperand()));
		builder.setLeftOperand(createLeftOperand(operation.getLeftOperand()));
		return builder.done();
	}

	/**
	 * Hack function because we want only constant in UI but we need a Variable type for the engine
	 * 
	 * @param operation
	 * @return
	 */
	@Deprecated
	public static org.bonitasoft.engine.core.operation.Operation createOperationForMessageContent(final Operation operation) {
		final OperationBuilder builder = new OperationBuilder();
		builder.createNewInstance();
		builder.setType(OperatorType.valueOf(operation.getOperator().getType()));
		builder.setOperator(operation.getOperator().getExpression());
		final EList<String> operatorInputTypes = operation.getOperator().getInputTypes();
		if (!operatorInputTypes.isEmpty()) {
			builder.setOperatorInputType(operatorInputTypes.get(0));
		}
		final org.bonitasoft.studio.model.expression.Expression rightOperand = EcoreUtil.copy(operation.getRightOperand());
		rightOperand.setType(ExpressionConstants.VARIABLE_TYPE);

		builder.setRightOperand(createExpression(rightOperand));
		builder.setLeftOperand(createLeftOperand(operation.getLeftOperand()));
		return builder.done();
	}

	public static List<Expression> createDependenciesList(final org.bonitasoft.studio.model.expression.Expression expression) {
		final List<Expression> result = new ArrayList<Expression>();
		if (expression.getType().equals(ExpressionConstants.SCRIPT_TYPE) || expression.getType().equals(ExpressionConstants.PATTERN_TYPE)) {
			for (final EObject element : expression.getReferencedElements()) {
				if (element instanceof Data) {
					result.add(createVariableExpression((Data) element));
				} else if (element instanceof Output) {
					result.add(createConnectorOutputExpression((Output) element));
				} else if (element instanceof Parameter) {
					result.add(createParameterExpression((Parameter) element));
				} else if (element instanceof org.bonitasoft.studio.model.expression.Expression) {
					result.add(createExpression((org.bonitasoft.studio.model.expression.Expression) element));
				} else if (element instanceof Widget) {
					result.add(createWidgetExpression((Widget) element));
				}
			}
		}

		return result;
	}

	/**
	 * @param element
	 * @return
	 * @throws InvalidExpressionException
	 */
	private static Expression createWidgetExpression(final Widget element) {
		final ExpressionBuilder exp = new ExpressionBuilder();
		exp.createNewInstance(ExporterTools.FIELD_IDENTIFIER + element.getName());
		exp.setContent(ExporterTools.FIELD_IDENTIFIER + element.getName());
		exp.setExpressionType(ExpressionConstants.FORM_FIELD_TYPE);
		if(element instanceof Duplicable && (((Duplicable)element).isDuplicate())){
			exp.setReturnType(List.class.getName());
		}else{
			if(element instanceof TextFormField && element.getReturnTypeModifier() != null){
				exp.setReturnType(element.getReturnTypeModifier());
			}else{
				exp.setReturnType(element.getAssociatedReturnType());
			}
		}

		try {
			return exp.done();
		} catch (final InvalidExpressionException e) {// TODO should throw the exception and show it in UI?
			BonitaStudioLog.error(e);
			return null;
		}
	}

	public static Expression createParameterExpression(final Parameter parameter) {
		final ExpressionBuilder exp = new ExpressionBuilder();
		exp.createNewInstance(parameter.getName());
		exp.setContent(parameter.getName());
		exp.setExpressionType(ExpressionType.TYPE_PARAMETER.toString());
		exp.setReturnType(parameter.getTypeClassname());
		try {
			return exp.done();
		} catch (final InvalidExpressionException e) {
			BonitaStudioLog.error(e);
			return null;
		}
	}

	public static LeftOperand createLeftOperand(final org.bonitasoft.studio.model.expression.Expression leftOperand) {
		final LeftOperandBuilder builder = new LeftOperandBuilder();
		builder.createNewInstance();
		builder.setName(leftOperand.getContent());
		return builder.done();
	}


	public static LeftOperand createLeftOperandIndex(final int i) {
		final LeftOperandBuilder builder = new LeftOperandBuilder();
		builder.createNewInstance();
		builder.setName(String.valueOf(i));
		return builder.done();
	}

	public static Expression createExpression(final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
		if (expression instanceof org.bonitasoft.studio.model.expression.Expression) {
			return buildSimpleEngineExpression(expression);
		} else if (expression instanceof ListExpression) {
			return buildListEngineExpression(expression);
		} else if (expression instanceof TableExpression) {
			return buildTableEngineExpression(expression);
		} else {
			return null;
		}
	}

	protected static Expression buildTableEngineExpression(final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
		final ExpressionBuilder exp = new ExpressionBuilder();
		final List<List<Expression>> expressions = new ArrayList<List<Expression>>();
		final StringBuilder expressionNames = new StringBuilder("Table of expression containing the following expressions: [");
		for (final ListExpression listExpression : ((TableExpression) expression).getExpressions()) {
			final List<Expression> engineExpressionList = new ArrayList<Expression>();
			expressionNames.append("(");
			for (final org.bonitasoft.studio.model.expression.Expression simpleExpression : listExpression.getExpressions()) {
				final Expression createExpression = createExpression(simpleExpression);
				if (createExpression!=null){
					engineExpressionList.add(createExpression);
					expressionNames.append(createExpression.getName());
					expressionNames.append(",");
				}
			}
			expressionNames.append(")");
			expressions.add(engineExpressionList);
		}
		expressionNames.append("].");
		try {
			return exp.createListOfListExpression(expressionNames.toString(), expressions);
		} catch (final InvalidExpressionException e) {
			BonitaStudioLog.error(e);
			return null;
		}
	}

	protected static Expression buildListEngineExpression(final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
		final ExpressionBuilder exp = new ExpressionBuilder();
		final List<Expression> expressions = new ArrayList<Expression>();
		final StringBuilder expressionNames = new StringBuilder("List of expression containing the following expressions: (");
		for (final org.bonitasoft.studio.model.expression.Expression simpleExpression : ((ListExpression) expression).getExpressions()) {
			final Expression createExpression = createExpression(simpleExpression);
			if (createExpression!=null){
				expressions.add(createExpression);
				expressionNames.append(createExpression.getName());
				expressionNames.append(",");
			}
		}
		expressionNames.append(").");
		try {
			return exp.createListExpression(expressionNames.toString(), expressions);
		} catch (final InvalidExpressionException e) {
			BonitaStudioLog.error(e);
			return null;
		}
	}

	protected static Expression buildSimpleEngineExpression(final org.bonitasoft.studio.model.expression.AbstractExpression expression) {
		final org.bonitasoft.studio.model.expression.Expression simpleExpression = (org.bonitasoft.studio.model.expression.Expression) expression;
		if (simpleExpression.getContent() != null && !simpleExpression.getContent().isEmpty()) {
			final ExpressionBuilder exp = new ExpressionBuilder();
			String interpreter = simpleExpression.getInterpreter();
			String name = simpleExpression.getName();
			if(name == null || name.isEmpty()){
				name = "<empty-name>";
			}
			if(ExpressionConstants.CONDITION_TYPE.equals(simpleExpression.getType())){
				return createComparisonExpression(exp, simpleExpression);
			}else if(ExpressionConstants.PATTERN_TYPE.equals(simpleExpression.getType())){
				return createPatternExpression(simpleExpression);
			}else{
				exp.createNewInstance(name);
				exp.setContent(simpleExpression.getContent());
				final String engineExpressionType = toEngineExpressionType(simpleExpression);
				exp.setExpressionType(engineExpressionType);
				if(ExpressionConstants.SCRIPT_TYPE.equals(engineExpressionType)){
					exp.setInterpreter(interpreter);
				} else {
					exp.setInterpreter("");
				}
				exp.setReturnType(simpleExpression.getReturnType());
				exp.setDependencies(createDependenciesList(simpleExpression));
				try {
					return exp.done();
				} catch (final InvalidExpressionException e) {
					BonitaStudioLog.error(e);
					return null;
				}
			}
		} else {
			return null;
		}
	}

	private static Expression createComparisonExpression(final ExpressionBuilder exp,
			org.bonitasoft.studio.model.expression.Expression simpleExpression) {
		String name = simpleExpression.getName();
		if(name == null || name.isEmpty()){
			name = "<empty-name>";
		}
		try {
			final String content = simpleExpression.getContent();
			Operation_Compare compare = parseConditionExpression(content);
			EObject op = compare.getOp();
			if(op instanceof Unary_Operation){
				org.bonitasoft.studio.condition.conditionModel.Expression conditionExp = ((Unary_Operation)op).getValue();
				final Expression expression = new ExpressionConditionModelSwitch(simpleExpression).doSwitch(conditionExp);
				if(op instanceof Operation_NotUnary){
					return exp.createLogicalComplementExpression(name, expression);
				}else{
					return expression;
				}	
			}else if(op instanceof org.bonitasoft.studio.condition.conditionModel.Operation){
				org.bonitasoft.studio.condition.conditionModel.Expression rightExp = ((org.bonitasoft.studio.condition.conditionModel.Operation)op).getRight();
				org.bonitasoft.studio.condition.conditionModel.Expression leftExp = ((org.bonitasoft.studio.condition.conditionModel.Operation)op).getLeft();
				final String operator = new ConditionModelSwitch<String>(){
					public String caseOperation_Equals(org.bonitasoft.studio.condition.conditionModel.Operation_Equals object) {return "==";};
					public String caseOperation_Greater(org.bonitasoft.studio.condition.conditionModel.Operation_Greater object) {return ">";};
					public String caseOperation_Greater_Equals(org.bonitasoft.studio.condition.conditionModel.Operation_Greater_Equals object) {return ">=";};
					public String caseOperation_Less(org.bonitasoft.studio.condition.conditionModel.Operation_Less object) {return "<";};
					public String caseOperation_Less_Equals(org.bonitasoft.studio.condition.conditionModel.Operation_Less_Equals object) {return "=<";};
					public String caseOperation_Not_Equals(org.bonitasoft.studio.condition.conditionModel.Operation_Not_Equals object) {return "!=";};
				}.doSwitch(op);

				final Expression rightExpression = new ExpressionConditionModelSwitch(simpleExpression).doSwitch(rightExp);
				final Expression leftExpression = new ExpressionConditionModelSwitch(simpleExpression).doSwitch(leftExp);
				return exp.createComparisonExpression(name, leftExpression, operator, rightExpression);
			}

		} catch (final InvalidExpressionException e) {
			BonitaStudioLog.error(e);
			return null;
		}
		return null;
	}

	public static Operation_Compare parseConditionExpression(String content) {
		final Injector injector = ConditionModelActivator.getInstance().getInjector(ConditionModelActivator.ORG_BONITASOFT_STUDIO_CONDITION_CONDITIONMODEL);
		final XtextResourceSetProvider xtextResourceSetProvider = injector.getInstance(XtextResourceSetProvider.class);
		final ResourceSet resourceSet = xtextResourceSetProvider.get(RepositoryManager.getInstance().getCurrentRepository().getProject());
		final XtextResource resource = (XtextResource) resourceSet.createResource(URI.createURI("somefile.cmodel"));
		try {
			resource.load(new StringInputStream(content, "UTF-8"), Collections.emptyMap());
		} catch (UnsupportedEncodingException e1) {
			BonitaStudioLog.error(e1, EnginePlugin.PLUGIN_ID);
		} catch (IOException e1) {
			BonitaStudioLog.error(e1, EnginePlugin.PLUGIN_ID);
		}
		return (Operation_Compare) resource.getContents().get(0);
	}

	private static String toEngineExpressionType(final org.bonitasoft.studio.model.expression.Expression expression) {
		String type = expression.getType();
		if (ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(type)) {
			return ExpressionType.TYPE_INPUT.name();
		}
		if (ExpressionConstants.VARIABLE_TYPE.equals(type) &&  !expression.getReferencedElements().isEmpty()) {
			final Data data = (Data) expression.getReferencedElements().get(0);
			final String ds = data.getDatasourceId();
			if (DatasourceConstants.PAGEFLOW_DATASOURCE.equals(ds)) {
				type = ExpressionConstants.FORM_FIELD_TYPE;
			}
		}
		return type;
	}

	public static Expression createConnectorOutputExpression(final Output element) {
		final ExpressionBuilder exp = new ExpressionBuilder();
		exp.createNewInstance(element.getName());
		exp.setContent(element.getName());
		exp.setExpressionType(ExpressionType.TYPE_INPUT.toString());
		exp.setReturnType(element.getType());
		try {
			return exp.done();
		} catch (final InvalidExpressionException e) {
			BonitaStudioLog.error(e);
			return null;
		}
	}

	public static Expression createVariableExpression(final Data element) {
		final String datasourceId = element.getDatasourceId();
		String type = ExpressionConstants.VARIABLE_TYPE;
		if (DatasourceConstants.PAGEFLOW_DATASOURCE.equals(datasourceId)) {
			type = ExpressionConstants.FORM_FIELD_TYPE;
		}
		final ExpressionBuilder exp = new ExpressionBuilder();
		exp.createNewInstance(element.getName());
		exp.setContent(element.getName());
		exp.setExpressionType(type);
		exp.setReturnType(DataUtil.getTechnicalTypeFor(element));
		try {
			return exp.done();
		} catch (final InvalidExpressionException e) {
			BonitaStudioLog.error(e);
			return null;
		}
	}

	public static Expression createEmptyListExpression() {
		final ExpressionBuilder exp = new ExpressionBuilder();
		exp.createNewInstance("<empty-name>");
		exp.setContent("[]");
		exp.setExpressionType(ExpressionConstants.SCRIPT_TYPE);
		exp.setInterpreter(ExpressionConstants.GROOVY);
		exp.setReturnType(Collection.class.getName());
		exp.setDependencies(new ArrayList<Expression>());
		try {
			return exp.done();
		} catch (final InvalidExpressionException e) {
			BonitaStudioLog.error(e);
			return null;
		}
	}

	public static Expression createConstantExpression(String name,
			String content, String returnType) {
		final ExpressionBuilder exp = new ExpressionBuilder();
		exp.createNewInstance(name);
		exp.setContent(content);
		exp.setExpressionType(ExpressionConstants.CONSTANT_TYPE);
		exp.setReturnType(returnType);
		try {
			return exp.done();
		} catch (final InvalidExpressionException e) {
			BonitaStudioLog.error(e);
			return null;
		}
	}

	public static Expression createPatternExpression(org.bonitasoft.studio.model.expression.Expression patternExpression) {
		final org.bonitasoft.studio.model.expression.Expression simpleExpression = (org.bonitasoft.studio.model.expression.Expression) patternExpression;
		String content = simpleExpression.getContent();
		if (content != null && !content.isEmpty()) {
			final ExpressionBuilder exp = new ExpressionBuilder();
			String name = simpleExpression.getName();
			if(name == null || name.isEmpty()){
				name = "<empty-name>";
			}
			exp.createNewInstance(name);
			IDocument document = new Document();
			document.set(content);
			StringBuilder patternExpressionContent = new StringBuilder();
			FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document);
			List<Expression> dependencies = createDependenciesList(simpleExpression);

			int lenght = content.length();
			int i = 0;
			for (final Expression dep : dependencies) {
				try {
					IRegion index = null;
					index = finder.find(i, dep.getName(), true, true, true, false);
					while(index != null && index.getOffset() <  lenght){
						if(isNotEscapeWord(content, index.getOffset())){
							patternExpressionContent.append(content.substring(i, index.getOffset()));
							patternExpressionContent.append("${");
							patternExpressionContent.append(dep.getName());
							patternExpressionContent.append("}");
						}else{
							patternExpressionContent.append(content.substring(i, index.getOffset()-1));
							patternExpressionContent.append(content.substring(index.getOffset(),index.getOffset()+index.getLength()));
						}
						i = index.getOffset() + index.getLength();
						if(i < lenght){
							index = finder.find(i, dep.getName(), true, true, true, false);
						}else{
							index = null;
						}
					}
				} catch (BadLocationException e) {
					// Ignore
				}
				if(i < lenght){
					patternExpressionContent.append(content.substring(i, lenght));
				}
				content = patternExpressionContent.toString();
				lenght = content.length();
				i = 0;
				patternExpressionContent = new StringBuilder();
				document.set(content);
				finder = new FindReplaceDocumentAdapter(document);
			}

			exp.setContent(content);
			final String engineExpressionType = toEngineExpressionType(simpleExpression);
			exp.setExpressionType(engineExpressionType);
			exp.setInterpreter("");

			exp.setReturnType(simpleExpression.getReturnType());
			exp.setDependencies(dependencies);
			try {
				return exp.done();
			} catch (final InvalidExpressionException e) {
				BonitaStudioLog.error(e);
				return null;
			}
		} else {
			return null;
		}
	}

	protected static boolean isNotEscapeWord(String content, int indexOf) {
		if(indexOf-1>-1){
			return content.charAt(indexOf-1) != '\\';
		}
		return true;
	}
}
