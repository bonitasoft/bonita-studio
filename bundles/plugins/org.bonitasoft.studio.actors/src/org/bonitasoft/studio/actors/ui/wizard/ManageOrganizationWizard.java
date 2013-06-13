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
package org.bonitasoft.studio.actors.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.page.AbstractOrganizationWizardPage;
import org.bonitasoft.studio.actors.ui.wizard.page.GroupsWizardPage;
import org.bonitasoft.studio.actors.ui.wizard.page.ManageOrganizationWizardPage;
import org.bonitasoft.studio.actors.ui.wizard.page.OrganizationUserValidator;
import org.bonitasoft.studio.actors.ui.wizard.page.RolesWizardPage;
import org.bonitasoft.studio.actors.ui.wizard.page.UsersWizardPage;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class ManageOrganizationWizard extends Wizard {

    private final List<Organization> organizations;
    private final List<Organization> organizationsWorkingCopy;
    private final OrganizationRepositoryStore store;
    private final OrganizationUserValidator validator = new OrganizationUserValidator() ;

    public ManageOrganizationWizard(){
        organizations = new ArrayList<Organization>() ;
        organizationsWorkingCopy = new ArrayList<Organization>() ;
        setWindowTitle(Messages.manageOrganizationTitle);
        store = (OrganizationRepositoryStore) RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(OrganizationRepositoryStore.class) ;
        for(IRepositoryFileStore file : store.getChildren()){
            organizations.add((Organization) file.getContent()) ;
        }
        for(Organization orga : organizations){
            organizationsWorkingCopy.add(EcoreUtil.copy(orga)) ;
        }
        setDefaultPageImageDescriptor(Pics.getWizban()) ;
        setNeedsProgressMonitor(true) ;
    }

    @Override
    public void addPages() {
        addPage(new ManageOrganizationWizardPage(organizationsWorkingCopy)) ;
        GroupsWizardPage p = new GroupsWizardPage() ;
        RolesWizardPage p1 = new RolesWizardPage() ;
        UsersWizardPage p2 = new UsersWizardPage() ;
        addPage(p) ;
        addPage(p1) ;
        addPage(p2) ;
    }

    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        if(page instanceof ManageOrganizationWizardPage){
            Organization orga =	((ManageOrganizationWizardPage)page).getSelectedOrganization() ;
            if(orga != null){
                for(IWizardPage p : getPages()){
                    if(p instanceof AbstractOrganizationWizardPage){
                        ((AbstractOrganizationWizardPage)p).setOrganization(orga) ;
                    }
                }
            }
        }
        return super.getNextPage(page);
    }



    @Override
    public boolean performFinish() {
        try {
            getContainer().run(true, false,new IRunnableWithProgress() {

                @Override
                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.saveOrganization, IProgressMonitor.UNKNOWN) ;
                    for(Organization organization : organizationsWorkingCopy){
                        monitor.subTask(Messages.validatingOrganizationContent);
                        String errorMessage = isOrganizationValid(organization) ;
                        if(errorMessage != null){
                            throw new InterruptedException(organization.getName() + ": " +errorMessage) ;
                        }
                        String fileName = organization.getName()+"."+OrganizationRepositoryStore.ORGANIZATION_EXT;
                        IRepositoryFileStore file = store.getChild(fileName) ;
                        if(file == null){
                            file = store.createRepositoryFileStore(fileName) ;
                        }
                        file.save(organization) ;

                    }
                    for(Organization orga : organizations){
                        boolean exists = false ;
                        for(Organization orgCopy : organizationsWorkingCopy){
                            if(orgCopy.getName().equals(orga.getName())){
                                exists = true ;
                                break ;
                            }
                        }
                        if(!exists){
                            IRepositoryFileStore f = store.getChild(orga.getName()+"."+OrganizationRepositoryStore.ORGANIZATION_EXT) ;
                            if(f != null){
                                f.delete() ;
                            }
                        }
                    }
                    monitor.done();
                }
            });
        } catch (InterruptedException e){
            openErrorStatusDialog(e.getMessage()) ;
            return false ;
        } catch (InvocationTargetException e) {
            BonitaStudioLog.error(e) ;
            return false ;
        }

        return true;
    }


    protected void openErrorStatusDialog(String errorMessage) {
        MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.organizationValidationFailed, errorMessage) ;
    }

    protected String isOrganizationValid(Organization organization) {
        final IStatus status = validator.validate(organization) ;
        if(!status.isOK()){
            return status.getMessage() ;
        }
        return null;
    }


}
