/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.data.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.core.SourceType;
import org.eclipse.jdt.internal.corext.codemanipulation.GetterSetterUtil;
import org.eclipse.jdt.internal.corext.util.JdtFlags;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 */
public class JavaSetterContentProvider implements ITreeContentProvider {

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    @Override
    public void dispose() {
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface
     * .viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.
     * Object)
     */
    @Override
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof String) {
            IType type = null;
            try {
                type = RepositoryManager.getInstance().getCurrentRepository().getJavaProject()
                        .findType(inputElement.toString());
            } catch (final JavaModelException e1) {
                BonitaStudioLog.error(e1);
            }
            return new Object[] { type };
        } else if (inputElement instanceof IType) {
            return getChildren(inputElement);
        }
        return new Object[0];
    }

    @Override
    public Object[] getChildren(final Object parentElement) {
        try {
            final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
            if (parentElement instanceof IMethod) {
                final String typeName = ((IMethod) parentElement).getReturnType();
                IType type = null;
                try {
                    type = javaProject.findType(Signature.toString(typeName));
                } catch (final JavaModelException e1) {
                    BonitaStudioLog.error(e1);
                }
                return getChildren(type);
            } else if (parentElement instanceof IType) {
                final IType type = (IType) parentElement;
                return computeChildren(type, javaProject);
            } else {
                return new Object[0];
            }
        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
            return new Object[] {};
        }
    }

    protected Object[] computeChildren(final IType type, final IJavaProject javaProject) throws Exception {
        final List<IMethod> res = new ArrayList<IMethod>();
        res.addAll(JDTMethodHelper.allPublicMethodWithOneParameter(type));
        Collections.sort(res, new Comparator<IMember>() {

            @Override
            public int compare(final IMember arg0, final IMember arg1) {
                if (arg0.getElementType() == arg1.getElementType()) {
                    return arg0.getElementName().compareTo(arg1.getElementName());
                } else if (arg0.getElementType() == IJavaElement.FIELD) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        return res.toArray();
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object
     * )
     */
    @Override
    public Object getParent(final Object element) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.
     * Object)
     */
    @Override
    public boolean hasChildren(final Object element) {
        if (element instanceof IMethod) {
            final IMethod method = (IMethod) element;
            try {
                return method.getParameterNames().length == 0;
            } catch (final Exception ex) {
                BonitaStudioLog.error(ex);
                return true;
            }
        }
        return true;
    }

}
