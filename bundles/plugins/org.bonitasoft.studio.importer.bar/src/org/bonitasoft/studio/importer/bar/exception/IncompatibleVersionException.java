/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bar.exception;

import org.bonitasoft.studio.importer.bar.i18n.Messages;


/**
 * @author Romain Bioteau
 *
 */
public class IncompatibleVersionException extends Exception {

    private static final long serialVersionUID = 74048719962320387L;
    private final String sourceVersion;
    private final String supportedVersion;

    public IncompatibleVersionException(String sourceVersion,String supportedVersion){
        this.sourceVersion = sourceVersion;
        this.supportedVersion = supportedVersion;
    }

    @Override
    public String getMessage() {
        return Messages.bind(Messages.unsupportedVersionMessage, sourceVersion,supportedVersion);
    }
}
