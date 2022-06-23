/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common;

import org.bonitasoft.engine.expression.ExpressionConstants;

/**
 * @author Romain Bioteau
 */
public class BonitaConstants {

    public static final String HOME = "bonita.home";

    public static final String LOGGING_PROPERTY = "java.util.logging.config.file";

    public static final String JAAS_PROPERTY = "java.security.auth.login.config";

    public static final String ENVIRONMENT_PROPERTY = "org.ow2.bonita.environment";

    public static final String API_TYPE_PROPERTY = "org.ow2.bonita.api-type";

    public static final String INITIAL_CONTEXT_FACTORY_PROPERTY = "java.naming.factory.initial";

    public static final String PROVIDER_URL_PROPERTY = "java.naming.provider.url";

    public static final String SYSTEM_USER = "SYSTEM";

    public static final String JEE_SERVER_PROPERTY = "org.ow2.bonita.test.jee.server";

    public static final String DEFAULT_DOMAIN = "default";

    // used in examples/tests
    public static final String LOGIN_MODE_PROPERTY = "login.mode";

    public static final String LOGIN_MODE_TEST = "test";

    public static final String LOGIN_MODE_EXAMPLE = "example";

    public static final int MAX_QUERY_SIZE = 500;

    public static final int MAX_LIST_SIZE = 2500;

    public static final String TIMER_EVENT_PREFIX = "**bonita_timer**-";

    public static final String DEADLINE_EVENT_PREFIX = "**bonita_deadline**-";

    public static final String ASYNC_EVENT_PREFIX = "**bonita_async**-";

    // Encoding
    public static final String FILE_ENCONDING = "UTF-8";

    // XPath
    public static final String XPATH_VAR_SEPARATOR = "$";

    public static final Object XPATH_APPEND_FLAG = "APPEND";

    // Java
    public static final String JAVA_VAR_SEPARATOR = "#";

    // Context
    public static final String CONTEXT_PREFIX = "#context[";

    public static final String CONTEXT_SUFFIX = "]";

    public static final String CONTEXTS_FOLDER_IN_BAR = "contexts/";

    // REST
    public static final String REST_SERVER_ADDRESS_PROPERTY = "org.ow2.bonita.rest-server-address";

    public static final String REST_SERVER_EXCEPTION = "rest.server.exception";

    public static final String API_ACCESSOR = ExpressionConstants.API_ACCESSOR.getEngineConstantName();

    public static final String ENGINE_EXECUTION_CONTEXT = ExpressionConstants.ENGINE_EXECUTION_CONTEXT.getEngineConstantName();

    public static final String ACTIVITY_INSTANCE_ID = ExpressionConstants.ACTIVITY_INSTANCE_ID.getEngineConstantName();

    public static final String PROCESS_DEFINITION_ID = ExpressionConstants.PROCESS_DEFINITION_ID.getEngineConstantName();

    public static final String PARENT_PROCESS_INSTANCE_ID = ExpressionConstants.PROCESS_INSTANCE_ID.getEngineConstantName();

    public static final String ROOT_PROCESS_INSTANCE_ID = ExpressionConstants.ROOT_PROCESS_INSTANCE_ID.getEngineConstantName();
    
    //Organization
    
	public static final String STUDIO_TECHNICAL_USER_NAME = "studio";
	
	public static final String STUDIO_TECHNICAL_USER_FIRST_NAME = "Studio";

	public static final String STUDIO_TECHNICAL_USER_JOB_TITLE="Studio technical user for preview";

}
