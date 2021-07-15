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

import java.util.ArrayList;
import java.util.Collection;

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
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.DependencyLookup;
import org.bonitasoft.studio.common.repository.extension.ExtensionActionRegistry;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ExtensionCard extends Composite {

    public static final String REST_API_EXTENSION_ACTION_ID = "org.bonitasoft.rest.api.extension";

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
        localDependencyStore = RepositoryManager.getInstance().getCurrentRepository().getLocalDependencyStore();

        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        createContent();
    }

    public void addRemoveExtensionListener(RemoveExtensionListener removeListener) {
        removeListeners.add(removeListener);
    }

    public void addUpdateExtensionListener(UpdateExtensionListener updateListener) {
        updateListeners.add(updateListener);
    }

    private void createContent() {
        var contentComposite = new Composite(this, SWT.NONE);
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        contentComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        createTitleComposite(contentComposite);
        createTypeComposite(contentComposite);
        createIcon(contentComposite);
        createDescriptionLabel(contentComposite);

        var separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.END).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);

        createToolbar(this);
    }

    private void createDetailsButton(Composite parent) {
        if (this instanceof Zoomable) {
            new DynamicButtonWidget.Builder()
                    .withTooltipText(org.bonitasoft.studio.common.Messages.moreDetails)
                    .withImage(Pics.getImage(PicsConstants.details))
                    .withHotImage(Pics.getImage(PicsConstants.detailsHot))
                    .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                    .withLayoutData(
                            GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.BOTTOM).create())
                    .onClick(e -> ((Zoomable) this).getZoomListener().zoom(e))
                    .createIn(parent);
        }
    }

    private void createToolbar(Composite parent) {
        var mainToolbarComposite = new Composite(parent, SWT.NONE);
        mainToolbarComposite.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).create());
        mainToolbarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        mainToolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var action = ArtifactType.REST_API.equals(bonitaDep.getArtifactType())
                ? ExtensionActionRegistry.getInstance().getAction(REST_API_EXTENSION_ACTION_ID)
                : ExtensionActionRegistry.getInstance()
                        .getAction(String.format("%s:%s", dep.getGroupId(), dep.getArtifactId()));
        if (action != null) {
            var leftToolbarComposite = new Composite(mainToolbarComposite, SWT.NONE);
            leftToolbarComposite.setLayout(GridLayoutFactory.fillDefaults().create());
            leftToolbarComposite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.FILL).create());
            leftToolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);
            action.fill(leftToolbarComposite);
        }

        var toolbarComposite = new Composite(mainToolbarComposite, SWT.NONE);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.FILL)
                .span(action != null ? 1 : 2, 1).create());
        toolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        if (isABonitaExtensionUpdatable()) {
            new DynamicButtonWidget.Builder()
                    .withId(SWTBotConstants.updateToLatestExtensionFromCard(bonitaDep.getArtifactId()))
                    .withText(Messages.upgradeBonitaExtension)
                    .withTooltipText(Messages.upgradeBonitaExtensionTooltip)
                    .withImage(Pics.getImage(PicsConstants.updateDependency))
                    .withHotImage(Pics.getImage(PicsConstants.updateDependencyHot))
                    .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                    .withTextColors(BonitaThemeConstants.SUCCESS_TEXT_COLOR,
                            BonitaThemeConstants.SUCCESS_HOVER_TEXT_COLOR)
                    .onClick(e -> updateListeners.stream().forEach(l -> l.updateExtension(bonitaDep, dep)))
                    .createIn(toolbarComposite);
        } else if (!bonitaDep.isFromMarketplace()) {
            if (canEditMavenCoordinates(bonitaDep)) {
                new DynamicButtonWidget.Builder()
                        .withText(Messages.editMavenCoordinates)
                        .withTooltipText(Messages.editMavenCoordinatesTooltip)
                        .withImage(Pics.getImage(PicsConstants.edit_simple))
                        .withHotImage(Pics.getImage(PicsConstants.edit_simple_hot))
                        .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                        .onClick(e -> updateListeners.stream().forEach(l -> l.updateGav(bonitaDep, dep)))
                        .createIn(toolbarComposite);
            }

            new DynamicButtonWidget.Builder()
                    .withId(SWTBotConstants.updateExtensionFromCard(bonitaDep.getArtifactId()))
                    .withText(Messages.upgradeExtension)
                    .withTooltipText(Messages.upgradeExtensionTooltip)
                    .withImage(Pics.getImage(PicsConstants.updateDependency))
                    .withHotImage(Pics.getImage(PicsConstants.updateDependencyHot))
                    .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                    .onClick(e -> updateListeners.stream().forEach(l -> l.updateExtension(bonitaDep, dep)))
                    .createIn(toolbarComposite);
        }

        new DynamicButtonWidget.Builder()
                .withId(SWTBotConstants.removeExtensionFromCard(bonitaDep.getArtifactId()))
                .withText(Messages.removeExtension)
                .withTooltipText(Messages.removeExtensionTooltip)
                .withImage(Pics.getImage(PicsConstants.delete))
                .withHotImage(Pics.getImage(PicsConstants.delete_hot))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> removeListeners.stream().forEach(l -> l.removeExtension(dep)))
                .createIn(toolbarComposite);
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

    protected void createTypeComposite(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

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

    protected void createTitleComposite(Composite parent) {
        var titleComposite = new Composite(parent, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(2)
                .spacing(LayoutConstants.getSpacing().x, 1)
                .create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(1, 2).create());
        titleComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.extensionCardId(bonitaDep.getArtifactId()));
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).create());
        titleLabel.setText(bonitaDep.getName());
        titleLabel.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.BOLD_8_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);

        createDetailsButton(titleComposite);

        var gav = new CLabel(titleComposite, SWT.WRAP);
        gav.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).span(2, 1).indent(5, 0).create());
        gav.setText(String.format("%s:%s:%s", dep.getGroupId(), dep.getArtifactId(), dep.getVersion()));
        gav.setFont(JFaceResources.getFont(ProjectOverviewEditorPart.ITALIC_0_FONT_ID));
        gav.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.GAV_TEXT_COLOR);
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
                            return latest.compareTo(new ComparableVersion(dep.getVersion())) < 0;
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
