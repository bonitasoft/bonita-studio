/**
 * Copyright (c) 2007, 2009 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik (Borland) - initial API and implementation
 *    Svyatoslav Kovalsky (Montages) - #410477 "same-generated" code extracted to GMFT-runtime 
 */
package org.eclipse.gmf.tooling.runtime.part;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.tooling.runtime.Messages;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Wizard page that allows to select element from model.
 * @since 3.1
 */
public class DefaultModelElementSelectionPage extends WizardPage {

	private final AdapterFactory myAdapterFactory;

	/**
	 * @deprecated use {@link DefaultModelElementSelectionPage#getModelElement()} instead
	 */
	@Deprecated
	protected EObject selectedModelElement;

	private TreeViewer myModelViewer;

	public DefaultModelElementSelectionPage(AdapterFactory adapterFactory, String pageName) {
		super(pageName);
		myAdapterFactory = adapterFactory;
	}

	public EObject getModelElement() {
		return selectedModelElement;
	}

	public void setModelElement(EObject modelElement) {
		selectedModelElement = modelElement;
		if (myModelViewer != null) {
			if (modelElement != null) {
				myModelViewer.setInput(modelElement.eResource());
				myModelViewer.setSelection(new StructuredSelection(modelElement));
			} else {
				myModelViewer.setInput(null);
			}
			setPageComplete(validatePage());
		}
	}

	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		Composite plate = new Composite(parent, SWT.NONE);
		plate.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		plate.setLayout(layout);
		setControl(plate);

		Label label = new Label(plate, SWT.NONE);
		label.setText(getSelectionTitle());
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		myModelViewer = new TreeViewer(plate, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.heightHint = 300;
		layoutData.widthHint = 300;
		myModelViewer.getTree().setLayoutData(layoutData);
		myModelViewer.setContentProvider(new AdapterFactoryContentProvider(myAdapterFactory));
		myModelViewer.setLabelProvider(new AdapterFactoryLabelProvider(myAdapterFactory));
		if (getModelElement() != null) {
			myModelViewer.setInput(getModelElement().eResource());
			myModelViewer.setSelection(new StructuredSelection(getModelElement()));
		}
		myModelViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				DefaultModelElementSelectionPage.this.updateSelection((IStructuredSelection) event.getSelection());
			}
		});

		setPageComplete(validatePage());
	}

	/**
	* Override to provide custom model element description.
	*/
	protected String getSelectionTitle() {
		return Messages.DefaultModelElementSelectionPageMessage;
	}

	protected void updateSelection(IStructuredSelection selection) {
		selectedModelElement = null;
		if (selection.size() == 1) {
			Object selectedElement = selection.getFirstElement();
			if (selectedElement instanceof IWrapperItemProvider) {
				selectedElement = ((IWrapperItemProvider) selectedElement).getValue();
			}
			if (selectedElement instanceof FeatureMap.Entry) {
				selectedElement = ((FeatureMap.Entry) selectedElement).getValue();
			}
			if (selectedElement instanceof EObject) {
				selectedModelElement = (EObject) selectedElement;
			}
		}
		setPageComplete(validatePage());
	}

	/**
	* Override to provide specific validation of the selected model element.
	*/
	protected boolean validatePage() {
		return true;
	}

}
