/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 */
package org.bonitasoft.studio.connectors.extension;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.extension.ExtensionAction;
import org.bonitasoft.studio.common.repository.extension.ExtensionActionRegistry;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.bonitasoft.studio.ui.widget.DynamicButtonWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class DatabaseConnectorActionExtension implements ExtensionAction {

    private static final String DATABASE_EXTENSION_ID = "org.bonitasoft.connectors:bonita-connector-database";
    private static final String DATABASE_DRIVER_SETTINGS_COMMAND_ID = "org.bonitasoft.studio.connectors.database.driver.settings.command";
    private static final String DATABASE_ADD_DRIVER_COMMAND_ID = "org.bonitasoft.studio.connectors.database.driver.add.command";
    private CommandExecutor commandExecutor = new CommandExecutor();

    @PostConstruct
    public void registerExtensionAction() {
        ExtensionActionRegistry.getInstance().register(this);
    }

    @Override
    public String getExtensionId() {
        return DATABASE_EXTENSION_ID;
    }

    @Override
    public void fill(Composite parent) {
        var composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.FILL).create());
        composite.setData(BonitaThemeConstants.CSS_CLASS_PROPERTY_NAME, BonitaThemeConstants.CARD_BACKGROUND);

        new DynamicButtonWidget.Builder()
                .withText(commandExecutor.getCommandName(DATABASE_ADD_DRIVER_COMMAND_ID))
                .withImage(Pics.getImage("database_driver_add.png", ConnectorPlugin.getDefault()))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> commandExecutor
                        .executeCommand(DATABASE_ADD_DRIVER_COMMAND_ID, Collections.emptyMap()))
                .createIn(composite);

        new DynamicButtonWidget.Builder()
                .withText(commandExecutor.getCommandName(DATABASE_DRIVER_SETTINGS_COMMAND_ID))
                .withImage(Pics.getImage("database_driver_settings.png", ConnectorPlugin.getDefault()))
                .withCssclass(BonitaThemeConstants.CARD_BACKGROUND)
                .onClick(e -> commandExecutor
                        .executeCommand(DATABASE_DRIVER_SETTINGS_COMMAND_ID, Collections.emptyMap()))
                .createIn(composite);
    }

}
