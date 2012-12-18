/**
 * 
 */
package org.bonitasoft.studio.expression.editor.autocompletion;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.widgets.Control;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaContentProposalAdapter extends ContentProposalAdapter {

    public BonitaContentProposalAdapter(final Control control,
            IControlContentAdapter controlContentAdapter,
            IContentProposalProvider proposalProvider, KeyStroke keyStroke,
            char[] autoActivationCharacters) {
        super(control, controlContentAdapter, proposalProvider, keyStroke,autoActivationCharacters);
    }

}
