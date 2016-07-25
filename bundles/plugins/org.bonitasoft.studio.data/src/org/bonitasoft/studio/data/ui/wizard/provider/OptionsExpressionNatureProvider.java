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
package org.bonitasoft.studio.data.ui.wizard.provider;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.EnumType;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 *
 */
public class OptionsExpressionNatureProvider implements IExpressionNatureProvider {

    private final EnumType type;

    public OptionsExpressionNatureProvider(final EnumType type){
        this.type = type ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider#getExpressions()
     */
    @Override
    public Expression[] getExpressions(final EObject context) {
        final List<Expression> result = new ArrayList<Expression>() ;
        for(final String option : type.getLiterals()){
            final Expression exp = ExpressionFactory.eINSTANCE.createExpression() ;
            exp.setName(option) ;
            exp.setContent(option) ;
            exp.setReturnType(String.class.getName()) ;
            exp.setType(ExpressionConstants.CONSTANT_TYPE) ;
            result.add(exp) ;
        }
        return result.toArray(new Expression[]{});
    }

}
