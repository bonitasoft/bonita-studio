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
package org.bonitasoft.studio.identity.organization.repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.NamespaceUtil;
import org.bonitasoft.studio.common.model.validator.ModelNamespaceValidator;
import org.bonitasoft.studio.common.model.validator.XMLModelCompatibilityValidator;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.common.ui.jface.FileActionDialog;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.organization.model.organization.DocumentRoot;
import org.bonitasoft.studio.identity.organization.model.organization.Organization;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.util.OrganizationAdapterFactory;
import org.bonitasoft.studio.identity.organization.model.organization.util.OrganizationResourceFactoryImpl;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;

/**
 * @author Romain Bioteau
 */
public class OrganizationRepositoryStore extends AbstractEMFRepositoryStore<OrganizationFileStore> {

	private static final String LEGACY_ORGANIZATION_NAME = "organization";
	private static final String INITIAL_ORGANIZATION_NAMESPACE = "http://www.bonitasoft.org/ns/organization/6.0";
	private static final List<String> LEGACY_ORGA_NAMESPACES;

	static {
		LEGACY_ORGA_NAMESPACES = new ArrayList<>();
		LEGACY_ORGA_NAMESPACES.add(INITIAL_ORGANIZATION_NAMESPACE);
		LEGACY_ORGA_NAMESPACES.add("http://www.bonitasoft.org/ns/organization/6.0.0-beta-016");
		LEGACY_ORGA_NAMESPACES.add(NamespaceUtil.namespaceRoot(ModelVersion.CURRENT_ORGANIZATION_NAMESPACE));
	}

	private static final String STORE_NAME = "organizations";
	public static final String ORGANIZATION_EXT = "xml";
	private static final Set<String> extensions = new HashSet<>();
	static {
		extensions.add(ORGANIZATION_EXT);
		extensions.add(LEGACY_ORGANIZATION_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#
	 * createRepositoryFileStore(java.lang.String)
	 */
	@Override
	public OrganizationFileStore createRepositoryFileStore(final String fileName) {
		return new OrganizationFileStore(fileName, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
	 */
	@Override
	public String getName() {
		return STORE_NAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#
	 * getCompatibleExtensions()
	 */
	@Override
	public Set<String> getCompatibleExtensions() {
		return extensions;
	}

	@Override
	protected OrganizationFileStore doImportInputStream(final String fileName, final InputStream inputStream) {
		final String newFileName = fileName.replace("." + LEGACY_ORGANIZATION_NAME, "." + ORGANIZATION_EXT);
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
			if (fileStore != null) {
				final Organization orga = fileStore.getContent();
				if (orga != null && (orga.getName() != null || orga.getDescription() != null)) {
					orga.setName(null);
					orga.setDescription(null);
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
		var tmpFile = Files.createTempFile(null, fileName);
		Files.copy(originalFile.toPath(), tmpFile, StandardCopyOption.REPLACE_EXISTING);
		return new OrganizationResourceFactoryImpl()
				.createResource(URI.createFileURI(tmpFile.toFile().getAbsolutePath()));
	}

	@Override
	protected void performMigration(final Migrator migrator, final URI resourceURI, final Release release)
			throws MigrationException {
		migrator.setLevel(ValidationLevel.RELEASE);
		final ResourceSet rSet = migrator.migrateAndLoad(Collections.singletonList(resourceURI), release, null,
				AbstractRepository.NULL_PROGRESS_MONITOR);
		if (!rSet.getResources().isEmpty()) {
			try {
				var migratedResource = rSet.getResources().get(0);
				var resource = new OrganizationResourceFactoryImpl().createResource(resourceURI);
				var root = migratedResource.getContents().get(0);
				final DocumentRoot docRoot = OrganizationFactory.eINSTANCE.createDocumentRoot();
				if (root instanceof DocumentRoot) {
					var orga = EcoreUtil.copy(((DocumentRoot) migratedResource.getContents().get(0)).getOrganization());
					orga.setName(null);
					orga.setDescription(null);
					docRoot.setOrganization(orga);
				}
				if (root instanceof Organization) {
					var orga = (Organization) root;
					orga.setName(null);
					orga.setDescription(null);
					docRoot.setOrganization(orga);
				}
				resource.getContents().add(docRoot);
				resource.save(Map.of());
			} catch (final Exception e) {
				BonitaStudioLog.error(e, IdentityPlugin.PLUGIN_ID);
			}
		}
	}

	@Override
	public Migrator getMigrator(String nsURI) {
		var migrator = super.getMigrator(nsURI);
		migrator.setResourceSetFactory(() -> {
			var resourceSet = new ResourceSetImpl();
			resourceSet.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
			resourceSet.getLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
			resourceSet.getLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
			return resourceSet;
		});
		return migrator;
	}

	@Override
	public IStatus validate(String filename, InputStream inputStream) {
		if (filename != null && filename.toLowerCase().endsWith(".xml")) {
			return new XMLModelCompatibilityValidator(new ModelNamespaceValidator(
					ModelVersion.CURRENT_ORGANIZATION_NAMESPACE,
					String.format(org.bonitasoft.studio.common.Messages.incompatibleModelVersion, filename),
					String.format(org.bonitasoft.studio.common.Messages.migrationWillBreakRetroCompatibility, filename),
					LEGACY_ORGA_NAMESPACES)).validate(inputStream);
		}
		return super.validate(filename, inputStream);
	}

	@Override
	public void migrate(IRepositoryFileStore<?> fileStore, IProgressMonitor monitor)
			throws CoreException, MigrationException {
		// Rename organization file to use xml extension instead of legacy one
		if (fileStore.getResource().getFileExtension().equals(LEGACY_ORGANIZATION_NAME)) {
			String newName = fileStore.getResource().getName().replace("." + LEGACY_ORGANIZATION_NAME,
					"." + ORGANIZATION_EXT);
			fileStore.getResource().move(Path.fromOSString(newName), true, new NullProgressMonitor());
			fileStore = createRepositoryFileStore(newName);
		}
		super.migrate(fileStore, monitor);
		try {
			Organization orga = (Organization) fileStore.getContent();
			if(orga.getName() != null) {
				orga.setName(null);
			}
			if(orga.getDescription() != null) {
				orga.setDescription(null);
			}
			fileStore.save(orga);
		} catch (ReadFileStoreException e) {
			throw new MigrationException(e);
		}
	}

}
