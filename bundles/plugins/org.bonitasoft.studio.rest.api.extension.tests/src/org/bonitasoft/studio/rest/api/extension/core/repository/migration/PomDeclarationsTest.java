/**
 * Copyright (C) 2026 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.rest.api.extension.core.repository.migration;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.Profile;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.junit.Test;

/**
 * The shared answer to "does anything still name this property?". Tested directly because two steps now delete a
 * property on the strength of it: a miss corrupts a pom rather than merely adding noise, and it was previously
 * exercised only through the steps that call it.
 */
public class PomDeclarationsTest {

    private static final String PROPERTY = "groovy.version";
    private static final String REFERENCE = "${groovy.version}";

    @Test
    public void should_find_a_reference_in_a_dependency_version() {
        var model = new Model();
        model.getDependencies().add(dependency(REFERENCE));

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_embedded_in_a_composite_version() {
        // ${groovy.version}-01 is how the groovy-eclipse batch versions are pinned: matched by containment,
        // since equality would miss it and the caller then deletes the property it still needs
        var model = new Model();
        model.getDependencies().add(dependency(REFERENCE + "-01"));

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_in_managed_dependencies() {
        var model = new Model();
        var management = new DependencyManagement();
        management.addDependency(dependency(REFERENCE));
        model.setDependencyManagement(management);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_in_another_property_value() {
        var model = new Model();
        model.getProperties().setProperty("groovy-all.version", REFERENCE);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_in_a_plugin_version() {
        var model = new Model();
        var build = new Build();
        build.getPlugins().add(plugin(REFERENCE));
        model.setBuild(build);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_in_a_plugin_dependency_version() {
        var model = new Model();
        var build = new Build();
        var plugin = plugin("3.11.0");
        plugin.getDependencies().add(dependency(REFERENCE));
        build.getPlugins().add(plugin);
        model.setBuild(build);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_in_plugin_management() {
        var model = new Model();
        var build = new Build();
        var pluginManagement = new PluginManagement();
        pluginManagement.addPlugin(plugin(REFERENCE));
        build.setPluginManagement(pluginManagement);
        model.setBuild(build);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_a_profile_contributes() {
        var model = new Model();
        var profile = new Profile();
        profile.getDependencies().add(dependency(REFERENCE));
        model.getProfiles().add(profile);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_in_a_profile_property_value() {
        // the asymmetry this closed: profile plugins were scanned while profile properties were not
        var model = new Model();
        var profile = new Profile();
        profile.getProperties().setProperty("groovy-all.version", REFERENCE);
        model.getProfiles().add(profile);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_in_a_profile_plugin() {
        var model = new Model();
        var profile = new Profile();
        var build = new Build();
        build.getPlugins().add(plugin(REFERENCE));
        profile.setBuild(build);
        model.getProfiles().add(profile);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_not_find_a_property_nothing_names() {
        var model = new Model();
        model.getDependencies().add(dependency("3.0.19"));
        model.getProperties().setProperty(PROPERTY, "3.0.19");

        // the property's own declaration is not a reference to itself
        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isFalse();
    }

    @Test
    public void should_not_trip_on_declarations_without_a_version() {
        // a version left to dependencyManagement or an imported BOM
        var model = new Model();
        model.getDependencies().add(dependency(null));
        var build = new Build();
        build.getPlugins().add(plugin(null));
        model.setBuild(build);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isFalse();
    }

    @Test
    public void should_not_confuse_a_property_whose_name_extends_another() {
        // groovy.version must not be considered named by a reference to groovy.version.suffix
        var model = new Model();
        model.getDependencies().add(dependency("${groovy.version.suffix}"));

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isFalse();
    }

    @Test
    public void should_find_a_reference_nested_in_a_plugin_configuration() {
        var model = new Model();
        var build = new Build();
        var plugin = plugin("3.0.2");
        var dependency = node("dependency", null);
        dependency.addChild(node("groupId", "org.apache.groovy"));
        dependency.addChild(node("artifactId", "groovy"));
        dependency.addChild(node("version", REFERENCE));
        var dependencies = node("dependencies", null);
        dependencies.addChild(dependency);
        var configuration = node("configuration", null);
        configuration.addChild(dependencies);
        plugin.setConfiguration(configuration);
        build.getPlugins().add(plugin);
        model.setBuild(build);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_in_an_execution_configuration() {
        var model = new Model();
        var build = new Build();
        var plugin = plugin("3.0.2");
        var execution = new PluginExecution();
        execution.setId("compile");
        var configuration = node("configuration", null);
        configuration.addChild(node("targetBytecode", REFERENCE));
        execution.setConfiguration(configuration);
        plugin.getExecutions().add(execution);
        build.getPlugins().add(plugin);
        model.setBuild(build);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_find_a_reference_in_a_configuration_attribute() {
        var model = new Model();
        var build = new Build();
        var plugin = plugin("3.0.2");
        var source = node("source", null);
        source.setAttribute("combine.self", REFERENCE);
        var configuration = node("configuration", null);
        configuration.addChild(source);
        plugin.setConfiguration(configuration);
        build.getPlugins().add(plugin);
        model.setBuild(build);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isTrue();
    }

    @Test
    public void should_not_find_a_property_a_configuration_does_not_name() {
        var model = new Model();
        var build = new Build();
        var plugin = plugin("3.0.2");
        var configuration = node("configuration", null);
        configuration.addChild(node("targetBytecode", "17"));
        plugin.setConfiguration(configuration);
        build.getPlugins().add(plugin);
        model.setBuild(build);

        assertThat(PomDeclarations.namesProperty(model, PROPERTY)).isFalse();
    }

    private Xpp3Dom node(String name, String value) {
        var node = new Xpp3Dom(name);
        node.setValue(value);
        return node;
    }

    private Dependency dependency(String version) {
        var dependency = new Dependency();
        dependency.setGroupId("org.codehaus.groovy");
        dependency.setArtifactId("groovy");
        dependency.setVersion(version);
        return dependency;
    }

    private Plugin plugin(String version) {
        var plugin = new Plugin();
        plugin.setGroupId("org.apache.maven.plugins");
        plugin.setArtifactId("maven-compiler-plugin");
        plugin.setVersion(version);
        return plugin;
    }
}
