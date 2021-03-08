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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.RegExpValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.FileInputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.core.maven.FileDependencyLookupOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenRepositoryRegistry;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup.Status;
import org.bonitasoft.studio.common.widgets.CustomStackLayout;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.widget.TextWidget.Builder;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.m2e.core.repository.IRepository;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

public class ImportExtensionPage implements ControlSupplier {

    private static final String MAVEN_REPO_CONFIG_DOC_URL = "https://maven.apache.org/settings.html#Repositories";

    public enum ImportMode {
        MANUAL, FILE
    }

    private Dependency dependency;
    private SelectObservableValue<ImportMode> importModeObservable;
    private Composite manualComposite;
    private Composite archiveComposite;
    private String filePath;
    private TextWidget filePathText;
    private MavenRepositoryRegistry mavenRepositoryRegistry;
    private IWizardContainer wizardContainer;
    private IObservableValue<Dependency> dependencyObservable;
    private IObservableValue<Boolean> editableDependencyObservable;
    private IObservableValue<Boolean> visibleDependencyObservable;
    private IObservableValue<DependencyLookup> dependencyLookupObservable;
    private IObservableValue<String> filePathObserveValue;
    private DependencyLookup dependencyLookup;

    public ImportExtensionPage(MavenRepositoryRegistry mavenRepositoryRegistry) {
        this.mavenRepositoryRegistry = mavenRepositoryRegistry;
        this.dependency = new Dependency();
        this.dependency.setType("jar");
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        this.wizardContainer = wizardContainer;

        this.dependencyObservable = PojoProperties.value("dependency", Dependency.class).observe(this);
        this.dependencyLookupObservable = PojoProperties.value("dependencyLookup", DependencyLookup.class).observe(this);
        
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createRadioButtons(mainComposite);

        createStackComposite(mainComposite, ctx);

        createMavenCoordinatesGroup(mainComposite, ctx);
        importModeObservable.setValue(ImportMode.MANUAL);
        importModeObservable.addValueChangeListener(e -> {
            if (e.diff.getNewValue() == ImportMode.MANUAL) {
                editableDependencyObservable.setValue(true);
            } else if (dependencyLookup != null && dependencyLookup.getStatus() == Status.FOUND) {
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
        manualCoordinateLink.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        manualCoordinateLink.setText(Messages.importRemoteDependencyTip);
        manualCoordinateLink.addSelectionListener(new OpenBrowserListener(MAVEN_REPO_CONFIG_DOC_URL));
    }

    private void createFromArchiveComposite(Composite parent, DataBindingContext ctx) {
        archiveComposite = new Composite(parent, SWT.NONE);
        archiveComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        archiveComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Label fromFileLabel = new Label(archiveComposite, SWT.WRAP);
        fromFileLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        fromFileLabel.setText(Messages.importFromFileTip);

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
        filePathObserveValue.addValueChangeListener(this::analyzeFile);
        filePathText = new TextWidget.Builder()
                .withLabel(Messages.file)
                .grabHorizontalSpace()
                .fill()
                .alignMiddle()
                .labelAbove()
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new MultiValidator.Builder()
                                .havingValidators(filePathValidator()))
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
        return filePath -> {
            if (importModeObservable.getValue() == ImportMode.FILE && (filePath == null || filePath.isBlank())) {
                return ValidationStatus.error("");
            }
            return ValidationStatus.ok();
        };
    }

    private void browseFile(Event e) {
        Optional.ofNullable(openFileDialog(Display.getDefault().getActiveShell()))
                .ifPresent(filePathText::setText);
    }

    private String openFileDialog(Shell shell) {
        final FileDialog fd = new FileDialog(shell, SWT.OPEN | SWT.SINGLE);
        fd.setFilterExtensions(new String[] { "*.jar;*.zip" });
        return fd.open();
    }

    private void analyzeFile(ValueChangeEvent<? extends String> event) {
        String path = event.diff.getNewValue();
        if (path != null) {
            File file = new File(path);
            if (file.exists()) {
                try (InputStreamSupplier iss = new FileInputStreamSupplier(file)) {
                    FileDependencyLookupOperation operation = new FileDependencyLookupOperation(iss);
                    wizardContainer.run(true, false, mavenRepositoryRegistry.updateRegistry());
                    mavenRepositoryRegistry.getGlobalRepositories().stream()
                            .map(IRepository::getUrl)
                            .forEach(operation::addRemoteRespository);
                    wizardContainer.run(true, false, operation);
                    dependencyLookup = operation.getResult();
                    if (file.getName().endsWith(".zip")) {
                        dependencyLookup.setType("zip");
                    }
                    dependencyLookupObservable.setValue(dependencyLookup);
                    dependencyObservable.setValue(dependencyLookup.toMavenDependency());
                    Image image = Pics
                            .getImageDescriptor(
                                    dependencyLookup.getStatus() == Status.FOUND ? PicsConstants.checkmark
                                            : PicsConstants.warning)
                            .createImage();
                    String message = dependencyLookup.getStatus() == Status.FOUND
                            ? String.format(Messages.resolvedDependency, dependencyLookup.getRepository())
                            : Messages.cannotResolveDependencyInstalledLocally;
                    filePathText.setMessage(message, image);
                    editableDependencyObservable.setValue(dependencyLookup.getStatus() != Status.FOUND);
                } catch (Exception e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    private void createMavenCoordinatesGroup(Composite parent, DataBindingContext ctx) {
        Group dependencyGroup = new Group(parent, SWT.NONE);
        dependencyGroup.setText(Messages.dependencyCoordinate);
        dependencyGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        dependencyGroup.setLayout(
                GridLayoutFactory.fillDefaults().margins(20, 20).spacing(LayoutConstants.getSpacing().x, 10).create());

        editableDependencyObservable = new WritableValue<>(true, Boolean.class);
        visibleDependencyObservable = new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return importModeObservable.getValue() == ImportMode.MANUAL || dependencyLookupObservable.getValue() != null ;
            }
            
        };

        // We do not want to translate the maven property names, it would lead to too many confusions.
        TextWidget groupIdText = createText(dependencyGroup,
                "Group ID",
                PojoProperties.value("groupId", String.class).observeDetail(dependencyObservable),
                ctx,
                true,
                List.of(new EmptyInputValidator("Group ID"),
                        new RegExpValidator("Invalid format", "[A-Za-z0-9_\\-.]+")));
        groupIdText.setFocus();
        ctx.bindValue(groupIdText.observeEnable(), editableDependencyObservable);

        TextWidget artifactIdText = createText(dependencyGroup,
                "Artifact ID",
                PojoProperties.value("artifactId", String.class).observeDetail(dependencyObservable),
                ctx,
                true,
                List.of(new EmptyInputValidator("Artifact ID"),
                        new RegExpValidator("Invalid format", "[A-Za-z0-9_\\-.]+")));
        ctx.bindValue(artifactIdText.observeEnable(), editableDependencyObservable);

        TextWidget versionText = createText(dependencyGroup,
                "Version",
                PojoProperties.value("version", String.class).observeDetail(dependencyObservable),
                ctx,
                true,
                List.of(new EmptyInputValidator("Version")));
        ctx.bindValue(versionText.observeEnable(), editableDependencyObservable);

        Composite subPropertiesComposite = new Composite(dependencyGroup, SWT.NONE);
        subPropertiesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        subPropertiesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        ComboWidget typeCombo = createCombo(subPropertiesComposite, "Type",
                PojoProperties.value("type", String.class).observeDetail(dependencyObservable),
                ctx);
        ctx.bindValue(typeCombo.observeEnable(), editableDependencyObservable);

        TextWidget classifierText = createText(subPropertiesComposite, "Classifier",
                PojoProperties.value("classifier", String.class).observeDetail(dependencyObservable),
                ctx,
                false,
                List.of());
        ctx.bindValue(classifierText.observeEnable(), editableDependencyObservable);
        ctx.bindValue(WidgetProperties.visible().observe(dependencyGroup),
                visibleDependencyObservable, 
                neverUpdateValueStrategy().create(), 
                null);
    }

    private TextWidget createText(Composite parent,
            String label,
            IObservableValue<String> binding,
            DataBindingContext ctx,
            boolean mandatory,
            List<IValidator> validators) {
        Builder builder = new TextWidget.Builder()
                .withLabel(label + (mandatory ? " *" : ""))
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
                .inShell();
        return builder.createIn(parent);
    }

    private ComboWidget createCombo(Composite parent,
            String label,
            IObservableValue<String> binding,
            DataBindingContext ctx) {
        ComboWidget.Builder builder = new ComboWidget.Builder()
                .withLabel(label)
                .readOnly()
                .withItems("jar", "zip")
                .labelAbove()
                .grabHorizontalSpace()
                .bindTo(binding)
                .inContext(ctx)
                .fill()
                .inShell();
        return builder.createIn(parent);
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

    class OpenBrowserListener extends SelectionAdapter {

        private String url;

        OpenBrowserListener(String url) {
            this.url = url;
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            try {
                java.awt.Desktop.getDesktop().browse(new URI(url));
            } catch (final IOException | URISyntaxException ex) {
                BonitaStudioLog.error(ex);
            }
        }
    }

}
