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
package org.bonitasoft.studio.connectors.ui.wizard.page;

import static org.bonitasoft.studio.model.process.builders.LaneBuilder.aLane;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.jface.databinding.validator.MultiValidator;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.runtime.IStatus;
import org.junit.Rule;
import org.junit.Test;

public class MoveConnectorWizardPageTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Test
    public void should_return_a_valid_status_if_not_moving_a_task_connector() throws Exception {
        final Pool pool = aPool().build();
        final Task task = aTask().build();
        final MoveConnectorWizardPage wizardPage = new MoveConnectorWizardPage(pool,
                new WritableValue(task, Connector.class),
                new WritableValue(ConnectorEvent.ON_ENTER.name(),
                        String.class));

        final MultiValidator validator = wizardPage.selectionValidator();

        StatusAssert.assertThat(validator.validate(task)).isOK();
    }

    @Test
    public void should_return_a_valid_status_if_moving_a_process_connector() throws Exception {
        final Pool pool = aPool().build();
        final Task task = aTask().build();
        final MoveConnectorWizardPage wizardPage = new MoveConnectorWizardPage(pool, new WritableValue(pool, Connector.class), new WritableValue(
                ConnectorEvent.ON_ENTER.name(),
                String.class));

        final MultiValidator validator = wizardPage.selectionValidator();

        StatusAssert.assertThat(validator.validate(task)).isOK();
    }

    @Test
    public void should_return_a_warning_status_if_moving_a_connector_from_a_tasks() throws Exception {
        final Pool pool = aPool().build();
        final Task task = aTask().build();
        final MoveConnectorWizardPage wizardPage = new MoveConnectorWizardPage(pool, new WritableValue(task, Connector.class), new WritableValue(
                ConnectorEvent.ON_ENTER.name(),
                String.class));

        final MultiValidator validator = wizardPage.selectionValidator();

        StatusAssert.assertThat(validator.validate(pool)).hasSeverity(IStatus.WARNING);
    }

    @Test
    public void should_return_an_error_status_if_no_target_location_is_selected_tasks() throws Exception {
        final Pool pool = aPool().build();
        final Task task = aTask().build();
        final MoveConnectorWizardPage wizardPage = new MoveConnectorWizardPage(pool, new WritableValue(task, Connector.class), new WritableValue(
                ConnectorEvent.ON_ENTER.name(),
                String.class));

        final MultiValidator validator = wizardPage.selectionValidator();

        StatusAssert.assertThat(validator.validate(null)).isNotOK();
    }

    @Test
    public void should_return_an_error_status_if_target_location_is_not_a_connectable_element() throws Exception {
        final Pool pool = aPool().build();
        final Task task = aTask().build();
        final MoveConnectorWizardPage wizardPage = new MoveConnectorWizardPage(pool, new WritableValue(task, Connector.class), new WritableValue(
                ConnectorEvent.ON_ENTER.name(),
                String.class));

        final MultiValidator validator = wizardPage.selectionValidator();

        StatusAssert.assertThat(validator.validate(aLane().build())).isNotOK();
    }

}
