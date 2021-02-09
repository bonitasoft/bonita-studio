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
package org.bonitasoft.studio.application.views.extension;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class ProjectExtensionViewPart extends ViewPart {

    public static final String ID = "org.bonitasoft.studio.application.extension.view";

    private static final String OPEN_MARKETPLACE_COMMAND = "org.bonitasoft.studio.application.extend.project.command";

    private RepositoryAccessor repositoryAccessor;
    private LocalResourceManager localResourceManager;
    private CommandExecutor commandExecutor;
    private Font titleFont;

    public ProjectExtensionViewPart() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();

        commandExecutor = new CommandExecutor();
    }

    @Override
    public void createPartControl(Composite parent) {
        initVariables(parent);

        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 800).create());

        createTitleAndToolbar(mainComposite);

        Label separator = new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

    }

    private void createTitleAndToolbar(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        Label title = new Label(composite, SWT.NONE);
        title.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        title.setText(repositoryAccessor.getCurrentRepository().getName());
        title.setFont(titleFont);
        title.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);

        Composite toolbarsComposite = new Composite(composite, SWT.NONE);
        toolbarsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        toolbarsComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        createMarketplaceButton(toolbarsComposite);
    }

    private void createMarketplaceButton(Composite parent) {
        new DynamicButtonWidget.Builder()
                .withText(Messages.openMarketplace)
                .withMaxTextWidth(150)
                .withTooltipText(Messages.openMarketplaceTooltip)
                .withImage(Pics.getImage(PicsConstants.openMarketplace))
                .withHotImage(Pics.getImage(PicsConstants.openMarketplaceHot))
                .onClick(e -> commandExecutor.executeCommand(OPEN_MARKETPLACE_COMMAND, null))
                .createIn(parent);
    }

    private void initVariables(Composite parent) {
        localResourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);
        titleFont = new Font(Display.getDefault(), "titleFont", 30, SWT.BOLD);
    }

    @Override
    public void setFocus() {
    }

    @Override
    public void dispose() {
        titleFont.dispose();
        super.dispose();
    }

}
