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

import org.bonitasoft.studio.common.ModelVersion;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edapt.common.ResourceUtils;
import org.eclipse.emf.edapt.history.History;
import org.eclipse.emf.edapt.history.HistoryPackage;
import org.eclipse.emf.edapt.history.Release;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class EdaptHistoryIT {

    private History history;

    @Before
    public void setUp() throws Exception{
        final URI historyURI = URI.createPlatformPluginURI("org.bonitasoft.studio-models/process.history", false);
        HistoryPackage.eINSTANCE.getHistory();
        history = ResourceUtils.loadElement(historyURI);
    }

    @Test
    public void should_history_last_release_has_no_name_and_date() throws Exception {
        final Release release = history.getLastRelease();
        assertThat(release.getLabel()).isNull();
        assertThat(release.getDate()).isNull();
    }

    @Test
    public void should_history_latest_release_label_equals_current_model_version() throws Exception {
        final Release release = history.getLatestRelease();
        assertThat(release.getLabel()).isEqualTo(ModelVersion.CURRENT_VERSION);
    }




}
