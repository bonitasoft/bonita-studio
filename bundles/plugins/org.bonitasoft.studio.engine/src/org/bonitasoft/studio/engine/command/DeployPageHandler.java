/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.command;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.inject.Named;

import org.bonitasoft.engine.api.PageAPI;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.bar.CustomPageBarResourceFactory;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.DeployPageRunnable;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class DeployPageHandler {

    @Execute
    public IStatus execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            RepositoryAccessor repositoryAccessor,
            @Named("name") String pageName,
            @Named("disablePopup") String disablePopup) {
        GetApiSessionOperation apiSessionOperation = new GetApiSessionOperation();
        try {
            APISession apiSession = apiSessionOperation.execute();
            BOSEngineManager bosEngineManager = BOSEngineManager.getInstance();
            PageAPI pageAPI = bosEngineManager.getPageAPI(apiSession);
            Optional<WebPageFileStore> fStore = findFileStore(repositoryAccessor, pageName);
            if (fStore.isPresent()) {
                DeployPageRunnable operation = new DeployPageRunnable(pageAPI,
                        new HttpClientFactory(),
                        new CustomPageBarResourceFactory(PageDesignerURLFactory.INSTANCE),
                        fStore.get());
                if(disablePopup == null || !Boolean.valueOf(disablePopup)) {
                    PlatformUI.getWorkbench().getProgressService().run(true, false, operation);
                    displayDeploymentResult(activeShell, operation);
                }else {
                    operation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                    return operation.getStatus();
                }
            } else {
                throw new IllegalArgumentException(String.format("The page %s doesn't exist", pageName));
            }
        } catch (BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
            throw new RuntimeException("An error occurred while retrieving page API", e);
        } catch (InvocationTargetException | InterruptedException e) {
            throw new RuntimeException(String.format("An error occurred while deploying page %s", pageName), e);
        } finally {
            apiSessionOperation.logout();
        }
        return ValidationStatus.ok();
    }

    private void displayDeploymentResult(Shell activeShell, IRunnableWithStatus operation) {
        IStatus status = operation.getStatus();
        if (status.isOK()) {
            MessageDialog.openInformation(activeShell, Messages.deployDoneTitle, status.getMessage());
        } else {
            MessageDialog.openError(activeShell, Messages.deployFailedTitle, status.getMessage());
        }
    }

    private Optional<WebPageFileStore> findFileStore(RepositoryAccessor repositoryAccessor, String pageName) {
        return Optional.ofNullable(repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChild(pageName, true));
    }
}
