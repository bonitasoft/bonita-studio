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

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;

public class AddCustomPageRequest extends HttpRequest<String> {

    private static final String PORTAL_ADD_PAGE_API = "API/portal/page";

    private final String uploadedFileToken;


    public AddCustomPageRequest(final String uploadedFileToken, final HttpClientFactory httpClientFactory) {
        super(httpClientFactory);
        this.uploadedFileToken = uploadedFileToken;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.rest.api.extension.core.remote.http.HttpRequest#doExecute(org.apache.http.impl.client.CloseableHttpClient)
     */
    @Override
    protected String doExecute(final HttpClient httpClient) throws IOException, HttpException {
        final HttpPost addPageRequest = new HttpPost(getURLBase() + PORTAL_ADD_PAGE_API);
        addPageRequest.setHeader(API_TOKEN_HEADER, getAPITokenFromCookie());
        final EntityBuilder entityBuilder = EntityBuilder.create();
        entityBuilder.setContentType(ContentType.APPLICATION_JSON);
        entityBuilder.setText(String.format("{\"pageZip\" : \"%s\" }", uploadedFileToken));
        addPageRequest.setEntity(entityBuilder.build());
        final HttpResponse response = httpClient.execute(addPageRequest);
        final int status = response.getStatusLine().getStatusCode();
        String responseContent = contentAsString(response);
        if (status != HttpURLConnection.HTTP_OK) {
            throw new HttpException(responseContent.isEmpty() ? String.format("Add custom page failed with status: %s. Open Engine log for more details.", status) : responseContent);
        }
        return responseContent;
    }


}
