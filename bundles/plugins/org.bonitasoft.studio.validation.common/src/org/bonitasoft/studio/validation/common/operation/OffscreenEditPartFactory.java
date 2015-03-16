/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.common.operation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class OffscreenEditPartFactory {

    private final List<Shell> toDispose = new ArrayList<Shell>();
    private final org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory factory;

    public OffscreenEditPartFactory(final org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory factory) {
        this.factory = factory;
    }

    public DiagramEditPart createOffscreenDiagramEditPart(final Diagram d) {
        final EObject element = d.getElement();
        if (element != null) {
            final OffscreenDiagramEditPartRunnable runnable = new OffscreenDiagramEditPartRunnable(d, factory);
            if (inUIThread()) {
                runnable.run();
            } else {
                runInUI(runnable);
            }

            toDispose.add(runnable.getDisposable());
            return runnable.getDiagramEditPart();
        }
        return null;
    }

    protected void runInUI(final Runnable runnable) {
        Display.getDefault().syncExec(runnable);
    }

    protected boolean inUIThread() {
        return Thread.currentThread() == Display.getDefault().getThread();
    }

    public void dispose() {
        for (final Shell s : toDispose) {
            if (inUIThread()) {
                s.dispose();
            } else {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        s.dispose();
                    }
                });
            }

        }
        toDispose.clear();
    }
}
