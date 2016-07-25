package org.bonitasoft.studio.intro.actions;

import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
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
			ICommandService cmdService = (ICommandService)part.getSite().getService( ICommandService.class);
			// Do not replace by static link since this command does not resolve to the same between BOS and SP
			Command open = cmdService.getCommand("org.bonitasoft.studio.diagram.command.openDiagram");//$NON-NLS-1$
			ExecutionEvent executionEvent = handlerService.createExecutionEvent(open, null);
			try {
				open.executeWithChecks(executionEvent);
			} catch (ExecutionException e) {
				BonitaStudioLog.error(e);
			} catch (NotDefinedException e) {
				BonitaStudioLog.error(e);
			} catch (NotEnabledException e) {
				BonitaStudioLog.error(e);
			} catch (NotHandledException e) {
				BonitaStudioLog.error(e);
			}
		} catch (Exception e) {
			BonitaStudioLog.error(e);
		}
	}

}
