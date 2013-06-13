/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.model.process.Assignable;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
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
 * 
 * @author Baptiste Mesta
 * @author Aurelien Pupier - constraint for assigned actors with Lane
 *
 */
public class AssignableConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performLiveValidation(IValidationContext ctx) {
        final EStructuralFeature featureTriggered = ctx.getFeature();
        if(featureTriggered.equals(ProcessPackage.Literals.ASSIGNABLE__ACTOR)){
            final Assignable assignable = (Assignable) ctx.getTarget();
            if(noActorsDefined(assignable)){
                return ctx.createFailureStatus(new Object[] { ((Element) assignable).getName() });
            }
        }else if(featureTriggered.equals(NotationPackage.Literals.VIEW__ELEMENT)){
            EObject eobject = (EObject) ctx.getFeatureNewValue();
            if(eobject instanceof Assignable){
                if(noActorsDefined((Assignable) eobject)){
                    return ctx.createFailureStatus(new Object[] {  ((Element) eobject).getName() });
                }
            }
        }else if(featureTriggered.equals(ProcessPackage.Literals.TASK__OVERRIDE_ACTORS_OF_THE_LANE)){
            final Task task = (Task) ctx.getTarget();
            final Boolean overrideGroupsOfLane = (Boolean) ctx.getFeatureNewValue();
            if(overrideGroupsOfLane){
                if(noActorsDefined(task)){
                    return ctx.createFailureStatus(new Object[] { task.getName() });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    protected boolean noActorsDefined(Assignable assignable){
        if(assignable instanceof Task && !((Task) assignable).isOverrideActorsOfTheLane() && assignable.eContainer() instanceof Lane ){
            return false;
        }
        return assignable.getActor() == null ;
    }

    @Override
    protected String getMarkerType(DiagramEditor editor) {
        return ProcessMarkerNavigationProvider.MARKER_TYPE;
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.assignable";
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext ctx) {
        EObject eObj = ctx.getTarget();
        if (eObj instanceof Task) {
            Task task = (Task) eObj;
            // task must have at least an actor (or group)
            if(task.isOverrideActorsOfTheLane() || !(task.eContainer() instanceof Lane)){
                if (noActorsDefined(task)){
                    return ctx.createFailureStatus(new Object[] { ((Task) eObj).getName() });
                }
            } else if(!task.isOverrideActorsOfTheLane() && task.eContainer() instanceof Lane){
                if (noActorsDefined((Assignable) task.eContainer())){
                    return ctx.createFailureStatus(new Object[] { ((Task) eObj).getName() });
                }
            }
        } else if(eObj instanceof Lane){
            if (noActorsDefined((Assignable) eObj)){
                for(EObject temp : ((Lane)eObj).getElements()){
                    if(temp instanceof Task){
                        if(!((Task) temp).isOverrideActorsOfTheLane()){
                            return ctx.createFailureStatus(new Object[] { ((Lane) eObj).getName() });
                        }
                    }
                }
            }
        }
        return ctx.createSuccessStatus();
    }


}
