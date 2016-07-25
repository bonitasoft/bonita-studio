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

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.model.simulation.util.SimulationAdapterFactory;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.simulation.SimulationPlugin;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class SimulationResourceRepositoryStore extends AbstractEMFRepositoryStore<SimulationResourceFileStore> {

    public final static String STORE_NAME = "simulation" +File.separatorChar + "resources" ;
    public static final String SIMULATION_RESOURCE_EXT = "simresource";
    private static final Set<String> extensions = new HashSet<String>() ;
    static{
        extensions.add(SIMULATION_RESOURCE_EXT) ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public SimulationResourceFileStore createRepositoryFileStore(String fileName) {
        return new SimulationResourceFileStore(fileName,this);
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#canImportBARResource(java.lang.String)
     */
    public boolean canImportBARResource(String resourceName) {
        return false;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.SimulationResourceRepositoryName;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("simulationRessource.png",SimulationPlugin.getDefault());
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    protected void addAdapterFactory(ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new SimulationAdapterFactory()) ;
    }

}
