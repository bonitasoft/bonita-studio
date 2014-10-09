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
package org.bonitasoft.studio.common.emf.tools;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 */
public class ExpressionHelper {

    protected static final String EMPTY_LIST_NAME = Messages.emptyListExpressionName;
    protected static final String EMPTY_LIST_CONTENT = "[]";

    public static Expression createExpressionFromEnumType(final EnumType type) {
        final Expression generatedExp = ExpressionFactory.eINSTANCE.createExpression();
        generatedExp.setInterpreter(ExpressionConstants.GROOVY);
        generatedExp.setType(ExpressionConstants.SCRIPT_TYPE);
        generatedExp.setName(type.getName() + " values");
        final StringBuilder script = new StringBuilder("[");
        for (final String lit : type.getLiterals()) {
            script.append("\"" + lit + "\"");
            script.append(",");
        }
        if (script.length() > 1) {
            script.delete(script.length() - 1, script.length());
        }
        script.append("]");
        generatedExp.setContent(script.toString());
        generatedExp.setReturnType(List.class.getName());
        return generatedExp;
    }

    public static Expression createGroovyScriptExpression(final String expressionContent, final String returnType) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setName("ExpressionForEvaluation");
        exp.setInterpreter(ExpressionConstants.GROOVY);
        exp.setType(ExpressionConstants.SCRIPT_TYPE);
        exp.setContent(expressionContent);
        exp.setReturnType(returnType);
        return exp;
    }

    public static Expression createExpressionFromDocument(final Document document) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setName(document.getName());
        exp.setType(ExpressionConstants.CONSTANT_TYPE);
        exp.setContent(document.getName());
        exp.setReturnType(String.class.getName());
        return exp;
    }

    public static Operation createDocumentOperation(final String targetDocName, final Widget widget) {
        final Operation action = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
        assignment.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        action.setOperator(assignment);
        final Expression storageExpression = ExpressionFactory.eINSTANCE.createExpression();
        storageExpression.setContent(targetDocName);
        storageExpression.setName(targetDocName);
        storageExpression.setType(ExpressionConstants.CONSTANT_TYPE);
        storageExpression.setReturnType(ExpressionConstants.DOCUMENT_VALUE_RETURN_TYPE);
        action.setLeftOperand(storageExpression);

        final Expression actionExpression = ExpressionFactory.eINSTANCE.createExpression();
        actionExpression.setContent(WidgetHelper.FIELD_PREFIX + widget.getName());
        actionExpression.setName(WidgetHelper.FIELD_PREFIX + widget.getName());
        actionExpression.setType(ExpressionConstants.FORM_FIELD_TYPE);
        actionExpression.setReturnType(ExpressionConstants.DOCUMENT_VALUE_RETURN_TYPE);
        actionExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(widget));
        action.setRightOperand(actionExpression);
        return action;
    }

    public static Expression createConstantExpression(final String content, final String returnClassName) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.CONSTANT_TYPE);
        exp.setName(content);
        exp.setContent(content);
        exp.setReturnType(returnClassName);
        return exp;
    }

    public static Expression createConstantExpression(final String name, final String content, final String returnClassName) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.CONSTANT_TYPE);
        exp.setName(name);
        exp.setContent(content);
        exp.setReturnType(returnClassName);
        return exp;
    }

    public static EObject createDependencyFromEObject(final EObject dependency) {
        if (dependency instanceof Widget) {
            return createWidgetDependency((Widget) dependency);
        }
        if (dependency instanceof Data) {
            return createDataDependency(dependency);
        }
        if (dependency instanceof Document) {
            return createDocumentDependency(dependency);
        }
        if (dependency instanceof SearchIndex) {
            return createSearchIndexDependency(dependency);
        }
        return EcoreUtil.copy(dependency);
    }

    private static EObject createSearchIndexDependency(final EObject dependency) {
        final SearchIndex searchIndexDependency = (SearchIndex) ProcessFactory.eINSTANCE.create(dependency.eClass());
        final Expression name = ((SearchIndex) dependency).getName();
        if (name != null) {
            final Expression nameExpression = EcoreUtil.copy(name);
            nameExpression.getReferencedElements().clear();
            searchIndexDependency.setName(nameExpression);
        }
        return searchIndexDependency;
    }

    private static EObject createDocumentDependency(final EObject dependency) {
        final Document documentDependency = (Document) ProcessFactory.eINSTANCE.create(dependency.eClass());
        documentDependency.setName(((Document) dependency).getName());
        documentDependency.setMultiple(((Document) dependency).isMultiple());
        return documentDependency;
    }

    private static EObject createDataDependency(final EObject dependency) {
        final Data dataDependency = (Data) EcoreUtil.copy(dependency);
        dataDependency.setDefaultValue(null);
        return dataDependency;
    }

    private static EObject createWidgetDependency(final Widget dependency) {
        final Widget widgetDependency = (Widget) FormFactory.eINSTANCE.create(dependency.eClass());
        widgetDependency.setName(dependency.getName());
        widgetDependency.setReturnTypeModifier(dependency.getReturnTypeModifier());
        if (dependency instanceof Duplicable) {
            ((Duplicable) widgetDependency).setDuplicate(((Duplicable) dependency).isDuplicate());
        }
        return widgetDependency;
    }

    public static CompoundCommand clearExpression(final Expression expr, final String type, final EditingDomain editingDomain) {
        if (editingDomain != null) {
            final CompoundCommand cc = new CompoundCommand();
            cc.append(SetCommand.create(editingDomain, expr,
                    ExpressionPackage.Literals.EXPRESSION__TYPE, type));
            cc.append(SetCommand.create(editingDomain, expr,
                    ExpressionPackage.Literals.EXPRESSION__NAME, ""));
            cc.append(SetCommand.create(editingDomain, expr,
                    ExpressionPackage.Literals.EXPRESSION__CONTENT, ""));
            cc.append(SetCommand.create(editingDomain, expr,
                    ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
            cc.append(RemoveCommand.create(editingDomain, expr,
                    ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS,
                    expr.getReferencedElements()));
            cc.append(RemoveCommand.create(editingDomain, expr,
                    ExpressionPackage.Literals.EXPRESSION__CONNECTORS, expr.getConnectors()));
            return cc;
        } else {
            clearExpression(expr);
            return null;
        }
    }

    public static void clearExpression(final Expression expr) {
        Assert.isLegal(expr != null, "Expression cannot be null.");
        expr.setName("");
        expr.setContent("");
        expr.setType(ExpressionConstants.CONSTANT_TYPE);
        expr.getReferencedElements().clear();
        expr.setReturnType(String.class.getName());
    }

    public static Expression createEmptyListGroovyScriptExpression() {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setType(ExpressionConstants.SCRIPT_TYPE);
        expression.setInterpreter(ExpressionConstants.GROOVY);
        expression.setReturnType(Collection.class.getName());
        expression.setName(EMPTY_LIST_NAME);
        expression.setContent(EMPTY_LIST_CONTENT);
        return expression;
    }

    public static Expression createVariableExpression(final Data data) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setType(ExpressionConstants.VARIABLE_TYPE);
        expression.setReturnType(DataUtil.getTechnicalTypeFor(data));
        expression.setName(data.getName());
        expression.setContent(data.getName());
        expression.getReferencedElements().add(createDependencyFromEObject(data));
        return expression;
    }

    public static Expression createExpression(final String name, final String content, final String type, final String returnType, final boolean fixedReturnType) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setType(type);
        expression.setReturnType(returnType);
        expression.setReturnTypeFixed(true);
        expression.setName(name);
        expression.setContent(content);
        return expression;
    }

    public static Expression createListDocumentExpressionWithDependency(final String targetDocName) {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName(targetDocName);
        document.setMultiple(true);
        final Expression storageExpression = ExpressionFactory.eINSTANCE.createExpression();
        storageExpression.setContent(targetDocName);
        storageExpression.setName(targetDocName);
        storageExpression.setType(ExpressionConstants.DOCUMENT_LIST_TYPE);
        storageExpression.setReturnType(List.class.getName());
        storageExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        return storageExpression;
    }

    public static Expression createDocumentExpressionWithDependency(final String targetDocName) {
        final Document document = ProcessFactory.eINSTANCE.createDocument();
        document.setName(targetDocName);
        document.setMultiple(false);
        final Expression storageExpression = ExpressionFactory.eINSTANCE.createExpression();
        storageExpression.setContent(targetDocName);
        storageExpression.setName(targetDocName);
        storageExpression.setType(ExpressionConstants.DOCUMENT_TYPE);
        storageExpression.setReturnType(String.class.getName());
        storageExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        return storageExpression;
    }

}
