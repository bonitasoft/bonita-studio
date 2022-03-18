/*******************************************************************************
 * Copyright (C) 2009, 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.preferences;

import org.bonitasoft.studio.team.TeamPlugin;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.egit.core.Activator;
import org.eclipse.egit.core.GitCorePreferences;
import org.eclipse.equinox.internal.security.storage.SecurePreferencesContainer;
import org.eclipse.equinox.internal.security.storage.SecurePreferencesWrapper;
import org.eclipse.equinox.security.storage.ISecurePreferences;
import org.eclipse.equinox.security.storage.SecurePreferencesFactory;
import org.eclipse.equinox.security.storage.provider.IProviderHints;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.team.internal.ui.IPreferenceIds;
import org.eclipse.team.internal.ui.TeamUIPlugin;
import org.eclipse.team.internal.ui.history.IFileHistoryConstants;
import org.eclipse.team.svn.core.SVNTeamPlugin;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.ui.SVNTeamUIPlugin;
import org.eclipse.team.svn.ui.preferences.SVNTeamPreferences;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarion.team.svn.connector.svnkit.SVNKitConnectorFactory;

public class TeamPreferenceInitializer extends AbstractPreferenceInitializer {

    protected static final String DEFAULT_SVNKIT = SVNKitConnectorFactory.CLIENT_ID;

    @Override
    public void initializeDefaultPreferences() {
        initializeTeamPreferenceSynchronizationMode();
        initializeSvnSecurePreferenceForDialogPrompt();
        initializeSvnConnectorDefaultValue(SVNTeamUIPlugin.instance().getPreferenceStore());
        TeamUIPlugin.getPlugin().getPreferenceStore()
                .setValue(IFileHistoryConstants.PREF_GENERIC_HISTORYVIEW_EDITOR_LINKING,
                        true);
        
        TeamUIPlugin.getPlugin().getPreferenceStore().setValue(IPreferenceIds.SYNCHRONIZING_COMPLETE_PERSPECTIVE,  MessageDialogWithToggle.NEVER);
        
        IPreferenceStore gitPreferenceStore = new ScopedPreferenceStore(InstanceScope.INSTANCE,
                Activator.PLUGIN_ID);
        gitPreferenceStore.setDefault(GitCorePreferences.core_autoShareProjects, false);
        WorkbenchPlugin.getDefault().getDecoratorManager()
                .setEnabled("org.eclipse.team.svn.ui.decorator.SVNLightweightDecorator", true);
        SVNTeamUIPlugin.instance().savePreferences();
        SVNRemoteStorage.instance().dispose();
    }

    protected void initializeSvnConnectorDefaultValue(final IPreferenceStore svnPreferenceStore) {
        final String coreSVNConnectorPreferenceName = SVNTeamPreferences
                .fullCoreName(SVNTeamPreferences.CORE_SVNCONNECTOR_NAME);
        svnPreferenceStore.setDefault(SVNTeamPreferences.COMMIT_DIALOG_KEEP_LOCKS, true);
        svnPreferenceStore.setDefault(SVNTeamPreferences.fullCoreName(SVNTeamPlugin.CORE_SVNCLIENT_NAME), DEFAULT_SVNKIT);
        svnPreferenceStore.setDefault(coreSVNConnectorPreferenceName, DEFAULT_SVNKIT);
    }

    private void initializeTeamPreferenceSynchronizationMode() {
        final IPreferenceStore store = TeamPlugin.getDefault().getPreferenceStore();
        store.setDefault(TeamPreferencesConstants.ApplyAllState, TeamPreferencesConstants.MANUAL);
    }

    private void initializeSvnSecurePreferenceForDialogPrompt() {
        final ISecurePreferences basePrefs = SecurePreferencesFactory.getDefault();
        final SecurePreferencesContainer prefsContainer = ((SecurePreferencesWrapper) basePrefs).getContainer();
        prefsContainer.setOption(IProviderHints.PROMPT_USER, Boolean.FALSE);
    }

}
