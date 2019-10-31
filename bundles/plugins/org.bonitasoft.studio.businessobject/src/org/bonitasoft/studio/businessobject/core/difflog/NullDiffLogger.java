/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.core.difflog;

import org.bonitasoft.engine.bdm.model.field.RelationField.Type;

public class NullDiffLogger implements IDiffLogger {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger#boAdded(java.lang.String)
     */
    @Override
    public void boAdded(String qualifiedName) {
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger#boRemoved(java.lang.String)
     */
    @Override
    public void boRemoved(String qualifiedName) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger#boRenamed(java.lang.String, java.lang.String)
     */
    @Override
    public void boRenamed(String oldQualifiedName, String newQualifiedName) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger#fieldAdded(java.lang.String, java.lang.String)
     */
    @Override
    public void fieldAdded(String qualifiedName, String fieldName) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger#fieldRenamed(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void fieldRenamed(String qualifiedName, String oldValue, String newValue) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger#fieldTypeChanged(java.lang.String, java.lang.String, java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public void fieldTypeChanged(String qualifiedName, String fieldName, Object oldType, Object newType) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger#fieldRelationTypeChanged(java.lang.String, java.lang.String,
     * org.bonitasoft.engine.bdm.model.field.RelationField.Type, org.bonitasoft.engine.bdm.model.field.RelationField.Type)
     */
    @Override
    public void fieldRelationTypeChanged(String qualifiedName, String fieldName, String referencedType, Type oldType,
            Type newType) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger#fieldRemoved(java.lang.String, java.lang.String)
     */
    @Override
    public void fieldRemoved(String qualifiedName, String fieldName) {

    }

}
