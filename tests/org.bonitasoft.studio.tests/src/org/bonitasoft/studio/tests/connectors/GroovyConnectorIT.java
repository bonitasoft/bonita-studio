/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.connectors;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class GroovyConnectorIT {

    @Test
    public void should_groovy_connector_configuration_be_converted_at_export() throws Exception {
        RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator
                .toFileURL(TestDatabaseConnectorResulset.class.getResource("GroovyConnectorTest-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(new NullProgressMonitor());
        for (final IRepositoryFileStore fStore : op.getFileStoresToOpen()) {
            fStore.open();
        }
        final MainProcess mainProcess = (MainProcess) op.getFileStoresToOpen().get(0).getContent();

        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        final Status status = (Status) runProcessCommand
                .execute(ProcessSelector.createExecutionEvent((AbstractProcess) mainProcess.getElements().get(0)));
        assertThat(status.isOK()).isTrue();

    }
}
