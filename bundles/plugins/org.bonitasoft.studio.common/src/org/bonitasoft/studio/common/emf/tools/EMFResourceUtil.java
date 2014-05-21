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
package org.bonitasoft.studio.common.emf.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.FeatureNotFoundException;


/**
 * @author Romain Bioteau
 *
 */
public class EMFResourceUtil {

    private File eResourceFile;

    public EMFResourceUtil(File eResourceFile){
        Assert.isNotNull(eResourceFile);
        Assert.isLegal(eResourceFile.exists());
        this.eResourceFile = eResourceFile;

    }

    /**
     * 
     * @param xmiId
     * @param featureNames
     * @return return string values of featureNames for EObject with id xmiId 
     */
    public String[] getFeatureValuesFromEObjectId(String xmiId, String... featureNames) throws FeatureNotFoundException{
        Scanner scanner = null;
        try {
            scanner = new Scanner(eResourceFile, "UTF-8");
            List<String> values = new ArrayList<String>();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains(toXMIIdPattern(xmiId))){
                    for(String featureName : featureNames){
                        values.add(getFeatureValue(line, featureName));
                    }
                    return values.toArray(new String[values.size()]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            if(scanner != null){
                scanner.close();
            }
        }
        return null;
    }

    /**
     * 
     * @param xmiId
     * @param featureName
     * @return return string value of featureName for EObject with id xmiId 
     */
    public String getFeatureValueFromEObjectId(String xmiId, String featureName) throws FeatureNotFoundException{
        String[] values = getFeatureValuesFromEObjectId(xmiId, featureName);
        if(values != null && values.length == 1){
            return values[0];
        }
        return null;
    }

    public String[] getFeatureValuesFromEObjectId(String xmiId, EStructuralFeature... features) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(eResourceFile, "UTF-8");
            List<String> values = new ArrayList<String>();
            String xmiIdPattern = toXMIIdPattern(xmiId);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains(xmiIdPattern)){
                    for(EStructuralFeature feature : features){
                        values.add(getFeatureValue(line,feature));
                    }
                    return values.toArray(new String[values.size()]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            if(scanner != null){
                scanner.close();
            }
        }
        return null;
    }

    private String toXMIIdPattern(String xmiId) {
        return toFeaturePattern("xmi:id") + "\""+xmiId+"\"";
    }

    private String toXMITypePattern(String xmiType) {
        return toFeaturePattern("xmi:type") + "\""+xmiType+"\"";
    }

    private String toXSITypePattern(String xmiType) {
        return toFeaturePattern("xsi:type") + "\""+xmiType+"\"";
    }

    private String toFeaturePattern(String featureName) {
        return featureName+"=";
    }


    public Map<String, String[]> getFeatureValueFromEObjectType(String xmiType, EStructuralFeature... features) {
        Scanner scanner = null;
        Map<String, String[]> featureValuesByObjectId = new HashMap<String, String[]>();
        try {
            scanner = new Scanner(eResourceFile, "UTF-8");
            String xmiTypePattern = toXMITypePattern(xmiType);
            String xsiTypePattern = toXSITypePattern(xmiType);
            String tagTypePattern = toTagTypePattern(xmiType);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains(xmiTypePattern) || line.contains(xsiTypePattern) || line.contains(tagTypePattern)){
                    List<String> values = new ArrayList<String>();
                    for(EStructuralFeature feature : features){
                        values.add(getFeatureValue(line, feature));
                    }
                    String id;
                    try {
                        id = getFeatureValue(line, "xmi:id");
                    } catch (FeatureNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    featureValuesByObjectId.put(id, values.toArray(new String[values.size()]));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            if(scanner != null){
                scanner.close();
            }
        }
        return featureValuesByObjectId;

    }

    /**
     * @param xmiType
     * @return
     */
    private String toTagTypePattern(String xmiType) {
        return "<"+xmiType;
    }

    private String getFeatureValue(String line, String featureName) throws FeatureNotFoundException {
        String featurePattern = toFeaturePattern(featureName);
        int indexOf = line.indexOf(featurePattern);
        if(indexOf == -1){//Retrieve default value
            throw new FeatureNotFoundException(featureName, null, eResourceFile.getName(),0,0);
        }
        int nextIndex = indexOf+featurePattern.length();
        String value = line.substring(nextIndex+1, line.indexOf('"', nextIndex+1));
        return value;

    }

    protected String getFeatureValue(String line, EStructuralFeature feature) {
        String featurePattern = toFeaturePattern(feature.getName());
        int indexOf = line.indexOf(featurePattern);
        if(indexOf == -1){//Retrieve default value
            Object defaultValue = feature.getDefaultValue();
            if(defaultValue != null){
                return defaultValue.toString();
            }else{
                return "";
            }
        }else{
            int nextIndex = indexOf+featurePattern.length();
            String value = line.substring(nextIndex+1, line.indexOf('"', nextIndex+1));
            return value;
        }
    }


    public String[] getEObectIfFromEObjectType(String xmiType) throws FeatureNotFoundException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(eResourceFile, "UTF-8");
            List<String> values = new ArrayList<String>();
            String xmiTypePattern = toXMITypePattern(xmiType);
            String xsiTypePattern = toXSITypePattern(xmiType);
            String tagTypePattern = toTagTypePattern(xmiType);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.contains(xmiTypePattern) || line.contains(xsiTypePattern) || line.contains(tagTypePattern)){
                    values.add(getFeatureValue(line, "xmi:id"));
                }
            }
            return values.toArray(new String[values.size()]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            if(scanner != null){
                scanner.close();
            }
        }
    }
}
