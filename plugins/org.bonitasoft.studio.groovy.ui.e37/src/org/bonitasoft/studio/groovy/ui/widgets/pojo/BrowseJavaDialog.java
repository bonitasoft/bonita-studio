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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.dialog.BonitaGroovyEditorDialog;
import org.bonitasoft.studio.groovy.ui.widgets.GroovyComboProvider;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Mickael Istria
 *
 */
public class BrowseJavaDialog extends Dialog {

	private String value;
	private TreeViewer viewer;
	protected ISelection selection;
	private TextOrData textOrData;
	private String script;
	private BonitaGroovyEditorDialog groovyEditorDialog;
	private String dataName;
	private IType type;
	private String className;

	/**
	 * @param shell
	 * @param string
	 * @param javaData
	 */
	public BrowseJavaDialog(Shell shell, String initialValue, JavaObjectData javaData, final TextOrData textOrData) {
		this(shell, initialValue, javaData.getName(), javaData.getClassName(), textOrData);
	}
	
	/**
	 * @param shell
	 * @param isMultiple 
	 * @param string
	 * @param javaData
	 */
	public BrowseJavaDialog(Shell shell, String initialValue, String dataName, String className, final TextOrData textOrData) {
		super(shell);
		setShellStyle(SWT.RESIZE);
		this.value = initialValue;
		this.dataName = dataName;
		this.className = className;
		this.textOrData = textOrData ;
	}

	/**
	 * @param shell
	 * @param initialValue
	 * @param jdtType
	 * @param createEditExpressionDialog
	 */
	public BrowseJavaDialog(Shell shell, String variableName, IType jdtType, String initialValue, BonitaGroovyEditorDialog createEditExpressionDialog) {
		super(shell);
		setShellStyle(SWT.RESIZE);
		this.value = initialValue;
		this.dataName = variableName;
		this.type = jdtType;
		this.groovyEditorDialog = createEditExpressionDialog;
	}

	@Override
	public Control createDialogArea(Composite parent) {
		if (type == null) {
			try {
				type = RepositoryManager.getInstance().getCurrentRepository().getJavaProject().findType(className);
			} catch (JavaModelException e1) {
				BonitaStudioLog.error(e1) ;
			}
		}
		
		Composite res = (Composite) super.createDialogArea(parent);
		Label label = new Label(res, SWT.NONE);
		label.setText(Messages.browseJava);
		viewer = new TreeViewer(res, SWT.SINGLE | SWT.BORDER);
		viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		//viewer.setContentProvider(new PojoBrowserContentProvider());
		viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 300).create());
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
				selection = event.getSelection();
			}
		});
		viewer.setInput(new Object());
//		viewer.setExpandedState(dataName, true);
		Link link = new Link(res, SWT.NONE);
		link.setText("<A>" + Messages.openExpressionEditor + "</A>");
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				okPressed();
				if (textOrData != null) {
					script = new GroovyComboProvider().openGroovyEditor(textOrData, getScript());
				} else if (groovyEditorDialog != null) {
					if (groovyEditorDialog.open() == Dialog.OK) {
						script = groovyEditorDialog.getExpression();
					}
				}
			}
		});
		return res;
	}

	/**
	 * @return
	 */
	public String getScript() {
		if (script != null) {
			return script;
		}
		if(selection == null){
			return  GroovyUtil.GROOVY_PREFIX + dataName + GroovyUtil.GROOVY_SUFFIX;
		}
		StringBuilder builder = new StringBuilder();
		TreePath path = ((ITreeSelection)selection).getPaths()[0];
		for (int i = 0; i < path.getSegmentCount(); i++) {
			Object item = path.getSegment(i);
			if (item instanceof String) {
				builder.append((String)item);
			} else if (item instanceof IMethod) {
				builder.append(".");
				builder.append(((IMethod)item).getElementName());
				builder.append("()");
			} else if (item instanceof IField) {
				builder.append(".");
				builder.append(((IField)item).getElementName());
			}
		}
		return  GroovyUtil.GROOVY_PREFIX + builder.toString() + GroovyUtil.GROOVY_SUFFIX;
	}

}
