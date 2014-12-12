/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.actors.operation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.actors.model.organization.util.OrganizationXMLProcessor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;

public final class OrganizationXMLProcessorRespectingEncoding extends OrganizationXMLProcessor {

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.xmi.util.XMLProcessor#saveToString(org.eclipse.emf.ecore.resource.Resource, java.util.Map)
     * Override to fix Eclipse EMF bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=441484
     */
    @Override
    public String saveToString(final Resource resource, final Map<?, ?> options) throws IOException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        if (options != null) {
            final Map<Object, Object> mergedOptions = new HashMap<Object, Object>(saveOptions);
            mergedOptions.putAll(options);
            ((XMLResource) resource).save(os, mergedOptions);
        } else {
            ((XMLResource) resource).save(os, saveOptions);
        }
        final Object encoding = options.get(XMLResource.OPTION_ENCODING);
        if (encoding != null && encoding instanceof String) {
            return os.toString((String) encoding);
        } else {
            return os.toString();
        }
    }
}