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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.connectors.TestConnector;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.extension.ExtensionProjectUtil;
import org.bonitasoft.studio.connectors.wizards.extensions.CustomExtensionWizardPage;
import org.bonitasoft.studio.groovy.ui.providers.ITextOrDataFactory;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.internal.core.search.JavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 *
 */
public class JavaConnectorWizardPage extends CustomExtensionWizardPage implements IWizardPage {

	public static final String JAVA_CONNECTOR_METHODS = "_javaConnector_methods_";

	private IJavaProject javaProject;
	/**
	 * 
	 */
	private static final String METHODS_SETTER = "setMethods";
	/**
	 * 
	 */
	private static final String CLASS_NAME_SETTER = "setClassName";
	private Map<String, Object> parameters;
	protected Class<?> currentClass;
	private List<MethodEntry> methodEntries;
	private TreeViewer methodsTree;

	/**
	 * @param pageName
	 */
	public JavaConnectorWizardPage() {
		super("java connector");
		setTitle(Messages.javaConnectorTitle);
		setDescription(Messages.javaConnectorDesc);
		javaProject = ExtensionProjectUtil.getJavaProject();
		methodEntries = new ArrayList<MethodEntry>();
		setImageDescriptor(Pics.getWizban());
	}

	/**
	 * @param modelConnector
	 */
	private void initFromParams() {
		Object callsItem = parameters.get(JAVA_CONNECTOR_METHODS);
		if (callsItem != null) {
			List<MethodEntry> calls = (List<MethodEntry>)callsItem;
			for (MethodEntry call : calls) {
				addMethod(call);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		parameters = setConnectorFieldsWizard.getInputParameters();
		initFromParams();

		Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayout(new GridLayout(3, false));

		Label classLabel = new Label(mainComposite, SWT.NONE);
		classLabel.setText(Messages.classLabel);
		final Text classText = new Text(mainComposite, SWT.BORDER);
		classText.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		if (parameters.get(CLASS_NAME_SETTER) != null) {
			classText.setText((String)parameters.get(CLASS_NAME_SETTER));
		}
		classText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				parameters.put(CLASS_NAME_SETTER, classText.getText());
				try {
					currentClass = Class.forName(classText.getText());
				} catch (ClassNotFoundException ex) {
					currentClass = null;
				}
			}
		});
		classText.setEditable(false);

		Button browseClassesButton = new Button(mainComposite, SWT.FLAT);
		browseClassesButton.setText(Messages.browseClasses);
		browseClassesButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				openClassSelectionDialog(classText);

			}
		});

		Label methodsLAbel = new Label(mainComposite, SWT.NONE);
		methodsLAbel.setText(Messages.methodsLabel);
		methodsLAbel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 3, 1));

		methodsTree = new TreeViewer(mainComposite, SWT.NO);
		methodsTree.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		methodsTree.getTree().setBackground(mainComposite.getBackground());
		TreeViewerColumn nameColumn = new TreeViewerColumn(methodsTree, SWT.NONE);
		nameColumn.getColumn().setWidth(200);
		nameColumn.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				if (cell.getElement() instanceof MethodEntry) {
					cell.setText(((MethodEntry)cell.getElement()).methodName);
				} else if (cell.getElement() instanceof ArgDescription) {
					ArgDescription arg = (ArgDescription)cell.getElement();
					cell.setText(arg.name + " (" + arg.type + ")");
				}
			}
		});
		TreeViewerColumn valueColumn = new TreeViewerColumn(methodsTree, SWT.NONE);
		valueColumn.getColumn().setWidth(200);
		valueColumn.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				if (cell.getElement() instanceof ArgDescription) {
					cell.setBackground(org.eclipse.draw2d.ColorConstants.lightGray);
					ArgDescription arg = (ArgDescription)cell.getElement();
					if (arg.value != null) {
						cell.setText(arg.value.toString());
					} else {
						cell.setText("");
					}
				}
			}
		});
		valueColumn.setEditingSupport(new ParameterValueEditingSupport(this, valueColumn.getViewer()));
		methodsTree.setContentProvider(new ITreeContentProvider() {

			public Object[] getElements(Object inputElement) {
				return ((List<MethodEntry>)inputElement).toArray();
			}

			public boolean hasChildren(Object element) {
				return getChildren(element).length > 0;
			}

			public Object getParent(Object element) {
				// TODO Auto-generated method stub
				return null;
			}

			public Object[] getChildren(Object parentElement) {
				if (parentElement instanceof MethodEntry) {
					return ((MethodEntry)parentElement).args.toArray();
				}
				return new Object[0];
			}

			public void dispose() {
				// TODO Auto-generated method stub

			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				// TODO Auto-generated method stub

			}
		});


		methodsTree.addPostSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				getContainer().updateButtons(); 
			}
		});

		methodsTree.getTree().addKeyListener(new KeyListener() {

			public void keyReleased(KeyEvent e) {
				if(e.keyCode == SWT.CR || e.keyCode == SWT.ESC){
					getContainer().updateButtons() ;
				}

			}

			public void keyPressed(KeyEvent e) {


			}
		});

		methodsTree.setInput(methodEntries);

		Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
		buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		buttonComposite.setLayout(new GridLayout(1, false));
		GridData buttonLayoutData = new GridData(SWT.FILL, SWT.DEFAULT, true, false);
		Button addMethodButton = new Button(buttonComposite, SWT.FLAT);
		addMethodButton.setLayoutData(buttonLayoutData);
		addMethodButton.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD));
		addMethodButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				openSelectMethodDialog();
			}
		});

		final Button removeButton = new Button(buttonComposite, SWT.FLAT);
		removeButton.setLayoutData(buttonLayoutData);
		removeButton.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_DELETE));
		removeButton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				removeMethod(((IStructuredSelection)methodsTree.getSelection()).getFirstElement());
			}
		});

		methodsTree.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				if (((IStructuredSelection)event.getSelection()).getFirstElement() instanceof MethodEntry) {
					removeButton.setEnabled(true);
				} else {
					removeButton.setEnabled(false);
				}
			}
		});


		setControl(mainComposite);
	}

	@Override
	public boolean isPageComplete() {
		return methodsTree != null && !methodsTree.isCellEditorActive() ;
	}

	/**
	 * 
	 */
	protected void openSelectMethodDialog() {
		SelectMethodDialog selectMethodDialog = new SelectMethodDialog(getShell(), (String) parameters.get(CLASS_NAME_SETTER));
		if (selectMethodDialog.open() == Dialog.OK) {
			addNewMethod(selectMethodDialog.getMethod());
			refreshMethodCalls();
			getContainer().updateButtons();
		}
	}

	/**
	 * @param firstElement
	 */
	protected void removeMethod(Object firstElement) {
		if (! (firstElement instanceof MethodEntry)) {
			return;
		}
		MethodEntry entry = (MethodEntry)firstElement;
		methodEntries.remove(entry);
		refreshMethodCalls();
	}

	/**
	 * @param methodCalls
	 * @return
	 */
	private List<List<Object>> toMethodCalls(List<MethodEntry> methodCalls) {
		List<List<Object>> calls = new ArrayList<List<Object>>();
		for (MethodEntry entry : methodCalls) {
			List<Object> method = new ArrayList<Object>();
			method.add(entry.methodName);
			method.addAll(toValuesList(entry.args));
			calls.add(method);
		}
		return calls;
	}

	/**
	 * @param args
	 * @return
	 */
	private List<Object> toValuesList(List<ArgDescription> args) {
		List<Object> res = new ArrayList<Object>();
		for (ArgDescription arg : args) {
			res.add(arg.value);
		}
		return res;
	}


	/**
	 * @param methodName
	 * @param values
	 */
	private void addMethod(MethodEntry entry) {
		methodEntries.add(entry);
	}

	private void addNewMethod(IMethod method) {
		String methodName = method.getElementName();
		MethodEntry entry = new MethodEntry(methodName); 
		try {
			for (int i = 0; i < method.getParameterNames().length; i++) {
				ArgDescription arg = new ArgDescription(method.getParameterNames()[i], Signature.toString(method.getParameterTypes()[i]), null); // TODO replace of remove default value
				entry.addArgDescription(arg);
			}
		} catch (JavaModelException ex) {
			BonitaStudioLog.log(ex);
		}
		methodEntries.add(entry);
	}

	/**
	 * 
	 */
	public void refreshMethodCalls() {
		parameters.put(JAVA_CONNECTOR_METHODS, methodEntries);
		parameters.put(METHODS_SETTER, toMethodCalls(methodEntries));
		methodsTree.refresh();
	}

	/**
	 * @return
	 */
	public List<MethodEntry> getMethodEntries() {
		return methodEntries;
	}

	/**
	 * @param classText
	 */
	private void openClassSelectionDialog(final Text classText) {
		JavaSearchScope scope = new JavaSearchScope();
		try {
			scope.add(javaProject);
		} catch (Exception ex) {
			BonitaStudioLog.log(ex);
		}
		FilteredTypesSelectionDialog searchDialog = new FilteredTypesSelectionDialog(getShell(), false, null, scope, IJavaSearchConstants.CLASS);
		if (searchDialog.open() == Dialog.OK) {
			if(searchDialog.getFirstResult() != null){
				classText.setText(((IType)searchDialog.getFirstResult()).getFullyQualifiedName());
				openSelectMethodDialog();
			}
		}
	}

	public void setTextOrDataFactory(ITextOrDataFactory textOrDataFactory) {

	}

	public void setFixedOutput(boolean fixedOutput) {

	}

	public void testConnector() {
		new TestConnector("org.bonitasoft.connectors.java.JavaConnector", parameters, TestConnector.CONNECTOR_TYPE).test();
	}



}
