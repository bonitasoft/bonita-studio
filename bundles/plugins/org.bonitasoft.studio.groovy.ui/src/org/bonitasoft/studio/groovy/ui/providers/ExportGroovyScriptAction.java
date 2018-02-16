/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui.providers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.extension.IConfigurationExportAction;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;

/**
 * @author Romain Bioteau
 */
public class ExportGroovyScriptAction extends Action implements IConfigurationExportAction {

    private Configuration configuration;
    private String path;

    public ExportGroovyScriptAction() {
        super();

    }

    @Override
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void setProcess(AbstractProcess process) {
    }

    @Override
    public void run() {
        if (path != null) {
            try {
                exportGroovyScriptAsJarForProcess(path);
                exportGroovyScriptAsJarForApplication(path);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private void exportGroovyScriptAsJarForProcess(String filePath) throws IOException {
        File destDir = new File(path, PROCESS_CLASSPATH_FOLDER);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        List<File> scripts = new ArrayList<File>();
        final GroovyRepositoryStore groovyStore = (GroovyRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(GroovyRepositoryStore.class);
        FragmentContainer groovyContainer = getGroovyProcessContainer(configuration);
        for (Fragment script : groovyContainer.getFragments()) {
            if (script.isExported()) {
                GroovyFileStore file = groovyStore.getChild(script.getValue());
                if (file != null) {
                    scripts.add(file.getResource().getLocation().toFile()); //source file
                    List<IFile> classFiles = file.getClassFiles();
                    for (IFile classFile : classFiles) {
                        scripts.add(classFile.getLocation().toFile()); //class file
                    }
                }
            }
        }
        if (!scripts.isEmpty()) {
            File jar = new File(destDir, GroovyRepositoryStore.EXPORTED_JAR_NAME);
            if (jar.exists()) {
                jar.delete();
            }

            jar.createNewFile();

            try (JarOutputStream jo = new JarOutputStream(new FileOutputStream(jar));) {
                for (File entry : scripts) {
                    JarEntry jarEntry = new JarEntry(entry.getName());
                    jo.putNextEntry(jarEntry);
                    try (FileInputStream fis = new FileInputStream(entry);) {
                        byte[] buf = new byte[1024];
                        int nbBytes;
                        while ((nbBytes = fis.read(buf)) != -1) {
                            jo.write(buf, 0, nbBytes);
                        }
                    }
                }
            }
        }
    }

    private void exportGroovyScriptAsJarForApplication(String filePath) throws IOException {
        File destDir = new File(path, APPLICATION_CLASSPATH_FOLDER);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        List<File> scripts = new ArrayList<File>();
        final GroovyRepositoryStore groovyStore = (GroovyRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(GroovyRepositoryStore.class);
        FragmentContainer groovyContainer = getGroovyApplicationContainer(configuration);
        for (Fragment script : groovyContainer.getFragments()) {
            if (script.isExported()) {
                GroovyFileStore file = groovyStore.getChild(script.getValue());
                if (file != null) {
                    scripts.add(file.getResource().getLocation().toFile()); //source file                   
                    List<IFile> classFiles = file.getClassFiles();
                    for (IFile classFile : classFiles) {
                        scripts.add(classFile.getLocation().toFile()); //class file
                    }
                }
            }
        }
        if (!scripts.isEmpty()) {
            File jar = new File(destDir, GroovyRepositoryStore.EXPORTED_JAR_NAME);
            if (jar.exists()) {
                jar.delete();
            }

            jar.createNewFile();

            try (JarOutputStream jo = new JarOutputStream(new FileOutputStream(jar));) {
                for (File entry : scripts) {
                    JarEntry jarEntry = new JarEntry(entry.getName());
                    jo.putNextEntry(jarEntry);
                    try (FileInputStream fis = new FileInputStream(entry);) {
                        byte[] buf = new byte[1024];
                        int nbBytes;
                        while ((nbBytes = fis.read(buf)) != -1) {
                            jo.write(buf, 0, nbBytes);
                        }
                    }
                }
            }
        }

    }

    private FragmentContainer getGroovyProcessContainer(Configuration configuration) {
        for (FragmentContainer fc : configuration.getProcessDependencies()) {
            if (fc.getId().equals(FragmentTypes.GROOVY_SCRIPT)) {
                return fc;
            }
        }
        return null;
    }

    private FragmentContainer getGroovyApplicationContainer(Configuration configuration) {
        for (FragmentContainer fc : configuration.getApplicationDependencies()) {
            if (fc.getId().equals(FragmentTypes.GROOVY_SCRIPT)) {
                return fc;
            }
        }
        return null;
    }

    @Override
    public void setTargetPath(String path) {
        this.path = path;
    }

}
