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

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;

/**
 * @author Baptiste Mesta
 */
public class UniqueContainerIdConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof Pool) {
            for (final Element el : ((Container) eObj.eContainer()).getElements()) {
                if (!el.equals(eObj) && el.getName().equals(((Element) eObj).getName())
                        && ((AbstractProcess) el).getVersion().equals(((AbstractProcess) eObj).getVersion())) {
                    return ctx.createFailureStatus(Messages.Validation_Element_SameName + ": " + el.getName());
                }
            }

            final Pool p = (Pool) eObj;
            DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class);
            List<AbstractProcess> allProcesses = diagramStore.hasComputedProcesses() ? diagramStore.getComputedProcesses() : diagramStore.getAllProcesses();
            for (final AbstractProcess other_p : allProcesses) {
                if (!EcoreUtil.equals(p, other_p)
                        && !sameEObjectId(p, other_p)
                        && p.getName().equals(other_p.getName())
                        && p.getVersion().equals(other_p.getVersion())) {
                    return ctx.createFailureStatus(NLS.bind(Messages.Validation_Duplicate_Process, p.getName(), p.getVersion()));
                }
            }
        }

        return ctx.createSuccessStatus();
    }
    
    protected boolean sameEObjectId(final Pool p, final AbstractProcess other_p) {
        final String eObjectID = ModelHelper.getEObjectID(p);
        final String eObjectID2 = ModelHelper.getEObjectID(other_p);
        return eObjectID != null && eObjectID.equals(eObjectID2);
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.uniquecontainerid";
    }

}
