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
package org.bonitasoft.studio.common.repository;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jdt.launching.JavaRuntime;

public class JavaProjectClassLoader extends ClassLoader {

    private final IJavaProject javaProject;
    private static final String PROTOCOL_PREFIX = "file:///";

    public JavaProjectClassLoader(IJavaProject project) {
        super();
        if (project == null || !project.exists())
            throw new IllegalArgumentException("Invalid javaProject");
        this.javaProject = project;
    }

    @Override
    public Class findClass(String className) {
        try {
            final String[] classPaths = JavaRuntime.computeDefaultRuntimeClassPath(javaProject);
            final URL[] urls = new URL[classPaths.length];
            for (int i = 0; i < classPaths.length; i++)
                urls[i] = new URL(PROTOCOL_PREFIX + computeForURLClassLoader(classPaths[i]));
            try (final URLClassLoader loader = new URLClassLoader(urls);) {
                final Class<?> classObject = loader.loadClass(className);
                return classObject;
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public List<Class> findClasses(String classNamePattern) {
        final List<Class> results = new ArrayList<>();
        if (classNamePattern.endsWith(".java")) {
            classNamePattern = classNamePattern.substring(0, classNamePattern.lastIndexOf("."));
        }
        // find exact matches first
        findClasses(classNamePattern, results);
        // and then everything else
        if (!classNamePattern.endsWith("*")) {
            classNamePattern += "*";
            findClasses(classNamePattern, results);
        }
        return results;
    }

    public void findClasses(String classNamePattern, final List<Class> results) {
        final SearchPattern pattern = SearchPattern.createPattern(classNamePattern,
                IJavaSearchConstants.TYPE, IJavaSearchConstants.TYPE,
                SearchPattern.R_PATTERN_MATCH);
        final SearchEngine searchEngine = new SearchEngine();
        final IJavaSearchScope scope = SearchEngine.createJavaSearchScope(new IJavaProject[] { javaProject });
        final SearchRequestor requestor = new SearchRequestor() {

            @Override
            public void acceptSearchMatch(SearchMatch match) {
                IJavaElement e = (IJavaElement) match.getElement();
                final String elementName = e.getElementName();
                while (e != null) {
                    if (e instanceof IPackageFragment) {
                        final IPackageFragment pf = (IPackageFragment) e;
                        final String className = pf.getElementName() + "." + elementName;
                        final Class c = findClass(className);
                        if (c != null) {
                            boolean found = false;
                            for (final Class cr : results) {
                                if (cr.getName().equals(c.getName())) {
                                    found = true;
                                    break;
                                }
                            }
                            if (!found)
                                results.add(c);
                        }
                    }
                    e = e.getParent();
                }
            }
        };
        try {
            searchEngine.search(
                    pattern,
                    new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() },
                    scope,
                    requestor,
                    null);
        } catch (final CoreException e) {
        }
    }

    public static IJavaProject[] findProject(final String className) {
        final SearchPattern pattern = SearchPattern.createPattern(className,
                IJavaSearchConstants.TYPE, IJavaSearchConstants.TYPE,
                SearchPattern.R_EXACT_MATCH);
        final List<IJavaProject> results = new ArrayList<>();
        final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        for (final IProject p : projects) {
            try {
                if (p.isOpen() && p.hasNature(JavaCore.NATURE_ID)) {
                    final IJavaProject javaProject = JavaCore.create(p);
                    final SearchEngine searchEngine = new SearchEngine();
                    final IJavaSearchScope scope = SearchEngine.createJavaSearchScope(new IJavaProject[] { javaProject });
                    final SearchRequestor requestor = new SearchRequestor() {

                        @Override
                        public void acceptSearchMatch(SearchMatch match) {
                            IJavaElement e = (IJavaElement) match.getElement();
                            final String elementName = e.getElementName();
                            while (e != null) {
                                if (e instanceof IPackageFragment) {
                                    final IPackageFragment pf = (IPackageFragment) e;
                                    final String n = pf.getElementName() + "." + elementName;
                                    if (className.equals(n))
                                        results.add(javaProject);
                                }
                                e = e.getParent();
                            }
                        }
                    };
                    searchEngine.search(
                            pattern,
                            new SearchParticipant[] { SearchEngine.getDefaultSearchParticipant() },
                            scope,
                            requestor,
                            null);
                }
            } catch (final Exception e) {
            }
        }
        return results.toArray(new IJavaProject[results.size()]);
    }

    private static String computeForURLClassLoader(String classpath) {
        if (!classpath.endsWith("/")) {
            final File file = new File(classpath);
            if (file.exists() && file.isDirectory())
                classpath = classpath.concat("/");
        }
        return classpath;
    }

}
