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
package org.bonitasoft.studio.form.preview;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.eclipse.ui.internal.browser.IBrowserDescriptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AbstractFormPreviewInitializationTest {

    AbstractFormPreviewInitialization formPreviewInit;

    @Mock
    private ApplicationLookNFeelFileStore lookNFeel;

    @Mock
    private IBrowserDescriptor browser;

    private Form form;

    @Before
    public void setUp() {
        form = FormFactory.eINSTANCE.createForm();
        formPreviewInit = spy(new FormPreviewInitialization(form, lookNFeel, browser));

    }

    @Test
    public void shouldInitializeActorMappingWhenFormIsOnPool() {
        final AbstractProcess process = ProcessFactory.eINSTANCE.createPool();
        process.getForm().add(form);
        final Actor actor = ProcessFactory.eINSTANCE.createActor();
        process.getActors().add(actor);
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        doNothing().when(formPreviewInit).setActorMapping(process, configuration);
        formPreviewInit.createAbstractProcess(configuration);
        verify(formPreviewInit).setActorMapping(process, configuration);
    }

    @Test
    public void shouldInitializeActorMappingWhenFormIsOnTask() {
        final AbstractProcess process = ProcessFactory.eINSTANCE.createPool();
        final Actor actor = ProcessFactory.eINSTANCE.createActor();
        process.getActors().add(actor);
        final Task task = ProcessFactory.eINSTANCE.createTask();
        process.getElements().add(task);
        task.getForm().add(form);
        task.setActor(actor);
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        doNothing().when(formPreviewInit).setActorMapping(process, configuration);
        formPreviewInit.createAbstractProcess(configuration);
        verify(formPreviewInit).setActorMapping(process, configuration);
    }

    @Test
    public void shouldOpenErrorMessageWhenNoActorIsDefinedAndFormIsOnPool() {
        final AbstractProcess process = ProcessFactory.eINSTANCE.createPool();
        process.getForm().add(form);
        doNothing().when(formPreviewInit).openNoActorErrorMessage(process);
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        formPreviewInit.createAbstractProcess(configuration);
        verify(formPreviewInit).openNoActorErrorMessage(process);
    }

    @Test
    public void shouldOpenErrorMessageWhenNoActorIsDefinedAndFormIsOnTask() {
        final AbstractProcess process = ProcessFactory.eINSTANCE.createPool();
        final Task task = ProcessFactory.eINSTANCE.createTask();
        process.getElements().add(task);
        task.getForm().add(form);
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        doNothing().when(formPreviewInit).openNoActorErrorMessage(any(Element.class));
        formPreviewInit.createAbstractProcess(configuration);
        verify(formPreviewInit).openNoActorErrorMessage(any(Element.class));
    }

    @Test
    public void should_process_have_a_legacy_form_mapping() {
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        doNothing().when(formPreviewInit).openNoActorErrorMessage(any(Element.class));
        doNothing().when(formPreviewInit).initializeProcessActor(any(Configuration.class), any(AbstractProcess.class));
        final AbstractProcess process = formPreviewInit.createAbstractProcess(configuration);
        assertThat(process.getFormMapping().getType()).isEqualTo(FormMappingType.LEGACY);
    }

    @Test
    public void should_task_have_a_legacy_form_mapping() {
        final Configuration configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        doNothing().when(formPreviewInit).openNoActorErrorMessage(any(Element.class));
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final Task task = formPreviewInit.initializeTask(aTask().in(pool).build(), pool, configuration);
        assertThat(task.getFormMapping().getType()).isEqualTo(FormMappingType.LEGACY);
    }
}
