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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class DependenciesTreeLabelProvider extends LabelProvider {

    private final FragmentTypeLabelProvider fragmentTypeLabelProvider = new FragmentTypeLabelProvider() ;

    @Override
    public String getText(Object element) {
        if(element instanceof FragmentContainer){
            return fragmentTypeLabelProvider.getText(element) ;
        }else if(element instanceof Fragment){
            return ((Fragment) element).getValue() ;
        }

        return super.getText(element);
    }


    @Override
    public Image getImage(Object element) {
        if(element instanceof FragmentContainer){
            return fragmentTypeLabelProvider.getImage(element) ;
        }else if(element instanceof Fragment){
            return Pics.getImage("jar.gif",ConfigurationPlugin.getDefault()) ;
        }

        return super.getImage(element);
    }

}
