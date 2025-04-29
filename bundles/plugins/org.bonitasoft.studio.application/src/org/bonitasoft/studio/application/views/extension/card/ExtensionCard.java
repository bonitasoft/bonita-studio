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
package org.bonitasoft.studio.application.views.extension.card;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.maven.artifact.versioning.ComparableVersion;
import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage;
import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.views.extension.RemoveExtensionListener;
import org.bonitasoft.studio.application.views.extension.UpdateExtensionListener;
import org.bonitasoft.studio.application.views.extension.card.zoom.Zoomable;
import org.bonitasoft.studio.application.views.overview.ProjectOverviewEditorPart;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.extension.ExtensionActionRegistry;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.common.ui.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class ExtensionCard extends Composite {

    public static final String REST_API_EXTENSION_ACTION_ID = "org.bonitasoft.rest.api.extension";
    private static final int ERROR_BORDER_WITDH = 3;

    private Collection<RemoveExtensionListener> removeListeners = new ArrayList<>();
    private Collection<UpdateExtensionListener> updateListeners = new ArrayList<>();

    protected Dependency dep;
    protected BonitaArtifactDependency bonitaDep;
    protected CLabel titleLabel;

    private LocalDependenciesStore localDependencyStore;

    public ExtensionCard(Composite parent,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, SWT.BORDER);
        this.dep = dep;
        this.bonitaDep = bonitaDep;
        localDependencyStore = RepositoryManager.getInstance().getCurrentRepository().orElseThrow()
                .getLocalDependencyStore();

        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        var backgroundCssClassname = bonitaDep.getStatus().isOK() ? BonitaThemeConstants.CARD_BACKGROUND
                : BonitaThemeConstants.CARD_BACKGROUND + "-error";
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, backgroundCssClassname);

        createContent(backgroundCssClassname);

        if (!bonitaDep.getStatus().isOK()) {
            addListener(SWT.Paint, e -> {
                var gc = e.gc;
                var borderColor = new Color(Display.getDefault(), 237, 137, 54);
                gc.setForeground(borderColor);
                gc.setLineStyle(SWT.LINE_SOLID);
                gc.setLineWidth(ERROR_BORDER_WITDH);
                var clientArea = getClientArea();
                gc.drawRectangle(clientArea.x, clientArea.y, clientArea.width - 1, clientArea.height - 1);
                borderColor.dispose();
            });
        }
    }

    public void addRemoveExtensionListener(RemoveExtensionListener removeListener) {
        removeListeners.add(removeListener);
    }

    public void addUpdateExtensionListener(UpdateExtensionListener updateListener) {
        updateListeners.add(updateListener);
    }

    protected String getTextClassName() {
        return bonitaDep.getStatus().isOK() ? BonitaThemeConstants.TITLE_TEXT_COLOR
                : BonitaThemeConstants.TITLE_TEXT_COLOR + "-error";
    }

    private void createContent(String backgroundCssClassname) {
        var contentComposite = new Composite(this, SWT.NONE);
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        contentComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, backgroundCssClassname);

        createTitleComposite(contentComposite, backgroundCssClassname);
        createTypeComposite(contentComposite, backgroundCssClassname);
        createIcon(contentComposite);
        createDescriptionLabel(contentComposite);

        createViewSourceButton(contentComposite, backgroundCssClassname);

        createDetailsButton(contentComposite, backgroundCssClassname);

        var separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.END).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);

        createToolbar(this, backgroundCssClassname);
    }

    private void createViewSourceButton(Composite parent, String backgroundCssClassname) {
        if (Strings.hasText(bonitaDep.getScmUrl())) {
            try {
                var url = new URL(bonitaDep.getScmUrl());
                new DynamicButtonWidget.Builder()
                        .withLabel(Messages.viewSource)
                        .withTooltipText(String.format(Messages.viewSourceTooltip, bonitaDep.getScmUrl()))
                        .withImage(Pics.getImage(PicsConstants.viewSource))
                        .withHotImage(Pics.getImage(PicsConstants.viewSourceHot))
                        .withCssclass(backgroundCssClassname)
                        .withLayoutData(
                                GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL)
                                        .create())
                        .onClick(e -> new OpenBrowserOperation(url).execute())
                        .createIn(parent);
            } catch (MalformedURLException e) {
                BonitaStudioLog
                        .error(String.format("Failed to parse SCM url %s", bonitaDep.getScmUrl()), e);
            }
        }
    }

    private void createDetailsButton(Composite parent, String backgroundCssClassname) {
        if (this instanceof Zoomable) {
            new DynamicButtonWidget.Builder()
                    .withTooltipText(Messages.showMore)
                    .withImage(Pics.getImage(PicsConstants.details))
                    .withHotImage(Pics.getImage(PicsConstants.detailsHot))
                    .withCssclass(backgroundCssClassname)
                    .withLayoutData(GridDataFactory.fillDefaults()
                            .span(Strings.hasText(bonitaDep.getScmUrl()) ? 1 : 2, 1)
                            .align(SWT.END, SWT.FILL)
                            .create())
                    .onClick(e -> ((Zoomable) this).getZoomListener().zoom(e))
                    .createIn(parent);
        }
    }

    private void createToolbar(Composite parent, String backgroundCssClassname) {
        var mainToolbarComposite = new Composite(parent, SWT.NONE);
        mainToolbarComposite.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        mainToolbarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        mainToolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                backgroundCssClassname);

        var action = ArtifactType.REST_API.equals(bonitaDep.getArtifactType())
                ? ExtensionActionRegistry.getInstance().getAction(REST_API_EXTENSION_ACTION_ID)
                : ExtensionActionRegistry.getInstance()
                        .getAction(String.format("%s:%s", dep.getGroupId(), dep.getArtifactId()));
        if (action != null) {
            var leftToolbarComposite = new Composite(mainToolbarComposite, SWT.NONE);
            leftToolbarComposite.setLayout(GridLayoutFactory.fillDefaults().create());
            leftToolbarComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
            leftToolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME,
                    backgroundCssClassname);
            action.fill(leftToolbarComposite);
        }

        var toolbarContributions = getToolbarContributions();
        var toolbarComposite = new Composite(mainToolbarComposite, SWT.NONE);
        toolbarComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(getToolbarMaxSize(toolbarContributions.size())).create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.FILL)
                .span(action != null ? 1 : 2, 1).create());
        toolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, backgroundCssClassname);

        int columnUsed = 0;
        for (DynamicButtonWidget.Builder builder : toolbarContributions) {
            builder.withCssclass(backgroundCssClassname);
            builder.createIn(toolbarComposite);
            columnUsed++;
        }
        if (isABonitaExtensionUpdatable()) {
            new DynamicButtonWidget.Builder()
                    .withId(SWTBotConstants.updateToLatestExtensionFromCard(bonitaDep.getArtifactId()))
                    .withLabel(Messages.upgradeBonitaExtension)
                    .withTooltipText(Messages.upgradeBonitaExtensionTooltip)
                    .withImage(Pics.getImage(PicsConstants.updateDependency))
                    .withHotImage(Pics.getImage(PicsConstants.updateDependencyHot))
                    .withCssclass(backgroundCssClassname)
                    .withTextColors(BonitaThemeConstants.SUCCESS_TEXT_COLOR,
                            BonitaThemeConstants.SUCCESS_HOVER_TEXT_COLOR)
                    .onClick(e -> updateListeners.stream().forEach(l -> l.updateExtension(bonitaDep, dep)))
                    .createIn(toolbarComposite);
            columnUsed++;
        } else if (!bonitaDep.isFromMarketplace() && !bonitaDep.isProjectExtension()) {
            new DynamicButtonWidget.Builder()
                    .withId(SWTBotConstants.updateExtensionFromCard(bonitaDep.getArtifactId()))
                    .withLabel(Messages.upgradeExtension)
                    .withTooltipText(Messages.upgradeExtensionTooltip)
                    .withImage(Pics.getImage(PicsConstants.updateDependency))
                    .withHotImage(Pics.getImage(PicsConstants.updateDependencyHot))
                    .withCssclass(backgroundCssClassname)
                    .onClick(e -> updateListeners.stream().forEach(l -> l.updateExtension(bonitaDep, dep)))
                    .createIn(toolbarComposite);
            columnUsed++;
        }

        new DynamicButtonWidget.Builder()
                .withId(SWTBotConstants.removeExtensionFromCard(bonitaDep.getArtifactId()))
                .withLabel(Messages.removeExtension)
                .withTooltipText(Messages.removeExtensionTooltip)
                .withImage(Pics.getImage(PicsConstants.delete))
                .withHotImage(Pics.getImage(PicsConstants.delete_hot))
                .withCssclass(backgroundCssClassname)
                .withLayoutData(GridDataFactory.swtDefaults()
                        .span(getToolbarMaxSize(toolbarContributions.size()) - columnUsed, 1).create())
                .onClick(e -> removeListeners.stream().forEach(l -> l.removeExtension(dep)))
                .createIn(toolbarComposite);
    }

	protected List<DynamicButtonWidget.Builder> getToolbarContributions() {
        return new ArrayList<>();
    }

    private int getToolbarMaxSize(int nbToolbarContributions) {
        return nbToolbarContributions + 2;
    }

    protected boolean canEditMavenCoordinates(BonitaArtifactDependency bonitaDep) {
        if (bonitaDep.isLocalDependency()) {
            return DependencyLookup.readPomProperties(localDependencyStore.dependencyPath(dep)
                    .resolve(LocalDependenciesStore.dependencyFileName(dep)).toFile()).isEmpty();
        }
        return false;
    }

    protected void createDescriptionLabel(Composite parent) {
        var description = new Label(parent, SWT.WRAP);
        description.setLayoutData(
                GridDataFactory.fillDefaults().indent(0, 10).grab(true, false).hint(SWT.DEFAULT, 50).create());
        description.setText(bonitaDep.getDescription() != null ? bonitaDep.getDescription() : "");
    }

    protected void createIcon(Composite parent) {
        var iconLabel = new Label(parent, SWT.NONE);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.FILL)
                .span(1, 2)
                .hint(BonitaMarketplacePage.ICON_SIZE, BonitaMarketplacePage.ICON_SIZE)
                .create());
        iconLabel.setImage(bonitaDep.getIconImage());
    }

    protected void createTypeComposite(Composite parent, String backgroundCssClassname) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, backgroundCssClassname);

        var type = new Label(composite, SWT.NONE);
        type.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        type.setText(bonitaDep.getArtifactType().getName() + " ");
        type.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.ITALIC_0_FONT_ID));
        type.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.GAV_TEXT_COLOR);

        var icon = new Label(composite, SWT.NONE);
        icon.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
        if (bonitaDep.isFromMarketplace()) {
            icon.setImage(Pics.getImage(PicsConstants.marketplace));
            icon.setToolTipText(Messages.marketplaceDependencyTooltip);
        } else if (bonitaDep.isLocalDependency()) {
            icon.setImage(Pics.getImage(PicsConstants.local));
            icon.setToolTipText(Messages.localDependencyTooltip);
        } else {
            icon.setImage(Pics.getImage(PicsConstants.remote));
            icon.setToolTipText(Messages.remoteDependencyTooltip);
        }
    }

    protected void createTitleComposite(Composite parent, String backgroundCssClassname) {
        var titleComposite = new Composite(parent, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults()
                .spacing(LayoutConstants.getSpacing().x, 1)
                .create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(1, 2).create());
        titleComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, backgroundCssClassname);

        titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY,
                SWTBotConstants.extensionCardId(bonitaDep.getArtifactId()));
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).create());
        titleLabel.setText(bonitaDep.getName());
        titleLabel.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.BOLD_8_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, getTextClassName());

        if (bonitaDep.getStatus().getSeverity() != IStatus.OK) {
            titleLabel.setImage(Pics.getImage(PicsConstants.problem));
            titleLabel.setToolTipText(String.format("%s problem(s) found, click for more details.",
                    bonitaDep.getStatus().getChildren().length));
        }

        var gavComposite = new Composite(titleComposite, SWT.NONE);
        gavComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(1, LayoutConstants.getSpacing().y).create());
        gavComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        gavComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, backgroundCssClassname);

        var gav = new CLabel(gavComposite, SWT.WRAP);
        gav.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).indent(5, 0).create());
        gav.setText(String.format("%s:%s:%s", dep.getGroupId(), dep.getArtifactId(), dep.getVersion()));
        gav.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.ITALIC_0_FONT_ID));
        gav.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.GAV_TEXT_COLOR);

        if (!bonitaDep.isFromMarketplace() && canEditMavenCoordinates(bonitaDep)) {
            new DynamicButtonWidget.Builder()
                    .withTooltipText(Messages.editMavenCoordinatesTooltip)
                    .withImage(Pics.getImage(PicsConstants.edit_simple))
                    .withHotImage(Pics.getImage(PicsConstants.edit_simple_hot))
                    .withCssclass(backgroundCssClassname)
                    .onClick(e -> updateListeners.stream().forEach(l -> l.updateGav(bonitaDep, dep)))
                    .createIn(gavComposite);
        }
    }

    /**
     * Return true if the extension comes from the marketplace and has a new compatible version available (i.e a compatible
     * version higher than the current one).
     */
    private boolean isABonitaExtensionUpdatable() {
        if (bonitaDep.isFromMarketplace()) {
            return bonitaDep.getLatestCompatibleVersion()
                    .map(latest -> {
                        try {
                        	var depVersion = dep.getVersion();
                            return depVersion != null && latest.compareTo(new ComparableVersion(depVersion)) < 0;
                        } catch (IllegalArgumentException e) {
                            // version badly formatted, unable to compare
                            BonitaStudioLog.error(e);
                            return false;
                        }
                    })
                    .orElse(false);
        }
        return false;
    }

}
