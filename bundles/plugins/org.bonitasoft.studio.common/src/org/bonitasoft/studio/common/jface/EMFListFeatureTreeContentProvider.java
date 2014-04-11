package org.bonitasoft.studio.common.jface;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Mickael Istria
 *
 */
public class EMFListFeatureTreeContentProvider implements ITreeContentProvider {

	private EStructuralFeature feature;

	public EMFListFeatureTreeContentProvider(EStructuralFeature feature) {
		this.feature = feature;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object inputElement) {
		Object eGet = ((EObject)inputElement).eGet(feature);
		if(eGet instanceof List<?>){
			return ((List<?>) eGet).toArray();	
		}
		return new Object[0];
	}

	public boolean hasChildren(Object element) {
		return false;
	}

	public Object getParent(Object element) {
		return null;
	}

	public Object[] getChildren(Object parentElement) {
		return null;
	}
}