/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.model;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Properties;

import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.junit.Test;

public class RestAPIExtensionArchetypeConfigurationTest {

    @Test
    public void should_create_a_valid_default_configuration_for_rest_api_extension_archetype() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration();

        assertThat(configuration.getPageName()).isEqualTo("resourceNameRestAPI");
        assertThat(configuration.getPageDisplayName()).isEqualTo(Messages.defaultAPIDisplayName);
        assertThat(configuration.getPageDescription()).isEqualTo(Messages.defaultAPIDisplayDescription);
        assertThat(configuration.getHttpVerb()).isEqualTo("GET");
        assertThat(configuration.getPathTemplate()).isEqualTo("resourceName");
        assertThat(configuration.getPermissions()).containsOnly("myPermission");
        assertThat(configuration.getUrlParameters()).contains("p", "c");
        assertThat(configuration.getBdmGroupId()).isNullOrEmpty();
    }

    @Test
    public void should_transform_configuration_of_rest_api_extension_archetype_as_properties() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration();

        final Properties properties = configuration.toProperties();

        assertThat(properties).containsEntry("apiName", "resourceNameRestAPI");
        assertThat(properties).containsEntry("apiDisplayName", Messages.defaultAPIDisplayName);
        assertThat(properties).containsEntry("apiDesc", Messages.defaultAPIDisplayDescription);
        assertThat(properties).containsEntry("pathTemplate", "resourceName");
        assertThat(properties).containsEntry("httpVerb", "GET");
        assertThat(properties).containsEntry("permissionNames", "myPermission");
        assertThat(properties).containsEntry("urlParameters", "p,c");
        assertThat(properties).containsEntry("bdmGroupId", "!");
        assertThat(properties).containsEntry("bdmVersion", "!");
    }

    @Test
    public void should_transform_permissions_to_comma_separated_list() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration();

        configuration.getPermissions().add("newPermission");
        final Properties properties = configuration.toProperties();

        assertThat(properties).containsEntry("permissionNames", "myPermission,newPermission");
    }

    @Test
    public void should_transform_url_parameters_to_comma_separated_list() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration();

        configuration.getUrlParameters().addAll(newArrayList("userId", "startDate"));
        final Properties properties = configuration.toProperties();

        assertThat(properties).containsEntry("urlParameters", "p,c,userId,startDate");
    }

    @Test
    public void should_set_bdmPackage_properties() throws Exception {
        final RestAPIExtensionArchetypeConfiguration configuration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration("org.test","1.1.0");

        final Properties properties = configuration.toProperties();

        assertThat(properties).containsEntry("bdmGroupId", "org.test");
        assertThat(properties).containsEntry("bdmVersion", "1.1.0");
    }
}
