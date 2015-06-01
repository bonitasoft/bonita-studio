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
package org.bonitasoft.studio.diagram.form.custom.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetHelper;
import org.bonitasoft.studio.common.emf.tools.WidgetModifiersSwitch;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetContainer;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.Info;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.Table;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.form.providers.ElementInitializers;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * Add a new Form to a pageFlow It also create the Diagram and open it
 *
 * @author Romain Bioteau
 */
public class CreateFormCommand extends AbstractTransactionalCommand {

    private static final int MIN_BUTTON_LINE = 2;

    private final String formName;
    private final List<? extends WidgetMapping> widgesMappings;

    private final Element pageFlow;
    private final String description;
    private final EStructuralFeature feature;
    private boolean initialValueGenerated = true;

    public CreateFormCommand(final Element pageFlow,
            final EStructuralFeature feature,
            final String formName,
            final String description,
            final List<? extends WidgetMapping> widgesMappings,
            final TransactionalEditingDomain editingDomain) {
        super(editingDomain, "Create form", getWorkspaceFiles(pageFlow));
        Assert.isNotNull(widgesMappings);
        this.formName = NamingUtils.toJavaIdentifier(formName, true);
        this.description = description;
        this.widgesMappings = widgesMappings;
        this.pageFlow = pageFlow;
        this.feature = feature;
    }

    public CreateFormCommand(final Element pageFlow,
            final EStructuralFeature feature,
            final String formName,
            final String description,
            final TransactionalEditingDomain editingDomain) {
        this(pageFlow, feature, formName, description, Collections.<WidgetMapping> emptyList(), editingDomain);
    }

    public void setGenerateInitialValue(final boolean initialValueGenerated) {
        this.initialValueGenerated = initialValueGenerated;
    }

    protected Expression createLabelExpression(final String name) {
        final String capName = CodeGenUtil.capName(name, Locale.getDefault());
        final String formattedName = CodeGenUtil.format(capName, ' ', null, false, false);

        final Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        expr.setName(formattedName);
        expr.setContent(formattedName);
        expr.setType(ExpressionConstants.CONSTANT_TYPE);
        expr.setReturnType(String.class.getName());
        expr.setReturnTypeFixed(true);
        return expr;
    }

    protected int getHorizontalSpan(final Form myForm) {
        if (myForm instanceof ViewForm) {
            return 1;
        }
        int res = 1;
        if (((List<?>) pageFlow.eGet(feature)).size() == 0) {
            res = 1;
        } else {
            res = 2;
        }
        myForm.setNColumn(res);
        return res;
    }

    protected int getLineIndex(final WidgetContainer container) {
        int nbLines = 0;
        for (final Widget w : container.getWidgets()) {
            nbLines += incrementLineIndex(w);
        }
        return nbLines;
    }

    private int incrementLineIndex(final Widget w) {
        int nbLines = 0;
        if (w instanceof Group) {
            for (final Widget groupChild : ((Group) w).getWidgets()) {
                nbLines += incrementLineIndex(groupChild);
            }
        } else {
            nbLines = 1;
        }
        return nbLines;
    }

    public String getDescription() {
        return "Create a form with generated widget and add it to pageflow";
    }

    @Override
    protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info)
            throws ExecutionException {
        final Form form = createForm();
        final int hSpan = getHorizontalSpan(form);
        form.setNColumn(hSpan);
        final WidgetContainer formContainer = new WidgetContainer(form);
        addWidgetsToForm(formContainer, hSpan);

        // add a "previous" button if there is more than one form
        if (((List<?>) pageFlow.eGet(feature)).size() != 0) {
            addPreviousButton(form);
        }

        if (feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM)) {
            addSubmitButton(form);
        }

        form.setNLine(Math.max(getLineIndex(formContainer), MIN_BUTTON_LINE + 1));

        AddCommand.create(getEditingDomain(), pageFlow, feature, form).execute();
        return CommandResult.newOKCommandResult(form);
    }

    protected void addWidgetsToForm(final WidgetContainer container, final int horizontalSpan) {
        for (final WidgetMapping mapping : widgesMappings) {
            createWidgetFromMapping(container, mapping, horizontalSpan);
        }
    }

    protected Widget createWidgetFromMapping(final WidgetContainer container, final WidgetMapping mapping, final int horizontalSpan) {
        if (mapping.isGenerated()) {
            final Widget widget = new CreateWidgetSwitch(pageFlow, ElementInitializers.getInstance()).doSwitch(mapping.getWidgetType());
            widget.setInjectWidgetScript(createInsertWidgetIfScript());
            if (supportReadOnly(widget)) {
                widget.setReadOnly(mapping.isReadOnly());
            }
            if (supportMandatory(widget)) {
                widget.setMandatory(mapping.isMandatory());
            }
            widget.setWidgetLayoutInfo(createWidgetLayout(getLineIndex(container), 0, horizontalSpan, getVerticalSpan(widget)));
            container.getWidgets().add(widget);
            addNameAndDisplayLabel(mapping, widget);
            addMappingExpressions(mapping, widget);
            return widget;
        } else if (!mapping.getChildren().isEmpty()) {
            for (final WidgetMapping childMapping : mapping.getChildren()) {
                createWidgetFromMapping(container, childMapping, horizontalSpan);
            }
            return null;
        }
        return null;
    }

    protected int getVerticalSpan(final Widget widget) {
        if (widget instanceof Group) {
            int vSpan = 0;
            for (final Widget w : ((Group) widget).getWidgets()) {
                vSpan += getVerticalSpan(w);
            }
            return vSpan;
        } else {
            return 1;
        }
    }

    protected boolean supportMandatory(final Widget widget) {
        final boolean isAFormFieldAndNotHiddenWidget = widget instanceof FormField && !(widget instanceof HiddenWidget);
        return isAFormFieldAndNotHiddenWidget || widget instanceof Group;
    }

    protected boolean supportReadOnly(final Widget widget) {
        return (widget instanceof FormField || widget instanceof Group) && !(widget instanceof FileWidget);
    }

    protected void addMappingExpressions(final WidgetMapping mapping, final Widget widget) {
        final Object modelElement = mapping.getModelElement();
        if (modelElement instanceof Data) {
            setWidgetModifier(DataUtil.getTechnicalTypeFor((Data) modelElement), widget);
            addInputExpressionForData((Data) modelElement, widget);
            addOutputOperationForData(widget, mapping);
        } else if (modelElement instanceof Document && widget instanceof FileWidget) {
            addInputExpressionForDocument((Document) modelElement, (FileWidget) widget);
            addOutputOperationForDocument(mapping, (FileWidget) widget);
        } else if (modelElement instanceof SimpleField) {
            addMappingExpressionsForField(mapping, widget, (SimpleField) modelElement);
            setMaxLength(widget, (SimpleField) modelElement);
        }
    }

    protected void setMaxLength(final Widget widget, final SimpleField modelElement) {
        if (widget instanceof TextFormField && modelElement.getType() == FieldType.STRING && modelElement.getLength() != null) {
            ((TextFormField) widget).setMaxLength(modelElement.getLength());
        }
    }

    protected void addMappingExpressionsForField(
            final WidgetMapping mapping, final Widget widget, final SimpleField field) {
        setWidgetModifier(field.getType().getClazz().getName(), widget);
        if (widget instanceof Duplicable && ((Duplicable) widget).isDuplicate()) {
            if (field.isNullable() != null && !field.isNullable()) {
                ((Duplicable) widget).setLimitMinNumberOfDuplication(true);
                ((Duplicable) widget).setMinNumberOfDuplication(ExpressionHelper.createConstantExpression("1", Integer.class.getName()));
            }
        }
        final BusinessObjectData data = getBusinessOjectDataFor(mapping);
        Assert.isNotNull(data);
        addInputExpressionForBusinessDataField(data, field, widget);
        addOutputOperationForBusinessDataField(data, field, widget);
    }

    protected void addNameAndDisplayLabel(final WidgetMapping mapping,
            final Widget widget) {
        final Object modelElement = mapping.getModelElement();
        if (modelElement instanceof Element) {
            final String keyName = ((Element) modelElement).getName();
            final String widgetId = computeWidgetId(keyName, widget);
            widget.setName(widgetId);
            widget.setDisplayLabel(createLabelExpression(keyName));
        } else if (modelElement instanceof SimpleField) {
            final GenFeature genFeature = createGenFeature((SimpleField) modelElement);
            final String widgetId = computeWidgetId(genFeature.getName(), widget);
            widget.setName(widgetId);
            widget.setDisplayLabel(createLabelExpression(genFeature.getCapName()));
        }
    }

    protected void addOutputOperationForBusinessDataField(final BusinessObjectData data, final SimpleField field, final Widget widget) {
        if (!isDataPageFlowTransient(data) && hasOutputOperation(widget)) {
            widget.setAction(createBusinessDataOutputOperation(widget, field, data));
        }
    }

    protected void setWidgetModifier(final String type, final Widget widget) {
        final Collection<String> supportedModifiers = new WidgetModifiersSwitch().doSwitch(widget);
        if (supportedModifiers.contains(type) && widget instanceof TextFormField) {
            widget.setReturnTypeModifier(type);
        } else {
            widget.setReturnTypeModifier(null);
        }
    }

    protected void addInputExpressionForData(final Data data, final Widget widget) {
        Expression inputExpression = ExpressionFactory.eINSTANCE.createExpression();
        inputExpression.setContent(data.getName());
        inputExpression.setName(data.getName());
        inputExpression.setType(ExpressionConstants.VARIABLE_TYPE);
        inputExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(data));
        inputExpression.setReturnType(org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data));

        if (widget instanceof CheckBoxSingleFormField) {
            inputExpression.setReturnTypeFixed(true);
        }

        if (isAnEnumOnAMultipleValuatedFormField(data, widget)) {
            if (!isOnInstantiationForm(data)) {
                ((MultipleValuatedFormField) widget).setDefaultExpression(inputExpression);
            }
            inputExpression = ExpressionHelper.createExpressionFromEnumType((EnumType) data.getDataType());
            widget.setInputExpression(inputExpression);
        } else if (!isOnInstantiationForm(data)) { //Do not set input expression if we are in an instantiation form
            widget.setInputExpression(inputExpression);
        }
    }

    protected boolean isAnEnumOnAMultipleValuatedFormField(final Data data, final Widget widget) {
        return data.getDataType() instanceof EnumType && widget instanceof MultipleValuatedFormField;
    }

    protected boolean isOnInstantiationForm(final Data data) {
        return ProcessPackage.Literals.PAGE_FLOW__FORM.equals(feature) //Entry form
                && pageFlow instanceof Pool //On a pool
                && !ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA.equals(data.eContainingFeature()); //Not a pageflow data
    }

    protected void addOutputOperationForData(final Widget widget, final WidgetMapping mapping) {
        final Data data = (Data) mapping.getModelElement();
        if (!isDataPageFlowTransient(data) && hasOutputOperation(widget)) {
            widget.setAction(createDataOutputOperation(widget, data));
        }
    }

    protected boolean hasOutputOperation(final Widget widget) {
        return !(widget instanceof Info) && !(widget instanceof Table);
    }

    protected void addOutputOperationForDocument(final WidgetMapping mapping, final FileWidget widget) {
        final Document doc = (Document) mapping.getModelElement();
        widget.setAction(createDocumentOutputOperation(widget, doc));
    }

    protected void addInputExpressionForDocument(final Document key, final FileWidget widget) {
        final Expression inputExpression = ExpressionHelper.createDocumentReferenceExpression(key);
        if (!(feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM) && pageFlow instanceof Pool)) { //Do not set input expression if we are in an instantiation form
            widget.setInputExpression(inputExpression);
        }
        widget.setReturnTypeModifier(WidgetModifiersSwitch.ENGINE_DOCUMENT_QUALIFIED_NAME);
    }

    protected void addSubmitButton(final Form myForm) {
        final Widget submitButton = FormFactory.eINSTANCE.createSubmitFormButton();
        final String submitButtonName = NamingUtils.getInstance(pageFlow).generateName(submitButton, pageFlow);
        submitButton.setName(NamingUtils.toJavaIdentifier(submitButtonName, true));
        submitButton.setDisplayLabel(createLabelExpression(submitButtonName));
        submitButton.setInjectWidgetScript(createInsertWidgetIfScript());
        final WidgetLayoutInfo wLayout = FormFactory.eINSTANCE.createWidgetLayoutInfo();
        final int numberOfLines = getLineIndex(new WidgetContainer(myForm));
        wLayout.setLine(Math.max(((List<?>) pageFlow.eGet(feature)).size() != 0 ? numberOfLines - 1 : numberOfLines, MIN_BUTTON_LINE));
        if (((List<?>) pageFlow.eGet(feature)).size() != 0) {
            wLayout.setColumn(1);
        }
        submitButton.setWidgetLayoutInfo(wLayout);
        myForm.getWidgets().add(submitButton);
    }

    protected void addPreviousButton(final Form myForm) {
        final Widget previousButton = FormFactory.eINSTANCE.createPreviousFormButton();
        final String previousName = NamingUtils.getInstance(pageFlow).generateName(previousButton, pageFlow);
        previousButton.setDisplayLabel(createLabelExpression(previousName));
        previousButton.setName(NamingUtils.toJavaIdentifier(previousName, true));
        previousButton.setInjectWidgetScript(createInsertWidgetIfScript());
        final WidgetLayoutInfo wLayout = FormFactory.eINSTANCE.createWidgetLayoutInfo();
        wLayout.setLine(Math.max(getLineIndex(new WidgetContainer(myForm)), MIN_BUTTON_LINE));
        previousButton.setWidgetLayoutInfo(wLayout);
        myForm.getWidgets().add(previousButton);
    }

    protected Form createForm() {
        Form myForm;
        /* set the numColumn (which will be the horizontal span for widgets) */
        if (feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM)) {
            myForm = FormFactory.eINSTANCE.createForm();

        } else {
            myForm = FormFactory.eINSTANCE.createViewForm();
            // submit button
        }
        myForm.setName(formName);
        myForm.setPageLabel(createLabelExpression(formName));
        myForm.setShowPageLabel(true);
        myForm.setDocumentation(description == null ? "" : description);
        return myForm;
    }

    protected WidgetLayoutInfo createWidgetLayout(final int nLine, final int nCol, final int horizontalSpan, final int verticalSpan) {
        final WidgetLayoutInfo wLayout = FormFactory.eINSTANCE.createWidgetLayoutInfo();
        wLayout.setLine(nLine);
        wLayout.setColumn(nCol);
        wLayout.setHorizontalSpan(horizontalSpan);
        wLayout.setVerticalSpan(verticalSpan);
        return wLayout;
    }

    protected Operation createDocumentOutputOperation(final FileWidget widget, final Document doc) {
        final Operation action = ExpressionFactory.eINSTANCE.createOperation();

        final Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
        assignment.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR);
        action.setOperator(assignment);

        final Expression storageExpression = createStorageExpressionForDocument(doc, false);
        action.setLeftOperand(storageExpression);

        final Expression actionExpression = createActionExpressionForDocument(widget);
        action.setRightOperand(actionExpression);
        return action;
    }

    protected Expression createStorageExpressionForDocument(final Document doc, final boolean isMultiple) {
        final Expression storageExpression = ExpressionFactory.eINSTANCE.createExpression();
        storageExpression.setContent(doc.getName());
        storageExpression.setName(doc.getName());
        storageExpression.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
        if (isMultiple) {
            storageExpression.setReturnType(List.class.getName());
        } else {
            storageExpression.setReturnType(String.class.getName());
        }
        storageExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(doc));
        return storageExpression;

    }

    protected Expression createActionExpressionForDocument(final FileWidget widget) {
        final Expression actionExpression = ExpressionFactory.eINSTANCE.createExpression();
        final String widgetName = widget.getName();
        actionExpression.setContent(WidgetHelper.FIELD_PREFIX + widgetName);
        actionExpression.setName(WidgetHelper.FIELD_PREFIX + widgetName);
        actionExpression.setType(ExpressionConstants.FORM_FIELD_TYPE);
        if (widget.isDuplicate()) {
            actionExpression.setReturnType(List.class.getName());
        } else {
            actionExpression.setReturnType(DocumentValue.class.getName());
        }
        actionExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(widget));
        return actionExpression;
    }

    protected Operation createDataOutputOperation(final Widget widget, final Data data) {
        final Operation action = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
        assignment.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        action.setOperator(assignment);
        action.setLeftOperand(createVariableExpression(data));
        action.setRightOperand(createWidgetExpression(widget));
        return action;
    }

    protected Expression createWidgetExpression(final Widget widget) {
        final Expression actionExpression = ExpressionFactory.eINSTANCE.createExpression();
        final String widgetName = widget.getName();
        actionExpression.setContent(WidgetHelper.FIELD_PREFIX + widgetName);
        actionExpression.setName(WidgetHelper.FIELD_PREFIX + widgetName);
        actionExpression.setType(ExpressionConstants.FORM_FIELD_TYPE);
        actionExpression.setReturnType(WidgetHelper.getAssociatedReturnType(widget));
        actionExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(widget));
        return actionExpression;
    }

    protected Expression createVariableExpression(final Data data) {
        final Expression storageExpression = ExpressionFactory.eINSTANCE.createExpression();
        storageExpression.setContent(data.getName());
        storageExpression.setName(data.getName());
        storageExpression.setType(ExpressionConstants.VARIABLE_TYPE);
        storageExpression.setReturnType(org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data));
        storageExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(data));
        return storageExpression;
    }

    protected void addInputExpressionForBusinessDataField(final BusinessObjectData data, final SimpleField field, final Widget widget) {
        if (initialValueGenerated) {
            if (!isOnInstantiationForm(data)) { // Do not set input expression if we are in an instantiation form
                final String qualifiedClassname = org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data);
                final Expression inputExpression = ExpressionFactory.eINSTANCE.createExpression();
                inputExpression.setContent(getGetterFor(field));
                inputExpression.setName(data.getName() + " - " + qualifiedClassname + "#" + getGetterFor(field));
                inputExpression.setType(ExpressionConstants.JAVA_TYPE);
                inputExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(data));
                String returnType = field.getType().getClazz().getName();
                if (field.isCollection() != null && field.isCollection()) {
                    returnType = List.class.getName();
                }
                inputExpression.setReturnType(returnType);
                if (widget instanceof CheckBoxSingleFormField) {
                    inputExpression.setReturnTypeFixed(true);
                }
                widget.setInputExpression(inputExpression);
            }
        }
    }

    protected Operation createBusinessDataOutputOperation(final Widget widget, final SimpleField field, final BusinessObjectData data) {
        final Operation action = ExpressionFactory.eINSTANCE.createOperation();
        final Operator operator = ExpressionFactory.eINSTANCE.createOperator();
        operator.setType(ExpressionConstants.JAVA_METHOD_OPERATOR);
        operator.setExpression(getSetterFor(field));
        String returnType = field.getType().getClazz().getName();
        if (field.isCollection() != null && field.isCollection()) {
            returnType = List.class.getName();
        }
        operator.getInputTypes().add(returnType);
        action.setOperator(operator);
        action.setLeftOperand(createVariableExpression(data));

        final Expression actionExpression = ExpressionFactory.eINSTANCE.createExpression();
        actionExpression.setContent(WidgetHelper.FIELD_PREFIX + widget.getName());
        actionExpression.setName(WidgetHelper.FIELD_PREFIX + widget.getName());
        actionExpression.setType(ExpressionConstants.FORM_FIELD_TYPE);
        actionExpression.setReturnType(WidgetHelper.getAssociatedReturnType(widget));
        actionExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(widget));
        action.setRightOperand(actionExpression);
        return action;
    }

    protected String getSetterFor(final SimpleField field) {
        return "set" + createGenFeature(field).getAccessorName();
    }

    protected String getGetterFor(final SimpleField field) {
        return createGenFeature(field).getGetAccessor();
    }

    protected GenFeature createGenFeature(final SimpleField field) {
        final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        final EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName(field.getName());
        if (field.getType() == FieldType.BOOLEAN) {
            eAttribute.setEType(EcorePackage.Literals.EBOOLEAN);
        }
        eClass.getEStructuralFeatures().add(eAttribute);
        ePackage.getEClassifiers().add(eClass);

        final GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
        final GenPackage genPackage = genModel.createGenPackage();
        genPackage.setEcorePackage(ePackage);
        final GenClass genClass = genModel.createGenClass();
        genClass.setEcoreClass(eClass);
        final GenFeature genFeature = genModel.createGenFeature();
        genFeature.setEcoreFeature(eAttribute);
        genFeature.setGenClass(genClass);
        genPackage.getGenClasses().add(genClass);
        genModel.getGenPackages().add(genPackage);
        return genFeature;
    }

    protected BusinessObjectData getBusinessOjectDataFor(final WidgetMapping mapping) {
        Object modelElement = mapping.getModelElement();
        WidgetMapping currentMapping = mapping;
        while (!(modelElement instanceof BusinessObjectData) && currentMapping != null && currentMapping.getParent() != null) {
            currentMapping = currentMapping.getParent();
            modelElement = currentMapping.getModelElement();
        }
        return (BusinessObjectData) modelElement;
    }

    protected boolean isDataPageFlowTransient(final Data data) {
        final EReference reference = data.eContainmentFeature();
        if (reference != null) {
            return reference.equals(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA)
                    || reference.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA)
                    || reference.equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA);
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.workspace.AbstractEMFOperation#canUndo()
     */
    @Override
    public boolean canUndo() {
        return false;//avoid issues when a form was created an en editor is open on it
    }

    protected Expression createInsertWidgetIfScript() {
        final Expression exp = org.bonitasoft.studio.common.NamingUtils.generateConstantExpression("");
        exp.setReturnType(Boolean.class.getName());
        exp.setReturnTypeFixed(true);
        return exp;
    }

    protected String computeWidgetId(String key, final Widget widget) {
        final PageFlow pf = (PageFlow) pageFlow;
        if (widget.eContainer() instanceof Group) {
            key = ((Element) widget.eContainer()).getName() + "_" + key;
        }
        if (pf != null) {
            int number = NamingUtils.getMaxElements(pageFlow, key);
            number++;
            key += number;
        }
        return key;
    }

    protected List<? extends WidgetMapping> getWidgesMappings() {
        return widgesMappings;
    }
}
