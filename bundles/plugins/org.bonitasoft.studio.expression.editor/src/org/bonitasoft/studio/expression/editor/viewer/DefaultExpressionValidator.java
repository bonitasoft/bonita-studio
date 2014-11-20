/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.expression.editor.viewer;

import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * @author Romain Bioteau
 *
 */
public abstract class DefaultExpressionValidator implements IExpressionValidator {

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#setInputExpression(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public void setInputExpression(final Expression inputExpression) {
        //Intended to be implement in children
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#setDomain(org.eclipse.emf.edit.domain.EditingDomain)
     */
    @Override
    public void setDomain(final EditingDomain domain) {
        //Intended to be implement in children
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#setContext(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void setContext(final EObject context) {
        //Intended to be implement in children
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionValidator#isRelevantForExpressionType(java.lang.String)
     */
    @Override
    public boolean isRelevantForExpressionType(final String type) {
        return true;
    }

}
