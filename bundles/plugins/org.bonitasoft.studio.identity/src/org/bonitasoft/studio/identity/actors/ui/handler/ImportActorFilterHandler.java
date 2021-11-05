/**
 * Copyright (C) 2009-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.actors.ui.handler;

import org.bonitasoft.studio.connectors.handler.ImportConnectorHandler;
import org.bonitasoft.studio.connectors.repository.ImportConnectorArchiveOperation;
import org.bonitasoft.studio.identity.actors.repository.ImportActorFilterArchiveOperation;
import org.bonitasoft.studio.identity.i18n.Messages;

public class ImportActorFilterHandler extends ImportConnectorHandler {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.handler.ImportConnectorHandler#newImportOperation()
     */
    @Override
    protected ImportConnectorArchiveOperation newImportOperation() {
        return new ImportActorFilterArchiveOperation();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.handler.ImportConnectorHandler#getDialogTitle()
     */
    @Override
    protected String getDialogTitle() {
        return Messages.importFilterArchive;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.handler.ImportConnectorHandler#getFailedImportTitle()
     */
    @Override
    protected String getFailedImportTitle() {
        return Messages.importFailedTitle;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.handler.ImportConnectorHandler#getFailedTitleMessage()
     */
    @Override
    protected String getFailedImportMessage() {
        return Messages.importFailedMsg;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.handler.ImportConnectorHandler#getSuccessDialogMessage()
     */
    @Override
    protected String getImportSuccessMessage() {
        return Messages.importSuccessfulMsg;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.handler.ImportConnectorHandler#getSuccessDialogTitle()
     */
    @Override
    protected String getImportSuccessTitle() {
        return Messages.importSuccessfulTitle;
    }
}
