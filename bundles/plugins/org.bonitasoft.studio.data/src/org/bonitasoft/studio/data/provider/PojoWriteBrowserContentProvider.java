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
package org.bonitasoft.studio.data.provider;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 * 
 */
public class PojoWriteBrowserContentProvider implements ITreeContentProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface
	 * .viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.
	 * Object)
	 */
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof String ){
			IType type = null ;
			try {
				type = RepositoryManager.getInstance().getCurrentRepository().getJavaProject().findType(inputElement.toString());
			} catch (JavaModelException e1) {
				BonitaStudioLog.error(e1) ;
			}
			return new Object[]{type};
		}else if(inputElement instanceof IType){
			return getChildren(inputElement) ;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.
	 * Object)
	 */
	public Object[] getChildren(Object parentElement) {
		try {
			if (parentElement instanceof IMethod) {
				String typeName = ((IMethod) parentElement).getReturnType();
				IType type = null ;
				try {
					type = RepositoryManager.getInstance().getCurrentRepository().getJavaProject().findType(Signature.toString(typeName));
				} catch (JavaModelException e1) {
					BonitaStudioLog.error(e1) ;
				}
				return getChildren(type);
			} else if (parentElement instanceof IType) {
				IType type = (IType) parentElement;
				return computeChildren(type);
			} else {
				return new Object[] {};
			}
		} catch (Exception ex) {
			BonitaStudioLog.error(ex);
			return new Object[] {};
		}
	}

	/**
	 * @param type
	 * @return
	 */
	protected Object[] computeChildren(final IType type) throws Exception {
		final List<IMember> res = new ArrayList<IMember>();
		PlatformUI.getWorkbench().getProgressService().busyCursorWhile(new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				
				try {
					for (IMethod method : type.getMethods()) {
						if (Flags.isPublic(method.getFlags()) &&
								method.getParameterNames().length <= 1) {
							res.add(method);
						}
					}
				} catch (CoreException e) {
					BonitaStudioLog.error(e);
				}

			}
		});
		for (IMethod method : type.getMethods()) {
			if ((method.getParameterTypes().length == 0 || method.getParameterTypes().length == 1) && Flags.isPublic(method.getFlags())
					&& !method.isConstructor() && !res.contains(method)) {
				res.add(method);
			}
		}

		Collections.sort(res, new Comparator<IMember>() {

			public int compare(IMember arg0, IMember arg1) {
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
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object
	 * )
	 */
	public Object getParent(Object element) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.
	 * Object)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof IMethod) {
			IMethod method = (IMethod) element;
			try {
				return method.getParameterNames().length == 0;
			} catch (Exception ex) {
				BonitaStudioLog.error(ex);
				return true;
			}
		}
		return true;
	}

}
