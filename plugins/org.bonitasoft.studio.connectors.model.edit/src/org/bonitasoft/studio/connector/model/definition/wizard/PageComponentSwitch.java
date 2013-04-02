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
package org.bonitasoft.studio.connector.model.definition.wizard;

import java.util.ArrayList;
import java.util.Collections;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.Checkbox;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Group;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.List;
import org.bonitasoft.studio.connector.model.definition.Orientation;
import org.bonitasoft.studio.connector.model.definition.Password;
import org.bonitasoft.studio.connector.model.definition.RadioGroup;
import org.bonitasoft.studio.connector.model.definition.ScriptEditor;
import org.bonitasoft.studio.connector.model.definition.Select;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionSwitch;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.viewer.CheckBoxExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.expression.editor.viewer.IExpressionModeListener;
import org.bonitasoft.studio.expression.editor.viewer.PatternExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.scripting.extensions.IScriptLanguageProvider;
import org.bonitasoft.studio.scripting.extensions.ScriptLanguageService;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Section;



/**
 * @author Romain Bioteau
 *
 */
public class PageComponentSwitch extends ConnectorDefinitionSwitch<Component> {


	private final Composite parent;
	private final EMFDataBindingContext context;
	private final ConnectorConfiguration connectorConfiguration;
	private final EObject container;
	private final ConnectorDefinition definition;
	private final java.util.List<Section> sections = new ArrayList<Section>() ;
	private final DefinitionResourceProvider messageProvider;
	private final ViewerFilter connectorExpressionContentTypeFilter;
	private IWizardContainer iWizardContainer;

	public PageComponentSwitch(IWizardContainer iWizardContainer, Composite parent,EObject container,ConnectorDefinition definition,ConnectorConfiguration connectorConfiguration, EMFDataBindingContext context,DefinitionResourceProvider messageProvider,ViewerFilter connectorExpressionContentTypeFilter) {
		this.parent = parent ;
		this.context = context ;
		this.connectorConfiguration = connectorConfiguration ;
		this.container = container ;
		this.definition = definition ;
		this.messageProvider = messageProvider ;
		this.connectorExpressionContentTypeFilter = connectorExpressionContentTypeFilter;
		this.iWizardContainer = iWizardContainer;
	}

	@Override
	public Component caseArray(Array object) {
		createArrayControl(parent,object) ;
		return object;
	}

	@Override
	public Component caseCheckbox(Checkbox object) {
		createCheckboxControl(parent,object) ;
		return object;
	}

	@Override
	public Component caseScriptEditor(ScriptEditor object) {
		createScriptEditorControl(parent,object) ;
		return object;
	}

	@Override
	public Component caseGroup(Group object) {
		final Section section = createGroupControl(parent,object) ;
		final Composite client = new Composite(section, SWT.NONE) ;
		client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		client.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(5, 2).create()) ;
		final PageComponentSwitch groupSwitch = new PageComponentSwitch(iWizardContainer,client,container,definition,connectorConfiguration,context,messageProvider,connectorExpressionContentTypeFilter) ;
		for(Component component : object.getWidget()){
			groupSwitch.doSwitch(component) ;
		}

		section.setClient(client) ;
		java.util.List<Section> sectionsToExpand = groupSwitch.getSectionsToExpand() ;
		sections.addAll(sectionsToExpand) ;
		if(!object.isOptional() && object.isSetOptional()){
			sections.add(section) ;
		}
		section.addExpansionListener(new IExpansionListener() {

			@Override
			public void expansionStateChanging(ExpansionEvent event) {}

			@Override
			public void expansionStateChanged(ExpansionEvent event) {
				Shell shell = section.getShell();
				Point defaultSize = shell.getSize() ;
				Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true) ;
				shell.setSize(defaultSize.x, size.y) ;
				shell.layout(true, true) ;
			}
		}) ;

		return object;
	}


	public java.util.List<Section> getSectionsToExpand() {
		return sections  ;
	}

	@Override
	public Component caseList(List object) {
		createListControl(parent,object) ;
		return object;
	}


	@Override
	public Component casePassword(Password object) {
		createPasswordControl(parent,object) ;
		return object;
	}


	@Override
	public Component caseSelect(Select object) {
		createSelectControl(parent,object) ;
		return object;
	}


	@Override
	public Component caseText(Text object) {
		createTextControl(parent,object) ;
		return object;
	}


	@Override
	public Component caseRadioGroup(RadioGroup object) {
		createRadioGroupControl(parent,object) ;
		return object;
	}


	@Override
	public Component caseTextArea(TextArea object) {
		createTextAreaControl(parent,object) ;
		return object;
	}

	protected void createRadioGroupControl(Composite composite, RadioGroup object) {
		final Input input = getConnectorInput(object.getInputName()) ;
		final ConnectorParameter parameter = getConnectorParameter(object.getInputName(),object,input) ;

		if(parameter != null){
			Label label = null ;
			if(object.getOrientation() == Orientation.HORIZONTAL){
				label = createFieldLabel(composite,SWT.CENTER,object.getId(),input.isMandatory()) ;
			}else{
				label = createFieldLabel(composite,SWT.TOP,object.getId(),input.isMandatory()) ;
			}
			String desc = messageProvider.getFieldDescription(definition, object.getId()) ;
			if(desc != null && !desc.isEmpty()){
				label.setToolTipText(desc) ;
			}

			final Composite radioCompoiste = new Composite(composite, SWT.NONE) ;
			radioCompoiste.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
			if(object.getOrientation() == Orientation.HORIZONTAL){
				radioCompoiste.setLayout(GridLayoutFactory.fillDefaults().numColumns(object.getChoices().size()).create()) ;
			}else{
				radioCompoiste.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
			}
			String defaultSelection = input.getDefaultValue() ;

			((Expression) parameter.getExpression()).setType(ExpressionConstants.CONSTANT_TYPE) ;
			((Expression) parameter.getExpression()).setReturnType(input.getType()) ;
			String content = ((Expression) parameter.getExpression()).getContent() ;
			for(final String choice : object.getChoices()){
				final Button radioButton = new Button(radioCompoiste, SWT.RADIO) ;
				radioButton.setText(choice) ;
				radioButton.setLayoutData(GridDataFactory.fillDefaults().create()) ;
				radioButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if(radioButton.getSelection()){
							((Expression) parameter.getExpression()).setContent(choice) ;
							((Expression) parameter.getExpression()).setName(choice) ;
						}
					}
				}) ;
				if(content == null && defaultSelection != null && defaultSelection.equals(choice)){
					radioButton.setSelection(true) ;
				}else if(content != null && content.equals(choice)){
					radioButton.setSelection(true) ;
				}

			}
			if(defaultSelection == null){
				Button button = (Button) radioCompoiste.getChildren()[0] ;
				button.setSelection(true) ;
			}
		}

	}

	protected void createTextAreaControl(Composite composite, TextArea object) {
		final Input input = getConnectorInput(object.getInputName()) ;
		final ConnectorParameter parameter = getConnectorParameter(object.getInputName(),object,input) ;

		if(parameter != null){
			createFieldLabel(composite,SWT.TOP,object.getId(),input.isMandatory()) ;

			final PatternExpressionViewer viewer =  new PatternExpressionViewer(composite,SWT.NONE);
			viewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(400, SWT.DEFAULT).create()) ;
			viewer.addFilter(connectorExpressionContentTypeFilter);

			Expression exp = (Expression) parameter.getExpression();
//			if(!exp.getType().equals(ExpressionConstants.PATTERN_TYPE)){
//				exp.setType(ExpressionConstants.PATTERN_TYPE);
//			}
			String desc = messageProvider.getFieldDescription(definition, object.getId()) ;
			if(desc != null && !desc.isEmpty()){
				viewer.setHint(desc) ;
			}
			viewer.setContextInput(container);
		
			UpdateValueStrategy startegy = new UpdateValueStrategy();
			if(input.isMandatory()){
				startegy.setAfterConvertValidator(new EmptyInputValidator(getLabel(object.getId())));
			}
			viewer.setEMFBindingContext(context);
			if(input.isMandatory()){
				viewer.setMandatoryField(getLabel(object.getId())) ;
			}
		
			viewer.setExpression(exp) ;
		}
	}

	protected void createCheckboxControl(Composite composite, Checkbox object) {
		final Input input = getConnectorInput(object.getInputName()) ;
		final ConnectorParameter parameter = getConnectorParameter(object.getInputName(),object,input) ;
		if(parameter != null){
			createFieldLabel(composite,SWT.CENTER,object.getId(),input.isMandatory()) ;
			final CheckBoxExpressionViewer viewer = new CheckBoxExpressionViewer(composite, SWT.BORDER, ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION);
			viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
			viewer.setContext(container);
			if(input.isMandatory()){
				viewer.setMandatoryField(getLabel(object.getId()),context) ;
			}
			viewer.addFilter(connectorExpressionContentTypeFilter) ;
			viewer.setInput(parameter) ;
			String desc = messageProvider.getFieldDescription(definition, object.getId()) ;
			if(desc != null && !desc.isEmpty()){
				viewer.setMessage(desc,IStatus.INFO) ;
			}
			viewer.setSelection(new StructuredSelection(parameter.getExpression()));
			context.bindValue(ViewersObservables.observeSingleSelection(viewer), EMFObservables.observeValue(parameter, ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION));
		}
	}

	private String getLabel(String inputName) {
		String label =  messageProvider.getFieldLabel(definition, inputName) ;
		if(label == null){
			label = "" ;
		}

		return label ;
	}

	protected void createTextControl(Composite composite, final Text object) {
		final Input input = getConnectorInput(object.getInputName()) ;
		if(input != null){
			final ConnectorParameter parameter = getConnectorParameter(object.getInputName(),object,input) ;
			if(parameter != null){
				createFieldLabel(composite,SWT.CENTER,object.getId(),input.isMandatory()) ;
				final ExpressionViewer viewer = new ExpressionViewer(composite,SWT.BORDER, ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION) ;
				viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
				viewer.setContext(container);
				if(input.isMandatory()){
					viewer.setMandatoryField(getLabel(object.getId()),context) ;
				}
				viewer.addFilter(connectorExpressionContentTypeFilter) ;
				viewer.setInput(parameter) ;
				String desc = messageProvider.getFieldDescription(definition, object.getId()) ;
				if(desc != null && !desc.isEmpty()){
					viewer.setMessage(desc, IStatus.INFO) ;
				}
				context.bindValue(ViewersObservables.observeSingleSelection(viewer), EMFObservables.observeValue(parameter, ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION));
			}
		} else {
			//Should we create a label to warn final user?
			BonitaStudioLog.log("WARNING: No input found with name "+object.getInputName());
		}
	}

	protected void createScriptEditorControl(Composite composite, ScriptEditor object) {
		final Input input = getConnectorInput(object.getInputName()) ;
		final ConnectorParameter parameter = getConnectorParameter(object.getInputName(),object,input) ;

		if(parameter != null){
			final Label label = createFieldLabel(composite,SWT.CENTER,object.getId(),input.isMandatory()) ;
			label.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());
			IScriptLanguageProvider provider = ScriptLanguageService.getInstance().getScriptLanguageProvider(object.getInterpreter()) ;
			if(provider == null){
				throw new RuntimeException("No script editor available for "+object.getInterpreter()+" interpreter");
			}
			Expression inputExpression = (Expression) parameter.getExpression();
			final IExpressionEditor groovyExpressionEditor = provider.getExpressionEditor() ;
			final Composite editorComposite = new Composite(composite, SWT.NONE);
			editorComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).hint(300, 220).create());
			editorComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
			Control editor = groovyExpressionEditor.createExpressionEditor(editorComposite);
			editor.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
			groovyExpressionEditor.bindExpression(context, container, inputExpression, new ViewerFilter[]{connectorExpressionContentTypeFilter});
			if(input.isMandatory()){
				final EmptyInputValidator validator =  new EmptyInputValidator(getLabel(input.getName()));
				final IObservableValue textObservable = (IObservableValue) groovyExpressionEditor.getContentObservable();
				context.addValidationStatusProvider(new MultiValidator() {

					@Override
					protected IStatus validate() {
						return validator.validate(textObservable.getValue());
					}
				});
			}
		}

	}

	protected void createArrayControl(Composite composite, Array object) {
		final Input input = getConnectorInput(object.getInputName()) ;
		final ConnectorParameter parameter = getConnectorParameter(object.getInputName(),object,input) ;

		if(parameter != null){
			createFieldLabel(composite,SWT.TOP,object.getId(),input.isMandatory()) ;
			final ExpressionCollectionViewer viewer = new ExpressionCollectionViewer(composite,0,object.isFixedRows(),object.getCols().intValue(),object.isFixedCols(),object.getColsCaption(),true,false) ;
			viewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
			for(int i =0 ; i< object.getCols().intValue() ;i++){
				viewer.addFilter(connectorExpressionContentTypeFilter);
			}
			viewer.setInput(parameter) ;
			viewer.setSelection(parameter.getExpression()) ;
			viewer.addExpressionModeListener(new IExpressionModeListener() {

				@Override
				public void useTable() {
					AbstractExpression expression = parameter.getExpression() ;
					if(!(expression instanceof TableExpression)){
						expression = ExpressionFactory.eINSTANCE.createTableExpression() ;
						parameter.setExpression(expression) ;
					}
					viewer.setSelection(expression) ;
				}

				@Override
				public void useSimpleExpression() {
					AbstractExpression expression = parameter.getExpression() ;
					if(!(expression instanceof Expression)){
						expression = ExpressionFactory.eINSTANCE.createExpression() ;
						parameter.setExpression(expression) ;
					}
					viewer.setSelection(expression) ;
				}
			}) ;
			if(input.isMandatory()){
				final IObservableValue inputObservable = (IObservableValue) ViewersObservables.observeInput(viewer.getViewer());
				final IObservableValue tableValue = EMFObservables.observeValue(parameter.getExpression(), ExpressionPackage.Literals.TABLE_EXPRESSION__EXPRESSIONS);
				context.addValidationStatusProvider(new MultiValidator() {

					@Override
					protected IStatus validate() {
						TableExpression value = (TableExpression) inputObservable.getValue();
						tableValue.getValue();
						if(value.getExpressions().isEmpty()){
							return ValidationStatus.error(Messages.bind(Messages.emptyTable,getLabel(input.getName())));
						}
						return ValidationStatus.ok();
					}
				});
			}
		}
	}

	protected Section createGroupControl(Composite composite, Group object) {
		final Section groupSection = new Section(composite,Section.NO_TITLE_FOCUS_BOX | Section.TWISTIE | Section.CLIENT_INDENT) ;
		groupSection.setText(getLabel(object.getId())) ;
		groupSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create()) ;
		return groupSection;
	}

	protected void createListControl(Composite composite, List object) {
		final Input input = getConnectorInput(object.getInputName()) ;
		final ConnectorParameter parameter = getConnectorParameter(object.getInputName(),object,input) ;

		if(parameter != null){
			createFieldLabel(composite,SWT.TOP,object.getId(),input.isMandatory()) ;
			@SuppressWarnings("unchecked")
			final ExpressionCollectionViewer viewer = new ExpressionCollectionViewer(composite,0,false,1,true,Collections.EMPTY_LIST,true,false) ;
			viewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
			viewer.addFilter(connectorExpressionContentTypeFilter);
			viewer.setInput(parameter) ;
			viewer.setSelection(parameter.getExpression()) ;
			viewer.addExpressionModeListener(new IExpressionModeListener() {

				@Override
				public void useTable() {
					AbstractExpression expression = parameter.getExpression() ;
					if(!(expression instanceof TableExpression)){
						expression = ExpressionFactory.eINSTANCE.createListExpression() ;
						parameter.setExpression(expression) ;
					}
					viewer.setSelection(expression) ;
				}

				@Override
				public void useSimpleExpression() {
					AbstractExpression expression = parameter.getExpression() ;
					if(!(expression instanceof Expression)){
						expression = ExpressionFactory.eINSTANCE.createExpression() ;
						parameter.setExpression(expression) ;
					}
					viewer.setSelection(expression) ;
				}
			}) ;
			if(input.isMandatory()){
				final IObservableValue inputObservable = (IObservableValue) ViewersObservables.observeInput(viewer.getViewer());
				final IObservableValue listValue = EMFObservables.observeValue(parameter.getExpression(), ExpressionPackage.Literals.LIST_EXPRESSION__EXPRESSIONS);
				context.addValidationStatusProvider(new MultiValidator() {

					@Override
					protected IStatus validate() {
						ListExpression value = (ListExpression) inputObservable.getValue();
						listValue.getValue();
						if(value.getExpressions().isEmpty()){
							return ValidationStatus.error(Messages.bind(Messages.emptyList,getLabel(input.getName())));
						}
						return ValidationStatus.ok();
					}
				});
			}
		}

	}

	protected void createPasswordControl(Composite composite, Password object) {
		final Input input = getConnectorInput(object.getInputName()) ;
		final ConnectorParameter parameter = getConnectorParameter(object.getInputName(),object,input) ;

		if(parameter != null){
			createFieldLabel(composite,SWT.CENTER,object.getId(),input.isMandatory()) ;
			final ExpressionViewer viewer = new ExpressionViewer(composite,SWT.BORDER | SWT.PASSWORD, ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION) ;
			viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
			viewer.setContext(container);
			if(input.isMandatory()){
				viewer.setMandatoryField(getLabel(object.getId()),context) ;
			}
			viewer.addFilter(connectorExpressionContentTypeFilter) ;
			viewer.setInput(parameter) ;
			String desc = messageProvider.getFieldDescription(definition, object.getId()) ;
			if(desc != null && !desc.isEmpty()){
				viewer.setMessage(desc, IStatus.INFO) ;
			}
			context.bindValue(ViewersObservables.observeSingleSelection(viewer), EMFObservables.observeValue(parameter, ConnectorConfigurationPackage.Literals.CONNECTOR_PARAMETER__EXPRESSION));
		}
	}

	protected void createSelectControl(Composite composite, Select object) {
		final Input input = getConnectorInput(object.getInputName()) ;
		final ConnectorParameter parameter = getConnectorParameter(object.getInputName(),object,input) ;

		if(parameter != null){
			createFieldLabel(composite,SWT.CENTER,object.getId(),input.isMandatory()) ;
			final Combo combo = new Combo(composite,SWT.READ_ONLY | SWT.BORDER) ;
			combo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

			final AbstractExpression inputExpression = parameter.getExpression();
			for(String item : object.getItems()){
				combo.add(item) ;
			}
			context.bindValue(SWTObservables.observeText(combo), EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__NAME)) ;
			context.bindValue(SWTObservables.observeText(combo), EMFObservables.observeValue(inputExpression, ExpressionPackage.Literals.EXPRESSION__CONTENT)) ;

			if(combo.getText() == null || combo.getText().isEmpty()){
				String defaultValue = input.getDefaultValue() ;
				for(String item : combo.getItems()){
					if(defaultValue != null && item.equals(defaultValue) ){
						combo.setText(defaultValue) ;
					}
				}
				if(defaultValue == null){
					((Expression) inputExpression).setName(combo.getItem(0));
					((Expression) inputExpression).setContent(combo.getItem(0));
				}else if(combo.getText() == null || combo.getText().isEmpty()){
					((Expression) inputExpression).setName(combo.getItem(0));
					((Expression) inputExpression).setContent(combo.getItem(0));
				}
			}

			String desc = messageProvider.getFieldDescription(definition, object.getId()) ;
			if(desc != null && !desc.isEmpty()){
				combo.setToolTipText(desc) ;
			}

			((Expression) inputExpression).setType(ExpressionConstants.CONSTANT_TYPE) ;
			((Expression) inputExpression).setReturnType(input.getType()) ;

		}
	}

	private Input getConnectorInput(String inputName) {
		for(Input input : definition.getInput()){
			if(input.getName().equals(inputName)){
				return input ;
			}
		}
		return null;
	}

	protected Label createFieldLabel(Composite composite,int verticalAlignment, String id, boolean isMandatory) {
		final Label fieldLabel = new Label(composite, SWT.NONE) ;
		if(id != null){
			String label = getLabel(id) ;
			if(isMandatory){
				label = label + " *" ;
			}
			fieldLabel.setText(label) ;
		}
		fieldLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, verticalAlignment).create()) ;
		return fieldLabel ;
	}

	private ConnectorParameter getConnectorParameter(String inputName, WidgetComponent object, Input input) {
		for(ConnectorParameter param : connectorConfiguration.getParameters()){
			if(param.getKey().equals(inputName)){
				if(param.getExpression() == null){
					param.setExpression(createExpression(object,input)) ;
				}else{
					if(param.getExpression() instanceof Expression){
						Expression exp = (Expression) param.getExpression();
						if(!input.getType().equals(exp.getReturnType())){
							exp.setReturnType(input.getType());
						}
					}

				}
				return param ;
			}
		}

		final ConnectorParameter parameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter() ;
		parameter.setKey(inputName) ;
		parameter.setExpression(createExpression(object,input)) ;
		connectorConfiguration.getParameters().add(parameter) ;

		return parameter ;
	}


	protected AbstractExpression createExpression(WidgetComponent widget,Input input) {
		String inputClassName = input.getType() ;
		if( widget instanceof Array){
			final TableExpression expression = ExpressionFactory.eINSTANCE.createTableExpression() ;
			return expression ;
		}else if( widget instanceof org.bonitasoft.studio.connector.model.definition.List){
			final ListExpression expression = ExpressionFactory.eINSTANCE.createListExpression() ;
			return expression ;
		}else{
			final Expression expression = ExpressionFactory.eINSTANCE.createExpression() ;
			expression.setReturnType(inputClassName) ;
			expression.setReturnTypeFixed(true) ;
			expression.setType(ExpressionConstants.CONSTANT_TYPE) ;
			expression.setName(input.getDefaultValue()) ;
			expression.setContent(input.getDefaultValue()) ;
			if(widget instanceof ScriptEditor){
				expression.setName(input.getName()+"Script") ;
				expression.setType(ExpressionConstants.SCRIPT_TYPE);
				expression.setInterpreter(((ScriptEditor) widget).getInterpreter());
			}else if(widget instanceof TextArea){
				expression.setType(ExpressionConstants.PATTERN_TYPE);
			}
			return expression ;
		}
	}
}
