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
