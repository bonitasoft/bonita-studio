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
package org.bonitasoft.studio.connectors.ui;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IMethod;

/**
 * @author Baptiste Mesta
 *
 */
public class ReflectionUtil {

	/**
	 * @param field
	 * @param value
	 * @return 
	 */
	public static Object evaluate(IField field, Object value) {
		try {
			Field rField = value.getClass().getField(field.getElementName());
			return rField.get(value);
		} catch (SecurityException e) {
			BonitaStudioLog.error(e);
		} catch (NoSuchFieldException e) {
			Field rField;
			try {
				rField = value.getClass().getDeclaredField(field.getElementName());
				rField.setAccessible(true);
				return rField.get(value);
			} catch (SecurityException e1) {
				BonitaStudioLog.error(e1);
			} catch (NoSuchFieldException e1) {
				BonitaStudioLog.error(e1);
			} catch (IllegalArgumentException e1) {
				BonitaStudioLog.error(e1);
			} catch (IllegalAccessException e1) {
				BonitaStudioLog.error(e1);
			}
		} catch (IllegalArgumentException e) {
			BonitaStudioLog.error(e);
		} catch (IllegalAccessException e) {
			BonitaStudioLog.error(e);
		}
		return null;
	}

	/**
	 * @param first
	 * @param second
	 * @return 
	 */
	public static Object evaluate(IMethod method, Object object) {
		try {
			Method rMethod = object.getClass().getMethod(method.getElementName());
			return rMethod.invoke(object);
		} catch (SecurityException e) {
			BonitaStudioLog.error(e);
		} catch (NoSuchMethodException e) {
			BonitaStudioLog.error(e);
		} catch (IllegalArgumentException e) {
			BonitaStudioLog.error(e);
		} catch (IllegalAccessException e) {
			BonitaStudioLog.error(e);
		} catch (InvocationTargetException e) {
			BonitaStudioLog.error(e);
		}
		return null;
		
	}

}
