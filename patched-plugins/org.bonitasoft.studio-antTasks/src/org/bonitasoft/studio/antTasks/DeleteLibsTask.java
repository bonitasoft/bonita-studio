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
package org.bonitasoft.studio.antTasks;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author Romain Bioteau
 *
 */
public class DeleteLibsTask extends Task {

	private String destFolder;
	private String startingWith;


	@Override
	public void execute() throws BuildException {

		try {
			File contentFolderFile = new File(destFolder);
			if(!contentFolderFile.exists()){
				log(contentFolderFile.getName()+"doesn't exists"); //$NON-NLS-1$
				throw new BuildException("Dest folder doesn't exists"); //$NON-NLS-1$
			}

			for (File f :contentFolderFile.listFiles(new FilenameFilter() {
				
				public boolean accept(File dir, String name) {
					return name.startsWith(startingWith);
				}
			})) {
		        
		        log("try to delete :"+f.getAbsolutePath()); //$NON-NLS-1$
		        if(f.exists()){
		        	f.delete() ;
		        	log(f.getAbsolutePath()+" deleted"); //$NON-NLS-1$
		        }
		    }
		     			
		} catch (Exception e) {
			throw new BuildException(e);
		}

	}

	
	/**
	 * @param destFolder the destFolder to set
	 */
	public void setDestFolder(String destFolder) {
		this.destFolder = destFolder;
	}	
	
	/**
	 * @param startingWith the startingWith to set
	 */
	public void setStartingWith(String startingWith) {
		this.startingWith = startingWith;
	}

}
