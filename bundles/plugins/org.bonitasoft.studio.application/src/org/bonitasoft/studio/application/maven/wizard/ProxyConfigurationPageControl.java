/**
 * Copyright (C) 2022 BonitaSoft S.A.
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

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.Objects;
import java.util.Optional;

import org.apache.maven.settings.Proxy;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.maven.security.MavenPasswordManager;
import org.bonitasoft.studio.common.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.validator.InputValidatorWrapper;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;

public class ProxyConfigurationPageControl implements ControlSupplier {

    private static final String ENCRYPT_PWD_BUTTON_ID = "encryptPassword";
    private static final char CLEAR_CHAR = '\0';

    private DataBindingContext ctx;
    private char hiddenEchoChar;
    private int currentPasswordStyle;
    private MavenPasswordManager passwordManager = new MavenPasswordManager();
    private Proxy proxyConfiguration;
    private IObservableValue<String> masterPwdObservable = new WritableValue<>();
    private TextWidget passwordField;
    private MavenConfiguration mavenConfiguration;
    private IObservableValue<Boolean> enableProxyObservable;

    ProxyConfigurationPageControl(MavenConfiguration mavenConfiguration) {
        this.mavenConfiguration = mavenConfiguration;
        this.proxyConfiguration = mavenConfiguration.getProxy();
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
        configurationContainer.setLayout(GridLayoutFactory.fillDefaults().create());

        createProtocolHostPortComposite(configurationContainer);

        new TextWidget.Builder()
                .withLabel(Messages.nonProxyHost)
                .labelAbove()
                .withTootltip(Messages.nonProxyHostsTootltip)
                .fill()
                .horizontalSpan(2)
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("nonProxyHosts", String.class).observe(proxyConfiguration))
                .inContext(ctx)
                .useNativeRender()
                .createIn(configurationContainer);

        createAuthenticationComposite(configurationContainer);

        ctx.bindValue(WidgetProperties.visible().observe(configurationContainer), enableProxyObservable);
        enableProxyObservable.addValueChangeListener(e -> {
            ctx.updateTargets();
        });
        return container;
    }

    private void createRadioButtons(Composite container) {
        Group optionalGroup = new Group(container, SWT.NONE);
        optionalGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        var rowLayout = new RowLayout(SWT.HORIZONTAL);
        rowLayout.marginTop = 5;
        rowLayout.marginBottom = 5;
        optionalGroup.setLayout(rowLayout);
        optionalGroup.setText(Messages.wizardConnectionProxyOptional);

        var yesButton = new Button(optionalGroup, SWT.RADIO);
        yesButton.setText(IDialogConstants.YES_LABEL);

        var noButton = new Button(optionalGroup, SWT.RADIO);
        noButton.setText(IDialogConstants.NO_LABEL);

        var optionalObserbable = new SelectObservableValue<Boolean>();
        optionalObserbable.addOption(Boolean.TRUE, WidgetProperties.buttonSelection().observe(yesButton));
        optionalObserbable.addOption(Boolean.FALSE, WidgetProperties.buttonSelection().observe(noButton));

        enableProxyObservable = PojoProperties.value("enableProxy", Boolean.class).observe(mavenConfiguration);
        ctx.bindValue(optionalObserbable, enableProxyObservable);
    }

    private void createAuthenticationComposite(Composite parent) {
        var authenticationGroup = new Group(parent, SWT.NONE);
        authenticationGroup.setLayout(GridLayoutFactory.fillDefaults().create());
        authenticationGroup
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 90).create());
        authenticationGroup.setText(Messages.authentication);

        var usernamePwdComposite = new Composite(authenticationGroup, SWT.NONE);
        usernamePwdComposite
                .setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).numColumns(2).equalWidth(true).create());
        usernamePwdComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        new TextWidget.Builder()
                .withLabel(Messages.username)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("username", String.class).observe(proxyConfiguration))
                .inContext(ctx)
                .useNativeRender()
                .createIn(usernamePwdComposite);

        createPasswordField(usernamePwdComposite, SWT.PASSWORD);
        hiddenEchoChar = passwordField.getTextControl().getEchoChar();
        createPasswordEncryptButton();
    }

    private void createProtocolHostPortComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        new TextWidget.Builder()
                .withLabel(Messages.protocol)
                .labelAbove()
                .fill()
                .widthHint(80)
                .bindTo(PojoProperties.value("protocol", String.class).observe(proxyConfiguration))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.host + " *")
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("host", String.class).observe(proxyConfiguration))
                .withValidator(whenEnabled(new EmptyInputValidator(Messages.host)))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        IValidator<String> intValueValidaor = value -> {
            try {
                Integer.valueOf(value);
                return ValidationStatus.ok();
            } catch (NumberFormatException e) {
                return ValidationStatus.error(Messages.intValueExpected);
            }
        };

        new TextWidget.Builder()
                .withLabel(Messages.port)
                .labelAbove()
                .fill()
                .widthHint(100)
                .withModelToTargetStrategy(updateValueStrategy()
                        .withConverter(IConverter.<Integer, String> create(String::valueOf))
                        .create())
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(whenEnabled(intValueValidaor))
                        .withConverter(IConverter.<String, Integer> create(Integer::valueOf))
                        .create())
                .bindTo(PojoProperties.value("port", String.class).observe(proxyConfiguration))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);
    }

    private <T> IValidator<T> whenEnabled(IValidator<T> validator) {
        return value -> {
            if (enableProxyObservable.getValue()) {
                return validator.validate(value);
            }
            return Status.OK_STATUS;
        };
    }

    private TextWidget createPasswordField(Composite parent, int style) {
        currentPasswordStyle = style;
        passwordField = new TextWidget.Builder()
                .withLabel(Messages.password)
                .labelAbove()
                .withStyle(style)
                .withButton(Pics.getImage("view.png", IdentityPlugin.getDefault()), Messages.showPassword)
                .onClickButton(e -> showPassword(parent))
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("password", String.class).observe(proxyConfiguration))
                .inContext(ctx)
                .useNativeRender()
                .createIn(parent);
        return passwordField;
    }

    private void showPassword(Composite parent) {
        var isHidden = Objects.equals(currentPasswordStyle, SWT.PASSWORD);
        if (Platform.OS_MACOSX.equals(Platform.getOS())) {
            passwordField.dispose();
            createPasswordField(parent, isHidden ? SWT.NONE : SWT.PASSWORD);
            createPasswordEncryptButton();
            parent.layout();
        } else {
            Text text = passwordField.getTextControl();
            Optional<ToolItem> button = passwordField.getButtonWithImage();
            if (button.isPresent()) {
                text.setEchoChar(Objects.equals(text.getEchoChar(), hiddenEchoChar) ? CLEAR_CHAR : hiddenEchoChar);
            }
        }
    }

    private void createPasswordEncryptButton() {
        passwordField.getToolBar().ifPresent(toolbar -> {
            var encryptPwdItem = new ToolItem(toolbar, SWT.PUSH);
            encryptPwdItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ENCRYPT_PWD_BUTTON_ID);
            encryptPwdItem.addListener(SWT.Selection, e -> encryptPassword());
            encryptPwdItem.setImage(Pics.getImage(PicsConstants.key));

            ComputedValue<Boolean> masterPasswordObservable = new ComputedValueBuilder<Boolean>()
                    .withSupplier(
                            () -> masterPwdObservable.getValue() != null && !masterPwdObservable.getValue().isEmpty())
                    .build();
            ctx.bindValue(WidgetProperties.tooltipText().observe(encryptPwdItem),
                    masterPasswordObservable,
                    new UpdateValueStrategy<>(UpdateValueStrategy.POLICY_NEVER),
                    updateValueStrategy()
                            .withConverter(IConverter.<Boolean, String> create(masterPwdSet -> {
                                if (Boolean.FALSE.equals(masterPwdSet)) {
                                    return Messages.encryptButtonTooltip;
                                }
                                return Messages.encryptPassword;
                            }))
                            .create());
        });
    }

    private void encryptPassword() {
        if (passwordManager.getCurrentMasterPassword().isPresent()) {
            String currentPassword = passwordField.getText();
            passwordField.setText(passwordManager.encryptPassword(currentPassword));
        } else {
            creatingMasterPassword();
        }
    }

    private void creatingMasterPassword() {
        var dialog = new InputDialog(Display.getDefault().getActiveShell(),
                Messages.encryptButtonTooltip,
                Messages.encryptionMasterPassword,
                "", new InputValidatorWrapper(new EmptyInputValidator(Messages.encryptionMasterPassword)));

        if (dialog.open() == Window.OK) {
            passwordManager.updateMasterPassword(passwordManager.encryptMasterPassword(dialog.getValue()));
            masterPwdObservable.setValue(passwordManager.getCurrentMasterPassword().get());
        }
    }

}
