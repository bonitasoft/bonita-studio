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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
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
public class SAPFunctionDefinitionWizardPage extends DefaultGeneratedConnectorWizardPage implements ICustomExtensionWizardPage {

	protected static final GridData WIDGET_GRID_DATA = new GridData(SWT.FILL,SWT.TOP,true,false);
	private static final String SET_ROLLBACK_ON_FAILURE = "setRollbackOnFailure"; //$NON-NLS-1$
	private static final String SET_COMMIT_ON_SUCCESS = "setCommitOnSuccess"; //$NON-NLS-1$
	private static final String SET_REPOSITORY = "setRepository"; //$NON-NLS-1$
	private static final String SET_FUNCTION_NAME = "setFunctionName"; //$NON-NLS-1$
	private static final String SET_RELEASE_CLIENT = "setReleaseClient"; //$NON-NLS-1$

	private List<Setter> inputs;
	protected Label functionNamelabel;
	protected TextOrData functionNameText;


	public SAPFunctionDefinitionWizardPage() {
		super(SAPFunctionDefinitionWizardPage.class.getName());
		requiredFields = new ArrayList<IRequirement>();
	}

	public SAPFunctionDefinitionWizardPage(DefaultSetConnectorFieldsWizard wizard, String pageId, ConnectorDescription connector, Element container) {
		super(wizard,pageId,connector,container);
	}

	public SAPFunctionDefinitionWizardPage(DefaultSetConnectorFieldsWizard wizard, String pageId, ConnectorDescription connectorDesc, Connector connector) {
		super(wizard, pageId, connectorDesc, (Element)connector.eContainer());
	}

	public SAPFunctionDefinitionWizardPage(String wizardPageName) {
		super(wizardPageName);
		requiredFields = new ArrayList<IRequirement>();
	}

	protected void updateScrolledComposite() {
		scrolledComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setContent(mainComposite);
	}
	
	@Override
	protected void createMainCompositeContent(Composite composite) {
		inputs = connector.getInputs() ;
		currentParameters = ((DefaultSetConnectorFieldsWizard)getWizard()).getInputParameters();


		createRepositoryWidget(getSetter(SET_REPOSITORY));
		createFunctionNameWidget(getSetter(SET_FUNCTION_NAME));
		
		createCommitOnSuccessWidget(getSetter(SET_COMMIT_ON_SUCCESS));
		createRollbackOnFailureWidget(getSetter(SET_ROLLBACK_ON_FAILURE));
		
		createReleaseClientWidget(getSetter(SET_RELEASE_CLIENT));
	}


	private void createRollbackOnFailureWidget(final Setter setter) {
		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(Messages.rollbackOnFailureLabel);

		final Button button = new Button(mainComposite, SWT.CHECK);
		button.setLayoutData(WIDGET_GRID_DATA);

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				setParamValue(setter, button.getSelection());
				setPageComplete(allRequiredWidgetsSet());
			}
		});

		if(currentParameters.get(setter.getSetterName()) != null ){
			button.setSelection(Boolean.parseBoolean(currentParameters.get(setter.getSetterName()).toString()));
		}else if(setter.getParameters().length > 0){
			button.setSelection(Boolean.parseBoolean(setter.getParameters()[0].toString())) ;
		}
		
	}

	private void createCommitOnSuccessWidget(final Setter setter) {
		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(Messages.commitOnSuccessLabel);

		final Button button = new Button(mainComposite, SWT.CHECK);
		button.setLayoutData(WIDGET_GRID_DATA);

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				setParamValue(setter, button.getSelection());
				setPageComplete(allRequiredWidgetsSet());
			}
		});

		if(currentParameters.get(setter.getSetterName()) != null ){
			button.setSelection(Boolean.parseBoolean(currentParameters.get(setter.getSetterName()).toString()));
		}else if(setter.getParameters().length > 0){
			button.setSelection(Boolean.parseBoolean(setter.getParameters()[0].toString())) ;
		}
		
	}

	protected void createFunctionNameWidget(final Setter setter) {
		functionNamelabel = new Label(mainComposite, SWT.NONE);
		functionNamelabel.setText(Messages.functionNameLabel);

		functionNameText = new TextOrData(mainComposite, container);
		functionNameText.getControl().setLayoutData(WIDGET_GRID_DATA);

		((Combo)functionNameText.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, functionNameText.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});
		
		requiredFields.add(new IRequirement() {
			public boolean isSet() {
				if(!functionNameText.getControl().isVisible()){
					return true;
				} else {
					return functionNameText.getText() != null && functionNameText.getText().trim().length() > 0 ;
				}
			}
		});
		
		if(currentParameters.get(setter.getSetterName()) != null ){
			functionNameText.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			functionNameText.setText(setter.getParameters()[0].toString()) ;
		}
		
		if(setter.getParameters().length > 0){
			((Combo)functionNameText.getControl()).add(setter.getParameters()[0].toString());
		}
	}

	private void createRepositoryWidget(final Setter setter) {
		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(Messages.repositoryLabel);

		final TextOrData text = new TextOrData(mainComposite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);

		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});

		requiredFields.add(new IRequirement() {
			public boolean isSet() {
				return text.getText() != null && text.getText().trim().length() > 0;
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

	private void createReleaseClientWidget(final Setter setter) {
		Label label = new Label(mainComposite, SWT.NONE);
		label.setText(Messages.releaseClientLabel);

		final Button button = new Button(mainComposite, SWT.CHECK);
		button.setLayoutData(WIDGET_GRID_DATA);

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				setParamValue(setter, button.getSelection());
				setPageComplete(allRequiredWidgetsSet());
			}
		});

		if(currentParameters.get(setter.getSetterName()) != null ){
			button.setSelection(Boolean.parseBoolean(currentParameters.get(setter.getSetterName()).toString()));
		}else if(setter.getParameters().length > 0){
			button.setSelection(Boolean.parseBoolean(setter.getParameters()[0].toString())) ;
		}

	}
	
	private Setter getSetter(String setterName) {
		for(Setter setter : inputs){
			if(setter.getSetterName().equals(setterName))
				return setter ;
		}
		return null;
	}

	public void setConnectorId(String connectorId) {
		ConnectorDescription connectorDesc = ConnectorRepository.getInstance().getConnectorAPI().getConnector(connectorId);
	
		this.setTitle(Messages.functionDefinitionPageTitle);
		this.setDescription(Messages.functionDefinitionPageDescription);

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
	
	}

}
