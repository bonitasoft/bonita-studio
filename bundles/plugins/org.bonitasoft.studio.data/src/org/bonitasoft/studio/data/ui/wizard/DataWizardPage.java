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
package org.bonitasoft.studio.data.ui.wizard;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IWidgetContribtution;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.data.DataPlugin;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.data.ui.dialog.EnumDataTypeDialog;
import org.bonitasoft.studio.data.ui.wizard.provider.BooleanExpressionNatureProvider;
import org.bonitasoft.studio.data.ui.wizard.provider.NowDateExpressionNatureProvider;
import org.bonitasoft.studio.data.ui.wizard.provider.OptionsExpressionNatureProvider;
import org.bonitasoft.studio.data.util.DataUtil;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.ExpressionContentProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.BusinessObjectType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.DateType;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.FloatType;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StringType;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.model.process.XMLType;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.xml.repository.XSDFileStore;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.core.search.JavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.util.ContentBuilder;
import org.eclipse.wst.xml.ui.internal.wizards.NewXMLGenerator;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

/**
 * @author Romain Bioteau
 */
public class DataWizardPage extends WizardPage implements IBonitaVariableContext{

	private static final String GENERATE_DATA_CONTRIBUTION_ID = "org.bonitasoft.studio.propertiies.generateData";

	private Data data;
	private final EObject container;
	private EMFDataBindingContext emfDatabindingContext;
	private final Set<EStructuralFeature> featureToCheckForUniqueID;
	private final boolean allowXML;
	private final boolean allowEnum;
	private final boolean showIsTransient;
	private final boolean showAutoGenerateform;
	private ExpressionViewer defaultValueViewer;
	private Section moreSection;
	private Text nameText;
	private Text descriptionText;
	private Button generateDataCheckbox;
	private ComboViewer typeCombo;
	private Text classText;
	private IViewerObservableValue observeSingleSelectionTypeCombo;
	private IViewerObservableValue observeSingleSelectionDefaultValueExpression;
	final private String multipleReturnType = List.class.getName();
	private ToolItem browseXMLButton;
	private ToolItem newXMLButton;
	private Combo nsCombo;
	private Combo elementCombo;
	private Button isTransientButton;
	private Button multiplicityButton;
	private ControlDecoration typeDescriptionDecorator;
	private WizardPageSupport pageSupport;
	private String fixedReturnType;
	private boolean isPageFlowContext=false;
	private boolean isOverviewContext=false;
	final private Set<String> availableDataNames = new HashSet<String>();

	private final ViewerFilter typeViewerFilter = new ViewerFilter() {

		@Override
		public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
			if (!allowXML && element instanceof XMLType) {
				return false;
			}
			if (!allowEnum && element instanceof EnumType) {
				return false;
			}
			if(element instanceof BusinessObjectType){
				return false;
			}
			return true;
		}
	};

	private final IValueChangeListener typeChangeListener = new IValueChangeListener() {

		@Override
		public void handleValueChange(final ValueChangeEvent event) {
			final DataType newType = (DataType) event.diff.getNewValue();
			if (newType instanceof JavaType && !(data instanceof JavaObjectData)) {
				final JavaObjectData javaData = ProcessFactory.eINSTANCE.createJavaObjectData();
				javaData.setDataType(newType);
				copyDataFeature(javaData);
				data = javaData;
				updateDatabinding();
			} else if (newType instanceof XMLType && !(data instanceof XMLData)) {
				final XMLData xmlData = ProcessFactory.eINSTANCE.createXMLData();
				xmlData.setDataType(newType);
				copyDataFeature(xmlData);
				data = xmlData;
			} else {
				final Data simpleData = ProcessFactory.eINSTANCE.createData();
				simpleData.setDataType(newType);
				copyDataFeature(simpleData);
				data = simpleData;
			}

			updateMoreSection(newType);
			updateBrowseXMLButton(newType);
			if(mainComposite != null && !mainComposite.isDisposed()){
				final Composite parent = mainComposite.getParent();
				final Point defaultSize = parent.getSize();
				final Point size = parent.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
				parent.setSize(defaultSize.x, size.y);
				parent.layout(true, true);
			}
			updateDatabinding();
		}
	};

	private ToolItem separator;
	private Composite defaultValueComposite;
	private ToolBar xmlToolbar;
	private Composite mainComposite;

	public DataWizardPage(final Data data, final EObject container, final boolean allowXML, final boolean allowEnum, final boolean showIsTransient,
			final boolean showAutoGenerateform, final Set<EStructuralFeature> featureToCheckForUniqueID, String fixedReturnType) {
		super(DataWizardPage.class.getName());
		this.container = container;
		setTitle(Messages.bind(Messages.addDataWizardTitle, getCurrentDataAwareContextName()));
		setDescription(Messages.addDataWizardDescription);
		this.data = data;
		this.featureToCheckForUniqueID = featureToCheckForUniqueID;
		this.allowXML = allowXML;
		this.allowEnum = allowEnum;
		this.showIsTransient = showIsTransient;
		this.showAutoGenerateform = showAutoGenerateform;
		this.fixedReturnType = fixedReturnType;
	}

	private String getCurrentDataAwareContextName(){
		String name = "---";
		EObject context = container;
		while (!(context instanceof DataAware) || context instanceof Form) {
			context = context.eContainer();
		}
		if(context instanceof Lane){
			name = ((Lane) context).getName();
		} else if(context instanceof Pool){
			name = ((Pool) context).getName();
		} else if(context instanceof Task){
			name = ((Task) context).getName();
		}
		return name;
	}

	protected String getHintFor(final DataType newType) {
		final ProcessSwitch<String> dataTypeSwitch = new ProcessSwitch<String>() {

			@Override
			public String caseDateType(final DateType object) {
				return Messages.dateTypeHint;
			}

			@Override
			public String caseStringType(final StringType object) {
				return Messages.stringTypeHint;
			}

			@Override
			public String caseIntegerType(final IntegerType object) {
				return Messages.integerTypeHint;
			}

			@Override
			public String caseBooleanType(final BooleanType object) {
				return Messages.booleanTypeHint;
			}

			@Override
			public String caseLongType(final LongType object) {
				return Messages.longTypeHint;
			}

			@Override
			public String caseDoubleType(final DoubleType object) {
				return Messages.doubleTypeHint;
			}

			@Override
			public String caseFloatType(final FloatType object) {
				return Messages.floatTypeHint;
			}

			@Override
			public String caseJavaType(final JavaType object) {
				return Messages.javaTypeHint;
			}

			@Override
			public String caseXMLType(final XMLType object) {
				return Messages.xmlTypeHint;
			}

			@Override
			public String caseEnumType(final EnumType object) {
				return Messages.enumTypeHint;
			}

		};
		return dataTypeSwitch.doSwitch(newType);
	}

	protected void copyDataFeature(final Data newData) {
		for (final EStructuralFeature feature : ProcessPackage.Literals.DATA.getEAllStructuralFeatures()) {
			newData.eSet(feature, data.eGet(feature));
		}
	}

	@Override
	public void createControl(final Composite parent) {
		mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(15, 10).create());

		createNameAndDescription(mainComposite);
		createTypeChooser(mainComposite);
		createMoreSection(mainComposite);
		createDefaultValue(mainComposite);
		createDataOptions(mainComposite);

		updateDatabinding();

		if(fixedReturnType!=null){
			for (Object object : (EObjectContainmentEList)typeCombo.getInput()) {
				final DataType type = (DataType) object;
				if(fixedReturnType.equals(String.class.getName()) && type.getName().equals("Text")){
					typeCombo.setSelection(new StructuredSelection(type));
					break;
				} else if(fixedReturnType.equals(Boolean.class.getName()) && type.getName().equals("Boolean")){
					typeCombo.setSelection(new StructuredSelection(type));
					break;
				}
			}			
		} else {

		}
		setControl(mainComposite);
	}

	@Override
	public void dispose() {
		if(pageSupport != null){
			pageSupport.dispose();
		}
		if (emfDatabindingContext != null) {
			emfDatabindingContext.dispose();
			emfDatabindingContext = null;
		}
		super.dispose();
	}

	protected void updateDatabinding() {
		if(nameText != null && !nameText.isDisposed()){
			if(pageSupport != null){
				pageSupport.dispose();
			}
			if (emfDatabindingContext != null) {
				emfDatabindingContext.dispose();
			}
			emfDatabindingContext = new EMFDataBindingContext();
			pageSupport = WizardPageSupport.create(this, emfDatabindingContext);
			bindNameAndDescription();
			bindGenerateDataCheckbox();
			bindDataTypeCombo();
			bindJavaClassText();
			bindXSDCombo();
			bindTransientButton();
			bindDefaultValueViewer();
			bindIsMultipleData();

			typeDescriptionDecorator.setDescriptionText(getHintFor(data.getDataType()));

			MultiValidator returnTypeValidator = new MultiValidator() {

				@Override
				protected IStatus validate() {
					final IViewerObservableValue selectedType = ViewersObservables.observeSingleSelection(typeCombo);
					DataType type = (DataType) selectedType.getValue();
					String technicalTypeFor = DataUtil.getTechnicalTypeFor(ModelHelper.getMainProcess(container), type.getName()).replace(Messages.dataTechnicalTypeLabel+" ", "");
					if(data instanceof JavaObjectData){
						technicalTypeFor = ((JavaObjectData) data).getClassName();
					}
					final Expression defaultValueExp = (Expression) observeSingleSelectionDefaultValueExpression.getValue() ;
					if(defaultValueExp != null){
						final IObservableValue returnTypeObservable = EMFObservables.observeValue(defaultValueExp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
						final IObservableValue contentObservable = EMFObservables.observeValue(defaultValueExp, ExpressionPackage.Literals.EXPRESSION__CONTENT);
						final IObservableValue typeObservable = EMFObservables.observeValue(defaultValueExp, ExpressionPackage.Literals.EXPRESSION__TYPE);
						final IObservableValue multipleObservable = EMFObservables.observeValue(data, ProcessPackage.Literals.DATA__MULTIPLE);

						final String expressionType = (String) typeObservable.getValue();
						final String returnType = (String) returnTypeObservable.getValue();
						boolean isMultiple =  (Boolean) multipleObservable.getValue();
						if(isMultiple){
							if(returnType != null && !returnType.isEmpty() && !isReturnTypeCompatible(multipleReturnType, returnType)){
								return ValidationStatus.error(Messages.dataWizardPageReturnTypeNotCorresponding);
							}
							return ValidationStatus.ok();
						}

						if(returnType != null && !returnType.isEmpty() && !isReturnTypeCompatible(technicalTypeFor, returnType)){
							if(Date.class.getName().equals(technicalTypeFor)){
								if(!isReturnTypeCompatible(technicalTypeFor, returnType)){
									return ValidationStatus.error(Messages.dataWizardPageReturnTypeNotCorresponding);
								}
							}else{
								return ValidationStatus.error(Messages.dataWizardPageReturnTypeNotCorresponding);
							}
						}
						final String content = (String) contentObservable.getValue();
						if(content != null && !content.isEmpty() && ExpressionConstants.CONSTANT_TYPE.equals(expressionType)){
							if(Integer.class.getName().equals(returnType)){
								try{
									Integer.valueOf(content);
								}catch (NumberFormatException e) {
									return ValidationStatus.warning(Messages.dataDefaultValueNotCompatibleWithReturnType);
								}
							}else if(Double.class.getName().equals(returnType)){
								try{
									Double.valueOf(content);
								}catch (NumberFormatException e) {
									return ValidationStatus.warning(Messages.dataDefaultValueNotCompatibleWithReturnType);
								}
							}else if(Float.class.getName().equals(returnType)){
								try{
									Float.valueOf(content);
								}catch (NumberFormatException e) {
									return ValidationStatus.warning(Messages.dataDefaultValueNotCompatibleWithReturnType);
								}
							}else if(Long.class.getName().equals(returnType)){
								try{
									Long.valueOf(content);
								}catch (NumberFormatException e) {
									return ValidationStatus.warning(Messages.dataDefaultValueNotCompatibleWithReturnType);
								}
							}else if(Date.class.getName().equals(returnType)){
								try{
									new Date(content);
								}catch (IllegalArgumentException e) {
									return ValidationStatus.warning(Messages.dataDefaultValueNotCompatibleWithReturnType);
								}
							}
						}
					}
					return ValidationStatus.ok();

				}

				protected boolean isReturnTypeCompatible(String technicalTypeFor, final String returnType) {
					try{
						Class typeClass = Class.forName(technicalTypeFor);
						Class returnTypeClass = Class.forName(returnType);
						return typeClass.isAssignableFrom(returnTypeClass);
					}catch (Exception e) {
						return returnType.equals(technicalTypeFor);
					}

				}
			};
			emfDatabindingContext.addValidationStatusProvider(returnTypeValidator);
			
		}
	}

	protected void bindIsMultipleData() {
		emfDatabindingContext.bindValue(SWTObservables.observeSelection(multiplicityButton),
				EMFObservables.observeValue(data, ProcessPackage.Literals.DATA__MULTIPLE));
		final UpdateValueStrategy startegy = new UpdateValueStrategy();
		final IObservableValue returnTypeObservable = EMFObservables.observeValue( data.getDefaultValue(), ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
		final IObservableValue typeObservable = EMFObservables.observeValue( data.getDefaultValue(), ExpressionPackage.Literals.EXPRESSION__TYPE);
		final IObservableValue interpreterObservable = EMFObservables.observeValue( data.getDefaultValue(), ExpressionPackage.Literals.EXPRESSION__INTERPRETER);
		startegy.setConverter(new Converter(Boolean.class,String.class){

			private Object previousExpressionType;

			@Override
			public Object convert(Object input) {
				if((Boolean)input){
					previousExpressionType = typeObservable.getValue();
					typeObservable.setValue(ExpressionConstants.SCRIPT_TYPE);
					interpreterObservable.setValue(ExpressionConstants.GROOVY);
					defaultValueViewer.refresh();
					return multipleReturnType;
				}else{
					if(previousExpressionType != null){
						typeObservable.setValue(previousExpressionType);
						if(!ExpressionConstants.SCRIPT_TYPE.equals(previousExpressionType)){
							interpreterObservable.setValue(null);
						}
						defaultValueViewer.refresh();
					}
					return getSelectedReturnType();
				}
			}

		});

		emfDatabindingContext.bindValue(SWTObservables.observeSelection(multiplicityButton),
				returnTypeObservable,startegy,new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
	}

	protected void bindGenerateDataCheckbox() {
		if (generateDataCheckbox != null) {
			emfDatabindingContext.bindValue(SWTObservables.observeSelection(generateDataCheckbox),
					EMFObservables.observeValue(data, ProcessPackage.Literals.DATA__GENERATED));
		}
	}

	protected void bindNameAndDescription() {
		final UpdateValueStrategy nameStrategy = new UpdateValueStrategy();
		nameStrategy.setAfterGetValidator(new InputLengthValidator(Messages.name, 50));
		nameStrategy.setBeforeSetValidator(new IValidator() {

			@Override
			public IStatus validate(final Object value) {
				/* Search to check at the same level and under */
				for (final EStructuralFeature featureToCheck : featureToCheckForUniqueID) {
					for (final Object object : (List<?>) container.eGet(featureToCheck)) {
						if (object instanceof Data) {
							final Data otherData = (Data) object;
							final Data originalData = ((DataWizard) getWizard()).getOriginalData();
							if (!otherData.equals(originalData) && value.toString().toLowerCase().equals(otherData.getName().toLowerCase())) {
								return new Status(IStatus.ERROR, DataPlugin.PLUGIN_ID, Messages.dataAlreadyExist);
							}
						}
					}
				}

				/* Search above level */
				List<Data> allData = null;
				if( container instanceof AbstractProcess){
					allData = ModelHelper.getAllItemsOfType(ModelHelper.getParentProcess(container), ProcessPackage.Literals.DATA);
				}else{
					//	 allData = ModelHelper.getAccessibleData(container, true);
					allData = getAllAccessibleDatas(container);
				}
				for (final Data object : allData) {
					if (object instanceof Data && !(object.eContainer() instanceof Expression)) {
						final Data otherData = (Data) object;
						final Data originalData = ((DataWizard) getWizard()).getOriginalData();
						if (!otherData.equals(originalData) && value.toString().toLowerCase().equals(otherData.getName().toLowerCase())) {
							return new Status(IStatus.ERROR, DataPlugin.PLUGIN_ID, Messages.dataAlreadyExist);
						}
					}
				}

				return new GroovyReferenceValidator(Messages.name).validate(value);
			}

			private List<Data> getAllAccessibleDatas(EObject container){
				List<Data> allDatas = ModelHelper.getAccessibleData(container, true);
				for (Object o:ModelHelper.getAllItemsOfType(container, ProcessPackage.Literals.DATA)){
					if (o instanceof Data){
						if (!allDatas.contains(o)){
							allDatas.add((Data)o);
						}
					}
				}

				return allDatas;
			}
		});



		UpdateValueStrategy descTargetToModel = new UpdateValueStrategy();
		descTargetToModel.setAfterGetValidator(new InputLengthValidator(Messages.dataDescriptionLabel, 255));
		String previousName = null;
		if(nameText != null && !nameText.isDisposed() && nameText.getText() != null){
			previousName = nameText.getText();
		}
		ISWTObservableValue observeText = SWTObservables.observeText(nameText, SWT.Modify);
		emfDatabindingContext.bindValue(observeText,
				EMFObservables.observeValue(data, ProcessPackage.Literals.ELEMENT__NAME), nameStrategy, null);
		emfDatabindingContext.bindValue(SWTObservables.observeText(descriptionText, SWT.Modify),
				EMFObservables.observeValue(data, ProcessPackage.Literals.ELEMENT__DOCUMENTATION),
				descTargetToModel,
				null);
		if(previousName!= null && !previousName.isEmpty()){
			observeText.setValue(previousName);
		}
	}

	protected void bindDataTypeCombo() {
		final AbstractProcess process = ModelHelper.getMainProcess(container);
		emfDatabindingContext.bindValue(ViewersObservables.observeInput(typeCombo),
				EMFObservables.observeValue(process, ProcessPackage.Literals.ABSTRACT_PROCESS__DATATYPES));
		final IObservableValue modelObservable = EMFObservables.observeValue(data, ProcessPackage.Literals.DATA__DATA_TYPE);
		modelObservable.addValueChangeListener(typeChangeListener);

		observeSingleSelectionTypeCombo = ViewersObservables.observeSingleSelection(typeCombo);
		emfDatabindingContext.bindValue(observeSingleSelectionTypeCombo, modelObservable, null, null);
	}

	protected void bindDefaultValueViewer() {
		defaultValueViewer.setExpressionNatureProvider(new ExpressionContentProvider());
		defaultValueViewer.setExample("");
		final DataType dataType = data.getDataType();
		if (dataType instanceof DateType) {
			defaultValueViewer.setExpressionNatureProvider(new NowDateExpressionNatureProvider());
			defaultValueViewer.setExample(Messages.dateFormatLabel);
		} else if (dataType instanceof EnumType) {
			defaultValueViewer.setExpressionNatureProvider(new OptionsExpressionNatureProvider((EnumType) dataType));
		} else if (dataType instanceof BooleanType) {
			defaultValueViewer.setExpressionNatureProvider(new BooleanExpressionNatureProvider());
		}

		if (data.getDefaultValue() == null) {
			data.setDefaultValue(ExpressionFactory.eINSTANCE.createExpression());
		}

		final Expression exp = data.getDefaultValue();
		final String expType = exp.getType();
		if(!(ExpressionConstants.VARIABLE_TYPE.equals(expType) || ExpressionConstants.PARAMETER_TYPE.equals(expType))){
			exp.setReturnType(getSelectedReturnType());
		}


		defaultValueViewer.setInput(data);
		observeSingleSelectionDefaultValueExpression = ViewersObservables.observeSingleSelection(defaultValueViewer);
		emfDatabindingContext.bindValue(observeSingleSelectionDefaultValueExpression,
				EMFObservables.observeValue(data, ProcessPackage.Literals.DATA__DEFAULT_VALUE));
	}

	private String getSelectedReturnType() {
		if(data.isMultiple()){
			return multipleReturnType;
		}
		IViewerObservableValue selectedType = ViewersObservables.observeSingleSelection(typeCombo);
		DataType type = (DataType) selectedType.getValue();
		if(type instanceof JavaType){
			final String className = ((JavaObjectData)data).getClassName();
			if(className == null || className.isEmpty()){
				return String.class.getName();
			}
			return className;
		}else if (type != null){
			final String technicalTypeFor = DataUtil.getTechnicalTypeFor(ModelHelper.getMainProcess(container), type.getName()).replace(Messages.dataTechnicalTypeLabel+" ", "");
			if(technicalTypeFor.isEmpty()){
				return String.class.getName();
			}
			return technicalTypeFor;
		}
		return null;

	}

	protected void bindTransientButton() {
		if (isTransientButton != null && !isTransientButton.isDisposed()) {
			emfDatabindingContext.bindValue(SWTObservables.observeSelection(isTransientButton),
					EMFObservables.observeValue(data, ProcessPackage.Literals.DATA__TRANSIENT));
		}
	}

	protected void bindJavaClassText() {
		if (classText != null && !classText.isDisposed()) {
			final UpdateValueStrategy classNameStrategy = new UpdateValueStrategy();
			classNameStrategy.setBeforeSetValidator(new IValidator() {

				@Override
				public IStatus validate(final Object value) {
					if (value == null || value.toString().isEmpty()) {
						return new Status(IStatus.ERROR, DataPlugin.PLUGIN_ID, Messages.emptyClassName);
					}
					IType type = null;
					try {
						type = RepositoryManager.getInstance().getCurrentRepository().getJavaProject().findType(value.toString());
					} catch (final JavaModelException e) {
					}
					if (type == null) {
						return new Status(IStatus.WARNING, DataPlugin.PLUGIN_ID, NLS.bind(Messages.classNotFound, value.toString()));
					}
					return Status.OK_STATUS;
				}
			});
			emfDatabindingContext.bindValue(SWTObservables.observeText(classText, SWT.Modify),
					EMFObservables.observeValue(data, ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME), classNameStrategy, null);
		}
	}

	protected void bindXSDCombo() {
		final XSDRepositoryStore store = (XSDRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class);
		if (nsCombo != null && !nsCombo.isDisposed()) {
			final List<XSDFileStore> allArtifacts = store.getChildren();
			for (final XSDFileStore artifact : allArtifacts) {
				final String namespace = ((XSDSchema) artifact.getContent()).getTargetNamespace();
				if (namespace != null) {
					nsCombo.add(namespace);
				} else {
					nsCombo.add("No Namespace " + "(" + artifact.getName() + ")");
				}
			}

			nsCombo.addModifyListener(new ModifyListener() {

				@Override
				public void modifyText(final ModifyEvent e) {
					elementCombo.removeAll();
					if (nsCombo.getSelectionIndex() >= 0) {
						final XSDFileStore artifact = store.getChildren().get(nsCombo.getSelectionIndex());
						for (final String element : artifact.getElements()) {
							elementCombo.add(element);
						}
					}
				}
			});
			final IObservableValue observeValue = EMFObservables.observeValue(data, ProcessPackage.Literals.XML_DATA__NAMESPACE);
			final ISWTObservableValue observeText = SWTObservables.observeText(nsCombo);
			emfDatabindingContext.bindValue(observeText,
					observeValue);
			observeText.addValueChangeListener(new IValueChangeListener() {

				@Override
				public void handleValueChange(ValueChangeEvent event) {
					if(newXMLButton != null && !newXMLButton.isDisposed()){
						newXMLButton.setEnabled(((XMLData) data).getType() != null && event.diff.getNewValue() != null && !event.diff.getNewValue().toString().isEmpty());
					}
				}
			});
		}

		if (elementCombo != null && !elementCombo.isDisposed() && data instanceof XMLData) {
			elementCombo.removeAll();
			final XSDFileStore artifact = store.findArtifactWithNamespace(((XMLData) data).getNamespace());
			if (((XMLData) data).getNamespace() != null) {
				for (final String element : artifact.getElements()) {
					elementCombo.add(element);
				}
			}
			final UpdateValueStrategy strategy = new UpdateValueStrategy();
			strategy.setBeforeSetValidator(new IValidator() {

				@Override
				public IStatus validate(final Object value) {
					if (value == null || value.toString().isEmpty()) {
						return new Status(IStatus.ERROR, DataPlugin.PLUGIN_ID, Messages.emptyXMLElement);
					}
					return Status.OK_STATUS;
				}
			});
			final IObservableValue observeValue = EMFObservables.observeValue(data, ProcessPackage.Literals.XML_DATA__TYPE);
			final ISWTObservableValue observeText = SWTObservables.observeText(elementCombo);
			emfDatabindingContext.bindValue(observeText,
					observeValue, strategy, null);
			observeText.addValueChangeListener(new IValueChangeListener() {

				@Override
				public void handleValueChange(ValueChangeEvent event) {
					if(newXMLButton != null && !newXMLButton.isDisposed()){
						newXMLButton.setEnabled(((XMLData) data).getNamespace() != null && event.diff.getNewValue() != null && !event.diff.getNewValue().toString().isEmpty());
					}
				}
			});

		}
	}

	protected void createMoreSection(final Composite parent) {
		new Label(parent, SWT.NONE);
		moreSection = new Section(parent, ExpandableComposite.NO_TITLE_FOCUS_BOX | ExpandableComposite.TWISTIE);
		moreSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		moreSection.setText(Messages.additionalInformation);
		moreSection.addExpansionListener(new IExpansionListener() {

			@Override
			public void expansionStateChanging(final ExpansionEvent event) {
			}

			@Override
			public void expansionStateChanged(final ExpansionEvent event) {
				final Composite parent2 = mainComposite.getParent();
				final Point defaultSize = parent2.getSize();
				final Point size = parent2.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
				parent2.setSize(defaultSize.x, size.y);
				parent2.layout(true, true);
			}
		});

		updateMoreSection(data.getDataType());
		updateBrowseXMLButton(data.getDataType());
	}

	protected void createDefaultValue(final Composite parent) {
		final Label defaultValueLabel = new Label(parent, SWT.NONE);
		defaultValueLabel.setText(Messages.defaultValueLabel);
		defaultValueLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

		defaultValueComposite = new Composite (parent,SWT.NONE);
		defaultValueComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		defaultValueComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(300, SWT.DEFAULT).span(2, 1).create());

		defaultValueViewer = new ExpressionViewer(defaultValueComposite, SWT.BORDER, ProcessPackage.Literals.DATA__DEFAULT_VALUE);
		defaultValueViewer.setIsPageFlowContext(isPageFlowContext);
		defaultValueViewer.setIsOverviewContext(isOverviewContext);
		defaultValueViewer.getControl().setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());
		defaultValueViewer.setContext(container);

		ToolItem eraseItem = defaultValueViewer.getEraseControl();
		eraseItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final Expression defaultValueExp = (Expression) observeSingleSelectionDefaultValueExpression.getValue() ;
				if(defaultValueExp != null){
					final IObservableValue returnTypeObservable = EMFObservables.observeValue(defaultValueExp, ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);
					final IObservableValue contentObservable = EMFObservables.observeValue(defaultValueExp, ExpressionPackage.Literals.EXPRESSION__CONTENT);
					if(contentObservable.getValue() != null && contentObservable.getValue().toString().isEmpty()){//ERASE BUTTON
						returnTypeObservable.setValue(getSelectedReturnType());
					}
				}
			}
		});


		refreshDataNames();

		defaultValueViewer.addFilter(new AvailableExpressionTypeFilter(new String[] {ExpressionConstants.VARIABLE_TYPE,ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.SCRIPT_TYPE,
				ExpressionConstants.PARAMETER_TYPE }){
			@Override
			public boolean select(Viewer viewer, Object context, Object element) {
				boolean selected = super.select(viewer, context, element);
				refreshDataNames();
				if(element instanceof Expression && ExpressionConstants.VARIABLE_TYPE.equals(((Expression)element).getType())){
					return availableDataNames.contains(((Expression)element).getName());
				}else if(element instanceof IExpressionProvider && ExpressionConstants.VARIABLE_TYPE.equals(((IExpressionProvider) element).getExpressionType())){
					return !(container instanceof AbstractProcess ) || (container instanceof Pool && isOverviewContext);
				}
				return selected;
			}
		});
		defaultValueViewer.setInput(data);

		updateBrowseXMLButton(data.getDataType());
	}

	protected void createNewDocumentItem(final ToolBar tb) {
		newXMLButton = new ToolItem(tb, SWT.PUSH);
		newXMLButton.setImage(Pics.getImage("filenew.png", DataPlugin.getDefault()));
		newXMLButton.setToolTipText("New empty document");
		newXMLButton.setEnabled(((XMLData) data).getType() != null && ((XMLData) data).getNamespace() != null);
		newXMLButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings({ "restriction" })
			@Override
			public void widgetSelected(SelectionEvent e) {
				final NewXMLGenerator generator = new NewXMLGenerator();
				final XSDRepositoryStore store = (XSDRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class);
				if(data instanceof XMLData){
					final XSDFileStore nameSpaceStore = store.findArtifactWithNamespace(((XMLData)data).getNamespace());
					if(nameSpaceStore != null){
						String[] errors = new String[2];
						final CMDocument createCMDocument = NewXMLGenerator.createCMDocument(nameSpaceStore.getResource().getLocation().toFile().toURI().toString(), errors);
						generator.setCMDocument(createCMDocument);
						generator.setBuildPolicy(ContentBuilder.BUILD_ALL_CONTENT);
						generator.setRootElementName(((XMLData)data).getType());
						try {
							ByteArrayOutputStream stream = generator.createXMLDocument("xmlFileName", "UTF-8");
							String xmlContent = new String(stream.toByteArray(),"UTF-8");
							defaultValueViewer.getTextControl().setText(xmlContent);
						} catch (Exception e1) {
							BonitaStudioLog.error(e1);
						}
					}
				}
			}
		});
	}

	protected void createBrowseXMLItem(final ToolBar tb) {
		browseXMLButton = new ToolItem(tb, SWT.PUSH);
		browseXMLButton.setText("...");
		browseXMLButton.setToolTipText(Messages.browseClasses);
		browseXMLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				fd.setFilterExtensions(new String[] { "*.xml" });
				String file = fd.open();
				if (file != null) {
					FileReader fr = null;
					BufferedReader br = null;
					try {
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						String temp;
						StringBuilder builder = new StringBuilder();
						while ((temp = br.readLine()) != null) {
							builder.append(temp);
						}
						defaultValueViewer.getTextControl().setText(builder.toString());
						if (!defaultValueViewer.getTextControl().getText().equals(builder.toString())) {
							MessageDialog.openError(getShell(), Messages.xmlDefaultValueTooLongTitle, Messages.xmlDefaultValueTooLongMessage);
						}
					} catch (Exception e1) {
						BonitaStudioLog.error(e1);
					} finally {
						if (fr != null) {
							try {
								fr.close();
							} catch (IOException e1) {
								BonitaStudioLog.error(e1);
							}
						}
						if (br != null) {
							try {
								br.close();
							} catch (IOException e1) {
								BonitaStudioLog.error(e1);
							}
						}
					}
				}

			}

		});
	}

	protected void createTypeChooser(final Composite parent) {
		final Label typeLabel = new Label(parent, SWT.NONE);
		typeLabel.setText(Messages.datatypeLabel);
		typeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

		typeCombo = new ComboViewer(parent, SWT.BORDER | SWT.READ_ONLY);
		typeCombo.setContentProvider(new ArrayContentProvider());
		typeCombo.setLabelProvider(new DataTypeLabelProvider());
		typeCombo.addFilter(typeViewerFilter);
		typeCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(10, 0).create());

		typeDescriptionDecorator = new ControlDecoration(typeCombo.getCombo(), SWT.LEFT);
		typeDescriptionDecorator.setImage(Pics.getImage(PicsConstants.hint));

		if (allowEnum) {
			final Button createOptionButton = new Button(parent, SWT.PUSH);
			createOptionButton.setText(Messages.listOfOptions);
			createOptionButton.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(final SelectionEvent e) {
					final EnumDataTypeDialog dialog = new EnumDataTypeDialog(Display.getDefault().getActiveShell(), ModelHelper.getMainProcess(container));
					if (dialog.open() == Window.OK) {
						final EnumType type = dialog.getSelectedEnum();
						updateDatabinding();
						typeCombo.setSelection(new StructuredSelection(type));
					}
				}
			});
		} else {
			typeCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).indent(10, 0).create());
		}
	}

	protected void updateMoreSection(final DataType type) {
		if(moreSection != null && !moreSection.isDisposed()){
			moreSection.setExpanded(false);
			moreSection.setEnabled(false);

			if (moreSection.getClient() != null) {
				moreSection.getClient().dispose();
			}

			if (type instanceof JavaType) {
				moreSection.setClient(createJavaTypeSelection(moreSection));
				moreSection.setEnabled(true);
				moreSection.setExpanded(true);
			} else if (type instanceof XMLType) {
				moreSection.setClient(createXMLTypeSelection(moreSection));
				moreSection.setEnabled(true);
				moreSection.setExpanded(true);
			} else if (type instanceof DateType) {
				moreSection.setClient(createDateSelection(moreSection));
				moreSection.setEnabled(true);
				moreSection.setExpanded(true);
			} else {
				moreSection.setClient(new Composite(moreSection, SWT.NONE));
			}
		}
	}

	protected void updateBrowseXMLButton(final DataType type){
		if(defaultValueComposite != null){
			if (type instanceof XMLType){
				if(xmlToolbar == null || xmlToolbar.isDisposed()){
					xmlToolbar = new ToolBar(defaultValueComposite, SWT.FLAT);
					separator = new ToolItem(xmlToolbar, SWT.SEPARATOR | SWT.VERTICAL);
					createNewDocumentItem(xmlToolbar);
					createBrowseXMLItem(xmlToolbar);
					defaultValueComposite.layout(true, true);
				}
			} else {
				if(xmlToolbar != null){
					browseXMLButton.dispose();
					xmlToolbar.dispose();
					separator.dispose();
					newXMLButton.dispose();
					defaultValueComposite.layout(true, true);
				}
			}
		}
	}

	protected void createNameAndDescription(final Composite parent) {
		final Label nameLabel = new Label(parent, SWT.NONE);
		nameLabel.setText(Messages.name +" *");
		nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

		nameText = new Text(parent, SWT.BORDER);
		nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

		final Label descriptionLabel = new Label(parent, SWT.NONE);
		descriptionLabel.setText(Messages.dataDescriptionLabel);
		descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());

		descriptionText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 70).span(2, 1).create());
		descriptionText.setTextLimit(255);
	}

	protected void createDataOptions(final Composite parent) {

		new Label(parent, SWT.NONE); // FILLER
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).spacing(25, 0).create());

		multiplicityButton = new Button(composite, SWT.CHECK);
		multiplicityButton.setLayoutData(GridDataFactory.fillDefaults().create());
		multiplicityButton.setText(Messages.isMultiple);
		final ControlDecoration multiplicityControlDecoration = new ControlDecoration(multiplicityButton, SWT.RIGHT);
		multiplicityControlDecoration.setImage(Pics.getImage(PicsConstants.hint));
		multiplicityControlDecoration.setDescriptionText(Messages.multiplicity_hint);

		if (showIsTransient) {
			isTransientButton = new Button(composite, SWT.CHECK);
			isTransientButton.setText(Messages.transientLabel);
			final ControlDecoration controlDecoration = new ControlDecoration(isTransientButton, SWT.RIGHT);
			controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
			controlDecoration.setDescriptionText(Messages.transientHint);
		}

		if (showAutoGenerateform) {
			final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
					"org.bonitasoft.studio.properties.widget"); //$NON-NLS-1$
			IWidgetContribtution generateDataContribution = null;
			for (final IConfigurationElement elem : elements) {
				try {
					if (elem.getAttribute("id").equals(GENERATE_DATA_CONTRIBUTION_ID)) { //$NON-NLS-1$
						generateDataContribution = (IWidgetContribtution) elem.createExecutableExtension("class"); //$NON-NLS-1$
					}
				} catch (final CoreException e) {
					BonitaStudioLog.error(e);
				}
			}

			if (generateDataContribution != null) {
				generateDataCheckbox = (Button) generateDataContribution.createControl(composite);
			}
		}

		if (showAutoGenerateform && showIsTransient) {
			if (generateDataCheckbox != null) {
				generateDataCheckbox.setLayoutData(GridDataFactory.fillDefaults().create());
			}
			isTransientButton.setLayoutData(GridDataFactory.fillDefaults().create());
		} else if (showAutoGenerateform && !showIsTransient && generateDataCheckbox != null) {
			generateDataCheckbox.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());
		} else if (!showAutoGenerateform && showIsTransient) {
			isTransientButton.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());
		}
	}

	protected Composite createDateSelection(final Composite parent) {
		final Composite client = new Composite(parent, SWT.NONE);
		client.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).spacing(15, 10).create());

		final Label dateLabel = new Label(client, SWT.NONE);
		dateLabel.setText(Messages.pickADate);
		dateLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

		final DateTime dateChooser = new DateTime(client, SWT.BORDER | SWT.DATE | SWT.DROP_DOWN);
		dateChooser.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
		final DateTime timeChooser = new DateTime(client, SWT.BORDER | SWT.TIME);
		timeChooser.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());

		dateChooser.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				final Expression dateExpression = ExpressionFactory.eINSTANCE.createExpression();
				final String displayDate = DateUtil.getWidgetDisplayDate(dateChooser, timeChooser);
				dateExpression.setName(displayDate);
				dateExpression.setContent(DateUtil.getDateExpressionContent(dateChooser.getYear(),
						dateChooser.getMonth(),
						dateChooser.getDay(),
						timeChooser.getHours(),
						timeChooser.getMinutes(),
						timeChooser.getSeconds()));
				dateExpression.setType(ExpressionConstants.SCRIPT_TYPE);
				dateExpression.setInterpreter(ExpressionConstants.GROOVY);
				dateExpression.setReturnType(Date.class.getName());
				dateExpression.setReturnTypeFixed(true);
				data.setDefaultValue(dateExpression);	
			}

		});
		timeChooser.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				final Expression dateExpression = ExpressionFactory.eINSTANCE.createExpression();
				final String displayDate = DateUtil.getWidgetDisplayDate(dateChooser, timeChooser);
				dateExpression.setName(displayDate);
				dateExpression.setContent(DateUtil.getDateExpressionContent(dateChooser.getYear(),
						dateChooser.getMonth(),
						dateChooser.getDay(),
						timeChooser.getHours(),
						timeChooser.getMinutes(),
						timeChooser.getSeconds()));
				dateExpression.setType(ExpressionConstants.SCRIPT_TYPE);
				dateExpression.setInterpreter(ExpressionConstants.GROOVY);
				dateExpression.setReturnType(Date.class.getName());
				dateExpression.setReturnTypeFixed(true);
				data.setDefaultValue(dateExpression);
			}
		});
		return client;
	}

	protected Composite createJavaTypeSelection(final Composite parent) {
		final Composite client = new Composite(parent, SWT.NONE);
		client.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).create());
		final Label classLabel = new Label(client, SWT.NONE);
		classLabel.setText(Messages.classLabel);
		classLabel.setLayoutData(new GridData(SWT.DEFAULT, SWT.CENTER, false, false));
		classText = new Text(client, SWT.BORDER);
		final GridData layoutData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		classText.setLayoutData(layoutData);
		classText.setEditable(false);
		final Button browseClassesButton = new Button(client, SWT.PUSH);
		browseClassesButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		browseClassesButton.setText(Messages.browseClasses);
		browseClassesButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(final Event event) {
				openClassSelectionDialog(classText);
				data.getDefaultValue().setReturnType(classText.getText());
				data.getDefaultValue().setType(ExpressionConstants.SCRIPT_TYPE);
			}
		});
		return client;
	}

	@SuppressWarnings("restriction")
	protected void openClassSelectionDialog(final Text classText) {
		final JavaSearchScope scope = new JavaSearchScope();
		try {
			scope.add(RepositoryManager.getInstance().getCurrentRepository().getJavaProject());
		} catch (final Exception ex) {
			BonitaStudioLog.error(ex);
		}
		final FilteredTypesSelectionDialog searchDialog = new FilteredTypesSelectionDialog(getShell(), false, null, scope, IJavaSearchConstants.TYPE);
		if (searchDialog.open() == Window.OK) {
			classText.setText(((IType) searchDialog.getFirstResult()).getFullyQualifiedName());
		}
	}

	protected Composite createXMLTypeSelection(final Composite parent) {
		final Composite xmlTypeComposite = new Composite(parent, SWT.NONE);
		xmlTypeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		xmlTypeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 5).create());

		final Label namespaceLabel = new Label(xmlTypeComposite, SWT.NONE);
		namespaceLabel.setText(Messages.selectXMLNamespace);
		namespaceLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

		nsCombo = new Combo(xmlTypeComposite, SWT.BORDER | SWT.SINGLE);
		nsCombo.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());

		final Button addXSDButton = new Button(xmlTypeComposite, SWT.PUSH);
		addXSDButton.setText(Messages.AddXSDSchema);
		addXSDButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				final FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.MULTI);
				fd.setFilterExtensions(new String[] { "*.xsd" });
				fd.setText(Messages.selectXSDToImport);
				if (fd.open() != null) {
					final XSDRepositoryStore store = (XSDRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class);
					for (final String fileName : fd.getFileNames()) {
						final File file = new File(fd.getFilterPath() + File.separator + fileName);
						final IStatus xsdFileValid = store.isXSDFileValid(file);
						if (!xsdFileValid.isOK()) {
							MessageDialog.openError(getShell(), Messages.importXsd_title, Messages.importXsd_msg + "\n" + xsdFileValid.getMessage());
							return;
						}

						final Resource resource = new XSDResourceFactoryImpl().createResource(URI.createFileURI(file.getAbsolutePath()));
						try {
							resource.load(Collections.EMPTY_MAP);
						} catch (final IOException e1) {
							BonitaStudioLog.error(e1);
						}
						XSDSchema content = null;
						if (!resource.getContents().isEmpty()) {
							content = (XSDSchema) resource.getContents().get(0);
						}
						XSDFileStore artifact = store.getChild(fileName);
						if (artifact == null) {
							artifact = store.createRepositoryFileStore(fileName);
						}

						artifact.save(content);
					}
					nsCombo.removeAll();
					for (final IRepositoryFileStore artifact : store.getChildren()) {
						final XSDFileStore file = (XSDFileStore) artifact;
						final XSDSchema schema = (XSDSchema) file.getContent();
						String xmlNamespace = null;
						if (schema != null && schema.getTargetNamespace() != null && !schema.getTargetNamespace().isEmpty()) {
							xmlNamespace = schema.getTargetNamespace();
						} else {
							xmlNamespace = "No Namespace " + "(" + artifact.getName() + ")";
						}
						nsCombo.add(xmlNamespace);
					}
					nsCombo.setText(((XMLData) data).getNamespace());
				}
			}

		});

		final Label elementLabel = new Label(xmlTypeComposite, SWT.NONE);
		elementLabel.setText(Messages.selectXMLElement);
		elementLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

		elementCombo = new Combo(xmlTypeComposite, SWT.BORDER | SWT.SINGLE);
		elementCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

		return xmlTypeComposite;
	}

	public Data getWorkingCopy() {
		return data;
	}

	protected String getXMLInstanceLabel(EObject container2) {
		AbstractProcess process = ModelHelper.getMainProcess(container);
		for (DataType dataType : process.getDatatypes()) {
			if (dataType instanceof XMLType) {
				return dataType.getName();
			}
		}
		return null;
	}

	@Override
	public boolean isPageFlowContext() {

		return isPageFlowContext;
	}

	@Override
	public void setIsPageFlowContext(boolean isPageFlowContext) {
		this.isPageFlowContext=isPageFlowContext;

	}

	public void refreshDataNames(){
		if(!(container instanceof AbstractProcess)){
			List<Data> availableData = ModelHelper.getAccessibleData(ModelHelper.getParentProcess(container));
			if(isPageFlowContext && container instanceof Task){
				availableData.addAll(((Task)container).getData());
			}
			for(Data d : availableData){
				availableDataNames.add(d.getName());
			}
		} else {
			if (container instanceof Pool && isOverviewContext){
				List<Data> availableData = ModelHelper.getAccessibleData(ModelHelper.getParentProcess(container));
				availableData.addAll(((Pool)container).getData());
				for(Data d : availableData){
					availableDataNames.add(d.getName());
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
		return isOverviewContext;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(boolean isOverviewContext) {
		this.isOverviewContext=isOverviewContext;
		
	}
}
