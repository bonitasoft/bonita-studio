/**
 * Copyright (C) 2023 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.preferences.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;


public class ConfigurationUpdater {

    public void updateLocale(String locale) {
       var configFile = getConfigFile();
        if (configFile.exists()) {
            Properties configIniProperties = new Properties();
            try (var inStream = new FileInputStream(configFile) ) {
                configIniProperties.load(inStream);
                configIniProperties.setProperty(InternalPlatform.PROP_NL, locale);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            } 
            
            try( var out = new FileOutputStream(configFile)){
                configIniProperties.store(out, "");
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            } 
        }
    }
    
    protected File getConfigFile() {
        Location configArea = Platform.getInstallLocation();
        if (configArea == null) {
           throw new IllegalStateException("No configuration area defined");
        }
        var configAreaFolder = new File(configArea.getURL().getFile());
        return configAreaFolder.toPath().resolve("configuration").resolve("config.ini").toFile();
    }
    
    

}
