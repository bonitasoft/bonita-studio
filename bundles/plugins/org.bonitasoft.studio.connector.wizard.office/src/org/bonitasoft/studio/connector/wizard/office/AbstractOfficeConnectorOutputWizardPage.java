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
package org.bonitasoft.studio.connector.wizard.office;

import java.util.Objects;

import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.Widget;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.bonitasoft.studio.connector.wizard.office.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorAvailableExpressionTypeFilter;
import org.bonitasoft.studio.connectors.ui.wizard.page.ConnectorOutputWizardPage;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;


public abstract class AbstractOfficeConnectorOutputWizardPage extends ConnectorOutputWizardPage {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.ui.wizard.page.ConnectorOutputWizardPage#doCreateControl(org.eclipse.swt.widgets.Composite,
     * org.eclipse.emf.databinding.EMFDataBindingContext)
     */
    @Override
    protected Control doCreateControl(Composite parent, EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Composite outputFileNameComposite = new Composite(mainComposite, SWT.NONE);
        outputFileNameComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());
        outputFileNameComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        final ConnectorDefinition connectorDefinition = getDefinition();
        final Connector connector = getConnector();

        final PageComponentSwitch componentSwitch = getPageComponenetSwitch(context, outputFileNameComposite, connector);
        doSwitch(connectorDefinition, componentSwitch);

        new Label(outputFileNameComposite, SWT.NONE);
        final Label exampleLabel = new Label(outputFileNameComposite, SWT.NONE);
        exampleLabel.setText(getExampleOutputFilenameText());
        exampleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(15, -5).create());

        final Group outptOperationGroup = new Group(mainComposite, SWT.NONE);
        outptOperationGroup.setText(Messages.outputOperations);
        outptOperationGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        outptOperationGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).indent(0, 0).create());
        super.doCreateControl(outptOperationGroup, context);
        return mainComposite;
    }

    protected abstract void doSwitch(final ConnectorDefinition connectorDefinition, final PageComponentSwitch componentSwitch);

    protected abstract String getExampleOutputFilenameText();

    protected PageComponentSwitch getPageComponenetSwitch(EMFDataBindingContext context, final Composite outputFileNameComposite, final Connector connector) {
        final PageComponentSwitchBuilder builder = new PageComponentSwitchBuilder(getElementContainer(), getDefinition(), connector.getConfiguration(), context,
                getMessageProvider(),
                new ConnectorAvailableExpressionTypeFilter());
        final PageComponentSwitch componentSwitch = new PageComponentSwitch(getContainer(), outputFileNameComposite, builder);
        return componentSwitch;
    }

    protected Widget findWidgetWithInput(final ConnectorDefinition connectorDefinition, String inputName) {
        final EList<Page> pages = connectorDefinition.getPage();
        for (final Page page : pages) {
            for(final Component w :  page.getWidget()){
                if (Objects.equals(inputName, w.getId())) {
                    return (Widget) w;
                }
            }
        }
        throw new IllegalStateException("No widget found for input: " + inputName);
    }
}
