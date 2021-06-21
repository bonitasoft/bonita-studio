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
package org.bonitasoft.studio.application.preference;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.File;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.preference.provider.ServerIdContentProvider;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.widgets.CustomStackLayout;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.browser.OpenSystemBrowserListener;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.google.common.base.Strings;

public class ServersComposite extends Composite {

    public static final String SERVERS_VIEWER_ID = "serversViewer";
    public static final String ADD_SERVER_BUTTON_ID = "addServer";
    public static final String REMOVE_SERVER_BUTTON_ID = "removeServer";
    public static final String ENCRYPT_PWD_BUTTON_ID = "encryptPassword";
    public static final String DEFAULT_SERVER_NAME = "serverId";

    private static final char CLEAR_CHAR = '\0';
    private static final String USER_HOME = System.getProperty("user.home");

    public enum AuthenticationMode {
        USERNAME_PWD, SSH
    }

    private IObservableValue<Settings> settingsObservable;
    private DataBindingContext ctx;
    private MavenPasswordManager passwordManager;

    private ToolItem deleteItem;
    private ToolItem encryptPwdItem;
    private TableViewer viewer;
    private Composite usernamePwdComposite;
    private Composite sshComposite;
    private TextWidget passwordField;
    private TextWidget passphraseField;

    private IObservableList<Server> serversObservable;
    private IViewerObservableValue<Server> selectionObservable;
    private SelectObservableValue<AuthenticationMode> authenticationModeObservable;
    private IObservableValue<String> masterPwdObservable;
    private IObservableValue<String> passwordObservable;
    private IObservableValue<String> privateKeyObservable;

    private char hiddenEchoChar;
    private int currentPasswordStyle;
    private int currentPassphraseStyle;

    public ServersComposite(Composite parent, IObservableValue<Settings> settings,
            IObservableValue<String> masterPwdObservable) {
        super(parent, SWT.NONE);

        this.settingsObservable = settings;
        this.serversObservable = PojoProperties.list(Settings.class, "servers", Server.class).observeDetail(settings);
        this.ctx = new DataBindingContext();
        this.passwordManager = new MavenPasswordManager();
        this.masterPwdObservable = masterPwdObservable;

        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 10).create());

        new MasterPasswordComposite(this, passwordManager, masterPwdObservable, ctx);

        var separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        createServersComposite(this);

        ctx.bindValue(WidgetProperties.enabled().observe(deleteItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());

        authenticationModeObservable.addValueChangeListener(e -> {
            if (Objects.equals(authenticationModeObservable.getValue(), AuthenticationMode.SSH)
                    && selectionObservable.getValue() != null
                    && Strings.isNullOrEmpty(selectionObservable.getValue().getPrivateKey())) {
                String defaultSshKey = String.format("%s%s%s%s%s", USER_HOME, File.separator, ".ssh", File.separator,
                        "id_rsa");
                privateKeyObservable.setValue(defaultSshKey);
            }
        });
    }

    private void createServersComposite(Composite parent) {
        var serversComposite = new Composite(parent, SWT.NONE);
        serversComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        serversComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var link = new Link(serversComposite, SWT.NONE);
        link.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        link.setText(Messages.serversLink);
        link.addListener(SWT.Selection, new OpenSystemBrowserListener("https://maven.apache.org/settings.html#Servers"));

        createServerListComposite(serversComposite);
        createServerDetailsComposite(serversComposite);
    }

    private void createServerDetailsComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createServerIdCombo(composite);

        var authenticationGroup = new Group(composite, SWT.NONE);
        authenticationGroup.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).create());
        authenticationGroup
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, true).create());
        authenticationGroup.setText(Messages.authentication);

        createRadioButtons(authenticationGroup);
        createStackComposite(authenticationGroup);

        ctx.bindValue(WidgetProperties.visible().observe(composite), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());
    }

    private void createStackComposite(Composite parent) {
        Composite stackComposite = new Composite(parent, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        CustomStackLayout stackLayout = new CustomStackLayout(stackComposite);
        stackComposite.setLayout(stackLayout);

        createUsernamePwdComposite(stackComposite);
        createSshComposite(stackComposite);

        ctx.bindValue(PojoProperties.value("topControl").observe(stackLayout), authenticationModeObservable,
                UpdateStrategyFactory.neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(ConverterBuilder.<AuthenticationMode, Composite> newConverter()
                                .fromType(AuthenticationMode.class)
                                .toType(Composite.class)
                                .withConvertFunction(o -> o != null ? compositeFor(o) : null)
                                .create())
                        .create());
        selectionObservable.addValueChangeListener(e -> {
            if (e.diff.getNewValue() != null
                    && selectionObservable.getValue().getPassphrase() != null
                    && selectionObservable.getValue().getPassword() == null) {
                authenticationModeObservable.setValue(AuthenticationMode.SSH);
            } else {
                authenticationModeObservable.setValue(AuthenticationMode.USERNAME_PWD);
            }
        });
    }

    private void createSshComposite(Composite parent) {
        sshComposite = new Composite(parent, SWT.NONE);
        sshComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        sshComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        privateKeyObservable = PojoProperties.value("privateKey", String.class).observeDetail(selectionObservable);
        new TextWidget.Builder()
                .withLabel(Messages.privateKey)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(privateKeyObservable)
                .inContext(ctx)
                .useNativeRender()
                .createIn(sshComposite);

        var passphraseComposite = new Composite(sshComposite, SWT.NONE);
        passphraseComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        passphraseComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createPassphraseField(passphraseComposite, SWT.PASSWORD);

        ButtonWidget clearPasswordButton = new ButtonWidget.Builder()
                .withLabel(Messages.clearPassword)
                .onClick(e -> passwordObservable.setValue(null))
                .createIn(sshComposite);

        ControlDecoration clearPasswordWarningDecorator = new ControlDecoration(clearPasswordButton, SWT.RIGHT);
        clearPasswordWarningDecorator.setDescriptionText(Messages.clearPasswordTooltip);
        clearPasswordWarningDecorator.setImage(Pics.getImageDescriptor(PicsConstants.warning).createImage());
        clearPasswordWarningDecorator.setMarginWidth(3);

        ComputedValue<Boolean> passwordSetObservable = new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> !Strings.isNullOrEmpty(passwordObservable.getValue()))
                .build();
        passwordSetObservable.addValueChangeListener(v -> {
            if (Boolean.TRUE.equals(v.diff.getNewValue())) {
                clearPasswordWarningDecorator.show();
            } else {
                clearPasswordWarningDecorator.hide();
            }
        });
        if (Boolean.TRUE.equals(passwordSetObservable.getValue())) {
            clearPasswordWarningDecorator.show();
        } else {
            clearPasswordWarningDecorator.hide();
        }
        ctx.bindValue(WidgetProperties.visible().observe(clearPasswordButton), passwordSetObservable);

    }

    private void createPassphraseField(Composite parent, int style) {
        currentPassphraseStyle = style;
        passphraseField = new TextWidget.Builder()
                .withLabel(Messages.passphrase)
                .labelAbove()
                .withStyle(style)
                .withButton(Pics.getImage("view.png", IdentityPlugin.getDefault()), Messages.showPassphrase)
                .onClickButton(e -> showPassphase(parent))
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("passphrase", String.class).observeDetail(selectionObservable))
                .inContext(ctx)
                .useNativeRender()
                .createIn(parent);
    }

    private void showPassphase(Composite parent) {
        var isHidden = Objects.equals(currentPassphraseStyle, SWT.PASSWORD);
        if (Platform.OS_MACOSX.equals(Platform.getOS())) {
            passphraseField.dispose();
            createPassphraseField(parent, isHidden ? SWT.NONE : SWT.PASSWORD);
            parent.layout();
        } else {
            Text text = passphraseField.getTextControl();
            Optional<ToolItem> button = passphraseField.getButtonWithImage();
            if (button.isPresent()) {
                text.setEchoChar(Objects.equals(text.getEchoChar(), hiddenEchoChar) ? CLEAR_CHAR : hiddenEchoChar);
            }
        }
    }

    private void createUsernamePwdComposite(Composite parent) {
        usernamePwdComposite = new Composite(parent, SWT.NONE);
        usernamePwdComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        usernamePwdComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        new TextWidget.Builder()
                .withLabel(Messages.username)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("username", String.class).observeDetail(selectionObservable))
                .inContext(ctx)
                .useNativeRender()
                .createIn(usernamePwdComposite);

        passwordObservable = PojoProperties.value("password", String.class).observeDetail(selectionObservable);

        passwordField = createPasswordField(usernamePwdComposite, SWT.PASSWORD);
        hiddenEchoChar = passwordField.getTextControl().getEchoChar();
        createPasswordEncryptButton();
    }

    private void createPasswordEncryptButton() {
        passwordField.getToolBar().ifPresent(toolbar -> {
            encryptPwdItem = new ToolItem(toolbar, SWT.PUSH);
            encryptPwdItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ENCRYPT_PWD_BUTTON_ID);
            encryptPwdItem.addListener(SWT.Selection, e -> encryptPassword());
            encryptPwdItem.setImage(Pics.getImage(PicsConstants.key));

            ComputedValue<Boolean> masterPasswordObservable = new ComputedValueBuilder<Boolean>()
                    .withSupplier(() -> masterPwdObservable.getValue() != null && !masterPwdObservable.getValue().isEmpty())
                    .build();
            ctx.bindValue(WidgetProperties.enabled().observe(encryptPwdItem), masterPasswordObservable);
            ctx.bindValue(WidgetProperties.tooltipText().observe(encryptPwdItem),
                    masterPasswordObservable,
                    new UpdateValueStrategy<>(UpdateValueStrategy.POLICY_NEVER),
                    updateValueStrategy()
                            .withConverter(ConverterBuilder.<Boolean, String> newConverter()
                                    .fromType(Boolean.class)
                                    .toType(String.class)
                                    .withConvertFunction(masterPwdSet -> {
                                        if (Boolean.FALSE.equals(masterPwdSet)) {
                                            return Messages.encryptButtonTooltip;
                                        }
                                        return Messages.encryptPassword;
                                    }).create())
                            .create());
        });
    }

    private void encryptPassword() {
        String currentPassword = passwordField.getText();
        passwordField.setText(passwordManager.encryptPassword(currentPassword));
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
                .bindTo(passwordObservable)
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

    private Composite compositeFor(AuthenticationMode importMode) {
        switch (importMode) {
            case SSH:
                return sshComposite;
            case USERNAME_PWD:
            default:
                return usernamePwdComposite;
        }
    }

    private void createRadioButtons(Composite parent) {
        Composite radioComposite = new Composite(parent, SWT.NONE);
        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.CENTER, SWT.FILL).create());

        Button userPasswordButton = new Button(radioComposite, SWT.RADIO);
        userPasswordButton.setText(Messages.userPasswordAuthentication);
        userPasswordButton.setLayoutData(GridDataFactory.fillDefaults().create());

        Button sshButton = new Button(radioComposite, SWT.RADIO);
        sshButton.setText(Messages.sshAuthentication);
        sshButton.setLayoutData(GridDataFactory.fillDefaults().create());

        authenticationModeObservable = new SelectObservableValue<>();
        authenticationModeObservable.addOption(AuthenticationMode.USERNAME_PWD,
                WidgetProperties.buttonSelection().observe(userPasswordButton));
        authenticationModeObservable.addOption(AuthenticationMode.SSH,
                WidgetProperties.buttonSelection().observe(sshButton));
    }

    private void createServerIdCombo(Composite parent) {
        var serverIdComposite = new Composite(parent, SWT.NONE);
        serverIdComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        serverIdComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var serverIdLabel = new Label(serverIdComposite, SWT.NONE);
        serverIdLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.FILL).create());
        serverIdLabel.setText(Messages.serverId);

        ControlDecoration controlDecoration = new ControlDecoration(serverIdLabel, SWT.RIGHT);
        controlDecoration.setDescriptionText(Messages.serverIdHint);
        controlDecoration.setMarginWidth(5);
        controlDecoration.setImage(FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
        controlDecoration.show();

        var serverIdCombo = new Combo(serverIdComposite, SWT.BORDER | SWT.READ_ONLY);
        serverIdCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        serverIdCombo.setItems(new ServerIdContentProvider(settingsObservable).toArray());
        IObservableValue<String> serverComboSelectionObservable = WidgetProperties.text()
                .observe(serverIdCombo);
        ctx.bindValue(serverComboSelectionObservable,
                PojoProperties.value("id", String.class).observeDetail(selectionObservable));

        serverComboSelectionObservable.addValueChangeListener(e -> viewer.refresh());
    }

    private void createServerListComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(250, SWT.DEFAULT).create());

        createToolbar(composite);
        createViewer(composite);
    }

    private void createToolbar(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());

        var toolBar = new ToolBar(composite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);

        var addItem = new ToolItem(toolBar, SWT.PUSH);
        addItem.setImage(Pics.getImage(PicsConstants.add_simple));
        addItem.setText(Messages.add);
        addItem.setToolTipText(Messages.addServerTooltip);
        addItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_SERVER_BUTTON_ID);
        addItem.addListener(SWT.Selection, e -> addServer());

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setToolTipText(Messages.deleteServerTooltip);
        deleteItem.setText(Messages.delete);
        deleteItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_SERVER_BUTTON_ID);
        deleteItem.addListener(SWT.Selection, e -> removeServer());
    }

    protected void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SERVERS_VIEWER_ID);

        viewer.setUseHashlookup(true);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTable().setLayout(layout);

        createServerColumn(viewer);

        viewer.setContentProvider(new ObservableListContentProvider<Server>());
        viewer.setInput(serversObservable);

        selectionObservable = ViewerProperties.singleSelection(Server.class).observe(viewer);
        ColumnViewerToolTipSupport.enableFor(viewer);
    }

    private void createServerColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.setLabelProvider(new LabelProviderBuilder<Server>()
                .withTextProvider(Server::getId)
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
    }

    private void removeServer() {
        if (MessageDialog.openQuestion(getShell(), Messages.removeServerConfirmationTitle,
                String.format(Messages.removeServerConfirmation, selectionObservable.getValue().getId()))) {
            serversObservable.remove(selectionObservable.getValue());
            refreshViewer();
        }
    }

    private void addServer() {
        var server = new Server();
        String name = StringIncrementer.getNextIncrement(DEFAULT_SERVER_NAME,
                serversObservable.stream().map(Server::getId).collect(Collectors.toList()));
        server.setId(name);
        serversObservable.add(server);
        selectionObservable.setValue(server);
        refreshViewer();
    }

    private void refreshViewer() {
        getDisplay().asyncExec(() -> viewer.refresh());
    }

    public void refresh() {
        refreshViewer();
    }

}
