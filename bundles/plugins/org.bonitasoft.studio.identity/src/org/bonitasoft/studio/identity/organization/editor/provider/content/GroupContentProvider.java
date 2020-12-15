/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.organization.editor.provider.content;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class GroupContentProvider implements ITreeContentProvider {

    public static final String GROUP_SEPARATOR = "/";
    private IObservableList<Group> groups;

    public GroupContentProvider(IObservableList<Group> groups) {
        this.groups = groups;
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(Viewer viewer, Object arg1, Object arg2) {
    }

    @Override
    public Object[] getChildren(Object element) {
        Group group = (Group) element;
        String groupPath = null;
        if (group.getParentPath() == null) {
            groupPath = GROUP_SEPARATOR + group.getName();
        } else {
            groupPath = group.getParentPath() + GROUP_SEPARATOR + group.getName();
        }
        List<Group> children = new ArrayList<>();
        for (Group g : groups) {
            if (g.getParentPath() != null && g.getParentPath().equals(groupPath)) {
                children.add(g);
            }
        }
        return children.toArray();
    }

    @Override
    public Object[] getElements(Object element) {
        List<Group> rootGroups = new ArrayList<>();
        for (Group g : groups) {
            if (g.getParentPath() == null) {
                rootGroups.add(g);
            }
        }
        return rootGroups.toArray();
    }

    @Override
    public Object getParent(Object element) {
        Group group = (Group) element;
        String parentPath = group.getParentPath();
        if (parentPath == null) {
            return null;
        }

        String[] parents = parentPath.split(GROUP_SEPARATOR);
        String parentName = parents[parents.length - 1];

        StringBuilder builder = new StringBuilder(GROUP_SEPARATOR);
        for (int i = 0; i < parents.length - 1; i++) {
            builder.append(parents[i]);
            builder.append(GROUP_SEPARATOR);
        }
        builder.delete(builder.length() - 1, builder.length());
        String ancestorPath = builder.toString();
        for (Group g : groups) {
            if (g.getName().equals(parentName)) {
                if (g.getParentPath() != null && g.getParentPath().equals(ancestorPath)) {
                    return g;
                } else if (g.getParentPath() == null && ancestorPath.equals(GROUP_SEPARATOR)) {
                    return g;
                }

            }
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        Group group = (Group) element;
        String groupPath = getGroupPath(group);
        for (Group g : groups) {
            if (g.getParentPath() != null && g.getParentPath().equals(groupPath)) {
                return true;
            }
        }
        return false;
    }

    public static String getGroupPath(Group group) {
        return getGroupPath(group.getName(), group.getParentPath());
    }

    public static String getGroupPath(String groupName, String parentPath) {
        String groupPath = null;
        if (parentPath == null) {
            groupPath = GROUP_SEPARATOR + groupName;
        } else {
            groupPath = parentPath + GROUP_SEPARATOR + groupName;
        }
        return groupPath;
    }

}
