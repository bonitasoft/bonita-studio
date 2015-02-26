/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.diagram.form.custom.providers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.diagram.form.custom.part.CustomFormDiagramEditor;
import org.bonitasoft.studio.importer.builder.ProcBuilderException;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DiagramEditorProviderTest {

    private URI globalUri;

    @Mock
    private FileEditorInput fileEditorInput;

    @Before
    public void setup() throws URISyntaxException {
        initEMFRegistryForStandaloneMode();
        final URL resource = DiagramEditorProviderTest.class.getResource("/DiagramWithPoolWithInstanciationForm-1.0.proc");

        globalUri = URI.createURI(resource.toURI().toString());
    }

    protected void initEMFRegistryForStandaloneMode() {
        ProcessPackage.eINSTANCE.eClass();
        FormPackage.eINSTANCE.eClass();
        NotationPackage.eINSTANCE.eClass();
        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put("proc", new GMFResourceFactory());
    }

    @Test
    public void testGetEditorIdProcessWithURIEditorInput() throws IOException, ProcBuilderException {
        final DiagramEditorProvider provider = new DiagramEditorProvider();

        final URI uri = globalUri.appendFragment("_HzivLFtPEeS_U_Le4RccVQ");
        final IEditorInput editorInput = new URIEditorInput(uri);
        Assertions.assertThat(provider.getEditorId(editorInput)).isEqualTo(ProcessDiagramEditor.ID);
    }

    @Test
    public void testGetEditorIdProcessWithFileEditorInput() throws IOException, ProcBuilderException, URISyntaxException {
        final DiagramEditorProvider provider = new DiagramEditorProvider();

        final URL resource = DiagramEditorProviderTest.class.getResource("/DiagramWithPoolWithInstanciationForm-1.0.proc");

        Mockito.when(fileEditorInput.getURI()).thenReturn(resource.toURI());

        Assertions.assertThat(provider.getEditorId(fileEditorInput)).isEqualTo(ProcessDiagramEditor.ID);
    }

    @Test
    public void testGetEditorIdFormWithURIEditorInput() throws IOException, ProcBuilderException {
        final DiagramEditorProvider provider = new DiagramEditorProvider();

        final URI uri = globalUri.appendFragment("_Pi_t8FtPEeS_U_Le4RccVQ");
        final IEditorInput editorInput = new URIEditorInput(uri);
        Assertions.assertThat(provider.getEditorId(editorInput)).isEqualTo(CustomFormDiagramEditor.ID);
    }

}
