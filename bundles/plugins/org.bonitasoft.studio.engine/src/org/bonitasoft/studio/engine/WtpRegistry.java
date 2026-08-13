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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.ServerCore;

/**
 * The WTP registry as this plugin uses it: look a persisted server or runtime up by id, ask what
 * adapter type it carries, and delete one without the failure becoming the caller's. Both the purge
 * and server creation need the same answers.
 */
class WtpRegistry {

    /** The persisted objects this plugin drives, and the adapter types they must carry. */
    record PersistedIds(String serverId, String serverTypeId, String runtimeId, String runtimeTypeId) {
    }

    /** A WTP deletion that may fail without its caller failing: see {@link #deleteQuietly}. */
    @FunctionalInterface
    interface Deletion {

        void delete() throws CoreException;
    }

    IServer findServer(String id) {
        return ServerCore.findServer(id);
    }

    IRuntime findRuntime(String id) {
        return ServerCore.findRuntime(id);
    }

    /**
     * ServerType and RuntimeType do not override equals, so the id is the identity: every comparison
     * must be made this way, and the type can be null on a persisted object whose adapter is absent.
     */
    String typeId(IServer server) {
        return server.getServerType() == null ? null : server.getServerType().getId();
    }

    String typeId(IRuntime runtime) {
        return runtime.getRuntimeType() == null ? null : runtime.getRuntimeType().getId();
    }

    /**
     * Deletes and swallows the failure. Every caller is recovering from stale state that the
     * configuration project's resource delta may already have dropped, so a failed deletion usually
     * means the object is gone: log it and let the next start's purge catch a genuine survivor.
     */
    void deleteQuietly(Deletion deletion, String id) {
        try {
            deletion.delete();
        } catch (CoreException e) {
            // keep the stack: it usually names the resource holding the deletion up
            BonitaStudioLog.error(String.format("Failed to delete the stale WTP object %s", id), e,
                    EnginePlugin.PLUGIN_ID);
        }
    }
}
