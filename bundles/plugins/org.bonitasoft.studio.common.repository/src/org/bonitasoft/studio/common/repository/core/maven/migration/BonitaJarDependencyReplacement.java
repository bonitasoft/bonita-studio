/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven.migration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.CMISConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.DatabaseConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.DocumentConverterDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.DocumentTemplatingDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.EmailConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.GoogleCalendarConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.GroovyScriptConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.LDAPConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.RestConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.SAPConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.SalesforceConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.ShellScriptConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.TwitterConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.UIPathConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.connector.WebServiceConnectorDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.driver.DerbyJDBCDriverDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.driver.H2JDBCDriverDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.driver.HSQLJDBCDriverDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.driver.MySQLJDBCDriverDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.driver.Oracle5JDBCDriverDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.driver.Oracle6JDBCDriverDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.driver.PostgresJDBCDriverDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.driver.SQLLiteJDBCDriverDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.driver.SQLServerJDBCDriverDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.filter.CustomUserInfoActorFilterDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.filter.InitiatorActorFilterDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.filter.InitiatorManagerActorFilterDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.filter.SameTaskUserActorFilterDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.filter.SingleUserActorFilterDependenciesReplacement;
import org.bonitasoft.studio.common.repository.core.maven.migration.filter.UserManagerActorFilterDependenciesReplacement;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class BonitaJarDependencyReplacement {
    
    private static final String IMPLEMENTATION_NS = "http://www.bonitasoft.org/ns/connector/implementation/6.0";
    public static final String CONNECTOR_GROUP_ID = "org.bonitasoft.connectors";
    public static final String ACTOR_FILTER_GROUP_ID = "org.bonitasoft.actorfilter";
    private static List<BonitaJarDependencyReplacement> REPLACEMENTS;
    
    private String[] jarFilePatterns;
    private Dependency mavenDependency;
    
    public BonitaJarDependencyReplacement(Dependency mavenDependency,String... jarFilePatterns) {
        this.jarFilePatterns = jarFilePatterns;
        this.mavenDependency = mavenDependency;
    }
    
    public boolean matches(String jarName) {
        return Stream.of(jarFilePatterns).anyMatch(jarName::matches);
    }
    
    public boolean matchesDefinition(String definitionId) {
        return false;
    }

    
    public Dependency getMavenDependency() {
        return mavenDependency;
    }
    
    public static Set<String> getTransitiveDependencies(File implementationJarFile){
        try {
            List<Document> descriptors = findImplementationDescriptors(implementationJarFile);
            return descriptors.stream()
                    .map(BonitaJarDependencyReplacement::readJarDependencies)
                    .flatMap(Collection::stream)
                    .filter(jarName -> !Objects.equals(jarName, implementationJarFile.getName()))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
        return Collections.emptySet();
    }
    
    private static Set<String> readJarDependencies(Document document) {
        Set<String> jarDependencies = new HashSet<>();
        NodeList dependencyJars = document.getElementsByTagName("jarDependency");
        for (int i = 0; i < dependencyJars.getLength(); i++) {
            jarDependencies.add(dependencyJars.item(i).getTextContent());
        }
        return jarDependencies;
    }

    private static List<Document> findImplementationDescriptors(File artifactFile) throws IOException {
        try (JarFile jarFile = new JarFile(artifactFile)) {
            return jarFile.stream()
            .filter(entry -> entry.getName().endsWith(".impl"))
            .map(entry -> toDocument(jarFile, entry))
            .collect(Collectors.toList());
        }
    }
    
    private static Document toDocument(JarFile jarFile, JarEntry entry) {
        try (InputStream is = jarFile.getInputStream(entry)) {
            return asXMLDocument(is, IMPLEMENTATION_NS);
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    private static Document asXMLDocument(InputStream source, String namespace) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(source));
            Node firstChild = document.getFirstChild();
            String namespaceURI = firstChild.getNamespaceURI();
            if (namespace.equals(namespaceURI)) {
                return document;
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            return null;
        }
        return null;
    }
    
    protected static Dependency dependency(String groupId, String artifactId, String version) {
        Dependency dependency = new Dependency();
        dependency.setArtifactId(artifactId);
        dependency.setGroupId(groupId);
        dependency.setVersion(version);
        dependency.setScope(Artifact.SCOPE_COMPILE);
        return dependency;
    }
    
    public static List<BonitaJarDependencyReplacement> getBonitaJarDependencyReplacements(){
        if(REPLACEMENTS == null) {
            REPLACEMENTS = new ArrayList<>();
            
            // Connectors
            REPLACEMENTS.add(new CMISConnectorDependenciesReplacement());
            REPLACEMENTS.add(new DatabaseConnectorDependenciesReplacement());
            REPLACEMENTS.add(new DocumentConverterDependenciesReplacement());
            REPLACEMENTS.add(new DocumentTemplatingDependenciesReplacement());
            REPLACEMENTS.add(new EmailConnectorDependenciesReplacement());
            REPLACEMENTS.add(new GoogleCalendarConnectorDependenciesReplacement());
            REPLACEMENTS.add(new GroovyScriptConnectorDependenciesReplacement());
            REPLACEMENTS.add(new LDAPConnectorDependenciesReplacement());
            REPLACEMENTS.add(new RestConnectorDependenciesReplacement());
            REPLACEMENTS.add(new SalesforceConnectorDependenciesReplacement());
            REPLACEMENTS.add(new SAPConnectorDependenciesReplacement());
            REPLACEMENTS.add(new ShellScriptConnectorDependenciesReplacement());
            REPLACEMENTS.add(new TwitterConnectorDependenciesReplacement());
            REPLACEMENTS.add(new UIPathConnectorDependenciesReplacement());
            REPLACEMENTS.add(new WebServiceConnectorDependenciesReplacement());
            
            // Actor filters
            REPLACEMENTS.add(new CustomUserInfoActorFilterDependenciesReplacement());
            REPLACEMENTS.add(new InitiatorActorFilterDependenciesReplacement());
            REPLACEMENTS.add(new InitiatorManagerActorFilterDependenciesReplacement());
            REPLACEMENTS.add(new SameTaskUserActorFilterDependenciesReplacement());
            REPLACEMENTS.add(new SingleUserActorFilterDependenciesReplacement());
            REPLACEMENTS.add(new UserManagerActorFilterDependenciesReplacement());
            
            // Db drivers
            REPLACEMENTS.add(new DerbyJDBCDriverDependenciesReplacement());
            REPLACEMENTS.add(new H2JDBCDriverDependenciesReplacement());
            REPLACEMENTS.add(new HSQLJDBCDriverDependenciesReplacement());
            REPLACEMENTS.add(new Oracle5JDBCDriverDependenciesReplacement());
            REPLACEMENTS.add(new Oracle6JDBCDriverDependenciesReplacement());
            REPLACEMENTS.add(new PostgresJDBCDriverDependenciesReplacement());
            REPLACEMENTS.add(new SQLLiteJDBCDriverDependenciesReplacement());
            REPLACEMENTS.add(new SQLServerJDBCDriverDependenciesReplacement());
            REPLACEMENTS.add(new MySQLJDBCDriverDependenciesReplacement());
            
        }
        return REPLACEMENTS;
        
    }

}
