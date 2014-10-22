/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class EdaptHistoryIT {

    private Migrator migrator;

    @Before
    public void setUp() throws Exception{
        final DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        migrator = store.getMigrator(ProcessPackage.eNS_URI);
    }

    @Test
    public void should_migrator_has_an_unreleased_release() throws Exception {
        final List<Release> releases = migrator.getReleases();
        assertThat(releases).extracting("label").containsOnlyOnce("");
    }





}
