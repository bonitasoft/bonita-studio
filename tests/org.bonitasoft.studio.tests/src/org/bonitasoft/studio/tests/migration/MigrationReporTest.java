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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.junit.BeforeClass;
import org.junit.Test;

public class MigrationReporTest {

    private SWTGefBot bot = new SWTGefBot();

    private final DiagramRepositoryStore store = RepositoryManager.getInstance()
            .getRepositoryStore(DiagramRepositoryStore.class);

    @BeforeClass
    public static void disablePopup() {
        FileActionDialog.setDisablePopup(true);
    }

    @Test
    public void testAutomaticPerspectiveSwitch() throws Exception {
        FileActionDialog.setDisablePopup(true);
        final URL url = MigrationReporTest.class.getResource("TestMigrationReport-1.0.bos");
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        op.setArchiveFile(FileLocator.toFileURL(url).getFile());
        op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);
        store.getChild("MonDiagramme1-1.0.proc").open();
        assertEquals("Invalid perspective for process with migration report",
                "org.bonitasoft.studio.migration.perspective.process",
                BonitaPerspectivesUtils.getPerspectiveId(bot.activeEditor().getReference().getEditor(false)));
    }

    @Test
    public void testCompleteReport() throws Exception {
        FileActionDialog.setDisablePopup(true);
        final URL url = MigrationReporTest.class.getResource("TestMigrationReport-1.0.bos");
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        op.setArchiveFile(FileLocator.toFileURL(url).getFile());
        op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);
        store.getChild("MonDiagramme1-1.0.proc").open();
        bot.viewById("org.bonitasoft.studio.migration.view").bot().button(Messages.completeImport).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.completeImport));
        bot.checkBox().deselect();
        bot.button(IDialogConstants.OK_LABEL).click();
        assertEquals("Invalid perspective for process without migration report", "org.bonitasoft.studio.perspective.process",
                BonitaPerspectivesUtils.getPerspectiveId(bot.activeEditor().getReference().getEditor(false)));
        final EObject mainProcess = (EObject) bot.gefEditor(bot.activeEditor().getTitle()).mainEditPart().part().getModel();
        assertEquals("Report model should have been deleted", 2, mainProcess.eResource().getContents().size());
    }

    @Test
    public void testEditorSelectionSynchronization() throws Exception {
        FileActionDialog.setDisablePopup(true);
        final URL url = MigrationReporTest.class.getResource("TestMigrationReport-1.0.bos");
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        op.setArchiveFile(FileLocator.toFileURL(url).getFile());
        op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);
        store.getChild("MonDiagramme1-1.0.proc").open();
        bot.viewById("org.bonitasoft.studio.migration.view").toolbarToggleButton("Link with Editor").select();
        bot.table().select(1);
        final SWTBotGefEditPart part = bot.gefEditor(bot.activeEditor().getTitle()).selectedEditParts().get(0);
        assertTrue(((IGraphicalEditPart) part.part()).resolveSemanticElement() instanceof Pool);
    }

}
