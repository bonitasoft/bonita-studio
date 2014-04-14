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
package org.bonitasoft.studio.exporter.application;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

/**
 * 
 * Exports resources from a process
 * 
 * @author Baptiste Mesta
 * 
 */
public class ResourcesExporter {

    private static final String TMP_DIR = ProjectUtil.getBonitaStudioWorkFolder().getAbsolutePath();

    /**
     * 
     * export and add process resources to the war file
     * 
     * @param process
     *            from which resources are exported
     * @param warFile
     *            were to put resources
     * @param monitor
     * @return true if all files are exported
     */
    public static boolean exportResources(AbstractProcess process, File destFolder, IProgressMonitor monitor) {

        HashMap<File, String> toAdd = new HashMap<File, String>();
        boolean succes = true;
        File applicationDir = new File(destFolder.getAbsolutePath());
        applicationDir.mkdir();

        succes = addWidgetImages(process, toAdd);

        copyResources(monitor, toAdd, applicationDir);
        toAdd.clear();

        addLoginPage(process, toAdd);

        succes = addResources(process, toAdd);

        copyResources(monitor, toAdd, applicationDir);

        return succes;
    }

    /**
     * @param monitor
     * @param toAdd
     * @param applicationDir
     */
    private static void copyResources(IProgressMonitor monitor, HashMap<File, String> toAdd, File applicationDir) {
        Iterator<Entry<File, String>> it = toAdd.entrySet().iterator();
        while (it.hasNext()) {
            Entry<File, String> entry = it.next();
            String absolutePath = entry.getKey().getAbsolutePath();
            String value = entry.getValue();
            if(value.endsWith(File.separatorChar+"")){
                value = value.substring(0, value.length()-1);
            }
            String pathFromRoot = absolutePath.substring(
                    absolutePath.indexOf(value) + value.length() + 1,
                    absolutePath.length());
            File destTree = new File(applicationDir.getAbsolutePath() + File.separatorChar + pathFromRoot);
            File parent = new File(destTree.getParent());
            parent.mkdirs();
            File file = entry.getKey();
            if(file.exists()) {
                PlatformUtil.copyResource(new File(destTree.getParent()), file, monitor);
            }

        }
    }

    /**
     * get login page from process
     * 
     * @param process
     * @param toAdd
     */
    private static void addLoginPage(AbstractProcess process, HashMap<File, String> toAdd) {

        File loginPage = null;
        if (process.getLogInPage() != null) {
            loginPage = WebTemplatesUtil.getFile(process.getLogInPage().getPath());
        }

        if (loginPage != null && loginPage.exists()) {
            File tempFile = new File(TMP_DIR + File.separatorChar + "application" + File.separatorChar +"login.jsp"); //$NON-NLS-1$
            tempFile.getParentFile().mkdirs();
            tempFile.delete();
            try {
                FileUtil.copyFile(loginPage, tempFile);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
            // replace login.jsp by this one
            toAdd.put(tempFile, TMP_DIR);
        }

    }

    public static boolean addWidgetImages(Element el, HashMap<File, String> toAdd) {
        if (el instanceof PageFlow) {
            for (Form form : ((PageFlow) el).getForm()) {
                for (Widget w : form.getWidgets()) {
                    if (w instanceof ImageWidget && ((ImageWidget) w).getImgPath() != null) {
                        File file = new File(((ImageWidget) w).getImgPath().getContent());
                        if (file.exists()) {
                            toAdd.put(file, file.getParent());
                        }
                    }
                }
            }
        }
        if (el instanceof Container) {
            for (Element child : ((Container) el).getElements()) {
                if (!(child instanceof AbstractProcess)) {
                    addWidgetImages(child, toAdd);
                }
            }
        }
        return false;
    }

    /**
     * 
     * add resources to the list
     * 
     * @param process
     * @param toAdd
     * @return true if all files exists and are added
     */
    public static boolean addResources(AbstractProcess process, HashMap<File, String> toAdd) {

        EList<ResourceFolder> resFolders = process.getResourceFolders();
        File temp = null;
        File resFolder = null;
        boolean succes = true;

        for (ResourceFolder resourceFolder : resFolders) {
            resFolder = WebTemplatesUtil.getFile(resourceFolder.getPath());

            // TODO log file missing
            if(resFolder != null){
                addFileRecusive(resFolder, resFolder.getParent(), toAdd);
            }
        }

        for (ResourceFile resFile : process.getResourceFiles()) {
            temp = WebTemplatesUtil.getFile(resFile.getPath());
            if (temp != null && temp.isFile()) {
                toAdd.put(temp, temp.getParent());
            }
        }
        return succes;
    }

    /**
     * Add all files, except .svn, recursively in the map of file to export.
     */
    private static void addFileRecusive(File file, String path, HashMap<File, String> toAdd) {
        if (file.isDirectory()) {
            File[] listFilesIgnoringSvn = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return ! (dir.getName().endsWith(".svn") || name.equals(".svn"));
                }
            });
            for (File child : listFilesIgnoringSvn) {
                addFileRecusive(child, path, toAdd);
            }
        } else {
            toAdd.put(file, path);
        }
    }

    public static boolean exportJars(AbstractProcess process,Configuration configuration, File destFolder, IProgressMonitor monitor){
        boolean succes = false;

        List<File> fileToIncludes = new ArrayList<File>();
        final DependencyRepositoryStore store = (DependencyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class) ;
        for (FragmentContainer fc : configuration.getApplicationDependencies()) {
            List<Fragment> fragments = ModelHelper.getAllItemsOfType(fc, ConfigurationPackage.Literals.FRAGMENT) ;
            for (Fragment fragment : fragments) {
                if(FragmentTypes.JAR.equals(fragment.getType()) && fragment.isExported()){
                    final String jarName = fragment.getValue() ;
                    IRepositoryFileStore fileStore =  store.getChild(jarName) ;
                    if(fileStore != null){
                        fileToIncludes.add(fileStore.getResource().getLocation().toFile());
                    }
                }
            }
        }

        for (File f : fileToIncludes) {
            PlatformUtil.copyResource(destFolder, f, monitor);
        }

        return succes;
    }

}
