/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.importer.bos.wizard;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.ImportRepositoryModel;
import org.bonitasoft.studio.importer.bos.model.ImportWorkspaceModel;
import org.bonitasoft.studio.importer.bos.operation.ScanWorkspaceOperation;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.PathValidator;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Section;

public class ImportWorkspaceControlSupplier implements ControlSupplier {

    private static final String LAST_IMPORT_PATH = "last.workspace.import.path";

    protected TreeViewer viewer;
    private String folderPath;

    protected TextWidget textWidget;
    protected Section treeSection;
    protected Label descriptionLabel;

    protected RepositoryAccessor repositoryAccessor;
    protected IWizardContainer wizardContainer;
    protected ConflictStatus archiveStatus;
    protected final ExceptionDialogHandler exceptionDialogHandler;

    protected ImportWorkspaceModel workspaceModel;

    private Section statusSection;

    public ImportWorkspaceControlSupplier(RepositoryAccessor repositoryAccessor,
            ExceptionDialogHandler exceptionDialogHandler) {
        this.repositoryAccessor = repositoryAccessor;
        this.exceptionDialogHandler = exceptionDialogHandler;
    }

    /**
     * @see org.bonitasoft.studio.ui.wizard.ControlSupplier#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.core.databinding.DataBindingContext)
     */
    @Override
    public Control createControl(Composite parent, IWizardContainer container, DataBindingContext ctx) {
        this.wizardContainer = container;
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 25).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        doCreateWorkspaceTips(mainComposite);
        doCreateFileBrowser(mainComposite, ctx);
        statusSection = createStatusSection(mainComposite);

        statusSection.setVisible(false);
        textWidget.addTextListener(SWT.Modify, e -> {
            statusSection.setVisible(textWidget.getText() != null && !textWidget.getText().isEmpty());
        });
        return mainComposite;
    }

    private void doCreateWorkspaceTips(Composite mainComposite) {
        Label workspaceTips = new Label(mainComposite, SWT.NONE);
        workspaceTips.setLayoutData(GridDataFactory.fillDefaults().create());
        workspaceTips.setText(Messages.workspaceTips);

        final Section section = new Section(mainComposite, Section.TREE_NODE | Section.CLIENT_INDENT);
        section.setLayout(GridLayoutFactory.fillDefaults().margins(0, 0).create());
        section.setLayoutData(
                GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).hint(200, SWT.DEFAULT)
                        .grab(true, false).create());
        section.setText(Messages.moreInfo);
        Label label = new Label(section, SWT.WRAP);
        label.setLayoutData(GridDataFactory.swtDefaults().create());
        label.setText(Messages.importWorkspaceOverwriteBehavior);
        section.setClient(label);
        section.setExpanded(false);
        section.addExpansionListener(new UpdateLayoutListener(mainComposite));
    }

    private Section createStatusSection(Composite parent) {
        final Section section = new Section(parent, Section.TREE_NODE | Section.CLIENT_INDENT);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setText(Messages.importDetails);
        section.addExpansionListener(new UpdateLayoutListener(parent));
        section.setExpanded(false);
        return section;
    }

    private Composite doCreateFileBrowser(Composite parent, DataBindingContext dbc) {
        final Composite folderBrowserComposite = new Composite(parent, SWT.NONE);
        folderBrowserComposite
                .setLayout(GridLayoutFactory.fillDefaults().create());
        folderBrowserComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final IObservableValue folderPathObserveValue = PojoObservables.observeValue(this, "folderPath");
        textWidget = new TextWidget.Builder()
                .withLabel(Messages.selectABonitaStudioWorkspace)
                .grabHorizontalSpace()
                .fill()
                .alignMiddle()
                .labelAbove()
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(new EmptyInputValidator(""),
                                        new PathValidator.Builder().withMessage(Messages.invalidFilePath).create(),
                                        this::scannerValidator)
                                .create()))
                .bindTo(folderPathObserveValue)
                .inContext(dbc)
                .readOnly()
                .withButton(Messages.browseButton_label)
                .onClickButton(this::browseFile)
                .createIn(folderBrowserComposite);

        textWidget.focusButton();
        return parent;
    }

    private IStatus scannerValidator(Object value) {
        final String path = value.toString();
        if (path.isEmpty()) {
            return ValidationStatus.ok();
        }
        workspaceModel = parseFolder(path);
        final IStatus status = workspaceModel.getStatus();
        if (!status.isOK()) {
            return status;
        }
        if (workspaceModel.getRepositories()
                .map(ImportRepositoryModel::getStatus)
                .anyMatch(IStatus::isOK)) {
            return ValidationStatus.ok();
        }
        return ValidationStatus.error(Messages.noValidRepositoryFoundAtLocation);
    }

    protected void browseFile(Event e) {
        Optional.ofNullable(openFileDialog(Display.getDefault().getActiveShell()))
                .ifPresent(this::updateFilePath);
    }

    private void updateFilePath(String filePath) {
        if (!Objects.equals(textWidget.getText(), filePath)) {
            textWidget.setText(filePath);
            if (statusSection.getClient() != null) {
                statusSection.getClient().dispose();
            }
            statusSection.setClient(createStatusControl(statusSection));
            if (workspaceModel.getStatus().isOK()) {
                statusSection.setExpanded(true);
                statusSection.getShell().pack(true);
                statusSection.getShell().layout(true, true);
            }
            textWidget.getParent().getParent().layout();
            if (new File(filePath).exists()) {
                savePath(filePath);
            }
        }
    }

    private Control createStatusControl(Section parent) {
        final Label statusReport = new Label(parent, SWT.NONE);
        statusReport.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        statusReport.setText(workspaceModel.buildReport());
        return statusReport;
    }

    protected ImportWorkspaceModel parseFolder(String folderPath) {
        final File selectedFolder = new File(folderPath);
        final ScanWorkspaceOperation operation = newParseOperation(selectedFolder);
        try {
            wizardContainer.run(true, false, operation);
        } catch (final InvocationTargetException | InterruptedException e) {
            exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                    Messages.errorOccuredWhileParsingBosArchive, e);
        }
        return operation.getImportWorkspaceModel();
    }

    protected ScanWorkspaceOperation newParseOperation(final File selectedFolder) {
        return new ScanWorkspaceOperation(selectedFolder, repositoryAccessor.getCurrentRepository());
    }

    protected String openFileDialog(Shell shell) {
        final DirectoryDialog dd = new DirectoryDialog(shell, SWT.OPEN | SWT.SINGLE);
        dd.setText(Messages.selectABonitaStudioWorkspace);
        dd.setFilterPath(getLastPath());
        return dd.open();
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

    public ImportWorkspaceModel getWorkspaceModel() {
        return workspaceModel;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    protected class UpdateLayoutListener implements IExpansionListener {

        private final Composite toLayout;

        public UpdateLayoutListener(Composite toLayout) {
            this.toLayout = toLayout;
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.ui.forms.events.IExpansionListener#expansionStateChanging(org.eclipse.ui.forms.events.ExpansionEvent)
         */
        @Override
        public void expansionStateChanging(ExpansionEvent e) {
            //NOTHING TO DO
        }

        /*
         * (non-Javadoc)
         * @see org.eclipse.ui.forms.events.IExpansionListener#expansionStateChanged(org.eclipse.ui.forms.events.ExpansionEvent)
         */
        @Override
        public void expansionStateChanged(ExpansionEvent e) {
            toLayout.getShell().pack(true);
            toLayout.layout();
        }

    }

}
