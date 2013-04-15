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
package org.bonitasoft.studio.examples.store;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.examples.ExamplePlugin;
import org.bonitasoft.studio.examples.i18n.Messages;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ExamplesRepositoryStore implements IRepositoryStore<ExampleFileStore> {

    public static final String STORE_NAME = "examples" ;
    private static final Set<String> extensions = new HashSet<String>() ;
    static{
        extensions.add("zip") ;
        extensions.add("bar") ;
    }

    @Override
    public void createRepositoryStore(IRepository repository) {

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public ExampleFileStore createRepositoryFileStore(String url) {
        try {
            return new ExampleFileStore(new URL(url),this)  ;
        } catch (MalformedURLException e) {

        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.examples;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return null ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryStore#isShared()
     */
    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }



    @Override
    public ExampleFileStore importInputStream(String fileName,InputStream inputStream) {
        return null;
    }


    @Override
    public IFolder getResource() {
        return null;
    }

    @Override
    public ExampleFileStore getChild(String fileName) {
        URL url = ExamplePlugin.getDefault().getBundle().getEntry(STORE_NAME+"/"+fileName);
        if(url != null){
            return createRepositoryFileStore(url.toString()) ;
        }
        return null ;
    }

    @Override
    public List<ExampleFileStore> getChildren() {
        Enumeration<URL> examples = ExamplePlugin.getDefault().getBundle().findEntries(STORE_NAME, "*.*", false);
        List<ExampleFileStore> result = new ArrayList<ExampleFileStore>() ;
        if(	examples != null ){
            while (examples.hasMoreElements()) {
                URL url = examples.nextElement();
                String[] segments = url.getFile().split("/") ;
                String fileName = segments[segments.length-1] ;
                if(fileName.lastIndexOf(".") != -1){
                    String extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()) ;
                    if(extensions.contains(extension)){
                        result.add(createRepositoryFileStore(url.toString())) ;
                    }
                }
            }
        }

        return result;
    }

    @Override
    public void refresh() {

    }

    @Override
    public boolean canBeShared() {
        return false;
    }

    @Override
    public ExampleFileStore importIResource(String fileName,
            IResource resource) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canBeExported() {
        // TODO Auto-generated method stub
        return false;
    }

	@Override
	public void migrate() throws CoreException, MigrationException {
		// TODO Auto-generated method stub
		
	}

}
