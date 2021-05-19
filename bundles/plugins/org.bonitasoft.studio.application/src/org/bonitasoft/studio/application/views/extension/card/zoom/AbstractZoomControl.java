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

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.views.extension.ProjectExtensionEditorPart;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractZoomControl extends Composite {

    protected ZoomListener zoomListener;
    protected Dependency dep;
    protected BonitaArtifactDependency bonitaDep;
    protected ProjectDependenciesStore projectDependenciesStore;
    protected IThemeEngine engine;
    protected Cursor cursorHand;
    protected Cursor cursorArrow;
    protected CLabel gav;

    protected AbstractZoomControl(Composite parent,
            ZoomListener zoomListener,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, SWT.BORDER);

        var repoManager = RepositoryManager.getInstance();
        this.zoomListener = zoomListener;
        this.dep = dep;
        this.bonitaDep = bonitaDep;
        this.projectDependenciesStore = repoManager.getCurrentRepository().getProjectDependenciesStore();
        this.engine = PlatformUI.getWorkbench().getService(IThemeEngine.class);
        this.cursorHand = parent.getDisplay().getSystemCursor(SWT.CURSOR_HAND);
        this.cursorArrow = parent.getDisplay().getSystemCursor(SWT.CURSOR_ARROW);

        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        createZoomedTitleComposite(this);

        var description = new Label(this, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, 10).create());
        description.setText(bonitaDep.getDescription());
        description.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        createDetailsSection(this);
    }

    protected abstract void createDetailsSection(Composite parent);

    protected void createZoomedTitleComposite(Composite parent) {
        var titleComposite = createComposite(parent, SWT.NONE);
        titleComposite.setLayout(
                GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).numColumns(2).create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        CLabel titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleLabel.setText(bonitaDep.getName());

        titleLabel.setFont(JFaceResources.getFont(ProjectExtensionEditorPart.BOLD_8_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        titleLabel.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        titleLabel.addListener(SWT.MouseUp, e -> {
            if (zoomListener != null) {
                Rectangle bounds = titleLabel.getBounds();
                if (e.x >= 0 && e.x <= bounds.width && e.y >= 0 && e.y <= bounds.height) {
                    zoomListener.deZoom(e);
                }
            }
        });

        titleLabel.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseEnter(MouseEvent e) {
                titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME,
                        BonitaThemeConstants.CARD_HIGHLIGHT_TITLE_COLOR);
                engine.applyStyles(titleLabel, false);
                titleLabel.setCursor(cursorHand);
            }

            @Override
            public void mouseExit(MouseEvent e) {
                titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
                engine.applyStyles(titleLabel, false);
                titleLabel.setCursor(cursorArrow);
            }
        });

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

    protected Composite createComposite(Composite parent, int style) {
        Composite composite = new Composite(parent, style);
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);
        return composite;
    }

}
