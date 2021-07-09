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
package org.bonitasoft.studio.la.application.ui.overview;

import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.bonitasoft.studio.application.views.extension.card.zoom.Zoomable;
import org.bonitasoft.studio.common.RedirectURLBuilder;
import org.bonitasoft.studio.common.extension.OverviewContribution;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;

public class ApplicationOverviewContribution implements OverviewContribution, Zoomable {

    static final String NEW_APPLICATION_COMMAND = "org.bonitasoft.studio.la.new.command";
    private static final String APPLICATIONS_REDIRECT_ID = "730";

    private ZoomListener zoomListener;

    private Listener computeScrollListener;

    @Override
    public String getName() {
        return Messages.application;
    }

    @Override
    public String getDescription() {
        return Messages.overviewApplicationDescription;
    }

    @Override
    public String getDocumentationLink() {
        return RedirectURLBuilder.create(APPLICATIONS_REDIRECT_ID);
    }

    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.application32);
    }

    @Override
    public void contributeActions(Composite parent) {
        var toolbarComposite = new Composite(parent, SWT.NONE);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.FILL).create());
        toolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        new DynamicButtonWidget.Builder()
                .withText(org.bonitasoft.studio.common.Messages.create)
                .withImage(Pics.getImage(PicsConstants.add_simple))
                .withHotImage(Pics.getImage(PicsConstants.add_simple_hot))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .withId(SWTBotConstants.createArtifactButtonId(Messages.application))
                .onClick(e -> commandExecutor.executeCommand(NEW_APPLICATION_COMMAND, null))
                .createIn(toolbarComposite);
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public Control createZoomedControl(Composite parent) {
        return new ApplicationZoomControl(parent, zoomListener, computeScrollListener, this);
    }

    @Override
    public void addZoomListener(ZoomListener listener) {
        this.zoomListener = listener;
    }

    @Override
    public ZoomListener getZoomListener() {
        return zoomListener;
    }

    @Override
    public void addComputeScrollListener(Listener computeScrollListener) {
        this.computeScrollListener = computeScrollListener;
    }

}
