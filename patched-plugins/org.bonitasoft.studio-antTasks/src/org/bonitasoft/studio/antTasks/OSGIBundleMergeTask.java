package org.bonitasoft.studio.antTasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;


public class OSGIBundleMergeTask extends Task {

	private String conf;

	@Override
	public void execute() throws BuildException {

		File confFile = new File(conf+"/config-tmp.ini");
		File newConfFile = new File(conf+"/configuration/config.ini");

		if(confFile.exists() && newConfFile.exists()){
			Properties properties = new Properties();
			Properties propertiesTmp = new Properties();

			try {
				properties.load(new FileInputStream(confFile));
				propertiesTmp.load(new FileInputStream(newConfFile));

			} catch (IOException e) {
				e.printStackTrace();
			}

			String bundles = properties.getProperty("osgi.bundles");
			propertiesTmp.setProperty("osgi.bundles", bundles);

			try {
				propertiesTmp.store(new FileOutputStream(newConfFile),null);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public void setConf(String conf) {
		this.conf = conf;
	}
}
