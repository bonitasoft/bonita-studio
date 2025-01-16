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
package org.bonitasoft.studio.common.repository.store;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.util.ProcessResourceImpl;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.CopyInputStream;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.MigratorRegistry;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractEMFRepositoryStore<T extends EMFFileStore<?>> extends AbstractRepositoryStore<T> {

	private AdapterFactoryLabelProvider labelProvider;

	private final ComposedAdapterFactory adapterFactory;

	protected AbstractEMFRepositoryStore() {
		super();
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		addAdapterFactory(adapterFactory);

	}

	public AdapterFactoryLabelProvider getLabelProvider() {
		if (labelProvider != null) {
			labelProvider.dispose();
		}
		labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		return labelProvider;
	}

	protected abstract void addAdapterFactory(ComposedAdapterFactory adapterFactory);

	public EditingDomain getEditingDomain(final URI uri) {
		return createAdapterFactoryEditingDomain();
	}

	public EditingDomain getEditingDomain() {
		return createAdapterFactoryEditingDomain();
	}

	protected EditingDomain createAdapterFactoryEditingDomain() {
		var domain = new AdapterFactoryEditingDomain(adapterFactory, new BasicCommandStack(), new HashMap<>());
		// always migrate the .proc resource to the latest version
		domain.getResourceSet().getLoadOptions().put(ProcessResourceImpl.OPTION_MIGRATION_POLICY,
				MigrationPolicy.ALWAYS_MIGRATE_POLICY);
		return domain;
	}

	@Override
	protected InputStream handlePreImport(final String fileName, final InputStream inputStream)
			throws MigrationException, IOException {
		final InputStream is = super.handlePreImport(fileName, inputStream);
		if (fileName.endsWith(".properties") || fileName.toLowerCase().endsWith(".png")
				|| fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".gif")
				|| fileName.toLowerCase().endsWith(".jpeg") || fileName.toLowerCase().endsWith(".xsd")) {// not an emf
																											// resource
			return is;
		}
		final CopyInputStream copyIs = new CopyInputStream(is);
		final InputStream originalStream = copyIs.getCopy();

		final Resource resource = getTmpEMFResource(fileName, copyIs.getFile());
		if (resource == null) {
			BonitaStudioLog.debug("Failed to retrieve EMF Resource for migration", CommonRepositoryPlugin.PLUGIN_ID);
			copyIs.close();
			return originalStream;
		}
		final URI resourceURI = resource.getURI();
		final File tmpFile = new File(resource.getURI().toFileString());
		final String nsURI = getNamespaceURI(fileName, copyIs, resourceURI, tmpFile);
		if (nsURI == null) {
			tmpFile.delete();
			copyIs.close();
			throw new IOException(fileName);
		}
		final Migrator targetMigrator = getMigrator(nsURI);
		// proc resource and associates are already auto-migrated at resource loading
		if (targetMigrator != null && !targetMigrator.getNsURIs().contains(ProcessPackage.eNS_URI)) {
			final Release release = getRelease(targetMigrator, resource);
			if (release != null && !release.isLatestRelease()) {
				try {
					BonitaStudioLog.info(
							"Performing migration on " + fileName + " from " + release.getLabel() + " to latest...",
							CommonRepositoryPlugin.PLUGIN_ID);
					performMigration(targetMigrator, resourceURI, release);
				} catch (final MigrationException e) {
					Files.delete(tmpFile.toPath());
					copyIs.close();
					throw e;
				}
				try {
					return new FileInputStream(tmpFile);
				} catch (final Exception e) {
					BonitaStudioLog.error(e, CommonRepositoryPlugin.PLUGIN_ID);
					return null;
				} finally {
					Files.delete(tmpFile.toPath());
					copyIs.close();
				}
			}
		}
		Files.delete(tmpFile.toPath());
		copyIs.close();
		return originalStream;
	}

	private String getNamespaceURI(final String fileName, final CopyInputStream copyIs, final URI resourceURI,
			final File tmpFile) throws IOException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			dBuilder.setErrorHandler(new ErrorHandler() {

				@Override
				public void warning(SAXParseException exception) throws SAXException {
				}

				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
				}

				@Override
				public void error(SAXParseException exception) throws SAXException {
				}
			});
			dBuilder.parse(tmpFile);
		} catch (ParserConfigurationException | SAXException e1) {
			throw new IOException(e1);
		}

		return ReleaseUtils.getNamespaceURI(resourceURI);
	}

	public Migrator getMigrator(final String nsURI) {
		return MigratorRegistry.getInstance().getMigrator(nsURI);
	}

	protected Release getRelease(final Migrator targetMigrator, final Resource resource) {
		Set<Release> uriReleases = targetMigrator.getRelease(resource.getURI());
		if (uriReleases.size() == 1) {
			// only 1 release, version is probably in namespace
			return uriReleases.iterator().next();
		} else {
			final String modelVersion = getModelVersion(resource);
			Optional<Release> matchingRelease = targetMigrator.getReleases().stream().filter(uriReleases::contains)
					.filter(r -> modelVersion == null || modelVersion.equals(r.getLabel())).findFirst();
			return matchingRelease.orElseGet(() ->
			// take first release when none match
			targetMigrator.getReleases().stream().filter(uriReleases::contains).findFirst().orElse(null));
		}
	}

	/**
	 * Get the model version, loading the resource when necessary
	 * 
	 * @param resource the model resource
	 * @return model version or null when no clue
	 */
	protected String getModelVersion(final Resource resource) {
		// by default, we do not know how to get the version
		return null;
	}

	protected void performMigration(final Migrator migrator, final URI resourceURI, final Release release)
			throws MigrationException {
		migrator.setLevel(ValidationLevel.RELEASE);
		Map<String, Object> loadOptions = new HashMap<>();
		loadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		try {
			migrator.migrateAndSave(Collections.singletonList(resourceURI), release, null,
					AbstractRepository.NULL_PROGRESS_MONITOR, loadOptions);
		} catch (RuntimeException e) {
			throw new MigrationException(String.format("Failed to migrate %s", resourceURI), e);
		}

	}

	protected Resource getTmpEMFResource(final String fileName, final File originalFile) throws IOException {
		final EditingDomain editingDomain = getEditingDomain();
		var tmpFile = Files.createTempFile(null, fileName);
		Files.copy(originalFile.toPath(), tmpFile, StandardCopyOption.REPLACE_EXISTING);
		return editingDomain.getResourceSet().createResource(URI.createFileURI(tmpFile.toFile().getAbsolutePath()));
	}

}
