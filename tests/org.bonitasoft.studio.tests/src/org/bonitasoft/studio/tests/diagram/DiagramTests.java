/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.diagram;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.diagram.custom.editPolicies.ActivitySwitchEditPolicy;
import org.bonitasoft.studio.diagram.custom.figures.SlideMenuBarFigure;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.bonitasoft.studio.swtbot.framework.application.BotApplicationWorkbenchWindow;
import org.bonitasoft.studio.swtbot.framework.conditions.AssertionCondition;
import org.bonitasoft.studio.swtbot.framework.draw.BotGefProcessDiagramEditor;
import org.bonitasoft.studio.swtbot.framework.rule.SWTGefBotRule;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEclipseEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class DiagramTests {

    private final SWTGefBot bot = new SWTGefBot();
    
   @Rule
   public SWTGefBotRule rule = new SWTGefBotRule(bot);
    
    @Test
    public void testDiagramTest() {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        gmfEditor.activateTool(Messages.Step_title);
        gmfEditor.click(200, 200);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR)));
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).click();
        final IGraphicalEditPart part = (IGraphicalEditPart) gmfEditor.mainEditPart().part();
        final MainProcess model = (MainProcess) part.resolveSemanticElement();
        final Pool pool = (Pool) model.getElements().get(0);
        final Lane lane = (Lane) pool.getElements().get(0);
        Assert.assertEquals("Lane should contain 3 nodes", 3, lane.getElements().size());
    }

    @Test
    public void testConvert() throws Exception {
        SWTBotTestUtil.createNewDiagram(bot);
        final SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
        final SWTBotGefEditPart part = getPartRecursively(gmfEditor.rootEditPart(), "Step1");
        part.select();
        IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) part.part();
        final ActivitySwitchEditPolicy convertPolicy = (ActivitySwitchEditPolicy) graphicalEditPart
                .getEditPolicy(ActivitySwitchEditPolicy.SWITCH_TYPE_ROLE);
        final Class<?> beforeClass = graphicalEditPart.resolveSemanticElement().getClass();
        final SlideMenuBarFigure toolbarFigure = convertPolicy.getToolbarFigure();
        final int x = toolbarFigure.getBounds().x + toolbarFigure.getBounds().width / 2;
        int y = toolbarFigure.getBounds().y + toolbarFigure.getBounds().height / 2;
        gmfEditor.click(x, y);
        y += toolbarFigure.getBounds().height; // move cursor down to first sub item
        gmfEditor.click(x, y);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR)));
        bot.toolbarButtonWithId(SWTBotConstants.SWTBOT_ID_SAVE_EDITOR).click();
        SWTBotGefEditPart editPart = gmfEditor.getEditPart("Step1");
        graphicalEditPart = (IGraphicalEditPart) editPart.part();
        final Activity newModelElement = (Activity) graphicalEditPart.resolveSemanticElement();
        Assert.assertEquals("Step1", newModelElement.getName());
        Assert.assertNotSame(beforeClass, newModelElement.getClass());
    }

    @Test
    public void should_undo_redo_after_lane_creation() throws Exception {
        final BotApplicationWorkbenchWindow botApplicationWorkbenchWindow = new BotApplicationWorkbenchWindow(bot);
        final BotGefProcessDiagramEditor activeProcessDiagramEditor = botApplicationWorkbenchWindow.createNewDiagram()
                .activeProcessDiagramEditor();
        final EObject mainProcess = activeProcessDiagramEditor.selectDiagram().getSelectedSemanticElement();
        final List<Pool> allPools = ModelHelper.getAllElementOfTypeIn(mainProcess, Pool.class);
        final Pool pool = allPools.get(0);
        activeProcessDiagramEditor.addLaneOnPool(pool.getName());
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(pool.getElements()).hasSize(2);
            }
        });
        botApplicationWorkbenchWindow.editMenu().undo();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(pool.getElements()).hasSize(1);
            }
        });
      
        botApplicationWorkbenchWindow.editMenu().redo();
        bot.waitUntil(new AssertionCondition() {

            @Override
            protected void makeAssert() throws Exception {
                assertThat(pool.getElements()).hasSize(2);
            }
        });
    }

    public SWTBotGefEditPart getPartRecursively(final SWTBotGefEditPart from, final String label) {
        for (final SWTBotGefEditPart child : from.children()) {
            final Element model = (Element) ((IGraphicalEditPart) child.part()).resolveSemanticElement();
            if (model.getName().equals(label)) {
                return child;
            }
        }
        SWTBotGefEditPart res = null;
        for (final SWTBotGefEditPart child : from.children()) {
            res = getPartRecursively(child, label);
            if (res != null) {
                return res;
            }
        }
        return res;
    }
}
