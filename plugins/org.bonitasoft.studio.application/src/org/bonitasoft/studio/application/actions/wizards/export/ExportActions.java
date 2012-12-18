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
package org.bonitasoft.studio.application.actions.wizards.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveFactory;
import org.bonitasoft.engine.bpm.model.DesignProcessDefinition;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.exporter.ExporterService;
import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
import org.bonitasoft.studio.exporter.application.service.IWarFactory;
import org.bonitasoft.studio.exporter.application.service.IWarFactory.ExportMode;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

/**
 * @author Mickael Istria
 * This methods uses services such as WarFactory
 * It should not be extended.
 */
final public class ExportActions {

    private static final String WEBINF_LIB = "WEB-INF" + File.separatorChar + "lib"; //$NON-NLS-1$ //$NON-NLS-2$
    private static final String WEBAPP = "webapp/"; //$NON-NLS-1$
    private static final String BONITA_WAR = "bonita.war"; //$NON-NLS-1$
    private static final String WAR_EXTENSION = ".war"; //$NON-NLS-1$
    private static final String BAR_EXTENSION = ".bar"; //$NON-NLS-1$

    private final Map<AbstractProcess, DesignProcessDefinition> procDefsByBonitaProc = new HashMap<AbstractProcess, DesignProcessDefinition>();
    private final IWarFactory warFactory = (IWarFactory) ExporterService.getInstance().getExporterService(SERVICE_TYPE.WarFactory);

    /**
     * @param process
     * @return
     */
    public File doExportProcessProc(AbstractProcess process, final File destFile) throws Exception {
        DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(DiagramRepositoryStore.class) ;
        DiagramFileStore file = diagramStore.getChild(NamingUtils.toDiagramFilename(ModelHelper.getMainProcess(process))) ;
        InputStream contents = file.getResource().getContents();
        if (destFile.exists()) {
            Display.getDefault().syncExec(new Runnable() {
                @Override
                public void run() {
                    if (MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.overrideFileTitle, NLS.bind(
                            Messages.overrideFileDefinition, destFile.getName()))) {
                        destFile.delete();
                    }
                }
            });
        }
        if (destFile.exists()) {
            // file still exists (not overwritten)
            throw new FileNotFoundException(destFile.getName() + " already exists");//$NON-NLS-1$
        }
        FileOutputStream out = new FileOutputStream(destFile);
        byte[] bytes = new byte[1024];
        int nbBytes = 0;
        while ((nbBytes = contents.read(bytes)) > 0) {
            out.write(bytes, 0, nbBytes);
        }
        contents.close();
        out.close();
        return destFile;
    }

    /**
     * @param monitor
     * @param proc
     * @return
     */
    public File doExportProcessBar(IProgressMonitor monitor, final AbstractProcess proc,final String configuration,boolean withApplication, File containingFile) throws Exception {
        File processFolder = new File(containingFile, "processes"); //$NON-NLS-1$
        if(!processFolder.exists()){
            processFolder.mkdir();
        }

        String name = proc.getName()+"_"+ proc.getVersion() ;
        final File dest = new File(processFolder, name + BAR_EXTENSION);
        if (dest.exists()) {

            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    if (FileActionDialog.overwriteQuestion(dest.getName())) {
                        dest.delete();
                    }
                }
            }) ;

        }

        if (dest.exists()) {
            return null;
        }

        BusinessArchive bar = BarExporter.getInstance().createBusinessArchive(proc, configuration, Collections.EMPTY_SET);
        procDefsByBonitaProc.put(proc, bar.getProcessDefinition());
        dest.getParentFile().mkdirs();
        //        dest.createNewFile();
        BusinessArchiveFactory.writeBusinessArchiveToFile(bar,dest);
        if (dest.exists()) {
            return dest;
        }

        return null;
    }


    public File doExportUserXP(IProgressMonitor monitor, ExportMode userXPExportMode, File destDir) throws Exception {

        final String targetWarName = "bonita";
        final Bundle webappContainerBundle = ProjectUtil.getConsoleLibsBundle();
        final String webappPath = WEBAPP + BONITA_WAR;

        return exportGenericWarWithMode(webappContainerBundle, webappPath, userXPExportMode, destDir, targetWarName, monitor);

    }

    /**
     * @param webappContainerBundle
     * @param webappPath
     * @param srcWarName
     * @param userXPExportMode
     * @param destDir
     * @param targetWarName
     * @param monitor
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    public File exportGenericWarWithMode(final Bundle webappContainerBundle, final String webappPath, ExportMode userXPExportMode, File destDir, final String targetWarName,
            IProgressMonitor monitor) throws IOException, FileNotFoundException {
        int lastSlash = webappPath.lastIndexOf("/");
        String webappFolderInBundle = webappPath.substring(0, lastSlash);
        String webappName = webappPath.substring(lastSlash + 1, webappPath.length());
        File targetWebFolder = new File(destDir, "web"); //$NON-NLS-1$
        if(!targetWebFolder.exists()){
            targetWebFolder.mkdir();
        }

        File tmpDir = new File(targetWebFolder, "tmp");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }

        String warName = null;
        Enumeration<?> e = webappContainerBundle.findEntries(webappFolderInBundle, webappName, true);
        while (e.hasMoreElements()) {
            URL url = (URL) e.nextElement();
            warName = url.getFile().substring(url.getFile().lastIndexOf("/") + 1, url.getFile().length()); //$NON-NLS-1$
        }

        assert (warName != null);

        PlatformUtil.copyResource(tmpDir, webappContainerBundle, webappPath, monitor);
        File srcBonitaWar = new File(tmpDir, warName);
        File dest = new File(targetWebFolder, targetWarName);
        dest.delete();
        dest.mkdir();
        PlatformUtil.unzipZipFiles(srcBonitaWar, dest, monitor);

        if (monitor.isCanceled()) {
            return null;
        }

        if (userXPExportMode.equals(ExportMode.LIGHT)) {
            File fileToDelete = new File(dest.getAbsolutePath() + File.separatorChar + WEBINF_LIB);
            PlatformUtil.delete(fileToDelete, monitor);

            //   warFactory.addUserXPConsoleLibs(targetWebFolder, monitor);
        }

        File targetWar = new File(targetWebFolder, targetWarName + ".war");
        targetWar.delete();
        targetWar.createNewFile();
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targetWar));
        FileUtil.zipDir(dest.getAbsolutePath(), zos, dest.getAbsolutePath());
        zos.close();
        PlatformUtil.delete(dest, monitor);
        if (monitor.isCanceled()) {
            return null;
        }
        PlatformUtil.delete(tmpDir, monitor);

        return targetWar;
    }

    public File doExportProcessWar(IProgressMonitor monitor, AbstractProcess process, ExportMode processesExportMode, File destDir) throws Exception {
        File webFolder = new File(destDir, "web"); //$NON-NLS-1$
        if(!webFolder.exists()){
            webFolder.mkdir();
        }
        String processFileName = process.getName()+"_"+process.getVersion()+WAR_EXTENSION;
        final File war = new File(webFolder,processFileName);
        if(war.exists()){
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    if (FileActionDialog.overwriteQuestion(war.getName())) {
                        war.delete();
                    }

                }
            }) ;
        }

        if(war.exists()){
            return null ;
        }


        DesignProcessDefinition processDefinition = procDefsByBonitaProc.get(process);
        if(processDefinition == null){//the bar was not asked t be generated, so generate it
            processDefinition = BarExporter.getInstance().getProcessDefinitionBuilder().createDefinition(process);
        }
        File resultWar = warFactory.generateProcessApplicationWar(process, processesExportMode, new File(destDir + File.separator + "web") , monitor, processDefinition);
        if (monitor.isCanceled()) {
            return null;
        }

        return resultWar;

    }

    /**
     * @param nullProgressMonitor
     * @param exportMode
     * @param destFolder
     */
    public void doExportRestWar(IProgressMonitor monitor, ExportMode exportMode, File destFolder) throws Exception {
        exportGenericWarWithMode(ProjectUtil.getConsoleLibsBundle(),
                "rest/bonita-server-rest.war",
                exportMode,
                destFolder,
                "bonita-server-rest",
                monitor);
    }


}
