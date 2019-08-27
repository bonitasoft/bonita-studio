/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.operation;

import org.bonitasoft.engine.api.result.Status;
import org.bonitasoft.engine.api.result.StatusContext;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.EngineStatusMapper;


public class DeployStatusMapper extends EngineStatusMapper {
    
    private static DeployStatusMapper INSTANCE;

    public static DeployStatusMapper instance() {
        if (INSTANCE == null) {
            INSTANCE = new DeployStatusMapper();
        }
        return INSTANCE;
    }

    @Override
    public String localizedMessage(Status status) {
        StatusContext context = status.getContext();
        switch (status.getCode()) {
            case PROCESS_DEPLOYMENT_IMPOSSIBLE_UNRESOLVED:
                return String.format(Messages.cannotResolveProcess,
                        context.get(StatusContext.PROCESS_NAME_KEY),
                        context.get(StatusContext.PROCESS_VERSION_KEY),
                        context.get(StatusContext.PROCESS_RESOLUTION_PROBLEM_DESCRIPTION_KEY));
            case PROCESS_DEPLOYMENT_ENABLEMENT_KO:
                return String.format(Messages.cannotEnableProcess,
                        context.get(StatusContext.PROCESS_NAME_KEY),
                        context.get(StatusContext.PROCESS_VERSION_KEY));
            case LIVING_APP_REFERENCES_UNKNOWN_APPLICATION_PAGE:
                return String.format(Messages.appDescriptorUnknownPageToken,
                        context.get(StatusContext.LIVING_APPLICATION_TOKEN_KEY),
                        context.get(StatusContext.LIVING_APPLICATION_INVALID_ELEMENT_NAME));
            case LIVING_APP_REFERENCES_UNKNOWN_PAGE:
                return String.format(Messages.appDescriptorUnknownAppPage,
                        context.get(StatusContext.LIVING_APPLICATION_TOKEN_KEY),
                        context.get(StatusContext.LIVING_APPLICATION_INVALID_ELEMENT_NAME));
            case LIVING_APP_REFERENCES_UNKNOWN_LAYOUT:
                return String.format(Messages.appDescriptorUnknownLayoutPage,
                        context.get(StatusContext.LIVING_APPLICATION_TOKEN_KEY),
                        context.get(StatusContext.LIVING_APPLICATION_INVALID_ELEMENT_NAME));
            case LIVING_APP_REFERENCES_UNKNOWN_THEME:
                return String.format(Messages.appDescriptorUnknownTheme,
                        context.get(StatusContext.LIVING_APPLICATION_TOKEN_KEY),
                        context.get(StatusContext.LIVING_APPLICATION_INVALID_ELEMENT_NAME));
            default:
                return super.localizedMessage(status);
        }
    }

}
