/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.engine.http;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

public class UploadCustomPageRequest extends HttpRequest<String> {

    private static final String PORTAL_PAGE_UPLOAD_API = "portal/pageUpload";
    private final File file;

    public UploadCustomPageRequest(final File file, final HttpClientFactory httpClientFactory) {
        super(httpClientFactory);
        this.file = file;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.rest.api.extension.core.remote.http.HttpRequest#doExecute(org.apache.http.impl.client.CloseableHttpClient)
     */
    @Override
    protected String doExecute(final HttpClient httpClient) throws IOException, HttpException {
        final HttpPost uploadFileRequest = new HttpPost(getURLBase() + PORTAL_PAGE_UPLOAD_API);
        uploadFileRequest.setHeader(API_TOKEN_HEADER, getAPITokenFromCookie());
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
        uploadFileRequest.setEntity(builder.build());
        final HttpResponse response = httpClient.execute(uploadFileRequest);
        final String result = contentAsString(response);
        final int uploadStatus = response.getStatusLine().getStatusCode();
        if (isNullOrEmpty(result)) {
            throw new HttpException(String.format("Custom page upload failed with status: %s. Open Engine log for more details.", uploadStatus));
        }
        return result;
    }

}
