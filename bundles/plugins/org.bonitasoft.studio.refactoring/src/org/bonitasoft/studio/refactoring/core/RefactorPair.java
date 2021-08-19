/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.refactoring.core;

import static com.google.common.base.Preconditions.checkArgument;

public class RefactorPair<T, Y> {

    public static final String EMPTY_VALUE = "     ";
    private final T newValue;
    private final Y oldValue;

    /**
     * @param newValue, if null it means that the oldValue need to be removed
     * @param oldValue
     */
    public RefactorPair(final T newValue, final Y oldValue) {
        checkArgument(oldValue != null);
        this.newValue = newValue;
        this.oldValue = oldValue;
    }

    public T getNewValue() {
        return newValue;
    }

    public Y getOldValue() {
        return oldValue;
    }

    /**
     * @return the old value name used in scripts
     */
    public String getOldValueName() {
        return oldValue.toString();
    }

    /**
     * @return the new value name to use in scripts
     */
    public String getNewValueName() {
        return newValue != null ? newValue.toString() : EMPTY_VALUE;
    }

    public boolean canBeContainedInScript() {
        return true;
    }

}
