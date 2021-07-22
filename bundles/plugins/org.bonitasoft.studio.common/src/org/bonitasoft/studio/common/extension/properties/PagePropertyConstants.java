package org.bonitasoft.studio.common.extension.properties;

public interface PagePropertyConstants {

    public static final String PATH_TEMAPLTE = "pathTemplate";
    public static final String NAME = "name";
    public static final String DISPLAY_NAME = "displayName";
    public static final String DESCRIPTION = "description";
    public static final String CONTENT_TYPE = "contentType";
    public static final String API_EXTENSIONS = "apiExtensions";
    public static final String PERMISSIONS = "permissions";
    public static final String CLASS_FILENAME = "classFileName";
    public static final String METHOD = "method";
    public static final String CLASS_NAME = "className";

    public enum HTTPMethod {
        GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS, TRACE
    }

}
