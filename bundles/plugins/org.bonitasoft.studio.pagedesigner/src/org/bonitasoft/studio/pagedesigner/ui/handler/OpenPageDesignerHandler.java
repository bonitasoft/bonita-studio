package org.bonitasoft.studio.pagedesigner.ui.handler;

import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.browser.operation.OpenBrowserOperation;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.pagedesigner.PageDesignerPlugin;
import org.bonitasoft.studio.pagedesigner.core.PageDesignerURLBuilder;
import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;


public class OpenPageDesignerHandler extends AbstractHandler {



    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final PageDesignerURLBuilder pageDesignerURLBuilder = new PageDesignerURLBuilder(getPreferenceStore());
        try {
            createOpenBrowserOperation(pageDesignerURLBuilder.build()).execute();
        } catch (final MalformedURLException e) {
            if (!FileActionDialog.getDisablePopup()) {
                BonitaErrorDialog.openError(Display.getDefault().getActiveShell(),
                        Messages.invalidURLTitle,
                        Messages.invalidURLMsg,
                        new Status(IStatus.ERROR, PageDesignerPlugin.PLUGIN_ID, e.getMessage(), e));
            }

            throw new ExecutionException("Failed to open web browser", e);
        }
        return null;
    }

    protected OpenBrowserOperation createOpenBrowserOperation(final URL url) throws MalformedURLException {
        return new OpenBrowserOperation(url);
    }

    protected IPreferenceStore getPreferenceStore() {
        return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
    }

}
