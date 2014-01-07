/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.store;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.resources.IFile;

/**
 * @author Romain Bioteau
 *
 */
public class URLDeploymentConfigurationFileStore extends DeploymentConfigurationFileStore {

    private final URL url;

    public URLDeploymentConfigurationFileStore(URL url,
            IRepositoryStore store) {
        super(url.toString(), store);
        this.url = url;
    }

    @Override
    public String getName() {
        String file = url.getFile() ;
        String[] segments = file.split("/") ;
        return segments[segments.length -1] ;
    }

    @Override
    public String getDisplayName() {
        return Messages.defaultConfiguration;
    }

    @Override
    public Properties getContent() {
        try {
            return load(url.openStream());
        } catch (IOException e) {
            BonitaStudioLog.error(e) ;
        }
        return null;
    }


    @Override
    public boolean canBeExported() {
        return false ;
    }

    @Override
    public IFile getResource() {
        return null ;
    }

    @Override
    protected void doDelete() {

    }

    @Override
    protected void doSave(Object content) {

    }

    @Override
    public boolean isReadOnly() {
        return true ;
    }

    @Override
    public boolean canBeShared() {
        return false ;
    }

}
