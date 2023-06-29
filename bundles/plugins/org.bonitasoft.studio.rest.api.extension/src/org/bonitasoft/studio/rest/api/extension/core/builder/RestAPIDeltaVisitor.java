/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.builder;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.maven.builder.CustomPageDeltaVisitor;
import org.bonitasoft.studio.maven.builder.validator.AbstractCustomPageValidator;
import org.bonitasoft.studio.maven.builder.validator.PagePropertyFileValidator;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

public class RestAPIDeltaVisitor extends CustomPageDeltaVisitor {

    public RestAPIDeltaVisitor(List<AbstractCustomPageValidator> validators) {
        super(validators);
    }
    
    @Override
    protected void doVisitFile(IFile candidate) throws CoreException {
        if (isAGroovyFile(candidate)) {
            candidate = candidate.getProject().getFile(PagePropertyFileValidator.PAGE_PROPERTIES_PATH);
        }
        super.doVisitFile(candidate);
    }

    private boolean isAGroovyFile(final IFile candidate) {
        return Objects.equals(candidate.getFileExtension(), "groovy");
    }
}
