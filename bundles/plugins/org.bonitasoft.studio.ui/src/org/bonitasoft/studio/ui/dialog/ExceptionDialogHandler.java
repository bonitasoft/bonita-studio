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
package org.bonitasoft.studio.ui.dialog;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Singleton;

import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;

@Singleton
@Creatable
public class ExceptionDialogHandler {

    private static final String PLUGIN_ID = "org.bonitasoft.studio.ui"; //$NON-NLS-1$

    private static final ILog LOG = Platform.getLog(Platform.getBundle(PLUGIN_ID));

    public void openErrorDialog(Shell shell, String errorMessage, Throwable t) {
        final Status status = createErrorStatus(t);
        LOG.log(status);
        new ErrorDialog(shell, Messages.errorTitle, errorMessage,
                status, IStatus.ERROR).open();
    }

    private Status createErrorStatus(Throwable t) {
        Throwable exception = InvocationTargetException.class.isInstance(t)
                ? ((InvocationTargetException) t).getTargetException() : t;
        return new Status(IStatus.ERROR, PLUGIN_ID, exception.getMessage(), exception);
    }

}
