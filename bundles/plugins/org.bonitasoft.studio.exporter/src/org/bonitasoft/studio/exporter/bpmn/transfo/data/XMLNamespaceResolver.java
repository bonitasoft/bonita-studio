/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.bpmn.transfo.data;

import static java.util.Objects.requireNonNull;

import java.util.Map.Entry;

import org.bonitasoft.studio.model.process.XMLData;
import org.eclipse.emf.common.util.EMap;
import org.omg.spec.bpmn.model.DocumentRoot;

/**
 * @author Romain Bioteau
 */
public class XMLNamespaceResolver {

    private int cuurentNamespaceCounter;
    private final EMap<String, String> namespacePrefixRegistry;

    public XMLNamespaceResolver(final DocumentRoot documentRoot) {
        namespacePrefixRegistry = requireNonNull(documentRoot).getXMLNSPrefixMap();
    }

    public String resolveNamespacePrefix(final XMLData data) {
        final String dataTypeNamespace = requireNonNull(data).getNamespace();
        String resolvedNSPrefix = findExistingNSPrefix(data.getNamespace());
        if (resolvedNSPrefix == null) {
            resolvedNSPrefix = "n" + cuurentNamespaceCounter;
            cuurentNamespaceCounter++;
            namespacePrefixRegistry.put(resolvedNSPrefix, dataTypeNamespace);
        }
        return resolvedNSPrefix;
    }

    private String findExistingNSPrefix(final String dataTypeNamespace) {
        for (final Entry<String, String> prefixMap : namespacePrefixRegistry.entrySet()) {
            final String value = prefixMap.getValue();
            if (value != null && value.equals(dataTypeNamespace)) {
                return prefixMap.getKey();
            }
        }
        return null;
    }
}
