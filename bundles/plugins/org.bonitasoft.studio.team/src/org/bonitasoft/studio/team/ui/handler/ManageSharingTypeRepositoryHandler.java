/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.handler;

import java.util.Map;
import java.util.Map.Entry;

import org.bonitasoft.studio.team.TeamPlugin;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.ui.wizard.ManageSharingTypeRepositoryWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class ManageSharingTypeRepositoryHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		WizardDialog dialog = new WizardDialog(shell, new ManageSharingTypeRepositoryWizard());

		//save values
		Map<String, Object> backup = TeamRepositoryUtil.backUpSettings();
		
		if(Dialog.OK != dialog.open()){
			//come back to old value
			IPreferenceStore preferenceStore = TeamPlugin.getDefault().getPreferenceStore();
			for (Entry<String, Object> entry : backup.entrySet()) {
				Object value = entry.getValue();
				if(value instanceof String){
					preferenceStore.setValue(entry.getKey(), (String) value);
				} else {
					preferenceStore.setValue(entry.getKey(), (Boolean) value);
				}				
			}		
		}
		return null;
	}

}
