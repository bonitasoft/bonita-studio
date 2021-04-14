/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.document.refactoring;

import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.refactoring.core.RefactorPair;

public class DocumentRefactorPair extends RefactorPair<Document, Document> {

    public DocumentRefactorPair(final Document newValue, final Document oldValue) {
        super(newValue, oldValue);
    }

    @Override
    public String getOldValueName() {
        return getOldValue().getName();
    }

    @Override
    public String getNewValueName() {
        if (getNewValue() != null) {
            return getNewValue().getName();
        } else {
            return super.getNewValueName();
        }
    }

    @Override
    public boolean canBeContainedInScript() {
        return false;
    }
}
