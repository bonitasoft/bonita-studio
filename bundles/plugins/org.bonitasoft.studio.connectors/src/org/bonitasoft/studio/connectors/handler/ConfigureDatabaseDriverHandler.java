/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;

import javax.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.preferences.DriverAssociationPage;
import org.bonitasoft.studio.ui.wizard.WizardPageBuilder;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.widgets.Shell;

public class ConfigureDatabaseDriverHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell,
            RepositoryAccessor repositoryAccessor) {
        newWizard()
                .havingPage(WizardPageBuilder.newPage()
                        .withTitle(Messages.configureDatabaseDriverTitle)
                        .withDescription(Messages.configureDatabaseDriverDesc)
                        .withControl(new DriverAssociationPage(repositoryAccessor)))
                .open(shell);
    }


}
