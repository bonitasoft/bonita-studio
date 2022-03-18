/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.wizard;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.ui.WidgetFactory;
import org.bonitasoft.studio.maven.ui.handler.CustomPageProjectSelectionProvider;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.Wizard;

public abstract class DeleteCustomPageProjectWizard extends Wizard {

    private final IObservableValue<CustomPageProjectFileStore> fileStoreObservable;
    private final CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore;
    private final WidgetFactory widgetFactory;

    public DeleteCustomPageProjectWizard(final CustomPageProjectRepositoryStore<? extends CustomPageProjectFileStore> repositoryStore, final WidgetFactory widgetFactory,
            final CustomPageProjectSelectionProvider selectionProvider) {
        this.repositoryStore = repositoryStore;
        this.widgetFactory = widgetFactory;
        fileStoreObservable = new WritableValue<CustomPageProjectFileStore>(selectionProvider.getSelection(), CustomPageProjectFileStore.class);
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.delete);
    }

    @Override
    public void addPages() {
        super.addPages();
        final SelectCustomPageProjectPage selectionPage = new SelectCustomPageProjectPage(repositoryStore, widgetFactory, fileStoreObservable);
        selectionPage.addFilter(new ViewerFilter() {
            
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return element instanceof CustomPageProjectFileStore && !((CustomPageProjectFileStore)element).isReadOnly();
            }
        });
        selectionPage.setTitle(getSelectionPageTitle());
        selectionPage.setDescription(getSelectionPageDeleteDescription());
        addPage(selectionPage);
    }
    
    protected abstract String getSelectionPageDeleteDescription();

    protected abstract String getSelectionPageTitle();

    @Override
    public boolean performFinish() {
        final CustomPageProjectFileStore fileStore = fileStoreObservable.getValue();
        if (FileActionDialog.confirmDeletionQuestion(fileStore.getDisplayName())) {
            fileStore.delete();
            return true;
        }
        return false;
    }

}
