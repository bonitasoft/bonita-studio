/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.repository;

import org.bonitasoft.studio.groovy.Messages;

/**
 * @author Romain Bioteau
 *
 */
public class ProvidedGroovyRepositoryStore extends GroovyRepositoryStore {

    private static final String STORE_NAME = "src-providedGroovy" ;
    public static final String EXPORTED_PROVIDED_JAR_NAME = "providedscripts.jar";

    @Override
    public boolean isShared() {
        return false ;
    }

    @Override
    public boolean canBeShared() {
        return false ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.groovyProvidedScriptRepository;
    }

    @Override
    public boolean canBeExported() {
        return false ;
    }


}
