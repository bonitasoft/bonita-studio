/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.engine.operation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.apache.http.HttpException;
import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.page.Page;
import org.bonitasoft.engine.page.PageNotFoundException;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

public abstract class DeployCustomPageOperation implements IRunnableWithStatus {

    private final PageAPI pageApi;
    private final HttpClientFactory httpClientFactory;
    private Page deployedPage;
    private IStatus status = Status.OK_STATUS;

    public DeployCustomPageOperation(PageAPI pageApi, HttpClientFactory httpClientFactory) {
        this.pageApi = pageApi;
        this.httpClientFactory = httpClientFactory;
    }

    public Page deploy() throws IOException, HttpException {
        final String pageId = getPageId();
        final File file = getArchiveFile();
        final Optional<Page> existingPage = findCustomPage(pageId);
        httpClientFactory.newLoginRequest().execute();
        final String uploadedFileToken = httpClientFactory.newUploadCustomPageRequest(file).execute();
        if (existingPage.isPresent()) {
            httpClientFactory.newUpdateCustomPageRequest(uploadedFileToken, existingPage.get()).execute();
            BonitaStudioLog.info(
                    String.format("%s has been updated in portal.", pageId),
                    EnginePlugin.PLUGIN_ID);
        } else {
            httpClientFactory.newAddCustomPageRequest(uploadedFileToken).execute();
            BonitaStudioLog.info(
                    String.format("%s has been added in portal.", pageId),
                    EnginePlugin.PLUGIN_ID);
        }
        return findCustomPage(pageId).orElseThrow(RuntimeException::new);
    }

    protected abstract File getArchiveFile();

    protected abstract String getPageId();

    protected Optional<Page> findCustomPage(final String pageId) {
        Optional<Page> page = Optional.empty();
        try {
            page = Optional.ofNullable(pageApi.getPageByName(pageId));
        } catch (final PageNotFoundException e) {
            //returns empty optional
        }
        return page;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.setTaskName(taskName());
        try {
            deployedPage = deploy();
            status = new Status(IStatus.OK, EnginePlugin.PLUGIN_ID,
                    String.format(Messages.deploySuccessMessage, getPageId()));
        } catch (IOException | HttpException e) {
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,
                    NLS.bind(Messages.deployFailedMessage, getPageId()), e);
            throw new InvocationTargetException(e);
        } finally {
            monitor.worked(1);
        }
    }

    protected abstract String taskName();

    public Page getDeployedPage() {
        return deployedPage;
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
