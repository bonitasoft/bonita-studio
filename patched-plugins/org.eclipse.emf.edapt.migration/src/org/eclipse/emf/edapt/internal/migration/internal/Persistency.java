/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.internal.migration.internal;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edapt.common.IResourceSetFactory;
import org.eclipse.emf.edapt.common.IResourceSetProcessor;
import org.eclipse.emf.edapt.internal.common.ResourceUtils;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.MetamodelResource;
import org.eclipse.emf.edapt.spi.migration.MigrationFactory;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * Helper class for loading and saving models.
 *
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 7340771F1DE173BDAA2534B8901681B1
 */
public class Persistency {

	/** Load metamodel based on {@link URI}. */
	public static Metamodel loadMetamodel(URI metamodelURI) throws IOException {
		final ResourceSet resourceSet = ResourceUtils.loadResourceSet(metamodelURI);

		return loadMetamodel(resourceSet);
	}

	/** Create metamodel from a {@link ResourceSet}. */
	public static Metamodel loadMetamodel(ResourceSet resourceSet) {
		ResourceUtils.resolveAll(resourceSet);
		final Metamodel metamodel = MigrationFactory.eINSTANCE.createMetamodel();
		for (final Resource resource : resourceSet.getResources()) {
			final MetamodelResource metamodelResource = MigrationFactory.eINSTANCE
				.createMetamodelResource();
			metamodelResource.setUri(resource.getURI());
			metamodel.getResources().add(metamodelResource);
			for (final EObject element : resource.getContents()) {
				if (element instanceof EPackage) {
					final EPackage ePackage = (EPackage) element;
					FactoryHelper.INSTANCE.overrideFactory(ePackage);
					for (final EPackage sub : ePackage.getESubpackages()) {
						FactoryHelper.INSTANCE.overrideFactory(sub);
					}
					metamodelResource.getRootPackages().add(ePackage);
				}
			}
		}

		return metamodel;
	}

	/** Load metamodel based on file name. */
	public static Metamodel loadMetamodel(String fileName) throws IOException {
		return loadMetamodel(URI.createFileURI(fileName));
	}

	/** Save metamodel based on {@link URI}. */
	public static void saveMetamodel(Metamodel metamodel) throws IOException {
		final ResourceSet resourceSet = new ResourceSetImpl();

		for (final MetamodelResource metamodelResource : metamodel.getResources()) {
			final Resource resource = resourceSet.createResource(metamodelResource
				.getUri());
			resource.getContents().addAll(metamodelResource.getRootPackages());
		}

		ResourceUtils.saveResourceSet(resourceSet, null);
	}

	/** Load model based on {@link URI} for model and metamodel. */
	public static Model loadModel(URI modelURI, URI metamodelURI,
		IResourceSetFactory resourceSetFactory, IResourceSetProcessor postLoadProcessor) throws IOException {
		final Metamodel metamodel = loadMetamodel(metamodelURI);
		final Model model = loadModel(modelURI, metamodel, resourceSetFactory, postLoadProcessor);
		return model;
	}

	/** Load model based on {@link URI} and metamodel. */
	public static Model loadModel(URI modelURI, Metamodel metamodel,
		IResourceSetFactory resourceSetFactory, IResourceSetProcessor postLoadProcessor) throws IOException {
		return loadModel(Collections.singletonList(modelURI), metamodel,
			resourceSetFactory, postLoadProcessor);
	}

	/** Load model based on a set of {@link URI} and metamodel. */
	public static Model loadModel(List<URI> modelURIs, Metamodel metamodel,
		IResourceSetFactory resourceSetFactory, IResourceSetProcessor postLoadProcessor) throws IOException {
		final ResourceSet resourceSet = ResourceUtils.loadResourceSet(modelURIs,
			metamodel.getEPackages(), resourceSetFactory);
		if (postLoadProcessor != null) {
			postLoadProcessor.process(resourceSet);
		}
		final ForwardConverter fConverter = new ForwardConverter();
		final Model model = fConverter.convert(resourceSet);
		model.setMetamodel(metamodel);
		return model;
	}

	/** Load model based on file name and metamodel. */
	public static Model loadModel(String fileName, Metamodel metamodel,
		IResourceSetFactory resourceSetFactory, IResourceSetProcessor postLoadProcessor) throws IOException {
		return loadModel(URI.createFileURI(fileName), metamodel,
			resourceSetFactory, postLoadProcessor);
	}

	/** Save model based on {@link URI}. */
	public static void saveModel(Model model, Map<String, Object> options) throws IOException {
		final BackwardConverter bConverter = new BackwardConverter();
		final ResourceSet resourceSet = bConverter.convert(model);
		ResourceUtils.saveResourceSet(resourceSet, options);
	}
}
