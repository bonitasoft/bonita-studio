/**
 * 
 */
package org.bonitasoft.studio.application.actions;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.IDocumentProviderExtension;

/**
 * @author Romain Bioteau
 *
 */
public class EditorRefreshActionDelegate implements IEditorActionDelegate {

	private TextEditor editor;

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if(editor != null){
			IDocumentProvider provider = editor.getDocumentProvider() ; 
			IDocumentProviderExtension extension= (IDocumentProviderExtension) provider;
			try {
				extension.synchronize(editor.getEditorInput());
			} catch (CoreException e) {
				BonitaStudioLog.error(e) ;
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IEditorActionDelegate#setActiveEditor(org.eclipse.jface.action.IAction, org.eclipse.ui.IEditorPart)
	 */
	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		if(targetEditor instanceof TextEditor){
			this.editor = (TextEditor) targetEditor ;
		}
	}

}
