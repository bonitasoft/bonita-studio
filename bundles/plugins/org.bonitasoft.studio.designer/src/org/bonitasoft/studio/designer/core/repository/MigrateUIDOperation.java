/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.designer.core.repository;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.restlet.representation.EmptyRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


public class MigrateUIDOperation implements IRunnableWithProgress {


    private PageDesignerURLFactory pageDesignerURLBuilder;

    public MigrateUIDOperation(PageDesignerURLFactory pageDesignerURLBuilder) {
        this.pageDesignerURLBuilder = pageDesignerURLBuilder;
    }

    public MigrateUIDOperation() {

    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.subTask(Messages.migratingUID);
        PageDesignerURLFactory urlBuilder = pageDesignerURLBuilder == null
                ? new PageDesignerURLFactory(getPreferenceStore()) : pageDesignerURLBuilder;
        URI uri = null;
        try {
            uri = urlBuilder.migrate().toURI();
        } catch (MalformedURLException | URISyntaxException e1) {
            throw new InvocationTargetException(new MigrationException(e1));
        }
        try {
            ClientResource clientResource = new ClientResource(uri);
            clientResource.setRetryOnError(true);
            clientResource.setRetryDelay(500);
            clientResource.setRetryAttempts(10);
            clientResource.post(new EmptyRepresentation());
        } catch (ResourceException e) {
            throw new InvocationTargetException(new MigrationException(e),
                    "Failed to post on " + uri);
        }
    }

    protected IEclipsePreferences getPreferenceStore() {
        return InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_ID);
    }

}
