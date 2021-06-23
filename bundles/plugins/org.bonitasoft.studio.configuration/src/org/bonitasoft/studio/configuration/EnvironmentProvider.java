/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration;

import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.DefaultEnvironmentProvider;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.extension.IEnvironmentProvider;
import org.bonitasoft.studio.configuration.repository.EnvironmentFileStore;
import org.bonitasoft.studio.configuration.repository.EnvironmentRepositoryStore;

public class EnvironmentProvider extends DefaultEnvironmentProvider implements IEnvironmentProvider {

    public List<String> getEnvironment() {
        return RepositoryManager.getInstance().getRepositoryStore(EnvironmentRepositoryStore.class).getChildren()
                .stream()
                .map(t -> {
                    try {
                        return t.getContent();
                    } catch (ReadFileStoreException e) {
                        return null;
                    }
                })
                .map(Environment::getName)
                .collect(Collectors.toList());
    }

}
