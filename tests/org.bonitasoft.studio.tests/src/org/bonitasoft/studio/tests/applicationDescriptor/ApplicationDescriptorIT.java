/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.applicationDescriptor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.Application;
import org.bonitasoft.engine.business.application.xml.ApplicationMenuNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.core.BonitaPagesRegistry;
import org.bonitasoft.studio.la.application.handler.NewApplicationHandler;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.la.BotApplicationEditor;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class ApplicationDescriptorIT {

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);

    private static RepositoryAccessor repositoryAccessor;
    private static BOSEngineManager engineManager;
    private static APISession session;

    @BeforeClass
    public static void init() throws Exception {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        engineManager = BOSEngineManager.getInstance();
        session = engineManager.loginDefaultTenant(new NullProgressMonitor());
        LivingApplicationPlugin.getDefault().getPreferenceStore()
                .setValue(NewApplicationHandler.DO_NOT_SHOW_HELP_MESSAGE_DIALOG, true);
        BonitaPagesRegistry.getInstance().getPages();
    }

    @AfterClass
    public static void resetPreference() {
        if (session != null) {
            engineManager.logoutDefaultTenant(session);
        }
    }

    @Test
    public void should_create_and_build_applications_descriptors() throws Exception {
        final BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        BotApplicationEditor botApplicationEditor = workBenchBot.newApplicationContainer()
                .add()
                .withToken("tokenA")
                .withDisplayName("My App A")
                .withVersion("1.0.0")
                .create()
                .add()
                .withToken("tokenB")
                .withDisplayName("My App B")
                .withVersion("1.0.0")
                .create();

        botApplicationEditor.selectDescriptor("tokenB")
                .setToken("tokenB_bis")
                .setDisplayName("My app B bis")
                .setDescription("a description")
                .setProfile("User")
                .addOnePageMenu("SingleMenuA")
                .setMenuName("SingleMenuA", "SingleMenuARenamed")
                .setMenuPageName("SingleMenuARenamed", "pageA")
                .setMenuPageToken("SingleMenuARenamed", "tokenA");

        botApplicationEditor.selectDescriptor("tokenA")
                .addMultiPageMenu("topMenuA", "subMenuA")
                .addSubMenu("topMenuA", "subMenuB")
                .setSubMenuName("topMenuA", "subMenuA", "subMenuARenamed")
                .setSubMenuPageName("topMenuA", "subMenuARenamed", "pageB")
                .setSubMenuPageToken("topMenuA", "subMenuARenamed", "tokenB")
                .setSubMenuPageName("topMenuA", "subMenuB", "pageC")
                .setSubMenuPageToken("topMenuA", "subMenuB", "tokenC")
                .downSubMenu("topMenuA", "subMenuARenamed");

        botApplicationEditor.selectDescriptor("tokenB_bis")
                .addOrphanPage("pageD")
                .setOrphanPageToken("pageD", "tokenD");

        botApplicationEditor.save().rename("applicationTestIT");

        ApplicationFileStore applicationFile = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class)
                .getChild("applicationTestIT.xml", true);

        List<ApplicationNode> applications = applicationFile.getContent().getApplications();
        assertThat(applications).hasSize(2);
        assertThat(applications).extracting("token").containsExactly("tokenA", "tokenB_bis");
        ApplicationNode applicationNodeB = applications.stream().filter(app -> Objects.equals("tokenB_bis", app.getToken()))
                .findFirst().get();
        ApplicationNode applicationNodeA = applications.stream().filter(app -> Objects.equals("tokenA", app.getToken()))
                .findFirst().get();

        assertThat(applicationNodeB.getDisplayName()).isEqualTo("My app B bis");
        assertThat(applicationNodeB.getDescription()).isEqualTo("a description");
        assertThat(applicationNodeB.getProfile()).isEqualTo("User");
        assertThat(applicationNodeB.getApplicationMenus().get(0).getDisplayName()).isEqualTo("SingleMenuARenamed");
        assertThat(applicationNodeB.getApplicationMenus().get(0).getApplicationPage()).isEqualTo("tokenA");
        assertThat(applicationNodeB.getApplicationPages()).hasSize(2);
        assertThat(applicationNodeB.getApplicationPages().stream()
                .filter(page -> Objects.equals("pageA", page.getCustomPage())).findFirst().get().getToken())
                        .isEqualTo("tokenA");
        assertThat(applicationNodeB.getApplicationPages().stream()
                .filter(page -> Objects.equals("pageD", page.getCustomPage())).findFirst().get().getToken())
                        .isEqualTo("tokenD");

        List<ApplicationMenuNode> submenus = applicationNodeA.getApplicationMenus().get(0).getApplicationMenus();
        assertThat(submenus).hasSize(2);
        assertThat(submenus.stream().filter(menu -> Objects.equals("subMenuARenamed", menu.getDisplayName())).findFirst()
                .get().getApplicationPage()).isEqualTo("tokenB");
        assertThat(submenus.stream().filter(menu -> Objects.equals("subMenuB", menu.getDisplayName())).findFirst().get()
                .getApplicationPage()).isEqualTo("tokenC");
        assertThat(applicationNodeA.getApplicationPages()).hasSize(2);
        assertThat(applicationNodeA.getApplicationPages().stream()
                .filter(page -> Objects.equals("pageB", page.getCustomPage())).findFirst().get().getToken())
                        .isEqualTo("tokenB");
        assertThat(applicationNodeA.getApplicationPages().stream()
                .filter(page -> Objects.equals("pageC", page.getCustomPage())).findFirst().get().getToken())
                        .isEqualTo("tokenC");

    }

    @Test
    public void should_add_an_application_descriptor_from_an_other_one() throws Exception {
        final BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);
        ApplicationRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class);

        workBenchBot.newApplicationContainer()
                .save()
                .rename("ApplicationAddFromIntegrationTest")
                .add()
                .withToken("toDuplicate")
                .create()
                .save()
                .add()
                .addFromMode()
                .withNewToken("duplicated")
                .selectExistingApplication("toDuplicate ApplicationAddFromIntegrationTest.xml")
                .create()
                .save();

        ApplicationNodeContainer appNodeContainer = repositoryStore.getChild("ApplicationAddFromIntegrationTest.xml", false)
                .getContent();
        assertThat(appNodeContainer.getApplications()).hasSize(2);
        List<String> tokens = appNodeContainer.getApplications().stream().map(ApplicationNode::getToken)
                .collect(Collectors.toList());
        assertThat(tokens).containsExactly("toDuplicate", "duplicated");
    }

    @Test
    public void should_deploy_application_descriptor_using_menu() throws Exception {
        final BotApplicationWorkbenchWindow workBenchBot = new BotApplicationWorkbenchWindow(bot);

        workBenchBot.newApplicationContainer()
                .save()
                .rename("ApplicationToDeploy")
                .add()
                .addNewMode()
                .withToken("appToDeploy")
                .create()
                .save()
                .close();
        workBenchBot.deployApplicationFile()
                .select("ApplicationToDeploy.xml  ../apps/appToDeploy")
                .deploy();

        ApplicationAPI applicationAPI = engineManager.getApplicationAPI(session);
        final Stream<Application> deployedAppsStream = applicationAPI
                .searchApplications(new SearchOptionsBuilder(0, 10).done()).getResult().stream();
        assertThat(deployedAppsStream).extracting(Application::getToken).contains("appToDeploy");
    }
}
