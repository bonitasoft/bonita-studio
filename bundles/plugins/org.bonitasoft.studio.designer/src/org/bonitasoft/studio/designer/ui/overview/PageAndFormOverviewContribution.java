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
package org.bonitasoft.studio.designer.ui.overview;

import org.bonitasoft.studio.common.extension.OverviewContribution;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

public class PageAndFormOverviewContribution implements OverviewContribution {

    private static final String OPEN_UID_COMMAND = "org.bonitasoft.studio.pagedesigner.open.command";

    @Override
    public String getName() {
        return Messages.pageAndFormOverviewName;
    }

    @Override
    public String getDescription() {
        return Messages.pageAndFormOverviewDescription;
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.page32);
    }

    @Override
    public String getDocumentationLink() {
        return "https://documentation.bonitasoft.com/bonita/latest/ui-designer-overview";
    }

    @Override
    public void contributeActions(Composite parent) {
        var toolbarComposite = new Composite(parent, SWT.NONE);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.FILL).create());
        toolbarComposite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        new DynamicButtonWidget.Builder()
                .withText(org.bonitasoft.studio.common.Messages.openUid)
                .withImage(Pics.getImage(PicsConstants.open))
                .withHotImage(Pics.getImage(PicsConstants.openHot))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> commandExecutor.executeCommand(OPEN_UID_COMMAND, null))
                .createIn(toolbarComposite);
    }

}
