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
package org.bonitasoft.studio.tests.bug;

import static org.junit.Assert.assertFalse;

import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramActionBarContributor;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Test;


public class TestBugs {

    private DiagramFileStore newDiagram;

    @Test
    public void testBug1644() throws Exception {
        newDiagram = new NewDiagramCommandHandler().newDiagram();
        newDiagram.open();

        final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        final ProcessDiagramActionBarContributor actionBarContributor = (ProcessDiagramActionBarContributor) editor
                .getEditorSite().getActionBarContributor();
        final IContributionItem[] items = actionBarContributor.getActionBars().getToolBarManager().getItems();
        for (final IContributionItem iContributionItem : items) {
            assertFalse("zoom contribution is still present", iContributionItem.getId().equals("zoomContributionItem"));
        }
    }
    
    @After
    public void closeEditor() throws Exception {
        if(newDiagram != null) {
            newDiagram.close();
        }
    }

}
