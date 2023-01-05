/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.theme.builder;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.maven.builder.CustomPageProjectBuilder;
import org.bonitasoft.studio.maven.builder.validator.AbstractCustomPageValidator;
import org.bonitasoft.studio.maven.builder.validator.PagePropertyFileValidator;
import org.bonitasoft.studio.maven.builder.validator.PomFileValidator;
import org.bonitasoft.studio.theme.ThemeRepositoryStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

public class ThemeProjectBuilder extends CustomPageProjectBuilder {

    public static final String BUILDER_ID = "org.bonitasoft.studio.rest.api.extension.themePagePropertyBuilder";

    public List<AbstractCustomPageValidator> createValidators(IProject project, IProgressMonitor monitor) {
        List<AbstractCustomPageValidator> validators = new ArrayList<>();
        validators.add(new PomFileValidator(project, monitor).addDependencyValidator(new ThemeDependencyVersionToUpdateFinder()));
        return validators;
    }
    
    protected PagePropertyFileValidator createPagePropertyFileValidator(IProject project) {
        return new ThemePagePropertyFileValidator(project,
                RepositoryManager.getInstance().getRepositoryStore(ThemeRepositoryStore.class).getResource());
    }

}
