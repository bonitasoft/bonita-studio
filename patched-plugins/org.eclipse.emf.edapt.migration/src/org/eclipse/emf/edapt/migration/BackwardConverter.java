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
package org.eclipse.emf.edapt.migration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edapt.common.EcoreUtils;
import org.eclipse.emf.edapt.common.ResourceUtils;
import org.eclipse.emf.edapt.common.ReversableMap;
import org.eclipse.emf.edapt.common.TwoWayIdentityHashMap;

/**
 * Convert a model graph to an EMF model.
 * 
 * @author herrmama
 * @author $Author: mherrmannsd $
 * @version $Rev: 264 $
 * @levd.rating YELLOW Hash: 4D9E84B44F84E9C760AB3E79983988B4
 */
public class BackwardConverter {

    /** Mapping from graph nodes to EMF model elements. */
    private ReversableMap<Instance, EObject> mapping;

    /** Convert model graph to EMF elements. */
    public ResourceSet convert(Model model) {
        model.getMetamodel().refreshCaches();

        mapping = new TwoWayIdentityHashMap<Instance, EObject>();

        initObjects(model);
        ResourceSet resourceSet = initResources(model);
        initProperties(model);

        return resourceSet;
    }

    /** Create an EMF model element for each node. */
    private void initObjects(Model model) {
        for (Type type : model.getTypes()) {
            createObjects(type);
        }
    }

    /** Create all EMF model elements of a certain type. */
    private void createObjects(Type type) {
        EClass sourceClass = type.getEClass();
        EClass targetClass = resolveEClass(sourceClass);
        for (Instance element : type.getInstances()) {
            EObject eObject = EcoreUtil.create(targetClass);
            if (element.isProxy()) {
                ((InternalEObject) eObject).eSetProxyURI(element.getUri());
            }
            mapping.put(element, eObject);
        }
    }

    /** Resolve the class to which an instance should be converted. */
    protected EClass resolveEClass(EClass eClass) {
        return eClass;
    }

    /** Determine root EMF model elements. */
    private ResourceSet initResources(Model model) {
        ResourceSet resourceSet = new ResourceSetImpl();
        ResourceUtils.register(model.getMetamodel().getEPackages(), resourceSet
                .getPackageRegistry());
        for (ModelResource modelResource : model.getResources()) {
            Resource resource = resourceSet.createResource(modelResource
                    .getUri());
            if (resource instanceof XMLResource) {
                XMLResource xmlResource = (XMLResource) resource;
                if (modelResource.getEncoding() != null) {
                    xmlResource.setEncoding(modelResource.getEncoding());
                }
            }
            for (Instance element : modelResource.getRootInstances()) {
                resource.getContents().add(resolve(element));
            }
        }
        return resourceSet;
    }

    /** Initialize the EMF model elements based on the edges. */
    private void initProperties(Model model) {
        for (Type type : model.getTypes()) {
            for (Instance instance : type.getInstances()) {
                initProperties(instance);
                String uuid = instance.getUuid();
                if (uuid != null) {
                    EObject eObject = resolve(instance);
                    EcoreUtils.setUUID(eObject, uuid);
                }
            }
        }
    }

    /** Initialize an EMF model element based on the edges outgoing from a node. */
    @SuppressWarnings("unchecked")
    private void initProperties(Instance element) {
        EObject eObject = resolve(element);
        for (Slot slot : element.getSlots()) {
            EStructuralFeature sourceFeature = slot.getEFeature();
            EStructuralFeature targetFeature = resolveFeature(sourceFeature);
            if (ignore(sourceFeature)) {
                continue;
            }
            if (slot instanceof AttributeSlot) {
                if (sourceFeature.getEType() instanceof EEnum) {
                    AttributeSlot attributeSlot = (AttributeSlot) slot;
                    if (sourceFeature.isMany()) {
                        EList values = (EList) eObject.eGet(targetFeature);
                        for (Object value : attributeSlot.getValues()) {
                            values.add(resolveLiteral((EEnumLiteral) value));
                        }
                    } else {
                        if (!attributeSlot.getValues().isEmpty()) {
                            Object literal = attributeSlot.getValues().get(0);
                            if(literal instanceof EEnumLiteral){
                                eObject.eSet(targetFeature,
                                        resolveLiteral((EEnumLiteral)literal));
                            }else{
                                //Patch for Notation model
                                eObject.eSet(targetFeature,literal);
                            }
                        }
                    }
                } else {
                	//Fix NPE With e4
                	eObject.eSetDeliver(false);
                    eObject.eSet(targetFeature, element.get(sourceFeature));
                }
            } else {
                ReferenceSlot referenceSlot = (ReferenceSlot) slot;
                if (sourceFeature.isMany()) {
                    EList values = (EList) eObject.eGet(targetFeature);
                    int index = 0;
                    for (Instance value : referenceSlot.getValues()) {
                        EObject valueEObject = resolve(value);
                        if (sourceFeature.isUnique()
                                && values.contains(valueEObject)) {
                            values.move(index, valueEObject);
                        } else {
                            values.add(index, valueEObject);
                        }
                        index++;
                    }
                } else {
                    if (!referenceSlot.getValues().isEmpty()) {
                        eObject.eSet(targetFeature, resolve(referenceSlot
                                .getValues().get(0)));
                    }
                }
            }
        }
    }

    /** Resolve the feature to which a value should be transferred. */
    protected EStructuralFeature resolveFeature(EStructuralFeature feature) {
        return feature;
    }

    /** Resolve the literal value of an enumeration. */
    protected Enumerator resolveLiteral(EEnumLiteral literal) {
        return literal;
    }

    /**
     * Determines whether a certain feature should be ignored during conversion.
     */
    private boolean ignore(EStructuralFeature feature) {
        return feature.isTransient()
                || !feature.isChangeable()
                ||
                // according to
                // http://www.eclipse.org/newsportal/article.php?id=26780&group=eclipse.tools.emf
                // the following three references need to be ignored
                EcorePackage.eINSTANCE.getEClass_ESuperTypes().equals(feature)
                || EcorePackage.eINSTANCE.getETypedElement_EType().equals(
                        feature)
                        || EcorePackage.eINSTANCE.getEOperation_EExceptions().equals(
                                feature);
    }

    /** Get the EMF model element corresponding to a node. */
    private EObject resolve(Instance instance) {
        return mapping.get(instance);
    }
}
