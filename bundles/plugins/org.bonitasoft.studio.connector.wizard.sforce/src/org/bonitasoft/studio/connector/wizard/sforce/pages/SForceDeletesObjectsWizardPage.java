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

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.List;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sforce.componentSwitch.AbstractSforceComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sforce.componentSwitch.ExpressionNameNatureProvider;
import org.bonitasoft.studio.connector.wizard.sforce.i18n.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.ComboContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.Section;

/**
 * @author Maxence Raoux
 * 
 */
public class SForceDeletesObjectsWizardPage extends AbstractSforceWizardPage {

	private SForceDeleteObjectComponentSwitch compSwitch;

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

		createAutoCompletionGroup(pageComposite);

		final Page page = getPage();
		final PageComponentSwitch componentSwitch = getPageComponentSwitch(
				context, pageComposite);

		for (Component component : page.getWidget()) {
			componentSwitch.doSwitch(component);
		}
		for (Section section : componentSwitch.getSectionsToExpand()) {
			section.setExpanded(true);
		}
		return mainComposite;
	}

	private void createAutoCompletionGroup(final Composite pageComposite) {
		final Group connectionGroup = new Group(pageComposite, SWT.NONE);
		connectionGroup.setLayoutData(GridDataFactory.fillDefaults().span(2, 1)
				.create());
		connectionGroup.setText(Messages.filterAutoCompletionTitle);
		connectionGroup.setLayout(GridLayoutFactory.fillDefaults()
				.numColumns(2).margins(5, 5).create());

		final Label connectionLabel = new Label(connectionGroup, SWT.NONE);
		connectionLabel.setLayoutData(GridDataFactory.swtDefaults().span(2, 1)
				.create());
		connectionLabel.setText(Messages.filterAutoCompletionExplanation);

		final Combo combo = new Combo(connectionGroup, SWT.BORDER);
		final java.util.List<String> allObjects = sfTool.getAllSForceObjects();
		final String[] allObjectsTab = sfTool.getAllSForceObjects().toArray(
				new String[allObjects.size()]);
		combo.setItems(allObjectsTab);
		combo.setLayoutData(GridDataFactory.fillDefaults().span(2, 1)
				.grab(true, false).create());
		new AutoCompleteField(combo, new ComboContentAdapter(), allObjectsTab);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				comboListener(combo, combo.getItem(combo.getSelectionIndex()));
			}
		});
		combo.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				comboListener(combo, combo.getText());
			}
		});
	}

	private void comboListener(final Combo combo, String objName) {
		if (sfTool.getAllSForceObjects().contains(objName)) {
			compSwitch.getExpIdViewer().removeExpressionNatureProvider(0);
			ExpressionNameNatureProvider provider = new ExpressionNameNatureProvider(
					sfTool.getObjectIdAndName(objName));
			compSwitch.getExpIdViewer().addExpressionNatureProvider(provider);
		} else {
			compSwitch.getExpIdViewer().removeExpressionNatureProvider(0);
		}
	}

	@Override
	protected PageComponentSwitch getPageComponentSwitch(
			EMFDataBindingContext context, Composite pageComposite) {
		compSwitch = new SForceDeleteObjectComponentSwitch(getContainer(),
				pageComposite, getElementContainer(), getDefinition(),
				getConfiguration(), context, getMessageProvider(),
				getExpressionTypeFilter());
		return compSwitch;
	}

	private class SForceDeleteObjectComponentSwitch extends
			AbstractSforceComponentSwitch {

		private ExpressionCollectionViewer expIdViewer;

		public SForceDeleteObjectComponentSwitch(
				IWizardContainer iWizardContainer, Composite parent,
				EObject container, ConnectorDefinition definition,
				ConnectorConfiguration connectorConfiguration,
				EMFDataBindingContext context,
				DefinitionResourceProvider messageProvider,
				AvailableExpressionTypeFilter connectorExpressionContentTypeFilter) {
			super(iWizardContainer, parent, container, definition,
					connectorConfiguration, context, messageProvider,
					connectorExpressionContentTypeFilter);
			expIdViewer = null;
		}

		protected ExpressionCollectionViewer getExpIdViewer() {
			return expIdViewer;
		}

		@Override
		protected ExpressionCollectionViewer createListControl(
				Composite composite, List object) {
			if (object.getInputName().equals(S_OBJECT_IDS)) {
				expIdViewer = super.createListControl(composite, object);
				expIdViewer.setExpressionProposalLableProvider(0,new ExpressionLabelProvider(){
					@Override
					public String getDescription(Expression expression) {
						return expression.getContent();
					}
					
					@Override
					public String getText(Object expression) {
						String string = objectAndIdMap.get(((Expression) expression).getContent());
						if(string == null){
							string = ((Expression) expression).getContent();
						}
						return string;
					}
				});
				return expIdViewer;
			} else {
				return super.createListControl(composite, object);
			}
		}
	}
}
