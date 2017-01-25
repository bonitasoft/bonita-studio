/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ivy.plugins.parser.m2.PomReader;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.junit.Test;
import org.osgi.framework.Version;
import org.xml.sax.SAXException;

public class ProductVersionTest {


    private String loadPomVersion() throws MalformedURLException, IOException, SAXException {
        URL originURL = ProductVersion.class.getResource(ProductVersion.class.getSimpleName() + ".class");
		File originFile = new File(originURL.getFile());
        assertThat(originFile).exists();
		File pomFile = originFile;
		while (pomFile != null && !pomFile.getName().equals("pom.xml")) {
			File[] listFiles = pomFile.getParentFile().listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File arg0, String arg1) {
					return arg1.equals("pom.xml");
				}
			});
			if(listFiles == null || listFiles.length == 0){
				pomFile = pomFile.getParentFile();
			}else{
				pomFile = listFiles[0];
			}
		}
		assertThat(pomFile != null && pomFile.exists() && pomFile.getName().equals("pom.xml")).isTrue();
		URL descriptorURL = pomFile.toURI().toURL();
		URLResource resource = new URLResource(descriptorURL);
		PomReader domReader = new PomReader(descriptorURL, resource);        
        String pomVersion = domReader.getVersion();
		if(pomVersion.indexOf("-SNAPSHOT") != -1){
			pomVersion = pomVersion.substring(0, pomVersion.indexOf("-SNAPSHOT"));
		}
        return pomVersion;
    }



	@Test
	public void shouldCurrentProductVersionEquals_POMVersionIgnoringQualifier() throws Exception {
        String pomVersion = loadPomVersion();
		Version current = new Version(ProductVersion.CURRENT_VERSION);
		Version osgiPomVersion = Version.parseVersion(pomVersion);
		assertThat(current.getMajor()).isEqualTo(osgiPomVersion.getMajor());
		assertThat(current.getMinor()).isEqualTo(osgiPomVersion.getMinor());
		assertThat(current.getMicro()).isEqualTo(osgiPomVersion.getMicro());
	}
	
	@Test
	public void shouldCurrentYearEquals_EffectiveCurrentYear() throws Exception {
		String currentYear = new SimpleDateFormat("yyyy").format(new Date());
		assertThat(ProductVersion.CURRENT_YEAR).isEqualTo(currentYear);
	}
}
