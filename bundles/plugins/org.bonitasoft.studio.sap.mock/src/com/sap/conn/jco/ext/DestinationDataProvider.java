/**
 * Copyright (C) 2020 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package com.sap.conn.jco.ext;

/**
 * This is a mock class, used to be able to build the plugin without the sap library (sapjco3.jar)
 * This class is not packaged at build time.
 * Users are supposed to provide the third tier library to use the advanced sap feature.
 */
public interface DestinationDataProvider {

    public static String JCO_CLIENT = "";
    public static String JCO_USER = "";
    public static String JCO_PASSWD = "";
    public static String JCO_LANG = "";
    public static String JCO_ASHOST = "";
    public static String JCO_SYSNR = "";
    public static String JCO_GROUP = "";
    public static String JCO_R3NAME = "";
    public static String JCO_ALIAS_USER = "";
    public static String JCO_AUTH_METHOD = "";
    public static String JCO_AUTH_TYPE = "";
    public static String JCO_AUTH_TYPE_CONFIGURED_USER = "";
    public static String JCO_AUTH_TYPE_CURRENT_USER = "";
    public static String JCO_CODEPAGE = "";
    public static String JCO_CPIC_TRACE = "";
    public static String JCO_DEST = "";
    public static String JCO_EXPIRATION_PERIOD = "";
    public static String JCO_EXPIRATION_TIME = "";
    public static String JCO_GETSSO2 = "";
    public static String JCO_GWHOST = "";
    public static String JCO_GWSERV = "";
    public static String JCO_LCHECK = "";
    public static String JCO_MAX_GET_TIME = "";
    public static String JCO_MSHOST = "";
    public static String JCO_MSSERV = "";
    public static String JCO_MYSAPSSO2 = "";
    public static String JCO_PCS = "";
    public static String JCO_PEAK_LIMIT = "";
    public static String JCO_POOL_CAPACITY = "";
    public static String JCO_REPOSITORY_DEST = "";
    public static String JCO_REPOSITORY_PASSWD = "";
    public static String JCO_REPOSITORY_SNC = "";
    public static String JCO_REPOSITORY_USER = "";
    public static String JCO_SAPROUTER = "";
    public static String JCO_SNC_LIBRARY = "";
    public static String JCO_SNC_MODE = "";
    public static String JCO_SNC_MYNAME = "";
    public static String JCO_SNC_PARTNERNAME = "";
    public static String JCO_SNC_QOP = "";
    public static String JCO_TPHOST = "";
    public static String JCO_TPNAME = "";
    public static String JCO_TRACE = "";
    public static String JCO_TYPE = "";
    public static String JCO_USE_SAPGUI = "";
    public static String JCO_USER_ID = "";
    public static String JCO_X509CERT = "";

}
