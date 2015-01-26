/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.migration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.studio.common.ModelVersion;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.Release;
import org.junit.Test;

public class DiagramRepositoryStoreMigrationTest {

	@Test
	public void testLatestVersionIsSameThanModelVersion() {
		final DiagramRepositoryStore store = RepositoryManager.getInstance()
				.getRepositoryStore(DiagramRepositoryStore.class);
		final Migrator migrator = store.initializeMigrator();

		final String latestReleaseLabel = getLatestRelease(migrator).getLabel();

		assertThat(latestReleaseLabel).isEqualTo(ModelVersion.CURRENT_VERSION);
	}

	@Test
	public void testCurrentModelVersionWontBeGoThroughMigration() {
		final DiagramRepositoryStore store = RepositoryManager.getInstance()
				.getRepositoryStore(DiagramRepositoryStore.class);
		final Migrator migrator = store.initializeMigrator();

		final String releaseForCurrentModelVersion = store.getRelease(migrator,
				ModelVersion.CURRENT_VERSION).getLabel();

		assertThat(releaseForCurrentModelVersion).isEqualTo(
				ModelVersion.CURRENT_VERSION);

	}

	@Test
	public void testReleaseVersionHistoryHaveCorrectFormat() {
		final DiagramRepositoryStore store = RepositoryManager.getInstance()
				.getRepositoryStore(DiagramRepositoryStore.class);
		final Migrator migrator = store.initializeMigrator();

		final String pattern = "(\\d+)\\.(\\d+)\\.(\\d+)-.*";

		for (final Release release : migrator.getReleases()) {
			final String releaseLabel = release.getLabel();
			assertThat(releaseLabel).startsWith("6.");
			assertThat(releaseLabel.matches(pattern)).isTrue();
		}

	}

	public Release getLatestRelease(final Migrator migrator) {
		final List<Release> releases = migrator.getReleases();
		return releases.get(releases.size() - 1);
	}
}
