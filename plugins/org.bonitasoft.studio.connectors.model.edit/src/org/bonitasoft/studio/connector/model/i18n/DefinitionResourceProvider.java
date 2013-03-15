/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.imageio.ImageIO;

import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
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
 * 
 */
public class DefinitionResourceProvider {

    public static final String pageTilte = "pageTitle";
    public static final String pageField = "label";
    public static final String pageDescription = "pageDescription";
    public static final String category = "category";
    public static final String connectorDefinition = "connectorDefinitionLabel";
    public static final String connectorDefinitionDescription = "connectorDefinitionDescription";
    public static final String fieldDescription = "description";
    private static final String CLASSPATH_DIR = "dependencies";

    private ImageRegistry categoryImageRegistry;
    private final ImageRegistry definitionImageRegistry;
    private final IRepositoryStore<? extends IRepositoryFileStore> store;
    private final Bundle bundle;
    private DefinitionControl pluginControl;
    private DefinitionControl storeControl;
    private ArrayList<Category> categories;
    private final static Map<IRepositoryStore<? extends IRepositoryFileStore>, DefinitionResourceProvider> INSTANCES_MAP;

    static {
        INSTANCES_MAP = new HashMap<IRepositoryStore<? extends IRepositoryFileStore>, DefinitionResourceProvider>();
    }

    public static DefinitionResourceProvider getInstance(
            IRepositoryStore<? extends IRepositoryFileStore> store,
            Bundle bundle) {
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
            IRepositoryStore<? extends IRepositoryFileStore> store,
            Bundle bundle) {
        this.store = store;
        this.bundle = bundle;
        categoryImageRegistry = createImageRegistry();
        definitionImageRegistry = createImageRegistry();
        try {
            URL url = bundle.getResource(store.getName());
            pluginControl = new DefinitionControl(FileLocator.toFileURL(url)
                    .getFile());
            storeControl = new DefinitionControl(store.getResource()
                    .getLocation().toFile().getAbsolutePath());
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    protected ImageRegistry createImageRegistry() {

        // If we are in the UI Thread use that
        if (Display.getCurrent() != null) {
            return new ImageRegistry(Display.getCurrent());
        }

        if (PlatformUI.isWorkbenchRunning()) {
            return new ImageRegistry(PlatformUI.getWorkbench().getDisplay());
        }

        // Invalid thread access if it is not the UI Thread
        // and the workbench is not created.
        throw new SWTError(SWT.ERROR_THREAD_INVALID_ACCESS);
    }

    private ResourceBundle getResourceBundle(ConnectorDefinition definition,
            Locale locale) {
        if (definition == null || definition.eResource() == null) {
            return null;
        }
        IRepositoryFileStore fileStore = store.getChild(URI.decode(definition.eResource().getURI().lastSegment()));
        if(fileStore == null){
        	return null;
        }
        
        Resource emfResource = ((ConnectorDefinition)fileStore.getContent()).eResource();
        if (emfResource == null) {
            return null;
        }
        String baseName = URI.decode(emfResource.getURI().lastSegment());
        if (baseName.lastIndexOf(".") != -1) {
            baseName = baseName.substring(0, baseName.lastIndexOf("."));
        }

        ResourceBundle bundle = null;
        try {
            ResourceBundle.clearCache();
            bundle = ResourceBundle.getBundle(baseName, locale, pluginControl);
        } catch (MissingResourceException e) {
            try {
                ResourceBundle.clearCache();
                bundle = ResourceBundle.getBundle(baseName, locale,
                        storeControl);
            } catch (MissingResourceException e1) {
                return null;
            }
        }

        return bundle;
    }

    private String getMessage(ConnectorDefinition definition, String key) {
        ResourceBundle messages = getResourceBundle(definition, new Locale(Platform.getNL()));
        if (messages == null) {
            return null;
        }
        try {
            return messages.getString(key);
        } catch (Exception e) {
            return null;
        }
    }

    public String getPageTitle(ConnectorDefinition definition, String pageId) {
        return getMessage(definition, pageId + "." + pageTilte);
    }

    public String getPageDescription(ConnectorDefinition definition,
            String pageId) {
        return getMessage(definition, pageId + "." + pageDescription);
    }

    public String getFieldLabel(ConnectorDefinition definition, String fieldId) {
        return getMessage(definition, fieldId + "." + pageField);
    }

    public String getFieldDescription(ConnectorDefinition definition,
            String fieldId) {
        return getMessage(definition, fieldId + "." + fieldDescription);
    }

    public String getCategoryLabel(ConnectorDefinition definition,
            String categoryId) {
        String label = getMessage(definition, categoryId + "." + category);
        if (label == null || label.isEmpty()) {
            label = categoryId;
        }
        return label;
    }

    public void setCategoryLabel(Properties messages, String categoryId,
            String label) {
        if (label != null) {
            messages.put(categoryId + "." + category, label);
        }
    }

    public void setPageTitleLabel(Properties messages, String pageId,
            String label) {
        if (label == null) {
            label = "";
        }
        messages.put(pageId + "." + pageTilte, label);
    }

    public void setPageDescriptionLabel(Properties messages, String pageId,
            String label) {
        if (label == null) {
            label = "";
        }
        messages.put(pageId + "." + pageDescription, label);
    }

    public void setConnectorDefinitionLabel(Properties messages, String label) {
        if (label != null) {
            messages.put(connectorDefinition, label);
        }
    }

    public void setFieldLabel(Properties messages, String fieldId, String label) {
        String value = label ;
        if (value == null) {
            value = fieldId;
        }
        messages.put(fieldId + "." + pageField, value);
    }

    public String getConnectorDefinitionLabel(ConnectorDefinition definition) {
        String label = getMessage(definition, connectorDefinition);
        if (label == null || label.isEmpty()) {
            label = null ;
        }
        return label;
    }

    public Properties getDefaultMessageProperties(ConnectorDefinition definition) {
        ResourceBundle bundle = getResourceBundle(definition, new Locale(""));
        Properties properties = new Properties();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                properties.put(key, bundle.getString(key));
            }
        }
        return properties;
    }

    public void saveMessagesProperties(ConnectorDefinition definition,
            Properties messages) {

        String fileName = store.getResource().getLocation().toFile()
                .getAbsolutePath()
                + File.separatorChar
                + NamingUtils.toConnectorDefinitionFilename(definition.getId(),
                        definition.getVersion(), false) + ".properties";
        File defaultMessageFile = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(defaultMessageFile);
            messages.store(fos, null);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    public Set<Locale> getExistingLocale(ConnectorDefinition definition) {
        Set<Locale> result = new HashSet<Locale>();
        if(definition == null || definition.eResource() == null){
        	return result;
        }
        String defId = URI.decode(definition.eResource().getURI().trimFileExtension().lastSegment());
        try {
            for (IResource r : store.getResource().members()) {
                if (r.getFileExtension() != null
                        && r.getFileExtension().equals("properties")) {
                    String resourceName = r.getName();
                    if (resourceName.length() >= defId.length()) {
                        String baseName = resourceName.substring(0,
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
                                    String[] split = language.split("_");
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
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        return result;
    }

    public String getFieldLabel(Properties messages, String fieldId) {
        return messages.getProperty(fieldId + "." + pageField);
    }

    public String getPageTitle(Properties messages, String pageId) {
        return messages.getProperty(pageId + "." + pageTilte);
    }

    public String getPageDescription(Properties messages, String pageId) {
        return messages.getProperty(pageId + "." + pageDescription);
    }

    public String getCategoryLabel(Properties messages, Category category) {
        String label = messages.getProperty(category.getId() + "."
                + DefinitionResourceProvider.category);
        if (label == null || label.isEmpty()) {
            label = category.getId();
        }
        return label;
    }

    public List<File> getExistingLocalesResource(ConnectorDefinition def) {
        List<File> result = new ArrayList<File>();
        if(def.eResource() == null){
        	return result;
        }
        String defId = URI.decode(def.eResource().getURI().trimFileExtension().lastSegment());
        try {
            for (IResource r : store.getResource().members()) {
                if (r.getFileExtension() != null
                        && r.getFileExtension().equals("properties")) {
                    String resourceName = r.getName();
                    if (resourceName.contains(defId)) {
                        String baseName = resourceName.substring(0,
                                defId.length());
                        if (baseName.equals(defId)) {
                            result.add(((IFile) r).getLocation().toFile());
                        }
                    }
                }
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }

        try {
            URL defaultPropertyFile = bundle.getResource(store.getName() + "/"
                    + defId + ".properties");
            if (defaultPropertyFile != null) {
                result.add(new File(FileLocator.toFileURL(defaultPropertyFile)
                        .getFile()));
            }
            Enumeration<URL> files = bundle.findEntries(store.getName() + "/",
                    defId + "*.properties", false);
            if (files != null) {
                while (files.hasMoreElements()) {
                    URL url = files.nextElement();
                    result.add(new File(FileLocator.toFileURL(url).getFile()));
                }
            }
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }

        return Collections.unmodifiableList(result);
    }

    public void createIcon(final File imageFile, final String iconName) {
        BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

            @Override
            public void run() {
                try {
                    IFolder targetFoler = store.getResource();
                    IFile iconFile = targetFoler.getFile(iconName);
                    if (iconFile.exists()) {
                        iconFile.delete(true, Repository.NULL_PROGRESS_MONITOR);
                    }
                    BufferedImage image = ImageIO.read(imageFile);
                    image = FileUtil.resizeImage(image, 16);
                    ImageIO.write(image, "PNG", iconFile.getLocation().toFile());
                } catch (Exception ex) {
                    BonitaStudioLog.error(ex);
                }
            }
        });

    }

    public String getConnectorDefinitionDescription(
            ConnectorDefinition definition) {
        String label = getMessage(definition, connectorDefinitionDescription);
        if (label == null || label.isEmpty()) {
            label = "";
        }
        return label;
    }

    public void setConnectorDefinitionDescription(Properties messages,
            String label) {
        if (label != null) {
            messages.put(connectorDefinitionDescription, label);
        }
    }

    public String getFieldDescription(Properties messages, String fieldId) {
        return messages.getProperty(fieldId + "." + fieldDescription);
    }

    public void setFieldDescription(Properties messages, String fieldId,
            String description) {
        String value = description ;
        if (value == null) {
            value = "";
        }
        messages.put(fieldId + "." + fieldDescription, value);
    }

    public Set<String> getProvidedCategoriesIds() {
        Set<String> providedCategoryIds = new HashSet<String>();
        for (IRepositoryFileStore defFile : store.getChildren()) {
            if (!defFile.canBeShared()) { // provided definition
                ConnectorDefinition def = (ConnectorDefinition) defFile
                        .getContent();
                for (Category cat : def.getCategory()) {
                    providedCategoryIds.add(cat.getId());
                }
            }
        }
        return providedCategoryIds;
    }

    public Set<String> getUserCategoriesIds() {
        Set<String> userCategoryIds = new HashSet<String>();
        Set<String> providedCategoryIds = getProvidedCategoriesIds();
        for (IRepositoryFileStore defFile : store.getChildren()) {
            if (defFile.canBeShared()) {
                ConnectorDefinition def = (ConnectorDefinition) defFile
                        .getContent();
                for (Category cat : def.getCategory()) {
                    if (!providedCategoryIds.contains(cat.getId())) {
                        userCategoryIds.add(cat.getId());
                    }
                }
            }
        }
        return userCategoryIds;
    }



    public List<Category> getAllCategories() {
        if(categories == null){
            loadDefinitionsCategories(null);
        }
        return categories;
    }

    public Category getUnloadableCategory() {
        Category unloadable = ConnectorDefinitionFactory.eINSTANCE
                .createCategory();
        unloadable.setId(Messages.unloadable);
        return unloadable;
    }

    public Image getCategoryIcon(Category category) {
        if(Messages.unloadable.equals(category.getId())){
            return Pics.getImage(PicsConstants.error);
        }
        Image icon = categoryImageRegistry.get(category.getId());
        try {
            FileLocator.toFileURL(bundle.getResource(store.getName()));
        } catch (IOException e1) {
            BonitaStudioLog.error(e1);
        }
        if (icon == null) {
            Resource resource = category.eResource();
            File f = null;
            if (resource != null) {
                URI uri = resource.getURI();
                f = new File(uri.toFileString());
                f = f.getParentFile();
            } else {
                f = store.getResource().getLocation().toFile();
            }

            if (f != null && f.exists() && category.getIcon() != null) {
                File iconFile = new File(f, category.getIcon());
                if (iconFile.exists()) {
                    try {
                        final URL iconURL = iconFile.toURI().toURL();
                        icon = ImageDescriptor.createFromURL(iconURL)
                                .createImage();
                        categoryImageRegistry.put(category.getId(), icon);
                        return icon;
                    } catch (MalformedURLException e) {
                        BonitaStudioLog.error(e);
                    }
                }
            }
        }
        return icon;
    }

    public String getCategoryLabel(Category category) {
        ConnectorDefinition categoryDef = (ConnectorDefinition) category
                .eContainer();
        String label = getCategoryLabel(categoryDef, category.getId());
        if (category.getId().equals(label)) {// Try to find a provided category
            // label
            List<ConnectorDefinition> definitions = getAllDefinitionWithCategotyId(category.getId());
            for (ConnectorDefinition def : definitions) {
                label = getCategoryLabel(def, category.getId());
                if (!category.getId().equals(label)) {
                    return label;
                }
            }
        }
        return label;
    }

    private List<ConnectorDefinition> getAllDefinitionWithCategotyId(String id) {
		if(store instanceof IDefinitionRepositoryStore){
			final List<ConnectorDefinition> result = new ArrayList<ConnectorDefinition>();
			for(ConnectorDefinition def : ((IDefinitionRepositoryStore) store).getDefinitions()){
				for(Category c : def.getCategory()){
					if(c.getId().equals(id)){
						result.add(def);
					}
				}
			}
			return result;
		}
		return Collections.emptyList();
	}

	private List<Category> getAllCategoriesWithId(String id) {
        List<Category> result = new ArrayList<Category>();
        for (Category cat : categories) {
            if (cat.getId().equals(id)) {
                result.add(cat);
            }
        }

        return result;
    }

    public void loadDefinitionsCategories(IProgressMonitor monitor){
        categories = new ArrayList<Category>();
        Set<String> idsToAdd = new HashSet<String>();
        idsToAdd.addAll(getProvidedCategoriesIds());
        idsToAdd.addAll(getUserCategoriesIds());
        boolean addUnloadableCategory = false;
        for (IRepositoryFileStore defFile : store.getChildren()) {
            if (!defFile.canBeShared()) { // provided definition
                ConnectorDefinition def = (ConnectorDefinition) defFile
                        .getContent();
                if (def instanceof UnloadableConnectorDefinition) {
                    addUnloadableCategory = true;
                } else {
                    for (Category cat : def.getCategory()) {
                        if (idsToAdd.contains(cat.getId())) {
                            categories.add(cat);
                            idsToAdd.remove(cat.getId());
                        }
                    }
                }
            }
        }

        for (IRepositoryFileStore defFile : store.getChildren()) {
            if (defFile.canBeShared()) { // user definition
                ConnectorDefinition def = (ConnectorDefinition) defFile
                        .getContent();
                if (def instanceof UnloadableConnectorDefinition) {
                    addUnloadableCategory = true;
                } else {
                    for (Category cat : def.getCategory()) {
                        if (idsToAdd.contains(cat.getId())) {
                            categories.add(cat);
                            idsToAdd.remove(cat.getId());
                        }
                    }
                }
            }
        }
        if (addUnloadableCategory) {
            categories.add(getUnloadableCategory());
        }
        Category uncategorized = ConnectorDefinitionFactory.eINSTANCE.createCategory();
        uncategorized.setId(Messages.uncategorized);
        categories.add(uncategorized);

        Collections.sort(categories, new Comparator<Category>() {

            @Override
            public int compare(Category c1, Category c2) {
                return getCategoryLabel(c1).compareTo(
                        getCategoryLabel(c2));
            }
        });
    }

    public Image getDefinitionIcon(ConnectorDefinition definition) {
        if (definition == null) {
            return Pics.getImage(PicsConstants.error);
        }
        String definitionId = definition.getId() + "_"
                + definition.getVersion();
        Image icon = definitionImageRegistry.get(definitionId);
        if (icon == null || icon.isDisposed()) {
            Resource resource = definition.eResource();
            File f = null;
            if (resource != null) {
                URI uri = resource.getURI();
                f = new File(uri.toFileString());
                f = f.getParentFile();
            } else {
                f = store.getResource().getLocation().toFile();
            }

            if (f != null && f.exists() && definition.getIcon() != null) {
                File iconFile = new File(f, definition.getIcon());
                if (iconFile.exists()) {
                    try {
                        final URL iconURL = iconFile.toURI().toURL();
                        icon = ImageDescriptor.createFromURL(iconURL)
                                .createImage();
                        if (definitionImageRegistry.get(definitionId) != null) {
                            definitionImageRegistry.remove(definitionId);
                        }
                        definitionImageRegistry.put(definitionId, icon);
                        return icon;
                    } catch (MalformedURLException e) {
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

    public InputStream getDependencyInputStream(String jarName) {
        URL url = bundle.getResource(CLASSPATH_DIR + "/" + jarName);
        if (url != null) {
            try {
                return url.openStream();
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

	public void removeCategoryLabel(Properties messages, Category c) {
		messages.remove(c.getId() + "." + category);
	}
}
