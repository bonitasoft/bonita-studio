package org.bonitasoft.studio.application.ui.control.model;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.swt.graphics.Image;

public class RepositoryStoreDisplayable implements IDisplayable {

    private RepositoryStore repositoryStore;

    public RepositoryStoreDisplayable(RepositoryStore repositoryStore) {
        this.repositoryStore = repositoryStore;
    }

    @Override
    public String getDisplayName() {
        if (repositoryStore.getStore() instanceof DiagramRepositoryStore) {
            return Messages.processes;
        }
        if (repositoryStore.getStore() instanceof WebPageRepositoryStore) {
            return Messages.pagesAndLayouts;
        }
        return repositoryStore.getStore().getDisplayName();
    }

    @Override
    public Image getIcon() {
        return Pics.getImage(repositoryStore.getStore().getPathIcon());
    }

}
