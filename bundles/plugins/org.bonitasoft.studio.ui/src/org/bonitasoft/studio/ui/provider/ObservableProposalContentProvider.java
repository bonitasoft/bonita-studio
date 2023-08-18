/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.provider;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

/**
 * Provide proposals for an observable list of objects. If no labelProvider is provided, then toString method is used.
 * Warning: The IContentProposal returned after selection contains a String, it is meant to be used by a text widget or
 * something equivalent.
 * You will certainly have to implement a converter String -> Object on your side.
 */
public class ObservableProposalContentProvider<T> implements IContentProposalProvider {

    private IObservableList<T> proposals;
    private Function<T, String> labelProvider;
    private boolean filterProposals;

    public ObservableProposalContentProvider(IObservableList<T> proposals) {
        this(proposals, element -> element.toString());
    }

    public ObservableProposalContentProvider(IObservableList<T> proposals, Function<T, String> labelProvider) {
        this(proposals, labelProvider, false);
    }

    public ObservableProposalContentProvider(IObservableList<T> proposals, Function<T, String> labelProvider,
            boolean filterProposals) {
        this.proposals = proposals;
        this.labelProvider = labelProvider;
        this.filterProposals = filterProposals;
    }

    @Override
    public IContentProposal[] getProposals(String contents, int position) {
        if (filterProposals) {
            List<ContentProposal> contentProposals = proposals.stream()
                    .map(labelProvider::apply)
                    .filter(element -> element.length() >= contents.length())
                    .filter(element -> element.substring(0, contents.length()).equalsIgnoreCase(contents))
                    .map(ContentProposal::new)
                    .collect(Collectors.toList());
            return contentProposals.toArray(new IContentProposal[contentProposals.size()]);
        }
        List<ContentProposal> contentProposals = proposals.stream()
                .map(labelProvider::apply)
                .map(ContentProposal::new)
                .collect(Collectors.toList());
        return contentProposals.toArray(new IContentProposal[contentProposals.size()]);
    }

}
