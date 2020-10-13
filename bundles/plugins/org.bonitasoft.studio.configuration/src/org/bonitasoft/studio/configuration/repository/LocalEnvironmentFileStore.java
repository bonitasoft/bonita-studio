/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration.repository;

import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.environment.EnvironmentFactory;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;

/**
 * The purpose of this class is to display an element "Local.xml" in the project explorer.
 * The local environment doesn't have a real fileStore, it is not persisted, this class is a 'fake' file store.
 */
public class LocalEnvironmentFileStore extends EnvironmentFileStore {

    public LocalEnvironmentFileStore(IRepositoryStore<EnvironmentFileStore> store) {
        super(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON + ".xml", store);
    }

    @Override
    public void save(Object content) {
    }

    @Override
    protected void doSave(Object content) {
    }

    @Override
    protected Environment doGetContent() throws ReadFileStoreException {
        Environment environment = EnvironmentFactory.eINSTANCE.createEnvironment();
        environment.setName(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON);
        return environment;
    }
    
    
    @Override
    public String getDisplayName() {
        return ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON;
    }
}
