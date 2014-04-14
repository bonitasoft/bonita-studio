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
package org.bonitasoft.studio.profiles.repository;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IFile;


/**
 * @author Romain Bioteau
 *
 */
public class URLProfileFileStore extends ProfileFileStore {

    private final URL url;

    public URLProfileFileStore(URL url, IRepositoryStore<?> store) {
        super(url.toString(), store);
        this.url = url ;
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getName()
     */
    @Override
    public String getName() {
        String file = url.getFile() ;
        String[] segments = file.split("/") ;
        return segments[segments.length -1] ;
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getContent()
     */
    @Override
    public Properties getContent() {
        try {
            return load(url.openStream());
        } catch (IOException e) {
            BonitaStudioLog.error(e) ;
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#getFile()
     */
    @Override
    public IFile getResource() {
        return null ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.IRepositoryFileStore#isShared()
     */
    @Override
    public boolean isShared() {
        return false;
    }

    @Override
    public boolean canBeShared() {
        return false ;
    }


    @Override
    public boolean canBeExported() {
        return false ;
    }

    @Override
    protected void doDelete() {

    }


    public URL getUrl() {
        return url;
    }

    @Override
    public boolean isReadOnly() {
        return true ;
    }

    @Override
    public void setReadOnly(boolean readOnly) {

    }

    @Override
    protected void doSave(Object content) {

    }

}
