/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.operation;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Shell;


/**
 * @author Romain Bioteau
 *
 */
public class OffscreenDiagramEditPartRunnable implements Runnable {

    private DiagramEditPart diagramEp;
    private final Diagram diagram;
    private Shell shell;

    public OffscreenDiagramEditPartRunnable(final Diagram diagram) {
        this.diagram = diagram;
    }

    @Override
    public void run() {
        shell = new Shell();
        if (TransactionUtil.getEditingDomain(diagram) == null) {
            BonitaStudioLog.log("The diagram doesn't have any Editing domain, we can't create a DiagramEditPart: " + diagram.getName());
            return;
        }

        diagramEp = org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, shell);
    }

    public DiagramEditPart getDiagramEditPart() {
        return diagramEp;
    }

    public Shell getDisposable() {
        return shell;
    }

}
