/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.repository;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Collections;
import java.util.Set;

import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.swt.graphics.Image;

public class ApplicationRepositoryStore extends AbstractRepositoryStore<ApplicationFileStore> {

    private static final String XML_EXTENSION = "xml";

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return "applications"; //$NON-NLS-N$
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IDisplayable#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.applicationStoreName;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IDisplayable#getIcon()
     */
    @Override
    public Image getIcon() {
        return LivingApplicationPlugin.getImage("icons/applicationStore.gif");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public ApplicationFileStore createRepositoryFileStore(String fileName) {
        return new ApplicationFileStore(fileName, this);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return Collections.unmodifiableSet(newHashSet(XML_EXTENSION));
    }

}
