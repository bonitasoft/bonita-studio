/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.preferences.OrganizationPreferenceConstants;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;
import org.osgi.service.prefs.BackingStoreException;

@Creatable
public class ActiveOrganizationProvider {

    private static final String PLUGIN_ID = CommonRepositoryPlugin.PLUGIN_ID;
    public static final String ACTIVE_ORGANIZATION_CHANGED = "activeOrganizationChanged";

    public String getActiveOrganization() {
        return getPreferenceNode().get(OrganizationPreferenceConstants.DEFAULT_ORGANIZATION,
                OrganizationPreferenceConstants.DEFAULT_ORGANIZATION_NAME);
    }

    public String getActiveOrganizationFileName() {
        return String.format("%s.organization", getActiveOrganization());
    }

    public String getDefaultUser() {
        return getPreferenceNode().get(OrganizationPreferenceConstants.DEFAULT_USER,
                OrganizationPreferenceConstants.DEFAULT_USER_NAME);
    }

    private IEclipsePreferences getPreferenceNode() {
        final IScopeContext projectScope = RepositoryManager.getInstance().getCurrentRepository().getScopeContext();
        return projectScope.getNode(PLUGIN_ID);
    }

    public String getDefaultPassword() {
        return getPreferenceNode().get(OrganizationPreferenceConstants.DEFAULT_PASSWORD,
                OrganizationPreferenceConstants.DEFAULT_USER_PASSWORD);
    }

    public void saveDefaultUser(final String userName) {
        getPreferenceNode().put(OrganizationPreferenceConstants.DEFAULT_USER, userName);
    }

    public void saveDefaultPassword(final String password) {
        getPreferenceNode().put(OrganizationPreferenceConstants.DEFAULT_PASSWORD, password);
    }

    public void saveActiveOrganization(final String organizationName) {
        getPreferenceNode().put(OrganizationPreferenceConstants.DEFAULT_ORGANIZATION, organizationName);
        PlatformUI.getWorkbench().getService(IEventBroker.class).send(ACTIVE_ORGANIZATION_CHANGED, organizationName);
    }

    public boolean shouldPublishOrganization() {
        return getPreferenceNode().getBoolean(OrganizationPreferenceConstants.PUBLISH_ORGANIZATION, false);
    }

    public String getPublishOrganizationState() {
        return getPreferenceNode().get(OrganizationPreferenceConstants.TOGGLE_STATE_FOR_PUBLISH_ORGANIZATION,
                MessageDialogWithToggle.NEVER);
    }

    public IPreferenceStore getPreferenceStore() {
        return CommonRepositoryPlugin.getDefault().getPreferenceStore();
    }

    public void savePublishOrganization(final boolean publishOrganization) {
        getPreferenceNode().putBoolean(OrganizationPreferenceConstants.PUBLISH_ORGANIZATION, publishOrganization);
    }

    public void savePublishOrganizationState(final String state) {
        getPreferenceNode().get(OrganizationPreferenceConstants.TOGGLE_STATE_FOR_PUBLISH_ORGANIZATION, state);
    }

    public void flush() {
        try {
            getPreferenceNode().flush();
        } catch (BackingStoreException e) {
            BonitaStudioLog.error(e);
        }
    }
}
