/**
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.handler;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.connectors.repository.ConnectorImplFileStore;
import org.bonitasoft.studio.connectors.ui.wizard.ExportConnectorWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

public class ExportSingleConnectorHandler extends AbstractHandler {

    private RepositoryAccessor repositoryAccessor;
    private FileStoreFinder fileStoreFinder;

    public ExportSingleConnectorHandler() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        fileStoreFinder = new FileStoreFinder();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        fileStoreFinder
                .findSelectedFileStore(repositoryAccessor.getCurrentRepository())
                .filter(ConnectorImplFileStore.class::isInstance)
                .map(ConnectorImplFileStore.class::cast)
                .map(ConnectorImplFileStore::getContent)
                .ifPresent(impl -> {
                    WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),
                            new ExportConnectorWizard(impl));
                    dialog.open();
                });
        return null;
    }

}
