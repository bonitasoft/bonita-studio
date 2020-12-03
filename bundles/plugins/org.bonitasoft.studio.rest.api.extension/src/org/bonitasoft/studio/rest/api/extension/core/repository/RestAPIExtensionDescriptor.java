/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.maven.CustomPageMavenProjectDescriptor;
import org.bonitasoft.studio.maven.builder.PagePropertyConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import com.google.common.base.Strings;

public class RestAPIExtensionDescriptor extends CustomPageMavenProjectDescriptor {

    protected final static String SRC_PROJECT_PATH = "src/main/groovy/";

    public RestAPIExtensionDescriptor(final IProject project) {
        super(project);
    }

    @Override
    public List<IFile> getFilesToOpen() {
        ensureProjectOpen();
        final List<IFile> result = new ArrayList<>();
        final Properties pageProperties = getPageProperties();
        final String apis = pageProperties.getProperty("apiExtensions");
        if (!Strings.isNullOrEmpty(apis)) {
            for (final String apiName : apis.split(",")) {
                final String name = apiName.trim();
                final String fileName = pageProperties.getProperty(name + ".classFileName");
                if (!Strings.isNullOrEmpty(fileName)) {
                    final IPath path = Path.fromOSString(SRC_PROJECT_PATH + fileName);
                    result.add(project.getFile(path));
                }
                final String className = pageProperties.getProperty(name + ".className");
                if (!Strings.isNullOrEmpty(className)) {
                    IJavaProject javaProject = JavaCore.create(getProject());
                    try {
                        IType type = javaProject.findType(className);
                        if (type != null && type.getCompilationUnit().getResource() instanceof IFile) {
                            result.add((IFile) type.getCompilationUnit().getResource());
                        }
                    } catch (JavaModelException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        return result;
    }

    public List<String> getAPIExtensions() {
        Properties pageProperties = getPageProperties();
        String value = pageProperties.getProperty(PagePropertyConstants.API_EXTENSIONS);
        if (value != null && !value.isEmpty()) {
            return Stream.of(value.trim().split(","))
                    .map(apiName -> apiName.trim())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public String getPathTemplate(String apiName) {
        String value = getPageProperties()
                .getProperty(String.format("%s.%s", apiName, PagePropertyConstants.PATH_TEMAPLTE));
        return value == null ? "" : value.trim();
    }

    public String getHTTPMethod(String apiName) {
        String value = getPageProperties().getProperty(String.format("%s.%s", apiName, PagePropertyConstants.METHOD));
        return value == null ? "" : value.trim();
    }

    public List<PathTemplate> getPathTemplates() {
        return getAPIExtensions().stream()
                .map(apiName -> new PathTemplate(getPathTemplate(apiName), getHTTPMethod(apiName)))
                .collect(Collectors.toList());
    }

    public Collection<String> getPermissions(String apiName) {
        String value = getPageProperties()
                .getProperty(String.format("%s.%s", apiName, PagePropertyConstants.PERMISSIONS));
        return value == null || value.trim().isEmpty() ? Collections.emptyList()
                : Stream.of(value.trim().split(",")).map(String::trim).collect(Collectors.toSet());
    }

    public Optional<String> findAPINameForPathTemplate(PathTemplate pathTemplate) {
        return getAPIExtensions().stream()
                .filter(apiName -> Objects.equals(pathTemplate,
                        new PathTemplate(getPathTemplate(apiName), getHTTPMethod(apiName))))
                .findFirst();
    }

}
