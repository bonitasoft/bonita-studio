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
package org.bonitasoft.studio.common;

/**
 * @author Romain Bioteau
 *
 */
public class ModelVersion {

    public static final String VERSION_5_0_PREVIEW = "5.0.preview";
    public static final String VERSION_5_0_M4 = "5.0.M4";
    public static final String VERSION_5_0_M5 = "5.0.M5";
    public static final String VERSION_5_0_RC1 = "5.0.RC1";
    public static final String VERSION_5_0 = "5.0";
    public static final String VERSION_5_1_M1 = "5.1.M1";
    public static final String VERSION_5_1 = "5.1";
    public static final String VERSION_5_2 = "5.2";
    public static final String VERSION_5_3 = "5.3";
    public static final String VERSION_5_4 = "5.4";
    public static final String VERSION_5_5 = "5.5";
    public static final String VERSION_5_6 = "5.6";
    public static final String VERSION_6_0 = "6.0";
    public static final String VERSION_6_0_0_ALPHA = "6.0.0-Alpha";
    public static final String VERSION_6_0_0_BETA = "6.0.0-Beta";
    public static final String VERSION_6_0_0_GA_001 = "6.0.0-ga-001";
    public static final String VERSION_6_0_0_GA_002 = "6.0.0-ga-002";
    public static final String VERSION_6_0_0_GA_003 = "6.0.0-ga-003";
    public static final String VERSION_6_0_0_GA_004 = "6.0.0-ga-004";
    public static final String VERSION_6_0_0_GA_005 = "6.0.0-ga-005";
    public static final String VERSION_6_0_1_001 = "6.0.1-001";
    public static final String VERSION_6_1_1_001 = "6.1.1-001";
    public static final String VERSION_6_3_0_001 = "6.3.0-001";
    public static final String CURRENT_VERSION = VERSION_6_3_0_001;


    public static boolean sameVersion(String version){
        return CURRENT_VERSION.equals(version);

    }

    public static boolean sameMinorVersion(String version){
        if(version == null){
            return false ;
        }
        String minor =  CURRENT_VERSION.substring(0, CURRENT_VERSION.lastIndexOf(".")) ;
        String[] split = version.split("\\.");
        String testedVersion = version ;
        if(split.length > 2){
            testedVersion = split[0] + "." + split[1] ;
        }
        return minor.equals(testedVersion) ;
    }

}
