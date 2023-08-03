/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.actors.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationXMLProcessor;
import org.bonitasoft.plugin.analyze.report.model.ActorFilterImplementation;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;

public class DependencyActorFilterImplFileStore extends ActorFilterImplFileStore {

    private ActorFilterImplementation implementation;

    public DependencyActorFilterImplFileStore(ActorFilterImplementation implementation,
            AbstractEMFRepositoryStore<ActorFilterImplFileStore> store) {
        super(new File(implementation.getArtifact().getFile()).getName(), store);
        this.implementation =implementation;
    }

    @Override
    protected Resource doCreateEMFResource() {
        if (implementation != null) {
            File file = new File(implementation.getArtifact().getFile());
            if (file.exists()) {
                try (InputStream is = new JarFile(file).getInputStream(new JarEntry(implementation.getJarEntry()))) {
                    ConnectorImplementationXMLProcessor xmlProcessor = new ConnectorImplementationXMLProcessor();
                    return xmlProcessor.load(is, loadOptions());
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        return null;
    }
    
    
    @Override
    public boolean canBeDeleted() {
        return false;
    }
    
    @Override
    public boolean canBeExported() {
        return false;
    }
    
    @Override
    public boolean canBeShared() {
        return false;
    }
    
    @Override
    public boolean isReadOnly() {
        return true;
    }
    
    private static Map<String, Object> loadOptions() {
        Map<String, Object> loadOptions = new HashMap<>();
        loadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
        loadOptions.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
        return loadOptions;
    }
}
