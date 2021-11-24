/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class TestMoveBetweenLane {

    private SWTGefBot bot = new SWTGefBot();

    @Test
    public void testMoveElementsBetweenLanes() {
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        final SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

        gmfEditor.activateTool("Human");
        gmfEditor.click(200, 150);

        // Create lane
        gmfEditor.activateTool("Lane");
        gmfEditor.click(300, 50);

        final SWTBotGefEditPart lane = gmfEditor.getEditPart("Employee lane");
        lane.parent().select().resize(PositionConstants.NORTH, 1000, 150);

        // compute target dest
        final SWTBotGefEditPart lane1 = gmfEditor.getEditPart("Lane1");

        // move
        SWTBotGefEditPart step = gmfEditor.getEditPart("Step2").parent();
        step.select();

        IFigure figure = ((GraphicalEditPart) step.part()).getFigure();
        Rectangle dest = figure.getBounds().getCopy();
        figure.translateToAbsolute(dest);

        gmfEditor.drag(step, dest.x, dest.y + 200);

        step = gmfEditor.getEditPart("Step2").parent();
        step.select();
        figure = ((GraphicalEditPart) step.part()).getFigure();
        Rectangle targetdest = figure.getBounds().getCopy();
        figure.translateToAbsolute(targetdest);

        assertTrue("Move has failed", !targetdest.equals(dest));

        bot.saveAllEditors();

        bot.waitUntil(new ICondition() {

            @Override
            public boolean test() throws Exception {
                return getPartRecursively(lane1.parent(), "Step2") != null;
            }

            @Override
            public void init(SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "Step2 is not in Lane2 (unable to move the step)";
            }
        });
    }

    @After
    public void closeEditors() throws Exception {
        bot.closeAllEditors();
    }

    public SWTBotGefEditPart getPartRecursively(SWTBotGefEditPart from, String label) {
        for (SWTBotGefEditPart child : from.children()) {
            Element model = (Element) ((IGraphicalEditPart) child.part()).resolveSemanticElement();
            if (model.getName().equals(label)) {
                return child;
            }
        }
        SWTBotGefEditPart res = null;
        for (SWTBotGefEditPart child : from.children()) {
            res = getPartRecursively(child, label);
            if (res != null) {
                return res;
            }
        }
        return res;
    }
}
