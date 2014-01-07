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
//package org.bonitasoft.studio.properties.sections.loop;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.eclipse.jface.viewers.ITreeContentProvider;
//import org.eclipse.jface.viewers.Viewer;
//
///**
// * @author Mickael Istria
// *
// */
//public class JoinContentProvider implements ITreeContentProvider {
//
//    public Object[] getElements(Object inputElement) {
//        List<Object> res = new ArrayList<Object>();
//
//        ConnectorAPI connectorAPI = ConnectorRepository.getInstance().getConnectorAPI();
//        if (connectorAPI != null) {
//            for (Category category : connectorAPI.getJoinCheckerCategories()) {
//                res.add(category);
//            }
//            return res.toArray();
//        } else {
//            return new Object[0];
//        }
//    }
//
//    public boolean hasChildren(Object element) {
//        Object [] children = getChildren(element);
//        return children != null && children.length != 0;
//    }
//
//    public Object getParent(Object element) {
//        return null;
//    }
//
//    public Object[] getChildren(Object parentElement) {
//        if (parentElement instanceof Category) {
//            Category category = (Category)parentElement;
//            return ConnectorRepository.getInstance().getConnectorAPI().getJoinCheckerConnectors(category.getName()).toArray();
//        } else {
//            return null;
//        }
//    }
//
//    public void dispose() {
//    }
//
//    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
//    }
//
//}
