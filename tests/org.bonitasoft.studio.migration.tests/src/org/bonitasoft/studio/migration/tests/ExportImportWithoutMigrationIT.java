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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.operation.ExportBosArchiveOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.exporter.handler.ExportBosArchiveHandler;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

/**
 * @author Romain Bioteau
 *
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ExportImportWithoutMigrationIT extends SWTBotGefTestCase {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Override
    @Before
    public void setUp() throws Exception{
        bot.saveAllEditors();
        bot.closeAllEditors();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        bot.saveAllEditors();
        bot.closeAllEditors();
    }

    @Test
    public void should_export_and_import_of_a_diagram_do_not_trigger_migration() throws Exception {
        final BotApplicationWorkbenchWindow workbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotProcessDiagramPerspective newDiagram = workbenchWindow.createNewDiagram();
        final BotGefProcessDiagramEditor diagramEditor = newDiagram.activeProcessDiagramEditor();
        diagramEditor.selectDiagram();
        final EObject originalSemanticElement = diagramEditor.getSelectedSemanticElement();
        assertThat(originalSemanticElement).isInstanceOf(MainProcess.class);

        final ExportBosArchiveOperation exportBosArchiveOperation = new ExportBosArchiveOperation();
        final File destFolder = folder.newFolder();
        destFolder.mkdirs();
        final File bosFile = new File(destFolder,"test.bos");
        exportBosArchiveOperation.setDestinationPath(bosFile.getAbsolutePath());

        final Set<Object> allDiagramRelatedFiles = ExportBosArchiveHandler.getAllDiagramRelatedFiles((MainProcess) originalSemanticElement);
        final Set<IResource> resources = new HashSet<IResource>();
        for (final Object filestore : allDiagramRelatedFiles) {
            if (filestore instanceof IRepositoryFileStore) {
                resources.add(((IRepositoryFileStore) filestore).getResource());
            }
        }
        assertThat(resources).isNotEmpty();
        exportBosArchiveOperation.setResources(resources);
        assertThat(bosFile.exists()).isFalse();
        exportBosArchiveOperation.run(Repository.NULL_PROGRESS_MONITOR);
        assertThat(bosFile.exists()).isTrue();

        //delete diagram
        final DiagramRepositoryStore diagramRepositoryStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        diagramRepositoryStore.getDiagram(((MainProcess) originalSemanticElement).getName(), ((MainProcess) originalSemanticElement).getVersion());
    }




}
