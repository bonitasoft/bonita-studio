/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.pages.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.connector.wizard.sapjco3.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.sapjco3.providers.TreeContentProvider;
import org.bonitasoft.studio.connector.wizard.sapjco3.providers.TreeLabelProvider;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapFunctionField;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapTool;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Maxence Raoux
 * 
 */
public abstract class AbstractInputOutputDialog extends AbstractDialog {
	protected CheckboxTreeViewer checkboxTreeViewer;
	protected SapTool sapTool;
	protected List<SapFunctionField> treeElement;
	protected List<SapFunctionField> selectedElements;
	private Button singleRadioButton;
	private Button tableRadioButton;
	private Button structureRadioButton;

	public AbstractInputOutputDialog(Shell parentShell, SapTool sapTool) {
		super(parentShell);
		this.sapTool = sapTool;
		selectedElements = new ArrayList<SapFunctionField>();
		initializeTreeElement();
	}

	@Override
	protected void initializeBounds() {
		super.initializeBounds();
		int shellWidth = 400;
		int shellHeight = 500;
		getShell().setSize(shellWidth, shellHeight);
	}

	public List<SapFunctionField> getSelectedElement() {
		return selectedElements;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1)
				.margins(10, 10).spacing(3, 10).create());
		pageComposite.setLayoutData(GridDataFactory.fillDefaults()
				.grab(true, true).create());

		createRadioButtonComponent(pageComposite);
		createTreeViewerComponent(pageComposite);
		createButtonCheckComponent(pageComposite);

		return super.createDialogArea(parent);
	}

	private void createButtonCheckComponent(final Composite pageComposite) {
		final Composite buttonComposite = new Composite(pageComposite, SWT.NONE);
		buttonComposite.setLayout(GridLayoutFactory.fillDefaults()
				.numColumns(2).create());
		buttonComposite.setLayoutData(GridDataFactory.fillDefaults()
				.grab(true, false).create());
		final Button selecteAllButton = new Button(buttonComposite, SWT.FLAT);
		selecteAllButton.setText(Messages.selectAll);
		selecteAllButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				for (SapFunctionField element : treeElement) {
					if (!checkboxTreeViewer.getChecked(element)) {
						checkboxTreeViewer.setSubtreeChecked(element, true);
					}
				}
				refreshSelectedElements();
			}
		});

		final Button unselecteAllButton = new Button(buttonComposite, SWT.FLAT);
		unselecteAllButton.setText(Messages.unselectedAll);
		unselecteAllButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				for (SapFunctionField element : treeElement) {
					if (checkboxTreeViewer.getChecked(element)) {
						checkboxTreeViewer.setSubtreeChecked(element, false);
					}
				}
				refreshSelectedElements();
			}
		});
	}

	private void createRadioButtonComponent(final Composite pageComposite) {
		final Label checkboxLabel = new Label(pageComposite, SWT.WRAP);
		checkboxLabel.setText(Messages.selectedInputLabel);
		final Composite radioButtonComposite = new Composite(pageComposite,
				SWT.NONE);
		radioButtonComposite.setLayout(GridLayoutFactory.fillDefaults()
				.numColumns(3).create());
		radioButtonComposite.setLayoutData(GridDataFactory.fillDefaults()
				.grab(true, false).create());
		singleRadioButton = new Button(radioButtonComposite, SWT.RADIO);
		makeRadioButton(singleRadioButton, "Single", true, false, false);
		tableRadioButton = new Button(radioButtonComposite, SWT.RADIO);
		makeRadioButton(tableRadioButton, "Table", false, true, false);
		structureRadioButton = new Button(radioButtonComposite, SWT.RADIO);
		makeRadioButton(structureRadioButton, "Structure", false, false, true);
		singleRadioButton.setSelection(true);
	}

	private void createTreeViewerComponent(final Composite pageComposite) {
		checkboxTreeViewer = new CheckboxTreeViewer(pageComposite);
		checkboxTreeViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		checkboxTreeViewer.setContentProvider(new TreeContentProvider(treeElement));
		checkboxTreeViewer.setLabelProvider(new TreeLabelProvider());
		checkboxTreeViewer.setInput("root");
		checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				SapFunctionField field = (SapFunctionField) event.getElement();
				if (event.getChecked()) {
					checkboxTreeViewer.setSubtreeChecked(field, true);
					if (field.getParent() != null) {
						checkboxTreeViewer.setChecked(field.getParent(), true);
					}
				} else {
					checkboxTreeViewer.setSubtreeChecked(field, false);
					if (field.getParent() != null) {
						Boolean allChildNotCheck = true;
						for (SapFunctionField child : field.getParent().getFieldsList()) {
							if (checkboxTreeViewer.getChecked(child)) {
								allChildNotCheck = false;
								break;
							}
						}
						if (allChildNotCheck) {
							checkboxTreeViewer.setChecked(field.getParent(),false);
						}
					}
				}
				refreshSelectedElements();
			}
		});
	}

	protected abstract void makeRadioButton(Button button, String name,
			final Boolean single, final Boolean table, final Boolean structure);

	private void refreshSelectedElements() {
		selectedElements.clear();
		refreshSelectedElements(treeElement);
	}

	private void refreshSelectedElements(List<SapFunctionField> l) {
		for (SapFunctionField element : l) {
			if (checkboxTreeViewer.getChecked(element)) {
				if (element.isStructure() || element.isTable()) {
					refreshSelectedElements(element.getFieldsList());
				} else {
					selectedElements.add(element);
				}
			}
		}
	}

	protected abstract void initializeTreeElement();
}
