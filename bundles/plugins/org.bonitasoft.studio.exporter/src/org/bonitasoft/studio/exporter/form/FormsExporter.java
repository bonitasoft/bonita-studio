/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bonitasoft.forms.client.model.ActionType;
import org.bonitasoft.forms.client.model.ReducedFormSubtitle.SubTitlePosition;
import org.bonitasoft.forms.client.model.ReducedFormValidator.ValidatorPosition;
import org.bonitasoft.forms.client.model.ReducedFormWidget.ItemPosition;
import org.bonitasoft.forms.client.model.ReducedFormWidget.SelectMode;
import org.bonitasoft.forms.client.model.WidgetType;
import org.bonitasoft.forms.server.accessor.DefaultValidatorsProperties;
import org.bonitasoft.forms.server.builder.IFormBuilder;
import org.bonitasoft.forms.server.builder.impl.FormBuilderImpl;
import org.bonitasoft.forms.server.exception.InvalidFormDefinitionException;
import org.bonitasoft.forms.server.provider.impl.util.FormServiceProviderUtil;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.DatasourceConstants;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.common.exporter.ExporterTools.TemplateType;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.diagram.custom.resources.ResourceTreeContentProvider;
import org.bonitasoft.studio.engine.export.EngineExpressionUtil;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.form.AbstractTable;
import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.DynamicTable;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetDownloadType;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormButton;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.IFrameWidget;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.Info;
import org.bonitasoft.studio.model.form.ItemContainer;
import org.bonitasoft.studio.model.form.LabelPosition;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.NextFormButton;
import org.bonitasoft.studio.model.form.PasswordFormField;
import org.bonitasoft.studio.model.form.PreviousFormButton;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.RichTextAreaFormField;
import org.bonitasoft.studio.model.form.SelectFormField;
import org.bonitasoft.studio.model.form.SingleValuatedFormField;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.SuggestBox;
import org.bonitasoft.studio.model.form.Table;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.TextInfo;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractPageFlow;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.ConsultationPageFlowType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.RecapFlow;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Aurelien Pupier
 */
public class FormsExporter {

    private long timestamp;

    private String procDefid;

    // dummy expression properties for empty cells in Table and EditableGrid
    // widgets
    private final String EMPTY_EXPRESSION_NAME = "empty";

    private final String EMPTY_EXPRESSION_CONTENT = "";

    private final String EMPTY_EXPRESSION_TYPE = ExpressionConstants.CONSTANT_TYPE;

    private final String EMPTY_EXPRESSION_RETURN_TYPE = String.class.getName();

    protected Set<EObject> excludedObject;

    protected final ExpressionDependencyBuilder expressionDependecyBuilder;

    public FormsExporter() {
        expressionDependecyBuilder = new ExpressionDependencyBuilder();
    }

    public File createXmlForms(final AbstractProcess studioProcess,
            final boolean isAllInBarExport, final Set<EObject> excludedObject)
            throws Exception {
        timestamp = System.currentTimeMillis();
        procDefid = studioProcess.getName() + "--" + studioProcess.getVersion();
        this.excludedObject = excludedObject;
        final IFormBuilder builder = createBuilder();
        // need to initialize
        builder.createFormDefinition();
        final String version = studioProcess.getVersion();
        if (version != null && version.length() > 0) {
            builder.addApplication(studioProcess.getName(), version);
        } else {
            builder.addApplication(studioProcess.getName(), "1.0");
        }
        builder.addPermissions(buildPermission(studioProcess));
        builder.addLabelExpression(studioProcess.getName(),
                studioProcess.getName(), ExpressionConstants.CONSTANT_TYPE,
                String.class.getName(), null);
        File tempFile;
        final AssociatedFile processTemplate = studioProcess
                .getProcessTemplate();
        if (processTemplate != null) {
            tempFile = WebTemplatesUtil.getFile(processTemplate.getPath());
            if (tempFile != null && tempFile.exists()) {
                builder.addLayout(ExporterTools.getTemplateWarPath(
                        studioProcess, TemplateType.PROCESS));
            }
        }

        final Expression mandatorySymbol = studioProcess.getMandatorySymbol();
        if (mandatorySymbol != null && mandatorySymbol.getContent() != null
                && !mandatorySymbol.getContent().isEmpty()) {
            final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                    .createExpression(mandatorySymbol);
            builder.addMandatorySymbolExpression(engineExpression.getName(),
                    engineExpression.getContent(), engineExpression
                            .getExpressionType(), engineExpression
                            .getReturnType(),
                    engineExpression.getInterpreter().isEmpty() ? null
                            : engineExpression.getInterpreter());
            expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
        }
        final Expression mandatoryLabel = studioProcess.getMandatoryLabel();
        if (mandatoryLabel != null && mandatoryLabel.getContent() != null
                && !mandatoryLabel.getContent().isEmpty()) {
            final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                    .createExpression(mandatoryLabel);
            builder.addMandatoryLabelExpression(engineExpression.getName(),
                    engineExpression.getContent(), engineExpression
                            .getExpressionType(), engineExpression
                            .getReturnType(),
                    engineExpression.getInterpreter().isEmpty() ? null
                            : engineExpression.getInterpreter());
            expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
        }
        final AssociatedFile errorTemplate = studioProcess.getErrorTemplate();
        if (errorTemplate != null) {
            tempFile = WebTemplatesUtil.getFile(errorTemplate.getPath());
            if (tempFile != null && tempFile.exists()) {
                builder.addErrorTemplate(ExporterTools.getTemplateWarPath(
                        studioProcess, TemplateType.ERROR));
            }
        }

        exportEntryPageFlow(studioProcess, builder);
        exportViewPageFlow(studioProcess, builder);
        exportRecapPageFlow(studioProcess, builder);

        addActivities(studioProcess, builder);

        return builder.done();
    }

    public Set<EObject> getExcludedObject() {
        return excludedObject;
    }

    public void setExcludedObject(final Set<EObject> excludedObject) {
        this.excludedObject = excludedObject;
    }

    /**
     * @param recapPageFlow
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void exportRecapPageFlow(final RecapFlow recapPageFlow,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (recapPageFlow.getRecapPageFlowType() == ConsultationPageFlowType.PAGEFLOW) {
            addProcessRecapPageFlow(recapPageFlow, builder);
        }
    }

    /**
     * @param studioProcess
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void exportViewPageFlow(final ViewPageFlow viewPageFlow,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        // if (viewPageFlow.getViewPageFlowType() ==
        // ConsultationPageFlowType.PAGEFLOW) {
        // if (viewPageFlow instanceof AbstractProcess) {
        // addProcessConsultationPageFlow(viewPageFlow, builder);
        // } else {
        // addConsultationPageFlow(viewPageFlow, builder);
        // }
        // }
    }

    /**
     * @param pageFlow
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void exportEntryPageFlow(final PageFlow pageFlow,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        switch (pageFlow.getEntryPageFlowType()) {
            case PAGEFLOW:
                addEntryPageFlow(pageFlow, builder);
                break;
            case SKIP:
                builder.addEntryForm(buildEntryFormID(pageFlow));
                builder.addPermissions(buildPermission(pageFlow));
                break;
            case REDIRECT:
                break;
        }
    }

    protected void addProcessRecapPageFlow(final RecapFlow recapPageFlow,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        final EList<ViewForm> recapForms = recapPageFlow.getRecapForms();
        if (!recapForms.isEmpty()) {
            builder.addViewForm(buildRecapFormID(recapPageFlow));
            builder.addPermissions(buildPermission(recapPageFlow));
            for (final Form form : recapForms) {
                addRecapPage(form, builder);
            }

            addTransientData(recapPageFlow,
                    ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA,
                    ProcessPackage.Literals.RECAP_FLOW__RECAP_FLOW_CONNECTORS,
                    builder);
            addFirstPageId(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS,
                    recapPageFlow, builder);
        }
    }

    protected void addProcessConsultationPageFlow(final ViewPageFlow viewFlow,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (viewFlow.getViewForm().size() > 0) {
            builder.addViewForm(buildViewFormID(viewFlow));
            builder.addPermissions(buildPermission(viewFlow));
            for (final Form f : viewFlow.getViewForm()) {
                addViewPage(f, builder);
            }
            addTransientData(
                    viewFlow,
                    ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA,
                    ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS,
                    builder);
            addFirstPageId(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM,
                    viewFlow, builder);
        } else {
            generatePageFlow(viewFlow, builder, true);
        }
    }

    /**
     * @param task
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addConsultationPageFlow(final ViewPageFlow task,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        // add forms
        final EList<ViewForm> viewForm = task.getViewForm();
        if (viewForm.size() > 0) {
            builder.addViewForm(buildViewFormID(task));
            builder.addPermissions(buildPermission(task));
            for (final Form f : viewForm) {
                addViewPage(f, builder);
            }
            addTransientData(
                    task,
                    ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA,
                    ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_PAGE_FLOW_CONNECTORS,
                    builder);
            addFirstPageId(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM,
                    task, builder);
        } else {
            generatePageFlow(task, builder, true);
        }
    }

    /**
     * @param pageFlow
     * @param builder
     * @param isViewPageFlow
     * @throws InvalidFormDefinitionException
     */
    protected void generatePageFlow(final AbstractPageFlow pageFlow,
            final IFormBuilder builder, final boolean isViewPageFlow)
            throws InvalidFormDefinitionException {
        final String toMatch = pageFlow.getRegExpToHideDefaultField();
        boolean noDataToHide = true;
        for (final Data data : ModelHelper.getAccessibleData(pageFlow)) {
            if (!data.isGenerated()
                    || (pageFlow.isUseRegExpToHideDefaultField()
                            && toMatch != null ? data.getName()
                            .matches(toMatch) : false)) {
                noDataToHide = false;
                break;
            }
        }
        if (!noDataToHide) {
            // no forms if there is a data that must be hidden then we
            // create a form without it
            createDefaultPageFlow(pageFlow, builder, isViewPageFlow);
        }
    }

    protected IFormBuilder createBuilder() {
        return FormBuilderImpl.getInstance();
    }

    /**
     * Add all the Task on the tag activities.
     *
     * @param studioProcess
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addActivities(final AbstractProcess studioProcess,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        final TreeIterator<EObject> eAllContents = studioProcess.eAllContents();
        while (eAllContents.hasNext()) {
            final EObject eObject = eAllContents.next();
            if (eObject instanceof Task) {
                addTask((Task) eObject, builder);
            } else if (eObject instanceof ViewPageFlow) {
                addFlowElement((ViewPageFlow) eObject, builder);
            }
        }
    }

    /**
     * @param viewPageFlow
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addFlowElement(final ViewPageFlow viewPageFlow,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        exportViewPageFlow(viewPageFlow, builder);
    }

    /**
     * This add a task in the forms.xml, the tag for it is activity...
     *
     * @param task
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addTask(final Task task, final IFormBuilder builder)
            throws InvalidFormDefinitionException {
        exportEntryPageFlow(task, builder);
        exportViewPageFlow(task, builder);
    }

    /**
     * @param f
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addViewPage(final Form f, final IFormBuilder builder)
            throws InvalidFormDefinitionException {

        builder.addPage(f.getName());
        // show display == false -> no label
        addPageLabelExpression(f, builder);
        builder.addAllowHTMLInLabelBehavior(f.isAllowHTMLInPageLabel());
        /* match with <page-template> */
        // always add a template (generated in studio if not given by the user);
        builder.addLayout(ExporterTools
                .getTemplateWarPath(f, TemplateType.PAGE));
        addWidgets(f, builder, true);
        addNextPageId(f, builder);
    }

    protected void addPageLabelExpression(final Form f,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (f.getShowPageLabel() == null || f.getShowPageLabel()) {
            // show a label
            final Expression pageLabel = f.getPageLabel();
            if (pageLabel != null && pageLabel.getContent() != null
                    && !pageLabel.getContent().isEmpty()) {
                // display label
                addLabelExpressionIfValid(builder, pageLabel);
            } else {
                builder.addLabelExpression(EcoreUtil.generateUUID(), "",
                        ExpressionConstants.CONSTANT_TYPE,
                        String.class.getName(), null);
            }
        } else {
            builder.addLabelExpression(EcoreUtil.generateUUID(), "",
                    ExpressionConstants.CONSTANT_TYPE, String.class.getName(),
                    null);
        }
    }

    protected void addLabelExpressionIfValid(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        if (expression != null && expression.getContent() != null
                && !expression.getContent().isEmpty()) {
            final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                    .createExpression(expression);
            builder.addLabelExpression(engineExpression.getName(),
                    engineExpression.getContent(), engineExpression
                            .getExpressionType(), engineExpression
                            .getReturnType(),
                    engineExpression.getInterpreter().isEmpty() ? null
                            : engineExpression.getInterpreter());
            expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
        } else {
            builder.addLabelExpression(EMPTY_EXPRESSION_NAME,
                    EMPTY_EXPRESSION_CONTENT, EMPTY_EXPRESSION_TYPE,
                    EMPTY_EXPRESSION_RETURN_TYPE, null);
        }
    }

    protected String getExpressionType(final AbstractExpression expression) {
        if (expression instanceof Expression) {
            final Expression exp = (Expression) expression;
            String type = exp.getType();
            if (ExpressionConstants.VARIABLE_TYPE.equals(type) && !exp.getReferencedElements().isEmpty()) {
                final Data data = (Data) exp.getReferencedElements().get(0);
                final String ds = data.getDatasourceId();
                if (DatasourceConstants.PAGEFLOW_DATASOURCE.equals(ds)) {
                    type = ExpressionConstants.FORM_FIELD_TYPE;
                }
            }
            return type;
        }
        return null;

    }

    /**
     * @param pageflow
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addActions(final Form f, final IFormBuilder builder)
            throws InvalidFormDefinitionException {
        /* Add actions relative to each form */
        final PageFlow pageflow = (PageFlow) f.eContainer();
        /* Add action relative to a widget */
        for (final Widget w : f.getWidgets()) {
            addWidgetAction(w, pageflow, builder);
        }
        // submit buttons action after other widgets
        final TreeIterator<EObject> eAllContents = f.eAllContents();
        while (eAllContents.hasNext()) {
            final EObject w = eAllContents.next();
            if (w instanceof SubmitFormButton) {
                addSubmitButtonActions(builder, (SubmitFormButton) w);
            }
        }

        // Always add Form action after Submit Button
        addFormGlobalActions(builder, f);
    }

    protected void addFormGlobalActions(final IFormBuilder builder, final Form f)
            throws InvalidFormDefinitionException {
        // add forms global actions last
        for (final Operation action : f.getActions()) {
            if (action.getRightOperand() != null
                    && action.getRightOperand().getContent() != null
                    && !action.getRightOperand().getContent().isEmpty()
                    && action.getLeftOperand() != null
                    && action.getLeftOperand().getContent() != null
                    && !action.getLeftOperand().getContent().isEmpty()) {
                addAction(builder, action);
            }
        }
    }

    protected void addSubmitButtonActions(final IFormBuilder builder,
            final SubmitFormButton w) throws InvalidFormDefinitionException {
        for (final Operation action : w.getActions()) {
            if (action.getOperator() != null && action.getOperator().getType().equals(ExpressionConstants.DELETION_OPERATOR)) {
                if (action.getLeftOperand() != null
                        && action.getLeftOperand().getContent() != null
                        && !action.getLeftOperand().getContent().isEmpty()) {
                    addAction(builder, action);
                }
            } else {
                if (action.getRightOperand() != null
                        && action.getRightOperand().getContent() != null
                        && !action.getRightOperand().getContent().isEmpty()
                        && action.getLeftOperand() != null
                        && action.getLeftOperand().getContent() != null
                        && !action.getLeftOperand().getContent().isEmpty()) {
                    addAction(builder, action);
                }
            }

        }
    }

    protected void addWidgetAction(final Widget w, final PageFlow pageflow,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (w instanceof FormField) {
            if (w instanceof FileWidget && ((FileWidget) w).isDownloadOnly()) {
                return;
            }

            final Operation action = ((FormField) w).getAction();
            if (action != null) {
                if (action.getRightOperand() != null
                        && action.getRightOperand().getContent() != null
                        && !action.getRightOperand().getContent().isEmpty()
                        && action.getLeftOperand() != null
                        && action.getLeftOperand().getContent() != null
                        && !action.getLeftOperand().getContent().isEmpty()
                        && !w.isReadOnly()) {
                    addAction(builder, action);
                }
            }
        }
    }

    /**
     * @param pageflow
     * @param builder
     * @param expression
     * @param setVar
     * @throws InvalidFormDefinitionException
     */
    protected void addAction(final IFormBuilder builder, final Operation action)
            throws InvalidFormDefinitionException {
        String widgetName = null;
        if (action.eContainer() instanceof SubmitFormButton) {
            widgetName = ((SubmitFormButton) action.eContainer()).getName();
        }
        addAction(builder, action, widgetName);

    }

    protected ActionType getActionType(final Operation operation) {
        return ActionTypeResolver.toActionType(EngineExpressionUtil
                .getOperatorType(operation));
    }

    protected void addAction(final IFormBuilder builder,
            final Operation action, final String submitButtonIdName)
            throws InvalidFormDefinitionException {
        final ActionType actionType = ActionTypeResolver.toActionType(action
                .getOperator().getType());
        final String variableName = action.getLeftOperand().getContent();
        final String variableType = EngineExpressionUtil.getLeftOperandType(
                action.getLeftOperand(), false);
        final String operator = action.getOperator().getExpression();
        final EList<String> inputTypes = action.getOperator().getInputTypes();
        final String operatorInputType = inputTypes != null
                && !inputTypes.isEmpty() ? inputTypes.get(0) : null;
        final Expression expression = action.getRightOperand();

        builder.addAction(actionType, variableName, variableType, operator,
                operatorInputType, submitButtonIdName);
        if (expression != null) {
            addActionExpression(builder, expression);
        }
        if (action.eContainer() instanceof Widget) {
            final Widget widget = (Widget) action.eContainer();
            addConditionExpression(builder, widget);
        }
    }

    /**
     * @param builder
     * @param widget
     * @throws InvalidFormDefinitionException
     */
    protected void addConditionExpression(final IFormBuilder builder, final Widget widget) throws InvalidFormDefinitionException {
        if (widget.isInjectWidgetCondition()) {
            final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                    .createExpression(widget.getInjectWidgetScript());
            if (engineExpression != null) {
                builder.addConditionExpression(engineExpression.getName(), engineExpression.getContent(), engineExpression.getExpressionType(),
                        engineExpression.getReturnType(), engineExpression.getInterpreter() == null
                                || engineExpression.getInterpreter().isEmpty() ? null
                                : engineExpression.getInterpreter());
                expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
            }
        }
    }

    protected void addActionExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        if (engineExpression != null) {
            builder.addActionExpression(engineExpression.getName(),
                    engineExpression.getContent(), engineExpression
                            .getExpressionType(), engineExpression.getReturnType(),
                    engineExpression.getInterpreter() == null
                            || engineExpression.getInterpreter().isEmpty() ? null
                            : engineExpression.getInterpreter());
            expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
        }
    }

    /**
     * @param task
     * @param pageFlowTransientData
     * @param connectors
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addTransientData(final Element element,
            final EReference pageFlowTransientData,
            final EReference connectors, final IFormBuilder builder)
            throws InvalidFormDefinitionException {
        final List<?> list = (List<?>) element.eGet(pageFlowTransientData);
        for (final Object child : list) {
            if (child instanceof Data) {
                final Data data = (Data) child;
                Expression defaultValue = null;
                if (data.getDefaultValue() != null
                        && data.getDefaultValue().getName() != null
                        && !data.getDefaultValue().getName().isEmpty()) {
                    defaultValue = data.getDefaultValue();
                } else if (data.isMultiple()) {
                    defaultValue = null;// "${[]}";
                }
                builder.addTransientData(data.getName(),
                        DataUtil.getTechnicalTypeFor(data));
                if (defaultValue != null) {
                    addTransientDataExpression(builder, defaultValue);
                }
            }
        }
    }

    protected void addTransientDataExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addTransientDataExpression(engineExpression.getName(),
                engineExpression.getContent(), engineExpression
                        .getExpressionType(), engineExpression.getReturnType(),
                engineExpression.getInterpreter() == null
                        || engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());
        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
    }

    //
    // /**
    // * @param theData
    // * @return the class of the data as string
    // */
    // public String getReturnTypeClassFor(final Data data) {
    // final DataType dataType = data.getDataType();
    // if (data.isMultiple()) {
    // return List.class.getName();
    // } else if (data.getDataType() instanceof EnumType) {
    // // TODO no enum
    // } else if (dataType instanceof DateType) { // To be tested before
    // // StringType
    // return Date.class.getName();
    // } else if (dataType instanceof StringType) {
    // return String.class.getName();
    // } else if (dataType instanceof BooleanType) {
    // return Boolean.class.getName();
    // } else if (dataType instanceof IntegerType) {
    // return Long.class.getName();
    // } else if (dataType instanceof FloatType) {
    // return Double.class.getName();
    // } else if (dataType instanceof JavaType) {
    // final JavaObjectData javaObjectData = (JavaObjectData) data;
    // if (javaObjectData.getClassName() != null) {
    // return javaObjectData.getClassName();
    // } else {
    // return Object.class.getName();
    // }
    // } else if (dataType instanceof XMLType) {
    // // TODO no xml
    // }
    //
    // return String.class.getName();
    // }

    /**
     * @param task
     * @param builder
     * @param isViewPage
     * @throws InvalidFormDefinitionException
     */
    protected void createDefaultPageFlow(final AbstractPageFlow pageFlow,
            final IFormBuilder builder, final boolean isViewPageFlow)
            throws InvalidFormDefinitionException {
        // nothing to do

    }

    /**
     * @param f
     * @param index
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addEntryPage(final Form f, final IFormBuilder builder)
            throws InvalidFormDefinitionException {
        builder.addPage(f.getName());
        addPageLabelExpression(f, builder);
        // should be added before any widgets
        addPageValidators(f, builder);

        builder.addAllowHTMLInLabelBehavior(f.isAllowHTMLInPageLabel());
        /* match with <page-template> */
        // always add a template (generated in studio if not given by the user)
        builder.addLayout(ExporterTools
                .getTemplateWarPath(f, TemplateType.PAGE));
        addWidgets(f, builder, false);

        addActions(f, builder);
        addNextPageId(f, builder);
    }

    /**
     * @param f
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    public void addPageValidators(final Form f, final IFormBuilder builder)
            throws InvalidFormDefinitionException {
        final ValidatorDescriptorRepositoryStore store = RepositoryManager
                .getInstance().getRepositoryStore(
                        ValidatorDescriptorRepositoryStore.class);
        for (final Validator validator : f.getValidators()) {
            if (validator.getValidatorClass() != null) {
                builder.addValidator(validator.getName(), validator
                        .getValidatorClass(), validator.getHtmlClass(),
                        validator.isBelowField() ? ValidatorPosition.BOTTOM
                                : ValidatorPosition.TOP);
                if (validator.getDisplayName() != null
                        && validator.getDisplayName().getContent() != null) {
                    addLabelExpressionIfValid(builder,
                            validator.getDisplayName());
                }
                final ValidatorDescriptor descriptor = store
                        .getValidatorDescriptor(validator.getValidatorClass());
                if (descriptor.getHasParameter()
                        && validator.getParameter() != null
                        && validator.getParameter().getContent() != null) {
                    addParameterExpression(builder, validator.getParameter());
                }
            }
        }
    }

    protected void addParameterExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        if (engineExpression != null && engineExpression.getContent() != null
                && !engineExpression.getContent().isEmpty()) {
            builder.addParameterExpression(engineExpression.getName(),
                    engineExpression.getContent(), engineExpression
                            .getExpressionType(), engineExpression
                            .getReturnType(),
                    engineExpression.getInterpreter().isEmpty() ? null
                            : engineExpression.getInterpreter());
            expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
        }
    }

    /**
     * Add each widget
     *
     * @param f
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addWidgets(final Form f, final IFormBuilder builder,
            final boolean isViewForm) throws InvalidFormDefinitionException {
        /*
         * if parameter are set to 0 or are null, don't call the add on the
         * builder
         */

        final List<Widget> widgets = new ArrayList<Widget>();

        for (int i = 0; i < f.getNLine(); i++) {
            for (int j = 0; j < f.getNColumn(); j++) {
                for (final Widget w : f.getWidgets()) {
                    if (w.getWidgetLayoutInfo() != null) {
                        if (w.getWidgetLayoutInfo().getLine() == i
                                && w.getWidgetLayoutInfo().getColumn() == j) {
                            widgets.add(w);
                            break;
                        }
                    }
                }
            }
        }

        for (final Widget widget : widgets) {
            performOnWidget(builder, widget, false, isViewForm);
        }

    }

    protected void performOnWidget(final IFormBuilder builder,
            final Widget widget, final boolean haveParent,
            final boolean isViewForm) throws InvalidFormDefinitionException {
        if (widget != null) {
            if (widget instanceof FormField) {
                final FormField formField = (FormField) widget;
                addSpecificFormField(formField, builder, isViewForm);
                if (!(formField instanceof HiddenWidget)) {
                    /* common to all FormField */
                    addMandatoryBehavior(builder, widget);
                    // do not add validators if the table do not allow selection
                    if (!(widget instanceof Table && !((Table) widget)
                            .isAllowSelection())) {
                        addFormFieldValidators((FormField) widget, builder);
                    }
                }
                final Expression exampleMessage = formField.getExampleMessage();
                if (exampleMessage != null
                        && exampleMessage.getContent() != null
                        && !exampleMessage.getContent().isEmpty()) {
                    builder.addSubTitle(getSubtitlePosition(toItemPosition(formField
                            .getExampleMessagePosition())));
                    addLabelExpressionIfValid(builder, exampleMessage);
                }
                /* Read only behavior for formfield */
                addReadOnlyBehavior(builder, widget, isViewForm);
            } else if (widget instanceof Info) {
                if (widget instanceof TextInfo) {
                    builder.addWidget(widget.getName(), WidgetType.TEXT);
                    addInitialValue(widget, builder);
                } else if (widget instanceof MessageInfo
                        || widget instanceof HtmlWidget) {
                    builder.addWidget(widget.getName(), WidgetType.MESSAGE);
                    addInitialValue(widget, builder);
                } else if (widget instanceof IFrameWidget) {
                    builder.addWidget(widget.getName(), WidgetType.IFRAME);
                    addInitialValue(widget, builder);
                }

            } else if (widget instanceof FormButton) {
                if (widget instanceof SubmitFormButton) {
                    builder.addWidget(widget.getName(),
                            WidgetType.BUTTON_SUBMIT);
                } else if (widget instanceof NextFormButton) {
                    builder.addWidget(widget.getName(), WidgetType.BUTTON_NEXT);
                } else if (widget instanceof PreviousFormButton) {
                    builder.addWidget(widget.getName(),
                            WidgetType.BUTTON_PREVIOUS);
                } else { // Simple button
                    builder.addWidget(widget.getName(), WidgetType.BUTTON);
                }
                builder.addLabelButtonBehavior(((FormButton) widget)
                        .getLabelBehavior() != null
                        && ((FormButton) widget).getLabelBehavior());

            } else if (widget instanceof ImageWidget) {
                performOnImageWidget(builder, widget);
            }
            if (haveParent) {
                addParentProperties(builder, widget);
            }
            // add label and label position
            addLabel(builder, widget);
            final Expression helpMessage = widget.getHelpMessage();
            if (helpMessage != null && helpMessage.getName() != null
                    && !helpMessage.getName().isEmpty()) {
                addTooltipExpression(builder, helpMessage);
            }

            addStyle(builder, widget, widget instanceof FormField
                    || widget instanceof FormButton
                    || widget instanceof TextInfo
                    || widget instanceof ImageWidget || widget instanceof Table);

            addHtmlAttributes(builder, widget);
            addDisplayCondition(builder, widget);
            addModifierReturnType(builder, widget);
        }
    }

    protected void addModifierReturnType(final IFormBuilder builder,
            final Widget widget) throws InvalidFormDefinitionException {
        if (widget instanceof TextFormField
                && widget.getReturnTypeModifier() != null) {
            builder.addFieldOutputType(widget.getReturnTypeModifier());
        }
    }

    protected void addDisplayCondition(final IFormBuilder builder,
            final Widget widget) throws InvalidFormDefinitionException {
        final Expression injectWidgetScript = widget.getInjectWidgetScript();
        if (widget.isInjectWidgetCondition() && injectWidgetScript != null
                && !injectWidgetScript.getName().isEmpty()) {
            addDisplayConditionExpression(builder, injectWidgetScript);
        }
    }

    protected void addHtmlAttributes(final IFormBuilder builder,
            final Widget widget) throws InvalidFormDefinitionException {
        final String s = widget.getRealHtmlAttributes();
        if (widget instanceof Duplicable) {
            if (((Duplicable) widget).isDuplicate()) {
                return;
            }
        }
        if (widget instanceof Group) {
            return;
        }
        if (s != null && !s.isEmpty()) {
            final Map<String, String> map = parseToExtractHtmlAttributes(s);
            final Set<Entry<String, String>> entries = map.entrySet();
            for (final Entry<String, String> entry : entries) {
                builder.addHTMLAttribute(entry.getKey(), entry.getValue());
            }
        }
    }

    protected void addTooltipExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addPopupToolTipExpression(engineExpression.getName(),
                engineExpression.getContent(), engineExpression
                        .getExpressionType(), engineExpression.getReturnType(),
                engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());
        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);

    }

    protected void addDisplayConditionExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addDisplayConditionExpression(engineExpression.getName(),
                engineExpression.getContent(), engineExpression
                        .getExpressionType(), engineExpression.getReturnType(),
                engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());

        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);

    }

    private void performOnImageWidget(final IFormBuilder builder,
            final Widget widget) throws InvalidFormDefinitionException {
        builder.addWidget(widget.getName(), WidgetType.IMAGE);
        addImageWidgetInitialValue(builder, widget);
        builder.addImageStyle(getCSSClasses(widget, ExporterTools.PREFIX_IMAGE));
        builder.addAttachmentImageBehavior(((ImageWidget) widget)
                .isIsADocument());
    }

    protected void addImageWidgetInitialValue(final IFormBuilder builder,
            final Widget widget) throws InvalidFormDefinitionException {
        if (((ImageWidget) widget).getImgPath() != null) {
            if (((ImageWidget) widget).isIsADocument()) {
                final Expression documentExpression = ((ImageWidget) widget)
                        .getImgPath();
                addInitialValueExpression(builder, documentExpression);
            } else {
                final Expression path = ((ImageWidget) widget).getImgPath();
                final String type = path.getType();
                if (path.getContent() != null && !path.getContent().isEmpty()) {
                    if (ExpressionConstants.CONSTANT_TYPE.equals(type)) {
                        final AbstractProcess process = ModelHelper
                                .getParentProcess(widget);
                        if (isAnApplicationResource(process, path)) {
                            builder.addInitialValueExpression(path.getName(),
                                    ExporterTools.toApplicationResourceURL(
                                            "application/"
                                                    + path.getContent().trim(),
                                            process.getName(),
                                            process.getVersion(), timestamp),
                                    ExpressionConstants.CONSTANT_TYPE,
                                    String.class.getName(), null);
                        } else if (isARootResource(process, path)) {
                            builder.addInitialValueExpression(path.getName(),
                                    ExporterTools.toApplicationResourceURL(path
                                            .getContent().trim(), process
                                            .getName(), process.getVersion(),
                                            timestamp),
                                    ExpressionConstants.CONSTANT_TYPE,
                                    String.class.getName(), null);
                        } else {
                            addInitialValueExpression(builder, path);
                        }
                    } else {
                        addInitialValueExpression(builder, path);
                    }
                }
            }
        }
    }

    private boolean isARootResource(final AbstractProcess process, final Expression path) {
        final ResourceTreeContentProvider provider = new ResourceTreeContentProvider();
        provider.inputChanged(null, null, process);
        final Object[] initial = provider.getElements(process);
        final Object[] currentChildren = provider.getChildren(initial[0]);
        final StringTokenizer tokenizer = new StringTokenizer(path.getContent(), "/");
        Object temp = null;
        while (tokenizer.hasMoreElements()) {
            final String item = tokenizer.nextToken();
            for (int i = 0; i < currentChildren.length; i++) {
                temp = currentChildren[i];
                if (temp instanceof File) {
                    if (((File) temp).getName().equals(item)) {
                        if (!((File) temp).isDirectory()) {
                            if (!tokenizer.hasMoreElements()) {
                                return true;
                            }
                        }
                    }
                } else if (temp instanceof ResourceFile) {
                    if (((ResourceFile) temp).getPath().endsWith("/" + item)) {
                        if (!tokenizer.hasMoreElements()) {
                            return true;
                        }
                    }
                } else if (temp instanceof ResourceFolder) {
                    if (((ResourceFolder) temp).getPath().endsWith("/" + item)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isAnApplicationResource(final AbstractProcess process,
            final Expression path) {
        final ResourceTreeContentProvider provider = new ResourceTreeContentProvider();
        provider.inputChanged(null, null, process);
        final Object[] initial = provider.getElements(process);
        Object[] currentChildren = provider.getChildren(initial[0]);
        final StringTokenizer tokenizer = new StringTokenizer(path.getContent(), "/");
        Object temp = null;
        while (tokenizer.hasMoreElements()) {
            final String item = tokenizer.nextToken();
            for (int i = 0; i < currentChildren.length; i++) {
                temp = currentChildren[i];
                if (temp instanceof File) {
                    if (((File) temp).getName().equals(item)) {
                        if (((File) temp).isDirectory()) {
                            currentChildren = provider.getChildren(temp);
                            break;
                        } else {
                            return !tokenizer.hasMoreElements();
                        }
                    }
                } else if (temp instanceof ResourceFile) {
                    if (((ResourceFile) temp).getPath().endsWith("/" + item)) {
                        return !tokenizer.hasMoreElements();
                    }
                } else if (temp instanceof ResourceFolder) {
                    if (((ResourceFolder) temp).getPath().endsWith("/" + item)) {
                        currentChildren = provider.getChildren(temp);
                        break;
                    } else {
                        currentChildren = provider.getChildren(temp);
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private void performOnAbstractTable(final IFormBuilder builder,
            final Widget widget, final boolean isViewForm)
            throws InvalidFormDefinitionException {
        final AbstractTable abstractTable = (AbstractTable) widget;
        if (widget instanceof Table) {
            final Table table = (Table) widget;
            builder.addWidget(widget.getName(), WidgetType.TABLE);
            if (table.isAllowSelection()) {
                final Expression columnIndex = table
                        .getColumnForInitialSelectionIndex();
                if (columnIndex != null && columnIndex.getContent() != null
                        && !columnIndex.getContent().isEmpty()) {
                    addValueColumnIndexExpression(builder, columnIndex);
                }
                if (table.isSelectionModeIsMultiple()) {
                    builder.addSelectMode(SelectMode.MULTIPLE);
                } else {
                    builder.addSelectMode(SelectMode.SINGLE);
                }
            } else {
                builder.addSelectMode(SelectMode.NONE);
                final Expression maxRowForPagination = table
                        .getMaxRowForPagination();
                if (maxRowForPagination != null
                        && maxRowForPagination.getContent() != null
                        && !maxRowForPagination.getContent().isEmpty()) {
                    addMaxRowsExpression(builder, maxRowForPagination);
                }
            }
            addTableAvailableValue((Table) abstractTable, builder);
        } else {
            final DynamicTable dynamicTable = (DynamicTable) widget;
            builder.addWidget(widget.getName(), WidgetType.EDITABLE_GRID);
            if (!isViewForm && !widget.isReadOnly()) {
                builder.addVariableRowsNumber(dynamicTable
                        .isAllowAddRemoveRow());
                builder.addVariableColumnsNumber(dynamicTable
                        .isAllowAddRemoveColumn());

                if (dynamicTable.isAllowAddRemoveColumn()) {
                    final Expression maxNumberOfColumn = dynamicTable
                            .getMaxNumberOfColumn();
                    if (dynamicTable.isLimitMaxNumberOfColumn()
                            && maxNumberOfColumn != null
                            && !maxNumberOfColumn.getName().isEmpty()) {
                        addMaxColumnsExpression(builder, maxNumberOfColumn);
                    }
                    final Expression minNumberOfColumn = dynamicTable
                            .getMinNumberOfColumn();
                    if (dynamicTable.isLimitMinNumberOfColumn()
                            && minNumberOfColumn != null
                            && !minNumberOfColumn.getName().isEmpty()) {
                        addMinColumnsExpression(builder, minNumberOfColumn);
                    }
                }
                if (dynamicTable.isAllowAddRemoveRow()) {
                    final Expression maxNumberOfRow = dynamicTable
                            .getMaxNumberOfRow();
                    if (dynamicTable.isLimitMaxNumberOfRow()
                            && maxNumberOfRow != null
                            && !maxNumberOfRow.getName().isEmpty()) {
                        addMaxRowsExpression(builder, maxNumberOfRow);
                    }
                    final Expression minNumberOfRow = dynamicTable
                            .getMinNumberOfRow();
                    if (dynamicTable.isLimitMinNumberOfRow()
                            && minNumberOfRow != null
                            && !minNumberOfRow.getName().isEmpty()) {
                        addMinRowsExpression(builder, minNumberOfRow);
                    }
                }
            }
        }

        addReadOnlyBehavior(builder, widget, isViewForm);
        addTableInitialValue(abstractTable, builder);
        addTableVerticalHeader(abstractTable, builder);
        addTableHorizontalHeader(abstractTable, builder);
        if (abstractTable.getInputExpression() != null) {
            builder.addAllowHTMLInFieldBehavior(abstractTable
                    .getInputExpression().isHtmlAllowed());
        }
        // styling
        String classes = getCSSClasses(widget, ExporterTools.PREFIX_TABLE_CELLS);
        if (classes != null && classes.length() > 0) {
            builder.addCellsStyle(classes);
        }
        classes = getCSSClasses(widget, ExporterTools.PREFIX_TABLE_HEADERS);
        // don't check classe to add the header position event if there
        // is no style on it
        builder.addHeadingsStyle(classes, abstractTable.isLeftColumnIsHeader(),
                abstractTable.isFirstRowIsHeader(),
                abstractTable.isRightColumnIsHeader(),
                abstractTable.isLastRowIsHeader());
        classes = getCSSClasses(widget, ExporterTools.PREFIX_TABLE);
        builder.addTableStyle(classes);
    }

    protected void addValueColumnIndexExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addValueColumnIndexExpression(engineExpression.getName(),
                engineExpression.getContent(), engineExpression
                        .getExpressionType(), engineExpression.getReturnType(),
                engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());
        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
    }

    protected void addMinColumnsExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addMinColumnsExpression(engineExpression.getName(),
                engineExpression.getContent(), engineExpression
                        .getExpressionType(), engineExpression.getReturnType(),
                engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());
        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
    }

    protected void addMaxColumnsExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addMaxColumnsExpression(engineExpression.getName(),
                engineExpression.getContent(), engineExpression
                        .getExpressionType(), engineExpression.getReturnType(),
                engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());
        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
    }

    protected void addMinRowsExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addMinRowsExpression(engineExpression.getName(),
                engineExpression.getContent(), engineExpression
                        .getExpressionType(), engineExpression.getReturnType(),
                engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());
        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);

    }

    protected void addMaxRowsExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addMaxRowsExpression(engineExpression.getName(),
                engineExpression.getContent(), engineExpression
                        .getExpressionType(), engineExpression.getReturnType(),
                engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());
        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
    }

    private SubTitlePosition getSubtitlePosition(final ItemPosition itemPosition) {
        switch (itemPosition) {
            case BOTTOM:
                return SubTitlePosition.BOTTOM;
            case TOP:
                return SubTitlePosition.TOP;
            default:
                return SubTitlePosition.BOTTOM;
        }
    }

    /**
     * add the horizontal header of the table
     *
     * @param table
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    private void addTableHorizontalHeader(final AbstractTable table,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (table.isInitializedUsingCells()) {
            if (table.isUseHorizontalHeader()
                    && table.getTableExpression() != null) {// use the first row
                // as header
                final TableExpression tableExpresssion = table
                        .getTableExpression();
                final EList<ListExpression> list = tableExpresssion
                        .getExpressions();
                if (list.size() > 0) {
                    final EList<Expression> firstRowList = list.get(0)
                            .getExpressions();
                    builder.addHorizontalHeaderExpressionList();
                    for (int i = 0; i < firstRowList.size(); i++) {
                        final Expression cell = firstRowList.get(i);
                        addHorizontalHeaderExpression(builder, cell);
                    }
                }
            }
        } else {
            if (table.isFirstRowIsHeader() || table.isLastRowIsHeader()) {
                final Expression horizontalHeaderExpression = table
                        .getHorizontalHeaderExpression();
                if (horizontalHeaderExpression != null
                        && horizontalHeaderExpression.getContent() != null
                        && !horizontalHeaderExpression.getContent().isEmpty()) {
                    addHorizontalHeaderExpression(builder,
                            horizontalHeaderExpression);
                }
            }
        }
    }

    protected void addHorizontalHeaderExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        if (engineExpression != null) {
            builder.addHorizontalHeaderExpression(engineExpression.getName(),
                    engineExpression.getContent(), engineExpression
                            .getExpressionType(), engineExpression
                            .getReturnType(),
                    engineExpression.getInterpreter().isEmpty() ? null
                            : engineExpression.getInterpreter());
            expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
        } else {
            builder.addHorizontalHeaderExpression(EMPTY_EXPRESSION_NAME,
                    EMPTY_EXPRESSION_CONTENT, EMPTY_EXPRESSION_TYPE,
                    EMPTY_EXPRESSION_RETURN_TYPE, null);
        }
    }

    protected void addVerticalHeaderExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        if (engineExpression != null) {
            builder.addVerticalHeaderExpression(engineExpression.getName(),
                    engineExpression.getContent(), engineExpression
                            .getExpressionType(), engineExpression
                            .getReturnType(),
                    engineExpression.getInterpreter().isEmpty() ? null
                            : engineExpression.getInterpreter());
            expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
        } else {
            builder.addHorizontalHeaderExpression(EMPTY_EXPRESSION_NAME,
                    EMPTY_EXPRESSION_CONTENT, EMPTY_EXPRESSION_TYPE,
                    EMPTY_EXPRESSION_RETURN_TYPE, null);
        }

    }

    /**
     * add the vertical header of the table
     *
     * @param table
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    private void addTableVerticalHeader(final AbstractTable table,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (table.isInitializedUsingCells()) {
            if (table.isUseVerticalHeader()
                    && table.getTableExpression() != null) {
                final TableExpression tableExpression = table
                        .getTableExpression();
                final EList<ListExpression> list = tableExpression
                        .getExpressions();
                final int minRow = table.isUseHorizontalHeader() ? 1 : 0;
                if (list.get(minRow).getExpressions().size() > 0) {
                    builder.addVerticalHeaderExpressionList();
                    for (int i = minRow; i < list.size(); i++) {
                        final ListExpression list2 = list.get(i);
                        final Expression cell = list2.getExpressions().get(0);
                        addVerticalHeaderExpression(builder, cell);
                    }
                }
            }
        } else {
            if (table.isRightColumnIsHeader() || table.isLeftColumnIsHeader()) {
                final Expression verticalHeaderExpression = table
                        .getVerticalHeaderExpression();
                if (verticalHeaderExpression != null
                        && verticalHeaderExpression.getContent() != null
                        && !verticalHeaderExpression.getContent().isEmpty()) {
                    addVerticalHeaderExpression(builder,
                            verticalHeaderExpression);
                }
            }
        }
    }

    /**
     * add the available values of the table
     *
     * @param table
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addTableAvailableValue(final Table table,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (table.isInitializedUsingCells()
                && table.getTableExpression() != null) {
            final TableExpression tableExpression = table.getTableExpression();
            final int minRow = table.isUseHorizontalHeader() ? 1 : 0;
            final int minCol = table.isUseVerticalHeader() ? 1 : 0;
            final EList<ListExpression> rowExpressions = tableExpression
                    .getExpressions();
            builder.addAvailableValuesArray();
            for (int i = minRow; i < rowExpressions.size(); i++) {
                final ListExpression listExpression = rowExpressions.get(i);
                builder.addRow();
                for (int j = minCol; j < listExpression.getExpressions().size(); j++) {
                    final Expression cellExpression = listExpression
                            .getExpressions().get(j);
                    builder.addAvailableValue();
                    addLabelExpressionIfValid(builder, cellExpression);
                    addValueExpressionIfValid(builder, cellExpression);
                }
            }
        } else {
            final Expression tableInputExpression = table.getInputExpression();
            if (tableInputExpression != null
                    && tableInputExpression.getContent() != null
                    && !tableInputExpression.getContent().isEmpty()) {
                addAvailableValuesExpression(builder, tableInputExpression);
            }
        }
    }

    private void addValueExpressionIfValid(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        if (expression != null && expression.getContent() != null
                && !expression.getContent().isEmpty()) {
            final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                    .createExpression(expression);
            builder.addValueExpression(engineExpression.getName(),
                    engineExpression.getContent(), engineExpression
                            .getExpressionType(), engineExpression
                            .getReturnType(),
                    engineExpression.getInterpreter().isEmpty() ? null
                            : engineExpression.getInterpreter());
            expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
        } else {
            builder.addValueExpression(EMPTY_EXPRESSION_NAME,
                    EMPTY_EXPRESSION_CONTENT, EMPTY_EXPRESSION_TYPE,
                    EMPTY_EXPRESSION_RETURN_TYPE, null);
        }
    }

    protected void addAvailableValuesExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        if (expression.getContent() != null
                && !expression.getContent().isEmpty()) {
            final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                    .createExpression(expression);
            builder.addAvailableValuesExpression(
                    engineExpression.getName(),
                    engineExpression.getContent(),
                    engineExpression.getExpressionType(),
                    engineExpression.getReturnType(),
                    engineExpression.getInterpreter() == null
                            || engineExpression.getInterpreter().isEmpty() ? null
                            : engineExpression.getInterpreter());
            expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
        }
    }

    /**
     * @param builder
     *        , Widget widget
     * @throws InvalidFormDefinitionException
     */
    protected void addParentProperties(final IFormBuilder builder,
            final Widget widget) throws InvalidFormDefinitionException {
        // do nothing

    }

    private void addReadOnlyBehavior(final IFormBuilder builder,
            final Widget widget, final boolean isViewForm)
            throws InvalidFormDefinitionException {
        boolean readOnly = false;
        if (widget.isReadOnly() || isViewForm) {
            readOnly = true;
        } else {
            EObject container = widget.eContainer();
            /* search if there is a super-group which is read-only */
            while (container != null && container instanceof Group) {
                if (((Group) container).isReadOnly()) {
                    readOnly = true;
                    break;
                }
                container = container.eContainer();
            }
        }
        builder.addReadOnlyBehavior(readOnly);
    }

    /**
     * add the initial value of the table
     *
     * @param widget
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addTableInitialValue(final AbstractTable table,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (table instanceof Table) {
            // initial value only in selection mode
            if (((Table) table).isAllowSelection()) {
                final Expression selectedValues = ((Table) table)
                        .getSelectedValues();
                if (selectedValues != null
                        && !selectedValues.getName().isEmpty()) {
                    addInitialValueExpression(builder, selectedValues);
                }
            }
        } else {
            // dynamic table -> only an initial value
            if (table.isInitializedUsingCells()
                    && table.getTableExpression() != null) {
                builder.addInitialValuesArray();
                final TableExpression tableExpression = table
                        .getTableExpression();
                final int minRow = table.isUseHorizontalHeader() ? 1 : 0;
                final int minCol = table.isUseVerticalHeader() ? 1 : 0;
                final EList<ListExpression> rowExpressions = tableExpression
                        .getExpressions();
                for (int i = minRow; i < rowExpressions.size(); i++) {
                    final ListExpression listExpression = rowExpressions.get(i);
                    builder.addRow();
                    for (int j = minCol; j < listExpression.getExpressions()
                            .size(); j++) {
                        final Expression cellExpression = listExpression
                                .getExpressions().get(j);
                        addInitialValueExpression(builder, cellExpression);
                        // addLabelExpressionIfValid(builder, cellExpression);
                        // addValueExpressionIfValid(builder, cellExpression);
                    }
                }
            } else {
                final Expression tableInputExpression = table
                        .getInputExpression();
                if (tableInputExpression != null
                        && tableInputExpression.getContent() != null) {
                    addInitialValueExpression(builder, tableInputExpression);
                }
            }
        }

    }

    protected void addInitialValueExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        if (expression != null && expression.getContent() != null
                && !expression.getContent().isEmpty()) {
            final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                    .createExpression(expression);
            if (engineExpression != null) {
                final String interpreter = engineExpression.getInterpreter();
                builder.addInitialValueExpression(engineExpression.getName(),
                        engineExpression.getContent(),
                        engineExpression.getExpressionType(),
                        engineExpression.getReturnType(), interpreter == null
                                || interpreter.isEmpty() ? null : interpreter);
                expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
            }
        } else { // add empty expression
            builder.addInitialValueExpression(EMPTY_EXPRESSION_NAME,
                    EMPTY_EXPRESSION_CONTENT, EMPTY_EXPRESSION_TYPE,
                    EMPTY_EXPRESSION_RETURN_TYPE, null);
        }
    }

    /**
     * @param builder
     * @param widget
     * @throws InvalidFormDefinitionException
     */
    protected void addLabel(final IFormBuilder builder, final Widget widget)
            throws InvalidFormDefinitionException {
        // show display == false -> no label
        final Expression displayLabel = widget.getDisplayLabel();
        if ((widget.getShowDisplayLabel() == null || widget
                .getShowDisplayLabel().booleanValue())
                && displayLabel != null
                && displayLabel.getContent() != null
                && !displayLabel.getContent().isEmpty()) {
            // display label
            addLabelExpressionIfValid(builder, displayLabel);
            builder.addAllowHTMLInLabelBehavior(widget
                    .isAllowHTMLForDisplayLabel());
            builder.addLabelPosition(toItemPosition(widget.getLabelPosition()));
        } else if (widget instanceof FormButton) {
            // add a empty label for buttons
            builder.addLabelExpression("", "",
                    ExpressionConstants.CONSTANT_TYPE, String.class.getName(),
                    null);
        }
    }

    /**
     * @param pos
     */
    public ItemPosition toItemPosition(final LabelPosition pos) {
        switch (pos) {
            case DOWN:
                return ItemPosition.BOTTOM;
            case UP:
                return ItemPosition.TOP;
            case RIGHT:
                return ItemPosition.RIGHT;
            case LEFT:
                return ItemPosition.LEFT;

            default:
                return ItemPosition.LEFT;
        }
    }

    protected void addMandatoryBehavior(final IFormBuilder builder,
            final Widget widget) throws InvalidFormDefinitionException {
        builder.addMandatoryBehavior(widget.isMandatory());
    }

    /**
     * @param widget
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addSpecificFormField(final FormField widget,
            final IFormBuilder builder, final boolean isViewForm)
            throws InvalidFormDefinitionException {
        if (widget instanceof TextFormField) {
            builder.addWidget(widget.getName(), WidgetType.TEXTBOX);
            addInitialValue(widget, builder);
            if (((TextFormField) widget).getMaxLength() > 0) {
                builder.addMaxLength(((TextFormField) widget).getMaxLength());
            }
        } else if (widget instanceof PasswordFormField) {
            builder.addWidget(widget.getName(), WidgetType.PASSWORD);
            if (((PasswordFormField) widget).getMaxLength() > 0) {
                builder.addMaxLength(((PasswordFormField) widget)
                        .getMaxLength());
            }
            addInitialValue(widget, builder);
        } else if (widget instanceof DurationFormField) {
            builder.addWidget(widget.getName(), WidgetType.DURATION);
            final DurationFormField durationWidget = (DurationFormField) widget;
            addItemsStyle(builder, durationWidget,
                    durationWidget.getItemClass() != null
                            && durationWidget.getItemClass().equals("v"));
            builder.addDisplayFormat((durationWidget.getDay() ? "d" : "")
                    + (durationWidget.getHour() ? "h" : "")
                    + (durationWidget.getMin() ? "m" : "")
                    + (durationWidget.getSec() ? "s" : ""));
            addInitialValue(widget, builder);
        } else if (widget instanceof TextAreaFormField) {
            builder.addWidget(widget.getName(), WidgetType.TEXTAREA);
            if (((TextAreaFormField) widget).getMaxLength() > 0) {
                builder.addMaxLength(((TextAreaFormField) widget)
                        .getMaxLength());
            }
            if (((TextAreaFormField) widget).getMaxHeigth() > 0) {
                builder.addMaxHeight(((TextAreaFormField) widget)
                        .getMaxHeigth());
            }
            addInitialValue(widget, builder);
        } else if (widget instanceof CheckBoxSingleFormField) {
            builder.addWidget(widget.getName(), WidgetType.CHECKBOX);
            addInitialValue(widget, builder);
        } else if (widget instanceof RadioFormField) {
            builder.addWidget(widget.getName(), WidgetType.RADIOBUTTON_GROUP);
            addItemsStyle(
                    builder,
                    widget,
                    ((ItemContainer) widget).getItemClass() != null
                            && ((ItemContainer) widget).getItemClass().equals(
                                    "h"));
        } else if (widget instanceof SelectFormField) {
            builder.addWidget(widget.getName(), WidgetType.LISTBOX_SIMPLE);
        } else if (widget instanceof DateFormField) {
            builder.addWidget(widget.getName(), WidgetType.DATE);
            addInitialValue(widget, builder);
            if (((DateFormField) widget).getDisplayFormat() != null) {
                builder.addDisplayFormat(((DateFormField) widget)
                        .getDisplayFormat());
            }
        } else if (widget instanceof ListFormField) {
            builder.addWidget(widget.getName(), WidgetType.LISTBOX_MULTIPLE);
            if (((ListFormField) widget).getMaxHeigth() > 0) {
                builder.addMaxHeight(((ListFormField) widget).getMaxHeigth());
            }

        } else if (widget instanceof CheckBoxMultipleFormField) {
            builder.addWidget(widget.getName(), WidgetType.CHECKBOX_GROUP);
            addItemsStyle(
                    builder,
                    widget,
                    ((ItemContainer) widget).getItemClass() != null
                            && ((ItemContainer) widget).getItemClass().equals(
                                    "h"));

        } else if (widget instanceof HiddenWidget) {
            builder.addWidget(widget.getName(), WidgetType.HIDDEN);
            addInitialValue(widget, builder);
        } else if (widget instanceof FileWidget) {
            if (isViewForm || ((FileWidget) widget).isDownloadOnly()) {
                builder.addWidget(widget.getName(), WidgetType.FILEDOWNLOAD);
            } else {
                builder.addWidget(widget.getName(), WidgetType.FILEUPLOAD);
            }
            builder.addAttachmentImageBehavior(((FileWidget) widget)
                    .isUsePreview());
            addFileWidgetInputType((FileWidget) widget, builder);
            addDocumentInitialValue((FileWidget) widget, builder);
            setDocumentIsMultiple((FileWidget) widget, builder);
        } else if (widget instanceof RichTextAreaFormField) {
            builder.addWidget(widget.getName(), WidgetType.RICH_TEXTAREA);

            addInitialValue(widget, builder);
        } else if (widget instanceof SuggestBox) {
            final SuggestBox suggestBox = (SuggestBox) widget;
            if (suggestBox.isAsynchronous()) {
                builder.addWidget(widget.getName(), WidgetType.SUGGESTBOX_ASYNC);
                builder.addDelayMillis(suggestBox.getDelay());
            } else {
                builder.addWidget(widget.getName(), WidgetType.SUGGESTBOX);
            }

            addItemsStyle(builder, suggestBox, false);

            if (suggestBox.isUseMaxItems() && suggestBox.getMaxItems() > 0) {
                builder.addMaxItems(suggestBox.getMaxItems());
            }
        } else if (widget instanceof AbstractTable) {
            performOnAbstractTable(builder, widget, isViewForm);
        }

        // for multiple valuated fields
        if (widget instanceof MultipleValuatedFormField) {
            addMultipleValuatedFormFieldInitialValue(
                    (MultipleValuatedFormField) widget, builder);
            if (!(widget instanceof AbstractTable)) {
                addAvailableValues((MultipleValuatedFormField) widget, builder);
            }
        }
    }

    protected void addFileWidgetInputType(final FileWidget widget,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        final FileWidgetDownloadType widgetDownloadType = widget.getDownloadType();
        switch (widgetDownloadType) {
            case BOTH:
                builder.addFileWidgetInputType(org.bonitasoft.forms.client.model.FileWidgetInputType.ALL);
                break;
            case URL:
                builder.addFileWidgetInputType(org.bonitasoft.forms.client.model.FileWidgetInputType.URL);
                break;
            case BROWSE:
                builder.addFileWidgetInputType(org.bonitasoft.forms.client.model.FileWidgetInputType.FILE);
                break;
            default:
                break;
        }
    }

    protected void addDocumentInitialValue(final FileWidget widget,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        final FileWidgetInputType widgetInputType = widget.getInputType();
        if (widgetInputType == FileWidgetInputType.URL
                || widgetInputType == FileWidgetInputType.DOCUMENT) {
            final Expression inputExpression = widget.getInputExpression();
            if (inputExpression != null && inputExpression.getContent() != null
                    && !inputExpression.getContent().isEmpty()) {
                addInitialValueExpression(builder, inputExpression);
            }
        } else if (widgetInputType == FileWidgetInputType.RESOURCE) {
            final String resourcePath = widget.getInitialResourcePath();
            if (resourcePath != null && !resourcePath.isEmpty()) {
                builder.addInitialValueResource(resourcePath);
            }
        }

        //   builder.addDocumentListBehavior(widget.getDocument().isMultiple());
    }

    /**
     * @param widget
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    private void setDocumentIsMultiple(final FileWidget widget, final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (widget.isDuplicate()) {
            builder.addFieldOutputType(widget.getReturnTypeModifier());
        }
    }

    /**
     * @param widget
     * @param builder
     * @param alignmentCondition
     * @throws InvalidFormDefinitionException
     */
    private void addItemsStyle(final IFormBuilder builder,
            final FormField widget, final boolean alignmentCondition)
            throws InvalidFormDefinitionException {
        String classes = "";
        final String widgetUID = ExporterTools.getWidgetUID(widget);
        if (alignmentCondition) {
            classes = widgetUID + "_align ";
        }
        if (widget.getHtmlAttributes() != null
                && widget.getHtmlAttributes().size() > 0) {
            boolean containsStyle = false;
            for (final Iterator<Entry<String, String>> iterator = widget
                    .getHtmlAttributes().iterator(); iterator.hasNext();) {
                final Entry<String, String> entry = iterator.next();
                if (entry.getKey().startsWith(ExporterTools.PREFIX_ITEMS)) {
                    containsStyle = true;
                    break;
                }
            }
            if (containsStyle) {
                classes += ExporterTools.PREFIX_ITEMS + widgetUID + " ";
            }
        }
        if (widget.getHtmlAttributes() != null
                && widget.getHtmlAttributes().containsKey(
                        ExporterTools.PREFIX_ITEMS + ExporterTools.CLASS_ATTR)
                && widget.getHtmlAttributes().get(
                        ExporterTools.PREFIX_ITEMS + ExporterTools.CLASS_ATTR) != null) {
            classes += widget.getHtmlAttributes().get(
                    ExporterTools.PREFIX_ITEMS + ExporterTools.CLASS_ATTR);
        }
        if (classes.length() > 0) {
            builder.addItemsStyle(classes);
        }
    }

    protected void addMultipleValuatedFormFieldInitialValue(
            final MultipleValuatedFormField widget, final IFormBuilder builder)
            throws InvalidFormDefinitionException {
        final Expression defaultValueExpression = widget.getDefaultExpression();
        if (defaultValueExpression != null
                && defaultValueExpression.getContent() != null
                && !defaultValueExpression.getContent().isEmpty()) {
            addInitialValueExpression(builder, defaultValueExpression);
        }
    }

    /**
     * The initial value for SingleValuatedFormField is the value returned by
     * the input Groovy Script. In case of MultipleValuatedField, it is the
     * defaultValue.
     *
     * @param widget
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addInitialValue(final Widget widget,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        final Expression inputExpression = widget.getInputExpression();
        if (inputExpression != null) {
            final String widgetInputScript = inputExpression.getContent();
            if (widgetInputScript != null && widgetInputScript.length() > 0) {
                if (widget instanceof FormField) {
                    if (widget instanceof SingleValuatedFormField) {
                        addInitialValueExpression(builder, inputExpression);
                    }
                } else if (widget instanceof Info
                        || widget instanceof HiddenWidget
                        || widget instanceof Table) {
                    addInitialValueExpression(builder, inputExpression);
                }
            }
            // forbidden html have a sens only for these kind of widget
            if (widget instanceof TextFormField
                    || widget instanceof MessageInfo
                    || widget instanceof HtmlWidget
                    || widget instanceof TextAreaFormField
                    || widget instanceof TextInfo) {
                builder.addAllowHTMLInFieldBehavior(inputExpression
                        .isHtmlAllowed() || widget instanceof HtmlWidget);
            }

            // TODO For document addInitialValueExpression for URL and
            // addInitialValueResource for file
        }

    }

    /**
     * Set available values for multiplevaluated widget.
     *
     * @param widget
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addAvailableValues(final MultipleValuatedFormField widget,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        final Expression inputExpression = widget.getInputExpression();
        /* if there is no script set an empty groovy list */
        if (inputExpression != null && inputExpression.getContent() != null
                && !inputExpression.getContent().isEmpty()) {
            addAvailableValuesExpression(builder, inputExpression);
        }
        // }
        // forbidden html have a sens only for these kinds of widget
        if (widget instanceof RadioFormField
                || widget instanceof CheckBoxMultipleFormField) {
            if (inputExpression != null) {
                builder.addAllowHTMLInFieldBehavior(inputExpression
                        .isHtmlAllowed());
            } else {
                // by default not allowed
                builder.addAllowHTMLInFieldBehavior(false);
            }
        }
    }

    /**
     * @param formField
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addFormFieldValidators(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        addFormFieldDefaultValidators(formField, builder);
        addFormFieldUserValidators(formField, builder);
    }

    protected void addFormFieldDefaultValidators(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        if (formField.getUseDefaultValidator() && !formField.isDuplicate()
                && !(formField instanceof MultipleValuatedFormField)) {
            if (formField instanceof DateFormField) {
                addDefaultDateValidator(formField, builder);
                return;
            } else if (formField instanceof TextFormField) {
                final String returnTypeModifier = ((TextFormField) formField)
                        .getReturnTypeModifier();
                if (returnTypeModifier != null) {
                    if (Integer.class.getName().equals(returnTypeModifier)) {
                        addDefaultIntegerValidator(formField, builder);
                        return;
                    }
                    if (Float.class.getName().equals(returnTypeModifier)) {
                        addDefaultFloatValidator(formField, builder);
                        return;
                    }
                    if (Short.class.getName().equals(returnTypeModifier)) {
                        addDefaultShortValidator(formField, builder);
                        return;
                    }
                    if (Double.class.getName().equals(returnTypeModifier)) {
                        addDefaultDoubleValidator(formField, builder);
                        return;
                    }
                    if (Long.class.getName().equals(returnTypeModifier)) {
                        addDefaultLongValidator(formField, builder);
                        return;
                    }
                    if (Character.class.getName().equals(returnTypeModifier)) {
                        addDefaultCharValidator(formField, builder);
                        return;
                    }
                }
            }
            final Operation formFieldScript = formField.getAction();
            if (formFieldScript != null
                    && formFieldScript.getRightOperand() != null
                    && formFieldScript.getRightOperand().getContent() != null
                    && formFieldScript.getLeftOperand() != null
                    && formFieldScript.getLeftOperand().getReferencedElements() != null
                    && !formFieldScript.getLeftOperand()
                            .getReferencedElements().isEmpty()) {
                final EObject element = formFieldScript.getLeftOperand()
                        .getReferencedElements().get(0);
                if (element instanceof Data) {
                    final Data data = (Data) element;
                    final EClass dataTypeEclass = data.getDataType().eClass();
                    if (dataTypeEclass.equals(ProcessPackage.eINSTANCE
                            .getDateType())) {
                        addDefaultDateValidator(formField, builder);
                        return;
                    }
                    if (dataTypeEclass.equals(ProcessPackage.eINSTANCE
                            .getIntegerType())
                            && !(formField instanceof DurationFormField)) {
                        addDefaultIntegerValidator(formField, builder);
                        return;
                    }
                    if (dataTypeEclass.equals(ProcessPackage.eINSTANCE
                            .getFloatType())) {
                        addDefaultFloatValidator(formField, builder);
                        return;
                    }
                    if (dataTypeEclass.equals(ProcessPackage.eINSTANCE
                            .getDoubleType())) {
                        addDefaultDoubleValidator(formField, builder);
                        return;
                    }
                    if (dataTypeEclass.equals(ProcessPackage.eINSTANCE
                            .getLongType())) {
                        addDefaultLongValidator(formField, builder);
                        return;
                    }
                }
            }

        }
    }

    private void addDefaultLongValidator(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        builder.addValidator(
                formField.getName() + "_default_validator",
                DefaultValidatorsProperties.getInstance().getDefaultValidator(
                        Long.class.getName()), null, ValidatorPosition.BOTTOM);
        builder.addLabelExpression("#numericLongFieldValidatorLabel",
                "#numericLongFieldValidatorLabel",
                ExpressionConstants.CONSTANT_TYPE, String.class.getName(), null);
    }

    private void addDefaultDoubleValidator(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        builder.addValidator(
                formField.getName() + "_default_validator",
                DefaultValidatorsProperties.getInstance().getDefaultValidator(
                        Double.class.getName()), null, ValidatorPosition.BOTTOM);
        builder.addLabelExpression("#numericDoubleFieldValidatorLabel",
                "#numericDoubleFieldValidatorLabel",
                ExpressionConstants.CONSTANT_TYPE, String.class.getName(), null);
    }

    private void addDefaultFloatValidator(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        builder.addValidator(
                formField.getName() + "_default_validator",
                DefaultValidatorsProperties.getInstance().getDefaultValidator(
                        Float.class.getName()), null, ValidatorPosition.BOTTOM);
        builder.addLabelExpression("#numericFloatFieldValidatorLabel",
                "#numericFloatFieldValidatorLabel",
                ExpressionConstants.CONSTANT_TYPE, String.class.getName(), null);
    }

    private void addDefaultShortValidator(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        builder.addValidator(
                formField.getName() + "_default_validator",
                DefaultValidatorsProperties.getInstance().getDefaultValidator(
                        Short.class.getName()), null, ValidatorPosition.BOTTOM);
        builder.addLabelExpression("#numericShortFieldValidatorLabel",
                "#numericShortFieldValidatorLabel",
                ExpressionConstants.CONSTANT_TYPE, String.class.getName(), null);
    }

    private void addDefaultCharValidator(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        builder.addValidator(
                formField.getName() + "_default_validator",
                DefaultValidatorsProperties.getInstance().getDefaultValidator(
                        Character.class.getName()), null,
                ValidatorPosition.BOTTOM);
        builder.addLabelExpression("#charFieldValidatorLabel",
                "#charFieldValidatorLabel", ExpressionConstants.CONSTANT_TYPE,
                String.class.getName(), null);
    }

    private void addDefaultIntegerValidator(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        builder.addValidator(
                formField.getName() + "_default_validator",
                DefaultValidatorsProperties.getInstance().getDefaultValidator(
                        Integer.class.getName()), null,
                ValidatorPosition.BOTTOM);
        builder.addLabelExpression("#numericIntegerFieldValidatorLabel",
                "#numericIntegerFieldValidatorLabel",
                ExpressionConstants.CONSTANT_TYPE, String.class.getName(), null);
    }

    private void addDefaultDateValidator(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        builder.addValidator(
                formField.getName() + "_default_validator",
                DefaultValidatorsProperties.getInstance().getDefaultValidator(
                        Date.class.getName()), null, ValidatorPosition.BOTTOM);
        builder.addLabelExpression("#dateFieldValidatorLabel",
                "#dateFieldValidatorLabel", ExpressionConstants.CONSTANT_TYPE,
                String.class.getName(), null);
    }

    protected void addFormFieldUserValidators(final FormField formField,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        for (final Validator validator : formField.getValidators()) {
            if (validator.getValidatorClass() != null) {
                builder.addValidator(validator.getName(), validator
                        .getValidatorClass(), validator.getHtmlClass(),
                        validator.isBelowField() ? ValidatorPosition.BOTTOM
                                : ValidatorPosition.TOP);
                if (validator.getDisplayName() != null
                        && validator.getDisplayName().getContent() != null) {
                    addLabelExpressionIfValid(builder,
                            validator.getDisplayName());
                }

                if (validator.getParameter() != null
                        && validator.getParameter().getContent() != null) {
                    addParameterExpression(builder, validator.getParameter());
                }
            }
        }
    }

    /**
     * Add all form page relative to a studio process.
     * Set public for test purpose
     *
     * @param entryPageFlow
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    public void addEntryPageFlow(final PageFlow entryPageFlow,
            final IFormBuilder builder) throws InvalidFormDefinitionException {
        final EList<Form> formPages = entryPageFlow.getForm();
        if (formPages.isEmpty()) {
            generatePageFlow(entryPageFlow, builder, false);
        } else {
            builder.addEntryForm(buildEntryFormID(entryPageFlow));
            builder.addPermissions(buildPermission(entryPageFlow));
            for (final Form form : formPages) {
                addEntryPage(form, builder);
            }
            addTransientData(entryPageFlow,
                    ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA,
                    ProcessPackage.Literals.PAGE_FLOW__PAGE_FLOW_CONNECTORS,
                    builder);
        }

        if (!formPages.isEmpty()) {
            addConfirmation(builder, entryPageFlow);
        }
        File tempFile;
        final AssociatedFile confirmationTemplate = entryPageFlow
                .getConfirmationTemplate();
        if (!formPages.isEmpty() && confirmationTemplate != null
                && confirmationTemplate.getPath() != null) {
            tempFile = WebTemplatesUtil.getFile(confirmationTemplate.getPath());

            if (tempFile != null && tempFile.exists()) {
                builder.addConfirmationLayout(ExporterTools.getTemplateWarPath(
                        entryPageFlow, TemplateType.CONFIRMATION));
            }
        }

        if (!formPages.isEmpty()) {
            addFirstPageId(ProcessPackage.Literals.PAGE_FLOW__FORM,
                    entryPageFlow, builder);
        }

    }

    protected void addConfirmation(final IFormBuilder builder,
            final PageFlow entryPageFlow) throws InvalidFormDefinitionException {
        final AssociatedFile confirmationTemplate = entryPageFlow
                .getConfirmationTemplate();
        if (confirmationTemplate != null
                && confirmationTemplate.getPath() != null) {
            final File tempFile = WebTemplatesUtil.getFile(confirmationTemplate
                    .getPath());

            if (tempFile != null && tempFile.exists()) {
                builder.addConfirmationLayout(ExporterTools.getTemplateWarPath(
                        entryPageFlow, TemplateType.CONFIRMATION));
            }
        }
        final Expression confirmationMessage = entryPageFlow
                .getConfirmationMessage();
        if (confirmationMessage != null
                && confirmationMessage.getContent() != null
                && !confirmationMessage.getContent().isEmpty()) {
            addConfirmationMessageExpression(builder, confirmationMessage);
        }
    }

    protected void addConfirmationMessageExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addConfirmationMessageExpression(engineExpression.getName(),
                engineExpression.getContent(), engineExpression
                        .getExpressionType(), engineExpression.getReturnType(),
                engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());
        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);

    }

    /**
     * @throws InvalidFormDefinitionException
     */
    protected void addRecapPage(final Form f, final IFormBuilder builder)
            throws InvalidFormDefinitionException {
        builder.addPage(f.getName());
        addPageLabelExpression(f, builder);
        builder.addAllowHTMLInLabelBehavior(f.isAllowHTMLInPageLabel());
        /* match with <page-template> */
        // always add a template (generated in studio if not given by the user)
        builder.addLayout(ExporterTools
                .getTemplateWarPath(f, TemplateType.PAGE));
        addWidgets(f, builder, true);
        addNextPageId(f, builder);

    }

    protected void addStyle(final IFormBuilder builder, final Widget widget,
            final boolean withLabel) throws InvalidFormDefinitionException {
        /*
         * Common to all widgets
         */
        if (withLabel) {
            final String label_classes = getCSSClasses(widget,
                    ExporterTools.PREFIX_LABEL);
            if (label_classes.length() > 0) {
                builder.addLabelStyle(label_classes);
            }
        }

        final String cellClasses = getCSSClasses(widget,
                ExporterTools.PREFIX_WIDGET);
        if (cellClasses.length() > 0) {
            builder.addStyle(cellClasses);
        }

        final String classes = getCSSClasses(widget, ExporterTools.PREFIX_INPUT);
        if (classes.length() > 0) {
            builder.addInputStyle(classes);
        }
        if (!(widget instanceof IFrameWidget)
                && !(widget instanceof HiddenWidget)) {
            final Expression widgetTooltip = widget.getTooltip();
            if (widgetTooltip != null && widgetTooltip.getContent() != null
                    && !widgetTooltip.getContent().isEmpty()) {
                addTitleExpression(builder, widgetTooltip);
            }
        }
    }

    protected void addTitleExpression(final IFormBuilder builder,
            final Expression expression) throws InvalidFormDefinitionException {
        final org.bonitasoft.engine.expression.Expression engineExpression = EngineExpressionUtil
                .createExpression(expression);
        builder.addTitleExpression(engineExpression.getName(), engineExpression
                .getContent(), engineExpression.getExpressionType(),
                engineExpression.getReturnType(),
                engineExpression.getInterpreter().isEmpty() ? null
                        : engineExpression.getInterpreter());
        expressionDependecyBuilder.buildExpressionDependency(builder, engineExpression);
    }

    /**
     * @param widget
     * @return
     */
    private String getCSSClasses(final Widget widget, final String prefix) {
        String label_classes = ""; //$NON-NLS-1$
        final EMap<String, String> htmlAttributes = widget.getHtmlAttributes();
        if (htmlAttributes != null && htmlAttributes.size() > 0) {
            label_classes += prefix + ExporterTools.getWidgetUID(widget);
        }
        if (htmlAttributes != null
                && htmlAttributes
                        .containsKey(prefix + ExporterTools.CLASS_ATTR)
                && htmlAttributes.get(prefix + ExporterTools.CLASS_ATTR) != null) {
            label_classes += (label_classes.length() > 0 ? " " : "") + htmlAttributes.get(prefix + ExporterTools.CLASS_ATTR); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return label_classes;
    }

    public static Map<String, String> parseToExtractHtmlAttributes(
            final String s) {
        final String spaces = "[ ]*";
        final String keyPattern = spaces + "(" + "[a-zA-Z_:][-a-zA-Z0-9_:.]+"
                + ")" + spaces;
        /*
         * the quote can be double or simple and it need to be different quote
         * inside the value examples : "alert('test')" -> OK 'alert("test")' ->
         * OK "alert("test")" -> KO 'alert('test')' -> KO
         */
        final String valuePattern = spaces
                + "(\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"" + "|"
                + "'([^'\\\\]*(\\\\.[^'\\\\]*)*)')";
        final String stringPattern = keyPattern + "=" + valuePattern;
        final Pattern pattern = Pattern.compile(stringPattern);
        final Matcher m = pattern.matcher(s);
        final Map<String, String> res = new HashMap<String, String>();
        while (m.find()) {
            final String key = m.group(1);
            String value = null;
            if (m.group(3) != null) {
                value = m.group(3);
            } else {
                value = m.group(5);
            }
            res.put(key, value);
        }

        return res;
    }

    /**
     * @param task
     * @param builder
     * @throws InvalidFormDefinitionException
     */
    protected void addFirstPageId(final EStructuralFeature feature,
            final Element pageflow, final IFormBuilder builder)
            throws InvalidFormDefinitionException {
        final String firstPageName = ((Form) ((List<?>) pageflow.eGet(feature))
                .get(0)).getName();
        builder.addFirstPageIdExpression(firstPageName, firstPageName,
                ExpressionConstants.CONSTANT_TYPE, String.class.getName(), null);
    }

    /**
     * @param f
     * @param builder
     * @return
     * @throws InvalidFormDefinitionException
     */
    protected void addNextPageId(final Form f, final IFormBuilder builder)
            throws InvalidFormDefinitionException {
        final EStructuralFeature feature = f.eContainingFeature();
        final Element pageflow = (Element) f.eContainer();
        // no page flow defined so we return the next page in the list
        final List<?> forms = (List<?>) pageflow.eGet(feature);
        final int indexOf = forms.indexOf(f);
        if (forms.size() > indexOf + 1) {
            final String nextPageName = ((Element) forms.get(indexOf + 1))
                    .getName();
            builder.addNextPageIdExpression(nextPageName, nextPageName,
                    ExpressionConstants.CONSTANT_TYPE, String.class.getName(),
                    null);
        } else {
            // no next form (must not have a next button)
        }
    }

    protected String buildViewFormID(final ViewPageFlow viewFlow) {
        return buildFormID(viewFlow, FormServiceProviderUtil.VIEW_FORM_TYPE);
    }

    protected String buildRecapFormID(final RecapFlow recapFlow) {
        return buildFormID(recapFlow, FormServiceProviderUtil.RECAP_FORM_TYPE);
    }

    protected String buildEntryFormID(final PageFlow entryFlow) {
        return buildFormID(entryFlow, FormServiceProviderUtil.ENTRY_FORM_TYPE);
    }

    private String buildFormID(final AbstractPageFlow pageFlow,
            final String formType) {
        if (pageFlow instanceof Activity || pageFlow instanceof Event) {
            // ActivityDefinition activityDefinition =
            // activityDefNameMap.get(pageFlow.getName());
            // if(activityDefinition != null){
            return procDefid + "--" + pageFlow.getName()
                    + FormServiceProviderUtil.FORM_ID_SEPARATOR + formType;
            // } else {
            // //try to build it manually :s
            // return
            // procDefid+"--"+pageFlow.getName()+FormServiceProviderUtil.FORM_ID_SEPARATOR+formType;
            // }
        } else {
            return procDefid + FormServiceProviderUtil.FORM_ID_SEPARATOR
                    + formType;
        }
    }

    protected String buildPermission(final AbstractPageFlow pageFlow) {
        if (pageFlow instanceof Activity) {
            // ActivityDefinition activityDefinition =
            // activityDefNameMap.get(pageFlow.getName());
            // if(activityDefinition != null){
            // return FormServiceProviderUtil.ACTIVITY_UUID+"#"+procDefid + "--"
            // + activityDefinition.getName();
            // } else {
            return FormServiceProviderUtil.ACTIVITY_UUID + "#" + procDefid
                    + "--" + pageFlow.getName();
            // }
        } else {
            return FormServiceProviderUtil.PROCESS_UUID + "#" + procDefid;
        }
    }

}
