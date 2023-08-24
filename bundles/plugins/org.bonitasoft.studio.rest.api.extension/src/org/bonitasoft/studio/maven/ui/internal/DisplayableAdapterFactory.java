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
package org.bonitasoft.studio.maven.ui.internal;

import java.util.Optional;
import java.util.function.Supplier;

import org.bonitasoft.plugin.analyze.report.model.CustomPage;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.maven.ExtensionProjectDescriptor;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.repository.DependencyRestAPIExtensionFileStore;
import org.bonitasoft.studio.theme.DependencyThemeFileStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

/**
 * Adapts {@link ExtensionProjectFileStore}, {@link RestAPIExtensionRepositoryStore} or {@link ThemeRepositoryStore} to {@link IDisplayable}
 * 
 * @author Vincent Hemery
 */
public class DisplayableAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.isAssignableFrom(IDisplayable.class)) {
            if (adaptableObject instanceof ExtensionProjectFileStore) {
                ExtensionProjectFileStore store = (ExtensionProjectFileStore) adaptableObject;
                IDisplayable display = new IDisplayable() {

                    private JavaUILabelProvider javaUILabelProvider;

                    @Override
                    public String getDisplayName() {
                        if (store instanceof DependencyRestAPIExtensionFileStore
                                || store instanceof DependencyThemeFileStore) {
                            // use display name of the custom page (extension or theme)
                            CustomPage customPage = store.getAdapter(CustomPage.class);
                            if (customPage != null) {
                                return customPage.getDisplayName();
                            }
                        }
                        Supplier<String> getFromName = () -> {
                            if (store.getName() != null && store.getName().indexOf('.') != -1) {
                                return store.getName().substring(0, store.getName().lastIndexOf('.'));
                            }
                            return store.getName();
                        };
                        try {
                            final ExtensionProjectDescriptor content = store.getContent();
                            return Optional.ofNullable(content)
                                    .map(ExtensionProjectDescriptor::getDisplayName)
                                    .orElseGet(getFromName);
                        } catch (final ReadFileStoreException e) {
                            return getFromName.get();
                        }
                    }

                    @Override
                    public Image getIcon() {
                        if (store instanceof DependencyRestAPIExtensionFileStore
                                || store instanceof DependencyThemeFileStore) {
                            return Pics.getImage("binary.png", RestAPIExtensionActivator.getDefault());
                        } else {
                            IProject project = store.getProject();
                            if (javaUILabelProvider == null) {
                                javaUILabelProvider = createJavaUILabelProvider();
                            }
                            if (javaUILabelProvider != null && project.isAccessible() && project.isOpen()) {
                                return javaUILabelProvider.getImage(project);
                            }
                            return Pics.getImage("prj_obj.gif", RestAPIExtensionActivator.getDefault());
                        }
                    }

                    JavaUILabelProvider createJavaUILabelProvider() {
                        return new JavaUILabelProvider();
                    }

                    @Override
                    public StyledString getStyledString() {
                        StyledString styledString = new StyledString(store.getName());
                        var type = store.getContentType() ;
                        if ((store.getProject() == null || !store.getProject().exists()) && store.canBeImported()) {
                            styledString.append("  ");
                            styledString.append(Messages.rightClickToConvert, StyledString.DECORATIONS_STYLER);
                        }else if(type != null) {
                            styledString.append("  ");
                            styledString.append(type.equals(ExtensionRepositoryStore.API_EXTENSION_CONTENT_TYPE) ? "REST API EXTENSION" : type.toUpperCase(), StyledString.QUALIFIER_STYLER);
                        }
                        return styledString;
                    }
                };
                return (T) display;
            } else if (adaptableObject instanceof ExtensionRepositoryStore) {
                IDisplayable display = new IDisplayable() {

                    @Override
                    public String getDisplayName() {
                        return Messages.extensionRepositoryName;
                    }

                    @Override
                    public Image getIcon() {
                        return Pics.getImage(PicsConstants.extensions);
                    }
                };
                return (T) display;
            }
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class[] { IDisplayable.class };
    }

}
