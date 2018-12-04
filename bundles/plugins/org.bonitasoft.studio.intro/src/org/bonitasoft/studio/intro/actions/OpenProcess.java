package org.bonitasoft.studio.intro.actions;

import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.Command;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

public class OpenProcess implements IIntroAction {

    public void run(IIntroSite site, Properties param) {
        try {
            IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
            IHandlerService handlerService = (IHandlerService) part.getSite().getService(IHandlerService.class);
            ICommandService cmdService = (ICommandService) part.getSite().getService(ICommandService.class);
            // Do not replace by static link since this command does not resolve to the same between BOS and SP
            Command open = cmdService.getCommand("org.bonitasoft.studio.diagram.command.openDiagram");//$NON-NLS-1$
            if (open.isEnabled()) {
                open.executeWithChecks(handlerService.createExecutionEvent(open, null));
            }
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }
    }

}
