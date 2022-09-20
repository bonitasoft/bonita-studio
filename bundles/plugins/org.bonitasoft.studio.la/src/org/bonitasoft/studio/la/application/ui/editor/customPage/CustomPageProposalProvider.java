/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor.customPage;

import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

public abstract class CustomPageProposalProvider implements IContentProposalProvider, IResourceChangeListener {

    /*
     * The proposals provided.
     */
    protected Collection<CustomPageDescriptor> proposals;

    /*
     * The proposals mapped to IContentProposal. Cached for speed in the case
     * where filtering is not used.
     */
    private IContentProposal[] contentProposals;

    /*
     * Boolean that tracks whether filtering is used.
     */
    private boolean filterProposals;

    protected final CustomPageProvider provider;

    private boolean updated;

    public CustomPageProposalProvider(CustomPageProvider provider, boolean filterProposals) {
        this.provider = provider;
        this.filterProposals = filterProposals;
        setProposals(getCustomPageDescriptors(provider));
        provider.getWebPageStore().getResource().getWorkspace().addResourceChangeListener(this);
    }

    @Override
    public IContentProposal[] getProposals(String contents, int position) {
        if (filterProposals) {
            IContentProposal[] pagesToPropose = proposals.stream()
                    .filter(page -> page.getDisplayName().length() >= contents.length())
                    .filter(page -> page.getDisplayName().substring(0, contents.length())
                            .equalsIgnoreCase(contents))
                    .map(page -> new ContentProposal(page.getId(), page.toString(), page.getDescription()))
                    .toArray(IContentProposal[]::new);
            if (pagesToPropose.length == 0) {
                pagesToPropose = proposals.stream()
                        .filter(page -> page.getId().length() >= contents.length())
                        .filter(page -> page.getId().substring(0, contents.length())
                                .equalsIgnoreCase(contents))
                        .map(page -> new ContentProposal(page.getId(), page.toString(), page.getDescription()))
                        .toArray(IContentProposal[]::new);
            }
            return pagesToPropose;
        }
        if (contentProposals == null) {
            contentProposals = proposals.stream()
                    .map(page -> new ContentProposal(page.getId(), page.toString(), page.getDescription()))
                    .toArray(IContentProposal[]::new);
        }
        return contentProposals;
    }

    public void setProposals(Collection<CustomPageDescriptor> items) {
        this.proposals = items;
        contentProposals = null;
    }

    public void setFiltering(boolean filterProposals) {
        this.filterProposals = filterProposals;
        // Clear any cached proposals.
        contentProposals = null;
    }

    @Override
    public void resourceChanged(IResourceChangeEvent event) {
        updated = false;
        try {
            if (event.getDelta() != null) {
                event.getDelta().accept(this::updateProposals);
            }
        } catch (final CoreException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean updateProposals(IResourceDelta delta) {
        if (updated) {
            return false;
        }
        if (shouldUpdateProposals(delta)) {
            computeProposals();
            updated = true;
            return false;
        }
        return true;
    }

    protected void computeProposals() {
        new WorkspaceJob("Update proposals") {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                setProposals(getCustomPageDescriptors(provider));
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    protected boolean shouldUpdateProposals(IResourceDelta delta) {
        IResource resource = delta.getResource();
        return ("json".equals(resource.getFileExtension()) && !".index.json".equals(resource.getName()))
                && resource.getLocation() != null
                && provider.getWebPageStore().getResource().getLocation().isPrefixOf(resource.getLocation());
    }

    protected abstract Collection<CustomPageDescriptor> getCustomPageDescriptors(CustomPageProvider provider);

    public void dispose() {
        provider.getWebPageStore().getResource().getWorkspace().removeResourceChangeListener(this);
    }

}
