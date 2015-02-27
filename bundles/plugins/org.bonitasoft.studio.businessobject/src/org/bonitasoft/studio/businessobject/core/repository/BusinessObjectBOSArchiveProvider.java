/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.core.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ProcessPackage;

/**
 * @author Romain Bioteau
 * 
 */
public class BusinessObjectBOSArchiveProvider implements IBOSArchiveFileStoreProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider#getFileStoreForConfiguration(org.bonitasoft.studio.model.process.
     * AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public Set<IRepositoryFileStore> getFileStoreForConfiguration(AbstractProcess process, Configuration configuration) {
        Set<IRepositoryFileStore> result = new HashSet<IRepositoryFileStore>();
        List<BusinessObjectData> allBusinessObjectData = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.BUSINESS_OBJECT_DATA);
        BusinessObjectModelRepositoryStore store = getBusinessObjectDefinitionStore();
        for (BusinessObjectData data : allBusinessObjectData) {
            BusinessObjectModelFileStore childByName = store.getChildByQualifiedName(data.getClassName());
            if (childByName != null) {
                result.add(childByName);
            }
        }
        return result;
    }

    protected BusinessObjectModelRepositoryStore getBusinessObjectDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

}
