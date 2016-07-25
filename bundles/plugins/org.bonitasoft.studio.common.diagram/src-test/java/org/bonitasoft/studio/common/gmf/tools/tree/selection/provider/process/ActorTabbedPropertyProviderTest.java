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
package org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ActorBuilder.anActor;

import org.bonitasoft.studio.model.process.ProcessFactory;
import org.junit.Test;

public class ActorTabbedPropertyProviderTest {

    @Test
    public void should_return_general_viewId() throws Exception {
        final ActorTabbedPropertyProvider provider = new ActorTabbedPropertyProvider();

        assertThat(provider.viewId()).isEqualTo("org.bonitasoft.studio.views.properties.process.general");
    }

    @Test
    public void should_return_actorTab_id() throws Exception {
        final ActorTabbedPropertyProvider provider = new ActorTabbedPropertyProvider();

        assertThat(provider.tabId(null)).isEqualTo("tab.actors");
    }

    @Test
    public void should_appliesTo_Actor_and_ActorFilter() throws Exception {
        final ActorTabbedPropertyProvider provider = new ActorTabbedPropertyProvider();

        assertThat(provider.appliesTo(anActor().build(), null)).isTrue();
        assertThat(provider.appliesTo(ProcessFactory.eINSTANCE.createActorFilter(), null)).isTrue();
        assertThat(provider.appliesTo(null, null)).isFalse();
    }
}
