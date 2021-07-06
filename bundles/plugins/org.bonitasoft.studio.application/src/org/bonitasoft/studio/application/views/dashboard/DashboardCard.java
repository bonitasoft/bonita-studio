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
package org.bonitasoft.studio.application.views.dashboard;

import org.bonitasoft.studio.application.views.extension.card.zoom.Zoomable;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.extension.DashboardContribution;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.browser.OpenSystemBrowserListener;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

public class DashboardCard extends Composite {

    private DashboardContribution contribution;

    public DashboardCard(Composite parent, DashboardContribution contribution) {
        super(parent, SWT.BORDER);
        this.contribution = contribution;

        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        createTitleComposite(this);
        createDescriptionLabel(this);

        var separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        separator.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_SEPARATOR);

        contribution.contributeActions(this);
    }

    protected void createTitleComposite(Composite parent) {
        var titleComposite = new Composite(parent, SWT.NONE);
        titleComposite
                .setLayout(
                        GridLayoutFactory.fillDefaults().numColumns(contribution instanceof Zoomable ? 3 : 2).create());
        titleComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var icon = new Label(titleComposite, SWT.NONE);
        icon.setLayoutData(GridDataFactory.fillDefaults().create());
        icon.setImage(contribution.getIcon());
        icon.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);

        var titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleLabel.setText(contribution.getName());
        titleLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.BOLD_4_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);

        if (contribution instanceof Zoomable) {
            ((Zoomable) contribution).addZoomBehavior(titleLabel);

            new DynamicButtonWidget.Builder()
                    .withImage(Pics.getImage(PicsConstants.details))
                    .withHotImage(Pics.getImage(PicsConstants.detailsHot))
                    .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                    .withLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).create())
                    .withId(SWTBotConstants.extensionCardId(contribution.getName()))
                    .onClick(e -> ((Zoomable) contribution).getZoomListener().zoom(e))
                    .createIn(titleComposite);
        }
    }

    protected void createDescriptionLabel(Composite parent) {
        var description = new Link(parent, SWT.WRAP);
        description.setLayoutData(
                GridDataFactory.fillDefaults().indent(0, 10).grab(true, false).hint(SWT.DEFAULT, 80).create());
        description.setText(String.format("%s <a>%s.</a>", contribution.getDescription(), Messages.moreDetails));
        description.addListener(SWT.Selection, new OpenSystemBrowserListener(contribution.getDocumentationLink()));
        // Fix unclickable link in description on Windows
        description.addMouseTrackListener(new MouseTrackAdapter() {

            @Override
            public void mouseEnter(MouseEvent e) {
                description.setFocus();
                parent.setFocus();
            }
        });
    }

}
