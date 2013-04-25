/**
 * 
 */
package org.bonitasoft.studio.expression.editor.autocompletion;

import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalListener;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Control;



public class AutoCompletionField {

    private final ExpressionProposalProvider contentProposalProvider;
    private final BonitaContentProposalAdapter  contentProposalAdapter;


    public AutoCompletionField(final Control control,final IControlContentAdapter controlContentAdapter,final ILabelProvider proposalLabelProvider) {

        contentProposalProvider = new ExpressionProposalProvider(proposalLabelProvider);
        contentProposalProvider.setFiltering(true);
        contentProposalAdapter = new BonitaContentProposalAdapter(control, controlContentAdapter, contentProposalProvider, null, null);
        if(proposalLabelProvider != null){
            contentProposalAdapter.setLabelProvider(proposalLabelProvider) ;
        }
        contentProposalAdapter.setPropagateKeys(true);
        contentProposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);

    }

    public void addExpressionProposalListener(IContentProposalListener listener){
        contentProposalAdapter.addContentProposalListener(listener) ;
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

}