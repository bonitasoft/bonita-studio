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
package org.bonitasoft.studio.application.views.extension;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.application.handler.ImportExtensionHandler;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependencyConverter;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaMarketplace;
import org.bonitasoft.studio.application.views.extension.card.ExtensionCard;
import org.bonitasoft.studio.application.views.extension.card.ExtensionCardFactory;
import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.bonitasoft.studio.application.views.extension.card.zoom.Zoomable;
import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorPart;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.AppProjectConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.widget.DropdownDynamicButtonWidget;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

public class ExtensionComposite extends Composite {

    private RepositoryAccessor repositoryAccessor;
    private BonitaArtifactDependencyConverter bonitaArtifactDependencyConverter;
    private ExceptionDialogHandler errorHandler;
    private CommandExecutor commandExecutor;
    private UpdateExtensionListener upadateExtensionListener;
    private RemoveExtensionListener removeExtensionListener;
    private MavenProjectHelper mavenHelper;
    private DataBindingContext ctx;
    private Composite contentComposite;
    private ScrolledComposite scrolledComposite;
    private List<BonitaArtifactDependency> allDependencies;

    class BonitaDependencyTuple {

        Dependency dep;
        BonitaArtifactDependency bonitaDep;

        public BonitaDependencyTuple(Dependency dep, BonitaArtifactDependency bonitaDep) {
            this.dep = dep;
            this.bonitaDep = bonitaDep;
        }

        public Dependency getDep() {
            return dep;
        }

        public BonitaArtifactDependency getBonitaDep() {
            return bonitaDep;
        }
    }

    public ExtensionComposite(Composite parent, RepositoryAccessor repositoryAccessor) {
        super(parent, SWT.NONE);
        this.repositoryAccessor = repositoryAccessor;
        initVariables();

        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        setLayout(GridLayoutFactory.fillDefaults().margins(20, 20).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        scrolledComposite = createExtensionSection(this);
    }

    private void initVariables() {
        mavenHelper = new MavenProjectHelper();
        ctx = new DataBindingContext();

        var currentRepository = repositoryAccessor.getCurrentRepository().orElseThrow();
        bonitaArtifactDependencyConverter = new BonitaArtifactDependencyConverter(
                currentRepository.getProjectDependenciesStore(),
                currentRepository.getLocalDependencyStore(),
                currentRepository.getProjectId());

        var eclipseContext = EclipseContextFactory.create();
        errorHandler = ContextInjectionFactory.make(ExceptionDialogHandler.class, eclipseContext);
        commandExecutor = ContextInjectionFactory.make(CommandExecutor.class, eclipseContext);
        upadateExtensionListener = ContextInjectionFactory.make(UpdateExtensionListener.class, eclipseContext);
        removeExtensionListener = ContextInjectionFactory.make(RemoveExtensionListener.class, eclipseContext);
    }

    private void createToolbar(Composite parent) {
        var toolbarComposite = createComposite(parent, SWT.NONE);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        toolbarComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).create());

        createMarketplaceButton(toolbarComposite);
        createImportButton(toolbarComposite);
    }

    private void createImportButton(Composite parent) {
        new DropdownDynamicButtonWidget.Builder()
                .withLabel(Messages.importExtensionButtonLabel)
                .withId(SWTBotConstants.SWTBOT_ID_ADD_EXTENSION_DROPDOWN)
                .withTooltipText(Messages.importExtension)
                .withImage(Pics.getImage(PicsConstants.add_item_32))
                .withHotImage(Pics.getImage(PicsConstants.add_item_32_hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .addDropdownItem(Messages.addConnector, null,
                        e -> commandExecutor.executeCommand(ProjectOverviewEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, ArtifactType.CONNECTOR.name())))
                .addDropdownItem(Messages.addActorFilter, null,
                        e -> commandExecutor.executeCommand(ProjectOverviewEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER,
                                        ArtifactType.ACTOR_FILTER.name())))
                .addDropdownItem(Messages.addTheme, null,
                        e -> commandExecutor.executeCommand(ProjectOverviewEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, ArtifactType.THEME.name())))
                .addDropdownItem(Messages.addRestApiExtension, null,
                        e -> commandExecutor.executeCommand(ProjectOverviewEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, ArtifactType.REST_API.name())))
                .addDropdownItem(Messages.addProjectExtension, null,
                        e -> commandExecutor.executeCommand(ProjectOverviewEditorPart.IMPORT_PROJECT_EXTENSION_COMMAND,
                                Map.of()))
                .addDropdownItem(Messages.addOther, null,
                        e -> commandExecutor.executeCommand(ProjectOverviewEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, ArtifactType.OTHER.name())))
                .createIn(parent);
    }

    private void createMarketplaceButton(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withLabel(Messages.openMarketplace)
                .withId(SWTBotConstants.SWTBOT_ID_OPEN_MARKETPLACE_TOOLITEM)
                .withTooltipText(Messages.openMarketplaceTooltip)
                .withImage(Pics.getImage(PicsConstants.openMarketplace))
                .withHotImage(Pics.getImage(PicsConstants.openMarketplaceHot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .onClick(e -> commandExecutor.executeCommand(ProjectOverviewEditorPart.OPEN_MARKETPLACE_COMMAND, null))
                .createIn(parent);
    }

    private ScrolledComposite createExtensionSection(Composite parent) {
        try {
            PlatformUI.getWorkbench().getProgressService().run(true, false,
                    BonitaMarketplace.getInstance()::loadDependencies);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
        }

        allDependencies = BonitaMarketplace.getInstance().getDependencies();

        var sc = new ScrolledComposite(parent, SWT.V_SCROLL);
        sc.setLayout(GridLayoutFactory.fillDefaults().create());
        sc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        contentComposite = createComposite(sc, SWT.NONE);
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(20, 20).create());
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createExtensionTitleComposite(contentComposite);
        createContent();

        sc.setContent(contentComposite);
        sc.setExpandVertical(true);
        sc.setExpandHorizontal(true);
        sc.setMinHeight(contentComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        return sc;
    }

    private void createContent() {
        List<BonitaDependencyTuple> bonitaDependencies = new ArrayList<>();
        List<Dependency> otherDependencies = new ArrayList<>();
        var dependencyResolution = computeDependencies(bonitaDependencies, otherDependencies);

        if (dependencyResolution.hasExtensionIssues()) {
            createProblemsSection(contentComposite);
        }

        if (!bonitaDependencies.isEmpty() || !otherDependencies.isEmpty()) {
            var cardsComposite = createComposite(contentComposite, SWT.NONE);
            cardsComposite.setLayout(GridLayoutFactory.fillDefaults()
                    .margins(20, 10).spacing(20, 20).numColumns(2).equalWidth(true).create());
            cardsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

            bonitaDependencies.forEach(tuple -> createCard(cardsComposite, tuple.getDep(), tuple.getBonitaDep()));

            if (!otherDependencies.isEmpty()) {
                new OtherExtensionsComposite(contentComposite,
                        otherDependencies,
                        removeExtensionListener,
                        upadateExtensionListener,
                        dependencyResolution,
                        bonitaArtifactDependencyConverter,
                        ctx);
            }
        } else {
            createEmptyExtensionComposite(contentComposite);
        }
    }

    private void createProblemsSection(Composite parent) {
        var container = createComposite(contentComposite, SWT.NONE);
        container.setLayout(GridLayoutFactory.fillDefaults()
                .margins(20, 5).spacing(20, 10).create());
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        new ProblemSection(container, Messages.problemInExtensionsMessage);
    }

    private DependencyResolution computeDependencies(List<BonitaDependencyTuple> bonitaDependencies,
            List<Dependency> otherDependencies) {
        var result = new DependencyResolution();
        try {
            MavenProject mavenProject = MavenProjectHelper
                    .getMavenProject(repositoryAccessor.getCurrentRepository().orElseThrow().getProject());
            if (mavenProject != null) {
                List<Dependency> modelDependencies = mavenProject.getDependencies();
                var currentBdmModelDependency = currentBdmModelDependency();
                modelDependencies.forEach(dep -> {
                    Optional<BonitaArtifactDependency> bonitaDependency = allDependencies.stream()
                            .filter(bonitaDep -> sameDependency(dep, bonitaDep))
                            .findFirst();
                    if (bonitaDependency
                            .filter(d -> !Objects.equals(d.getArtifactType(), ArtifactType.OTHER))
                            .isPresent()) {
                        BonitaArtifactDependency bonitaArtifactDependency = bonitaDependency.get();
                        BonitaArtifactDependency artifactDependency = bonitaArtifactDependencyConverter
                                .toBonitaArtifactDependency(dep);
                        bonitaArtifactDependency.setSCMUrl(artifactDependency.getScmUrl());
                        if (!result.hasExtensionIssues() && !bonitaDependency.get().getStatus().isOK()) {
                            result.setHasExtensionIssues(true);
                        }
                        bonitaDependencies.add(new BonitaDependencyTuple(dep, bonitaDependency.get()));
                    } else if (!AppProjectConfiguration.isInternalDependency(dep)
                            && currentBdmModelDependency.map(GAV::new).filter(new GAV(dep)::equals).isEmpty()) {
                        BonitaArtifactDependency bonitaDep = bonitaArtifactDependencyConverter
                                .toBonitaArtifactDependency(dep);
                        if (Objects.equals(bonitaDep.getArtifactType(), ArtifactType.OTHER)) {
                            if (!bonitaDep.getStatus().isOK()) {
                                result.setHasOtherDependenciesIssues(true);
                                result.addOtherDependencyProblem(bonitaDep.getStatus());
                            }
                            otherDependencies.add(dep);
                        } else {
                            if (!result.hasExtensionIssues() && !bonitaDep.getStatus().isOK()) {
                                result.setHasExtensionIssues(true);
                            }
                            bonitaDependencies.add(new BonitaDependencyTuple(dep, bonitaDep));
                        }
                    }
                });
            }
        } catch (CoreException e) {
            errorHandler.openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
        }
        return result;
    }

    private Optional<Dependency> currentBdmModelDependency() {
        var businessObjectModelRepositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore businessObjectModelFileStore = (BusinessObjectModelFileStore) businessObjectModelRepositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (businessObjectModelFileStore != null) {
            try {
                return Optional.ofNullable(businessObjectModelFileStore.getModelMavenDependency());
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private void createExtensionTitleComposite(Composite parent) {
        var titleComposite = createComposite(parent, SWT.NONE);
        titleComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(3).spacing(20, LayoutConstants.getSpacing().y).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());

        var extensionLabel = new Label(titleComposite, SWT.NONE);
        extensionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
        extensionLabel.setText(Messages.bonitaExtensions);
        extensionLabel.setToolTipText(Messages.bonitaExtensionTooltip);
        extensionLabel.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.BOLD_8_FONT_ID));
        extensionLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        extensionLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        var vseparator = new Label(titleComposite, SWT.SEPARATOR | SWT.VERTICAL);
        vseparator.setLayoutData(GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 10).create());

        createToolbar(titleComposite);

        var separator = new Label(titleComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
    }

    private void createEmptyExtensionComposite(Composite parent) {
        Composite composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().span(2, 1).grab(true, true).align(SWT.CENTER, SWT.CENTER).create());

        Label label = new Label(composite, SWT.WRAP | SWT.CENTER);
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.CENTER, SWT.CENTER).create());
        label.setText(Messages.enhanceProject);
        label.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.NORMAL_10_FONT_ID));
        label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        label.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
    }

    private void createCard(Composite parent, Dependency dep, BonitaArtifactDependency bonitaDep) {
        ExtensionCard card = ExtensionCardFactory.createExtensionCard(parent, dep, bonitaDep);
        card.addUpdateExtensionListener(upadateExtensionListener);
        card.addRemoveExtensionListener(removeExtensionListener);
        if (card instanceof Zoomable) {
            ((Zoomable) card).addZoomListener(new ZoomListener() {

                @Override
                public void zoom(Event e) {
                    Arrays.asList(contentComposite.getChildren()).forEach(Control::dispose);
                    ((Zoomable) card).createZoomedControl(contentComposite);
                    contentComposite.layout();
                    scrolledComposite.setMinHeight(contentComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
                }

                @Override
                public void deZoom(Event e) {
                    refreshContent();
                }

            });
        }
    }

    private boolean sameDependency(Dependency dep, BonitaArtifactDependency bonitaDep) {
        return Objects.equals(dep.getGroupId(), bonitaDep.getGroupId())
                && Objects.equals(dep.getArtifactId(), bonitaDep.getArtifactId());
    }

    public void refreshContent() {
        Display.getDefault().asyncExec(() -> {
            Arrays.asList(contentComposite.getChildren()).forEach(Control::dispose);
            createExtensionTitleComposite(contentComposite);
            createContent();
            contentComposite.layout();
            scrolledComposite.setMinHeight(contentComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        });
    }

    private Composite createComposite(Composite parent, int style) {
        Composite composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        return composite;
    }

    static class DependencyResolution {

        private boolean hasExtensionIssues = false;
        private boolean hasOtherDependenciesIssues = false;
        private List<MultiStatus> otherDependenciesProblems = new ArrayList<>();

        public void setHasExtensionIssues(boolean hasExtensionIssues) {
            this.hasExtensionIssues = hasExtensionIssues;
        }

        public void setHasOtherDependenciesIssues(boolean hasOtherDependenciesIssues) {
            this.hasOtherDependenciesIssues = hasOtherDependenciesIssues;
        }

        public boolean hasExtensionIssues() {
            return hasExtensionIssues;
        }

        public boolean hasOtherDependenciesIssues() {
            return hasOtherDependenciesIssues;
        }

        public void addOtherDependencyProblem(MultiStatus status) {
            otherDependenciesProblems.add(status);
        }

        public List<MultiStatus> getOtherDependenciesProblems() {
            return otherDependenciesProblems;
        }

    }

}
