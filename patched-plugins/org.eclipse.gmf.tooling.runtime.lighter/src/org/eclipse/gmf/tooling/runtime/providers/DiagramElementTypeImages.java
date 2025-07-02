package org.eclipse.gmf.tooling.runtime.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public class DiagramElementTypeImages {

	private AdapterFactory myAdapterFactory;

	private ImageRegistry myImageRegistry;

	public DiagramElementTypeImages(AdapterFactory adapterFactory) {
		this(adapterFactory, new ImageRegistry());
	}

	public DiagramElementTypeImages(AdapterFactory adapterFactory, ImageRegistry imageRegistry) {
		myAdapterFactory = adapterFactory;
		myImageRegistry = imageRegistry;
	}

	public ImageRegistry getImageRegistry() {
		if (myImageRegistry == null) {
			myImageRegistry = new ImageRegistry();
		}
		return myImageRegistry;
	}

	public String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	public Image getImage(ENamedElement element) {
		if (element == null) {
			return null;
		}
		getImageDescriptor(element); //ensures is cached in registry
		String key = getImageRegistryKey(element);
		return getImageRegistry().get(key);
	}

	public ImageDescriptor getImageDescriptor(ENamedElement element) {
		if (element == null) {
			return null;
		}
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	public ImageDescriptor getProvidedImageDescriptor(ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			//			ImageDescriptor feelingLucky = getReferenceImageDescritor((EStructuralFeature) element);
			//			if (feelingLucky != null) {
			//				return feelingLucky;
			//			}
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = findNotAbstractEClassOrSubClass(feature.getEContainingClass());
			EClass eType = findNotAbstractEClassOrSubClass(feature.getEType());
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType != null && !eType.isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				EObject instance = eClass.getEPackage().getEFactoryInstance().create(eClass);
				return getItemImageDescriptor(instance);
			}
		}
		// TODO : support structural features
		return null;
	}

	public ImageDescriptor getItemImageDescriptor(EObject item) {
		IItemLabelProvider labelProvider = (IItemLabelProvider) myAdapterFactory.adapt(item, IItemLabelProvider.class);
		if (labelProvider != null) {
			return ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(item));
		}
		return null;
	}

	public ImageDescriptor getReferenceImageDescritor(EStructuralFeature feature) {
		EObject containerInstance = instantiate(feature.getEContainingClass());
		if (containerInstance == null) {
			return null;
		}
		IItemLabelProvider labelProvider = (IItemLabelProvider) myAdapterFactory.adapt(containerInstance, IItemLabelProvider.class);
		if (false == labelProvider instanceof CreateChildCommand.Helper) {
			return null;
		}
		CreateChildCommand.Helper helper = (CreateChildCommand.Helper) labelProvider;
		Object imageUrl = helper.getCreateChildImage(containerInstance, feature, null, null);
		if (imageUrl == null) {
			EObject child = instantiate(feature.getEType());
			if (child != null) {
				imageUrl = helper.getCreateChildImage(containerInstance, feature, child, null);
			}
		}
		if (imageUrl == null) {
			return null;
		}
		return ExtendedImageRegistry.getInstance().getImageDescriptor(imageUrl);
	}

	protected EObject instantiate(EClassifier classifier) {
		if (false == classifier instanceof EClass) {
			return null;
		}
		EClass instantiatable = findNotAbstractEClassOrSubClass((EClass) classifier);
		return instantiatable == null ? null : instantiatable.getEPackage().getEFactoryInstance().create(instantiatable);
	}

	protected EClass findNotAbstractEClassOrSubClass(EClassifier classifier) {
		if (false == classifier instanceof EClass) {
			return null;
		}
		EClass eClass = (EClass) classifier;
		if (!eClass.isAbstract()) {
			return eClass;
		}
		for (EClassifier nextFromSameEPackage : eClass.getEPackage().getEClassifiers()) {
			if (nextFromSameEPackage instanceof EClass) {
				EClass nextEClass = (EClass) nextFromSameEPackage;
				if (!nextEClass.isAbstract() && eClass.isSuperTypeOf(nextEClass)) {
					return nextEClass;
				}
			}
		}
		return null;
	}

}
