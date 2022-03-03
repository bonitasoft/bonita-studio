/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.operation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.http.HttpException;
import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.page.Page;
import org.bonitasoft.engine.page.PageNotFoundException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;

public class DeployCustomPageOperation implements IRunnableWithProgress {

    private final BOSEngineManager engineManager;
    private final HttpClientFactory httpClientFactory;
    private final CustomPageProjectFileStore fileStore;
    private Page deployedPage;
    private IStatus status = Status.OK_STATUS;

    public DeployCustomPageOperation(BOSEngineManager engineManager, HttpClientFactory httpClientFactory,
            CustomPageProjectFileStore fileStore) {
        this.engineManager = engineManager;
        this.httpClientFactory = httpClientFactory;
        this.fileStore = fileStore;
    }

    public Page deploy() throws IOException, HttpException {
        final String pageId = fileStore.getPageId();
        final File file = fileStore.getArchiveFile();
        final Page existingPage = findCustomPage(pageId);
        httpClientFactory.newLoginRequest().execute();
        final String uploadedFileToken = httpClientFactory.newUploadCustomPageRequest(file).execute();
        if (existingPage != null) {
            httpClientFactory.newUpdateCustomPageRequest(uploadedFileToken, existingPage).execute();
            BonitaStudioLog.info(
                    String.format("%s has been updated in portal.", fileStore.getDisplayName()),
                    RestAPIExtensionActivator.PLUGIN_ID);
        } else {
            httpClientFactory.newAddCustomPageRequest(uploadedFileToken).execute();
            BonitaStudioLog.info(
                    String.format("%s has been added in portal.", fileStore.getDisplayName()),
                    RestAPIExtensionActivator.PLUGIN_ID);
        }
        return findCustomPage(pageId);
    }

    protected Page findCustomPage(final String pageId) {
        APISession session = null;
        Page existingPage = null;
        try {
            session = engineManager.loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
            final PageAPI pageAPI = engineManager.getPageAPI(session);
            try {
                existingPage = pageAPI.getPageByName(pageId);
            } catch (final PageNotFoundException e) {
                return null;
            }
        } catch (LoginException | BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
            BonitaStudioLog.error(e);
        } finally {
            if (session != null) {
                engineManager.logoutDefaultTenant(session);
            }
        }
        return existingPage;
    }


    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(NLS.bind(Messages.deployingCustomPage, fileStore.getDisplayName()),
                IProgressMonitor.UNKNOWN);
        try {
            deployedPage = deploy();
        } catch (IOException | HttpException e) {
            status = new Status(IStatus.ERROR, RestAPIExtensionActivator.PLUGIN_ID,
                    NLS.bind(Messages.deployFailedMessage, fileStore.getName()), e);
        }
    }

    public Page getDeployedPage() {
        return deployedPage;
    }

    public IStatus getStatus() {
        return status;
    }

}
