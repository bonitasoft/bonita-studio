/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.internal.migration.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edapt.internal.common.ResourceUtils;
import org.eclipse.emf.edapt.spi.migration.Metamodel;

/**
 * Helper methods to deal with model backup.
 *
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 79D1350584168827FC59AC2546F8221E
 */
public final class BackupUtils {

	/** File extension for backed up model. */
	public static final String BACKUP_FILE_EXTENSION = "backup"; //$NON-NLS-1$

	/** File extension for log file. */
	public static final String LOG_FILE_EXTENSION = "log"; //$NON-NLS-1$

	/** Constructor. */
	private BackupUtils() {
		// hidden, since this class only provides static helper methods
	}

	/**
	 * Get the URI of the backup model corresponding to the URI of the model.
	 *
	 * @param modelURI
	 *            URI of model
	 * @return URI of backup model
	 */
	public static URI getBackupURI(URI modelURI) {
		return modelURI.appendFileExtension(BACKUP_FILE_EXTENSION);
	}

	/**
	 * Get the URI of the model corresponding to the URI of the backup model.
	 *
	 * @param backupURI
	 *            URI of backup model
	 * @return URI of model
	 */
	public static URI getModelURI(URI backupURI) {
		if (BACKUP_FILE_EXTENSION.equals(backupURI.fileExtension())) {
			return backupURI.trimFileExtension();
		}
		return null;
	}

	/**
	 * Get the URI of the log file corresponding to the URI of the model.
	 *
	 * @param modelURI
	 *            URI of model
	 * @return URI of log file
	 */
	public static URI getLogURI(URI modelURI) {
		return modelURI.appendFileExtension(LOG_FILE_EXTENSION);
	}

	/** Backup a model based on a number of {@link URI}s. */
	public static List<URI> backup(List<URI> modelURIs, Metamodel metamodel)
		throws IOException {
		return copy(modelURIs, metamodel, new BackupMapper());
	}

	/** Backup a model based on a number of {@link URI}s. */
	public static List<URI> restore(List<URI> backupURIs, Metamodel metamodel)
		throws IOException {
		return copy(backupURIs, metamodel, new RestoreMapper());
	}

	/** Copy a model based on a number of {@link URI}s. */
	public static List<URI> copy(List<URI> sourceURIs, Metamodel metamodel,
		URIMapper mapper) throws IOException {
		final List<URI> targetURIs = new ArrayList<URI>();
		final ResourceSet model = ResourceUtils.loadResourceSet(sourceURIs, metamodel
			.getEPackages());
		for (final Resource resource : model.getResources()) {
			if (resource.getURI() == null
				|| resource.getURI().isPlatformPlugin()) {
				continue;
			}
			final URI targetURI = mapper.map(resource.getURI());
			if (targetURI != null) {
				resource.setURI(targetURI);
				targetURIs.add(targetURI);
			}
		}
		ResourceUtils.saveResourceSet(model, null);
		return targetURIs;
	}

	/** Mapper to perform a mapping on URIs. */
	public static interface URIMapper {
		/** Map a source URI to a target URI. */
		URI map(URI uri);
	}

	/** Mapper to backup a model based on a number of {@link URI}s. */
	private static class BackupMapper implements URIMapper {
		/** {@inheritDoc} */
		@Override
		public URI map(URI uri) {
			return getBackupURI(uri);
		}
	}

	/** Mapper to restore a model based on a number of {@link URI}s. */
	private static class RestoreMapper implements URIMapper {
		/** {@inheritDoc} */
		@Override
		public URI map(URI uri) {
			return getModelURI(uri);
		}
	}
}
