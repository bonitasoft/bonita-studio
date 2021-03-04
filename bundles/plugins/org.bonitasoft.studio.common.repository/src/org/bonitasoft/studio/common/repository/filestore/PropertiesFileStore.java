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
package org.bonitasoft.studio.common.repository.filestore;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.resources.IFile;

/**
 * @author Romain Bioteau
 *
 */
public abstract class PropertiesFileStore extends AbstractFileStore<Properties> {

	private Properties properties;

	public PropertiesFileStore(String fileName, IRepositoryStore store){
		super(fileName, store) ;
		InputStream is = null ; 
		if(getResource() != null && getResource().exists()){
			try{
				is = getResource().getContents() ;
				this.properties = load(is) ;
			}catch (Exception e) {
				BonitaStudioLog.error(e) ;
			}finally{
				if(is != null){
					try {
						is.close() ;
					} catch (IOException e) {
						BonitaStudioLog.error(e) ;
					}
				}
			}
		}else{
			this.properties = new Properties() ;
		}
	}

	protected Properties load(InputStream is) {
		final Properties properties = new Properties() ;
		try {
			properties.load(is) ;
		} catch (IOException e) {
			BonitaStudioLog.error(e) ;
		}
		return properties ;
	}

	@Override
	protected Properties doGetContent() {
	    return properties;
	}

	
	@Override
	protected void doSave(Object content) {
		if(content instanceof Properties){
			this.properties = (Properties) content ;
			FileOutputStream fos = null ;
			try{
				fos = new FileOutputStream(getResource().getLocation().toFile()) ; 
				((Properties)content).store(fos, null) ;
			}catch(Exception e){
				BonitaStudioLog.error(e) ;
			}finally{
				if(fos != null){
					try {
						fos.close() ;
					} catch (IOException e) {
						BonitaStudioLog.error(e) ;
					}
				}
			}
		}
	}
	
	public IFile getResource() {
		return getParentStore().getResource().getFile(getName());
	}

}
