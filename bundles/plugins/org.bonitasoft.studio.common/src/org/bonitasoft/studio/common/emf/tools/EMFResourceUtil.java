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
package org.bonitasoft.studio.common.emf.tools;

import java.io.InputStream;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;

public class EMFResourceUtil {

    private static final String UTF_8 = "UTF-8";

    public static String toXMIIdPattern(String xmiId) {
        return toFeaturePattern("xmi:id") + "\"" + xmiId + "\"";
    }

    public static String toXMITypePattern(String xmiType) {
        return toFeaturePattern("xmi:type") + "\"" + xmiType + "\"";
    }

    public static String toXSITypePattern(String xmiType) {
        return toFeaturePattern("xsi:type") + "\"" + xmiType + "\"";
    }

    public static String toFeaturePattern(String featureName) {
        return featureName + "=";
    }

    public static Map<String, String[]> getFeatureValueFromEObjectType(InputStream is, String xmiType,
            EStructuralFeature... features) {
        Map<String, String[]> featureValuesByObjectId = new HashMap<>();
        try (Scanner scanner = new Scanner(Channels.newChannel(is), UTF_8)) {
            String xmiTypePattern = toXMITypePattern(xmiType);
            String xsiTypePattern = toXSITypePattern(xmiType);
            String tagTypePattern = toTagTypePattern(xmiType);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(xmiTypePattern) || line.contains(xsiTypePattern) || line.contains(tagTypePattern)) {
                    List<String> values = new ArrayList<>();
                    for (EStructuralFeature feature : features) {
                        values.add(getFeatureValue(line, feature));
                    }
                    String id;
                    try {
                        id = getFeatureValue(line, xmiType, "xmi:id");
                    } catch (FeatureNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    featureValuesByObjectId.put(id, values.toArray(new String[values.size()]));
                }
            }
        }
        return featureValuesByObjectId;

    }

    public static String toTagTypePattern(String xmiType) {
        return "<" + xmiType;
    }

    public static String getFeatureValue(String line, String xmiType, String featureName)
            throws FeatureNotFoundException {
        String featurePattern = toFeaturePattern(featureName);
        int start = line.indexOf(xmiType);
        if (start != -1) {
            line = line.substring(start + xmiType.length());
        }
        int indexOf = line.indexOf(featurePattern);
        if (indexOf == -1) {//Retrieve default value
            throw new FeatureNotFoundException(featureName, null, line, 0, 0);
        }
        int nextIndex = indexOf + featurePattern.length();
        return line.substring(nextIndex + 1, line.indexOf('"', nextIndex + 1));

    }

    protected static String getFeatureValue(String line, EStructuralFeature feature) {
        String featurePattern = toFeaturePattern(feature.getName());
        int indexOf = line.indexOf(featurePattern);
        if (indexOf == -1) {//Retrieve default value
            Object defaultValue = feature.getDefaultValue();
            if (defaultValue != null) {
                return defaultValue.toString();
            } else {
                return "";
            }
        } else {
            int nextIndex = indexOf + featurePattern.length();
            return line.substring(nextIndex + 1, line.indexOf('"', nextIndex + 1));
        }
    }

    public static String[] getEObectIfFromEObjectType(InputStream is, String xmiType) throws FeatureNotFoundException {
        try (Scanner scanner = new Scanner(Channels.newChannel(is), UTF_8)) {
            List<String> values = new ArrayList<>();
            String xmiTypePattern = toXMITypePattern(xmiType);
            String xsiTypePattern = toXSITypePattern(xmiType);
            String tagTypePattern = toTagTypePattern(xmiType);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(xmiTypePattern) || line.contains(xsiTypePattern) || line.contains(tagTypePattern)) {
                    values.add(getFeatureValue(line, xmiType, "xmi:id"));
                }
            }
            return values.toArray(new String[values.size()]);
        }
    }

}
