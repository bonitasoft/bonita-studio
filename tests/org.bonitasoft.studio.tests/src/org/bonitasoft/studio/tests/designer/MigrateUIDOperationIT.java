/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.designer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.designer.core.operation.MigrateUIDOperation;
import org.bonitasoft.studio.tests.util.InitialProjectRule;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MigrateUIDOperationIT {

    private Path tmpUidWSFolder;
    
    @Rule
    public InitialProjectRule projectRule = InitialProjectRule.INSTANCE;

    @Before
    public void setup() throws IOException {
        tmpUidWSFolder = Files.createTempDirectory("MigrateUIDOperationTest");
        Files.delete(tmpUidWSFolder);
    }

    @After
    public void clean() throws IOException {
        FileUtil.deleteDir(tmpUidWSFolder);
    }

    @Test
    public void shouldMigrateUidArtifacts() throws Exception {
        var validUidWorkspace = new File(
                FileLocator.toFileURL(MigrateUIDOperationIT.class.getResource("/valid_uid_workspace")).getFile());
        FileUtil.copyDirectory(validUidWorkspace.toPath(), tmpUidWSFolder);

        var operation = new MigrateUIDOperation().useStandaloneUIDAt(tmpUidWSFolder);
        operation.run(new NullProgressMonitor());

        StatusAssert.assertThat(operation.getStatus()).isOK();
        assertThat(operation.getStatus().getChildren())
                .hasSize(5) // One status per artifact migrated
                .allMatch(status -> status.getSeverity() == IStatus.OK);
        assertThat(operation.getLogs()).isNotEmpty();
    }

    @Test
    public void shouldFailMigration() throws Exception {
        var validUidWorkspace = new File(
                FileLocator.toFileURL(MigrateUIDOperationIT.class.getResource("/invalid_uid_workspace")).getFile());
        FileUtil.copyDirectory(validUidWorkspace.toPath(), tmpUidWSFolder);

        var operation = new MigrateUIDOperation().useStandaloneUIDAt(tmpUidWSFolder);
        try {
            operation.run(new NullProgressMonitor());
            fail();
        } catch (InvocationTargetException e) {
            StatusAssert.assertThat(operation.getStatus()).isError();
            assertThat(operation.getLogs()).isNotEmpty().contains("ERROR");
        }
    }

}
