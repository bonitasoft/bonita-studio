/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.common.operation;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * @author Romain Bioteau
 */
public class FindDiagramRunnable implements Runnable {

    private final BatchValidationOperation validateOperation;
    private final Resource eResource;

    public FindDiagramRunnable(final Resource eResource, final BatchValidationOperation validateOperation) {
        this.eResource = eResource;
        this.validateOperation = validateOperation;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        eResource.unload();
        try {
            eResource.load(Collections.emptyMap());
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final TreeIterator<EObject> allContents = eResource.getAllContents();
        while (allContents.hasNext()) {
            final EObject eObject = allContents.next();
            if (eObject instanceof Diagram) {
                validateOperation.addDiagram((Diagram) eObject);
            }
        }
    }

}
