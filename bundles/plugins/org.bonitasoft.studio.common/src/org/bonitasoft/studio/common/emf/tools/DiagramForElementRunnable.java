/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.emf.tools;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gmf.runtime.notation.Diagram;


/**
 * @author Romain Bioteau
 *
 */
public class DiagramForElementRunnable implements RunnableWithResult<Diagram> {

    private IStatus status = Status.OK_STATUS;

    private final Resource resource;

    private final EObject element;

    private Diagram result;

    public DiagramForElementRunnable(final Resource resource, final EObject element) {
        Assert.isLegal(element != null);
        Assert.isLegal(resource != null);
        this.resource = resource;
        this.element = element;
    }

    @Override
    public void run() {
        final TreeIterator<EObject> allContents = resource.getAllContents();
        EObject elementToFind = null;
        final Set<Diagram> diagrams = new HashSet<Diagram>();
        while (allContents.hasNext()) {
            final EObject eObject = allContents.next();
            if (EcoreUtil.equals(eObject, element)) {
                elementToFind = eObject;
            }
            if (eObject instanceof Diagram) {
                diagrams.add((Diagram) eObject);
            }
        }
        if (elementToFind == null) {
            return;
        }
        for (final Diagram diagram : diagrams) {
            final EObject diagramElement = diagram.getElement();
            if (diagramElement != null && diagramElement.equals(elementToFind)) {
                result = diagram;
                break;
            }
        }
    }

    @Override
    public Diagram getResult() {
        return result;
    }

    @Override
    public void setStatus(final IStatus status) {
        this.status = status;
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
