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
package org.bonitasoft.studio.application.views.extension.card.zoom;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.bonitasoft.plugin.analyze.report.model.Implementation;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.BonitaMarketplacePage;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.views.extension.ProjectExtensionEditorPart;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.provider.ConnectorDefinitionRegistry;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class ConnectorZoomControl extends Composite {

    private ZoomListener zoomListener;
    private CLabel gav;
    private ConnectorDefRepositoryStore defRepositoryStore;
    private LocalResourceManager localResourceManager;
    private BonitaArtifactDependency bonitaDep;
    private Dependency dep;
    private ProjectDependenciesStore projectDependenciesStore;

    public ConnectorZoomControl(Composite parent,
            ZoomListener zoomListener,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, SWT.BORDER);

        var repoManager = RepositoryManager.getInstance();
        this.zoomListener = zoomListener;
        this.dep = dep;
        this.bonitaDep = bonitaDep;
        this.defRepositoryStore = repoManager.getRepositoryStore(ConnectorDefRepositoryStore.class);
        this.localResourceManager = new LocalResourceManager(JFaceResources.getResources(Display.getDefault()));
        this.projectDependenciesStore = repoManager.getCurrentRepository().getProjectDependenciesStore();

        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        createZoomedTitleComposite(this);

        var description = new Label(this, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, 10).create());
        description.setText(bonitaDep.getDescription());
        description.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        description.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.ITALIC_0_FONT_ID));

        createDefinitionSection(this);
    }

    private void createZoomedTitleComposite(Composite parent) {
        var titleComposite = createComposite(parent, SWT.NONE);
        titleComposite.setLayout(
                GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).numColumns(2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleLabel.setText(bonitaDep.getName());

        titleLabel.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_8_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        titleLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        new DynamicButtonWidget.Builder()
                .withText(Messages.back)
                .withImage(Pics.getImage(PicsConstants.back))
                .withHotImage(Pics.getImage(PicsConstants.backHot))
                .withLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.END).create())
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> {
                    if (zoomListener != null) {
                        zoomListener.deZoom(e);
                    }
                })
                .createIn(titleComposite);

        gav = new CLabel(titleComposite, SWT.WRAP);
        gav.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, -5).span(2, 1).create());
        gav.setText(String.format("%s:%s:%s", dep.getGroupId(), dep.getArtifactId(), dep.getVersion()));
        gav.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.ITALIC_0_FONT_ID));
        gav.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.GAV_TEXT_COLOR);
        gav.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);
    }

    private void createDefinitionSection(Composite parent) {
        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 10, 20, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var titleComposite = createComposite(composite, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var sectionTitle = new Label(titleComposite, SWT.WRAP);
        sectionTitle.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        sectionTitle.setText(Messages.connectors);
        sectionTitle.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        sectionTitle.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);
        sectionTitle.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_4_FONT_ID));

        var iconLabel = new Label(titleComposite, SWT.NONE);
        iconLabel.setLayoutData(GridDataFactory.fillDefaults()
                .align(SWT.END, SWT.FILL)
                .span(1, 2)
                .hint(BonitaMarketplacePage.ICON_SIZE, BonitaMarketplacePage.ICON_SIZE)
                .create());
        iconLabel.setImage(bonitaDep.getIconImage());

        var hint = new Label(titleComposite, SWT.WRAP);
        hint.setLayoutData(GridDataFactory.fillDefaults().indent(10, 0).create());
        hint.setText(String.format(Messages.connectorsHint, bonitaDep.getName()));
        hint.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var definitionsComposite = createComposite(composite, SWT.NONE);
        definitionsComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 10).create());
        definitionsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        getDefinitions().forEach(def -> createConnectorDetails(definitionsComposite, def));

        var separator = new Label(definitionsComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);
    }

    private void createConnectorDetails(Composite parent, ExtendedConnectorDefinition extendedDefinition) {
        var separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);

        var composite = createComposite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(true).margins(10, 10)
                .spacing(LayoutConstants.getSpacing().x, 20).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createIdVersionStyledText(composite, extendedDefinition.getConnectorDefinitionLabel(),
                extendedDefinition.getVersion(), JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_0_FONT_ID), 2);

        var descriptionContainer = createComposite(composite, SWT.NONE);
        descriptionContainer.setLayoutData(GridDataFactory.fillDefaults().indent(10, 0).grab(true, true).create());
        descriptionContainer.setLayout(GridLayoutFactory.fillDefaults().create());

        var description = new Label(descriptionContainer, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        description.setText(extendedDefinition.getConnectorDefinitionDescription());
        description.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.ITALIC_0_FONT_ID));
        description.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var implementationsComposite = createComposite(composite, SWT.NONE);
        implementationsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        implementationsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        var implementationsLabel = new Label(implementationsComposite, SWT.WRAP);
        implementationsLabel.setLayoutData(GridDataFactory.fillDefaults().indent(30, 0).create());
        implementationsLabel.setText(Messages.implementations);
        implementationsLabel.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_0_FONT_ID));
        implementationsLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var implementations = getImplementations(extendedDefinition);

        var implementationListComposite = createComposite(implementationsComposite, SWT.NONE);
        implementationListComposite.setLayout(GridLayoutFactory.fillDefaults().margins(5, 0).create());
        implementationListComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.FILL).create());

        implementations.forEach(impl -> createIdVersionStyledText(implementationListComposite,
                impl.getImplementationId(),
                impl.getImplementationVersion(), null, 1));
    }

    private void createIdVersionStyledText(Composite parent, String id, String version, Font font, int horizontalSpan) {
        var styledText = new StyledText(parent, SWT.WRAP);
        styledText.setEditable(false);
        styledText.setEnabled(false);
        styledText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(horizontalSpan, 1).create());
        styledText.setText(String.format("%s (%s)", id, version));
        styledText.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        styledText.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        Display.getDefault().asyncExec(() -> {
            StyleRange definitionStyle = new StyleRange(0, id.length(), styledText.getForeground(),
                    styledText.getBackground());
            definitionStyle.font = font;
            StyleRange versionStyle = new StyleRange(id.length() + 1,
                    version.length() + 2,
                    gav.getForeground(), styledText.getBackground());
            versionStyle.font = JFaceResources.getFont(ProjectExtensionEditorPart.ITALIC_0_FONT_ID);
            styledText.setStyleRanges(new StyleRange[] { definitionStyle, versionStyle });
            parent.layout();
        });
    }

    private List<ExtendedConnectorDefinition> getDefinitions() {
        ConnectorDefinitionRegistry registry = defRepositoryStore.getResourceProvider().getConnectorDefinitionRegistry();
        return projectDependenciesStore.getConnectorDefinitions().stream()
                .filter(def -> Objects.equals(def.getArtifact().getGroupId(), dep.getGroupId()))
                .filter(def -> Objects.equals(def.getArtifact().getArtifactId(), dep.getArtifactId()))
                .filter(def -> Objects.equals(def.getArtifact().getVersion(), dep.getVersion()))
                .map(def -> registry.find(def.getDefinitionId(), def.getDefinitionVersion()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .sorted((def1, def2) -> def1.getConnectorDefinitionLabel().compareTo(def2.getConnectorDefinitionLabel()))
                .collect(Collectors.toList());
    }

    private List<Implementation> getImplementations(ExtendedConnectorDefinition def) {
        return projectDependenciesStore.getConnectorImplementations().stream()
                .filter(impl -> Objects.equals(impl.getDefinitionId(), def.getId()))
                .filter(impl -> Objects.equals(impl.getDefinitionVersion(), def.getVersion()))
                .collect(Collectors.toList());
    }

    private Composite createComposite(Composite parent, int style) {
        Composite composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);
        return composite;
    }

}
