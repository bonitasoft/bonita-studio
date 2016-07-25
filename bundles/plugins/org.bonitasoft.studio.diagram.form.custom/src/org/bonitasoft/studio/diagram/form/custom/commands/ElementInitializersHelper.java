/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.form.custom.commands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bonitasoft.studio.model.process.diagram.form.providers.ElementInitializers;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Aurelien Pupier
 *
 */
public class ElementInitializersHelper {
	
	
	public static void init(EObject toInit){
		try {
			Method[] methods = ElementInitializers.class.getDeclaredMethods();
			for(Method m : methods){
				if(m.getName().matches("init_"+toInit.getClass().getSimpleName().replaceFirst("Custom", "").replaceFirst("Impl", "")+"_2130")){
					try {
						m.invoke(null, toInit);
						break;
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
