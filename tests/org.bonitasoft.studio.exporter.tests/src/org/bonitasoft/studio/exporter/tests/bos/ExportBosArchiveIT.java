package org.bonitasoft.studio.exporter.tests.bos;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateFormOperation;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ExportBosArchiveIT {

    private SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule SWTswtGefBotRule = new SWTGefBotRule(bot);

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void should_not_export_ui_designer_metadata() throws Exception {
        WebPageRepositoryStore repositoryStore = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(WebPageRepositoryStore.class);

        CreateFormOperation createFormOperation = new CreateFormOperation(
                new PageDesignerURLFactory(InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID)));
        createFormOperation.run(Repository.NULL_PROGRESS_MONITOR);
        String pageName = createFormOperation.getNewPageName();
        String pageId = createFormOperation.getNewPageId();

        assertThat(pageName).isNotEmpty();
        assertThat(pageId).isNotEmpty();
        assertThat(repositoryStore.getChild(pageId)).isNotNull();

        File bosFile = temporaryFolder.newFile("exportWithoutMetadat.bos");
        new BotApplicationWorkbenchWindow(bot).export().selectAll().setDestinationPath(bosFile.getAbsolutePath()).finish();

        assertThat(bosFile).exists();
        try (ZipFile zipFile = new ZipFile(bosFile);) {
            assertThat(zipFile.getEntry(String.format("default/web_page/%s/%s.json", pageId, pageId))).isNotNull();
            assertThat(zipFile.getEntry("default/web_page/.metadata")).isNull();
        }
    }

    @After
    public void tearDown() throws Exception {
        WebPageRepositoryStore repositoryStore = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(WebPageRepositoryStore.class);
        for (WebPageFileStore fs : repositoryStore.getChildren()) {
            fs.delete();
        }
    }

}
