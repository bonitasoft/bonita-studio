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
package org.bonitasoft.studio.la.application.ui.dashboard;

import org.bonitasoft.studio.common.extension.DashboardContribution;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ApplicationDashboardContribution implements DashboardContribution {

    public static final String NEW_APPLICATION_COMMAND = "org.bonitasoft.studio.la.new.command";

    @Override
    public Category getCategory() {
        return Category.PROJECT;
    }

    @Override
    public String getName() {
        return Messages.applications;
    }

    @Override
    public String getDescription() {
        return Messages.dashboardApplicationDescription;
    }

    @Override
    public String getColorCssClass() {
        return BonitaThemeConstants.DASHBOARD_APPLICATION_BACKGROUND;
    }

    @Override
    public String getDocumentationLink() {
        return "https://documentation.bonitasoft.com/bonita/latest/applications";
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
                .onClick(e -> commandExecutor.executeCommand(NEW_APPLICATION_COMMAND, null))
                .createIn(toolbarComposite);
    }

    @Override
    public int getPriority() {
        return 0;
    }

}
