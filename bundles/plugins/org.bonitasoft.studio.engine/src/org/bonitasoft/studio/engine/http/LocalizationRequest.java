/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.http;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;


public class LocalizationRequest extends HttpRequest<String> {

    private static final String PORTAL_LOCALIZATION_API = "API/system/i18ntranslation";
    private String locale;

    public LocalizationRequest(HttpClientFactory httpClientFactory,String locale) {
        super(httpClientFactory);
        this.locale = locale;
    }

    @Override
    protected String doExecute(HttpClient httpClient) throws IOException, HttpException {
        final HttpGet request = new HttpGet(
                String.format("%s%s?p=0&c=0&&f=locale=%s", getURLBase(), PORTAL_LOCALIZATION_API,locale));
        request.setHeader(API_TOKEN_HEADER, getAPITokenFromCookie());
        HttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() != HttpURLConnection.HTTP_OK) {
            throw new HttpException(
                    String.format("Bonita Pages request failed with status: %s", response.getStatusLine().getStatusCode()));
        }
        return contentAsString(response);
    }

}
