/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.intro.actions;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

/**
 * @author Mickael Istria
 * 
 */
public class OpenSpecificProcessAction implements IIntroAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IIntroSite site, Properties params) {
		String fileName;
		try {
			fileName = URLDecoder.decode(params.getProperty("file"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			fileName = params.getProperty("file");
		}
		
		final IEditorReference openEditor = PlatformUtil.getOpenEditor(fileName);
		if(openEditor!=null){
			PlatformUtil.swtichToOpenedEditor(openEditor);
		} else {
			DiagramRepositoryStore diagramSotre = (DiagramRepositoryStore) RepositoryManager
					.getInstance().getCurrentRepository()
					.getRepositoryStore(DiagramRepositoryStore.class);
			IRepositoryFileStore store = diagramSotre.getChild(fileName, true);
			store.open();
		}
	}

}
