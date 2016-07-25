/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.migration.migrator;

import java.lang.reflect.Field;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.migration.model.report.MigrationReportFactory;
import org.bonitasoft.studio.migration.model.report.Report;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edapt.common.IResourceSetFactory;
import org.eclipse.emf.edapt.internal.migration.execution.IClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.spi.history.Change;
import org.eclipse.emf.edapt.spi.history.MigrationChange;
import org.eclipse.emf.edapt.spi.history.Release;

/**
 * @author Romain Bioteau
 *
 */
public class BOSReportReconstructor extends org.eclipse.emf.edapt.internal.migration.execution.MigrationReconstructor {

    private final Report report;
    private CustomMigration customMigration;

    public BOSReportReconstructor(final List<URI> modelURIs, final Release sourceRelease,
            final Release targetRelease, final IProgressMonitor monitor,
            final IClassLoader classLoader, final ValidationLevel level, final IResourceSetFactory resourceSetFactory) {
        super(modelURIs, sourceRelease, targetRelease, monitor, classLoader, level, resourceSetFactory);
        report = MigrationReportFactory.eINSTANCE.createReport();
    }

    @Override
    public void startChange(final Change change) {
        super.startChange(change);
        if (change instanceof MigrationChange) {
            customMigration = getCustomMigration();
        }
    }

    @Override
    public void endChange(final Change change) {
        super.endChange(change);
        if (change instanceof MigrationChange) {
            if (customMigration instanceof IReportMigration) {
                report.getChanges().addAll(((IReportMigration) customMigration).getChanges());
                customMigration = null;
            }
        }
    }

    protected CustomMigration getCustomMigration() {
        try {
            final Field customMigrationInstance = org.eclipse.emf.edapt.internal.migration.execution.MigrationReconstructor.class
                    .getDeclaredField("customMigration");
            customMigrationInstance.setAccessible(true);
            return  (CustomMigration) customMigrationInstance.get(this);
        } catch (final NoSuchFieldException e) {
            BonitaStudioLog.error(e);
        } catch (final SecurityException e) {
            BonitaStudioLog.error(e);
        } catch (final IllegalArgumentException e) {
            BonitaStudioLog.error(e);
        } catch (final IllegalAccessException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public Report getReport() {
        return report;
    }


}
