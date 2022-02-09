/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder.validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

public abstract class PagePropertyFileValidator extends AbstractCustomPageValidator {

    public static final String PAGE_PROPERTY_VALIDATION_MARKER = "org.bonitasoft.studio.rest.api.extension.validation.pagePropertyMarker";
    public static final String PAGE_PROPERTIES_PATH = "src/main/resources/page.properties";
    private List<PagePropertyValidator> validators;

    public PagePropertyFileValidator(IProject project, List<PagePropertyValidator> validators) {
        super(project);
        this.validators = validators;
    }

    @Override
    public boolean accept(IFile candidate) {
        return candidate.getProjectRelativePath().equals(Path.fromOSString(getPagePropertyPath()));
    }
    
   protected String getPagePropertyPath() {
       return PAGE_PROPERTIES_PATH;
   }

    @Override
    public void validate(IFile pagePropertyFile) throws CoreException {
        if(!RepositoryManager.getInstance().hasActiveRepository() || !project.exists()) {
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
            PagePropertiesValidator pagePropertiesValidator = new PagePropertiesValidator(validators);
            refreshMarkers(pagePropertyFile, pagePropertiesValidator.validate(pagePropertyFile),
                    PAGE_PROPERTY_VALIDATION_MARKER);
        } catch (IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, RestAPIExtensionActivator.PLUGIN_ID, e.getMessage(), e));
        }
    }

    @Override
    public void acceptAndValidate() throws CoreException {
        IFile candidate = project.getFile(getPagePropertyPath());
        if (candidate != null) {
            acceptAndValidate(candidate);
        }
    }

    @Override
    public String getMarkerType() {
        return PAGE_PROPERTY_VALIDATION_MARKER;
    }

}
