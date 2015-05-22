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
package org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.form;

import static org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.DefaultTabbedPropertyProvider.isFormDiagramEditor;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorReference;

public class FormDataTabbedPropertyProvider extends FormGeneralTabbedPropertySelectionProvider {

    @Override
    public String tabId(final EObject element) {
        return "Form.DataTab";
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.gmf.tools.tree.selection.ITabbedPropertySelectionProvider#appliesTo(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean appliesTo(final EObject element, final IEditorReference activeEditor) {
        return element instanceof Expression && isFormDiagramEditor(activeEditor);
    }

}
