/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.builder.validators;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Properties;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.maven.builder.validator.PagePropertyFileValidator;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.rest.api.extension.core.validation.RestAPIPagePropertiesValidator;
import org.bonitasoft.studio.rest.api.extension.core.validation.UniquePathTemplateValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class RestAPIPagePropertyFileValidator extends PagePropertyFileValidator {

    public RestAPIPagePropertyFileValidator(IProject project) {
        super(project, Collections.emptyList());
    }

    @Override
    public void validate(IFile pagePropertyFile) throws CoreException {
        if(!project.exists()) {
            return;
        }
        project.deleteMarkers(PAGE_PROPERTY_VALIDATION_MARKER, false, IResource.DEPTH_ZERO);
        if (!pagePropertyFile.exists()) {
            createMarker(project, ValidationStatus.error(String.format(Messages.pagePropertyFileIsMissing, getPagePropertyPath())),
                    PAGE_PROPERTY_VALIDATION_MARKER);
            return;
        }
        Properties properties = new Properties();
        try (InputStream is = pagePropertyFile.getContents()) {
            properties.load(is);
            RestAPIExtensionRepositoryStore repositoryStore = restAPIExtensionRepositoryStore();
            RestAPIPagePropertiesValidator pagePropertiesValidator = new RestAPIPagePropertiesValidator(repositoryStore,
                    new UniquePathTemplateValidator(repositoryStore));
            refreshMarkers(pagePropertyFile, pagePropertiesValidator.validate(pagePropertyFile),
                    PAGE_PROPERTY_VALIDATION_MARKER);
        } catch (IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, RestAPIExtensionActivator.PLUGIN_ID, e.getMessage(), e));
        }
    }

    private RestAPIExtensionRepositoryStore restAPIExtensionRepositoryStore() {
        return RepositoryManager.getInstance().getCurrentRepository().getRepositoryStore(RestAPIExtensionRepositoryStore.class);
    }

    @Override
    protected IFolder getParentFolder() {
        return restAPIExtensionRepositoryStore().getResource();
    }

}
