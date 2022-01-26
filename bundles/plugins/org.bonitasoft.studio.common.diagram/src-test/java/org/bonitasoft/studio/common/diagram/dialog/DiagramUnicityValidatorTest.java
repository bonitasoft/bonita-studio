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
package org.bonitasoft.studio.common.diagram.dialog;

import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;
import static org.bonitasoft.studio.model.process.builders.MainProcessBuilder.aMainProcess;

import java.util.Collections;

import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.Sets;

@RunWith(MockitoJUnitRunner.class)
public class DiagramUnicityValidatorTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_not_fail_for_unique_diagram_name_and_version() throws Exception {
        final DiagramUnicityValidator validator = new DiagramUnicityValidator(aMainProcess().withName("MyDiagram").withVersion("1.0").build(),
                new WritableValue("MyDiagram", String.class),
                new WritableValue("1.0", String.class),
                Collections.<String> emptySet());

        final IStatus status = validator.validate();

        assertThat(status).isOK();
    }

    @Test
    public void should_not_fail_for_diagram_name_and_version_that_has_not_changed() throws Exception {
        final DiagramUnicityValidator validator = new DiagramUnicityValidator(aMainProcess().withName("MyDiagram").withVersion("1.0").build(),
                new WritableValue("MyDiagram", String.class),
                new WritableValue("1.0", String.class),
                Sets.newHashSet("MyDiagram-1.0.proc"));

        final IStatus status = validator.validate();

        assertThat(status).isOK();
    }

    @Test
    public void should_fail_if_diagram_name_and_version_exists() throws Exception {
        final DiagramUnicityValidator validator = new DiagramUnicityValidator(aMainProcess().withName("MyDiagram").withVersion("1.0").build(),
                new WritableValue("MyNewDiagram", String.class),
                new WritableValue("1.0", String.class),
                Sets.newHashSet("MyNewDiagram-1.0.proc"));

        final IStatus status = validator.validate();

        assertThat(status).isNotOK();
    }

    @Test
    public void should_fail_if_diagram_name_and_version_exists_with_another_case() throws Exception {
        final DiagramUnicityValidator validator = new DiagramUnicityValidator(aMainProcess().withName("MyDiagram").withVersion("1.0").build(),
                new WritableValue("MyNewDiagram", String.class),
                new WritableValue("1.0", String.class),
                Sets.newHashSet("Mynewdiagram-1.0.proc"));

        final IStatus status = validator.validate();

        assertThat(status).isNotOK();
    }
}
