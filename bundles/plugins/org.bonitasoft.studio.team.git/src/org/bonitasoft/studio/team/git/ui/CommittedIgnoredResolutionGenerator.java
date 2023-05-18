/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.team.git.ui;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.team.git.validation.RepositoryValidator;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Auto-resolution for {@link RepositoryValidator#GIT_COMMITTED_IGNORED_MARKER_TYPE} error markers
 * 
 * @author Vincent Hemery
 */
public class CommittedIgnoredResolutionGenerator implements IMarkerResolutionGenerator {

    @Override
    public IMarkerResolution[] getResolutions(IMarker marker) {
        return new IMarkerResolution[] {
                new IMarkerResolution2() {

                    @Override
                    public void run(IMarker marker) {
                        try {
                            marker.getResource().delete(true, new NullProgressMonitor());
                        } catch (CoreException e) {
                            BonitaStudioLog.error(e);
                        }
                    }

                    @Override
                    public String getLabel() {
                        return Messages.committedIgnoredResolutionLabel;
                    }

                    @Override
                    public String getDescription() {
                        return Messages.committedIgnoredResolutionDescription;
                    }

                    @Override
                    public Image getImage() {
                        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_DELETE);
                    }

                }
        };
    }

}
