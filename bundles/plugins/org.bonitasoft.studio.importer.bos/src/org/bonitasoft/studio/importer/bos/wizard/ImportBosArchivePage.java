/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bos.wizard;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.convertUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.maven.artifact.versioning.ComparableVersion;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.FakeRepository;
import org.bonitasoft.studio.common.repository.ProjectIdValidator;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.ui.validator.MavenIdValidator;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.common.ui.widgets.CustomStackLayout;
import org.bonitasoft.studio.importer.bos.handler.SwitchRepositoryStrategy;
import org.bonitasoft.studio.importer.bos.i18n.Messages;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.bonitasoft.studio.importer.bos.operation.FetchRemoteBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.operation.ImportConflictsChecker;
import org.bonitasoft.studio.importer.bos.operation.ParseBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.validator.ValidatorWrapper;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ImportBosArchivePage implements ControlSupplier, Supplier<ImportArchiveModel> {

    public enum RepositoryMode {
        CURRENT, NEW
    }

    private static final String BOS_EXTENSION = "*.bos";

    private String filePath;
    private ImportArchiveModel archiveModel;
    private TextWidget fileLocationText;

    private RepositoryAccessor repositoryAccessor;
    private IWizardContainer wizardContainer;
    private final ExceptionDialogHandler exceptionDialogHandler;
    private IObservableValue<String> filePathObserveValue;
    private URLTempPath urlTempPath;
    private SelectObservableValue<RepositoryMode> repositoryModeObservable;

    private ProjectMetadata projectMetadata;
    private IObservableValue<ProjectMetadata> projectMetadataObservable;
    private Composite newRepositoryComposite;
    private Composite existingRepositoryComposite;
    private TextWidget newProjectIdText;
    private SwitchRepositoryStrategy switchRepositoryStrategy;
    private RepositoryMode mode = RepositoryMode.CURRENT;
    private IObservableValue<Boolean> visibleTargetProjectObservable;
    private Map<String, ImportArchiveModel> parsedModels = new HashMap<>();


    public ImportBosArchivePage(RepositoryAccessor repositoryAccessor,
            SwitchRepositoryStrategy switchRepositoryStrategy,
            ExceptionDialogHandler exceptionDialogHandler,
            String filePath) {
        this.repositoryAccessor = repositoryAccessor;
        this.exceptionDialogHandler = exceptionDialogHandler;
        this.filePath = filePath;
        this.switchRepositoryStrategy = switchRepositoryStrategy;
        projectMetadata = ProjectMetadata.defaultMetadata();
        projectMetadataObservable = PojoProperties.value("projectMetadata", ProjectMetadata.class)
                .observe(this);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer container, DataBindingContext ctx) {
        this.wizardContainer = container;
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 25).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        doCreateFileLocationBrowser(mainComposite, ctx);
        doCreateSwitchRepositoryControl(mainComposite, ctx);

        repositoryModeObservable.setValue(RepositoryMode.NEW);

        return mainComposite;
    }

    @Override
    public void pageChanged(PageChangedEvent event) {
        IWizardPage selectedPage = (IWizardPage) event.getSelectedPage();
        if (Messages.importBosArchiveTitle.equals(selectedPage.getTitle()) && filePath != null) {
            Display.getDefault().asyncExec(() -> archiveModel = parsedModels.computeIfAbsent(filePath, key -> {
                File myFile = new File(key);
                if (!myFile.exists()) {
                    try {
                        myFile = fetchArchive(key);
                    } catch (FetchRemoteBosArchiveException e) {
                        fileLocationText.getValueBinding().getValidationStatus().setValue(ValidationStatus
                                .error(String.format(Messages.cannotImportRemoteArchive,
                                        e.getLocalizedMessage())));
                        return null;
                    }
                }
                return parseArchive(myFile.getAbsolutePath());
            }));
        }
    }

    protected void doCreateSwitchRepositoryControl(Composite parent, DataBindingContext ctx) {
        var repositorySection = new Composite(parent, SWT.NONE);
        repositorySection.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIDGET_BACKGROUND_CLASS);
        repositorySection.setLayout(GridLayoutFactory.fillDefaults().create());
        repositorySection.setLayoutData(GridDataFactory.fillDefaults().create());
        Label targetProjectLabel = new Label(repositorySection, SWT.NONE);
        targetProjectLabel.setText(org.bonitasoft.studio.importer.i18n.Messages.targetRepository);
        targetProjectLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Composite mainComposite = doCreateRadioButtons(repositorySection);
        doCreateStackLayout(mainComposite, ctx);

        ctx.bindValue(WidgetProperties.visible().observe(repositorySection),
                visibleTargetProjectObservable,
                neverUpdateValueStrategy().create(),
                null);
        ctx.bindValue(WidgetProperties.visible().observe(repositorySection),
                WidgetProperties.visible().observe(repositorySection));
    }

    private void doCreateStackLayout(Composite parent, DataBindingContext ctx) {
        final Composite stackComposite = new Composite(parent, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).grab(true, true).create());

        final CustomStackLayout stackLayout = new CustomStackLayout(stackComposite);
        stackComposite.setLayout(stackLayout);

        doCreateComposites(stackComposite, ctx);

        ctx.bindValue(PojoProperties.value("topControl", Composite.class).observe(stackLayout),
                repositoryModeObservable,
                neverUpdateValueStrategy().create(),
                updateValueStrategy()
                        .withConverter(repoModeToCompositeConverter()).create());
    }

    private IConverter<RepositoryMode, Composite> repoModeToCompositeConverter() {
        return new Converter<RepositoryMode, Composite>(RepositoryMode.class, Composite.class) {

            @Override
            public Composite convert(RepositoryMode fromObject) {
                return fromObject != null ? compositeFor(fromObject) : null;
            }
        };
    }

    private Composite compositeFor(final RepositoryMode repoMode) {
        switch (repoMode) {
            case NEW:
                return newRepositoryComposite;
            case CURRENT:
            default:
                return existingRepositoryComposite;
        }
    }

    private void doCreateComposites(Composite parent, DataBindingContext ctx) {
        doCreateExistingRepositoryComposite(parent);
        doCreateNewRepositoryComposite(parent, ctx);
    }

    private void doCreateExistingRepositoryComposite(Composite parent) {
        existingRepositoryComposite = new Composite(parent, SWT.NONE);
        existingRepositoryComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        existingRepositoryComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
    }

    private void doCreateNewRepositoryComposite(Composite parent, DataBindingContext ctx) {
        newRepositoryComposite = new Composite(parent, SWT.NONE);
        newRepositoryComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(5, 2).create());
        newRepositoryComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var projectNameObservable = PojoProperties.value("name", String.class)
                .observeDetail(projectMetadataObservable);
        var projectArtifactIdObservable = PojoProperties.value("artifactId", String.class)
                .observeDetail(projectMetadataObservable);

        new TextWidget.Builder()
                .widthHint(250)
                .labelAbove()
                .alignTop()
                .fill()
                .withLabel(org.bonitasoft.studio.common.Messages.name)
                .withId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_NAME_TEXT)
                .bindTo(projectNameObservable)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(conditionalValidator(
                                new EmptyInputValidator(org.bonitasoft.studio.common.Messages.name), whenNewProject())))
                .inContext(ctx)
                .useNativeRender()
                .createIn(newRepositoryComposite);

        newProjectIdText = new TextWidget.Builder()
                .widthHint(250)
                .labelAbove()
                .alignTop()
                .fill()
                .withLabel("Project ID")
                .withId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_ARTIFACTID_TEXT)
                .bindTo(projectArtifactIdObservable)
                .withTargetToModelStrategy(
                        convertUpdateValueStrategy().withValidator(new MultiValidator.Builder()
                                .havingValidators(
                                        conditionalValidator(new MavenIdValidator("Project ID"),
                                                whenNewProject()),
                                        conditionalValidator(
                                                new ProjectIdValidator(() -> projectNameObservable.getValue(), null,
                                                        whenNewProject()),
                                                whenNewProject()))))
                .inContext(ctx)
                .transactionalEdit()
                .useNativeRender()
                .createIn(newRepositoryComposite);

        projectNameObservable.addValueChangeListener(event -> {
            String newProjectName = event.diff.getNewValue();
            if (newProjectName != null && !newProjectName.isBlank()
                    && archiveModel != null
                    && mode == RepositoryMode.NEW
                    && Strings.isNullOrEmpty(projectArtifactIdObservable.getValue())) {
                projectArtifactIdObservable.setValue(ProjectMetadata.toArtifactId(newProjectName));
            }
        });

        projectArtifactIdObservable.addValueChangeListener(event -> {
            String newProjectId = event.diff.getNewValue();
            if (Strings.hasText(newProjectId)
                    && archiveModel != null
                    && mode == RepositoryMode.NEW) {
                updateTargetRepository(projectMetadataObservable.getValue().getProjectId());
            }
        });

        new TextWidget.Builder()
                .widthHint(350)
                .labelAbove()
                .alignTop()
                .fill()
                .withLabel("Group ID")
                .withId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_GROUPID_TEXT)
                .bindTo(PojoProperties.value("groupId", String.class).observeDetail(projectMetadataObservable))
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(conditionalValidator(new MavenIdValidator("Group ID"), whenNewProject())))
                .inContext(ctx)
                .useNativeRender()
                .createIn(newRepositoryComposite);

        new TextWidget.Builder()
                .widthHint(150)
                .labelAbove()
                .alignTop()
                .fill()
                .withLabel(org.bonitasoft.studio.common.Messages.version)
                .withId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_VERSION_TEXT)
                .bindTo(PojoProperties.value("version", String.class).observeDetail(projectMetadataObservable))
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(conditionalValidator(
                                new EmptyInputValidator(org.bonitasoft.studio.common.Messages.version),
                                whenNewProject())))
                .inContext(ctx)
                .useNativeRender()
                .createIn(newRepositoryComposite);

        TextWidget textArea = new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .withId(SWTBotConstants.SWTBOT_ID_NEW_PROJECT_DESCRIPTION_TEXT)
                .labelAbove()
                .heightHint(110)
                .grabHorizontalSpace()
                .fill()
                .bindTo(PojoProperties.value("description").observeDetail(projectMetadataObservable))
                .inContext(ctx)
                .horizontalSpan(2)
                .useNativeRender()
                .createIn(newRepositoryComposite);
        textArea.getTextControl().addTraverseListener(event -> {
            if (event.detail == SWT.TRAVERSE_TAB_NEXT
                    || event.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
                event.doit = true;
            }
        });

    }

    private BooleanSupplier whenNewProject() {
        return () -> repositoryModeObservable.getValue() == RepositoryMode.NEW;
    }

    private IValidator<String> conditionalValidator(IValidator<String> validator,
            BooleanSupplier validateConditionSupplier) {
        return new ValidatorWrapper<>(validator, validateConditionSupplier);
    }

    private Composite doCreateRadioButtons(Composite parent) {
        final Composite radioGroup = new Composite(parent, SWT.NONE);
        radioGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        radioGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Button newRepositoryButton = new Button(radioGroup, SWT.RADIO);
        newRepositoryButton.setText(org.bonitasoft.studio.importer.i18n.Messages.aNewRepository);
        newRepositoryButton.setLayoutData(GridDataFactory.fillDefaults().create());

        final Button currentRepositoryButton = new Button(radioGroup, SWT.RADIO);
        currentRepositoryButton.setText(org.bonitasoft.studio.importer.i18n.Messages.currentRepository);
        currentRepositoryButton.setLayoutData(GridDataFactory.fillDefaults().create());
        currentRepositoryButton.setEnabled(repositoryAccessor.getCurrentRepository().isPresent());

        repositoryModeObservable = new SelectObservableValue<>();
        repositoryModeObservable.addOption(RepositoryMode.CURRENT,
                WidgetProperties.buttonSelection().observe(currentRepositoryButton));
        repositoryModeObservable.addOption(RepositoryMode.NEW,
                WidgetProperties.buttonSelection().observe(newRepositoryButton));
        repositoryModeObservable.addValueChangeListener(this::repositoryModeChanged);

        return radioGroup;
    }

    private void repositoryModeChanged(ValueChangeEvent<? extends RepositoryMode> event) {
        newProjectIdText.getValueBinding().validateTargetToModel();
        mode = event.diff.getNewValue();
        switchRepositoryStrategy.setCreateNewProject(mode == RepositoryMode.NEW);
        updateTargetRepository(targetRepository(mode));
    }

    private void refreshArchiveModel(String targetRepository) {
        try {
            wizardContainer.run(true, false, monitor -> updateArchiveModel(targetRepository, monitor));
        } catch (InvocationTargetException | InterruptedException e) {
            exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                    "Failed to update archive model.",
                    e);
        }
    }

    protected void updateArchiveModel(String targetRepository, IProgressMonitor monitor) {
        if (repositoryAccessor.getRepository(targetRepository) != null && archiveModel != null) {
            var newRepository = repositoryAccessor.getRepository(targetRepository);
            var project = Adapters.adapt(newRepository, BonitaProject.class);
            if (repositoryAccessor.getCurrentRepository().filter(repo -> Objects.equals(repo.getProjectId(),
                    newRepository.getProjectId())).isEmpty()) {
                try {
                    project.open(monitor);
                } catch (CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
            final ImportConflictsChecker conflictChecker = new ImportConflictsChecker(newRepository);
            try {
                setArchiveModel(conflictChecker.checkConflicts(archiveModel.getBosArchive(), monitor));
            } catch (final IOException e) {
                exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                        Messages.errorReadArchive,
                        e);
            } finally {
                if (repositoryAccessor.getCurrentRepository().filter(repo -> Objects.equals(repo.getProjectId(),
                        newRepository.getProjectId())).isEmpty()) {
                    try {
                        project.close(monitor);
                    } catch (CoreException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        } else if (archiveModel != null) {
            archiveModel.resetStatus();
        }
    }

    public void updateTargetRepository(String targetProjectId) {
        final boolean repoChanged = !Objects.equals(switchRepositoryStrategy.getTargetRepository(), targetProjectId);
        switchRepositoryStrategy.setTargetRepository(targetProjectId);
        if (Strings.hasText(targetProjectId) && archiveModel != null && repoChanged) {
            refreshArchiveModel(targetProjectId);
        }
    }

    private String targetRepository(RepositoryMode mode) {
        var metadata = projectMetadataObservable.getValue();
        switch (mode) {
            case NEW:
                return newProjectIdText.getStatus().isOK()
                        ? metadata.getProjectId()
                        : switchRepositoryStrategy.getTargetRepository();
            case CURRENT:
            default:
                return repositoryAccessor.getCurrentRepository().orElseThrow().getProjectId();
        }
    }

    private Composite doCreateFileLocationBrowser(Composite parent, DataBindingContext dbc) {
        filePathObserveValue = PojoProperties.value("filePath", String.class).observe(this);
        filePathObserveValue.addValueChangeListener(this::parseArchive);
        fileLocationText = new TextWidget.Builder()
                .withLabel(Messages.selectLocation)
                .grabHorizontalSpace()
                .fill()
                .widthHint(700)
                .alignMiddle()
                .labelAbove()
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(new EmptyInputValidator("")).create()))
                .bindTo(filePathObserveValue)
                .inContext(dbc)
                .readOnly()
                .withButton(Messages.browseButton_label)
                .onClickButton(this::browseFile)
                .useNativeRender()
                .createIn(parent);

        fileLocationText.focusButton();

        visibleTargetProjectObservable = new ComputedValue<>() {

            @Override
            protected Boolean calculate() {
                return filePathObserveValue.getValue() != null && !filePathObserveValue.getValue().isBlank();
            }

        };
        return parent;
    }

    protected void browseFile(Event e) {
        Optional.ofNullable(openFileDialog(Display.getDefault().getActiveShell()))
                .ifPresent(this::updateFilePath);
    }

    private void updateFilePath(String filePath) {
        fileLocationText.setText(filePath);
        if (archiveModel != null) {
            IObservableValue<IStatus> validationStatus = fileLocationText
                    .getValueBinding()
                    .getValidationStatus();
            validationStatus.setValue(archiveModel.getValidationStatus());
        }
    }

    protected void parseArchive(ValueChangeEvent<? extends String> e) {
        Optional.ofNullable(e.diff.getNewValue()).ifPresent(arhivePath -> {
            File myFile = new File(arhivePath);
            if (!myFile.exists()) {
                try {
                    myFile = fetchArchive(arhivePath);
                } catch (FetchRemoteBosArchiveException ex) {
                    fileLocationText.getValueBinding().getValidationStatus().setValue(ValidationStatus
                            .error(String.format(Messages.cannotImportRemoteArchive, ex.getLocalizedMessage())));
                    return;
                }
            }
            archiveModel = parsedModels.computeIfAbsent(myFile.getAbsolutePath(), this::parseArchive);
        });
    }

    private File fetchArchive(String filePath) throws FetchRemoteBosArchiveException {
        if (urlTempPath != null && Objects.equals(filePath, urlTempPath.getOriginalURL())) {
            return urlTempPath.getTmpPath().toFile();
        } else if (urlTempPath != null && !Objects.equals(filePath, urlTempPath.getOriginalURL())) {
            try {
                Files.deleteIfExists(urlTempPath.getTmpPath());
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        FetchRemoteBosArchiveOperation operation = new FetchRemoteBosArchiveOperation(filePath);
        try {
            wizardContainer.run(true, false, operation::run);
        } catch (InvocationTargetException | InterruptedException ex) {
            exceptionDialogHandler.openErrorDialog(Display.getDefault().getActiveShell(),
                    Messages.errorOccuredWhileParsingBosArchive, ex);
        }
        if (!operation.getStatus().isOK()) {
            throw new FetchRemoteBosArchiveException(operation.getStatus().getException());
        }
        urlTempPath = operation.getURLTempPath();
        return urlTempPath.getTmpPath().toFile();
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
        BosArchive bosArchive = importArchiveModel.getBosArchive();
        fileLocationText
                .setMessage(String.format("%s %s (%s)",
                        Messages.bosArchiveName,
                        selectedFile.getName(),
                        bosArchive.getBonitaVersion()));
        Model mavenProject = bosArchive.getMavenProject();
        var rootProject = bosArchive.getRootMavenModel();
        ProjectMetadata metadata = mavenProject != null ? ProjectMetadata.read(mavenProject)
                : ProjectMetadata.fromBosFileName(selectedFile.getName());
        metadata.setIncludeAdminApp(true);
        if(rootProject != null) {
            var bonitaRuntimeVersion = ProjectMetadata.getBonitaRuntimeVersion(rootProject);
            if(bonitaRuntimeVersion != null) {
                metadata.setBonitaRuntimeVersion(bonitaRuntimeVersion);
                // Do not include admin app if bos file version is greater or equal to 9.0.0
                if(new ComparableVersion(bonitaRuntimeVersion).compareTo(new ComparableVersion("9.0.0")) >= 0){
                    metadata.setIncludeAdminApp(false);
                }
            }
        }
    
        List<String> existingProjectNames = repositoryAccessor.getAllRepositories().stream()
                .map(r -> Adapters.adapt(r, BonitaProject.class))
                .map(BonitaProject::getId)
                .collect(Collectors.toList());
        if (existingProjectNames.contains(metadata.getArtifactId())) {
            metadata.setArtifactId(StringIncrementer.getNextIncrement(metadata.getArtifactId(), existingProjectNames));
        }
        projectMetadataObservable.setValue(metadata);
        newProjectIdText.getValueBinding().validateTargetToModel();
        if (repositoryModeObservable.getValue() == RepositoryMode.NEW
                && !metadata.getArtifactId().isEmpty()) {
            switchRepositoryStrategy.setTargetRepository(metadata.getProjectId());
            importArchiveModel.resetStatus();
        }
        return importArchiveModel;
    }

    private ParseBosArchiveOperation newParseOperation(File selectedFile) {
        return new ParseBosArchiveOperation(selectedFile,
                mode == RepositoryMode.NEW ? FakeRepository.INSTANCE
                        : repositoryAccessor.getRepository(switchRepositoryStrategy.getTargetRepository()));
    }

    protected String openFileDialog(Shell shell) {
        final FileDialog fd = new FileDialog(shell, SWT.OPEN | SWT.SINGLE);
        fd.setText(Messages.importProcessTitle);
        fd.setFilterExtensions(new String[] { BOS_EXTENSION });
        return fd.open();
    }

    public ImportArchiveModel getArchiveModel() {
        return archiveModel;
    }

    public void setArchiveModel(ImportArchiveModel archiveModel) {
        this.archiveModel = archiveModel;
        if(filePath != null) {
            parsedModels.put(filePath, archiveModel);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ProjectMetadata getProjectMetadata() {
        return projectMetadata;
    }

    public void setProjectMetadata(ProjectMetadata projectMetadata) {
        this.projectMetadata = projectMetadata;
    }

    @Override
    public ImportArchiveModel get() {
        return archiveModel;
    }

}
