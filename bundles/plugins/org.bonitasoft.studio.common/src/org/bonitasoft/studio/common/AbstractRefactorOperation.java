package org.bonitasoft.studio.common;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.jface.operation.IRunnableWithProgress;

public abstract class AbstractRefactorOperation implements
		IRunnableWithProgress {

	protected String newValue;
	protected CompoundCommand cc;
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		

	}
	
	public void setNewValue(String newValue){
		this.newValue = newValue;
	}
	
	public void setCompoundCommand(CompoundCommand cc){
		this.cc=cc;
	}

}
