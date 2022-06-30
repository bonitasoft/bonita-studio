/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.provider;

import static com.google.common.collect.Iterables.toArray;
import static com.google.common.io.Files.toByteArray;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.jdt.CreateJarOperation;
import org.bonitasoft.studio.groovy.GroovyPlugin;
import org.bonitasoft.studio.groovy.Messages;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.osgi.util.NLS;

public class GroovyScriptBarResourceProvider implements BARResourcesProvider {

    @Inject
    private RepositoryAccessor repositoryAccessor;

    @Override
    public IStatus addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process,
            final Configuration configuration) throws Exception {
        if (configuration != null) {
            addGroovyScriptDependenciesToClasspath(builder, configuration, configuration.getProcessDependencies());
        }
        addProvidedScriptsToClasspath(builder);
        return Status.OK_STATUS;
    }

    private void addProvidedScriptsToClasspath(final BusinessArchiveBuilder builder)
            throws InvocationTargetException, InterruptedException, IOException {
        final Set<ICompilationUnit> compilationUnits = new HashSet<>();
        final ProvidedGroovyRepositoryStore providedStore = repositoryAccessor
                .getRepositoryStore(ProvidedGroovyRepositoryStore.class);
        for (final GroovyFileStore file : providedStore.getChildren()) {
            if (!file.getName().startsWith(GroovyFileStore.EXPRESSION_SCRIPT_NAME)) {
                compilationUnits.add(file.getCompilationUnit());
            }
        }
        addGroovyCompilationUnitToClasspath(builder, compilationUnits,
                ProvidedGroovyRepositoryStore.EXPORTED_PROVIDED_JAR_NAME);
    }

    protected void addGroovyScriptDependenciesToClasspath(
            final BusinessArchiveBuilder builder,
            final Configuration configuration,
            final List<FragmentContainer> containers) throws InvocationTargetException, InterruptedException, IOException {
        final Set<ICompilationUnit> compilationUnits = collectCompilationUnits(configuration, containers);
        addGroovyCompilationUnitToClasspath(builder, compilationUnits, GroovyRepositoryStore.EXPORTED_JAR_NAME);
    }

    protected void addGroovyCompilationUnitToClasspath(final BusinessArchiveBuilder builder,
            final Set<ICompilationUnit> compilationUnits,
            final String exportedProvidedJarName) throws InvocationTargetException, InterruptedException, IOException {
        for (ICompilationUnit compilationUnit : compilationUnits) {
            List<IMarker> errorMarkers = new ArrayList<>();
            try {
                IMarker[] markers = compilationUnit.getResource().findMarkers(null, true, IResource.DEPTH_ZERO);
                if (markers != null) {
                    for (IMarker marker : markers) {
                        if (Objects.equals(marker.getAttribute(IMarker.SEVERITY), IMarker.SEVERITY_ERROR)) {
                            errorMarkers.add(marker);
                        }
                    }
                    if (!errorMarkers.isEmpty()) {
                        MultiStatus errorStatus = new MultiStatus(GroovyPlugin.PLUGIN_ID, 0, "", null);
                        for (IMarker e : errorMarkers) {
                            errorStatus.add(new Status(IStatus.ERROR, GroovyPlugin.PLUGIN_ID,
                                    String.format("%s (line %s, col %s): %s", compilationUnit.getElementName(),
                                            e.getAttribute(IMarker.LINE_NUMBER),
                                            e.getAttribute(IMarker.CHAR_START), e.getAttribute(IMarker.MESSAGE))));
                            throw new JarExportFailedException(
                                    Messages.errorBuildingJarForGroovyScriptsForProcess + " ",
                                    errorStatus);
                        }
                    }
                }
            } catch (CoreException e1) {
                BonitaStudioLog.error(e1);
            }
        }

        if (!compilationUnits.isEmpty()) {
            final File targetJar = new File(ProjectUtil.getBonitaStudioWorkFolder(), exportedProvidedJarName);
            final CreateJarOperation createJarOperation = new CreateJarOperation(targetJar,
                    toArray(compilationUnits, ICompilationUnit.class));
            createJarOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
            final IStatus status = createJarOperation.getStatus();
            if (status.getSeverity() == IStatus.ERROR || status.getSeverity() == IStatus.CANCEL) {
                targetJar.delete();
                if (GroovyRepositoryStore.EXPORTED_JAR_NAME.equals(exportedProvidedJarName)) {
                    throw new JarExportFailedException(
                            Messages.errorBuildingJarForGroovyScriptsForProcess + " ",
                            status);
                } else {
                    throw new JarExportFailedException(
                            NLS.bind(Messages.errorBuildingJarForProvidedGroovyScriptsForProcess + " ",
                                    org.bonitasoft.studio.common.Messages.bosProductName),
                            status);
                }
            }
            builder.addClasspathResource(new BarResource(targetJar.getName(), toByteArray(targetJar)));
            targetJar.delete();
        }
    }

    private Set<ICompilationUnit> collectCompilationUnits(final Configuration configuration,
            final List<FragmentContainer> containers) {
        final Set<ICompilationUnit> result = new HashSet<>();
        if (configuration != null) {
            final GroovyRepositoryStore store = repositoryAccessor.getRepositoryStore(GroovyRepositoryStore.class);
            for (final FragmentContainer fc : containers) {
                for (final EObject fragment : ModelHelper.getAllItemsOfType(fc, ConfigurationPackage.Literals.FRAGMENT)) {
                    if (((Fragment) fragment).getType().equals(FragmentTypes.GROOVY_SCRIPT)) {
                        if (((Fragment) fragment).isExported()) {
                            final IResource file = store.getResource()
                                    .findMember(Path.fromOSString(((Fragment) fragment).getValue()));
                            if (file instanceof IFile && file.exists()) {
                                result.add(JavaCore.createCompilationUnitFrom((IFile) file));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

}
