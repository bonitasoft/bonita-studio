/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.designer.core.bar;

import static com.google.common.io.ByteStreams.toByteArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import javax.inject.Inject;

import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.exception.PageIncompatibleException;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.restlet.Context;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

@Creatable
public class RestFormBuilder implements FormBuilder {

    static {
        Context.getCurrentLogger().setLevel(Level.OFF);
    }
    
    private final PageDesignerURLFactory pageDesignerURLFactory;
    
    @Inject
    public RestFormBuilder(PageDesignerURLFactory pageDesignerURLFactory) {
        this.pageDesignerURLFactory = pageDesignerURLFactory;
    }
  
    @Override
    public byte[] export(String formId) throws IOException, PageIncompatibleException {
        try (final InputStream is = get(pageDesignerURLFactory.exportPage(formId).toString(), formId)) {
            if (is == null) {
                throw new IOException(String.format("Failed to export custom page for form %s", formId));
            }
            return toByteArray(is);
        }
    }

    protected InputStream get(final String exportURL, String formId) throws IOException, PageIncompatibleException {
        ClientResource clientResource = new ClientResource(exportURL);
        clientResource.getLogger().setLevel(Level.OFF);
        try {
            final Representation representation = clientResource.get();
            return representation != null && representation.isAvailable() ? representation.getStream() : null;
        } catch (ResourceException e) {
            if (clientResource.getResponse().getStatus().getCode() == 422) {
                throw new PageIncompatibleException(String.format(
                        "Form '%s' is not compatible with the current UI-Designer version. check UI-Designer logs for more details.",
                        formId));
            }
            throw e;
        }
    }
}
