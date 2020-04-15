/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.swt;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;


/**
 * @author Romain Bioteau
 *
 */
/**
 * Simple realm implementation that will set itself as default when constructed. Invoke {@link #dispose()} to remove the realm from being the default. Does not
 * support asyncExec(...).
 */
public class DefaultRealm extends Realm {

    private final Realm previousRealm;

    public DefaultRealm(final Display display) {
        final Realm displayRealm = SWTObservables.getRealm(display);
        previousRealm = super.setDefault(displayRealm);
    }

    /**
     * @return always returns true
     */
    @Override
    public boolean isCurrent() {
        return true;
    }

    @Override
    protected void syncExec(final Runnable runnable) {
        runnable.run();
    }

    /**
     * @throws UnsupportedOperationException
     */
    @Override
    public void asyncExec(final Runnable runnable) {
        throw new UnsupportedOperationException("asyncExec is unsupported");
    }

    /**
     * Removes the realm from being the current and sets the previous realm to the default.
     */
    public void dispose() {
        if (getDefault() == this) {
            setDefault(previousRealm);
        }
    }
}