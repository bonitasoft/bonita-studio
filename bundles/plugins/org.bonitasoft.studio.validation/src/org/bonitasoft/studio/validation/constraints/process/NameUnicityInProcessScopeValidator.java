/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.constraints.process;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.osgi.util.NLS;


public class NameUnicityInProcessScopeValidator extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final List<IStatus> failureStatuses = searchForConstraintViolation(context);
        if (!failureStatuses.isEmpty()) {
            return createMultiStatusConstraint(context, failureStatuses);
        }
        return context.createSuccessStatus();
    }

    protected IStatus createMultiStatusConstraint(final IValidationContext context, final List<IStatus> failureStatuses) {
        return ConstraintStatus.createMultiStatus(context, Messages.nameUnicityConstraintsGenericMessage, new Object[] {}, failureStatuses);
    }

    protected List<IStatus> searchForConstraintViolation(final IValidationContext context) {
        final EObject targetObject = context.getTarget();
        final List<EObject> elementBaseToSearchFor = computeElementsBaseToSearchFor(targetObject);
        final List<EObject> elementBaseToSearchIn = computeElementsToCompare(targetObject);
        final List<IStatus> failureStatuses = new ArrayList<IStatus>();
        final List<EObject> alreadyHandled = new ArrayList<EObject>();
        for (final EObject currentElement : elementBaseToSearchFor) {
            final String currentName = getName(currentElement);
            for (final EObject element : elementBaseToSearchIn) {
                if (!(element == currentElement) && !alreadyHandled.contains(element)) {
                    final String nameSearched = getName(element);
                    if (nameSearched.equals(currentName)) {
                        alreadyHandled.add(currentElement);
                        alreadyHandled.add(element);
                        failureStatuses.add(context.createFailureStatus(
                                NLS.bind(Messages.nameUnicityConstraintsDuplicateNameMessage,
                                        new String[] { currentName, getTypeLabel(currentElement), getTypeLabel(element) })));
                    }
                }
            }
        }
        return failureStatuses;
    }

    private List<EObject> computeElementsBaseToSearchFor(final EObject targetObject) {
        if (targetObject instanceof Activity) {
            return computeElementsToCompareInActivityScope((Activity) targetObject);
        } else {
            return computeElementsToCompare(targetObject);
        }
    }

    protected List<EObject> computeElementsToCompare(final EObject targetObject) {
        final List<EObject> elements = new ArrayList<EObject>();
        if(targetObject instanceof Activity){
            elements.addAll(computeElementsToCompareInActivityScope((Activity) targetObject));
        }
        if (targetObject instanceof Pool) {
            final Contract contract = ((Pool) targetObject).getContract();
            if (contract != null) {
                elements.addAll(contract.getInputs());//TODO: what about complex?
            }
        }
        final Pool pool = ModelHelper.getParentPool(targetObject);
        elements.addAll(pool.getParameters());
        elements.addAll(pool.getDocuments());
        elements.addAll(pool.getData());
        return elements;
    }

    protected List<EObject> computeElementsToCompareInActivityScope(final Activity targetObject) {
        final List<EObject> elements = new ArrayList<EObject>();
        elements.addAll(targetObject.getData());
        if (targetObject instanceof Task) {
            final Contract contract = ((Task) targetObject).getContract();
            if (contract != null) {
                elements.addAll(contract.getInputs());
            }
        }
        return elements;
    }

    private String getTypeLabel(final EObject o1) {
        if (o1 instanceof Document) {
            return Messages.nameUnicityConstraintDocument;
        } else if (o1 instanceof Parameter) {
            return Messages.nameUnicityConstraintParameter;
        } else if (o1 instanceof ContractInput) {
            return Messages.nameUnicityConstraintContractInput;
        } else if (o1 instanceof Data) {
            return Messages.nameUnicityConstraintVariable;
        }
        return "";
    }

    private String getName(final EObject o1) {
        if (o1 instanceof Element) {
            return ((Element) o1).getName();
        } else if (o1 instanceof Parameter) {
            return ((Parameter) o1).getName();
        } else if (o1 instanceof ContractInput) {
            return ((ContractInput) o1).getName();
        }
        return "";
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraint.unicityNameInProcess";
    }

}
