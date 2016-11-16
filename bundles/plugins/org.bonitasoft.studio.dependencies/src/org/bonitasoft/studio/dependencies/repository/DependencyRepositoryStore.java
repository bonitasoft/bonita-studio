/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.dependencies.repository;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.dependencies.DependenciesPlugin;
import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class DependencyRepositoryStore extends AbstractRepositoryStore<DependencyFileStore> {

    private static final String BDM_CLIENT_POJO_JAR = "bdm-client-pojo.jar";
	public static final String STORE_NAME = "lib";
    public static final String JAR_EXT = "jar";
    private static final Set<String> extensions = new HashSet<String>();
    static {
        extensions.add("jar");
    }

    private Map<String, String> runtimeDependencies;

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public DependencyFileStore createRepositoryFileStore(final String fileName) {
        return new DependencyFileStore(fileName, this);
    }

    protected static Set<String> retriveAllJarFilesFrom(final File root) {
        final Set<String> allJarFiles = new HashSet<String>();
        final File[] listFiles = root.listFiles();
        if (listFiles != null) {
            for (final File f : listFiles) {
                if (f.isDirectory()) {
                    allJarFiles.addAll(retriveAllJarFilesFrom(f));
                } else if (f.getName().endsWith(".jar")) {
                    allJarFiles.add(f.getName());
                }
            }
        }
        return allJarFiles;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.dependenciesRepository;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("dependencies.png", DependenciesPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    public Map<String, String> getRuntimeDependencies() {
        if (runtimeDependencies == null) {
            runtimeDependencies = new HashMap<String, String>();
            final File tomcatRoot = getTomcatRootFile();
            final Set<String> allJarFiles = retriveAllJarFilesFrom(new File(tomcatRoot, "lib"));
            allJarFiles.add(BDM_CLIENT_POJO_JAR);
            allJarFiles.addAll(retriveAllJarFilesFrom(new File(tomcatRoot, "webapps")));
            for (final String jarName : allJarFiles) {
                runtimeDependencies.put(getLibName(jarName), getLibVersion(jarName));
            }
        }
        return runtimeDependencies;
    }

    protected String getLibVersion(String jarName) {
        if (jarName.endsWith(".jar")) {
            jarName = jarName.replace(".jar", "");
        }
        String libVersion = "";
        boolean appendSnapshot = false;
        if (jarName.indexOf("-SNAPSHOT") != -1) {
            jarName = jarName.substring(0, jarName.lastIndexOf("-SNAPSHOT"));
            appendSnapshot = true;
        }
        if (jarName.indexOf("-") != -1 && Character.isDigit(jarName.charAt(jarName.lastIndexOf("-") + 1))) {
            libVersion = jarName.substring(jarName.lastIndexOf("-") + 1, jarName.length());
        }
        if (appendSnapshot) {
            libVersion = libVersion + "-SNAPSHOT";
        }
        return libVersion;
    }

    protected String getLibName(String jarName) {
        if (jarName.endsWith(".jar")) {
            jarName = jarName.replace(".jar", "");
        }
        String libName = "";
        if (jarName.indexOf("-SNAPSHOT") != -1) {
            jarName = jarName.substring(0, jarName.lastIndexOf("-SNAPSHOT"));
        }
        if (jarName.indexOf("-") != -1 && Character.isDigit(jarName.charAt(jarName.lastIndexOf("-") + 1))) {
            libName = jarName.substring(0, jarName.lastIndexOf("-"));
        } else {
            libName = jarName;
        }
        return libName;
    }

    protected File getTomcatRootFile() {
        return new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), "tomcat" + File.separatorChar + "server");
    }

    @Override
    public void migrate(final IProgressMonitor monitor) throws CoreException, MigrationException {
        //NOTHING TO DO
    }
}
