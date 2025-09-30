/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.team;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.common.repository.core.internal.team.GitProjectImpl;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public interface GitProject extends IAdaptable {

    static final String PARENT_GITIGNORE_TEMPLATE = ".gitignore.parent.template";
    static final String GITIGNORE_TEMPLATE = ".gitignore.template";
    
    IRunnableWithProgress newConnectProviderOperation() throws CoreException;
    
    IRunnableWithProgress newDiconnectProviderOperation() throws CoreException;

    void createDefaultIgnoreFile() throws CoreException;
    
    File getGitDir();
    
    void commitAll(String commitMessage, IProgressMonitor monitor) throws CoreException;
    
    public static URL getGitignoreTemplateFileURL() throws IOException {
        return FileLocator.toFileURL(GitProjectImpl.class.getResource(GITIGNORE_TEMPLATE));
    }

    public static URL getParentGitIgnoreTemplate() throws IOException {
        return FileLocator.toFileURL(GitProjectImpl.class.getResource(PARENT_GITIGNORE_TEMPLATE));
    }

}
