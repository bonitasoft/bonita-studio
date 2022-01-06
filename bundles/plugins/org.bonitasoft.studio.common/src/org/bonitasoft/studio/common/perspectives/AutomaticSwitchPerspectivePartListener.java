/**
 * Copyright (C) 2010-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.perspectives;

import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.IPartListener;
import org.eclipse.ui.internal.e4.compatibility.CompatibilityEditor;

public final class AutomaticSwitchPerspectivePartListener implements IPartListener {

    private boolean isSwitching;

    @Override
    public void partActivated(final MPart part) {
        switchPerspective(part);
    }

    @Override
    public void partBroughtToTop(final MPart part) {

    }

    @Override
    public void partDeactivated(final MPart part) {

    }

    @Override
    public void partHidden(final MPart part) {

    }

    @Override
    public void partVisible(final MPart part) {
        switchPerspective(part);
    }

    protected void switchPerspective(final MPart part) {
        if (!isSwitching) {
            isSwitching = true;
            try {
                final String activePerspective = getActivePerspectiveId(part);
                if (part != null && "org.eclipse.e4.ui.compatibility.editor".equals(part.getElementId())) {
                    final CompatibilityEditor compatibilityEditor = (CompatibilityEditor) part.getObject();
                    if (compatibilityEditor != null && activePerspective != null) {
                        final String id = BonitaPerspectivesUtils.getPerspectiveId(compatibilityEditor.getEditor());
                        if (id != null && !id.equals(activePerspective)) {
                            BonitaPerspectivesUtils.switchToPerspective(id);
                        }
                    }
                }
            } finally {
                isSwitching = false;
            }
        }
    }

    protected String getActivePerspectiveId(final MPart part) {
        if (part != null && part.getContext() != null) {
            final EModelService service = part.getContext().get(EModelService.class);
            final MWindow window = service.getTopLevelWindowFor(part);
            String activePerspective = null;
            final MPerspective selectedElement = service.getActivePerspective(window);
            if (selectedElement != null) {
                activePerspective = selectedElement.getElementId();
            }
            return activePerspective;
        }
        return null;
    }
}
