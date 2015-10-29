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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPageLayout;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AbstractPerspectiveFactoryTest {

    @Mock
    private IEditorPart part;
    private IFileEditorInput editorInput;
    private AbstractPerspectiveFactory factory;

    @Before
    public void setUp() throws Exception {
        editorInput = mock(IFileEditorInput.class, RETURNS_DEEP_STUBS);
        doReturn(editorInput).when(part).getEditorInput();
        factory = new AbstractPerspectiveFactory() {

            @Override
            public void createInitialLayout(final IPageLayout layout) {
            }

            @Override
            public boolean isRelevantFor(final IEditorPart part) {
                return false;
            }

            @Override
            public String getID() {
                return null;
            }
        };
    }

    @Test
    public void test_isInRestAPiExtensionProject() throws Exception {
        when(editorInput.getFile().getProject().hasNature(RestAPIExtensionNature.NATURE_ID)).thenReturn(true);
        assertThat(factory.isInsideprojectWithREStApiExtensionNature(part)).isTrue();
    }

    @Test
    public void test_isNotInRestAPiExtensionProject() throws Exception {
        when(editorInput.getFile().getProject().hasNature(RestAPIExtensionNature.NATURE_ID)).thenReturn(false);
        assertThat(factory.isInsideprojectWithREStApiExtensionNature(part)).isFalse();
    }

    @Test
    public void test_isNotInRestAPiExtensionProject_whenNotAFileEditor() throws Exception {
        doReturn(mock(IEditorInput.class)).when(part).getEditorInput();
        assertThat(factory.isInsideprojectWithREStApiExtensionNature(part)).isFalse();
    }

    @Test
    public void test_isNotInRestAPiExtensionProject_whenExceptionRetrievingProject() throws Exception {
        when(editorInput.getFile().getProject().hasNature(RestAPIExtensionNature.NATURE_ID)).thenThrow(mock(CoreException.class));
        assertThat(factory.isInsideprojectWithREStApiExtensionNature(part)).isFalse();
    }
}
