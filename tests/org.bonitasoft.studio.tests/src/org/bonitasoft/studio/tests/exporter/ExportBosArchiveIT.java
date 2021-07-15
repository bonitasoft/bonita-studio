package org.bonitasoft.studio.tests.exporter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateFormOperation;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.exporter.Messages;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.bonitasoft.studio.tests.util.ProjectUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ExportBosArchiveIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule botRule = new SWTGefBotRule(bot);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    @After
    public void cleanProject() throws CoreException {
        ProjectUtil.cleanProject();
    }

    @Test
    public void should_export_pom_and_local_store() throws Exception {
        var workbenchBot = new BotApplicationWorkbenchWindow(bot);
        workbenchBot.importBOSArchive()
                .setArchive(ExportBosArchiveIT.class.getResource("/project-with-extensions.bos"))
                .currentRepository()
                .next()
                .next()
                .importArchive();

        var exportDialog = workbenchBot.export();

        assertThat(bot.tree().getAllItems()).allSatisfy(SWTBotTreeItem::isChecked);

        var bosFile = temporaryFolder.newFile("exportWithExtensions.bos");
        exportDialog
                .setDestinationPath(bosFile.getAbsolutePath())
                .finish();

        assertThat(bosFile).exists();

        RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        try (var zipFile = new ZipFile(bosFile)) {
            assertThat(
                    zipFile.getEntry(String.format("%s/pom.xml", repositoryAccessor.getCurrentRepository().getName())))
                            .isNotNull();
            assertThat(zipFile.getEntry(
                    String.format("%s/.store/org/bonitasoft/theme/my-darkly-theme/1.0.0/my-darkly-theme-1.0.0.zip",
                            repositoryAccessor.getCurrentRepository().getName()))).isNotNull();
            assertThat(zipFile.getEntry(
                    String.format("%s/.store/org/bonitasoft/restapi/extension/task-candidates-rest-api/1.0.1-SNAPSHOT/task-candidates-rest-api-1.0.1-SNAPSHOT.zip",
                            repositoryAccessor.getCurrentRepository().getName()))).isNotNull();
        }
    }

    @Test
    public void should_not_export_ui_designer_metadata() throws Exception {
        RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        final WebPageRepositoryStore repositoryStore = repositoryAccessor.getCurrentRepository()
                .getRepositoryStore(WebPageRepositoryStore.class);

        final CreateFormOperation createFormOperation = new CreateFormOperation(
                new PageDesignerURLFactory(InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID)),
                repositoryAccessor);
        var progressService = PlatformUI.getWorkbench().getProgressService();
        Display.getDefault().syncExec(() -> {
            try {
                progressService.run(true, false, createFormOperation);
            } catch (InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        });

        final String pageName = createFormOperation.getNewPageName();
        final String pageId = createFormOperation.getNewArtifactId();

        assertThat(pageName).isNotEmpty();
        assertThat(pageId).isNotEmpty();
        assertThat(repositoryStore.getChild(pageId, true)).isNotNull();

        final File bosFile = temporaryFolder.newFile("exportWithoutMetadata.bos");
        new BotApplicationWorkbenchWindow(bot).export().selectAll().setDestinationPath(bosFile.getAbsolutePath())
                .finish();

        assertThat(bosFile).exists();
        try (ZipFile zipFile = new ZipFile(bosFile);) {
            assertThat(zipFile.getEntry(String.format("%s/web_page/%s/%s.json",
                    repositoryAccessor.getCurrentRepository().getName(), pageId, pageId))).isNotNull();
            assertThat(zipFile.getEntry(String.format("%s/web_page/.metadata",
                    repositoryAccessor.getCurrentRepository().getName()))).isNull();
        }
    }

    @Test
    public void testExportDiagramBOSArchive() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);

        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        final List<SWTBotGefEditPart> runnableEPs = gmfEditor.editParts(new BaseMatcher<EditPart>() {

            @Override
            public boolean matches(Object item) {
                return item instanceof PoolEditPart;
            }

            @Override
            public void describeTo(Description description) {

            }
        });
        Assert.assertFalse(runnableEPs.isEmpty());
        gmfEditor.select(runnableEPs.get(0));

        bot.saveAllEditors();

        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_EXPORT_TOOLITEM).click();
        bot.waitUntil(Conditions.shellIsActive(Messages.ExportButtonLabel));

        final SWTBotCombo destComboBot = bot
                .comboBoxWithLabel(org.bonitasoft.studio.common.repository.Messages.destinationPath + " *");
        final String defaultPath = destComboBot.getText();
        assertTrue("Invalid default file name", defaultPath.endsWith(".bos"));
        final File destFile = new File(defaultPath);
        if (destFile.exists()) {
            destFile.delete();
        }
        try {
            final SWTBotButton finishButton = bot.button(IDialogConstants.FINISH_LABEL);
            bot.waitUntil(Conditions.widgetIsEnabled(finishButton));
            finishButton.click();
            bot.waitUntil(Conditions.shellIsActive("Export result"));
            bot.button(IDialogConstants.OK_LABEL).click();
            assertTrue("Destination file doesn't exists", destFile.exists());
            assertTrue("Destination file is empty", destFile.length() > 0);
        } finally {
            destFile.delete();
        }
    }

}
