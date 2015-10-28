/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.perspectives;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.part.EditorPart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PerspectiveJavaFactoryTest {

    private IFileEditorInput editorInput;
    private AbstractPerspectiveFactory factory;

    @Before
    public void setUp() throws Exception {
        editorInput = mock(IFileEditorInput.class, RETURNS_DEEP_STUBS);
        factory = new PerspectiveJavaFactory();
    }

    @Test
    public void test_isRelevantFor_JavaEditor() throws Exception {
        final IEditorPart part = initMock(JavaEditor.class, false);
        assertThat(factory.isRelevantFor(part)).isTrue();
    }

    @Test
    public void test_isRelevantFor_JavaEditorInRestApiExtension() throws Exception {
        final IEditorPart part = initMock(JavaEditor.class, true);
        assertThat(factory.isRelevantFor(part)).isFalse();
    }

    @Test
    public void test_isNotRelevantFor_lambdaEditor() throws Exception {
        final IEditorPart part = initMock(EditorPart.class, false);
        assertThat(factory.isRelevantFor(part)).isFalse();
    }

    protected IEditorPart initMock(final Class<? extends IEditorPart> classToMock, final boolean hasNatureRestAPIExtension) throws CoreException {
        final IEditorPart part = mock(classToMock);
        doReturn(editorInput).when(part).getEditorInput();
        when(editorInput.getFile().getProject().hasNature(RestAPIExtensionNature.NATURE_ID)).thenReturn(hasNatureRestAPIExtension);
        return part;
    }
}
