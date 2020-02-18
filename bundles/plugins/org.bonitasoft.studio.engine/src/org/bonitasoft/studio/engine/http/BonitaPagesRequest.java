/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.engine.http;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class BonitaPagesRequest extends HttpRequest<String> {

    private static final String PORTAL_BONITA_PAGE_API = "API/portal/bonitaPage/";

    public BonitaPagesRequest(HttpClientFactory httpClientFactory) {
        super(httpClientFactory);
    }

    @Override
    protected String doExecute(HttpClient httpClient) throws IOException, HttpException {
        final HttpGet request = new HttpGet(
                String.format("%s%s?p=0&c=%s", getURLBase(), PORTAL_BONITA_PAGE_API, Integer.MAX_VALUE));
        request.setHeader(API_TOKEN_HEADER, getAPITokenFromCookie());
        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != HttpURLConnection.HTTP_OK) {
            throw new HttpException(
                    String.format("Bonita Pages request failed with status: %s", response.getStatusLine().getStatusCode()));
        }
        return contentAsString(response);
    }

}
