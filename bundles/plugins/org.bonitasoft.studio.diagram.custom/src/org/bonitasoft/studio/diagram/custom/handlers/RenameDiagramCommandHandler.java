/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.handlers;

import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.refactoring.ProcessNamingTools;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;


/**
 * @author Romain Bioteau
 *
 */
public class RenameDiagramCommandHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		MainProcess diagram =  getMainProcess();
		DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
		final OpenNameAndVersionForDiagramDialog nameDialog = new OpenNameAndVersionForDiagramDialog(Display.getDefault().getActiveShell(),diagram,diagramStore) ;
		if(nameDialog.open() == Dialog.OK ) {
			DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
		
			EObject currentObject = editor.getDiagramEditPart().resolveSemanticElement();
			while(!(currentObject instanceof MainProcess)){
				currentObject = currentObject.eContainer();
			}			

			MainProcess newProcess = (MainProcess) currentObject;			
			EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(newProcess);
			
			final ProcessNamingTools tool = new ProcessNamingTools(domain);
			tool.changeProcessNameAndVersion(newProcess, nameDialog.getDiagramName(), nameDialog.getDiagramVersion());
			for(ProcessesNameVersion pnv : nameDialog.getPools()){
				tool.changeProcessNameAndVersion(pnv.getAbstractProcess(), pnv.getNewName(), pnv.getNewVersion());
			}
			try{
				ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class) ;
				org.eclipse.core.commands.Command c = service.getCommand("org.eclipse.ui.file.save") ;
				if(c.isEnabled()){
					final Boolean askForRename = BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().getBoolean(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE);
					BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, false);
					c.executeWithChecks(new ExecutionEvent()) ;
					BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.ASK_RENAME_ON_FIRST_SAVE, askForRename);
				}
			}catch (Exception e) {
				BonitaStudioLog.error(e) ;
			}
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		return getMainProcess() != null;
	}

	protected MainProcess getMainProcess() {
		if(PlatformUI.isWorkbenchRunning()){
			IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if(editor instanceof ProcessDiagramEditor || editor instanceof FormDiagramEditor){
				EObject mainElement = ((DiagramEditor)editor).getDiagramEditPart().resolveSemanticElement();
				MainProcess diagram = ModelHelper.getMainProcess(mainElement);
				return diagram;
			}
		}
		return null;
	}
}
