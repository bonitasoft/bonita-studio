package org.bonitasoft.studio.application.actions;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

public class RefreshHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		EditorRefreshActionDelegate action = new EditorRefreshActionDelegate() ; 
		action.setActiveEditor(null, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
		action.run(null) ;
		return null;
	}

	

}
