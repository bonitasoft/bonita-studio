

/**
 * 
 */
package org.bonitasoft.studio.expression.editor.autocompletion;

import java.util.ArrayList;

import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.widgets.Control;

public class AutoCompletionField  {

	private final ExpressionProposalProvider contentProposalProvider;
	private final BonitaContentProposalAdapter contentProposalAdapter;

	public AutoCompletionField(final Control control, final IControlContentAdapter controlContentAdapter,
			final IExpressionProposalLabelProvider proposalLabelProvider) {

		contentProposalProvider = new ExpressionProposalProvider(proposalLabelProvider);
		contentProposalProvider.setFiltering(true);
		contentProposalAdapter = new BonitaContentProposalAdapter(control, controlContentAdapter, contentProposalProvider,null, null);
		contentProposalAdapter.setLabelProvider(new ExpressionLabelProvider() {
			@Override
			public String getText(Object expression) {
				return ((ExpressionProposal) expression).getLabel();
			}
		});
		contentProposalAdapter.setPropagateKeys(true);
		contentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
	}

	public void setExpressionProposalLabelProvider(IExpressionProposalLabelProvider labelProvider) {
		contentProposalProvider.setLabelProvider(labelProvider);
		// contentProposalAdapter.setContentProposalProvider(contentProposalProvider);
	}

	public void addExpressionProposalListener(IContentProposalListener listener) {
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

	public void setContext(EObject context) {
		contentProposalAdapter.setContext(context);
	}

	public void setFilteredExpressionType(ArrayList<String> filteredExpressionType) {
		contentProposalAdapter.setFilteredExpressionType(filteredExpressionType);
	}

}