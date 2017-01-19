/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.importer.bos.wizard;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.pathValidator;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.ImportAction;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.model.ImportStoreModel;
import org.bonitasoft.studio.importer.bos.operation.ParseBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.provider.ActionLabelProvider;
import org.bonitasoft.studio.importer.bos.provider.ArchiveTreeContentProvider;
import org.bonitasoft.studio.importer.bos.provider.DecisionEditingSupport;
import org.bonitasoft.studio.importer.bos.provider.ImportModelLabelProvider;
import org.bonitasoft.studio.importer.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.Section;

public class ImportBosArchiveControlSupplier implements ControlSupplier {

    private static final int BUTTON_WIDTH = 80;
    private static final String BOS_EXTENSION = "*.bos";
    private static final String LAST_IMPORT_PATH = null;

    private TreeViewer viewer;
    private String filePath;
    private ButtonWidget importAllButton, keepAllButton;

    private ImportArchiveModel archiveModel;
    private TextWidget textWidget;
    private Section treeSection;
    private Label descriptionLabel;
    private LocalResourceManager resourceManager;
    private Color errorColor;
    private Color successColor;

    /**
     * @see org.bonitasoft.studio.ui.wizard.ControlSupplier#createControl(org.eclipse.swt.widgets.Composite, org.eclipse.core.databinding.DataBindingContext)
     */
    @Override
    public Control createControl(Composite parent, DataBindingContext ctx) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources(), mainComposite);
        this.errorColor = resourceManager.createColor(ColorConstants.ERROR_RGB);
        this.successColor = resourceManager.createColor(ColorConstants.SUCCESS_RGB);
        this.doCreateFileBrowser(mainComposite, ctx);

        this.doCreateFileTree(mainComposite, ctx);

        return mainComposite;
    }

    private void doCreateFileTree(Composite parent, DataBindingContext dbc) {

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 400).create());
        createTreeHeader(composite);
        treeSection.setClient(createTree(treeSection));
    }

    private void createTreeHeader(Composite parent) {
        treeSection = new Section(parent, Section.TREE_NODE);
        treeSection.setLayout(GridLayoutFactory.fillDefaults().create());
        treeSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        treeSection.setText(Messages.importDetails);
        treeSection.setExpanded(false);
        descriptionLabel = new Label(treeSection, SWT.WRAP);
        treeSection.setDescriptionControl(descriptionLabel);
    }

    private Composite createTree(Composite parent) {
        final Composite fileTreeGroup = new Composite(parent, SWT.NONE);
        fileTreeGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 5).create());

        fileTreeGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        viewer = new TreeViewer(fileTreeGroup, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.setContentProvider(new ArchiveTreeContentProvider());

        viewer.getTree().setHeaderVisible(true);
        viewer.getTree().setLinesVisible(true);

        final TreeViewerColumn mainColumn = new TreeViewerColumn(viewer, SWT.NONE);
        mainColumn.getColumn().setText(Messages.archiveColumn);
        mainColumn.getColumn().setWidth(450);
        mainColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new ImportModelLabelProvider(
                new ImportModelLabelProvider.ConflictStyler())));

        final TreeViewerColumn secondColumn = new TreeViewerColumn(viewer, SWT.NONE);
        secondColumn.getColumn().setText(Messages.actionColumn);
        secondColumn.getColumn().setWidth(300);
        secondColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new ActionLabelProvider()));

        secondColumn.setEditingSupport(new DecisionEditingSupport(viewer));

        final Composite buttonsComposite = new Composite(fileTreeGroup, SWT.NONE);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(5, 2).create());
        buttonsComposite.setLayoutData(
                GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.TOP).create());

        importAllButton = new ButtonWidget.Builder()
                .withLabel(Messages.importAll)
                .alignLeft()
                .fill()
                .grabHorizontalSpace()
                .minimumWidth(BUTTON_WIDTH)
                .onClick(this::selectOverwriteAll)
                .createIn(buttonsComposite);
        importAllButton.disable();

        keepAllButton = new ButtonWidget.Builder()
                .withLabel(Messages.keepAll)
                .alignLeft()
                .fill()
                .minimumWidth(BUTTON_WIDTH)
                .grabHorizontalSpace()
                .onClick(this::selectKeepAll)
                .createIn(buttonsComposite);
        keepAllButton.disable();

        return fileTreeGroup;
    }

    protected void selectKeepAll(Event e) {
        selectAll(ImportAction.KEEP);
    }

    private void selectAll(ImportAction importAction) {
        archiveModel.getStores().stream().forEach(folder -> {
            folder.getFiles().stream()
                    .filter(AbstractFileModel::isConflicting)
                    .forEach(f -> f.setImportAction(importAction));
        });
        viewer.refresh();
    }

    protected void selectOverwriteAll(Event e) {
        selectAll(ImportAction.OVERWRITE);
    }

    private Composite doCreateFileBrowser(Composite parent, DataBindingContext dbc) {

        final Composite fileBrowserComposite = new Composite(parent, SWT.NONE);
        fileBrowserComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        fileBrowserComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        textWidget = new TextWidget.Builder()
                .withLabel(Messages.selectFileToImport)
                .widthHint(400)
                .alignLeft()
                .alignMiddle()
                .labelAbove()
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(pathValidator(Messages.selectFileToImport).overrideMessage(Messages.invalidFilePath))
                        .create())
                .bindTo(PojoObservables.observeValue(this, "filePath"))
                .inContext(dbc)
                .readOnly()
                .withButton(Messages.browseButton_label)
                .onClickButton(this::parseArchive)
                .createIn(fileBrowserComposite);
        textWidget.focusButton();

        return parent;
    }

    protected void parseArchive(Event e) {
        final Optional<String> file = Optional.ofNullable(openFileDialog(Display.getDefault().getActiveShell()));
        if (file.isPresent()) {
            final String filePath = file.get();
            textWidget.setText(filePath);
            if (new File(file.get()).exists()) {
                savePath(filePath);
                final File myFile = new File(filePath);
                archiveModel = parseArchive(myFile.getAbsolutePath());
                if (archiveModel != null) {
                    viewer.setInput(archiveModel);
                    openTree();
                }
            } else {
                descriptionLabel.setText("");
                viewer.setInput(null);
            }
        }
    }

    private ImportArchiveModel parseArchive(String path) {
        final File selectedFile = new File(path);
        final SkippableProgressMonitorJobsDialog progressManager = new SkippableProgressMonitorJobsDialog(
                Display.getDefault().getActiveShell());
        final ParseBosArchiveOperation operation = new ParseBosArchiveOperation(selectedFile,
                RepositoryManager.getInstance().getCurrentRepository());
        try {
            progressManager.run(true, false, operation);
        } catch (final InvocationTargetException | InterruptedException e) {
            final Throwable t = e instanceof InvocationTargetException
                    ? ((InvocationTargetException) e).getTargetException() : e;
            BonitaStudioLog.error("Import has failed for file " + selectedFile.getName(), e);
            String message = Messages.errorWhileImporting_message;
            if (t != null && !isNullOrEmpty(t.getMessage())) {
                message = t.getMessage();
            }
            new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.errorWhileImporting_title, message, e)
                    .open();
        }
        return operation.getImportArchiveModel();
    }

    private void openTree() {
        treeSection.setExpanded(true);
        if (conflicts(archiveModel)) {
            for (final TreeItem item : viewer.getTree().getItems()) {
                if (item.getData() instanceof AbstractFolderModel
                        && ((AbstractFolderModel) item.getData()).isConflicting()) {
                    openItem(item);
                }
            }
            viewer.refresh();
            descriptionLabel.setText(Messages.conflictMessage);
            descriptionLabel.getParent().layout();
            treeSection.getDescriptionControl().setForeground(errorColor);
            keepAllButton.enable();
            importAllButton.enable();

        } else {
            descriptionLabel.setText(Messages.noConflictMessage);
            descriptionLabel.getParent().layout();
            treeSection.getDescriptionControl().setForeground(successColor);
            keepAllButton.disable();
            importAllButton.disable();
        }
    }

    private void openItem(TreeItem item) {
        item.setExpanded(true);
        viewer.refresh();
        Stream.<TreeItem> of(item.getItems()).filter(childItem -> childItem.getData() instanceof AbstractFolderModel
                && ((AbstractFolderModel) childItem.getData()).isConflicting()).forEach(this::openItem);
    }

    private boolean conflicts(ImportArchiveModel archiveModel) {
        return archiveModel.getStores().stream().anyMatch(ImportStoreModel::isConflicting);
    }

    protected String openFileDialog(Shell shell) {
        final FileDialog fd = new FileDialog(shell, SWT.OPEN | SWT.SINGLE);
        fd.setText(Messages.importProcessTitle);
        fd.setFilterPath(getLastPath());
        fd.setFilterExtensions(new String[] { BOS_EXTENSION });
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

    public ImportArchiveModel getArchiveModel() {
        return archiveModel;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
