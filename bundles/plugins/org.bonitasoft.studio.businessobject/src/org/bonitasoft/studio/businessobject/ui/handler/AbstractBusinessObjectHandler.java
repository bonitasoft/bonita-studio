/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.handler;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractBusinessObjectHandler extends AbstractHandler {

    protected CustomWizardDialog createWizardDialog(IWizard wizard, String finishLabel) {
        return new CustomWizardDialog(getShell(), wizard, finishLabel);
    }

    protected Shell getShell() {
        return Display.getDefault().getActiveShell();
    }

    protected BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> getStore() {
        return RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

}
