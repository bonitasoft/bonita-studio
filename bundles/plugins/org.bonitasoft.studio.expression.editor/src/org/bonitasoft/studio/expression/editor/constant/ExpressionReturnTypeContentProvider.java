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
package org.bonitasoft.studio.expression.editor.constant;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ExpressionReturnTypeContentProvider implements IStructuredContentProvider {

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(final Viewer arg0, final Object arg1, final Object arg2) {

    }

    @Override
    public Object[] getElements(final Object input) {
        final String[] javaDefaultTypes = new String[]{
                String.class.getName()
                ,Boolean.class.getName()
                ,Long.class.getName()
                ,Float.class.getName()
                ,Double.class.getName()
                ,Integer.class.getName()
                ,Date.class.getName()
                ,Object.class.getName()
        };
        final Set<String> types = new HashSet<String>(Arrays.asList(javaDefaultTypes)) ;

        if(input != null && input instanceof String){
            types.add((String) input) ;
        }
        return types.toArray() ;
    }

}
