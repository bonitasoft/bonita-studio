/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.git.core;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;


public class CustomEvaluationContext implements IEvaluationContext {

    private Map<String, Object> variables = new HashMap<>();

    /* (non-Javadoc)
     * @see org.eclipse.core.expressions.IEvaluationContext#getParent()
     */
    @Override
    public IEvaluationContext getParent() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.expressions.IEvaluationContext#getRoot()
     */
    @Override
    public IEvaluationContext getRoot() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.expressions.IEvaluationContext#setAllowPluginActivation(boolean)
     */
    @Override
    public void setAllowPluginActivation(boolean value) {

    }

    /* (non-Javadoc)
     * @see org.eclipse.core.expressions.IEvaluationContext#getAllowPluginActivation()
     */
    @Override
    public boolean getAllowPluginActivation() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.expressions.IEvaluationContext#getDefaultVariable()
     */
    @Override
    public Object getDefaultVariable() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.expressions.IEvaluationContext#addVariable(java.lang.String, java.lang.Object)
     */
    @Override
    public void addVariable(String name, Object value) {
        variables.put(name, value);
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.expressions.IEvaluationContext#removeVariable(java.lang.String)
     */
    @Override
    public Object removeVariable(String name) {
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.expressions.IEvaluationContext#getVariable(java.lang.String)
     */
    @Override
    public Object getVariable(String name) {
        return variables.get(name);
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.expressions.IEvaluationContext#resolveVariable(java.lang.String, java.lang.Object[])
     */
    @Override
    public Object resolveVariable(String name, Object[] args) throws CoreException {
        return null;
    }

}
