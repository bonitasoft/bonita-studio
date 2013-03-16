/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * @author Baptiste Mesta
 * 
 */
public class UniqueWidgetIdConstraint extends AbstractLiveValidationMarkerConstraint {


    @Override
    protected IStatus performLiveValidation(IValidationContext ctx) {
        return ctx.createSuccessStatus();
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext ctx) {
        EObject eObj = ctx.getTarget();
        if (eObj instanceof Widget && !(eObj.eContainer() instanceof Expression)) {
            Widget w = (Widget) eObj;
            String name = w.getName();
            Element pageFlow = ModelHelper.getPageFlow(w);
            Form form = ModelHelper.getForm(w);
            if(pageFlow != null){
                List<String> existingNames = new ArrayList<String>();
                for (Widget w1 : ModelHelper.getAllWidgetInsidePageFlow((PageFlow) pageFlow)) {
                    existingNames.add(w1.getName());
                }

                if(Collections.frequency(existingNames,name)>1){
                    return ctx.createFailureStatus(new Object[] { Messages.Validation_Widget_SameName + ": " + name });
                }
            }else{
                for (Widget w1 : ModelHelper.getAllWidgetInsideForm(form)) {//in a form template
                    if (!w1.equals(w) && w1.getName().equals(name)) {
                        return ctx.createFailureStatus(new Object[] { Messages.Validation_Widget_SameName + ": " + name });
                    }
                }
            }

        }
        return ctx.createSuccessStatus();
    }


    @Override
    protected String getMarkerType(DiagramEditor editor) {
        return ProcessMarkerNavigationProvider.MARKER_TYPE;
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraint.Widget";
    }

}
