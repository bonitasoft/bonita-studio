/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.editor.EditorUtil;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.operation.ExportBosArchiveOperation;
import org.bonitasoft.studio.common.repository.ui.viewer.CheckboxRepositoryTreeViewer;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Section;

/**
 * @author Romain Bioteau
 */
public class ExportRepositoryWizardPage extends WizardPage {

    public static final String FILE_EXTENSION = "bos";

    private static final String STORE_DESTINATION_NAMES_ID = "ExportRepositoryWizardPage.STORE_DESTINATION_NAMES_ID";

    private static final int COMBO_HISTORY_LENGTH = 5;

    private String detinationPath;

    private CheckboxRepositoryTreeViewer treeViewer;
    private final List<IRepositoryStore<? extends IRepositoryFileStore>> stores;
    private final boolean isZip;

    private DataBindingContext dbc;
    private Button destinationBrowseButton;
    private Combo destinationCombo;

    private final String defaultFileName;
    private ViewerFilter[] filters = {};

    private Set<Object> defaultSelectedFiles = new HashSet<>();

    public ExportRepositoryWizardPage(final List<IRepositoryStore<? extends IRepositoryFileStore>> stores,
            final boolean isZip, final String defaultFileName) {
        super(ExportRepositoryWizardPage.class.getName());
        this.isZip = isZip;
        this.defaultFileName = defaultFileName;
        if (isZip) {
            setDescription(Messages.exportArtifactsWizard_desc);
        } else {
            setDescription(Messages.exportArtifactsWizard_desc_toFile);
        }
        this.stores = stores;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        dbc = new DataBindingContext();

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setLayout(new GridLayout(3, false));

        final Section browseRepoSection = new Section(composite, Section.NO_TITLE_FOCUS_BOX | Section.TWISTIE);
        browseRepoSection.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        browseRepoSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(3, 1).create());

        browseRepoSection.setText(Messages.browseRepository);
        browseRepoSection.addExpansionListener(new IExpansionListener() {

            @Override
            public void expansionStateChanging(final ExpansionEvent event) {
            }

            @Override
            public void expansionStateChanged(final ExpansionEvent event) {
                browseRepoSection.setLayoutData(
                        GridDataFactory.fillDefaults().grab(true, browseRepoSection.isExpanded()).span(3, 1).create());
                final Point defaultSize = getShell().getSize();
                final Point size = getShell().computeSize(SWT.DEFAULT, 500, true);
                getShell().setSize(defaultSize.x, size.y);
                getShell().layout(true, true);

            }
        });
        browseRepoSection.setExpanded(true);
        browseRepoSection.setClient(createViewer(browseRepoSection));
        createDestination(composite);
        WizardPageSupport.create(this, dbc);
        setControl(composite);
    }

    protected Control createViewer(final Composite composite) {
        final Composite treeComposite = new Composite(composite, SWT.NONE);
        treeComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        treeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        treeViewer = new CheckboxRepositoryTreeViewer(treeComposite);
        for (final ViewerFilter filter : filters) {
            treeViewer.addFilter(filter);
        }
        treeViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(400, SWT.DEFAULT).create());
        treeViewer.bindTree(dbc, stores);
        treeViewer.setCheckedElements(defaultSelectedFiles.toArray());

        createButtonsComposite(treeComposite, treeViewer.checkedElementsObservable());

        return treeComposite;
    }

    private void createButtonsComposite(final Composite treeComposite,
            final IObservableSet checkedElementsObservable) {
        final Composite buttonsComposite = new Composite(treeComposite, SWT.NONE);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).create());

        final Button selectAllButton = new Button(buttonsComposite, SWT.FLAT);
        selectAllButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        selectAllButton.setText(Messages.selectAll);
        selectAllButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                checkedElementsObservable.clear();
                final ITreeContentProvider provider = (ITreeContentProvider) treeViewer.getContentProvider();
                checkedElementsObservable.addAll(Arrays.asList(provider.getElements(stores)));
            }
        });

        final Button deSelectAllButton = new Button(buttonsComposite, SWT.FLAT);
        deSelectAllButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        deSelectAllButton.setText(Messages.deselectAll);
        deSelectAllButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                checkedElementsObservable.clear();
            }
        });
    }

    private Set<IRepositoryFileStore> getSelectedFileStores() {
        final Set<IRepositoryFileStore> checkedArtifacts = new HashSet<>();
        for (final Object element : treeViewer.checkedElementsObservable()) {
            if (element instanceof IRepositoryFileStore) {
                checkedArtifacts.add((IRepositoryFileStore) element);
                checkedArtifacts.addAll(((IRepositoryFileStore) element).getRelatedFileStore());
            }
        }
        return checkedArtifacts;
    }

    public boolean finish() {
        saveWidgetValues();
        boolean exportSuccessful = isZip
                ? performFinishForZipExport()
                : performFinishForNotZipExport();
        if (exportSuccessful) {
            MessageDialog.openInformation(getContainer().getShell(), Messages.exportLabel,
                    Messages.bind(Messages.exportFinishMessage, getDetinationPath()));
        }
        return exportSuccessful;
    }

    protected boolean performFinishForNotZipExport() {
        try {
            MultiStatus status = new MultiStatus(CommonRepositoryPlugin.PLUGIN_ID, 0, "", null);
            getContainer().run(false, false, monitor -> {
                Set<IRepositoryFileStore> selectedFileStores = getSelectedFileStores();
                monitor.beginTask(Messages.exporting, selectedFileStores.size());
                File dest = new File(getDetinationPath());
                if (!dest.exists()) {
                    dest.mkdirs();
                }
                for (IRepositoryFileStore file : selectedFileStores) {
                    if (file.getResource() != null && file.getResource().exists()) {
                        try {
                            status.add(file.export(dest.getAbsolutePath()));
                        } catch (IOException e) {
                            throw new InvocationTargetException(e);
                        }
                        monitor.worked(1);
                    }
                }
            });

            if (Objects.equals(status.getSeverity(), ValidationStatus.ERROR)) {
                MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.exportFailed,
                        getErrorMessages(status));
                return false;
            }
            return !allExportCancel(status);
        } catch (InterruptedException | InvocationTargetException e) {
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.exportFailed, e.getCause().getMessage());
            return false;
        }
    }

    private boolean allExportCancel(MultiStatus multiStatus) {
        return Stream.of(multiStatus.getChildren())
                .allMatch(status -> Objects.equals(status.getSeverity(), ValidationStatus.CANCEL));
    }

    private String getErrorMessages(MultiStatus multiStatus) {
        return Stream.of(multiStatus.getChildren())
                .filter(status -> Objects.equals(status.getSeverity(), ValidationStatus.ERROR))
                .map(IStatus::getMessage)
                .collect(Collectors.joining("\n"));
    }

    protected boolean performFinishForZipExport() {
        ExportBosArchiveOperation operation = createExportBOSOperation();
        try {
            getContainer().run(false, true, operation::run);
        } catch (InterruptedException | InvocationTargetException e) {
            BonitaStudioLog.error(e);
            MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.exportFailed, e.getCause().getMessage());
            return false;
        }

        if (!operation.getStatus().isOK()) {
            ErrorDialog.openError(Display.getDefault().getActiveShell(), Messages.exportFailed, null, operation.getStatus());
            return false;
        }
        return true;
    }

    protected ExportBosArchiveOperation createExportBOSOperation() {
        final ExportBosArchiveOperation operation = new ExportBosArchiveOperation();
        operation.setDestinationPath(getDetinationPath());
        operation.setFileStores(getSelectedFileStores());
        try {
            final Set<IResource> toOpen = new HashSet<>();
            for (final IEditorReference ref : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .getEditorReferences()) {
                final IFile file = (IFile) EditorUtil.retrieveResourceFromEditorInput(ref.getEditorInput());
                if (operation.getResources().contains(file)) {
                    toOpen.add(file);
                }
            }
            operation.setResourcesToOpen(toOpen);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }

        return operation;
    }

    protected void createDestination(final Composite group) {
        final Label destPath = new Label(group, SWT.NONE);
        destPath.setText(Messages.destinationPath + " *");
        destPath.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        // destination name entry field
        destinationCombo = new Combo(group, SWT.SINGLE | SWT.BORDER);
        destinationCombo
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create());

        restoreWidgetValues();
        final UpdateValueStrategy pathStrategy = new UpdateValueStrategy();
        pathStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.destinationPath));
        pathStrategy.setBeforeSetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object input) {
                if (!isZip) {
                    if (!new File(input.toString()).isDirectory()) {
                        return ValidationStatus.error(Messages.destinationPathMustBeADirectory);
                    }
                } else {
                    if (!input.toString().endsWith(".bos")) {
                        return ValidationStatus.error(Messages.invalidFileFormat);
                    }
                    if (new File(input.toString()).isDirectory()) {
                        return ValidationStatus.error(Messages.invalidFileFormat);
                    }
                }
                return ValidationStatus.ok();
            }
        });

        dbc.bindValue(SWTObservables.observeText(destinationCombo),
                PojoProperties.value(ExportRepositoryWizardPage.class, "detinationPath").observe(this),
                pathStrategy, null);

        // destination browse button
        destinationBrowseButton = new Button(group, SWT.PUSH);
        destinationBrowseButton.setText(Messages.browse);
        destinationBrowseButton.setLayoutData(GridDataFactory.fillDefaults().hint(85, SWT.DEFAULT).create());
        destinationBrowseButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                handleDestinationBrowseButtonPressed();
            }
        });
    }

    /**
     * Open an appropriate destination browser so that the user can specify a source
     * to import from
     */
    protected void handleDestinationBrowseButtonPressed() {
        if (isZip) {
            final FileDialog dialog = new FileDialog(getContainer().getShell(), SWT.SAVE | SWT.SHEET);
            // dialog.setFilterExtensions(new String[] { "*.bar" }); //$NON-NLS-1$
            dialog.setText(Messages.selectDestinationTitle);
            final String currentSourceString = getDetinationPath();
            final int lastSeparatorIndex = currentSourceString.lastIndexOf(File.separator);
            if (lastSeparatorIndex != -1) {
                dialog.setFilterPath(currentSourceString.substring(0,
                        lastSeparatorIndex));
                final File f = new File(currentSourceString);
                if (!f.isDirectory()) {
                    dialog.setFileName(f.getName());
                }

                dialog.setFilterExtensions(new String[] { "*." + FILE_EXTENSION });
            }
            final String selectedFileName = dialog.open();

            if (selectedFileName != null) {
                destinationCombo.setText(selectedFileName);
            }
        } else {
            final DirectoryDialog dialog = new DirectoryDialog(getContainer().getShell(), SWT.SAVE | SWT.SHEET);
            // dialog.setFilterExtensions(new String[] { "*.bar" }); //$NON-NLS-1$
            dialog.setText(Messages.selectDestinationTitle);
            final String currentSourceString = getDetinationPath();
            final int lastSeparatorIndex = currentSourceString.lastIndexOf(File.separator);
            if (lastSeparatorIndex != -1) {
                dialog.setFilterPath(currentSourceString.substring(0,
                        lastSeparatorIndex));
            }
            final String selectedFileName = dialog.open();

            if (selectedFileName != null) {
                destinationCombo.setText(selectedFileName);
            }
        }

    }

    /**
     * Hook method for restoring widget values to the values that they held
     * last time this wizard was used to completion.
     */
    protected void restoreWidgetValues() {
        final IDialogSettings settings = getDialogSettings();
        if (settings != null) {
            final String[] directoryNames = settings
                    .getArray(STORE_DESTINATION_NAMES_ID);
            if (directoryNames == null || directoryNames.length == 0) {
                String path = System.getProperty("user.home");
                if (defaultFileName != null && isZip) {
                    path = path + File.separator + defaultFileName;
                }
                setDetinationPath(path);
                return; // ie.- no settings stored
            }

            // destination

            String oldPath = directoryNames[0];
            if (defaultFileName != null && isZip) {
                final File f = new File(oldPath);
                if (f.isFile()) {
                    oldPath = f.getParentFile().getAbsolutePath() + File.separator + defaultFileName;
                } else {
                    oldPath = oldPath + File.separator + defaultFileName;
                }

            } else if (!isZip) {
                final File f = new File(oldPath);
                if (f.isFile()) {
                    oldPath = f.getParentFile().getAbsolutePath();
                }
            }
            setDetinationPath(oldPath);
            for (int i = 0; i < directoryNames.length; i++) {
                addDestinationItem(directoryNames[i]);
            }
        }
    }

    public void setViewerFilter(final ViewerFilter[] filters) {
        this.filters = filters;
    }

    /**
     * Hook method for saving widget values for restoration by the next instance
     * of this class.
     */
    protected void saveWidgetValues() {
        // update directory names history
        final IDialogSettings settings = getDialogSettings();
        if (settings != null) {
            String[] directoryNames = settings
                    .getArray(STORE_DESTINATION_NAMES_ID);
            if (directoryNames == null) {
                directoryNames = new String[0];
            }

            String dest = getDetinationPath();
            if (dest.endsWith(".bos")) {
                dest = new File(dest).getParentFile().getAbsolutePath();
            }
            directoryNames = addToHistory(directoryNames, dest);
            settings.put(STORE_DESTINATION_NAMES_ID, directoryNames);
        }
    }

    /**
     * Adds an entry to a history, while taking care of duplicate history items
     * and excessively long histories. The assumption is made that all histories
     * should be of length <code>WizardDataTransferPage.COMBO_HISTORY_LENGTH</code>.
     *
     * @param history the current history
     * @param newEntry the entry to add to the history
     */
    protected String[] addToHistory(final String[] history, final String newEntry) {
        final java.util.ArrayList l = new java.util.ArrayList(Arrays.asList(history));
        addToHistory(l, newEntry);
        final String[] r = new String[l.size()];
        l.toArray(r);
        return r;
    }

    /**
     * Adds an entry to a history, while taking care of duplicate history items
     * and excessively long histories. The assumption is made that all histories
     * should be of length <code>WizardDataTransferPage.COMBO_HISTORY_LENGTH</code>.
     *
     * @param history the current history
     * @param newEntry the entry to add to the history
     */
    protected void addToHistory(final List history, final String newEntry) {
        history.remove(newEntry);
        history.add(0, newEntry);

        // since only one new item was added, we can be over the limit
        // by at most one item
        if (history.size() > COMBO_HISTORY_LENGTH) {
            history.remove(COMBO_HISTORY_LENGTH);
        }
    }

    /**
     * Add the passed value to self's destination widget's history
     *
     * @param value java.lang.String
     */
    protected void addDestinationItem(final String value) {
        destinationCombo.add(value);
    }

    public String getDetinationPath() {
        return detinationPath;
    }

    public void setDetinationPath(final String detinationPath) {
        this.detinationPath = detinationPath;
    }

    public void setDefaultSelectedFiles(final Set<Object> defaultSelectedFiles) {
        this.defaultSelectedFiles = defaultSelectedFiles;
    }

}
