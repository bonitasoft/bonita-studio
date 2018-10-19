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
package org.bonitasoft.studio.configuration.ui.wizard;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.configuration.extension.IConfigurationExportAction;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IMessage;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 */
public class ConfigurationWizardDialog extends WizardDialog implements ISelectionChangedListener, IPageChangedListener {

    private static final String CONFIGURATION_WIZARD_DIALOG_SIMPLE_MODE = "ConfigurationWizardDialog_simpleMode";
    private static final String EXPORT_CONFIGURATION_ACTION_ID = "org.bonitasoft.studio.configuration.exportConfigurationAction";
    private static final String CLASS = "class";

    private Composite pageContainer;
    private ProgressMonitorPart progressMonitorPart;
    private final PageContainerFillLayout pageContainerLayout = new PageContainerFillLayout(10, 10, 400, 225);
    private TableViewer pageChooserViewer;
    private Image image;
    private Group group;
    private boolean isSimpleMode;
    private IDialogSettings dialogSettings;

    public ConfigurationWizardDialog(Shell parentShell, IWizard newWizard) {
        super(parentShell, newWizard);
        IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
        dialogSettings = workbenchSettings.getSection(ConfigurationWizardDialog.class.getName());
        if (dialogSettings == null) {
            dialogSettings = workbenchSettings.addNewSection(ConfigurationWizardDialog.class.getName());
        }
        isSimpleMode = dialogSettings.getBoolean(CONFIGURATION_WIZARD_DIALOG_SIMPLE_MODE);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(0, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setFont(parent.getFont());

        final Label titleBarSeparator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
        titleBarSeparator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        createPageChooserComposite(composite);

        // Build the Page container
        final Composite groupContainer = new Composite(composite, SWT.NONE);
        groupContainer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        groupContainer.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        group = new Group(groupContainer, SWT.NONE);
        group.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        group.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).create());
        pageContainer = createPageContainer(group);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = getInitialSize().x;
        gd.heightHint = getInitialSize().y;
        pageContainer.setLayoutData(gd);
        pageContainer.setFont(parent.getFont());

        Label filler = new Label(composite, SWT.NONE);
        filler.setLayoutData(GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 30).create());
        try {
            Field f = WizardDialog.class.getDeclaredField("pageContainer");
            f.setAccessible(true);
            f.set(this, pageContainer);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }
        // Build the separator line
        Label separator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        applyDialogFont(progressMonitorPart);

        return composite;

    }

    @Override
    protected Control createContents(Composite parent) {
        Control contents = super.createContents(parent);
        AbstractProcess process = ((ConfigurationWizard) getWizard()).getProcess();
        if (process != null) {
            updateSelectedProcess(process);
            image = Pics.getWizban().createImage();
            setTitleImage(image);
        }
        return contents;
    }

    @Override
    protected void initializeBounds() {
        super.initializeBounds();

        Point size = getInitialSize();
        Point location = getInitialLocation(size);
        getShell().setBounds(getConstrainedShellBounds(new Rectangle(location.x, location.y, size.x, size.y)));
    }

    @Override
    public boolean close() {
        if (image != null) {
            image.dispose();
        }
        return super.close();
    }

    @Override
    public void updateMessage() {
        super.updateMessage();
        if (getCurrentPage() instanceof IProcessConfigurationWizardPage) {
            String errorMessage = ((IProcessConfigurationWizardPage) getCurrentPage())
                    .isConfigurationPageValid(getConfiguration());
            if (errorMessage != null) {
                setMessage(errorMessage, IMessage.WARNING);
            } else {
                setMessage(getCurrentPage().getDescription());
            }
        }

        if (pageChooserViewer != null) {
            pageChooserViewer.refresh();
        }
    }

    public void updateSelectedProcess(AbstractProcess process) {
        ConfigurationWizard confWizard = (ConfigurationWizard) getWizard();
        enableControls();
        if (!confWizard.getProcess().equals(process)) {
            confWizard.setProcess(process);
        }

        showPage(getWizard().getStartingPage());
        pageChooserViewer.setSelection(new StructuredSelection(getCurrentPage()));
        pageChooserViewer.refresh();
        updateMessage();
    }

    protected void exportAction() {
        final DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.SHEET | SWT.SAVE);
        final String path = dialog.open();
        if (path != null) {
            try {
                final IProgressService service = PlatformUI.getWorkbench().getProgressService();
                service.run(true, false, new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        monitor.beginTask(Messages.exporting, IProgressMonitor.UNKNOWN);
                        for (IConfigurationExportAction action : getConfigurationExporterContributions()) {
                            action.setTargetPath(path);
                            action.run();
                        }
                    }
                });
            } catch (InvocationTargetException e) {
                BonitaStudioLog.error(e);
            } catch (InterruptedException e) {
                BonitaStudioLog.error(e);
            }

        }
    }

    private List<IConfigurationExportAction> getConfigurationExporterContributions() {
        List<IConfigurationExportAction> result = new ArrayList<IConfigurationExportAction>();
        for (IConfigurationElement element : BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements(
                EXPORT_CONFIGURATION_ACTION_ID)) {
            try {
                IConfigurationExportAction action = (IConfigurationExportAction) element.createExecutableExtension(CLASS);
                action.setProcess(getProcess());
                action.setConfiguration(getConfiguration());
                result.add(action);
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return result;
    }

    protected AbstractProcess getProcess() {
        ConfigurationWizard wiard = (ConfigurationWizard) getWizard();
        return wiard.getProcess();
    }

    protected void importAction() {
        ImportWizard wizard = new ImportWizard(getConfiguration(), getProcess());
        WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
        dialog.open();
        ConfigurationWizard confWizard = (ConfigurationWizard) getWizard();
        confWizard.updatePages();
    }

    protected Configuration getConfiguration() {
        ConfigurationWizard wiard = (ConfigurationWizard) getWizard();
        return wiard.getConfigurationCopy();
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);

        // increment the number of columns in the button bar
        ((GridLayout) parent.getLayout()).numColumns++;
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        Composite composite = new Composite(parent, SWT.NONE);
        // create a layout with spacing and margins appropriate for the font
        // size.
        GridLayout layout = new GridLayout();
        layout.numColumns = 0; // will be incremented by createButton
        layout.marginWidth = 0;
        layout.marginLeft = 0;
        layout.marginRight = 0;
        layout.marginHeight = 0;
        layout.horizontalSpacing = 0;
        layout.verticalSpacing = 0;
        composite.setLayout(layout);

        composite.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());
        composite.setFont(parent.getFont());
        layout.numColumns++;

        createAdvancedCheckBox(composite);

        getButton(IDialogConstants.FINISH_ID).moveBelow(null);
        getButton(IDialogConstants.CANCEL_ID).moveBelow(null);

        if (parent.getDisplay().getDismissalAlignment() == SWT.RIGHT) {
            getButton(IDialogConstants.FINISH_ID).moveBelow(null);
        }

        layout.numColumns++;
    }

    protected void createAdvancedCheckBox(Composite composite) {
        final Button checkBox = new Button(composite, SWT.CHECK);
        checkBox.setText(Messages.displayAdvancedConfiguration);
        checkBox.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(true, false).create());
        checkBox.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                updateAdvancedCheckBox(checkBox.getSelection());
            }
        });
        checkBox.setSelection(!isSimpleMode);
    }

    protected void updateAdvancedCheckBox(boolean isAdvanced) {
        isSimpleMode = !isAdvanced;
        dialogSettings.put(CONFIGURATION_WIZARD_DIALOG_SIMPLE_MODE, isSimpleMode);
        pageChooserViewer.refresh();
    }

    protected void performApply() {
        ConfigurationWizard wizard = (ConfigurationWizard) getWizard();
        wizard.applyChanges();
    }

    private void createPageChooserComposite(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().span(1, 2).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 10).create());

        pageChooserViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
        pageChooserViewer.getTable().setLayoutData(
                GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.FILL).grab(false, true).create());
        pageChooserViewer.setContentProvider(new WizardPageContentProvider());
        ILabelDecorator decorator = new WizardPageDecorator(this);
        pageChooserViewer.setLabelProvider(new DecoratingLabelProvider(new WizardPageLabelProvider(this), decorator));
        pageChooserViewer.setInput(getWizard().getPages());
        pageChooserViewer.addFilter(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                IProcessConfigurationWizardPage page = (IProcessConfigurationWizardPage) element;
                if (!isSimpleMode) {
                    return true;
                }
                return page.isDefault() || page.isConfigurationPageValid(getConfiguration()) != null;
            }
        });
        pageChooserViewer.addSelectionChangedListener(this);
        pageChooserViewer.getTable().setEnabled(false);

        addPageChangedListener(this);
    }

    private Composite createPageContainer(Composite parent) {
        Composite result = new Composite(parent, SWT.NULL);
        result.setLayout(pageContainerLayout);
        return result;
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        IWizardPage selectedPage = (IWizardPage) ((IStructuredSelection) event.getSelection()).getFirstElement();
        if (selectedPage != null && !selectedPage.equals(getCurrentPage())) {
            showPage(selectedPage);
        }
        updateButtons();
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        IWizardPage currentSelectedPage = (IWizardPage) ((IStructuredSelection) pageChooserViewer.getSelection())
                .getFirstElement();
        IProcessConfigurationWizardPage selectedPage = (IProcessConfigurationWizardPage) event.getSelectedPage();
        Configuration conf = getConfiguration();
        if (conf != null) {
            if (!selectedPage.equals(currentSelectedPage)) {
                pageChooserViewer.setSelection(new StructuredSelection(selectedPage));
            }
        }
        pageChooserViewer.refresh();
    }

    protected void setControlEnabled(Control control, boolean enabled) {
        if (control instanceof Composite) {
            for (Control c : ((Composite) control).getChildren()) {
                c.setEnabled(enabled);
                if (c instanceof Composite) {
                    setControlEnabled(c, enabled);
                }
            }
        } else if (control instanceof TabFolder) {
            for (Control c : ((TabFolder) control).getChildren()) {
                c.setEnabled(enabled);
                if (c instanceof Composite) {
                    setControlEnabled(c, enabled);
                }
            }
        } else {
            control.setEnabled(enabled);
        }
    }

    @Override
    public void showPage(IWizardPage page) {
        super.showPage(page);
        page.setPreviousPage(null);
        setControlEnabled(page.getControl(), ((ConfigurationWizard) getWizard()).getProcess() != null);
        ConfigurationWizard confWizard = (ConfigurationWizard) getWizard();
        confWizard.updatePages();
        page.setVisible(true);
        pageChooserViewer.setSelection(new StructuredSelection(getCurrentPage()));
        group.setText(page.getTitle());
        String processName = getProcess().getName() + " (" + getProcess().getVersion() + ")";
        setTitle(Messages.bind(Messages.configurationWizardTitle, processName));
        if (((IProcessConfigurationWizardPage) page).isConfigurationPageValid(getConfiguration()) == null) {
            setMessage(Messages.bind(Messages.configurationWizardDesc, processName));
        }

    }

    @Override
    public IWizardPage getCurrentPage() {
        IWizardPage page = super.getCurrentPage();
        if (page == null) {
            return getWizard().getStartingPage();
        }
        return page;
    }

    private void enableControls() {
        pageChooserViewer.getTable().setEnabled(true);
        // exportButton.setEnabled(true) ;
        setControlEnabled(pageContainer, true);
    }

}
