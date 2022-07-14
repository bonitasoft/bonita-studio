
/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import java.io.File;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.model.process.Connector;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ExportConnectorWizardPage extends WizardPage implements ISelectionChangedListener, IDoubleClickListener {

    protected Connector connector;
    private EMFDataBindingContext context;
    private WizardPageSupport pageSupport;
    private ConnectorImplementation selectedImplementation;
    private Button removeButton;
    private String destFilePath;
    private String destFileName;
    private boolean includeSources = true;
    private boolean addDependencies = true;
    private final IContentProvider contentProvider;
    private final LabelProvider labelProvider;
    private final IDefinitionRepositoryStore defStore;
    private TableViewer tableViewer;
    private Text destFileNameText;

    public ExportConnectorWizardPage(final String pageTitle, final String pageDesc, final IContentProvider contentProvider,
            final LabelProvider labelProvider, final IDefinitionRepositoryStore defStore) {
        super(ExportConnectorWizardPage.class.getName());
        setTitle(pageTitle);
        setDescription(pageDesc);
        this.contentProvider = contentProvider;
        this.labelProvider = labelProvider;
        this.defStore = defStore;
    }

    @Override
    public void createControl(final Composite parent) {
        context = new EMFDataBindingContext();
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(10, 10, 10, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Text serachBox = new Text(composite, SWT.BORDER | SWT.SEARCH | SWT.ICON_SEARCH);
        serachBox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        serachBox.setMessage(Messages.search);

        tableViewer = new TableViewer(composite, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        tableViewer.getTable()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(3, 1).hint(SWT.DEFAULT, 280).create());
        tableViewer.setContentProvider(contentProvider);
        tableViewer.setLabelProvider(labelProvider);
        tableViewer.addSelectionChangedListener(this);
        tableViewer.addDoubleClickListener(this);
        tableViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer arg0, final Object parentElement, final Object element) {
                final String search = serachBox.getText();
                return search == null || search.isEmpty()
                        || labelProvider.getText(element).toLowerCase().contains(search.toLowerCase());
            }
        });
        tableViewer.setInput(new Object());
        tableViewer.getTable().setFocus();

        serachBox.addModifyListener(e -> tableViewer.refresh());

        final IValidator selectionValidator = value -> {
            if (value == null) {
                return new Status(IStatus.ERROR, ConnectorPlugin.PLUGIN_ID, Messages.selectAConnectorImplWarning);
            }
            if (value instanceof ConnectorImplementation) {
                final String id = ((ConnectorImplementation) value).getDefinitionId();
                final String version = ((ConnectorImplementation) value).getDefinitionVersion();
                if (id == null || id.isEmpty()) {
                    return new Status(IStatus.ERROR, ConnectorPlugin.PLUGIN_ID, Messages.selectAValidConnectorImplWarning);
                }
                if (version == null || version.isEmpty()) {
                    return new Status(IStatus.ERROR, ConnectorPlugin.PLUGIN_ID, Messages.selectAValidConnectorImplWarning);
                }
                if (defStore.getDefinition(id, version) == null) {
                    return new Status(IStatus.ERROR, ConnectorPlugin.PLUGIN_ID, Messages.selectAValidConnectorImplWarning);
                }

                if (((ConnectorImplementation) value).getImplementationClassname() == null
                        || ((ConnectorImplementation) value).getImplementationClassname().isEmpty()) {
                    return new Status(IStatus.ERROR, ConnectorPlugin.PLUGIN_ID, Messages.selectAValidConnectorImplWarning);
                }
            }
            return Status.OK_STATUS;
        };

        final UpdateValueStrategy selectionStrategy = new UpdateValueStrategy();
        selectionStrategy.setBeforeSetValidator(selectionValidator);

        final Label inculeSourcesLabel = new Label(composite, SWT.NONE);
        inculeSourcesLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        inculeSourcesLabel.setText(Messages.inculeSourcesLabel);

        final Button includeSourceCheckbox = new Button(composite, SWT.CHECK);
        includeSourceCheckbox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        final Label addDependenciesLabel = new Label(composite, SWT.NONE);
        addDependenciesLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        addDependenciesLabel.setText(Messages.addDependencies);

        final Button addDependenciesCheckbox = new Button(composite, SWT.CHECK);
        addDependenciesCheckbox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        final Label destDirectoryLabel = new Label(composite, SWT.NONE);
        destDirectoryLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        destDirectoryLabel.setText(Messages.destinationLabel + " *");

        final Text destDirectoryText = new Text(composite, SWT.BORDER);
        destDirectoryText
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());

        final Button destFileButton = new Button(composite, SWT.PUSH);
        destFileButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create());
        destFileButton.setText(Messages.browsePackages);
        destFileButton.addListener(SWT.Selection, event -> {
            final String defaultName = generateDefaultName();
            selectDirectory(destDirectoryText, defaultName);
        });
        final Label destFileNameLabel = new Label(composite, SWT.NONE);
        destFileNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        destFileNameLabel.setText(Messages.destFileNameLabel);
        destFileNameText = new Text(composite, SWT.BORDER);
        destFileNameText.setText(generateDefaultName());
        destFileNameText
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());
        final UpdateValueStrategy pathStrategy = new UpdateValueStrategy();
        final EmptyInputValidator emptyValidator = new EmptyInputValidator(Messages.destinationLabel);
        pathStrategy.setBeforeSetValidator(value -> {
            final IStatus status = emptyValidator.validate((String)value);
            if (!status.isOK()) {
                return status;
            }
            final File target = new File((String) value);
            if (!target.exists()) {
                return new Status(IStatus.ERROR, ConnectorPlugin.PLUGIN_ID, Messages.targetPathIsInvalid);
            }
            return Status.OK_STATUS;
        });

        final UpdateValueStrategy nameStrategy = new UpdateValueStrategy();
        nameStrategy.setBeforeSetValidator(value -> {
            if (!value.toString().endsWith(".zip")) {
                return new Status(IStatus.ERROR, ConnectorPlugin.PLUGIN_ID, Messages.notAZipFile);
            }
            return Status.OK_STATUS;
        });
        context.bindValue(ViewersObservables.observeSingleSelection(tableViewer),
                PojoProperties.value(ExportConnectorWizardPage.class, "selectedImplementation").observe(this),
                selectionStrategy, null);
        context.bindValue(SWTObservables.observeSelection(includeSourceCheckbox),
                PojoProperties.value(ExportConnectorWizardPage.class, "includeSources").observe(this));
        context.bindValue(SWTObservables.observeSelection(addDependenciesCheckbox),
                PojoProperties.value(ExportConnectorWizardPage.class, "addDependencies").observe(this));
        context.bindValue(SWTObservables.observeText(destDirectoryText, SWT.Modify),
                PojoProperties.value(ExportConnectorWizardPage.class, "destFilePath").observe(this), pathStrategy, null);
        context.bindValue(SWTObservables.observeText(destFileNameText, SWT.Modify),
                PojoProperties.value(ExportConnectorWizardPage.class, "destFileName").observe(this), nameStrategy, null);

        pageSupport = WizardPageSupport.create(this, context);

        setControl(composite);
    }

    protected void filterViewer(final TableViewer tableViewer, final String text) {

    }

    protected void selectDirectory(final Text destFileText, final String defualtFileName) {
        final DirectoryDialog directoryDialog = new DirectoryDialog(getShell(), SWT.SAVE);
        //  directoryDialog.setFilterExtensions(new String[] { "*.zip" });
        if (destFileText.getText() != null && !destFileText.getText().isEmpty()) {
            final File beforeFile = new File(destFileText.getText());
            directoryDialog.setFilterPath(beforeFile.getParent());
            //   directoryDialog.setFileName(beforeFile.getName());
        } else {
            directoryDialog.setFilterPath(System.getProperty("user.home"));
            //  directoryDialog.setFileName(defualtFileName) ;
        }
        final String res = directoryDialog.open();
        if (res != null) {
            //   if (!res.endsWith(".zip")) {
            //       res += ".zip";
            //   }
            destFileText.setText(res);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        if (context != null) {
            context.dispose();
        }
        if (pageSupport != null) {
            pageSupport.dispose();
        }
    }

    @Override
    public void selectionChanged(final SelectionChangedEvent event) {
        final Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
        if (destFileNameText != null) {
            destFileNameText.setText(generateDefaultName());
        }
        if (removeButton != null && selection instanceof ConnectorImplementation) {
            removeButton.setEnabled(true);
        }
    }

    @Override
    public void doubleClick(final DoubleClickEvent event) {
        final Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
        if (selection instanceof ConnectorImplementation) {
            if (getNextPage() != null) {
                getContainer().showPage(getNextPage());
            } else {
                final IWizard wizard = getWizard();
                if (wizard.canFinish() && wizard.performFinish()) {
                    ((WizardDialog) getContainer()).close();
                }
            }
        }
    }

    public ConnectorImplementation getSelectedImplementation() {
        return selectedImplementation;
    }

    public void setSelectedImplementation(final ConnectorImplementation selectedImplementation) {
        this.selectedImplementation = selectedImplementation;
    }

    public String getDestFilePath() {
        return destFilePath;
    }

    public void setDestFilePath(final String destFilePath) {
        this.destFilePath = destFilePath;
    }

    public boolean isIncludeSources() {
        return includeSources;
    }

    public void setIncludeSources(final boolean includeSources) {
        this.includeSources = includeSources;
    }

    public boolean isAddDependencies() {
        return addDependencies;
    }

    public void setAddDependencies(final boolean addDependencies) {
        this.addDependencies = addDependencies;
    }

    public String getDestFileName() {
        return destFileName;
    }

    public void setDestFileName(final String destFileName) {
        this.destFileName = destFileName;
    }

    private String generateDefaultName() {
        final ConnectorImplementation impl = (ConnectorImplementation) ((IStructuredSelection) tableViewer.getSelection())
                .getFirstElement();
        String defaultName = "";
        if (impl != null) {
            defaultName = NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(),
                    impl.getImplementationVersion(), false) + ".zip";
        }
        return defaultName;
    }

}
