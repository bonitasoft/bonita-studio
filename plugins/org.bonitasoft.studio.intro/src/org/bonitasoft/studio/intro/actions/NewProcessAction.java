package org.bonitasoft.studio.intro.actions;

import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

public class NewProcessAction implements IIntroAction {


	public void run(IIntroSite action, Properties param) {
		ICommandService service = (ICommandService)PlatformUI.getWorkbench().getService(ICommandService.class);
		Command cmd = service.getCommand("org.bonitasoft.studio.diagram.command.newDiagram") ;
		try {
			cmd.executeWithChecks(new ExecutionEvent()) ;
		} catch (Exception e){
			BonitaStudioLog.error(e) ;
		}
	}

}
