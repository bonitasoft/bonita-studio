package org.bonitasoft.studio.connector.wizard.sapjco3.pages;

import java.util.List;

import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.Select;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sapjco3.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapTool;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.ExpressionNatureProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class SapConnectionPageComponentSwitch extends PageComponentSwitch {

	private SapTool sap;
	private ExpressionCollectionViewer expColViewer;
	private Group serverTypeOptionsComposite;
	private SapConnectionPage connectionPage;

	private static String DESTINATION_DATA = "destinationData";

	public SapConnectionPageComponentSwitch(IWizardContainer iWizardContainer, 
	        Composite parent, 
	        EObject container,
	        ExtendedConnectorDefinition definition, 
			ConnectorConfiguration connectorConfiguration, 
			EMFDataBindingContext context,
			AvailableExpressionTypeFilter connectorExpressionContentTypeFilter,
			SapTool sap, SapConnectionPage connectionPage) {
		super(iWizardContainer, parent, container, definition, connectorConfiguration, context,
				connectorExpressionContentTypeFilter);
		this.sap = sap;
		this.connectionPage = connectionPage;
	}

	@Override
	protected ExpressionCollectionViewer createArrayControl(Composite composite, Array object) {
		if (object.getInputName().equals(DESTINATION_DATA)) {
			expColViewer = super.createArrayControl(composite, object);

			if (sap != null) {
				final List<String> propertiesName = sap.getConnectionPropertiesName();
				expColViewer.removeExpressionNatureProvider(0);
				expColViewer.addExpressionNatureProvider(new ExpressionNatureProvider(propertiesName));
				expColViewer.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						final TableViewer table = (TableViewer) event.getSource();
						final StructuredSelection s = (StructuredSelection) table.getSelection();
						final ListExpression l = (ListExpression) s.getFirstElement();
						if (l != null && l.getExpressions() != null && l.getExpressions().size() >= 2) {
							final String name = l.getExpressions().get(0).getContent();
							final String value = l.getExpressions().get(1).getContent();
							sap.addConnectionProperties(name, value);
						}
					}

				});
				expColViewer.getRemoveRowButton().addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						final StructuredSelection s = (StructuredSelection) expColViewer.getViewer().getSelection();
						final ListExpression l = (ListExpression) s.getFirstElement();
						if (l != null && l.getExpressions() != null && l.getExpressions().size() >= 1) {
							final String name = l.getExpressions().get(0).getContent();
							sap.removeConnectionProperties(name);
						}
						super.widgetSelected(e);
					}
				});
			}
			return expColViewer;
		} else {
			return super.createArrayControl(composite, object);
		}
	}

	@Override
	protected Combo createSelectControl(Composite composite, Select object) {
		if (object.getInputName().equals("serverType")) {
			final Composite destinationType = new Composite(composite, SWT.NONE);
			destinationType.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
			destinationType
					.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).indent(20, 0).grab(true, false).create());
			final Combo combo = super.createSelectControl(destinationType, object);
			combo.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					super.widgetSelected(e);
					final Combo c = (Combo) e.getSource();
					connectionPage.refreshServerTypeOptions(serverTypeOptionsComposite, c.getItem(c.getSelectionIndex()));
					destinationType.layout(true, true);
				}
			});
			serverTypeOptionsComposite = new Group(composite, SWT.NONE);
			serverTypeOptionsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());
			serverTypeOptionsComposite.setLayoutData(GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 57).grab(true, false)
					.span(2, 1).create());
			serverTypeOptionsComposite.setText(Messages.serverTypeGroupTitle);
			connectionPage.refreshServerTypeOptions(serverTypeOptionsComposite, "ApplicationServer");
			return combo;
		} else {
			return super.createSelectControl(composite, object);
		}
	}
}
