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
import org.codehaus.groovy.eclipse.ui.decorators.GroovyPluginImages;
import org.codehaus.groovy.eclipse.wizards.NewClassWizardPage;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.wizards.NewElementWizard;
import org.eclipse.jface.viewers.IStructuredSelection;

public class NewGroovyClassWizard extends NewElementWizard {

    private NewClassWizardPage fPage;

    public NewGroovyClassWizard() {
        super();
        setWindowTitle(Messages.createNewGroovyClass);
        setDialogSettings(JavaPlugin.getDefault().getDialogSettings());
        setDefaultPageImageDescriptor(GroovyPluginImages.DESC_NEW_GROOVY_ELEMENT);
    }

    @Override
    public void addPages() {
        super.addPages();
        fPage = new NewClassWizardPage();
        addPage(fPage);
        fPage.init(getSelection());
        IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
        IFolder srcFolder = RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class).getResource();
        fPage.setPackageFragmentRoot(project.getPackageFragmentRoot(
                srcFolder), false);
        IStructuredSelection selection = getSelection();
        Object firstElement = selection.getFirstElement();
        if (firstElement instanceof IAdaptable && ((IAdaptable) firstElement).getAdapter(IPackageFragment.class) != null) {
            fPage.setPackageFragment(((IAdaptable) firstElement).getAdapter(IPackageFragment.class), true);
        } else {
            fPage.setPackageFragment(srcFolder.getAdapter(IPackageFragment.class), true);
        }

    }

    @Override
    protected boolean canRunForked() {
        return !fPage.isEnclosingTypeSelected();
    }

    @Override
    protected void finishPage(IProgressMonitor monitor) throws InterruptedException, CoreException {
        fPage.createType(monitor); // use the full progress monitor
    }

    @Override
    public boolean performFinish() {
        warnAboutTypeCommentDeprecation();
        boolean res = super.performFinish();
        if (res) {
            IResource resource = fPage.getModifiedResource();
            if (resource != null) {
                selectAndReveal(resource);
                openResource((IFile) resource);
            }
        }
        return res;
    }

    @Override
    public IJavaElement getCreatedElement() {
        return fPage.getCreatedType();
    }
}
