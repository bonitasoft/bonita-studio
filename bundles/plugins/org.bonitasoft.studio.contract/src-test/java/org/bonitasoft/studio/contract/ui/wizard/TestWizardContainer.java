/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.ui.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Shell;

final class TestWizardContainer implements IWizardContainer {

    private final Shell shell;

    public TestWizardContainer(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable)
            throws InvocationTargetException, InterruptedException {
        runnable.run(new NullProgressMonitor());
    }

    @Override
    public void updateWindowTitle() {

    }

    @Override
    public void updateTitleBar() {

    }

    @Override
    public void updateMessage() {

    }

    @Override
    public void updateButtons() {

    }

    @Override
    public void showPage(IWizardPage page) {

    }

    @Override
    public Shell getShell() {
        return shell;
    }

    @Override
    public IWizardPage getCurrentPage() {
        return null;
    }
}
