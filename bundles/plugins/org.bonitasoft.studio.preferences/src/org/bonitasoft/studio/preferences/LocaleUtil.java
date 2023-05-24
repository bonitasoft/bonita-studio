/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.preferences;

import java.io.File;
import java.net.URL;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.core.resources.ResourcesPlugin;

/**
 * @author Romain Bioteau
 */
public class LocaleUtil {

    public static Locale DEFAULT_LOCALE = Locale.ENGLISH;

    private static File getPortalI18NFolder() {
        return new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), "tomcat"
                + File.separator + "server"
                + File.separator + "i18n");
    }

    public static Locale[] getProtalLocales() {
        final File portalI18NFolder = getPortalI18NFolder();
        final Set<Locale> locales = new TreeSet<>(Comparator.comparing(Locale::getLanguage));
        locales.add(Locale.ENGLISH);
        locales.add(Locale.FRENCH);
        locales.add(new Locale("es"));
        locales.add(Locale.JAPANESE);
        locales.add(new Locale("pt", "BR"));
        if (portalI18NFolder.exists()) {
            Stream.of(portalI18NFolder.listFiles())
                    .map(File::getName)
                    .filter(file -> file.endsWith(".po") && file.contains("portal"))
                    .map(LocaleUtil::replacePartNotI18nRelated)
                    .filter(Predicate.not(String::isEmpty))
                    .map(poFile -> {
                        String country = null;
                        String language = null;
                        if (poFile.contains("_")) {
                            language = poFile.split("_")[0];
                            country = poFile.split("_")[1];
                        } else {
                            language = poFile;
                        }
                        return country == null ? new Locale(language) : new Locale(language, country);
                    })
                    .forEach(locales::add);
        }
        return locales.toArray(new Locale[locales.size()]);
    }

    static String replacePartNotI18nRelated(final String poFile) {
        return poFile
                .replace("mobile_", "")
                .replace("mobile-sp_", "")
                .replace("portal-sp_", "")
                .replace("portal_", "")
                .replace("portal-js-sp_", "")
                .replace("portal-js_", "")
                .replace(".po", "")
                .replace("mobile-sp", "")
                .replace("mobile", "")
                .replace("portal-sp", "")
                .replace("portal-js-sp", "")
                .replace("portal-js", "")
                .replace("portal", "");
    }

    public static Locale[] getStudioLocales() {
        final Enumeration<URL> findEntries = BonitaStudioPreferencesPlugin.getDefault().getBundle().findEntries("/",
                "message*_*.properties", false);
        final Set<Locale> locales = new TreeSet<>(Comparator.comparing(Locale::getLanguage));
        locales.add(Locale.ENGLISH);
        if (findEntries != null) {
            while (findEntries.hasMoreElements()) {
                final URL propertiesFile = findEntries.nextElement();
                final String file = propertiesFile.getFile();
                final String[] split = file.replace(".properties", "").split("_");
                String language = null;
                String country = null;
                if (split.length == 2) {
                    language = split[1];
                } else if (split.length == 3) {
                    language = split[1];
                    country = split[2];
                }
                if (language != null) {
                    locales.add(country == null ? new Locale(language) : new Locale(language, country));
                }
            }
        }
        return locales.toArray(new Locale[locales.size()]);
    }

}
