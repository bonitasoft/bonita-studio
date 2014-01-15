/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.handler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.processors.ImportFileOperation;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Romain Bioteau
 * 
 */
public class ImportHandler extends AbstractHandler {


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.
	 * ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ImportFileWizard importFileWizard = new ImportFileWizard();
		new CustomWizardDialog(Display.getDefault().getActiveShell(), importFileWizard,Messages.importButtonLabel).open();
		String absoluteFilePath = importFileWizard.getSelectedFilePath();
		if (absoluteFilePath == null) {
			return null;
		}
		File selectedFile = new File(absoluteFilePath);
		final ImportFileOperation operation = new ImportFileOperation(importFileWizard.getSelectedTransfo(),selectedFile);
		IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
		try {
			progressManager.run(true, false, operation);
		} catch (InvocationTargetException e) {
			BonitaStudioLog.error("Import has failed for file "+ selectedFile.getName(), ImporterPlugin.PLUGIN_ID);
			BonitaStudioLog.error(e,ImporterPlugin.PLUGIN_ID);
			String message =  Messages.errorWhileImporting_message;
			if(e.getMessage() != null && !e.getMessage().isEmpty()){
				message = e.getMessage();
			}
			new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.errorWhileImporting_title,message,e).open();
			return null;
		} catch (InterruptedException e) {
			BonitaStudioLog.error("Import has failed for file "+ selectedFile.getName(), ImporterPlugin.PLUGIN_ID);
			BonitaStudioLog.error(e,ImporterPlugin.PLUGIN_ID);
			String message =  Messages.errorWhileImporting_message;
			if(e.getMessage() != null && !e.getMessage().isEmpty()){
				message = e.getMessage();
			}
			new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.errorWhileImporting_title,message,e).open();
			return null;
		}

		for(final DiagramFileStore fileStore : operation.getFileStoresToOpen()){
			fileStore.open();
		}
		PlatformUtil.closeIntro() ;
		PlatformUtil.openIntroIfNoOtherEditorOpen() ;

		return null;
	}



}
