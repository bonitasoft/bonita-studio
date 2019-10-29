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

import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Iterators.forArray;
import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.dao.BusinessObjectDAO;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IRegion;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swt.graphics.Image;
import org.xml.sax.SAXException;

import com.google.common.base.Predicate;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectModelRepositoryStore<F extends AbstractBDMFileStore>
        extends AbstractRepositoryStore<AbstractBDMFileStore> implements IBonitaProjectListener {

    private static final String STORE_NAME = "bdm";

    public static final String BDM_TYPE_EXTENSION = "xml";

    private static final Set<String> extensions = new HashSet<>();

    private static final String BDM_CLIENT_POJO_JAR_NAME = "bdm-client-pojo.jar";

    private BusinessObjectModelConverter converter = new CustomBusinessObjectModelConverter();

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
    public AbstractBDMFileStore createRepositoryFileStore(final String fileName) {
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
        final Optional<BusinessObjectModelFileStore> businessObjectFileStore = Optional
                .ofNullable((BusinessObjectModelFileStore) getChild(BusinessObjectModelFileStore.BOM_FILENAME, true));
        return businessObjectFileStore.map(fileStore -> fileStore.getBusinessObject(qualifiedName));
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
        }

        generateJar(fileStore);
        return fileStore;
    }

    protected IStatus generateJar(F fileStore) {
        try {
            new GenerateBDMOperation((BusinessObjectModelFileStore) fileStore).run(Repository.NULL_PROGRESS_MONITOR);
            return Status.OK_STATUS;
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID, "Failed to generate jar from BDM.", e);
        }
    }

    @Override
    public void migrate(IProgressMonitor monitor) throws CoreException, MigrationException {
        super.migrate(monitor);
        final BusinessObjectModelFileStore fStore = (BusinessObjectModelFileStore) getChild(
                BusinessObjectModelFileStore.ZIP_FILENAME, true);
        if (fStore != null) {
            final File legacyBDM = fStore.getResource().getLocation().toFile();
            BusinessObjectModel businessObjectModel;
            try {
                BusinessObjectModelConverter converter = getConverter();
                businessObjectModel = converter.unzip(Files.toByteArray(legacyBDM));
                try (InputStream is = ByteSource.wrap(converter.marshall(businessObjectModel)).openBufferedStream()) {
                    doImportInputStream(BusinessObjectModelFileStore.BOM_FILENAME, is);
                } catch (IOException e) {
                    throw new MigrationException("Failed to migrate Business data model", e);
                }
            } catch (IOException | JAXBException | SAXException e1) {
                throw new MigrationException("Failed to migrate Business data model", e1);
            }
            fStore.getResource().delete(true, monitor);
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
                    new DeployBDMOperation(fileStore).run(Repository.NULL_PROGRESS_MONITOR);
                } catch (final InvocationTargetException | InterruptedException e) {
                    return new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID, "Failed to deploy BDM", e);
                }
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    public List<IType> allBusinessObjectDao(final IJavaProject javaProject) {
        IType daoType = null;
        try {
            daoType = javaProject.findType(BusinessObjectDAO.class.getName());
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(String.format("Failed to retrieve %s type", BusinessObjectDAO.class.getName()), e);
        }
        if (daoType == null) {
            return Collections.emptyList();
        }
        final ITypeHierarchy newTypeHierarchy = typeHierarchy(javaProject, daoType);
        if (newTypeHierarchy != null) {
            return newArrayList(newTypeHierarchy.getAllSubtypes(daoType));
        }
        return Collections.emptyList();
    }

    protected ITypeHierarchy typeHierarchy(final IJavaProject javaProject, final IType daoType) {
        IRegion newRegion = null;
        try {
            newRegion = regionWithBDM(javaProject);
        } catch (final JavaModelException e) {
            BonitaStudioLog.error("Failed to compute region for BDM", e);
        }
        ITypeHierarchy newTypeHierarchy = null;
        try {
            newTypeHierarchy = javaProject.newTypeHierarchy(daoType, newRegion,
                    Repository.NULL_PROGRESS_MONITOR);
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(String.format("Failed to compute %s hierarchy", daoType.getElementName()), e);
        }
        return newTypeHierarchy;
    }

    protected IRegion regionWithBDM(final IJavaProject javaProject) throws JavaModelException {
        final IRegion newRegion = JavaCore.newRegion();
        IClasspathEntry[] rawClasspath = javaProject.getRawClasspath();
        if (rawClasspath != null) {
            final IClasspathEntry repositoryDependenciesClasspathEntry = find(asIterable(rawClasspath),
                    repositoryDependenciesEntry(), null);
            final IPackageFragmentRoot[] fragmentRoots = javaProject
                    .findPackageFragmentRoots(repositoryDependenciesClasspathEntry);
            if (fragmentRoots != null) {
                final IPackageFragmentRoot packageFragmentRoot = find(asIterable(fragmentRoots),
                        withElementName(BDM_CLIENT_POJO_JAR_NAME), null);
                if (packageFragmentRoot != null) {
                    newRegion.add(packageFragmentRoot);
                }
            }
        }
        return newRegion;
    }

    private Predicate<IPackageFragmentRoot> withElementName(final String elementName) {
        return new Predicate<IPackageFragmentRoot>() {

            @Override
            public boolean apply(final IPackageFragmentRoot input) {
                return elementName.equals(input.getElementName());
            }
        };
    }

    private <T> Iterable<T> asIterable(final T[] elements) {
        return new Iterable<T>() {

            @Override
            public Iterator<T> iterator() {
                return forArray(elements);
            }
        };
    }

    protected Predicate<IClasspathEntry> repositoryDependenciesEntry() {
        return new Predicate<IClasspathEntry>() {

            @Override
            public boolean apply(final IClasspathEntry input) {
                return input.getPath().equals(new Path("repositoryDependencies"));
            }
        };
    }

    @Override
    public F getChild(String fileName, boolean force) {
        if(Objects.equals(fileName, BusinessObjectModelFileStore.BDM_ARTIFACT_DESCRIPTOR)) {
            return null;
        }
        return (F) super.getChild(fileName, force);
    }

    @Override
    public void projectOpened(Repository repository, IProgressMonitor monitor) {
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
    public void projectClosed(Repository repository, IProgressMonitor monitor) {
        //Nothing to do
    }

}
