/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.migration;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.migration.utils.PDFMigrationReportWriter;
import org.eclipse.core.runtime.FileLocator;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class MigrationReportPDFExportTest {

    @BeforeClass
    public static void disablePopup() {
        FileActionDialog.setDisablePopup(true);
    }

    @Test
    public void testExportAsPDF() throws Exception {
        FileActionDialog.setDisablePopup(true);
        final URL url = MigrationReportPDFExportTest.class.getResource("TestMigrationReport-1.0.bos");
        ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        op.setArchiveFile(FileLocator.toFileURL(url).getFile());
        op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);

        DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        final PDFMigrationReportWriter writer = new PDFMigrationReportWriter(
                store.getDiagram("MonDiagramme1", "1.0").getMigrationReport());
        final File targetFile = new File(
                ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath() + File.separatorChar + "test.pdf");
        targetFile.deleteOnExit();
        writer.execute(targetFile.getAbsolutePath());
        assertTrue(targetFile.exists());
        assertTrue(targetFile.length() > 0);
    }

}
