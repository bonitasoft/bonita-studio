/*
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.preferences;

import java.util.List;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesFileStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.bonitasoft.studio.connectors.ui.provider.DabaBaseConnectorDefinitionLabelProvider;
import org.bonitasoft.studio.connectors.ui.provider.DatabaseConnectorDefinitionContentProvider;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.pages.AbstractBonitaPreferencePage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;




/**
 * @author aurelie zara 
 *
 */
public class DBConnectorsPreferencePage extends AbstractBonitaPreferencePage implements IWorkbenchPreferencePage, IExecutableExtension {

	private static final int DEFAULT_BUTTON_WIDTH_HINT = 85;
	private final String DATABASE = "database";
	private TableViewer viewer;
	private TableViewer driverManagerViewer;
	private DbConnectorsPreferenceFilter connectorFilter;
	private DatabaseConnectorPropertiesRepositoryStore store;
	
	 @Override
	    protected Control createContents(Composite parent) {
			final Composite titleComposite = new Composite(parent, SWT.NONE) ;
	        titleComposite.setLayout(new GridLayout(2,false)) ;
	        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create()) ;
	        final Label imageLabel = new Label(titleComposite, SWT.NONE) ;
	        imageLabel.setImage(Pics.getImage(PicsConstants.preferenceAdvanced)) ;

	        final Label title = new Label(titleComposite, SWT.NONE) ;
	        title.setText(Messages.BonitaPreferenceDialog_DBConnectors);
	        title.setFont(BonitaStudioFontRegistry.getPreferenceTitleFont()) ;
	        
	        final Composite composite = new Composite(parent, SWT.NONE);
	        GridLayout layout = new GridLayout(2, false);
	        layout.marginHeight = layout.marginWidth = 0;
	        layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
	        layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
	        composite.setLayout(layout);
	        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;

	        final Label label = new Label(composite, SWT.WRAP);
	        label.setText(Messages.BonitaPreferencePage_DBConnectors_Description);
	        GridData data = new GridData(GridData.FILL_HORIZONTAL);
	        data.widthHint = 150;
	        data.horizontalSpan = 2;
	        label.setLayoutData(data);
	        label.setLayoutData(data);
	        createDBConnectorsList(composite);
	        createDriverManager(composite);
			return composite;
		 
	 }
	
	 private void createDBConnectorsList(Composite parent){
		 final Composite connectorListComposite = new Composite(parent,SWT.NONE);
		 connectorListComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		 connectorListComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(80,250).create());
		 final Text searchField = new Text(connectorListComposite,SWT.BORDER);
		 searchField.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		 viewer = new TableViewer(connectorListComposite,SWT.BORDER | SWT.FULL_SELECTION);
		 viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		 viewer.setLabelProvider(new DabaBaseConnectorDefinitionLabelProvider());
		 viewer.setContentProvider(new DatabaseConnectorDefinitionContentProvider());
		 viewer.setInput(getCategory());
		 connectorFilter = new DbConnectorsPreferenceFilter();
		 viewer.addFilter(connectorFilter);
		 searchField.addKeyListener(new KeyAdapter() {
			 /* (non-Javadoc)
			 * @see org.eclipse.swt.events.KeyAdapter#keyReleased(org.eclipse.swt.events.KeyEvent)
			 */
			@Override
			public void keyReleased(KeyEvent e) {
				connectorFilter.setSearchText(searchField.getText());
				viewer.refresh();
			}
		});
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				ConnectorDefinition def = (ConnectorDefinition)(selection.getFirstElement());
				driverManagerViewer.setInput(getDefaultDriver(def.getId()));
				
			}
		});
	 }
	 
	 
	
	 
	 private void createDriverManager(Composite parent){
		 final Composite driverManagerComposite = new Composite(parent,SWT.NONE);
		 driverManagerComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
		 driverManagerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		 driverManagerViewer = new TableViewer(driverManagerComposite,SWT.BORDER | SWT.FULL_SELECTION);
		 driverManagerViewer.getTable().setLinesVisible(true);
		 driverManagerViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		 
		 final Composite buttonsComposite = new Composite(driverManagerComposite,SWT.NONE);
		 buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;
	     buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create()) ;
		 final Button add = new Button(buttonsComposite,SWT.FLAT);
		 add.setText(Messages.add);
		 add.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
		 final Button remove = new Button(buttonsComposite,SWT.FLAT);
		 remove.setText(Messages.remove);
		 remove.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
		 final Button activate = new Button(buttonsComposite,SWT.FLAT);
		 activate.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(DEFAULT_BUTTON_WIDTH_HINT, SWT.DEFAULT).create()) ;
		 activate.setText(Messages.activate);
		 
		 final Button automaticallyAddDriver = new Button(driverManagerComposite,SWT.CHECK);
		 automaticallyAddDriver.setText(Messages.automaticallyAddDriver);
		 automaticallyAddDriver.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).create());
	 }

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbench workbench) {
		store = (DatabaseConnectorPropertiesRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DatabaseConnectorPropertiesRepositoryStore.class) ;
		
		
	}
	
	private List<String> getDrivers(String connectorId){
		DatabaseConnectorPropertiesFileStore property =(DatabaseConnectorPropertiesFileStore) store.getChild(connectorId);
		return property.getJarList();
	}
	
	private String getDefaultDriver(String connectorId){
		DatabaseConnectorPropertiesFileStore property =(DatabaseConnectorPropertiesFileStore) store.getChild(connectorId);
		return property.getDefault();
	}

	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		
	}
	 
	 private Category getCategory(){
			ConnectorDefRepositoryStore connectorDefStore = (ConnectorDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class) ;
			DefinitionResourceProvider messageProvider = DefinitionResourceProvider.getInstance(connectorDefStore, ConnectorPlugin.getDefault().getBundle()) ;
			List<Category> categories =messageProvider.getAllCategories();
			for (Category category:categories){
				if (DATABASE.equals(category.getId())){
					return category;
				}
			}
		 return null;
	 }

	

}
