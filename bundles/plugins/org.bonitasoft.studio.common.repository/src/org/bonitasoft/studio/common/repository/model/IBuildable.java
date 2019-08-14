/**
 * 
 */
package org.bonitasoft.studio.common.repository.model;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IBuildable {

    public void build(IPath buildPath, IProgressMonitor monitor) throws CoreException;
    
}
