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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Romain Bioteau
 *
 */
public class ProductVersion {

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
    public static final String VERSION_6_0_0_GA = "6.0.0-GA";
    public static final String VERSION_6_0_1 = "6.0.1";
    public static final String VERSION_6_0_2 = "6.0.2";
    public static final String VERSION_6_0_3 = "6.0.3";
    public static final String VERSION_6_0_4 = "6.0.4";
    public static final String VERSION_6_0_5 = "6.0.5";
    public static final String VERSION_6_0_6 = "6.0.6";
    public static final String VERSION_6_1_0 = "6.1.0";


    public static final String CURRENT_VERSION = VERSION_6_1_0;

    public static final List<String> orderedVerions = new ArrayList<String>();
    static{
    	orderedVerions.add(VERSION_6_0_0_ALPHA);
    	orderedVerions.add(VERSION_6_0_0_BETA);
    	orderedVerions.add(VERSION_6_0_0_GA);
    	orderedVerions.add(VERSION_6_0_1);
    	orderedVerions.add(VERSION_6_0_2);
    	orderedVerions.add(VERSION_6_0_3);
    	orderedVerions.add(VERSION_6_0_4);
    	orderedVerions.add(VERSION_6_0_5);
    	orderedVerions.add(VERSION_6_1_0);
    }

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

	public static boolean canBeMigrated(String version) {
		int latestVerionRank = orderedVerions.indexOf(CURRENT_VERSION);
		int verisonRank = orderedVerions.indexOf(version);
		return verisonRank != -1 && verisonRank < latestVerionRank;
	}

}
