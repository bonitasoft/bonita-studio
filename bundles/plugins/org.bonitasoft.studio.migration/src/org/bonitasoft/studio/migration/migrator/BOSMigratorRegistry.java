/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BMW Car IT - Initial API and implementation
 *     Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.bonitasoft.studio.migration.migrator;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edapt.common.LoggingUtils;
import org.eclipse.emf.edapt.common.URIUtils;
import org.eclipse.emf.edapt.internal.migration.execution.BundleClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.IClassLoader;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.spi.migration.MigrationPlugin;
import org.osgi.framework.Bundle;


public final class BOSMigratorRegistry {

	/** Singleton instance. */
	private static BOSMigratorRegistry migratorRegistry;

	/** Registered migrators identified by unversioned namespace URI. */
	private final Map<String, BOSMigrator> migrators;

	/** Private default constructor. */
	private BOSMigratorRegistry() {
		migrators = new HashMap<String, BOSMigrator>();
		if (Platform.isRunning()) {
			registerExtensionMigrators();
		}
	}

	/** Getter for instance. */
	public static BOSMigratorRegistry getInstance() {
		if (migratorRegistry == null) {
			migratorRegistry = new BOSMigratorRegistry();
		}
		return migratorRegistry;
	}

	/** Register all migrators from extensions. */
	private void registerExtensionMigrators() {
		final IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		final IConfigurationElement[] configurationElements = extensionRegistry
				.getConfigurationElementsFor("org.bonitasoft.studio.edapt.migrators");

		for (final IConfigurationElement configurationElement : configurationElements) {
			registerExtensionMigrator(configurationElement);
		}
	}

	/** Register migrator for one extension. */
	private void registerExtensionMigrator(
			final IConfigurationElement configurationElement) {

		final String migrationPath = configurationElement.getAttribute("path");

		final IContributor contributor = configurationElement.getContributor();
		final String bundleName = contributor.getName();
		final Bundle bundle = Platform.getBundle(bundleName);
		final URI migratorURI = URI.createPlatformPluginURI("/" + bundleName + "/"
				+ migrationPath, true);
		try {
			registerMigrator(migratorURI, new BundleClassLoader(bundle));
		} catch (final MigrationException e) {
			LoggingUtils.logError(MigrationPlugin.getPlugin(), e);
		}
	}

	/** Register a migrator by its URL. */
	public void registerMigrator(final URL migratorURL, final IClassLoader loader)
			throws MigrationException {
		final BOSMigrator migrator = new BOSMigrator(URIUtils.getURI(migratorURL), loader);
		for (final String nsURI : migrator.getNsURIs()) {
			migrators.put(nsURI, migrator);
		}
	}

	/** Register a migrator by its URI. */
	public void registerMigrator(final URI migratorURI, final IClassLoader loader)
			throws MigrationException {
		registerMigrator(URIUtils.getURL(migratorURI), loader);
	}

	/** Get a migrator by its namespace already stripped from version. */
	public BOSMigrator getMigrator(final String nsURI) {
		return migrators.get(nsURI);
	}

	/** Get a migrator for a certain model. */
	public BOSMigrator getMigrator(final URI modelURI) {
		final String nsURI = ReleaseUtils.getNamespaceURI(modelURI);
		return getMigrator(nsURI);
	}
}
