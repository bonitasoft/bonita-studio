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
package org.bonitasoft.studio.connectors.wizards.java;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.extension.ExtensionProjectUtil;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Mickael Istria
 *
 */
public class SelectMethodDialog extends Dialog {

	private String className;
	protected IMethod method;

	/**
	 * @param parentShell
	 */
	public SelectMethodDialog(Shell parentShell, String className) {
		super(parentShell);
		this.className = className;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Control createContents(Composite parent) {
		FilteredTree tree = new FilteredTree(parent, SWT.SINGLE, new PatternFilter(), true);
		tree.getViewer().setContentProvider(new ITreeContentProvider() {
			
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
			
			public void dispose() {
			}
			
			public Object[] getElements(Object inputElement) {
				try {
					IType type = ExtensionProjectUtil.getJavaProject().findType(className);
					if(type != null){
						return type.getMethods();
					} else {
						return new Object[0];
					}
				} catch (JavaModelException ex) {
					BonitaStudioLog.log(ex);
					return new Object[0];
				}
			}
			
			public boolean hasChildren(Object element) {
				return false;
			}
			
			public Object getParent(Object element) {
				return null;
			}
			
			public Object[] getChildren(Object parentElement) {
				return null;
			}
		});
		tree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			
			public void selectionChanged(SelectionChangedEvent event) {
				method = (IMethod) ((IStructuredSelection)event.getSelection()).getFirstElement();
			}
		});
		tree.getViewer().setInput(new Object());
		tree.getViewer().setLabelProvider(new JavaUILabelProvider());
		tree.getViewer().getControl().setLayoutData(new GridData(400, 500));
		tree.getViewer().addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				method = (IMethod) selection.getFirstElement();
				okPressed();
			}
		});
		return tree;
	}

	/**
	 * @return
	 */
	public IMethod getMethod() {
		return this.method;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Java");
	}
	
}
