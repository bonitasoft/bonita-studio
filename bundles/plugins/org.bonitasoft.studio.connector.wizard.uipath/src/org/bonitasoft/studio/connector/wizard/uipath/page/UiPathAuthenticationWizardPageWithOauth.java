/**
 * Copyright (C) 2025 Bonitasoft S.A.
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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.bpm.connector.model.definition.Input;
import org.bonitasoft.bpm.connector.model.definition.Select;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.studio.common.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.ui.widgets.CustomStackLayout;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorConfigurationSupport;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.bonitasoft.studio.connector.wizard.uipath.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.uipath.switchBuilder.UIPathPageComponentSwitchBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class UiPathAuthenticationWizardPageWithOauth extends UiPathAuthenticationWizardPage {
	
	protected static final String CLOUD_AUTH_INPUT = "cloudAuthType";
	protected static final String CLOUD_AUTH_ID = "cloudAuthTypeRadio";
	protected static final String CLOUD_AUTH_TOKEN_VALUE = "Token (Bearer)";
	protected static final String CLOUD_AUTH_CLIENT_CREDENTIALS_VALUE = "Client credentials (Oauth)";
	protected static final String CLIENT_SECRET_INPUT = "clientSecret";
	protected static final String CLIENT_SECRET_WIDGET_ID = "clientSecretWidget";
	protected static final String SCOPE_INPUT = "scope";
	protected static final String SCOPE_WIDGET_ID = "scopeWidget";
	protected static final String TOKEN_INPUT = "token";
	protected static final String TOKEN_WIDGET_ID = "tokenWidget";
	
	
	protected IObservableValue<String> cloudAuthTypeObservable = new WritableValue<String>();

    public UiPathAuthenticationWizardPageWithOauth(String pageName) {
        super(pageName);
    }

    public UiPathAuthenticationWizardPageWithOauth() {
        super();
    }

    @Override
    protected void addValidators(UIPathPageComponentSwitchBuilder builder) {
        builder.addCustomMandatoryValidator(ACCOUNT_LOGICAL_NAME_ID,
                new MandatoryValidator(Mode.CLOUD, modeObservable, builder.getLabel(ACCOUNT_LOGICAL_NAME_ID)));
        builder.addCustomMandatoryValidator(TENANT_LOGICAL_NAME_ID,
                new MandatoryValidator(Mode.CLOUD, modeObservable, builder.getLabel(TENANT_LOGICAL_NAME_ID)));
        builder.addCustomMandatoryValidator(CLIENT_ID_ID,
                new MandatoryValidatorForCloudAuth(Mode.CLOUD, Messages.cloudAuthClientCredentialsLabel, modeObservable, cloudAuthTypeObservable, builder.getLabel(CLIENT_ID_ID)));
        builder.addCustomMandatoryValidator(CLIENT_SECRET_WIDGET_ID, 
                new MandatoryValidatorForCloudAuth(Mode.CLOUD, Messages.cloudAuthClientCredentialsLabel, modeObservable, cloudAuthTypeObservable, builder.getLabel(CLIENT_SECRET_WIDGET_ID)));
        builder.addCustomMandatoryValidator(TOKEN_WIDGET_ID,
                new MandatoryValidatorForCloudAuth(Mode.CLOUD, Messages.cloudAuthTokenLabel, modeObservable, cloudAuthTypeObservable, builder.getLabel(TOKEN_WIDGET_ID)));

        builder.addCustomMandatoryValidator(URL_ID,
                new MandatoryValidator(Mode.PREMISE, modeObservable, builder.getLabel(URL_ID)));
        builder.addCustomMandatoryValidator(TENANT_ID,
                new MandatoryValidator(Mode.PREMISE, modeObservable, builder.getLabel(TENANT_ID)));
        builder.addCustomMandatoryValidator(USER_ID,
                new MandatoryValidator(Mode.PREMISE, modeObservable, builder.getLabel(USER_ID)));
        builder.addCustomMandatoryValidator(PASSWORD_ID,
                new MandatoryValidator(Mode.PREMISE, modeObservable, builder.getLabel(PASSWORD_ID)));
    }

    @Override
    protected void createStackComposite(Composite parent, PageComponentSwitchBuilder builder, EMFDataBindingContext ctx) {
        Composite stackComposite = new Composite(parent, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        CustomStackLayout stackLayout = new CustomStackLayout(stackComposite);
        stackComposite.setLayout(stackLayout);

        Composite onPremiseComposite = createOnPremiseComposite(stackComposite, builder);
        Composite cloudComposite = createCloudComposite(stackComposite, builder, ctx);

        ctx.bindValue(PojoProperties.value("topControl").observe(stackLayout), modeObservable,
                UpdateStrategyFactory.neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(IConverter.<Mode, Composite> create(mode -> Objects.equals(mode, Mode.CLOUD) ? cloudComposite : onPremiseComposite))
                        .create());
    }

    protected Composite createCloudComposite(Composite parent, PageComponentSwitchBuilder builder, EMFDataBindingContext ctx) {
        Composite cloudComposite = new Composite(parent, SWT.NONE);
        cloudComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        cloudComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
    	
        Composite selectContainerComposite = new Composite(cloudComposite, SWT.NONE);
        selectContainerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        selectContainerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createSelectForCloud(builder, selectContainerComposite, ctx);
        
        Composite clientCredentialsComposite = createClientCredentialsComposite(cloudComposite, builder, ctx);
        Composite tokenCredentialsComposite = createTokenComposite(cloudComposite, builder, ctx);
        setCompositeVisibility(tokenCredentialsComposite, clientCredentialsComposite, Messages.cloudAuthTokenLabel.equals(cloudAuthTypeObservable.getValue()));
        setCompositeVisibility(clientCredentialsComposite, tokenCredentialsComposite, Messages.cloudAuthClientCredentialsLabel.equals(cloudAuthTypeObservable.getValue()));
        
        cloudAuthTypeObservable.addValueChangeListener(e -> {
        	var newValue = getAuthTypeComboValue(e.diff.getNewValue());
        	setCompositeVisibility(tokenCredentialsComposite, clientCredentialsComposite, Messages.cloudAuthTokenLabel.equals(newValue));
        	setCompositeVisibility(clientCredentialsComposite, tokenCredentialsComposite, Messages.cloudAuthClientCredentialsLabel.equals(newValue));
        });
        return cloudComposite;
    }
    
    private void setCompositeVisibility(Composite composite, Composite compositeBelow, boolean isVisible) {
    	composite.setVisible(isVisible);
    	if (isVisible) {
    		composite.moveAbove(compositeBelow);
                //Refresh the layout to match visible content only
    		composite.getParent().layout();
    	}
    }
    
    protected Composite createClientCredentialsComposite(Composite parent, PageComponentSwitchBuilder builder, EMFDataBindingContext ctx) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createText(builder, composite, ACCOUNT_LOGICAL_NAME_INPUT, ACCOUNT_LOGICAL_NAME_ID);
        createText(builder, composite, TENANT_LOGICAL_NAME_INPUT, TENANT_LOGICAL_NAME_ID);
        createText(builder, composite, ORGANIZATION_UNIT_ID_INPUT, ORGANIZATION_UNIT_WIDGET_ID);
        createText(builder, composite, CLIENT_ID_INPUT, CLIENT_ID_ID);
        createText(builder, composite, CLIENT_SECRET_INPUT, CLIENT_SECRET_WIDGET_ID);
        createText(builder, composite, SCOPE_INPUT, SCOPE_WIDGET_ID);

        return composite;
    }
    
    protected Composite createTokenComposite(Composite parent, PageComponentSwitchBuilder builder, EMFDataBindingContext ctx) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createText(builder, composite, ACCOUNT_LOGICAL_NAME_INPUT, ACCOUNT_LOGICAL_NAME_ID);
        createText(builder, composite, TENANT_LOGICAL_NAME_INPUT, TENANT_LOGICAL_NAME_ID);
        createText(builder, composite, ORGANIZATION_UNIT_ID_INPUT, ORGANIZATION_UNIT_WIDGET_ID);
        createText(builder, composite, TOKEN_INPUT, TOKEN_WIDGET_ID);

        return composite;
    }

    private void createSelectForCloud(PageComponentSwitchBuilder builder, Composite parent, EMFDataBindingContext context) {
    	Select cloudAuthSelect = ConnectorDefinitionFactory.eINSTANCE.createSelect();
    	cloudAuthSelect.setInputName(CLOUD_AUTH_INPUT);
    	cloudAuthSelect.setId(CLOUD_AUTH_ID);

        Input cloudAuthInput = getDefinition().getInput().stream()
                .filter(input -> Objects.equals(input.getName(), CLOUD_AUTH_INPUT))
                .findFirst().orElseThrow(() -> new RuntimeException("Cloud Auth input not found"));

        ConnectorParameter parameter = new ConnectorConfigurationSupport(getConfiguration())
                .getConnectorParameter(CLOUD_AUTH_INPUT, cloudAuthSelect, cloudAuthInput);

        ((Expression) parameter.getExpression()).setType(ExpressionConstants.CONSTANT_TYPE);
        ((Expression) parameter.getExpression()).setReturnType(cloudAuthInput.getType());
        String content = ((Expression) parameter.getExpression()).getContent();

        String[] items = new String[] { Messages.cloudAuthClientCredentialsLabel, Messages.cloudAuthTokenLabel };
        Combo authCombo = createSelect(builder, parent, CLOUD_AUTH_INPUT, CLOUD_AUTH_ID, Arrays.asList(items));

        context.bindValue(WidgetProperties.comboSelection().observe(authCombo), cloudAuthTypeObservable);
        
        cloudAuthTypeObservable.addValueChangeListener(e -> {
        	var newValue = getAuthTypeComboValue(e.diff.getNewValue());
            ((Expression) parameter.getExpression()).setContent(newValue);
            ((Expression) parameter.getExpression()).setName(newValue);
            context.updateModels(); // trigger custom mandatory validators
        });
        cloudAuthTypeObservable.setValue(content);
    }
    
	protected String getAuthTypeComboValue(String displayValue) {
		return Messages.cloudAuthTokenLabel.equals(displayValue) ? CLOUD_AUTH_TOKEN_VALUE : CLOUD_AUTH_CLIENT_CREDENTIALS_VALUE;
	}
	
    protected Combo createSelect(PageComponentSwitchBuilder builder, Composite composite, String name, String id, List<String> list) {
        Select select = ConnectorDefinitionFactory.eINSTANCE.createSelect();
        select.setInputName(name);
        select.setId(id);
        select.getItems().addAll(list);
        return builder.createSelectControl(composite, select);
    }
}

class MandatoryValidatorForCloudAuth implements IValidator<String> {

    private Mode mode;
    private String cloudAuthType;
    private IObservableValue<Mode> currentMode;
    private IObservableValue<String> cloudAuthTypeObservable;
    private EmptyInputValidator emptyInputValidator;

    public MandatoryValidatorForCloudAuth(Mode mode, String cloudAuthType, IObservableValue<Mode> currentMode, IObservableValue<String> cloudAuthTypeObservable, String inputName) {
        this.mode = mode;
        this.cloudAuthType = cloudAuthType;
        this.currentMode = currentMode;
        this.cloudAuthTypeObservable = cloudAuthTypeObservable;
        emptyInputValidator = new EmptyInputValidator(inputName);
    }

    @Override
    public IStatus validate(String value) {
        return Objects.equals(mode, currentMode.getValue()) && Objects.equals(cloudAuthType, cloudAuthTypeObservable.getValue())
                ? emptyInputValidator.validate(value)
                : ValidationStatus.ok();
    }

}
