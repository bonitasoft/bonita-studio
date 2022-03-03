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
package org.bonitasoft.studio.engine.export;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.ActorMappingFactory;
import org.bonitasoft.studio.model.actormapping.ActorMappingsType;
import org.bonitasoft.studio.model.actormapping.DocumentRoot;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.actormapping.Membership;
import org.bonitasoft.studio.model.actormapping.Roles;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.actormapping.util.ActorMappingResourceFactoryImpl;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;


public class ActorMappingExporter {

    public byte[] toByteArray(final Configuration configuration) throws ActorMappingExportException {
        if (containsActorMapping(configuration)) {
            final File tmpFile = new File(getTempFolder(), EcoreUtil.generateUUID() + ".xml");
            final org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createFileURI(tmpFile.getAbsolutePath());
            final Resource resource = new ActorMappingResourceFactoryImpl().createResource(uri);
            final DocumentRoot root = ActorMappingFactory.eINSTANCE.createDocumentRoot();
            final ActorMappingsType mapping = EcoreUtil.copy(configuration.getActorMappings());
            cleanMapping(mapping);
            root.setActorMappings(mapping);
            resource.getContents().add(root);
            final Map<String, String> options = new HashMap<>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                resource.save(options);
                new XMLProcessor().save(byteArrayOutputStream, resource, options);
                resource.delete(Collections.EMPTY_MAP);
                return byteArrayOutputStream.toByteArray();
            } catch (final IOException e) {
                throw new ActorMappingExportException("Failed to save actor mapping", e);
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (final IOException e) {

                }
            }
        }
        return null;
    }

    protected File getTempFolder() {
        return ProjectUtil.getBonitaStudioWorkFolder();
    }

    protected boolean containsActorMapping(final Configuration configuration) {
        return configuration.getActorMappings() != null
                && configuration.getActorMappings().getActorMapping() != null
                && !configuration.getActorMappings().getActorMapping().isEmpty();
    }

    protected void cleanMapping(final ActorMappingsType mapping) {
        for (final ActorMapping m : mapping.getActorMapping()) {
            final Groups groups = m.getGroups();
            if (groups != null && groups.getGroup().isEmpty()) {
                m.setGroups(null);
            }
            final Membership memberships = m.getMemberships();
            if (memberships != null && memberships.getMembership().isEmpty()) {
                m.setMemberships(null);
            }
            final Roles roles = m.getRoles();
            if (roles != null && roles.getRole().isEmpty()) {
                m.setRoles(null);
            }
            final Users users = m.getUsers();
            if (users != null && users.getUser().isEmpty()) {
                m.setUsers(null);
            }
        }
    }
}
