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
package org.bonitasoft.studio.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.bonitasoft.studio.common.editingdomain.BonitaOperationHistory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * This class controls all aspects of the application's execution
 */
public class BonitaStudioApplication implements IApplication {

	private Display display;
	public static final String PREFERENCES_FILE = ".wsPreferences";
	public static final String WS_ROOT   = "wsRootDir";
	//    private static final String PATH_TO_SNAP_FILE = ".metadata"+File.separatorChar+".plugins"+File.separatorChar+"org.eclipse.core.resources"+File.separatorChar+".snap";

	public static long START_TIME  = 0;

	public BonitaStudioApplication() {

	}

	public BonitaStudioApplication(Display display) {
		this.display = display;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	@Override
	public Object start(IApplicationContext context) {
		//avoid the execution of AutoBuild job during startup
		Job.getJobManager().addJobChangeListener(new IJobChangeListener() {

			@Override
			public void sleeping(IJobChangeEvent event) {}

			@Override
			public void scheduled(IJobChangeEvent event) {
				if(event.getJob().belongsTo(ResourcesPlugin.FAMILY_AUTO_BUILD)){
					if(!PlatformUI.isWorkbenchRunning()){
						event.getJob().cancel();
					}
				}
			}

			@Override
			public void running(IJobChangeEvent event) {	}

			@Override
			public void done(IJobChangeEvent event) {	}

			@Override
			public void awake(IJobChangeEvent event) {}

			@Override
			public void aboutToRun(IJobChangeEvent event) {}
		});
		START_TIME = System.currentTimeMillis() ;

		if (display == null) {
			display = PlatformUI.createDisplay();
		}

		preStartupStudio();

		OperationHistoryFactory.setOperationHistory(new BonitaOperationHistory());//set our custom operation factory
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new BonitaStudioWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	}


	public static void preStartupStudio() {

		Location instanceLoc = Platform.getInstanceLocation();
		//if workspace is set via -Data, can't reset it
		if(!instanceLoc.isSet()){
			String path2 = Platform.getInstallLocation().getURL().getFile()+ File.separator + PREFERENCES_FILE;
			String lastUsedWs = null;//preferences.get(WS_ROOT, null);
			File propertiesFile = new File(path2);
			if(propertiesFile.exists()){
				Properties properties = new Properties();
				try {
					FileInputStream fis = new FileInputStream(propertiesFile) ;
					properties.load(fis);
					fis.close() ;
					lastUsedWs = properties.getProperty(WS_ROOT);
				} catch (FileNotFoundException e) {
					BonitaStudioLog.error(e);
				} catch (IOException e) {
					BonitaStudioLog.error(e);
				}
			}
			if(lastUsedWs != null && lastUsedWs.length()>1) {
				// set the last used location and continue
				try {
					instanceLoc.set(new URL("file", null, lastUsedWs), true);
				} catch (Exception e) {
					BonitaStudioLog.error(e);
				}
			}
			//no pref found use default ws location
			if(!instanceLoc.isSet()){
				String path = Platform.getInstallLocation().getURL().getPath() + "workspace";
				try {
					instanceLoc.set(new URL("file", null, path), true);
				} catch (Exception e) {
					BonitaStudioLog.error(e);
				}
			}


			// ResourcesPlugin.getPlugin().getPluginPreferences().setValue(ResourcesPlugin.PREF_AUTO_BUILDING, false);
		}

	}



	public Display getDisplay() {
		return display;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	@Override
	public void stop() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null) {
			return;
		}
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				if (!display.isDisposed()) {
					workbench.close();
				}
			}
		});
	}
}
