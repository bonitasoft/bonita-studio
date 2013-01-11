package org.bonitasoft.studio.properties.test;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;

import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SearchIndexesTest extends SWTBotGefTestCase{

	private final String indexSection = "Index";
	
	@Test
	public void testSearchIndex() throws ExecutionException{
		SWTBotTestUtil.createNewDiagram(bot);
		 bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
	     bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();
	     SWTBotTestUtil.selectTabbedPropertyView(bot, indexSection);
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
	     SWTBotEditor botEditor = bot.activeEditor();
	     SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
	     SWTBotTestUtil.selectEventOnProcess(bot, gmfEditor, "Step1");
	     SWTBotTestUtil.selectTabbedPropertyView(bot, "Operations");
	     bot.button("Add").click();
	     bot.comboBox().setSelection("index1 ("+String.class.getName()+") -- SEARCH_INDEX_TYPE");
	     bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT).setText("myNewValue");
	     MainProcess mainProcess= (MainProcess)((IGraphicalEditPart)gmfEditor.mainEditPart().part()).resolveSemanticElement();
	     List<AbstractProcess> processes = ModelHelper.getAllProcesses(mainProcess);
	     Pool pool = null;
	     for (AbstractProcess process :processes){
	    	 if (process.getName().contains("Pool")){
	    		 pool = (Pool)process;
	    	 }
	     }
	     assertNotNull("at least one pool should be on diagram ",pool);
	     for (int i=0;i<5;i++){
	    	 SearchIndex searchIndex = pool.getSearchIndexes().get(i);
	    	 assertEquals("index"+(i+1),searchIndex.getName().getContent());
	    	 assertEquals("value"+(i+1),searchIndex.getValue().getContent());
	     }
	     SWTBotTestUtil.selectAndRunFirstPoolFound(bot);
	     
	}
	

}
