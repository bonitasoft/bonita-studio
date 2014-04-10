/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.editingdomain;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;

/**
 * 
 * @author Baptiste Mesta
 */
public class BonitaResourceSetInfoDelegate {


	private WorkspaceSynchronizer theSynchronizer;
	private final TransactionalEditingDomain editingDomain;
	private final List<WorkspaceSynchronizer.Delegate> delegates;
	private long theModificationStamp = IResource.NULL_STAMP;

	public BonitaResourceSetInfoDelegate(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
		this.delegates = new ArrayList<WorkspaceSynchronizer.Delegate>();
		startResourceListening();
	}

	public long getModificationStamp() {
		return theModificationStamp;
	}

	public void setModificationStamp(long modificationStamp) {
		theModificationStamp = modificationStamp;
	}

	public TransactionalEditingDomain getEditingDomain() {
		return editingDomain;
	}

	public void dispose() {
		stopResourceListening();
	}

	public final void stopResourceListening() {
		if (theSynchronizer != null) {
			theSynchronizer.dispose();
		}
		theSynchronizer = null;
	}

	public final void startResourceListening() {
		if (theSynchronizer == null) {
			theSynchronizer = new WorkspaceSynchronizer(getEditingDomain(), new CompositeSynchronizerDelegate());
		}
	}

	public boolean addWorkspaceSynchronizerDelegate(WorkspaceSynchronizer.Delegate delegate) {
		return delegates.add(delegate);
	}

	public boolean removeWorkspaceSynchronizerDelegate(WorkspaceSynchronizer.Delegate delegate) {
		return delegates.remove(delegate);
	}

	private class CompositeSynchronizerDelegate implements WorkspaceSynchronizer.Delegate {

		public boolean handleResourceChanged(final Resource resource) {
			org.eclipse.core.resources.IFile file = org.eclipse.emf.workspace.util.WorkspaceSynchronizer.getFile(resource);
			if (file != null) {
				try {
					file.refreshLocal(org.eclipse.core.resources.IResource.DEPTH_INFINITE, new NullProgressMonitor());
				} catch (org.eclipse.core.runtime.CoreException ex) {
					BonitaStudioLog.error(ex);
				}
			}
			resource.unload();
			synchronized (BonitaResourceSetInfoDelegate.this) {
				for (WorkspaceSynchronizer.Delegate delegate : delegates) {
					delegate.handleResourceChanged(resource);
				}
			}
			return true;
		}

		public boolean handleResourceMoved(final Resource resource, final URI newURI) {
			synchronized (BonitaResourceSetInfoDelegate.this) {
				for (WorkspaceSynchronizer.Delegate delegate : delegates) {
					delegate.handleResourceMoved(resource, newURI);
				}
			}
			return true;
		}

		public boolean handleResourceDeleted(final Resource resource) {
			synchronized (BonitaResourceSetInfoDelegate.this) {
				for (WorkspaceSynchronizer.Delegate delegate : delegates) {
					delegate.handleResourceDeleted(resource);
				}
			}
			return true;
		}

		public void dispose() {
			//Nothing to do
		}
	}

	/**
	 * 
	 * @return
	 * 	true if some resource of the resource set is modified
	 */
	public boolean resourceSetIsDirty() {
		for (Resource resource : getEditingDomain().getResourceSet().getResources()) {
			if (resource.isLoaded() && !getEditingDomain().isReadOnly(resource) && resource.isModified()) {
				return true;
			}
		}
		return false;
	}

	public static BonitaResourceSetInfoDelegate adapt(TransactionalEditingDomain editingDomain) {
		BonitaResourceSetInfoAdapter.ResourceSetFactory factory = new BonitaResourceSetInfoAdapter.ResourceSetFactory();
		BonitaResourceSetInfoAdapter adapter = (BonitaResourceSetInfoAdapter) factory
				.adapt(editingDomain.getResourceSet(), BonitaResourceSetInfoDelegate.class);
		return (adapter != null) ? adapter.getSharedResourceSetInfoDelegate() : null;
	}
}
