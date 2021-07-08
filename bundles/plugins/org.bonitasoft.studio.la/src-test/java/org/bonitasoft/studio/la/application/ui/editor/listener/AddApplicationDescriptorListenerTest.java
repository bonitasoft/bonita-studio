package org.bonitasoft.studio.la.application.ui.editor.listener;

import static org.bonitasoft.engine.business.application.xml.ApplicationNodeBuilder.newApplicationContainer;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.editor.ApplicationFormPage;
import org.bonitasoft.studio.la.application.ui.editor.listener.AddApplicationDescriptorListener;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;
import org.mockito.Mockito;

public class AddApplicationDescriptorListenerTest {

    @Test
    public void should_add_an_application_descriptor_node() {
        final ApplicationFormPage applicationFormPage = mock(ApplicationFormPage.class);
        final IDocument document = mock(IDocument.class);
        final ApplicationNodeContainer applicationWorkingCopy = newApplicationContainer().create();
        when(applicationFormPage.getWorkingCopy()).thenReturn(applicationWorkingCopy);
        when(applicationFormPage.getDocument()).thenReturn(document);
        RepositoryAccessor repositoryAccesor = mock(RepositoryAccessor.class);
        ApplicationRepositoryStore store = mock(ApplicationRepositoryStore.class);
        when(repositoryAccesor.getRepositoryStore(ApplicationRepositoryStore.class)).thenReturn(store);
        when(store.getConverter()).thenReturn(new ApplicationNodeContainerConverter());

        final AddApplicationDescriptorListener addDescriptorListener = new AddApplicationDescriptorListener(
                mock(Shell.class), applicationFormPage, repositoryAccesor);

        final ApplicationNode appNode = new ApplicationNode();
        appNode.setToken("token");
        appNode.setDisplayName("name");
        appNode.setVersion("1.0.0");
        appNode.setState("ACTIVATED");

        addDescriptorListener.addApplicationNode(appNode);

        final ApplicationNode applicationNode = applicationWorkingCopy.getApplications().get(0);

        assertEquals(applicationNode.getToken(), "token");
        assertEquals(applicationNode.getDisplayName(), "name");
        assertEquals(applicationNode.getVersion(), "1.0.0");
        assertEquals(applicationNode.getState(), "ACTIVATED");

        verify(document).set(anyString());
        verify(applicationFormPage).recreateForm();
        verify(applicationFormPage).reflow();

    }
}
