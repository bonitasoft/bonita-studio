/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.perspectives;

import java.util.Objects;

import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * Must extends this class to enable the automatic switch between perspectives depending of the editor type
 *
 * @author Baptiste Mesta
 * @author Aurelien Pupier
 */
public abstract class AbstractPerspectiveFactory implements IPerspectiveFactory {

    private static final String CUSTOM_PERMISSIONS_MAPPING_FILE = "custom-permissions-mapping.properties";

    /**
     * returns true if the perspective must be activated when opening the editor part given in param
     *
     * @param part
     *        the editor part that is brought to top
     * @return
     *         true if perspective must be activated, false otherwise
     */
    public abstract boolean isRelevantFor(IEditorPart part);

    /**
     * @return the id of the perspective
     */
    public abstract String getID();

    protected float getExplorerViewRatio() {
        return 0.18f;
    }

    protected boolean isInsideprojectWithREStApiExtensionNature(final IEditorPart part) {
        final IEditorInput editorInput = part.getEditorInput();
        IFile file = editorInput.getAdapter(IFile.class);
        if (file != null) {
            try {
                return file.getProject().hasNature(RestAPIExtensionNature.NATURE_ID)
                        || Objects.equals(file.getName(), CUSTOM_PERMISSIONS_MAPPING_FILE);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return false;
    }

}
