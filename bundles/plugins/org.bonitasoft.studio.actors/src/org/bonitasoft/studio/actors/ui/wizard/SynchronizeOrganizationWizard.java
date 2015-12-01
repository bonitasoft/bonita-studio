/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.repository.OrganizationFileStore;
import org.bonitasoft.studio.actors.ui.wizard.page.DefaultUserOrganizationWizardPage;
import org.bonitasoft.studio.actors.ui.wizard.page.SynchronizeOrganizationWizardPage;
import org.bonitasoft.studio.actors.validator.OrganizationValidationException;
import org.bonitasoft.studio.actors.validator.OrganizationValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * @author Romain Bioteau
 */
public class SynchronizeOrganizationWizard extends Wizard {

    protected IRepositoryFileStore file;
    private SynchronizeOrganizationWizardPage page;
    private DefaultUserOrganizationWizardPage userPage;
    private Organization activeOrganization;
    private final OrganizationValidator validator;
    private final ActiveOrganizationProvider activeOrganizationProvider;

    public SynchronizeOrganizationWizard() {
        setWindowTitle(Messages.synchronizeOrganizationTitle);
        setDefaultPageImageDescriptor(Pics.getWizban());
        setForcePreviousAndNextButtons(false);
        setNeedsProgressMonitor(true);
        validator = new OrganizationValidator();
        activeOrganizationProvider = new ActiveOrganizationProvider();
    }

    @Override
    public void addPages() {
        page = new SynchronizeOrganizationWizardPage();
        userPage = new DefaultUserOrganizationWizardPage();
        RepositoryManager.getInstance().getCurrentRepository().getScopeContext();
        userPage.setUser(activeOrganizationProvider.getDefaultUser());
        addPage(page);
        addPage(userPage);
    }

    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page instanceof SynchronizeOrganizationWizardPage) {
            activeOrganization = ((SynchronizeOrganizationWizardPage) page).getFileStore().getContent();
            userPage.setOrganization(activeOrganization);
            return userPage;
        } else {
            return super.getNextPage(page);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        updateDefaultUserPreference();
        final OrganizationFileStore artifact = getFileStore();
        try {
            getContainer().run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.validatingOrganizationContent, IProgressMonitor.UNKNOWN);
                    final IStatus status = validator.validate(artifact.getContent());
                    if (!status.isOK()) {
                        throw new InvocationTargetException(new OrganizationValidationException(status));
                    }
                    monitor.beginTask(Messages.synchronizingOrganization, IProgressMonitor.UNKNOWN);
                    final ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
                    final IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
                    final Command cmd = service.getCommand("org.bonitasoft.studio.engine.installOrganization");
                    try {
                        final Parameterization p = new Parameterization(cmd.getParameter("artifact"), artifact.getName());
                        handlerService.executeCommand(new ParameterizedCommand(cmd, new Parameterization[] { p }), null);
                        activeOrganizationProvider.saveActiveOrganization(artifact.getDisplayName());
                    } catch (final Exception e) {
                        throw new InvocationTargetException(e);
                    }
                }
            });
        } catch (final InvocationTargetException e) {
            if (e.getCause() instanceof OrganizationValidationException) {
                MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.organizationValidationFailed, e.getCause().getMessage());
            }
            return false;
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
            return false;
        }
        final String organizationName = artifact.getDisplayName();
        MessageDialog.openInformation(Display.getDefault().getActiveShell(), Messages.synchronizeInformationTitle,
                Messages.bind(Messages.synchronizeOrganizationSuccessMsg, organizationName));
        return true;
    }

    protected void updateDefaultUserPreference() {
        final String userName = userPage.getUser();
        String password = null;
        if (activeOrganization != null) {
            for (final User user : activeOrganization.getUsers().getUser()) {
                if (user.getUserName().equals(userPage.getUser())) {
                    password = user.getPassword().getValue();
                }
            }
        }
        activeOrganizationProvider.saveDefaultUser(userName);
        activeOrganizationProvider.saveDefaultPassword(password);
    }

    public OrganizationFileStore getFileStore() {
        return page.getFileStore();
    }

}
