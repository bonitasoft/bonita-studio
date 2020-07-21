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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.connector.model.definition.util.ConnectorDefinitionAdapterFactory;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.model.parameter.util.ParameterAdapterFactory;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
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
        repositoryAccessor.init();
        final String[] args = (String[]) context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
        final Optional<String> scan = Stream.of(args).filter("-scan"::equals).findFirst();
        final Optional<String> export = Stream.of(args).filter(arg -> arg.startsWith("-export=")).findFirst();

        final File exportTargetFolder = new File(System.getProperty("java.io.tmpdir"), IMPORT_CACHE_FOLDER);
        if (export.isPresent()) {
            if (exportTargetFolder.exists()) {
                PlatformUtil.delete(exportTargetFolder, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
            exportTargetFolder.mkdirs();
        }

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        if (scan.isPresent() || export.isPresent()) {
            Stream.of(workspace.getRoot().getProjects())
                    .filter(hasBonitaNature())
                    .map(IProject::getName)
                    .map(repositoryAccessor::getRepository)
                    .forEach(repository -> {
                        System.out.println(
                                String.format("$SCAN_PROGRESS_%s:%s:%s:%s", repository.getName(),
                                        repository.getVersion(),
                                        findEdition(repository), connected(repository)));
                        export
                                .map(value -> value.split("=")[1])
                                .map(repositories -> repositories.split(":"))
                                .map(Stream::of)
                                .orElse(Stream.empty())
                                .filter(repository.getName()::equals)
                                .findFirst()
                                .ifPresent(repoToExport -> migrateAndExportRepository(exportTargetFolder, repository));

                    });
        }
        return IApplication.EXIT_OK;
    }

    private void migrateAndExportRepository(final File targetFolder, AbstractRepository repository) {
        System.out.println(
                String.format("$EXPORT_PROGRESS_%s",
                        String.format(Messages.exportingWorkspace, repository.getName())));
        final boolean closed = !repository.getProject().isOpen();
        repositoryAccessor.setRepository(repository.getName());
        try {
            repository.migrate(AbstractRepository.NULL_PROGRESS_MONITOR);
            repository
                    .exportToArchive(
                            new File(targetFolder, repository.getName() + ".bos")
                                    .getAbsolutePath());

        } catch (CoreException | MigrationException e) {
            BonitaStudioLog.error(e);
        } finally {
            if (closed) {
                repository.close();
            }
        }
    }

    private String findEdition(AbstractRepository repository) {
        File repoDir = repository.getProject().getLocation().toFile();
        File fragRepo = new File(repoDir, WebFragmentRepositoryStore.WEB_FRAGMENT_REPOSITORY_NAME);
        if (fragRepo.listFiles(f -> !f.getName().startsWith(".") && f.isDirectory()).length > 0) {
            return "Subscription";
        }
        File diagramRepo = new File(repoDir, "diagrams");
        File[] diagrams = diagramRepo.listFiles(f -> !f.getName().startsWith(".") && f.isFile());
        if (diagrams.length > 0
                && isSPDiagram(diagrams[0])) {
            return "Subscription";
        }
        return "Community";
    }

    private boolean isSPDiagram(File file) {
        ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ProcessAdapterFactory());
        adapterFactory.addAdapterFactory(new ParameterAdapterFactory());
        adapterFactory.addAdapterFactory(new ConnectorDefinitionAdapterFactory());
        adapterFactory
                .addAdapterFactory(new ResourceItemProviderAdapterFactory());
        adapterFactory
                .addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        AdapterFactoryEditingDomain editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
                new BasicCommandStack(), new HashMap<Resource, Boolean>());
        URI fileURI = URI.createFileURI(file.getAbsolutePath());
        editingDomain.getResourceSet().getLoadOptions().put(XMIResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
        Resource resource = editingDomain.getResourceSet().getResource(fileURI, true);
        MainProcess process = (MainProcess) resource.getContents().get(0);
        return process.getConfigId().toString().contains("sp");
    }

    private String connected(AbstractRepository repository) {
        File git = new File(repository.getProject().getLocation().toFile(), ".git");
        File svn = new File(repository.getProject().getLocation().toFile(), ".svn");
        return svn.exists() || git.exists() ? "Shared" : "Local";
    }

    private Predicate<? super IProject> hasBonitaNature() {
        return project -> {
            final boolean closed = !project.isOpen();
            try {
                project.open(AbstractRepository.NULL_PROGRESS_MONITOR);
                return project.hasNature(BonitaProjectNature.NATURE_ID);
            } catch (final CoreException e) {
                return false;
            } finally {
                if (closed) {
                    try {
                        project.close(AbstractRepository.NULL_PROGRESS_MONITOR);
                    } catch (final CoreException e) {
                        return false;
                    }
                }
            }
        };
    }

    @Override
    public void stop() {
        try {
            ResourcesPlugin.getWorkspace().save(true, new NullProgressMonitor());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        PlatformUI.getWorkbench().close();
    }

}
