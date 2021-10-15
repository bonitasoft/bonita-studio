/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.store;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.migration.MigrationPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edapt.internal.common.ResourceUtils;
import org.eclipse.emf.edapt.internal.migration.execution.internal.BundleClassLoader;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.Release;


public class SingleResourceMigrator extends Migrator {

    private static final String MIGRATION_HISTORY_PATH = "process.history";

    public SingleResourceMigrator() throws MigrationException {
        super(URI.createPlatformPluginURI(getMigrationHistoryPath(), true),
                new BundleClassLoader(MigrationPlugin.getDefault().getBundle()));
    }

    private static String getMigrationHistoryPath() {
        return "/"
                + Platform.getBundle("org.bonitasoft.studio-models")
                        .getSymbolicName()
                + "/" + MIGRATION_HISTORY_PATH;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.execution.Migrator#migrateAndSave(java.util.List, org.eclipse.emf.edapt.spi.history.Release,
     * org.eclipse.emf.edapt.spi.history.Release, org.eclipse.core.runtime.IProgressMonitor, java.util.Map)
     */
    @Override
    public void migrateAndSave(List<URI> modelURIs, Release sourceRelease, Release targetRelease,
            IProgressMonitor monitor, Map<String, Object> options) throws MigrationException {
        ResourceSet resourceSet = migrateAndLoad(modelURIs, sourceRelease, targetRelease, monitor);
        try {
            if (resourceSet.getResources().size() > 1) {
                resourceSet.getResources().removeIf(r -> !modelURIs.contains(r.getURI()));
            }
            ResourceUtils.saveResourceSet(resourceSet, options);
        } catch (IOException e) {
            throw new MigrationException(e);
        }
    }
}
