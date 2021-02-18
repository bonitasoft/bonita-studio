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
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;
import org.bonitasoft.studio.common.widgets.CustomStackLayout;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.bos.handler.SwitchRepositoryStrategy;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.bonitasoft.studio.importer.bos.model.AbstractFolderModel;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.model.SmartImportFileStoreModel;
import org.bonitasoft.studio.importer.bos.operation.FetchRemoteBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.operation.ImportConflictsChecker;
import org.bonitasoft.studio.importer.bos.operation.ParseBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.provider.ArchiveTreeContentProvider;
import org.bonitasoft.studio.importer.bos.provider.ImportActionEditingSupport;
import org.bonitasoft.studio.importer.bos.provider.ImportModelLabelProvider;
import org.bonitasoft.studio.importer.bos.validator.RepositoryNameValidator;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileData.RepositoryMode;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.PathValidator;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.bonitasoft.studio.ui.widget.CComboWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
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
    private Section repositorySection;
    protected String filePath;
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
    private IObservableValue filePathObserveValue;
    private URLTempPath urlTempPath;
    private SelectObservableValue repositoryModeObservable;

    private String newTargetRepo = "";
    private String existingTargetRepo = "";
    private IObservableValue<String> newRepoObservable;
    private IObservableValue<String> existingRepoObservable;
    private Composite newRepositoryComposite;
    private Composite existingRepositoryComposite;
    private RepositoryNameValidator validator;
    private TextWidget newRepoText;
    private SwitchRepositoryStrategy switchRepositoryStrategy;
    private RepositoryMode mode = RepositoryMode.EXISTING;

    public ImportBosArchiveControlSupplier(RepositoryAccessor repositoryAccessor,
            SwitchRepositoryStrategy switchRepositoryStrategy,
            ExceptionDialogHandler exceptionDialogHandler) {
        this(repositoryAccessor, switchRepositoryStrategy, exceptionDialogHandler, null);
    }

    public ImportBosArchiveControlSupplier(RepositoryAccessor repositoryAccessor,
            SwitchRepositoryStrategy switchRepositoryStrategy,
            ExceptionDialogHandler exceptionDialogHandler,
            String filePath) {
        this.repositoryAccessor = repositoryAccessor;
        this.exceptionDialogHandler = exceptionDialogHandler;
        this.filePath = filePath;
        newRepoObservable = PojoProperties.<ImportBosArchiveControlSupplier, String> value("newTargetRepo").observe(this);
        existingRepoObservable = PojoProperties.<ImportBosArchiveControlSupplier, String> value("existingTargetRepo")
                .observe(this);
        this.switchRepositoryStrategy = switchRepositoryStrategy;
        if (switchRepositoryStrategy.isSwitchRepository()) {
            newRepoObservable.setValue(switchRepositoryStrategy.getTargetRepository());
        }
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer container, DataBindingContext ctx) {
        this.wizardContainer = container;
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 25).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        final LocalResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources(),
                mainComposite);
        this.errorColor = resourceManager.createColor(ColorConstants.ERROR_RGB);
        this.successColor = resourceManager.createColor(ColorConstants.SUCCESS_RGB);
        doFileLocationBrowser(mainComposite, ctx);
        doCreateSwitchRepositoryControl(mainComposite, ctx);
        doCreateFileTree(mainComposite, ctx);

        treeSection.setVisible(filePath != null);
        textWidget.addTextListener(SWT.Modify, e -> {
            treeSection.setVisible(textWidget.getText() != null && !textWidget.getText().isEmpty());
            treeSection.layout();
        });

        return mainComposite;
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        repositoryModeObservable.setValue(
                !newRepoObservable.getValue().isEmpty() ? RepositoryMode.NEW : RepositoryMode.EXISTING);
        if (filePath != null) {
            Display.getDefault().asyncExec(() -> {
                File myFile = new File(filePath);
                if (!myFile.exists()) {
                    try {
                        myFile = fetchArchive(filePath);
                    } catch (FetchRemoteBosArchiveException e) {
                        textWidget.getValueBinding().getValidationStatus().setValue(ValidationStatus
                                .error(String.format(Messages.cannotImportRemoteArchive, e.getLocalizedMessage())));
                        return;
                    }
                }
                archiveModel = parseArchive(myFile.getAbsolutePath());
                if (archiveModel != null) {
                    textWidget
                            .setMessage(String.format("%s %s (%s)",
                                    Messages.bosArchiveName,
                                    myFile.getName(),
                                    archiveModel.getBosArchive().getVersion()));
                    importActionSelector.setArchiveModel(archiveModel);
                    viewer.setInput(archiveModel);
                    openTree();
                }
            });
        }
    }

    protected void doCreateSwitchRepositoryControl(Composite parent, DataBindingContext ctx) {
        repositorySection = new Section(parent, Section.TREE_NODE);
        repositorySection.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIDGET_BACKGROUND_CLASS);
        repositorySection.setLayout(GridLayoutFactory.fillDefaults().create());
        repositorySection.setLayoutData(GridDataFactory.fillDefaults().create());
        repositorySection.setText(org.bonitasoft.studio.importer.i18n.Messages.targetRepository);
        repositorySection.addExpansionListener(new UpdateLayoutListener(parent));
        repositorySection.setExpanded(!newRepoObservable.getValue().isEmpty());
        final Composite mainComposite = doCreateRadioButtons(repositorySection);
        doCreateStackLayout(mainComposite, ctx);

        repositorySection.setClient(mainComposite);
    }

    private void doCreateStackLayout(Composite parent, DataBindingContext ctx) {
        final Composite stackComposite = new Composite(parent, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).grab(true, true).create());

        final CustomStackLayout stackLayout = new CustomStackLayout(stackComposite);
        stackComposite.setLayout(stackLayout);

        doCreateComposites(stackComposite, ctx);

        ctx.bindValue(PojoProperties.value("topControl").observe(stackLayout), repositoryModeObservable,
                UpdateStrategyFactory.neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(repoModeToCompositeConverter()).create());
    }

    private IConverter repoModeToCompositeConverter() {
        return new Converter(RepositoryMode.class, Composite.class) {

            @Override
            public Object convert(Object fromObject) {
                return fromObject != null ? compositeFor((RepositoryMode) fromObject) : null;
            }
        };
    }

    private Control compositeFor(final RepositoryMode repoMode) {
        switch (repoMode) {
            case NEW:
                return newRepositoryComposite;
            case EXISTING:
            default:
                return existingRepositoryComposite;
        }
    }

    private void doCreateComposites(Composite parent, DataBindingContext ctx) {
        doCreateExistingRepositoryComposite(parent, ctx);
        doCreateNewRepositoryComposite(parent, ctx);
    }

    private void doCreateExistingRepositoryComposite(Composite parent, DataBindingContext ctx) {
        existingRepositoryComposite = new Composite(parent, SWT.NONE);
        existingRepositoryComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        existingRepositoryComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final String[] repositories = repositoryAccessor.getAllRepositories().stream()
                .map(repo -> {
                    if (Objects.equals(repo.getName(), repositoryAccessor.getCurrentRepository().getName())) {
                        return getCurrentRepoNameWithInfo();
                    }
                    return repo.getName();
                })
                .toArray(String[]::new);

        existingRepoObservable.setValue(getCurrentRepoNameWithInfo());

        new CComboWidget.Builder()
                .withItems(repositories)
                .labelAbove()
                .readOnly()
                .widthHint(500)
                .bindTo(existingRepoObservable)
                .inContext(ctx)
                .createIn(existingRepositoryComposite);
    }

    private String getCurrentRepoNameWithInfo() {
        return repositoryAccessor.getCurrentRepository().getName() + " " + Messages.currentRepoinfo;
    }

    private void doCreateNewRepositoryComposite(Composite parent, DataBindingContext ctx) {
        newRepositoryComposite = new Composite(parent, SWT.NONE);
        newRepositoryComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        newRepositoryComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        newRepoText = new TextWidget.Builder()
                .widthHint(500)
                .labelAbove()
                .withId("newRepoTextWidget")
                .bindTo(newRepoObservable)
                .withTargetToModelStrategy(updateValueStrategy().withValidator(validator))
                .inContext(ctx)
                .withDelay(500)
                .createIn(newRepositoryComposite);

        newRepoText.getValueBinding().getValidationStatus().addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(ValueChangeEvent event) {
                if (!newRepoText.getStatus().isOK()) {
                    switchRepositoryStrategy.setTargetRepository(newRepoText.getText());
                    resetTree();
                }
            }
        });
    }

    private void resetTree() {
        descriptionLabel.setText("");
        viewer.setInput(null);
        treeSection.setExpanded(false);
        keepAllButton.disable();
        overwriteButton.disable();
    }

    private Composite doCreateRadioButtons(Composite parent) {
        final Composite radioGroup = new Composite(parent, SWT.NONE);
        radioGroup.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 0, 0).numColumns(2).create());
        radioGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Button existingRepositoryButton = new Button(radioGroup, SWT.RADIO);
        existingRepositoryButton.setText(org.bonitasoft.studio.importer.i18n.Messages.anExistingRepository);
        existingRepositoryButton.setEnabled(repositoryAccessor.getAllRepositories().size() > 0);
        existingRepositoryButton.setLayoutData(GridDataFactory.fillDefaults().create());

        final Button newRepositoryButton = new Button(radioGroup, SWT.RADIO);
        newRepositoryButton.setText(org.bonitasoft.studio.importer.i18n.Messages.aNewRepository);
        newRepositoryButton.setLayoutData(GridDataFactory.fillDefaults().create());

        repositoryModeObservable = new SelectObservableValue();
        repositoryModeObservable.addOption(RepositoryMode.EXISTING,
                WidgetProperties.buttonSelection().observe(existingRepositoryButton));
        repositoryModeObservable.addOption(RepositoryMode.NEW,
                WidgetProperties.buttonSelection().observe(newRepositoryButton));
        repositoryModeObservable.addValueChangeListener(this::repositoryModeChanged);

        validator = new org.bonitasoft.studio.importer.bos.validator.RepositoryNameValidator(repositoryModeObservable);

        return radioGroup;
    }

    private void repositoryModeChanged(ValueChangeEvent event) {
        newRepoText.getValueBinding().validateTargetToModel();
        mode = (RepositoryMode) event.diff.getNewValue();
        switchRepositoryStrategy.setRebuildModel(mode == RepositoryMode.NEW);
        updateTargetRepository(targetRepository(mode));
        if (mode == RepositoryMode.NEW && !newRepoText.getStatus().isOK()) {
            resetTree();
        }
    }

    private void refreshArchiveModel(String targetRepository) {
        try {
            wizardContainer.run(true, false, monitor -> updateArchiveModel(targetRepository, monitor));
            Display.getDefault().asyncExec(() -> {
                importActionSelector.setArchiveModel(archiveModel);
                viewer.setInput(archiveModel);
                openTree();
            });
        } catch (InvocationTargetException | InterruptedException e) {
            exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                    "Failed to update archive model.",
                    e);
        }
    }

    protected void updateArchiveModel(String targetRepository, IProgressMonitor monitor) {
        if (repositoryAccessor.getRepository(targetRepository) != null && archiveModel != null) {
            final AbstractRepository newRepository = repositoryAccessor.getRepository(targetRepository);
            newRepository.open(monitor);
            final ImportConflictsChecker conflictChecker = new ImportConflictsChecker(newRepository);
            try {
                setArchiveModel(conflictChecker.checkConflicts(archiveModel.getBosArchive(), monitor));
            } catch (final IOException e) {
                exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                        Messages.errorReadArchive,
                        e);
            } finally {
                if (!Objects.equals(repositoryAccessor.getCurrentRepository().getName(),
                        newRepository.getName())) {
                    newRepository.close();
                }
            }
        } else if (archiveModel != null) {
            archiveModel.resetStatus();
        }
    }

    public void updateTargetRepository(String targetRepository) {
        final boolean repoChanged = !switchRepositoryStrategy.getTargetRepository().equals(targetRepository);
        switchRepositoryStrategy.setTargetRepository(targetRepository);
        if (!targetRepository.isEmpty() && archiveModel != null && repoChanged) {
            refreshArchiveModel(targetRepository);
        }
    }

    private String targetRepository(RepositoryMode mode) {
        switch (mode) {
            case NEW:
                return newRepoText.getStatus().isOK()
                        ? newTargetRepo
                        : newTargetRepo.isEmpty() && archiveModel != null
                                ? ""
                                : switchRepositoryStrategy.getTargetRepository();
            case EXISTING:
            default:
                return existingTargetRepo;
        }
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
        treeSection.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.WIDGET_BACKGROUND_CLASS);
        treeSection.setLayout(GridLayoutFactory.fillDefaults().create());
        treeSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        treeSection.setText(Messages.importDetails);
        treeSection.addExpansionListener(new UpdateLayoutListener(parent));
        treeSection.setExpanded(false);
        descriptionLabel = new Label(treeSection, SWT.WRAP);
        archiveStatusObservable = PojoProperties.value("archiveStatus").observe(this);

        ctx.bindValue(WidgetProperties.text().observe(descriptionLabel), archiveStatusObservable,
                neverUpdateValueStrategy().create(), updateValueStrategy().withValidator(this::archiveStatusValidator)
                        .withConverter(createArchiveStatusConverter()).create());
        treeSection.setDescriptionControl(descriptionLabel);
    }

    protected IStatus archiveStatusValidator(Object value) {
        return Objects.equals(value, ConflictStatus.SAME_CONTENT)
                ? ValidationStatus.error("Archive content already exists.")
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

        archiveColumn.setLabelProvider(new ImportModelLabelProvider());

        final TreeViewerColumn actionColumn = new TreeViewerColumn(viewer, SWT.NONE);
        actionColumn.getColumn().setText(Messages.actionColumn);
        actionColumn.setLabelProvider(new LabelProviderBuilder<>()
                .withTextProvider(this::getActionText)
                .createColumnLabelProvider());
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

    private String getActionText(Object element) {
        if (element instanceof AbstractFileModel) {
            AbstractFileModel fileModel = (AbstractFileModel) element;
            if (fileModel.isConflicting()) {
                return fileModel.getImportAction().toString();
            }
        } else if (element instanceof SmartImportableUnit) {
            SmartImportableUnit unit = (SmartImportableUnit) element;
            if (unit.getImportAction() != null
                    && Objects.equals(unit.getConflictStatus(), ConflictStatus.CONFLICTING)) {
                return unit.getImportAction().toString();
            }
        }
        return "";
    }

    private Composite doFileLocationBrowser(Composite parent, DataBindingContext dbc) {
        Composite fileBrowserComposite = new Composite(parent, SWT.NONE);
        fileBrowserComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        fileBrowserComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        filePathObserveValue = PojoProperties.value("filePath").observe(this);
        filePathObserveValue.addValueChangeListener(this::parseArchive);
        textWidget = new TextWidget.Builder()
                .withLabel(Messages.selectLocation)
                .grabHorizontalSpace()
                .fill()
                .alignMiddle()
                .labelAbove()
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(getEmptyInputValidator("")).create()))
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
            File myFile = new File(filePath);
            if (urlTempPath != null && urlTempPath.getTmpPath().toFile().exists()) {
                urlTempPath.getTmpPath().toFile().delete();
                urlTempPath = null;
            }
            if (!myFile.exists()) {
                try {
                    myFile = fetchArchive(filePath);
                } catch (FetchRemoteBosArchiveException ex) {
                    textWidget.getValueBinding().getValidationStatus().setValue(ValidationStatus
                            .error(String.format(Messages.cannotImportRemoteArchive, ex.getLocalizedMessage())));
                    return;
                }
            }
            archiveModel = parseArchive(myFile.getAbsolutePath());
            if (archiveModel != null) {
                importActionSelector.setArchiveModel(archiveModel);
                viewer.setInput(archiveModel);
                openTree();
            }
        });
    }

    private File fetchArchive(String filePath) throws FetchRemoteBosArchiveException {
        File myFile;
        FetchRemoteBosArchiveOperation operation = new FetchRemoteBosArchiveOperation(filePath);
        try {
            wizardContainer.run(true, false, operation);
        } catch (InvocationTargetException | InterruptedException ex) {
            exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                    Messages.errorOccuredWhileParsingBosArchive, ex);
        }
        if (!operation.getStatus().isOK()) {
            throw new FetchRemoteBosArchiveException(operation.getStatus().getException());
        }
        urlTempPath = operation.getURLTempPath();
        myFile = urlTempPath.getTmpPath().toFile();
        return myFile;
    }

    public boolean shouldDeleteTempFile() {
        return urlTempPath != null && urlTempPath.getTmpPath().toFile().exists();
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
        ImportArchiveModel importArchiveModel = operation.getImportArchiveModel();
        if ((RepositoryMode) repositoryModeObservable.getValue() == RepositoryMode.NEW
                && !newTargetRepo.isEmpty()) {
            switchRepositoryStrategy.setTargetRepository(newTargetRepo);
            importArchiveModel.resetStatus();
        }
        return importArchiveModel;
    }

    private ParseBosArchiveOperation newParseOperation(File selectedFile) {
        return new ParseBosArchiveOperation(selectedFile,
                mode == RepositoryMode.NEW ? repositoryAccessor.getCurrentRepository()
                        : repositoryAccessor.getRepository(switchRepositoryStrategy.getTargetRepository()));
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
                if (item.getData() instanceof SmartImportFileStoreModel
                        && Objects.equals(((SmartImportFileStoreModel) item.getData()).getImportAction(),
                                ImportAction.SMART_IMPORT)
                        && ((SmartImportFileStoreModel) item.getData()).isConflicting()) {
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
        if (archiveModel.isConflicting()) {
            repositorySection.setExpanded(true);
        }
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

    protected void setTextWidgetText(String text) {
        textWidget.setText(text);
    }

    protected class UpdateLayoutListener implements IExpansionListener {

        private final Composite toLayout;

        public UpdateLayoutListener(Composite toLayout) {
            this.toLayout = toLayout;
        }

        @Override
        public void expansionStateChanging(ExpansionEvent e) {
            //NOTHING TO DO
        }

        @Override
        public void expansionStateChanged(ExpansionEvent e) {
            toLayout.layout();
        }

    }

    public String getExistingTargetRepo() {
        if (Objects.equals(existingTargetRepo, repositoryAccessor.getCurrentRepository().getName())) {
            return getCurrentRepoNameWithInfo();
        }
        return existingTargetRepo;
    }

    public void setExistingTargetRepo(String existingTargetRepo) {
        this.existingTargetRepo = existingTargetRepo.endsWith(Messages.currentRepoinfo)
                ? repositoryAccessor.getCurrentRepository().getName() : existingTargetRepo;
        if (!this.existingTargetRepo.isEmpty() && repositoryModeObservable.getValue() == RepositoryMode.EXISTING) {
            updateTargetRepository(this.existingTargetRepo);
        }
    }

    public String getNewTargetRepo() {
        return newTargetRepo;
    }

    public void setNewTargetRepo(String newTargetRepo) {
        this.newTargetRepo = newTargetRepo;
        if (!newTargetRepo.isEmpty() && archiveModel != null) {
            updateTargetRepository(newTargetRepo);
        }
    }
}
