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
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.http.HttpException;
import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.page.Page;
import org.bonitasoft.engine.page.PageSearchDescriptor;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

public abstract class DeployCustomPageOperation implements IRunnableWithStatus {

    private final PageAPI pageApi;
    private final HttpClientFactory httpClientFactory;
    private IStatus status = Status.OK_STATUS;

    public DeployCustomPageOperation(PageAPI pageApi, HttpClientFactory httpClientFactory) {
        this.pageApi = pageApi;
        this.httpClientFactory = httpClientFactory;
    }

    public List<Page> deploy(IProgressMonitor monitor) throws IOException, HttpException {
        final String pageId = getCustomPageId();
        final File file = getArchiveFile(monitor);
        final List<Page> existingPages = findDeployedPages(pageId);
        httpClientFactory.newLoginRequest().execute();
        if (!existingPages.isEmpty()) {
            existingPages.stream().forEach(page -> {
                try {
                    final String uploadedFileToken = httpClientFactory.newUploadCustomPageRequest(file).execute();
                    if(checkUploadResponse(uploadedFileToken)) {
                        httpClientFactory.newUpdateCustomPageRequest(uploadedFileToken, page).execute();
                        BonitaStudioLog.info(
                                String.format("%s has been updated in portal.", pageId),
                                EnginePlugin.PLUGIN_ID);
                    }
                } catch (IOException | HttpException e) {
                   throw new RuntimeException(e);
                }
            });
        } else {
            final String uploadedFileToken = httpClientFactory.newUploadCustomPageRequest(file).execute();
            if(checkUploadResponse(uploadedFileToken)) {
                httpClientFactory.newAddCustomPageRequest(uploadedFileToken).execute();
                BonitaStudioLog.info(
                        String.format("%s has been added in portal.", pageId),
                        EnginePlugin.PLUGIN_ID);
            }
        }
        Files.deleteIfExists(file.toPath());
        return findDeployedPages(pageId);
    }

    protected boolean checkUploadResponse(String uploadedFileToken) {
        String[] parsedResult = uploadedFileToken.split("::");
        if(parsedResult.length == 3 ) {
            String permissions = parsedResult[2].substring(1,parsedResult[2].length()-1);
            List<String> missingAPIExtensions = Stream.of(permissions.split(","))
                    .map(String::trim)
                    .filter(entry -> entry.startsWith("<"))
                    .collect(Collectors.toList());
            if(!missingAPIExtensions.isEmpty()) {
                status = ValidationStatus.error(String.format(Messages.missingRestAPIStatus, getCustomPageLabel(), missingAPIExtensions.stream()
                        .collect(Collectors.joining(", "))));
                return false;
            }
        }
        return true;
    }

    protected abstract File getArchiveFile(IProgressMonitor monitor);

    protected abstract String getCustomPageId();
    
    protected abstract String getCustomPageLabel();
    
    protected abstract String getCustomPageType();

    protected List<Page> findDeployedPages(final String pageId) {
        try {
            SearchResult<Page> searchResult = pageApi.searchPages(new SearchOptionsBuilder(0, Integer.MAX_VALUE)
                    .filter(PageSearchDescriptor.NAME, pageId)
                    .done());
            return searchResult.getResult();
        } catch (SearchException e) {
            BonitaStudioLog.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.setTaskName(taskName());
        try {
            status = new Status(IStatus.OK, EnginePlugin.PLUGIN_ID,
                    String.format(Messages.deploySuccessMessage, getCustomPageLabel(),getCustomPageType()));
            deploy(monitor);
        } catch (IOException | HttpException e) {
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,
                    NLS.bind(Messages.deployFailedMessage, getCustomPageLabel()), e);
            throw new InvocationTargetException(e);
        } finally {
            monitor.worked(1);
        }
    }

    protected abstract String taskName();

    @Override
    public IStatus getStatus() {
        return status;
    }

}
