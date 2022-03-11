/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.egit.ui.Activator;
import org.eclipse.egit.ui.UIPreferences;
import org.eclipse.egit.ui.internal.staging.StagingView.Presentation;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.team.internal.ui.TeamUIPlugin;
import org.eclipse.team.internal.ui.history.IFileHistoryConstants;


public class PreferenceInitializer extends AbstractPreferenceInitializer {

    private static final String PREF_SHOWALLFILTER = "org.eclipse.egit.ui.githistorypage.showallfilter";

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
        preferenceStore.setDefault(
                PREF_SHOWALLFILTER,
                "SHOWALLREPO");
        TeamUIPlugin.getPlugin().getPreferenceStore()
                .setValue(IFileHistoryConstants.PREF_GENERIC_HISTORYVIEW_EDITOR_LINKING, true);
        preferenceStore.setDefault(UIPreferences.STAGING_VIEW_PRESENTATION, Presentation.COMPACT_TREE.name());
    }

}
