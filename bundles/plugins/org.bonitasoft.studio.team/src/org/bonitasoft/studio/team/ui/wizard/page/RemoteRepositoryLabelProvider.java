/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.ui.wizard.page;

import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.ui.wizard.RemoteRepositoryFinder;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryResource;

public class RemoteRepositoryLabelProvider extends StyledCellLabelProvider implements ILabelProvider {

    private final RemoteRepositoryFinder remoteRepositoryFinder;

    public RemoteRepositoryLabelProvider(final RemoteRepositoryFinder remoteRepositoryFinder) {
        this.remoteRepositoryFinder = remoteRepositoryFinder;
    }

    @Override
    public String getText(final Object element) {
        if (element instanceof IRepositoryContainer) {
            final Map<IRepositoryContainer, String> bonitaRepositories = remoteRepositoryFinder.getBonitaRepositories();
            final List<IRepositoryResource> emptyContainers = remoteRepositoryFinder.getEmptyContainers();
            final List<IRepositoryResource> unkownContainers = remoteRepositoryFinder.getUnkownContainers();
            String name;
            final IRepositoryContainer container = (IRepositoryContainer) element;
            if (allRepositoriesContains(container)) {
                name = container.getName() + " (" + Messages.alreadyExists + ")";
            } else {
                name = container.getName();
            }
            if (bonitaRepositories.containsKey(container)) {
                name += " [" + bonitaRepositories.get(container) + "]";
            } else if (emptyContainers.contains(element)) {
                name += " [" + Messages.team_empty + "]";
            } else if (!unkownContainers.contains(element)) {
                name += " [<" + ProductVersion.CURRENT_VERSION + "]";
            }
            return name;
        }
        return null;
    }

    /**
     * @param container
     * @return
     */
    protected boolean allRepositoriesContains(final IRepositoryContainer container) {
        return RepositoryManager.getInstance().getAllRepositories().contains(container.getName());
    }

    @Override
    public void update(final ViewerCell cell) {
        final Map<IRepositoryContainer, String> bonitaRepositories = remoteRepositoryFinder.getBonitaRepositories();
        final List<IRepositoryResource> emptyContainers = remoteRepositoryFinder.getEmptyContainers();
        final List<IRepositoryResource> unkownContainers = remoteRepositoryFinder.getUnkownContainers();
        final IRepositoryContainer resource = (IRepositoryContainer) cell.getElement();
        Color foreground = null;
        final String text = getText(resource);
        final StyledString ss = new StyledString(text);

        if (unkownContainers.contains(resource)) {
            foreground = ColorConstants.gray;
        } else if (!emptyContainers.contains(resource) && !canBeMigrated(bonitaRepositories, resource)
                && !bonitaRepositories.get(resource).equals(ProductVersion.CURRENT_VERSION)) {
            foreground = ColorConstants.red;
        } else if (!emptyContainers.contains(resource) && canBeMigrated(bonitaRepositories, resource)) {
            setMigrationText(ss);
        }
        cell.setText(ss.toString());
        cell.setForeground(foreground);
        cell.setStyleRanges(ss.getStyleRanges());
    }

    /**
     * @param bonitaRepositories
     * @param resource
     * @return
     */
    protected boolean canBeMigrated(final Map<IRepositoryContainer, String> bonitaRepositories, final IRepositoryContainer resource) {
        return ProductVersion.canBeMigrated(bonitaRepositories.get(resource));
    }

    /**
     * @param ss
     */
    protected void setMigrationText(final StyledString ss) {
        ss.append(" -- ", StyledString.DECORATIONS_STYLER);
        ss.append(Messages.needTobeMigrated, StyledString.QUALIFIER_STYLER);
    }

    @Override
    public Image getImage(final Object element) {
        return null;
    }

}
