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

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.settings.Proxy;
import org.apache.maven.settings.Settings;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.browser.OpenSystemBrowserListener;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ProxiesComposite extends Composite {

    private static final char CLEAR_CHAR = '\0';
    private static final String DEFAULT_PROXY_NAME = "proxyId";

    private DataBindingContext ctx;
    private MavenPasswordManager passwordManager;

    private IObservableValue<String> masterPwdObservable;
    private IObservableList<Proxy> proxiesObservable;
    private IObservableValue<Proxy> selectionObservable;

    private ToolItem deleteItem;
    private TableViewer viewer;

    private IObservableValue<String> passwordObservable;

    private int currentPasswordStyle;

    private TextWidget passwordField;

    private char hiddenEchoChar;
    private ToolItem activateItem;
    private ToolItem encryptPwdItem;

    public ProxiesComposite(Composite parent, IObservableValue<Settings> settingsObservable,
            IObservableValue<String> masterPwdObservable) {
        super(parent, SWT.NONE);

        this.proxiesObservable = PojoProperties.list(Settings.class, "proxies", Proxy.class)
                .observeDetail(settingsObservable);
        this.ctx = new DataBindingContext();
        this.passwordManager = new MavenPasswordManager();
        this.masterPwdObservable = masterPwdObservable;

        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 10).create());

        new MasterPasswordComposite(this, passwordManager, masterPwdObservable, ctx);

        var separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createProxiesComposite(this);

        ctx.bindValue(WidgetProperties.enabled().observe(deleteItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());

        ctx.bindValue(WidgetProperties.enabled().observe(activateItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(
                        () -> selectionObservable.getValue() != null && !selectionObservable.getValue().isActive())
                .build());
    }

    private void createProxiesComposite(Composite parent) {
        var proxiesComposite = new Composite(parent, SWT.NONE);
        proxiesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        proxiesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var link = new Link(proxiesComposite, SWT.NONE);
        link.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        link.setText(Messages.proxiesLink);
        link.addListener(SWT.Selection,
                new OpenSystemBrowserListener("https://maven.apache.org/settings.html#proxies"));

        createProxyListComposite(proxiesComposite);
        createProxyDetailsComposite(proxiesComposite);
    }

    private void createProxyDetailsComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        new TextWidget.Builder()
                .withLabel(Messages.id)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("id", String.class).observeDetail(selectionObservable))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        createProtocolHostPortComposite(composite);

        new TextWidget.Builder()
                .withLabel(Messages.nonProxyHost)
                .labelAbove()
                .withTootltip(Messages.nonProxyHostsTootltip)
                .fill()
                .horizontalSpan(2)
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("nonProxyHosts", String.class).observeDetail(selectionObservable))
                .inContext(ctx)
                .withValidator(new EmptyInputValidator(Messages.host))
                .useNativeRender()
                .createIn(composite);

        createAuthenticationComposite(composite);

        ctx.bindValue(WidgetProperties.visible().observe(composite), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());
    }

    private void createAuthenticationComposite(Composite parent) {
        var authenticationGroup = new Group(parent, SWT.NONE);
        authenticationGroup.setLayout(GridLayoutFactory.fillDefaults().create());
        authenticationGroup
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, true).create());
        authenticationGroup.setText(Messages.authentication);

        var usernamePwdComposite = new Composite(authenticationGroup, SWT.NONE);
        usernamePwdComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 5).numColumns(3).create());
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
    }

    private void createPasswordEncryptButton() {
        passwordField.getToolBar().ifPresent(toolbar -> {
            encryptPwdItem = new ToolItem(toolbar, SWT.PUSH);
            encryptPwdItem.addListener(SWT.Selection, e -> encryptPassword());
            encryptPwdItem.setImage(Pics.getImage(PicsConstants.key));
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

    private void createProtocolHostPortComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        new TextWidget.Builder()
                .withLabel(Messages.protocol)
                .labelAbove()
                .fill()
                .widthHint(80)
                .bindTo(PojoProperties.value("protocol", String.class).observeDetail(selectionObservable))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.host)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoProperties.value("host", String.class).observeDetail(selectionObservable))
                .inContext(ctx)
                .withValidator(new EmptyInputValidator(Messages.host))
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
                .bindTo(PojoProperties.value("port", Integer.class).observeDetail(selectionObservable))
                .withModelToTargetStrategy(updateValueStrategy()
                        .withConverter(ConverterBuilder.<Integer, String> newConverter()
                                .fromType(Integer.class)
                                .toType(String.class)
                                .withConvertFunction(String::valueOf)
                                .create())
                        .create())
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(intValueValidaor)
                        .withConverter(ConverterBuilder.<String, Integer> newConverter()
                                .fromType(String.class)
                                .toType(Integer.class)
                                .withConvertFunction(Integer::valueOf)
                                .create())
                        .create())
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);
    }

    private void createProxyListComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

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
        addItem.setToolTipText(Messages.addProxyTooltip);
        addItem.addListener(SWT.Selection, e -> addProxy());

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setToolTipText(Messages.deleteProxyTooltip);
        deleteItem.setText(Messages.delete);
        deleteItem.addListener(SWT.Selection, e -> removeProxy());

        new ToolItem(toolBar, SWT.SEPARATOR);

        activateItem = new ToolItem(toolBar, SWT.PUSH);
        activateItem.setImage(Pics.getImageDescriptor(PicsConstants.checkmark).createImage());
        activateItem.setText(Messages.activate);
        activateItem.setToolTipText(Messages.activateProxyTooltip);
        activateItem.addListener(SWT.Selection, e -> activateProxy());
    }

    private void activateProxy() {
        selectionObservable.getValue().setActive(true);
        proxiesObservable.stream()
                .filter(proxy -> !Objects.equals(proxy, selectionObservable.getValue()))
                .forEach(proxy -> proxy.setActive(false));
        refreshViewer();
    }

    private void removeProxy() {
        if (MessageDialog.openQuestion(getShell(), Messages.removeProxyConfirmationTitle,
                String.format(Messages.removeProxyConfirmation, selectionObservable.getValue().getId()))) {
            proxiesObservable.remove(selectionObservable.getValue());
            refreshViewer();
        }
    }

    private void addProxy() {
        var proxy = new Proxy();
        String name = StringIncrementer.getNextIncrement(DEFAULT_PROXY_NAME,
                proxiesObservable.stream().map(Proxy::getId).collect(Collectors.toList()));
        proxy.setId(name);
        proxy.setActive(proxiesObservable.stream().noneMatch(Proxy::isActive));
        proxiesObservable.add(proxy);
        selectionObservable.setValue(proxy);
        refreshViewer();
    }

    protected void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        viewer.setUseHashlookup(true);
        createProxyColumn(viewer);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTable().setLayout(layout);

        viewer.setContentProvider(new ObservableListContentProvider<Proxy>());
        viewer.setInput(proxiesObservable);

        ColumnViewerToolTipSupport.enableFor(viewer);

        selectionObservable = ViewerProperties.singleSelection(Proxy.class).observe(viewer);
    }

    private void createProxyColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.setLabelProvider(new LabelProviderBuilder<Proxy>()
                .withTextProvider(Proxy::getId)
                .withImageProvider(
                        p -> p.isActive() ? Pics.getImageDescriptor(PicsConstants.checkmark).createImage() : null)
                .withTooltipProvider(p -> p.isActive() ? Messages.active : null)
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
    }

    private void refreshViewer() {
        getDisplay().asyncExec(() -> viewer.refresh());
    }

    public void refresh() {
        refreshViewer();
    }

}
