package org.bonitasoft.studio.common.refactoring;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class BonitaScriptRefactoringContentProvider implements
		ITreeContentProvider {

	@Override
	public void dispose() {
		
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput,
			Object newInput) {
		
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof DiffNode){
			return ((DiffNode) inputElement).getChildren();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (((DiffNode)parentElement).hasChildren()){
			return ((DiffNode)parentElement).getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		final int length = ((DiffNode)element).getChildren().length;
		return length!=0;
	}
	

}
