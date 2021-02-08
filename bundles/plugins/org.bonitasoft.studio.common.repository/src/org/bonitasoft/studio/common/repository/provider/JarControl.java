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
package org.bonitasoft.studio.common.repository.provider;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;


/**
 * @author Romain Bioteau
 *
 */
public class JarControl extends Control {

    private File file;

	public JarControl(File jarFile){
		this.file = jarFile;
	}

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {

		if(!format.equals("java.properties")){
			return null ;
		}
		String bundleName = toBundleName(baseName, locale);
		ResourceBundle bundle = null;
		InputStreamReader reader = null;
		try(JarFile jarFile = new JarFile(file)) {
		    ZipEntry entry = jarFile.getEntry(bundleName);
		    if(entry != null) {
		        reader = new InputStreamReader(jarFile.getInputStream(entry), StandardCharsets.UTF_8);
                bundle = new PropertyResourceBundle(reader);
		    }
		} finally {
			if(reader != null){
				reader.close() ;
			}
		}
		return bundle;
	}

	@Override
	public String toBundleName(String baseName, Locale locale) {
		if(locale == null || locale.toString().isEmpty()){
			return baseName + ".properties";
		}
		return baseName + "_" + locale.toString() + ".properties";
	}

}
