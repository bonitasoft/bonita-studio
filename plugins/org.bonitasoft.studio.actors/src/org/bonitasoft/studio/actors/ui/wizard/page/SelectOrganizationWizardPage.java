/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.ui.wizard.page;

import java.util.List;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.preference.ActorsPreferenceConstants;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 *
 */
public abstract class SelectOrganizationWizardPage extends WizardPage implements ISelectionChangedListener{

    private final OrganizationRepositoryStore organizationStore;

    public SelectOrganizationWizardPage() {
        super(SelectOrganizationWizardPage.class.getName());
        organizationStore = (OrganizationRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(OrganizationRepositoryStore.class) ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite mainComposite = new Composite(parent, SWT.NONE) ;
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
        mainComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).margins(5, 5).equalWidth(false).create()) ;

        Label organizationLabel = new Label(mainComposite, SWT.NONE) ;
        organizationLabel.setText(Messages.selectOrganization) ;

        final ComboViewer organizationCombo = new ComboViewer(mainComposite, SWT.READ_ONLY | SWT.BORDER) ;
        organizationCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,false).create()) ;
        organizationCombo.setContentProvider(new ArrayContentProvider()) ;
        organizationCombo.setLabelProvider(new LabelProvider(){
            @Override
            public String getText(Object element) {
                return ((Organization)((IRepositoryFileStore)element).getContent()).getName();
            }
        }) ;

        organizationCombo.addSelectionChangedListener(this) ;
        organizationCombo.setInput(organizationStore.getChildren()) ;
        String id =	BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getString(ActorsPreferenceConstants.DEFAULT_ORGANIZATION) ;
        IRepositoryFileStore defaultOrganization = organizationStore.getChild(id+"."+OrganizationRepositoryStore.ORGANIZATION_EXT) ;
        if(defaultOrganization == null){
        	List<OrganizationFileStore> orga = organizationStore.getChildren();
        	if(!orga.isEmpty()){
        		defaultOrganization = orga.get(0);
        	}
        }
        if(defaultOrganization != null){
        	organizationCombo.setSelection(new StructuredSelection(defaultOrganization)) ;
        	refreshOrganization((Organization) defaultOrganization.getContent()) ;
        }
        Label separator = new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL) ;
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create()) ;

        setControl(mainComposite);
    }


    protected abstract void refreshOrganization(Organization organization) ;

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        Object selectedRepositoryFileStore = ((IStructuredSelection) event.getSelection()).getFirstElement();
        if(selectedRepositoryFileStore != null){
            Organization organization = (Organization) ((IRepositoryFileStore)selectedRepositoryFileStore).getContent() ;
            refreshOrganization(organization) ;
        }
    }

}
