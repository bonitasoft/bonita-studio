/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.emf.tools;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.junit.Test;

public class ProcessVersionComparatorTest {

    @Test
    public void should_compare_version_using_maven_semver() throws Exception {
        ProcessVersionComparator processVersionComparator = new ProcessVersionComparator();

        int compare = processVersionComparator.compare(aPool().withVersion("0.9").build(),
                aPool().withVersion("0.10").build());

        assertThat(compare).isLessThan(0);
    }

    @Test
    public void should_compare_version_using_maven_semver_with_SNAPSHOT() throws Exception {
        ProcessVersionComparator processVersionComparator = new ProcessVersionComparator();

        int compare = processVersionComparator.compare(aPool().withVersion("0.9-SNAPSHOT").build(),
                aPool().withVersion("0.10-SNAPSHOT").build());

        assertThat(compare).isLessThan(0);
    }

    @Test
    public void should_compare_version_without_maven_semver() throws Exception {
        ProcessVersionComparator processVersionComparator = new ProcessVersionComparator();

        int compare = processVersionComparator.compare(aPool().withVersion("10 alpha").build(),
                aPool().withVersion("2 beta").build());

        assertThat(compare).isGreaterThan(0);
    }
}
