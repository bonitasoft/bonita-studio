/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration.step;

import java.nio.file.Path;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.Version;

public class ProvidedGroovyScriptRemovedStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.providedGroovyScriptRemovedMigrationTitle,
                Messages.providedGroovyScriptRemovedMigrationDescription);
    }

    @Override
    public MigrationReport run(Path project, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.providedGroovyScriptRemovedMigrationTitle);
        BonitaStudioLog.info(String.format("Starting %s...", ProvidedGroovyScriptRemovedStep.class.getName()));
        var result = MigrationReport.emptyReport();
        result.removed(
                "Deprecated provided groovy classes `BonitaUsers`, `BonitaSql`, `BonitaXML` and `BonitaTypes` have been removed.");
        BonitaStudioLog.info(String.format("%s completed.", ProvidedGroovyScriptRemovedStep.class.getName()));
        return result;
    }

    @Override
    public boolean appliesToVersion(String sourceVersion) {
        return Version.parseVersion(sourceVersion).compareTo(new Version("9.0.0")) < 0;
    }

}
