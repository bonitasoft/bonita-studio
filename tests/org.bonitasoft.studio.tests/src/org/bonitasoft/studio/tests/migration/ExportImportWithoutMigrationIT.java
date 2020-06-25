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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.operation.ExportBosArchiveOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.exporter.handler.ExportBosArchiveHandler;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.assertions.ElementAssert;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ExportImportWithoutMigrationIT extends SWTBotGefTestCase {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private RepositoryAccessor repositoryAccessor;

    @Override
    @Before
    public void setUp() throws Exception {
        bot.saveAllEditors();
        bot.closeAllEditors();
        FileActionDialog.setDisablePopup(true);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore()
                .setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
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
        final BotProcessDiagramPerspective diagramPerspective = workbenchWindow.createNewDiagram();
        BotGefProcessDiagramEditor diagramEditor = diagramPerspective.activeProcessDiagramEditor();
        diagramEditor.selectDiagram();
        diagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDiagramTab()
                .setName("ExportImportWithoutMigrationIT");
        diagramEditor = diagramPerspective.activeProcessDiagramEditor().selectDiagram();
        final EObject originalSemanticElement = diagramEditor.getSelectedSemanticElement();
        assertThat(originalSemanticElement).isInstanceOf(MainProcess.class);
        ElementAssert.assertThat((MainProcess) originalSemanticElement).hasName("ExportImportWithoutMigrationIT");
        final EObject source = EcoreUtil.copy(originalSemanticElement);
        final ExportBosArchiveOperation exportBosArchiveOperation = new ExportBosArchiveOperation();
        final File destFolder = folder.newFolder();
        destFolder.mkdirs();
        final File bosFile = new File(destFolder, "ExportImportWithoutMigrationIT-1.0.bos");
        exportBosArchiveOperation.setDestinationPath(bosFile.getAbsolutePath());

        final DiagramRepositoryStore diagramRepositoryStore = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        DiagramFileStore diagramFileStore = diagramRepositoryStore.getDiagram("ExportImportWithoutMigrationIT",
                "1.0");
        assertThat(diagramFileStore).isNotNull();
        final MainProcess mainProcess = diagramFileStore.getContent();
        assertThat(mainProcess.eResource()).isNotNull();
        final Set<Object> allDiagramRelatedFiles = ExportBosArchiveHandler.getAllDiagramRelatedFiles(mainProcess);
        final Set<IRepositoryFileStore> fileStores = new HashSet<>();
        for (final Object filestore : allDiagramRelatedFiles) {
            if (filestore instanceof IRepositoryFileStore) {
                fileStores.add((IRepositoryFileStore) filestore);
            }
        }
        assertThat(fileStores).isNotEmpty();
        exportBosArchiveOperation.setFileStores(fileStores);
        assertThat(bosFile.exists()).isFalse();
        exportBosArchiveOperation.run(Repository.NULL_PROGRESS_MONITOR);
        assertThat(bosFile.exists()).isTrue();

        //delete diagram
        diagramFileStore.delete();

        final ImportBosArchiveOperation importBosArchiveOperation = new ImportBosArchiveOperation(false, repositoryAccessor);
        importBosArchiveOperation.setArchiveFile(bosFile.getAbsolutePath());
        importBosArchiveOperation.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        importBosArchiveOperation.run(Repository.NULL_PROGRESS_MONITOR);
        assertThat(importBosArchiveOperation.getStatus().isOK()).isTrue();

        diagramFileStore = diagramRepositoryStore.getDiagram("ExportImportWithoutMigrationIT",
                "1.0");
        diagramFileStore.open();
        diagramPerspective.activeProcessDiagramEditor().selectDiagram();
        final EObject newSemanticElement = diagramPerspective.activeProcessDiagramEditor().getSelectedSemanticElement();
        final EObject target = EcoreUtil.copy(newSemanticElement);
        assertThat(EcoreUtil.equals(source, target)).isTrue();
    }

}
