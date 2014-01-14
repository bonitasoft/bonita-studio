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

import java.util.List;

import org.bonitasoft.studio.migration.model.report.MigrationReportFactory;
import org.bonitasoft.studio.migration.model.report.Report;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edapt.history.Change;
import org.eclipse.emf.edapt.history.MigrationChange;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.IClassLoader;
import org.eclipse.emf.edapt.migration.execution.ValidationLevel;

/**
 * @author Romain Bioteau
 *
 */
public class BOSReportReconstructor extends MigrationReconstructor {

	private Report report;

	public BOSReportReconstructor(List<URI> modelURIs, Release sourceRelease,
			Release targetRelease, IProgressMonitor monitor,
			IClassLoader classLoader, ValidationLevel level) {
		super(modelURIs, sourceRelease, targetRelease, monitor, classLoader, level);
		this.report = MigrationReportFactory.eINSTANCE.createReport();
	}

	@Override
	public void endChange(Change change) {
		if (isEnabled()) {
			checkResume(change);
			if (isStarted()) {
				if (change instanceof MigrationChange
						&& customMigration != null) {
					try {
						customMigration.migrateAfter(repository.getModel(),
								repository.getMetamodel());
						if(customMigration instanceof IReportMigration){
							report.getChanges().addAll(((IReportMigration) customMigration).getChanges());
						}
						monitor.worked(1);
						checkConformanceIfMoreThan(ValidationLevel.CUSTOM_MIGRATION);
					} catch (MigrationException e) {
						throwWrappedMigrationException(e);
					} finally {
						customMigration = null;
					}
				} else if (change.eContainer() instanceof Release) {
					monitor.worked(1);
					checkConformanceIfMoreThan(ValidationLevel.CHANGE);
				}
			}
		}
	}

	public Report getReport() {
		return report;
	}
	
	
}
