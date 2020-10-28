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
package org.bonitasoft.studio.connector.wizard.office.templating;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.properties.Well;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.wizard.ConnectorConfigurationSupport;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder;
import org.bonitasoft.studio.connector.wizard.office.FilteredGeneratedConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connector.wizard.office.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorAvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionCollectionViewer;
import org.bonitasoft.studio.expression.editor.viewer.IAddLineListener;
import org.bonitasoft.studio.expression.editor.viewer.IExpressionModeListener;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.common.collect.Sets;

public class DocumentTemplatingConnectorConfigurationWizardPage extends FilteredGeneratedConnectorConfigurationWizardPage {

    private static final String OUTPUT_FILE_NAME_INPUT = "outputFileName";

    private static final String REPLACEMENTS_INPUT = "replacements";

    private static final String SEARCH_KEYWORD = "\"insert data in template\", \"lazy\"";

    private Listener validationListener;

    private ExpressionCollectionViewer viewer;

    private Well warningWell;

    public DocumentTemplatingConnectorConfigurationWizardPage() {
        super(Sets.newHashSet(OUTPUT_FILE_NAME_INPUT));
    }

    private static final String VELOCITY_DOC_LINK = "http://velocity.apache.org/";

    @Override
    public Control doCreateControl(final Composite parent, final EMFDataBindingContext context) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());

        final Composite pageComposite = new Composite(mainComposite, SWT.NONE);
        pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(3, 10).create());
        pageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Well info = new Well(pageComposite, Messages.advancedDocumentation, new FormToolkit(Display.getDefault()),
                IStatus.INFO);
        info.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).hint(410, SWT.DEFAULT).create());
        info.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                performHelp();
            }
        });

        final Composite composite = new Composite(pageComposite, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 2).hint(500, 300).create());

        super.doCreateControl(composite, context).setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Label label = new Label(pageComposite, SWT.NONE);
        label.setLayoutData(GridDataFactory.fillDefaults().hint(100, SWT.NONE).create());

        warningWell = new Well(pageComposite, "", String.format(Messages.replacemantWithlazyRefExample, SEARCH_KEYWORD),
                new FormToolkit(Display.getDefault()),
                IStatus.WARNING);
        warningWell.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(20, 0).create());
        warningWell.setVisible(false);

        final TableExpressionWithoutLazyLoadedRefs validator = new TableExpressionWithoutLazyLoadedRefs(
                RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class));
        validationListener = validationListener(warningWell, validator);
        viewer.addExpressionModeListener(new IExpressionModeListener() {

            @Override
            public void useTable() {

            }

            @Override
            public void useSimpleExpression() {
                warningWell.setVisible(false);
            }
        });
        viewer.addModifyListener(validationListener);
        validationListener.handleEvent(null);

        return mainComposite;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.wizard.office.FilteredGeneratedConnectorConfigurationWizardPage#getComponentBuilder(org.eclipse.emf.databinding.
     * EMFDataBindingContext)
     */
    @Override
    protected PageComponentSwitchBuilder getComponentBuilder(final EMFDataBindingContext context) {
        return new PageComponentSwitchBuilder(getElementContainer(), getDefinition(), getConfiguration(), context,
                getMessageProvider(), getExpressionTypeFilter()) {

            /*
             * (non-Javadoc)
             * @see org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitchBuilder#createArrayControl(org.eclipse.swt.widgets.Composite,
             * org.bonitasoft.studio.connector.model.definition.Array)
             */
            @Override
            public ExpressionCollectionViewer createArrayControl(final Composite composite, final Array array) {
                viewer = super.createArrayControl(composite, array);
                viewer.addLineListener(new IAddLineListener() {

                    @Override
                    public void newLinedAdded(List<Expression> expressions) {
                        final Expression expression = expressions.get(0);
                        expression.setReturnTypeFixed(true);
                    }
                });
                return viewer;
            }


            @Override
            protected void configureArrayColumnFilters(Array array, ExpressionCollectionViewer viewer) {
                viewer.addFilter(new AvailableExpressionTypeFilter(ExpressionConstants.CONSTANT_TYPE));
                ConnectorAvailableExpressionTypeFilter connectorAvailableExpressionTypeFilter = new ConnectorAvailableExpressionTypeFilter(
                        new String[] { ExpressionConstants.CONSTANT_TYPE,
                        ExpressionConstants.VARIABLE_TYPE,
                        ExpressionConstants.SCRIPT_TYPE,
                        ExpressionConstants.PARAMETER_TYPE,
                                ExpressionConstants.DOCUMENT_TYPE });
                viewer.addFilter(connectorAvailableExpressionTypeFilter);
                viewer.setExpressionEditorFilter(connectorAvailableExpressionTypeFilter);
            }

        };
    }

    protected Listener validationListener(final Well well, final TableExpressionWithoutLazyLoadedRefs validator) {
        return new Listener() {

            @Override
            public void handleEvent(final Event event) {
                final ConnectorConfigurationSupport connectorConfigurationSupport = new ConnectorConfigurationSupport(
                        getConfiguration());
                final ConnectorParameter connectorParam = connectorConfigurationSupport
                        .findConnectorParameter(REPLACEMENTS_INPUT);
                well.setVisible(false);
                if (connectorParam.getExpression() instanceof TableExpression) {
                    final TableExpression expression = (TableExpression) connectorParam.getExpression();
                    final IStatus status = validator.validate(expression);
                    well.setText(status.getMessage());
                    well.setVisible(!status.isOK());
                }
                well.getParent().layout();
            }

        };
    }

    @Override
    public void performHelp() {
        try {
            new OpenBrowserOperation(new URL(VELOCITY_DOC_LINK)).execute();
        } catch (final MalformedURLException e) {
            BonitaStudioLog.error(e);
        }
    }
}
