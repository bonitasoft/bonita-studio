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
package org.bonitasoft.studio.condition.scoping;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.DefaultGlobalScopeProvider;

import com.google.common.base.Predicate;
import com.google.inject.Singleton;

/**
 * @author Romain Bioteau
 *
 */
@Singleton
public class ConditionModelGlobalScopeProvider extends DefaultGlobalScopeProvider {


	private List<String> accessibleObjects;

	@Override
	public IScope getScope(Resource resource, EReference reference, Predicate<IEObjectDescription> filter) {
		if(filter==null){
			filter = new Predicate<IEObjectDescription>() {

				@Override
				public boolean apply(IEObjectDescription input) {
					if(accessibleObjects != null){
						URI uri = EcoreUtil2.getURI(input.getEObjectOrProxy());
						if(uri.hasFragment()){
							return accessibleObjects.contains(uri.fragment());
						}
						return false;
					}
					return true;
				}
			};
		}
		return super.getScope(resource, reference, filter);
	}

	public void setAccessibleEObjects(List<String> accessibleObjects) {
		this.accessibleObjects = accessibleObjects;
	}
	
	protected IScope createContainerScopeWithContext(Resource eResource, IScope parent, IContainer container,
			Predicate<IEObjectDescription> filter, EClass type, boolean ignoreCase) {
		return createContainerScope(parent, container, filter, type, ignoreCase);
	}
}
