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
package org.bonitasoft.studio.condition.resources;

import java.util.List;

import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.util.Strings;

/**
 * @author Romain Bioteau
 *
 */
public class ProcQualifiedNameConverter implements IQualifiedNameConverter {


	/**
	 * Converts the given qualified name to a string.
	 * 
	 * @exception IllegalArgumentException
	 *                when the qualified name is null.
	 */
	public String toString(QualifiedName qualifiedName) {
		if (qualifiedName == null)
			throw new IllegalArgumentException("Qualified name cannot be null");
		return qualifiedName.toString(getDelimiter());
	}

	/**
	 * Splits the given string into segments and returns them as a {@link QualifiedName}.
	 * 
	 * @exception IllegalArgumentException
	 *                if the input is empty or null.
	 */
	public QualifiedName toQualifiedName(String qualifiedNameAsString) {
		if (qualifiedNameAsString == null)
			throw new IllegalArgumentException("Qualified name cannot be null");
		if (qualifiedNameAsString.equals(""))
			throw new IllegalArgumentException("Qualified name cannot be empty");
		if (Strings.isEmpty(getDelimiter()))
			return QualifiedName.create(qualifiedNameAsString);
		List<String> segs = getDelimiter().length() == 1 ? Strings.split(qualifiedNameAsString, getDelimiter()
				.charAt(0)) : Strings.split(qualifiedNameAsString, getDelimiter());
		return QualifiedName.create(segs);
	}

	public String getDelimiter() {
		return ".";
	}

}
