/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.PropertiesFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

public class DatabaseConnectorPropertiesFileStore extends PropertiesFileStore {

    public static final String DEFAULT = "default";
    public static final String JAR_LIST = "jars";
    public static final String SEPARATOR = ";";
    public static final String AUTO_ADD_DRIVERS = "auto";

    public DatabaseConnectorPropertiesFileStore(String fileName,
            IRepositoryStore<DatabaseConnectorPropertiesFileStore> store) {
        super(fileName, store);

    }

    @Override
    public Image getIcon() {
        return Pics.getImage("databases_driver.png", ConnectorPlugin.getDefault());
    }

    public void setAutoAddDriver(Boolean b) {
        Properties properties;
        try {
            properties = getContent();
        } catch (ReadFileStoreException e) {
            properties = new Properties();
        }
        properties.put(AUTO_ADD_DRIVERS, b.toString());
        save(properties);
    }

    public boolean getAutoAddDriver() {
        Properties propeties;
        try {
            propeties = getContent();
        } catch (ReadFileStoreException e) {
            propeties = new Properties();
        }
        return Boolean.valueOf((String) propeties.get(AUTO_ADD_DRIVERS));
    }

    public void setDefault(String jarName) {
        Properties properties;
        try {
            properties = getContent();
        } catch (ReadFileStoreException e) {
            properties = new Properties();
        }
        if (jarName == null) {
            properties.remove(DEFAULT);
        } else {
            properties.put(DEFAULT, jarName);
        }

        save(properties);
    }

    public String getDefault() {
        String jarName = null;
        try {
            jarName = getContent().getProperty(DEFAULT);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.warning(e.getMessage(), ConnectorPlugin.PLUGIN_ID);
        }
        return jarName;
    }

    public List<String> getJarList() {
        try {
            return readJarLists(getContent().getProperty(JAR_LIST));
        } catch (ReadFileStoreException e) {
            return new ArrayList<>();
        }
    }

    public static List<String> readJarLists(String jars) {
        if (jars == null || jars.isBlank()) {
            return new ArrayList<>();
        }
        return Stream.of(jars.split(SEPARATOR)).collect(Collectors.toList());
    }

    public static String jarListsToString(List<String> jars) {
        return jars.stream().collect(Collectors.joining(SEPARATOR));
    }

    public void setJarList(List<String> jars) {
        Properties properties;
        try {
            properties = (Properties) getContent();
        } catch (ReadFileStoreException e) {
            properties = new Properties();
        }
        properties.put(JAR_LIST, jarListsToString(jars));
        save(properties);
    }

    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    @Override
    protected void doClose() {

    }

    public void jarRemoved(String jar) {
        String defaultJar = getDefault();
        if(Objects.equals(defaultJar, jar)) {
            setDefault("");
        }
        List<String> jarList = getJarList();
        if(jarList.contains(jar)) {
            jarList.remove(jar);
            setJarList(jarList);
        }
    }

}
