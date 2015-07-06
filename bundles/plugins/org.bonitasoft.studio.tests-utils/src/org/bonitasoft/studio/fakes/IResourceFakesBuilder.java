/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.fakes;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;

public class IResourceFakesBuilder {

    public static IResourceFakesBuilder anIFile() {
        return new IResourceFakesBuilder(mock(IFile.class));
    }

    public static IResourceFakesBuilder anIFolder() {
        return new IResourceFakesBuilder(mock(IFolder.class));
    }

    private final IResource resourceFake;

    private IResourceFakesBuilder(final IResource resourceFake) {
        this.resourceFake = resourceFake;
    }

    public IResourceFakesBuilder withName(final String resourceName) {
        doReturn(resourceName).when(resourceFake).getName();
        return this;
    }

    public IResourceFakesBuilder exists() {
        doReturn(true).when(resourceFake).exists();
        return this;
    }

    public IResource build() {
        return resourceFake;
    }

}
