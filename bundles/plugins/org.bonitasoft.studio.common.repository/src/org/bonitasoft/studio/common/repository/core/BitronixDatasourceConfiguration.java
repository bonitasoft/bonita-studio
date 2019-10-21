/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.core;

import java.util.HashMap;
import java.util.Map;

public class BitronixDatasourceConfiguration {

    private static final String DEFAULT_H2_USERNAME = "sa";
    private static final int MAX_POOL_SIZE = 50;
    private static final int MIN_POOL_SIZE = 1;
    private static final String H2_DRIVER_CLASSNAME = "org.h2.jdbcx.JdbcDataSource";
    private static final String URL_TEMPLATE = "jdbc:h2:file:%s;MVCC=TRUE;DB_CLOSE_ON_EXIT=FALSE;IGNORECASE=TRUE;AUTO_SERVER=TRUE;";

    private final String uniqueName;

    private int minPoolSize = MIN_POOL_SIZE;

    private int maxPoolSize = MAX_POOL_SIZE;

    private String className = H2_DRIVER_CLASSNAME;

    private String testQuery = "SELECT 1";

    private String user = DEFAULT_H2_USERNAME;

    private String password = "";

    private String url;

    public BitronixDatasourceConfiguration(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public int getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTestQuery() {
        return testQuery;
    }

    public void setTestQuery(String testQuery) {
        this.testQuery = testQuery;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setDatabaseFile(String dbFilePath) {
        this.url = String.format(URL_TEMPLATE, dbFilePath);
    }

    public Map<String, String> toMap(String datasourceId) {
        final Map<String, String> result = new HashMap<>();
        result.put(String.format("resource.%s.uniqueName", datasourceId), uniqueName);
        result.put(String.format("resource.%s.minPoolSize", datasourceId), String.valueOf(minPoolSize));
        result.put(String.format("resource.%s.maxPoolSize", datasourceId), String.valueOf(maxPoolSize));
        result.put(String.format("resource.%s.className", datasourceId), className);
        result.put(String.format("resource.%s.driverProperties.user", datasourceId), user);
        result.put(String.format("resource.%s.driverProperties.password", datasourceId), password);
        result.put(String.format("resource.%s.driverProperties.URL", datasourceId), url);
        result.put(String.format("resource.%s.testQuery", datasourceId), testQuery);
        return result;
    }

}
