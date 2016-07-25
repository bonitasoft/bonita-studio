/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.profiles.manager;

import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.activities.WorkbenchActivityHelper;


/**
 * @author Romain Bioteau
 *
 */
public class ActivityPropertyTester extends PropertyTester {

    protected static final String PROPERTY_IS_ACTIVITY_ENABLED = "isActivityEnabled"; //$NON-NLS-1$
    protected static final String PROPERTY_IS_CATEGORY_ENABLED = "isCategoryEnabled"; //$NON-NLS-1$
    protected static final String PROPERTY_IS_6XLEGACY_ENABLED = "is6XLegacyEnabled"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.core.expressions.PropertyTester#test(java.lang.Object,
	 *      java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
    public boolean test(final Object receiver, final String property, final Object[] args,
			final Object expectedValue) {
		if (args.length == 1 && receiver instanceof IWorkbenchWindow && args[0] instanceof String) {
            switch (property) {
                case PROPERTY_IS_ACTIVITY_ENABLED:
                    return isActivityEnabled((String) args[0], ((IWorkbenchWindow) receiver).getWorkbench());
                case PROPERTY_IS_CATEGORY_ENABLED:
                    return isCategoryEnabled((String) args[0], ((IWorkbenchWindow) receiver).getWorkbench());
                default:
                    return false;
            }
        }
        if (PROPERTY_IS_6XLEGACY_ENABLED.equals(property)) {
            return is6xLegacyModeActivated();
		}
		return false;
	}

    protected boolean is6xLegacyModeActivated() {
        return getBonitaPreferenceStore().getBoolean(BonitaPreferenceConstants.SHOW_LEGACY_6X_MODE);
    }

    protected IPreferenceStore getBonitaPreferenceStore() {
        return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
    }

    private boolean isActivityEnabled(final String activityId, final IWorkbench workbench) {
		try {
            final IActivityManager activityManager = getActivityManager(workbench);
			return activityManager.getActivity(activityId).isEnabled();
		} catch (final Exception e) {
			// workbench not yet activated; nothing enabled yet
		}
		return false;
	}

    private boolean isCategoryEnabled(final String categoryId, final IWorkbench workbench) {
		try {
            final IActivityManager activityManager = getActivityManager(workbench);
            return WorkbenchActivityHelper.isEnabled(activityManager, categoryId);
		} catch (final Exception e) {
			// workbench not yet activated; nothing enabled yet
		}
		return false;
	}

    protected IActivityManager getActivityManager(final IWorkbench workbench) {
        final IWorkbenchActivitySupport workbenchActivitySupport = workbench.getActivitySupport();
        final IActivityManager activityManager = workbenchActivitySupport.getActivityManager();
        return activityManager;
    }

}
