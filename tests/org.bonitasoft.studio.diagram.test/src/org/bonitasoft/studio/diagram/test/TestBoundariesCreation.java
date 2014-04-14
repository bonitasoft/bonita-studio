/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.test;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.BoundarySignalEvent;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;

/**
 * @author Aurelien Pupier
 */
public class TestBoundariesCreation extends SWTBotGefTestCase {

    SWTBotGefEditor gmfEditor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        SWTBotTestUtil.createNewDiagram(bot);
        SWTBotEditor botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());
    }


    public void testCreateBoundaryErrorEventFromPalette(){
        testCreateBoundaryFromPalette(IntermediateErrorCatchEvent.class, Messages.CatchError_title);
    }

    public void testCreateBoundaryMessageEventFromPalette(){
        testCreateBoundaryFromPalette(BoundaryMessageEvent.class, Messages.CatchMessage_title);
    }

    public void testCreateBoundarySignalEventFromPalette(){
        testCreateBoundaryFromPalette(BoundarySignalEvent.class, Messages.CatchSignal_title);
    }

    public void testCreateBoundaryTimerEventFromPalette(){
        testCreateBoundaryFromPalette(BoundaryTimerEvent.class, Messages.Timer_title);
    }

    protected void testCreateBoundaryFromPalette(Class<?> clazz, String toolName){
        /*Activate the right tool*/
        gmfEditor.activateTool(toolName);
        /*And then click on the figure, in fact we try to click on the NameEditPart*/
        SWTBotGefEditPart editPartStep1 = gmfEditor.getEditPart("Step1");
        IGraphicalEditPart step1Part = (IGraphicalEditPart)editPartStep1.part();
        Point center = step1Part.getFigure().getBounds().getCenter();
        step1Part.getFigure().translateToAbsolute(center);
        gmfEditor.click(center.x, center.y);
        /*Check that the boundary is created*/
        boolean createWorks = false;
        for(SWTBotGefEditPart swtBotEditPart : editPartStep1.parent().children()){
            EditPart part = swtBotEditPart.part();
            if(part instanceof IGraphicalEditPart
                    && clazz.isInstance(((IGraphicalEditPart) part).resolveSemanticElement())){
                createWorks = true;
                break;
            }
        }
        assertTrue("Can't create a Boundary "+ toolName, createWorks);
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        gmfEditor.close();
    }

}
