/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.wizard.page;

import java.util.Set;

import org.bonitasoft.studio.common.jface.databinding.validator.GroovyReferenceValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.InputLengthValidator;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.parameters.property.section.editingsupport.ParameterTypeEditingSupport;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterTypeLabelProvider;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Maxence Raoux
 * 
 */
public class ParametersWizardPage extends WizardPage {

    private Text nameText;

    private Text descriptionText;

    private ComboViewer typeCombo;

    private Parameter parameterWorkingCopy;

    private EMFDataBindingContext dataBindingContext;

    private Set<String> existingParameterNames;

    public ParametersWizardPage(Parameter parameterWorkingCopy, Set<String> existingParameterNames) {
        super(ParametersWizardPage.class.getName());
        Assert.isNotNull(parameterWorkingCopy);
        Assert.isNotNull(existingParameterNames);
        this.parameterWorkingCopy = parameterWorkingCopy;
        this.existingParameterNames = existingParameterNames;
    }

    public String getParameterName() {
        return nameText.getText();
    }

    public String getParameterDescription() {
        return descriptionText.getText();
    }

    public String getParameterType(String type) {
        if (type != null) {
            if (type.equals("Double")) {
                return Double.class.getName();
            } else if (type.equals("Integer")) {
                return Integer.class.getName();
            } else if (type.equals("Boolean")) {
                return Boolean.class.getName();
            } else {
                return String.class.getName();
            }
        } else {
            return String.class.getName();
        }

    }

    @Override
    public void createControl(Composite parent) {
        dataBindingContext = new EMFDataBindingContext();
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(7, 7).create());

        createParameterComposite(mainComposite);
        WizardPageSupport.create(this, dataBindingContext);
        setControl(mainComposite);

    }

    protected void createParameterComposite(Composite parent) {
        createName(parent);
        createDescription(parent);
        createTypeChooser(parent);
        new Composite(parent, SWT.NONE);
        Label additionalInformation = new Label(parent, SWT.NONE);
        additionalInformation.setText(Messages.additionalInformationText);
        additionalInformation.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
    }

    private void createName(final Composite parent) {
        final Label nameLabel = new Label(parent, SWT.NONE);
        nameLabel.setText(Messages.name);
        nameLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());

        nameText = new Text(parent, SWT.BORDER);
        nameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .create());
        UpdateValueStrategy nameStrategy = new UpdateValueStrategy();
        nameStrategy.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(Object value) {
                String errorMessage = isNameValid((String) value);
                if (errorMessage != null) {
                    return ValidationStatus.error(errorMessage);
                }
                return Status.OK_STATUS;
            }
        });
        dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(nameText),
                EMFObservables.observeValue(parameterWorkingCopy, ParameterPackage.Literals.PARAMETER__NAME), nameStrategy, null);
    }

    private void createDescription(final Composite parent) {
        final Label descriptionLabel = new Label(parent, SWT.NONE);
        descriptionLabel.setText(Messages.parameterDescription);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.TOP).create());

        descriptionText = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.WRAP
                | SWT.V_SCROLL);
        descriptionText.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).hint(SWT.DEFAULT, 70).create());
        descriptionText.setTextLimit(255);
        dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(descriptionText),
                EMFObservables.observeValue(parameterWorkingCopy, ParameterPackage.Literals.PARAMETER__DESCRIPTION));
    }

    private void createTypeChooser(final Composite parent) {
        final Label parameterType = new Label(parent, SWT.NONE);
        parameterType.setText(Messages.type);
        parameterType.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.CENTER).create());
        typeCombo = new ComboViewer(parent, SWT.BORDER | SWT.READ_ONLY);
        typeCombo.setContentProvider(new ArrayContentProvider());
        typeCombo.setLabelProvider(new ParameterTypeLabelProvider());
        typeCombo.getCombo().setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        typeCombo.setInput(ParameterTypeEditingSupport.types);
        dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(typeCombo),
                EMFObservables.observeValue(parameterWorkingCopy, ParameterPackage.Literals.PARAMETER__TYPE_CLASSNAME));
    }

    protected String isNameValid(String value) {
        final IStatus javaConventionNameStatus = new GroovyReferenceValidator(
                Messages.name).validate(value);
        if (!javaConventionNameStatus.isOK()) {
            return javaConventionNameStatus.getMessage();
        }
        final IStatus lenghtNameStatus = new InputLengthValidator(
                Messages.name, 50).validate(value);
        if (!lenghtNameStatus.isOK()) {
            return lenghtNameStatus.getMessage();
        }

        if (existingParameterNames.contains(value.toString())) {
            return Messages.invalidName;
        }

        return null;
    }

}
