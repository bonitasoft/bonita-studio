/**
 * Copyright (C) 2014 BonitaSoft S.A.
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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.designer.core.FormScope;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.converter.ToWebContract;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * @author Romain Bioteau
 */
public class CreateFormFromContractOperation implements IRunnableWithProgress {

    private JSONObject responseObject;
    private final PageDesignerURLFactory pageDesignerURLBuilder;
    private String formName = "newPage";
    private final Contract contract;
    private final FormScope formScope;

    public CreateFormFromContractOperation(final PageDesignerURLFactory pageDesignerURLBuilder, final String formName, final Contract contract,
            final FormScope formScope) {
        checkArgument(pageDesignerURLBuilder != null);
        checkArgument(!isNullOrEmpty(formName));
        checkArgument(contract != null);
        this.pageDesignerURLBuilder = pageDesignerURLBuilder;
        this.contract = contract;
        this.formName = formName;
        this.formScope = formScope;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.creatingNewForm, IProgressMonitor.UNKNOWN);
        responseObject = null;
        try {
            final String source = doPost(pageDesignerURLBuilder.newPageFromContract(formScope, formName), new ToWebContract().apply(contract));
            responseObject = new JSONObject(source);
        } catch (ResourceException | IOException | JSONException | URISyntaxException e) {
            throw new InvocationTargetException(e, "Failed to post request to create a new page.");
        }
    }

    protected String doPost(final URL url, final org.bonitasoft.web.designer.model.contract.Contract contract) throws URISyntaxException,
            MalformedURLException, IOException {
        return new ClientResource(url.toURI()).post(new JacksonRepresentation<org.bonitasoft.web.designer.model.contract.Contract>(contract)).getText();
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

}
