/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.filters;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormField;
import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

public class FormAndFormFieldFilter implements IFilter {

    public boolean select(Object object) {
        if (object instanceof IGraphicalEditPart) {
            IGraphicalEditPart editPart = (IGraphicalEditPart) object;
            Object model = editPart.resolveSemanticElement();
            if ((model instanceof FormField || model instanceof Form) && !(model instanceof HiddenWidget)) {
                Form form;
                if (model instanceof Form) {
                    form = (Form) model;
                } else {
                    form = ModelHelper.getForm((Widget) model);
                }
                return !(form instanceof ViewForm);
            } else {
                return false;
            }
        }
        return false;
    }

}
