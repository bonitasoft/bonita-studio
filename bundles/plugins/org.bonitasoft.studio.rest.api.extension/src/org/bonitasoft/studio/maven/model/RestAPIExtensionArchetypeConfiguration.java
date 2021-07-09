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
import java.util.List;
import java.util.Properties;

import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.maven.contribution.MavenLocalRepositoryContributor;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.maven.i18n.Messages;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

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

    private String bdmVersion;

    public static RestAPIExtensionArchetypeConfiguration defaultArchetypeConfiguration(final String bdmGroupId,
            String bdmVersion) {
        final RestAPIExtensionArchetypeConfiguration configuration = new RestAPIExtensionArchetypeConfiguration();
        configuration.setBonitaVersion(MavenLocalRepositoryContributor.BONITA_ARTIFACT_VERSION);
        configuration.setPageName("resourceNameRestAPI");
        configuration.setPathTemplate("resourceName");
        configuration.setPageDisplayName(Messages.defaultAPIDisplayName);
        configuration.setPageDescription(Messages.defaultAPIDisplayDescription);
        configuration.setGroupId(defaultGroupId() + ".rest.api");
        configuration.setVersion("1.0.0-SNAPSHOT");
        configuration.setLanguage(GROOVY_LANGUAGE);
        configuration.setHttpVerb("GET");
        configuration.getPermissions().add("myPermission");
        configuration.getUrlParameters().add("p");
        configuration.getUrlParameters().add("c");
        configuration.setBdmGroupId(bdmGroupId);
        configuration.setBdmVersion(bdmVersion);
        configuration.setEnableBDMDependencies(bdmGroupId != null);
        return configuration;
    }

    private static String defaultGroupId() {
        if(CommonRepositoryPlugin.getDefault() == null) {
            return RepositoryPreferenceConstant.DEFAULT_GROUPID_VALUE;
        }
        return CommonRepositoryPlugin.getDefault().getPreferenceStore()
                .getString(RepositoryPreferenceConstant.DEFAULT_GROUPID);
    }

    public static RestAPIExtensionArchetypeConfiguration defaultArchetypeConfiguration() {
        return defaultArchetypeConfiguration(null, null);
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

    public void setBdmVersion(String bdmVersion) {
        this.bdmVersion = bdmVersion;
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
    public Properties toProperties() {
        final Properties properties = new Properties();
        properties.setProperty("bonitaVersion", bonitaVersion);
        properties.setProperty("sp", "false");
        properties.setProperty("language", getLanguage());
        properties.setProperty("apiName", getPageName());
        properties.setProperty("apiDisplayName", getPageDisplayName());
        properties.setProperty("apiDesc", getPageDescription());
        properties.setProperty("httpVerb", httpVerb);
        properties.setProperty("pathTemplate", pathTemplate);
        properties.setProperty("permissionNames", Joiner.on(",").join(permissions));
        properties.setProperty("urlParameters", urlParameters.isEmpty() ? "!" : Joiner.on(",").join(urlParameters));
        properties.setProperty("bdmGroupId",
                !Strings.isNullOrEmpty(bdmGroupId) && isEnableBDMDependencies() ? bdmGroupId : "!");
        properties.setProperty("bdmVersion",
                !Strings.isNullOrEmpty(bdmVersion) && isEnableBDMDependencies() ? bdmVersion : "!");
        properties.setProperty("wrapper", "false");
        return properties;
    }

    @Override
    public String getArtifactLabel() {
        return Messages.restApiExtensionRepositoryName;
    }

}
