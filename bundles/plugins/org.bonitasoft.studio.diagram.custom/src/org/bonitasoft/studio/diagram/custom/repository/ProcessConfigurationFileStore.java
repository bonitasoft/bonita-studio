/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 */
public class ProcessConfigurationFileStore extends EMFFileStore<Configuration> {

    public ProcessConfigurationFileStore(final String folderName, final IRepositoryStore<ProcessConfigurationFileStore> store) {
        super(folderName, store);
    }

    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    protected void doSave(final Object content) {
        final Resource resource = getEMFResource();
        if (content instanceof Configuration) {
            resource.getContents().clear();
            resource.getContents().add(EcoreUtil.copy((Configuration) content));
        }
        try {
            final Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            resource.save(options);
            resource.unload();
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    @Override
    public IFile getResource() {
        return getParentStore().getResource().getFile(getName());
    }

    @Override
    public String getDisplayName() {
        return getName();
    }

    @Override
    public Image getIcon() {
        return getParentStore().getIcon();
    }

}
