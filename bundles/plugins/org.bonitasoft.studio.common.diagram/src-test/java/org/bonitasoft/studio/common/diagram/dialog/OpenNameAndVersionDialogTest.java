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

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OpenNameAndVersionDialogTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();
    @Mock
    private IRepositoryStore<?> diagramStore;

    @Test
    public void should_diagram_name_strategy_validate_fileName() throws Exception {
        final OpenNameAndVersionDialog dialog = new OpenNameAndVersionDialog(realmWithDisplay.getShell(),
                aMainProcess().withName("MyDiagram")
                        .withVersion("1.0").build(),
                diagramStore);

        final UpdateValueStrategy nameStrategy = dialog.diagramUpdateStrategy("");

        assertThat(nameStrategy.validateAfterGet("My\\Diagram")).isNotOK();
    }

    @Test
    public void should_diagram_name_strategy_validate_empty_name() throws Exception {
        final OpenNameAndVersionDialog dialog = new OpenNameAndVersionDialog(realmWithDisplay.getShell(),
                aMainProcess().withName("MyDiagram")
                        .withVersion("1.0").build(),
                diagramStore);

        final UpdateValueStrategy nameStrategy = dialog.diagramUpdateStrategy("");

        assertThat(nameStrategy.validateAfterGet("")).isNotOK();
    }

    @Test
    public void should_diagram_name_strategy_validate_max_length_name() throws Exception {
        final OpenNameAndVersionDialog dialog = new OpenNameAndVersionDialog(realmWithDisplay.getShell(),
                aMainProcess().withName("MyDiagram")
                        .withVersion("1.0").build(),
                diagramStore);

        final UpdateValueStrategy nameStrategy = dialog.diagramUpdateStrategy("");

        assertThat(nameStrategy.validateAfterGet(
                "tooooooooooooooooooooooooooooo00000000000000000000000000000000000000000000000000000000ooooooooooooooooooLong"))
                        .isNotOK();
    }

}
