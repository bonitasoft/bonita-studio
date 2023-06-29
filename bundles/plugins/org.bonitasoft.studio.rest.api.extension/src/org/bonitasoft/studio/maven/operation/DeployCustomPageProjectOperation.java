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

import org.apache.http.HttpException;
import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.page.Page;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.operation.DeployCustomPageOperation;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

public class DeployCustomPageProjectOperation extends DeployCustomPageOperation {

    private final ExtensionProjectFileStore fileStore;
    private Page deployedPage;

    public DeployCustomPageProjectOperation(PageAPI pageApi, HttpClientFactory httpClientFactory,
            ExtensionProjectFileStore fileStore) {
        super(pageApi, httpClientFactory);
        this.fileStore = fileStore;
    }

    @Override
    public Page deploy(IProgressMonitor monitor) throws IOException, HttpException {
        deployedPage = super.deploy(monitor);
        return deployedPage;
    }

    public Page getDeployedPage() {
        return deployedPage;
    }

    @Override
    protected File getArchiveFile(IProgressMonitor monitor) {
        try {
            return fileStore.getArchiveFile();
        } catch (IOException e) {
            try {
                BuildCustomPageOperation buildOperation = fileStore.newBuildOperation();
                buildOperation.run(monitor);
                return fileStore.getArchiveFile();
            } catch (CoreException | ReadFileStoreException | IOException e1) {
                throw new RuntimeException(
                        String.format("No archive found for %s.", fileStore.getPageId()), e);
            }
        }
    }

    @Override
    protected String getCustomPageId() {
        return fileStore.getPageId();
    }

    @Override
    protected String getCustomPageLabel() {
        return IDisplayable.toDisplayName(fileStore).orElse("");
    }

    @Override
    protected String getCustomPageType() {
        return fileStore.getContentType();
    }

    @Override
    protected String taskName() {
        return NLS.bind(Messages.deployingCustomPage, getCustomPageLabel());
    }

}
