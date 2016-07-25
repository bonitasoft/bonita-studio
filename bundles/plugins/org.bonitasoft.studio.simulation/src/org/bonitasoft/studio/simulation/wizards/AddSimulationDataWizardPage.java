/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.simulation.wizards;

import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.databinding.BonitaNumberFormat;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.WrappingValidator;
import org.bonitasoft.studio.common.properties.DynamicAddRemoveLineComposite;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.simulation.SimulationBoolean;
import org.bonitasoft.studio.model.simulation.SimulationData;
import org.bonitasoft.studio.model.simulation.SimulationDataContainer;
import org.bonitasoft.studio.model.simulation.SimulationFactory;
import org.bonitasoft.studio.model.simulation.SimulationLiteral;
import org.bonitasoft.studio.model.simulation.SimulationLiteralData;
import org.bonitasoft.studio.model.simulation.SimulationNumberData;
import org.bonitasoft.studio.model.simulation.SimulationNumberRange;
import org.bonitasoft.studio.model.simulation.SimulationPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.validation.StringToDoubleValidator;
import org.eclipse.core.internal.databinding.validation.StringToLongValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Baptiste Mesta
 */
public class AddSimulationDataWizardPage extends WizardPage {

    private final SelectionListener updateButtonSelectionListener = new SelectionAdapter() {

        @Override
        public void widgetSelected(final org.eclipse.swt.events.SelectionEvent e) {
            if (getContainer().getCurrentPage() != null) {
                getContainer().updateButtons();
            }
        };

    };

    private final SimulationData data;
    private GridData gd;
    private String dataDescription;
    private StackLayout sLayout;
    private Composite configStackComposite;
    private ComboViewer datatypeCombo;
    protected EClass dataClass;
    private final Map<EClass, Composite> configCompositeMap = new HashMap<EClass, Composite>();
    private DataBindingContext context;
    private String dataName;
    private Expression dataExpression;
    private boolean expressionBased = false;

    //boolean
    private double probabilityOfTrue;
    //number
    private final List<SimulationNumberRange> ranges = new ArrayList<SimulationNumberRange>();
    //literals
    private final List<SimulationLiteral> literals = new ArrayList<SimulationLiteral>();
    private final SimulationDataContainer element;
    private Button isExpressionBased;
    private Button isOtherBased;

    /**
     * @param element
     * @param container
     * @param simulationData
     */
    public AddSimulationDataWizardPage(final SimulationDataContainer element, final SimulationData simulationData) {
        super("add simulation data");
        this.element = element;
        data = simulationData;
        setTitle(Messages.AddSimulationDataWizardPage_title);
        this.setMessage(Messages.AddSimulationDataWizardPage_msg);
        setImageDescriptor(Pics.getWizban());
        if (data != null) {
            dataClass = data.eClass();
            dataDescription = data.getDescription();
            dataName = data.getName();
            if (data.getExpression() == null) {
                dataExpression = ExpressionFactory.eINSTANCE.createExpression();
            } else {
                dataExpression = EcoreUtil.copy(data.getExpression());
            }

            setExpressionBased(data.isExpressionBased());

            if (data instanceof SimulationBoolean) {
                probabilityOfTrue = ((SimulationBoolean) data).getProbabilityOfTrue();
            }
            if (data instanceof SimulationNumberData) {
                for (final SimulationNumberRange range : ((SimulationNumberData) data).getRanges()) {
                    ranges.add(EcoreUtil.copy(range));
                }
            }
            if (data instanceof SimulationLiteralData) {
                for (final SimulationLiteral literal : ((SimulationLiteralData) data).getLiterals()) {
                    literals.add(EcoreUtil.copy(literal));
                }
            }
        } else {
            dataExpression = ExpressionFactory.eINSTANCE.createExpression();
            dataName = generateDataName();
        }

    }

    private String generateDataName() {
        return Messages.Data.toLowerCase()
                + (ModelHelper.getAllItemsOfType(ModelHelper.getParentProcess(element), SimulationPackage.eINSTANCE.getSimulationData()).size() + 1);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);

        context = new DataBindingContext();
        WizardPageSupport.create(this, context);
        composite.setLayout(new GridLayout(2, false));

        createNameAndDescription(composite);

        createTypeCombo(composite);

        configStackComposite = new Composite(composite, SWT.NONE);
        sLayout = new StackLayout();
        configStackComposite.setLayout(sLayout);
        configStackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).hint(SWT.DEFAULT, 200).create());

        final Composite booleanComposite = createBooleanConfigurationComposite(configStackComposite);
        configCompositeMap.put(SimulationPackage.eINSTANCE.getSimulationBoolean(), booleanComposite);

        final Composite literalsComposite = createLiteralsConfigurationComposite(configStackComposite);
        configCompositeMap.put(SimulationPackage.eINSTANCE.getSimulationLiteralData(), literalsComposite);

        final Composite numberComposite = createNumberConfigurationComposite(configStackComposite);
        configCompositeMap.put(SimulationPackage.eINSTANCE.getSimulationNumberData(), numberComposite);

        context.bindValue(SWTObservables.observeVisible(configStackComposite), SWTObservables.observeSelection(isExpressionBased), null,
                new UpdateValueStrategy().setConverter(new org.eclipse.core.databinding.conversion.Converter(Boolean.class, Boolean.class) {

                    @Override
                    public Object convert(final Object fromObject) {
                        return !((Boolean) fromObject);
                    }
                }));

        /*
         * So like that it forces call to isPageCOmplete after that the model is updated.
         * what would be even better it to implement validator on each related bindings instead of doing the work in isPageComplete
         */
        WizardPageSupport.create(this, context);

        changeDataConfigurationComposite((EClass) ((IStructuredSelection) datatypeCombo.getSelection()).getFirstElement());
        setControl(composite);

    }

    /**
     * @param composite
     * @return
     */
    private Composite createNumberConfigurationComposite(final Composite composite) {

        final ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.V_SCROLL);
        scrolledComposite.setLayout(new FillLayout());
        final Composite typeComposite = new Composite(scrolledComposite, SWT.NONE);
        typeComposite.setLayout(new GridLayout(1, false));
        final DynamicAddRemoveLineComposite literalsComposite = new DynamicAddRemoveLineComposite(typeComposite, SWT.NONE) {

            @Override
            protected void lineRemoved(final int i) {
                ranges.remove(i);

                getShell().pack(true);
                getShell().layout(true, true);

                scrolledComposite.setMinSize(typeComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
                scrolledComposite.setAlwaysShowScrollBars(false);
                scrolledComposite.setExpandHorizontal(true);
                scrolledComposite.setExpandVertical(true);
                scrolledComposite.setContent(typeComposite);
                if (getContainer().getCurrentPage() != null) {
                    getContainer().updateButtons();
                }
            }

            @Override
            protected void lineAdded(final int i) {
                getShell().pack(true);
                getShell().layout(true, true);

                scrolledComposite.setMinSize(typeComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
                scrolledComposite.setAlwaysShowScrollBars(false);
                scrolledComposite.setExpandHorizontal(true);
                scrolledComposite.setExpandVertical(true);
                scrolledComposite.setContent(typeComposite);
                if (getContainer().getCurrentPage() != null) {
                    getContainer().updateButtons();
                }
            }

            @Override
            protected Button createAddButton(final Composite parent) {
                final Button button = new Button(parent, SWT.FLAT);
                button.setText(Messages.addARange);
                return button;
            }

            @Override
            protected TabbedPropertySheetWidgetFactory getWidgetFactory() {
                return null;
            }

            @Override
            protected Composite getTopComposite() {
                return scrolledComposite;
            }

            @Override
            protected Control createLineComposite(final Composite parent, final Object object) {
                SimulationNumberRange range;
                if (object == null) {
                    range = SimulationFactory.eINSTANCE.createSimulationNumberRange();
                    ranges.add(range);
                } else {
                    range = (SimulationNumberRange) object;
                }
                final Composite composite = new Composite(parent, SWT.NONE);
                composite.setLayout(new GridLayout(6, false));
                //min
                final Label minLabel = new Label(composite, SWT.NONE);
                minLabel.setText(Messages.AddSimulationDataWizardPage_min);
                final Text minText = new Text(composite, SWT.BORDER);
                minText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).hint(100, SWT.DEFAULT).create());
                final ControlDecoration minControlDecoration = new ControlDecoration(minText, SWT.LEFT | SWT.TOP);
                FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
                        .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
                minControlDecoration.setImage(fieldDecoration.getImage());
                minControlDecoration.setDescriptionText(Messages.mustBeANumber);
                //max
                final Label maxLiteral = new Label(composite, SWT.NONE);
                maxLiteral.setText(Messages.AddSimulationDataWizardPage_max);
                final Text maxText = new Text(composite, SWT.BORDER);
                maxText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).hint(100, SWT.DEFAULT).create());
                final ControlDecoration maxControlDecoration = new ControlDecoration(maxText, SWT.LEFT | SWT.TOP);
                fieldDecoration = FieldDecorationRegistry.getDefault()
                        .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
                maxControlDecoration.setImage(fieldDecoration.getImage());
                maxControlDecoration.setDescriptionText(Messages.mustBeANumber);
                //proba
                final Label probaLabel = new Label(composite, SWT.NONE);
                probaLabel.setText(Messages.AddSimulationDataWizardPage_probability);
                final Text probaText = new Text(composite, SWT.BORDER);
                probaText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).hint(40, SWT.DEFAULT).create());
                //                ControlDecoration controlDecoration = new ControlDecoration(probaText, SWT.LEFT|SWT.TOP);
                //                fieldDecoration = FieldDecorationRegistry.getDefault()
                //                        .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
                //                controlDecoration.setImage(fieldDecoration.getImage());
                //                controlDecoration.setDescriptionText(Messages.mustBeAPercentage);
                //repartitionType

                //				Label repartitionLabel = new Label(composite, SWT.NONE);
                //				repartitionLabel.setText(Messages.AddSimulationDataWizardPage_repartition);
                //				ComboViewer comboViewer = new ComboViewer(new Combo(composite, SWT.READ_ONLY));
                //				comboViewer.setContentProvider(ArrayContentProvider.getInstance());
                //				comboViewer.setLabelProvider(new LabelProvider(){
                //					@Override
                //					public String getText(Object element) {
                //						return ((RepartitionType)element).getName();
                //					}
                //				});
                //				comboViewer.setInput(RepartitionType.VALUES);

                context.bindValue(
                        SWTObservables.observeText(minText, SWT.Modify),
                        EMFObservables.observeValue(range, SimulationPackage.Literals.SIMULATION_NUMBER_RANGE__MIN),
                        new UpdateValueStrategy().setConverter(StringToNumberConverter.toLong(false))
                                .setAfterGetValidator(
                                        new WrappingValidator(minControlDecoration, new StringToLongValidator(StringToNumberConverter.toLong(false))))
                        , new UpdateValueStrategy().setConverter(NumberToStringConverter.fromLong(false)));
                context.bindValue(
                        SWTObservables.observeText(maxText, SWT.Modify),
                        EMFObservables.observeValue(range, SimulationPackage.Literals.SIMULATION_NUMBER_RANGE__MAX),
                        new UpdateValueStrategy().setConverter(StringToNumberConverter.toLong(false))
                                .setAfterGetValidator(
                                        new WrappingValidator(maxControlDecoration, new StringToLongValidator(StringToNumberConverter.toLong(false))))
                        , new UpdateValueStrategy().setConverter(NumberToStringConverter.fromLong(false)));
                final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
                targetToModel.setConverter(StringToNumberConverter.toDouble(BonitaNumberFormat.getPercentInstance(), true));
                targetToModel.setAfterGetValidator(new ProbabilityValidator(new StringToDoubleValidator(StringToNumberConverter.toDouble(
                        BonitaNumberFormat.getPercentInstance(), false))));
                final Binding provider = context.bindValue(SWTObservables.observeText(probaText, SWT.Modify),
                        EMFObservables.observeValue(range, SimulationPackage.Literals.SIMULATION_NUMBER_RANGE__PROBABILITY),
                        targetToModel
                        , new UpdateValueStrategy().setConverter(NumberToStringConverter.fromDouble(BonitaNumberFormat.getPercentInstance(), false)));
                //context.bindValue(ViewersObservables.observeSingleSelection(comboViewer), EMFObservables.observeValue(range, SimulationPackage.Literals.SIMULATION_NUMBER_RANGE__REPARTITION_TYPE));

                //				minText.addModifyListener(updateButtonModifyListener);
                //				maxText.addModifyListener(updateButtonModifyListener) ;
                //				probaText.addModifyListener(updateButtonModifyListener) ;
                ControlDecorationSupport.create(provider, SWT.TOP | SWT.LEFT);

                return composite;
            }
        };
        for (final SimulationNumberRange range : ranges) {
            literalsComposite.addLine(range);
        }
        literalsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        scrolledComposite.setMinSize(typeComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        scrolledComposite.setAlwaysShowScrollBars(false);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setContent(typeComposite);
        return scrolledComposite;
    }

    /**
     * @param composite
     * @return
     */
    private Composite createLiteralsConfigurationComposite(final Composite composite) {

        final ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.V_SCROLL);
        scrolledComposite.setLayout(new FillLayout());

        final Composite typeComposite = new Composite(scrolledComposite, SWT.NONE);
        typeComposite.setLayout(new GridLayout(1, false));
        final DynamicAddRemoveLineComposite literalsComposite = new DynamicAddRemoveLineComposite(typeComposite, SWT.NONE) {

            @Override
            protected void lineRemoved(final int i) {
                literals.remove(i);

                getShell().pack(true);
                getShell().layout(true, true);

                scrolledComposite.setMinSize(typeComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
                scrolledComposite.setAlwaysShowScrollBars(false);
                scrolledComposite.setExpandHorizontal(true);
                scrolledComposite.setExpandVertical(true);
                scrolledComposite.setContent(typeComposite);

                getContainer().updateButtons();
            }

            @Override
            protected void lineAdded(final int i) {

                getShell().pack(true);
                getShell().layout(true, true);

                scrolledComposite.setMinSize(typeComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
                scrolledComposite.setAlwaysShowScrollBars(false);
                scrolledComposite.setExpandHorizontal(true);
                scrolledComposite.setExpandVertical(true);
                scrolledComposite.setContent(typeComposite);
                if (getContainer().getCurrentPage() != null) {
                    getContainer().updateButtons();
                }

            }

            @Override
            protected Button createAddButton(final Composite parent) {
                final Button button = new Button(parent, SWT.FLAT);
                button.setText(Messages.addALiteral);
                return button;
            }

            @Override
            protected TabbedPropertySheetWidgetFactory getWidgetFactory() {
                return null;
            }

            @Override
            protected Composite getTopComposite() {
                return scrolledComposite;
            }

            @SuppressWarnings("restriction")
            @Override
            protected Control createLineComposite(final Composite parent, final Object object) {
                SimulationLiteral literal;
                if (object == null) {
                    literal = SimulationFactory.eINSTANCE.createSimulationLiteral();
                    literals.add(literal);
                } else {
                    literal = (SimulationLiteral) object;
                }
                final Composite composite = new Composite(parent, SWT.NONE);
                composite.setLayout(new GridLayout(4, false));
                final Label literalLabel = new Label(composite, SWT.NONE);
                literalLabel.setText(Messages.dataNameLabel);
                final Text literalText = new Text(composite, SWT.BORDER);
                literalText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).hint(200, SWT.DEFAULT).create());
                //literalText.addModifyListener(updateButtonModifyListener) ;
                final Label probaLabel = new Label(composite, SWT.NONE);
                probaLabel.setText(Messages.AddSimulationDataWizardPage_probability);
                final Text probaText = new Text(composite, SWT.BORDER);
                probaText.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).hint(60, SWT.DEFAULT).create());
                //                ControlDecoration controlDecoration = new ControlDecoration(probaText, SWT.LEFT|SWT.TOP);
                //                FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
                //                        .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
                //                controlDecoration.setImage(fieldDecoration.getImage());
                //                controlDecoration.setDescriptionText(Messages.mustBeAPercentage);
                context.bindValue(SWTObservables.observeText(literalText, SWT.Modify),
                        EMFObservables.observeValue(literal, SimulationPackage.Literals.SIMULATION_LITERAL__VALUE));
                final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
                targetToModel.setConverter(StringToNumberConverter.toDouble(BonitaNumberFormat.getPercentInstance(), true));
                targetToModel.setAfterGetValidator(new ProbabilityValidator(new StringToDoubleValidator(StringToNumberConverter.toDouble(
                        BonitaNumberFormat.getPercentInstance(), false))));
                final Binding provider = context.bindValue(SWTObservables.observeText(probaText, SWT.Modify),
                        EMFObservables.observeValue(literal, SimulationPackage.Literals.SIMULATION_LITERAL__PROBABILITY),
                        targetToModel
                        , new UpdateValueStrategy().setConverter(NumberToStringConverter.fromDouble(BonitaNumberFormat.getPercentInstance(), false)));

                ControlDecorationSupport.create(provider, SWT.TOP | SWT.LEFT);
                probaText.addModifyListener(new ModifyListener() {

                    @Override
                    public void modifyText(final ModifyEvent e) {
                        if (getContainer().getCurrentPage() != null) {
                            getContainer().updateButtons();
                        }

                    }
                });
                return composite;
            }
        };
        for (final SimulationLiteral literal : literals) {
            literalsComposite.addLine(literal);
        }
        literalsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        scrolledComposite.setMinSize(typeComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        scrolledComposite.setAlwaysShowScrollBars(false);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        scrolledComposite.setContent(typeComposite);
        return scrolledComposite;
    }

    /**
     * @param composite
     * @return
     */
    private Composite createBooleanConfigurationComposite(final Composite composite) {

        final Composite booleanComposite = new Composite(composite, SWT.NONE);
        booleanComposite.setLayout(new GridLayout(2, false));
        final Label nameLabel = new Label(booleanComposite, SWT.NONE);
        nameLabel.setText(Messages.AddSimulationDataWizardPage_ProbabilityOfTrueLabel);
        final Text labelText = new Text(booleanComposite, SWT.BORDER);
        labelText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        //        ControlDecoration controlDecoration = new ControlDecoration(labelText, SWT.LEFT|SWT.TOP);
        //        FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault()
        //                .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
        //        controlDecoration.setImage(fieldDecoration.getImage());
        //        controlDecoration.setDescriptionText(Messages.mustBeAPercentage);
        final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setConverter(StringToNumberConverter.toDouble(BonitaNumberFormat.getPercentInstance(), true));
        targetToModel.setAfterGetValidator(new ProbabilityValidator(new StringToDoubleValidator(StringToNumberConverter.toDouble(
                BonitaNumberFormat.getPercentInstance(), false))));

        final Binding provider = context.bindValue(SWTObservables.observeText(labelText,
                SWT.Modify), PojoObservables.observeValue(this, "probabilityOfTrue"),
                targetToModel
                , new UpdateValueStrategy().setConverter(NumberToStringConverter.fromDouble(BonitaNumberFormat.getPercentInstance(), true)));

        ControlDecorationSupport.create(provider, SWT.TOP | SWT.LEFT);
        return booleanComposite;
    }

    private void createNameAndDescription(final Composite composite) {
        final Label nameLabel = new Label(composite, SWT.NONE);
        nameLabel.setText(Messages.dataNameLabel);
        final Text labelText = new Text(composite, SWT.BORDER);
        labelText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        final MultiValidator multiValidator = new MultiValidator();
        // Add an validator so that age can only be a number
        multiValidator.addValidator(new IValidator() {

            Set<String> existingDataNames = null;

            @Override
            public IStatus validate(final Object arg0) {
                if (existingDataNames == null) {
                    existingDataNames = new HashSet<String>();
                    if (element != null) {
                        for (final SimulationData simuData : element.getSimulationData()) {
                            existingDataNames.add(simuData.getName());
                        }
                    }
                }

                if (existingDataNames.contains(labelText.getText())) {

                    return ValidationStatus.error("Data name already exists.");
                }
                return ValidationStatus.ok();
            }

        });
        multiValidator.addValidator(groovyReferenceValidator(Messages.name).startsWithLowerCase().create());
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setBeforeSetValidator(multiValidator);

        final Binding bindingDataName = context.bindValue(SWTObservables.observeText(labelText,
                SWT.Modify), PojoObservables.observeValue(this, "dataName"), strategy, null);

        ControlDecorationSupport.create(bindingDataName, SWT.TOP | SWT.LEFT);
        //labelText.addModifyListener(updateButtonModifyListener) ;

        final Label isExpressionLabel = new Label(composite, SWT.NONE);
        isExpressionLabel.setText(Messages.BasedOn);

        final Composite radioBasedComposite = new Composite(composite, SWT.NONE);
        radioBasedComposite.setLayout(new GridLayout(2, true));

        isExpressionBased = new Button(radioBasedComposite, SWT.RADIO);
        isExpressionBased.setText(Messages.Expression);
        isExpressionBased.setSelection(expressionBased);
        isOtherBased = new Button(radioBasedComposite, SWT.RADIO);
        isOtherBased.setSelection(!expressionBased);
        isOtherBased.setText(Messages.AddSimulationDataWizardPage_probability);
        context.bindValue(SWTObservables.observeSelection(isExpressionBased), PojoObservables.observeValue(this, "expressionBased"));
        context.bindValue(SWTObservables.observeSelection(isOtherBased), PojoObservables.observeValue(this, "expressionBased"),
                new UpdateValueStrategy().setConverter(new Converter(Boolean.class, Boolean.class) {

                    @Override
                    public Object convert(final Object fromObject) {
                        return !(Boolean) fromObject;
                    }
                }), new UpdateValueStrategy().setConverter(new Converter(Boolean.class, Boolean.class) {

                    @Override
                    public Object convert(final Object fromObject) {
                        return !(Boolean) fromObject;
                    }
                }));

        final Label expressionLabel = new Label(composite, SWT.NONE);
        expressionLabel.setText(Messages.Expression);

        final ExpressionViewer expressionViewer = new ExpressionViewer(composite, SWT.BORDER);
        expressionViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        if (element != null) {
            expressionViewer.setInput(element);
        } else {
            expressionViewer.setInput(data.eContainer());
        }
        expressionViewer.addFilter(new AvailableExpressionTypeFilter(new String[] { ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE,
                ExpressionConstants.SCRIPT_TYPE, ExpressionConstants.SIMULATION_VARIABLE_TYPE }));
        expressionViewer.setSelection(new StructuredSelection(dataExpression));

        context.bindValue(SWTObservables.observeVisible(expressionLabel), SWTObservables.observeSelection(isExpressionBased));
        context.bindValue(SWTObservables.observeVisible(expressionViewer.getControl()), SWTObservables.observeSelection(isExpressionBased));

        isOtherBased.addSelectionListener(updateButtonSelectionListener);
        isExpressionBased.addSelectionListener(updateButtonSelectionListener);

    }

    /**
     * @param composite
     * @return the 3rd column composite
     */
    private void createTypeCombo(final Composite composite) {
        final Label datatypeLabel = new Label(composite, SWT.NONE);
        datatypeLabel.setText(Messages.datatypeLabel);

        datatypeCombo = new ComboViewer(new Combo(composite, SWT.READ_ONLY | SWT.BORDER));
        datatypeCombo.setContentProvider(ArrayContentProvider.getInstance());
        datatypeCombo.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element.equals(SimulationPackage.eINSTANCE.getSimulationBoolean())) {
                    return Messages.SimulationBoolean;
                }
                if (element.equals(SimulationPackage.eINSTANCE.getSimulationLiteralData())) {
                    return Messages.SimulationLiteralData;
                }
                if (element.equals(SimulationPackage.eINSTANCE.getSimulationNumberData())) {
                    return Messages.SimulationNumberData;
                }
                return ((EClass) element).getName();
            }
        });
        datatypeCombo.setInput(getAvailableDataTypes());
        if (dataClass == null) {
            dataClass = SimulationPackage.eINSTANCE.getSimulationBoolean();
        }
        gd = new GridData();
        gd.horizontalAlignment = GridData.FILL;
        gd.verticalAlignment = GridData.CENTER;
        gd.widthHint = 150;
        datatypeCombo.getControl().setLayoutData(gd);

        if (data != null) {
            datatypeCombo.setSelection(new StructuredSelection(data.eClass()));
            datatypeCombo.getControl().setEnabled(false);
        } else {
            datatypeCombo.setSelection(new StructuredSelection(datatypeCombo.getElementAt(0)));
        }
        datatypeCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                dataClass = (EClass) ((IStructuredSelection) datatypeCombo.getSelection()).getFirstElement();
                changeDataConfigurationComposite(dataClass);
                setPageComplete(isPageComplete());
            }
        });

        //context.bindValue(SWTObservables.observeText(isOtherBased),SWTObservables.observeText(datatypeCombo.getControl()));

    }

    @Override
    public boolean isPageComplete() {
        if (!isExpressionBased()) {
            if (SimulationPackage.eINSTANCE.getSimulationLiteralData().equals(dataClass)) {
                double total = 0;
                boolean emptyValue = false;
                for (final SimulationLiteral l : getLiterals()) {
                    total = total + l.getProbability();
                    final String simuValue = l.getValue();
                    if (simuValue == null || simuValue.trim().length() == 0) {
                        emptyValue = true;
                    }
                }
                if (total != 1.0) {
                    setErrorMessage(Messages.AddSimulationDataWizardPage_Error_Proba);
                    return false;
                }
                if (emptyValue) {
                    setErrorMessage(Messages.AddSimulationDataWizardPage_Error_EmptyLiteral);
                    return false;
                }

            } else if (dataClass.equals(SimulationPackage.eINSTANCE.getSimulationNumberData())) {
                double total = 0;
                for (final SimulationNumberRange l : getRanges()) {
                    total = total + l.getProbability();
                }
                if (total != 1.0) {
                    setErrorMessage(Messages.AddSimulationDataWizardPage_Error_Proba);
                    return false;
                }
                for (final SimulationNumberRange l : getRanges()) {
                    if (l.getMin() >= l.getMax()) {
                        setErrorMessage(Messages.AddSimulationDataWizardPage_Error_MinMax);
                        return false;
                    }
                }
            }
        }
        return super.isPageComplete();
    }

    /**
     * @param dataClass2
     */
    protected void changeDataConfigurationComposite(final EClass dataClass2) {
        sLayout.topControl = configCompositeMap.get(dataClass2);

        configStackComposite.layout();

    }

    /**
     * @return
     */
    private List<EClass> getAvailableDataTypes() {
        final ArrayList<EClass> list = new ArrayList<EClass>();
        list.add(SimulationPackage.eINSTANCE.getSimulationBoolean());
        list.add(SimulationPackage.eINSTANCE.getSimulationLiteralData());
        list.add(SimulationPackage.eINSTANCE.getSimulationNumberData());
        return list;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    @Override
    public void dispose() {
        if (context != null) {
            context.dispose();
        }
        super.dispose();
    }

    /**
     * @param dataName the dataName to set
     */
    public void setDataName(final String dataName) {
        this.dataName = dataName;
    }

    /**
     * @return the dataName
     */
    public String getDataName() {
        return dataName;
    }

    /**
     * @param dataDescription the dataDescription to set
     */
    public void setDataDescription(final String dataDescription) {
        this.dataDescription = dataDescription;
    }

    /**
     * @return the dataDescription
     */
    public String getDataDescription() {
        return dataDescription;
    }

    /**
     * @param probabilityOfTrue the probabilityOfTrue to set
     */
    public void setProbabilityOfTrue(final double probabilityOfTrue) {
        this.probabilityOfTrue = probabilityOfTrue;
    }

    /**
     * @return the probabilityOfTrue
     */
    public double getProbabilityOfTrue() {
        return probabilityOfTrue;
    }

    /**
     * @return the dataExpression
     */
    public Expression getDataExpression() {
        return dataExpression;
    }

    public EClass getDataClass() {
        return dataClass;
    }

    /**
     * @return the ranges
     */
    public List<SimulationNumberRange> getRanges() {
        return ranges;
    }

    /**
     * @return the literals
     */
    public List<SimulationLiteral> getLiterals() {
        return literals;
    }

    /**
     * @param expressionBased the expressionBased to set
     */
    public void setExpressionBased(final boolean expressionBased) {
        this.expressionBased = expressionBased;
    }

    /**
     * @return the expressionBased
     */
    public boolean isExpressionBased() {
        return expressionBased;
    }

}
