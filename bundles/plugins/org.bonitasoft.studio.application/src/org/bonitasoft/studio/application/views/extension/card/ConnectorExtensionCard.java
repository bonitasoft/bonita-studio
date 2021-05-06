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
package org.bonitasoft.studio.application.views.extension.card;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.views.extension.card.zoom.ConnectorZoomControl;
import org.bonitasoft.studio.application.views.extension.card.zoom.IZoomable;
import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ConnectorExtensionCard extends ExtensionCard implements IZoomable {

    private ZoomListener zoomListener;

    public ConnectorExtensionCard(Composite parent,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, dep, bonitaDep);
    }

    @Override
    public Control createZoomedControl(Composite parent) {
        return new ConnectorZoomControl(parent, zoomListener, dep, bonitaDep);
    }

    @Override
    protected void createTitleComposite(Composite parent) {
        super.createTitleComposite(parent);
        addZoomBehavior(titleLabel);
    }

    @Override
    public void addZoomListener(ZoomListener listener) {
        this.zoomListener = listener;
    }

    @Override
    public ZoomListener getZoomListener() {
        return zoomListener;
    }

}
