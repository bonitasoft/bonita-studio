/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.pagedesigner.ui.handler;

import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.browser.operation.OpenBrowserOperation;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.pagedesigner.PageDesignerPlugin;
import org.bonitasoft.studio.pagedesigner.core.PageDesignerURLFactory;
import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class OpenUIDesignerHandler extends AbstractHandler {

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        execute();
        return null;
    }

    public void execute() throws ExecutionException {
        final PageDesignerURLFactory pageDesignerURLBuilder = new PageDesignerURLFactory(getPreferenceStore());
        try {
            createOpenBrowserOperation(pageDesignerURLBuilder.openPageDesignerHome()).execute();
        } catch (final MalformedURLException e) {
            if (!FileActionDialog.getDisablePopup()) {
                BonitaErrorDialog.openError(Display.getDefault().getActiveShell(),
                        Messages.invalidURLTitle,
                        Messages.invalidURLMsg,
                        new Status(IStatus.ERROR, PageDesignerPlugin.PLUGIN_ID, e.getMessage(), e));
            }
            throw new ExecutionException("Failed to open web browser", e);
        }
    }

    protected OpenBrowserOperation createOpenBrowserOperation(final URL url) throws MalformedURLException {
        return new OpenBrowserOperation(url);
    }

    protected IEclipsePreferences getPreferenceStore() {
        return InstanceScope.INSTANCE.getNode(BonitaStudioPreferencesPlugin.PLUGIN_PREFERENCE_SCOPE);
    }

}
