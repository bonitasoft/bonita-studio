/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connector.wizard.sforce.pages;

import java.util.List;

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sforce.componentSwitch.AbstractSforceComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sforce.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.sforce.querys.QueryCondition;
import org.bonitasoft.studio.connector.wizard.sforce.querys.QueryHelper;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.pattern.ITextControl;
import org.bonitasoft.studio.expression.editor.pattern.PatternExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.forms.widgets.Section;

/**
 * @author Maxence Raoux
 * 
 */
public class SForceQuerysObjectsWizardPage extends AbstractSforceWizardPage {

	private SForceQueryObjectComponentSwitch compSwitch;
	private Group helperGroup;
	private Combo objName;
	private org.eclipse.swt.widgets.List possibleFields;
	private org.eclipse.swt.widgets.List chooseFields;
	private Combo condChamp1;
	private Combo condChamp2;
	private Combo condOpp1;
	private Combo condOpp2;

	private final QueryHelper queryHelper;

	public SForceQuerysObjectsWizardPage() {
		super();
		queryHelper = new QueryHelper();
	}

	private void generateQuery() {
		final String query = queryHelper.generateQuery();
		compSwitch.getQueryViewer().getTextControl().setText(query);
	}

	@Override
	public Control doCreateControl(Composite parent,
			EMFDataBindingContext context) {

		final Composite mainComposite = new Composite(parent, SWT.NONE);
		mainComposite.setLayoutData(GridDataFactory.fillDefaults()
				.grab(true, false).create());
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1)
				.margins(0, 0).spacing(0, 0).create());

		final Composite pageComposite = new Composite(mainComposite, SWT.NONE);
		pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
				.margins(10, 10).spacing(3, 10).create());
		pageComposite.setLayoutData(GridDataFactory.fillDefaults()
				.grab(true, true).create());

		final Page page = getPage();
		final PageComponentSwitch componentSwitch = getPageComponentSwitch(
				context, pageComposite);

		for (final Component component : page.getWidget()) {
			componentSwitch.doSwitch(component);
		}

		for (final Section section : componentSwitch.getSectionsToExpand()) {
			section.setExpanded(true);
		}

        createHelperGroup(pageComposite);

		return mainComposite;
	}

	private void completeCombo(Combo combo, List<String> listes) {
		final String[] allObjectsTab = listes.toArray(new String[listes.size()]);
		combo.setItems(allObjectsTab);
	}

	private void createHelperGroup(final Composite pageComposite) {
		final Link switchControl = new Link(pageComposite, SWT.NONE);
		switchControl.setText(Messages.helperLink);
        switchControl.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1)
				.create());
		switchControl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				helperGroup.setVisible(!helperGroup.isVisible());
				final ITextControl control = compSwitch.getQueryViewer()
						.getTextControl();
				control.setEditable(!control.getEditable());
				control.setEnabled(!control.getEnabled());
				if (control.getEditable()) {
				    control.setBackground(getShell().getDisplay().getSystemColor(
							SWT.COLOR_WHITE));
				} else {
				    control.setBackground(getShell().getDisplay().getSystemColor(
							SWT.COLOR_WIDGET_LIGHT_SHADOW));
					cleanHelper();
				}
			}

		});

		final ITextControl t = compSwitch.getQueryViewer().getTextControl();
		t.setEditable(true);
		t.setEnabled(true);
        t.getParent().getParent().setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).hint(SWT.DEFAULT, 100).create());
		helperGroup = new Group(pageComposite, SWT.NONE);
        helperGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1)
				.create());
		helperGroup.setText(Messages.filterAutoCompletionTitle);
		helperGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
				.margins(5, 5).create());
		final Label objectTypeLabel = new Label(helperGroup, SWT.NONE);
		objectTypeLabel.setText(Messages.queryObjectType);
		objName = new Combo(helperGroup, SWT.BORDER);
		completeCombo(objName, sfTool.getAllSForceObjects());
		objName.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
				.create());
		objName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				final String name = objName.getText();
				queryHelper.setFrom(name);
				if (sfTool.getAllSForceObjects().contains(name)) {
					final List<String> fields = sfTool.getFields(name);
					completeCombo(condChamp1, fields);
					possibleFields.setItems(fields.toArray(new String[fields
							.size()]));
					chooseFields.setItems(new String[0]);
					generateQuery();
					condOpp1.setEnabled(false);
				}
			}
		});

		final Label objectFieldsLabel = new Label(helperGroup, SWT.NONE);
		objectFieldsLabel.setText(Messages.queryObjectFields);

		final Composite fieldChooser = new Composite(helperGroup, SWT.NONE);
		fieldChooser.setLayout(GridLayoutFactory.fillDefaults().numColumns(3)
				.create());
		fieldChooser.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
        possibleFields = new org.eclipse.swt.widgets.List(fieldChooser, SWT.BORDER | SWT.MULTI
				| SWT.V_SCROLL);
        possibleFields.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
		final Composite fieldChooserButton = new Composite(fieldChooser,
				SWT.NONE);
		fieldChooserButton.setLayout(GridLayoutFactory.fillDefaults()
				.numColumns(1).create());
		fieldChooserButton.setLayoutData(GridDataFactory.fillDefaults()
                .grab(false, false).create());
		final Button addToFields = new Button(fieldChooserButton, SWT.FLAT);
		addToFields.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final String[] selection = possibleFields.getSelection();
				for (int i = 0; i < selection.length; i++) {
					chooseFields.add(selection[i]);
					queryHelper.addField(selection[i]);
					possibleFields.remove(selection[i]);
					generateQuery();
				}
			}
		});
		addToFields.setText(Messages.add + " >");
		addToFields.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
		final Button removeToFields = new Button(fieldChooserButton, SWT.FLAT);
		removeToFields.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
		removeToFields.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final String[] selection = chooseFields.getSelection();
				for (int i = 0; i < selection.length; i++) {
					possibleFields.add(selection[i]);
					chooseFields.remove(selection[i]);
					queryHelper.removeField(selection[i]);
					generateQuery();
				}
			}
		});
		removeToFields.setText("< " + Messages.remove);
        chooseFields = new org.eclipse.swt.widgets.List(fieldChooser, SWT.BORDER | SWT.MULTI
				| SWT.V_SCROLL);
        chooseFields.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, true).create());
		final Label ConditionLabel = new Label(helperGroup, SWT.NONE);
		ConditionLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.LEFT, SWT.CENTER).grab(false, false).create());
		ConditionLabel.setText(Messages.queryObjectConditions);
		final Composite conditionComposite = new Composite(helperGroup, SWT.NONE);
		conditionComposite.setLayout(GridLayoutFactory.fillDefaults()
				.numColumns(5).create());
		conditionComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(false, false).create());
		condOpp1 = new Combo(conditionComposite, SWT.BORDER | SWT.READ_ONLY);
		condOpp1.setItems(QueryHelper.boolOperands);
		condOpp1.select(0);
		condOpp1.setEnabled(false);
		condChamp1 = new Combo(conditionComposite, SWT.BORDER);
        condChamp1.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		condChamp1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final String name = objName.getItem(objName.getSelectionIndex());
				final String field = condChamp1.getItem(condChamp1
						.getSelectionIndex());
				if (sfTool.getAllSForceObjects().contains(name)
						&& sfTool.getFields(name).contains(field)) {
					completeCombo(condChamp2, sfTool.getPickValues(name, field));
				}
			}
		});
		condOpp2 = new Combo(conditionComposite, SWT.BORDER | SWT.READ_ONLY);
		condOpp2.setItems(QueryHelper.operands);
		condOpp2.select(0);

		condChamp2 = new Combo(conditionComposite, SWT.BORDER);
		condChamp2.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		final Button addCondition = new Button(conditionComposite, SWT.FLAT);
		addCondition.setLayoutData(GridDataFactory.fillDefaults()
                .grab(false, false).create());
		addCondition.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addConditionListner();
			}
		});
		addCondition.setText(Messages.add);
		helperGroup.setVisible(false);
	}

	private void addConditionListner() {
		if (!condChamp1.getText().isEmpty() && !condChamp2.getText().isEmpty()) {
			QueryCondition condition = null;
			if (condOpp1.isEnabled()) {
				condition = new QueryCondition(condOpp1.getText(),
						condChamp1.getText(), condOpp2.getText(),
						condChamp2.getText());

			} else {
				condOpp1.setEnabled(true);
				condition = new QueryCondition(condChamp1.getText(),
						condOpp2.getText(), condChamp2.getText());
			}
			condChamp1.setText("");
			condChamp2.setText("");
			queryHelper.addCondition(condition);
			generateQuery();
		} else {
			MessageDialog.openError(Display.getDefault().getActiveShell(),
					Messages.error, Messages.errorExplanation);
		}
	}

	private void cleanHelper() {
		queryHelper.clean();
		objName.setText("");
		possibleFields.removeAll();
		chooseFields.removeAll();
		condChamp1.setText("");
		condChamp2.setText("");
		condOpp1.select(0);
		condOpp2.select(0);
	}

	@Override
	protected PageComponentSwitch getPageComponentSwitch(
			EMFDataBindingContext context, Composite pageComposite) {
		compSwitch = new SForceQueryObjectComponentSwitch(getContainer(),
				pageComposite, getElementContainer(), getDefinition(),
				getConfiguration(), context, getMessageProvider(),
				getExpressionTypeFilter());
		return compSwitch;
	}

	private class SForceQueryObjectComponentSwitch extends
			AbstractSforceComponentSwitch {

		private PatternExpressionViewer queryViewer;

		public SForceQueryObjectComponentSwitch(
				IWizardContainer iWizardContainer, Composite parent,
				EObject container, ConnectorDefinition definition,
				ConnectorConfiguration connectorConfiguration,
				EMFDataBindingContext context,
				DefinitionResourceProvider messageProvider,
				AvailableExpressionTypeFilter connectorExpressionContentTypeFilter) {
			super(iWizardContainer, parent, container, definition,
					connectorConfiguration, context, messageProvider,
					connectorExpressionContentTypeFilter);
		}

		public PatternExpressionViewer getQueryViewer() {
			return queryViewer;
		}

		@Override
		protected PatternExpressionViewer createTextAreaControl(
				Composite composite, TextArea object) {
			if (object.getInputName().equals("query")) {
				queryViewer = super.createTextAreaControl(composite, object);
				queryViewer.getTextControl().setWordWrap(true);
				return queryViewer;
			} else {
				return super.createTextAreaControl(composite, object);
			}
		}
	}
}
