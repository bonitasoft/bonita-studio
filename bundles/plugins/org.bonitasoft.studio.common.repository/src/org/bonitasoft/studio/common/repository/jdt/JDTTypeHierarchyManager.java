/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.jdt;

import java.util.WeakHashMap;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;


public class JDTTypeHierarchyManager {

    private static final WeakHashMap<IType, ITypeHierarchy> typeHierarchies = new WeakHashMap<IType, ITypeHierarchy>();
    private static IResourceChangeListener listener = null;

    public JDTTypeHierarchyManager() {
        if (listener == null) {
            registerModificationListener();
        }
    }

    /**
     * It will clear the cache each time a resource change in the workspace with a POST_BUILD event.
     * A more intelligent way to do it will be do listen exactly the changes which implies a modification of the AST
     * but it is a safer implementation which will already provide a great performance improvement.
     */
    protected void registerModificationListener() {
        listener = new IResourceChangeListener() {

            @Override
            public void resourceChanged(final IResourceChangeEvent event) {
                clearCache();
            }
        };
        ResourcesPlugin.getWorkspace().addResourceChangeListener(listener, IResourceChangeEvent.POST_BUILD);
    }

    public ITypeHierarchy getTypeHierarchy(final IType type) throws JavaModelException {
        ITypeHierarchy res = typeHierarchies.get(type);
        if (res == null) {
            res = createTypeHierarchy(type);
        }
        return res;
    }

    protected ITypeHierarchy createTypeHierarchy(final IType type) throws JavaModelException {
        final ITypeHierarchy res = type.newTypeHierarchy(null);
        typeHierarchies.put(type, res);
        return res;
    }

    public void clearCache() {
        typeHierarchies.clear();
    }

}
