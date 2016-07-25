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
package org.bonitasoft.studio.simulation.repository;

import java.io.IOException;
import java.util.Collections;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 *
 */
public class SimulationResourceFileStore extends EMFFileStore {

    public SimulationResourceFileStore(String fileName,IRepositoryStore<SimulationResourceFileStore> store) {
        super(fileName, store) ;
    }

    @Override
    public String getDisplayName() {
        return ((org.bonitasoft.studio.model.simulation.Resource) getContent()).getName() ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(Object content) {
        if(content instanceof org.bonitasoft.studio.model.simulation.Resource){
            Resource emfResource = getEMFResource() ;
            emfResource.getContents().clear() ;
            emfResource.getContents().add(EcoreUtil.copy((EObject) content)) ;
            try {
                emfResource.save(Collections.EMPTY_MAP) ;
            } catch (IOException e) {
                BonitaStudioLog.error(e) ;
            }
        }
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
     */
    @Override
    protected IWorkbenchPart doOpen() {
        return null ;
    }


}
