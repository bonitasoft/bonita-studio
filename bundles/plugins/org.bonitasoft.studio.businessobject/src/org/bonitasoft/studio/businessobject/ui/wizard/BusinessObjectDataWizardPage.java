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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.handler.ManageBusinessObjectHandler;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;

import org.bonitasoft.engine.bdm.model.BusinessObject;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectDataWizardPage extends WizardPage {

    private BusinessObjectModelRepositoryStore businessObjectModelStore;

    private BusinessObjectData businessObjectData;

    private Set<String> existingNames;

    protected BusinessObjectDataWizardPage(BusinessObjectData businessObjectData, BusinessObjectModelRepositoryStore businessObjectDefinitionStore,
            Set<String> existingNames) {
        super(BusinessObjectDataWizardPage.class.getName());
        this.businessObjectModelStore = businessObjectDefinitionStore;
        this.existingNames = existingNames;
        this.businessObjectData = businessObjectData;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        final EMFDataBindingContext ctx = createDatabindingContext();
        WizardPageSupport.create(this, ctx);
        final Composite mainComposite = createMainComposite(parent);
        createNameLabelAndText(mainComposite, ctx);
        createDescriptionLabelAndText(mainComposite, ctx);
        createIsMultiple(mainComposite, ctx);
        createBusinessObjectTypeLabelAndCombo(mainComposite, ctx);
        setControl(mainComposite);
    }

    protected void createIsMultiple(Composite mainComposite, EMFDataBindingContext ctx) {
        new Label(mainComposite, SWT.NONE);

        final Button multipleCheckbox = new Button(mainComposite, SWT.CHECK);
        multipleCheckbox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        multipleCheckbox.setText(Messages.multipleBusinessData);

        ctx.bindValue(SWTObservables.observeSelection(multipleCheckbox),
                EMFObservables.observeValue(businessObjectData, ProcessPackage.Literals.DATA__MULTIPLE));
    }

    protected void createBusinessObjectTypeLabelAndCombo(Composite mainComposite,
            EMFDataBindingContext ctx) {
        final Label businessObjectLabel = new Label(mainComposite, SWT.NONE);
        businessObjectLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        businessObjectLabel.setText(Messages.businessObject + " *");

        final Composite comboComposite = new Composite(mainComposite, SWT.NONE);
        comboComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
        comboComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final ComboViewer businessObjectComboViewer = new ComboViewer(comboComposite, SWT.READ_ONLY | SWT.BORDER);
        businessObjectComboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        businessObjectComboViewer.setContentProvider(new ObservableListContentProvider());
        businessObjectComboViewer.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(Object element) {
                if (element instanceof BusinessObject) {
                    return NamingUtils.getSimpleName(((BusinessObject) element).getQualifiedName());
                }
                return super.getText(element);
            }
        });

        final WritableList businessObjectsObservableList = new WritableList(getAllBusinessObjects(), BusinessObject.class);

        final IViewerObservableValue observeSingleSelection = ViewersObservables.observeSingleSelection(businessObjectComboViewer);

        final Link createBusinessObjectLink = new Link(comboComposite, SWT.NO_FOCUS);
        createBusinessObjectLink.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
        createBusinessObjectLink.setText(Messages.createNewBusinessObject);
        createBusinessObjectLink.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Object value = observeSingleSelection.getValue();
                openNewBusinessObjectWizard();
                List<BusinessObject> allBusinessObjects = getAllBusinessObjects();
                businessObjectsObservableList.clear();
                businessObjectsObservableList.addAll(allBusinessObjects);
                if (value != null && allBusinessObjects.contains(value)) {
                    observeSingleSelection.setValue(value);
                } else if (!allBusinessObjects.isEmpty()) {
                    observeSingleSelection.setValue(businessObjectsObservableList.get(0));
                }
            }
        });
        businessObjectComboViewer.setInput(businessObjectsObservableList);

        final UpdateValueStrategy targetClassNameStrategy = new UpdateValueStrategy();
        targetClassNameStrategy.setConverter(new Converter(BusinessObject.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject instanceof BusinessObject) {
                    return ((BusinessObject) fromObject).getQualifiedName();
                }
                return null;
            }

        });

        final UpdateValueStrategy modelClassNameStrategy = new UpdateValueStrategy();
        targetClassNameStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.businessObject));
        modelClassNameStrategy.setConverter(new Converter(String.class, BusinessObject.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject instanceof String) {
                    String qualifiedName = fromObject.toString();
                    BusinessObjectModelFileStore childByQualifiedName = businessObjectModelStore.getChildByQualifiedName(qualifiedName);
                    if (childByQualifiedName != null) {
                        return childByQualifiedName.getBusinessObject(qualifiedName);
                    }
                }
                return null;
            }

        });

        ctx.bindValue(observeSingleSelection,
                EMFObservables.observeValue(getBusinessObjectData(), ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME),
                targetClassNameStrategy,
                modelClassNameStrategy);

        String className = getBusinessObjectData().getClassName();
        if ((className == null || className.isEmpty()) && !businessObjectsObservableList.isEmpty()) {
            observeSingleSelection.setValue(businessObjectsObservableList.get(0));
        }
    }

    protected void openNewBusinessObjectWizard() {
        try {
            new ManageBusinessObjectHandler().execute(null);
        } catch (ExecutionException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected List<BusinessObject> getAllBusinessObjects() {
        List<BusinessObject> result = new ArrayList<BusinessObject>();
        for (BusinessObjectModelFileStore def : businessObjectModelStore.getChildren()) {
            List<BusinessObject> bo = def.getBusinessObjects();
            result.addAll(bo);
        }
        return result;
    }

    protected void createDescriptionLabelAndText(Composite mainComposite,
            EMFDataBindingContext ctx) {
        final Label descriptionLabel = new Label(mainComposite, SWT.NONE);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());
        descriptionLabel.setText(Messages.description);

        final Text descriptionText = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.WRAP);
        descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 70).create());

        UpdateValueStrategy targetStrategy = new UpdateValueStrategy();
        targetStrategy.setAfterGetValidator(new InputLengthValidator(Messages.description, 255));

        ctx.bindValue(SWTObservables.observeText(descriptionText, SWT.Modify),
                EMFObservables.observeValue(getBusinessObjectData(), ProcessPackage.Literals.ELEMENT__DOCUMENTATION), targetStrategy, null);
    }

    protected void createNameLabelAndText(Composite mainComposite,
            EMFDataBindingContext ctx) {
        final Label nameLabel = new Label(mainComposite, SWT.NONE);
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        nameLabel.setText(Messages.name + " *");

        final Text nameText = new Text(mainComposite, SWT.BORDER);
        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        UpdateValueStrategy targetStrategy = new UpdateValueStrategy();
        targetStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.name));
        targetStrategy.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                return validateInputName(value);
            }

        });

        ctx.bindValue(SWTObservables.observeText(nameText, SWT.Modify),
                EMFObservables.observeValue(getBusinessObjectData(), ProcessPackage.Literals.ELEMENT__NAME), targetStrategy, null);
    }

    protected IStatus validateInputName(final Object value) {
        IStatus status = new InputLengthValidator(Messages.name, 50).validate(value);
        if (!status.isOK()) {
            return status;
        }
        if (existingNames.contains(value.toString())) {
            return ValidationStatus.error(Messages.dataWithSameNameAlreadyExists);
        }
        return new GroovyReferenceValidator(Messages.name).validate(value);
    }

    protected EMFDataBindingContext createDatabindingContext() {
        return new EMFDataBindingContext();
    }

    protected Composite createMainComposite(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        return mainComposite;
    }

    public BusinessObjectData getBusinessObjectData() {
        return this.businessObjectData;
    }

    public void setBusinessObjectData(BusinessObjectData businessObjectData) {
        this.businessObjectData = businessObjectData;
    }

}
