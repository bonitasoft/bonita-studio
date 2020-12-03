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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.RepositoryNameValidator;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.ImporterPriorityDisplayComparator;
import org.bonitasoft.studio.importer.ImporterRegistry;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileData.RepositoryMode;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
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
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).extendedMargins(10, 10, 10, 0).create());

        final Group transfoGroup = new Group(mainComposite, SWT.NONE);
        transfoGroup.setText(Messages.fileFormat);
        transfoGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(3, 1).create());
        transfoGroup.setLayout(new GridLayout(2, true));

        final TableViewer importList = new TableViewer(transfoGroup,
                SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
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

        doCreateTargetRepositorySection(mainComposite, dbc);

        final Label fileLabel = new Label(mainComposite, SWT.NONE);
        fileLabel.setText(Messages.selectFileToImport);

        final Text text = new Text(mainComposite, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

        final IObservableValue filePathObservable = PojoObservables.observeValue(importFileData, "filePath");
        dbc.bindValue(SWTObservables.observeText(text, SWT.Modify), filePathObservable,
                updateValueStrategy()
                        .withValidator(pathValidator(Messages.selectFileToImport).overrideMessage(Messages.invalidFilePath))
                        .create(),
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

    protected void doCreateTargetRepositorySection(Composite mainComposite, DataBindingContext dbc) {
        final Group importInto = new Group(mainComposite, SWT.NONE);
        importInto.setText(Messages.targetRepository);
        importInto.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        importInto.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).grab(true, false).create());

        final Composite radioGroup = new Composite(importInto, SWT.NONE);
        radioGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(5, 5).create());
        radioGroup.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());

        final Button currentRepositoryButton = new Button(radioGroup, SWT.RADIO);
        currentRepositoryButton.setText(
                String.format(Messages.currentRepository, RepositoryManager.getInstance().getCurrentRepository().getName()));
        currentRepositoryButton.setLayoutData(GridDataFactory.fillDefaults().create());

        final Button newRepositoryButton = new Button(radioGroup, SWT.RADIO);
        newRepositoryButton.setText(Messages.aNewRepository);
        newRepositoryButton.setLayoutData(GridDataFactory.fillDefaults().create());

        final Button existingRepositoryButton = new Button(radioGroup, SWT.RADIO);
        existingRepositoryButton.setText(Messages.anExistingRepository);
        existingRepositoryButton.setEnabled(RepositoryManager.getInstance().getAllRepositories().size() > 1);
        existingRepositoryButton.setLayoutData(GridDataFactory.fillDefaults().create());

        final SelectObservableValue selectObservableValue = new SelectObservableValue();
        selectObservableValue.addOption(RepositoryMode.CURRENT, SWTObservables.observeSelection(currentRepositoryButton));
        selectObservableValue.addOption(RepositoryMode.EXISTING, SWTObservables.observeSelection(existingRepositoryButton));
        selectObservableValue.addOption(RepositoryMode.NEW, SWTObservables.observeSelection(newRepositoryButton));

        final Composite stackComposite = new Composite(importInto, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
        final StackLayout stackLayout = new StackLayout();
        stackComposite.setLayout(stackLayout);

        currentRepositoryButton
                .addSelectionListener(updateStackLayout(stackComposite, stackLayout, createNameComposite(stackComposite)));

        existingRepositoryButton
                .addSelectionListener(
                        updateStackLayout(stackComposite, stackLayout, createSelectRepositoryControl(stackComposite, dbc)));

        IObservableValue<String> newRepositoryObservable = PojoProperties.<ImportFileData, String> value("newRepositoryName")
                .observe(importFileData);
        newRepositoryButton
                .addSelectionListener(
                        updateStackLayout(stackComposite, stackLayout,
                                createNewRepositoryNameControl(stackComposite, newRepositoryObservable, dbc)));

        dbc.bindValue(selectObservableValue, PojoProperties.<ImportFileData, String> value("mode")
                .observe(importFileData));

        currentRepositoryButton.notifyListeners(SWT.Selection, null);
        dbc.addValidationStatusProvider(new MultiValidator() {

            @Override
            protected IStatus validate() {
                final Object value = newRepositoryObservable.getValue();
                final RepositoryMode mode = (RepositoryMode) selectObservableValue.getValue();
                if (RepositoryMode.NEW == mode) {
                    return new RepositoryNameValidator().validate(value);
                }
                return ValidationStatus.ok();
            }
        });
    }

    private Composite createNameComposite(final Composite stackComposite) {
        final Composite nameComposite = new Composite(stackComposite, SWT.NONE);
        nameComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).extendedMargins(0, 0, 0, 10).create());

        final Label currentRepository = new Label(nameComposite, SWT.NONE);
        currentRepository.setText(
                NLS.bind(Messages.currentRepositoryName, RepositoryManager.getInstance().getCurrentRepository().getName()));
        currentRepository.setLayoutData(GridDataFactory.fillDefaults().indent(20, 0).create());

        return nameComposite;
    }

    private SelectionAdapter updateStackLayout(final Composite stackComposite, final StackLayout stackLayout,
            final Composite topControl) {
        return new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                stackLayout.topControl = topControl;
                stackComposite.layout();
            }

        };
    }

    private Composite createNewRepositoryNameControl(final Composite stackComposite,
            IObservableValue newRepositoryObservable, DataBindingContext dbc) {
        final Composite repositoryNameControl = new Composite(stackComposite, SWT.NONE);
        repositoryNameControl.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).extendedMargins(10, 10, 0, 10).create());

        final Label newRepoLabel = new Label(repositoryNameControl, SWT.NONE);
        newRepoLabel.setText(Messages.repositoryName);
        newRepoLabel.setLayoutData(GridDataFactory.swtDefaults().create());

        final Text newRepoNameText = new Text(repositoryNameControl, SWT.BORDER);
        newRepoNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        dbc.bindValue(WidgetProperties.text(SWT.Modify).observe(newRepoNameText), newRepositoryObservable);

        return repositoryNameControl;
    }

    private Composite createSelectRepositoryControl(final Composite stackComposite, DataBindingContext dbc) {
        final Composite parent = new Composite(stackComposite, SWT.NONE);
        parent.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).extendedMargins(10, 10, 0, 10).create());

        final Label newRepoLabel = new Label(parent, SWT.NONE);
        newRepoLabel.setText(Messages.selectRepository);
        newRepoLabel.setLayoutData(GridDataFactory.swtDefaults().create());

        final Combo repoCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
        repoCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final List<String> repositoties = new ArrayList<>();
        for (final IRepository r : RepositoryManager.getInstance().getAllRepositories()) {
            if (!Objects.equals(r.getName(), RepositoryManager.getInstance().getCurrentRepository().getName())) {
                repositoties.add(r.getName());
            }
        }
        repoCombo.setItems(repositoties.toArray(new String[] {}));
        repoCombo.setEnabled(!repositoties.isEmpty());

        final IObservableValue selectedRepository = PojoObservables.observeValue(importFileData, "selectedRepositoryName");
        if (!repositoties.isEmpty()) {
            selectedRepository.setValue(repositoties.get(0));
        }

        dbc.bindValue(SWTObservables.observeText(repoCombo), selectedRepository,
                updateValueStrategy().create(),
                updateValueStrategy().create());
        return parent;
    }

    protected void updatePanel(final ImporterFactory importerFactory, final Label descriptionImage,
            final Label descriptionLabel) {
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
