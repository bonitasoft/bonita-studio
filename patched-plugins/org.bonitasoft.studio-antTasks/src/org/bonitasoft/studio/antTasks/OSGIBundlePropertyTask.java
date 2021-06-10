/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author Romain Bioteau
 * This Task is used to merge the osgi-bundle of config.ini of the different targeted platforms
 */
public class OSGIBundlePropertyTask extends Task {

	private String conf;
	private String tmp;

	@Override
	public void execute() throws BuildException {

		File confFile = new File(conf+"/configuration/config.ini");
		File newConfFile = new File(tmp+"/config-tmp.ini");
		
		if(!newConfFile.exists()){
			try {
				newConfFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		if(confFile.exists() && newConfFile.exists()){
			
			Properties properties = new Properties();
			Properties targetProperties = new Properties();

			try {
				
				FileInputStream in = new FileInputStream(confFile);
				FileInputStream in2 =new FileInputStream(newConfFile);
				properties.load(in);
				targetProperties.load(in2);
				
				String bundles = properties.getProperty("osgi.bundles");
			

				Set<String> bundlesList = new HashSet<String>();
				int begin = 0;
				for(int i =0 ;i < bundles.length() ; i++){
					if(bundles.charAt(i) == ','){
						String bundle = bundles.substring(begin, i);
						System.out.println("adding bundle : "+bundle);
						bundlesList.add(bundle);
						begin = i+1;
					}
				}
				String bundle = bundles.substring(begin, bundles.length());
				System.out.println("adding bundle : "+bundle);
				bundlesList.add(bundle);
				
				int length = bundlesList.toString().length();
				String result = bundlesList.toString().substring(1,length-1);
				System.out.println("merged bundle : "+result);
				
				String r = targetProperties.getProperty("osgi.bundles");
				if(r == null){
					targetProperties.setProperty("osgi.bundles", result);
				}else{
					r = r.concat(","+result); 
					targetProperties.setProperty("osgi.bundles", r);
				}
				
				
				in.close();
				in2.close();
				
				
				FileOutputStream out = new FileOutputStream(newConfFile);
				targetProperties.store(out,null);
				out.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		System.out.println(conf);


	}

	public void setConf(String conf) {
		this.conf = conf;
	}
	
	public void setTmp(String tmp) {
		this.tmp = tmp;
	}


}
