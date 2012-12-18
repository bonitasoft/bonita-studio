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
import java.util.StringTokenizer;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.connector.wizard.sap.SaveSapConfigurationWizard;
import org.bonitasoft.studio.connector.wizard.sap.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.sap.pages.viewer.InputParamsColumnLabelProvider;
import org.bonitasoft.studio.connector.wizard.sap.pages.viewer.OutputParamsContentProvider;
import org.bonitasoft.studio.connector.wizard.sap.pages.viewer.OutputParamsEditingSupport;
import org.bonitasoft.studio.connectors.wizards.DefaultGeneratedConnectorWizardPage;
import org.bonitasoft.studio.connectors.wizards.DefaultSetConnectorFieldsWizard;
import org.bonitasoft.studio.connectors.wizards.SaveConfigurationWizard;
import org.bonitasoft.studio.connectors.wizards.extensions.ICustomExtensionWizardPage;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ui.providers.ITextOrDataFactory;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.OutputParameterMapping;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.repository.connectors.connector.ConnectorRepository;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
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
public class SAPOutputParamsWizardPage extends DefaultGeneratedConnectorWizardPage implements ICustomExtensionWizardPage {

	private static final GridData WIDGET_GRID_DATA = new GridData(SWT.FILL,SWT.TOP,true,false);
	private static final String SET_OUTPUT_PARAMETERS = "setOutputParameters"; //$NON-NLS-1$
	private static final String SET_HTML_OUTPUT = "setHtmlOutput"; //$NON-NLS-1$


	private List<Setter> inputs;
	private Setter outputSetter;
	private OutputParamsContentProvider provider;
	private Connector modelConnector;
	private TableViewerColumn variableNameColumnViewer;
	private TableViewer outputTableViewer;
	private Combo varCombo;
	private Button useRaw;

	public SAPOutputParamsWizardPage() {
		super(SAPOutputParamsWizardPage.class.getName());
		requiredFields = new ArrayList<IRequirement>();
	}

	public SAPOutputParamsWizardPage(DefaultSetConnectorFieldsWizard wizard, String pageId, ConnectorDescription connector, Element container) {
		super(wizard,pageId,connector,container);
	}

	public SAPOutputParamsWizardPage(DefaultSetConnectorFieldsWizard wizard, String pageId, ConnectorDescription connectorDesc, Connector connector) {
		super(wizard, pageId, connectorDesc, (Element)connector.eContainer());
	}
	
	@Override
	protected void createMainCompositeContent(Composite composite) {
		inputs = connector.getInputs() ;
		currentParameters = ((DefaultSetConnectorFieldsWizard)getWizard()).getInputParameters();
		outputSetter = getSetter(SET_OUTPUT_PARAMETERS) ;

		createSimpleOutputParemters(outputSetter);
		createOutputParametersWidget(outputSetter);
		
		varCombo.setEnabled(false);
		useRaw.setSelection(false);
		variableNameColumnViewer.setEditingSupport(new OutputParamsEditingSupport(outputTableViewer, 3,getContainer(), container,textOrDataFactory)) ;
		outputTableViewer.refresh() ;
		if(modelConnector != null){
			for(OutputParameterMapping output : modelConnector.getOutputs()){
				if(output.getValueExpression() != null && output.getValueExpression().equals("results")){ //$NON-NLS-1$
					useRaw.setSelection(true);
					varCombo.setText(output.getTargetData().getName());
					varCombo.setEnabled(true);
					variableNameColumnViewer.setEditingSupport(new OutputParamsEditingSupport(outputTableViewer, 3,getContainer(), container,false,textOrDataFactory)) ;
					outputTableViewer.refresh() ;
				}
			}
		}
		
		createHtmlOuputWidget(getSetter(SET_HTML_OUTPUT));
	}
	
	private void createSimpleOutputParemters(final Setter setter) {


		useRaw = new Button(mainComposite, SWT.CHECK);
		useRaw.setText(Messages.rawOutputLabel);
	
		
		varCombo = new Combo(mainComposite, SWT.READ_ONLY);
		varCombo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		for(Data d : ModelHelper.getAccessibleData(container)){
			varCombo.add(d.getName());
		}
		
		useRaw.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(useRaw.getSelection()){
					varCombo.setEnabled(true);
					variableNameColumnViewer.setEditingSupport(new OutputParamsEditingSupport(outputTableViewer, 3,getContainer(), container,false,textOrDataFactory)) ;
					outputTableViewer.refresh() ;
					provider.getOutputParameterMapping().clear();
					if(varCombo.getText() != null && varCombo.getText().trim().length() > 0){
						OutputParameterMapping simpleOutput = ProcessFactory.eINSTANCE.createOutputParameterMapping();
						simpleOutput.setTargetData(getData(varCombo.getText()));
						simpleOutput.setValueExpression("results"); //$NON-NLS-1$
						provider.getOutputParameterMapping().add(simpleOutput);
					}
					
				}else{
					varCombo.setEnabled(false);
					variableNameColumnViewer.setEditingSupport(new OutputParamsEditingSupport(outputTableViewer, 3,getContainer(), container,textOrDataFactory)) ;
					outputTableViewer.refresh() ;
					provider.getOutputParameterMapping().clear();
					for(List<String> row : provider.getMatrix()){
						if(row.get(3) != null && getData(row.get(3)) != null){
							OutputParameterMapping simpleOutput = ProcessFactory.eINSTANCE.createOutputParameterMapping();
							simpleOutput.setTargetData(getData(row.get(3)));
							simpleOutput.setValueExpression("results.get("+ provider.getMatrix().indexOf(row)+")"); //$NON-NLS-1$
							provider.getOutputParameterMapping().add(simpleOutput);
						}
					}
				}
			}
			
		});

		varCombo.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {


				OutputParameterMapping simpleOutput = null ;

				for(OutputParameterMapping output : provider.getOutputParameterMapping()){
					if(output.getValueExpression() != null && output.getValueExpression().equals("results")){ //$NON-NLS-1$
						simpleOutput = output ;
						output.setTargetData(getData(varCombo.getText()));
					}
				}

				if(simpleOutput == null){
					simpleOutput = ProcessFactory.eINSTANCE.createOutputParameterMapping();
					simpleOutput.setTargetData(getData(varCombo.getText()));
					simpleOutput.setValueExpression("results"); //$NON-NLS-1$
					provider.getOutputParameterMapping().clear();
					provider.getOutputParameterMapping().add(simpleOutput);

				}
			}
		});
		



	}

	protected Data getData(String name) {
		for(Data d : ModelHelper.getAccessibleData(container)){
			if(d.getName().equals(name)){
				return d ;
			}
		}

		return null;
	}

	protected void updateScrolledComposite() {
		scrolledComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setContent(mainComposite);
	}

	public static Object parseCollection(Object object, String objectSeparator) {
		if(object instanceof String){
			String item = (String) object ;
			List<List<String>> result= new ArrayList<List<String>>();
			StringTokenizer stk = new StringTokenizer(item, objectSeparator);
			while(stk.hasMoreTokens()){
				String subItem = stk.nextToken();
				List<String> sublist = new ArrayList<String>();
				result.add(sublist);
				String[] row = subItem.split(SaveConfigurationWizard.SEPARATOR_COLUMN,4) ;
				for(int i = 0 ; i < row.length ; i++){
					String subsubItem = row[i];
					sublist.add(subsubItem);
				}

			}
			if (result.size() == 1 && result.get(0).size() == 1 && GroovyUtil.isGroovyExpression(result.get(0).get(0))) {
				return result.get(0).get(0);
			}
			return result;
		}else{
			return (List<List<String>>) object;
		}
	}

	private void createHtmlOuputWidget(final Setter setter) {
		new Label(mainComposite, SWT.NONE);//FILLER

		final Button button = new Button(mainComposite, SWT.CHECK);
		button.setText(Messages.writeToHtmlFileLabel); 
		button.setLayoutData(new GridData(GridData.FILL,SWT.TOP,false,false));


		Composite htmlComposite = new Composite(mainComposite, SWT.NONE);
		GridData gd = new GridData(SWT.FILL,SWT.FILL,true,true,2,1) ;
		gd.horizontalIndent = 10 ;
		htmlComposite.setLayoutData(gd);
		htmlComposite.setLayout(new GridLayout(2, false));

		Label label = new Label(htmlComposite, SWT.NONE);
		label.setText(Messages.htmlFileLabel);


		final TextOrData text = new TextOrData(htmlComposite, container);
		text.getControl().setLayoutData(WIDGET_GRID_DATA);

		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				if(!button.getSelection()){
					setParamValue(setter, null);
				}else{
					setParamValue(setter, text.getText());
				}
				text.getControl().setEnabled(button.getSelection());
			}
		});

		((Combo)text.getControl()).addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent arg0) {
				setParamValue(setter, text.getText());
				setPageComplete(allRequiredWidgetsSet());
			}

		});

		if(currentParameters.get(setter.getSetterName()) != null ){
			text.setText(currentParameters.get(setter.getSetterName()).toString());
		}else if(setter.getParameters().length > 0){
			text.setText(setter.getParameters()[0].toString()) ;
		}

		if(text.getText() != null && text.getText().trim().length() > 0){
			button.setSelection(true);
			text.getControl().setEnabled(true);
		}else{
			button.setSelection(false);
			text.getControl().setEnabled(false);
		}

	}



	@SuppressWarnings("unchecked")
	private void createOutputParametersWidget(Setter setter) {
		outputTableViewer = new TableViewer(mainComposite,SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		GridData gd = new GridData(SWT.FILL,SWT.FILL,true,true, 2, 1);
		gd.heightHint = 100 ;
		outputTableViewer.getTable().setLayoutData(gd);
		outputTableViewer.getTable().setLinesVisible(true);
		outputTableViewer.getTable().setHeaderVisible(true);
		provider = new OutputParamsContentProvider();
		outputTableViewer.setContentProvider(provider);

		provider.addColumn();

		TableViewerColumn typeColumnViewer = new TableViewerColumn(outputTableViewer, SWT.FILL);
		typeColumnViewer.getColumn().setText(Messages.columnTypeLabel);
		typeColumnViewer.getColumn().setWidth(120);
		typeColumnViewer.getColumn().setMoveable(true);
		typeColumnViewer.getColumn().setResizable(true);
		typeColumnViewer.setEditingSupport(new OutputParamsEditingSupport(outputTableViewer, 0,getContainer(), container,textOrDataFactory));
		typeColumnViewer.setLabelProvider(new InputParamsColumnLabelProvider(0));
	

		provider.addColumn();

		TableViewerColumn tableColumnViewer = new TableViewerColumn(outputTableViewer, SWT.FILL);
		tableColumnViewer.getColumn().setText(Messages.columnTableLabel);
		tableColumnViewer.getColumn().setWidth(130);
		tableColumnViewer.getColumn().setMoveable(true);
		tableColumnViewer.getColumn().setResizable(true);
		tableColumnViewer.setEditingSupport(new OutputParamsEditingSupport(outputTableViewer, 1,getContainer(), container,textOrDataFactory));
		tableColumnViewer.setLabelProvider(new InputParamsColumnLabelProvider(1));

		provider.addColumn();

		TableViewerColumn xPathColumnViewer = new TableViewerColumn(outputTableViewer, SWT.FILL);
		xPathColumnViewer.getColumn().setText(Messages.columnXPathLabel);
		xPathColumnViewer.getColumn().setWidth(120);
		xPathColumnViewer.getColumn().setMoveable(true);
		xPathColumnViewer.getColumn().setResizable(true);
		xPathColumnViewer.setEditingSupport(new OutputParamsEditingSupport(outputTableViewer, 2,getContainer(), container,textOrDataFactory));
		xPathColumnViewer.setLabelProvider(new InputParamsColumnLabelProvider(2));

		provider.addColumn();

		variableNameColumnViewer = new TableViewerColumn(outputTableViewer, SWT.FILL);
		variableNameColumnViewer.getColumn().setText(Messages.columnVariableName);
		variableNameColumnViewer.getColumn().setWidth(120);
		variableNameColumnViewer.getColumn().setMoveable(true);
		variableNameColumnViewer.getColumn().setResizable(true);
		variableNameColumnViewer.setEditingSupport(new OutputParamsEditingSupport(outputTableViewer, 3,getContainer(), container,textOrDataFactory));
		variableNameColumnViewer.setLabelProvider(new InputParamsColumnLabelProvider(3));

		
		
		if(currentParameters.get(setter.getSetterName())!=null){
			provider.setMatrix((List<List<String>>) parseCollection(currentParameters.get(setter.getSetterName()),SaveConfigurationWizard.SEPARATOR_ROW));
		}

		if(modelConnector == null){
			provider.setOutputParameterMapping(((DefaultSetConnectorFieldsWizard)getWizard()).getOutputMappings());
		}else{
			List<OutputParameterMapping> outputs = ((DefaultSetConnectorFieldsWizard)getWizard()).getOutputMappings() ;
			for(OutputParameterMapping out : modelConnector.getOutputs()){
				OutputParameterMapping modelOutput = (OutputParameterMapping) EcoreUtil.copy(out);
				outputs.add(modelOutput);
			}
			provider.setOutputParameterMapping(outputs);
		}
		
		outputTableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
			
			public void selectionChanged(SelectionChangedEvent event) {
				getContainer().updateButtons() ;
			}
		}) ;
		
		outputTableViewer.getTable().addKeyListener(new KeyListener() {
			
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == SWT.CR || e.keyCode == SWT.ESC){
					getContainer().updateButtons() ;
				}
				
			}
			
			public void keyPressed(KeyEvent e) {
			
				
			}
		});

		outputTableViewer.setInput(new Object());

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
				outputTableViewer.refresh();
				getContainer().updateButtons();
			}
		});
		Button removeRowButton = new Button(buttonComposite, SWT.FLAT);
		removeRowButton.setText(Messages.removeRow);
		removeRowButton.setLayoutData(rd);
		removeRowButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (outputTableViewer != null && outputTableViewer.getSelection() != null && ! outputTableViewer.getSelection().isEmpty()) {
					provider.removeRow( (List<Object>) (((IStructuredSelection)outputTableViewer.getSelection()).getFirstElement()));
					outputTableViewer.refresh();
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
				if (outputTableViewer != null && outputTableViewer.getSelection() != null && ! outputTableViewer.getSelection().isEmpty()) {
					provider.down( (List<String>) (((IStructuredSelection)outputTableViewer.getSelection()).getFirstElement()));
					outputTableViewer.refresh();
				}
			}
		});

		Button upButton = new Button(buttonComposite, SWT.FLAT);
		upButton.setText(Messages.up);
		upButton.setLayoutData(rd);
		upButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {
				if (outputTableViewer != null && outputTableViewer.getSelection() != null && ! outputTableViewer.getSelection().isEmpty()) {
					provider.up( (List<String>) (((IStructuredSelection)outputTableViewer.getSelection()).getFirstElement()));
					outputTableViewer.refresh();
				}
			}
		});

		outputTableViewer.refresh() ;

	}

	@Override
	public boolean canFlipToNextPage() {
		setParamValue(outputSetter, provider.getMatrix());
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

		this.setTitle(Messages.outputParametersPageTitle);
		this.setDescription(Messages.outputParametersPageDescription);

		this.connector = connectorDesc ;
		setImageDescriptor(Pics.getWizban());
	}

	public void setModelConnector(Connector modelConnector) {
		this.modelConnector = modelConnector ;
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
	
	@Override
	public boolean isPageComplete() {
		return outputTableViewer != null && !outputTableViewer.isCellEditorActive();
	}

	public void setFixedOutput(boolean fixedOutput) {
		// TODO Auto-generated method stub
		
	}
}
