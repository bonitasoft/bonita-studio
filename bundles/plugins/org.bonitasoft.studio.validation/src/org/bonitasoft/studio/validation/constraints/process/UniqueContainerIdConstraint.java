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
package org.bonitasoft.studio.validation.constraints.process;

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * @author Baptiste Mesta
 * 
 */
public class UniqueContainerIdConstraint extends AbstractLiveValidationMarkerConstraint {


	@Override
	protected IStatus performLiveValidation(IValidationContext ctx) {
		return ctx.createSuccessStatus();
	}

	@Override
	protected IStatus performBatchValidation(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		if(eObj instanceof Pool){
			for (Element el : ((Container) eObj.eContainer()).getElements()) {
				if(!el.equals(eObj) && el.getName().equals(((Element) eObj).getName()) && ((AbstractProcess) el).getVersion().equals(((AbstractProcess) eObj).getVersion())){
					return ctx.createFailureStatus(new Object[] { Messages.Validation_Element_SameName + ": " + el.getName() });
				}
			}
			
			Pool p = (Pool) eObj;
			final DiagramRepositoryStore diagramStore =  (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
			final List<AbstractProcess> allProcesses = diagramStore.getAllProcesses();
			for (AbstractProcess other_p : allProcesses) {
				if(!EcoreUtil.equals(p,other_p) && p.getName().equals(other_p.getName()) && p.getVersion().equals(other_p.getVersion())){
					return ctx.createFailureStatus(new Object[] { Messages.bind(Messages.Validation_Duplicate_Process , p.getName(), p.getVersion())});
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
		return "org.bonitasoft.studio.validation.constraints.uniquecontainerid";
	}

}
