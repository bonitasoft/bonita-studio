/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.test.swtbot.util.conditions;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class EditorOpenCondition extends DefaultCondition {

	private final IFile file;

	public EditorOpenCondition(IFile file) {
		this.file = file;
	}

	public String getFailureMessage() {
		return String.format("Timed out waiting for editor on %s to open",file.getFullPath());
	}

	public boolean test() throws Exception {
		if (!file.exists()) {
			return false;
		}
		return UIThreadRunnable.syncExec(new BoolResult() {

			public Boolean run() {
				IEditorReference[] editorReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
				for (IEditorReference reference: editorReferences) {
					try {
						IEditorInput input = reference.getEditorInput();
						if (input instanceof IFileEditorInput) {
							IFileEditorInput editorInput = (IFileEditorInput) input;
							if (editorInput.getFile().equals(file)) {
								return true;
							}
						}
						if (input instanceof IStorageEditorInput) {
							IStorageEditorInput editorInput = (IStorageEditorInput) input;
							IPath fullPath = editorInput.getStorage().getFullPath();
							if (fullPath.equals(file.getFullPath())) {
								return true;
							}
						}
					} catch (PartInitException e) {
						BonitaStudioLog.error(e);
					} catch (CoreException e) {
						BonitaStudioLog.error(e);
					}
				}
				return false;
			}
		});
	}

}
