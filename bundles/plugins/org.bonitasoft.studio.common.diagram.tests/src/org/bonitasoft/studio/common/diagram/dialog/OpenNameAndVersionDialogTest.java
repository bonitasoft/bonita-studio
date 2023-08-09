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
import static org.bonitasoft.bpm.model.process.builders.MainProcessBuilder.aMainProcess;
import static org.bonitasoft.bpm.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/*
 * org.bonitasoft.studio.validation has been added to dependencies to ensure
 * correct validation rules are registered...
 */
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

        final UpdateValueStrategy nameStrategy = dialog.nameUpdateStrategy();

        assertThat(nameStrategy.validateAfterGet("My\\Diagram")).isNotOK();
        assertThat(nameStrategy.validateAfterGet("My*Diagram")).isNotOK();
        assertThat(nameStrategy.validateAfterGet("MyDiagram")).isOK();
    }

    @Test
    public void should_diagram_version_strategy_validate_fileName() throws Exception {
        final OpenNameAndVersionDialog dialog = new OpenNameAndVersionDialog(realmWithDisplay.getShell(),
                aMainProcess().withName("MyDiagram")
                        .withVersion("1.0").build(),
                diagramStore);

        final UpdateValueStrategy versionStrategy = dialog.versionUpdateStrategy();

        assertThat(versionStrategy.validateAfterGet("1.0/2")).isNotOK();
        assertThat(versionStrategy.validateAfterGet("1.0b")).isOK();
    }

    @Test
    public void should_pool_name_strategy_validate_fileName() throws Exception {
        final OpenNameAndVersionDialog dialog = new OpenNameAndVersionDialog(realmWithDisplay.getShell(),
                aPool().withName("MyPool")
                        .withVersion("1.0").build(),
                diagramStore);

        final UpdateValueStrategy nameStrategy = dialog.nameUpdateStrategy();
        final UpdateValueStrategy versionStrategy = dialog.versionUpdateStrategy();

        assertThat(nameStrategy.validateAfterGet("My\\Pool")).isNotOK();
        assertThat(nameStrategy.validateAfterGet("My*Pool")).isNotOK();
        assertThat(nameStrategy.validateAfterGet("MyPool")).isOK();
    }

    @Test
    public void should_pool_version_strategy_validate_fileName() throws Exception {
        final OpenNameAndVersionDialog dialog = new OpenNameAndVersionDialog(realmWithDisplay.getShell(),
                aPool().withName("MyPool")
                        .withVersion("1.0").build(),
                diagramStore);

        final UpdateValueStrategy versionStrategy = dialog.versionUpdateStrategy();

        assertThat(versionStrategy.validateAfterGet("1.0/2")).isNotOK();
        assertThat(versionStrategy.validateAfterGet("1.0b")).isOK();
    }

    @Test
    public void should_diagram_name_strategy_validate_empty_name() throws Exception {
        final OpenNameAndVersionDialog dialog = new OpenNameAndVersionDialog(realmWithDisplay.getShell(),
                aMainProcess().withName("MyDiagram")
                        .withVersion("1.0").build(),
                diagramStore);

        final UpdateValueStrategy nameStrategy = dialog.nameUpdateStrategy();

        assertThat(nameStrategy.validateAfterGet("")).isNotOK();
    }

    @Test
    public void should_diagram_name_strategy_validate_max_length_name() throws Exception {
        final OpenNameAndVersionDialog dialog = new OpenNameAndVersionDialog(realmWithDisplay.getShell(),
                aMainProcess().withName("MyDiagram")
                        .withVersion("1.0").build(),
                diagramStore);

        final UpdateValueStrategy nameStrategy = dialog.nameUpdateStrategy();

        assertThat(nameStrategy.validateAfterGet(
                "tooooooooooooooooooooooooooooo00000000000000000000000000000000000000000000000000000000ooooooooooooooooooLong"))
                        .isNotOK();
    }

}
