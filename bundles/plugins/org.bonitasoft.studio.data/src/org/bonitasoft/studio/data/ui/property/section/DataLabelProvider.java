/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.data.ui.property.section;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 *
 * @author mistria
 *
 */
public class DataLabelProvider extends ObservableMapLabelProvider {

    public DataLabelProvider(final IObservableMap[] attributeMaps) {
        super(attributeMaps);
    }

    @Override
    public Image getImage(final Object element) {
        return null;
    }

    @Override
    public String getText(final Object element) {
        if(element instanceof Data){
            if(((Data)element).getDataType() != null){
                return ((Data)element).getName() + " -- " + getTypeLabel((Data)element); //$NON-NLS-1$
            }else{
                return ((Data)element).getName();
            }
        } else if (element != null) {
            return element.toString();
        }
        return super.getText(element);
    }

    /**
     * @param element
     * @return
     */
    public static String getTypeLabel(final Data element) {
        final StringBuilder builder = new StringBuilder();
        if (element.isMultiple()) {
            builder.append("Multiple<"); //$NON-NLS-1$
        };
        if(element instanceof JavaObjectData){
            builder.append( ((JavaObjectData)element).getClassName() );
        }else{
            builder.append( ModelHelper.getDataTypeNLLabel(element.getDataType().getName()) );
        }
        if (element.isMultiple()) {
            builder.append(">"); //$NON-NLS-1$
        };
        return builder.toString();
    }




}
