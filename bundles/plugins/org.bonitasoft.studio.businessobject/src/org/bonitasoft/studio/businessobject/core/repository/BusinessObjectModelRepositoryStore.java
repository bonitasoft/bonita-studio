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
    public BusinessObjectModelFileStore createRepositoryFileStore(final String fileName) {
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

    public BusinessObjectModelFileStore getChildByQualifiedName(final String qualifiedName) {
        for (final IRepositoryFileStore file : getChildren()) {
            final BusinessObjectModelFileStore businessObjectFileStore = (BusinessObjectModelFileStore) file;
            if (businessObjectFileStore.getBusinessObject(qualifiedName) != null) {
                return businessObjectFileStore;
            }
        }
        return null;
    }

    @Override
    protected BusinessObjectModelFileStore doImportInputStream(final String fileName, final InputStream inputStream) {
        final BusinessObjectModelFileStore fileStore = super.doImportInputStream(fileName, inputStream);
        try {
            new DeployBDMOperation(fileStore).run(Repository.NULL_PROGRESS_MONITOR);
        } catch (final InvocationTargetException e) {
            BonitaStudioLog.error(e);
        } catch (final InterruptedException e) {
            BonitaStudioLog.error(e);
        }
        return fileStore;
    }

}
