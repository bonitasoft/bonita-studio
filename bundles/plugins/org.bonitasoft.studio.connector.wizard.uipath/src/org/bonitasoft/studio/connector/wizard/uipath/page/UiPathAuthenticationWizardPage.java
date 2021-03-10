/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.connector.wizard.uipath.page;

import java.util.Objects;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.widgets.CustomStackLayout;
import org.bonitasoft.studio.connector.model.definition.Checkbox;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorConfigurationSupport;
import org.bonitasoft.studio.connector.model.definition.wizard.GeneratedConnectorWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.bonitasoft.studio.connector.wizard.uipath.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.uipath.switchBuilder.UIPathPageComponentSwitchBuilder;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class UiPathAuthenticationWizardPage extends GeneratedConnectorWizardPage {

    private static final String CLOUD_INPUT = "cloud";
    private static final String CLOUD_ID = "cloudWidget";

    // on-premise fields
    private static final String URL_INPUT = "url";
    private static final String URL_ID = "urlWidget";
    private static final String TENANT_INPUT = "tenant";
    private static final String TENANT_ID = "tenantWidget";
    private static final String USER_INPUT = "user";
    private static final String USER_ID = "userWidget";
    private static final String PASSWORD_INPUT = "password";
    private static final String PASSWORD_ID = "passwordWidget";

    // cloud fields
    private static final String ACCOUNT_LOGICAL_NAME_INPUT = "accountLogicalName";
    private static final String ACCOUNT_LOGICAL_NAME_ID = "accountLogicalNameWidget";
    private static final String TENANT_LOGICAL_NAME_INPUT = "tenantLogicalName";
    private static final String TENANT_LOGICAL_NAME_ID = "tenantLogicalNameWidget";
    private static final String USER_KEY_INPUT = "userKey";
    private static final String USER_KEY_ID = "userKeyWidget";
    private static final String CLIENT_ID_INPUT = "clientId";
    private static final String CLIENT_ID_ID = "clientIdWidget";
    private static final String ORGANIZATION_UNIT_ID_INPUT = "organizationUnitId";
    private static final String ORGANIZATION_UNIT_WIDGET_ID = "organizationUnitIdWidget";

    private SelectObservableValue modeObservable;

    public UiPathAuthenticationWizardPage(String pageName) {
        super(pageName);
    }

    public UiPathAuthenticationWizardPage() {
        super();
    }

    @Override
    public Control doCreateControl(Composite parent, EMFDataBindingContext context) {
        if (Objects.equals(getPage().getId(), "authenticationPage")) {
            Composite mainComposite = new Composite(parent, SWT.NONE);
            mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 10)
                    .spacing(LayoutConstants.getSpacing().x, 10).create());
            mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

            UIPathPageComponentSwitchBuilder builder = new UIPathPageComponentSwitchBuilder(getElementContainer(),
                    getDefinition(),
                    getConfiguration(), context, getMessageProvider(), getExpressionTypeFilter());

            createRadioButtons(mainComposite, context);
            addValidators(builder);

            Label separator = new Label(mainComposite, SWT.HORIZONTAL | SWT.SEPARATOR);
            separator.setLayoutData(GridDataFactory.fillDefaults().create());

            Composite stackParent = new Composite(mainComposite, SWT.NONE);
            stackParent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
            stackParent.setLayout(GridLayoutFactory.fillDefaults().create());
            
            createStackComposite(stackParent, builder, context);

            return mainComposite;
        }
        return super.doCreateControl(parent, context);
    }

    private void addValidators(UIPathPageComponentSwitchBuilder builder) {
        builder.addCustomMandatoryValidator(ACCOUNT_LOGICAL_NAME_ID,
                new MandatoryValidator(Mode.CLOUD, modeObservable, builder.getLabel(ACCOUNT_LOGICAL_NAME_ID)));
        builder.addCustomMandatoryValidator(TENANT_LOGICAL_NAME_ID,
                new MandatoryValidator(Mode.CLOUD, modeObservable, builder.getLabel(TENANT_LOGICAL_NAME_ID)));
        builder.addCustomMandatoryValidator(USER_KEY_ID,
                new MandatoryValidator(Mode.CLOUD, modeObservable, builder.getLabel(USER_KEY_ID)));
        builder.addCustomMandatoryValidator(CLIENT_ID_ID,
                new MandatoryValidator(Mode.CLOUD, modeObservable, builder.getLabel(CLIENT_ID_ID)));

        builder.addCustomMandatoryValidator(URL_ID,
                new MandatoryValidator(Mode.PREMISE, modeObservable, builder.getLabel(URL_ID)));
        builder.addCustomMandatoryValidator(TENANT_ID,
                new MandatoryValidator(Mode.PREMISE, modeObservable, builder.getLabel(TENANT_ID)));
        builder.addCustomMandatoryValidator(USER_ID,
                new MandatoryValidator(Mode.PREMISE, modeObservable, builder.getLabel(USER_ID)));
        builder.addCustomMandatoryValidator(PASSWORD_ID,
                new MandatoryValidator(Mode.PREMISE, modeObservable, builder.getLabel(PASSWORD_ID)));
    }

    private void createStackComposite(Composite parent, PageComponentSwitchBuilder builder, EMFDataBindingContext ctx) {
        Composite stackComposite = new Composite(parent, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        CustomStackLayout stackLayout = new CustomStackLayout(stackComposite);
        stackComposite.setLayout(stackLayout);

        Composite onPremiseComposite = createOnPremiseComposite(stackComposite, builder);
        Composite cloudComposite = createCloudComposite(stackComposite, builder);

        ctx.bindValue(PojoProperties.value("topControl").observe(stackLayout), modeObservable,
                UpdateStrategyFactory.neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(ConverterBuilder.<Mode, Composite> newConverter()
                                .fromType(Mode.class)
                                .toType(Composite.class)
                                .withConvertFunction(
                                        mode -> Objects.equals(mode, Mode.CLOUD) ? cloudComposite : onPremiseComposite)
                                .create())
                        .create());
    }

    protected Composite createCloudComposite(Composite parent, PageComponentSwitchBuilder builder) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createText(builder, composite, ACCOUNT_LOGICAL_NAME_INPUT, ACCOUNT_LOGICAL_NAME_ID);
        createText(builder, composite, TENANT_LOGICAL_NAME_INPUT, TENANT_LOGICAL_NAME_ID);
        createText(builder, composite, ORGANIZATION_UNIT_ID_INPUT, ORGANIZATION_UNIT_WIDGET_ID);
        createText(builder, composite, USER_KEY_INPUT, USER_KEY_ID);
        createText(builder, composite, CLIENT_ID_INPUT, CLIENT_ID_ID);

        return composite;
    }

    protected Composite createOnPremiseComposite(Composite parent, PageComponentSwitchBuilder builder) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createText(builder, composite, URL_INPUT, URL_ID);
        createText(builder, composite, TENANT_INPUT, TENANT_ID);
        createText(builder, composite, USER_INPUT, USER_ID);
        createText(builder, composite, PASSWORD_INPUT, PASSWORD_ID);

        return composite;
    }

    private void createRadioButtons(Composite parent, EMFDataBindingContext context) {
        Checkbox cloudCheckbox = ConnectorDefinitionFactory.eINSTANCE.createCheckbox();
        cloudCheckbox.setInputName(CLOUD_INPUT);
        cloudCheckbox.setId(CLOUD_ID);

        Input cloudInput = getDefinition().getInput().stream()
                .filter(input -> Objects.equals(input.getName(), CLOUD_INPUT))
                .findFirst().orElseThrow(() -> new RuntimeException("Cloud input not found"));

        ConnectorParameter parameter = new ConnectorConfigurationSupport(getConfiguration())
                .getConnectorParameter(CLOUD_INPUT, cloudCheckbox, cloudInput);

        Composite radioComposite = new Composite(parent, SWT.NONE);
        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.FILL).grab(true, false).create());

        ((Expression) parameter.getExpression()).setType(ExpressionConstants.CONSTANT_TYPE);
        ((Expression) parameter.getExpression()).setReturnType(cloudInput.getType());
        String content = ((Expression) parameter.getExpression()).getContent();

        Label platformTypeLabel = new Label(radioComposite, SWT.NONE);
        platformTypeLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        platformTypeLabel.setText(Messages.platformTypeLabel);

        Button onPremise = new Button(radioComposite, SWT.RADIO);
        onPremise.setText(Messages.onPremise);
        onPremise.setLayoutData(GridDataFactory.fillDefaults().create());

        Button cloud = new Button(radioComposite, SWT.RADIO);
        cloud.setText(Messages.cloud);
        cloud.setLayoutData(GridDataFactory.fillDefaults().create());

        modeObservable = new SelectObservableValue();
        modeObservable.addOption(Mode.PREMISE, WidgetProperties.selection().observe(onPremise));
        modeObservable.addOption(Mode.CLOUD, WidgetProperties.selection().observe(cloud));

        modeObservable.addValueChangeListener(e -> {
            Boolean isCloud = Objects.equals(Mode.CLOUD, modeObservable.getValue());
            ((Expression) parameter.getExpression()).setContent(isCloud.toString());
            ((Expression) parameter.getExpression()).setName(isCloud.toString());
            context.updateModels(); // trigger custom mandatory validators
        });
        modeObservable.setValue(Objects.equals(content, "true") ? Mode.CLOUD : Mode.PREMISE);
    }

    protected void createText(PageComponentSwitchBuilder builder, Composite composite, String name, String id) {
        Text text = ConnectorDefinitionFactory.eINSTANCE.createText();
        text.setInputName(name);
        text.setId(id);
        builder.createTextControl(composite, text);
    }

}

enum Mode {
    PREMISE, CLOUD
}

class MandatoryValidator implements IValidator<String> {

    private Mode mode;
    private IObservableValue<Mode> currentMode;
    private EmptyInputValidator emptyInputValidator;

    public MandatoryValidator(Mode mode, IObservableValue<Mode> currentMode, String inputName) {
        this.mode = mode;
        this.currentMode = currentMode;
        emptyInputValidator = new EmptyInputValidator(inputName);
    }

    @Override
    public IStatus validate(String value) {
        return Objects.equals(mode, currentMode.getValue())
                ? emptyInputValidator.validate(value)
                : ValidationStatus.ok();
    }

}
