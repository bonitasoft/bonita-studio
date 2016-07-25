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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Reference;

/**
 * @author Romain Bioteau
 * @author Aurelie Pupier - add support for rest
 *
 */
public class GenerateClientServerPropertyFile extends Task {


	private static final String SERVER = "server"; //$NON-NLS-1$
	private static final String CLIENT = "client"; //$NON-NLS-1$
	//private static final String REST = "rest"; //$NON-NLS-1$

	private static final String LIB_PROPERTY_FILENAME = "lib.properties"; //$NON-NLS-1$
	private static final String SERVER_CLIENT = "server_client"; //$NON-NLS-1$
	//private static final String SERVER_REST = "server_rest"; //$NON-NLS-1$
	//there are no libs in client server at the same time

	private String destFolder;
	private Vector<FileSet> filesets = new Vector<FileSet>();


	@Override
	public void execute() throws BuildException {
		
		
		System.err.println("Starting lib.properties creation...");
		
		FileOutputStream out = null ;
		try {
			File contentFolderFile = new File(destFolder);
			if(!contentFolderFile.exists()){
				throw new BuildException("Dest folder doesn't exists"); //$NON-NLS-1$
			}

			File propertyFile = new File(contentFolderFile,LIB_PROPERTY_FILENAME);
			propertyFile.delete();
			propertyFile.createNewFile();


			out = new FileOutputStream(propertyFile);
			Properties p = new Properties();


			Map<File, String> map = new HashMap<File, String>();

			DirectoryScanner ds = null ;
			
			if(filesets.isEmpty()){
				System.err.println("No fileset defined");
			}
			
			for (FileSet fileset : filesets) {
				Reference id = fileset.getRefid() ; 
				System.err.println("Fileset "+id.getRefId() + " found !");
				if(id != null){
					ds = fileset.getDirectoryScanner(getProject());
					File dir = ds.getBasedir();
					String[] filesInSet = ds.getIncludedFiles();
					for (String filename : filesInSet) {
						File file = new File(dir,filename);
						map.put(file, dir.getName());
						if(SERVER.equals(id.getRefId())){
							if(p.getProperty(file.getName()) != null){
								p.setProperty(file.getName(), SERVER_CLIENT);
								System.err.println("Adding "+file.getName() + " as COMMON_LIB");
							}else{
								p.setProperty(file.getName(), SERVER);
								System.err.println("Adding "+file.getName() + " as SERVER_LIB");
							}
						} else if(CLIENT.equals(id.getRefId())){
							if(p.getProperty(file.getName()) != null){
								p.setProperty(file.getName(), SERVER_CLIENT);
								System.err.println("Adding "+file.getName() + " as COMMON_LIB");
							}else{
								p.setProperty(file.getName(), CLIENT);
								System.err.println("Adding "+file.getName() + " as CLIENT_LIB");
							}
						} 
					}
				}
			}

			System.err.println("Saving lib.properties...");
			p.store(out,null);

		} catch (Exception e) {
			throw new BuildException(e);
		}finally{
			try {
				out.close();
			} catch (IOException e) {
				throw new BuildException(e);
			}
		}

	}


	public void addFileSet(FileSet fileset) {
		if (!filesets.contains(fileset)) {
			filesets.add(fileset);
		}
	}

	/**
	 * @param destFolder the destFolder to set
	 */
	public void setDestFolder(String destFolder) {
		this.destFolder = destFolder;
	}

}
