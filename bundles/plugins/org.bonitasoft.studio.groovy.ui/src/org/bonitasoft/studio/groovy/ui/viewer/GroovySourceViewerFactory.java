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
package org.bonitasoft.studio.groovy.ui.viewer;

import java.util.Objects;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.Workbench;

/**
 * @author Romain Bioteau
 */
public class GroovySourceViewerFactory {

    public GroovyViewer createSourceViewer(final Composite container) {
        final IEclipseContext context = (IEclipseContext) container.getShell().getData("org.eclipse.e4.ui.shellContext");
        if (context == null) {
            configureContext(container.getShell());
        }
        return new GroovyViewer(container);
    }

    public GroovyViewer createSourceViewer(final Composite container, final BonitaGroovyEditor editor) {
        final IEclipseContext context = (IEclipseContext) container.getShell().getData("org.eclipse.e4.ui.shellContext");
        if (context == null) {
            configureContext(container.getShell());
        }
        return new GroovyViewer(container, null, editor, true);
    }

    public GroovyViewer createSourceViewer(final Composite container, final boolean isPageFlowContext) {
        final IEclipseContext context = (IEclipseContext) container.getShell().getData("org.eclipse.e4.ui.shellContext");
        if (context == null) {
            configureContext(container.getShell());
        }
        return new GroovyViewer(container, isPageFlowContext, true);
    }

    private void configureContext(Shell shell) {
        final IEclipseContext e4Context = ((Workbench) PlatformUI.getWorkbench()).getContext();
        while (!Objects.equals(e4Context.getActiveLeaf(), e4Context)) {
            e4Context.getActiveLeaf().deactivate();
        }
        final IEclipseContext expressionDialogContext = e4Context.createChild("expressionDialogContext");
        expressionDialogContext.activate();
        shell.setData("org.eclipse.e4.ui.shellContext", expressionDialogContext);
    }

}
