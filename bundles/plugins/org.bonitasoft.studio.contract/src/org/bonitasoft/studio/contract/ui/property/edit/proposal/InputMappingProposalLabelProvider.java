package org.bonitasoft.studio.contract.ui.property.edit.proposal;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


public class InputMappingProposalLabelProvider extends LabelProvider {

    private final AdapterFactoryLabelProvider adapterFactoryLabelProvider;

    public InputMappingProposalLabelProvider(final AdapterFactoryLabelProvider adapterFactoryLabelProvider) {
        this.adapterFactoryLabelProvider = adapterFactoryLabelProvider;
    }

    @Override
    public String getText(final Object object) {
        if (object instanceof InputMappingProposal) {
            return ((InputMappingProposal) object).getLabel();
        }
        return null;
    }

    @Override
    public Image getImage(final Object object) {
        if (object instanceof InputMappingProposal) {
            return adapterFactoryLabelProvider.getImage(((InputMappingProposal) object).getData());
        }
        return null;
    }

}
