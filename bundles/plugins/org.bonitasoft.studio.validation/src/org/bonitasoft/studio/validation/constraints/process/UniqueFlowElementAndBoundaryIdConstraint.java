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
package org.bonitasoft.studio.validation.constraints.process;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Baptiste Mesta
 */
public class UniqueFlowElementAndBoundaryIdConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof FlowElement || eObj instanceof BoundaryEvent) {
            //check that there is no other elements with the same name
            final Container parentProcess = ModelHelper.getParentProcess(eObj);
            if (parentProcess != null) {
                final Element el = (Element) eObj;
                final List<Element> elements = new ArrayList<Element>();
                final List<FlowElement> flowElements = ModelHelper.getFlowElements(parentProcess, true);
                elements.addAll(flowElements);
                for (final FlowElement flowElement : flowElements) {
                    if (flowElement instanceof Activity) {
                        elements.addAll(((Activity) flowElement).getBoundaryIntermediateEvents());
                    }
                }
                final String elementName = el.getName();
                for (final Element e : elements) {
                    if (!e.equals(el) && e.getName().equalsIgnoreCase(elementName)) {
                        return ctx.createFailureStatus(new Object[] { Messages.Validation_Element_SameName + ": " + elementName });
                    }
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.uniqueid";
    }

}
