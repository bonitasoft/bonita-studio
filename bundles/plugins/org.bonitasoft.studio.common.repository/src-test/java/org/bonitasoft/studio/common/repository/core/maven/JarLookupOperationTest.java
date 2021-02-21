/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.bonitasoft.studio.common.repository.core.maven.migration.JarInputStreamSupplier;
import org.junit.Test;

public class JarLookupOperationTest {

    @Test
    public void should_give_maven_central_url_the_priority() throws Exception {
        JarLookupOperation jarLookupOperation = new JarLookupOperation(mock(JarInputStreamSupplier.class));
        jarLookupOperation.addRemoteRespository("url1");
        jarLookupOperation.addRemoteRespository(JarLookupOperation.MAVEN_CENTRAL_URL);
        jarLookupOperation.addRemoteRespository("url2");

        assertThat(jarLookupOperation.getRemoteRepositories())
                .containsExactlyInAnyOrder(JarLookupOperation.MAVEN_CENTRAL_URL, "url1", "url2");
    }

}
