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
package org.bonitasoft.studio.xml.repository;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.xml.XMLPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

/**
 * @author Romain Bioteau
 *
 */
public class DefaultXSDFileStore implements IFileStoreContribution {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IFileStoreContribution#appliesTo(org.bonitasoft.studio.common.repository.model.IRepositoryStore)
	 */
	public boolean appliesTo(IRepositoryStore repository) {
		return repository instanceof XSDRepositoryStore;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IFileStoreContribution#execute(org.bonitasoft.studio.common.repository.model.IRepositoryStore)
	 */
	public void execute(IRepositoryStore repository) {
		Enumeration<URL> xsds = XMLPlugin.getDefault().getBundle().findEntries("xsd", "*.xsd", false);
		if(	xsds != null ){
			while (xsds.hasMoreElements()) {
				URL url = (URL)xsds.nextElement();
				URL fileURL = null ;
				try {
					fileURL = FileLocator.toFileURL(url);
				} catch (IOException e) {
					BonitaStudioLog.error(e) ;
				}
				String[] segments = url.getFile().split("/") ;
				String fileName = segments[segments.length-1] ;
				Resource resource = new XSDResourceFactoryImpl().createResource(URI.createFileURI(fileURL.getFile())) ; 
				try {
					resource.load(Collections.EMPTY_MAP) ;
				} catch (IOException e) {
					BonitaStudioLog.error(e) ;
				}
				IRepositoryFileStore file = repository.createRepositoryFileStore(fileName) ;
				file.save(resource.getContents().get(0)) ;
			}
		}
	}

}
