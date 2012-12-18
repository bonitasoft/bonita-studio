package org.bonitasoft.studio.connector.wizard.java.test;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.wizards.java.SelectMethodDialog;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;


/**
 * @author Aurelien Pupier
 */
public class TestSelectJavaMethodDialog extends SWTBotEclipseTestCase{

	
	
	@Test
	public void testSelectJavaMethodDialog(){		
		Display.getDefault().asyncExec(new Runnable() {
			
			public void run() {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				SelectMethodDialog smd = new SelectMethodDialog(shell, "java.util.LinkedList");
				smd.open();
				
			}
		});
		bot.waitUntil(Conditions.shellIsActive("Java"));
		final SWTBotShell newShell = bot.activeShell();
		SWTBot dialogBot = newShell.bot();
		//39 if it is with java 1.5, will be 50 with 1.6
		SWTBotTreeItem[] items =  dialogBot.tree().getAllItems() ;
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				newShell.widget.close();
			}

		}) ;
		assertEquals("It didn't find the right number methods of java.util.LinkedList: "+items.toString(), items.length, 50);
		//TODO: double click
	}
	
	/**
	 * Test bug 3367
	 */
	@Test
	public void testJavaConnectorEvaluation() throws Exception {
		fail();
//		Display.getDefault().syncExec(new Runnable() {
//			public void run() {
//				try {
// 					URL jarPath = FileLocator.toFileURL(getClass().getResource("dummy.jar"));
//					File jarFile = new File(jarPath.getFile());
//					FileInputStream fis = new FileInputStream(jarFile);
//					JarRepository.getInstance().importArtifact(jarFile.getName(), fis, new NullProgressMonitor());
//					fis.close();
//				} catch (Exception ex) {
//					fail(ex.getMessage());
//				}
//			}
//		});
//		ConnectorDefRepositoryStore cdrs = (ConnectorDefRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
//		cdrs.removeAllArtifacts();
//		bot.menu("Connectors").menu("Test a Connector").click();
//		bot.tree().select("Connectors");
//		bot.button("Next >").click();
//		bot.tree().expandNode("Java");
//		SWTBotTreeItem node = bot.tree().getTreeItem("Java").getItems()[0] ;
//		bot.tree().select(node);
//		bot.button("Next >").click();
//		bot.button("Browse...").click();
//		bot.text().setText(DummyClass.class.getName());
//		bot.waitUntil(new ICondition() {
//			
//			public boolean test() throws Exception {
//				return 	bot.table().rowCount() > 0 ;
//			}
//			
//			public void init(SWTBot bot) {
//
//			}
//			
//			public String getFailureMessage() {
//				return "No Class Found";
//			}
//		}) ;
//	
//		bot.table().getTableItem(0).select() ;
//		bot.button(1).click() ;
//		bot.tree().getTreeItem("createFile()").doubleClick();
//		bot.button("Evaluate").click();
//		bot.waitUntil(new ICondition() {
//			public boolean test() throws Exception {
//				return DummyClass.getFile().exists();
//			}
//			
//			public void init(SWTBot bot) {
//			}
//			
//			public String getFailureMessage() {
//				return "File has not been created";
//			}
//		});
//		bot.button().click() ;
//		bot.button("Close").click() ;
	}
	
}

