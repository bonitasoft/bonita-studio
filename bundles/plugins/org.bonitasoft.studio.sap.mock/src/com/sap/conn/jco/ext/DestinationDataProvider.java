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

    public static String JCO_CLIENT = "jco.client.client";
    public static String JCO_USER = "jco.client.user";
    public static String JCO_PASSWD = "jco.client.passwd";
    public static String JCO_LANG = "jco.client.lang";
    public static String JCO_ASHOST = "jco.client.ashost";
    public static String JCO_SYSNR = "jco.client.sysnr";
    public static String JCO_GROUP = "jco.client.group";
    public static String JCO_R3NAME = "jco.client.r3name";
    public static String JCO_ALIAS_USER = "jco.client.alias_user";
    public static String JCO_AUTH_METHOD = "jco.destination.auth_method";
    public static String JCO_AUTH_TYPE = "jco.destination.auth_type";
    public static String JCO_AUTH_TYPE_CONFIGURED_USER = "CONFIGURED_USER";
    public static String JCO_AUTH_TYPE_CURRENT_USER = "CURRENT_USER";
    public static String JCO_CODEPAGE = "jco.client.codepage";
    public static String JCO_CPIC_TRACE = "jco.client.cpic_trace";
    public static String JCO_DEST = "jco.client.dest";
    public static String JCO_EXPIRATION_PERIOD = "jco.destination.expiration_check_period";
    public static String JCO_EXPIRATION_TIME = "jco.destination.expiration_time";
    public static String JCO_GETSSO2 = "jco.client.getsso2";
    public static String JCO_GWHOST = "jco.client.gwhost";
    public static String JCO_GWSERV = "jco.client.gwserv";
    public static String JCO_LCHECK = "jco.client.lcheck";
    public static String JCO_MAX_GET_TIME = "jco.destination.max_get_client_time";
    public static String JCO_MSHOST = "jco.client.mshost";
    public static String JCO_MSSERV = "jco.client.msserv";
    public static String JCO_MYSAPSSO2 = "jco.client.mysapsso2";
    public static String JCO_PCS = "jco.client.pcs";
    public static String JCO_PEAK_LIMIT = "jco.destination.peak_limit";
    public static String JCO_POOL_CAPACITY = "jco.destination.pool_capacity";
    public static String JCO_REPOSITORY_DEST = "jco.destination.repository_destination";
    public static String JCO_REPOSITORY_PASSWD = "jco.destination.repository.passwd";
    public static String JCO_REPOSITORY_SNC = "jco.destination.repository.snc_mode";
    public static String JCO_REPOSITORY_USER = "jco.destination.repository.user";
    public static String JCO_SAPROUTER = "jco.client.saprouter";
    public static String JCO_SNC_LIBRARY = "jco.client.snc_lib";
    public static String JCO_SNC_MODE = "jco.client.snc_mode";
    public static String JCO_SNC_MYNAME = "jco.client.snc_myname";
    public static String JCO_SNC_PARTNERNAME = "jco.client.snc_partnername";
    public static String JCO_SNC_QOP = "jco.client.snc_qop";
    public static String JCO_TPHOST = "jco.client.tphost";
    public static String JCO_TPNAME = "jco.client.tpname";
    public static String JCO_TRACE = "jco.client.trace";
    public static String JCO_TYPE = "jco.client.type";
    public static String JCO_USE_SAPGUI = "jco.client.use_sapgui";
    public static String JCO_USER_ID = "jco.destination.user_id";
    public static String JCO_X509CERT = "jco.client.x509cert";

}
