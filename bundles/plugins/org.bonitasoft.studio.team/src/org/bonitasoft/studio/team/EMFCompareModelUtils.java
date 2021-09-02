/*******************************************************************************
 * Copyright (c) 2006, 2009 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Obeo - initial API and implementation
 *******************************************************************************/
package org.bonitasoft.studio.team;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.EMFCompareMessages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * Utility class for model loading/saving and serialization.
 *
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class EMFCompareModelUtils {

	/**
	 * Utility classes don't need to (and shouldn't) be instantiated.
	 */
	private EMFCompareModelUtils() {
		// prevents instantiation
	}

	/**
	 * This will create a {@link Resource} given the model extension it is intended for.
	 *
	 * @param modelURI
	 *            {@link org.eclipse.emf.common.util.URI URI} where the model is stored.
	 * @return The {@link Resource} given the model extension it is intended for.
	 */
	public static Resource createResource(final URI modelURI) {
		return createResource(modelURI, new ResourceSetImpl());
	}

	/**
	 * This will create a {@link Resource} given the model extension it is intended for and a ResourceSet.
	 *
	 * @param modelURI
	 *            {@link org.eclipse.emf.common.util.URI URI} where the model is stored.
	 * @param resourceSet
	 *            The {@link ResourceSet} to load the model in.
	 * @return The {@link Resource} given the model extension it is intended for.
	 */
	public static Resource createResource(final URI modelURI, final ResourceSet resourceSet) {
		String fileExtension = modelURI.fileExtension();
		if (fileExtension == null || fileExtension.length() == 0) {
			fileExtension = Resource.Factory.Registry.DEFAULT_EXTENSION;
		}

		// First search the resource set for our resource factory
		Resource.Factory.Registry registry = resourceSet.getResourceFactoryRegistry();
		Object resourceFactory = registry.getExtensionToFactoryMap().get(fileExtension);
		if (resourceFactory == null) {
			// then the global registry
			registry = Resource.Factory.Registry.INSTANCE;
			resourceFactory = registry.getExtensionToFactoryMap().get(fileExtension);
			if (resourceFactory != null) {
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(fileExtension,
						resourceFactory);
			}
		}

		return resourceSet.createResource(modelURI);
	}

	/**
	 * Loads a model from a {@link java.io.File File} in a given {@link ResourceSet}.
	 * <p>
	 * This will return the first root of the loaded model, other roots can be accessed via the resource's
	 * content.
	 * </p>
	 *
	 * @param file
	 *            {@link java.io.File File} containing the model to be loaded.
	 * @param resourceSet
	 *            The {@link ResourceSet} to load the model in.
	 * @return The model loaded from the file.
	 * @throws IOException
	 *             If the given file does not exist.
	 */
	public static EObject load(final File file, final ResourceSet resourceSet) throws IOException {
		return load(URI.createFileURI(file.getPath()), resourceSet);
	}

	/**
	 * Load a model from an {@link java.io.InputStream InputStream} in a given {@link ResourceSet}.
	 * <p>
	 * This will return the first root of the loaded model, other roots can be accessed via the resource's
	 * content.
	 * </p>
	 *
	 * @param stream
	 *            The inputstream to load from
	 * @param fileName
	 *            The original filename
	 * @param resourceSet
	 *            The {@link ResourceSet} to load the model in.
	 * @return The loaded model
	 * @throws IOException
	 *             If the given file does not exist.
	 */
	public static EObject load(final InputStream stream, final String fileName, final ResourceSet resourceSet)
			throws IOException {
		if (stream == null)
         {
            throw new NullPointerException(EMFCompareMessages.getString("ModelUtils.NullInputStream")); //$NON-NLS-1$
        }
		EObject result = null;

		final Resource modelResource = createResource(URI.createURI(fileName), resourceSet);
		modelResource.load(stream, Collections.emptyMap());
		if (modelResource.getContents().size() > 0) {
            result = modelResource.getContents().get(0);
        }
		return result;
	}

	/**
	 * Loads a model from the String representing the location of a model.
	 * <p>
	 * This can be called with pathes of the form
	 * <ul>
	 * <li><code>/pluginID/path</code></li>
	 * <li><code>platform:/plugin/pluginID/path</code></li>
	 * <li><code>platform:/resource/pluginID/path</code></li>
	 * </ul>
	 * </p>
	 * <p>
	 * This will return the first root of the loaded model, other roots can be accessed via the resource's
	 * content.
	 * </p>
	 *
	 * @param path
	 *            Location of the model.
	 * @param resourceSet
	 *            The {@link ResourceSet} to load the model in.
	 * @return The model loaded from the path.
	 * @throws IOException
	 *             If the path doesn't resolve to a reachable location.
	 */
	public static EObject load(final String path, final ResourceSet resourceSet) throws IOException {
		if (path == null || "".equals(path))
         {
            throw new IllegalArgumentException(EMFCompareMessages.getString("ModelUtils.NullPath")); //$NON-NLS-1$
        }

		final EObject result;
		// path is already defined with a platform scheme
		if (path.startsWith("platform")) {
            result = load(URI.createURI(path), resourceSet);
        } else {
			EObject temp = null;
			try {
				// Will first try and load as if the model is in the plugins
				temp = load(URI.createPlatformPluginURI(path, true), resourceSet);
			} catch (final IOException e) {
				// Model wasn't in the plugins, try and load it within the workspace
				try {
					temp = load(URI.createPlatformResourceURI(path, true), resourceSet);
				} catch (final IOException ee) {
					// Silently discarded, will fail later on
				}
			}
			result = temp;
		}
		if (result == null)
         {
            throw new IOException(EMFCompareMessages.getString("ModelUtils.LoadFailure", path)); //$NON-NLS-1$
        }
		return result;
	}

	/**
	 * Loads a model from an {@link org.eclipse.emf.common.util.URI URI} in a given {@link ResourceSet}.
	 * <p>
	 * This will return the first root of the loaded model, other roots can be accessed via the resource's
	 * content.
	 * </p>
	 *
	 * @param modelURI
	 *            {@link org.eclipse.emf.common.util.URI URI} where the model is stored.
	 * @param resourceSet
	 *            The {@link ResourceSet} to load the model in.
	 * @return The model loaded from the URI.
	 * @throws IOException
	 *             If the given file does not exist.
	 */
	public static EObject load(final URI modelURI, final ResourceSet resourceSet) throws IOException {
		EObject result = null;

		final Resource modelResource = createResource(modelURI, resourceSet);
		modelResource.load(Collections.emptyMap());
		if (modelResource.getContents().size() > 0) {
            result = modelResource.getContents().get(0);
        }
		return result;
	}

}