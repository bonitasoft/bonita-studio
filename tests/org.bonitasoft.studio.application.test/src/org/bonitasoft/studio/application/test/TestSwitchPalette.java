/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.bonitasoft.studio.application.actions.SwitchPaletteMode;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

/**
 * @author Aurelien Pupier
 *
 */
public class TestSwitchPalette extends TestCase {

    @Test
    public void testSwitchPalette() throws Exception {
        NewDiagramCommandHandler newp = new NewDiagramCommandHandler();
        newp.execute(null);
        List<?> children = ((ProcessDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getPaletteRoot().getChildren();
        children = ((PaletteContainer)children.get(1)).getChildren();
        for (Object object : children) {
            assertTrue("some items were hidden at startup:"+object,((PaletteEntry)object).isVisible());
        }
        SwitchPaletteMode switchPaletteMode = new SwitchPaletteMode();
        Map<String, String> map = new HashMap<String, String>();
        map.put("org.bonitasoft.studio.application.switch.advanced","simple" );
        ExecutionEvent event = new ExecutionEvent(null,map ,null,null);
        switchPaletteMode.execute(event);
        children = ((ProcessDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getPaletteRoot().getChildren();
        children = ((PaletteContainer)children.get(1)).getChildren();
        //step
        //text annotation
        //lane
        //pool
        //event
        //gate
        //transition
        for (Object object : children) {
            PaletteEntry child = (PaletteEntry) object;
            String childLabel = child.getLabel();
            if(childLabel.equals("Service task")||
                    childLabel.equals("Text annotation")||
                    childLabel.equals("Lane")||
                    childLabel.equals("Pool")||
                    childLabel.equals("Event")||
                    childLabel.equals("Paralell gateway")||
                    childLabel.equals("Transition")){
                assertTrue("some items were mising from simple palette: "+child,child.isVisible());
            }else{
                assertTrue("some items were not hidden from simple palette: "+child,!child.isVisible());
            }
        }
        
        // back to the icone view palette
        map.put("org.bonitasoft.studio.application.switch.advanced","icones" );
        event = new ExecutionEvent(null,map ,null,null);
        switchPaletteMode.execute(event);

    }

}
