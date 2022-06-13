/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.engine.http;

import static com.google.common.collect.Iterables.find;
import static com.google.common.io.ByteStreams.toByteArray;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.cookie.Cookie;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.jface.preference.IPreferenceStore;

import com.google.common.base.Predicate;

public abstract class HttpRequest<T> {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static final String API_TOKEN_HEADER = "X-Bonita-API-Token";
    
    private final HttpClientFactory httpClientFactory;

    public HttpRequest(HttpClientFactory httpClientFactory) {
        this.httpClientFactory = httpClientFactory;
    }

    public T execute() throws IOException, HttpException {
        return doExecute(httpClientFactory.createHttpClient());
    }

    protected String contentAsString(final HttpResponse response) throws IOException {
        return new String(toByteArray(response.getEntity().getContent()), UTF_8);
    }

    protected abstract T doExecute(HttpClient httpClient) throws IOException, HttpException;

    protected String getURLBase() {
        final IPreferenceStore store = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
        final String port = store.getString(BonitaPreferenceConstants.CONSOLE_PORT);
        final String host = store.getString(BonitaPreferenceConstants.CONSOLE_HOST);
        return String.format("http://%s:%s/bonita/", host, port);
    }

    protected String getAPITokenFromCookie() {
        final CookieStore cookieStore = httpClientFactory.getCookieStore();
        return find(cookieStore.getCookies(), new Predicate<Cookie>() {

            @Override
            public boolean apply(Cookie c) {
                return Objects.equals(c.getName(), API_TOKEN_HEADER);
            }

        }).getValue();
    }

}
