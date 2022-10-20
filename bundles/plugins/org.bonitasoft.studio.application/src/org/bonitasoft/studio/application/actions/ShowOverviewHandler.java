package org.bonitasoft.studio.application.actions;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;


public class ShowOverviewHandler extends AbstractHandler {

    @Override
    public boolean isEnabled() {
        return RepositoryManager.getInstance().hasActiveRepository();
    }

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                    .showView("org.bonitasoft.studio.views.overview");
        } catch (Exception ex) {
            BonitaStudioLog.error(ex);
        }
        return null;
    }


}
