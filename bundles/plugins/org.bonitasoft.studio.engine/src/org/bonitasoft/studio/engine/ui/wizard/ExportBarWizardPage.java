/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.engine.ui.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.ValidationDialog;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.engine.operation.ExportBarOperation;
import org.bonitasoft.studio.engine.operation.ProcessValidationOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.validation.MultiValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class ExportBarWizardPage extends WizardPage implements ICheckStateListener, ICheckStateProvider {

    private final static String STORE_DESTINATION_NAMES_ID = "ExportBarWizardPage.STORE_DESTINATION_NAMES_ID"; //$NON-NLS-1$

    private final static int COMBO_HISTORY_LENGTH = 5;

    private String configurationId;
    protected DataBindingContext dbc;

    private Combo destinationCombo;
    private Set<AbstractProcess> selectedProcess = new HashSet<>();
    private String detinationPath;
    private final ComposedAdapterFactory adapterFactory;
    private Button destinationBrowseButton;
    private WizardPageSupport pageSupport;
    private CheckboxTreeViewer viewer;

    private Button noneButton;

    protected ExportBarWizardPage() {
        super(ExportBarWizardPage.class.getName());
        setTitle(Messages.buildTitle);
        setDescription(Messages.buildDesc);
        adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        final String confId = ConfigurationPlugin.getDefault().getPreferenceStore()
                .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION);
        setConfigurationId(confId);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        dbc = new DataBindingContext();

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 10, 5, 5).create());

        createProcessViewer(mainComposite);

        final Composite group = new Composite(mainComposite, SWT.NONE);
        group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        group.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).create());

        createConfiguration(group);
        createDestination(group);

        pageSupport = WizardPageSupport.create(this, dbc);
        setControl(mainComposite);
    }

    protected void createProcessViewer(final Composite mainComposite) {
        final Label processLabel = new Label(mainComposite, SWT.WRAP);
        processLabel.setText(Messages.selectProcessToExport);

        final Text serachBox = new Text(mainComposite, SWT.BORDER | SWT.SEARCH | SWT.ICON_CANCEL | SWT.ICON_SEARCH);
        serachBox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        serachBox.setMessage(Messages.searchProcess);

        viewer = new CheckboxTreeViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.addCheckStateListener(this);
        viewer.setCheckStateProvider(this);
        viewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                final String searchQuery = serachBox.getText();
                if (searchQuery != null && !searchQuery.isEmpty()) {
                    return queryElementAndChildren(searchQuery, element);
                }
                return true;
            }
        });
        viewer.setLabelProvider(new ColumnLabelProvider() {

            private final AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(adapterFactory);

            @Override
            public org.eclipse.swt.graphics.Image getImage(final Object element) {
                return labelProvider.getImage(element);
            }

            @Override
            public String getText(final Object element) {
                return ((AbstractProcess) element).getName() + " (" + ((AbstractProcess) element).getVersion() + ")";
            }

        });
        viewer.setContentProvider(new AbstractProcessContentProvider());
        viewer.setInput(new Object());

        final IObservableSet checkedElementsObservable = ViewersObservables.observeCheckedElements(viewer,
                AbstractProcess.class);
        final MultiValidator notEmptyValidator = new MultiValidator() {

            @Override
            protected IStatus validate() {
                final Iterator it = checkedElementsObservable.iterator();
                while (it.hasNext()) {
                    if (it.next() instanceof Pool) {
                        return ValidationStatus.ok();
                    }

                }
                return ValidationStatus.error(Messages.selectAtLeastOneProcess);
            }
        };

        dbc.addValidationStatusProvider(notEmptyValidator);
        dbc.bindSet(notEmptyValidator.observeValidatedSet(checkedElementsObservable),
                PojoObservables.observeSet(this, "selectedProcess"));
        serachBox.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                viewer.refresh();
            }
        });
    }

    protected boolean queryElementAndChildren(final String searchQuery, final Object element) {
        final String processName = ((AbstractProcess) element).getName();
        final String processVersion = ((AbstractProcess) element).getVersion();
        if (processName != null && processName.toLowerCase().contains(searchQuery.toLowerCase())
                || processVersion != null && processVersion.toLowerCase().contains(searchQuery.toLowerCase())) {
            Display.getDefault().asyncExec(new Runnable() {

                @Override
                public void run() {
                    viewer.expandAll();
                }
            });
            return true;
        }
        final ITreeContentProvider contentProvider = (ITreeContentProvider) viewer.getContentProvider();
        if (contentProvider.hasChildren(element)) {
            for (final Object child : contentProvider.getChildren(element)) {
                if (queryElementAndChildren(searchQuery, child)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected void createConfiguration(final Composite parent) {
        final Label configuration = new Label(parent, SWT.NONE);
        configuration.setText(Messages.configuration);
        configuration.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        final Composite radioComposite = new Composite(parent, SWT.NONE);
        radioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());

        noneButton = new Button(radioComposite, SWT.RADIO);
        noneButton.setText(Messages.none);
        final Button configButton = new Button(radioComposite, SWT.RADIO);

        final ComboViewer configurationCombo = new ComboViewer(radioComposite, SWT.READ_ONLY | SWT.BORDER);
        configurationCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        configurationCombo.setContentProvider(new EnvironmentContentProvider());
        configurationCombo.setLabelProvider(new LabelProvider());
        configurationCombo.setInput(new Object());
        configurationCombo.getCombo().setEnabled(true);
        configButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                configurationCombo.getCombo().setEnabled(configButton.getSelection());
            }
        });

        noneButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                configurationCombo.getCombo().setEnabled(configButton.getSelection());
            }
        });
        noneButton.setSelection(false);

        dbc.bindValue(ViewersObservables.observeSingleSelection(configurationCombo),
                PojoProperties.value(ExportBarWizardPage.class, "configurationId").observe(this));
        configButton.setSelection(true);
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
        pathStrategy.setBeforeSetValidator(new EmptyInputValidator(Messages.destinationPath));
        dbc.bindValue(SWTObservables.observeText(destinationCombo),
                PojoProperties.value(ExportBarWizardPage.class, "detinationPath").observe(this),
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
                setDetinationPath(System.getProperty("user.home"));
                return; // ie.- no settings stored
            }

            // destination
            setDetinationPath(directoryNames[0]);
            for (int i = 0; i < directoryNames.length; i++) {
                addDestinationItem(directoryNames[i]);
            }
        }
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

            directoryNames = addToHistory(directoryNames, getDetinationPath());
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

    /*
     * Implements method from IJarPackageWizardPage.
     */
    public IStatus finish(ExportBarOperation operation) throws InvocationTargetException, InterruptedException {
        saveWidgetValues();

        if (!validateBeforeExport(getSelectedProcess())) {
            return Status.CANCEL_STATUS;
        }

        operation.setTargetFolder(getDetinationPath());
        if (noneButton != null && noneButton.getSelection()) {
            operation.setConfigurationId(null);
        } else {
            operation.setConfigurationId(getConfigurationId());
        }
        for (AbstractProcess process : getSelectedProcess()) {
            if (!(process instanceof MainProcess)) {
                operation.addProcessToDeploy(process);
            }
        }
        getContainer().run(true, false, new IRunnableWithProgress() {

            @Override
            public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                operation.run(monitor);
            }
        });
        return operation.getStatus();
    }

    /**
     *
     */
    protected boolean validateBeforeExport(final Set<AbstractProcess> selectedList) {
        ProcessValidationOperation processValidationOperation = new ProcessValidationOperation(selectedList);
        processValidationOperation.run();
        IStatus status = processValidationOperation.getStatus();
        if (status.getSeverity() == IStatus.ERROR || status.getSeverity() == IStatus.WARNING) {
            final StringBuilder report = new StringBuilder("");
            final List<String> alreadyInReport = new ArrayList<>(selectedList.size());
            for (final IStatus s : status.getChildren()) {
                if(s.getMessage() != null && s.getMessage().indexOf(":") != -1) {
                    final String fileName = s.getMessage().substring(0, s.getMessage().indexOf(":"));
                    if (!alreadyInReport.contains(fileName)) {
                        report.append(fileName);
                        report.append("\n");
                        alreadyInReport.add(fileName);
                    }
                }
            }
            if (!FileActionDialog.getDisablePopup()) {
                String generalMessage = status.getSeverity() == IStatus.ERROR ? Messages.errorValidationInDiagramToExport
                        : Messages.warningValidationInDiagramToExport;
                String errorMessage = String.format("%s\n%s%s", generalMessage, report,
                        Messages.errorValidationContinueAnywayMessage);
                return new ValidationDialog(Display.getDefault().getActiveShell(),
                        Messages.validationFailedTitle, errorMessage,
                        ValidationDialog.YES_NO).open() != ValidationDialog.NO;
            }
        }
        return true;
    }

    @Override
    public void dispose() {
        super.dispose();
        if (adapterFactory != null) {
            adapterFactory.dispose();
        }
        if (pageSupport != null) {
            pageSupport.dispose();
        }
        if (dbc != null) {
            dbc.dispose();
        }

    }

    public Set<AbstractProcess> getSelectedProcess() {
        return selectedProcess;
    }

    public void setSelectedProcess(final Set<AbstractProcess> selectedProcess) {
        this.selectedProcess = selectedProcess;
    }

    public String getDetinationPath() {
        return detinationPath;
    }

    public void setDetinationPath(final String detinationPath) {
        this.detinationPath = detinationPath;
    }

    public String getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(final String configurationId) {
        this.configurationId = configurationId;
    }

    @Override
    public void checkStateChanged(final CheckStateChangedEvent event) {
        final Object element = event.getElement();
        if (element instanceof MainProcess) {
            viewer.setGrayChecked(element, false);
            viewer.setChecked(element, event.getChecked());
            for (final AbstractProcess proc : ModelHelper.getAllProcesses((Element) element)) {
                viewer.setChecked(proc, event.getChecked());
                if (event.getChecked()) {
                    selectedProcess.add(proc);
                } else {
                    selectedProcess.remove(proc);
                }
            }
        }
        if (element instanceof Pool) {
            final MainProcess diagram = ModelHelper.getMainProcess((EObject) element);
            viewer.setGrayChecked(diagram, false);
            if (isGrayed(diagram)) {
                viewer.setGrayChecked(diagram, true);
            } else {
                viewer.setChecked(diagram, event.getChecked());
            }
        }
    }

    @Override
    public boolean isChecked(final Object element) {
        return selectedProcess.contains(element);
    }

    @Override
    public boolean isGrayed(final Object element) {
        if (element instanceof MainProcess) {
            boolean atLeastOneNotContainded = false;
            boolean atLeastOneContainded = false;
            for (final AbstractProcess prc : ModelHelper.getAllProcesses((Element) element)) {
                if (!viewer.getChecked(prc)) {
                    atLeastOneNotContainded = true;
                }
                if (viewer.getChecked(prc)) {
                    atLeastOneContainded = true;
                }
            }
            return atLeastOneContainded && atLeastOneNotContainded;
        }
        return false;
    }

}
