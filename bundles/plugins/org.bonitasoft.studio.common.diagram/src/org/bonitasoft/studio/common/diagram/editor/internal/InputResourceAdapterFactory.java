/**
 * Copyright (C) 2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.diagram.editor.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;

public class InputResourceAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        /*
         * adaptableObject instanceof FileEditorInput test should not be necessary.
         * FileEditorInput can directly adapt to IResource.
         */
        if (adapterType.isAssignableFrom(IFile.class)) {
            if (adaptableObject instanceof URIEditorInput) {
                final URI trimmedUri = ((URIEditorInput) adaptableObject).getURI().trimFragment();
                if (trimmedUri.isPlatformResource()) {
                    final String path = trimmedUri.toPlatformString(true);
                    return (T) ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
                } else {
                    final String path = trimmedUri.toFileString();
                    return (T) ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(path));
                }
            }
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class<?>[] { IResource.class, IFile.class };
    }

}
