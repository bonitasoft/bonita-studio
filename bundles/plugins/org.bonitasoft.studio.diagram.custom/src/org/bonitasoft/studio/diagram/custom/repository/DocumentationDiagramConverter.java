/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.diagram.custom.repository;

import java.util.function.Function;

import org.bonitasoft.asciidoc.templating.model.process.Diagram;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.resources.IFolder;
import org.eclipse.swt.widgets.Display;


public class DocumentationDiagramConverter implements Function<MainProcess, Diagram> {
    
    
    private IFolder targetFolder;

    public DocumentationDiagramConverter(IFolder targetFolder) {
        this.targetFolder = targetFolder;
    }

    @Override
    public Diagram apply(MainProcess mainProcess) {
        GenerateDiagramImagesJob generateDiagramImagesJob = new GenerateDiagramImagesJob(Display.getDefault(), mainProcess, targetFolder);
        generateDiagramImagesJob.setUser(false);
        generateDiagramImagesJob.schedule();
        return Diagram.builder()
            .name(mainProcess.getName())
            .version(mainProcess.getVersion())
            .description(mainProcess.getDocumentation())
            .build();
    }

}
