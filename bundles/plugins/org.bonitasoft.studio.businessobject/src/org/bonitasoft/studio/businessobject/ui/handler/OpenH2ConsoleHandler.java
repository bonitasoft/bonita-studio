/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.handler;

import static com.google.common.collect.Iterables.tryFind;
import static com.google.common.io.Files.fileTreeTraverser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jdt.launching.SocketUtil;

import com.google.common.base.Optional;
import com.google.common.base.Predicate; 

public class OpenH2ConsoleHandler{

    private static final String URL = "jdbc:h2:file:%s/business_data.db;MVCC=TRUE;DB_CLOSE_ON_EXIT=TRUE;IGNORECASE=TRUE;AUTO_SERVER=TRUE;";
    private static final String DRIVER = "org.h2.Driver";
    private static final String USER = "sa";
    private static final int PORT = SocketUtil.findFreePort();


    @Execute
    public void execute(final RepositoryAccessor repositoryAccessor) throws ExecutionException {
        try {
            final String h2JarPath = locateH2jar(repositoryAccessor);
            final Process process = getRuntime()
                    .exec(new String[] {
                            "java",
                            "-jar",
                            h2JarPath,
                            "-browser",
                            "-webPort",
                            String.valueOf(PORT),
                            "-tcp",
                            "-user",
                            USER,
                            "-url",
                            String.format(URL, pathToDBFolder(repositoryAccessor)),
                            "-driver",
                            DRIVER });
            getRuntime().addShutdownHook(exitProcessHook(process));
        } catch (final IOException e) {
            throw new ExecutionException("Failed to locate h2 jar", e);
        }
    }

    protected String pathToDBFolder(final RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getCurrentRepository().getDatabaseHandler().getDBLocation().getAbsolutePath();
    }

    private Thread exitProcessHook(final Process process) {
        return new Thread() {

            @Override
            public void run() {
                if (process != null) {
                    process.destroy();
                }
            }
        };
    }

    protected Runtime getRuntime() {
        return Runtime.getRuntime();
    }

    protected String locateH2jar(RepositoryAccessor repositoryAccessor) throws IOException {
        final File root = rootFile(repositoryAccessor);
        final Path path = Paths.get(root.toURI()).resolve(Paths.get("tomcat", "server", "lib", "bonita"));
        final Optional<File> candidate = tryFind(fileTreeTraverser().children(path.toFile()), h2Jar());
        if (!candidate.isPresent()) {
            throw new FileNotFoundException("Cannot find h2 jar file in tomcat/lib/bonita folder.");
        }
        return candidate.get().getAbsolutePath();
    }

    protected File rootFile(RepositoryAccessor repositoryAccessor) {
        return repositoryAccessor.getWorkspace().getRoot().getLocation().toFile();
    }

    private Predicate<? super File> h2Jar() {
        return new Predicate<File>() {

            @Override
            public boolean apply(File file) {
                final String name = file.getName();
                return name.startsWith("h2-") && name.endsWith(".jar");
            }
        };
    }

}
