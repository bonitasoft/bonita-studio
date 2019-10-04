/**
 * Copyright (C) 2012-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.condition.resources;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.naming.SimpleNameProvider;
import org.eclipse.xtext.util.SimpleAttributeResolver;

/**
 * @author Romain Bioteau
 *
 */
public class ProcQualifiedNameProvider extends SimpleNameProvider implements IQualifiedNameProvider {

	private final IQualifiedNameConverter converter = new ProcQualifiedNameConverter();

	@Override
    public QualifiedName getFullyQualifiedName(final EObject obj) {
		final String name = SimpleAttributeResolver.NAME_RESOLVER.apply(obj);
        if (name == null || name.isEmpty()) {
            return null;
        }
		return converter.toQualifiedName(name);
	}
}
