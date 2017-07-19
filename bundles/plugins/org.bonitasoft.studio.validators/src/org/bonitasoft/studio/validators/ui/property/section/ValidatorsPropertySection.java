/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.validators.ui.property.section;

import java.util.Collection;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.MultipleValuatedFormField;
import org.bonitasoft.studio.model.form.Validable;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.validators.commands.AddValidatorCommand;
import org.bonitasoft.studio.validators.commands.RemoveValidatorCommand;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorDescriptor;
import org.bonitasoft.studio.validators.descriptor.validator.ValidatorType;
import org.bonitasoft.studio.validators.i18n.Messages;
import org.bonitasoft.studio.validators.repository.ValidatorDescriptorRepositoryStore;
import org.bonitasoft.studio.validators.ui.providers.ValidatorLabelProvider;
import org.bonitasoft.studio.validators.ui.wizard.ValidatorWizard;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 * @author Baptiste Mesta
 */
public class ValidatorsPropertySection extends AbstractBonitaDescriptionSection {

    private static final String GROOVY_TYPE = "Groovy expression";
    private static final String REGULAR_TYPE = "Regular expression";
    protected EMFDataBindingContext context = new EMFDataBindingContext();

    private TableViewer tableViewer;
    private Button defaultValidator;

    /* right panel */
    private Composite fieldsComposite;
    private Text nameField;
    private ExpressionViewer labelExpressionViewer;
    private ComboViewer comboViewerForValidatorClass;
    private ExpressionViewer parameterExpressionViewer;
    private Text htmlClassField;

    /* save the validator selected in the TreeViewer */
    private Validator currentValidator;

    private EObject lastEObject;

    private DataBindingContext defaultValidatorContext;
    private Button isBelow;
    private Button defaultValidatorIsBelow;

    private Button isAbove;
    private Button defaultValidatorIsAbove;
    private final Converter converter = new Converter(Boolean.class, Boolean.class) {

        @Override
        public Object convert(final Object fromObject) {
            return fromObject != null ? ((Boolean) fromObject) : false;
        }
    };

    private Composite positionComp;
    private Button removeValidatorButton;
    private ValidatorDescriptorRepositoryStore validatorStore;

    private final ViewerFilter formFilter = new ViewerFilter() {

        @Override
        public boolean select(final Viewer arg0, final Object arg1, final Object arg2) {
            final ValidatorDescriptor descriptor = (ValidatorDescriptor) arg2;
            return descriptor.getType() == ValidatorType.PAGE_VALIDATOR;
        }
    };

    private final ViewerFilter fieldFilter = new ViewerFilter() {

        @Override
        public boolean select(final Viewer arg0, final Object arg1, final Object arg2) {
            final ValidatorDescriptor descriptor = (ValidatorDescriptor) arg2;
            return descriptor.getType() == ValidatorType.FILED_VALIDATOR;
        }
    };

    private EMFDataBindingContext expressionContext;

    @Override
    protected void createContent(final Composite parent) {
        validatorStore = RepositoryManager.getInstance().getRepositoryStore(ValidatorDescriptorRepositoryStore.class);
        doCreateControls(parent);
    }

    protected void doCreateControls(final Composite parent) {
        final Composite mainComposite = getWidgetFactory().createComposite(parent, SWT.NONE);
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(3).margins(10, 10).extendedMargins(10, 10, 10, 10).create());

        createButtons(mainComposite);
        createListOfValidators(mainComposite);
        updateButton();
        createRightPanel(mainComposite);

    }

    private void createRightPanel(final Composite mainComposite) {
        fieldsComposite = getWidgetFactory().createComposite(mainComposite, SWT.NONE);
        final GridLayout gl = new GridLayout(4, false);
        fieldsComposite.setLayout(gl);

        // Name Label
        getWidgetFactory().createCLabel(fieldsComposite, Messages.GeneralSection_Name);
        // Name text
        nameField = getWidgetFactory().createText(fieldsComposite, "", SWT.NONE); //$NON-NLS-1$
        nameField.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 3, 1));

        getWidgetFactory().createCLabel(fieldsComposite, Messages.Validator_ValidatorClass + " *");
        // validatorClass text
        createComboAnButtonForValidatorClass(fieldsComposite);

        // Label Label
        getWidgetFactory().createLabel(fieldsComposite, Messages.Validator_ErrorMessage + " *");
        // Label text
        labelExpressionViewer = new ExpressionViewer(fieldsComposite, SWT.BORDER, getWidgetFactory());
        labelExpressionViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 3, 1));
        labelExpressionViewer.addFilter(new AvailableExpressionTypeFilterWitoutContingentWidgets());

        /* create the parameter field */
        getWidgetFactory().createLabel(fieldsComposite, Messages.Validator_Parameter);
        parameterExpressionViewer = new ExpressionViewer(fieldsComposite, SWT.BORDER, getWidgetFactory());
        parameterExpressionViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().hint(300, SWT.DEFAULT).span(3, 1).create());
        parameterExpressionViewer.addFilter(new AvailableExpressionTypeFilterWitoutContingentWidgets());

        // htmlClass Label
        getWidgetFactory().createLabel(fieldsComposite, Messages.Validator_HtmlClass);
        // htmlClass text
        htmlClassField = getWidgetFactory().createText(fieldsComposite, ""); //$NON-NLS-1$
        htmlClassField.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 3, 1));

        // htmlClass Label
        getWidgetFactory().createLabel(fieldsComposite, Messages.Validator_isBelow);
        final Composite positionComposite = getWidgetFactory().createComposite(fieldsComposite, SWT.NONE);
        positionComposite.setLayout(new GridLayout(2, false));
        isBelow = getWidgetFactory().createButton(positionComposite, Messages.Validator_Below, SWT.RADIO);
        isAbove = getWidgetFactory().createButton(positionComposite, Messages.Validator_Above, SWT.RADIO);

        createDefaultValidatorPanel(mainComposite);

        enableFields(false);

    }

    private void createDefaultValidatorPanel(final Composite mainComposite) {
        final Composite defaultValidatorComposite = getWidgetFactory().createComposite(mainComposite);
        defaultValidatorComposite.setLayout(new GridLayout(1, false));
        defaultValidatorComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 4, 1));

        defaultValidator = getWidgetFactory().createButton(defaultValidatorComposite, Messages.Validator_defaultValidator,
                SWT.CHECK);

        positionComp = getWidgetFactory().createComposite(defaultValidatorComposite);
        positionComp.setLayout(new GridLayout(3, false));
        getWidgetFactory().createLabel(positionComp, Messages.Validator_isBelow);
        defaultValidatorIsBelow = getWidgetFactory().createButton(positionComp, Messages.Validator_Below, SWT.RADIO);
        defaultValidatorIsAbove = getWidgetFactory().createButton(positionComp, Messages.Validator_Above, SWT.RADIO);

        positionComp.setLayoutData(GridDataFactory.fillDefaults().indent(15, 0).create());
        defaultValidator.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                positionComp.setVisible(defaultValidator.getSelection());
            }
        });
    }

    private void createButtons(final Composite mainComposite) {
        final Composite buttonsComposite = getWidgetFactory().createPlainComposite(mainComposite, SWT.NONE);
        buttonsComposite.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false));
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());

        final Button addValidatorButton = createAddValidatorButton(buttonsComposite);
        addValidatorButton.setFocus();
        createCreateValidatorButton(buttonsComposite);
        removeValidatorButton = createRemoveValidatorButton(buttonsComposite);

    }

    private void updateButton() {
        if (removeValidatorButton != null && !removeValidatorButton.isDisposed() && tableViewer != null
                && !tableViewer.getTable().isDisposed()) {
            removeValidatorButton.setEnabled(!tableViewer.getSelection().isEmpty());
        }
    }

    /**
     * Create a FilteredTree to list all validators on the Validable.
     * 
     * @param mainComposite
     */
    protected void createListOfValidators(final Composite mainComposite) {
        // ----------------left panel : tree + buttons
        tableViewer = new TableViewer(mainComposite,
                SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
        getWidgetFactory().adapt(tableViewer.getTable(), false, false);
        final GridData gridData = new GridData(SWT.CENTER, SWT.TOP, false, false);
        gridData.widthHint = 300;
        gridData.heightHint = 150;
        tableViewer.getTable().setLayoutData(gridData);
        //validatorList.setLayoutData(new GridData(300, 150));
        tableViewer.setLabelProvider(new ValidatorLabelProvider());
        //TODO : bind the list of validator
        tableViewer.setContentProvider(new IStructuredContentProvider() {

            @Override
            public void inputChanged(final Viewer arg0, final Object arg1, final Object arg2) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public Object[] getElements(final Object inputElement) {
                if (inputElement instanceof Validable) {
                    return ((Validable) inputElement).getValidators().toArray();
                } else {
                    return new Object[0];
                }
            }
        });
        tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {

                currentValidator = (Validator) ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
                enableFields(!tableViewer.getSelection().isEmpty());
                if (currentValidator != null) {
                    if (expressionContext != null) {
                        expressionContext.dispose();
                    }
                    expressionContext = new EMFDataBindingContext();

                    Expression selection = currentValidator.getParameter();
                    if (selection == null) {
                        selection = ExpressionFactory.eINSTANCE.createExpression();
                        getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), currentValidator,
                                FormPackage.Literals.VALIDATOR__PARAMETER, selection));

                    }
                    if (currentValidator != null) {
                        parameterExpressionViewer.setContext(currentValidator);
                    } else {
                        parameterExpressionViewer.setContext(getValidable());
                    }

                    parameterExpressionViewer.setInput(currentValidator);

                    expressionContext.bindValue(
                            ViewerProperties.singleSelection().observe(parameterExpressionViewer),
                            EMFEditProperties.value(getEditingDomain(), FormPackage.Literals.VALIDATOR__PARAMETER)
                                    .observe(currentValidator));

                }

                if (currentValidator != null) {
                    Expression selection = currentValidator.getDisplayName();
                    if (selection == null) {
                        selection = ExpressionFactory.eINSTANCE.createExpression();
                        getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), currentValidator,
                                FormPackage.Literals.VALIDATOR__DISPLAY_NAME, selection));
                    }
                    if (currentValidator != null) {
                        labelExpressionViewer.setContext(currentValidator);
                    } else {
                        labelExpressionViewer.setContext(getValidable());
                    }

                    labelExpressionViewer.setInput(currentValidator);
                    expressionContext.bindValue(
                            ViewerProperties.singleSelection().observe(labelExpressionViewer),
                            EMFEditProperties.value(getEditingDomain(), FormPackage.Literals.VALIDATOR__DISPLAY_NAME)
                                    .observe(currentValidator));

                }
                updateButton();
            }
        });

    }

    /**
     * @param fieldsComposite
     */
    private void createComboAnButtonForValidatorClass(final Composite fieldsComposite) {
        final Combo ccombo = new Combo(fieldsComposite, SWT.READ_ONLY);
        comboViewerForValidatorClass = new ComboViewer(ccombo);
        ccombo.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).create());
        comboViewerForValidatorClass.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {
                return ((ValidatorDescriptor) element).getName();
            }
        });
        comboViewerForValidatorClass.setContentProvider(new ArrayContentProvider());
        /* Sort element by alphabetical order */
        comboViewerForValidatorClass.setComparator(new ViewerComparator());

    }

    private void createCreateValidatorButton(final Composite buttonsComposite) {
        final Button importButton = getWidgetFactory().createButton(buttonsComposite, Messages.createButton, SWT.FLAT);
        importButton.setLayoutData(GridDataFactory.swtDefaults().hint(85, SWT.DEFAULT).create());
        importButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final CompoundCommand cc = new CompoundCommand();
                final ValidatorWizard cvw = new ValidatorWizard();
                final WizardDialog wd = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                        cvw);

                if (wd.open() == WizardDialog.OK) {
                    /*
                     * if OK create add the validator to the widget/form
                     * and set directly the validator type on the validator
                     */
                    createValidator(/* ValidatorRepositoryArtifact.ID_BASE+ */cvw.getValidatorId());
                    getEditingDomain().getCommandStack().execute(cc);
                    comboViewerForValidatorClass.setInput(validatorStore.getValidatorDescriptors());
                }
                comboViewerForValidatorClass.refresh(true);
            }
        });

    }

    private void enableFields(final boolean enable) {
        fieldsComposite.setVisible(enable);
        nameField.setEnabled(enable);
        labelExpressionViewer.getControl().setEnabled(enable);
        comboViewerForValidatorClass.getCombo().setEnabled(enable);
        htmlClassField.setEnabled(enable);
        isBelow.setEnabled(enable);
        if (currentValidator != null && currentValidator.getValidatorClass() != null) {
            final ValidatorDescriptor descriptior = validatorStore
                    .getValidatorDescriptor(currentValidator.getValidatorClass());
            if (descriptior == null) {
                parameterExpressionViewer.getControl().setEnabled(false);
            } else {
                final boolean enabled = enable && descriptior.getHasParameter();
                parameterExpressionViewer.getControl().setEnabled(enabled);
            }
        } else {
            parameterExpressionViewer.getControl().setEnabled(false);
        }
    }

    @Override
    public void refresh() {
        super.refresh();

        if (tableViewer != null && !tableViewer.getTable().isDisposed() && comboViewerForValidatorClass != null
                && !comboViewerForValidatorClass.getCombo().isDisposed()) {
            if (getValidable().getValidators().size() > 0) {
                /* update value in the treeviewer when the name changed. */
                tableViewer.refresh(true);
                currentValidator = (Validator) ((IStructuredSelection) tableViewer.getSelection()).getFirstElement();
            } else {
                /* update the treeviewer if the last validator is removed */
                tableViewer.refresh(true);
                currentValidator = null;
            }

            if (lastEObject == null || !lastEObject.equals(getEObject())) {
                lastEObject = getEObject();
                if (lastEObject != null) {
                    tableViewer.setInput(getValidable());
                    comboViewerForValidatorClass.resetFilters();
                    if (getEObject() instanceof Form) {
                        comboViewerForValidatorClass.addFilter(formFilter);
                    } else {
                        comboViewerForValidatorClass.addFilter(fieldFilter);
                    }

                    //                  for(Validator validator :((Validable)lastEObject).getValidators()){
                    //                      if(validator.getValidatorClass() != null){
                    //                          boolean stilExists = false ;
                    //                          for(IRepositoryFileStore file : validatorStore.getChildren()){
                    //                              ValidatorDescriptor desc = (ValidatorDescriptor) file.getContent() ;
                    //                              if(desc.getClassName().equals(validator.getValidatorClass())){
                    //                                  stilExists = true ;
                    //                                  break ;
                    //                              }
                    //                          }
                    //                          if(!stilExists){
                    //                              getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), validator, FormPackage.Literals.VALIDATOR__VALIDATOR_CLASS, null)) ;
                    //                          }
                    //                      }
                    //                  }

                    comboViewerForValidatorClass.setInput(validatorStore.getValidatorDescriptors());

                    if (context != null) {
                        context.dispose();
                    }
                    context = new EMFDataBindingContext();

                    updateFieldDependingOnValidatorClass();

                    if (defaultValidatorContext != null) {
                        defaultValidatorContext.dispose();
                    }
                    defaultValidatorContext = new DataBindingContext();
                    defaultValidatorContext.bindValue(SWTObservables.observeSelection(defaultValidator), EMFObservables
                            .observeValue(getEObject(), FormPackage.Literals.VALIDABLE__USE_DEFAULT_VALIDATOR));

                    defaultValidatorContext.bindValue(SWTObservables.observeSelection(defaultValidatorIsBelow),
                            EMFObservables.observeValue(getEObject(), FormPackage.Literals.VALIDABLE__BELOW));

                    defaultValidatorContext.bindValue(SWTObservables.observeSelection(defaultValidatorIsAbove),
                            EMFObservables.observeValue(getEObject(), FormPackage.Literals.VALIDABLE__BELOW),
                            new UpdateValueStrategy().setConverter(converter),
                            new UpdateValueStrategy().setConverter(converter));

                    defaultValidator.setVisible(
                            lastEObject instanceof Widget && !(lastEObject instanceof MultipleValuatedFormField));
                    positionComp.setVisible(lastEObject instanceof Widget && defaultValidator.getSelection());

                    /*
                     * Use pattern master-detail databinding in order to be up to date with the selection in the FilteredTreeViewer
                     */

                    //      labelField.setElement((Element) lastEObject);
                    //      labelField.reset();

                    /* Observe change in selection */
                    final IViewerObservableValue treeViewerObservaleValue = ViewersObservables
                            .observeSingleSelection(tableViewer);

                    //Observe the validator name property of the current selection
                    final IObservableValue nameDetailValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                            treeViewerObservaleValue, FormPackage.Literals.VALIDATOR__NAME);

                    //Bind the Text widget to the name detail
                    final ISWTObservableValue nameFieldObservableValue = SWTObservables.observeText(nameField, SWT.Modify);
                    context.bindValue(nameFieldObservableValue, nameDetailValue);
                    nameFieldObservableValue.addValueChangeListener(new IValueChangeListener() {

                        @Override
                        public void handleValueChange(final ValueChangeEvent event) {
                            /* update value in the treeviewer when the name changed. */
                            tableViewer.refresh(true);
                        }
                    });

                    //Observe the validator classes of the current selection
                    final IObservableValue emfValidatorClassObservableDetailValue = EMFObservables.observeDetailValue(
                            Realm.getDefault(), treeViewerObservaleValue, FormPackage.Literals.VALIDATOR__VALIDATOR_CLASS);

                    //Bind the Combo widget to the validator class detail
                    final IViewerObservableValue comboViewerObservable = ViewersObservables
                            .observeSingleSelection(comboViewerForValidatorClass);
                    final UpdateValueStrategy targetStrategy = new UpdateValueStrategy();
                    targetStrategy.setConverter(new Converter(ValidatorDescriptor.class, String.class) {

                        @Override
                        public Object convert(final Object from) {
                            if (from instanceof ValidatorDescriptor) {
                                return ((ValidatorDescriptor) from).getClassName();
                            }
                            return null;
                        }
                    });
                    final UpdateValueStrategy modelStrategy = new UpdateValueStrategy();
                    modelStrategy.setConverter(new Converter(String.class, ValidatorDescriptor.class) {

                        @Override
                        public Object convert(final Object from) {
                            if (from instanceof String) {
                                final Collection<ValidatorDescriptor> input = (Collection<ValidatorDescriptor>) comboViewerForValidatorClass
                                        .getInput();
                                for (final ValidatorDescriptor d : input) {
                                    if (d.getClassName().equals(from)) {
                                        return d;
                                    }
                                }
                            }
                            return null;
                        }
                    });

                    context.bindValue(comboViewerObservable, emfValidatorClassObservableDetailValue, targetStrategy,
                            modelStrategy);

                    /*
                     * When value changed,
                     * look if need to enabled/disabled some widgets
                     */
                    comboViewerObservable.addValueChangeListener(new IValueChangeListener() {

                        @Override
                        public void handleValueChange(final ValueChangeEvent event) {
                            updateFieldDependingOnValidatorClass();
                        }
                    });

                    final IObservableValue htmlDetailValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                            treeViewerObservaleValue, FormPackage.Literals.VALIDATOR__HTML_CLASS);
                    context.bindValue(SWTObservables.observeText(htmlClassField, SWT.Modify), htmlDetailValue);

                    final IObservableValue isBelowValue = EMFObservables.observeDetailValue(Realm.getDefault(),
                            treeViewerObservaleValue, FormPackage.Literals.VALIDATOR__BELOW_FIELD);
                    context.bindValue(SWTObservables.observeSelection(isBelow), isBelowValue);
                    context.bindValue(SWTObservables.observeSelection(isAbove), isBelowValue,
                            new UpdateValueStrategy().setConverter(converter),
                            new UpdateValueStrategy().setConverter(converter));
                }
            }
            //            else{
            //                comboViewerForValidatorClass.setInput(validatorStore.getValidatorDescriptors());
            //                Validator validator = (Validator) ((IStructuredSelection) tableViewer.getSelection()).getFirstElement() ;
            //                if(validator != null && validator.getValidatorClass() != null){
            //                    comboViewerForValidatorClass.setSelection(new StructuredSelection(validatorStore.getValidatorDescriptor(validator.getValidatorClass())));
            //                }
            //            }
        }
    }

    /**
     * Enable/disable Edit button and parameter field depending on Validator class
     */
    private void updateFieldDependingOnValidatorClass() {
        if (currentValidator != null) {
            final String validatorId = currentValidator.getValidatorClass();
            if (validatorId != null) {
                final ValidatorDescriptor descriptior = validatorStore.getValidatorDescriptor(validatorId);
                if (descriptior != null) {
                    final boolean enabled = descriptior.getHasParameter();
                    parameterExpressionViewer.getControl().setEnabled(enabled);
                    if (descriptior.getName().equals(GROOVY_TYPE)
                            && !currentValidator.getParameter().getReturnType().equals(Boolean.class.getName())) {
                        getEditingDomain().getCommandStack()
                                .execute(SetCommand.create(getEditingDomain(), currentValidator.getParameter(),
                                        ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, Boolean.class.getName()));
                        getEditingDomain().getCommandStack()
                                .execute(SetCommand.create(getEditingDomain(), currentValidator.getParameter(),
                                        ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE_FIXED, true));
                    } else {
                        if (descriptior.getName().equals(REGULAR_TYPE)) {
                            getEditingDomain().getCommandStack()
                                    .execute(SetCommand.create(getEditingDomain(), currentValidator.getParameter(),
                                            ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE, String.class.getName()));
                            getEditingDomain().getCommandStack()
                                    .execute(SetCommand.create(getEditingDomain(), currentValidator.getParameter(),
                                            ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE_FIXED, false));
                        }
                    }
                }
            }
        }
    }

    /**
     * get the Validable
     *
     * @return
     */
    private Validable getValidable() {
        return (Validable) getEObject();
    }

    private Button createAddValidatorButton(final Composite parent) {
        final Button addData = getWidgetFactory().createButton(parent, Messages.Add, SWT.FLAT);
        addData.setLayoutData(GridDataFactory.swtDefaults().hint(85, SWT.DEFAULT).create());
        addData.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                createValidator();
            }
        });
        return addData;
    }

    private Button createRemoveValidatorButton(final Composite parent) {
        final Button removeButton = getWidgetFactory().createButton(parent, Messages.Remove, SWT.FLAT);
        removeButton.setLayoutData(GridDataFactory.swtDefaults().hint(85, SWT.DEFAULT).create());
        removeButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(final Event event) {

                if (tableViewer != null && ((IStructuredSelection) tableViewer.getSelection()).size() > 0) {
                    final Object[] selection = ((IStructuredSelection) tableViewer.getSelection()).toArray();
                    final IUndoableOperation operation = new RemoveValidatorCommand(getEditingDomain(), getValidable(),
                            selection, ValidatorsPropertySection.this);
                    try {
                        OperationHistoryFactory.getOperationHistory().execute(operation, new NullProgressMonitor(), null);
                    } catch (final ExecutionException e) {
                        BonitaStudioLog.error(e);
                    }

                    currentValidator = null;
                    refresh();
                }
            }
        });
        return removeButton;
    }

    /**
     * create a new validator
     */
    private void createValidator() {
        final IUndoableOperation operation = new AddValidatorCommand(getEditingDomain(), getValidable(),
                generateValidatorDefaultName(), this);
        try {
            OperationHistoryFactory.getOperationHistory().execute(operation, new NullProgressMonitor(), null);
        } catch (final ExecutionException e) {
            BonitaStudioLog.error(e);
        }
    }

    /**
     * Create a validator with validatorClass and set the validatorClass in the list of resource to include in the application.
     * 
     * @param validatorClass
     */
    private void createValidator(final String validatorClass) {
        final IUndoableOperation operation = new AddValidatorCommand(getEditingDomain(), getValidable(),
                generateValidatorDefaultName(), validatorClass, this);
        try {
            OperationHistoryFactory.getOperationHistory().execute(operation, new NullProgressMonitor(), null);
        } catch (final ExecutionException e) {
            BonitaStudioLog.error(e);
        }
        //      EObject eobject = getEObject();
        //      while(!(eobject instanceof AbstractProcess)){
        //          eobject = eobject.eContainer();
        //      }
        //      AbstractProcess ap = (AbstractProcess) eobject;
        //      getEditingDomain().getCommandStack().execute(new AddCommand(getEditingDomain(), ap.getResourceValidators(), ValidatorRepositoryArtifact.ID_BASE+validatorClass));
    }

    /**
     * Generate a Validator name following <validableName>Validator<number>
     * 
     * @return
     */
    private String generateValidatorDefaultName() {
        String prefix = "";
        if (getValidable() instanceof Element) {
            prefix = ((Element) getValidable()).getName();
        }
        return prefix + Messages.ValidatorDefaultName + (getValidable().getValidators().size() + 1);
    }

    public void select(final Validator validator) {
        /* Unselect in order to avoid issues with databinding */
        tableViewer.setSelection(StructuredSelection.EMPTY);
        tableViewer.refresh(true);
        tableViewer.setSelection(new StructuredSelection(validator));
    }

    @Override
    public void dispose() {
        super.dispose();

        if (defaultValidatorContext != null) {
            defaultValidatorContext.dispose();
        }
        if (context != null) {
            context.dispose();
        }
        if (comboViewerForValidatorClass != null && !comboViewerForValidatorClass.getCombo().isDisposed()) {
            comboViewerForValidatorClass.getCombo().dispose();
        }
        if (defaultValidator != null && !defaultValidator.isDisposed()) {
            defaultValidator.dispose();
        }

    }

    @Override
    public String getSectionDescription() {
        // TODO Auto-generated method stub
        return null;
    }
}
