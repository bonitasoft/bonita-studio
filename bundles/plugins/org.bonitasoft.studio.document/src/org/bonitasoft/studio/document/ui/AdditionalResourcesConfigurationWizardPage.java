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
package org.bonitasoft.studio.document.ui;

import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.document.SelectDocumentInBonitaStudioRepository;
import org.bonitasoft.studio.document.core.repository.DocumentFileStore;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.document.ui.editingSupport.AdditionalResourcesFileEditingSupport;
import org.bonitasoft.studio.document.ui.validator.AdditionalResourceBarPathValidator;
import org.bonitasoft.studio.document.ui.validator.AdditionalResourceProjectPathValidator;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.Resource;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class AdditionalResourcesConfigurationWizardPage extends WizardPage implements IProcessConfigurationWizardPage {

    private DataBindingContext ctx;
    private TableViewer viewer;
    private Configuration configuration;
    private ToolItem deleteItem;
    private IViewerObservableValue<Resource> singleSelectionObservable;
    private IValidator<Resource> projectPathValidator;
    private AdditionalResourceBarPathValidator barPathValidator;

    public AdditionalResourcesConfigurationWizardPage() {
        super(AdditionalResourcesConfigurationWizardPage.class.getName());
        setTitle(Messages.additionalResources);
        setDescription(Messages.additionalResourcesDesc);
        ctx = new DataBindingContext();
        projectPathValidator = new AdditionalResourceProjectPathValidator();
        barPathValidator = new AdditionalResourceBarPathValidator();
    }

    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label description = new Label(composite, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        description.setText(Messages.additionalResourcesDesc);

        Composite viewerComposite = new Composite(composite, SWT.None);
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbar(viewerComposite);
        createViewer(viewerComposite);

        ctx.bindValue(WidgetProperties.enabled().observe(deleteItem), new ComputedValueBuilder()
                .withSupplier(() -> singleSelectionObservable.getValue() != null)
                .build());

        setControl(composite);
    }

    private void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        ColumnViewerToolTipSupport.enableFor(viewer);
        viewer.setUseHashlookup(true);
        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setLinesVisible(true);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTable().setLayout(layout);

        createNameColumn();
        createFileColumn();

        viewer.setContentProvider(new ArrayContentProvider());
        singleSelectionObservable = ViewerProperties.singleSelection(Resource.class).observe(viewer);
    }

    private void createFileColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.file);

        AdditionalResourceProjectPathValidator validator = new AdditionalResourceProjectPathValidator();

        column.setLabelProvider(new LabelProviderBuilder<Resource>()
                .withTextProvider(Resource::getProjectPath)
                .shouldRefreshAllLabels(viewer)
                .withStatusProvider(validator::validate)
                .createColumnLabelProvider());
        column.setEditingSupport(new AdditionalResourcesFileEditingSupport(viewer));
    }

    private void createNameColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.resourceName);

        column.setLabelProvider(new LabelProviderBuilder<Resource>()
                .withTextProvider(Resource::getBarPath)
                .shouldRefreshAllLabels(viewer)
                .withStatusProvider(barPathValidator::validate)
                .createColumnLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<Resource>(viewer)
                .withValueProvider(Resource::getBarPath)
                .withValueUpdater((resource, newName) -> resource.setBarPath((String) newName))
                .create());
    }

    private void createToolbar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL | SWT.LEFT | SWT.NO_FOCUS | SWT.FLAT);

        ToolItem addItem = new ToolItem(toolBar, SWT.PUSH);
        addItem.setImage(Pics.getImage("add.png"));
        addItem.addListener(SWT.Selection, e -> addItem());

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setImage(Pics.getImage("delete_icon.png"));
        deleteItem.addListener(SWT.Selection, e -> deleteItem());
    }

    private void deleteItem() {
        if (MessageDialog.openQuestion(getShell(), Messages.deleteAdditionalResourceConfirmTitle,
                String.format(Messages.deleteAdditionalResourceConfirm,
                        singleSelectionObservable.getValue().getBarPath()))) {
            configuration.getAdditionalResources().remove(singleSelectionObservable.getValue());
            viewer.refresh();
        }
    }

    private void addItem() {
        SelectDocumentInBonitaStudioRepository dialog = new SelectDocumentInBonitaStudioRepository(getShell());
        dialog.open();
        DocumentFileStore selectedDocument = dialog.getSelectedDocument();
        if (selectedDocument != null) {
            Resource resource = ConfigurationFactory.eINSTANCE.createResource();
            resource.setBarPath(selectedDocument.getName());
            resource.setProjectPath(selectedDocument.getResource().getProjectRelativePath().toString());
            configuration.getAdditionalResources().add(resource);
            viewer.refresh();
            viewer.editElement(resource, 1);
        }
    }

    @Override
    public void updatePage(AbstractProcess process, Configuration configuration) {
        if (process != null && configuration != null && viewer != null && !viewer.getTable().isDisposed()) {
            this.configuration = configuration;
            barPathValidator.setConfiguration(configuration);
            viewer.setInput(configuration.getAdditionalResources());
        }
    }

    @Override
    public String isConfigurationPageValid(Configuration conf) {
        boolean barPathMissing = conf.getAdditionalResources().stream()
                .anyMatch(resource -> barPathValidator.validate(resource).getSeverity() == IStatus.ERROR);
        if (barPathMissing) {
            return Messages.barPathInvalid;
        }
        boolean projectPathInvalid = conf.getAdditionalResources().stream()
                .anyMatch(resource -> projectPathValidator.validate(resource).getSeverity() == IStatus.ERROR);
        if (projectPathInvalid) {
            return Messages.projectPathInvalid;
        }
        return null;
    }

    @Override
    public Image getConfigurationImage() {
        return Pics.getImage("Document.gif");
    }

    @Override
    public boolean isDefault() {
        return false;
    }

}
