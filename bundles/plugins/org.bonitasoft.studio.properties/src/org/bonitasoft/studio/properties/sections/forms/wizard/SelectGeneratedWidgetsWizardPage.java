/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms.wizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * This Wizard page is the second page of the wizard which allow to select
 * multiples variables to generate a default Form
 * 
 * @author Baptiste Mesta
 * 
 */
public class SelectGeneratedWidgetsWizardPage extends WizardSelectionPage {

	private HashMap<Button, Widget> checkBoxes = new HashMap<Button, Widget>();


	private HashMap<Document, Button> documentToCheckboxes = new HashMap<Document, Button>();
	private HashMap<Data, Button> dataToCheckBoxes = new HashMap<Data, Button>();
	private List<Data> data;
	private final Element pageFlow;
	private final EStructuralFeature feature;
	private List<Document> documents = new ArrayList<Document>();

	private DataBindingContext databindingContext;
	private String formName;
	private String formDescription;

	public SelectGeneratedWidgetsWizardPage(Element pageFlow, EStructuralFeature feature) {
		super(SelectGeneratedWidgetsWizardPage.class.getName());
		this.pageFlow = pageFlow;
		this.feature = feature;
		setTitle(Messages.createForm_title);
		setDescription(Messages.createForm_desc);
		setFormName(generateDefaultFormName());
		data = getData();
		final AbstractProcess parentProcess = ModelHelper.getParentProcess(pageFlow);
		if(parentProcess instanceof Pool){
			documents = ((Pool) parentProcess).getDocuments();
		}
	}


	@Override
	public void createControl(Composite parent) {
		databindingContext = new DataBindingContext();
		// main composite
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		// ----- TOP
		Composite topComposite = new Composite(composite, SWT.NONE);
		topComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(10, 5).create());
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

		Label nameLabel = new Label(topComposite, SWT.LEFT);
		nameLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
		nameLabel.setText(Messages.GeneralSection_Name);

		final Text nameText = new Text(topComposite, SWT.BORDER);

		nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		UpdateValueStrategy stratetgy = new UpdateValueStrategy();
		stratetgy.setBeforeSetValidator(new InputLengthValidator(Messages.name, 50)) ;
		ISWTObservableValue observeText = SWTObservables.observeText(nameText, SWT.Modify);
		observeText.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				getContainer().updateButtons();
			}
		});
		databindingContext.bindValue(observeText, PojoProperties.value("formName").observe(this),stratetgy,null);

		// ------------ Description
		Label descLabel = new Label(topComposite, SWT.LEFT);
		descLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.TOP).create());
		descLabel.setText(Messages.GeneralSection_Description);

		final Text descriptionText = new Text(topComposite, SWT.MULTI + SWT.BORDER);
		descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).hint(SWT.DEFAULT, 45).create());
		databindingContext.bindValue(SWTObservables.observeText(descriptionText, SWT.Modify), PojoProperties.value("formDescription").observe(this));


		if (hasWidgetToGenerated()) {
			Group parentGroup = createWidgetGroup(composite);

			createSelectionComposite(parentGroup);

			ScrolledComposite scrolledComposite = createScrolledComposite(parentGroup);

			Composite scrollable = new Composite(scrolledComposite, SWT.NONE);
			scrollable.setLayout(new GridLayout(2, false));
			scrollable.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

			for (Data currentData : data) {
				createChoice(scrollable, currentData);
			}

			for (Document document : documents) {
				createDocumentChoice(scrollable, document);
			}

			selectAll(true);

			/*Add settings of the scrolled composite*/
			scrolledComposite.setMinSize(scrollable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			scrolledComposite.setAlwaysShowScrollBars(false);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			scrolledComposite.setContent(scrollable);
		} else {
			Label title = new Label(composite, SWT.CENTER);
			title.setText(Messages.createForm_noData);
		}

		WizardPageSupport.create(this, databindingContext);
		setControl(composite);
	}

	protected ScrolledComposite createScrolledComposite(Group parentGroup) {
		ScrolledComposite scrolledCompo = new ScrolledComposite(parentGroup,SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledCompo.setLayout(new FillLayout());
		scrolledCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 150).create());
		return scrolledCompo;
	}

	protected Group createWidgetGroup(Composite composite) {
		Group datasGrp = new Group(composite, SWT.NONE);
		datasGrp.setText(Messages.FormsSection_wizardVarsGroup_Title);
		datasGrp.setToolTipText(Messages.FormsSection_wizardVarsGroup_Tooltip);
		datasGrp.setLayout(new GridLayout(1, false));
		datasGrp.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		datasGrp.setSize(datasGrp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		return datasGrp;
	}

	protected void createSelectionComposite(Group datasGrp) {
		Composite selectionComposite = new Composite(datasGrp, SWT.NONE);
		selectionComposite.setLayout(new GridLayout(2, false));
		selectionComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));

		createSelectAllButton(selectionComposite);
		createUnselectAllButton(selectionComposite);
	}

	protected void createUnselectAllButton(Composite selectionComposite) {
		Button unselectButton = new Button(selectionComposite, SWT.FLAT);
		unselectButton.setText(Messages.unselectAll);
		unselectButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				selectAll(false);
			}
		});
	}

	protected void createSelectAllButton(Composite selectionComposite) {
		Button selectAllButton = new Button(selectionComposite, SWT.FLAT);
		selectAllButton.setText(Messages.selectAll);
		selectAllButton.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				selectAll(true);
			}
		});
	}

	protected void selectAll(boolean select) {
		for (Button button : checkBoxes.keySet()) {
			button.setSelection(select);
		}
	}

	protected void createDocumentChoice(Composite scrollable, Document document) {
		Button checkbox = new Button(scrollable, SWT.CHECK);
		checkbox.setText(document.getName());
		documentToCheckboxes.put(document, checkbox);
		Label label = new Label(scrollable, SWT.LEFT);
		StringBuilder labelText = new StringBuilder();
		labelText.append("-> ");
		labelText.append(Messages.FormsSection_widgetTypeFile);
		label.setText(labelText.toString());
		checkBoxes.put(checkbox, FormFactory.eINSTANCE.createFileWidget());
	}

	protected boolean hasWidgetToGenerated() {
		return !data.isEmpty() || !documents.isEmpty();
	}

	protected void createChoice(Composite parent, Data currentData) {
		Button checkbox = new Button(parent, SWT.CHECK);
		checkbox.setText(currentData.getName());
		mapDataAndCheckbox(currentData, checkbox);
		DataType dataType = currentData.getDataType();

		Label label = new Label(parent, SWT.LEFT);

		StringBuilder labelText = new StringBuilder();
		labelText.append("-> ");

		if (currentData.isMultiple()) {
			labelText.append(Messages.multipleWidget);
		}
		labelText.append(getLabelForDataType(dataType));
		mapCheckboxAndWidgetType(checkbox, getWidgetTypeFor(dataType,currentData));
		label.setText(labelText.toString());
	}

	protected Widget getWidgetTypeFor(DataType dataType,final Data currentData) {
		if(currentData.isMultiple()){
			if(dataType instanceof BooleanType){
				return FormFactory.eINSTANCE.createCheckBoxMultipleFormField();
			}
			return FormFactory.eINSTANCE.createListFormField();
		}else{
			return new ProcessSwitch<Widget>(){

				public Widget caseStringType(org.bonitasoft.studio.model.process.StringType object) {
					return FormFactory.eINSTANCE.createTextFormField();
				}

				public Widget caseIntegerType(org.bonitasoft.studio.model.process.IntegerType object) {
					return FormFactory.eINSTANCE.createTextFormField();
				}

				public Widget caseDateType(org.bonitasoft.studio.model.process.DateType object) {
					return FormFactory.eINSTANCE.createDateFormField();
				}

				public Widget caseFloatType(org.bonitasoft.studio.model.process.FloatType object) {
					return FormFactory.eINSTANCE.createTextFormField();
				}

				public Widget caseLongType(org.bonitasoft.studio.model.process.LongType object) {
					return FormFactory.eINSTANCE.createTextFormField();
				}

				public Widget caseBooleanType(org.bonitasoft.studio.model.process.BooleanType object) {
					return FormFactory.eINSTANCE.createCheckBoxSingleFormField();
				}

				public Widget caseEnumType(org.bonitasoft.studio.model.process.EnumType object) {
					return FormFactory.eINSTANCE.createRadioFormField();
				}

				public Widget defaultCase(org.eclipse.emf.ecore.EObject object) {
					return FormFactory.eINSTANCE.createTextFormField();
				}

			}.doSwitch(dataType);
		}
	}

	protected String getLabelForDataType(DataType type) {
		return new ProcessSwitch<String>(){

			public String caseStringType(org.bonitasoft.studio.model.process.StringType object) {
				return Messages.FormsSection_widgetTypeText;
			}

			public String caseIntegerType(org.bonitasoft.studio.model.process.IntegerType object) {
				return Messages.FormsSection_widgetTypeText;
			}

			public String caseDateType(org.bonitasoft.studio.model.process.DateType object) {
				return Messages.FormsSection_widgetTypeDate;
			}

			public String caseFloatType(org.bonitasoft.studio.model.process.FloatType object) {
				return Messages.FormsSection_widgetTypeText;
			}

			public String caseLongType(org.bonitasoft.studio.model.process.LongType object) {
				return Messages.FormsSection_widgetTypeText;
			}

			public String caseBooleanType(org.bonitasoft.studio.model.process.BooleanType object) {
				return Messages.FormsSection_widgetTypeCheckBox;
			}

			public String caseEnumType(org.bonitasoft.studio.model.process.EnumType object) {
				return Messages.FormsSection_widgetTypeRadio;
			}

			@Override
			public String defaultCase(EObject object) {
				return Messages.FormsSection_widgetTypeText;
			}

		}.doSwitch(type);
	}

	protected void mapCheckboxAndWidgetType(Button checkbox, Widget widgetType) {
		checkBoxes.put(checkbox,widgetType);
	}

	protected void mapDataAndCheckbox(Data currentData, Button checkbox) {
		dataToCheckBoxes.put(currentData, checkbox);
	}

	protected String generateDefaultFormName() {
		String baseName = pageFlow.getName();
		int i = ((List<?>) pageFlow.eGet(feature)).size();
		for (Iterator<?> iterator = ((List<?>) pageFlow.eGet(feature)).iterator(); iterator.hasNext();) {
			Form form = (Form) iterator.next();
			if(! form.getName().equals(baseName+i)){
				return baseName+(i<=0?"":"_"+i);
			}

		}
		return baseName+(i<=0?"":"_"+i);
	}


	protected List<Data> getData() {
		List<Data> data = new ArrayList<Data>();
		if(pageFlow instanceof PageFlow){
			List<Data> allData = ModelHelper.getAccessibleDataInFormsWithNoRestriction(pageFlow, feature);
			for (Data currentData : allData) {
				EClass eClassData = currentData.getDataType().eClass();
				if(!ProcessPackage.eINSTANCE.getJavaType().isSuperTypeOf(eClassData)
						&& !ProcessPackage.eINSTANCE.getXMLType().isSuperTypeOf(eClassData)) {
					data.add(currentData);
				}
			}
		}

		Collections.sort(data, new Comparator<Data>() {
			public int compare(Data d1, Data d2) {
				return d1.getName().compareTo(d2.getName());
			}
		});

		return data;
	}


	public Map<EObject, Widget> getWidgetsToGenerate() {
		Map<EObject, Widget> result = new HashMap<EObject, Widget>();
		for (Data data : getData()) {
			Button checkbox = dataToCheckBoxes.get(data);
			if (checkbox != null && checkbox.getSelection()) {
				result.put(data, getWidgetForCheckBox(checkbox));		
			}
		}
		for(Document document : documents){
			Button checkbox = documentToCheckboxes.get(document);
			if (checkbox != null && checkbox.getSelection()) {// check
				result.put(document,getWidgetForCheckBox(checkbox));
			}
		}
		return result;
	}


	protected Widget getWidgetForCheckBox(Button checkBox) {
		return checkBoxes.get(checkBox);
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String name) {
		this.formName = name;
	}

	public String getFormDescription() {
		return formDescription;
	}

	public void setFormDescription(String description) {
		this.formDescription = description;
	}



}
