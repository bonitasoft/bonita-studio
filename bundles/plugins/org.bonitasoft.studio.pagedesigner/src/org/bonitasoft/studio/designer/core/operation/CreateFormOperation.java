/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.operation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * @author Romain Bioteau
 */
public class CreateFormOperation implements IRunnableWithProgress {

    private JSONObject responseObject;
    private final PageDesignerURLFactory pageDesignerURLBuilder;
    private String formName = "newPage";

    public CreateFormOperation(final PageDesignerURLFactory pageDesignerURLBuilder) {
        this.pageDesignerURLBuilder = pageDesignerURLBuilder;
    }

    public void setFormName(final String formName) {
        this.formName = formName;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.creatingNewForm, IProgressMonitor.UNKNOWN);
        responseObject = null;
        JSONObject jsonObject;
        try {
            jsonObject = createBody();
        } catch (final JSONException e) {
            throw new InvocationTargetException(e, "Failed to create body to create a new page.");
        }
        try {
            final String source = doPost(pageDesignerURLBuilder.newPage(), jsonObject);
            responseObject = new JSONObject(source);
        } catch (final ResourceException e) {
            throw new InvocationTargetException(e, "Failed to post request to create a new page.");
        } catch (final MalformedURLException e) {
            throw new InvocationTargetException(e, "Failed to create new page URL.");
        } catch (final IOException e) {
            throw new InvocationTargetException(e, "Failed to retrieve new page POST response.");
        } catch (final JSONException e) {
            throw new InvocationTargetException(e, "Failed to build a JSON representation of the response.");
        } catch (final URISyntaxException e) {
            throw new InvocationTargetException(e, "Failed to build POST URL.");
        }
    }

    protected String doPost(final URL url, final JSONObject jsonObject) throws URISyntaxException, MalformedURLException, IOException {
        return new ClientResource(url.toURI()).post(new JsonRepresentation(jsonObject)).getText();
    }

    public String getNewPageId() {
        if (responseObject == null) {
            throw new IllegalStateException("Response is null.");
        }
        try {
            return responseObject.getString("id");
        } catch (final JSONException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public String getNewPageName() {
        if (responseObject == null) {
            throw new IllegalStateException("Response is null.");
        }
        try {
            return responseObject.getString("name");
        } catch (final JSONException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    private JSONObject createBody() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", formName);
        jsonObject.put("rows", Arrays.asList(new ArrayList<Object>()));
        return jsonObject;
    }

}
