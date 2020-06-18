/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.bos;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.configuration.builders.ConfigurationBuilder.aConfiguration;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentFileStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetFileStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class WebFormBOSArchiveFileStoreProviderTest {

    private WebFormBOSArchiveFileStoreProvider webFormArtifactsFileStoreProvider;

    @Mock
    private WebPageFileStore processFormFileStore;

    @Mock
    private WebPageFileStore taskFormFileStore;

    @Mock
    private WebWidgetFileStore customWidgetFileStore;

    private RepositoryAccessor repositoryAccessor;

    @Mock
    private WebFragmentFileStore fragmentFileStore;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        repositoryAccessor = mock(RepositoryAccessor.class, RETURNS_DEEP_STUBS);
        final WebPageRepositoryStore formRepositoryStore = mock(WebPageRepositoryStore.class);
        doReturn(formRepositoryStore).when(repositoryAccessor).getRepositoryStore(WebPageRepositoryStore.class);
        when(formRepositoryStore.getChild("process-form-id", true)).thenReturn(processFormFileStore);
        when(formRepositoryStore.getChild("step-form-id", true)).thenReturn(taskFormFileStore);

        final WebFragmentRepositoryStore fragmentRepositoryStore = mock(WebFragmentRepositoryStore.class);
        doReturn(fragmentRepositoryStore).when(repositoryAccessor).getRepositoryStore(WebFragmentRepositoryStore.class);
        when(fragmentRepositoryStore.getChild("fragmentDep", true)).thenReturn(fragmentFileStore);

        final WebWidgetRepositoryStore widgetRepositoryStore = mock(WebWidgetRepositoryStore.class);
        doReturn(widgetRepositoryStore).when(repositoryAccessor).getRepositoryStore(WebWidgetRepositoryStore.class);
        when(widgetRepositoryStore.getChild("customTestWidget", true)).thenReturn(customWidgetFileStore);
        when(customWidgetFileStore.canBeExported()).thenReturn(true);

        webFormArtifactsFileStoreProvider = spy(new WebFormBOSArchiveFileStoreProvider(repositoryAccessor, null));
        doReturn(newHashSet("resources/widgets/customTestWidget/customTestWidget.json")).when(webFormArtifactsFileStoreProvider)
        .findFormRelatedEntries(processFormFileStore);
        doReturn(newHashSet("resources/fragments/fragmentDep/fragmentDep.json")).when(webFormArtifactsFileStoreProvider)
        .findFormRelatedEntries(taskFormFileStore);

    }

    @Test
    public void should_contains_web_form_fileStore_according_to_process_form_mapping() throws Exception {
        final Set<IRepositoryFileStore<?>> fileStores = webFormArtifactsFileStoreProvider.getFileStoreForConfiguration(aProcessWithFormMappings(),
                aConfiguration().build());

        assertThat(fileStores).contains(processFormFileStore, taskFormFileStore, customWidgetFileStore, fragmentFileStore);
    }

    @Test
    public void should_not_contains_fileStore_if_file_does_not_exists() throws Exception {
        final Set<IRepositoryFileStore<?>> fileStores = webFormArtifactsFileStoreProvider.getFileStoreForConfiguration(aProcessWithInvalidFormMappings(),
                aConfiguration().build());

        assertThat(fileStores).isEmpty();
    }

    private AbstractProcess aProcessWithFormMappings() {
        return aPool().withName("Pool1").withVersion("1.0")
                .havingOverviewFormMapping(aFormMapping().withType(FormMappingType.URL).withURL("http://www.bonitasoft.com"))
                .havingFormMapping(aFormMapping().havingTargetForm(anExpression().withName("processForm").withContent("process-form-id")))
                .havingElements(
                        aTask().withName("Step1").havingFormMapping(
                                aFormMapping().havingTargetForm(anExpression().withName("StepForm").withContent("step-form-id"))))
                .build();
    }

    private AbstractProcess aProcessWithInvalidFormMappings() {
        return aPool().withName("Pool1").withVersion("1.0")
                .havingFormMapping(aFormMapping().havingTargetForm(anExpression().withName("processForm").withContent("invalid-process-form-id")))
                .build();
    }

}
