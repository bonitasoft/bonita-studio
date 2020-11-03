/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.xml.api;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.bonitasoft.studio.common.api.BonitaAPI;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

/**
 * @author Aurélien
 *
 */
public class XSDImport {
	
	@BonitaAPI
	public static void importXSD(String filePath){
		File file = new File(filePath);
		XSDRepositoryStore xsdStore = (XSDRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(XSDRepositoryStore.class) ;
		IRepositoryFileStore fileStore = xsdStore.createRepositoryFileStore(file.getName());
		Resource resource = new XSDResourceFactoryImpl().createResource(URI.createFileURI(file.getAbsolutePath())) ;
		try {
			resource.load(Collections.EMPTY_MAP) ;
		} catch (IOException e1) {
			BonitaStudioLog.error(e1) ;
		}
		if(!resource.getContents().isEmpty()){
			XSDSchema content = (XSDSchema) resource.getContents().get(0) ;
			fileStore.save(content) ;
		}
	}
	
}
