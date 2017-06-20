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
package org.bonitasoft.studio.importer.bos.operation;

import java.io.File;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.codehaus.groovy.eclipse.dsl.DSLPreferences;
import org.codehaus.groovy.eclipse.dsl.DSLPreferencesInitializer;
import org.codehaus.groovy.eclipse.dsl.GroovyDSLCoreActivator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;

public class ImportWorkspaceApplication implements IApplication {

    public static final String IMPORT_CACHE_FOLDER = ".importCache";
    private final RepositoryAccessor repositoryAccessor = new RepositoryAccessor();

    /*
     * (non-Javadoc)
     * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
     */
    @Override
    public Object start(IApplicationContext context) throws Exception {
        disableGroovyDSL();
        repositoryAccessor.init();
        final String[] args = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
        final Optional<String> scan = Stream.of(args).filter("-scan"::equals).findFirst();

        final Optional<String> export = Stream.of(args).filter(arg -> arg.startsWith("-export=")).findFirst();
        final File targetFolder = new File(System.getProperty("java.io.tmpdir"), IMPORT_CACHE_FOLDER);
        if (export.isPresent()) {
            if (targetFolder.exists()) {
                PlatformUtil.delete(targetFolder, Repository.NULL_PROGRESS_MONITOR);
            }
            targetFolder.mkdirs();
        }

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        if (scan.isPresent() || export.isPresent()) {
            Stream.of(workspace.getRoot().getProjects())
                    .filter(hasBonitaNature())
                    .map(IProject::getName)
                    .map(repositoryAccessor::getRepository)
                    .forEach(repository -> {
                        System.out.println(
                                String.format("$SCAN_PROGRESS_%s:%s:%s", repository.getName(), repository.getVersion(),
                                        findEdition(repository)));
                        export
                                .map(value -> value.split("=")[1])
                                .map(repositories -> repositories.split(":"))
                                .map(Stream::of)
                                .orElse(Stream.empty())
                                .filter(repository.getName()::equals)
                                .findFirst()
                                .ifPresent(repoToExport -> {
                                    System.out.println(
                                            String.format("$EXPORT_PROGRESS_%s",
                                                    String.format(Messages.exportingWorkspace, repository.getName())));
                                    final boolean closed = !repository.getProject().isOpen();
                                    repositoryAccessor.setRepository(repository.getName());
                                    repository
                                            .exportToArchive(
                                                    new File(targetFolder, repository.getName() + ".bos").getAbsolutePath());
                                    if (closed) {
                                        repository.close();
                                    }
                                });

                    });
        }
        return IApplication.EXIT_OK;
    }

    private String findEdition(Repository repository) {
        final DiagramRepositoryStore diagramStore = repository.getRepositoryStore(DiagramRepositoryStore.class);
        final WebFragmentRepositoryStore fragmentStore = repository.getRepositoryStore(WebFragmentRepositoryStore.class);
        if (!fragmentStore.isEmpty()) {
            return "Subscription";
        }
        if (!diagramStore.isEmpty()
                && diagramStore.getChildren().get(0).getContent().getConfigId().toString().contains("sp")) {
            return "Subscription";
        }
        return "Community";
    }

    private void disableGroovyDSL() {
        final IPreferenceStore groovyDSLstore = GroovyDSLCoreActivator.getDefault().getPreferenceStore();
        groovyDSLstore.setDefault(DSLPreferencesInitializer.AUTO_ADD_DSL_SUPPORT, false);
        groovyDSLstore.setValue(DSLPreferencesInitializer.AUTO_ADD_DSL_SUPPORT, false);
        groovyDSLstore.setDefault(DSLPreferencesInitializer.DSLD_DISABLED, true);
        groovyDSLstore.setValue(DSLPreferencesInitializer.DSLD_DISABLED, true);
        groovyDSLstore.setDefault(DSLPreferences.DISABLED_SCRIPTS, false);
        groovyDSLstore.setValue(DSLPreferences.DISABLED_SCRIPTS, false);
    }

    private Predicate<? super IProject> hasBonitaNature() {
        return project -> {
            final boolean closed = !project.isOpen();
            try {
                project.open(Repository.NULL_PROGRESS_MONITOR);
                return project.hasNature(BonitaProjectNature.NATURE_ID);
            } catch (final CoreException e) {
                return false;
            } finally {
                if (closed) {
                    try {
                        project.close(Repository.NULL_PROGRESS_MONITOR);
                    } catch (final CoreException e) {
                        return false;
                    }
                }
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.equinox.app.IApplication#stop()
     */
    @Override
    public void stop() {
        PlatformUI.getWorkbench().close();
    }

}
