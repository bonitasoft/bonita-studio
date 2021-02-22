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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.validator.ModelNamespaceValidator;
import org.bonitasoft.studio.common.model.validator.XMLModelCompatibilityValidator;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swt.graphics.Image;
import org.xml.sax.SAXException;

import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectModelRepositoryStore<F extends AbstractBDMFileStore<?>>
        extends AbstractRepositoryStore<AbstractBDMFileStore<?>>
        implements IBonitaProjectListener {

    private static final String STORE_NAME = "bdm";

    public static final String BDM_TYPE_EXTENSION = "xml";

    private static final Set<String> extensions = new HashSet<>();

    private BusinessObjectModelConverter converter = new BusinessObjectModelConverter();

    static {
        extensions.add(BDM_TYPE_EXTENSION);
    }

    public BusinessObjectModelConverter getConverter() {
        return converter;
    }

    @Override
    public void createRepositoryStore(IRepository repository) {
        super.createRepositoryStore(repository);
        repository.addProjectListener(this);
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
    public String getDisplayName() {
        return Messages.businessObjectRepositoryStoreName;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("bdm.png", BusinessObjectPlugin.getDefault());
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
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
        if (fileStore instanceof BusinessObjectModelFileStore) {
            try {
                BDMArtifactDescriptor descriptor = ((BusinessObjectModelFileStore) fileStore).loadArtifactDescriptor();
                ((BusinessObjectModelFileStore) fileStore).saveArtifactDescriptor(descriptor);
            } catch (CoreException e) {
                BonitaStudioLog.error("Failed to import Business data model artifact descriptor", e);
            }
            addNamespace((BusinessObjectModelFileStore) fileStore);
        }
        generateJar(fileStore);
        return fileStore;
    }

    // namespace migration (performed by the marshaller, engine side)
    private void addNamespace(BusinessObjectModelFileStore fStore) {
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

    protected IStatus generateJar(F fileStore) {
        try {
            new GenerateBDMOperation((BusinessObjectModelFileStore) fileStore).run(AbstractRepository.NULL_PROGRESS_MONITOR);
            return Status.OK_STATUS;
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID, "Failed to generate jar from BDM.", e);
        }
    }

    @Override
    public void migrate(IProgressMonitor monitor) throws CoreException, MigrationException {
        super.migrate(monitor);
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

    public List<IType> allBusinessObjectDao(final IJavaProject javaProject) {
        final Optional<BusinessObjectModelFileStore> businessObjectFileStore = Optional
                .ofNullable((BusinessObjectModelFileStore) getChild(BusinessObjectModelFileStore.BOM_FILENAME, true));
        return businessObjectFileStore.map(fStore -> {
                    try {
                        return fStore.getContent();
                    } catch (ReadFileStoreException e1) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(model -> model.getBusinessObjectsClassNames()
                        .stream()
                        .map(name -> name + "DAO")
                        .map(daoType -> {
                            try {
                                return javaProject.findType(daoType);
                            } catch (JavaModelException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    @Override
    public F getChild(String fileName, boolean force) {
        if (Objects.equals(fileName, BusinessObjectModelFileStore.BDM_ARTIFACT_DESCRIPTOR)) {
            return null;
        }
        return (F) super.getChild(fileName, force);
    }

    @Override
    public void projectOpened(AbstractRepository repository, IProgressMonitor monitor) {
        Job job = new Job(Messages.generatingJarFromBDMModel) {

            @Override
            protected IStatus run(IProgressMonitor monitor) {
                F fStore = getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
                if (fStore != null) {
                    return generateJar(fStore);
                }
                return Status.OK_STATUS;
            }

        };
        job.schedule();
    }

    @Override
    public void projectClosed(AbstractRepository repository, IProgressMonitor monitor) {
        //Nothing to do
    }

    @Override
    public IStatus validate(String filename, InputStream inputStream) {
        XMLModelCompatibilityValidator validator = new XMLModelCompatibilityValidator(
                new ModelNamespaceValidator(ModelVersion.CURRENT_BDM_NAMESPACE,  
                        String.format(org.bonitasoft.studio.common.Messages.incompatibleModelVersion, filename),
                        String.format(org.bonitasoft.studio.common.Messages.migrationWillBreakRetroCompatibility, filename)));
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
                }finally {
                    tempFile.delete();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return super.validate(filename, inputStream);
    }

}
