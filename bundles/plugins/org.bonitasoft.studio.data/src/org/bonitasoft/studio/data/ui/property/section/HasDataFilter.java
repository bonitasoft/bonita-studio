/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.data.ui.property.section;

import org.bonitasoft.studio.model.process.CatchMessageEvent;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.TimerEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

/**
 * @author Mickael Istria
 *         Has data filter ans is not:
 *         CatchMessageEvent
 *         TimerEvent
 *         MainProcess
 *         SendTask
 *         nor abstract task
 */
public class HasDataFilter implements IFilter {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
     */
    public boolean select(Object object) {
        if (object instanceof IGraphicalEditPart) {
            IGraphicalEditPart editPart = (IGraphicalEditPart) object;
            EObject eObject = editPart.resolveSemanticElement();
            if (eObject == null
                    || eObject instanceof CatchMessageEvent
                    || eObject instanceof TimerEvent
                    || eObject instanceof MainProcess
                    || eObject instanceof SendTask
                    || eObject instanceof StartSignalEvent
                    || eObject instanceof StartTimerEvent) {
                return false;
            }

            return (eObject.eClass().getEAllStructuralFeatures().contains(ProcessPackage.Literals.DATA_AWARE__DATA)
                    || eObject instanceof Lane);

        }
        return false;
    }

}
