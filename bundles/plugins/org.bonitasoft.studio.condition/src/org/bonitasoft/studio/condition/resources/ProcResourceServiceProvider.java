/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.condition.resources;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.DescriptionUtils;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.generic.GenericResourceServiceProvider;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionManager;
import org.eclipse.xtext.resource.impl.SimpleResourceDescriptionsBasedContainerManager;

public class ProcResourceServiceProvider extends GenericResourceServiceProvider implements IResourceServiceProvider {

	private DefaultResourceDescriptionManager resourceDescriptionManager = new ProcResourceDescriptionManager();
	
	public ProcResourceServiceProvider(){
		ProcResourceDescriptionStrategy startegy = new ProcResourceDescriptionStrategy();
		resourceDescriptionManager.setStrategy(startegy);
		resourceDescriptionManager.setDescriptionUtils(new DescriptionUtils());
		resourceDescriptionManager.setContainerManager(new SimpleResourceDescriptionsBasedContainerManager());
	}
	
    public ProcResourceServiceProvider(ProcResourceDescriptionManager manager) {
        ProcResourceDescriptionStrategy startegy = new ProcResourceDescriptionStrategy();
        resourceDescriptionManager = manager;
        resourceDescriptionManager.setStrategy(startegy);
        resourceDescriptionManager.setDescriptionUtils(new DescriptionUtils());
        resourceDescriptionManager.setContainerManager(new SimpleResourceDescriptionsBasedContainerManager());
    }

	public IResourceDescription.Manager getResourceDescriptionManager() {
		return resourceDescriptionManager;
	}
	
	public boolean canHandle(URI uri) {
		return uri.fileExtension().equals("proc");
	}
	
	@Override
	public <T> T get(Class<T> t) {
		return null;
	}
}
