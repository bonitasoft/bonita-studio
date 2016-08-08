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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.dao.BusinessObjectDAO;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
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
import com.google.common.collect.Iterators;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectModelRepositoryStore extends AbstractRepositoryStore<BusinessObjectModelFileStore> {

    private static final String STORE_NAME = "bdm";

    public static final String BDM_TYPE_EXTENSION = "xml";

    private static final Set<String> extensions = new HashSet<>();

    private static final String BDM_CLIENT_POJO_JAR_NAME = "bdm-client-pojo.jar";

    static {
        extensions.add(BDM_TYPE_EXTENSION);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public BusinessObjectModelFileStore createRepositoryFileStore(final String fileName) {
        return new BusinessObjectModelFileStore(fileName, this);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.businessObjectRepositoryStoreName;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("bdm.png", BusinessObjectPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    public BusinessObjectModelFileStore getChildByQualifiedName(final String qualifiedName) {
        for (final IRepositoryFileStore file : getChildren()) {
            final BusinessObjectModelFileStore businessObjectFileStore = (BusinessObjectModelFileStore) file;
            if (businessObjectFileStore.getBusinessObject(qualifiedName) != null) {
                return businessObjectFileStore;
            }
        }
        return null;
    }

    public BusinessObject getBusinessObjectByQualifiedName(final String qualifiedName) {
        for (final IRepositoryFileStore file : getChildren()) {
            final BusinessObjectModelFileStore businessObjectFileStore = (BusinessObjectModelFileStore) file;
            final BusinessObject businessObject = businessObjectFileStore.getBusinessObject(qualifiedName);
            if (businessObject != null) {
                return businessObjectFileStore.getBusinessObject(qualifiedName);
            }
        }
        return null;
    }

    @Override
    protected BusinessObjectModelFileStore doImportInputStream(final String fileName, final InputStream inputStream) {
        BusinessObjectModelFileStore fileStore = null;
        if (BusinessObjectModelFileStore.ZIP_FILENAME.equals(fileName)) {
            try {
                final BusinessObjectModelConverter converter = new BusinessObjectModelConverter();
                final BusinessObjectModel businessObjectModel = converter.unzip(ByteStreams.toByteArray(inputStream));
                final InputSupplier<ByteArrayInputStream> inputSupplier = ByteStreams.newInputStreamSupplier(converter.marshall(businessObjectModel));
                fileStore = superDoImportInputStream(BusinessObjectModelFileStore.BOM_FILENAME, inputSupplier.getInput());
            } catch (IOException | JAXBException | SAXException e) {
                BonitaStudioLog.error("Failed to import Business data model", e);
            }
        } else {
            fileStore = superDoImportInputStream(fileName, inputStream);
        }

        if (isDeployable()) {
            deploy(fileStore);
        }
        return fileStore;
    }
    
    @Override
    public void migrate(IProgressMonitor monitor) throws CoreException, MigrationException {
        super.migrate(monitor);
        final BusinessObjectModelFileStore fStore = getChild(BusinessObjectModelFileStore.ZIP_FILENAME);
        if(fStore != null){
            final File legacyBDM = fStore.getResource().getLocation().toFile();
            try {
                final BusinessObjectModelConverter converter = new BusinessObjectModelConverter();
                final BusinessObjectModel businessObjectModel = converter.unzip( Files.toByteArray(legacyBDM) );
                final InputSupplier<ByteArrayInputStream> inputSupplier = ByteStreams.newInputStreamSupplier(converter.marshall(businessObjectModel));
                doImportInputStream(BusinessObjectModelFileStore.BOM_FILENAME, inputSupplier.getInput());
            } catch (IOException | JAXBException | SAXException e) {
                throw new MigrationException("Failed to migrate Business data model", e);
            }
            fStore.getResource().delete(true, monitor);
        }
    }

    protected BusinessObjectModelFileStore superDoImportInputStream(final String fileName, final InputStream inputStream) {
        return super.doImportInputStream(fileName, inputStream);
    }

    protected void deploy(final BusinessObjectModelFileStore fileStore) {
        try {
            new DeployBDMOperation(fileStore).run(Repository.NULL_PROGRESS_MONITOR);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected boolean isDeployable() {
        return !PlatformUtil.isHeadless();
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
        final IClasspathEntry repositoryDependenciesClasspathEntry = find(asIterable(javaProject.getRawClasspath()), repositoryDependenciesEntry(), null);
        final IPackageFragmentRoot[] fragmentRoots = javaProject.findPackageFragmentRoots(repositoryDependenciesClasspathEntry);
        final IPackageFragmentRoot packageFragmentRoot = find(asIterable(fragmentRoots), withElementName(BDM_CLIENT_POJO_JAR_NAME), null);
        if (packageFragmentRoot != null) {
            newRegion.add(packageFragmentRoot);
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
                if (elements == null) {
                    return Iterators.emptyIterator();
                }
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

}
