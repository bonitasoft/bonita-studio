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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.http.HttpException;
import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.page.Page;
import org.bonitasoft.engine.page.PageNotFoundException;
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

    private static final Set<String> PAGE_TYPES = Set.of("form", "page", "layout");
    private final PageAPI pageApi;
    private final HttpClientFactory httpClientFactory;
    private IStatus status = Status.OK_STATUS;

    protected DeployCustomPageOperation(PageAPI pageApi, HttpClientFactory httpClientFactory) {
        this.pageApi = pageApi;
        this.httpClientFactory = httpClientFactory;
    }

    public Page deploy(IProgressMonitor monitor) throws IOException, HttpException {
        final String pageId = getCustomPageId();
        final File file = getArchiveFile(monitor);
        var existingPage = findCustomPage(pageId);
        httpClientFactory.newLoginRequest().execute();
        if (existingPage != null) {
            if (!hasCompatibleContentType(existingPage)) {
                status = Status.error(NLS.bind(
                        Messages.invalidPageContentType,
                        new String[] { pageId, existingPage.getContentType(), getCustomPageType() }));
                return existingPage;
            }
            try {
                final String uploadedFileToken = httpClientFactory.newUploadCustomPageRequest(file).execute();
                if (checkUploadResponse(uploadedFileToken)) {
                    httpClientFactory.newUpdateCustomPageRequest(uploadedFileToken, existingPage).execute();
                    BonitaStudioLog.info(
                            String.format("%s has been updated.", pageId),
                            EnginePlugin.PLUGIN_ID);
                }
            } catch (IOException | HttpException e) {
                throw new RuntimeException(e);
            }
        } else {
            final String uploadedFileToken = httpClientFactory.newUploadCustomPageRequest(file).execute();
            if (checkUploadResponse(uploadedFileToken)) {
                httpClientFactory.newAddCustomPageRequest(uploadedFileToken).execute();
                BonitaStudioLog.info(
                        String.format("%s has been added.", pageId),
                        EnginePlugin.PLUGIN_ID);
            }
        }
        return findCustomPage(pageId);
    }

    private boolean hasCompatibleContentType(Page page) {
        String currentContentType = getCustomPageType();
        String existingContentType = page.getContentType();
        return Objects.equals(existingContentType, currentContentType)
                || (PAGE_TYPES.contains(existingContentType) && PAGE_TYPES.contains(currentContentType));
    }

    protected boolean checkUploadResponse(String uploadedFileToken) {
        String[] parsedResult = uploadedFileToken.split("::");
        if (parsedResult.length == 3) {
            String permissions = parsedResult[2].substring(1, parsedResult[2].length() - 1);
            List<String> missingAPIExtensions = Stream.of(permissions.split(","))
                    .map(String::trim)
                    .filter(entry -> entry.startsWith("<"))
                    .collect(Collectors.toList());
            if (!missingAPIExtensions.isEmpty()) {
                status = ValidationStatus.error(
                        String.format(Messages.missingRestAPIStatus, getCustomPageLabel(), missingAPIExtensions.stream()
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

    public Page findCustomPage(final String pageId) {
        try {
            return pageApi.getPageByName(pageId);
        } catch (final PageNotFoundException e) {
            return null;
        }
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.setTaskName(taskName());
        try {
            status = new Status(IStatus.OK, EnginePlugin.PLUGIN_ID,
                    String.format(Messages.deploySuccessMessage, getCustomPageLabel(), getCustomPageType()));
            deploy(monitor);
        } catch (HttpException e) {
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,
                    NLS.bind(Messages.deployFailedMessage, getCustomPageLabel()), e);
        } catch (IOException e) {
            status = new Status(IStatus.ERROR, EnginePlugin.PLUGIN_ID,
                    NLS.bind(Messages.deployFailedMessage, getCustomPageLabel()), e);
            throw new InvocationTargetException(e);
        }finally {
            monitor.worked(1);
        }
    }

    protected abstract String taskName();

    @Override
    public IStatus getStatus() {
        return status;
    }

}
