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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;


/**
 * @author Romain Bioteau
 *
 */
public class RawDependenciesContentProvider implements IStructuredContentProvider {

    @Override
    public void dispose() {}

    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {}

    @Override
    public Object[] getElements(Object element) {
        Assert.isTrue(element instanceof Collection<?>) ;
        List<Fragment> fragments = new ArrayList<Fragment>() ;
        Collection<FragmentContainer> containers =  (Collection<FragmentContainer>) element ;
        Set<String> jarnames = new HashSet<String>() ;
        List<Fragment> toRemove = new ArrayList<Fragment>() ;
        for(FragmentContainer fc : containers){
            Collection<Fragment> containerFragments = ModelHelper.getAllItemsOfType((EObject) fc, ConfigurationPackage.Literals.FRAGMENT) ;
            for(Fragment f : containerFragments){
                if(f.isExported()){
                    if(!jarnames.contains(f.getValue())){
                        jarnames.add(f.getValue()) ;
                        fragments.add(f) ;
                    }
                }
            }
        }
        return fragments.toArray() ;
    }


}
