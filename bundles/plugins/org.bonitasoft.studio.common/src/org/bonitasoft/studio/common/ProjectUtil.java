/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common;

import java.io.File;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.bonitasoft.console.common.server.preferences.constants.WebBonitaConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.w3c.dom.Document;

/**
 * @author Mickael Istria
 *
 */
public class ProjectUtil {


	public static final String NEW_WORKSPACE = "NEW_WORKSPACE";
	/**
	 * 
	 */
	private static final String JETTY_WORK_FOLDER_NAME = "work";
	public static final String WEBAPP_PROJECT = "MyWebapp"; //$NON-NLS-1$
	private static final String WORK_FOLDER_NAME = "work";

	public static File getBonitaWebappeProject() {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final File workspaceRoot = workspace.getRoot().getLocation().toFile() ;
		final File jettyFolder = new File(workspaceRoot,WEBAPP_PROJECT) ;
		if(!jettyFolder.exists()){
			jettyFolder.mkdir() ;
		}

		final File webappWorkFolder = new File(jettyFolder,JETTY_WORK_FOLDER_NAME);
		if(!webappWorkFolder.exists()){
			webappWorkFolder.mkdir() ;
		}
		return jettyFolder;
	}

	/**
	 * @return
	 */
	public static String generateTypeName(Type clazz) {
		if (clazz.equals(Document.class)) {
			return Document.class.getName();
		} else if(clazz.equals(ArrayList.class)){
			return ArrayList.class.getName();
		}else if (clazz instanceof Class<?>) {
			return ((Class<?>)clazz).getSimpleName();
		} else if (clazz instanceof ParameterizedType) {
			final ParameterizedType type = (ParameterizedType)clazz;
			final StringBuilder builder = new StringBuilder();
			builder.append(generateTypeName(type.getRawType()));
			builder.append('<');
			for (final Type paramType : type.getActualTypeArguments()) {
				builder.append(generateTypeName(paramType));
				builder.append(", ");
			}
			builder.replace(builder.length() - 2, builder.length(), ">");
			return builder.toString();
		} else if (clazz instanceof GenericArrayType) {
			final GenericArrayType type = (GenericArrayType)clazz;
			return generateTypeName(type.getGenericComponentType()) + "[]";
		} else {
			return "?";
		}
	}

	public static File getBonitaStudioWorkFolder() {
		final File workFolder = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString() + File.separator + WORK_FOLDER_NAME);
		if(!workFolder.exists()){
			workFolder.mkdirs();
		}
		return workFolder;
	}

    public static IFolder getBonitaStudioWorkIFolder() {
        final IFolder workFolder = ResourcesPlugin.getWorkspace().getRoot().getFolder(Path.fromOSString("/" + WORK_FOLDER_NAME));
        if (!workFolder.exists()) {
            try {
                workFolder.create(true, true, new NullProgressMonitor());
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return workFolder;
    }

	public static Bundle getConsoleLibsBundle() {
		return FrameworkUtil.getBundle(WebBonitaConstants.class);
	}

}
