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
package org.bonitasoft.studio.model.process.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.TargetElement;

/**
 * @author Romain Bioteau
 */
public abstract class ConnectionBuilder<T extends Connection, B extends ConnectionBuilder<T, B>> extends ElementBuilder<T, B> {

    public B havingSource(final SourceElement sourceElement) {
        getBuiltInstance().setSource(sourceElement);
        return getThis();
    }

    public B havingSource(final Buildable<? extends SourceElement> sourceElementBuildable) {
        getBuiltInstance().setSource(sourceElementBuildable.build());
        return getThis();
    }

    public B havingTarget(final TargetElement targetElement) {
        getBuiltInstance().setTarget(targetElement);
        return getThis();
    }

    public B havingTarget(final Buildable<? extends TargetElement> targetElementBuildable) {
        getBuiltInstance().setTarget(targetElementBuildable.build());
        return getThis();
    }

}
