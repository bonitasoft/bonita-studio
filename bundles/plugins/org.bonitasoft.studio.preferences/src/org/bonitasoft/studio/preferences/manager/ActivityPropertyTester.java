/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.preferences.manager;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;

public class ActivityPropertyTester extends PropertyTester {

    protected static final String PROPERTY_IS_6XLEGACY_ENABLED = "is6XLegacyEnabled"; //$NON-NLS-1$

    @Override
    public boolean test(final Object receiver, final String property, final Object[] args,
            final Object expectedValue) {
        return PROPERTY_IS_6XLEGACY_ENABLED.equals(property) ? is6xLegacyModeActivated() : false;
    }

    protected boolean is6xLegacyModeActivated() {
        return getBonitaPreferenceStore().getBoolean(BonitaPreferenceConstants.SHOW_LEGACY_6X_MODE);
    }

    protected IPreferenceStore getBonitaPreferenceStore() {
        return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
    }

    protected IActivityManager getActivityManager(final IWorkbench workbench) {
        final IWorkbenchActivitySupport workbenchActivitySupport = workbench.getActivitySupport();
        final IActivityManager activityManager = workbenchActivitySupport.getActivityManager();
        return activityManager;
    }

}
