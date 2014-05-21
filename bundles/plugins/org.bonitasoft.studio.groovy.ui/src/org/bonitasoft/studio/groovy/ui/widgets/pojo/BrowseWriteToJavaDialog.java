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
package org.bonitasoft.studio.groovy.ui.widgets.pojo;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Mickael Istria
 *
 */
public class BrowseWriteToJavaDialog extends Dialog {

	private IType type;
	private String className;
	private TreeViewer viewer;
	private String dataName;
	protected ITreeSelection selection;
	private String initialValue;
	//private PojoWriteBrowserContentProvider provider;

	
	/**
	 * @param shell
	 * @param name
	 * @param className2
	 * @param additionalPath
	 */
	public BrowseWriteToJavaDialog(Shell shell, String name, String className, String additionalPath) {
		super(shell);
		setShellStyle(SWT.RESIZE);
		this.dataName = name;
		if (name.endsWith("...")) {
			this.dataName = dataName.substring(0, name.length() - "...".length());
		}
		if (GroovyUtil.isGroovyExpression(dataName)) {
			this.dataName = GroovyUtil.toSimpleExpression(dataName);
		}
		this.className = className;
		this.initialValue = additionalPath;
	}

	@Override
	public Control createDialogArea(Composite parent) {
		Composite res = (Composite) super.createDialogArea(parent);
		
		if (type == null) {
			try {
				type = RepositoryManager.getInstance().getCurrentRepository().getJavaProject().findType(className);
			} catch (JavaModelException e1) {
				BonitaStudioLog.error(e1) ;
			}
		}
		
		Label label = new Label(res, SWT.NONE);
		label.setText(Messages.browseJava);
		viewer = new TreeViewer(res, SWT.SINGLE | SWT.BORDER);
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
		//provider = new PojoWriteBrowserContentProvider();
	//	viewer.setContentProvider(provider);
		viewer.setLabelProvider(new JavaUILabelProvider() {
			@Override
			public String getText(Object item) {
				if (item instanceof String) {
					return (String)item;
				} else {
					return super.getText(item);
				}
			}
		});
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				selection = (ITreeSelection) event.getSelection();
				if (getButton(OK) != null) {
					getButton(OK).setEnabled(isSetterOrDataSelected());
				}
			}

		});
		viewer.setInput(new Object());
	//	viewer.setSelection(new StructuredSelection(provider.getElements(new Object()))) ;
		return res;
	}


	/**
	 * @return
	 */
	public String generateJavaAdditionalPath() {
		if(selection == null){
			return "";
		}
		TreePath path = selection.getPaths()[0];
		if (path.getSegmentCount() == 1) {
			return "";
		}
		StringBuilder res = new StringBuilder(dataName);
		res.append(".");
		for (int i = 1; i < path.getSegmentCount() - 1; i++) {
			Object item = path.getSegment(i);
			final IJavaElement iJavaElement = (IJavaElement)item;
			res.append(iJavaElement.getElementName());
			if (iJavaElement.getElementType() == IJavaElement.METHOD) {
				res.append("()");
			}
			res.append(".");
		}
		if (res.length() > 0) {
			res.deleteCharAt(res.length() - 1);
		}
		res.append(BonitaConstants.JAVA_VAR_SEPARATOR);
		Object item = path.getSegment(path.getSegmentCount() - 1);
		res.append(((IJavaElement)item).getElementName());
		return res.toString();
	}

	private boolean isSetterOrDataSelected() {
		if (selection.getFirstElement() instanceof IMethod) {
			IMethod method = (IMethod)selection.getFirstElement();
			try {
				return method.getParameterNames().length == 1;
			} catch (Exception ex) {
				BonitaStudioLog.error(ex);
				return false;
			}
		} else if (selection.getFirstElement() instanceof String) {
			return true;
		} else {
			return false;
		}
	}
}
