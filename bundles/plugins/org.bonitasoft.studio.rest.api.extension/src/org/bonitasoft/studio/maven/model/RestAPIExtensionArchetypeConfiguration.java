/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.maven.i18n.Messages;

import com.google.common.base.Joiner;

public class RestAPIExtensionArchetypeConfiguration extends CustomPageArchetypeConfiguration {

    public static final String JAVA_LANGUAGE = "java";
    public static final String GROOVY_LANGUAGE = "groovy";

    private String bonitaVersion;

    private String pathTemplate;

    private String httpVerb;

    private String language;

    private List<String> permissions = new ArrayList<>();

    private List<String> urlParameters = new ArrayList<>();

    private String bdmGroupId;

    private boolean enableBDMDependencies;

    public static RestAPIExtensionArchetypeConfiguration defaultArchetypeConfiguration(ProjectMetadata projectMetadata, boolean addBdmDependency) {
        final RestAPIExtensionArchetypeConfiguration configuration = new RestAPIExtensionArchetypeConfiguration();
        configuration.setJavaPackage(projectMetadata.getGroupId()+".rest.api");
        configuration.setBonitaVersion(ProductVersion.BONITA_RUNTIME_VERSION);
        configuration.setPageName("resourceNameRestAPI");
        configuration.setPathTemplate("resourceName");
        configuration.setPageDisplayName(Messages.defaultAPIDisplayName);
        configuration.setPageDescription(Messages.defaultAPIDisplayDescription);
        configuration.setGroupId(projectMetadata.getGroupId());
        configuration.setVersion(projectMetadata.getVersion());
        configuration.setLanguage(GROOVY_LANGUAGE);
        configuration.setHttpVerb("GET");
        configuration.getPermissions().add("myPermission");
        configuration.getUrlParameters().add("p");
        configuration.getUrlParameters().add("c");
        configuration.setEnableBDMDependencies(addBdmDependency);
        return configuration;
    }

    public static RestAPIExtensionArchetypeConfiguration defaultArchetypeConfiguration(ProjectMetadata projectMetadata) {
        return defaultArchetypeConfiguration(projectMetadata, false);
    }

    public void setBonitaVersion(final String bonitaVersion) {
        this.bonitaVersion = bonitaVersion;
    }

    public String getBonitaVersion() {
        return bonitaVersion;
    }

    public void setHttpVerb(final String httpVerb) {
        this.httpVerb = httpVerb;
    }

    public void setPathTemplate(final String pathTemplate) {
        this.pathTemplate = pathTemplate;
    }

    public void setPermissions(final List<String> permissions) {
        this.permissions = permissions;
    }

    public void setUrlParameters(final List<String> urlParameters) {
        this.urlParameters = urlParameters;
    }

    public String getPathTemplate() {
        return pathTemplate;
    }

    public String getHttpVerb() {
        return httpVerb;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public List<String> getUrlParameters() {
        return urlParameters;
    }

    public String getBdmGroupId() {
        return bdmGroupId;
    }

    public void setBdmGroupId(final String bdmGroupId) {
        this.bdmGroupId = bdmGroupId;
    }

    public boolean isEnableBDMDependencies() {
        return enableBDMDependencies;
    }

    public void setEnableBDMDependencies(final boolean enableBDMDependencies) {
        this.enableBDMDependencies = enableBDMDependencies;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public Map<String, String> toProperties() {
        final Map<String, String> properties = new HashMap<>();
        properties.put("bonitaVersion", bonitaVersion);
        properties.put("sp", "false");
        properties.put("language", getLanguage());
        properties.put("apiName", getPageName());
        properties.put("apiDisplayName", getPageDisplayName());
        properties.put("apiDesc", getPageDescription());
        properties.put("httpVerb", httpVerb);
        properties.put("pathTemplate", pathTemplate);
        properties.put("permissionNames", Joiner.on(",").join(permissions));
        properties.put("urlParameters", urlParameters.isEmpty() ? "!" : Joiner.on(",").join(urlParameters));
        properties.put("wrapper", "false");
        return properties;
    }

    @Override
    public String getArtifactLabel() {
        return Messages.restApiExtensionRepositoryName;
    }

}
