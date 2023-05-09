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

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.settings.Server;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.maven.security.MavenPasswordManager;
import org.bonitasoft.studio.common.RedirectURLBuilder;
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
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;

public class BARConfigurationPageControl implements ControlSupplier {

    private static final String ENCRYPT_PWD_BUTTON_ID = "encryptPassword";
    private static final char CLEAR_CHAR = '\0';
    private static final String REDIRECT_ID_CREDENTIAL_DOC = "756";

    private DataBindingContext ctx;
    private char hiddenEchoChar;
    private int currentPasswordStyle;
    private MavenPasswordManager passwordManager = new MavenPasswordManager();
    private Server barServer;
    private IObservableValue<String> masterPwdObservable = new WritableValue<>();
    private TextWidget passwordField;
    private ToolItem encryptPwdItem;
    private IObservableValue<Boolean> enableMirrorObservable;

    BARConfigurationPageControl(Server barServer, IObservableValue<Boolean> enableMirrorObservable) {
        this.barServer = barServer;
        this.enableMirrorObservable = enableMirrorObservable;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        this.ctx = ctx;
        var container = new Composite(parent, SWT.NONE);
        container.setLayout(
                GridLayoutFactory.swtDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 15).create());
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createServerDetailsComposite(container);

        return container;
    }

    private void createServerDetailsComposite(Composite parent) {
        new TextWidget.Builder()
                .withLabel(Messages.username)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("username").observe(barServer))
                .withValidator(value -> enableMirrorObservable.getValue() ? ValidationStatus.ok()
                        :  new EmptyInputValidator(Messages.username).validate((String) value))
                .inContext(ctx)
                .useNativeRender()
                .createIn(parent);

        passwordField = createPasswordField(parent, SWT.PASSWORD);
        hiddenEchoChar = passwordField.getTextControl().getEchoChar();
        createPasswordEncryptButton();
        createInformationComposite(parent);
        
        enableMirrorObservable.addValueChangeListener(e -> ctx.updateTargets());
    }

    private void createInformationComposite(Composite container) {

        var docLink = new Link(container, SWT.NONE);
        docLink.setText(Messages.wizardConnectionBARDocumentationLink);
        docLink.addListener(SWT.Selection, e -> openBrowser(REDIRECT_ID_CREDENTIAL_DOC));

        var infoComposite = new Composite(container, SWT.NONE);
        infoComposite
                .setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).numColumns(2).create());
        infoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        new Label(infoComposite, SWT.NONE).setImage(Pics.getImage(PicsConstants.hint));
        new Label(infoComposite, SWT.NONE).setText(Messages.wizardConnectionBARMainText);

    }

    private void openBrowser(String redirectId) {
        try {
            Desktop.getDesktop().browse(RedirectURLBuilder.createURI(redirectId));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private TextWidget createPasswordField(Composite parent, int style) {
        currentPasswordStyle = style;
        passwordField = new TextWidget.Builder()
                .withLabel(Messages.token)
                .labelAbove()
                .withStyle(style)
                .withButton(Pics.getImage("view.png", IdentityPlugin.getDefault()), Messages.showPassword)
                .onClickButton(e -> showPassword(parent))
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("password").observe(barServer))
                .withValidator(value -> enableMirrorObservable.getValue() ? ValidationStatus.ok()
                        :  new EmptyInputValidator(Messages.token).validate((String) value))
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
            encryptPwdItem = new ToolItem(toolbar, SWT.PUSH);
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
