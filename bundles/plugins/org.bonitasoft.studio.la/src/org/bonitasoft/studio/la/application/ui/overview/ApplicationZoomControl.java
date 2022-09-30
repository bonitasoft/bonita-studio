/**
 * Copyright (C) 2021 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.la.application.ui.overview;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.bonitasoft.studio.application.views.overview.AbstractOverviewZoomControl;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.extension.OverviewContribution;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;

public class ApplicationZoomControl extends AbstractOverviewZoomControl<ApplicationFileStore> {

    protected ApplicationZoomControl(Composite parent, ZoomListener zoomListener, Listener computeScrollListener,
            OverviewContribution contribution) {
        super(parent, zoomListener, computeScrollListener, contribution);
    }

    @Override
    protected String getHint() {
        return Messages.applicationZoomHint;
    }

    @Override
    protected String getElementName() {
        return Messages.application.toLowerCase();
    }

    @Override
    protected String getNewCommand() {
        return ApplicationOverviewContribution.NEW_APPLICATION_COMMAND;
    }

    @Override
    protected List<ApplicationFileStore> getFileStores() {
        return RepositoryManager.getInstance().getRepositoryStore(ApplicationRepositoryStore.class).getChildren();
    }

    @Override
    protected List<Element> retrieveFileStoreContent(ApplicationFileStore fileStore) {
        try {
            return fileStore.getContent().getApplications().stream()
                    .map(app -> new Element(app.getDisplayName(), app.getDescription()))
                    .collect(Collectors.toList());
        } catch (ReadFileStoreException e) {
            errorHandler.openErrorDialog(getShell(), e.getMessage(), e);
            return Collections.emptyList();
        }
    }

}
