/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

    private final Parameter parameterWorkingCopy;

    private EMFDataBindingContext dataBindingContext;

    private final Set<String> existingParameterNames;

    public ParametersWizardPage(final Parameter parameterWorkingCopy, final Set<String> existingParameterNames) {
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

    public String getParameterType(final String type) {
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
    public void createControl(final Composite parent) {
        dataBindingContext = new EMFDataBindingContext();
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(7, 7).create());

        createParameterComposite(mainComposite);
        WizardPageSupport.create(this, dataBindingContext);
        setControl(mainComposite);

    }

    protected void createParameterComposite(final Composite parent) {
        createName(parent);
        createDescription(parent);
        createTypeChooser(parent);
        new Composite(parent, SWT.NONE);
        final Label additionalInformation = new Label(parent, SWT.NONE);
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
        final UpdateValueStrategy nameStrategy = new UpdateValueStrategy();
        nameStrategy.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                final String errorMessage = isNameValid((String) value);
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

    protected String isNameValid(final String value) {
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
