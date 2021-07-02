/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.environment.util.EnvironmentAdapterFactory;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class EnvironmentRepositoryStore extends AbstractEMFRepositoryStore<EnvironmentFileStore> {

    private static final String STORE_NAME = "environements";
    private static final Set<String> extensions = new HashSet<>();
    public static final String ENV_EXT = "xml";
    private LocalEnvironmentFileStore localEnvironmentFileStore;

    static {
        extensions.add(ENV_EXT);
    }

    public EnvironmentRepositoryStore() {
        localEnvironmentFileStore = new LocalEnvironmentFileStore(this);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public EnvironmentFileStore createRepositoryFileStore(String fileName) {
        return new EnvironmentFileStore(fileName, this);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.environments;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("environment.png", ConfigurationPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    @Override
    protected void addAdapterFactory(ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new EnvironmentAdapterFactory());
    }

    @Override
    public List<EnvironmentFileStore> getChildren() {
        List<EnvironmentFileStore> children = new ArrayList<>();
        children.add(localEnvironmentFileStore);
        children.addAll(super.getChildren());
        return children;
    }

    @Override
    public EnvironmentFileStore getChild(String fileName, boolean force) {
        if (Objects.equals(fileName, localEnvironmentFileStore.getName())) {
            return localEnvironmentFileStore;
        }
        return super.getChild(fileName, force);
    }

}
