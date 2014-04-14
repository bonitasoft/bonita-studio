/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.form.custom.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * 
 * Add a new Form to a pageFlow It also create the Diagram and open it
 * 
 * @author Romain Bioteau
 */
public class CreateFormCommand extends AbstractTransactionalCommand {


	private static final int MIN_BUTTON_LINE = 2;

	private final String formName;
	private List<? extends WidgetMapping> widgesMappings;


	private final Element pageFlow;
	private final String description;
	private final EStructuralFeature feature;

	public CreateFormCommand(Element pageFlow, 
			EStructuralFeature feature,
			String formName, 
			String description, 
			List<? extends WidgetMapping> widgesMappings,
			TransactionalEditingDomain editingDomain) {
		super(editingDomain, "Create form", getWorkspaceFiles(pageFlow));
		Assert.isNotNull(widgesMappings);
		this.formName = NamingUtils.toJavaIdentifier(formName,true);
		this.description = description;
		this.widgesMappings = widgesMappings;
		this.pageFlow = pageFlow;
		this.feature = feature;
	}
	
	public CreateFormCommand(Element pageFlow, 
			EStructuralFeature feature,
			String formName, 
			String description, 
			TransactionalEditingDomain editingDomain) {
		this(pageFlow,feature,formName,description,Collections.<WidgetMapping>emptyList(),editingDomain);
	}

	protected Expression createLabelExpression(String name) {
		String capName = CodeGenUtil.capName(name, Locale.getDefault());
		String formattedName = CodeGenUtil.format(capName, ' ', null, false, false);

		Expression expr = ExpressionFactory.eINSTANCE.createExpression();
		expr.setName(formattedName);
		expr.setContent(formattedName);
		expr.setType(ExpressionConstants.CONSTANT_TYPE);
		expr.setReturnType(String.class.getName());
		expr.setReturnTypeFixed(true);
		return expr;
	}

	protected int getHorizontalSpan(Form myForm) {
		if(myForm instanceof ViewForm){
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

	protected int getLineIndex(WidgetContainer container) {
		int nbLines = 0;
		for(Widget w : container.getWidgets()){
			nbLines += incrementLineIndex(w);
		}
		return nbLines;
	}


	private int incrementLineIndex(Widget w) {
		int nbLines = 0 ;
		if(w instanceof Group){
			for(Widget groupChild : ((Group) w).getWidgets()){
				nbLines += incrementLineIndex(groupChild);
			}
		}else{
			nbLines = 1;
		}
		return nbLines;
	}

	public String getDescription() {
		return "Create a form with generated widget and add it to pageflow";
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		Form form = createForm();
		int hSpan = getHorizontalSpan(form);
		form.setNColumn(hSpan);
		WidgetContainer formContainer = new WidgetContainer(form);
		addWidgetsToForm(formContainer,hSpan);

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

	protected void addWidgetsToForm(WidgetContainer container,int horizontalSpan) {
		for (WidgetMapping mapping : widgesMappings) {
			createWidgetFromMapping(container,mapping,horizontalSpan);
		}
	}

	protected Widget createWidgetFromMapping(WidgetContainer container, WidgetMapping mapping, int horizontalSpan) {
		if(mapping.isGenerated()){
			Widget widget = new CreateWidgetSwitch(ElementInitializers.getInstance()).doSwitch(mapping.getWidgetType());
			widget.setInjectWidgetScript(createInsertWidgetIfScript());
			if(supportReadOnly(widget)){
				widget.setReadOnly(mapping.isReadOnly());
			}
			if(supportMandatory(widget)){
				widget.setMandatory(mapping.isMandatory());
			}
			widget.setWidgetLayoutInfo(ceateWidgetLayout(getLineIndex(container), 0,horizontalSpan,getVerticalSpan(widget)));
			container.getWidgets().add(widget);
			addNameAndDisplayLabel(mapping, widget);
			addMappingExpressions(mapping, widget);
			return widget;
		}
		return null;
	}


	protected int getVerticalSpan(Widget widget) {
		if(widget instanceof Group){
			int vSpan = 0;
			for(Widget w : ((Group) widget).getWidgets()){
				vSpan += getVerticalSpan(w);
			}
			return vSpan;
		}else{
			return 1;	
		}
	}

	protected boolean supportMandatory(Widget widget) {
		return widget instanceof FormField && !(widget instanceof HiddenWidget) || widget instanceof Group ;
	}


	protected boolean supportReadOnly(Widget widget) {
		return (widget instanceof FormField || widget instanceof Group) && !(widget instanceof FileWidget);
	}

	protected void addMappingExpressions(final WidgetMapping mapping, Widget widget) {
		Object modelElement = mapping.getModelElement();
		if(modelElement instanceof Data){
			setWidgetModifier(DataUtil.getTechnicalTypeFor((Data) modelElement),widget);
			addInputExpressionForData((Data) modelElement, widget);
			addOutputOperationForData(widget,mapping);
		}else if(modelElement instanceof Document && widget instanceof FileWidget){
			addInputExpressionForDocument((Document)modelElement, (FileWidget)widget);
			addOutputOperationForDocument(mapping, widget);
		}

	}

	protected void addNameAndDisplayLabel(final WidgetMapping mapping,
			Widget widget) {
	    Object modelElement = mapping.getModelElement();
		if(modelElement instanceof Element){
			String keyName = ((Element) modelElement).getName();
			String widgetId  = computeWidgetId(keyName,widget);
			widget.setName(widgetId);
			widget.setDisplayLabel(createLabelExpression(keyName));
		}
	}

	protected void setWidgetModifier(String type, Widget widget) {
		Collection<String> supportedModifiers = new WidgetModifiersSwitch().doSwitch(widget);
		if(supportedModifiers.contains(type) && widget instanceof TextFormField){
			widget.setReturnTypeModifier(type);
		}else{
			widget.setReturnTypeModifier(null);
		}
	}

	protected void addInputExpressionForData(final Data data, Widget widget) {
		Expression inputExpression = ExpressionFactory.eINSTANCE.createExpression();
		inputExpression.setContent(data.getName()) ;
		inputExpression.setName(data.getName()) ;
		inputExpression.setType(ExpressionConstants.VARIABLE_TYPE) ;
		inputExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(data)) ;
		inputExpression.setReturnType(org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data)) ;

		if(widget instanceof CheckBoxSingleFormField){
			inputExpression.setReturnTypeFixed(true);
		}                    

		if (isAnEnumOnAMultipleValuatedFormField(data, widget)) {
			if(!isOnInstantiationForm(data)){
				((MultipleValuatedFormField) widget).setDefaultExpression(inputExpression);
			}
			inputExpression = ExpressionHelper.createExpressionFromEnumType((EnumType) data.getDataType());
			widget.setInputExpression(inputExpression);
		}else if(!isOnInstantiationForm(data)){ //Do not set input expression if we are in an instantiation form
			widget.setInputExpression(inputExpression);
		}
	}

	protected boolean isAnEnumOnAMultipleValuatedFormField(Data data,Widget widget) {
		return data.getDataType() instanceof EnumType && widget instanceof MultipleValuatedFormField;
	}

	protected boolean isOnInstantiationForm(final Data data) {
		return feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM) //Entry form
				&& pageFlow instanceof Pool  //On a pool
				&& !ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA.equals(data.eContainingFeature()); //Not a pageflow data
	}

	protected void addOutputOperationForData(Widget widget,final WidgetMapping mapping) {
		Data data = (Data)mapping.getModelElement();
		if(!isDataPageFlowTransient(data) && hasOutputOperation(widget)) {
			widget.setAction(createDataOutputOperation(widget, data)) ;
		}
	}

	protected boolean hasOutputOperation(Widget widget) {
		return !(widget instanceof Info) && !(widget instanceof Table) ;
	}

	protected void addOutputOperationForDocument(final WidgetMapping mapping,
			Widget widget) {
		final Document doc = (Document) mapping.getModelElement();
		widget.setAction(createDocumentOutputOperation(widget, doc)) ;
	}

	protected void addInputExpressionForDocument(final Document key,
			FileWidget widget) {
		Expression inputExpression = ExpressionFactory.eINSTANCE.createExpression();
		inputExpression.setContent(key.getName()) ;
		inputExpression.setName(key.getName()) ;
		inputExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(key));
		inputExpression.setReturnType(String.class.getName()) ;
		inputExpression.setType(ExpressionConstants.DOCUMENT_REF_TYPE);
		if(!(feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM) && pageFlow instanceof Pool)){ //Do not set input expression if we are in an instantiation form
			widget.setInputExpression(inputExpression);
		}

		widget.setDocument((Document) key);
	}

	protected void addSubmitButton(Form myForm) {
		Widget submitButton = FormFactory.eINSTANCE.createSubmitFormButton();
		String submitButtonName = NamingUtils.getInstance(pageFlow).generateName(submitButton, pageFlow);
		submitButton.setName(NamingUtils.toJavaIdentifier(submitButtonName, true));
		submitButton.setDisplayLabel(createLabelExpression(submitButtonName));
		submitButton.setInjectWidgetScript(createInsertWidgetIfScript());
		WidgetLayoutInfo wLayout = FormFactory.eINSTANCE.createWidgetLayoutInfo();
		int numberOfLines = getLineIndex(new WidgetContainer(myForm));
		wLayout.setLine(Math.max(((List<?>) pageFlow.eGet(feature)).size() != 0 ? numberOfLines-1 : numberOfLines, MIN_BUTTON_LINE));
		if (((List<?>) pageFlow.eGet(feature)).size() != 0) {
			wLayout.setColumn(1);
		}
		submitButton.setWidgetLayoutInfo(wLayout);
		myForm.getWidgets().add(submitButton);
	}

	protected void addPreviousButton(Form myForm) {
		Widget previousButton = FormFactory.eINSTANCE.createPreviousFormButton();
		String previousName = NamingUtils.getInstance(pageFlow).generateName(previousButton, pageFlow);
		previousButton.setDisplayLabel(createLabelExpression(previousName));
		previousButton.setName(NamingUtils.toJavaIdentifier(previousName, true));
		previousButton.setInjectWidgetScript(createInsertWidgetIfScript());
		WidgetLayoutInfo wLayout = FormFactory.eINSTANCE.createWidgetLayoutInfo();
		wLayout.setLine(Math.max(getLineIndex(new WidgetContainer(myForm)), MIN_BUTTON_LINE));
		previousButton.setWidgetLayoutInfo(wLayout);
		myForm.getWidgets().add(previousButton);
	}

	protected Form createForm() {
		Form myForm;
		/* set the numColumn (which will be the horizontal span for widgets) */
		if(feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM)){
			myForm = FormFactory.eINSTANCE.createForm();

		}else{
			myForm = FormFactory.eINSTANCE.createViewForm();
			// submit button
		}
		myForm.setName(formName);
		myForm.setPageLabel(createLabelExpression(formName));
		myForm.setShowPageLabel(true);
		myForm.setDocumentation(description);
		return myForm;
	}

	protected WidgetLayoutInfo ceateWidgetLayout(int nLine, int nCol, int horizontalSpan,int verticalSpan) {
		WidgetLayoutInfo wLayout = FormFactory.eINSTANCE.createWidgetLayoutInfo();
		wLayout.setLine(nLine);
		wLayout.setColumn(nCol);
		wLayout.setHorizontalSpan(horizontalSpan);
		wLayout.setVerticalSpan(verticalSpan);
		return wLayout;
	}

	protected Operation createDocumentOutputOperation(Widget widget,Document doc) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation() ;

		Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
		assignment.setType(ExpressionConstants.SET_DOCUMENT_OPERATOR) ;
		action.setOperator(assignment) ;

		Expression storageExpression = ExpressionFactory.eINSTANCE.createExpression();
		storageExpression.setContent(doc.getName()) ;
		storageExpression.setName(doc.getName()) ;
		storageExpression.setType(ExpressionConstants.DOCUMENT_REF_TYPE) ;
		storageExpression.setReturnType(String.class.getName()) ;
		storageExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(doc)) ;
		action.setLeftOperand(storageExpression) ;

		Expression actionExpression = ExpressionFactory.eINSTANCE.createExpression();
		actionExpression.setContent(WidgetHelper.FIELD_PREFIX+widget.getName()) ;
		actionExpression.setName(WidgetHelper.FIELD_PREFIX+widget.getName()) ;
		actionExpression.setType(ExpressionConstants.FORM_FIELD_TYPE) ;
		actionExpression.setReturnType(DocumentValue.class.getName()) ;
		actionExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(widget)) ;
		action.setRightOperand(actionExpression) ;
		return action;
	}

	protected Operation createDataOutputOperation(Widget widget, Data data) {
		Operation action = ExpressionFactory.eINSTANCE.createOperation() ;
		Operator assignment = ExpressionFactory.eINSTANCE.createOperator();
		assignment.setType(ExpressionConstants.ASSIGNMENT_OPERATOR) ;
		action.setOperator(assignment) ;
		action.setLeftOperand(createVariableExpression(data)) ;
		action.setRightOperand(createWidgetExpression(widget)) ;
		return action;
	}

	protected Expression createWidgetExpression(Widget widget) {
		Expression actionExpression = ExpressionFactory.eINSTANCE.createExpression();
		actionExpression.setContent(WidgetHelper.FIELD_PREFIX+widget.getName()) ;
		actionExpression.setName(WidgetHelper.FIELD_PREFIX+widget.getName()) ;
		actionExpression.setType(ExpressionConstants.FORM_FIELD_TYPE) ;
		actionExpression.setReturnType(WidgetHelper.getAssociatedReturnType(widget)) ;
		actionExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(widget)) ;
		return actionExpression;
	}


	protected Expression createVariableExpression(Data data) {
		Expression storageExpression = ExpressionFactory.eINSTANCE.createExpression();
		storageExpression.setContent(data.getName()) ;
		storageExpression.setName(data.getName()) ;
		storageExpression.setType(ExpressionConstants.VARIABLE_TYPE) ;
		storageExpression.setReturnType(org.bonitasoft.studio.common.DataUtil.getTechnicalTypeFor(data)) ;
		storageExpression.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(data)) ;
		return storageExpression;
	}

	protected boolean isDataPageFlowTransient(Data data) {
		EReference reference = data.eContainmentFeature();
		if(reference!=null){
			return reference.equals(ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA)
					|| reference.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA)
					|| reference.equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.workspace.AbstractEMFOperation#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return false;//avoid issues when a form was created an en editor is open on it
	}

	protected Expression createInsertWidgetIfScript(){
		Expression exp = org.bonitasoft.studio.common.NamingUtils.generateConstantExpression("");
		exp.setReturnType(Boolean.class.getName());
		exp.setReturnTypeFixed(true);
		return exp ;	
	}

	protected String computeWidgetId(String key, Widget widget){
		PageFlow pf = (PageFlow)pageFlow;
		if(widget.eContainer() instanceof Group){
			key = ((Element) widget.eContainer()).getName()+"_"+key;
		}
		if (pf !=null){
			int number = NamingUtils.getMaxElements((Element) pageFlow, key);
			number++;
			key +=number;		
		}
		return key;
	}

	protected List<? extends WidgetMapping> getWidgesMappings() {
		return widgesMappings;
	}
}
