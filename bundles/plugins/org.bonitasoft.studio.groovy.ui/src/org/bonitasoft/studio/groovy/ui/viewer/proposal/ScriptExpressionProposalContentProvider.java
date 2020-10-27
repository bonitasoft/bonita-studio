/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.groovy.ui.viewer.proposal;

import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.Category;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptExpressionContext;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptProposal;
import org.eclipse.jface.viewers.ITreeContentProvider;

public class ScriptExpressionProposalContentProvider implements ITreeContentProvider {

    @Override
    public Object[] getElements(Object inputElement) {
        if(inputElement instanceof ScriptExpressionContext) {
            return ((ScriptExpressionContext) inputElement).getCategories().toArray();
        }
       throw new IllegalArgumentException("Only ScriptExpressionContext is supported");
    }


    @Override
    public Object[] getChildren(Object parentElement) {
        if(parentElement instanceof Category) {
            return ((Category) parentElement).getProposals().toArray();
        }
        return new Object[0];
    }

    @Override
    public Object getParent(Object element) {
        if(element instanceof ScriptProposal) {
            return ((ScriptProposal) element).getCategory();
        }
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return element instanceof Category && !((Category)element).getProposals().isEmpty();
    }

}
