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
package org.bonitasoft.studio.application.views.provider;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.codehaus.groovy.eclipse.ui.GroovyNavigatorLabelProvider;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.internal.core.PackageFragment;
import org.eclipse.jdt.internal.ui.navigator.JavaNavigatorLabelProvider;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerProblemsDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;

public class BonitaExplorerLabelProvider extends JavaNavigatorLabelProvider {

    private PackageExplorerProblemsDecorator packageExplorerProblemsDecorator;
    private FileStoreFinder fileStoreFinder;
    private GroovyNavigatorLabelProvider groovyNavigatorLabelProvider;

    public BonitaExplorerLabelProvider() {
        groovyNavigatorLabelProvider = new GroovyNavigatorLabelProvider();
        packageExplorerProblemsDecorator = new PackageExplorerProblemsDecorator();
        fileStoreFinder = new FileStoreFinder();
    }

    @Override
    public void init(ICommonContentExtensionSite commonContentExtensionSite) {
        super.init(commonContentExtensionSite);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.internal.ui.navigator.JavaNavigatorLabelProvider#addListener(org.eclipse.jface.viewers.
     * ILabelProviderListener)
     */
    @Override
    public void addListener(ILabelProviderListener listener) {
        super.addListener(listener);
        packageExplorerProblemsDecorator.addListener(listener);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jdt.internal.ui.navigator.JavaNavigatorLabelProvider#removeListener(org.eclipse.jface.viewers.
     * ILabelProviderListener)
     */
    @Override
    public void removeListener(ILabelProviderListener listener) {
        super.removeListener(listener);
        packageExplorerProblemsDecorator.removeListener(listener);
    }

    @Override
    public Image getImage(Object element) {
        if (element instanceof GroovyCompilationUnit) {
            return groovyNavigatorLabelProvider.getImage(element);
        }
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        if (RepositoryManager.getInstance().getCurrentRepository().filter(IRepository::isLoaded).isEmpty()) {
            return super.getImage(element);
        }
        if (!(element instanceof IJavaElement)) {
            if (element instanceof IResource) {
                Optional<? extends IRepositoryFileStore> fileStore = asFileStore(element, repositoryManager);
                if (fileStore.isPresent()) {
                    return fileStore
                            .flatMap(IDisplayable::adapt).map(IDisplayable::getIcon)
                            .map(icon -> packageExplorerProblemsDecorator.decorateImage(icon, element))
                            .orElse(super.getImage(element));
                }
            }
        }
        if (!(element instanceof PackageFragment)) {
            Optional<IRepositoryStore<? extends IRepositoryFileStore>> repositoryStore = repositoryManager
                    .getRepositoryStore(element);
            Optional<IDisplayable> display = repositoryStore.flatMap(IDisplayable::adapt);
            if (display.isPresent()) {
                return packageExplorerProblemsDecorator.decorateImage(display.get().getIcon(), element);
            }
        }
        if (element instanceof IProject) {
            return packageExplorerProblemsDecorator.decorateImage(Pics.getImage(PicsConstants.project), element);
        }
        return super.getImage(element);
    }

    @Override
    public StyledString getStyledText(Object element) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        if (repositoryManager.getCurrentRepository().filter(IRepository::isLoaded).isEmpty()) {
            return super.getStyledText(element);
        }
        if (repositoryManager.getCurrentRepository()
                .map(IRepository::getProject)
                .filter(currentProject -> Objects.equals(currentProject, element))
                .isPresent()) {
            return Adapters.adapt(repositoryManager.getCurrentRepository().get(), IDisplayable.class).getStyledString();
        }

        if (!(element instanceof IPackageFragment)) {
            Optional<IRepositoryStore<? extends IRepositoryFileStore>> repositoryStore = repositoryManager
                    .getRepositoryStore(element);
            if (repositoryStore.isPresent()) {
                IRepositoryStore<? extends IRepositoryFileStore> iRepositoryStore = repositoryStore.get();
                IDisplayable displayable = Adapters.adapt(iRepositoryStore, IDisplayable.class);
                if (displayable != null) {
                    return displayable.getStyledString();
                } else {
                    return new StyledString(iRepositoryStore.getName());
                }
            }
            return asFileStore(element, repositoryManager)
                    .filter(fStore -> Objects.equals(fStore.getResource(), element))
                    .flatMap(IDisplayable::adapt)
                    .map(IDisplayable::getStyledString)
                    .orElse(super.getStyledText(element));
        }
        return super.getStyledText(element);
    }

    private Optional<? extends IRepositoryFileStore> asFileStore(Object element, RepositoryManager repositoryManager) {
        return Optional.ofNullable(element)
                .filter(IResource.class::isInstance)
                .map(IResource.class::cast)
                .flatMap(resource -> fileStoreFinder.findFileStore(resource,
                        repositoryManager.getCurrentRepository().orElseThrow()));
    }

    public static boolean isFolder(Object element, String folderName) {
        return element instanceof IFolder
                && ((IFolder) element).getParent() != null
                && Path.fromOSString(folderName).equals(((IFolder) element).getProjectRelativePath())
                && !((IFolder) element).getName().startsWith(".");
    }

}
