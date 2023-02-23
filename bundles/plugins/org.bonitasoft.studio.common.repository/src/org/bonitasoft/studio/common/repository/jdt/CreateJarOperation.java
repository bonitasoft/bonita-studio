/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.jdt;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.ui.jarpackager.IJarExportRunnable;
import org.eclipse.jdt.ui.jarpackager.JarPackageData;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 */
public class CreateJarOperation implements IRunnableWithProgress {

    private final ICompilationUnit[] compilationUnits;
    private final File toFile;
    private boolean includeSources;
    private IJarExportRunnable exportRunnable;

    public CreateJarOperation(final File toFile, final ICompilationUnit... compilationUnits) {
        checkArgument(compilationUnits != null);
        checkArgument(toFile != null);
        this.compilationUnits = compilationUnits;
        this.toFile = toFile;
    }

    public CreateJarOperation includeSources() {
        includeSources = true;
        return this;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        exportRunnable = configureJar(compilationUnits).createJarExportRunnable(null);
        exportRunnable.run(monitor);
    }

    public IStatus getStatus() {
        if (exportRunnable != null) {
            return exportRunnable.getStatus();
        }
        return new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, "Operation has not been executed yet.");
    }

    private JarPackageData configureJar(final ICompilationUnit[] compilationUnits) {
        final JarPackageData jarPackakeData = newJarPackageData();
        jarPackakeData.setJarLocation(Path.fromOSString(toFile.getAbsolutePath()));
        jarPackakeData.setElements(compilationUnits);
        jarPackakeData.setExportWarnings(true);
        jarPackakeData.setExportErrors(false);
        jarPackakeData.setDeprecationAware(true);
        jarPackakeData.setExportJavaFiles(includeSources);
        jarPackakeData.setOverwrite(true);
        return jarPackakeData;
    }

    protected JarPackageData newJarPackageData() {
        return new JarPackageData();
    }

}
