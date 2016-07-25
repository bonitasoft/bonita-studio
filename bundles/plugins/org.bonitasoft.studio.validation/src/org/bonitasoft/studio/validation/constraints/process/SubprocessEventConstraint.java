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

import java.util.Iterator;

import org.bonitasoft.studio.model.process.StartErrorEvent;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Baptiste Mesta
 */
public class SubprocessEventConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final SubProcessEvent eObj = (SubProcessEvent) ctx.getTarget();
        int nbStartEvent = 0;
        /* check that it starts with an unique start event */
        final Iterator<EObject> it = eObj.eAllContents();
        while (it.hasNext()) {
            final EObject iterable_element = it.next();
            if (iterable_element instanceof StartTimerEvent
                    || iterable_element instanceof StartSignalEvent
                    || iterable_element instanceof StartMessageEvent
                    || iterable_element instanceof StartErrorEvent) {
                if (iterable_element.eContainer().equals(eObj)) {
                    nbStartEvent++;
                }
            }
        }

        if (nbStartEvent == 0) {
            return ctx.createFailureStatus(new Object[] { Messages.Validation_SubProcMustHaveStartEvent });
        } else if (nbStartEvent > 1) {
            return ctx.createFailureStatus(new Object[] { Messages.Validation_SubProcMustHaveOnlyOneStartEvent });
        }

        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraint.subProcessEvent";
    }

}
