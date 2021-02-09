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
package org.bonitasoft.studio.identity.actors.ui.wizard.page;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class SelectUserFilterDefinitionWizardPage extends SelectFilterDefinitionWizardPage {

    private Button removeButton;
    private ConnectorDefinition selection;

    public SelectUserFilterDefinitionWizardPage(Connector connectorWorkingCopy,DefinitionResourceProvider messageProvider) {
        super(connectorWorkingCopy,messageProvider) ;
    }

    @Override
    public void createControl(Composite parent) {
        super.createControl(parent);
        Composite composite = (Composite) getControl() ;
        removeButton = new Button(composite, SWT.PUSH) ;
        removeButton.setText(Messages.remove) ;
        removeButton.setLayoutData(GridDataFactory.swtDefaults().hint(85, SWT.DEFAULT).create()) ;
        removeButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if(selection != null){
                    ActorFilterDefRepositoryStore store = (ActorFilterDefRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class) ;
                    String fileName = selection.eResource().getURI().lastSegment() ;
                    IRepositoryFileStore file = store.getChild(fileName, true) ;
                    if(FileActionDialog.confirmDeletionQuestion(fileName)){
                        file.delete() ;
                    }
                    refresh() ;
                }


            }
        }) ;
        removeButton.setEnabled(false) ;
    }

    @Override
    protected FilterDefinitionContentProvider getContentProvider() {
        return new FilterDefinitionContentProvider(true);
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        Object selection =  ((IStructuredSelection) event.getSelection()).getFirstElement() ;
        if(removeButton != null && selection instanceof ConnectorDefinition){
            this.selection = (ConnectorDefinition) selection ;
            removeButton.setEnabled(true) ;
        }
    }

}
