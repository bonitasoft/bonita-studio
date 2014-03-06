/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.bonitasoft.studio.common.BonitaHomeUtil;


/**
 * @author Romain Bioteau
 *
 */
public class LocaleUtil {

    private  static Comparator<Locale> localeComparator = new Comparator<Locale>() {

        @Override
        public int compare(Locale paramT1, Locale paramT2) {
            return paramT1.getLanguage().compareTo(paramT2.getLanguage());
        }

    };
    
    
    public static Locale[] getProtalLocales() {
        File portalI18NFolder = BonitaHomeUtil.getPortalI18NFolder();
        String[] poFiles = portalI18NFolder.list(new FilenameFilter() {

            @Override
            public boolean accept(File parent, String filename) {
                return filename.endsWith(".po");
            }
        });
        Set<Locale> locales = new TreeSet<Locale>(localeComparator);
        locales.add(Locale.ENGLISH);
        for(String poFile : poFiles){
            poFile = poFile.replace("mobile_", "").replace("portal-sp_", "").replace("portal_", "").replace(".po", "").replace("mobile", "").replace("portal-sp", "").replace("portal", "");
            if(!poFile.isEmpty()){
                String country = null;
                String language = null;
                if(poFile.contains("_")){
                    language = poFile.split("_")[0];
                    country = poFile.split("_")[1];
                }else{
                    language = poFile;
                }
                locales.add(country == null ? new Locale(language) : new Locale(language,country));
            }
        }
        return locales.toArray(new Locale[locales.size()]);
    }

    public static Locale[] getStudioLocales() {
        Enumeration<URL> findEntries = BonitaStudioPreferencesPlugin.getDefault().getBundle().findEntries("/", "message*_*.properties", false);
        Set<Locale> locales = new TreeSet<Locale>(localeComparator);
        locales.add(Locale.ENGLISH);
        while(findEntries.hasMoreElements()){
            URL propertiesFile =  findEntries.nextElement();
            String file = propertiesFile.getFile();
            String[] split = file.replace(".properties", "").split("_");
            String language = null;
            String country = null;
            if(split.length == 2){
                language = split[1];
            }else if(split.length == 3){
                country = split[2];
            }
            if(language != null){
                locales.add(country == null ? new Locale(language) : new Locale(language,country));
            }
        }
        return locales.toArray(new Locale[locales.size()]);
    }
    
}
