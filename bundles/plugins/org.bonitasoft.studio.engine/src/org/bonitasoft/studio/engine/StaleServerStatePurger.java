/**
 * Copyright (C) 2026 Bonitasoft S.A.
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

package org.bonitasoft.studio.engine;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

/**
 * Drops the WTP server, runtime, configuration project and install a previous Studio version left
 * behind, so a workspace that never crossed a version boundary stops resurrecting them.
 * <p>
 * Stale means one of three things, checked independently: the persisted server or runtime carries a
 * different adapter type id than the one this Studio drives, or the install content on disk reports
 * a different Tomcat line than the adapter expects. The install content is checked on its own
 * because the WTP markers can be absent while a stale install survives - a closed configuration
 * project hides the persisted server, and a half-failed workspace migration leaves the same shape.
 * <p>
 * The order the purge runs in is load-bearing: see {@link #purge()}.
 */
class StaleServerStatePurger {

    private final TomcatInstall install;
    private final WtpRegistry registry;
    private final String configurationProjectName;
    private final WtpRegistry.PersistedIds ids;

    StaleServerStatePurger(TomcatInstall install, WtpRegistry registry, String configurationProjectName,
            WtpRegistry.PersistedIds ids) {
        this.install = install;
        this.registry = registry;
        this.configurationProjectName = configurationProjectName;
        this.ids = ids;
    }

    void purge() throws CoreException {
        var server = registry.findServer(ids.serverId());
        var staleServer = server != null && !ids.serverTypeId().equals(registry.typeId(server));
        var runtime = registry.findRuntime(ids.runtimeId());
        var staleRuntime = runtime != null && !ids.runtimeTypeId().equals(registry.typeId(runtime));
        var staleInstall = !install.matchesAdapter();
        if (!staleServer && !staleRuntime && !staleInstall) {
            return;
        }
        // conf project first, and the disk before the WTP objects: deleting the bundle first would
        // erase the install-staleness evidence, so a failed deletion would leave no marker for the
        // next start to retry from, and the stale tomcat_conf would be published into the freshly
        // extracted install. Everything metadata-derived is recreated regardless (the conf project
        // here, the launch configuration unconditionally in createLaunchConfiguration), so the
        // multi-hundred-MB install tree is only purged when its content is genuinely stale - a
        // stale WTP marker over a current install must not cost a full re-extraction under the
        // workspace rule
        deleteServerConfigurationProject();
        if (staleInstall) {
            install.deleteStaleBundle();
        }
        // the conf project deletion may already have dropped the persisted objects through the
        // resource delta: a failed delete here means the state is already purged, so log and
        // continue - a genuinely surviving object is caught by the next start's purge
        if (server != null && (staleServer || staleInstall)) {
            registry.deleteQuietly(server::delete, ids.serverId());
        }
        if (runtime != null && (staleRuntime || staleInstall)) {
            registry.deleteQuietly(runtime::delete, ids.runtimeId());
        }
    }

    /**
     * The tomcat_conf folder is copied with overwrite semantics on every start, so files removed from
     * the current bundle would survive there and be published into the new install.
     */
    void deleteServerConfigurationProject() throws CoreException {
        var confProject = ResourcesPlugin.getWorkspace().getRoot()
                .getProject(configurationProjectName);
        if (confProject.exists()) {
            try {
                confProject.delete(true, true, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (CoreException e) {
                // the first purge step, so the most likely failure a user sees: it needs the
                // close-and-delete-by-hand instruction rather than the generic Eclipse status
                throw new CoreException(Status.error(
                        NLS.bind(Messages.staleServerConfigurationDeletionFailed,
                                String.valueOf(confProject.getLocation())),
                        e));
            }
        }
    }
}
