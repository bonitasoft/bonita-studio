/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.bar;

import static com.google.common.io.ByteStreams.toByteArray;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * @author Romain Bioteau
 */
@Creatable
public class CustomPageBarResourceFactory {

    private static final String BAR_CUSTOMPAGES_LOCATION = "customPages";

    private final PageDesignerURLFactory pageDesignerURLFactory;

    @Inject
    public CustomPageBarResourceFactory(final PageDesignerURLFactory pageDesignerURLFactory) {
        this.pageDesignerURLFactory = pageDesignerURLFactory;
    }

    public BarResource newBarResource(final String targetFormCustomPageId, final String formPageTechnicalUUID)
            throws BarResourceCreationException {
        try {
            return new BarResource(BAR_CUSTOMPAGES_LOCATION + "/" + targetFormCustomPageId + ".zip",
                    export(formPageTechnicalUUID));
        } catch (ResourceException | IOException e) {
            throw new BarResourceCreationException(
                    String.format("Failed to create a BarResource for form %s", targetFormCustomPageId), e);
        }
    }

    public byte[] export(final String formPageTechnicalUUID) throws IOException {
        try (final InputStream is = get(pageDesignerURLFactory.exportPage(formPageTechnicalUUID).toString());) {
            if (is == null) {
                throw new IOException(String.format("Failed to export custom page for form %s", formPageTechnicalUUID));
            }
            return toByteArray(is);
        }
    }

    protected InputStream get(final String exportURL) throws IOException {
        final Representation representation = new ClientResource(exportURL).get();
        return representation != null && representation.isAvailable() ? representation.getStream() : null;
    }
}
