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
package org.bonitasoft.studio.expression.editor.provider;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.ExpressionProviderService;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class ExpressionTypeContentProvider implements IStructuredContentProvider {

    @Override
    public void dispose() {

    }

    @Override
    public void inputChanged(Viewer arg0, Object arg1, Object arg2) {

    }

    @Override
    public Object[] getElements(Object context) {
        Set<IExpressionProvider> expressionTypes = new HashSet<>() ;
        if(context == null){
            expressionTypes.add(ExpressionProviderService.getInstance().getExpressionProvider(ExpressionConstants.CONSTANT_TYPE));
            expressionTypes.add(ExpressionProviderService.getInstance().getExpressionProvider(ExpressionConstants.SCRIPT_TYPE));
        }else{
            for(IExpressionProvider provider : ExpressionProviderService.getInstance().getExpressionProviders()){
                if(provider.getExpressionEditor(null, (EObject) context) != null && provider.isRelevantFor((EObject)context)){
                    expressionTypes.add(provider) ;
                }
            }
        }


        return expressionTypes.toArray();
    }


}
