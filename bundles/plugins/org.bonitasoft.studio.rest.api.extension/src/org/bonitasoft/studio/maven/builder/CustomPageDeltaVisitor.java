/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder;

import java.util.List;

import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.maven.builder.validator.AbstractCustomPageValidator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

public class CustomPageDeltaVisitor implements IResourceDeltaVisitor {

    private List<AbstractCustomPageValidator> validators;

    public CustomPageDeltaVisitor(List<AbstractCustomPageValidator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean visit(IResourceDelta delta) throws CoreException {
        IResource resource = delta.getResource();

        if (resource instanceof IProject) {
            return isInterestingProject((IProject) resource);
        }

        if (resource instanceof IFolder) {
            return true;
        }

        if (resource instanceof IFile) {
            doVisitFile((IFile) resource);
        }
        return false;
    }

    protected void doVisitFile(IFile candidate) throws CoreException {
        for (AbstractCustomPageValidator validator : validators) {
            validator.acceptAndValidate(candidate);
        }
    }

    private boolean isInterestingProject(final IProject project) throws CoreException {
        return project.hasNature(RestAPIExtensionNature.NATURE_ID);
    }

}
