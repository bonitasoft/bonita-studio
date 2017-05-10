/**
 * Copyright (C) 2010-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.model.process.Assignable;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Baptiste Mesta
 * @author Aurelien Pupier - constraint for assigned actors with Lane
 */
public class AssignableConstraint extends AbstractLiveValidationMarkerConstraint {

    public static final String ID = "org.bonitasoft.studio.validation.constraints.assignable";

    @Override
    protected IStatus performLiveValidation(IValidationContext ctx) {
        final EStructuralFeature featureTriggered = ctx.getFeature();
        if (featureTriggered.equals(ProcessPackage.Literals.ASSIGNABLE__ACTOR)) {
            final Assignable assignable = (Assignable) ctx.getTarget();
            if (!hasActorsDefined(assignable)) {
                return ctx.createFailureStatus(new Object[] { ((Element) assignable).getName() });
            }
        } else if (featureTriggered.equals(NotationPackage.Literals.VIEW__ELEMENT)) {
            final EObject eobject = (EObject) ctx.getFeatureNewValue();
            if (eobject instanceof Assignable) {
                if (!hasActorsDefined((Assignable) eobject)) {
                    return ctx.createFailureStatus(new Object[] { ((Element) eobject).getName() });
                }
            }
        } else if (featureTriggered.equals(ProcessPackage.Literals.TASK__OVERRIDE_ACTORS_OF_THE_LANE)) {
            final Task task = (Task) ctx.getTarget();
            final Boolean overrideGroupsOfLane = (Boolean) ctx.getFeatureNewValue();
            if (overrideGroupsOfLane) {
                if (!hasActorsDefined(task)) {
                    return ctx.createFailureStatus(new Object[] { task.getName() });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    private boolean hasActorsDefined(Assignable assignable) {
        if (assignable instanceof Task) {
            if (((Task) assignable).isOverrideActorsOfTheLane()) {
                return assignable.getActor() != null;
            } else {
                final Lane parentLane = getParentLane(assignable);
                if (parentLane != null) {
                    return hasActorsDefined(parentLane);
                } else {
                    return false;
                }
            }
        } else {
            return assignable.getActor() != null;
        }

    }

    @Override
    protected String getMarkerType(DiagramEditor editor) {
        return ProcessMarkerNavigationProvider.MARKER_TYPE;
    }

    @Override
    protected String getConstraintId() {
        return ID;
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof Assignable) {
            final Assignable assignable = (Assignable) eObj;
            if (!hasActorsDefined(assignable)) {
                if (assignable instanceof Lane) {
                    if (hasTaskUsingLaneActor((Lane) assignable)) {
                        return ctx.createFailureStatus(new Object[] { ((Element) assignable).getName() });
                    }
                } else {
                    return ctx.createFailureStatus(new Object[] { ((Element) assignable).getName() });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    private boolean hasTaskUsingLaneActor(Lane lane) {
        for (final Element element : lane.getElements()) {
            if (element instanceof Task) {
                if (!((Task) element).isOverrideActorsOfTheLane()) {
                    return true;
                }
            } else if (element instanceof SubProcessEvent) {
                for (final Element elementInSubProc : ((SubProcessEvent) element).getElements()) {
                    if (elementInSubProc instanceof Task && !((Task) elementInSubProc).isOverrideActorsOfTheLane()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Lane getParentLane(EObject eObject) {
        Lane res = null;
        EObject current = eObject;
        while (current != null && res == null) {
            if (current instanceof Lane) {
                res = (Lane) current;
            } else {
                current = current.eContainer();
            }
        }
        return res;
    }
}
