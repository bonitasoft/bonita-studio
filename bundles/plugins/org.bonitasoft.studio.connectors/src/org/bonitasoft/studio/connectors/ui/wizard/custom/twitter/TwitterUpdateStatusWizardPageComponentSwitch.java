/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.ui.wizard.custom.twitter;

import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.pattern.PatternExpressionViewer;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class TwitterUpdateStatusWizardPageComponentSwitch extends
        PageComponentSwitch {

    private PatternExpressionViewer newStatusViewer;
    private Text caracterCountNumberText;

    public TwitterUpdateStatusWizardPageComponentSwitch(
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

    @Override
    protected PatternExpressionViewer createTextAreaControl(
            Composite composite, TextArea object) {
        if ("status".equals(object.getInputName())) {
            newStatusViewer = super.createTextAreaControl(composite, object);
            newStatusViewer.getTextControl().addKeyListener(new KeyListener() {

                @Override
                public void keyReleased(KeyEvent e) {
                    caracterCountNumberText.setText(""
                            + (140 - newStatusViewer.getTextControl().getText()
                                    .length()));
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }
            });
            createCaracterCountComposite(composite);

            return newStatusViewer;
        }
        return super.createTextAreaControl(composite, object);
    }

    private void createCaracterCountComposite(Composite composite) {
        final Composite caractereCountComposite = new Composite(composite,
                SWT.NONE);
        caractereCountComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(2).margins(5, 5).create());
        caractereCountComposite.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).span(2, 1).create());

        final Label caracterCountLabel = new Label(caractereCountComposite,
                SWT.NONE);
        caracterCountLabel.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false).create());
        caracterCountLabel.setText(Messages.charactersLeft);
        caracterCountNumberText = new Text(caractereCountComposite, SWT.NONE
                | SWT.READ_ONLY);
        caracterCountNumberText.setLayoutData(GridDataFactory.fillDefaults()
                .hint(40, 0).create());
        caracterCountNumberText.setText("140");
    }
}
