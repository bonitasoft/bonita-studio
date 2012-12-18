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
package org.bonitasoft.studio.application.contribution;

import java.io.File;
import java.net.URL;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.common.extension.IPostStartupContribution;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Mickael Istria
 *
 */
public class StudioPropertiesContributor implements IPostStartupContribution {

	public void execute() {	
		try{
			File root = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile() ;
			File destFile = new File(root,"studio.properties") ;
			if(!destFile.exists()){
				URL url = ApplicationPlugin.getDefault().getBundle().getResource("/studio.properties");
				File propertyFile = new File(FileLocator.toFileURL(url).getFile()) ;
				PlatformUtil.copyResource(root, propertyFile, new NullProgressMonitor()) ;
			}
		}catch (Exception e) {
			BonitaStudioLog.error(e);
		}
	}

}
