/**
 * Copyright (C) 2026 Bonitasoft S.A.
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

package org.bonitasoft.studio.engine;

/**
 * What the extraction of the shipped Tomcat bundle into the workspace did. Explicit rather than a
 * boolean because a copy that failed halfway must not be mistaken for one that completed: the version
 * check reads a partial install as a version mismatch, which sends the user to reinstall the Studio.
 */
public enum BundleCopy {
    /** A usable install was already on disk; nothing was extracted. */
    KEPT,
    EXTRACTED,
    /** The shipped bundle could not be read: nothing usable is on disk. */
    FAILED
}
