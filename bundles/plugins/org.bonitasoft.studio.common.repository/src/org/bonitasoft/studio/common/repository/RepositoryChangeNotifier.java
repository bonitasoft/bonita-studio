///**
// * Copyright (C) 2010 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.common.repository;
//
//import org.bonitasoft.studio.common.repository.model.IRepositoryChangeNotifier;
//import org.eclipse.core.resources.IResource;
//import org.eclipse.core.runtime.ListenerList;
//
///**
// * @author Baptiste Mesta
// * 
// */
//public class RepositoryChangeNotifier implements IRepositoryChangeNotifier {
//
//	private ListenerList fListenerList;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.bonitasoft.studio.repository.IRepositoryChangeNotifier#
//	 * addRepositoryChangeListener
//	 * (org.bonitasoft.studio.repository.IRepositoryChangeListener)
//	 */
//	public void addRepositoryChangeListener(
//			IRepositoryChangeListener repositoryChangeListener) {
//		if (fListenerList == null)
//			fListenerList = new ListenerList();
//		fListenerList.add(repositoryChangeListener);
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.bonitasoft.studio.repository.IRepositoryChangeNotifier#
//	 * removeAllRepositoryChangeListener()
//	 */
//	public void removeAllRepositoryChangeListener() {
//		fListenerList = null;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @seeorg.bonitasoft.studio.repository.IRepositoryChangeNotifier#
//	 * removeRepositoryChangeListener
//	 * (org.bonitasoft.studio.repository.IRepositoryChangeListener)
//	 */
//	public void removeRepositoryChangeListener(
//			IRepositoryChangeListener repositoryChangeListener) {
//		if (fListenerList != null) {
//			fListenerList.remove(repositoryChangeListener);
//			if (fListenerList.isEmpty())
//				fListenerList = null;
//		}
//	}
//
//
//	/**
//	 * Notifies all registered <code>IRepositoryChangeListener</code>s of a content
//	 * change.
//	 */
//	public void fireRepositoryChanged(final AbstractRepository<?extends AbstractRepositoryArtifact> repository) {
//		if (isEmpty()) {
//			return;
//		}
//
//		if(fListenerList != null){
//			Object[] listeners = fListenerList.getListeners();
//			for (int i = 0; i < listeners.length; i++) {
//				final IRepositoryChangeListener listener = (IRepositoryChangeListener) listeners[i];
//				listener.repositoryChanged(repository);
//			}
//		}
//	}
//
//	/**
//	 * Notifies all registered <code>IRepositoryChangeListener</code>s of a content
//	 * change.
//	 */
//	public void fireArtifactChanged(final AbstractRepositoryArtifact artifact) {
//		if (isEmpty()) {
//			return;
//		}
//		if(fListenerList != null){
//			Object[] listeners = fListenerList.getListeners();
//			for (int i = 0; i < listeners.length; i++) {
//				final IRepositoryChangeListener listener = (IRepositoryChangeListener) listeners[i];
//				listener.artifactChanged(artifact);
//			}
//		}
//	}
//
//	public void fireArtifactAdded(final AbstractRepositoryArtifact artifact) {
//		if (isEmpty()) {
//			return;
//		}
//		if(fListenerList != null){
//			Object[] listeners = fListenerList.getListeners();
//			for (int i = 0; i < listeners.length; i++) {
//				final IRepositoryChangeListener listener = (IRepositoryChangeListener) listeners[i];
//				listener.artifactAdded(artifact);
//			}
//		}
//	}
//
//	public void fireArtifactAboutToBeRemoved(final AbstractRepositoryArtifact artifact) {
//		if (isEmpty()) {
//			return;
//		}
//		// Legacy listeners may expect to be notified in the UI thread.
//		if(fListenerList != null){
//			Object[] listeners = fListenerList.getListeners();
//			for (int i = 0; i < listeners.length; i++) {
//				final IRepositoryChangeListener listener = (IRepositoryChangeListener) listeners[i];
//				listener.artifactAboutToBeRemoved(artifact);
//			}
//		}
//	}
//
//	public void fireArtifactRemoved(final AbstractRepositoryArtifact artifact) {
//		if (isEmpty()) {
//			return;
//		}
//		// Legacy listeners may expect to be notified in the UI thread.
//		if(fListenerList != null){
//			Object[] listeners = fListenerList.getListeners();
//			for (int i = 0; i < listeners.length; i++) {
//				final IRepositoryChangeListener listener = (IRepositoryChangeListener) listeners[i];
//				listener.artifactRemoved(artifact);
//			}
//		}
//	}
//
//	public void fireArtifactAboutToOpen(final AbstractRepositoryArtifact artifact) {
//		if (isEmpty()) {
//			return;
//		}
//		// Legacy listeners may expect to be notified in the UI thread.
//		if(fListenerList != null){
//			Object[] listeners = fListenerList.getListeners();
//			for (int i = 0; i < listeners.length; i++) {
//				final IRepositoryChangeListener listener = (IRepositoryChangeListener) listeners[i];
//				listener.artifactAboutToOpen(artifact);
//			}
//		}
//
//	}
//
//
//	public void fireArtifactOpened(final AbstractRepositoryArtifact artifact) {
//		if (isEmpty()) {
//			return;
//		}
//		// Legacy listeners may expect to be notified in the UI thread.
//		if(fListenerList != null){
//			Object[] listeners = fListenerList.getListeners();
//			for (int i = 0; i < listeners.length; i++) {
//				final IRepositoryChangeListener listener = (IRepositoryChangeListener) listeners[i];
//				listener.artifactOpened(artifact);
//			}
//		}
//	}
//
//	/**
//	 * @param abstractRepositoryArtifact
//	 */
//	public void fireArtifactClosed(final AbstractRepositoryArtifact artifact) {
//		if (isEmpty()) {
//			return;
//		}
//		// Legacy listeners may expect to be notified in the UI thread.
//		if(fListenerList != null){
//			Object[] listeners = fListenerList.getListeners();
//			for (int i = 0; i < listeners.length; i++) {
//				final IRepositoryChangeListener listener = (IRepositoryChangeListener) listeners[i];
//				listener.artifactClosed(artifact);
//			}
//		}
//
//	}
//
//
//	/**
//	 * @param abstractRepositoryArtifact
//	 */
//	public void fireResourcesNeedUpdate(final IResource[] resources) {
//		if (isEmpty()) {
//			return;
//		}
//		// Legacy listeners may expect to be notified in the UI thread.
//		if(fListenerList != null){
//			Object[] listeners = fListenerList.getListeners();
//			for (int i = 0; i < listeners.length; i++) {
//				final IRepositoryChangeListener listener = (IRepositoryChangeListener) listeners[i];
//				listener.resourceNeedUpdate(resources);
//			}
//		}
//
//	}
//
//	/**
//	 * Return whether this notifier is empty (i.e. has no listeners).
//	 * 
//	 * @return whether this notifier is empty
//	 */
//	public boolean isEmpty() {
//		return fListenerList == null || fListenerList.isEmpty();
//	}
//}
