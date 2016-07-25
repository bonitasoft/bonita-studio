/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.autocompletion;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

public class ExpressionProposalProvider implements IContentProposalProvider {

	private Expression[] proposals;
	private IContentProposal[] contentProposals;
	private boolean filterProposals	= false;
	private IExpressionProposalLabelProvider labelProvider;


	public ExpressionProposalProvider(IExpressionProposalLabelProvider proposalLabelProvider) {
		super();
		Assert.isNotNull(proposalLabelProvider);
		labelProvider = proposalLabelProvider;
	}

	public void setLabelProvider(IExpressionProposalLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}


	@Override
	public IContentProposal[] getProposals(String contents, int position) {
		if(proposals == null){
			return new IContentProposal[]{} ;
		}

		if (filterProposals) {
			List<IContentProposal> list = new ArrayList<IContentProposal>();

			for (int i = 0; i < proposals.length; i++) {

				final String text = labelProvider.getText(proposals[i]);
				if (text != null && text.length() >= contents.length() && text.substring(0, contents.length()).equalsIgnoreCase(contents)) {
					list.add(makeContentProposal(proposals[i]));
				}
			}
			return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
		}
		if (contentProposals == null) {
			contentProposals = new IContentProposal[proposals.length];
			for (int i = 0; i < proposals.length; i++) {
				contentProposals[i] = makeContentProposal(proposals[i]);
			}
		}
		return contentProposals;
	}

	public Expression[] getExpressions(){
		if(proposals == null){
			return new Expression[]{} ;
		}
		return proposals ;
	}

	public void setProposals(Expression[] items) {
		proposals = items;
		contentProposals = null;
	}

	public void setFiltering(boolean filterProposals) {
		this.filterProposals = filterProposals;
		contentProposals = null;
	}

	private IContentProposal makeContentProposal(final Expression proposal) {
		return new ExpressionProposal(proposal,labelProvider);
	}


}
