package org.bonitasoft.studio.groovy.ui.viewer.proposal;

import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.Category;
import org.bonitasoft.studio.groovy.ui.viewer.proposal.model.ScriptProposal;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class ScriptExpressionProposalLabelProvider extends LabelProvider {
    
    @Override
    public String getText(Object element) {
        if(element instanceof Category) {
            return ((Category) element).getName();
        }
        if(element instanceof ScriptProposal) {
            return ((ScriptProposal) element).getName();
        }
        return super.getText(element);
    }
    
    @Override
    public Image getImage(Object element) {
        if(element instanceof Category) {
            return ((Category) element).getIcon();
        }
        return super.getImage(element);
    }

}
