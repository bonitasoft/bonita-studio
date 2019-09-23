/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.ui.wizard;

import static org.bonitasoft.studio.common.Messages.bonitaStudioModuleName;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.mandatoryValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.pathValidator;

import java.io.File;
import java.util.List;

import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.ImporterPriorityDisplayComparator;
import org.bonitasoft.studio.importer.ImporterRegistry;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ImportFileWizardPage extends WizardPage {

    private static final String LAST_IMPORT_PATH = null;
    private final ImportFileData importFileData;

    protected ImportFileWizardPage(ImportFileData importFileData) {
        super(ImportFileWizardPage.class.getName());
        setTitle(Messages.importFileTitle);
        setDescription(Messages.bind(Messages.importFileDescription, new Object[] { bonitaStudioModuleName }));
        setImageDescriptor(Pics.getWizban());
        this.importFileData = importFileData;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        final DataBindingContext dbc = new DataBindingContext();
        final Composite mainComposite = doCreateControls(parent, dbc);
        setControl(mainComposite);
        WizardPageSupport.create(this, dbc);
    }

    protected Composite doCreateControls(Composite parent, DataBindingContext dbc) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).extendedMargins(10, 10, 10, 0).create());

        final Group transfoGroup = new Group(mainComposite, SWT.NONE);
        transfoGroup.setText(Messages.fileFormat);
        transfoGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(3, 1).create());
        transfoGroup.setLayout(new GridLayout(2, true));

        final TableViewer importList = new TableViewer(transfoGroup, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        importList.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        importList.setContentProvider(ArrayContentProvider.getInstance());
        importList.setComparator(new ImporterPriorityDisplayComparator());
        importList.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(Object item) {
                return ((ImporterFactory) item).getName();
            }
        });

        final List<ImporterFactory> allAvailableImports = ImporterRegistry.getInstance().getAllAvailableImports();
        importList.setInput(allAvailableImports);

        final IObservableValue importerFactoryObservable = PojoObservables.observeValue(importFileData, "importerFactory");
        importerFactoryObservable.setValue(importList.getElementAt(0));

        dbc.bindValue(ViewersObservables.observeSingleSelection(importList), importerFactoryObservable,
                updateValueStrategy().withValidator(mandatoryValidator(Messages.fileFormat)).create(),
                updateValueStrategy().create());

        final Composite descComposite = new Composite(transfoGroup, SWT.NONE);
        descComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(5, 3).create());
        descComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        final Label importDescriptionLabel = new Label(descComposite, SWT.NONE);
        importDescriptionLabel.setText(Messages.importDescriptionLabel);

        final Label descriptionImage = new Label(descComposite, SWT.NONE);
        descriptionImage.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());

        final Label separator = new Label(descComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final Label descriptionLabel = new Label(descComposite, SWT.WRAP);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().hint(230, SWT.DEFAULT).grab(false, true).create());

        updatePanel(importFileData.getImporterFactory(), descriptionImage, descriptionLabel);

        doCreateAdditionalControls(mainComposite, dbc);

        final Label fileLabel = new Label(mainComposite, SWT.NONE);
        fileLabel.setText(Messages.selectFileToImport);

        final Text text = new Text(mainComposite, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        final IObservableValue filePathObservable = PojoObservables.observeValue(importFileData, "filePath");
        dbc.bindValue(SWTObservables.observeText(text, SWT.Modify), filePathObservable,
                updateValueStrategy().withValidator(pathValidator(Messages.selectFileToImport).overrideMessage(Messages.invalidFilePath)).create(),
                updateValueStrategy().create());

        final Button browseButton = new Button(mainComposite, SWT.PUSH);
        browseButton.setText(Messages.browseButton_label);
        browseButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final String file = openFileDialog();
                if (file != null) {
                    text.setText(file);
                    if (new File(file).exists()) {
                        savePath(new File(file).getParentFile().getAbsolutePath());
                    }
                }
            }
        });

        importList.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                updatePanel((ImporterFactory) importerFactoryObservable.getValue(), descriptionImage, descriptionLabel);
            }

        });
        importList.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent arg0) {
                updatePanel((ImporterFactory) importerFactoryObservable.getValue(), descriptionImage, descriptionLabel);
                browseButton.notifyListeners(SWT.Selection, null);
            }
        });

        return mainComposite;
    }

    protected void doCreateAdditionalControls(Composite mainComposite, DataBindingContext dbc) {

    }

    protected void updatePanel(final ImporterFactory importerFactory, final Label descriptionImage, final Label descriptionLabel) {
        descriptionLabel.setText(importerFactory.getDescription());
        descriptionImage.setImage(importerFactory.getImageDescription());
        descriptionImage.getParent().getParent().layout(true, true);
        descriptionLabel.redraw();
    }

    protected String openFileDialog() {
        final FileDialog fd = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
        fd.setText(Messages.importProcessTitle);

        fd.setFilterPath(getLastPath());

        final String filterExtensions = importFileData.getImporterFactory().getFilterExtensions();

        final String[] filterExt = filterExtensions.split(",");
        fd.setFilterExtensions(filterExt);
        return fd.open();
    }

    private String getLastPath() {
        String path = ImporterPlugin.getDefault().getDialogSettings().get(LAST_IMPORT_PATH);
        if (path == null || !new File(path).exists()) {
            path = System.getProperty("user.home");
        }
        return path;
    }

    private void savePath(String path) {
        ImporterPlugin.getDefault().getDialogSettings().put(LAST_IMPORT_PATH, path);
    }

}
