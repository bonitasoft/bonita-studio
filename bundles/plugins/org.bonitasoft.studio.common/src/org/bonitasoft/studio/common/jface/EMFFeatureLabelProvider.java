/**
 * Copyright (C) 2009-2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * @author Mickael Istria
 *
 */
public class EMFFeatureLabelProvider extends LabelProvider {


	private final EStructuralFeature feature;

	public EMFFeatureLabelProvider(final EStructuralFeature feature) {
		this.feature = feature;
	}

	@Override
	public String getText(final Object input) {
		final Object eGet = ((EObject)input).eGet(feature);
		return eGet != null ? eGet.toString() : "";
	}

}
