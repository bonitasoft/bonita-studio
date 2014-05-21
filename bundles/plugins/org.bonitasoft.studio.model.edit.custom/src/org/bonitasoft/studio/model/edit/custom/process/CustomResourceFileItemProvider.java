/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.model.edit.custom.process;

import org.bonitasoft.studio.model.edit.custom.EditModelHelper;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.provider.ResourceFileItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * @author Romain Bioteau
 *
 */
public class CustomResourceFileItemProvider extends ResourceFileItemProvider {

	public CustomResourceFileItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public String getText(Object object) {
		AbstractProcess proc = EditModelHelper.getParentProcess((AssociatedFile)object) ;
		String path = ((ResourceFile) object).getPath() ;
		if(proc != null){
			String id = EditModelHelper.getEObjectID(proc) ;
			if(id != null && path.contains(id)){
				path = path.substring(path.indexOf(id)+id.length(), path.length()) ;
			}
		}
		return "File "+path ;
	}

}
