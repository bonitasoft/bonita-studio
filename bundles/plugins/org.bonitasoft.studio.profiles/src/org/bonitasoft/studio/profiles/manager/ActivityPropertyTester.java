/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.profiles.manager;

import org.eclipse.core.expressions.PropertyTester;
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

	private static final String PROPERTY_IS_ACTIVITY_ENABLED = "isActivityEnabled"; //$NON-NLS-1$
	private static final String PROPERTY_IS_CATEGORY_ENABLED = "isCategoryEnabled"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.expressions.PropertyTester#test(java.lang.Object,
	 *      java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		if (args.length == 1 && receiver instanceof IWorkbenchWindow && args[0] instanceof String) {
			if (PROPERTY_IS_ACTIVITY_ENABLED.equals(property)) {
				return isActivityEnabled((String) args[0], ((IWorkbenchWindow)receiver).getWorkbench());
			} else if (PROPERTY_IS_CATEGORY_ENABLED.equals(property)) {
				return isCategoryEnabled((String) args[0], ((IWorkbenchWindow)receiver).getWorkbench());
			}
		}
		return false;
	}

	private static boolean isActivityEnabled(String activityId, IWorkbench workbench) {
		try {
			IWorkbenchActivitySupport workbenchActivitySupport =
					workbench.getActivitySupport();
			IActivityManager activityManager = workbenchActivitySupport
					.getActivityManager();
			return activityManager.getActivity(activityId).isEnabled();
		} catch (Exception e) {
			// workbench not yet activated; nothing enabled yet
		}
		return false;
	}

	private static boolean isCategoryEnabled(String categoryId, IWorkbench workbench) {
		try {
			IWorkbenchActivitySupport workbenchActivitySupport =
					workbench.getActivitySupport();
			IActivityManager activityManager = workbenchActivitySupport
					.getActivityManager();
			return WorkbenchActivityHelper.isEnabled(activityManager,
					categoryId);
		} catch (Exception e) {
			// workbench not yet activated; nothing enabled yet
		}
		return false;
	}
	
}
