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
package org.bonitasoft.studio.businessobject.ui.handler;

import static com.google.common.collect.Iterables.tryFind;
import static com.google.common.io.Files.fileTreeTraverser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.launching.SocketUtil;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

/**
 * @author Romain Bioteau
 *
 */
public class OpenH2ConsoleHandler extends AbstractHandler {

    private static final String URL = "jdbc:h2:tcp://localhost:9091/business_data.db;MVCC=TRUE;DB_CLOSE_ON_EXIT=TRUE;IGNORECASE=TRUE;";
    private static final String DRIVER = "org.h2.Driver";
    private static final String USER = "sa";
    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        try {
            final String h2JarPath = locateH2jar(
                    rootFile());
            getRuntime().exec(String.format("java -jar %s -browser -webPort %s -tcp -user %s -url %s -driver %s", h2JarPath, SocketUtil.findFreePort(),
                    USER, URL, DRIVER));
        } catch (final IOException e) {
            throw new ExecutionException("Failed to locate h2 jar", e);
        }
        return IStatus.OK;
    }

    protected File rootFile() {
        return RepositoryManager.getInstance().getCurrentRepository().getProject().getWorkspace().getRoot().getLocation().toFile();
    }

    protected Runtime getRuntime() {
        return Runtime.getRuntime();
    }

    protected String locateH2jar(File root) throws IOException {
        final Path path = Paths.get(root.toURI()).resolve(Paths.get("tomcat", "lib", "bonita"));
        final Optional<File> candidate = tryFind(fileTreeTraverser().children(path.toFile()), h2Jar());
        if (!candidate.isPresent()) {
            throw new FileNotFoundException("Cannot find h2 jar file in tomcat/lib/bonita folder.");
        }
        return candidate.get().getAbsolutePath();
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
