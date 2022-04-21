/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.FileInputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.FileDependencyLookupOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.common.repository.ui.validator.MavenIdValidator;
import org.bonitasoft.studio.common.widgets.CustomStackLayout;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.preferences.dialog.BonitaPreferenceDialog;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class ImportExtensionPage implements ControlSupplier {

    public enum ImportMode {
        MANUAL, FILE
    }

    protected Dependency dependency;
    private SelectObservableValue<ImportMode> importModeObservable;
    private Composite manualComposite;
    private Composite archiveComposite;
    private String filePath;
    private TextWidget filePathText;
    private MavenRepositoryRegistry mavenRepositoryRegistry;
    protected IWizardContainer wizardContainer;
    private IObservableValue<Dependency> dependencyObservable;
    private IObservableValue<Boolean> editableDependencyObservable;
    private IObservableValue<DependencyLookup> dependencyLookupObservable;
    private DependencyLookup dependencyLookup;
    private Model mavenModel;
    private MavenProjectHelper mavenProjectHelper = new MavenProjectHelper();
    private IObservableValue<Optional<Dependency>> sameExistingDependency;
    private IObservableValue<String> versionObservable;
    private Label dependencyAlreadyExistsLabel;
    private Label dependencyAlreadyExistsIcon;
    private IThemeEngine engine;
    private IObservableValue<IStatus> dependencyAlreadyExistsStatus = new WritableValue<>(ValidationStatus.ok(),
            IStatus.class);
    private Optional<Dependency> extensionToUpdate;
    private Optional<Boolean> isLocal;
    protected Composite mainComposite;
    private ExtensionTypeHandler extensionTypeHandler;
    private IObservableValue<String> filePathObserveValue;

    public ImportExtensionPage(MavenRepositoryRegistry mavenRepositoryRegistry,
            Model mavenModel,
            ExtensionTypeHandler extensionTypeHandler,
            Optional<Dependency> extensionToUpdate,
            Optional<Boolean> isLocal) {
        this.mavenRepositoryRegistry = mavenRepositoryRegistry;
        this.mavenModel = mavenModel;
        this.extensionTypeHandler = extensionTypeHandler;
        this.extensionToUpdate = extensionToUpdate;
        this.isLocal = isLocal;
        this.engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
        this.dependency = new Dependency();
        this.dependency.setType(extensionTypeHandler.getExtensionType());

        extensionToUpdate.ifPresent(dep -> {
            dependency = dep.clone();
            dependency.setVersion(null);
        });

        this.dependencyObservable = PojoProperties.value("dependency", Dependency.class).observe(this);
        this.dependencyLookupObservable = PojoProperties.value("dependencyLookup", DependencyLookup.class).observe(this);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        this.wizardContainer = wizardContainer;

        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults()
                .margins(10, 10)
                .extendedMargins(0, 0, 0, 30)
                .create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createRadioButtons(mainComposite);

        createStackComposite(mainComposite, ctx);

        createMavenCoordinatesGroup(mainComposite, ctx);

        if (isLocal.isPresent()) {
            importModeObservable.setValue(isLocal.get() ? ImportMode.FILE : ImportMode.MANUAL);
        } else {
            importModeObservable.setValue(ImportMode.MANUAL);
        }
        importModeObservable.addValueChangeListener(e -> {
            if (e.diff.getNewValue() == ImportMode.MANUAL) {
                editableDependencyObservable.setValue(true);
            } else if (isMavenDependency() || (dependencyLookup != null && dependencyLookup.getStatus() == Status.FOUND)) {
                editableDependencyObservable.setValue(false);
            }
            ctx.updateModels();
        });
        return mainComposite;
    }

    private void createStackComposite(Composite parent, DataBindingContext ctx) {
        Composite stackComposite = new Composite(parent, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        CustomStackLayout stackLayout = new CustomStackLayout(stackComposite);
        stackComposite.setLayout(stackLayout);

        createFromRemoteDependencyComposite(stackComposite);
        createFromArchiveComposite(stackComposite, ctx);

        ctx.bindValue(PojoProperties.value("topControl").observe(stackLayout), importModeObservable,
                UpdateStrategyFactory.neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(ConverterBuilder.<ImportMode, Composite> newConverter()
                                .fromType(ImportMode.class)
                                .toType(Composite.class)
                                .withConvertFunction(o -> o != null ? compositeFor(o) : null)
                                .create())
                        .create());
    }

    private void createFromRemoteDependencyComposite(Composite stackComposite) {
        manualComposite = new Composite(stackComposite, SWT.NONE);
        manualComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        manualComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Link manualCoordinateLink = new Link(manualComposite, SWT.WRAP);
        manualCoordinateLink.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .hint(650, SWT.DEFAULT).create());
        manualCoordinateLink
                .setText(String.format(Messages.importRemoteDependencyTip, extensionTypeHandler.getExtensionLabel()));
        manualCoordinateLink.addListener(SWT.Selection, e -> {
            BonitaPreferenceDialog dialog = new BonitaPreferenceDialog(new Shell(Display.getDefault()));
            dialog.create();
            dialog.setSelectedPreferencePage(BonitaPreferenceDialog.MAVEN_PAGE_ID);
            dialog.open();
        });
        createHintLabel(manualComposite);
    }

    private void createHintLabel(Composite parent) {
        extensionTypeHandler.getHintMessage().ifPresent(hint -> {
            var hintLabel = new CLabel(parent, SWT.WRAP);
            hintLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
            hintLabel.setText(hint);
            hintLabel.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO));
        });
    }

    private void createFromArchiveComposite(Composite parent, DataBindingContext ctx) {
        archiveComposite = new Composite(parent, SWT.NONE);
        archiveComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        archiveComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Label fromFileLabel = new Label(archiveComposite, SWT.WRAP);
        fromFileLabel.setLayoutData(GridDataFactory.fillDefaults()
                .grab(true, false)
                .hint(650, SWT.DEFAULT)
                .create());
        fromFileLabel.setText(String.format(Messages.importFromFileTip, extensionTypeHandler.getExtensionLabel()));

        createHintLabel(archiveComposite);
        createFileBrowser(archiveComposite, ctx);
    }

    private Composite createFileBrowser(Composite parent, DataBindingContext dbc) {
        Composite fileBrowserComposite = new Composite(parent, SWT.NONE);
        fileBrowserComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        fileBrowserComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        filePathObserveValue = PojoProperties
                .value("filePath", String.class)
                .observe(this);
        filePathObserveValue.addValueChangeListener(e -> analyzeFile(e, dbc));
        filePathText = new TextWidget.Builder()
                .withLabel(String.format(Messages.file, extensionTypeHandler.getExtensionType()))
                .grabHorizontalSpace()
                .fill()
                .alignMiddle()
                .labelAbove()
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(filePathValidator(), fileContentValidator()))
                        .create())
                .bindTo(filePathObserveValue)
                .inContext(dbc)
                .readOnly()
                .useNativeRender()
                .withButton(Messages.browse)
                .onClickButton(this::browseFile)
                .createIn(fileBrowserComposite);
        filePathText.focusButton();
        return parent;
    }

    private IValidator<String> filePathValidator() {
        return path -> {
            if (importModeObservable.getValue() == ImportMode.FILE && (path == null || path.isBlank())) {
                return ValidationStatus.error("");
            }
            return ValidationStatus.ok();
        };
    }

    private IValidator<String> fileContentValidator() {
        return path -> {
            if (importModeObservable.getValue() == ImportMode.FILE && !(path == null || path.isBlank())) {
                return extensionTypeHandler.getExtensionValidator().validate(new File(path));
            }
            return ValidationStatus.ok();
        };
    }

    private void browseFile(Event e) {
        Optional.ofNullable(openFileDialog(Display.getDefault().getActiveShell()))
                .ifPresent(filePathText::setText);
    }

    private String openFileDialog(Shell shell) {
        var fd = new FileDialog(shell, SWT.OPEN | SWT.SINGLE);
        fd.setFilterExtensions(new String[] { String.format("*.%s", extensionTypeHandler.getExtensionType()) });
        return fd.open();
    }

    private void analyzeFile(ValueChangeEvent<? extends String> event, DataBindingContext dbc) {
        String path = event.diff.getNewValue();
        if (path != null) {
            File file = new File(path);
            if (file.exists()) {
                createDependencyLookup(file);
                Dependency parsedDependency = dependencyLookup.toMavenDependency();
                if (extensionToUpdate.isEmpty()) {
                    dependencyObservable.setValue(parsedDependency);
                } else { // Update mode -> Only version can be updated 
                    versionObservable.setValue(parsedDependency.getVersion());
                }
                Image image = Pics
                        .getImageDescriptor(
                                dependencyLookup.getStatus() == Status.FOUND ? PicsConstants.checkmark
                                        : PicsConstants.warning)
                        .createImage();
                String message = dependencyLookup.getStatus() == Status.FOUND
                        ? String.format(Messages.resolvedDependency, dependencyLookup.getRepository())
                        : Messages.cannotResolveDependencyInstalledLocally;
                filePathText.setMessage(message, image);
                editableDependencyObservable
                        .setValue(!isMavenDependency() && (dependencyLookup.getStatus() != Status.FOUND));
                dbc.getBindings().forEach(Binding::validateTargetToModel);

            }
        }
    }

    protected void createDependencyLookup(File file) {
        try (InputStreamSupplier iss = new FileInputStreamSupplier(file)) {
            FileDependencyLookupOperation operation = new FileDependencyLookupOperation(iss);
            wizardContainer.run(true, false, mavenRepositoryRegistry.updateRegistry());
            mavenRepositoryRegistry.getGlobalRepositories().stream()
                    .map(IRepository::getUrl)
                    .forEach(operation::addRemoteRespository);
            wizardContainer.run(true, false, monitor -> {
                monitor.beginTask("", IProgressMonitor.UNKNOWN);
                operation.run(monitor);
            });
            dependencyLookup = operation.getResult();
            if (file.getName().endsWith(".zip")) {
                dependencyLookup.setType("zip");
            }
            dependencyLookupObservable.setValue(dependencyLookup);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    protected void createMavenCoordinatesGroup(Composite parent, DataBindingContext ctx) {
        Group dependencyGroup = new Group(parent, SWT.NONE);
        dependencyGroup.setText(Messages.dependencyCoordinate);
        dependencyGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        dependencyGroup.setLayout(GridLayoutFactory.fillDefaults()
                .margins(15, 15)
                .create());

        editableDependencyObservable = new WritableValue<>(true, Boolean.class);
        var visibleDependencyObservable = createGavVisibleComputedValue();

        // We do not want to translate the maven property names, it would lead to too many confusions.
        IObservableValue<String> groupIdObservable = PojoProperties.value("groupId", String.class)
                .observeDetail(dependencyObservable);
        TextWidget groupIdText = createText(dependencyGroup,
                "Group ID",
                Messages.groupIdTootltip,
                groupIdObservable,
                ctx,
                true,
                List.of(new MavenIdValidator("Group ID")));
        groupIdText.setFocus();
        Supplier<Boolean> gavEditableSupplier = createGavEditableSupplier();
        ctx.bindValue(new ComputedValueBuilder<Boolean>()
                .withSupplier(gavEditableSupplier)
                .build(), groupIdText.observeEnable(),
                updateValueStrategy().create(),
                neverUpdateValueStrategy().create());

        IObservableValue<String> artifactIdObservable = PojoProperties.value("artifactId", String.class)
                .observeDetail(dependencyObservable);
        TextWidget artifactIdText = createText(dependencyGroup,
                "Artifact ID",
                Messages.depArtifactIdTootltip,
                artifactIdObservable,
                ctx,
                true,
                List.of(new MavenIdValidator("Artifact ID")));
        ctx.bindValue(new ComputedValueBuilder<Boolean>()
                .withSupplier(gavEditableSupplier)
                .build(), artifactIdText.observeEnable(),
                updateValueStrategy().create(),
                neverUpdateValueStrategy().create());

        versionObservable = PojoProperties.value("version", String.class).observeDetail(dependencyObservable);
        TextWidget versionText = createText(dependencyGroup,
                "Version",
                null,
                versionObservable,
                ctx,
                true,
                List.of(new EmptyInputValidator("Version")));
        ctx.bindValue(versionText.observeEnable(), editableDependencyObservable);

        IObservableValue<String> typeObservable = PojoProperties.value("type", String.class)
                .observeDetail(dependencyObservable);

        IObservableValue<String> classifierObservable = PojoProperties.value("classifier", String.class)
                .observeDetail(dependencyObservable);
        TextWidget classifierText = createText(dependencyGroup,
                "Classifier",
                Messages.classifierTooltip,
                classifierObservable,
                ctx,
                false,
                List.of());
        ctx.bindValue(new ComputedValueBuilder<Boolean>()
                .withSupplier(gavEditableSupplier)
                .build(), classifierText.observeEnable(),
                updateValueStrategy().create(),
                neverUpdateValueStrategy().create());

        ctx.bindValue(WidgetProperties.visible().observe(dependencyGroup),
                visibleDependencyObservable,
                neverUpdateValueStrategy().create(),
                null);

        sameExistingDependency = new WritableValue<>(Optional.empty(), Optional.class);
        ctx.bindValue(new ComputedValueBuilder<Optional<Dependency>>()
                .withSupplier(() -> mavenProjectHelper.findDependencyInAnyVersion(mavenModel,
                        groupIdObservable.getValue(),
                        artifactIdObservable.getValue(),
                        typeObservable.getValue(),
                        classifierObservable.getValue()))
                .build(),
                sameExistingDependency,
                updateValueStrategy().create(),
                neverUpdateValueStrategy().create());

        createDependencyAlreadyExistsComposite(dependencyGroup, ctx);
    }

    protected ComputedValue<Boolean> createGavVisibleComputedValue() {
        return new ComputedValue<>() {

            @Override
            protected Boolean calculate() {
                return importModeObservable.getValue() == ImportMode.MANUAL || dependencyLookupObservable.getValue() != null;
            }

        };
    }

    protected Supplier<Boolean> createGavEditableSupplier() {
        return () -> !extensionToUpdate.isPresent() && editableDependencyObservable.getValue();
    }

    private boolean isMavenDependency() {
        return Objects.equals(importModeObservable.getValue(), ImportMode.FILE)
                && filePathObserveValue.getValue() != null
                && DependencyLookup.readPomProperties(new File(filePathObserveValue.getValue())).isPresent();
    }

    private void createDependencyAlreadyExistsComposite(Composite parent, DataBindingContext ctx) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(2, LayoutConstants.getSpacing().y)
                        .extendedMargins(0, 0, 15, 0).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        dependencyAlreadyExistsIcon = new Label(composite, SWT.WRAP);
        dependencyAlreadyExistsIcon
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).indent(0, 3).create());

        dependencyAlreadyExistsLabel = new Label(composite, SWT.WRAP);
        dependencyAlreadyExistsLabel
                .setLayoutData(GridDataFactory.fillDefaults().hint(400, SWT.DEFAULT).grab(true, true).create());

        ctx.addValidationStatusProvider(new ValidationStatusProvider() {

            @Override
            public IObservableValue<IStatus> getValidationStatus() {
                return dependencyAlreadyExistsStatus;
            }

            @Override
            public IObservableList<IObservable> getTargets() {
                return null;
            }

            @Override
            public IObservableList<IObservable> getModels() {
                return null;
            }
        });

        ctx.bindValue(new ComputedValueBuilder<IStatus>()
                .withSupplier(this::computeDependencyAlreadyExistsStatus)
                .build(), dependencyAlreadyExistsStatus,
                updateValueStrategy().create(),
                neverUpdateValueStrategy().create());

        ctx.bindValue(new ComputedValueBuilder<Boolean>()
                .withSupplier(
                        () -> sameExistingDependency.getValue() != null && sameExistingDependency.getValue().isPresent())
                .build(), WidgetProperties.visible().observe(composite),
                updateValueStrategy().create(),
                neverUpdateValueStrategy().create());
    }

    protected IStatus computeDependencyAlreadyExistsStatus() {
        if (sameExistingDependency.getValue() != null && sameExistingDependency.getValue().isPresent()) {
            String existingVersion = sameExistingDependency.getValue().get().getVersion();
            if (Objects.equals(existingVersion, versionObservable.getValue())) {
                updateDependencyAlreadyExistsLabel(
                        String.format(Messages.dependencyAlreadyExistsInSameVersion, existingVersion),
                        BonitaThemeConstants.WARNING_TEXT_COLOR, JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING));
                return ValidationStatus.warning(Messages.dependencyAlreadyExistsInSameVersion);
            }
            updateDependencyAlreadyExistsLabel(
                    String.format(Messages.dependencyAlreadyExistsInDifferentVersion, existingVersion),
                    BonitaThemeConstants.INFO_TEXT_COLOR, JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO));
            return ValidationStatus.ok();
        }
        updateDependencyAlreadyExistsLabel("", BonitaThemeConstants.INFO_TEXT_COLOR,
                JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO));
        return ValidationStatus.ok();
    }

    private void updateDependencyAlreadyExistsLabel(String message, String cssStyle, Image icon) {
        dependencyAlreadyExistsLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, cssStyle);
        engine.applyStyles(dependencyAlreadyExistsLabel, false);
        dependencyAlreadyExistsLabel.setText(message);
        dependencyAlreadyExistsIcon.setImage(icon);
        mainComposite.layout();
    }

    private TextWidget createText(Composite parent,
            String label,
            String tooltip,
            IObservableValue<String> binding,
            DataBindingContext ctx,
            boolean mandatory,
            List<IValidator> validators) {
        return new TextWidget.Builder()
                .withLabel(label + (mandatory ? " *" : ""))
                .withTootltip(tooltip)
                .labelAbove()
                .grabHorizontalSpace()
                .bindTo(binding)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(validators.toArray(IValidator[]::new))
                                .create())
                        .create())
                .inContext(ctx)
                .fill()
                .useNativeRender()
                .createIn(parent);
    }

    private Composite compositeFor(ImportMode importMode) {
        switch (importMode) {
            case MANUAL:
                return manualComposite;
            case FILE:
            default:
                return archiveComposite;
        }
    }

    private void createRadioButtons(Composite parent) {
        Composite radioComposite = new Composite(parent, SWT.NONE);
        radioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        radioComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.CENTER, SWT.FILL).create());

        Button mavenButton = new Button(radioComposite, SWT.RADIO);
        mavenButton.setText(Messages.manual);
        mavenButton.setLayoutData(GridDataFactory.fillDefaults().create());

        Button archiveButton = new Button(radioComposite, SWT.RADIO);
        archiveButton.setText(Messages.fromFile);
        archiveButton.setLayoutData(GridDataFactory.fillDefaults().create());

        importModeObservable = new SelectObservableValue<>();
        importModeObservable.addOption(ImportMode.MANUAL,
                WidgetProperties.buttonSelection().observe(mavenButton));
        importModeObservable.addOption(ImportMode.FILE,
                WidgetProperties.buttonSelection().observe(archiveButton));

    }

    public Dependency getDependency() {
        return dependency;
    }

    public DependencyLookup getDependencyLookup() {
        return dependencyLookup;
    }

    public void setDependencyLookup(DependencyLookup dependencyLookup) {
        this.dependencyLookup = dependencyLookup;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public ImportMode getImportMode() {
        return importModeObservable.getValue();
    }

}
