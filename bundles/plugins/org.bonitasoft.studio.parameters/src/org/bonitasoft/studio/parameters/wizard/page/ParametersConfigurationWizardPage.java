/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.wizard.page;

import org.bonitasoft.studio.common.jface.ListContentProvider;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.parameters.action.ExportParametersAction;
import org.bonitasoft.studio.parameters.action.ImportParametersAction;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterNameLabelProvider;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterTypeLabelProvider;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterValueLabelProvider;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 *
 */
public class ParametersConfigurationWizardPage extends WizardPage implements IProcessConfigurationWizardPage {

	private TableViewer parameterTableViewer;
	private ParameterValueEditingSupport valueEditingSupport;
	private AbstractProcess process;
	private Configuration configuration;
	public ParametersConfigurationWizardPage() {
		super(ParametersConfigurationWizardPage.class.getName());
		setTitle(Messages.parameters) ;
		setDescription(Messages.parameterWizardDesc) ;
	}

	@Override
	public void createControl(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create()) ;

		createParameterComposite(mainComposite);
		createImportExportButtons(mainComposite);
		setControl(mainComposite) ;
	}

	protected void createParameterComposite(Composite parent) {
		Composite parameterComposite = new Composite(parent,SWT.NONE);
		parameterComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		parameterComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

		final Label descriptionLabel = new Label(parameterComposite,SWT.WRAP);
		descriptionLabel.setText(getDescription());
		descriptionLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());

		parameterTableViewer = new TableViewer(parameterComposite, SWT.FULL_SELECTION | SWT.BORDER | SWT.MULTI | SWT.V_SCROLL) ;
		parameterTableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		parameterTableViewer.setContentProvider(new ListContentProvider());
		TableLayout tableLayout = new TableLayout() ;
		tableLayout.addColumnData(new ColumnWeightData(25)) ;
		tableLayout.addColumnData(new ColumnWeightData(25)) ;
		tableLayout.addColumnData(new ColumnWeightData(50)) ;
		parameterTableViewer.getTable().setLayout(tableLayout) ;
		parameterTableViewer.getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {

			@Override
			public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) { }

			@Override
			public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) { }

			@Override
			public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
				if(getContainer() != null){
					getContainer().updateMessage();
				}
			}

			@Override
			public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {}
		}) ;


		TableViewerColumn columnNameViewer = new TableViewerColumn(parameterTableViewer,SWT.NONE) ;
		columnNameViewer.setLabelProvider(new ParameterNameLabelProvider()) ;
		TableColumn column = columnNameViewer.getColumn()  ;
		column.setText(Messages.name) ;


		TableViewerColumn columnTypeViewer = new TableViewerColumn(parameterTableViewer,SWT.NONE) ;
		columnTypeViewer.setLabelProvider(new ParameterTypeLabelProvider()) ;
		TableColumn column3 = columnTypeViewer.getColumn() ;
		column3.setText(Messages.type) ;


		TableViewerColumn columnValueViewer = new TableViewerColumn(parameterTableViewer,SWT.NONE) ;
		columnValueViewer.setLabelProvider(new ParameterValueLabelProvider()) ;
		valueEditingSupport = new ParameterValueEditingSupport(columnValueViewer.getViewer(),this) ;

		columnValueViewer.setEditingSupport(valueEditingSupport) ;
		TableColumn column2 = columnValueViewer.getColumn() ;
		column2.setText(Messages.value) ;

		parameterTableViewer.getTable().setHeaderVisible(true);
		parameterTableViewer.getTable().setLinesVisible(true) ;


		TableColumnSorter sorter = new TableColumnSorter(parameterTableViewer) ;
		sorter.setColumn(column) ;

	}

	@Override
	public void updatePage(AbstractProcess process,Configuration configuration) {
		this.process = process ;
		this.configuration = configuration ;
		if(process != null && configuration != null && parameterTableViewer != null && !parameterTableViewer.getTable().isDisposed()){
			parameterTableViewer.setInput(configuration.getParameters()) ;
		}
	}

	private void createImportExportButtons(Composite mainComposite) {
		final Composite composite = new Composite(mainComposite, SWT.NONE) ;
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

		final Button importButton = new Button(composite, SWT.PUSH);
		importButton.setText(Messages.importParameterFile);
		importButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
		importButton.addSelectionListener(new SelectionAdapter() {


			@Override
			public void widgetSelected(SelectionEvent e) {
				final ImportParametersAction action = new ImportParametersAction() ;
				action.setConfiguration(configuration);
				action.setProcess(process) ;
				action.run() ;
				parameterTableViewer.refresh();
				getContainer().updateMessage();
			}
		}) ;

		final Button exportButton = new Button(composite, SWT.PUSH);
		exportButton.setText(Messages.exportParameterFile);
		exportButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create()) ;
		exportButton.addSelectionListener(new SelectionAdapter() {


			@Override
			public void widgetSelected(SelectionEvent e) {
				final ExportParametersAction action = new ExportParametersAction() ;
				action.setConfiguration(configuration);
				action.setProcess(process) ;
				action.run() ;
			}
		}) ;
	}

	@Override
	public String isConfigurationPageValid(Configuration configuration) {
		if(configuration != null){
			for(Parameter p :configuration.getParameters()){
				String input = p.getValue() ;
				String typeName = p.getTypeClassname() ;
				if(input == null || input.isEmpty()){
					return Messages.bind(Messages.missingParameterValue,p.getName()) ;
				}else if(typeName.equals(Integer.class.getName())){
					try{
						Integer.parseInt(input) ;
					}catch (NumberFormatException e) {
						return Messages.bind(Messages.invalidIntegerForParameter,p.getName()) ;
					}
				}else if(typeName.equals(Double.class.getName())){
					try{
						Double.parseDouble(input) ;
					}catch (NumberFormatException e) {
						return Messages.bind(Messages.invalidDoulbeForParameter,p.getName()) ;
					}
				}
			}
		}
		return null;
	}


	@Override
	public Image getConfigurationImage() {
		return Pics.getImage(PicsConstants.parameter);
	}

	@Override
	public boolean isDefault() {
		return true;
	}

}
