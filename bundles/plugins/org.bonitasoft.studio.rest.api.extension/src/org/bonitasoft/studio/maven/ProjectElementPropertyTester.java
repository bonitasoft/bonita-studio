/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.codehaus.jdt.groovy.model.GroovyNature;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;

public class ProjectElementPropertyTester extends PropertyTester {

    public static final String REST_API_ELEMENT_PROPERTY = "isRestApiElement";
    public static final String REST_API_FOLDER_PROPERTY = "isRestApiFolder";
    public static final String REST_API_PROJECT_PROPERTY = "isRestApiProject";
    public static final String NON_PROJECT_REST_API_FOLDER_PROPERTY = "isNonProjectRestApiFolder";
    public static final String THEME_ELEMENT_PROPERTY = "isThemeElement";
    public static final String THEME_FOLDER_PROPERTY = "isThemeFolder";
    public static final String CUSTOM_PAGE_ELEMENT_PROPERTY = "isCustomPageElement";
    private static final String JAVA_KIND = "java";
    private static final String GROOVY_KIND = "groovy";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        var store = RepositoryManager.getInstance()
                .getRepositoryStore(ExtensionRepositoryStore.class);
        switch (property) {
            case CUSTOM_PAGE_ELEMENT_PROPERTY:
                return isCustomPageElement((IAdaptable) receiver, store, args);
            case REST_API_ELEMENT_PROPERTY:
                return isRestApiElement((IAdaptable) receiver, store, args);
            case REST_API_FOLDER_PROPERTY:
                return isRestApiFolder((IAdaptable) receiver, store);
            case THEME_ELEMENT_PROPERTY:
                return isThemeElement((IAdaptable) receiver, store);
            case THEME_FOLDER_PROPERTY:
                return isThemeFolder((IAdaptable) receiver, store);
            case REST_API_PROJECT_PROPERTY:
                return isRestApiProject((IAdaptable) receiver, store);
            case NON_PROJECT_REST_API_FOLDER_PROPERTY:
                return isNonProjectRestApiProject((IAdaptable) receiver, store);
            default:
        }
        return false;
    }

    private boolean isCustomPageElement(IAdaptable receiver, ExtensionRepositoryStore store, Object[] args) {
        return isRestApiElement(receiver, store, args) || isThemeElement(receiver, store);
    }

    private boolean isRestApiProject(IAdaptable receiver, ExtensionRepositoryStore store) {
        return Optional.ofNullable(receiver.getAdapter(IProject.class))
                .map(IProject::getName)
                .filter(name -> store.getResource().getFolder(name).exists())
                .isPresent();
    }

    private boolean isNonProjectRestApiProject(IAdaptable receiver, ExtensionRepositoryStore store) {
        IFolder folder = receiver.getAdapter(IFolder.class);
        return folder != null
                && folder.findMember("pom.xml") != null
                && folder.findMember("pom.xml").exists()
                && Optional.ofNullable(receiver.getAdapter(IResource.class))
                        .map(IResource::getName)
                        .map(name -> store.getResource().getWorkspace().getRoot().getProject(name))
                        .filter(project -> project == null || !project.exists())
                        .isPresent();
    }

    private boolean isRestApiFolder(IAdaptable receiver, ExtensionRepositoryStore store) {
        return Objects.equals(receiver.getAdapter(IFolder.class), store.getResource());
    }

    private boolean isRestApiElement(IAdaptable receiver, ExtensionRepositoryStore store, Object[] args) {
        IResource resource = receiver.getAdapter(IResource.class);
        boolean isRestApiElement = Optional.ofNullable(resource)
                .map(IResource::getProject)
                .map(IProject::getName)
                .filter(name -> store.getResource().getFolder(name).exists())
                .isPresent();
        if (args.length == 1) {
            String kind = (String) args[0];
            try {
                boolean hasGroovyNature = resource.getProject().hasNature(GroovyNature.GROOVY_NATURE);
                if (JAVA_KIND.equals(kind)) {
                    return isRestApiElement && !hasGroovyNature;
                }
                if (GROOVY_KIND.equals(kind)) {
                    return isRestApiElement && hasGroovyNature;
                }
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return isRestApiElement;
    }

    private boolean isThemeFolder(IAdaptable receiver, ExtensionRepositoryStore store) {
        return Objects.equals(receiver.getAdapter(IFolder.class), store.getResource());
    }

    private boolean isThemeElement(IAdaptable receiver, ExtensionRepositoryStore store) {
        return Optional.ofNullable(receiver.getAdapter(IResource.class))
                .map(IResource::getProject)
                .map(IProject::getName)
                .filter(name -> store.getResource().getFolder(name).exists())
                .isPresent();
    }
}
