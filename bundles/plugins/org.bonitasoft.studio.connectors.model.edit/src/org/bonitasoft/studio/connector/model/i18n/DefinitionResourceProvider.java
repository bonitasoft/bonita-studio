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
package org.bonitasoft.studio.connector.model.i18n;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.WeakHashMap;

import javax.imageio.ImageIO;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 */
public class DefinitionResourceProvider {

    public static final String pageTilte = "pageTitle";
    public static final String pageField = "label";
    public static final String pageDescription = "pageDescription";
    public static final String category = "category";
    public static final String connectorDefinition = "connectorDefinitionLabel";
    public static final String connectorDefinitionDescription = "connectorDefinitionDescription";
    public static final String fieldDescription = "description";
    public static final String fieldExample = "example";
    private static final String CLASSPATH_DIR = "dependencies";
    private static final String OUTPUTS_DESC = "outputsDescription";
    private static final String OUTPUT_DESC = "output.description";

    private ImageRegistry categoryImageRegistry;
    private final ImageRegistry definitionImageRegistry;
    private final IRepositoryStore<? extends IRepositoryFileStore> store;
    private final Bundle bundle;
    private DefinitionControl pluginControl;
    private StoreControl storeControl;
    private ArrayList<Category> categories;
    private Category uncategorized;
    private final Map<String, ResourceBundle> resourceBundleCache = new WeakHashMap<>();
    private final static Map<IRepositoryStore<? extends IRepositoryFileStore>, DefinitionResourceProvider> INSTANCES_MAP;

    static {
        INSTANCES_MAP = new WeakHashMap<>();
    }

    public static DefinitionResourceProvider getInstance(
            final IRepositoryStore<? extends IRepositoryFileStore> store,
            final Bundle bundle) {
        if (INSTANCES_MAP.get(store) == null) {
            INSTANCES_MAP.put(store, new DefinitionResourceProvider(store,
                    bundle));
        }
        return INSTANCES_MAP.get(store);
    }

    public ImageRegistry getImageRegistry() {
        if (categoryImageRegistry == null) {
            categoryImageRegistry = createImageRegistry();
        }
        return categoryImageRegistry;
    }

    private DefinitionResourceProvider(
            final IRepositoryStore<? extends IRepositoryFileStore> store,
            final Bundle bundle) {
        this.store = store;
        this.bundle = bundle;
        categoryImageRegistry = createImageRegistry();
        definitionImageRegistry = createImageRegistry();
        try {
            pluginControl = new DefinitionControl(bundle, store.getName());
            storeControl = new StoreControl(store.getResource().getLocation().toFile().getAbsolutePath());
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    protected ImageRegistry createImageRegistry() {
        // If we are in the UI Thread use that
        if (Display.getDefault() != null) {
            return new ImageRegistry(Display.getDefault());
        }

        if (PlatformUI.isWorkbenchRunning()) {
            return new ImageRegistry(PlatformUI.getWorkbench().getDisplay());
        }

        // Invalid thread access if it is not the UI Thread
        // and the workbench is not created.
        throw new SWTError(SWT.ERROR_THREAD_INVALID_ACCESS);
    }

    private ResourceBundle getResourceBundle(final ConnectorDefinition definition,
            final Locale locale) {
        if (definition == null || definition.eResource() == null) {
            return null;
        }
        final String cacheKey = getCacheKey(definition, locale);
        final ResourceBundle resourceBundle = resourceBundleCache.get(cacheKey);
        if (resourceBundle != null) {
            return resourceBundle;
        }
        final IRepositoryFileStore fileStore = store.getChild(URI.decode(definition.eResource().getURI().lastSegment()), true);
        if (fileStore == null) {
            return null;
        }

        ConnectorDefinition def = null;
        try {
            def = (ConnectorDefinition) fileStore.getContent();
        } catch (final ReadFileStoreException e2) {
            BonitaStudioLog.error("Failed to retrieve connector definition", e2);
        }
        if (def == null) {
            return null;
        }
        final Resource emfResource = def.eResource();
        if (emfResource == null) {
            return null;
        }
        String baseName = URI.decode(emfResource.getURI().lastSegment());
        if (baseName.lastIndexOf(".") != -1) {
            baseName = baseName.substring(0, baseName.lastIndexOf("."));
        }

        ResourceBundle bundle = null;
        try {
            if (fileStore.canBeShared()) {
                ResourceBundle.clearCache();
            }
            bundle = ResourceBundle.getBundle(baseName, locale, pluginControl);
        } catch (final MissingResourceException e) {
            try {
                ResourceBundle.clearCache(); //Clear the cache to always have updated i18n for custom connectors
                bundle = ResourceBundle.getBundle(baseName, locale,
                        storeControl);
            } catch (final MissingResourceException e1) {
                return null;
            }
        }
        resourceBundleCache.put(cacheKey, bundle);
        return bundle;
    }

    private String getCacheKey(final ConnectorDefinition definition, final Locale locale) {
        return NamingUtils.toConnectorDefinitionFilename(definition.getId(), definition.getVersion(), false)
                + locale.toString();
    }

    private String getMessage(final ConnectorDefinition definition, final String key) {
        final ResourceBundle messages = getResourceBundle(definition, new Locale(Platform.getNL()));
        if (messages == null) {
            return null;
        }
        try {
            return messages.getString(key);
        } catch (final Exception e) {
            return null;
        }
    }

    public String getPageTitle(final ConnectorDefinition definition, final String pageId) {
        return getMessage(definition, pageId + "." + pageTilte);
    }

    public String getPageDescription(final ConnectorDefinition definition,
            final String pageId) {
        return getMessage(definition, pageId + "." + pageDescription);
    }

    public String getFieldLabel(final ConnectorDefinition definition, final String fieldId) {
        return getMessage(definition, fieldId + "." + pageField);
    }

    public String getFieldDescription(final ConnectorDefinition definition,
            final String fieldId) {
        return getMessage(definition, fieldId + "." + fieldDescription);
    }

    public String getFieldExample(final ConnectorDefinition definition,
            final String fieldId) {
        return getMessage(definition, fieldId + "." + fieldExample);
    }

    public String getOutputsDescription(final ConnectorDefinition definition) {
        return getMessage(definition, OUTPUTS_DESC);
    }

    public String getOutputDescription(final ConnectorDefinition definition, final String outputName) {
        return getMessage(definition, outputName + "." + OUTPUT_DESC);
    }

    public String getCategoryLabel(final ConnectorDefinition definition,
            final String categoryId) {
        String label = getMessage(definition, categoryId + "." + category);
        if (label == null || label.isEmpty()) {
            label = categoryId;
        }
        return label;
    }

    public void setCategoryLabel(final Properties messages, final String categoryId,
            final String label) {
        if (label != null) {
            messages.put(categoryId + "." + category, label);
        }
    }

    public void setPageTitleLabel(final Properties messages, final String pageId,
            String label) {
        if (label == null) {
            label = "";
        }
        messages.put(pageId + "." + pageTilte, label);
    }

    public void setPageDescriptionLabel(final Properties messages, final String pageId,
            String label) {
        if (label == null) {
            label = "";
        }
        messages.put(pageId + "." + pageDescription, label);
    }

    public void setConnectorDefinitionLabel(final Properties messages, final String label) {
        if (label != null) {
            messages.put(connectorDefinition, label);
        }
    }

    public void setFieldLabel(final Properties messages, final String fieldId, final String label) {
        String value = label;
        if (value == null) {
            value = fieldId;
        }
        messages.put(fieldId + "." + pageField, value);
    }

    public String getConnectorDefinitionLabel(final ConnectorDefinition definition) {
        String label = getMessage(definition, connectorDefinition);
        if (label == null || label.isEmpty()) {
            label = null;
        }
        return label;
    }

    public Properties getDefaultMessageProperties(final ConnectorDefinition definition) {
        final ResourceBundle bundle = getResourceBundle(definition, new Locale(""));
        final Properties properties = new Properties();
        if (bundle != null) {
            for (final String key : bundle.keySet()) {
                properties.put(key, bundle.getString(key));
            }
        }
        return properties;
    }

    public void saveMessagesProperties(final ConnectorDefinition definition,
            final Properties messages) {

        final String fileName = store.getResource().getLocation().toFile()
                .getAbsolutePath()
                + File.separatorChar
                + NamingUtils.toConnectorDefinitionFilename(definition.getId(),
                        definition.getVersion(), false)
                + ".properties";
        final File defaultMessageFile = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(defaultMessageFile);
            messages.store(fos, null);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (final IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        resourceBundleCache.clear();
    }

    public Set<Locale> getExistingLocale(final ConnectorDefinition definition) {
        final Set<Locale> result = new HashSet<>();
        String defId = null;
        if (definition == null) {
            return result;
        }
        if (definition.eResource() == null) {
            defId = NamingUtils.toConnectorDefinitionFilename(definition.getId(), definition.getVersion(), false);
        } else {
            defId = URI.decode(definition.eResource().getURI().trimFileExtension().lastSegment());
        }
        try {
            for (final IResource r : store.getResource().members()) {
                if (r.getFileExtension() != null
                        && r.getFileExtension().equals("properties")) {
                    final String resourceName = r.getName();
                    if (resourceName.length() >= defId.length()) {
                        final String baseName = resourceName.substring(0,
                                defId.length());
                        if (baseName.equals(defId)) {
                            if (resourceName.substring(baseName.length())
                                    .indexOf("_") != -1
                                    && resourceName
                                            .substring(baseName.length())
                                            .indexOf(".") != -1) {
                                String language = resourceName
                                        .substring(baseName.length());
                                language = language.substring(1,
                                        language.lastIndexOf("."));
                                String country = null;
                                String variant = null;
                                if (language.indexOf("_") != -1) {
                                    final String[] split = language.split("_");
                                    language = split[0];
                                    country = split[1];
                                    if (split.length == 3) {
                                        variant = split[2];
                                    }
                                }
                                result.add(new Locale(language,
                                        country == null ? "" : country,
                                        variant == null ? "" : variant));
                            }
                        }
                    }
                }
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return result;
    }

    public String getFieldLabel(final Properties messages, final String fieldId) {
        return messages.getProperty(fieldId + "." + pageField);
    }

    public String getPageTitle(final Properties messages, final String pageId) {
        return messages.getProperty(pageId + "." + pageTilte);
    }

    public String getPageDescription(final Properties messages, final String pageId) {
        return messages.getProperty(pageId + "." + pageDescription);
    }

    public String getOutputsDescription(final Properties messages) {
        return messages.getProperty(OUTPUTS_DESC);
    }

    public String getCategoryLabel(final Properties messages, final Category category) {
        String label = messages.getProperty(category.getId() + "."
                + DefinitionResourceProvider.category);
        if (label == null || label.isEmpty()) {
            label = category.getId();
        }
        return label;
    }

    public List<File> getExistingLocalesResource(final ConnectorDefinition def) {
        final List<File> result = new ArrayList<>();
        if (def.eResource() == null) {
            return result;
        }
        final String defId = URI.decode(def.eResource().getURI().trimFileExtension().lastSegment());
        try {
            for (final IResource r : store.getResource().members()) {
                if (r.getFileExtension() != null
                        && r.getFileExtension().equals("properties")) {
                    final String resourceName = r.getName();
                    if (resourceName.contains(defId)) {
                        final String baseName = resourceName.substring(0,
                                defId.length());
                        if (baseName.equals(defId)) {
                            result.add(((IFile) r).getLocation().toFile());
                        }
                    }
                }
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }

        try {
            final URL defaultPropertyFile = bundle.getResource(store.getName() + "/"
                    + defId + ".properties");
            if (defaultPropertyFile != null) {
                result.add(new File(FileLocator.toFileURL(defaultPropertyFile)
                        .getFile()));
            }
            final Enumeration<URL> files = bundle.findEntries(store.getName() + "/",
                    defId + "*.properties", false);
            if (files != null) {
                while (files.hasMoreElements()) {
                    final URL url = files.nextElement();
                    result.add(new File(FileLocator.toFileURL(url).getFile()));
                }
            }
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }

        return Collections.unmodifiableList(result);
    }

    public void createIcon(final File imageFile, final String iconName) {
        BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

            @Override
            public void run() {
                try {
                    final IFolder targetFoler = store.getResource();
                    final IFile iconFile = targetFoler.getFile(iconName);
                    if (iconFile.exists()) {
                        iconFile.delete(true, Repository.NULL_PROGRESS_MONITOR);
                    }
                    BufferedImage image = ImageIO.read(imageFile);
                    image = FileUtil.resizeImage(image, 16);
                    ImageIO.write(image, "PNG", iconFile.getLocation().toFile());
                } catch (final Exception ex) {
                    BonitaStudioLog.error(ex);
                }
            }
        });

    }

    public String getConnectorDefinitionDescription(
            final ConnectorDefinition definition) {
        String label = getMessage(definition, connectorDefinitionDescription);
        if (label == null || label.isEmpty()) {
            label = "";
        }
        return label;
    }

    public void setConnectorDefinitionDescription(final Properties messages,
            final String label) {
        if (label != null) {
            messages.put(connectorDefinitionDescription, label);
        }
    }

    public String getFieldDescription(final Properties messages, final String fieldId) {
        return messages.getProperty(fieldId + "." + fieldDescription);
    }

    public String getFieldExample(final Properties messages, final String fieldId) {
        return messages.getProperty(fieldId + "." + fieldExample);
    }

    public void setFieldDescription(final Properties messages, final String fieldId,
            final String description) {
        String value = description;
        if (value == null) {
            value = "";
        }
        messages.put(fieldId + "." + fieldDescription, value);
    }

    public Set<String> getProvidedCategoriesIds() {
        final Set<String> providedCategoryIds = new HashSet<>();
        for (final IRepositoryFileStore defFile : store.getChildren()) {
            if (!defFile.canBeShared()) { // provided definition
                try {
                    final ConnectorDefinition def = (ConnectorDefinition) defFile
                            .getContent();
                    for (final Category cat : def.getCategory()) {
                        providedCategoryIds.add(cat.getId());
                    }
                } catch (final ReadFileStoreException e) {
                    BonitaStudioLog.error("Failed to retrieve connector definition", e);
                }

            }
        }
        return providedCategoryIds;
    }

    public Set<String> getUserCategoriesIds() {
        final Set<String> userCategoryIds = new HashSet<>();
        final Set<String> providedCategoryIds = getProvidedCategoriesIds();
        for (final IRepositoryFileStore defFile : store.getChildren()) {
            if (defFile.canBeShared()) {
                try {
                    final ConnectorDefinition def = (ConnectorDefinition) defFile
                            .getContent();
                    if (def != null) {
                        for (final Category cat : def.getCategory()) {
                            if (!providedCategoryIds.contains(cat.getId())) {
                                userCategoryIds.add(cat.getId());
                            }
                        }
                    }
                } catch (final ReadFileStoreException e) {
                    BonitaStudioLog.error("Failed to retrieve connector definition", e);
                }

            }
        }
        return userCategoryIds;
    }

    public List<Category> getAllCategories() {
        if (categories == null) {
            loadDefinitionsCategories(null);
        }
        return categories;
    }

    public Category getUnloadableCategory() {
        final Category unloadable = ConnectorDefinitionFactory.eINSTANCE
                .createCategory();
        unloadable.setId(Messages.unloadable);
        return unloadable;
    }

    public Category getUncategorizedCategory() {
        return uncategorized;
    }

    public Image getCategoryIcon(final Category category) {
        if (Messages.unloadable.equals(category.getId())) {
            return Pics.getImage(PicsConstants.error);
        }
        Image icon = categoryImageRegistry.get(category.getId());

        if (icon == null) {
            final Resource resource = category.eResource();
            File f = null;
            if (resource != null) {
                final URI uri = resource.getURI();
                f = new File(uri.toFileString());
                f = f.getParentFile();
            } else {
                f = store.getResource().getLocation().toFile();
            }

            if (f != null && f.exists() && category.getIcon() != null) {
                final File iconFile = new File(f, category.getIcon());
                URL iconURL = null;
                if (!iconFile.exists()) {
                    try {
                        final String name = store.getResource().getName() + "/" + category.getIcon();
                        final URL resourceURL = bundle.getResource(name);
                        if (resourceURL != null) {
                            iconURL = FileLocator.toFileURL(resourceURL);
                        }
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
                if (iconURL != null || iconFile.exists()) {
                    try {
                        if (iconURL == null) {
                            iconURL = iconFile.toURI().toURL();
                        }
                        icon = ImageDescriptor.createFromURL(iconURL)
                                .createImage();
                        categoryImageRegistry.put(category.getId(), icon);
                        return icon;
                    } catch (final MalformedURLException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        return icon;
    }

    public String getCategoryLabel(final Category category) {
        final ConnectorDefinition categoryDef = (ConnectorDefinition) category
                .eContainer();
        String label = getCategoryLabel(categoryDef, category.getId());
        if (category.getId().equals(label)) {// Try to find a provided category
            // label
            final List<ConnectorDefinition> definitions = getAllDefinitionWithCategotyId(category.getId());
            for (final ConnectorDefinition def : definitions) {
                label = getCategoryLabel(def, category.getId());
                if (!category.getId().equals(label)) {
                    return label;
                }
            }
        }
        return label;
    }

    private List<ConnectorDefinition> getAllDefinitionWithCategotyId(final String id) {
        if (store instanceof IDefinitionRepositoryStore) {
            final List<ConnectorDefinition> result = new ArrayList<>();
            for (final ConnectorDefinition def : ((IDefinitionRepositoryStore) store).getDefinitions()) {
                for (final Category c : def.getCategory()) {
                    if (c.getId().equals(id)) {
                        result.add(def);
                    }
                }
            }
            return result;
        }
        return Collections.emptyList();
    }

    public void loadDefinitionsCategories(final IProgressMonitor monitor) {
        categories = new ArrayList<>();
        final Set<String> idsToAdd = new HashSet<>();
        idsToAdd.addAll(getProvidedCategoriesIds());
        idsToAdd.addAll(getUserCategoriesIds());
        boolean addUnloadableCategory = false;
        for (final IRepositoryFileStore defFile : store.getChildren()) {
            if (!defFile.canBeShared()) { // provided definition
                try {
                    final ConnectorDefinition def = (ConnectorDefinition) defFile
                            .getContent();
                    if (def instanceof UnloadableConnectorDefinition) {
                        addUnloadableCategory = true;
                    } else if (def != null) {
                        for (final Category cat : def.getCategory()) {
                            if (idsToAdd.contains(cat.getId())) {
                                categories.add(cat);
                                idsToAdd.remove(cat.getId());
                            }
                        }
                    }
                } catch (final ReadFileStoreException e) {
                    BonitaStudioLog.error("Failed to retrieve connector definition", e);
                }

            }
        }

        for (final IRepositoryFileStore defFile : store.getChildren()) {
            if (defFile.canBeShared()) { // user definition
                try {
                    final ConnectorDefinition def = (ConnectorDefinition) defFile
                            .getContent();
                    if (def instanceof UnloadableConnectorDefinition) {
                        addUnloadableCategory = true;
                    } else if (def != null) {
                        for (final Category cat : def.getCategory()) {
                            if (idsToAdd.contains(cat.getId())) {
                                categories.add(cat);
                                idsToAdd.remove(cat.getId());
                            }
                        }
                    }
                } catch (final ReadFileStoreException e) {
                    BonitaStudioLog.error("Failed to retrieve connector definition", e);
                }

            }
        }
        if (addUnloadableCategory) {
            categories.add(getUnloadableCategory());
        }
        uncategorized = ConnectorDefinitionFactory.eINSTANCE.createCategory();
        uncategorized.setId(Messages.uncategorized);
        categories.add(uncategorized);

        Collections.sort(categories, new Comparator<Category>() {

            @Override
            public int compare(final Category c1, final Category c2) {
                return getCategoryLabel(c1).compareTo(
                        getCategoryLabel(c2));
            }
        });
    }

    public Image getDefinitionIcon(final ConnectorDefinition definition) {
        if (definition == null) {
            return Pics.getImage(PicsConstants.error);
        }
        final String definitionId = definition.getId() + "_" + definition.getVersion();
        Image icon = definitionImageRegistry.get(definitionId);

        if (icon == null || icon.isDisposed()) {
            final Resource resource = definition.eResource();
            File f = null;
            if (resource != null) {
                final URI uri = resource.getURI();
                f = new File(URI.decode(uri.toFileString()));
                f = f.getParentFile();
            } else {
                f = store.getResource().getLocation().toFile();
            }
            if (f != null && f.exists() && definition.getIcon() != null && !definition.getIcon().isEmpty()) {
                final File iconFile = new File(f, definition.getIcon());
                URL iconURL = null;
                if (!iconFile.exists()) {
                    try {
                        final String name = store.getResource().getName() + "/" + definition.getIcon();
                        final URL resourceURL = bundle.getResource(name);
                        if (resourceURL != null) {
                            iconURL = FileLocator.toFileURL(resourceURL);
                        }
                    } catch (final IOException e) {
                        BonitaStudioLog.error(e);
                    }
                }
                if (iconURL != null || iconFile.exists()) {
                    try {
                        if (iconURL == null) {
                            iconURL = iconFile.toURI().toURL();
                        }
                        icon = ImageDescriptor.createFromURL(iconURL).createImage();
                        if (definitionImageRegistry.get(definitionId) != null) {
                            definitionImageRegistry.remove(definitionId);
                        }
                        definitionImageRegistry.put(definitionId, icon);
                        return icon;
                    } catch (final MalformedURLException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        if (icon == null || icon.isDisposed()) {
            icon = store.getIcon();
        }
        return icon;
    }

    public InputStream getDependencyInputStream(final String jarName) {
        final URL url = bundle.getResource(CLASSPATH_DIR + "/" + jarName);
        if (url != null) {
            try {
                return url.openStream();
            } catch (final IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    public void removeCategoryLabel(final Properties messages, final Category c) {
        messages.remove(c.getId() + "." + category);
    }

}
