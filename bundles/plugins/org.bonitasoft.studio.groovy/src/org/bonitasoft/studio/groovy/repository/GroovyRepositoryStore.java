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
package org.bonitasoft.studio.groovy.repository;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;

import org.bonitasoft.studio.common.JarInputStreamIFileFriendly;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.groovy.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Function;

/**
 * @author Romain Bioteau
 *
 */
public class GroovyRepositoryStore extends SourceRepositoryStore<GroovyFileStore> {

    private static final String STORE_NAME = "src-groovy" ;
    private static final Set<String> extensions = new HashSet<String>() ;
    public static final String GROOVY_EXT = "groovy";
    public static final String EXPORTED_JAR_NAME = "groovyscripts.jar";
    static{
        extensions.add(GROOVY_EXT) ;
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public GroovyFileStore createRepositoryFileStore(String fileName) {
        return new GroovyFileStore(fileName, this);
    }

    @Override
    protected Function<IResource, GroovyFileStore> toFileStore() {
        return resource -> {
            IPath resourcePath = resource.getProjectRelativePath();
            IPath repositoryStorePath = getResource().getProjectRelativePath();
            String fileName = resourcePath.makeRelativeTo(repositoryStorePath).toString();
            return createRepositoryFileStore(fileName);
        };
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.groovyScriptRepository;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.groovyScript);
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }


    @Override
    protected GroovyFileStore doImportInputStream(String fileName, InputStream inputStream) {
        if(fileName.endsWith(EXPORTED_JAR_NAME)){
            IFile jar = getResource().getFile("tmpGroovyScripts.jar");
            try {
                if (jar.exists()){
                    jar.delete(true, Repository.NULL_PROGRESS_MONITOR);
                }
                jar.create(inputStream, true, Repository.NULL_PROGRESS_MONITOR);
                inputStream.close();

                JarInputStreamIFileFriendly ji = new JarInputStreamIFileFriendly(jar.getContents());
                JarEntry entry = ji.getNextJarEntry();
                while (entry != null) {
                    if (entry.getName().endsWith(GROOVY_EXT)) {
                        super.doImportInputStream(entry.getName(), ji);
                    }
                    entry = ji.getNextJarEntry();
                }
                ji.forceClose();
                if (jar.exists()){
                    jar.delete(true, Repository.NULL_PROGRESS_MONITOR);
                }

            } catch (Exception e) {
                BonitaStudioLog.error(e);
            }
            return null;
        }else{
            return super.doImportInputStream(fileName, inputStream);
        }
    }
}
