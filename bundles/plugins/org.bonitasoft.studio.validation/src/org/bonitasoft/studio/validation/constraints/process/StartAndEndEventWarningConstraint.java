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

import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.EndMessageEvent;
import org.bonitasoft.studio.model.process.EndSignalEvent;
import org.bonitasoft.studio.model.process.EndTerminatedEvent;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.StartErrorEvent;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Baptiste Mesta
 */
public class StartAndEndEventWarningConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof Pool) {
            boolean containsEnd = false;
            boolean containsStart = false;
            for (final Element element : ((Pool) eObj).getElements()) {
                if (element instanceof StartEvent || element instanceof StartMessageEvent || element instanceof StartSignalEvent
                        || element instanceof StartTimerEvent || element instanceof StartErrorEvent) {
                    containsStart = true;
                } else if (element instanceof EndEvent || element instanceof EndMessageEvent || element instanceof EndSignalEvent
                        || element instanceof EndErrorEvent || element instanceof EndTerminatedEvent) {
                    containsEnd = true;
                } else if (element instanceof Lane) {
                    for (final Element el : ((Lane) element).getElements()) {
                        if (el instanceof StartEvent || el instanceof StartMessageEvent || el instanceof StartSignalEvent || el instanceof StartTimerEvent
                                || el instanceof StartErrorEvent) {
                            containsStart = true;
                        } else if (el instanceof EndEvent || el instanceof EndMessageEvent || el instanceof EndSignalEvent || el instanceof EndErrorEvent
                                || el instanceof EndTerminatedEvent) {
                            containsEnd = true;
                        }
                    }
                }
                if (containsEnd && containsStart) {
                    break;
                }
            }
            if (!containsStart) {
                return ctx.createFailureStatus(new Object[] { Messages.Validation_NoStartEvent });
            }
            if (!containsEnd) {
                return ctx.createFailureStatus(new Object[] { Messages.Validation_NoEndEvent });
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.nostartorendevent";
    }

}
