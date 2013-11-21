package org.bonitasoft.studio.common;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public abstract class AbstractRefactorOperation implements
		IRunnableWithProgress {

	protected String newValue;
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		

	}
	
	public void setNewValue(String newValue){
		this.newValue = newValue;
	}

}
