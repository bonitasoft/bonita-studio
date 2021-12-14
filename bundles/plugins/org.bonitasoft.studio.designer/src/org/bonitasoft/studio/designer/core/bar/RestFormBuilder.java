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
package org.bonitasoft.studio.designer.core.bar;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import javax.inject.Inject;

import org.bonitasoft.studio.common.net.HttpClientFactory;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.exception.PageIncompatibleException;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class RestFormBuilder implements FormBuilder {

    private final PageDesignerURLFactory pageDesignerURLFactory;

    @Inject
    public RestFormBuilder(PageDesignerURLFactory pageDesignerURLFactory) {
        this.pageDesignerURLFactory = pageDesignerURLFactory;
    }

    @Override
    public byte[] export(String formId) throws IOException, PageIncompatibleException {
        try {
            return get(pageDesignerURLFactory.exportPage(formId).toString(), formId);
        } catch (InterruptedException e) {
            throw new IOException(String.format("Failed to export custom page for form %s", formId), e);
        }
    }

    protected byte[] get(final String exportURL, String formId) throws IOException, PageIncompatibleException, InterruptedException {
        HttpResponse<byte[]> response = HttpClientFactory.INSTANCE.send(HttpRequest.newBuilder(URI.create(exportURL))
                .GET()
                .build(), BodyHandlers.ofByteArray());
        if (response.statusCode() == 422) {
            throw new PageIncompatibleException(String.format(
                    "Form '%s' is not compatible with the current UI-Designer version. check UI-Designer logs for more details.",
                    formId));
        }
        return response.body();
    }
}
