/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.util.List;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.DefinitionConfigurationFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.ui.provider.ConnectorDefinitionContentProvider;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class SelectUserConnectorDefinitionWizardPage extends SelectConnectorDefinitionWizardPage {

	private Button removeButton;
	private ConnectorDefinition selection;
	private final ConnectorConfRepositoryStore connectorConfStore;
	private final ConnectorImplRepositoryStore connectorImplStore;

	public SelectUserConnectorDefinitionWizardPage(final Connector connectorWorkingCopy,final DefinitionResourceProvider messageProvider) {
		super(connectorWorkingCopy,messageProvider) ;
		connectorConfStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorConfRepositoryStore.class);
		connectorImplStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
	}

	@Override
	public void createControl(final Composite parent) {
		super.createControl(parent);
		final Composite composite = (Composite) getControl() ;
		removeButton = new Button(composite, SWT.PUSH) ;
		removeButton.setText(Messages.remove) ;
		removeButton.setLayoutData(GridDataFactory.swtDefaults().hint(85, SWT.DEFAULT).create()) ;
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				deleteConnectorRessources();

			}
		}) ;
		removeButton.setEnabled(false) ;
	}

	@Override
	protected ConnectorDefinitionContentProvider getContentProvider() {
		return new ConnectorDefinitionContentProvider(true);
	}

	@Override
	public void selectionChanged(final SelectionChangedEvent event) {
		final Object selection =  ((IStructuredSelection) event.getSelection()).getFirstElement() ;
		if(removeButton != null && selection instanceof ConnectorDefinition){
			this.selection = (ConnectorDefinition) selection ;
			removeButton.setEnabled(true) ;
		}
	}

	private void deleteConnectorRessources(){
		if(selection != null){


			final ConnectorDefRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class) ;
			final String fileName = selection.eResource().getURI().lastSegment() ;
			final IRepositoryFileStore file = store.getChild(fileName) ;

			if(FileActionDialog.confirmDeletionQuestionWithCustomMessage(Messages.bind(Messages.deleteConnectorDefinition, fileName))){
				deleteConnectorConfigurations();
				deleteConnectorImplementations();
				file.delete() ;
			}
			refresh() ;
			removeButton.setEnabled(false);
		}
	}

	private void deleteConnectorConfigurations(){
		if (selection!=null){
            final List<ConnectorConfiguration> connectorConfs = connectorConfStore.getConnectorConfigurationsFor(selection.getId(), selection.getVersion());
			for (final ConnectorConfiguration conf:connectorConfs){
				final DefinitionConfigurationFileStore file = connectorConfStore.getChild(conf.getName()+"."+ConnectorConfRepositoryStore.CONF_EXT);
				if (file!=null){
					file.delete();
				}
			}
		}
	}

	private void deleteConnectorImplementations(){
		if (selection!=null){
			final List<ConnectorImplementation> connectorImpls=connectorImplStore.getImplementations(selection.getId(), selection.getVersion());
			for (final ConnectorImplementation connectorImpl:connectorImpls){
				connectorImplStore.getImplementationFileStore(connectorImpl.getImplementationId(), connectorImpl.getImplementationVersion()).delete();
			}
		}
	}

}
