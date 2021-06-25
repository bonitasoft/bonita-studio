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
package org.bonitasoft.studio.application.views.dashboard;

import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.preferences.BonitaThemeConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

public class DiagramZoomControl extends AbstractDashboardZoomControl<DiagramFileStore> {

    protected DiagramZoomControl(Composite parent, ZoomListener zoomListener, DiagramDashboardContribution contribution) {
        super(parent, zoomListener, contribution);
    }

    @Override
    protected String getHint() {
        return Messages.diagramZoomHint;
    }

    @Override
    protected String getElementName() {
        return org.bonitasoft.studio.diagram.custom.i18n.Messages.dashboardDiagramName.toLowerCase();
    }

    @Override
    protected String getNewCommand() {
        return DiagramDashboardContribution.NEW_DIAGRAM_COMMAND;
    }

    @Override
    protected List<DiagramFileStore> getFileStores() {
        return RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class).getChildren();
    }

    @Override
    protected List<AbstractDashboardZoomControl<DiagramFileStore>.Element> retrieveFileStoreContent(
            DiagramFileStore fileStore) {
        return fileStore.getProcesses(false).stream()
                .map(process -> new Element(String.format("%s (%s)", process.getName(), process.getVersion()),
                        process.getDocumentation()))
                .collect(Collectors.toList());
    }

    @Override
    protected void createFileStoreTitleLabel(DiagramFileStore fileStore, Composite titleComposite) {
        try {
            var mainProcess = fileStore.getContent();
            var titleLabel = new CLabel(titleComposite, SWT.NONE);
            titleLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
            titleLabel.setText(String.format("%s (%s)", mainProcess.getName(), mainProcess.getVersion()));
            titleLabel.setFont(JFaceResources.getFont(ProjectDashboardEditorPart.BOLD_4_FONT_ID));
            titleLabel.setData(BonitaThemeConstants.CSS_ID_PROPERTY_NAME, BonitaThemeConstants.TITLE_TEXT_COLOR);
        } catch (ReadFileStoreException e) {
            errorHandler.openErrorDialog(getShell(), e.getMessage(), e);
            super.createFileStoreTitleLabel(fileStore, titleComposite);
        }
    }

}
