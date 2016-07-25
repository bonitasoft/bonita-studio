/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.parameters.property.section.provider;

import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ParameterNameTypeLabelProvider extends ColumnLabelProvider {

	private final ParameterTypeLabelProvider typeProvider ;

	public ParameterNameTypeLabelProvider(){
		typeProvider = new ParameterTypeLabelProvider() ;
	}

	@Override
	public String getText(final Object element) {
		return ((Parameter) element).getName() + " ("+typeProvider.getText(element)+")";
	}

	@Override
	public Image getImage(final Object element) {
		return Pics.getImage(PicsConstants.parameter);
	}
}
