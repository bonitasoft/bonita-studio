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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;


/**
 * @author Romain Bioteau
 *
 */
public class DefinitionControl extends Control {

	private final Bundle osgiBundle;
	private final String storeName;

	public DefinitionControl(Bundle bundle, String storeName){
		this.osgiBundle = bundle ;
		this.storeName = storeName;
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
		FileInputStream fis = null;
		try {
			URL fileurl = osgiBundle.getResource(storeName+"/"+bundleName);
			if(fileurl != null){
				File file = new File(FileLocator.toFileURL(fileurl).getFile());
				if (file.isFile()) { // Also checks for existance
					fis = new FileInputStream(file);
					reader = new InputStreamReader(fis, Charset.forName("UTF-8"));
					bundle = new PropertyResourceBundle(reader);
				}
			}
		} finally {
			if(reader != null){
				reader.close() ;
			}
			if(fis != null){
				fis.close() ;
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
