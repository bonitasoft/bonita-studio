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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionManager;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;

import com.google.inject.Provider;

/**
 * @author Romain Bioteau
 *
 */
public class ProcResourceDescriptionManager extends
		DefaultResourceDescriptionManager {

	private static final String CACHE_KEY = ProcResourceDescriptionManager.class.getName() + "#getResourceDescription";
	private IDefaultResourceDescriptionStrategy localStrategy;
	
	@Override
	public IResourceDescription getResourceDescription(final Resource resource) {
		return getCache().get(CACHE_KEY, resource, new Provider<IResourceDescription>() {
			public IResourceDescription get() {
				return internalGetResourceDescription(resource, localStrategy);
			}
		});
	}
	
	protected IResourceDescription internalGetResourceDescription(Resource resource, IDefaultResourceDescriptionStrategy strategy) {
        return new InEditoProcResourceDescription(resource, (DefaultResourceDescriptionStrategy) strategy, getCache());
	}
	
	public void setStrategy(IDefaultResourceDescriptionStrategy strategy) {
		super.setStrategy(strategy);
		this.localStrategy = strategy;
	}
	
	
}
