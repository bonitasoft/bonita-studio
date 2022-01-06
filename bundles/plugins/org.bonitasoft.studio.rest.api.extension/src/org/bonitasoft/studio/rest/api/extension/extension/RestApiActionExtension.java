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
package org.bonitasoft.studio.rest.api.extension.extension;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.bonitasoft.studio.application.views.extension.card.ExtensionCard;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.extension.ExtensionAction;
import org.bonitasoft.studio.common.repository.extension.ExtensionActionRegistry;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class RestApiActionExtension implements ExtensionAction {

    private static final String EDIT_PERMISSION_COMMAND = "org.bonitasoft.studio.rest.api.extension.editPermissionCommand";
    private CommandExecutor commandExecutor = new CommandExecutor();

    @PostConstruct
    public void registerExtensionAction() {
        ExtensionActionRegistry.getInstance().register(this);
    }

    @Override
    public String getExtensionId() {
        return ExtensionCard.REST_API_EXTENSION_ACTION_ID;
    }

    @Override
    public void fill(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.FILL).create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        new DynamicButtonWidget.Builder()
                .withLabel(commandExecutor.getCommandName(EDIT_PERMISSION_COMMAND))
                .withImage(Pics.getImage(PicsConstants.edit_simple))
                .withHotImage(Pics.getImage(PicsConstants.edit_simple_hot))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> commandExecutor.executeCommand(EDIT_PERMISSION_COMMAND, Collections.emptyMap()))
                .createIn(composite);
    }

}
