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

import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.preferences.OrganizationPreferenceConstants;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
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
        return getPreferenceNode()
                .map(prefs -> prefs.get(OrganizationPreferenceConstants.DEFAULT_ORGANIZATION,
                        OrganizationPreferenceConstants.DEFAULT_ORGANIZATION_NAME))
                .orElse(OrganizationPreferenceConstants.DEFAULT_ORGANIZATION_NAME);
    }

    public String getActiveOrganizationFileName() {
        return String.format("%s.xml", getActiveOrganization());
    }

    public String getDefaultUser() {
        return getPreferenceNode().map(prefs -> prefs.get(OrganizationPreferenceConstants.DEFAULT_USER,
                OrganizationPreferenceConstants.DEFAULT_USER_NAME))
                .orElse(OrganizationPreferenceConstants.DEFAULT_USER_NAME);
    }

    private Optional<IEclipsePreferences> getPreferenceNode() {
        return RepositoryManager.getInstance().getCurrentProject()
                .map(BonitaProject::getScopeContext)
                .map(ctx -> ctx.getNode(PLUGIN_ID));
    }


    public void saveDefaultUser(final String userName) {
        getPreferenceNode().ifPresent(prefs -> prefs.put(OrganizationPreferenceConstants.DEFAULT_USER, userName));
    }

    public void saveActiveOrganization(final String organizationName) {
        getPreferenceNode().ifPresent(prefs -> {
            prefs.put(OrganizationPreferenceConstants.DEFAULT_ORGANIZATION, organizationName);
            PlatformUI.getWorkbench().getService(IEventBroker.class).send(ACTIVE_ORGANIZATION_CHANGED,
                    organizationName);
        });
    }

    public boolean shouldPublishOrganization() {
        return getPreferenceNode()
                .map(prefs -> prefs.getBoolean(OrganizationPreferenceConstants.PUBLISH_ORGANIZATION, false))
                .orElse(false);
    }

    public String getPublishOrganizationState() {
        return getPreferenceNode()
                .map(prefs -> prefs.get(OrganizationPreferenceConstants.TOGGLE_STATE_FOR_PUBLISH_ORGANIZATION,
                        MessageDialogWithToggle.NEVER))
                .orElse(MessageDialogWithToggle.NEVER);
    }

    public IPreferenceStore getPreferenceStore() {
        return CommonRepositoryPlugin.getDefault().getPreferenceStore();
    }

    public void savePublishOrganization(final boolean publishOrganization) {
        getPreferenceNode().ifPresent(
                prefs -> prefs.putBoolean(OrganizationPreferenceConstants.PUBLISH_ORGANIZATION, publishOrganization));
    }

    public void savePublishOrganizationState(final String state) {
        getPreferenceNode().ifPresent(
                prefs -> prefs.put(OrganizationPreferenceConstants.TOGGLE_STATE_FOR_PUBLISH_ORGANIZATION, state));
    }

    public void flush() {
        getPreferenceNode().ifPresent(prefs -> {
            try {
                prefs.flush();
            } catch (BackingStoreException e) {
                BonitaStudioLog.error(e);
            }
        });
    }

}
