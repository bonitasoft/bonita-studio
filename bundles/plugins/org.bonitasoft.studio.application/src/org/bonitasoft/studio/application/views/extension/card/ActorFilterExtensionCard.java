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
import org.bonitasoft.studio.application.views.extension.card.zoom.ActorFilterZoomControl;
import org.bonitasoft.studio.application.views.extension.card.zoom.ZoomListener;
import org.bonitasoft.studio.application.views.extension.card.zoom.Zoomable;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

public class ActorFilterExtensionCard extends ExtensionCard implements Zoomable {

    private ZoomListener zoomListener;

    public ActorFilterExtensionCard(Composite parent,
            Dependency dep,
            BonitaArtifactDependency bonitaDep) {
        super(parent, dep, bonitaDep);
    }

    @Override
    public Control createZoomedControl(Composite parent) {
        return new ActorFilterZoomControl(parent, zoomListener, dep, bonitaDep);
    }

    @Override
    protected void createTitleComposite(Composite parent, String backgroundCssClassname) {
        super.createTitleComposite(parent, backgroundCssClassname);
        addZoomBehavior(titleLabel);
    }

    @Override
    public void addZoomListener(ZoomListener listener) {
        this.zoomListener = listener;
    }
    
    @Override
    public String getTextClassName() {
        return super.getTextClassName();
    }

    @Override
    public ZoomListener getZoomListener() {
        return zoomListener;
    }

    @Override
    protected boolean canEditMavenCoordinates(BonitaArtifactDependency bonitaDep) {
        return false;
    }

}
