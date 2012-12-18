/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.userguidance;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.application.contribution.IPreStartupContribution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.userguidance.wizards.DocSynchronizer;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Romain Bioteau
 *
 */
public class InstallGuidanceContribution implements IPreStartupContribution{

	@Override
	public void execute() {
		File root = new File(DocSynchronizer.DOC_ROOT) ;
		URL url = UserGuidancePlugin.getDefault().getBundle().getResource("html") ;
		try {
			File sourceFile = new File(FileLocator.toFileURL(url).getFile()) ;
			File dest = new File(root,"html") ;
			PlatformUtil.copyResource(dest, sourceFile,new FilenameFilter() {

				@Override
				public boolean accept(File arg0, String arg1) {
					return !arg1.equals(".svn") && !arg1.equals(".DS_STORE") ;
				}
			}, new NullProgressMonitor()) ;
		} catch (IOException e) {
			BonitaStudioLog.error(e) ;
		} 
	}

	@Override
	public boolean canExecute() {
		return DocSynchronizer.getLatestDocRoot().isEmpty();
	}

}
