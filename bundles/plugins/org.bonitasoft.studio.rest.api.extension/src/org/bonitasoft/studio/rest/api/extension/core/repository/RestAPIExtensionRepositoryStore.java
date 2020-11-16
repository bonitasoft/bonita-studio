/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.repository;

import java.util.List;
import java.util.Optional;

import org.apache.maven.archetype.catalog.Archetype;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.builder.validator.AbstractCustomPageValidator;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetype;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.builder.RestAPIBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.graphics.Image;

public class RestAPIExtensionRepositoryStore extends CustomPageProjectRepositoryStore<RestAPIExtensionFileStore> {

    public static final String STORE_NAME = "restAPIExtensions";

    @Override
    public String getName() {
        return STORE_NAME;
    }

    @Override
    public String getDisplayName() {
        return Messages.restApiExtensionRepositoryName;
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("rest_api_icon7_16x16.png", RestAPIExtensionActivator.getDefault());
    }

    @Override
    public RestAPIExtensionFileStore createRepositoryFileStore(final String restApiName) {
        return new RestAPIExtensionFileStore(restApiName, this);
    }

    @Override
    public Archetype getArchetype() {
        return RestAPIExtensionArchetype.INSTANCE;
    }

    @Override
    public void refreshMarkers() throws CoreException {
        List<RestAPIExtensionFileStore> children = getChildren();
        RestAPIBuilder restAPIBuilder = new RestAPIBuilder();
        for (CustomPageProjectFileStore fileStore : children) {
            for (AbstractCustomPageValidator validator : restAPIBuilder.createValidators(fileStore.getProject(),
                    new NullProgressMonitor())) {
                validator.acceptAndValidate();
            }
        }
    }

    /**
     * @param resource, eg: GET|extension/resourceName
     * @return an Optional with the corresponding file store implementing the given resource
     */
    public Optional<RestAPIExtensionFileStore> findByRestResource(String resource) {
        return getChildren().stream().filter(fStore -> matchesRestResource(resource, fStore)).findFirst();
    }

    private boolean matchesRestResource(String restResource, RestAPIExtensionFileStore fStore) {
        String[] splitted = restResource.split("\\|");
        PathTemplate pathTemplate = new PathTemplate(splitted[1].substring("extension/".length()),
                splitted[0]);
        try {
            return fStore.getContent().getPathTemplates().stream().anyMatch(pathTemplate::equals);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
            return false;
        }
    }
}
