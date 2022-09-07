/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints;

import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ScriptTask;
import org.bonitasoft.studio.model.process.ServiceTask;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Romain Bioteau
 */
public class BPMN2ValidationConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final EObject eObj = context.getTarget();
        if (eObj instanceof ConnectableElement && !(eObj instanceof Pool)) {
            final ConnectableElement connectable = (ConnectableElement) eObj;
            if (!connectable.getConnectors().isEmpty()) {
                if (!(connectable instanceof ServiceTask || connectable instanceof ScriptTask)) {
                    return context
                            .createFailureStatus(new Object[] { Messages.bind(Messages.connectableElementIsNotAScriptorServiceTask, connectable.getName()) });
                }
                if (connectable.getConnectors().size() > 1) {
                    return context.createFailureStatus(new Object[] { Messages.bind(Messages.tooManyConnectorsDefined, connectable.getName()) });
                }
            }
        }
        return context.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.bpmn2";
    }

}
