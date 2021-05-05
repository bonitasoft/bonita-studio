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
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.views.extension.ProjectExtensionEditorPart;
import org.bonitasoft.studio.application.views.extension.RemoveExtensionListener;
import org.bonitasoft.studio.application.views.extension.UpdateExtensionListener;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.extension.ExtensionActionRegistry;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.e4.ui.css.swt.theme.IThemeEngine;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.PlatformUI;

public class ExtensionCard extends Composite {

    private Collection<RemoveExtensionListener> removeListeners = new ArrayList<>();
    private Collection<UpdateExtensionListener> updateListeners = new ArrayList<>();

    protected Dependency dep;
    protected BonitaArtifactDependency bonitaDep;
    protected IThemeEngine engine;
    protected Cursor cursorArrow;
    protected Cursor cursorHand;
    protected CLabel titleLabel;

    public ExtensionCard(Composite parent,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, SWT.BORDER);
        this.dep = dep;
        this.bonitaDep = bonitaDep;
        this.engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
        this.cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        this.cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);

        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
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
        createTitleComposite(this);
        createTypeLabel(this);
        createIcon(this);
        createDescriptionLabel(this);

        var separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);

        createToolbar(this);
    }

    private void createToolbar(Composite parent) {
        var action = ExtensionActionRegistry.getInstance()
                .getAction(String.format("%s:%s", dep.getGroupId(), dep.getArtifactId()));
        if (action != null) {
            var leftToolbar = new ToolBar(parent,
                    SWT.HORIZONTAL | SWT.WRAP | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
            leftToolbar.setLayoutData(
                    GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.FILL).create());
            leftToolbar.setLayout(GridLayoutFactory.fillDefaults().create());
            action.fill(leftToolbar);
        }

        var toolbarComposite = new Composite(parent, SWT.NONE);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.FILL)
                .span(action != null ? 1 : 2, 1).create());
        toolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        if (isABonitaExtensionUpdatable()) {
            new DynamicButtonWidget.Builder()
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
            new DynamicButtonWidget.Builder()
                    .withText(Messages.upgradeExtension)
                    .withTooltipText(Messages.upgradeExtensionTooltip)
                    .withImage(Pics.getImage(PicsConstants.updateDependency))
                    .withHotImage(Pics.getImage(PicsConstants.updateDependencyHot))
                    .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                    .onClick(e -> updateListeners.stream().forEach(l -> l.updateExtension(bonitaDep, dep)))
                    .createIn(toolbarComposite);
        }

        new DynamicButtonWidget.Builder()
                .withText(Messages.removeExtension)
                .withTooltipText(Messages.removeExtensionTooltip)
                .withImage(Pics.getImage(PicsConstants.delete))
                .withHotImage(Pics.getImage(PicsConstants.delete_hot))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> removeListeners.stream().forEach(l -> l.removeExtension(dep)))
                .createIn(toolbarComposite);
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

    protected void createTypeLabel(Composite parent) {
        var type = new Label(parent, SWT.NONE);
        type.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());
        type.setText(bonitaDep.getArtifactType().getName() + " ");
        type.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.ITALIC_0_FONT_ID));
        type.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.GAV_TEXT_COLOR);
    }

    protected void createTitleComposite(Composite parent) {
        var titleComposite = new Composite(parent, SWT.NONE);
        titleComposite.setLayout(
                GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(1, 2).create());
        titleComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleLabel.setText(bonitaDep.getName());

        titleLabel.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_8_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);

        var gav = new CLabel(titleComposite, SWT.WRAP);
        gav.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, 0).create());
        gav.setText(String.format("%s:%s:%s", dep.getGroupId(), dep.getArtifactId(), dep.getVersion()));
        gav.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.ITALIC_0_FONT_ID));
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
