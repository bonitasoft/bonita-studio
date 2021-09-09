/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.migration.transformation;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.migration.ProcessModelTransformation;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.emf.ecore.EObject;

public class DiagramVersionTransformation implements ProcessModelTransformation {

    @Override
    public void transform(EObject modelObject) {
        MainProcess diagram = (MainProcess) modelObject;
        final String pVersion = diagram.getBonitaVersion();
        final String mVersion = diagram.getBonitaModelVersion();
        if (!ProductVersion.CURRENT_VERSION.equals(pVersion)) {
            diagram.setBonitaVersion(ProductVersion.CURRENT_VERSION);
        }
        if (!ModelVersion.CURRENT_DIAGRAM_VERSION.equals(mVersion)) {
            diagram.setBonitaModelVersion(ModelVersion.CURRENT_DIAGRAM_VERSION);
        }
        if (diagram.getAuthor() == null) {
            diagram.setAuthor(System.getProperty("user.name",
                    "Unknown"));
        }
    }

    @Override
    public boolean appliesTo(EObject modelObject) {
        return modelObject instanceof MainProcess;
    }

}
