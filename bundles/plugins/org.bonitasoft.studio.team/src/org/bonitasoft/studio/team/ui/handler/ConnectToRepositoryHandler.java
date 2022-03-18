/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.preferences.TeamPreferenceInitializer;
import org.bonitasoft.studio.team.ui.dialog.DynamicLabelWizardDialog;
import org.bonitasoft.studio.team.ui.wizard.ConnectToRepositoryWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.equinox.security.storage.EncodingUtils;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.svn.core.SVNMessages;
import org.eclipse.team.svn.core.operation.LoggedOperation;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryLocation.LocationReferenceTypeEnum;
import org.eclipse.team.svn.core.resource.SSHSettings;
import org.eclipse.team.svn.core.resource.SSLSettings;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.ui.PlatformUI;

/**
 * @author Baptiste Mesta
 */
public class ConnectToRepositoryHandler extends AbstractHandler {

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        new TeamPreferenceInitializer().initializeDefaultPreferences();
        final boolean closed = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(true);
        if (closed) {
            final Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            final ConnectToRepositoryWizard wizard = new ConnectToRepositoryWizard();
            wizard.setNeedsProgressMonitor(true);
            final DynamicLabelWizardDialog dialog = new DynamicLabelWizardDialog(activeShell, wizard, Messages.team_checkout);
            if (dialog.open() == Dialog.OK) {
                saveRepositoryLocation(wizard.getRepositoryLocation());
            }
        }
        return null;
    }

    protected ISecurePreferences getSVNNodeForSecurePreferences(final IRepositoryLocation location, final String realm) {
        final ISecurePreferences preferences = SecurePreferencesFactory.getDefault();
        if (preferences == null) {
            return null;
        }
        String urlPart = location.getUrlAsIs() + ":" + location.getId(); //$NON-NLS-1$
        if (!"".equals(realm)) { //$NON-NLS-1$
            urlPart += ":" + realm; //$NON-NLS-1$
        }
        final String path = "/SVN/" + EncodingUtils.encodeSlashes(urlPart);
        try {
            return preferences.node(path);
        } catch (final IllegalArgumentException e) {
            return null; // invalid path
        }
    }

    private void saveRepositoryLocation(final IRepositoryLocation repoLocation) {
        final IEclipsePreferences pref = SVNRemoteStorage.getRepositoriesPreferences(SVNRemoteStorage.PREF_REPOSITORIES_NODE);
        pref.put(repoLocation.getId(), SVNRemoteStorage.instance().repositoryLocationAsReference(repoLocation, LocationReferenceTypeEnum.ALL));
        final ISecurePreferences node = getSVNNodeForSecurePreferences(repoLocation, "");
        if (node != null) {
            try {
                final IRepositoryLocation tmp = repoLocation; //$NON-NLS-1$
                final boolean toStorePass = tmp.isPasswordSaved();

                //store normal password settings
                node.put("username", toStorePass ? tmp.getUsername() : "", false); //$NON-NLS-1$
                node.put("password", toStorePass ? tmp.getPassword() : "", true); //$NON-NLS-1$ //$NON-NLS-2$
                node.putBoolean("password_saved", toStorePass, false); //$NON-NLS-1$

                //store SSH settings
                final SSHSettings sshSettings = tmp.getSSHSettings();
                final boolean useKeyFile = sshSettings.isUseKeyFile();
                node.putBoolean("ssh_use_key", useKeyFile, false); //$NON-NLS-1$
                boolean savePassphrase = sshSettings.isPassPhraseSaved();
                node.putBoolean("ssh_passphrase_saved", useKeyFile ? savePassphrase : false, false); //$NON-NLS-1$ //$NON-NLS-2$
                node.put("ssh_key", useKeyFile ? sshSettings.getPrivateKeyPath() : "", false); //$NON-NLS-1$ //$NON-NLS-2$
                node.put("ssh_passprase", useKeyFile && savePassphrase ? sshSettings.getPassPhrase() : "", true); //$NON-NLS-1$ //$NON-NLS-2$
                node.putInt("ssh_port", sshSettings.getPort(), false);

                //store SSL settings
                final SSLSettings sslSettings = tmp.getSSLSettings();
                final boolean clientAuthEnabled = sslSettings.isAuthenticationEnabled();
                savePassphrase = sslSettings.isPassPhraseSaved();
                node.putBoolean("ssl_enabled", clientAuthEnabled, false); //$NON-NLS-1$
                node.put("ssl_certificate", clientAuthEnabled ? sslSettings.getCertificatePath() : "", false); //$NON-NLS-1$ //$NON-NLS-2$
                node.putBoolean("ssl_passphrase_saved", clientAuthEnabled ? savePassphrase : false, false); //$NON-NLS-1$ //$NON-NLS-2$
                node.put("ssl_passphrase", clientAuthEnabled && savePassphrase ? sslSettings.getPassPhrase() : "", true); //$NON-NLS-1$ //$NON-NLS-2$
            } catch (final StorageException e) {
                LoggedOperation.reportError(SVNMessages.getErrorString("Error_SaveAutherizationInfo"), e); //$NON-NLS-1$
            }
        }

    }

}
