/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.pagedesigner.core.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.pagedesigner.PageDesignerPlugin;
import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class WebWidgetRepositoryStore extends AbstractRepositoryStore<WebWidgetFileStore> {

    public static final String WEB_WIDGET_REPOSITORY_NAME = "web_widgets";

    @Override
    public String getName() {
        return WEB_WIDGET_REPOSITORY_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.widgetRepository;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("custom_widget.png", PageDesignerPlugin.getDefault());
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return null;
    }

    @Override
    public WebWidgetFileStore createRepositoryFileStore(final String widgetFolderName) {
        return new WebWidgetFileStore(widgetFolderName, this);
    }

    @Override
    public WebWidgetFileStore getChild(final String widgetFolderName) {
        if (widgetFolderName != null) {
            final IFolder folder = getResource().getFolder(widgetFolderName);
            if (!folder.isSynchronized(IResource.DEPTH_INFINITE) && folder.isAccessible()) {
                try {
                    folder.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
            if (folder.exists()) {
                return createRepositoryFileStore(widgetFolderName);
            }
        }
        return null;
    }

    @Override
    public List<WebWidgetFileStore> getChildren() {
        refresh();

        final List<WebWidgetFileStore> result = new ArrayList<WebWidgetFileStore>();
        final IFolder folder = getResource();
        try {
            for (final IResource r : folder.members()) {
                if (!r.isHidden() && !r.getName().startsWith(".")) {
                    result.add(createRepositoryFileStore(r.getName()));
                }
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return result;
    }

    @Override
    public void migrate(final IProgressMonitor monitor) throws CoreException, MigrationException {
        //NOTHING TO MIGRATE
    }
}
