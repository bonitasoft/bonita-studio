/**
 * Copyright (C) 2012-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.migrator;

import java.io.IOException;
import java.util.List;

import org.bonitasoft.studio.migration.model.report.Report;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edapt.history.reconstruction.EcoreForwardReconstructor;
import org.eclipse.emf.edapt.internal.common.ResourceUtils;
import org.eclipse.emf.edapt.internal.migration.execution.IClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.internal.migration.execution.internal.WrappedMigrationException;
import org.eclipse.emf.edapt.internal.migration.internal.MaterializingBackwardConverter;
import org.eclipse.emf.edapt.internal.migration.internal.Persistency;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.History;
import org.eclipse.emf.edapt.spi.history.HistoryPackage;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class BOSMigrator extends Migrator {

    /** Classloader to load {@link CustomMigration}s. */
    private final IClassLoader classLoader;

    private final History history;

    /** Validation level. */
    private ValidationLevel level = ValidationLevel.CUSTOM_MIGRATION;

    public BOSMigrator(final History history, final IClassLoader classLoader) {
        super(history, classLoader);
        this.classLoader = classLoader;
        this.history = history;
    }

    public BOSMigrator(final URI historyURI, final IClassLoader classLoader) throws MigrationException {
        super(historyURI, classLoader);
        this.classLoader = classLoader;
        HistoryPackage.eINSTANCE.getHistory();
        try {
            history = ResourceUtils.loadElement(historyURI);
        } catch (final IOException e) {
            throw new MigrationException("History could not be loaded", e);
        }
    }

    private BOSReportReconstructor reportReconstructor;

    @Override
    public void migrateAndSave(final List<URI> modelURIs,
            final Release sourceRelease, final Release targetRelease,
            final IProgressMonitor monitor) throws MigrationException {

        final Model model = migrate(modelURIs, sourceRelease, targetRelease,
                monitor);
        if (model == null) {
            throw new MigrationException("Model is up-to-date", null);
        }
        try {
            Persistency.saveModel(model, null);
        } catch (final IOException e) {
            throw new MigrationException("Model could not be saved", e);
        }
    }

    /**
     * Migrate a model based on a set of {@link URI}s and load it afterwards.
     *
     * @param modelURIs
     *        The set of {@link URI}
     * @param sourceRelease
     *        Release to which the model conforms
     * @param targetRelease
     *        Release to which the model should be migrated (use null for
     *        the newest release)
     * @param monitor
     *        Progress monitor
     * @return The model in a {@link ResourceSet}
     */
    @Override
    public ResourceSet migrateAndLoad(final List<URI> modelURIs,
            final Release sourceRelease, final Release targetRelease,
            final IProgressMonitor monitor) throws MigrationException {
        final Model model = migrate(modelURIs, sourceRelease, targetRelease,
                monitor);
        if (model == null) {
            return null;
        }
        final MaterializingBackwardConverter converter = new MaterializingBackwardConverter();
        return converter.convert(model);
    }

    @Override
    public void setLevel(final ValidationLevel level) {
        super.setLevel(level);
        this.level = level;
    }

    /**
     * Migrate a model based on a set of {@link URI}s.
     *
     * @param modelURIs
     *        The set of {@link URI}
     * @param sourceRelease
     *        Release to which the model conforms
     * @param targetRelease
     *        Release to which the model should be migrated (use null for
     *        the newest release)
     * @param monitor
     *        Progress monitor
     * @return The model in the generic structure
     */
    private Model migrate(final List<URI> modelURIs,
            final Release sourceRelease, Release targetRelease,
            final IProgressMonitor monitor) throws MigrationException {
        try {
            if (targetRelease == null) {
                targetRelease = getLatestRelease();
            }
            if (sourceRelease == targetRelease) {
                return null;
            }

            monitor.beginTask("Migrate",
                    numberOfSteps(sourceRelease, targetRelease));
            final EcoreForwardReconstructor reconstructor = new EcoreForwardReconstructor(
                    URI.createFileURI("test"));
            reportReconstructor = new BOSReportReconstructor(modelURIs,
                    sourceRelease, targetRelease, monitor, classLoader, level, getResourceSetFactory());
            reconstructor.addReconstructor(reportReconstructor);

            reconstructor.reconstruct(targetRelease, false);

            final Model model = reportReconstructor.getModel();
            return model;
        } catch (final WrappedMigrationException e) {
            throw e.getCause();
        } finally {
            monitor.done();
        }
    }

    private int numberOfSteps(final Release sourceRelease, final Release targetRelease) {
        int size = 0;
        boolean inRelease = false;
        for (final Release release : history.getReleases()) {
            if (inRelease) {
                size += release.getChanges().size();
            }
            if (release == sourceRelease) {
                inRelease = true;
            }
            if (release == targetRelease) {
                break;
            }
        }
        return size;
    }

    public Report getReport() {
        return reportReconstructor.getReport();
    }
}
