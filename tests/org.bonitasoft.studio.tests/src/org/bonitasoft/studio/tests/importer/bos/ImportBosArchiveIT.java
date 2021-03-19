/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.importer.bos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.asyncExec;
import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.io.File;
import java.lang.reflect.Constructor;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.importer.BotImportBOSDialog;
import org.bonitasoft.studio.swtbot.framework.projectExplorer.ProjectExplorerBot;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ImportBosArchiveIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    @After
    @Before
    public void cleanRepository() throws Exception {
        RepositoryManager.getInstance().getCurrentRepository().getAllStores()
                .stream().forEach(store -> store.getChildren().stream().forEach(IRepositoryFileStore::delete));
    }

    @Test
    public void should_import_resolve_conflict_before_import() throws Exception {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindowEx = new BotApplicationWorkbenchWindow(bot);
        final BotImportBOSDialog botImport = botApplicationWorkbenchWindowEx.importBOSArchive();
        botImport.setArchive(ImportBosArchiveIT.class.getResource("ConnectorInFormsForTests-1.0.bos"));
        botImport.next().next().importArchive();

        botApplicationWorkbenchWindowEx.importBOSArchive();
        botImport.setArchive(ImportBosArchiveIT.class.getResource("conflictingArchive/ConnectorInFormsForTests-1.0.bos"));

        botImport.existingRepository()
             .next();
        
        final SWTBotTreeItem diagramNodeItem = botImport.tree().getTreeItem("diagrams")
                .getNode("ConnectorInFormsForTests-1.0.proc");
        assertThat(diagramNodeItem.cell(1)).isEqualTo(Messages.overwriteAction);
        botImport.keepAll();
        assertThat(diagramNodeItem.cell(1)).isEqualTo(Messages.keepAction);
        botImport.overwriteAll();
        assertThat(diagramNodeItem.cell(1)).isEqualTo(Messages.overwriteAction);
        botImport.keepAll();
        assertThat(diagramNodeItem.cell(1)).isEqualTo(Messages.keepAction);

        botImport.back();
        botImport.newProject("new repo");

        botImport.existingRepository(org.bonitasoft.studio.common.repository.Messages.defaultRepositoryName, true);

        botImport.next();
        
        final SWTBotTreeItem diagramNode = botImport.tree().getTreeItem("diagrams")
                .getNode("ConnectorInFormsForTests-1.0.proc");
        assertThat(diagramNode.cell(1)).isEqualTo(Messages.overwriteAction);
        botImport.keepAll();
        assertThat(diagramNode.cell(1)).isEqualTo(Messages.keepAction);
        botImport.overwriteAll();
        assertThat(diagramNode.cell(1)).isEqualTo(Messages.overwriteAction);
        botImport.keepAll();
        assertThat(diagramNode.cell(1)).isEqualTo(Messages.keepAction);

        botImport.next().importArchive(); // import with keepExisting -> next step should be conflicting too. 

        botApplicationWorkbenchWindowEx.importBOSArchive();
        botImport.setArchive(ImportBosArchiveIT.class.getResource("conflictingArchive/ConnectorInFormsForTests-1.0.bos"));

        botImport.next().next().importArchive(); // import with overwrite -> next step shouldn't be conflicting.
    }

    @Test
    public void should_switch_repo_to_manage_conflicts() throws Exception {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindowEx = new BotApplicationWorkbenchWindow(bot);
        final BotImportBOSDialog botImport = botApplicationWorkbenchWindowEx.importBOSArchive();
        botImport.setArchive(ImportBosArchiveIT.class.getResource("ConnectorInFormsForTests-1.0.bos"));
        botImport.next().next().importArchive();

        botApplicationWorkbenchWindowEx.importBOSArchive();
        botImport.setArchive(ImportBosArchiveIT.class.getResource("conflictingArchive/ConnectorInFormsForTests-1.0.bos"));

        botImport.newProject("repository 2").next().next().importArchive();

        botApplicationWorkbenchWindowEx.importBOSArchive();
        botImport.setArchive(ImportBosArchiveIT.class.getResource("ConnectorInFormsForTests-1.0.bos"));

        botImport.existingRepository(org.bonitasoft.studio.common.repository.Messages.defaultRepositoryName, false).cancel();
    }

    @Test
    public void should_import_bos_archive_using_drag_and_drop() throws Exception {

        SWTBotTree projectExplorerTree = new ProjectExplorerBot(bot).getProjectExplorerTree();
        DropTarget dropTarget = syncExec(new Result<DropTarget>() {

            @Override
            public DropTarget run() {
                Tree tree = projectExplorerTree.widget;
                return (DropTarget) tree.getData(DND.DROP_TARGET_KEY);
            }
        });

        Event dropEvent = createDNDEvent(projectExplorerTree.widget, projectExplorerTree.display, DND.Drop);
        String archive = new File(
                FileLocator.toFileURL(ImportBosArchiveIT.class.getResource("ConnectorInFormsForTests-1.0.bos")).getFile())
                        .getAbsolutePath();
        dropEvent.data = new String[] { archive };
        asyncExec(() -> dropTarget.notifyListeners(DND.Drop, dropEvent));
        new BotImportBOSDialog(bot).cancel();
    }

    private Event createDNDEvent(Widget widget, Display display, int type) {
        try {
            Class<?> clazz = Class.forName("org.eclipse.swt.dnd.DNDEvent");
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            Event dndEvent = (Event) constructor.newInstance();
            dndEvent.time = (int) System.currentTimeMillis();
            dndEvent.widget = widget;
            dndEvent.display = display;
            dndEvent.type = type;
            return dndEvent;
        } catch (Exception e) {
            throw new UnsupportedOperationException(e);
        }
    }

    @AfterClass
    public static void switchToDefaultRepo() {
        TeamRepositoryUtil.switchToRepository(org.bonitasoft.studio.common.repository.Messages.defaultRepositoryName,
                new NullProgressMonitor());
    }
}
