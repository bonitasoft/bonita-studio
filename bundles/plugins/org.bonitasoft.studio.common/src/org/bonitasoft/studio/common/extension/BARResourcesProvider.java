/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.extension;

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.runtime.IStatus;

public interface BARResourcesProvider {

    public static final String STUDIO_BAR_PATH = "studio/";
    public static final String FORMS_FOLDER_IN_BAR = "forms/";
    public static final String FORMS_DEPENDENCIES_IN_BAR = "lib/";

    /**
     * @param builder
     * @param process
     * @param configuration
     * @return a status
     * @throws Exception
     */
    IStatus addResourcesForConfiguration(BusinessArchiveBuilder builder, AbstractProcess process,
            Configuration configuration)
            throws Exception;

}
