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
package org.bonitasoft.studio.actors.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.DocumentRoot;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.util.OrganizationAdapterFactory;
import org.bonitasoft.studio.actors.model.organization.util.OrganizationResourceFactoryImpl;
import org.bonitasoft.studio.actors.model.organization.util.OrganizationResourceImpl;
import org.bonitasoft.studio.actors.model.organization.util.OrganizationXMLProcessor;
import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.NamespaceUtil;
import org.bonitasoft.studio.common.model.validator.ModelNamespaceValidator;
import org.bonitasoft.studio.common.model.validator.XMLModelCompatibilityValidator;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;

import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class OrganizationRepositoryStore extends AbstractEMFRepositoryStore<OrganizationFileStore> {

    private static final String INITIAL_ORGANIZATION_NAMESPACE = "http://www.bonitasoft.org/ns/organization/6.0";
    private static final List<String> LEGACY_ORGA_NAMESPACES;

    static {
        LEGACY_ORGA_NAMESPACES = new ArrayList<>();
        LEGACY_ORGA_NAMESPACES.add(INITIAL_ORGANIZATION_NAMESPACE);
        LEGACY_ORGA_NAMESPACES.add("http://www.bonitasoft.org/ns/organization/6.0.0-beta-016");
        LEGACY_ORGA_NAMESPACES.add(NamespaceUtil.namespaceRoot(ModelVersion.CURRENT_ORGANIZATION_NAMESPACE));
    }
    
    private static final String STORE_NAME = "organizations";
    public static final String ORGANIZATION_EXT = "organization";
    private static final Set<String> extensions = new HashSet<>();
    static {
        extensions.add(ORGANIZATION_EXT);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public OrganizationFileStore createRepositoryFileStore(final String fileName) {
        return new OrganizationFileStore(fileName, this);
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
        return Messages.organizations;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("organization.png", ActorsPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    protected OrganizationFileStore doImportInputStream(final String fileName, final InputStream inputStream) {
        final String newFileName = fileName.replace(".xml", "." + ORGANIZATION_EXT);
        final IFile file = getResource().getFile(newFileName);
        OrganizationFileStore fileStore = null;
        try {
            if (file.exists()) {
                if (FileActionDialog.overwriteQuestion(newFileName)) {
                    file.setContents(inputStream, true, false, AbstractRepository.NULL_PROGRESS_MONITOR);
                } else {
                    inputStream.close();
                }
            } else {
                file.create(inputStream, true, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
            fileStore = createRepositoryFileStore(newFileName);
            if (file != null && fileStore != null) {
                final Organization orga = fileStore.getContent();
                if (orga != null && (orga.getName() == null || orga.getName().isEmpty())) {
                    orga.setName(newFileName.substring(0, newFileName.length() - ORGANIZATION_EXT.length() - 1));
                    fileStore.save(orga);
                }
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
            return null;
        }
        return fileStore;
    }

    @Override
    protected void addAdapterFactory(final ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new OrganizationAdapterFactory());
    }

    @Override
    protected Resource getTmpEMFResource(String fileName, final File originalFile) throws IOException {
        fileName = fileName.replaceAll(".xml", ".organization");
        final File tmpFile = File.createTempFile("tmp", fileName, ProjectUtil.getBonitaStudioWorkFolder());
        Files.copy(originalFile, tmpFile);
        return new OrganizationResourceFactoryImpl().createResource(URI.createFileURI(tmpFile.getAbsolutePath()));
    }

    @Override
    protected void performMigration(final Migrator migrator, final URI resourceURI, final Release release)
            throws MigrationException {
        migrator.setLevel(ValidationLevel.RELEASE);
        final ResourceSet rSet = migrator.migrateAndLoad(
                Collections.singletonList(resourceURI), release,
                null, AbstractRepository.NULL_PROGRESS_MONITOR);
        if (!rSet.getResources().isEmpty()) {
            FileOutputStream fos = null;
            try {
                final OrganizationResourceImpl r = (OrganizationResourceImpl) rSet.getResources().get(0);
                final Resource resource = new XMLResourceImpl(resourceURI);
                final DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot();
                final Organization orga = EcoreUtil.copy(((DocumentRoot) r.getContents().get(0)).getOrganization());
                root.setOrganization(orga);
                resource.getContents().add(root);
                final Map<String, String> options = new HashMap<>();
                options.put(XMLResource.OPTION_ENCODING, "UTF-8");
                options.put(XMLResource.OPTION_XML_VERSION, "1.0");
                final File target = new File(resourceURI.toFileString());
                fos = new FileOutputStream(target);
                new OrganizationXMLProcessor().save(fos, resource, options);
            } catch (final Exception e) {
                BonitaStudioLog.error(e, ActorsPlugin.PLUGIN_ID);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e, ActorsPlugin.PLUGIN_ID);
                    }
                }
            }
        }
    }

    @Override
    public IStatus validate(String filename, InputStream inputStream) {
        if (filename != null && filename.endsWith("." + ORGANIZATION_EXT) || filename.toLowerCase().endsWith(".xml")) {
            return new XMLModelCompatibilityValidator(new ModelNamespaceValidator(ModelVersion.CURRENT_ORGANIZATION_NAMESPACE,
                    String.format(org.bonitasoft.studio.common.Messages.incompatibleModelVersion, filename),
                    String.format(org.bonitasoft.studio.common.Messages.migrationWillBreakRetroCompatibility, filename),
                    LEGACY_ORGA_NAMESPACES)).validate(inputStream);
        }
        return super.validate(filename, inputStream);
    }

}
