/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.parameters.wizard.page;

import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.parameters.action.ExportParametersAction;
import org.bonitasoft.studio.parameters.action.ImportParametersAction;
import org.bonitasoft.studio.parameters.i18n.Messages;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterNameLabelProvider;
import org.bonitasoft.studio.parameters.property.section.provider.ParameterTypeLabelProvider;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 */
public class ParametersConfigurationWizardPage extends WizardPage implements IProcessConfigurationWizardPage {

    private TableViewer parameterTableViewer;
    private ParameterValueEditingSupport valueEditingSupport;
    private AbstractProcess process;
    private Configuration configuration;

    public ParametersConfigurationWizardPage() {
        super(ParametersConfigurationWizardPage.class.getName());
        setTitle(Messages.parameters);
        setDescription(Messages.parameterWizardDesc);
    }

    @Override
    public void createControl(final Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        createParameterComposite(mainComposite);
        createImportExportButtons(mainComposite);
        setControl(mainComposite);
    }

    protected void createParameterComposite(final Composite parent) {
        final Composite parameterComposite = new Composite(parent, SWT.NONE);
        parameterComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        parameterComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

        final Label descriptionLabel = new Label(parameterComposite, SWT.WRAP);
        descriptionLabel.setText(getDescription());
        descriptionLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());

        parameterTableViewer = new TableViewer(parameterComposite,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        parameterTableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        ColumnViewerToolTipSupport.enableFor(parameterTableViewer);
        parameterTableViewer.setContentProvider(ArrayContentProvider.getInstance());
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(25));
        tableLayout.addColumnData(new ColumnWeightData(25));
        tableLayout.addColumnData(new ColumnWeightData(50));
        parameterTableViewer.getTable().setLayout(tableLayout);
        parameterTableViewer.getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {

            @Override
            public void beforeEditorDeactivated(final ColumnViewerEditorDeactivationEvent event) {
            }

            @Override
            public void beforeEditorActivated(final ColumnViewerEditorActivationEvent event) {
            }

            @Override
            public void afterEditorDeactivated(final ColumnViewerEditorDeactivationEvent event) {
                if (getContainer() != null) {
                    getContainer().updateMessage();
                }
            }

            @Override
            public void afterEditorActivated(final ColumnViewerEditorActivationEvent event) {
            }
        });

        final TableViewerColumn columnNameViewer = new TableViewerColumn(parameterTableViewer, SWT.NONE);
        columnNameViewer.setLabelProvider(new ParameterNameLabelProvider());
        final TableColumn column = columnNameViewer.getColumn();
        column.setText(Messages.name);

        final TableViewerColumn columnTypeViewer = new TableViewerColumn(parameterTableViewer, SWT.NONE);
        columnTypeViewer.setLabelProvider(new ParameterTypeLabelProvider());
        final TableColumn column3 = columnTypeViewer.getColumn();
        column3.setText(Messages.type);

        final TableViewerColumn columnValueViewer = new TableViewerColumn(parameterTableViewer, SWT.NONE);
        columnValueViewer.setLabelProvider(new LabelProviderBuilder<Parameter>()
                .withTextProvider(Parameter::getValue)
                .withStatusProvider(this::validateParameter)
                .createColumnLabelProvider());
        valueEditingSupport = new ParameterValueEditingSupport(columnValueViewer.getViewer(), this);

        columnValueViewer.setEditingSupport(valueEditingSupport);
        final TableColumn column2 = columnValueViewer.getColumn();
        column2.setText(Messages.value);

        parameterTableViewer.getTable().setHeaderVisible(true);

        final TableColumnSorter sorter = new TableColumnSorter(parameterTableViewer);
        sorter.setColumn(column);

    }

    @Override
    public void updatePage(final AbstractProcess process, final Configuration configuration) {
        this.process = process;
        this.configuration = configuration;
        if (process != null && configuration != null && parameterTableViewer != null
                && !parameterTableViewer.getTable().isDisposed()) {
            parameterTableViewer.setInput(configuration.getParameters());
        }
    }

    private void createImportExportButtons(final Composite mainComposite) {
        final Composite composite = new Composite(mainComposite, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

        final Button importButton = new Button(composite, SWT.PUSH);
        importButton.setText(Messages.importParameterFile);
        importButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
        importButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final ImportParametersAction action = new ImportParametersAction();
                action.setConfiguration(configuration);
                action.setProcess(process);
                action.run();
                parameterTableViewer.refresh();
                getContainer().updateMessage();
            }
        });

        final Button exportButton = new Button(composite, SWT.PUSH);
        exportButton.setText(Messages.exportParameterFile);
        exportButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
        exportButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final ExportParametersAction action = new ExportParametersAction();
                action.setConfiguration(configuration);
                action.setProcess(process);
                action.run();
            }
        });
    }

    @Override
    public String isConfigurationPageValid(final Configuration configuration) {
        if (configuration != null) {
            for (final Parameter p : configuration.getParameters()) {
                IStatus status = validateParameter(p);
                if (!status.isOK()) {
                    return status.getMessage();
                }
            }
        }
        return null;
    }

    private IStatus validateParameter(Parameter p) {
        final String input = p.getValue();
        final String typeName = p.getTypeClassname();
        if (input == null || input.isEmpty()) {
            return ValidationStatus.warning(Messages.bind(Messages.missingParameterValue, p.getName()));
        } else if (typeName.equals(Integer.class.getName())) {
            try {
                Integer.parseInt(input);
            } catch (final NumberFormatException e) {
                return ValidationStatus.error(Messages.bind(Messages.invalidIntegerForParameter, p.getName()));
            }
        } else if (typeName.equals(Double.class.getName())) {
            try {
                Double.parseDouble(input);
            } catch (final NumberFormatException e) {
                return ValidationStatus.error(Messages.bind(Messages.invalidDoulbeForParameter, p.getName()));
            }
        }
        return ValidationStatus.ok();
    }

    @Override
    public Image getConfigurationImage() {
        return Pics.getImage(PicsConstants.parameter);
    }

    @Override
    public boolean isDefault() {
        return true;
    }

}
