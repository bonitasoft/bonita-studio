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
import org.bonitasoft.studio.connector.wizard.sap.pages.viewer.InputParamsColumnLabelProvider;
import org.bonitasoft.studio.connector.wizard.sap.pages.viewer.InputParamsContentProvider;
import org.bonitasoft.studio.connector.wizard.sap.pages.viewer.InputParamsEditingSupport;
import org.bonitasoft.studio.connectors.wizards.DefaultGeneratedConnectorWizardPage;
import org.bonitasoft.studio.connectors.wizards.DefaultSetConnectorFieldsWizard;
import org.bonitasoft.studio.connectors.wizards.SaveConfigurationWizard;
import org.bonitasoft.studio.connectors.wizards.extensions.ICustomExtensionWizardPage;
import org.bonitasoft.studio.groovy.ui.providers.ITextOrDataFactory;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.repository.connectors.connector.ConnectorRepository;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
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
public class SAPInputParamsWizardPage extends DefaultGeneratedConnectorWizardPage implements ICustomExtensionWizardPage {


	private static final String SET_INPUT_PARAMETERS = "setInputParameters"; //$NON-NLS-1$

	private List<Setter> inputs;
	private Setter inputSetter;
	private InputParamsContentProvider provider;

	private TableViewer inputTableViewer;

	private Class textOrDataType;

	public SAPInputParamsWizardPage() {
		super(SAPInputParamsWizardPage.class.getName());
		requiredFields = new ArrayList<IRequirement>();
	}

	public SAPInputParamsWizardPage(DefaultSetConnectorFieldsWizard wizard, String pageId, ConnectorDescription connector, Element container) {
		super(wizard,pageId,connector,container);
	}

	public SAPInputParamsWizardPage(DefaultSetConnectorFieldsWizard wizard, String pageId, ConnectorDescription connectorDesc, Connector connector) {
		super(wizard, pageId, connectorDesc, (Element)connector.eContainer());
	}

	@Override
	protected void createMainCompositeContent(Composite composite) {
		inputs = connector.getInputs() ;
		currentParameters = ((DefaultSetConnectorFieldsWizard)getWizard()).getInputParameters();
		inputSetter = getSetter(SET_INPUT_PARAMETERS) ;
		createInputParametersWidget(inputSetter);
	}

	protected void updateScrolledComposite() {
		scrolledComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setContent(mainComposite);
	}

	@SuppressWarnings("unchecked")
	private void createInputParametersWidget(Setter setter) {
		inputTableViewer = new TableViewer(mainComposite,SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		GridData gd = new GridData(SWT.FILL,SWT.FILL,true,true, 2, 1);
		gd.heightHint = 100 ;
		inputTableViewer.getTable().setLayoutData(gd);
		inputTableViewer.getTable().setLinesVisible(true);
		inputTableViewer.getTable().setHeaderVisible(true);
		provider = new InputParamsContentProvider();
		inputTableViewer.setContentProvider(provider);

		provider.addColumn();

		TableViewerColumn typeColumnViewer = new TableViewerColumn(inputTableViewer, SWT.FILL);
		typeColumnViewer.getColumn().setText(Messages.columnTypeLabel);
		typeColumnViewer.getColumn().setWidth(120);
		typeColumnViewer.getColumn().setMoveable(true);
		typeColumnViewer.getColumn().setResizable(true);
		typeColumnViewer.setEditingSupport(new InputParamsEditingSupport(inputTableViewer, 0,getContainer(), container,textOrDataFactory));
		typeColumnViewer.setLabelProvider(new InputParamsColumnLabelProvider(0));


		provider.addColumn();

		TableViewerColumn tableColumnViewer = new TableViewerColumn(inputTableViewer, SWT.FILL);
		tableColumnViewer.getColumn().setText(Messages.columnTableLabel);
		tableColumnViewer.getColumn().setWidth(130);
		tableColumnViewer.getColumn().setMoveable(true);
		tableColumnViewer.getColumn().setResizable(true);
		tableColumnViewer.setEditingSupport(new InputParamsEditingSupport(inputTableViewer, 1,getContainer(), container,textOrDataFactory));
		tableColumnViewer.setLabelProvider(new InputParamsColumnLabelProvider(1));

		provider.addColumn();

		TableViewerColumn paramNameColumnViewer = new TableViewerColumn(inputTableViewer, SWT.FILL);
		paramNameColumnViewer.getColumn().setText(Messages.columnParamNameLabel);
		paramNameColumnViewer.getColumn().setWidth(120);
		paramNameColumnViewer.getColumn().setMoveable(true);
		paramNameColumnViewer.getColumn().setResizable(true);
		paramNameColumnViewer.setEditingSupport(new InputParamsEditingSupport(inputTableViewer, 2,getContainer(), container,textOrDataFactory));
		paramNameColumnViewer.setLabelProvider(new InputParamsColumnLabelProvider(2));

		provider.addColumn();

		TableViewerColumn paramValueColumnViewer = new TableViewerColumn(inputTableViewer, SWT.FILL);
		paramValueColumnViewer.getColumn().setText(Messages.columnParamValueLabel);
		paramValueColumnViewer.getColumn().setWidth(120);
		paramValueColumnViewer.getColumn().setMoveable(true);
		paramValueColumnViewer.getColumn().setResizable(true);
		paramValueColumnViewer.setEditingSupport(new InputParamsEditingSupport(inputTableViewer, 3,getContainer(), container,textOrDataFactory));
		paramValueColumnViewer.setLabelProvider(new InputParamsColumnLabelProvider(3));
		
		List<List<Object>> input = new ArrayList<List<Object>>();
		
		if(currentParameters.get(setter.getSetterName())!=null){
			input = (List<List<Object>>) parseCollection(currentParameters.get(setter.getSetterName()),SaveConfigurationWizard.SEPARATOR_ROW);	
		}
		
		inputTableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
			
			public void selectionChanged(SelectionChangedEvent event) {
				getContainer().updateButtons();
			}
			
		}) ;
		inputTableViewer.getTable().addKeyListener(new KeyListener() {
			
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == SWT.CR || e.keyCode == SWT.ESC){
					getContainer().updateButtons() ;
				}
				
			}
			
			public void keyPressed(KeyEvent e) {
			
				
			}
		});
		
		
		inputTableViewer.setInput(input);
		
		new Label(mainComposite, SWT.NONE);

		Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
		buttonComposite.setLayout(new RowLayout());
		gd = new GridData();
		buttonComposite.setLayoutData(gd);

		Button button = new Button(buttonComposite, SWT.FLAT);
		button.setText(Messages.addRow);
		RowData rd = new RowData() ;
		rd.width = 80 ;
		button.setLayoutData(rd);
		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				provider.addRow();
				inputTableViewer.refresh();
				setPageComplete(allRequiredWidgetsSet());
				getContainer().updateButtons();
			}
		});
		Button removeRowButton = new Button(buttonComposite, SWT.FLAT);
		removeRowButton.setText(Messages.removeRow);
		removeRowButton.setLayoutData(rd);
		removeRowButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				if (inputTableViewer != null && inputTableViewer.getSelection() != null && ! inputTableViewer.getSelection().isEmpty()) {
					provider.removeRow( (List<Object>) (((IStructuredSelection)inputTableViewer.getSelection()).getFirstElement()));
					inputTableViewer.refresh();
					setPageComplete(allRequiredWidgetsSet());
					getContainer().updateButtons();
				}
			}
		});
		
		Button downButton = new Button(buttonComposite, SWT.FLAT);
		downButton.setText(Messages.down);
		downButton.setLayoutData(rd);
		downButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				if (inputTableViewer != null && inputTableViewer.getSelection() != null && ! inputTableViewer.getSelection().isEmpty()) {
					provider.down( (List<Object>) (((IStructuredSelection)inputTableViewer.getSelection()).getFirstElement()));
					inputTableViewer.refresh();
				}
			}
		});
		
		Button upButton = new Button(buttonComposite, SWT.FLAT);
		upButton.setText(Messages.up);
		upButton.setLayoutData(rd);
		upButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				if (inputTableViewer != null && inputTableViewer.getSelection() != null && ! inputTableViewer.getSelection().isEmpty()) {
					provider.up( (List<Object>) (((IStructuredSelection)inputTableViewer.getSelection()).getFirstElement()));
					inputTableViewer.refresh();
				}
			}
		});


		inputTableViewer.refresh() ;

	}

	@Override
	public boolean canFlipToNextPage() {
		setParamValue(inputSetter, provider.getMatrix());
		return super.canFlipToNextPage();
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

		this.setTitle(Messages.inputParametersPageTitle);
		this.setDescription(Messages.inputParametersPageDescription);

		this.connector = connectorDesc ;
		setImageDescriptor(Pics.getWizban());
	}

	public void setModelConnector(Connector modelConnector) {}

	public void setModelContainer(Element modelContainer) {
		this.container = modelContainer ;
	}

	public void setSetConnectorFieldsWizard(DefaultSetConnectorFieldsWizard wizard) {
		this.setWizard(wizard);
	}
	
	protected void setInput(Object input){
		inputTableViewer.setInput(input);
		inputTableViewer.refresh();
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
	
	@Override
	public boolean isPageComplete() {
		return inputTableViewer != null && !inputTableViewer.isCellEditorActive();
	}

	public void setFixedOutput(boolean fixedOutput) {
		// TODO Auto-generated method stub
		
	}

}
