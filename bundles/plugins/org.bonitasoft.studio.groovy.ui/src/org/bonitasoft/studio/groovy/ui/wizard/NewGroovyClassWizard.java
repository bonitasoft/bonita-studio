/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.groovy.ui.wizard;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.codehaus.groovy.eclipse.wizards.NewTypeWizard;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jface.viewers.IStructuredSelection;

public class NewGroovyClassWizard extends NewTypeWizard {

    public NewGroovyClassWizard() {
        super();
        setWindowTitle(Messages.createNewGroovyClass);
    }

    @Override
    public void addPages() {
    	 PageOne page = new PageOne(getSelection());
    	 IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
         IFolder srcFolder = RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class).getResource();
         page.setPackageFragmentRoot(project.getPackageFragmentRoot(
                 srcFolder), false);
         IStructuredSelection selection = getSelection();
         Object firstElement = selection.getFirstElement();
         if (firstElement instanceof IAdaptable && ((IAdaptable) firstElement).getAdapter(IPackageFragment.class) != null) {
        	 page.setPackageFragment(((IAdaptable) firstElement).getAdapter(IPackageFragment.class), true);
         } else {
        	 page.setPackageFragment(srcFolder.getAdapter(IPackageFragment.class), true);
         }
		addPage(page);
    	addPage(new PageTwo());
    }

}
