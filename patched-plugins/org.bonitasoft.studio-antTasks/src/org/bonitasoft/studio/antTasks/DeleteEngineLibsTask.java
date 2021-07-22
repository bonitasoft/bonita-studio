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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author Romain Bioteau
 *
 */
public class DeleteEngineLibsTask extends Task {


	private static final String LIB_PROPERTY_FILENAME = "lib.properties"; //$NON-NLS-1$
	private static final String HISTORY_PROPERTY_FILENAME = "history.properties";
	private static final String FORMS = "FORMS";
	private static final String CONSOLE = "CONSOLE";

	private String parentDir;
	private String destFolder;
	private String engineLibsBundle;

	@Override
	public void execute() throws BuildException {
		FileInputStream in = null ;
		FileInputStream inHistory = null ;

		try {
			File contentFolderFile = new File(destFolder);
			if(!contentFolderFile.exists()){
				log(contentFolderFile.getName()+"doesn't exists"); //$NON-NLS-1$
				throw new BuildException("Dest folder doesn't exists"); //$NON-NLS-1$
			}

			File parentFolderFile = new File(parentDir);
			if(!parentFolderFile.exists()){
				log(parentFolderFile.getName()+"doesn't exists"); //$NON-NLS-1$
				throw new BuildException("Dest folder doesn't exists"); //$NON-NLS-1$
			}


			File deletionHistrotyFile = new File(parentFolderFile,HISTORY_PROPERTY_FILENAME);
			if(!deletionHistrotyFile.exists()) {
				deletionHistrotyFile.createNewFile() ;
			}

			inHistory = new FileInputStream(deletionHistrotyFile) ;
	

			Properties historyProperties = new Properties();
			historyProperties.load(inHistory);
			log("before delete properties="+historyProperties.keySet().toString()); //$NON-NLS-1$
			
			String type = "";
			if(destFolder.contains("bonita-app")){
				type = FORMS ; 
			}else if(destFolder.contains("bonita")){
				type = CONSOLE ; 
			}



			File propertyFile = new File(parentFolderFile.getParent()+File.separatorChar+ engineLibsBundle, LIB_PROPERTY_FILENAME);
			if(!propertyFile.exists())
				throw new BuildException("lib.properties doesn't exists"); //$NON-NLS-1$

			in = new FileInputStream(propertyFile);
			Properties p = new Properties();
			p.load(in) ;
			for (Object filename : p.keySet()) {
				File toDelete = new File(contentFolderFile, filename.toString());	
				if(toDelete.exists()){
					log("sotring: "+toDelete.getName()+","+type); //$NON-NLS-1$
					historyProperties.setProperty(toDelete.getName(),type) ;
					log("after delete properties="+historyProperties.keySet().toString()); //$NON-NLS-1$
					toDelete.delete() ;
					log(toDelete.getAbsolutePath()+" deleted"); //$NON-NLS-1$
				}else{
					for(String file : contentFolderFile.list()){
						if(file.startsWith(filename.toString())){
							toDelete = new File(contentFolderFile, file);
							if(toDelete.exists()){
								log("sotring: "+toDelete.getName()+","+type); //$NON-NLS-1$
								historyProperties.setProperty(toDelete.getName(),type) ;
								log("after delete properties="+historyProperties.keySet().toString()); //$NON-NLS-1$
								toDelete.delete() ;
								log(toDelete.getAbsolutePath()+" deleted"); //$NON-NLS-1$
							}
						}
					}
				}
			}
			FileOutputStream fw = new FileOutputStream(deletionHistrotyFile) ;
			historyProperties.store(fw,null) ;
			fw.close() ;
			log("after store properties="+historyProperties.keySet().toString()); //$NON-NLS-1$

		} catch (Exception e) {
			throw new BuildException(e);
		}finally{
			try {
				if (in != null) {
					in.close();
				}
				if (inHistory != null) {
					inHistory.close() ;
				}
			} catch (IOException e) {
				throw new BuildException(e);
			}
		}

	}


	/**
	 * @param destFolder the destFolder to set
	 */
	public void setDestFolder(String destFolder) {
		this.destFolder = destFolder;
	}	

	/**
	 * @param parentDir the parentDir to set
	 */
	public void setParentDir(String parentDir) {
		this.parentDir = parentDir;
	}
	
	public void setEngineLibsBundle(String bundleName) {
		this.engineLibsBundle = bundleName;
	}

}
