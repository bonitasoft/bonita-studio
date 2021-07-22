/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.engine.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.engine.operation.PortalURLBuilder;

public class LoginRequest extends HttpRequest<String> {

    public LoginRequest(final HttpClientFactory httpClientFactory) {
        super(httpClientFactory);
    }

    @Override
    protected String doExecute(final HttpClient httpClient) throws IOException, HttpException {
        HttpGet loginRequest;
        try {
            loginRequest = new HttpGet(new PortalURLBuilder().toTechnicalUserLoginURL(AbstractRepository.NULL_PROGRESS_MONITOR).toURI());
        } catch (final URISyntaxException e) {
            throw new HttpException("Failed to create login request", e);
        }
        final HttpResponse loginResponse = httpClient.execute(loginRequest);
        final int statusCode = loginResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpURLConnection.HTTP_OK) {
            throw new HttpException(String.format("Login failed with status: %s. Open Engine log for more details.", statusCode));
        }
        return contentAsString(loginResponse);
    }

}
