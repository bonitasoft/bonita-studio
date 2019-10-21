/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.handler;

import org.eclipse.ui.internal.statushandlers.IStatusDialogConstants;
import org.eclipse.ui.statushandlers.StatusAdapter;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.statushandlers.WorkbenchErrorHandler;
import org.eclipse.ui.statushandlers.WorkbenchStatusDialogManager;

public class BonitaStatusHandler extends WorkbenchErrorHandler {

    @Override
    public void handle(StatusAdapter statusAdapter, int style) {
        if (style == StatusManager.SHOW) {
            String message = statusAdapter.getStatus().getMessage();
            if (message != null && message.contains("Server Tomcat")) {
                return;
            }
        } else {
            super.handle(statusAdapter, style);
        }

    }

    @Override
    protected void configureStatusDialog(WorkbenchStatusDialogManager statusDialog) {
        super.configureStatusDialog(statusDialog);
        statusDialog.setProperty(IStatusDialogConstants.ERRORLOG_LINK, Boolean.FALSE);
    }
}
