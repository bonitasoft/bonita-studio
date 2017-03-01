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
package org.bonitasoft.studio.tests.form;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetDownloadType;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.FileLocator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author aurelie
 */
public class TestFileWidgetMigration {

    private final String diagramName = "MyDiagram7-1.0";
    private final String mainProcessName = "MyDiagram7";
    private final DiagramRepositoryStore store = RepositoryManager.getInstance()
            .getRepositoryStore(DiagramRepositoryStore.class);

    @Before
    public void before() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testFileWidgetResourceMigration() throws IOException, InvocationTargetException, InterruptedException {
        final MainProcess mainProcess = importDiagramAndOpen();
        final List<FileWidget> widgets = ModelHelper.getAllItemsOfType(mainProcess, FormPackage.eINSTANCE.getFileWidget());
        assertEquals(FileWidgetDownloadType.BROWSE, widgets.get(0).getDownloadType());
    }

    public MainProcess importDiagramAndOpen() throws IOException, InvocationTargetException, InterruptedException {
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        final URL fileURL1 = FileLocator.toFileURL(TestFileWidgetMigration.class.getResource(diagramName + ".bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);
        final DiagramFileStore diagramFileStore = store.getChild(diagramName + ".proc");
        diagramFileStore.open();
        final MainProcess mainProcess = diagramFileStore.getContent();
        assertEquals(mainProcessName, mainProcess.getName());
        return mainProcess;
    }

}
