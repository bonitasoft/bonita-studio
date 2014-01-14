/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.database.drivers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;

/**
 * @author Romain Bioteau
 *
 */
public class DriversFileStoreContribution implements IFileStoreContribution {


	
	 /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IFileStoreContribution#appliesTo(org.bonitasoft.studio.common.repository.model.IRepositoryStore)
     */
    @Override
    public boolean appliesTo(IRepositoryStore<? extends IRepositoryFileStore> repository) {
        return repository instanceof DependencyRepositoryStore;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IFileStoreContribution#execute(org.bonitasoft.studio.common.repository.model.IRepositoryStore)
     */
    @Override
    public void execute(IRepositoryStore<? extends IRepositoryFileStore> repository) {
        Enumeration<URL> drivers = ConnectorPlugin.getDefault().getBundle().findEntries("drivers", "*.jar", false) ;
        final List<String> jarList = new ArrayList<String>();
        while(drivers.hasMoreElements()){
            URL url = drivers.nextElement() ;
            String file = url.getFile() ;
            String[] segments = file.split("/") ;
            String name =  segments[segments.length -1] ;
            try {
                repository.importInputStream(name, url.openStream());
                jarList.add(name);
            } catch (IOException e) {
                BonitaStudioLog.error(e) ;
            }
        }
    }


}
