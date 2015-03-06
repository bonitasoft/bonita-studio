/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.conditions;

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.diagram.BotProcessDiagramPerspective;
import org.bonitasoft.studio.swtbot.framework.diagram.general.BotGeneralDiagramPropertiesView;
import org.bonitasoft.studio.swtbot.framework.diagram.general.data.BotAddDataWizardPage;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.expression.BotConditionExpressionEditor;
import org.bonitasoft.studio.swtbot.framework.expression.BotExpressionEditorDialog;
import org.bonitasoft.studio.validation.test.ValidationTestBase;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.validation.internal.EMFModelValidationStatusCodes;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SWTBOTTestConditions extends SWTBotGefTestCase{

	@Before
	public void setup(){
		bot.saveAllEditors();
		bot.closeAllEditors();
	}

	@After
	public void tearDown(){
		bot.saveAllEditors();
		bot.closeAllEditors();
	}

	@Test
	public void testResolveDependeciesBeforeSaving(){
		final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
		final BotProcessDiagramPerspective diagramPerspective = botApplicationWorkbenchWindow.createNewDiagram();
		String poolName = "conditionTestPool";
		diagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectPoolTab().editPool(poolName, "1.0");

		//add a data on pool
		final BotAddDataWizardPage addDataBot = diagramPerspective.getDiagramPropertiesPart().selectGeneralTab().selectDataTab().addData();
		addDataBot.setName("myData");
		addDataBot.finish();
		//add a new task
		final BotGefProcessDiagramEditor diagramBot = diagramPerspective.activeProcessDiagramEditor();
		diagramBot.addElement("Step1","Human", PositionConstants.EAST);
		diagramBot.addSequenceFlowBetween("Step1", "Step2", PositionConstants.EAST);
		diagramBot.selectFlowBetween("Step1", "Step2");
		//add a condition
		BotGeneralDiagramPropertiesView generalViewBot = diagramPerspective.getDiagramPropertiesPart().selectGeneralTab();
		bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
		BotExpressionEditorDialog expressionEditor = new BotExpressionEditorDialog(bot);
		BotConditionExpressionEditor botConditionEditor = expressionEditor.selectConditionExpressionType();
		botConditionEditor.setValue("myData < \"value\"");
		botConditionEditor.ok();
		DiagramRepositoryStore store = RepositoryManager
				.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
		AbstractProcess process=store.findProcess(poolName, "1.0");
		assertNotNull(process);
		ValidationTestBase validation = new ValidationTestBase();
		IBatchValidator batchValidator = ModelValidationService.getInstance().newValidator(
				EvaluationMode.BATCH);
		batchValidator.setIncludeLiveConstraints(true);
		batchValidator.setReportSuccesses(true);
		IStatus[] statuses = validation.getStatuses(batchValidator.validate(process));
		for (IStatus status:statuses){
			assertTrue(status.isOK()||status.matches(IStatus.INFO)||status.matches(IStatus.WARNING));
		}
	}


}
