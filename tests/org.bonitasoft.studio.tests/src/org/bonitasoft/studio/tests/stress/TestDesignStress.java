/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.stress;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.ui.PlatformUI;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestDesignStress extends TestCase {

    //private static final int PROCESS_COUNT = 100; // OUT OF MEMORY : More than 130 editors open... 
    private static final int PROCESS_COUNT = 100;

    public void testDesignStress() throws Exception {
        //fail("Diagram stress test is currently KO on Linux");
        System.gc();
        final DiagramRepositoryStore drs = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final int start = drs.getChildren().size();
        //create new proces to have 50 processes open at the end
        for (int minor = 0; minor + start < PROCESS_COUNT; minor++) {
            new NewDiagramCommandHandler().newDiagram().open();
        }
        System.gc();
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);

        for (final DiagramFileStore p : drs.getChildren()) {
            p.open();
        }
    }
}
