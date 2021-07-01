/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.dependencies.connector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.studio.connector.model.implementation.DocumentRoot;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;

@Creatable
public class ConnectorImplementationUpdater {

    public void update(ConnectorImplementationChange change) {
        for (var implementation : change.getConnectorImplementations()) {
            change.apply(implementation);
            Resource resource = implementation.eResource();
            if (resource != null) {
                try {
                    resource.getContents().clear();
                    DocumentRoot root = ConnectorImplementationFactory.eINSTANCE.createDocumentRoot();
                    root.setConnectorImplementation(EcoreUtil.copy(implementation));
                    resource.getContents().add(root);
                    resource.save(saveOptions());
                } catch (IOException e) {
                   BonitaStudioLog.error(e);
                }
            }
        }
    }

    private static Map<String, Object> saveOptions() {
        final Map<String, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        options.put(XMLResource.OPTION_XML_VERSION, "1.0");
        return options;
    }

}
