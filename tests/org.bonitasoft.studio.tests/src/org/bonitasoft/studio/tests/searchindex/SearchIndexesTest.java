/*******************************************************************************
 * Copyright (C) 2015 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.searchindex;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.junit.Assert.*;

import java.util.List;

import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SearchIndexesTest {

    private static final String INDEX_SECTION_NAME = "Search keys";

    private final SWTGefBot bot = new SWTGefBot();

    @Rule
    public SWTGefBotRule rule = new SWTGefBotRule(bot);
    
    @Before
    public void cleanRepo() throws Exception {
        DiagramRepositoryStore repositoryStore = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        repositoryStore.getChildren().stream().forEach(DiagramFileStore::delete);
    }

    @Test
    public void testSearchIndex() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, INDEX_SECTION_NAME);
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).setText("index1");
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 1).setText("value1");
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 2).setText("index2");
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 3).setText("value2");
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 4).setText("index3");
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 5).setText("value3");
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 6).setText("index4");
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 7).setText("value4");
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 8).setText("index5");
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 9).setText("value5");
        bot.sleep(500);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        SWTBotTestUtil.selectEventOnProcess(bot, gmfEditor, "Step1");
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION).setFocus();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Operations");
        bot.button("Add").click();
        SWTBotTestUtil.selectExpressionProposal(bot, "index1", String.class.getName(), 0);
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).setText("myNewValue");
        
        // wait for delayed observable to update the model
        bot.sleep(500);
        
        bot.saveAllEditors();
        
        final MainProcess mainProcess = (MainProcess) ((IGraphicalEditPart) gmfEditor.mainEditPart().part())
                .resolveSemanticElement();
        final List<AbstractProcess> processes = ModelHelper.getAllProcesses(mainProcess);
        assertNotNull("at least one pool should be on diagram ", processes.size() == 1);
        Pool pool = (Pool) processes.get(0); 
        for (int i = 0; i < 5; i++) {
            final SearchIndex searchIndex = pool.getSearchIndexes().get(i);
            assertEquals("index" + (i + 1), searchIndex.getName().getContent());
            assertEquals("value" + (i + 1), searchIndex.getValue().getContent());
        }
        IStatus status = SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
        assertThat(status).isOK();
    }

}
