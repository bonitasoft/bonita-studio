/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.eclipse.jface.layout.GridDataFactory.fillDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.ui.databinding.ValidatorEvent;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectDataWizardPage extends WizardPage {

    private final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectModelStore;

    private final BusinessObjectData businessObjectData;

    private final Set<String> existingNames;

    private final DataAware container;

    private IObservableValue<Boolean> multipleObservableValue;

    private IObservableValue<String> classNameObservable;

    private IObservableValue<String> defaultValueReturnTypeObservable;

    private IObservableValue<String> defaultValueContentObservable;

    private final HintImageProvider imageProvider;

    private IObservableValue<String> defaultReturnTypeObservable;

    protected BusinessObjectDataWizardPage(final DataAware container, final BusinessObjectData businessObjectData,
            final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> businessObjectDefinitionStore,
            final Set<String> existingNames,
            final HintImageProvider imageProvider) {
        super(BusinessObjectDataWizardPage.class.getName());
        this.container = container;
        businessObjectModelStore = businessObjectDefinitionStore;
        this.existingNames = existingNames;
        this.businessObjectData = businessObjectData;
        this.imageProvider = imageProvider;
    }

    @Override
    public void createControl(final Composite parent) {
        final EMFDataBindingContext ctx = new EMFDataBindingContext();

        final Composite mainComposite = createMainComposite(parent);
        createNameControl(mainComposite, ctx);
        createDescriptionControl(mainComposite, ctx);
        createBusinessObjectTypeControl(mainComposite, ctx);
        createIsMultipleControl(mainComposite, ctx);
        createDefaultValueControl(mainComposite, ctx);
        defaultValueContentObservable = EMFObservables.observeValue(businessObjectData.getDefaultValue(),
                ExpressionPackage.Literals.EXPRESSION__CONTENT);
        ctx.addValidationStatusProvider(defaultValueReturnTypeValidator());
        WizardPageSupport.create(this, ctx);
        setControl(mainComposite);

    }

    private MultiValidator defaultValueReturnTypeValidator() {
        return new MultiValidator() {

            @Override
            protected IStatus validate() {
                final boolean isMultiple = Boolean.TRUE.equals(multipleObservableValue.getValue());
                final String className = (String) classNameObservable.getValue();
                if (!isNullOrEmpty(className) && !isNullOrEmpty((String) defaultValueContentObservable.getValue())) {
                    return isMultiple ? returnTypeIs(List.class.getName()) : returnTypeIs(className);
                }
                return ValidationStatus.ok();
            }
        };
    }

    private IStatus returnTypeIs(final String expectedReturnType) {
        final String actualReturnType = (String) defaultValueReturnTypeObservable.getValue();
        return expectedReturnType.equals(actualReturnType) ? ValidationStatus.ok() : ValidationStatus
                .error(NLS.bind(Messages.defaultValueReturnTypeValidationMessage, actualReturnType,
                        expectedReturnType));
    }

    protected ExpressionViewer createDefaultValueControl(final Composite mainComposite, final EMFDataBindingContext ctx) {
        final Label defaultValue = new Label(mainComposite, SWT.NONE);
        defaultValue.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());
        defaultValue.setText(Messages.defaultValue);

        final ExpressionViewer defaultValueExpressionViewer = new ExpressionViewer(mainComposite, SWT.BORDER);
        defaultValueExpressionViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        defaultValueExpressionViewer
                .addFilter(new AvailableExpressionTypeFilter(ExpressionConstants.SCRIPT_TYPE, ExpressionConstants.QUERY_TYPE,
                        ExpressionConstants.CONTRACT_INPUT_TYPE, ExpressionConstants.PARAMETER_TYPE));
        defaultValueExpressionViewer.addEditorFilter(ExpressionConstants.CONTRACT_INPUT_TYPE,
                ExpressionConstants.PARAMETER_TYPE);
        defaultValueExpressionViewer
                .setExpressionNameResolver(new DataDefaultValueExpressionNameResolver(businessObjectData));
        final ControlDecoration hint = new ControlDecoration(defaultValueExpressionViewer.getTextControl(), SWT.LEFT);//TODO: remove me for 7.0.0 GA
        hint.setShowOnlyOnFocus(false);
        hint.setImage(imageProvider.getHintImage());
        hint.setDescriptionText(
                Messages.defaultValueBusinessDataTooltip);

        defaultValueExpressionViewer.setInput(container);
        ctx.bindValue(ViewerProperties.singleSelection(Expression.class).observe(defaultValueExpressionViewer),
                EMFObservables.observeValue(businessObjectData, ProcessPackage.Literals.DATA__DEFAULT_VALUE));
        defaultReturnTypeObservable = PojoProperties.value("defaultReturnType", String.class).observe(defaultValueExpressionViewer);
        ctx.bindValue(defaultReturnTypeObservable, classNameObservable, neverUpdateValueStrategy().create(),
                updateValueStrategy().withConverter(listConverter()).create());
        ctx.bindValue(defaultReturnTypeObservable, multipleObservableValue, neverUpdateValueStrategy().create(),
                updateValueStrategy().withConverter(multipleConverter()).create());
        return defaultValueExpressionViewer;

    }

    private IConverter<String,String> listConverter() {
        return new Converter<>(String.class, String.class) {

            @Override
            public String convert(final String fromObject) {
                if (businessObjectData.isMultiple()) {
                    return List.class.getName();
                }
                return fromObject;
            }
        };
    }

    private IConverter<Boolean, String> multipleConverter() {
        return new Converter<>(Boolean.class, String.class) {

            @Override
            public String convert(final Boolean fromObject) {
                if (Boolean.TRUE.equals(fromObject)) {
                    return List.class.getName();
                }
                return classNameObservable.getValue();
            }
        };
    }

    protected void createIsMultipleControl(final Composite mainComposite, final EMFDataBindingContext ctx) {
        new Label(mainComposite, SWT.NONE);

        final Button multipleCheckbox = new Button(mainComposite, SWT.CHECK);
        multipleCheckbox.setLayoutData(fillDefaults().grab(true, false).create());
        multipleCheckbox.setText(Messages.multipleBusinessData);

        multipleObservableValue = EMFObservables.observeValue(businessObjectData, ProcessPackage.Literals.DATA__MULTIPLE);
        ctx.bindValue(WidgetProperties.buttonSelection().observe(multipleCheckbox),
                multipleObservableValue);
    }

    protected void createBusinessObjectTypeControl(final Composite mainComposite,
            final EMFDataBindingContext ctx) {
        final Label businessObjectLabel = new Label(mainComposite, SWT.NONE);
        businessObjectLabel.setLayoutData(fillDefaults().align(SWT.END, SWT.CENTER).create());
        businessObjectLabel.setText(Messages.businessObject + " *");

        final Composite comboComposite = new Composite(mainComposite, SWT.NONE);
        comboComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 0).create());
        comboComposite.setLayoutData(fillDefaults().grab(true, false).create());

        final ComboViewer businessObjectComboViewer = new ComboViewer(comboComposite, SWT.READ_ONLY | SWT.BORDER);
        businessObjectComboViewer.getControl().setLayoutData(fillDefaults().grab(true, false).create());
        businessObjectComboViewer.setContentProvider(new ObservableListContentProvider<BusinessObject>());
        businessObjectComboViewer.setLabelProvider(businessObjectLabelProvider());

        final IObservableList<BusinessObject> businessObjectsObservableList = new WritableList<>(getAllBusinessObjects(),
                BusinessObject.class);

        final IObservableValue<BusinessObject> observeSingleSelection = ViewerProperties.singleSelection(BusinessObject.class).observe(businessObjectComboViewer);
        businessObjectComboViewer.setInput(businessObjectsObservableList);

        classNameObservable = EMFObservables.observeValue(businessObjectData,
                ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME);
        ctx.bindValue(observeSingleSelection,
                classNameObservable,
                updateValueStrategy()
                        .withConverter(businessObjectToFQN())
                        .withValidator(mandatoryValidator(Messages.businessObject), ValidatorEvent.AFTER_CONVERT)
                        .create(),
                updateValueStrategy()
                        .withConverter(fqnToBusinessObject())
                        .create());

        defaultValueReturnTypeObservable = EMFObservables.observeValue(businessObjectData.getDefaultValue(),
                ExpressionPackage.Literals.EXPRESSION__RETURN_TYPE);

        final String className = businessObjectData.getClassName();
        if ((className == null || className.isEmpty()) && !businessObjectsObservableList.isEmpty()) {
            observeSingleSelection.setValue(businessObjectsObservableList.get(0));
        }
    }

    private LabelProvider businessObjectLabelProvider() {
        return new LabelProvider() {

            @Override
            public String getText(final Object element) {
                if (element instanceof BusinessObject) {
                    return ((BusinessObject) element).getQualifiedName();
                }
                return super.getText(element);
            }
        };
    }

    private Converter<String, BusinessObject> fqnToBusinessObject() {
        return new Converter<>(String.class, BusinessObject.class) {

            @Override
            public BusinessObject convert(String qualifiedName) {
                if (qualifiedName instanceof String) {
                   return businessObjectModelStore
                            .getChildByQualifiedName(qualifiedName)
                            .map(fStore -> fStore.getBusinessObject(qualifiedName))
                            .orElse(null);
                }
                return null;
            }
        };
    }

    private Converter<BusinessObject,String> businessObjectToFQN() {
        return new Converter<>(BusinessObject.class, String.class) {

            @Override
            public String convert(final BusinessObject fromObject) {
                if (fromObject instanceof BusinessObject) {
                    return fromObject.getQualifiedName();
                }
                return null;
            }

        };
    }

    protected List<BusinessObject> getAllBusinessObjects() {
        return Optional.ofNullable(businessObjectModelStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true))
                .map(BusinessObjectModelFileStore::getBusinessObjects)
                .orElse(new ArrayList<>());
    }

    protected Text createDescriptionControl(final Composite mainComposite,
            final EMFDataBindingContext ctx) {
        final Label descriptionLabel = new Label(mainComposite, SWT.NONE);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());
        descriptionLabel.setText(Messages.description);

        final Text descriptionText = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
        descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(600, 70).create());

        ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(descriptionText),
                EMFObservables.observeValue(businessObjectData, ProcessPackage.Literals.ELEMENT__DOCUMENTATION),
                updateValueStrategy().withValidator(maxLengthValidator(Messages.description, 255)).create(), null);
        return descriptionText;
    }

    protected Text createNameControl(final Composite mainComposite,
            final EMFDataBindingContext ctx) {
        final Label nameLabel = new Label(mainComposite, SWT.NONE);
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        nameLabel.setText(Messages.name + " *");

        final Text nameText = new Text(mainComposite, SWT.BORDER);
        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(nameText),
                EMFObservables.observeValue(businessObjectData,
                        ProcessPackage.Literals.ELEMENT__NAME),
                updateValueStrategy().withValidator(multiValidator()
                        .addValidator(maxLengthValidator(Messages.name, 50))
                        .addValidator(groovyReferenceValidator(Messages.name).startsWithLowerCase())
                        .addValidator(uniqueDataNameValidator()).create())
                        .create(),
                null);
        return nameText;
    }

    private IValidator<String> uniqueDataNameValidator() {
        return value -> {
                if (existingNames.contains(value)) {
                    return ValidationStatus.error(Messages.dataWithSameNameAlreadyExists);
                }
                return ValidationStatus.ok();
            };
    }

    protected Composite createMainComposite(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        return mainComposite;
    }

}
