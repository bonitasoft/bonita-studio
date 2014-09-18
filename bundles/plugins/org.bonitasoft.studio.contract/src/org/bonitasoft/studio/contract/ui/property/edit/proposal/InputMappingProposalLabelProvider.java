package org.bonitasoft.studio.contract.ui.property.edit.proposal;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.swt.graphics.Image;


public class InputMappingProposalLabelProvider extends AdapterFactoryLabelProvider {

    public InputMappingProposalLabelProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
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
            return super.getImage(((InputMappingProposal) object).getData());
        }
        return null;
    }

}
