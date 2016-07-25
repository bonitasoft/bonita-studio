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
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.model.process.ResourceContainer;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * 
 * 
 * @author Baptiste Mesta
 * 
 */
public class ResourceTreeContentProvider implements ITreeContentProvider {

    public static final Object RESOURCES_CATEGORY = Messages.Application_Resources;
    public static final Object[] CATEGORYS = { RESOURCES_CATEGORY };
    private ResourceContainer container;


    /**
     * Gets the children of the specified object
     * 
     * @param parentElement
     *            the parent object
     * @return Object[]
     */
    public Object[] getChildren(Object parentElement) {
        if (parentElement.equals(RESOURCES_CATEGORY)) {
            return toResourceArray(container);
        } else if (parentElement instanceof ResourceFolder) {
            //parentElement.getre
            return getChildren(getFile((ResourceFolder)parentElement));
        } else if (parentElement instanceof File) {
            // Return the files and subdirectories in this directory except the .svn folder and files
            if (((File) parentElement).isDirectory()) {
                File[] listFilesIgnoringSvn = ((File) parentElement).listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return ! (dir.getName().endsWith(".svn") || name.equals(".svn"));
                    }
                });
                List<File> files = new ArrayList<File>();
                for (File file : listFilesIgnoringSvn) {
                    files.add(file);
                }
                Collections.sort(files);
                return files.toArray();
            }
        } else if(parentElement instanceof IContainer){
            try {
                return ((IContainer) parentElement).members();
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return new Object[0];

    }

    /**
     * @param parentElement
     * @return
     */
    private File getFile(ResourceFolder resourceFolder) {
        String path = resourceFolder.getPath();
        File file = new File(path);
        if (file.exists()) {
            return file;
        } else {
            return WebTemplatesUtil.getFile(resourceFolder.getPath());
        }
    }

    /**
     * 
     * List resource that are present in the template
     * @param resourceFiles
     * @param resourceFolders
     * @return
     */
    private Object[] toResourceArray(ResourceContainer container) {
        List<Object> res = new ArrayList<Object>();
        for (ResourceFolder folder : container.getResourceFolders()) {
            res.add(folder);
        }
        for (ResourceFile file : container.getResourceFiles()) {
            res.add(file);
        }
        return res.toArray();
    }

    /**
     * Gets the parent of the specified object
     * 
     * @param parentElement
     *            the object
     * @return Object
     */
    public Object getParent(Object element) {
        if (element instanceof File) {
            // Return this file's parent file
            return ((File) element).getParentFile();
        } else if (element instanceof IResource) {
            return ((IResource) element).getParent();
        }
        return null;
    }

    /**
     * Returns whether the passed object has children
     * 
     * @param arg0
     *            the parent object
     * @return boolean
     */
    public boolean hasChildren(Object arg0) {
        if (arg0.equals(RESOURCES_CATEGORY)) {
            return container.getResourceFiles().size() + container.getResourceFolders().size() > 0;
        } else {
            // Get the children
            Object[] obj = getChildren(arg0);

            // Return whether the parent has children
            return obj == null ? false : obj.length > 0;
        }
    }

    /**
     * Gets the root element(s) of the tree
     * 
     * @param arg0
     *            the input data
     * @return Object[]
     */
    public Object[] getElements(Object arg0) {
        if (arg0.equals(container)) {
            return CATEGORYS;
        } else {
            return File.listRoots();
        }
    }

    /**
     * Disposes any created resources
     */
    public void dispose() {
        // Nothing to dispose
    }

    /**
     * Called when the input changes
     * 
     * @param arg0
     *            the viewer
     * @param arg1
     *            the old input
     * @param arg2
     *            the new input
     */
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
        container = (ResourceContainer)arg2;
    }

    /**
     * @return
     */
    public ResourceContainer getResourceContainer() {
        return container;
    }

}
