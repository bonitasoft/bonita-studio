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

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.FileLocator;
import org.junit.Test;

public class ConfigurationUpdaterTest {


    @Test
    public void should_update_configuration() throws Exception {
        var updater = spy(new ConfigurationUpdater());
        var configFile = new File(FileLocator.toFileURL(ConfigurationUpdaterTest.class.getResource("config.ini")).getFile());
        var tmpConfig = Files.createTempFile("config", ".ini");
        Files.copy(configFile.toPath(), tmpConfig, StandardCopyOption.REPLACE_EXISTING);
        
        doReturn(tmpConfig.toFile()).when(updater).getConfigFile();
        
        var configuration = load(tmpConfig.toFile());
        assertThat(configuration).contains(entry("osgi.nls.warnings", "ignore"))
                                  .doesNotContainKey(InternalPlatform.PROP_NL);
        
        updater.updateLocale("fr");
        
        configuration = load(tmpConfig.toFile());
        assertThat(configuration)
            .contains(entry("osgi.nls.warnings", "ignore"), entry(InternalPlatform.PROP_NL, "fr"));
    }

    private Properties load(File configFile) throws FileNotFoundException, IOException {
        try(var is = new FileInputStream(configFile)){
           var config = new Properties();
           config.load(is);
           return config;
        }
    }    
    
}
