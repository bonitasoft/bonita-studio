/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.repository.themes.exceptions;

/**
 * @author Baptiste Mesta
 *
 */
public class ThemeCreationException extends ThemeRepositoryException {

	/**
	 * @param message
	 */
	public ThemeCreationException(String message) {
		super(message);
	}
	
	/**
	 * @param message 
	 * @param cause 
	 * 
	 */
	public ThemeCreationException(String message, Throwable cause) {
		super(message,cause);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6948269000388362732L;

}
