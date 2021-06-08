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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.artifact.versioning.ComparableVersion;
import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependencyVersion;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaMarketplace;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

/**
 * !!! HELLO !!!
 * Do not forget to add the required css class for the background
 * if you update the controls in the dependencies composite :)
 */
public class BonitaMarketplacePage implements ControlSupplier {

    public static final int ICON_SIZE = 64;

    public static final String CONNECTOR_TYPE = org.bonitasoft.studio.connectors.i18n.Messages.connectorType;
    public static final String ACTOR_FILTER_TYPE = org.bonitasoft.studio.identity.i18n.Messages.actorFilterType;
    public static final String ALL_TYPE = Messages.all;
    public static final String DATABASE_DRIVER_TYPE = Messages.databaseDriver;

    private static final Map<String, String> EXTENSIONS_TYPE = Map.of(
            CONNECTOR_TYPE, BonitaMarketplace.CONNECTOR_TYPE,
            ACTOR_FILTER_TYPE, BonitaMarketplace.ACTOR_FILTER_TYPE,
            DATABASE_DRIVER_TYPE, BonitaMarketplace.DATABASE_DRIVER_TYPE);

    private MavenProjectHelper helper = new MavenProjectHelper();
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

    private IThemeEngine engine;
    private Cursor cursorArrow;
    private Cursor cursorHand;

    private String[] extensionTypes;

    private IProject project;

    public BonitaMarketplacePage(IProject project, String... extensionTypes) {
        this.project = project;
        this.extensionTypes = extensionTypes;
    }

    public BonitaMarketplacePage(String... extensionTypes) {
        this(null, extensionTypes);
    }

    public BonitaMarketplacePage(IProject project) {
        this(project, ALL_TYPE, CONNECTOR_TYPE, ACTOR_FILTER_TYPE);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        this.wizardContainer = wizardContainer;

        cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);
        engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);

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
        Object selectedPage = event.getSelectedPage();
        if (selectedPage instanceof IWizardPage
                && Objects.equals(((IWizardPage) selectedPage).getControl(), mainComposite)
                && dependencies == null) {
            Display.getDefault().asyncExec(() -> {
                try {
                    wizardContainer.getShell().setDefaultButton(null);
                    wizardContainer.run(true, false, this::initVariables);
                    List<BonitaArtifactDependency> filteredDependencies = Stream
                            .concat(dependenciesUpdatable.stream(), newDependencies.stream()).filter(hasSelectedType())
                            .collect(Collectors.toList());
                    filterDependenciesByType(filteredDependencies);
                    displayFilteredDependencies(filteredDependencies);
                } catch (InvocationTargetException | InterruptedException e) {
                    new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(),
                            e);
                }
            });
        }
    }

    private Predicate<? super BonitaArtifactDependency> hasSelectedType() {
        return dep -> Stream.of(extensionTypes).map(EXTENSIONS_TYPE::get).anyMatch(t -> dep.getType().equals(t));
    }

    private void initVariables(IProgressMonitor monitor) {
        try {
            List<String> acceptedTypes = Arrays.asList(extensionTypes).stream()
                    .map(EXTENSIONS_TYPE::get).collect(Collectors.toList());
            dependencies = BonitaMarketplace.getInstance(monitor).getDependencies().stream()
                    .filter(dep -> acceptedTypes.contains(dep.getType()))
                    .collect(Collectors.toList());
            knownDependencies = project != null ? helper.getMavenModel(project).getDependencies()
                    : Collections.emptyList();
            splitDependencies();
        } catch (CoreException e) {
            new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
        }

    }

    private void createSearchComposite(Composite parent) {
        Composite searchComposite = new Composite(parent, SWT.NONE);
        searchComposite
                .setLayout(GridLayoutFactory.fillDefaults().numColumns(extensionTypes.length > 1 ? 2 : 1).create());
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
            String selectedType = Arrays.asList(extensionTypes).stream()
                    .filter(type -> Objects.equals(org.bonitasoft.studio.connectors.i18n.Messages.connectorType, type))
                    .findFirst().orElse(extensionTypes[0]);
            typeObservableValue.setValue(selectedType);
        } else {
            typeObservableValue = new WritableValue<>(extensionTypes[0], String.class);
        }
        typeObservableValue.addValueChangeListener(e -> applySearch());

        searchWidget = new SearchWidget.Builder()
                .fill()
                .grabVerticalSpace()
                .grabHorizontalSpace()
                .createIn(searchComposite);
        searchWidget.observeText(400, SWT.Modify).addValueChangeListener(e -> applySearch());
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
                        Display.getDefault().syncExec(() -> createDependencyControl(dependenciesComposite, dep));
                        monitor.worked(1);
                    });
                    monitor.done();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                new ExceptionDialogHandler().openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
            }
        }
        layoutDependenciesComposite();
    }

    private void layoutDependenciesComposite() {
        dependenciesComposite.layout();
        int shellWidth = dependenciesComposite.getShell().getSize().x;
        sc.setMinHeight(dependenciesComposite.computeSize(shellWidth, SWT.DEFAULT).y);
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
                || (Strings.hasText(dep.getDescription())
                        && dep.getDescription().toLowerCase().contains(searchValue.toLowerCase()));
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
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);

        if (addSeparator) {
            Label separator = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
            separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        }

        Composite depComposite = new Composite(composite, SWT.NONE);
        depComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        depComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        depComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);

        Label iconLabel = createLabel(depComposite, SWT.WRAP);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults().hint(ICON_SIZE, ICON_SIZE).create());
        iconLabel.setImage(icon);

        Composite contentComposite = new Composite(depComposite, SWT.NONE);
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

        Button addButton = latestCompatibleVersion.isPresent()
                ? createAddButton(dep, heading, updatable)
                : null;

        Label version = createLabel(heading, SWT.WRAP);
        version.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        version.setText(String.format("%s: %s", Messages.version, versionToDisplay));
        version.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));

        Label description = createLabel(contentComposite, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        if (Strings.hasText(dep.getDescription())) {
            description.setText(dep.getDescription());
        }

        if (updatable) {
            createUpdatableComposite(contentComposite);
        } else if (!latestCompatibleVersion.isPresent()) {
            createIncompatibleComposite(contentComposite);
        }

        if (addButton != null) {
            addSelectionListener(depComposite, addButton, dep, updatable);
            addSelectionListener(contentComposite, addButton, dep, updatable);
            addSelectionListener(heading, addButton, dep, updatable);
            addSelectionListener(title, addButton, dep, updatable);
            addSelectionListener(version, addButton, dep, updatable);
            addSelectionListener(description, addButton, dep, updatable);
            addSelectionListener(iconLabel, addButton, dep, updatable);
        }

        List<Control> hoverableControls = List
                .of(depComposite, contentComposite, heading, title, version, description, iconLabel, addButton)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        addHoverListener(hoverableControls);
    }

    private void addHoverListener(List<Control> controls) {
        controls.forEach(control -> control.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseExit(MouseEvent e) {
                controls.forEach(ctrl -> {
                    if (!(ctrl instanceof Button)) {
                        ctrl.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);
                        engine.applyStyles(ctrl, false);
                    }
                    ctrl.setCursor(cursorArrow);
                });
            }

            @Override
            public void mouseEnter(MouseEvent e) {
                controls.forEach(ctrl -> {
                    if (!(ctrl instanceof Button)) {
                        ctrl.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                                BonitaThemeConstants.WIZARD_HOVER_BACKGROUND);
                        engine.applyStyles(ctrl, false);
                    }
                    ctrl.setCursor(cursorHand);
                });
            }
        }));
    }

    private void addSelectionListener(Control control, Button addButton, BonitaArtifactDependency dep,
            boolean updatable) {
        control.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseUp(MouseEvent e) {
                Rectangle bounds = control.getBounds();
                if (e.x >= 0 && e.x <= bounds.width && e.y >= 0 && e.y <= bounds.height) {
                    addButton.setSelection(!addButton.getSelection());
                    addDependency(dep, addButton.getSelection(), updatable ? dependenciesToUpdate : dependenciesToAdd);
                }
            }
        });
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

    private Button createAddButton(BonitaArtifactDependency dep, Composite parent, boolean updatable) {
        Button button = new Button(parent, SWT.CHECK);
        button.setLayoutData(GridDataFactory.fillDefaults().create());
        button.addListener(SWT.Selection,
                e -> addDependency(dep, button.getSelection(), updatable ? dependenciesToUpdate : dependenciesToAdd));
        return button;
    }

    /**
     * If a dependency with the same groupId / artifactId is already present in the maven project, then:
     * - If the version of the existing dependency is lower than the version of the new dependency, then the new
     * dependency is added to the "updatable dependencies list".
     * - Else it is ignored (we do not offer the possibility to downgrade a dependency).
     * If a dependency is completely new, then is is added to the "new dependencies list".
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
        ComparableVersion existingV = new ComparableVersion(existingVersion);
        ComparableVersion newV = new ComparableVersion(newVersion);
        return existingV.compareTo(newV) >= 0;
    }

    private void addDependency(BonitaArtifactDependency dep, boolean isSelected,
            Collection<BonitaArtifactDependency> collection) {
        if (isSelected) {
            collection.add(dep);
        } else {
            collection.remove(dep);
        }
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
