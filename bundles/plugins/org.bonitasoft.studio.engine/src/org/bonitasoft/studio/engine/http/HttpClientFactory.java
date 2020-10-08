/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.engine.http;

import java.io.File;

import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.bonitasoft.engine.page.Page;
import org.eclipse.e4.core.di.annotations.Creatable;

/**
 * HttpClientFactory create a HTTP client sharing a common Cookie Store
 */
@Creatable
public class HttpClientFactory {

    private final RequestConfig requestConfiguration;
    private final HttpClientContext context;

    public HttpClientFactory() {
        requestConfiguration = RequestConfig.custom().setCookieSpec(CookieSpecs.BEST_MATCH).build();
        context = HttpClientContext.create();
        context.setCookieStore(new BasicCookieStore());
    }

    public HttpClient createHttpClient() {
        return HttpClients.custom().setDefaultRequestConfig(requestConfiguration)
                .setDefaultCookieStore(context.getCookieStore()).build();
    }

    public HttpRequest<String> newLoginRequest() {
        return new LoginRequest(this);
    }

    public HttpRequest<String> newUploadCustomPageRequest(File file) {
        return new UploadCustomPageRequest(file, this);
    }

    public HttpRequest<String> newUpdateCustomPageRequest(String uploadedFileToken, Page pageToUpdate) {
        return new UpdateCustomPageRequest(uploadedFileToken, pageToUpdate, this);
    }

    public HttpRequest<String> newAddCustomPageRequest(String uploadedFileToken) {
        return new AddCustomPageRequest(uploadedFileToken, this);
    }

    public HttpRequest<String> newBonitaPagesRequest() {
        return new BonitaPagesRequest(this);
    }
    
    public HttpRequest<String> newCustomPagesRequest() {
        return new CustomPagesRequest(this);
    }
    
    public HttpRequest<String> newLocalizationRequest(String locale) {
        return new LocalizationRequest(this,locale);
    }

    public CookieStore getCookieStore() {
        return context.getCookieStore();
    }

  
}
