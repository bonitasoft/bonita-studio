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
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.document.ui.editingSupport.AdditionalResourcesFileEditingSupport;
import org.bonitasoft.studio.document.ui.validator.AdditionalResourceProjectPathValidator;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.Resource;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
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

public class AdditionalResourcesConfigurationWizardPage extends WizardPage implements IProcessConfigurationWizardPage {

    private TableViewer viewer;
    private IValidator<Resource> projectPathValidator;

    public AdditionalResourcesConfigurationWizardPage() {
        super(AdditionalResourcesConfigurationWizardPage.class.getName());
        setTitle(Messages.additionalResources);
        setDescription(Messages.additionalResourcesDesc);
        projectPathValidator = new AdditionalResourceProjectPathValidator();
    }

    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label description = new Label(composite, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        description.setText(Messages.additionalResourcesDesc);

        Label liveUpdateWarning = new Label(composite, SWT.WRAP);
        liveUpdateWarning.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        liveUpdateWarning.setText(Messages.additionalResourcesLiveUpdateWarning);

        createViewer(composite);

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
                .createColumnLabelProvider());
    }

    @Override
    public void updatePage(AbstractProcess process, Configuration configuration) {
        if (process != null && configuration != null && viewer != null && !viewer.getTable().isDisposed()) {
            viewer.setInput(configuration.getAdditionalResources());
        }
    }

    @Override
    public String isConfigurationPageValid(Configuration conf) {
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
