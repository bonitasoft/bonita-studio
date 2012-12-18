package org.bonitasoft.studio.examples.handler;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.examples.i18n.Messages;
import org.bonitasoft.studio.examples.wizard.OpenExampleWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.ui.PlatformUI;

public class OpenExampleDiagramCommandHandler extends AbstractHandler implements IHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		final OpenExampleWizard wizard = new OpenExampleWizard();
		new CustomWizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				wizard, false, Messages.OpenProcessButtonLabel).open();

		return null;
	}

}
