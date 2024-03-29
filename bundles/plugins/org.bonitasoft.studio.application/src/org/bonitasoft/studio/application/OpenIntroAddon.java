/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.application;

import java.util.Objects;

import jakarta.annotation.PostConstruct;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.ui.PlatformUI;
import org.osgi.service.event.Event;

/**
 * @author Romain Bioteau
 */
public class OpenIntroAddon {
    
    private static final String INTROVIEW_ID = "org.eclipse.ui.internal.introview";
    private RepositoryAccessor repositoryAccessor;

    @PostConstruct
    public void pc(IEventBroker eventBroker, RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        eventBroker.subscribe(UIEvents.UIElement.TOPIC_TOBERENDERED, this::handleEvent);
    }

    private void handleEvent(Event event) {
        Object part = event.getProperty(UIEvents.EventTags.ELEMENT);
        if (part instanceof MPart) {
            if (((MPart) part).getElementId().equals("org.eclipse.e4.ui.compatibility.editor")) {
                var currentRepository = repositoryAccessor.getCurrentRepository();
                if(!PlatformUtil.isOpeningIntro() 
                        && currentRepository.isPresent() 
                        && currentRepository.orElseThrow().isOpenIntroListenerEnabled()) {
                    PlatformUtil.openDashboardIfNoOtherEditorOpen();
                }
            }
        } else if(part instanceof MPlaceholder && Objects.equals(INTROVIEW_ID, ((MPlaceholder) part).getElementId())) {
            var introView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(INTROVIEW_ID);
            if(introView == null) {
                PlatformUtil.openDashboardIfNoOtherEditorOpen();
            }
        }
    }

}
