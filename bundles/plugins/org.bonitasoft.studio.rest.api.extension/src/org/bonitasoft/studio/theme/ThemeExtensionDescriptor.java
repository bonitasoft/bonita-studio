/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.theme;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.maven.CustomPageMavenProjectDescriptor;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;

public class ThemeExtensionDescriptor extends CustomPageMavenProjectDescriptor {

    private static final String MAIN_SCSS_PATH = "src/scss/main.scss";
    private static final String BONITA_VARIABLES_SCSS_PATH = "src/scss/_bonita_variables.scss";;

    public ThemeExtensionDescriptor(IProject project) {
        super(project);
    }

    @Override
    protected String getPagePropertyPath() {
        return "page.properties";
    }

    @Override
    public List<IFile> getFilesToOpen() {
        ensureProjectOpen();
        return Stream.of(project.getFile(Path.fromOSString(MAIN_SCSS_PATH)),
                project.getFile(Path.fromOSString(BONITA_VARIABLES_SCSS_PATH))).collect(Collectors.toList());
    }

}
