/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;

/**
 * @author Romain Bioteau
 */
public abstract class IProposalAdapter implements IProposalListener {

    @Override
    public boolean isPageFlowContext() {
        return false;
    }

    @Override
    public void setIsPageFlowContext(final boolean isPageFlowContext) {

    }

    @Override
    public String handleEvent(final EObject context, final String fixedReturnType) {
        return null;
    }

    @Override
    public boolean isOverViewContext() {
        return false;
    }

    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {

    }

    @Override
    public void setEStructuralFeature(final EStructuralFeature feature) {

    }

    @Override
    public boolean isRelevant(final EObject context, final ISelection selection) {
        return false;
    }
}
