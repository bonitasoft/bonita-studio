/**
 * Copyright (C) 2023 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.maven.wizard;

import org.apache.maven.settings.Mirror;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

public class MirrorConfigurationPageControl implements ControlSupplier {

    private DataBindingContext ctx;
    private Mirror mirrorConfiguration;
    private IObservableValue<Boolean> enableMirrorObservable;

    MirrorConfigurationPageControl(Mirror mirrorConfiguration, IObservableValue<Boolean> enableMirrorObservable) {
        this.enableMirrorObservable = enableMirrorObservable;
        this.mirrorConfiguration = mirrorConfiguration;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        this.ctx = ctx;
        var container = new Composite(parent, SWT.NONE);
        container.setLayout(
                GridLayoutFactory.swtDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 15).create());
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createRadioButtons(container);

        var configurationContainer = new Composite(container, SWT.NONE);
        configurationContainer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        configurationContainer.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        createMirrorDetails(configurationContainer);

        ctx.bindValue(WidgetProperties.visible().observe(configurationContainer), enableMirrorObservable);
        enableMirrorObservable.addValueChangeListener(e -> ctx.updateTargets());
        return container;
    }

    private void createRadioButtons(Composite container) {
        Group optionalGroup = new Group(container, SWT.NONE);
        optionalGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        var rowLayout = new RowLayout(SWT.HORIZONTAL);
        rowLayout.marginTop = 5;
        rowLayout.marginBottom = 5;
        optionalGroup.setLayout(rowLayout);
        optionalGroup.setText(Messages.wizardConnectionMirrorOptional);

        var yesButton = new Button(optionalGroup, SWT.RADIO);
        yesButton.setText(IDialogConstants.YES_LABEL);

        var noButton = new Button(optionalGroup, SWT.RADIO);
        noButton.setText(IDialogConstants.NO_LABEL);

        var optionalObserbable = new SelectObservableValue<Boolean>();
        optionalObserbable.addOption(Boolean.TRUE, WidgetProperties.buttonSelection().observe(yesButton));
        optionalObserbable.addOption(Boolean.FALSE, WidgetProperties.buttonSelection().observe(noButton));

       
        ctx.bindValue(optionalObserbable, enableMirrorObservable);
    }

    private void createMirrorDetails(Composite container) {
        new TextWidget.Builder()
                .withLabel(Messages.id + " *")
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .withValidator(whenEnabled(new EmptyInputValidator(Messages.id)))
                .bindTo(PojoProperties.value("id", String.class).observe(mirrorConfiguration))
                .inContext(ctx)
                .useNativeRender()
                .createIn(container);

        new TextWidget.Builder()
                .withLabel(Messages.name)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("name", String.class).observe(mirrorConfiguration))
                .inContext(ctx)
                .useNativeRender()
                .createIn(container);

        new TextWidget.Builder()
                .withLabel(Messages.url + " *")
                .labelAbove()
                .withTootltip(Messages.mirrorUrlTooltip)
                .fill()
                .horizontalSpan(2)
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("url", String.class).observe(mirrorConfiguration))
                .withValidator(whenEnabled(new EmptyInputValidator(Messages.url)))
                .inContext(ctx)
                .useNativeRender()
                .createIn(container);

        new TextWidget.Builder()
                .withLabel(Messages.mirrorOf + " *")
                .labelAbove()
                .withTootltip(Messages.mirrorOfTooltip)
                .fill()
                .horizontalSpan(2)
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("mirrorOf", String.class).observe(mirrorConfiguration))
                .withValidator(whenEnabled(new EmptyInputValidator(Messages.mirrorOf)))
                .inContext(ctx)
                .useNativeRender()
                .createIn(container);

    }

    private <T> IValidator<T> whenEnabled(IValidator<T> validator) {
        return value -> {
            if (enableMirrorObservable.getValue()) {
                return validator.validate(value);
            }
            return Status.OK_STATUS;
        };
    }

}
