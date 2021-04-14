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
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

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
    public void fill(ToolBar toolbar) {
        ToolItem toolItem = new ToolItem(toolbar, SWT.LEFT | SWT.PUSH);
        toolItem.setText(commandExecutor.getCommandName(DATABASE_ADD_DRIVER_COMMAND_ID));
        toolItem.setImage(Pics.getImage("database_driver_add.png", ConnectorPlugin.getDefault()));
        toolItem.addListener(SWT.Selection, e -> commandExecutor
                .executeCommand(DATABASE_ADD_DRIVER_COMMAND_ID, Collections.emptyMap()));

        ToolItem settingsItem = new ToolItem(toolbar, SWT.LEFT | SWT.PUSH);
        settingsItem.setText(commandExecutor.getCommandName(DATABASE_DRIVER_SETTINGS_COMMAND_ID));
        settingsItem.setImage(Pics.getImage("database_driver_settings.png", ConnectorPlugin.getDefault()));
        settingsItem.addListener(SWT.Selection, e -> commandExecutor
                .executeCommand(DATABASE_DRIVER_SETTINGS_COMMAND_ID, Collections.emptyMap()));
    }

}
