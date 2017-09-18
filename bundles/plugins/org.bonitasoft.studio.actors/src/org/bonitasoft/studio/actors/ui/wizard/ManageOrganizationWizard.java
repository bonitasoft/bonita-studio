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
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.actors.ui.wizard.page.AbstractOrganizationWizardPage;
import org.bonitasoft.studio.actors.ui.wizard.page.GroupsWizardPage;
import org.bonitasoft.studio.actors.ui.wizard.page.ManageOrganizationWizardPage;
import org.bonitasoft.studio.actors.ui.wizard.page.RolesWizardPage;
import org.bonitasoft.studio.actors.ui.wizard.page.UsersWizardPage;
import org.bonitasoft.studio.actors.validator.OrganizationValidationException;
import org.bonitasoft.studio.actors.validator.OrganizationValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.preferences.OrganizationPreferenceConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ManageOrganizationWizard extends Wizard {

    private final List<Organization> organizations;
    private final List<Organization> organizationsWorkingCopy;
    private final OrganizationRepositoryStore store;
    private final OrganizationValidator validator = new OrganizationValidator();
    private Organization activeOrganization;
    private boolean activeOrganizationHasBeenModified = false;
    String userName;
    private final ActiveOrganizationProvider activeOrganizationProvider;
    private ECommandService commandService;
    private EHandlerService handlerService;

    public ManageOrganizationWizard(ECommandService commandService, EHandlerService handlerService) {
        this.commandService = commandService;
        this.handlerService = handlerService;
        activeOrganizationProvider = new ActiveOrganizationProvider();
        organizations = new ArrayList<Organization>();
        organizationsWorkingCopy = new ArrayList<Organization>();
        setWindowTitle(Messages.manageOrganizationTitle);
        store = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(OrganizationRepositoryStore.class);
        for (final IRepositoryFileStore file : store.getChildren()) {
            try {
                organizations.add((Organization) file.getContent());
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed read organization content", e);
            }
        }
        final IScopeContext projectScope = RepositoryManager.getInstance().getCurrentRepository().getScopeContext();
        final IEclipsePreferences node = projectScope.getNode(ActorsPlugin.PLUGIN_ID);
        final String activeOrganizationName = node.get(OrganizationPreferenceConstants.DEFAULT_ORGANIZATION,
                OrganizationPreferenceConstants.DEFAULT_ORGANIZATION_NAME);
        for (final Organization orga : organizations) {
            final Organization copy = EcoreUtil.copy(orga);
            if (activeOrganizationName.equals(orga.getName())) {
                activeOrganization = copy;
                final EContentAdapter adapter = new EContentAdapter() {

                    @Override
                    public void notifyChanged(final Notification notification) {
                        super.notifyChanged(notification);
                        activeOrganizationHasBeenModified = true;
                    }
                };
                activeOrganization.eAdapters().add(adapter);
            }
            organizationsWorkingCopy.add(copy);

        }

        setDefaultPageImageDescriptor(Pics.getWizban());
        setNeedsProgressMonitor(true);
    }

    @Override
    public void addPages() {
        addPage(new ManageOrganizationWizardPage(organizationsWorkingCopy));
        final GroupsWizardPage p = new GroupsWizardPage();
        final RolesWizardPage p1 = new RolesWizardPage();
        final UsersWizardPage p2 = new UsersWizardPage();
        addPage(p);
        addPage(p1);
        addPage(p2);
    }

    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page instanceof ManageOrganizationWizardPage) {
            final Organization orga = ((ManageOrganizationWizardPage) page).getSelectedOrganization();
            if (orga != null) {
                for (final IWizardPage p : getPages()) {
                    if (p instanceof AbstractOrganizationWizardPage) {
                        ((AbstractOrganizationWizardPage) p).setOrganization(orga);
                    }
                }
            }
        }
        return super.getNextPage(page);
    }

    @Override
    public boolean performFinish() {
        try {
            getContainer().run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    monitor.beginTask(Messages.saveOrganization, IProgressMonitor.UNKNOWN);
                    for (final Organization organization : organizationsWorkingCopy) {
                        monitor.subTask(Messages.validatingOrganizationContent);
                        final IStatus status = validator.validate(organization);
                        if (!status.isOK()) {
                            throw new InvocationTargetException(
                                    new OrganizationValidationException((MultiStatus) status, organization.getName()));
                        }
                        final String fileName = organization.getName() + "." + OrganizationRepositoryStore.ORGANIZATION_EXT;
                        IRepositoryFileStore file = store.getChild(fileName);
                        Organization oldOrga = null;
                        if (file == null) {
                            file = store.createRepositoryFileStore(fileName);
                        } else {
                            try {
                                oldOrga = (Organization) file.getContent();
                            } catch (final ReadFileStoreException e) {
                                BonitaStudioLog.error("Failed read organization content", e);
                            }
                        }
                        if (oldOrga != null) {
                            final RefactorActorMappingsOperation refactorOp = new RefactorActorMappingsOperation(oldOrga,
                                    organization);
                            refactorOp.run(monitor);
                        }
                        file.save(organization);
                    }
                    for (final Organization orga : organizations) {
                        boolean exists = false;
                        for (final Organization orgCopy : organizationsWorkingCopy) {
                            if (orgCopy.getName().equals(orga.getName())) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            final IRepositoryFileStore f = store
                                    .getChild(orga.getName() + "." + OrganizationRepositoryStore.ORGANIZATION_EXT);
                            if (f != null) {
                                f.delete();
                            }
                        }
                    }
                    monitor.done();
                }
            });
        } catch (final InterruptedException | InvocationTargetException e) {
            openErrorStatusDialog(e);
            return false;
        }
        final String pref = activeOrganizationProvider.getPublishOrganizationState();
        final boolean publishOrganization = activeOrganizationProvider.shouldPublishOrganization();
        if (publishOrganization && MessageDialogWithToggle.ALWAYS.equals(pref)) {
            try {
                return true;
            } finally {
                publishOrganization();
            }
        } else {
            if (MessageDialogWithToggle.NEVER.equals(pref) && activeOrganizationHasBeenModified) {
                final String[] buttons = { IDialogConstants.YES_LABEL, IDialogConstants.NO_LABEL };
                final MessageDialogWithToggle mdwt = new MessageDialogWithToggle(Display.getDefault().getActiveShell(),
                        Messages.organizationHasBeenModifiedTitle,
                        null, Messages.bind(Messages.organizationHasBeenModifiedMessage, activeOrganization.getName()),
                        MessageDialog.WARNING, buttons, 0,
                        Messages.doNotDisplayAgain, false);
                mdwt.setPrefStore(activeOrganizationProvider.getPreferenceStore());
                mdwt.setPrefKey(OrganizationPreferenceConstants.TOGGLE_STATE_FOR_PUBLISH_ORGANIZATION);
                final int index = mdwt.open();
                if (index == 2) {
                    try {
                        activeOrganizationProvider.savePublishOrganization(mdwt.getToggleState());
                        return true;
                    } finally {
                        publishOrganization();
                    }
                } else {
                    if (mdwt.getToggleState()) {
                        activeOrganizationProvider.savePublishOrganization(false);
                        activeOrganizationProvider.savePublishOrganizationState(MessageDialogWithToggle.ALWAYS);
                    }
                }
            }

        }
        return true;
    }

    private void publishOrganization() {
        Display.getDefault().asyncExec(() -> {
            handlerService.executeHandler(ParameterizedCommand
                    .generateCommand(commandService.getCommand("org.bonitasoft.studio.organization.publish"), null));
        });
    }

    protected void openErrorStatusDialog(final Exception exception) {
        if (exception.getCause() instanceof OrganizationValidationException) {
            new MultiStatusDialog(Display.getDefault().getActiveShell(), Messages.organizationValidationFailed,
                    Messages.organizationValidationFailedMsg, MessageDialog.ERROR,
                    new String[] { IDialogConstants.CLOSE_LABEL },
                    ((OrganizationValidationException) exception.getCause()).getValidationStatus()).open();
        } else {
            BonitaStudioLog.error(exception);
        }
    }

}
