/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.importer.bos.wizard;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.ConflictStatus;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.ParseBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.provider.ActionLabelProvider;
import org.bonitasoft.studio.importer.bos.provider.ArchiveTreeContentProvider;
import org.bonitasoft.studio.importer.bos.provider.ImportActionEditingSupport;
import org.bonitasoft.studio.importer.bos.provider.ImportModelLabelProvider;
import org.bonitasoft.studio.importer.bos.provider.ImportModelStyler;
import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.PathValidator;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.wizard.IWizardContainer;
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
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.events.IExpansionListener;
import org.eclipse.ui.forms.widgets.Section;

public class ImportBosArchiveControlSupplier implements ControlSupplier {

    private static final int BUTTON_WIDTH = 80;
    private static final String BOS_EXTENSION = "*.bos";
    private static final String LAST_IMPORT_PATH = "last.bos.import.path";

    protected TreeViewer viewer;
    private String filePath;
    protected ButtonWidget overwriteButton;
    protected ButtonWidget keepAllButton;
    private ArchiveTreeContentProvider provider;

    protected ImportArchiveModel archiveModel;
    protected TextWidget textWidget;
    protected Section treeSection;
    protected Label descriptionLabel;
    private Color errorColor;
    private Color successColor;
    protected ImportActionSelector importActionSelector;

    protected RepositoryAccessor repositoryAccessor;
    protected IWizardContainer wizardContainer;
    protected ConflictStatus archiveStatus;
    private IObservableValue archiveStatusObservable;
    protected final ExceptionDialogHandler exceptionDialogHandler;
    private DataBindingContext ctx;

    public ImportBosArchiveControlSupplier(RepositoryAccessor repositoryAccessor,
            ExceptionDialogHandler exceptionDialogHandler) {
        this.repositoryAccessor = repositoryAccessor;
        this.exceptionDialogHandler = exceptionDialogHandler;
    }

    /**
     * @see org.bonitasoft.studio.ui.wizard.ControlSupplier#createControl(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.core.databinding.DataBindingContext)
     */
    @Override
    public Control createControl(Composite parent, IWizardContainer container, DataBindingContext ctx) {
        this.wizardContainer = container;
        this.ctx = ctx;
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 25).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        final LocalResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(), mainComposite);
        this.errorColor = resourceManager.createColor(ColorConstants.ERROR_RGB);
        this.successColor = resourceManager.createColor(ColorConstants.SUCCESS_RGB);
        doCreateFileBrowser(mainComposite, ctx);
        doCreateAdditionalControl(mainComposite, ctx);
        doCreateFileTree(mainComposite, ctx);

        treeSection.setVisible(false);
        textWidget.addTextListener(SWT.Modify, e -> {
            treeSection.setVisible(textWidget.getText() != null && !textWidget.getText().isEmpty()
                    && new File(textWidget.getText()).exists());
            treeSection.layout();
        });

        return mainComposite;
    }

    protected void doCreateAdditionalControl(Composite mainComposite, DataBindingContext ctx) {
        // should be overwritten in subclasses
    }

    private void doCreateFileTree(Composite parent, DataBindingContext dbc) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).create());
        createTreeHeader(composite, dbc);
        treeSection.setClient(createTree(treeSection));
    }

    private void createTreeHeader(Composite parent, DataBindingContext ctx) {
        treeSection = new Section(parent, Section.TREE_NODE);
        treeSection.setLayout(GridLayoutFactory.fillDefaults().create());
        treeSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        treeSection.setText(Messages.importDetails);
        treeSection.addExpansionListener(new UpdateLayoutListener(parent));
        treeSection.setExpanded(false);
        descriptionLabel = new Label(treeSection, SWT.WRAP);
        archiveStatusObservable = PojoObservables.observeValue(this, "archiveStatus");

        ctx.bindValue(SWTObservables.observeText(descriptionLabel), archiveStatusObservable,
                neverUpdateValueStrategy().create(), updateValueStrategy().withValidator(this::archiveStatusValidator)
                        .withConverter(createArchiveStatusConverter()).create());
        treeSection.setDescriptionControl(descriptionLabel);
    }

    protected IStatus archiveStatusValidator(Object value) {
        return Objects.equals(value, ConflictStatus.SAME_CONTENT) ? ValidationStatus.error("Archive content already exists.")
                : ValidationStatus.ok();
    }

    protected Converter createArchiveStatusConverter() {
        return new Converter(ConflictStatus.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                return Objects.equals(fromObject, ConflictStatus.CONFLICTING) ? getConflictMessage()
                        : Messages.noConflictMessage;
            }
        };
    }

    private Composite createTree(Composite parent) {
        final Composite fileTreeGroup = new Composite(parent, SWT.NONE);
        fileTreeGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 5).create());
        fileTreeGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        viewer = new TreeViewer(fileTreeGroup,
                SWT.VIRTUAL | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(600, SWT.DEFAULT).create());
        provider = new ArchiveTreeContentProvider(viewer);
        viewer.setContentProvider(provider);
        viewer.setUseHashlookup(true); // important for lazy behavior!
        viewer.getTree().setHeaderVisible(true);
        viewer.getTree().setLinesVisible(true);
        final TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(6, true));
        layout.addColumnData(new ColumnWeightData(2, true));
        viewer.getTree().setLayout(layout);
        ColumnViewerToolTipSupport.enableFor(viewer);

        final TreeViewerColumn archiveColumn = new TreeViewerColumn(viewer, SWT.NONE);
        archiveColumn.getColumn().setText(Messages.archiveColumn);
        archiveColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new ImportModelLabelProvider(
                new ImportModelStyler())));

        final TreeViewerColumn actionColumn = new TreeViewerColumn(viewer, SWT.NONE);
        actionColumn.getColumn().setText(Messages.actionColumn);
        actionColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new ActionLabelProvider()));
        actionColumn.setEditingSupport(new ImportActionEditingSupport(viewer));

        final Composite buttonsComposite = new Composite(fileTreeGroup, SWT.NONE);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(5, 2).create());
        buttonsComposite.setLayoutData(
                GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.TOP).create());

        importActionSelector = new ImportActionSelector(viewer);
        overwriteButton = new ButtonWidget.Builder()
                .withLabel(Messages.overwriteAll)
                .alignLeft()
                .fill()
                .grabHorizontalSpace()
                .minimumWidth(BUTTON_WIDTH)
                .onClick(importActionSelector::selectOverwriteAll)
                .createIn(buttonsComposite);
        overwriteButton.disable();

        keepAllButton = new ButtonWidget.Builder()
                .withLabel(Messages.keepAll)
                .alignLeft()
                .fill()
                .minimumWidth(BUTTON_WIDTH)
                .grabHorizontalSpace()
                .onClick(importActionSelector::selectKeepAll)
                .createIn(buttonsComposite);
        keepAllButton.disable();

        return fileTreeGroup;
    }

    private Composite doCreateFileBrowser(Composite parent, DataBindingContext dbc) {
        final Composite fileBrowserComposite = new Composite(parent, SWT.NONE);
        fileBrowserComposite
                .setLayout(GridLayoutFactory.fillDefaults().create());
        fileBrowserComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        final IObservableValue filePathObserveValue = PojoObservables.observeValue(this, "filePath");
        filePathObserveValue.addValueChangeListener(this::parseArchive);
        textWidget = new TextWidget.Builder()
                .withLabel(Messages.selectFileToImport)
                .grabHorizontalSpace()
                .fill()
                .alignMiddle()
                .labelAbove()
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(getEmptyInputValidator(""), getPathValidator()).create()))
                .bindTo(filePathObserveValue)
                .inContext(dbc)
                .readOnly()
                .withButton(Messages.browseButton_label)
                .onClickButton(this::browseFile)
                .createIn(fileBrowserComposite);
        textWidget.focusButton();
        return parent;
    }

    protected void browseFile(Event e) {
        Optional.ofNullable(openFileDialog(Display.getDefault().getActiveShell()))
                .ifPresent(this::updateFilePath);
    }

    private void updateFilePath(String filePath) {
        textWidget.setText(filePath);
        if (archiveModel != null) {
            IObservableValue validationStatus = textWidget.getValueBinding().getValidationStatus();
            validationStatus.setValue(archiveModel.getValidationStatus());
        }
        textWidget.getParent().getParent().layout();
        if (new File(filePath).exists()) {
            savePath(filePath);
        } else {
            descriptionLabel.setText("");
            viewer.setInput(null);
        }
    }

    private EmptyInputValidator getEmptyInputValidator(String inputName) {
        return new EmptyInputValidator(inputName);
    }

    private PathValidator getPathValidator() {
        return new PathValidator.Builder().withMessage(Messages.invalidFilePath).create();
    }

    protected void parseArchive(ValueChangeEvent e) {
        Optional.ofNullable((String) e.diff.getNewValue()).ifPresent(filePath -> {
            final File myFile = new File(filePath);
            archiveModel = parseArchive(myFile.getAbsolutePath());
            if (archiveModel != null) {
                importActionSelector.setArchiveModel(archiveModel);
                viewer.setInput(archiveModel);
                openTree();
            }
        });
    }

    protected ImportArchiveModel parseArchive(String path) {
        final File selectedFile = new File(path);
        final ParseBosArchiveOperation operation = newParseOperation(selectedFile);
        try {
            wizardContainer.run(true, false, operation);
        } catch (final InvocationTargetException | InterruptedException e) {
            exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                    Messages.errorOccuredWhileParsingBosArchive, e);
        }
        return operation.getImportArchiveModel();
    }

    protected ParseBosArchiveOperation newParseOperation(final File selectedFile) {
        return new ParseBosArchiveOperation(selectedFile,
                repositoryAccessor.getCurrentRepository());
    }

    protected void openTree() {
        archiveStatusObservable.setValue(archiveModel.getStatus());
        if (archiveModel.isConflicting()) {
            final TreeItem[] items = viewer.getTree().getItems();
            for (int i = 0; i < items.length; i++) {
                final TreeItem item = items[i];
                provider.updateElement(archiveModel, i);
                if (item.getData() instanceof AbstractFolderModel
                        && ((AbstractFolderModel) item.getData()).isConflicting()) {
                    openItem(item);
                }
            }
            treeSection.getDescriptionControl().setForeground(errorColor);
            keepAllButton.enable();
            overwriteButton.enable();
        } else if (archiveModel.sameContentAsTarget()) {
            descriptionLabel.setText(getAlreadyPresentMessage());
            treeSection.getDescriptionControl().setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY));
            keepAllButton.disable();
            overwriteButton.disable();
        } else {
            descriptionLabel.setText(Messages.noConflictMessage);
            treeSection.getDescriptionControl().setForeground(successColor);
            keepAllButton.disable();
            overwriteButton.disable();
        }
        treeSection.setExpanded(true);
    }

    protected String getAlreadyPresentMessage() {
        return Messages.alreadyPresent;
    }

    protected String getConflictMessage() {
        return Messages.conflictMessage;
    }

    private void openItem(TreeItem item) {
        item.setExpanded(true);
        final AbstractFolderModel parent = (AbstractFolderModel) item.getData();
        parent.getFolders().stream().forEach(f -> provider.updateElement(parent, parent.getFolders().indexOf(f)));
        Stream.of(item.getItems())
                .filter(i -> i.getData() instanceof AbstractFolderModel)
                .filter(i -> ((AbstractFolderModel) i.getData()).isConflicting())
                .forEach(this::openItem);
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

    public void setArchiveModel(ImportArchiveModel archiveModel) {
        this.archiveModel = archiveModel;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ConflictStatus getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(ConflictStatus archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    protected class UpdateLayoutListener implements IExpansionListener {

        private final Composite toLayout;

        public UpdateLayoutListener(Composite toLayout) {
            this.toLayout = toLayout;
        }

        /*
         * (non-Javadoc)
         * @see
         * org.eclipse.ui.forms.events.IExpansionListener#expansionStateChanging(org.eclipse.ui.forms.events.ExpansionEvent)
         */
        @Override
        public void expansionStateChanging(ExpansionEvent e) {
            //NOTHING TO DO
        }

        /*
         * (non-Javadoc)
         * @see
         * org.eclipse.ui.forms.events.IExpansionListener#expansionStateChanged(org.eclipse.ui.forms.events.ExpansionEvent)
         */
        @Override
        public void expansionStateChanged(ExpansionEvent e) {
            toLayout.layout();
        }

    }
}
