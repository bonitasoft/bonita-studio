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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.handler.ImportExtensionHandler;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependencyConverter;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaMarketplace;
import org.bonitasoft.studio.application.views.dashboard.ProjectDashboardEditorPart;
import org.bonitasoft.studio.application.views.extension.card.ExtensionCard;
import org.bonitasoft.studio.application.views.extension.card.ExtensionCardFactory;
import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.bonitasoft.studio.application.views.extension.card.zoom.Zoomable;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.widget.DropdownDynamicButtonWidget;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

public class ExtensionComposite extends Composite {

    private RepositoryAccessor repositoryAccessor;
    private BonitaArtifactDependencyConverter bonitaArtifactDependencyConverter;
    private ExceptionDialogHandler errorHandler;
    private CommandExecutor commandExecutor;
    private UpdateExtensionListener upadateExtensionListener;
    private RemoveExtensionListener removeExtensionListener;

    private MavenProjectHelper mavenHelper;

    private DataBindingContext ctx;

    private Composite cardComposite;

    private ScrolledComposite scrolledComposite;

    private List<BonitaArtifactDependency> allDependencies;

    public ExtensionComposite(Composite parent, RepositoryAccessor repositoryAccessor) {
        super(parent, SWT.NONE);
        this.repositoryAccessor = repositoryAccessor;
        initVariables();

        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        setLayout(GridLayoutFactory.fillDefaults().create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        scrolledComposite = createExtensionSection(this);
    }

    private void initVariables() {
        mavenHelper = new MavenProjectHelper();
        ctx = new DataBindingContext();

        var currentRepository = repositoryAccessor.getCurrentRepository();
        bonitaArtifactDependencyConverter = new BonitaArtifactDependencyConverter(
                currentRepository.getProjectDependenciesStore(),
                currentRepository.getLocalDependencyStore());

        var eclipseContext = EclipseContextFactory.create();
        errorHandler = ContextInjectionFactory.make(ExceptionDialogHandler.class, eclipseContext);
        commandExecutor = ContextInjectionFactory.make(CommandExecutor.class, eclipseContext);
        upadateExtensionListener = ContextInjectionFactory.make(UpdateExtensionListener.class, eclipseContext);
        removeExtensionListener = ContextInjectionFactory.make(RemoveExtensionListener.class, eclipseContext);

        allDependencies = BonitaMarketplace.getInstance().getDependencies();
    }

    private ScrolledComposite createExtensionSection(Composite parent) {
        ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL);
        sc.setLayout(GridLayoutFactory.fillDefaults().create());
        sc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        cardComposite = createComposite(sc, SWT.NONE);
        cardComposite.setLayout(GridLayoutFactory.fillDefaults().margins(40, 20)
                .spacing(20, 20).numColumns(2).equalWidth(true).create());
        cardComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createExtensionCards(cardComposite);

        sc.setContent(cardComposite);
        sc.setExpandVertical(true);
        sc.setExpandHorizontal(true);
        sc.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        return sc;
    }

    private void createExtensionCards(Composite parent) {
        try {
            Model mavenModel = mavenHelper
                    .getMavenModel(repositoryAccessor.getCurrentRepository().getProject());
            if (mavenModel != null) {
                List<Dependency> modelDependencies = mavenModel.getDependencies();
                List<Dependency> otherDependencies = new ArrayList<>();
                modelDependencies.forEach(dep -> {
                    Optional<BonitaArtifactDependency> bonitaDependency = allDependencies.stream()
                            .filter(bonitaDep -> sameDependency(dep, bonitaDep))
                            .findFirst();
                    if (bonitaDependency
                            .filter(d -> !Objects.equals(d.getArtifactType(), ArtifactType.OTHER))
                            .isPresent()) {
                        createCard(parent, dep, bonitaDependency.get());
                    } else if (!ProjectDefaultConfiguration.isInternalDependency(dep) && !isBDMDependency(dep)) {
                        BonitaArtifactDependency bonitaDep = bonitaArtifactDependencyConverter
                                .toBonitaArtifactDependency(dep);
                        if (Objects.equals(bonitaDep.getArtifactType(), ArtifactType.OTHER)) {
                            otherDependencies.add(dep);
                        } else {
                            createCard(parent, dep, bonitaDep);
                        }
                    }
                });

                if (!otherDependencies.isEmpty()) {
                    if (parent.getChildren().length > 0) {
                        Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
                        separator.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, false).create());
                    }
                    new OtherExtensionsComposite(parent,
                            otherDependencies,
                            JFaceResources.getFont(ProjectDashboardEditorPart.BOLD_8_FONT_ID),
                            removeExtensionListener,
                            upadateExtensionListener,
                            ctx);
                }

                if (cardComposite.getChildren().length == 0) {
                    createEmptyExtensionComposite(cardComposite);
                }
            }

        } catch (CoreException e) {
            errorHandler.openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
        }
    }

    private void createEmptyExtensionComposite(Composite parent) {
        Composite composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().span(2, 1).grab(true, true).align(SWT.CENTER, SWT.CENTER).create());

        Label label = new Label(composite, SWT.WRAP | SWT.CENTER);
        label.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.CENTER, SWT.CENTER).create());
        label.setText(Messages.enhanceProject);
        label.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.NORMAL_10_FONT_ID));
        label.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        label.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);

        Composite buttonComposite = createComposite(composite, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).create());

        createMarketplaceBigButton(buttonComposite);
        createImportBigButton(buttonComposite);
    }

    private void createCard(Composite parent, Dependency dep, BonitaArtifactDependency bonitaDep) {
        ExtensionCard card = ExtensionCardFactory.createExtensionCard(parent, dep, bonitaDep);
        card.addUpdateExtensionListener(upadateExtensionListener);
        card.addRemoveExtensionListener(removeExtensionListener);
        if (card instanceof Zoomable) {
            ((Zoomable) card).addZoomListener(new ZoomListener() {

                @Override
                public void zoom(Event e) {
                    Arrays.asList(cardComposite.getChildren()).forEach(Control::dispose);
                    ((Zoomable) card).createZoomedControl(cardComposite);
                    cardComposite.layout();
                    scrolledComposite.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
                }

                @Override
                public void deZoom(Event e) {
                    refreshContent();
                }

            });
        }
    }

    private boolean isBDMDependency(Dependency dep) {
        var businessObjectModelRepositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore businessObjectModelFileStore = (BusinessObjectModelFileStore) businessObjectModelRepositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        if (businessObjectModelFileStore != null) {
            try {
                return Objects.equals(new GAV(businessObjectModelFileStore.getClientMavenDependency()), new GAV(dep));
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
                return false;
            }
        }
        return false;
    }

    private boolean sameDependency(Dependency dep, BonitaArtifactDependency bonitaDep) {
        return Objects.equals(dep.getGroupId(), bonitaDep.getGroupId())
                && Objects.equals(dep.getArtifactId(), bonitaDep.getArtifactId());
    }

    private void createImportBigButton(Composite parent) {
        new DropdownDynamicButtonWidget.Builder()
                .withText(Messages.importExtensionButtonLabel)
                .withMaxTextWidth(200)
                .withTooltipText(Messages.importExtension)
                .withImage(Pics.getImage(PicsConstants.import64))
                .withHotImage(Pics.getImage(PicsConstants.import64Hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .withFont(JFaceResources.getFont(ProjectDashboardEditorPart.NORMAL_4_FONT_ID))
                .addDropdownItem(Messages.addConnector, null,
                        e -> commandExecutor.executeCommand(ProjectDashboardEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, ArtifactType.CONNECTOR.name())))
                .addDropdownItem(Messages.addActorFilter, null,
                        e -> commandExecutor.executeCommand(ProjectDashboardEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER,
                                        ArtifactType.ACTOR_FILTER.name())))
                .addDropdownItem(Messages.addTheme, null,
                        e -> commandExecutor.executeCommand(ProjectDashboardEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, ArtifactType.THEME.name())))
                .addDropdownItem(Messages.addRestApiExtension, null,
                        e -> commandExecutor.executeCommand(ProjectDashboardEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, ArtifactType.REST_API.name())))
                .addDropdownItem(Messages.addOther, null,
                        e -> commandExecutor.executeCommand(ProjectDashboardEditorPart.IMPORT_EXTENSION_COMMAND,
                                Map.of(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, ArtifactType.OTHER.name())))
                .createIn(parent);
    }

    private void createMarketplaceBigButton(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withText(Messages.openMarketplace)
                .withId(SWTBotConstants.SWTBOT_ID_OPEN_MARKETPLACE_BIG_TOOLITEM)
                .withMaxTextWidth(200)
                .withTooltipText(Messages.openMarketplaceTooltip)
                .withImage(Pics.getImage(PicsConstants.openMarketplace64))
                .withHotImage(Pics.getImage(PicsConstants.openMarketplace64Hot))
                .withCssclass(BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND)
                .withFont(JFaceResources.getFont(ProjectDashboardEditorPart.NORMAL_4_FONT_ID))
                .onClick(e -> commandExecutor.executeCommand(ProjectDashboardEditorPart.OPEN_MARKETPLACE_COMMAND, null))
                .createIn(parent);
    }

    public void refreshContent() {
        Display.getDefault().asyncExec(() -> {
            Arrays.asList(cardComposite.getChildren()).forEach(Control::dispose);
            createExtensionCards(cardComposite);
            cardComposite.layout();
            scrolledComposite.setMinHeight(cardComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        });
    }

    private Composite createComposite(Composite parent, int style) {
        Composite composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.EXTENSION_VIEW_BACKGROUND);
        return composite;
    }

}
