/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.la.application.core;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.Application;
import org.bonitasoft.engine.business.application.ApplicationSearchDescriptor;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.exception.DeletionException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class DeleteApplicationContainerRunnable implements IRunnableWithProgress {

    private final ApplicationAPI applicationAPI;
    private final ApplicationNodeContainer applicationNodeContainer;
    private IStatus status = ValidationStatus.ok();
    private boolean ignoreErrors = false;

    public DeleteApplicationContainerRunnable(ApplicationAPI applicationAPI,
            ApplicationNodeContainer applicationNodeContainer) {
        this.applicationAPI = requireNonNull(applicationAPI);
        this.applicationNodeContainer = requireNonNull(applicationNodeContainer);
    }

    public DeleteApplicationContainerRunnable ignoreErrors() {
        this.ignoreErrors = true;
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        applicationNodeContainer.getApplications().stream().forEach(applicationNode -> {
            monitor.setTaskName(String.format(Messages.deletingApplication, applicationNode.getDisplayName()));
            status = delete(applicationNode);
            monitor.worked(1);
        });
    }

    public IStatus getStatus() {
        return status;
    }

    private SearchOptions withToken(String token) {
        return new SearchOptionsBuilder(0, 1).filter(ApplicationSearchDescriptor.TOKEN, token).done();
    }

    private IStatus delete(ApplicationNode applicationNode) {
        try {
            final long applicationId = applicationAPI.searchApplications(withToken(applicationNode.getToken()))
                    .getResult().stream()
                    .mapToLong(Application::getId)
                    .findFirst()
                    .orElseThrow(ApplicationDescriptorNotFoundException::new);
            applicationAPI.deleteApplication(applicationId);
        } catch (final SearchException | ApplicationDescriptorNotFoundException e) {
            return ignoreErrors ? Status.OK_STATUS : new Status(IStatus.ERROR, LivingApplicationPlugin.PLUGIN_ID,
                    String.format("Cannot find application descriptor with token '%s'", applicationNode.getToken()), e);
        } catch (final DeletionException e) {
            return new Status(IStatus.ERROR, LivingApplicationPlugin.PLUGIN_ID,
                    String.format("Failed to delete application descriptor: '%s'", e.getContext()), e);
        }
        return Status.OK_STATUS;
    }

}
