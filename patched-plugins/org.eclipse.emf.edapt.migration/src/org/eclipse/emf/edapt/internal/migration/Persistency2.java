/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.internal.migration;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edapt.common.ResourceUtils;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.MetamodelResource;
import org.eclipse.emf.edapt.spi.migration.MigrationFactory;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * Helper class for loading and saving models.
 *
 * @author herrmama
 * @author $Author: mherrmannsd $
 * @version $Rev: 138 $
 * @levd.rating YELLOW Hash: 7340771F1DE173BDAA2534B8901681B1
 */
public class Persistency2 {

    /** Load metamodel based on {@link URI}. */
    public static Metamodel loadMetamodel(final URI metamodelURI) throws IOException {
        final ResourceSet resourceSet = ResourceUtils.loadResourceSet(metamodelURI);

        return loadMetamodel(resourceSet);
    }

    /** Create metamodel from a {@link ResourceSet}. */
    public static Metamodel loadMetamodel(final ResourceSet resourceSet) {
        ResourceUtils.resolveAll(resourceSet);
        final Metamodel metamodel = MigrationFactory.eINSTANCE.createMetamodel();
        for (final Resource resource : resourceSet.getResources()) {
            final MetamodelResource metamodelResource = MigrationFactory.eINSTANCE.createMetamodelResource();
            metamodelResource.setUri(resource.getURI());
            metamodel.getResources().add(metamodelResource);
            for (final EObject element : resource.getContents()) {
                if (element instanceof EPackage) {
                    final EPackage ePackage = (EPackage) element;
                    metamodelResource.getRootPackages().add(ePackage);
                }
            }
        }

        return metamodel;
    }

    /** Load metamodel based on file name. */
    public static Metamodel loadMetamodel(final String fileName) throws IOException {
        return loadMetamodel(URI.createFileURI(fileName));
    }

    /** Save metamodel based on {@link URI}. */
    public static void saveMetamodel(final Metamodel metamodel) throws IOException {
        final ResourceSet resourceSet = new ResourceSetImpl();

        for (final MetamodelResource metamodelResource : metamodel.getResources()) {
            final Resource resource = resourceSet.createResource(metamodelResource.getUri());
            resource.getContents().addAll(metamodelResource.getRootPackages());
        }

        ResourceUtils.saveResourceSet(resourceSet);
    }

    /** Load model based on {@link URI} for model and metamodel. */
    public static Model loadModel(final URI modelURI, final URI metamodelURI) throws IOException {
        final Metamodel metamodel = loadMetamodel(metamodelURI);
        final Model model = loadModel(modelURI, metamodel);
        return model;
    }

    /** Load model based on {@link URI} and metamodel. */
    public static Model loadModel(final URI modelURI, final Metamodel metamodel) throws IOException {
        return loadModel(Collections.singletonList(modelURI), metamodel);
    }

    /** Load model based on a set of {@link URI} and metamodel. */
    public static Model loadModel(final List<URI> modelURIs, final Metamodel metamodel) throws IOException {
        final ResourceSet resourceSet = ResourceUtils.loadResourceSet(modelURIs, metamodel.getEPackages());
        final ForwardConverter fConverter = new ForwardConverter();
        final Model model = fConverter.convert(resourceSet);
        model.setMetamodel(metamodel);
        return model;
    }

    /** Load model based on file name and metamodel. */
    public static Model loadModel(final String fileName, final Metamodel metamodel) throws IOException {
        return loadModel(URI.createFileURI(fileName), metamodel);
    }

    /** Save model based on {@link URI}. */
    public static void saveModel(final Model model) throws IOException {
        final BackwardConverter bConverter = new BackwardConverter();
        final ResourceSet resourceSet = bConverter.convert(model);
        ResourceUtils.saveResourceSet(resourceSet);
    }
}