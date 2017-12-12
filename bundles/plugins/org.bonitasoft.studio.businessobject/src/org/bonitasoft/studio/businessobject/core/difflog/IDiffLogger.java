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

public interface IDiffLogger {

    void boAdded(String qualifiedName);

    void boRemoved(String qualifiedName);

    void boRenamed(String oldQualifiedName, String newQualifiedName);

    void fieldAdded(String qualifiedName, String fieldName);

    void fieldRenamed(String qualifiedName, String oldValue, String newValue);

    void fieldTypeChanged(String qualifiedName, String fieldName, Object oldType, Object newType);

    void fieldRelationTypeChanged(String qualifiedName, String fieldName, String referencedType, Type oldType, Type newType);

    void fieldRemoved(String qualifiedName, String fieldName);
}
