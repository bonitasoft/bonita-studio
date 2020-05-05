/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.operation;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.bonitasoft.studio.designer.core.repository.WebResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class DeleteUIDArtifactOperation implements IRunnableWithProgress {

    private WebResource fileStore;

    public DeleteUIDArtifactOperation(WebResource fileStore) {
        this.fileStore = fileStore;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        try {
            new ClientResource(fileStore.toURI()).delete();
        } catch (ResourceException | MalformedURLException | URISyntaxException e) {
            throw new InvocationTargetException(e);
        }
    }

}
