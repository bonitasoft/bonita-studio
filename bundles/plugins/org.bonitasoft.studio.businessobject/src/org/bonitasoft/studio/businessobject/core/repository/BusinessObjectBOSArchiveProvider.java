/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
 */
public class BusinessObjectBOSArchiveProvider implements IBOSArchiveFileStoreProvider {

    @Override
    public Set<IRepositoryFileStore> getFileStoreForConfiguration(final AbstractProcess process,
            final Configuration configuration) {
        final Set<IRepositoryFileStore> result = new HashSet<>();
        final List<BusinessObjectData> allBusinessObjectData = ModelHelper.getAllItemsOfType(process,
                ProcessPackage.Literals.BUSINESS_OBJECT_DATA);
        final BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> store = getBusinessObjectDefinitionStore();
        for (final BusinessObjectData data : allBusinessObjectData) {
            store.getChildByQualifiedName(data.getClassName()).ifPresent(result::add);
        }
        return result;
    }

    protected BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> getBusinessObjectDefinitionStore() {
        return RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class);
    }

}
