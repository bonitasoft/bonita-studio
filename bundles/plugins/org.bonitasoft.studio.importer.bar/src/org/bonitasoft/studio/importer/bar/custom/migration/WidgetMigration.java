/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bar.custom.migration;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetModifiersSwitch;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.migration.migrator.ReportCustomMigration;
import org.bonitasoft.studio.migration.utils.StringToExpressionConverter;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class WidgetMigration extends ReportCustomMigration {

    private final Map<String, Pair<String, String>> widgetActions = new HashMap<String, Pair<String, String>>();

    private final Map<String, String> widgetInputs = new HashMap<String, String>();

    private final Map<String, String> widgetDisplayLabels = new HashMap<String, String>();

    private final Map<String, String> widgetTooltips = new HashMap<String, String>();

    private final Map<String, String> displayDependentWidgetOnlyAfterFirstEventTriggeredAndConditions = new HashMap<String, String>();

    private final Map<String, String> scriptAfterEvents = new HashMap<String, String>();

    private final Map<String, String> displayAfterEventDependsOnConditionScripts = new HashMap<String, String>();

    private final Map<String, String> helpMessages = new HashMap<String, String>();

    private final Map<String, String> injectWidgetScripts = new HashMap<String, String>();

    private final Map<String, Instance> initialValueConnectors = new HashMap<String, Instance>();

    private final Map<String, Instance> afterEventConnector = new HashMap<String, Instance>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for (final Instance widget : model.getAllInstances("form.Widget")) {
            final Instance widgetContainer = widget.getContainer();
            final String uuid = widget.getUuid();
            if (uuid == null) {
                widget.setUuid(EcoreUtil.generateUUID());
            }
            if (widgetContainer != null && !widgetContainer.instanceOf("expression.Expression")) {
                final Instance action = widget.get("script");
                if (action != null) {
                    final String inputScript = action.get("inputScript");
                    final String expressionScript = action.get("exprScript");
                    final String setVarScript = action.get("setVarScript");
                    final Pair<String, String> actionScripts = new Pair<String, String>(expressionScript, setVarScript);
                    model.delete(action);
                    widgetActions.put(widget.getUuid(), actionScripts);
                    if (inputScript != null && !inputScript.trim().isEmpty()) {
                        widgetInputs.put(widget.getUuid(), inputScript);
                    } else {
                        if (widget.instanceOf("form.MultipleValuatedFormField")) {
                            final Instance data = widget.get("enum");
                            if (data != null) {
                                final List<String> literals = widget.get("literals");
                                final Instance datatype = data.get("dataType");
                                if (literals.isEmpty() && datatype != null && datatype.instanceOf("process.EnumType")) {
                                    widgetInputs.put(widget.getUuid(), "${" + generateListScript((Instance) data.get("dataType")) + "}");
                                } else if (!literals.isEmpty()) {
                                    widgetInputs.put(widget.getUuid(), "${" + generateListScript(literals) + "}");
                                }
                            }
                        }
                    }
                }
                storeDisplayLabels(widget);
                storeTooltips(widget);
                storeDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndConditions(widget);
                storeScriptAfterEvents(widget, model);
                storeDisplayAfterEventDependsOnConditionScripts(widget);
                storeHelpMessages(widget);
                storeInjectWidgetScripts(widget);
                storeInputConnectors(model, widget);
                storeAfterEventConnectors(model, widget);
            }
        }
    }

    private void storeInputConnectors(final Model model, final Instance widget) {
        final List<Instance> connectors = widget.get("inputConnectors");
        if (!connectors.isEmpty()) {
            final Instance instance = connectors.get(0);
            initialValueConnectors.put(widget.getUuid(), instance.copy());
            removeExtraConnectors(model, connectors, Messages.widgetDataInputMigrationDescription);
        }
    }

    private void removeExtraConnectors(final Model model, final List<Instance> connectors, final String reportErrorMessage) {
        if (connectors.size() > 1) {
            for (int i = 1; i < connectors.size(); i++) {
                final Instance connectorInstance = connectors.get(i);
                addReportChange((String) connectorInstance.get("name"), connectorInstance.getType().getEClass().getName(), connectorInstance.getUuid(),
                        reportErrorMessage, Messages.connectorProperty, IStatus.ERROR);
                model.delete(connectorInstance);
            }
        }
    }

    private void storeAfterEventConnectors(final Model model, final Instance widget) {
        final List<Instance> connectors = widget.get("afterEventConnectors");
        if (!connectors.isEmpty()) {
            final Instance instance = connectors.get(0);
            afterEventConnector.put(widget.getUuid(), instance.copy());
            removeExtraConnectors(model, connectors, Messages.widgetContigencyConnectorMigrationDescription);
        }
    }

    private String generateListScript(final List<String> literals) {
        final StringBuilder sb = new StringBuilder("[");
        for (final String l : literals) {
            sb.append("\"" + l + "\"");
            if (literals.indexOf(l) < literals.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private String getDefaultReturnTypeForWidget(final Instance actionExp) {
        final List<Instance> refs = actionExp.get("referencedElements");
        if (refs != null && !refs.isEmpty()) {
            final Instance widgetDependency = refs.get(0);
            if (widgetDependency.instanceOf("form.Duplicable")
                    && (Boolean) widgetDependency.get("duplicate")) {
                return List.class.getName();
            } else {
                if (widgetDependency.instanceOf("form.FileWidget")) {
                    return DocumentValue.class.getName();
                } else {
                    final EClassifier eClassifier = FormPackage.eINSTANCE.getEClassifier(widgetDependency.getEClass().getName());
                    if (eClassifier instanceof EClass) {
                        final EObject widgetInstance = FormFactory.eINSTANCE.create((EClass) eClassifier);
                        if (widgetInstance instanceof Widget) {
                            return WidgetHelper.getAssociatedReturnType((Widget) widgetInstance);
                        }
                    }
                    return String.class.getName();
                }
            }
        }
        return String.class.getName();
    }

    private void storeDisplayLabels(final Instance widget) {
        final String displayLabel = widget.get("displayLabel");
        widget.set("displayLabel", null);
        if (displayLabel != null && !displayLabel.trim().isEmpty()) {
            widgetDisplayLabels.put(widget.getUuid(), displayLabel);
        }
    }

    private void storeHelpMessages(final Instance widget) {
        final String helpMessage = widget.get("helpMessage");
        widget.set("helpMessage", null);
        if (helpMessage != null && !helpMessage.trim().isEmpty()) {
            helpMessages.put(widget.getUuid(), helpMessage);
        }
    }

    private void storeInjectWidgetScripts(final Instance widget) {
        final String injectWidgetScript = widget.get("injectWidgetScript");
        widget.set("injectWidgetScript", null);
        if (injectWidgetScript != null && !injectWidgetScript.trim().isEmpty()) {
            injectWidgetScripts.put(widget.getUuid(), injectWidgetScript);
        }
    }

    private void storeTooltips(final Instance widget) {
        final String tooltip = widget.get("tooltip");
        widget.set("tooltip", null);
        if (tooltip != null && !tooltip.trim().isEmpty()) {
            widgetTooltips.put(widget.getUuid(), tooltip);
        }
    }

    private void storeDisplayAfterEventDependsOnConditionScripts(final Instance widget) {
        final String displayAfterEventDependsOnConditionScript = widget.get("displayAfterEventDependsOnConditionScript");
        widget.set("displayAfterEventDependsOnConditionScript", null);
        if (displayAfterEventDependsOnConditionScript != null && !displayAfterEventDependsOnConditionScript.trim().isEmpty()) {
            displayAfterEventDependsOnConditionScripts.put(widget.getUuid(), displayAfterEventDependsOnConditionScript);
        }
    }

    private void storeScriptAfterEvents(final Instance widget, final Model model) {
        final Instance scriptAfterEventGroovyScript = widget.get("scriptAfterEvent");
        if (scriptAfterEventGroovyScript != null) {
            final String scriptAfterEvent = scriptAfterEventGroovyScript.get("inputScript");
            model.delete(scriptAfterEventGroovyScript);
            if (scriptAfterEvent != null && !scriptAfterEvent.trim().isEmpty()) {
                scriptAfterEvents.put(widget.getUuid(), scriptAfterEvent);
            }
        }
    }

    private void storeDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndConditions(final Instance widget) {
        final String displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition = widget
                .get("displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition");
        widget.set("displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition", null);
        if (displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition != null
                && !displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition.trim().isEmpty()) {
            displayDependentWidgetOnlyAfterFirstEventTriggeredAndConditions.put(widget.getUuid(),
                    displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition);
        }
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for (final Instance widget : model.getAllInstances("form.Widget")) {
            final Instance widgetContainer = widget.getContainer();
            if (widgetContainer != null && !widgetContainer.instanceOf("expression.Expression")) {
                setWidgetActions(widget, model);
                setReturnTypeModifier(widget);
                setInputScripts(widget, model);
                setDisplayLabels(widget, model);
                setTooltips(widget, model);
                setHelpMessages(widget, model);
                setDisplayAfterEventDependsOnConditionScripts(widget, model);
                setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndConditions(widget, model);
                setInjectWidgetScripts(widget, model);
                setScriptAfterEvents(widget, model);
            }
        }
    }

    private void setScriptAfterEvents(final Instance widget, final Model model) {
        Instance expression = null;
        if (afterEventConnector.containsKey(widget.getUuid())) {
            final Instance connector = afterEventConnector.get(widget.getUuid());
            expression = model.newInstance("expression.Expression");
            expression.set("name", connector.get("name"));
            expression.set("content", connector.get("name"));
            expression.set("type", ExpressionConstants.CONNECTOR_TYPE);
            final List<Instance> outputs = connector.get("outputs");
            if (outputs.isEmpty()) {
                connector.add("outputs", createWidgetOutput(model, connector));
            }
            expression.add("connectors", connector);
        } else if (scriptAfterEvents.containsKey(widget.getUuid())) {
            expression = getConverter(model, getScope(widget)).parse(scriptAfterEvents.get(widget.getUuid()), Object.class.getName(), false);
            String description = Messages.updateValueMigrationDescription;
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                if (widget.instanceOf("form.MultipleValuatedFormField")) {
                    expression.set("name", "updateAvailableValueScript");
                    description = Messages.updateAvailableValueMigrationDescription;
                } else {
                    expression.set("name", "updateValueScript");
                }
            }
            addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(), description, Messages.contingencyProperty,
                    StringToExpressionConverter.getStatusForExpression(expression));
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Object.class.getName(), ExpressionConstants.CONSTANT_TYPE, false);
        }
        widget.set("afterEventExpression", expression);
    }

    private void setInjectWidgetScripts(final Instance widget, final Model model) {
        Instance expression = null;
        if (injectWidgetScripts.containsKey(widget.getUuid())) {
            expression = getConverter(model, getScope(widget)).parse(injectWidgetScripts.get(widget.getUuid()), Boolean.class.getName(), true);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "insertWidgetScript");
            }
            addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(), Messages.helpMessageMigrationDescription,
                    Messages.optionsProperty, StringToExpressionConverter.getStatusForExpression(expression));
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "", "", Boolean.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        widget.set("injectWidgetScript", expression);
    }

    private void setDisplayDependentWidgetOnlyAfterFirstEventTriggeredAndConditions(final Instance widget, final Model model) {
        Instance expression = null;
        if (displayDependentWidgetOnlyAfterFirstEventTriggeredAndConditions.containsKey(widget.getUuid())) {
            expression = getConverter(model, getScope(widget)).parse(displayDependentWidgetOnlyAfterFirstEventTriggeredAndConditions.get(widget.getUuid()),
                    Boolean.class.getName(), true);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "showImmediatelyScript");
            }
            addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(), Messages.showImmediatelyConditionMigrationDescription,
                    Messages.contingencyProperty, StringToExpressionConverter.getStatusForExpression(expression));
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "true", "true", Boolean.class.getName(),
                    ExpressionConstants.CONSTANT_TYPE, true);
        }
        widget.set("displayDependentWidgetOnlyAfterFirstEventTriggeredAndCondition", expression);
    }

    private void setDisplayAfterEventDependsOnConditionScripts(final Instance widget, final Model model) {
        Instance expression = null;
        if (displayAfterEventDependsOnConditionScripts.containsKey(widget.getUuid())) {
            expression = getConverter(model, getScope(widget)).parse(displayAfterEventDependsOnConditionScripts.get(widget.getUuid()), Boolean.class.getName(),
                    true);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "showWhenContingentFieldChangedScript");
            }
            addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(),
                    Messages.displayAfterEventConditionMigrationDescription, Messages.contingencyProperty,
                    StringToExpressionConverter.getStatusForExpression(expression));
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "true", "true", Boolean.class.getName(),
                    ExpressionConstants.CONSTANT_TYPE, true);
        }
        widget.set("displayAfterEventDependsOnConditionScript", expression);
    }

    private void setHelpMessages(final Instance widget, final Model model) {
        Instance expression = null;
        if (helpMessages.containsKey(widget.getUuid())) {
            expression = getConverter(model, getScope(widget)).parse(helpMessages.get(widget.getUuid()), String.class.getName(), true);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "helpMessageScript");
            }
            addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(), Messages.helpMessageMigrationDescription,
                    Messages.userAidsProperty, StringToExpressionConverter.getStatusForExpression(expression));
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        widget.set("helpMessage", expression);
    }

    private void setTooltips(final Instance widget, final Model model) {
        Instance expression = null;
        if (widgetTooltips.containsKey(widget.getUuid())) {
            expression = getConverter(model, getScope(widget)).parse(widgetTooltips.get(widget.getUuid()), String.class.getName(), true);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "tooltipScript");
            }
            addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(), Messages.tooltipMigrationDescription,
                    Messages.userAidsProperty, StringToExpressionConverter.getStatusForExpression(expression));
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        widget.set("tooltip", expression);
    }

    private void setDisplayLabels(final Instance widget, final Model model) {
        Instance expression = null;
        if (widgetDisplayLabels.containsKey(widget.getUuid())) {
            expression = getConverter(model, getScope(widget)).parse(widgetDisplayLabels.get(widget.getUuid()), String.class.getName(), true);
            if (ExpressionConstants.SCRIPT_TYPE.equals(expression.get("type"))) {
                expression.set("name", "displayLabelScript");
            }
            addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(), Messages.displayLabelMigrationDescription,
                    Messages.generalProperty, StringToExpressionConverter.getStatusForExpression(expression));
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        }
        widget.set("displayLabel", expression);
        if (widget.instanceOf("form.FormButton")) {//New 6.0 behavior, we cannot hide FormButton label
            widget.set("showDisplayLabel", true);
        }
    }

    private void setInputScripts(final Instance widget, final Model model) {
        Instance expression = null;
        if (initialValueConnectors.containsKey(widget.getUuid())) {
            final Instance connector = initialValueConnectors.get(widget.getUuid());
            expression = model.newInstance("expression.Expression");
            expression.set("name", connector.get("name"));
            expression.set("content", connector.get("name"));
            expression.set("type", ExpressionConstants.CONNECTOR_TYPE);
            final List<Instance> outputs = connector.get("outputs");
            if (outputs.isEmpty()) {
                connector.add("outputs", createWidgetOutput(model, connector));
            }
            expression.add("connectors", connector);
        } else if (widgetInputs.containsKey(widget.getUuid())) {
            expression = getConverter(model, getScope(widget)).parse(widgetInputs.get(widget.getUuid()), String.class.getName(), false);
            final String expressionType = expression.get("type");
            if (expressionType.equals(ExpressionConstants.CONSTANT_TYPE) && widget.instanceOf("form.FileWidget")) {
                final EEnumLiteral inputType = widget.get("inputType");
                if (FileWidgetInputType.DOCUMENT.getName().equals(inputType.getName())) {
                    expression.set("type", ExpressionConstants.DOCUMENT_REF_TYPE);
                    final StringToExpressionConverter converter = getConverter(model, getScope(widget));
                    converter.resolveDocumentDependencies(expression);
                }
            }
            if (ExpressionConstants.VARIABLE_TYPE.equals(expressionType)) {
                final Instance data = ((List<Instance>) expression.get("referencedElements")).get(0);
                if (widget.instanceOf("form.MultipleValuatedFormField")) {
                    final Instance datatype = data.get("dataType");
                    if (datatype.instanceOf("process.EnumType")) {
                        expression.set("content", generateListScript(datatype));
                        expression.set("returnType", List.class.getName());
                        expression.set("name", "availableValuesScript");
                        expression.set("type", ExpressionConstants.SCRIPT_TYPE);
                        expression.set("interpreter", ExpressionConstants.GROOVY);
                        widget.set("inputExpression", expression);
                        addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(),
                                Messages.widgetDataInputMigrationDescription, Messages.dataProperty, IStatus.OK);
                        return;
                    }
                }
                expression.set("returnType", StringToExpressionConverter.getDataReturnType(data));
                final String dataName = expression.get("name");
                final Instance parentPageFlow = getParentPageFlow(widget);
                if (!isPageFlowData(dataName, parentPageFlow) && parentPageFlow.instanceOf("process.Pool")) {
                    model.delete(expression);
                    widget.set("inputExpression", StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(),
                            ExpressionConstants.CONSTANT_TYPE, false));
                    addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(),
                            Messages.widgetDataInputAtProcessLevelMigrationDescription, Messages.dataProperty, IStatus.ERROR);
                    return;
                } else {
                    addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(), Messages.widgetDataInputMigrationDescription,
                            Messages.dataProperty, IStatus.OK);
                }
            } else if (ExpressionConstants.SCRIPT_TYPE.equals(expressionType)) {
                if (widget.instanceOf("form.MultipleValuatedFormField")) {
                    expression.set("returnType", List.class.getName());
                    expression.set("name", "availableValuesScript");
                }
            }
            addReportChange((String) widget.get("name"), widget.getEClass().getName(), widget.getUuid(), Messages.widgetDataInputMigrationDescription,
                    Messages.dataProperty, StringToExpressionConverter.getStatusForExpression(expression));
        } else {
            expression = StringToExpressionConverter.createExpressionInstance(model, "", "", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, false);
        }
        widget.set("inputExpression", expression);
    }

    protected boolean isPageFlowData(final String dataName, final Instance parentPageFlow) {
        List<Instance> transientData = new ArrayList<Instance>();
        if (parentPageFlow.instanceOf("process.PageFlow")) {
            transientData = parentPageFlow.get("transientData");
        } else if (parentPageFlow.instanceOf("process.ViewPageFlow")) {
            transientData = parentPageFlow.get("viewTransientData");
        }

        for (final Instance data : transientData) {
            final String name = data.get("name");
            if (dataName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    private Instance createWidgetOutput(final Model model, final Instance connector) {
        final Instance output = model.newInstance("expression.Operation");
        final Instance expression = model.newInstance("expression.Expression");
        output.set("rightOperand", expression);
        return output;
    }

    private String generateListScript(final Instance datatype) {
        final List<String> literals = datatype.get("literals");
        final StringBuilder sb = new StringBuilder("[");
        for (final String l : literals) {
            sb.append("\"" + l + "\"");
            if (literals.indexOf(l) < literals.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private Instance getParentPageFlow(final Instance widget) {
        Instance current = widget.getContainer();
        while (current != null && !current.instanceOf("process.AbstractPageFlow")) {
            current = current.getContainer();
        }
        return current;
    }

    private void setReturnTypeModifier(final Instance widget) {
        if (!widget.instanceOf("form.TextFormField")) {
            return;
        }
        final Instance operation = widget.get("action");
        boolean added = false;
        if (operation != null) {
            final Instance leftOperand = operation.get("leftOperand");
            final Instance rightOperand = operation.get("rightOperand");
            if (leftOperand != null) {
                final List<Instance> elements = leftOperand.get("referencedElements");
                if (!elements.isEmpty()) {
                    final Instance dataInstance = elements.get(0);
                    if (dataInstance.instanceOf("process.Data")) {

                        final String returnType = StringToExpressionConverter.getDataReturnType(dataInstance);
                        if (new WidgetModifiersSwitch().doSwitch(FormFactory.eINSTANCE.createTextFormField()).contains(returnType)) {
                            widget.set("returnTypeModifier", returnType);
                            if (rightOperand != null) {
                                rightOperand.set("returnType", returnType);
                            }
                            added = true;
                            addReportChange((String) widget.get("name"), widget.getType().getEClass().getName(), widget.getUuid(),
                                    Messages.widgetModifierMigrationDescription, Messages.dataProperty, IStatus.OK);
                        }
                    }
                }
            }
        }
        if (!added) {
            widget.set("returnTypeModifier", String.class.getName());
        }
    }

    private void setWidgetActions(final Instance widget, final Model model) {
        if (widgetActions.containsKey(widget.getUuid())) {
            final Pair<String, String> actionScripts = widgetActions.get(widget.getUuid());
            final StringToExpressionConverter converter = getConverter(model, getScope(widget));
            String rightOperand = actionScripts.getFirst();
            if (isNullOrEmpty(rightOperand) && !isNullOrEmpty(actionScripts.getSecond())) {
                rightOperand = "${field_" + widget.get("name") + "}";
            }
            final Instance action = converter.parseOperation(String.class.getName(), false, rightOperand, actionScripts.getSecond());
            final Instance actionExp = action.get("rightOperand");
            if (actionExp != null) {
                if (ExpressionConstants.FORM_FIELD_TYPE.equals(actionExp.get("type"))) {
                    actionExp.set("returnType", getDefaultReturnTypeForWidget(actionExp));
                } else if (ExpressionConstants.SCRIPT_TYPE.equals(actionExp.get("type"))) {
                    final Instance leftExp = action.get("leftOperand");
                    if (leftExp != null) {
                        actionExp.set("returnType", leftExp.get("returnType"));
                    }
                }
            }
            widget.set("action", action);
            if (!widget.instanceOf("form.Info")) {
                addReportChange((String) widget.get("name"), widget.getType().getEClass().getName(), widget.getUuid(),
                        Messages.widgetActionsMigrationDescription, Messages.dataProperty,
                        StringToExpressionConverter.getStatusForExpression((Instance) action.get("rightOperand")));
            }
        }
    }
}
