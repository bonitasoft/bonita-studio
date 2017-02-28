/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
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

    public CookieStore getCookieStore() {
        return context.getCookieStore();
    }
}
