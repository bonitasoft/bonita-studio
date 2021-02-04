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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.assertj.core.util.Strings;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactDependencyLoader;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * !!! HELLO !!!
 * Do not forget to add the required css class for the background
 * if you update the controles in the dependencies composite :)
 */
public class ExtendProjectPage implements ControlSupplier {

    private static final String DEPENDENCIES_FILE = "https://raw.githubusercontent.com/alachambre/bonita-artifacts-provider/master/provided-dependencies.json";
    private static final String CONNECTOR_TYPE = "Connector";
    private static final String ACTOR_FILTER_TYPE = "Actor filter";
    private static final int ICON_SIZE = 64;

    private MavenProjectHelper helper = new MavenProjectHelper();
    private RepositoryAccessor repositoryAccessor;
    private LocalResourceManager manager;
    private List<Image> iconsToDispose = new ArrayList<>();
    private TextWidget searchWidget;
    private Composite dependenciesComposite;
    private List<Dependency> knownDependencies;
    private List<BonitaArtifactDependency> dependencies;
    private List<BonitaArtifactDependency> dependenciesToAdd = new ArrayList<>();
    private ComboWidget typeComboWidget;
    private ISWTObservableValue typeObservableValue;
    private ScrolledComposite sc;
    private IWizardContainer wizardContainer;
    private Composite mainComposite;

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        this.wizardContainer = wizardContainer;
        manager = new LocalResourceManager(JFaceResources.getResources(), parent);

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
        sc.setMinHeight(dependenciesComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

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
                wizardContainer.run(true, false, monitor -> {
                    monitor.beginTask(Messages.fetchingExtensions, IProgressMonitor.UNKNOWN);
                    initVariables();

                    List<BonitaArtifactDependency> dependenciesToDisplay = dependencies.stream()
                            .filter(dep -> !isKnownDependency(dep, knownDependencies))
                            .collect(Collectors.toList());

                    monitor.beginTask(Messages.fetchingExtensions, dependenciesToDisplay.size());
                    dependenciesToDisplay.forEach(dep -> {
                        Display.getDefault().syncExec(() -> {
                            createDependencyControl(dependenciesComposite, dep);
                        });
                        monitor.worked(1);
                    });
                    // TODO what if all provided dependencies are already installed? 
                    Display.getDefault().asyncExec(() -> {
                        dependenciesComposite.layout();
                        sc.setMinHeight(dependenciesComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
                        sc.layout();
                    });
                    monitor.done();
                });
            } catch (InvocationTargetException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void initVariables() {
        try {
            dependencies = loadDependencies();
            knownDependencies = helper.getMavenModel(getProject()).getDependencies();
        } catch (CoreException e) {
            throw new RuntimeException(e);
        }

    }

    private void createSearchComposite(Composite parent) {
        Composite searchComposite = new Composite(parent, SWT.NONE);
        searchComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        searchComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        typeComboWidget = new ComboWidget.Builder()
                .withLabel(Messages.type)
                .withItems(Messages.all, Messages.connectorType, Messages.actorFilterType)
                .fill()
                .readOnly()
                .createIn(searchComposite);
        typeObservableValue = typeComboWidget.observeComboText();
        typeObservableValue.setValue(Messages.all);

        searchWidget = new SearchWidget.Builder()
                .fill()
                .grabHorizontalSpace()
                .createIn(searchComposite);

        Button goButton = new Button(searchComposite, SWT.PUSH);
        goButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).create());
        goButton.setText(Messages.go);
        goButton.addListener(SWT.Selection, e -> applySearch());
    }

    private void applySearch() {
        Display.getDefault().asyncExec(() -> {
            String searchValue = searchWidget.getText();
            Arrays.asList(dependenciesComposite.getChildren()).forEach(Control::dispose);
            List<BonitaArtifactDependency> filteredDependencies = new ArrayList<>();
            if (Strings.isNullOrEmpty(searchValue)) {
                dependencies.forEach(filteredDependencies::add);
            } else {
                dependencies.stream()
                        .filter(dep -> dep.getName().contains(searchValue))
                        .forEach(filteredDependencies::add);
            }
            if (!Objects.equals(typeObservableValue.getValue(), Messages.all)) {
                String type = Objects.equals(typeObservableValue.getValue(), Messages.connectorType)
                        ? CONNECTOR_TYPE
                        : ACTOR_FILTER_TYPE;
                filteredDependencies.removeIf(dep -> !Objects.equals(dep.getType(), type));
            }
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
        });
    }

    private void createNoResultFoundLabel(Composite parent) {
        Label noResultFoundLabel = createLabel(parent, SWT.WRAP);
        noResultFoundLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        noResultFoundLabel.setText(Messages.noResultFound);
        noResultFoundLabel.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));
    }

    protected TextWidget createSearchField(Composite parent) {
        return new SearchWidget.Builder()
                .fill()
                .grabHorizontalSpace()
                .withButton(Messages.go)
                .onClickButton(e -> System.out.println(""))
                .createIn(parent);
    }

    private List<BonitaArtifactDependency> loadDependencies() {
        URL dependenciesFile;
        try {
            dependenciesFile = new URL(DEPENDENCIES_FILE);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e); // TODO ? 
        }
        return new ArtifactDependencyLoader().load(dependenciesFile);
    }

    private void createDependencyControl(Composite parent, BonitaArtifactDependency dep) {
        boolean addSeparator = parent.getChildren().length > 0;

        Image icon = getIcon(dep);

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
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 10).create());
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        contentComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);

        Label title = createLabel(contentComposite, SWT.WRAP);
        title.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        title.setText(String.format("%s", dep.getName()));
        title.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.HEADER_FONT));

        createAddButton(dep, contentComposite);

        Label version = createLabel(contentComposite, SWT.WRAP);
        version.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).indent(0, -5).create());
        version.setText(String.format("%s: %s", Messages.version, dep.getVersion()));
        version.setFont(JFaceResources.getFontRegistry().getItalic(JFaceResources.DEFAULT_FONT));

        Label description = createLabel(contentComposite, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).indent(0, 5).create());
        description.setText(dep.getDescription());
    }

    private void createAddButton(BonitaArtifactDependency dep, Composite parent) {
        Button button = new Button(parent, SWT.CHECK);
        button.setLayoutData(GridDataFactory.fillDefaults().create());
        button.addListener(SWT.Selection, e -> addDependency(dep, button.getSelection()));
    }

    private boolean isKnownDependency(BonitaArtifactDependency dep, List<Dependency> knownDependencies) {
        return knownDependencies.stream()
                .anyMatch(aDep -> Objects.equals(dep.getGroupId(), aDep.getGroupId())
                        && Objects.equals(dep.getArtifactId(), aDep.getArtifactId())
                        && Objects.equals(aDep.getVersion(), aDep.getVersion()));
    }

    private Image getIcon(BonitaArtifactDependency dep) {
        Image icon = null;
        if (dep.getIcon() != null) {
            try {
                icon = getIcon(new URL(dep.getIcon()));
            } catch (MalformedURLException e) {
                BonitaStudioLog.error(e);
            }
        }
        if (icon == null) {
            return Objects.equals(dep.getType(), CONNECTOR_TYPE)
                    ? Pics.getImage(PicsConstants.connectorDefaultIcon)
                    : Pics.getImage(PicsConstants.actorfilterDefaultIcon);
        }
        if (icon.getBounds().height != ICON_SIZE || icon.getBounds().width != ICON_SIZE) {
            icon = resize(icon);
        }
        return icon;
    }

    private Image getIcon(URL url) {
        try {
            ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url);
            return manager.createImage(imageDescriptor);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    private Image resize(Image image) {
        Image scaled = new Image(Display.getDefault(), ICON_SIZE, ICON_SIZE);
        GC gc = new GC(scaled);
        gc.setAntialias(SWT.ON);
        gc.setInterpolation(SWT.HIGH);
        gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, ICON_SIZE,
                ICON_SIZE);
        gc.dispose();
        iconsToDispose.add(scaled);
        return scaled;
    }

    private void addDependency(BonitaArtifactDependency dep, boolean isSelected) {
        if (isSelected) {
            dependenciesToAdd.add(dep);
        } else {
            dependenciesToAdd.remove(dep);
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

    private Label createLabel(Composite parent, int style) {
        Label label = new Label(parent, style);
        label.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.WIZARD_HIGHLIGHT_BACKGROUND);
        return label;
    }

}
