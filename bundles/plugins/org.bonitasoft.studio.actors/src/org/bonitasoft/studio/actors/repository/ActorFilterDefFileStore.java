/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.repository;

import java.util.Optional;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.ui.wizard.FilterDefinitionWizard;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.AbstractDefFileStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.dialog.ConnectorDefinitionWizardDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 * @author Baptiste Mesta
 */
public class ActorFilterDefFileStore extends AbstractDefFileStore implements IRenamable {

    public ActorFilterDefFileStore(String fileName, AbstractEMFRepositoryStore<ActorFilterDefFileStore> store) {
        super(fileName, store);
    }

    @Override
    protected Bundle getBundle() {
        return ActorsPlugin.getDefault().getBundle();
    }

    @Override
    public String getDisplayName() {
        ActorFilterDefRepositoryStore store = (ActorFilterDefRepositoryStore) getParentStore();
        ConnectorDefinition def;
        try {
            def = getContent();
        } catch (ReadFileStoreException e) {
            return getName();
        }
        if (def != null) {
            String defName = store.getResourceProvider().getConnectorDefinitionLabel(def);
            if (defName == null) {
                defName = def.getId();
            }
            return defName + " (" + def.getVersion() + ")";
        }
        return super.getDisplayName();
    }

    @Override
    public Image getIcon() {
        ActorFilterDefRepositoryStore store = (ActorFilterDefRepositoryStore) getParentStore();
        ConnectorDefinition def;
        try {
            def = getContent();
        } catch (ReadFileStoreException e) {
            return null;
        }
        if (def != null) {
            return store.getResourceProvider().getDefinitionIcon(def);
        }
        return null;
    }

    @Override
    protected IWorkbenchPart doOpen() {
        final ActorFilterDefRepositoryStore defStore = RepositoryManager.getInstance()
                .getRepositoryStore(ActorFilterDefRepositoryStore.class);
        final DefinitionResourceProvider messageProvider = DefinitionResourceProvider.getInstance(defStore,
                ActorsPlugin.getDefault().getBundle());
        FilterDefinitionWizard wizard;
        try {
            wizard = new FilterDefinitionWizard(getContent(), messageProvider);
        } catch (ReadFileStoreException e) {
            return null;
        }
        WizardDialog wd = new ConnectorDefinitionWizardDialog(Display.getCurrent().getActiveShell(), wizard,
                messageProvider);
        wd.open();
        return null;
    }

    @Override
    public void rename(String newName) {
        doOpen();
    }

    @Override
    public Optional<String> retrieveNewName() {
        return Optional.of("");
    }
}
