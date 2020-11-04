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
package org.bonitasoft.studio.groovy.ui.filter;

import java.util.List;

import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.Category;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptProposal;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class ScriptProposalViewerFilter extends ViewerFilter {

    private List<ScriptProposal> proposalToFilter;

    public ScriptProposalViewerFilter(List<ScriptProposal> proposalToFilter) {
        this.proposalToFilter = proposalToFilter;
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        return element instanceof Category
                ? filterCategory((Category) element)
                : filterProposal((ScriptProposal) element);
    }

    private boolean filterProposal(ScriptProposal proposal) {
        return proposal.getChildren().isEmpty()
                ? !proposalToFilter.contains(proposal)
                : proposal.getChildren().stream().anyMatch(this::filterProposal);
    }

    private boolean filterCategory(Category category) {
        return category.getSubcategories().stream().anyMatch(this::filterCategory)
                || category.getProposals().stream().anyMatch(this::filterProposal);
    }

}
