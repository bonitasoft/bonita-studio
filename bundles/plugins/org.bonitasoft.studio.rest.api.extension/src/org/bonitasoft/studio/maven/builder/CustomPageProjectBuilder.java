/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.maven.builder.validator.AbstractCustomPageValidator;
import org.bonitasoft.studio.maven.builder.validator.PagePropertyFileValidator;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

public abstract class CustomPageProjectBuilder extends IncrementalProjectBuilder {

    @Override
    protected IProject[] build(final int kind, final Map<String, String> args, IProgressMonitor monitor)
            throws CoreException {
        if(!RepositoryManager.getInstance().hasActiveRepository()) {
            return new IProject[0];
        }
        IResourceDelta delta = null;
        IProject project = getProject();
        if (kind != FULL_BUILD) {
            delta = getDelta(project);
        }
        if (delta == null) {
            if (isInterestingProject(project) && RepositoryManager.getInstance().hasActiveRepository()) {
                createPagePropertyFileValidator(project).acceptAndValidate();
            }
        } else {
            delta.accept(createDeltaVisitor(monitor, project));
        }
        return new IProject[0];
    }

    protected CustomPageDeltaVisitor createDeltaVisitor(IProgressMonitor monitor, IProject project) {
        return new CustomPageDeltaVisitor(createValidators(project, monitor));
    }

    protected abstract PagePropertyFileValidator createPagePropertyFileValidator(IProject project);

    public List<AbstractCustomPageValidator> createValidators(IProject project, IProgressMonitor monitor) {
        List<AbstractCustomPageValidator> validators = new ArrayList<>();
        validators.add(createPagePropertyFileValidator(project));
        return validators;
    }

    private boolean isInterestingProject(final IProject project) throws CoreException {
        return project.hasNature(RestAPIExtensionNature.NATURE_ID);
    }

}
