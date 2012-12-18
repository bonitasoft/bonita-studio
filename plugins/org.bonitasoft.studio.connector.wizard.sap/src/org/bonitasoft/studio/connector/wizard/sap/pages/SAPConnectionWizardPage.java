/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.connector.wizard.sap.pages;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.connector.wizard.sap.SaveSapConfigurationWizard;
import org.bonitasoft.studio.connector.wizard.sap.i18n.Messages;
import org.bonitasoft.studio.connectors.wizards.DefaultGeneratedConnectorWizardPage;
import org.bonitasoft.studio.connectors.wizards.DefaultSetConnectorFieldsWizard;
import org.bonitasoft.studio.connectors.wizards.extensions.ICustomExtensionWizardPage;
import org.bonitasoft.studio.groovy.ui.providers.ITextOrDataFactory;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.repository.connectors.connector.ConnectorRepository;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.ow2.bonita.connector.core.ConnectorDescription;
import org.ow2.bonita.connector.core.desc.Setter;

/**
 * @author Romain Bioteau
 *
 */
public class SAPConnectionWizardPage extends DefaultGeneratedConnectorWizardPage implements ICustomExtensionWizardPage {

	private static final GridData WIDGET_GRID_DATA = new GridData(SWT.FILL,SWT.TOP,true,false);
	private static final String SAP_CREATE_CLIENT_ID = "SAPCreateClient"; //$NON-NLS-1$
	private static final String SET_EXISTING_CONNECTION_NAME = "setExistingConnectionName"; //$NON-NLS-1$
	private static final String SET_HOST = "setHost"; //$NON-NLS-1$
	private static final String SET_LANGUAGE = "setLanguage"; //$NON-NLS-1$
	private static final String SET_PASSWORD = "setPassword"; //$NON-NLS-1$
	private static final String SET_USER = "setUser"; //$NON-NLS-1$
	private static final String SET_SERVER_TYPE = "setServerType"; //$NON-NLS-1$
	private static final String SET_CLIENT =  "setClient"; //$NON-NLS-1$
	private static final String SERVER_TYPE_MESSAGE_SERVER = "MessageServer"; //$NON-NLS-1$
	private static final String SERVER_TYPE_APPLICATION_SERVER = "ApplicationServer"; //$NON-NLS-1$
	private static final String SET_SYSTEM_NUMBER = "setSystemNumber"; //$NON-NLS-1$
	private static final String SET_SYSTEM_ID ="setSystemId"; //$NON-NLS-1$
	private static final String SET_GROUP_NAME ="setGroupName"; //$NON-NLS-1$
	private static final String SET_USE_EXISTING = "setUseExitingConnection"; //$NON-NLS-1$

	private List<Setter> inputs;
	private StackLayout stackLayout;
	private Composite connectionParameterLayer;
	private Composite applicationServerComposite;
	private Composite messageServerComposite;
	private Composite parentLayer;
	private StackLayout parentStackLayout;
	private Composite configurationComposite;
	private Composite chooseExistintgComposite;
	private boolean onlyNewConnection;

	public SAPConnectionWizardPage() {
		super(SAPConnectionWizardPage.class.getName());
		requiredFields = new ArrayList<IRequirement>();
	}

	public SAPConnectionWizardPage(DefaultSetConnectorFieldsWizard wizard, String pageId, ConnectorDescription connector, Element container) {
		super(wizard,pageId,connector,container);
	}

	public SAPConnectionWizardPage(DefaultSetConnectorFieldsWizard wizard, String pageId, ConnectorDescription connectorDesc, Connector connector) {
		super(wizard, pageId, connectorDesc, (Element)connector.eContainer());
	}

	protected void updateScrolledComposite() {
		scrolledComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setContent(mainComposite);
	}

	private void createChooseExistingWidget(final Setter setter,Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.existingConfigurationLabel);

		final TextOrData text = new TextOrData(composite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);

		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});

		if (setter.getRequired() != null && setter.getRequired().length() == 0) {
			requiredFields.add(new IRequirement() {
				public boolean isSet() {
					return text.getText() != null && text.getText().length() > 0 ||(parentStackLayout!=null && parentStackLayout.topControl != null  && parentStackLayout.topControl.equals(configurationComposite));
				}
			});
		}

		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString());
		}

		if(setter.getParameters().length > 0){
			((Combo)text.getControl()).add(setter.getParameters()[0].toString());
		}

	}

	private void createChooseExistingOrNewConfigurationWidget(final Setter setter, Composite composite) {


		Composite radioComposite = new Composite(composite,SWT.NONE);
		radioComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		GridData gd = new GridData(SWT.FILL, SWT.TOP, false, false);
		gd.horizontalIndent = 15 ;
		radioComposite.setLayoutData(gd);
		final Button newConfigChoiceButton = new Button(radioComposite,SWT.RADIO);
		newConfigChoiceButton.setText(Messages.newConfigurationLabel);

		final Button useExistingChoiceButton = new Button(radioComposite,SWT.RADIO);
		useExistingChoiceButton.setText(Messages.useExistingConfigurationLabel);

		newConfigChoiceButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if(newConfigChoiceButton.getSelection())
					useExistingChoiceButton.setSelection(false);
				else
					useExistingChoiceButton.setSelection(true);

				setParamValue(setter, useExistingChoiceButton.getSelection());
				updateParentStack(useExistingChoiceButton.getSelection());
				setPageComplete(allRequiredWidgetsSet());

			}

		});

		useExistingChoiceButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if(useExistingChoiceButton.getSelection())
					newConfigChoiceButton.setSelection(false);
				else
					newConfigChoiceButton.setSelection(true);

				setParamValue(setter, useExistingChoiceButton.getSelection());
				setPageComplete(allRequiredWidgetsSet());
			}

		});

		if(currentParameters.get(setter.getSetterName()) != null ){
			useExistingChoiceButton.setSelection(Boolean.parseBoolean(currentParameters.get(setter.getSetterName()).toString()));
			newConfigChoiceButton.setSelection(!Boolean.parseBoolean(currentParameters.get(setter.getSetterName()).toString()));
		}else if(setter.getParameters().length > 0){
			useExistingChoiceButton.setSelection(Boolean.parseBoolean(setter.getParameters()[0].toString())) ;
			newConfigChoiceButton.setSelection(!Boolean.parseBoolean(setter.getParameters()[0].toString())) ;
		}

	}

	@Override
	protected void createMainCompositeContent(Composite composite) {
		inputs = connector.getInputs() ;
		currentParameters = ((DefaultSetConnectorFieldsWizard)getWizard()).getInputParameters();

		if(!onlyNewConnection){
			createChooseExistingOrNewConfigurationWidget(getSetter(SET_USE_EXISTING),mainComposite);
		}

		parentLayer  = new Composite(mainComposite, SWT.NONE);
		parentLayer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		parentStackLayout= new StackLayout() ;
		parentLayer.setLayout(parentStackLayout);

		configurationComposite = new Composite(parentLayer, SWT.NONE);
		configurationComposite.setLayout(new GridLayout(2, false));
		configurationComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));


		chooseExistintgComposite = new Composite(parentLayer, SWT.NONE);
		chooseExistintgComposite.setLayout(new GridLayout(2, false));
		chooseExistintgComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		if(!onlyNewConnection){
			createChooseExistingWidget(getSetter(SET_EXISTING_CONNECTION_NAME),chooseExistintgComposite);
		}

		createServerTypeWidget(getSetter(SET_SERVER_TYPE),configurationComposite);
		createClientWidget(getSetter(SET_CLIENT),configurationComposite);
		createUserWidget(getSetter(SET_USER),configurationComposite);
		createPasswordWidget(getSetter(SET_PASSWORD),configurationComposite);
		createLanguageWidget(getSetter(SET_LANGUAGE),configurationComposite);
		createHostWidget(getSetter(SET_HOST),configurationComposite);

		connectionParameterLayer  = new Composite(configurationComposite, SWT.NONE);
		connectionParameterLayer.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,2,1));
		stackLayout= new StackLayout() ;
		connectionParameterLayer.setLayout(stackLayout);

		applicationServerComposite = new Composite(connectionParameterLayer, SWT.NONE);
		applicationServerComposite.setLayout(new GridLayout(2, false));

		messageServerComposite = new Composite(connectionParameterLayer, SWT.NONE);
		messageServerComposite.setLayout(new GridLayout(2, false));

		createSysNumberWidget(getSetter(SET_SYSTEM_NUMBER),applicationServerComposite);
		createSysIdWidget(getSetter(SET_SYSTEM_ID),messageServerComposite);
		createGroupNameWidget(getSetter(SET_GROUP_NAME),messageServerComposite);

		if(!onlyNewConnection){
			if(currentParameters.get(getSetter(SET_USE_EXISTING).getSetterName()) != null){
				updateParentStack(Boolean.parseBoolean(currentParameters.get(getSetter(SET_USE_EXISTING).getSetterName()).toString()));
			}else{
				updateParentStack(Boolean.parseBoolean(getSetter(SET_USE_EXISTING).getParameters()[0].toString()));
			}
		}else{
			updateParentStack(false);
		}

		if(currentParameters.get(getSetter(SET_SERVER_TYPE).getSetterName()) != null){
			updateStack(currentParameters.get(getSetter(SET_SERVER_TYPE).getSetterName()).toString());
		}else{
			updateStack(getSetter(SET_SERVER_TYPE).getParameters()[0].toString());
		}

	}

	private Setter getSetter(String setterName) {
		for(Setter setter : inputs){
			if(setter.getSetterName().equals(setterName))
				return setter ;
		}
		return null;
	}

	private void createGroupNameWidget(final Setter setter, Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.groupNameLabel);

		final TextOrData text = new TextOrData(composite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);


		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});

		requiredFields.add(new IRequirement() {
			public boolean isSet() {
				return text.getText() != null && text.getText().trim().length() > 0 || (stackLayout!=null && stackLayout.topControl != null  && stackLayout.topControl.equals(applicationServerComposite))  ||(parentStackLayout!=null && parentStackLayout.topControl != null  && parentStackLayout.topControl.equals(chooseExistintgComposite)); 
			}
		});

		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString()) ;
		}

		if(setter.getParameters().length > 0){
			((Combo)text.getControl()).add(setter.getParameters()[0].toString());
		}
	}

	private void createSysIdWidget(final Setter setter, Composite composite) {

		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.sysIdLabel);

		final TextOrData text = new TextOrData(composite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);

		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});

		requiredFields.add(new IRequirement() {
			public boolean isSet() {
				return text.getText() != null && text.getText().trim().length() > 0 || (stackLayout!=null && stackLayout.topControl != null && stackLayout.topControl.equals(applicationServerComposite))  ||(parentStackLayout!=null && parentStackLayout.topControl != null  && parentStackLayout.topControl.equals(chooseExistintgComposite));
			}
		});

		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString()) ;
		}

		if(setter.getParameters().length > 0){
			((Combo)text.getControl()).add(setter.getParameters()[0].toString());
		}

	}

	private void createSysNumberWidget(final Setter setter, Composite composite) {

		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.sysNumberLabel);

		final TextOrData text = new TextOrData(composite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);

		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});

		if (setter.getRequired() != null && setter.getRequired().length() == 0) {
			requiredFields.add(new IRequirement() {
				public boolean isSet() {
					return text.getText() != null && text.getText().trim().length() > 0 || (stackLayout!=null && stackLayout.topControl != null  && stackLayout.topControl.equals(messageServerComposite))  ||(parentStackLayout!=null && parentStackLayout.topControl != null  && parentStackLayout.topControl.equals(chooseExistintgComposite));
				}
			});
		}

		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString()) ;
		}

		if(setter.getParameters().length > 0){
			((Combo)text.getControl()).add(setter.getParameters()[0].toString());
		}

	}

	private void createHostWidget(final Setter setter, Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.hostNameLabel);

		final TextOrData text = new TextOrData(composite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);

		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});


		if (setter.getRequired() != null && setter.getRequired().length() == 0) {
			requiredFields.add(new IRequirement() {
				public boolean isSet() {
					return text.getText() != null && text.getText().trim().length() > 0 ||(parentStackLayout!=null && parentStackLayout.topControl != null  && parentStackLayout.topControl.equals(chooseExistintgComposite));
				}
			});
		}



		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString()) ;
		}

		if(setter.getParameters().length > 0){
			((Combo)text.getControl()).add(setter.getParameters()[0].toString());
		}


	}

	private void createLanguageWidget(final Setter setter,Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.languageLabel);

		final TextOrData text = new TextOrData(composite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);


		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});

		if (setter.getRequired() != null && setter.getRequired().length() == 0) {
			requiredFields.add(new IRequirement() {
				public boolean isSet() {
					return text.getText() != null && text.getText().trim().length() > 0  ||(parentStackLayout!=null && parentStackLayout.topControl != null  && parentStackLayout.topControl.equals(chooseExistintgComposite));
				}
			});
		}

		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString()) ;
		}

		if(setter.getParameters().length > 0){
			((Combo)text.getControl()).add(setter.getParameters()[0].toString());
		}

	}

	private void createUserWidget(final Setter setter,Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.userIdLabel);

		final TextOrData text = new TextOrData(composite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);

		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});



		if (setter.getRequired() != null && setter.getRequired().length() == 0) {
			requiredFields.add(new IRequirement() {
				public boolean isSet() {
					return text.getText() != null && text.getText().trim().length() > 0 ||(parentStackLayout!=null && parentStackLayout.topControl != null  && parentStackLayout.topControl.equals(chooseExistintgComposite));
				}
			});
		}
		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString()) ;
		}

		if(setter.getParameters().length > 0){
			((Combo)text.getControl()).add(setter.getParameters()[0].toString());
		}

	}

	private void createPasswordWidget(final Setter setter,Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.passwordLabel);

		final TextOrData text = new TextOrData(composite, container,true);
		text.getControl().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));

		text.addValueChangedListener(new Listener() {
			public void handleEvent(Event event) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}
		});

		if (setter.getRequired() != null && setter.getRequired().length() == 0) {
			requiredFields.add(new IRequirement() {
				public boolean isSet() {
					return text.getText() != null && text.getText().trim().length() > 0  ||(parentStackLayout!=null && parentStackLayout.topControl != null  && parentStackLayout.topControl.equals(chooseExistintgComposite));
				}
			});
		}

		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString()) ;
		}


	}

	private void createClientWidget(final Setter setter, Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.clientLabel);

		final TextOrData text = new TextOrData(composite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);

		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});

		if (setter.getRequired() != null && setter.getRequired().length() == 0) {
			requiredFields.add(new IRequirement() {
				public boolean isSet() {
					return text.getText() != null && text.getText().trim().length() > 0  ||(parentStackLayout!=null && parentStackLayout.topControl != null  && parentStackLayout.topControl.equals(chooseExistintgComposite));
				}
			});
		}

		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString());
		}

		if(setter.getParameters().length > 0){
			((Combo)text.getControl()).add(setter.getParameters()[0].toString());
		}

	}

	private void createServerTypeWidget(final Setter setter,Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.serverTypeLabel); 

		final Combo typeCombo = new Combo(composite, SWT.READ_ONLY);
		typeCombo.setLayoutData(WIDGET_GRID_DATA);
		typeCombo.add(SERVER_TYPE_APPLICATION_SERVER);
		typeCombo.add(SERVER_TYPE_MESSAGE_SERVER);



		typeCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, typeCombo.getText());
				updateStack(typeCombo.getText());
				setPageComplete(allRequiredWidgetsSet());
			}
		});

		if(currentParameters.get(setter.getSetterName()) != null ){
			typeCombo.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			typeCombo.setText(setter.getParameters()[0].toString());
		}
	}

	private void updateStack(String type) {
		if(stackLayout != null){
			if(type.equals(SERVER_TYPE_APPLICATION_SERVER)){
				stackLayout.topControl = applicationServerComposite;
			}else{
				stackLayout.topControl = messageServerComposite;
			}
			connectionParameterLayer.layout() ;
		}
	}

	private void updateParentStack(boolean existingConfig) {
		if(existingConfig){
			parentStackLayout.topControl = chooseExistintgComposite;
		}else{
			parentStackLayout.topControl = configurationComposite;
		}

		parentLayer.layout();

	}

	public void setConnectorId(String connectorId) {

		if(connectorId.equals(SAP_CREATE_CLIENT_ID)){
			onlyNewConnection = true ;
		}else{
			onlyNewConnection = false ;
		}
		ConnectorDescription connectorDesc = ConnectorRepository.getInstance().getConnectorAPI().getConnector(connectorId);
		this.setTitle(Messages.connectionPageTitle);
		this.setDescription(Messages.connectionPageDescription);
		this.connector = connectorDesc ;
		setImageDescriptor(Pics.getWizban());
	}

	public void setModelConnector(Connector modelConnector) {

	}

	public void setModelContainer(Element modelContainer) {
		this.container = modelContainer ;
	}

	public void setSetConnectorFieldsWizard(DefaultSetConnectorFieldsWizard wizard) {
		this.setWizard(wizard);
	}

	public void setTextOrDataFactory(ITextOrDataFactory textOrDataFactory) {
		this.textOrDataFactory = textOrDataFactory ;
	}
	
	@Override
	protected Button createSaveConfigurationButton(Composite parent) {
		Button button = super.createSaveConfigurationButton(parent);
		button.removeListener(SWT.Selection, button.getListeners(SWT.Selection)[0]);
		button.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				new WizardDialog(getShell(), new SaveSapConfigurationWizard(connector, currentParameters, ((DefaultSetConnectorFieldsWizard)getWizard()).getParentConfiguration())).open();
			}

		});
		return button;
	}

	public void setFixedOutput(boolean fixedOutput) {
		// TODO Auto-generated method stub
		
	}

}
