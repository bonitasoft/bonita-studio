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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author Romain Bioteau
 *
 */
public class GenerateIntroLocalizedContent extends Task {


	public static int bufferSize = 2*8192;
	private String contentFolder;
	private String contentXMLPath;
	private String indexTemplatePath;
	private String fragmentFolderPath;
	private Properties indexProperties ;


	@Override
	public void execute() throws BuildException {
		try {
			File contentFolderFile = new File(contentFolder);
			if(!contentFolderFile.exists()){
				throw new BuildException("Content folder doesn't exists"); //$NON-NLS-1$
			}
			File indexTemplateFile = new File(indexTemplatePath);
			if(!indexTemplateFile.exists()){
				throw new BuildException("index template file doesn't exists"); //$NON-NLS-1$
			}
			File xmlContentTemplateFile = new File(contentXMLPath);
			if(!xmlContentTemplateFile.exists()){
				throw new BuildException("xmlContent template file doesn't exists"); //$NON-NLS-1$
			}
			File fragmentFolderFile  = new File(fragmentFolderPath);
			if(!fragmentFolderFile.exists()){
				throw new BuildException("Fragment folder doesn't exists"); //$NON-NLS-1$
			}
			List<File> fragmentFiles = getFragments(fragmentFolderFile) ;
		
			if(fragmentFiles.size() == 0){
				System.out.println("No Fragment has been found"); //$NON-NLS-1$
			}
			
			for(File fragment : fragmentFiles){
				File messageProperties = getMessagesPropertiesFile(fragment);
				
				if(messageProperties == null){
					throw new BuildException("Messages.properties not found for fragment "+fragment.getName()); //$NON-NLS-1$
				}
				
				indexProperties = new Properties();
				indexProperties.load(new FileInputStream(messageProperties));

				int start =  messageProperties.getName().lastIndexOf('_') ;
				int end =  messageProperties.getName().lastIndexOf('.') ;
				String locale = messageProperties.getName().substring(start+1,end);
				System.out.println("Found Locale : "+locale); //$NON-NLS-1$
				createLocalizedIndexProperties(indexTemplateFile,contentFolderFile,indexProperties,locale);
				createLocalizedContentXmlFile(xmlContentTemplateFile,contentFolderFile,locale);
			}

		} catch (Exception ex) {
			throw new BuildException(ex);
		}
	}



	private void createLocalizedContentXmlFile(File xmlContentTemplateFile,File contentFolderFile,String locale) throws IOException {
		FileInputStream fis = new FileInputStream(xmlContentTemplateFile);
		String content = convertStreamToString(fis);
		File generatedFile = new File(contentFolderFile,"introContent_"+locale+".xml") ; //$NON-NLS-1$ //$NON-NLS-2$
		FileWriter fw = new FileWriter(generatedFile.getAbsolutePath());
		fw.write(content.replace("@locale@", locale)); //$NON-NLS-1$
		fw.flush();
		fw.close();
		if(generatedFile.exists()){
			System.out.println(generatedFile.getName()+" successfuly generated"); //$NON-NLS-1$
		}else{
			System.out.println(generatedFile.getName()+" generation failed"); //$NON-NLS-1$
		}
	}



	private File getMessagesPropertiesFile(File fragment) {
		for(File f : fragment.listFiles()){
			if(f.getName().contains("messages_")){ //$NON-NLS-1$
				return f ;
			}
		}
		return null;
	}

	private void createLocalizedIndexProperties(File indexTemplateFile,File contentFolderFile,Properties indexProperties,String locale) throws IOException {
		FileInputStream fis = new FileInputStream(indexTemplateFile);
		String content = convertStreamToString(fis);
		
		Iterator<Entry<Object, Object>> it = indexProperties.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Object, Object> entry = (Entry<Object, Object>) it.next();
			content = content.replace("@"+entry.getKey().toString()+"@",entry.getValue().toString()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		File generatedFile = new File(contentFolderFile,"index_"+locale+".html") ; //$NON-NLS-1$ //$NON-NLS-2$
		FileWriter fw = new FileWriter(generatedFile.getAbsolutePath());
		fw.write(content);
		fw.flush();
		fw.close();
		if(generatedFile.exists()){
			System.out.println(generatedFile.getName()+" successfuly generated"); //$NON-NLS-1$
		}else{
			System.out.println(generatedFile.getName()+" generation failed"); //$NON-NLS-1$
		}
	}

	public String convertStreamToString(InputStream is) throws IOException { 
		/* 
		 * To convert the InputStream to String we use the BufferedReader.readLine() 
		 * method. We iterate until the BufferedReader return null which means 
		 * there's no more data to read. Each line will appended to a StringBuilder 
		 * and returned as String. 
		 */
		if (is != null) { 
			StringBuilder sb = new StringBuilder(); 
			String line; 

			try { 
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));  //$NON-NLS-1$
				while ((line = reader.readLine()) != null) { 
					sb.append(line).append("\n");  //$NON-NLS-1$
				} 
			} finally { 
				is.close(); 
			} 
			return sb.toString(); 
		} else {         
			return "";  //$NON-NLS-1$

		} 
	} 

	private List<File> getFragments(File fragmentFolderFile) {
		List<File> introFragment = new ArrayList<File>() ;
		for(File f : fragmentFolderFile.listFiles()){
			if(f.getName().contains("org.bonitasoft.studio.intro.nl")){ //$NON-NLS-1$
				introFragment.add(f);
			}
		}
		return introFragment;
	}


	
	public void setIndexTemplateFilePath(String pathToIndexTemplate) {
		this.indexTemplatePath = pathToIndexTemplate;
	}

	public void setContentFolder(String contentFolder) {
		this.contentFolder = contentFolder;
	}

	public void setContentXMLTemplatePath(String pathToContentXMLTemplate) {
		this.contentXMLPath = pathToContentXMLTemplate;
	}

	public void setFragementFolder(String fragmentFolderPath){
		this.fragmentFolderPath = fragmentFolderPath;
	}
}
