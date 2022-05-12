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
package org.bonitasoft.studio.application.operation.extension.participant.configuration;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.repository.extension.update.preview.PreviewMessageProvider;

public class ProcessConfigurationPreviewMessageProvider implements PreviewMessageProvider {

    @Override
    public String getTitle() {
        return Messages.updateProcessesTitle;
    }

    @Override
    public String getMessage() {
        return String.format("%s  %s", Messages.otherDependencyUpdateMessage, Messages.updateActionsMessage);
    }

}
