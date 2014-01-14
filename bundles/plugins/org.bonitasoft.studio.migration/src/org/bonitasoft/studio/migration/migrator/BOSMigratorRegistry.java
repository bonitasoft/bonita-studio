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
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.MigrationPlugin;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.BundleClassLoader;
import org.eclipse.emf.edapt.migration.execution.IClassLoader;
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
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IConfigurationElement[] configurationElements = extensionRegistry
				.getConfigurationElementsFor("org.bonitasoft.studio.edapt.migrators");

		for (IConfigurationElement configurationElement : configurationElements) {
			registerExtensionMigrator(configurationElement);
		}
	}

	/** Register migrator for one extension. */
	private void registerExtensionMigrator(
			IConfigurationElement configurationElement) {

		String migrationPath = configurationElement.getAttribute("path");

		IContributor contributor = configurationElement.getContributor();
		String bundleName = contributor.getName();
		Bundle bundle = Platform.getBundle(bundleName);
		URI migratorURI = URI.createPlatformPluginURI("/" + bundleName + "/"
				+ migrationPath, true);
		try {
			registerMigrator(migratorURI, new BundleClassLoader(bundle));
		} catch (MigrationException e) {
			LoggingUtils.logError(MigrationPlugin.getPlugin(), e);
		}
	}

	/** Register a migrator by its URL. */
	public void registerMigrator(URL migratorURL, IClassLoader loader)
			throws MigrationException {
		BOSMigrator migrator = new BOSMigrator(URIUtils.getURI(migratorURL), loader);
		for (String nsURI : migrator.getNsURIs()) {
			migrators.put(nsURI, migrator);
		}
	}

	/** Register a migrator by its URI. */
	public void registerMigrator(URI migratorURI, IClassLoader loader)
			throws MigrationException {
		registerMigrator(URIUtils.getURL(migratorURI), loader);
	}

	/** Get a migrator by its namespace already stripped from version. */
	public BOSMigrator getMigrator(String nsURI) {
		return migrators.get(nsURI);
	}

	/** Get a migrator for a certain model. */
	public BOSMigrator getMigrator(URI modelURI) {
		String nsURI = ReleaseUtils.getNamespaceURI(modelURI);
		return getMigrator(nsURI);
	}
}
