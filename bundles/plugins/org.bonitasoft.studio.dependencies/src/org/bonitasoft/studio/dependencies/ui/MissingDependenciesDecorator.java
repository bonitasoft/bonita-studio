/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.dependencies.ui;

import java.util.Map;

import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class MissingDependenciesDecorator implements ILabelDecorator {

    private Image errorIcon;
    private Image warningIcon;
    private DependencyRepositoryStore store;

    public MissingDependenciesDecorator(DependencyRepositoryStore store) {
        this.store = store;
    }

    public MissingDependenciesDecorator() {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void addListener(ILabelProviderListener arg0) {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {
        if (errorIcon != null) {
            errorIcon.dispose();
        }
        if (warningIcon != null) {
            warningIcon.dispose();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean isLabelProperty(Object arg0, String arg1) {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void removeListener(ILabelProviderListener arg0) {

    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image, java.lang.Object)
     */
    @Override
    public Image decorateImage(Image image, Object element) {
        if (element instanceof Fragment && image != null) {
            Fragment fragment = (Fragment) element;
            String lib = fragment.getValue();
            if (lib.endsWith(DependencyRepositoryStore.JAR_EXT)) {
                DependencyFileStore resolvedDep = store.getChild(lib, true);
                if (isDependencyMissing(resolvedDep, lib, fragment)) {
                    return getErrorDecoratedImage(image);
                } else if (isInRuntimeContainer(resolvedDep, lib)) {
                    return getWarningDecoratedImage(image);
                } else if (isInRuntimeContainerWithAnotherVersion(resolvedDep, lib)) {
                    return getWarningDecoratedImage(image);
                } else {
                    return image;
                }
            }
        }
        return null;
    }

    protected Image getErrorDecoratedImage(Image image) {
        if (errorIcon == null) {
            Image errorDecoratorImage = FieldDecorationRegistry.getDefault()
                    .getFieldDecoration(FieldDecorationRegistry.DEC_ERROR).getImage();
            errorIcon = new DecorationOverlayIcon(image, ImageDescriptor.createFromImage(errorDecoratorImage),
                    IDecoration.BOTTOM_RIGHT).createImage();
        }
        return errorIcon;
    }

    protected Image getWarningDecoratedImage(Image image) {
        if (warningIcon == null) {
            Image warningDecoratorImage = FieldDecorationRegistry.getDefault()
                    .getFieldDecoration(FieldDecorationRegistry.DEC_WARNING).getImage();
            warningIcon = new DecorationOverlayIcon(image, ImageDescriptor.createFromImage(warningDecoratorImage),
                    IDecoration.BOTTOM_RIGHT).createImage();
        }
        return warningIcon;
    }

    protected boolean isInRuntimeContainerWithAnotherVersion(DependencyFileStore resolvedDep, String lib) {
        if (resolvedDep == null) {
            return false;
        }
        Map<String, String> runtimeDependencies = store.getRuntimeDependencies();
        String libName = store.getLibName(lib);
        String libVersion = store.getLibVersion(lib);
        return runtimeDependencies.containsKey(libName)
                && !runtimeDependencies.get(libName).equals(libVersion);
    }

    protected boolean isInRuntimeContainer(DependencyFileStore resolvedDep, String lib) {
        if (resolvedDep == null) {
            return false;
        }
        Map<String, String> runtimeDependencies = store.getRuntimeDependencies();
        String libName = store.getLibName(lib);
        String libVersion = store.getLibVersion(lib);
        return runtimeDependencies.containsKey(libName)
                && runtimeDependencies.get(libName).equals(libVersion);
    }

    protected boolean isDependencyMissing(DependencyFileStore resolvedDep, String libName, Fragment fragment) {
        if (resolvedDep == null && isGeneratedJar(libName, fragment)) {//Check in custom connector
            return false;
        }
        return resolvedDep == null;
    }

    protected boolean isGeneratedJar(String lib, Fragment fragment) {
        if (!(fragment.eContainer() instanceof FragmentContainer container)) {
            return false;
        }
        String id = container.getId();
        return lib.equals(id + ".jar");
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String, java.lang.Object)
     */
    @Override
    public String decorateText(String text, Object element) {
        if (element instanceof Fragment && text != null) {
            Fragment fragment = (Fragment) element;
            String lib = fragment.getValue();
            if (lib.endsWith(DependencyRepositoryStore.JAR_EXT)) {
                DependencyFileStore resolvedDep = store.getChild(lib, true);
                if (isDependencyMissing(resolvedDep, lib, fragment)) {
                    return text + " (" + Messages.missingDependenciesInRepository + ")";
                } else if (isInRuntimeContainer(resolvedDep, lib)) {
                    return text + " (" + Messages.dependencyExistsInRuntimeContainer + ")";
                } else if (isInRuntimeContainerWithAnotherVersion(resolvedDep, lib)) {
                    return text + " (" + Messages.dependencyExistsInRuntimeContainerWithAnotherVersion + ")";
                } else {
                    return text;
                }
            }
        }
        return null;
    }

}
