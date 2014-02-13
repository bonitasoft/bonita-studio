/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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

package org.bonitasoft.studio.exporter.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.zip.ZipOutputStream;

import org.bonitasoft.console.common.server.preferences.properties.SecurityProperties;
import org.bonitasoft.engine.bpm.process.DesignProcessDefinition;
import org.bonitasoft.forms.server.exception.InvalidFormDefinitionException;
import org.bonitasoft.studio.common.BonitaHomeUtil;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.exporter.application.service.IWarFactory;
import org.bonitasoft.studio.exporter.runtime.RuntimeExportMode;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier
 * 			add different export mode for Runtime
 */
public class WarFactory implements IWarFactory{

    private static final String RUNTIME = "runtime"; //$NON-NLS-1$
    private static final String LIB = "lib"; //$NON-NLS-1$
    private static final String FORMS_APPLICATION_WAR = "bonita-app.war"; //$NON-NLS-1$
    private static final String WEBAPP = "webapp/"; //$NON-NLS-1$
    protected static final String WEBINF_CLASSES_PATH = File.separatorChar+"WEB-INF"+File.separatorChar+"classes";  //$NON-NLS-1$//$NON-NLS-2$
    private static final String TMP_DIR = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();
    private static final String EXPORTER_BUNDLE_ID = "org.bonitasoft.studio.exporter"; //$NON-NLS-1$
    private static final String WEB_INF_LIB = "WEB-INF/lib/"; //$NON-NLS-1$
    //	private static final String BONITA_ENVIRONMENT_ENTRY = "WEB-INF/classes/bonita-environment.xml"; //$NON-NLS-1$
    private static final String WEBAPP_BONITA_APP_WAR = "/webapp/bonita-app.war"; //$NON-NLS-1$;
    private static final String WAR_EXTENSION = ".war"; //$NON-NLS-1$
    private static final long DEFAULT_TENANT_ID = 1;
    protected File createArchive(File newArchiveFile, File archiveTmpFile, String root, IProgressMonitor monitor) {
        try {
            //create a ZipOutputStream to zip the data to
            ZipOutputStream zos = new  ZipOutputStream(new FileOutputStream(newArchiveFile));
            FileUtil.zipDir(archiveTmpFile.getAbsolutePath(), zos, root);
            //close the stream
            zos.close();
        } catch(Exception e) {
            BonitaStudioLog.error(e);
            return null;
        }

        PlatformUtil.delete(archiveTmpFile, monitor);

        return newArchiveFile;
    }

    public File generateProcessApplicationWar(AbstractProcess process, ExportMode processesExportMode, File destinationFolder, IProgressMonitor monitor, DesignProcessDefinition processDefinition) throws InvalidFormDefinitionException {
        String destinationFolderPath = destinationFolder.getAbsolutePath();

        File bonitaAppWarFile = null;
        File dest = null;
        try{

            PlatformUtil.copyResource(new File(TMP_DIR), ProjectUtil.getConsoleLibsBundle(), WEBAPP_BONITA_APP_WAR, monitor);
            bonitaAppWarFile = new File(TMP_DIR+File.separatorChar+"bonita-app.war"); //$NON-NLS-1$

            String processFileName = process.getName() + "_" + process.getVersion() + WAR_EXTENSION;

            if(monitor.isCanceled()){
                return null;
            }

            dest = new File(TMP_DIR+File.separatorChar+processFileName.substring(0, processFileName.indexOf(WAR_EXTENSION)));
            if(dest.exists() && !dest.delete()){
                BonitaStudioLog.log("Can't delete the destination folder:"+dest.getAbsolutePath());
            }
            dest.mkdir();

            PlatformUtil.unzipZipFiles(bonitaAppWarFile, dest,monitor);

            File webInfLibFolder = new File(dest.getAbsolutePath() + File.separatorChar + WEB_INF_LIB);
            if (processesExportMode.equals(ExportMode.LIGHT)) {
                PlatformUtil.delete(webInfLibFolder, monitor);
                //                addProcessesLibs(destinationFolder, monitor);
                //                ResourcesExporter.exportJarsAndValidators(process, new File(destinationFolder,LIB), monitor);
            }
            //            else if (processesExportMode.equals(ExportMode.JEE)) {
            //                addEngineClientLibs(webInfLibFolder, monitor);
            //            } else if (processesExportMode.equals(ExportMode.EMBEDDED)) {
            //                addEngineLibs(webInfLibFolder, monitor);
            //            }
            else if(processesExportMode.equals(ExportMode.STUDIO)){
                for(File child :webInfLibFolder.listFiles()){
                    PlatformUtil.delete(child, monitor);
                }
            }
            addInWar(process, monitor, bonitaAppWarFile, dest);
            addInWEBINF(process, monitor, bonitaAppWarFile, dest);

            File processWar = new File(TMP_DIR+File.separatorChar+processFileName);
            if(processWar.exists() && !processWar.delete()){
                BonitaStudioLog.log("Can't remove the old war: "+ processWar.getAbsolutePath());
            }
            if(!processWar.createNewFile()){
                BonitaStudioLog.log("Can't create the war: "+processWar.getAbsolutePath());
            }
            ZipOutputStream zos = new  ZipOutputStream(new FileOutputStream(processWar));
            FileUtil.zipDir(dest.getAbsolutePath(), zos, TMP_DIR+File.separatorChar+processFileName.substring(0, processFileName.indexOf(WAR_EXTENSION)));
            zos.close();

            if(monitor.isCanceled()){
                return null;
            }
            PlatformUtil.move(processWar, destinationFolder, monitor);

            return new File(destinationFolderPath + File.separatorChar + processWar.getName());

        }catch (Exception e) {
            BonitaStudioLog.error(e);
            if(e instanceof InvalidFormDefinitionException){
                throw (InvalidFormDefinitionException)e;
            }
        }finally{
            if(dest != null) {
                PlatformUtil.delete(dest, monitor);
            }
            if(bonitaAppWarFile != null) {
                PlatformUtil.delete(bonitaAppWarFile, monitor);
            }
        }

        return null ;

    }

    /**
     * @param autoLoginId
     * @param dest
     * @param monitor
     */
    protected void addAutoLogin(String autoLoginId, File dest, IProgressMonitor monitor) {
        OutputStream os = null;
        try {
            File destPropertiesFile = new File(dest.getAbsolutePath() + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + SecurityProperties.SECURITY_DEFAULT_CONFIG_FILE_NAME);
            Properties properties = getDefaultServerConfigProperties();
            properties.setProperty(SecurityProperties.AUTO_LOGIN_PROPERTY, "true");
            if (autoLoginId != null && autoLoginId.trim().length() > 0) {
                properties.setProperty(SecurityProperties.AUTO_LOGIN_USERNAME_PROPERTY, autoLoginId);
            }
            os = new FileOutputStream(destPropertiesFile);
            properties.store(os, null);
        } catch (Exception ex) {
            BonitaStudioLog.error(ex);
        } finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    /**
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Properties getDefaultServerConfigProperties() throws FileNotFoundException, IOException {
        Properties properties = new Properties();
        File serverConfigPropertiesFile = BonitaHomeUtil.getDefaultTenantSecurityConfigFile(DEFAULT_TENANT_ID);
        InputStream is = new FileInputStream(serverConfigPropertiesFile);
        properties.load(is);
        is.close();
        return properties;
    }

    protected void addInWar(AbstractProcess process, IProgressMonitor monitor,
            File bonitaAppWarFile, File dest)
                    throws  Exception {


        //        if (process.isAutoLogin()) {
        //            addAutoLogin(process.getAutoLoginId(), dest, monitor);
        //        }
        //
        //        ResourcesExporter.exportResources(process, dest, monitor);
        //        if(monitor.isCanceled()){
        //            throw new OperationCanceledException();
        //        }
        //        monitor.worked(1);
        //        ResourcesExporter.exportJarsAndValidators(process, new File(dest,WEBINF_LIB_PATH), monitor);
        //        if(monitor.isCanceled()){
        //            throw new OperationCanceledException();
        //        }
        //        monitor.worked(1);
        //        if(monitor.isCanceled()){
        //            throw new OperationCanceledException();
        //        }
        //        TemplatesExporter.exportTemplates(process, dest, monitor);
        //        if(monitor.isCanceled()){
        //            throw new OperationCanceledException();
        //        }
        //        monitor.worked(1);
        //
        //        exportFormsXML(process, monitor, dest);
        //        if(monitor.isCanceled()){
        //            throw new OperationCanceledException();
        //        }
        //        monitor.worked(1);

        //addLib(new File(dest,WEBINF_LIB_PATH),"dom4j*.jar",monitor); //$NON-NLS-1$
        //removeLib(new File(dest,WEBINF_LIB_PATH),"icu4j"); //$NON-NLS-1$
    }

    protected void exportFormsXML(AbstractProcess process,
            IProgressMonitor monitor, File dest )
                    throws Exception {
        FormsXMLExporter.exportFormsXML(process, dest, false, Collections.<EObject>emptySet(), monitor);
    }

    protected void removeLib(File dest, String startingWith) {
        for(File f : dest.listFiles()){
            if(f.getName().startsWith(startingWith)){
                if(f.exists() && !f.delete()){
                    BonitaStudioLog.log("Can't remove the lib: "+ f.getAbsolutePath());
                }
            }
        }
    }

    //    public void addEngineLibs(File destFolder, IProgressMonitor monitor) {
    //
    //        File folder = null;
    //        try {
    //            folder = new File(FileLocator.toFileURL(BonitaHomeUtil.getEngineLibsBundle().getEntry(LIB)).getFile());
    //        } catch (IOException e) {
    //            BonitaStudioLog.log(e);
    //        }
    //        PlatformUtil.copyResource(destFolder, folder,new FilenameFilter() {
    //
    //            public boolean accept(File dir, String name) {
    //                return !name.startsWith("bonita-server-commands");
    //            }
    //        },monitor );
    //
    //    }

    //    /**
    //     * Add the runtime into destFolder,
    //     * it used the runtime folder in engine libs and add the correct lib that it searched from lib folder.
    //     * @param destFolder
    //     * @param monitor
    //     */
    //    public void addEngine(File destFolder,IProgressMonitor monitor) {
    //        File folder = null;
    //        FileInputStream in =  null;
    //        try{
    //            Bundle engineLibsBundle = BonitaHomeUtil.getEngineLibsBundle();
    //            URL runtimeFolder = engineLibsBundle.getResource(RUNTIME);
    //            folder = new File(FileLocator.toFileURL(runtimeFolder).getFile());
    //
    //
    //            PlatformUtil.copyResource(destFolder, folder, monitor);
    //            File confFolder = new File(destFolder,"conf") ;
    //            PlatformUtil.delete(confFolder, monitor) ;
    //
    //            URL lipPropertyURL = engineLibsBundle.getEntry(LIB_PROPERTY_FILE);
    //            File libPropertyFile = new File(FileLocator.toFileURL(lipPropertyURL).getFile());
    //            in =  new FileInputStream(libPropertyFile);
    //            Properties p = new Properties();
    //            p.load(in);
    //
    //            File libFolder = new File(FileLocator.toFileURL(engineLibsBundle.getEntry(LIB)).getFile());
    //            File clientLibDest = new File(destFolder,"bonita_execution_engine"+File.separatorChar+"bonita_client"+File.separatorChar+"libs");
    //            File serverLibDest = new File(destFolder,"bonita_execution_engine"+File.separatorChar+"engine"+File.separatorChar+"libs");
    //
    //
    //            Set<Entry<Object, Object>> libEntries = p.entrySet();
    //            for(Entry<Object, Object> libEntry : libEntries){
    //                Object libName = libEntry.getKey();
    //                Object libType = libEntry.getValue();
    //                if(libType.equals(SERVER_CLIENT) ){
    //                    File libFile = new File(libFolder,libName.toString());
    //                    if(libFile.exists()){
    //                        PlatformUtil.copyResource(serverLibDest, libFile, monitor);
    //                        PlatformUtil.copyResource(clientLibDest, libFile, monitor);
    //                    }else{
    //                        throw new RuntimeException(libFile.getName()+" doesn't exists"); //$NON-NLS-1$
    //                    }
    //                } else if(libType.equals(SERVER) ){
    //                    File libFile = new File(libFolder,libName.toString());
    //                    if(libFile.exists()){
    //                        PlatformUtil.copyResource(serverLibDest, libFile, monitor);
    //                    }else{
    //                        throw new RuntimeException(libFile.getName()+" doesn't exists"); //$NON-NLS-1$
    //                    }
    //                } else if(libType.equals(CLIENT)){
    //                    File libFile = new File(libFolder,libName.toString());
    //                    if(libFile.exists()){
    //                        PlatformUtil.copyResource(clientLibDest, libFile, monitor);
    //                    }else{
    //                        throw new RuntimeException(libFile.getName()+" doesn't exists"); //$NON-NLS-1$
    //                    }
    //                }
    //
    //
    //            }
    //        }catch (Exception e) {
    //            BonitaStudioLog.log(e);
    //        }finally{
    //            try {
    //                in.close();
    //            } catch (IOException e) {
    //                BonitaStudioLog.log(e);
    //            }
    //        }
    //
    //    }

    //    protected void addLib(File destFileFoler,String filePattern, IProgressMonitor monitor) {
    //        PlatformUtil.copyResource(destFileFoler,BonitaHomeUtil.getEngineLibsBundle(), LIB,filePattern,monitor);
    //    }
    //
    //    public void addUserXPConsoleLibs(File destFoler,IProgressMonitor monitor) {
    //
    //        File libFolder = new File(destFoler,LIB);
    //        if(!libFolder.exists()){
    //            if(! libFolder.mkdir()){
    //                BonitaStudioLog.log("Can't create the lib folder: "+ libFolder.getAbsolutePath());
    //            }
    //        }
    //
    //        String warName = null ;
    //        Enumeration<?> e =	ProjectUtil.getConsoleLibsBundle().findEntries(WEBAPP, CONSOLE_WAR, true);
    //        while (e.hasMoreElements()) {
    //            URL url = (URL) e.nextElement();
    //            warName = url.getFile().substring(url.getFile().lastIndexOf("/")+1, url.getFile().length()); //$NON-NLS-1$
    //        }
    //
    //        assert warName != null ;
    //
    //        PlatformUtil.copyResource(new File(TMP_DIR),ProjectUtil.getConsoleLibsBundle(), WEBAPP+warName, monitor) ;
    //        File bonitaWarFile = new File(TMP_DIR+ File.separatorChar +warName);
    //        FileUtil.getFilesFromZip(bonitaWarFile, WEB_INF_LIB, libFolder);
    //        PlatformUtil.delete(bonitaWarFile, monitor);
    //
    //        addEngineLibs(libFolder, monitor);
    //
    //    }


    /**
     * Add the conf files to archiveTmpFile in conf directory. They are :
     * - bonita-environment.xml
     * - jaas-standard.cfg
     * - logging.properties
     * @param destFolder
     * @param monitor
     */
    public void addConfFiles(File destFolder, IProgressMonitor monitor) {
        addClientConfFiles(destFolder) ;
        addServerConfFiles(destFolder) ;
        //		try{
        //			Bundle engineLibsBundle = BonitaEnvironmentInit.getEngineLibsBundle();
        //			URL runtimeFolder = engineLibsBundle.getResource(RUNTIME);
        //			File folder  = new File(FileLocator.toFileURL(runtimeFolder).getFile());
        //			File confFolder = new File(folder,"conf") ;
        //			File destConf = new File(destFolder,"conf") ;
        //			PlatformUtil.copyResource(destConf, confFolder, monitor) ;
        //			if (!ConsoleManagement.getConsoleLibsBundle().getSymbolicName().equals("org.bonitasoft.studio.console.libs")) {
        //				File license = new File(destConf,"bonita"+File.separatorChar+"server"+File.separatorChar+"licenses") ;
        //				license.mkdir();
        //			}
        //
        //		}catch (Exception e) {
        //			BonitaStudioLog.log(e) ;
        //		}
    }

    /**
     * @param destFolder
     */
    private void addClientConfFiles(File destFolder) {
        //	ConsoleManagement.copyClientConfFiles(new File(destFolder, "conf" + File.separator + "bonita"));
    }

    /**
     * @param destFoler
     */
    protected void addServerConfFiles(File destFoler) {
        File confFolder = new File(destFoler, "conf"); //$NON-NLS-1$
        File destBonitaDir = new File(confFolder, "bonita");
        File destExternalDir = new File(confFolder, "external");
        try {
            File srcBonitaHome = BonitaHomeUtil.getReferenceBonitaHome();
            FileUtil.copyDir(srcBonitaHome, destBonitaDir);
            FileUtil.deleteDir(new File(destBonitaDir, "external"));
            FileUtil.copyDir(BonitaHomeUtil.getReferenceExternalFolder(), destExternalDir);
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    /**
     * Add the INSTALL.txt to the archiveTmpFile at the root.
     * @param archiveTmpFile
     * @param monitor
     */
    public void addReadmeFile(File archiveTmpFile, IProgressMonitor monitor) {
        PlatformUtil.copyResource(archiveTmpFile, Platform.getBundle(EXPORTER_BUNDLE_ID),"properties/README.txt", monitor) ; //$NON-NLS-1$
    }

    /**
     * Add each file of entries into wars directory of archiveTmpFile.
     * @param entries
     * @param archiveTmpFile
     * @param monitor
     */
    protected void addWars(File[] entries, File archiveTmpFile,
            IProgressMonitor monitor) {
        for(File waFile : entries){
            PlatformUtil.move(waFile,archiveTmpFile,monitor);
        }
    }

    /**
     * Add each file of entries into wars directory of archiveTmpFile.
     * @param entries
     * @param archiveTmpFile
     * @param monitor
     */
    protected void addBars(File[] entries, File archiveTmpFile,
            IProgressMonitor monitor) {
        for(File barFile : entries){
            PlatformUtil.move(barFile,archiveTmpFile,monitor);
        }
    }

    //    /**
    //     * Add runtime taking care of runtime export mode:
    //     * - FULL: export all the runtime folder
    //     * - EJB2: provides bonita.ear EJB2 compatible and conf folder
    //     * - EJB3: provides bonita.ear EJB3 compatible and conf folder
    //     * - REST: provides bonita-server-rest.war and conf folder
    //     * @see org.bonitasoft.studio.exporter.application.service.IWarFactory#addRuntime(java.io.File, org.bonitasoft.studio.exporter.runtime.RuntimeExportMode, org.eclipse.core.runtime.IProgressMonitor)
    //     */
    //    public void addRuntime(File archiveTmpFile, RuntimeExportMode runtimeExportMode, IProgressMonitor monitor) {
    //        if(runtimeExportMode != null){//add it only if the user want
    //            File runtimeDir = cleanRuntimeDirectoryTarget(archiveTmpFile);
    //
    //            if(RuntimeExportMode.FULL.equals(runtimeExportMode)){
    //                /*In full mode, export all the runtime folder,
    //                 * the user will be available to generate all the runtime type he wants and also initdb.
    //                 * NB: this is the legacy mode of runtime export*/
    //                addEngine(runtimeDir,monitor);
    //            } else {
    //
    //                /*export Runtime in a temp folder*/
    //                File tempRuntimeFolderForgeneration = new File(TMP_DIR+File.separatorChar+"bonitaGeneration");
    //                addEngine(tempRuntimeFolderForgeneration, monitor);
    //
    //                /*Get the generated file in the temp folder and copy it to the right place*/
    //                File generatedRuntimeFil = getGeneratedRuntimeFile(tempRuntimeFolderForgeneration, runtimeExportMode);
    //                PlatformUtil.copyResource(runtimeDir, generatedRuntimeFil, monitor);
    //
    //                /*Clean temp folder*/
    //                PlatformUtil.delete(tempRuntimeFolderForgeneration, monitor);
    //            }
    //        }
    //    }

    protected File cleanRuntimeDirectoryTarget(File archiveTmpFile) {
        File runtimeDir = new File(archiveTmpFile.getAbsolutePath()+File.separatorChar+RUNTIME);
        if(runtimeDir.exists()){
            if(!runtimeDir.delete()){
                BonitaStudioLog.log("Can't delete the runtime directory: "+runtimeDir.getAbsolutePath());
            }
        }
        if(!runtimeDir.mkdir()){
            BonitaStudioLog.log("Can't create the runtime directory: "+runtimeDir.getAbsolutePath());
        }
        return runtimeDir;
    }

    /**
     * Search the generated artefact and return it.
     * - EJB2 -> runtime/ear/ejb2/bonita.ear
     * - EJB3 -> runtime/ear/ejb3/bonita.ear
     * - REST -> runtime/war/bonita-server-rest.war
     * @param runtimeDir
     * @param runtimeExportMode
     * @return
     */
    protected File getGeneratedRuntimeFile(File runtimeDir, RuntimeExportMode runtimeExportMode) {
        String runtimeDirAbsolutepath = runtimeDir.getAbsolutePath()+File.separatorChar + "bonita_execution_engine" + File.separatorChar;
        if(RuntimeExportMode.EJB2.equals(runtimeExportMode)){
            return new File(runtimeDirAbsolutepath + "interfaces"+ File.separatorChar + "EJB"+ File.separatorChar + "EJB2" + File.separatorChar + "bonita.ear");
        } else if(RuntimeExportMode.EJB3.equals(runtimeExportMode)){
            return new File(runtimeDirAbsolutepath + "interfaces"+ File.separatorChar + "EJB"+ File.separatorChar + "EJB3" + File.separatorChar + "bonita.ear");
        } else if(RuntimeExportMode.REST.equals(runtimeExportMode)){
            return new File(runtimeDirAbsolutepath + "interfaces"+ File.separatorChar + "REST" +  File.separatorChar + "with_engine" + File.separatorChar  +"bonita-server-rest.war");
        }
        return null;
    }


    //    protected List<String> getEngineServerLibName() {
    //        List<String> fileNames = new ArrayList<String>();
    //        FileInputStream in = null;
    //        try{
    //            URL lipPropertyURL = BonitaHomeUtil.getEngineLibsBundle().getEntry(LIB_PROPERTY_FILE);
    //            File libPropertyFile = new File(FileLocator.toFileURL(lipPropertyURL).getFile());
    //            in =  new FileInputStream(libPropertyFile);
    //            Properties p = new Properties();
    //            p.load(in);
    //
    //            Set<Entry<Object, Object>> libEntries = p.entrySet();
    //            for(Entry<Object, Object> libEntry : libEntries){
    //                String libName = libEntry.getKey().toString();
    //                Object libType = libEntry.getValue();
    //                if(libType.equals(SERVER)){
    //                    fileNames.add(libName);
    //                }
    //            }
    //        }catch (Exception e) {
    //            BonitaStudioLog.log(e);
    //        } finally {
    //            if(in != null){
    //                try {
    //                    in.close();
    //                } catch (IOException e) {
    //                    BonitaStudioLog.log(e);
    //                }
    //            }
    //        }
    //        return fileNames;
    //    }

    protected void addProcessesLibs(File destFoler,IProgressMonitor monitor) {

        File libFolder = new File(destFoler,LIB);
        if(!libFolder.exists()){
            if(! libFolder.mkdir()){
                BonitaStudioLog.log("Can't create directory" + libFolder.getAbsolutePath());
                return;
            }
        }

        String warName = null ;
        Enumeration<?> e =	ProjectUtil.getConsoleLibsBundle().findEntries(WEBAPP, FORMS_APPLICATION_WAR, true);
        while (e.hasMoreElements()) {
            URL url = (URL) e.nextElement();
            warName = url.getFile().substring(url.getFile().lastIndexOf("/")+1, url.getFile().length()); //$NON-NLS-1$
        }

        PlatformUtil.copyResource(new File(TMP_DIR), ProjectUtil.getConsoleLibsBundle(), "webapp/"+warName, monitor) ; //$NON-NLS-1$
        File bonitaWarFile = new File(TMP_DIR+ File.separatorChar +warName);
        FileUtil.getFilesFromZip(bonitaWarFile, WEB_INF_LIB, libFolder);
        PlatformUtil.delete(bonitaWarFile, monitor);

        //  addEngineLibs(libFolder, monitor);
    }


    //    public void addEngineClientLibs(File destFolder, IProgressMonitor monitor) {
    //
    //        File folder = null;
    //        try {
    //            folder = new File(FileLocator.toFileURL(BonitaHomeUtil.getEngineLibsBundle().getEntry(LIB)).getFile());
    //        } catch (IOException e) {
    //            BonitaStudioLog.log(e);
    //            return;
    //        }
    //        File[] toCopy = folder.listFiles(new FilenameFilter() {
    //            public boolean accept(File dir, String name) {
    //                //TODO Change mechanisms. Actual mechanisms use hardcoded library name belonging to bonita-server AND default Form Application
    //                //We need to retrieve the form application lib at build time to rebuild the war correctly
    //                return !getEngineServerLibName().contains(name) ||
    //                        name.startsWith("commons-httpclient") ||
    //                        name.startsWith("ehcache-core") ||
    //                        name.startsWith("commons-io") ;
    //            }
    //        });
    //
    //        for(File f: toCopy){
    //            PlatformUtil.copyResource(destFolder, f, monitor);
    //        }
    //
    //    }

    /**
     * @param process
     * @param monitor
     * @param bonitaAppWarFile
     * @param dest
     * @throws InvalidFormDefinitionException
     */
    protected void addInWEBINF(AbstractProcess process, IProgressMonitor monitor, File bonitaAppWarFile, File dest) throws InvalidFormDefinitionException {
    }

}

