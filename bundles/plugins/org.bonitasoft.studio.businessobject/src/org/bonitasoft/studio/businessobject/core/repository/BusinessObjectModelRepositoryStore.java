/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.core.repository;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 * 
 */
public class BusinessObjectModelRepositoryStore extends AbstractRepositoryStore<BusinessObjectModelFileStore> {

    private static final String STORE_NAME = "bdm";

    public static final String BDM_TYPE_EXTENSION = "zip";

    private static final Set<String> extensions = new HashSet<String>();
    static {
        extensions.add(BDM_TYPE_EXTENSION);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public BusinessObjectModelFileStore createRepositoryFileStore(String fileName) {
        return new BusinessObjectModelFileStore(fileName, this);
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
        return Messages.businessObjectRepositoryStoreName;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("bdm.png", BusinessObjectPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    public BusinessObjectModelFileStore getChildByQualifiedName(String qualifiedName) {
        for (IRepositoryFileStore file : getChildren()) {
            BusinessObjectModelFileStore businessObjectFileStore = (BusinessObjectModelFileStore) file;
            if (businessObjectFileStore.getBusinessObject(qualifiedName) != null) {
                return businessObjectFileStore;
            }
        }
        return null;
    }

    @Override
    protected BusinessObjectModelFileStore doImportInputStream(String fileName, InputStream inputStream) {
        BusinessObjectModelFileStore fileStore = super.doImportInputStream(fileName, inputStream);
        try {
            new DeployBDMOperation(fileStore).run(Repository.NULL_PROGRESS_MONITOR);
        } catch (InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (InterruptedException e) {
            BonitaStudioLog.error(e);
        }
        return fileStore;
    }

}
