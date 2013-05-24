/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.exporter.application.service;

import java.io.File;

import org.bonitasoft.engine.bpm.process.DesignProcessDefinition;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Aurelien Pupier
 */
public interface IWarFactory {


    public enum ExportMode {
        LIGHT, // Empty lib folder, all libs in a parent lib folder of the container
        EMBEDDED, // All libs in WAR, just deploy it
        JEE, // Only Client-libs in WAR
        STUDIO // Only console libs in WAR, engine available in classpath
    }


    public File generateProcessApplicationWar(AbstractProcess process, ExportMode processesExportMode, File destinationFolder, IProgressMonitor monitor, DesignProcessDefinition processDefinition) throws Exception;

    //	/**
    //	 * @param destFolder
    //	 * @param monitor
    //	 * @deprecated This method will be moved to a more generic export class
    //	 */
    //	public void addConfFiles(File destFolder, IProgressMonitor monitor);
    //
    //	/**
    //	 * @param destFolder
    //	 * @param monitor
    //	 * @deprecated This method will be moved to a more generic export class
    //	 */
    //	@Deprecated
    //	public void addReadmeFile(File destFolder, IProgressMonitor monitor);


    //	/**
    //	 * @param webFolder
    //	 * @param monitor
    //	 */
    //	public void addUserXPConsoleLibs(File webFolder, IProgressMonitor monitor);
    //
    //	/**
    //	 * @param destFolder
    //	 * @param monitor
    //	 */
    //	public void addEngineClientLibs(File destFolder, IProgressMonitor monitor);
    //
    //	/**
    //	 * @param destFolder
    //	 * @param monitor
    //	 */
    //	public void addEngineLibs(File destFolder, IProgressMonitor monitor);

    //	/**
    //	 * @param destFolder
    //	 * @param nullProgressMonitor
    //	 */
    //	public void addRuntime(File destFolder, RuntimeExportMode runtimeExportMode, IProgressMonitor nullProgressMonitor);


}
