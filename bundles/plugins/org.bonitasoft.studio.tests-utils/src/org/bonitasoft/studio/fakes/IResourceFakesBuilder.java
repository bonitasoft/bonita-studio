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
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.function.Supplier;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class IResourceFakesBuilder<T extends IResource> {

    public static IResourceFakesBuilder<IFile> anIFile() {
        final IFile mock =mock(IFile.class);
        Mockito.lenient().when(mock.getType()).thenReturn(IResource.FILE);
        return new IResourceFakesBuilder<>(mock);
    }

    public static IResourceFakesBuilder<IFolder> anIFolder() {
        final IFolder mock = mock(IFolder.class);
        Mockito.lenient().when(mock.getType()).thenReturn(IResource.FOLDER);
        return new IResourceFakesBuilder<>(mock);
    }

    private final T resourceFake;

    private IResourceFakesBuilder(final T resourceFake) {
        this.resourceFake = resourceFake;
    }

    public IResourceFakesBuilder<T> withName(final String resourceName) {
        doReturn(resourceName).when(resourceFake).getName();
        return this;
    }

    public IResourceFakesBuilder<T> exists() {
        doReturn(true).when(resourceFake).exists();
        return this;
    }

    public T build() {
        return resourceFake;
    }

    public IResourceFakesBuilder<T> withContentSupplier(Supplier<InputStream> inputStreamSupplier) {
        if (!(resourceFake instanceof IFile)) {
            throw new IllegalAccessError("Only IFile can have a content..");
        }
        try {
            Mockito.when(((IFile) resourceFake).getContents()).thenAnswer(new Answer<InputStream>() {

                @Override
                public InputStream answer(InvocationOnMock invocation) throws Throwable {
                    return inputStreamSupplier.get();
                }
            });
        } catch (final CoreException e) {
            return this;
        }
        return  this;
    }

}
