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

import org.bonitasoft.studio.common.extension.DashboardContribution;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DashboardCard extends Composite {

    private DashboardContribution contribution;

    public DashboardCard(Composite parent, DashboardContribution contribution) {
        super(parent, SWT.BORDER);
        this.contribution = contribution;

        setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var typeColorComposite = new Composite(this, SWT.NONE);
        typeColorComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        typeColorComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(50, SWT.DEFAULT).create());
        typeColorComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, contribution.getColorCssClass());

        var contentComposite = new Composite(this, SWT.NONE);
        contentComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createTitleComposite(contentComposite);
        createDescriptionLabel(contentComposite);
    }

    protected void createTitleComposite(Composite parent) {
        var titleComposite = new Composite(parent, SWT.NONE);
        titleComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        titleComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        var titleLabel = new CLabel(titleComposite, SWT.NONE);
        titleLabel.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, SWTBotConstants.extensionCardId(contribution.getName()));
        titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        titleLabel.setText(contribution.getName());

        titleLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.BOLD_4_FONT_ID));
        titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
    }

    protected void createDescriptionLabel(Composite parent) {
        var description = new Label(parent, SWT.WRAP);
        description.setLayoutData(
                GridDataFactory.fillDefaults().indent(0, 10).grab(true, false).hint(SWT.DEFAULT, 50).create());
        description.setText(contribution.getDescription());
    }

}
