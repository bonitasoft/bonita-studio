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
package org.bonitasoft.studio.xml.ui;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.xml.Messages;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.bonitasoft.studio.xml.ui.XSDContentProvider.Append;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDParticle;

/**
 * @author Mickael Istria
 *
 */
public class SelectPathDialog extends Dialog {

	protected String details;
	private String namespace;
	private String element;
	private TreeViewer xsdViewer;
	private boolean browseOnly;
	private XSDElementDeclaration root;
	public static final String NO_NAMESPACE = "No Namespace";

	/**
	 * 
	 * @param parentShell
	 * @param initialValue
	 * @param data
	 * @param browseOnly Whether the dialog should show a tree only for browsing, without "Append" nodes
	 */
	public SelectPathDialog(Shell parentShell, String initialValue, XMLData data, boolean browseOnly) {
		this(parentShell, initialValue, data.getNamespace(), data.getType(), browseOnly);
	}

	/**
	 * @param shell
	 * @param object
	 * @param object2
	 * @param object3
	 */
	public SelectPathDialog(Shell shell, String initialValue, String namespace, String element, boolean browseOnly) {
		super(shell);
		this.details = initialValue;
		this.namespace = namespace;
		this.element = element;
		this.browseOnly = browseOnly;
		XSDRepositoryStore store = (XSDRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class) ;
		root = store.findElementDeclaration(NO_NAMESPACE.equals(namespace)?"":namespace, element);
	}

	/**
	 * @param shell
	 * @param initialValue
	 * @param outputType
	 * @param browseOnly2
	 */
	public SelectPathDialog(Shell shell, Object initialValue, XSDElementDeclaration outputType, boolean browseOnly2) {
		super(shell);
		root = outputType;
	}

	@Override
	public Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.selectElementLabel);
		if (root != null) {
			xsdViewer = new TreeViewer(composite);
			XSDContentProvider provider = new XSDContentProvider(browseOnly);
			provider.setElement(root);
			xsdViewer.setContentProvider(provider);
			XSDLabelProvider labelProvider = new XSDLabelProvider();
			xsdViewer.setLabelProvider(new DecoratingLabelProvider(labelProvider, labelProvider));
			GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
			layoutData.minimumHeight = 400;
			layoutData.minimumWidth = 400;
			xsdViewer.getControl().setLayoutData(layoutData);
			xsdViewer.setInput(new Object());
			if (details != null) {
				xsdViewer.setSelection(new StructuredSelection(createTreePath(details, provider)));				
			}
		}
		final Text text = new Text(composite, SWT.WRAP);
		if (details != null) {
			text.setText(details);
		}
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				details = text.getText();
			}
		});
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		layoutData.minimumHeight = 100;
		layoutData.minimumWidth = 400;
		text.setLayoutData(layoutData);
		
		if (xsdViewer != null) {
			xsdViewer.addSelectionChangedListener(new ISelectionChangedListener() {
				
				public void selectionChanged(SelectionChangedEvent event) {
					ITreeSelection selection = (ITreeSelection) xsdViewer.getSelection();
					String xpath = computeXPath(selection, false);
					text.setText(xpath);
					text.redraw();
				}
			});
		}
		
		return composite;
	}
	
	/**
	 * @param provider 
	 * @param details2
	 * @return
	 */
	private TreePath createTreePath(String details, XSDContentProvider provider) {
		String[] pieces = details.split("\\" + BonitaConstants.XPATH_VAR_SEPARATOR);
		boolean append = false;
		if (pieces.length > 1 && pieces[1].equals(BonitaConstants.XPATH_APPEND_FLAG)) {
			append = true;
		}
		details = pieces[0];
		List<String> segments = new ArrayList<String>();
		for (String segment : details.split("/")) {
			if (segment.length() != 0 && ! segment.equals("text()")) {
				if (segment.startsWith("@")) {
					segments.add(segment.substring(1));
				} else if (segment.contains("[")) {
					segments.add(segment.substring(0, segment.indexOf("[")));
				} else {
					segments.add(segment);
				}
			}
		}
		List<Object> res = new ArrayList<Object>();
		Object current = XSDContentProvider.WHOLE_XML; 
		res.add(current);
		for (String segment : segments) {
			for (Object item : provider.getChildren(current)) {
				if (item instanceof XSDNamedComponent && ((XSDNamedComponent)item).getName().equals(segment)) {
					current = item;
					res.add(current);
					break;
				}
			}
		}
		if (append) {
			res.add(Append.newInstance(current));
		}
		return new TreePath(res.toArray());
	}

	/**
	 * @param selection
	 * @return
	 */
	public static String computeXPath(ITreeSelection selection) {
		return computeXPath(selection, false);
	}

	/**
	 * @param selection
	 * @param useQualifiedName TODO
	 * @return
	 */
	public static String computeXPath(ITreeSelection selection, boolean useQualifiedName) {
		if (selection.getPaths().length == 0) {
			return "";
		}
		
		TreePath path = selection.getPaths()[0];
		return computeXPath(path, useQualifiedName);
	}

	/**
	 * @param path
	 * @return
	 */
	public static String computeXPath(TreePath path) {
		return computeXPath(path, false);
	}

	/**
	 * @param path
	 * @param useQualifiedName TODO
	 * @return
	 */
	public static String computeXPath(TreePath path, boolean useQualifiedName) {
		StringBuilder pathBuilder = new StringBuilder();
		for (int i = 1; i < path.getSegmentCount(); i++) {
			if (path.getSegment(i) instanceof XSDContentProvider.Append) {
				continue;
			}
			
			pathBuilder.append('/');
			XSDNamedComponent item = (XSDNamedComponent)path.getSegment(i);
			if (item instanceof XSDAttributeDeclaration) {
				pathBuilder.append('@');
			}
			if(useQualifiedName){
				pathBuilder.append(item.getQName());
			} else {
				pathBuilder.append(item.getName());
			}
			if (item instanceof XSDElementDeclaration) {
				XSDElementDeclaration element = (XSDElementDeclaration)item;
				if (element.getContainer() instanceof XSDParticle) {
					XSDParticle particle = (XSDParticle)element.getContainer();
					if (particle.getMaxOccurs() < 0 || particle.getMinOccurs() > 1) {
						pathBuilder.append("[1]");
					}
				}
			}
		}
		if (path.getLastSegment() instanceof XSDElementDeclaration && 
			((XSDElementDeclaration)path.getLastSegment()).getType().getSimpleType() != null) {
			pathBuilder.append("/text()");
		}
		if (path.getLastSegment() instanceof XSDContentProvider.Append) {
			pathBuilder.append(BonitaConstants.XPATH_VAR_SEPARATOR + BonitaConstants.XPATH_APPEND_FLAG);
		}
		return pathBuilder.toString();
	}

	public String getDetails() {
		return details;
	}
}
