/**
 * Copyright (C) 2023 BonitaSoft S.A.
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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.BOSWebServerManager;

public class LoginRequest extends HttpRequest<Void> {

    public LoginRequest(final HttpClientFactory httpClientFactory) {
        super(httpClientFactory);
    }

    @Override
    protected Void doExecute(final HttpClient httpClient) throws IOException, HttpException {
        var loginRequest = new HttpPost(BOSWebServerManager.getInstance().generateLoginURL());
        var parameters = List.of(
                new BasicNameValuePair("username", BOSEngineManager.BONITA_TECHNICAL_USER),
                new BasicNameValuePair("password", BOSEngineManager.BONITA_TECHNICAL_USER_PASSWORD),
                new BasicNameValuePair("redirect", "false"));
        loginRequest.setEntity(new UrlEncodedFormEntity(parameters));
        final HttpResponse loginResponse = httpClient.execute(loginRequest);
        final int statusCode = loginResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpURLConnection.HTTP_NO_CONTENT) {
            throw new HttpException(
                    String.format("Login as technical user failed with status: %s. Open Engine log for more details.", statusCode));
        }
        return null;
    }

}
