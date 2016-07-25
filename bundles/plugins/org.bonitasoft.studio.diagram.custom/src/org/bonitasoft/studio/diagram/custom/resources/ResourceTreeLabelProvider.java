/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.resources;

import java.io.File;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author Baptiste Mesta
 * 
 */
public class ResourceTreeLabelProvider extends LabelProvider implements IBaseLabelProvider {

	// Images for tree nodes
	private Image file;
	private Image dir;
	private AbstractProcess process;
	private ApplicationResourceRepositoryStore resourceStore;

	/**
	 * Constructs a FileTreeLabelProvider
	 */
	public ResourceTreeLabelProvider() {
		super();
		// Create the images

		file = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
		dir = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		resourceStore = (ApplicationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class) ;
	}

	/**
	 * Gets the image to display for a node in the tree
	 * 
	 * @param arg0
	 *            the node
	 * @return Image
	 */
	public Image getImage(Object arg0) {
		if (arg0 instanceof File) {
			// If the node represents a directory, return the directory image.
			// Otherwise, return the file image.
			return ((File) arg0).isDirectory() ? dir : file;
		} else if (arg0 instanceof ResourceFile) {
			return file;
		} else if (arg0 instanceof ResourceFolder) {
			return dir;
		}	else if (arg0 instanceof IContainer) {
			return dir;
		} 	else if (arg0 instanceof IFile) {
			return file;
		} else {
			return super.getImage(arg0);
		}
	}

	/**
	 * Gets the text to display for a node in the tree
	 * 
	 * @param arg0
	 *            the node
	 * @return String
	 */
	public String getText(Object item) {
	
		if (item instanceof String) {
			String[] segments = ((String) item).split("/");
			return segments[segments.length - 1];
		}  else if (item instanceof File) {
			return ((File) item).getName();
		} else if (item instanceof IResource) {
			return ((IResource) item).getName();
		} else if (item instanceof ResourceFile) {
			process = ModelHelper.getParentProcess((EObject) item);
			String processUUID = ModelHelper.getEObjectID(process) ;
			String path = ((ResourceFile) item).getPath();
			ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID);
			if (artifact != null) {
				String relative = artifact.getResourceProjectRelativePath();

				if (path.startsWith(relative)) {
					return path.substring(relative.length() + 1);
				}
			} 
			return path;
		} else if (item instanceof ResourceFolder) {
			process = ModelHelper.getParentProcess((EObject) item);
			String processUUID = ModelHelper.getEObjectID(process) ;
			String path = ((ResourceFolder) item).getPath();
			File tmpFile = new File(path);
			if (!tmpFile.isAbsolute()) {
				ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID) ;
				if (artifact != null) {
					String relative = artifact.getResourceProjectRelativePath();

					if (path.startsWith(relative)) {
						return path.substring(relative.length() + 1);
					}
				}
			}
			return path;
		} else {
			return super.getText(item);
		}
	}

}
