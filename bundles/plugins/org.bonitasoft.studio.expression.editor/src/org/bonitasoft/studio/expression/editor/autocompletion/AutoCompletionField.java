/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.expression.editor.autocompletion;

import java.util.List;

import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Control;

public class AutoCompletionField {

    private final ExpressionProposalProvider contentProposalProvider;
    private final BonitaContentProposalAdapter contentProposalAdapter;

    public AutoCompletionField(final Control control, final IControlContentAdapter controlContentAdapter,
            final IExpressionProposalLabelProvider proposalLabelProvider) {

        contentProposalProvider = new ExpressionProposalProvider(proposalLabelProvider);
        contentProposalProvider.setFiltering(true);
        contentProposalAdapter = new BonitaContentProposalAdapter(control, controlContentAdapter, contentProposalProvider, null, null);
        contentProposalAdapter.setLabelProvider(new ExpressionLabelProvider() {

            @Override
            public String getText(final Object expression) {
                return ((ExpressionProposal) expression).getLabel();
            }
        });
        contentProposalAdapter.setPropagateKeys(true);
        contentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
    }

    public void setExpressionProposalLabelProvider(final IExpressionProposalLabelProvider labelProvider) {
        contentProposalProvider.setLabelProvider(labelProvider);
    }

    public void addExpressionProposalListener(final IContentProposalListener listener) {
        contentProposalAdapter.addContentProposalListener(listener);
    }

    public void setProposals(final Expression[] proposals) {
        contentProposalProvider.setProposals(proposals);
    }

    public ExpressionProposalProvider getContentProposalProvider() {
        return contentProposalProvider;
    }

    public BonitaContentProposalAdapter getContentProposalAdapter() {
        return contentProposalAdapter;
    }

    public void setContext(final EObject context) {
        contentProposalAdapter.setContext(context);
    }

    public void setFilteredExpressionType(final List<String> filteredExpressionType) {
        contentProposalAdapter.setFilteredExpressionType(filteredExpressionType);
    }

    public void setLabelProvider(final LabelProvider labelProvider) {
        contentProposalAdapter.setLabelProvider(labelProvider);
    }

    public void setCreateShortcutZone(final boolean createShortcutZone) {
        contentProposalAdapter.setCreateShortcutZone(createShortcutZone);
    }

    public void setSelection(ISelection selection) {
        this.contentProposalAdapter.setSelection(selection);
    }

}
