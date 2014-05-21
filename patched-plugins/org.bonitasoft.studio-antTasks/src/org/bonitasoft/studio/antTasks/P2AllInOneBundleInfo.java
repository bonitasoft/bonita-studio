package org.bonitasoft.studio.antTasks;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.equinox.internal.simpleconfigurator.manipulator.SimpleConfiguratorManipulatorUtils;
import org.eclipse.equinox.internal.simpleconfigurator.utils.BundleInfo;
import org.eclipse.equinox.internal.simpleconfigurator.utils.SimpleConfiguratorConstants;
import org.eclipse.equinox.internal.simpleconfigurator.utils.SimpleConfiguratorUtils;


public class P2AllInOneBundleInfo extends Task {

	private String installLocation;

	@Override
	public void execute() throws BuildException {
		final File outputFile = new File(installLocation,"configuration"+File.separator+SimpleConfiguratorConstants.CONFIGURATOR_FOLDER+File.separator+SimpleConfiguratorConstants.CONFIG_LIST);
		System.err.println("Output file ="+outputFile.getAbsolutePath());
		final File pluginsFolder = new File(installLocation,"plugins");
		System.err.println("Plugin folder ="+pluginsFolder.getAbsolutePath());

		try{
			final FileInputStream stream = new FileInputStream(outputFile);
			final File locationFile = new File(installLocation);
			final List<BundleInfo> bundleList = SimpleConfiguratorUtils.readConfiguration(stream, locationFile.toURI());
			final File[] platformSpecificPlugins = pluginsFolder.listFiles(new FileFilter() {
				public boolean accept(File file) {
					return isPlatformSpecific(file);
				}
			});
			if(platformSpecificPlugins != null){
				for(File pluginFile : platformSpecificPlugins){
					BundleInfo bInfo = createBundleInfo(pluginFile);
					if(bInfo != null){
						if(!bundleList.contains(bInfo)){
							bundleList.add(bInfo);
						}
					}else{
						System.err.println("Failed to parse bundle: "+pluginFile.getName());
					}
				}
			}
			SimpleConfiguratorManipulatorUtils.writeConfiguration(bundleList.toArray(new BundleInfo[bundleList.size()]), outputFile);
		}catch (Exception e) {
			throw new BuildException(e);
		}
	}

	private boolean isPlatformSpecific(File file) {
		return file.getName().contains("linux")
				|| file.getName().contains("macosx")
				|| file.getName().contains("win32")
				|| file.getName().contains("aix")
				|| file.getName().contains("solaris")
				|| file.getName().contains("hpux")
				|| file.getName().contains("qnx");
	}

	private BundleInfo createBundleInfo(File pluginFile) {
		final String name = pluginFile.getName();
		final String[] splitted = name.split("_");
		if(splitted.length >= 2){
			String symbolicName = splitted[0];
			String version ="";
			if(name.indexOf("x86_64") != -1 || name.indexOf("nl_") != -1){
				for(int i = 1 ; i< splitted.length-1 ;i++){
					symbolicName = symbolicName + "_" + splitted[i];
				}
			}
			if(name.indexOf(".jar") != -1){
				version = name.substring(symbolicName.length()+1, name.indexOf(".jar")) ;
			}else{
				version = name.substring(symbolicName.length()+1) ;
			}
			System.err.println("Create bundle entry with :"+symbolicName +",version ="+version);
			return new BundleInfo(symbolicName, version, pluginFile.getParentFile().getParentFile().toURI().relativize(pluginFile.toURI()), 4, false);
		}
		return null ;
	}

	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}
}
