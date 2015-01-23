/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Johannes Faltermeier - Initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.edapt.internal.migration;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edapt.spi.migration.MigrationPlugin;
import org.osgi.framework.Bundle;

/**
 * Helper class for reading custom {@link EFactory EFactories} from the extension point.
 * 
 * @author jfaltermeier
 *
 */
public final class FactoryHelper {
	
	/**
	 * The instance.
	 */
	public static final FactoryHelper INSTANCE = new FactoryHelper();

	private static final String CLASS = "class";
	private static final String NS_URI = "nsURI";
	private static final String POINT_ID = "org.eclipse.emf.edapt.factories";

	private Map<String, Class<? extends EFactory>> nsURIToFactoryMap;

	private FactoryHelper() {
		nsURIToFactoryMap = new LinkedHashMap<String, Class<? extends EFactory>>();
		readExtensionPoint();
	}

	private void readExtensionPoint() {
		final IExtensionRegistry extensionRegistry = Platform
				.getExtensionRegistry();
		if (extensionRegistry == null) {
			return;
		}
		final IConfigurationElement[] configurationElements = extensionRegistry
				.getConfigurationElementsFor(POINT_ID);
		for (final IConfigurationElement configurationElement : configurationElements) {
			registerFactory(configurationElement);
		}
	}

	private void registerFactory(IConfigurationElement configurationElement) {
		try {
			String nsURI = configurationElement.getAttribute(NS_URI);
			String bundle = configurationElement.getContributor().getName();
			String className = configurationElement.getAttribute(CLASS);
			Class<? extends EFactory> clazz = loadClass(bundle, className);
			if (nsURI == null || clazz == null) {
				return;
			}
			nsURIToFactoryMap.put(nsURI, clazz);
		} catch (ClassNotFoundException e) {
			MigrationPlugin.INSTANCE.log(e);
		}

	}

	@SuppressWarnings("unchecked")
	private static Class<? extends EFactory> loadClass(String bundleName,
			String clazz) throws ClassNotFoundException {
		final Bundle bundle = Platform.getBundle(bundleName);
		if (bundle == null) {
			MigrationPlugin.INSTANCE.log("Could not get bundle " + bundleName + " from platform.");
		}
		return (Class<? extends EFactory>) bundle.loadClass(clazz);
	}

	/**
	 * Overrides the {@link EFactory} for the given {@link EPackage} if a custom EFactory 
	 * was registered for the factory's nsURI.
	 * 
	 * @param ePackage the package
	 */
	public void overrideFactory(EPackage ePackage) {
		try {
			if (!nsURIToFactoryMap.containsKey(ePackage.getNsURI())) {
				return;
			}
			Class<? extends EFactory> clazz = nsURIToFactoryMap.get(ePackage
					.getNsURI());
			EFactory eFactory = clazz.getConstructor().newInstance();
			ePackage.setEFactoryInstance(eFactory);
		} catch (InstantiationException e) {
			MigrationPlugin.INSTANCE.log(e);
		} catch (IllegalAccessException e) {
			MigrationPlugin.INSTANCE.log(e);
		} catch (IllegalArgumentException e) {
			MigrationPlugin.INSTANCE.log(e);
		} catch (InvocationTargetException e) {
			MigrationPlugin.INSTANCE.log(e);
		} catch (NoSuchMethodException e) {
			MigrationPlugin.INSTANCE.log(e);
		} catch (SecurityException e) {
			MigrationPlugin.INSTANCE.log(e);
		}
	}
}
