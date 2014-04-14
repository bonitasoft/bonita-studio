package org.bonitasoft.studio.expression.editor.viewer;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ObservableExpressionContentProvider implements IContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		if(newInput != null){
			if(newInput instanceof IObservableValue){
				newInput = ((IObservableValue) newInput).getValue();
			}
			if(oldInput instanceof IObservableValue){
				oldInput = ((IObservableValue) oldInput).getValue();
			}
			((ExpressionViewer)viewer).inputChanged(newInput, oldInput);
		}

	}

}
