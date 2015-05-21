/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process;

import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.ITabbedPropertySelectionProvider;
import org.eclipse.ui.IEditorReference;

public abstract class ApplicationViewTabbedPropertySelectionProvider implements ITabbedPropertySelectionProvider {

    @Override
    public String viewId() {
        return "org.bonitasoft.studio.views.properties.application";
    }

    protected boolean isProcessDiagramEditor(final IEditorReference activeEditor) {
        return "org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorID".equals(activeEditor.getId()) ||
                "org.bonitasoft.studio.model.process.diagram.custom.ex.part.ProcessDiagramEditorExID".equals(activeEditor.getId());
    }
}
