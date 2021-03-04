/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control;

import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.widgets.CustomStackLayout;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.widget.TextWidget.Builder;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class ImportExtensionPage implements ControlSupplier {

    public enum ImportMode {
        MAVEN, ARCHIVE
    }

    private Dependency dependency = new Dependency();
    private SelectObservableValue importModeObservable;
    private Composite mavenComposite;
    private Composite archiveComposite;

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createRadioButtons(mainComposite);

        Label separator = new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createStackComposite(mainComposite, ctx);
        importModeObservable.setValue(ImportMode.MAVEN);
        importModeObservable.addValueChangeListener(e -> ctx.updateModels());
        return mainComposite;
    }

    private void createStackComposite(Composite parent, DataBindingContext ctx) {
        Composite stackComposite = new Composite(parent, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        CustomStackLayout stackLayout = new CustomStackLayout(stackComposite);
        stackComposite.setLayout(stackLayout);

        createMavenComposite(stackComposite, ctx);
        createArchiveComposite(stackComposite, ctx);

        ctx.bindValue(PojoProperties.value("topControl").observe(stackLayout), importModeObservable,
                UpdateStrategyFactory.neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(ConverterBuilder.<ImportMode, Composite> newConverter()
                                .fromType(ImportMode.class)
                                .toType(Composite.class)
                                .withConvertFunction(o -> o != null ? compositeFor(o) : null)
                                .create())
                        .create());
    }

    private void createArchiveComposite(Composite parent, DataBindingContext ctx) {
        archiveComposite = new Composite(parent, SWT.NONE);
        archiveComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 10).create());
        archiveComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label wipLabel = new Label(archiveComposite, SWT.NONE);
        wipLabel.setText("Coming soon :)");

    }

    private void createMavenComposite(Composite parent, DataBindingContext ctx) {
        mavenComposite = new Composite(parent, SWT.NONE);
        mavenComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(5, 10).spacing(LayoutConstants.getSpacing().x, 30).create());
        mavenComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label tip = new Label(mavenComposite, SWT.WRAP);
        tip.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        tip.setText(Messages.importExtensionAsMavenDependencyTip);

        Group propertiesGroup = new Group(mavenComposite, SWT.NONE);
        propertiesGroup.setLayoutData(GridDataFactory.fillDefaults().create());
        propertiesGroup.setLayout(
                GridLayoutFactory.fillDefaults().margins(20, 20).spacing(LayoutConstants.getSpacing().x, 10).create());

        // We do not want to translate the maven property names, it would lead to too many confusions.
        createText(propertiesGroup, "Group ID", PojoProperties.value("groupId", String.class).observe(dependency), ctx,
                true);
        createText(propertiesGroup, "Artifact ID", PojoProperties.value("artifactId", String.class).observe(dependency),
                ctx, true);
        createText(propertiesGroup, "Version", PojoProperties.value("version", String.class).observe(dependency), ctx, true);
        createText(propertiesGroup, "Type", PojoProperties.value("type", String.class).observe(dependency), ctx, true);
        createText(propertiesGroup, "Classifier", PojoProperties.value("classifier", String.class).observe(dependency),
                ctx, false);
    }

    private void createText(Composite parent, String label, IObservableValue<String> binding, DataBindingContext ctx,
            boolean mandatory) {
        Builder builder = new TextWidget.Builder()
                .withLabel(label)
                .labelAbove()
                .grabHorizontalSpace()
                .bindTo(binding)
                .inContext(ctx)
                .fill()
                .inShell();
        if (mandatory) {
            EmptyInputValidator validator = new EmptyInputValidator(label);
            builder.withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                    .withValidator(input -> validateMandatoryMavenInput(validator, input))
                    .create());
        }
        builder.createIn(parent);
    }

    private IStatus validateMandatoryMavenInput(EmptyInputValidator validator, Object input) {
        return Objects.equals(importModeObservable.getValue(), ImportMode.ARCHIVE)
                ? ValidationStatus.ok()
                : validator.validate(input);
    }

    private Composite compositeFor(ImportMode importMode) {
        switch (importMode) {
            case MAVEN:
                return mavenComposite;
            case ARCHIVE:
            default:
                return archiveComposite;
        }
    }

    private void createRadioButtons(Composite parent) {
        Composite radioComposite = new Composite(parent, SWT.NONE);
        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.CENTER, SWT.FILL).create());

        Button mavenButton = new Button(radioComposite, SWT.RADIO);
        mavenButton.setText(Messages.importExtensionAsMavenArtifactLabel);
        mavenButton.setLayoutData(GridDataFactory.fillDefaults().create());

        Button archiveButton = new Button(radioComposite, SWT.RADIO);
        archiveButton.setText(Messages.importExtensionAsArchiveLabel);
        archiveButton.setLayoutData(GridDataFactory.fillDefaults().create());

        importModeObservable = new SelectObservableValue();
        importModeObservable.addOption(ImportMode.MAVEN,
                WidgetProperties.buttonSelection().observe(mavenButton));
        importModeObservable.addOption(ImportMode.ARCHIVE,
                WidgetProperties.buttonSelection().observe(archiveButton));

    }

    public Dependency getDependenciesToAdd() {
        return dependency;
    }

}
