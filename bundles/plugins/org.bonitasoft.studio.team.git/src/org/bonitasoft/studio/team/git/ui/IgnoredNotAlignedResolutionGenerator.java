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

import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.team.GitProject;
import org.bonitasoft.studio.team.git.i18n.Messages;
import org.bonitasoft.studio.team.git.validation.RepositoryValidator;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Auto-resolution for {@link RepositoryValidator#GIT_IGNORE_NOT_ALIGNED_MARKER_TYPE} error markers
 * 
 * @author Vincent Hemery
 */
public class IgnoredNotAlignedResolutionGenerator implements IMarkerResolutionGenerator {

    @Override
    public IMarkerResolution[] getResolutions(IMarker marker) {
        return new IMarkerResolution[] {
                new IMarkerResolution2() {

                    @Override
                    public void run(IMarker marker) {
                        IProject project = marker.getResource().getProject();
                        // have .gitignore file corresponding to template
                        try {
                            var gitIgnore = project.getFile(Constants.GITIGNORE_FILENAME);
                            URL gitIgnoreTemplateUrl = GitProject.getGitignoreTemplateFileURL();
                            try (var templateStream = gitIgnoreTemplateUrl.openStream()) {
                                if (!gitIgnore.exists()) {
                                    // create new gile from template
                                    gitIgnore.create(templateStream, true, new NullProgressMonitor());
                                } else {
                                    // just restore it from template
                                    gitIgnore.setContents(templateStream, true, true, new NullProgressMonitor());
                                }
                            }
                        } catch (IOException | CoreException e) {
                            BonitaStudioLog.error(e);
                        }
                    }

                    @Override
                    public String getLabel() {
                        return Messages.ignoredNotAlignedResolutionLabel;
                    }

                    @Override
                    public String getDescription() {
                        return Messages.ignoredNotAlignedResolutionDescription;
                    }

                    @Override
                    public Image getImage() {
                        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_BACK);
                    }
                }
        };
    }

}
