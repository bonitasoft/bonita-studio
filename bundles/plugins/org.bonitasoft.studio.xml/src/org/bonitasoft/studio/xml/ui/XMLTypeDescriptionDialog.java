/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.xml.Messages;
import org.bonitasoft.studio.xml.api.XSDImport;
import org.bonitasoft.studio.xml.repository.XSDFileStore;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xsd.XSDSchema;

/**
 * @author Mickael Istria
 *
 */
public class XMLTypeDescriptionDialog extends Dialog {

	private String namespace;
	private String element;
	private XSDRepositoryStore xsdStore;

	/**
	 * @param parentShell
	 * @param element 
	 * @param namespace 
	 */
	public XMLTypeDescriptionDialog(Shell parentShell, String namespace, String element) {
		super(parentShell);
		this.namespace = namespace;
		this.element = element;
		this.xsdStore = (XSDRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class) ;
	}

	/**
	 * @return
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * @return
	 */
	public String getElement() {
		return element;
	}

	@Override
	public Composite createDialogArea(Composite parent) {
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayout(new GridLayout(2, false));
		Label selectNSAndElementLabel = new Label(res, SWT.None);
		selectNSAndElementLabel.setText(Messages.selectNamespaceAndElement);
		selectNSAndElementLabel.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
		Label namesapceLabel = new Label(res, SWT.NONE);
		namesapceLabel.setText(Messages.namespace);
		final Combo nsCombo = new Combo(res, SWT.NONE);
		GridData gridData = new GridData(SWT.FILL, SWT.NONE, true, false);
		gridData.minimumWidth = 300;
		nsCombo.setLayoutData(gridData);
		Label elementLabel = new Label(res, SWT.NONE);
		elementLabel.setText(Messages.element);
		final Combo elementCombo = new Combo(res, SWT.NONE);
		final List<XSDFileStore> allArtifacts = xsdStore.getChildren() ;
		final List<String> items = new ArrayList<String>();
		for (IRepositoryFileStore artifact : allArtifacts) {
			items.add(((XSDSchema) artifact.getContent()).getTargetNamespace());
		}
		nsCombo.setItems(items.toArray(new String[items.size()]));
		nsCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (nsCombo.getSelectionIndex() >= 0) {
					items.clear();
					XSDFileStore artifact =	(XSDFileStore) xsdStore.getChildren().get(nsCombo.getSelectionIndex());
					for (String element : artifact.getElements()) {
						items.add(element);
					}
					elementCombo.setItems(items.toArray(new String[items.size()]));
				} else {
					elementCombo.removeAll();
				}
			}
		});
		nsCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				namespace = nsCombo.getText();
			}
		});
		elementCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				element = elementCombo.getText();
			}
		});

		Button addXSDButton = new Button(res, SWT.FLAT);
		addXSDButton.setText(Messages.AddXSD);
		addXSDButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(), SWT.OPEN | SWT.MULTI);
				fd.setFilterExtensions(new String[] {"*.xsd"});
				fd.setText(Messages.selectXSDToImport);
				if (fd.open() != null) {
					for (String fileName : fd.getFileNames()) {
						XSDImport.importXSD(fd.getFilterPath() + File.separator + fileName);
					}
					items.clear();
					for (IRepositoryFileStore artifact : xsdStore.getChildren()) {
						items.add(((XSDSchema) artifact.getContent()).getTargetNamespace());
					}
					nsCombo.setItems(items.toArray(new String[items.size()]));
					if (namespace != null) {
						nsCombo.setText(namespace);
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		if (namespace != null) {
			nsCombo.setText(namespace);
			XSDFileStore artifact = xsdStore.findArtifactWithNamespace(namespace);
			items.clear();
			for (String element : artifact.getElements()) {
				items.add(element);
			}
			elementCombo.setItems(items.toArray(new String[items.size()]));
		}
		if (element != null) {
			elementCombo.setText(element);
		}

		return res;
	}
}
