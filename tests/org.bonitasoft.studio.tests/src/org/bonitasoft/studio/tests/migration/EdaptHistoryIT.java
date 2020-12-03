/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.migration;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URL;

import org.bonitasoft.studio.common.ModelVersion;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edapt.internal.common.ResourceUtils;
import org.eclipse.emf.edapt.spi.history.History;
import org.eclipse.emf.edapt.spi.history.HistoryPackage;
import org.eclipse.emf.edapt.spi.history.Release;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class EdaptHistoryIT {

    private History history;

    @Before
    public void setUp() throws Exception {
        final URL resource = Platform.getBundle("org.bonitasoft.studio-models").getResource("process.history");
        final URI historyURI = URI.createFileURI(new File(FileLocator.toFileURL(resource).getFile()).getAbsolutePath());
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
        assertThat(release.getLabel()).isEqualTo(ModelVersion.CURRENT_DIAGRAM_VERSION);
    }

}
