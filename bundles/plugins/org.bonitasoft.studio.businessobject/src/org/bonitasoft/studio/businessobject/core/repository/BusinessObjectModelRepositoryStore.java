/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.core.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.zip.ZipFile;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.validator.ModelNamespaceValidator;
import org.bonitasoft.studio.common.model.validator.XMLModelCompatibilityValidator;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.IProjectContainer;
import org.bonitasoft.studio.common.repository.core.maven.plugin.CreateBdmModulePlugin;
import org.bonitasoft.studio.common.repository.core.maven.plugin.ImportMavenModuleOperation;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.common.repository.store.FileStoreCollector;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.xml.sax.SAXException;

import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

public class BusinessObjectModelRepositoryStore<F extends AbstractBDMFileStore<?>>
        extends AbstractRepositoryStore<AbstractBDMFileStore<?>>
        implements IBonitaProjectListener, IProjectContainer {

    private static final String STORE_NAME = "bdm";

    public static final String BDM_TYPE_EXTENSION = "xml";

    private static final Set<String> extensions = new HashSet<>();

    private BusinessObjectModelConverter converter = new BusinessObjectModelConverter();

    private BusinessObjectModelFileStore bdmFileStore;

    static {
        extensions.add(BDM_TYPE_EXTENSION);
    }

    public BusinessObjectModelConverter getConverter() {
        return converter;
    }

    @Override
    public void createRepositoryStore(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public IFolder getResource() {
        var project = getBonitaProject();
        if (project != null && project.exists()) {
            return project.getAppProject().getFolder(STORE_NAME);
        }
        return super.getResource();
    }

    private BonitaProject getBonitaProject() {
        return Adapters.adapt(getRepository(), BonitaProject.class);
    }

    @Override
    public AbstractBDMFileStore<?> createRepositoryFileStore(final String fileName) {
        return new BusinessObjectModelFileStore(fileName, this);
    }

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    protected FileStoreCollector fileStoreCollector() {
        return new FileStoreCollector(getResource(), getCompatibleExtensions().toArray(String[]::new)) {

            @Override
            public boolean visit(IResource resource) throws CoreException {
                if (Objects.equals(resource.getName(), BusinessObjectModelFileStore.BOM_FILENAME)) {
                    return super.visit(resource);
                }
                return resource.equals(getResource());
            }
        };
    }

    public Optional<BusinessObjectModelFileStore> getChildByQualifiedName(final String qualifiedName) {
        final Optional<BusinessObjectModelFileStore> businessObjectFileStore = Optional
                .ofNullable((BusinessObjectModelFileStore) getChild(BusinessObjectModelFileStore.BOM_FILENAME, true));
        return businessObjectFileStore
                .map(fileStore -> fileStore.getBusinessObject(qualifiedName) != null ? fileStore : null);
    }

    public Optional<BusinessObject> getBusinessObjectByQualifiedName(String qualifiedName) {
        return Optional
                .ofNullable((BusinessObjectModelFileStore) getChild(BusinessObjectModelFileStore.BOM_FILENAME, true))
                .map(fileStore -> fileStore.getBusinessObject(qualifiedName));
    }

    @Override
    public AbstractBDMFileStore<?> importInputStream(String fileName, InputStream inputStream) {
        if (!getResource().exists() || !getResource().getFile("pom.xml").exists()) {
            try {
                var bdmFolder = createBdmModule(getBonitaProject(), new NullProgressMonitor());
                var bomFile = bdmFolder.getFile(BusinessObjectModelFileStore.BOM_FILENAME);
                if (bomFile.exists()) {
                    bomFile.delete(true, new NullProgressMonitor());
                }
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return super.importInputStream(fileName, inputStream);
    }

    @Override
    protected AbstractBDMFileStore<?> doImportArchiveData(ImportArchiveData importArchiveData, IProgressMonitor monitor)
            throws CoreException {
        if (!getResource().exists() || !getResource().getFile("pom.xml").exists()) {
            var bdmFolder = createBdmModule(getBonitaProject(), new NullProgressMonitor());
            var bomFile = bdmFolder.getFile(BusinessObjectModelFileStore.BOM_FILENAME);
            if (bomFile.exists()) {
                bomFile.delete(true, new NullProgressMonitor());
            }
        }
        return super.doImportArchiveData(importArchiveData, monitor);
    }

    @Override
    protected F doImportInputStream(final String fileName, final InputStream inputStream) {
        F fileStore = null;
        if (BusinessObjectModelFileStore.ZIP_FILENAME.equals(fileName)) {
            try {
                BusinessObjectModelConverter converter = getConverter();
                final BusinessObjectModel businessObjectModel = converter.unzip(ByteStreams.toByteArray(inputStream));
                try (InputStream is = ByteSource.wrap(converter.marshall(businessObjectModel)).openBufferedStream()) {
                    fileStore = superDoImportInputStream(BusinessObjectModelFileStore.BOM_FILENAME, is);
                }
            } catch (IOException | JAXBException | SAXException e) {
                BonitaStudioLog.error("Failed to import Business data model", e);
            }
        } else {
            fileStore = superDoImportInputStream(fileName, inputStream);
        }
        if (fileStore instanceof BusinessObjectModelFileStore
                && Objects.equals(fileStore.getName(), BusinessObjectModelFileStore.BOM_FILENAME)) {
            addNamespace((BusinessObjectModelFileStore) fileStore);
            installArtifact(fileStore);
        }
        return fileStore;
    }

    @Override
    public void refresh() {
        super.refresh();
        // Verify all modules projects are imported or cleaned
        var bdmParentFolder = getResource();
        BonitaProject bonitaProject = getBonitaProject();
        var parentProject = bonitaProject.getBdmParentProject();
        var modelProject = bonitaProject.getBdmModelProject();
        var daoProject = bonitaProject.getBdmDaoClientProject();
        // Remove all existing project before clean re import
        if (parentProject.exists() && !parentProject.isOpen()) {
            try {
                parentProject.delete(false, false, new NullProgressMonitor());
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        if (modelProject.exists() && !modelProject.isOpen()) {
            try {
                modelProject.delete(false, false, new NullProgressMonitor());
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        if (daoProject.exists() && !daoProject.isOpen()) {
            try {
                daoProject.delete(false, false, new NullProgressMonitor());
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        if (bdmParentFolder.getFile("pom.xml").exists() && !parentProject.exists()) {
            var importBdmModules = new ImportMavenModuleOperation(bdmParentFolder.getLocation().toFile());
            try {
                importBdmModules.run(new NullProgressMonitor());
                if(bonitaProject.getGitDir().exists()) {
                    var connectProviderOperation = bonitaProject.newConnectProviderOperation();
                    connectProviderOperation.run(new NullProgressMonitor());
                }
                bonitaProject.getBdmParentProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
                bonitaProject.getBdmModelProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD,
                        new NullProgressMonitor());
            } catch (CoreException | InvocationTargetException | InterruptedException e) {
                BonitaStudioLog.error(e);
            }
        }

        // Check if bdm resource link is consistent
        createBdmFolderLinkInAppProject(getBonitaProject());
    }

    public IFolder createBdmModule(BonitaProject project, IProgressMonitor monitor) throws CoreException {
        var parentProject = project.getParentProject();
        var parentProjectPath = parentProject.getLocation().toFile().toPath();
        var plugin = new CreateBdmModulePlugin(parentProjectPath, project.getId());
        plugin.execute(new NullProgressMonitor());
        var importBdmModules = new ImportMavenModuleOperation(parentProjectPath.resolve(STORE_NAME).toFile());
        importBdmModules.run(monitor);
        return createBdmFolderLinkInAppProject(project);
    }

    public IFolder createBdmFolderLinkInAppProject(BonitaProject project) {
        return BuildScheduler.callWithBuildRule(() -> {
            var bdmFolder = project.getParentProject().getFolder(STORE_NAME);
            var bdmLinkedFolder = project.getAppProject().getFolder(STORE_NAME);
            try {
                if (!bdmFolder.exists()) {
                    bdmFolder.create(false, true, new NullProgressMonitor());
                }
                if (!bdmLinkedFolder.exists()) {
                    bdmLinkedFolder.createLink(Path.fromOSString("PARENT-1-PROJECT_LOC/" + STORE_NAME),
                            IResource.REPLACE | IResource.ALLOW_MISSING_LOCAL,
                            new NullProgressMonitor());
                    // Persist workspace state
                    ResourcesPlugin.getWorkspace().save(false, new NullProgressMonitor());
                }
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            return bdmLinkedFolder;
        });
    }

    // namespace migration (performed by the marshaller, engine side)
    private void addNamespace(BusinessObjectModelFileStore fStore) {
        if (Objects.equals(fStore.getName(), BusinessObjectModelFileStore.BOM_FILENAME)) {
            try {
                BusinessObjectModel model = fStore.getContent();
                if (model != null) {
                    byte[] bytes = converter.marshall(model);
                    model = converter.unmarshall(bytes);
                    fStore.save(model);
                }
            } catch (JAXBException | IOException | SAXException | ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to perform namespace migration on Business data model", e);
            }
        }
    }

    protected IStatus installArtifact(F fileStore) {
        try {
            new GenerateBDMOperation((BusinessObjectModelFileStore) fileStore)
                    .run(AbstractRepository.NULL_PROGRESS_MONITOR);
            return Status.OK_STATUS;
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID, "Failed to generate jar from BDM.", e);
        }
    }

    @Override
    public MigrationReport migrate(IProgressMonitor monitor)
            throws CoreException, MigrationException {
        MigrationReport report = super.migrate(monitor);
        var project = getBonitaProject();
        if (project != null) {
            createBdmFolderLinkInAppProject(project);
        }
        BusinessObjectModelFileStore fStore = (BusinessObjectModelFileStore) getChild(
                BusinessObjectModelFileStore.ZIP_FILENAME, true);
        BusinessObjectModelConverter converter = getConverter();
        try {
            if (fStore != null) {
                final File legacyBDM = fStore.getResource().getLocation().toFile();
                BusinessObjectModel businessObjectModel;
                businessObjectModel = converter.unzip(Files.toByteArray(legacyBDM));
                try (InputStream is = ByteSource.wrap(converter.marshall(businessObjectModel)).openBufferedStream()) {
                    doImportInputStream(BusinessObjectModelFileStore.BOM_FILENAME, is);
                } catch (IOException e) {
                    throw new MigrationException("Failed to migrate Business data model", e);
                }

                fStore.getResource().delete(true, monitor);
            } else {
                fStore = (BusinessObjectModelFileStore) getChild(BusinessObjectModelFileStore.BOM_FILENAME, false);
                if (fStore != null) {
                    addNamespace(fStore);
                }
            }
        } catch (IOException | JAXBException | SAXException e1) {
            throw new MigrationException("Failed to migrate Business data model", e1);
        }
        return report;
    }

    protected F superDoImportInputStream(final String fileName, final InputStream inputStream) {
        return (F) super.doImportInputStream(fileName, inputStream);
    }

    protected void deploy(final BusinessObjectModelFileStore fileStore) {
        new Job("Deploy Business Data Model") {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                try {
                    new DeployBDMOperation(fileStore).run(AbstractRepository.NULL_PROGRESS_MONITOR);
                } catch (final InvocationTargetException | InterruptedException e) {
                    return new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID, "Failed to deploy BDM", e);
                }
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    public void updateBusinessObjectDao() {
        var fStore = (BusinessObjectModelFileStore) getChild(
                BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (fStore != null && fStore.getResource().exists()) {
            fStore.clearBusinessObjectDaoCache();
            fStore.allBusinessObjectDao(getBonitaProject().getAdapter(IJavaProject.class));
        }
    }

    public List<IType> allBusinessObjectDao() {
        var fStore = (BusinessObjectModelFileStore) getChild(
                BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (fStore == null || !fStore.getResource().exists()) {
            return Collections.emptyList();
        }
        return fStore.allBusinessObjectDao(getBonitaProject().getAdapter(IJavaProject.class));
    }

    @Override
    public F getChild(String fileName, boolean force) {
        if (Objects.equals(fileName, BusinessObjectModelFileStore.BOM_FILENAME)) {
            BusinessObjectModelFileStore fStore = (BusinessObjectModelFileStore) super.getChild(fileName, force);
            if (fStore != null && fStore.getResource().exists() && bdmFileStore != null) {
                return (F) bdmFileStore;
            } else if (fStore != null) {
                bdmFileStore = fStore;
                return (F) bdmFileStore;
            }
        }
        return null;
    }

    @Override
    public void projectOpened(IRepository repository, IProgressMonitor monitor) {
        F fStore = getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (fStore != null) {
            installArtifact(fStore);
        }
    }

    @Override
    public void projectClosed(IRepository repository, IProgressMonitor monitor) {
        //Nothing to do
    }

    @Override
    public IStatus validate(String filename, InputStream inputStream) {
        XMLModelCompatibilityValidator validator = new XMLModelCompatibilityValidator(
                new ModelNamespaceValidator(ModelVersion.CURRENT_BDM_NAMESPACE,
                        String.format(org.bonitasoft.studio.common.Messages.incompatibleModelVersion, filename),
                        String.format(org.bonitasoft.studio.common.Messages.migrationWillBreakRetroCompatibility,
                                filename)));
        if (Objects.equals(filename, BusinessObjectModelFileStore.BOM_FILENAME)) {
            return validator.validate(inputStream);
        }
        if (Objects.equals(filename, BusinessObjectModelFileStore.ZIP_FILENAME)) {
            try {
                File tempFile = File.createTempFile("bdm", ".zip");
                java.nio.file.Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                try (ZipFile zipFile = new ZipFile(tempFile);
                        InputStream is = zipFile
                                .getInputStream(zipFile.getEntry(BusinessObjectModelFileStore.BOM_FILENAME));) {
                    return validator.validate(is);
                } finally {
                    tempFile.delete();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return super.validate(filename, inputStream);
    }

    @Override
    public Collection<IProject> getChildrenProjects() {
        var bonitaProject = getBonitaProject();
        var childrenProjects = new ArrayList<IProject>();
        if (bonitaProject != null) {
            var bdmModelProject = bonitaProject.getBdmModelProject();
            if (bdmModelProject.exists() && bdmModelProject.isOpen()) {
                childrenProjects.add(bdmModelProject);
            }
            var bdmDaoClientProject = bonitaProject.getBdmDaoClientProject();
            if (bdmDaoClientProject.exists() && bdmDaoClientProject.isOpen()) {
                childrenProjects.add(bdmDaoClientProject);
            }
        }
        return childrenProjects;
    }

}
