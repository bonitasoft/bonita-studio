/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.core.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;

public class BDMArtifactDescriptor {

    private static final String GROUP_ID_PROPERTY = "groupId";

    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVersion() {
        return "1.0.0";
    }

    public BDMArtifactDescriptor load(File descriptorPropertyFile) throws CoreException {
        Properties properties = new Properties();
        try (InputStream is = new FileInputStream(descriptorPropertyFile)) {
            properties.load(is);
        } catch (IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID,
                    "Failed to load BDM artifact descriptor", e));
        }
        setGroupId(checkMandatoryProperty(descriptorPropertyFile, GROUP_ID_PROPERTY, properties));
        return this;
    }

    private String checkMandatoryProperty(File descriptorPropertyFile, String property, Properties properties) {
        String propertyValue = properties.getProperty(property);
        if (propertyValue == null || propertyValue.isEmpty()) {
            throw new IllegalStateException(String.format("%s property is missing in yje BDM artifact descriptor: %s",
                    property, descriptorPropertyFile.getAbsolutePath()));
        }
        return propertyValue;
    }

    public void save(IFile descriptorPropertyFile) throws CoreException {
        Properties properties = new PropertiesWithoutComment();
        properties.setProperty(GROUP_ID_PROPERTY, getGroupId());
        try (OutputStream out = new FileOutputStream(descriptorPropertyFile.getLocation().toFile())) {
            properties.store(out, null);
        } catch (IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID,
                    "Failed to save BDM artifact descriptor", e));
        }
        descriptorPropertyFile.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
    }
 
}
