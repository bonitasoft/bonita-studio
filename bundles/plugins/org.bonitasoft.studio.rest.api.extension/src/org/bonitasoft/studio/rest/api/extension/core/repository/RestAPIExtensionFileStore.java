/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.repository;

import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.eclipse.core.resources.IProject;

public class RestAPIExtensionFileStore extends CustomPageProjectFileStore<RestAPIExtensionDescriptor> {

    public RestAPIExtensionFileStore(final String restApiName, final RestAPIExtensionRepositoryStore parentStore) {
        super(restApiName, parentStore);
    }

    @Override
    protected RestAPIExtensionDescriptor doGetContent() throws ReadFileStoreException {
        final IProject project = getProject();
        if (!project.exists()) {
            throw new ReadFileStoreException(String.format("Project with name %s does not exist", project.getName()));
        }
        return new RestAPIExtensionDescriptor(project);
    }

    @Override
    protected String getBuildFolder() {
        return "restApiExtension";
    }

}
