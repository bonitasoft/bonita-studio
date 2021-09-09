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
import static org.bonitasoft.studio.ui.util.StatusCollectors.toMultiStatus;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.api.ApplicationAPI;
import org.bonitasoft.engine.api.ImportError;
import org.bonitasoft.engine.api.ImportStatus;
import org.bonitasoft.engine.api.result.StatusCode;
import org.bonitasoft.engine.business.application.ApplicationImportPolicy;
import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.engine.exception.AlreadyExistsException;
import org.bonitasoft.engine.exception.ImportException;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.xml.sax.SAXException;

public class DeployApplicationDescriptorOperation implements IRunnableWithProgress {

    protected ApplicationAPI applicationAPI;
    protected ApplicationNodeContainer applicationNodeContainer;
    protected MultiStatus status = new MultiStatus(LivingApplicationPlugin.PLUGIN_ID, 0, "", null);
    protected ApplicationNodeContainerConverter converter;
    private PageDependencyResolver pageDependencyResolver;
    private ThemeDependencyResolver themeDependencyResolver;

    public DeployApplicationDescriptorOperation(ApplicationAPI applicationAPI,
            ApplicationNodeContainer applicationNodeContainer,
            ApplicationNodeContainerConverter converter) {
        this.applicationAPI = requireNonNull(applicationAPI);
        this.applicationNodeContainer = requireNonNull(applicationNodeContainer);
        this.converter = requireNonNull(converter);

    }

    public DeployApplicationDescriptorOperation withDependencies(PageDependencyResolver pageDependencyResolver,
            ThemeDependencyResolver themeDependencyResolver) {
        this.pageDependencyResolver = pageDependencyResolver;
        this.themeDependencyResolver = themeDependencyResolver;
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        final List<IRunnableWithStatus> dependencies = findDependencies(applicationNodeContainer);
        monitor.beginTask(Messages.deployingLivingApplication, dependencies.size() + 1);
        status.add(deleteBeforeDeploy(monitor));
        if (status.isOK()) {
            status = dependencies.stream()
                    .map(deploy(monitor))
                    .collect(toMultiStatus());
            deployApplications(monitor);
        }
    }

    private Function<? super IRunnableWithStatus, ? extends IStatus> deploy(IProgressMonitor monitor) {
        return deployable -> {
            try {
                deployable.run(monitor);
            } catch (InvocationTargetException | InterruptedException e) {
                return new Status(IStatus.ERROR, LivingApplicationPlugin.PLUGIN_ID,
                        "Failed to deploy", e);
            }
            return deployable.getStatus();
        };
    }

    protected List<IRunnableWithStatus> findDependencies(ApplicationNodeContainer applicationNodeContainer) {
        List<String> profilesPages = getProfilePages();
        Stream.Builder<IRunnableWithStatus> deployables = Stream.builder();
        if (pageDependencyResolver != null) {
            Stream.Builder<String> builder = Stream.builder();
            //Application Pages
            applicationNodeContainer
                    .getApplications()
                    .stream()
                    .map(ApplicationNode::getApplicationPages)
                    .flatMap(Collection::stream)
                    .map(ApplicationPageNode::getCustomPage)
                    .forEach(builder::add);
            //Layout
            applicationNodeContainer
                    .getApplications()
                    .stream()
                    .map(ApplicationNode::getLayout)
                    .forEach(builder::add);
            pageDependencyResolver.prepareDeployOperation(builder.build()
                    .filter(customPage -> !profilesPages.contains(customPage)))
                    .forEach(deployables::add);
        }
        if (themeDependencyResolver != null) {
            themeDependencyResolver.prepareDeployOperation(applicationNodeContainer
                    .getApplications()
                    .stream()
                    .map(ApplicationNode::getTheme))
                    .forEach(deployables::add);
        }
        return deployables.build().collect(Collectors.toList());
    }

    protected List<String> getProfilePages() {
        return Collections.emptyList();
    }

    protected void deployApplications(IProgressMonitor monitor) {
        try {
            List<ImportStatus> importStatus = applicationAPI.importApplications(
                    converter.marshallToXML(applicationNodeContainer),
                    ApplicationImportPolicy.FAIL_ON_DUPLICATES);
            monitor.worked(1);
            if (status.isMultiStatus()) {
                final MultiStatus mStatus = status;
                importStatus.stream()
                        .flatMap(s -> s.getErrors().stream()
                                .map(errorToStatus(displayNameForToken(s.getName(), applicationNodeContainer))))
                        .forEach(mStatus::add);
                applicationNodeContainer.getApplications().stream()
                        .map(ApplicationNode::getToken)
                        .map(name -> new Status(IStatus.OK, LivingApplicationPlugin.PLUGIN_ID,
                                StatusCode.LIVING_APP_DEPLOYMENT.ordinal(),
                                name, null))
                        .forEach(mStatus::add);
            }
        } catch (AlreadyExistsException | ImportException | IOException | JAXBException | SAXException e) {
            BonitaStudioLog.error(e);
            if (status.isMultiStatus()) {
                status.add(new Status(IStatus.ERROR, LivingApplicationPlugin.PLUGIN_ID,
                        Messages.deployFailed, e));
            }
        } finally {
            monitor.done();
        }
    }

    private String displayNameForToken(String token, ApplicationNodeContainer applicationNodeContainer) {
        return applicationNodeContainer.getApplications().stream()
                .filter(node -> Objects.equals(token, node.getToken()))
                .map(ApplicationNode::getDisplayName)
                .findFirst()
                .orElse(token);
    }

    private Function<? super ImportError, ? extends IStatus> errorToStatus(String applicationDisplayName) {
        return s -> {
            switch (s.getType()) {
                case PROFILE:
                    return ValidationStatus
                            .warning(String.format(Messages.profileNotFound, applicationDisplayName, s.getName()));
                case LAYOUT:
                    return ValidationStatus
                            .warning(String.format(Messages.layoutNotFound, applicationDisplayName, s.getName()));
                case THEME:
                    return ValidationStatus
                            .warning(String.format(Messages.themeNotFound, applicationDisplayName, s.getName()));
                case APPLICATION_PAGE:
                    return ValidationStatus.warning(
                            String.format(Messages.appPageTokenNotFound, applicationDisplayName, s.getName()));
                case PAGE:
                    return ValidationStatus.warning(
                            String.format(Messages.applicationPageNotFound, applicationDisplayName, s.getName()));
                default:
                    return ValidationStatus.error(s.getName());
            }
        };
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
