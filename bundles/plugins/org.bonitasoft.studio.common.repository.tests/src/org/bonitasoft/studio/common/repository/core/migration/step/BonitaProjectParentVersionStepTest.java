/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration.step;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.ProductVersion;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BonitaProjectParentVersionStepTest {

    @TempDir
	private Path project;

	@BeforeEach
	void setup() throws Exception {
		copyDirectory(
				new File(FileLocator.toFileURL(BonitaProjectParentVersionStepTest.class.getResource("/parentPomUpdate")).getFile())
						.getAbsolutePath(),
				project.toFile().getAbsolutePath());
	}

	@Test
	void shouldUpdateBonitaProjectParent() throws Exception {
		var step = new BonitaProjectParentVersionStep();

		var report = step.run(project, new NullProgressMonitor());

		var model = load(project);
		assertThat(report.updates()).containsOnly(
				String.format("Bonita project parent has been updated from `%s` to `%s`.",
	                    "1.0.0",
	                    ProductVersion.BONITA_RUNTIME_VERSION));
		assertThat(model.getParent().getVersion()).isEqualTo(ProductVersion.BONITA_RUNTIME_VERSION);
	}

	private static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation)
			throws IOException {
		try (var files = Files.walk(Paths.get(sourceDirectoryLocation))) {
			files.forEach(source -> {
				Path destination = Paths.get(destinationDirectoryLocation,
						source.toString().substring(sourceDirectoryLocation.length()));
				try {
					Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			});
		}
	}

	private static Model load(Path project) throws IOException, CoreException {
		try (var is = Files.newInputStream(project.resolve("pom.xml"))) {
			return MavenPlugin.getMavenModelManager().readMavenModel(is);
		}
	}

}
