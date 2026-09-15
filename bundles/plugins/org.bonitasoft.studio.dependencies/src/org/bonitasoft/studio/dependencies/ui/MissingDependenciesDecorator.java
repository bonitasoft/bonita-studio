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
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.studio.dependencies.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.util.FragmentUtils;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IColorDecorator;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class MissingDependenciesDecorator implements ILabelDecorator, IColorDecorator {

    private Image errorIcon;
    private Image warningIcon;
    private Color conflictColor;
    private DependencyRepositoryStore store;
    /** Versions selected by the user, indexed by artifact base name. */
    private Map<String, Set<String>> selectedVersionsByBase = Map.of();

    public MissingDependenciesDecorator(DependencyRepositoryStore store) {
        this.store = store;
    }

    public MissingDependenciesDecorator() {

    }

    /**
     * Declare the versions currently selected, so that the rows can be flagged or dimmed.
     * <p>
     * The index is computed once per refresh by the caller rather than derived here, because a label
     * decorator is asked for every visible row and walking the whole configuration each time would be
     * quadratic. Use {@code FragmentUtils.selectedVersionsByArtifactBase(configuration)} to build it, so
     * that what is shown matches the versions the pom generation is able to pin.
     *
     * @param selectedVersionsByBase selected versions indexed by artifact base name, never {@code null}
     */
    public void setSelectedVersionsByBase(Map<String, Set<String>> selectedVersionsByBase) {
        this.selectedVersionsByBase = selectedVersionsByBase == null ? Map.of() : selectedVersionsByBase;
    }

    private Set<String> selectedVersionsOf(String lib) {
        return selectedVersionsByBase.getOrDefault(FragmentUtils.extractArtifactBase(lib), Set.of());
    }

    /**
     * A library is flagged only on its selected rows: an unselected version takes no part in the
     * conflict, and telling the user otherwise would point at the wrong row to fix.
     * <p>
     * The wizard now unselects the other versions as soon as one is picked, so this only remains
     * reachable for a configuration that already carried the conflict, or for a whole container checked
     * at once.
     * </p>
     * <p>
     * The row must carry a version of its own, and that version must be among the selected ones: a jar
     * whose file name holds no version shares its base with every version of the library -- the
     * generated connector jar does -- without taking part in the conflict, and a jar the user excluded
     * elsewhere is not selected however this fragment is flagged.
     * </p>
     */
    protected boolean isSelectedInSeveralVersions(Fragment fragment) {
        if (!fragment.isExported()) {
            return false;
        }
        String lib = fragment.getValue();
        Set<String> selectedVersions = selectedVersionsOf(lib);
        return FragmentUtils.extractArtifactVersion(lib)
                .map(version -> selectedVersions.contains(version) && selectedVersions.size() > 1)
                .orElse(Boolean.FALSE);
    }

    /**
     * An unselected version of a library another version of which is already selected.
     * <p>
     * Such a row cannot be picked without dropping the version in place, which is exactly what the
     * wizard does on click. Dimming it says so before the click rather than after.
     * </p>
     */
    protected boolean isShadowedByAnotherVersion(Fragment fragment) {
        if (fragment.isExported()) {
            return false;
        }
        String lib = fragment.getValue();
        Set<String> selectedVersions = selectedVersionsOf(lib);
        Optional<String> version = FragmentUtils.extractArtifactVersion(lib);
        return version.map(v -> selectedVersions.stream().anyMatch(selected -> !selected.equals(v)))
                .orElse(Boolean.FALSE);
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
        if (conflictColor != null) {
            conflictColor.dispose();
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

    /** What a row is reported for, most specific first: the first match wins. */
    private enum Decoration {
        MISSING, CONFLICTING, SHADOWED, IN_RUNTIME, IN_RUNTIME_OTHER_VERSION, NONE
    }

    /**
     * Read the row once, so that its icon and its text cannot report two different things.
     * <p>
     * {@code SHADOWED} carries no icon and no warning: the row stepped aside on its own, and there is
     * nothing to fix until it is picked again. It still has to be told apart from {@code NONE}, or the
     * runtime checks below would claim the row instead.
     * </p>
     */
    private Decoration classify(Fragment fragment, String lib) {
        DependencyFileStore resolvedDep = store.getChild(lib, true);
        if (isDependencyMissing(resolvedDep, lib, fragment)) {
            return Decoration.MISSING;
        }
        if (isSelectedInSeveralVersions(fragment)) {
            return Decoration.CONFLICTING;
        }
        if (isShadowedByAnotherVersion(fragment)) {
            return Decoration.SHADOWED;
        }
        if (isInRuntimeContainer(resolvedDep, lib)) {
            return Decoration.IN_RUNTIME;
        }
        if (isInRuntimeContainerWithAnotherVersion(resolvedDep, lib)) {
            return Decoration.IN_RUNTIME_OTHER_VERSION;
        }
        return Decoration.NONE;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image, java.lang.Object)
     */
    @Override
    public Image decorateImage(Image image, Object element) {
        if (element instanceof Fragment fragment && image != null) {
            String lib = fragment.getValue();
            if (lib.endsWith(DependencyRepositoryStore.JAR_EXT)) {
                return switch (classify(fragment, lib)) {
                    case MISSING -> getErrorDecoratedImage(image);
                    case CONFLICTING, IN_RUNTIME, IN_RUNTIME_OTHER_VERSION -> getWarningDecoratedImage(image);
                    case SHADOWED, NONE -> image;
                };
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

    /**
     * Dim the versions that cannot be added to the selection, and flag in red the ones that conflict.
     * <p>
     * Every other decoration on this tree reports something the user may legitimately live with -- a jar
     * already provided by the runtime, or provided in another version. Two versions of the same library
     * cannot be honoured at all: the pom generation abstains and Maven decides what lands in the
     * business archive. A dimmed row therefore reads as unavailable, before the click; red is kept for
     * the conflict itself, which only a pre-existing configuration can still carry.
     * </p>
     * <p>
     * The red is the one the Studio uses for every other error, rather than
     * {@code JFacePreferences.ERROR_COLOR}: nothing puts that key in the JFace registry unless the user
     * switches theme, so the lookup came back empty and the row stayed black. The grey is the platform
     * disabled colour, so it stays legible in both themes and needs no disposal.
     * </p>
     */
    @Override
    public Color decorateForeground(Object element) {
        if (element instanceof Fragment fragment) {
            String lib = fragment.getValue();
            if (lib != null && lib.endsWith(DependencyRepositoryStore.JAR_EXT)) {
                if (isSelectedInSeveralVersions(fragment)) {
                    if (conflictColor == null) {
                        conflictColor = new Color(ColorConstants.ERROR_RGB);
                    }
                    return conflictColor;
                }
                if (isShadowedByAnotherVersion(fragment)) {
                    return Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_DISABLED_FOREGROUND);
                }
            }
        }
        return null;
    }

    @Override
    public Color decorateBackground(Object element) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String, java.lang.Object)
     */
    @Override
    public String decorateText(String text, Object element) {
        if (element instanceof Fragment fragment && text != null) {
            String lib = fragment.getValue();
            if (lib.endsWith(DependencyRepositoryStore.JAR_EXT)) {
                return switch (classify(fragment, lib)) {
                    case MISSING -> suffixed(text, Messages.missingDependenciesInRepository);
                    case CONFLICTING -> suffixed(text, Messages.dependencySelectedInSeveralVersions);
                    case SHADOWED -> suffixed(text, Messages.dependencyAnotherVersionSelected);
                    case IN_RUNTIME -> suffixed(text, Messages.dependencyExistsInRuntimeContainer);
                    case IN_RUNTIME_OTHER_VERSION -> suffixed(text,
                            Messages.dependencyExistsInRuntimeContainerWithAnotherVersion);
                    case NONE -> text;
                };
            }
        }
        return null;
    }

    private static String suffixed(String text, String decoration) {
        return text + " (" + decoration + ")";
    }

}
