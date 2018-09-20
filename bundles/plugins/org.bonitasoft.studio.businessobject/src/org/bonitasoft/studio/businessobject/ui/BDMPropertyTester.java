package org.bonitasoft.studio.businessobject.ui;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.expressions.PropertyTester;


public class BDMPropertyTester extends PropertyTester {


    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        BusinessObjectModelRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        return store.getChild(BusinessObjectModelFileStore.BOM_FILENAME) == null;
    }

}
