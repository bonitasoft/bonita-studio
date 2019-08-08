package org.bonitasoft.studio.businessobject.ui;

import java.util.Objects;

import org.bonitasoft.studio.businessobject.core.repository.AbstractBDMFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

public class BDMPropertyTester extends PropertyTester {

    private static final String BDM_EXITS_PROPERTY = "bdmExists";
    private static final String BDM_FOLDER_PROPERTY = "isBDMFolder";
    private static final String BDM_FILE_PROPERTY = "isBDMFile";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        BusinessObjectModelRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        switch (property) {
            case BDM_EXITS_PROPERTY:
                return store.getChild(BusinessObjectModelFileStore.BOM_FILENAME) == null;
            case BDM_FOLDER_PROPERTY:
                return isBDMFolder(receiver, store);
            case BDM_FILE_PROPERTY:
                return isBDMFile(receiver, store);
            default:
                return false;
        }
    }

    private boolean isBDMFolder(Object receiver, BusinessObjectModelRepositoryStore store) {
        if (receiver instanceof IAdaptable) {
            return Objects.equals(store.getResource(), ((IAdaptable) receiver).getAdapter(IResource.class));
        }
        return false;
    }

    private boolean isBDMFile(Object receiver, BusinessObjectModelRepositoryStore store) {
        if (receiver instanceof IAdaptable) {
            AbstractBDMFileStore child = store.getChild(BusinessObjectModelFileStore.BOM_FILENAME);
            return child != null && Objects.equals(child.getResource(),
                    ((IAdaptable) receiver).getAdapter(IResource.class));
        }
        return false;
    }

}
