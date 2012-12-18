/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class GroovyScriptBarResourceProvider implements BARResourcesProvider {

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.BARResourcesProvider#getResourcesForConfiguration(org.bonitasoft.studio.model.process.AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration, org.bonitasoft.engine.bpm.model.DesignProcessDefinition, java.util.Map)
     */
    @Override
    public List<BarResource> addResourcesForConfiguration(BusinessArchiveBuilder builder, AbstractProcess process, Configuration configuration) throws Exception {

        final List<BarResource>  resources = new ArrayList<BarResource>() ;
        List<File> providedscripts = new ArrayList<File>();
        List<File> scripts = new ArrayList<File>();
        final GroovyRepositoryStore store = (GroovyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class) ;
        if(configuration != null){
            for (EObject fragment : ModelHelper.getAllItemsOfType(configuration, ConfigurationPackage.Literals.FRAGMENT)) {
                if(((Fragment) fragment).getType().equals(FragmentTypes.GROOVY_SCRIPT)){
                    if(((Fragment) fragment).isExported()){
                        GroovyFileStore file = store.getChild(((Fragment) fragment).getValue()) ;
                        scripts.add(file.getResource().getLocation().toFile()); //source file
                        IFile classFile = file.getClassFile();
                        if(classFile == null){
                            throw new Exception("Groovy file "+file.getName()+" has compilation failure and cannot be exported.");
                        }
                        scripts.add(classFile.getLocation().toFile()) ; //class file
                    }
                }
            }
        }

        final ProvidedGroovyRepositoryStore providedStore = (ProvidedGroovyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProvidedGroovyRepositoryStore.class) ;
        for (IRepositoryFileStore file : providedStore.getChildren()) {
            IFile classFile = ((GroovyFileStore)file).getClassFile() ;
            if(classFile != null){
                providedscripts.add(classFile.getLocation().toFile()) ;
            }
        }

        try{
            if(!providedscripts.isEmpty()){
                File jar = new File(providedStore.getResource().getLocation().toFile().getAbsolutePath()+File.separatorChar+ProvidedGroovyRepositoryStore.EXPORTED_PROVIDED_JAR_NAME);
                if(jar.exists()){
                    jar.delete();
                }
                jar.createNewFile();

                JarOutputStream jo = new JarOutputStream(new FileOutputStream(jar));
                for (File entry : providedscripts) {
                    JarEntry jarEntry = new JarEntry(entry.getName());
                    jo.putNextEntry(jarEntry);
                    FileInputStream fis = new FileInputStream(entry);
                    byte[] buf = new byte[1024];
                    int nbBytes;
                    while ((nbBytes = fis.read(buf)) != -1) {
                        jo.write(buf, 0, nbBytes);
                    }
                    fis.close();
                }

                jo.close() ;

                addFileContents(resources,jar);
                jar.delete();
            }

            if(!scripts.isEmpty()){
                File jar = new File(store.getResource().getLocation().toFile().getAbsolutePath()+File.separatorChar+GroovyRepositoryStore.EXPORTED_JAR_NAME);
                if(jar.exists()){
                    jar.delete();
                }

                jar.createNewFile();

                JarOutputStream jo = new JarOutputStream(new FileOutputStream(jar));
                for (File entry : scripts) {
                    JarEntry jarEntry = new JarEntry(entry.getName());
                    jo.putNextEntry(jarEntry);
                    FileInputStream fis = new FileInputStream(entry);
                    byte[] buf = new byte[1024];
                    int nbBytes;
                    while ((nbBytes = fis.read(buf)) != -1) {
                        jo.write(buf, 0, nbBytes);
                    }
                    fis.close();
                }

                jo.close() ;

                addFileContents(resources,jar);
                jar.delete();
            }
        }catch (Exception e) {
            BonitaStudioLog.error(e) ;
        }

        for(BarResource barResource : resources){
            builder.addClasspathResource(barResource) ;
        }

        return resources ;
    }

    private void addFileContents(final List<BarResource> resources, final File file) throws FileNotFoundException, IOException {
        if (file.exists()) {
            byte[] jarBytes = new byte[(int) file.length()];
            InputStream stream = new FileInputStream(file);
            stream.read(jarBytes);
            stream.close();
            resources.add(new BarResource(file.getName(), jarBytes));
        }
    }
}
