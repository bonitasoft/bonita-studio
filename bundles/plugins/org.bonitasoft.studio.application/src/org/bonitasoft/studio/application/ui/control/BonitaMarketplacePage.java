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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependencyVersion;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaMarketplace;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.osgi.framework.Version;

/**
 * !!! HELLO !!!
 * Do not forget to add the required css class for the background
 * if you update the controls in the dependencies composite :)
 */
public class BonitaMarketplacePage implements ControlSupplier {

    public static final int ICON_SIZE = 64;

    public static final String CONNECTOR_TYPE = Messages.connectorType;
    public static final String ACTOR_FILTER_TYPE = Messages.actorFilterType;
    public static final String ALL_TYPE = Messages.all;
    public static final String DATABASE_DRIVER_TYPE = Messages.databaseDriver;

    private static final Map<String, String> EXTENSIONS_TYPE = Map.of(
            CONNECTOR_TYPE, BonitaMarketplace.CONNECTOR_TYPE,
            ACTOR_FILTER_TYPE, BonitaMarketplace.ACTOR_FILTER_TYPE,
            DATABASE_DRIVER_TYPE, BonitaMarketplace.DATABASE_DRIVER_TYPE);

    private MavenProjectHelper helper = new MavenProjectHelper();
    private RepositoryAccessor repositoryAccessor;
    private List<Image> iconsToDispose = new ArrayList<>();
    private TextWidget searchWidget;
    private Composite dependenciesComposite;
    private List<Dependency> knownDependencies;
    private List<BonitaArtifactDependency> dependencies;
    private List<BonitaArtifactDependency> newDependencies = new ArrayList<>();
    private List<BonitaArtifactDependency> dependenciesUpdatable = new ArrayList<>();
    private List<BonitaArtifactDependency> dependenciesToAdd = new ArrayList<>();
    private List<BonitaArtifactDependency> dependenciesToUpdate = new ArrayList<>();
    private IObservableValue<String> typeObservableValue;
    private ScrolledComposite sc;
    private IWizardContainer wizardContainer;
    private Composite mainComposite;

    private Button findButton;

    private String[] extensionTypes;

    public BonitaMarketplacePage(String... extensionTypes) {
        this.extensionTypes = extensionTypes;
    }

    public BonitaMarketplacePage() {
        this(ALL_TYPE, CONNECTOR_TYPE, ACTOR_FILTER_TYPE);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        this.wizardContainer = wizardContainer;

        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(15, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createSearchComposite(mainComposite);

        sc = new ScrolledComposite(mainComposite, SWT.V_SCROLL | SWT.BORDER);
        sc.setLayout(GridLayoutFactory.fillDefaults().create());
        sc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        dependenciesComposite = new Composite(sc, SWT.NONE);
        dependenciesComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 5, 20).create());
        dependenciesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        dependenciesComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);
        dependenciesComposite.setBackgroundMode(SWT.INHERIT_FORCE);

        sc.setContent(dependenciesComposite);
        sc.setExpandVertical(true);
        sc.setExpandHorizontal(true);

        wizardContainer.getShell().addDisposeListener(e -> iconsToDispose.forEach(Image::dispose));

        return mainComposite;
    }

    /**
     * Only triggered when wizard opens, but after intital control creation -> Allow us to display properly the fetching
     * progress to the user using the wizard progress abr
     */
    @Override
    public void pageChanged(PageChangedEvent event) {
        Display.getDefault().asyncExec(() -> {
            try {
                wizardContainer.getShell().setDefaultButton(null);
                wizardContainer.run(true, false, monitor -> {
                    initVariables(monitor);

                    int totalWork = newDependencies.size() + dependenciesUpdatable.size();
                    monitor.beginTask(Messages.fetchingExtensions, totalWork);
                    dependenciesUpdatable.stream().filter(hasSelectedType()).forEach(dep -> {
                        Display.getDefault().syncExec(() -> {
                            createDependencyControl(dependenciesComposite, dep);
                        });
                        monitor.worked(1);
                    });
                    newDependencies.stream().filter(hasSelectedType()).forEach(dep -> {
                        Display.getDefault().syncExec(() -> {
                            createDependencyControl(dependenciesComposite, dep);
                        });
                        monitor.worked(1);
                    });
                    // TODO what if all provided dependencies are already installed? 
                    Display.getDefault().asyncExec(() -> {
                        dependenciesComposite.layout();
                        sc.setContent(dependenciesComposite);
                        Point size = dependenciesComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                        sc.setMinHeight(size.y);
                        sc.layout();
                    });
                    monitor.done();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Predicate<? super BonitaArtifactDependency> hasSelectedType() {
        return dep -> Stream.of(extensionTypes).map(EXTENSIONS_TYPE::get).anyMatch(t -> dep.getType().equals(t));
    }

    private void initVariables(IProgressMonitor monitor) {
        try {
            dependencies = BonitaMarketplace.getInstance(monitor).getDependencies();
            knownDependencies = helper.getMavenModel(getProject()).getDependencies();
            splitDependencies();
        } catch (CoreException e) {
            throw new RuntimeException(e);
        }

    }

    private void createSearchComposite(Composite parent) {
        Composite searchComposite = new Composite(parent, SWT.NONE);
        searchComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(extensionTypes.length > 1 ? 3 : 2).create());
        searchComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        if (extensionTypes.length > 1) {
            var filterComposite = new Composite(searchComposite, SWT.NONE);
            filterComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
            filterComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

            Label typeLabel = new Label(filterComposite, SWT.NONE);
            typeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
            typeLabel.setText(Messages.type);

            Combo combo = new Combo(filterComposite, SWT.READ_ONLY);
            combo.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, true).create());
            combo.setItems(extensionTypes);
            typeObservableValue = WidgetProperties.text().observe(combo);
            typeObservableValue.setValue(extensionTypes[0]);
        } else {
            typeObservableValue = new WritableValue<>(extensionTypes[0], String.class);
        }

        searchWidget = new SearchWidget.Builder()
                .fill()
                .grabVerticalSpace()
                .grabHorizontalSpace()
                .createIn(searchComposite);

        findButton = new Button(searchComposite, SWT.PUSH);
        findButton.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(false, true).create());
        findButton.setText(Messages.find);
        findButton.addListener(SWT.Selection, e -> applySearch());

        searchWidget.addTraverseListener(new TraverseListener() {

            @Override
            public void keyTraversed(final TraverseEvent event) {
                if (event.detail == SWT.TRAVERSE_RETURN) {
                    applySearch();
                }
            }
        });
    }

    private void applySearch() {
        Display.getDefault().asyncExec(() -> {
            String searchValue = searchWidget.getText();
            Arrays.asList(dependenciesComposite.getChildren()).forEach(Control::dispose);
            List<BonitaArtifactDependency> filteredDependencies = filterDependenciesBySearchValue(searchValue);
            filterDependenciesByType(filteredDependencies);
            displayFilteredDependencies(filteredDependencies);
        });
    }

    private void displayFilteredDependencies(List<BonitaArtifactDependency> filteredDependencies) {
        if (filteredDependencies.isEmpty()) {
            createNoResultFoundLabel(dependenciesComposite);
        } else {
            try {
                wizardContainer.run(true, false, monitor -> {
                    monitor.beginTask(Messages.filteringExtensions, filteredDependencies.size());
                    filteredDependencies.forEach(dep -> {
                        Display.getDefault().syncExec(() -> {
                            createDependencyControl(dependenciesComposite, dep);
                        });
                        monitor.worked(1);
                    });
                    monitor.done();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        dependenciesComposite.layout();
        sc.setMinHeight(dependenciesComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        sc.layout();
    }

    private void filterDependenciesByType(List<BonitaArtifactDependency> filteredDependencies) {
        if (!Objects.equals(typeObservableValue.getValue(), ALL_TYPE)) {
            var type = EXTENSIONS_TYPE.get(typeObservableValue.getValue());
            filteredDependencies.removeIf(dep -> !Objects.equals(dep.getType(), type));
        }
    }

    private List<BonitaArtifactDependency> filterDependenciesBySearchValue(String searchValue) {
        List<BonitaArtifactDependency> filteredDependencies = new ArrayList<>();
        if (searchValue == null || searchValue.isBlank()) {
            dependenciesUpdatable.forEach(filteredDependencies::add);
            newDependencies.forEach(filteredDependencies::add);
        } else {
            dependenciesUpdatable
                    .stream()
                    .filter(dep -> matchSearch(dep, searchValue))
                    .forEach(filteredDependencies::add);
            newDependencies
                    .stream()
                    .filter(dep -> matchSearch(dep, searchValue))
                    .forEach(filteredDependencies::add);
        }
        return filteredDependencies;
    }

    private boolean matchSearch(BonitaArtifactDependency dep, String searchValue) {
        return dep.getName().toLowerCase().contains(searchValue.toLowerCase())
                || dep.getDescription().toLowerCase().contains(searchValue.toLowerCase());
    }

    private void createNoResultFoundLabel(Composite parent) {
        Label noResultFoundLabel = createLabel(parent, SWT.WRAP);
        noResultFoundLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        noResultFoundLabel.setText(Messages.noResultFound);
        noResultFoundLabel.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));
    }

    private void createDependencyControl(Composite parent, BonitaArtifactDependency dep) {
        boolean addSeparator = parent.getChildren().length > 0;

        Image icon = dep.getIconImage();

        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);

        if (addSeparator) {
            Label separator = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
            separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        }

        Label iconLabel = createLabel(composite, SWT.WRAP);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults().hint(ICON_SIZE, ICON_SIZE).create());
        iconLabel.setImage(icon);

        Composite contentComposite = new Composite(composite, SWT.NONE);
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 10).create());
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        contentComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);

        Composite heading = new Composite(contentComposite, SWT.NONE);
        heading.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 0).create());
        heading.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        heading.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);

        Label title = createLabel(heading, SWT.WRAP);
        title.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        title.setText(dep.getName());
        title.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.HEADER_FONT));

        Optional<BonitaArtifactDependencyVersion> latestCompatibleVersion = dep.getLatestCompatibleVersion();
        String versionToDisplay = latestCompatibleVersion
                .map(BonitaArtifactDependencyVersion::getVersion)
                .orElseGet(() -> dep.getVersions().stream()
                        .sorted().findFirst()
                        .map(BonitaArtifactDependencyVersion::getVersion)
                        .orElse(""));
        boolean updatable = dependenciesUpdatable.contains(dep);

        if (latestCompatibleVersion.isPresent()) {
            createAddButton(dep, heading, updatable);
        }

        Label version = createLabel(heading, SWT.WRAP);
        version.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        version.setText(String.format("%s: %s", Messages.version, versionToDisplay));
        version.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));

        Label description = createLabel(contentComposite, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().hint(620, SWT.DEFAULT).create());
        description.setText(dep.getDescription());

        if (updatable) {
            createUpdatableComposite(contentComposite);
        } else if (!latestCompatibleVersion.isPresent()) {
            createIncompatibleComposite(contentComposite);
        }
        // TODO check definitions versions
    }

    private void createIncompatibleComposite(Composite parent) {
        Composite incompatibleComposite = new Composite(parent, SWT.NONE);
        incompatibleComposite
                .setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        incompatibleComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).indent(0, 5).create());
        incompatibleComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);

        Label versionIncompatibleTitle = createLabel(incompatibleComposite, SWT.WRAP);
        versionIncompatibleTitle.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        versionIncompatibleTitle.setText(Messages.incompatibleExtensionTitle);
        versionIncompatibleTitle.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        versionIncompatibleTitle.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                BonitaThemeConstants.ERROR_TEXT_COLOR);

        Label versionIncompatibleLabel = createLabel(incompatibleComposite, SWT.WRAP);
        versionIncompatibleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        versionIncompatibleLabel.setText(Messages.incompatibleExtension);
        versionIncompatibleLabel.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));
        versionIncompatibleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                BonitaThemeConstants.ERROR_TEXT_COLOR);
    }

    private void createUpdatableComposite(Composite parent) {
        Composite updatableComposite = new Composite(parent, SWT.NONE);
        updatableComposite
                .setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
        updatableComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).indent(0, 5).create());
        updatableComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);

        Label newVersionAvailableLabel = createLabel(updatableComposite, SWT.WRAP);
        newVersionAvailableLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        newVersionAvailableLabel.setText(Messages.newVersionAvailable);
        newVersionAvailableLabel.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT));
        newVersionAvailableLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                BonitaThemeConstants.INFO_TEXT_COLOR);

        Label depUpdatableLabel = createLabel(updatableComposite, SWT.WRAP);
        depUpdatableLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        depUpdatableLabel.setText(Messages.dependencyUpdatable);
        depUpdatableLabel.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));
        depUpdatableLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.INFO_TEXT_COLOR);
    }

    private void createAddButton(BonitaArtifactDependency dep, Composite parent, boolean updatable) {
        Button button = new Button(parent, SWT.CHECK);
        button.setLayoutData(GridDataFactory.fillDefaults().create());
        button.addListener(SWT.Selection,
                e -> addDependency(dep, button.getSelection(), updatable ? dependenciesToUpdate : dependenciesToAdd));
    }

    /**
     * If a dependency with the same groupId / artifactId is already present in the maven project, then:
     * - If the version of the existing dependency is lower than the version of the new dependency, then the new
     * dependency is added to the "updatable dependencies list".
     * - Else it is ignored (we do not offer the possibility to downgrade a dependency).
     * If a dependency is completly new, then is is added to the "new dependencies list".
     */
    private void splitDependencies() {
        for (BonitaArtifactDependency dep : dependencies) {
            Optional<Dependency> matchingDependency = knownDependencies.stream()
                    .filter(aDep -> Objects.equals(dep.getGroupId(), aDep.getGroupId())
                            && Objects.equals(dep.getArtifactId(), aDep.getArtifactId()))
                    .findFirst();
            if (matchingDependency.isPresent()) {
                Optional<String> version = dep.getLatestCompatibleVersion()
                        .map(BonitaArtifactDependencyVersion::getVersion);
                if (version.isPresent()
                        && !existingVersionEqualsOrGreater(matchingDependency.get().getVersion(), version.get())) {
                    dependenciesUpdatable.add(dep);
                }
            } else {
                newDependencies.add(dep);
            }
        }
    }

    private boolean existingVersionEqualsOrGreater(String existingVersion, String newVersion) {
        Version existingV = new Version(existingVersion);
        Version newV = new Version(newVersion);

        return existingV.compareTo(newV) >= 0;
    }

    private void addDependency(BonitaArtifactDependency dep, boolean isSelected, Collection collection) {
        if (isSelected) {
            collection.add(dep);
        } else {
            collection.remove(dep);
        }
    }

    private IProject getProject() {
        if (repositoryAccessor == null) {
            repositoryAccessor = new RepositoryAccessor();
            repositoryAccessor.init();
        }
        return repositoryAccessor.getCurrentRepository().getProject();
    }

    public List<BonitaArtifactDependency> getDependenciesToAdd() {
        return dependenciesToAdd;
    }

    public List<BonitaArtifactDependency> getDependenciesToUpdate() {
        return dependenciesToUpdate;
    }

    private Label createLabel(Composite parent, int style) {
        Label label = new Label(parent, style);
        label.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);
        return label;
    }

}
