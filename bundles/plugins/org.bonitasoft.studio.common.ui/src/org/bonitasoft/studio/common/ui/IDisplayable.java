/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.ui;

import static java.util.function.Predicate.not;

import java.util.Optional;

import org.bonitasoft.studio.common.Strings;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

@FunctionalInterface
public interface IDisplayable {

    String getDisplayName();

    default Image getIcon() {
        return null;
    }

    default StyledString getStyledString() {
        return new StyledString(getDisplayName());
    }

    /**
     * Try and adapt an object (which may eventually implement {@link IAdaptable}) to an {@link IDisplayable}
     * 
     * @param adaptable the object to adapt
     * @return {@link IDisplayable} adapter or empty
     */
    public static Optional<IDisplayable> adapt(Object adaptable) {
        IDisplayable adapter = Adapters.adapt(adaptable, IDisplayable.class);
        return Optional.ofNullable(adapter);
    }

    /**
     * Try and adapt an object (which may eventually implement {@link IAdaptable}) to an {@link IDisplayable} then get the display name.
     * 
     * @param adaptable the object to adapt
     * @return display name or empty
     */
    public static Optional<String> toDisplayName(Object adaptable) {
        IDisplayable adapter = Adapters.adapt(adaptable, IDisplayable.class);
        return Optional.ofNullable(adapter).map(IDisplayable::getDisplayName).filter(not(Strings::isNullOrEmpty));
    }
}
