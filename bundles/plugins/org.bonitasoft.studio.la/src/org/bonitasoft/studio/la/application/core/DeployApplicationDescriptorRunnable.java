/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.core;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.business.application.ApplicationImportPolicy;
import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.exception.AlreadyExistsException;
import org.bonitasoft.engine.exception.ImportException;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.xml.sax.SAXException;

public class DeployApplicationDescriptorRunnable implements IRunnableWithProgress {

    protected final ApplicationAPI applicationAPI;
    protected final ApplicationNodeContainer applicationNodeContainer;
    protected MultiStatus status = new MultiStatus(LivingApplicationPlugin.PLUGIN_ID, 0, "", null);
    protected final ApplicationNodeContainerConverter converter;

    public DeployApplicationDescriptorRunnable(ApplicationAPI applicationAPI,
            ApplicationNodeContainer applicationNodeContainer,
            ApplicationNodeContainerConverter converter) {
        this.applicationAPI = requireNonNull(applicationAPI);
        this.applicationNodeContainer = requireNonNull(applicationNodeContainer);
        this.converter = requireNonNull(converter);
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.deployingLivingApplication, IProgressMonitor.UNKNOWN);
        status.add(deleteBeforeDeploy(monitor));
        if (status.isOK()) {
            deployApplications(monitor);
        }
    }

    protected void deployApplications(IProgressMonitor monitor) {
        try {
            applicationAPI.importApplications(converter.marshallToXML(applicationNodeContainer),
                    ApplicationImportPolicy.FAIL_ON_DUPLICATES);
            monitor.worked(1);
            if (status.isMultiStatus()) {
                final MultiStatus mStatus = status;
                applicationNodeContainer.getApplications().stream()
                        .map(ApplicationNode::getDisplayName)
                        .map(name -> new Status(IStatus.OK, LivingApplicationPlugin.PLUGIN_ID,
                                String.format(Messages.applicationDescriptorDeployed,
                                        name)))
                        .forEach(mStatus::add);
            }
        } catch (AlreadyExistsException | ImportException | IOException | JAXBException | SAXException e) {
            if (status.isMultiStatus()) {
                status.add(new Status(IStatus.ERROR, LivingApplicationPlugin.PLUGIN_ID,
                        "Failed to deploy application.", e));
            }
        } finally {
            monitor.done();
        }
    }

    public IStatus deleteBeforeDeploy(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        final DeleteApplicationContainerRunnable deleteApplicationRunnable = new DeleteApplicationContainerRunnable(
                applicationAPI,
                applicationNodeContainer).ignoreErrors();
        deleteApplicationRunnable.run(monitor);
        return deleteApplicationRunnable.getStatus();
    }

    public MultiStatus getStatus() {
        return status;
    }

}
