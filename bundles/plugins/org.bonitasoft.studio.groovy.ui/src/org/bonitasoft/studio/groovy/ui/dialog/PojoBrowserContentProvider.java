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
package org.bonitasoft.studio.groovy.ui.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.PlatformUI;

/**
 * @author Baptiste Mesta
 */
public class PojoBrowserContentProvider implements ITreeContentProvider {

    private final Repository repository;

    public PojoBrowserContentProvider() {
        repository = RepositoryManager.getInstance().getCurrentRepository();
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

    @Override
    public Object[] getElements(final Object inputElement) {
        final IJavaProject project = repository.getJavaProject();
        IType type = null;;
        try {
            type = project.findType(inputElement.getClass().getName());
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        }
        final Pair<IMember, Object> pair = new Pair<IMember, Object>(type, inputElement);
        return new Object[] { pair };
    }

    @Override
    public Object[] getChildren(final Object parentElement) {
        try {
            if (parentElement instanceof Pair<?, ?>) {
                final Pair<?, ?> pair = (Pair<?, ?>) parentElement;
                if (pair.getFirst() instanceof IMethod) {
                    final IMethod method = (IMethod) pair.getFirst();
                    if (method.getNumberOfParameters() == 0) {
                        final Object result = ReflectionUtil.evaluate(method, pair.getSecond());
                        return toArrayResult(result);
                    }
                    return null;
                } else if (pair.getFirst() instanceof IField) {
                    final IField field = (IField) pair.getFirst();
                    final Object fieldContent = ReflectionUtil.evaluate(field, pair.getSecond());
                    return toArrayResult(fieldContent);
                } else if (pair.getFirst() instanceof IType) {
                    final Object value = pair.getSecond();
                    final IType type = (IType) pair.getFirst();
                    final List<Pair<IMember, ?>> res = new ArrayList<Pair<IMember, ?>>();

                    PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

                        @Override
                        public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            try {
                                for (final IField field : type.getFields()) {
                                    res.add(new Pair<IMember, Object>(field, value));
                                }
                                for (final IMethod method : type.getMethods()) {
                                    if (!method.isConstructor() && method.getParameterNames().length == 0 && Flags.isPublic(method.getFlags())) {
                                        res.add(new Pair<IMember, Object>(method, value));
                                    }
                                }
                            } catch (final CoreException e) {
                                BonitaStudioLog.error(e);
                            }
                        }
                    });

                    Collections.sort(res, new Comparator<Pair<IMember, ?>>() {

                        @Override
                        public int compare(final Pair<IMember, ?> arg0, final Pair<IMember, ?> arg1) {
                            if (arg0.getFirst().getElementType() == arg1.getFirst().getElementType()) {
                                return arg0.getFirst().getElementName().compareTo(arg1.getFirst().getElementName());
                            } else if (arg0.getFirst().getElementType() == IJavaElement.FIELD) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }

                    });
                    return res.toArray();
                }
            }

        } catch (final Exception ex) {
            BonitaStudioLog.error(ex);
        }
        return null;
    }

    /**
     * @param result
     * @return
     */
    private Object[] toArrayResult(final Object result) {
        if (result == null) {
            return null;
        } else if (result instanceof Collection<?>) {
            final List<Object> results = new ArrayList<Object>();
            for (final Iterator<?> iterator = ((Collection<?>) result).iterator(); iterator.hasNext();) {
                results.add(getPair(iterator.next()));
            }
            return results.toArray();
        } else if (result instanceof Object[]) {
            final List<Object> results = new ArrayList<Object>();
            final Object[] objects = (Object[]) result;
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] != null) {
                    results.add(getPair(objects[i]));
                }
            }
            return results.toArray();
        } else {
            return new Object[] { getPair(result) };
        }
    }

    private Object getPair(final Object result) {
        if (result instanceof Integer || result instanceof Long || result instanceof String) {//TODO check others
            return result;
        } else {
            final IJavaProject project = repository.getJavaProject();
            IType type = null;
            try {
                type = project.findType(result.getClass().getName());
            } catch (final JavaModelException e) {
                BonitaStudioLog.error(e);
            }
            return new Pair<IMember, Object>(type, result);
        }
    }

    @Override
    public Object getParent(final Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(final Object element) {
        return true;
    }

}
