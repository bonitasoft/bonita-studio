/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control.model.dependency;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.bonitasoft.plugin.analyze.report.model.Artifact;
import org.bonitasoft.plugin.analyze.report.model.CustomPage;
import org.bonitasoft.plugin.analyze.report.model.Definition;
import org.bonitasoft.plugin.analyze.report.model.Implementation;
import org.bonitasoft.plugin.analyze.report.model.RestAPIExtension;
import org.bonitasoft.plugin.analyze.report.model.Theme;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.swt.graphics.Image;

public class BonitaArtifactDependencyConverter {

    private ProjectDependenciesStore dependenciesStore;
    private LocalDependenciesStore localDependenciesStore;
    private Map<ArtifactType, Image> defaultIconsMap;
    private Object matchingArtifact;
    private MavenXpp3Reader pomReader;

    public BonitaArtifactDependencyConverter(ProjectDependenciesStore dependenciesStore,
            LocalDependenciesStore localDependenciesStore) {
        this.dependenciesStore = dependenciesStore;
        this.localDependenciesStore = localDependenciesStore;
        this.pomReader = new MavenXpp3Reader();

        defaultIconsMap = new EnumMap<>(ArtifactType.class);
        defaultIconsMap.put(ArtifactType.CONNECTOR, Pics.getImage(PicsConstants.connectorDefaultIcon));
        defaultIconsMap.put(ArtifactType.ACTOR_FILTER, Pics.getImage(PicsConstants.actorfilterDefaultIcon));
        defaultIconsMap.put(ArtifactType.THEME, Pics.getImage(PicsConstants.themeDefaultIcon));
        defaultIconsMap.put(ArtifactType.REST_API, Pics.getImage(PicsConstants.restApiDefaultIcon));
        defaultIconsMap.put(ArtifactType.PAGE, Pics.getImage(PicsConstants.pageDefaultIcon));
        defaultIconsMap.put(ArtifactType.FORM, Pics.getImage(PicsConstants.pageDefaultIcon));
    }

    public BonitaArtifactDependency toBonitaArtifactDependency(Dependency dep) {
        BonitaArtifactDependency bonitaDep = new BonitaArtifactDependency();
        bonitaDep.setFromMarketplace(false);
        bonitaDep.setLocalDependency(localDependenciesStore.isLocalDependency(dep));
        BonitaArtifactDependencyVersion version = new BonitaArtifactDependencyVersion();
        version.setVersion(dep.getVersion());
        bonitaDep.setVersions(Arrays.asList(version));
        ArtifactType type = findType(dep);
        bonitaDep.setArtifactType(type);
        if (Objects.equals(type, ArtifactType.OTHER)) {
            return bonitaDep;
        }
        switch (type) {
            case THEME:
            case REST_API:
            case FORM:
            case PAGE:
                fillCustomPage(dep, bonitaDep);
                break;
            case CONNECTOR:
            case ACTOR_FILTER:
            default:
                fillConnector(dep, bonitaDep);
                break;
        }
        bonitaDep.setIconImage(defaultIconsMap.get(type));
        return bonitaDep;
    }

    private void fillConnector(Dependency dep, BonitaArtifactDependency bonitaDep) {
        var file = matchingArtifact instanceof Definition
                ? ((Definition) matchingArtifact).getArtifact().getFile()
                : ((Implementation) matchingArtifact).getArtifact().getFile();
        if (Paths.get(file).toFile().exists()) {
            setDepInfoFromMavenMetaInf(dep, bonitaDep, file);
        }
        if (Strings.isNullOrEmpty(bonitaDep.getName())) {
            bonitaDep.setName(String.format("%s:%s", dep.getGroupId(), dep.getArtifactId()));
        }
    }

    private void setDepInfoFromMavenMetaInf(Dependency dep, BonitaArtifactDependency bonitaDep, String file) {
        try (ZipFile archiveFile = new ZipFile(file)) {
            archiveFile.stream()
                    .filter(entry -> entry.getName()
                            .matches(String.format("META-INF/maven/%s/%s/pom.xml", dep.getGroupId(),
                                    dep.getArtifactId())))
                    .map(entry -> {
                        try (InputStream is = archiveFile.getInputStream(entry)) {
                            return pomReader.read(is);
                        } catch (IOException | XmlPullParserException e) {
                            BonitaStudioLog.error(e);
                            return null;
                        }
                    }).findFirst().ifPresent(model -> {
                        bonitaDep.setName(model.getName());
                        bonitaDep.setDescription(model.getDescription());
                        if (model.getScm() != null && Strings.hasText(model.getScm().getUrl())) {
                            bonitaDep.setSCMUrl(resolve(model.getScm().getUrl(), model));
                        }
                    });
        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
    }

    /**
     * Replace properties used in the url
     * This is not a proper maven resolution.
     */
    private String resolve(String url, Model model) {
        Matcher m = Pattern.compile("\\$\\{(.*?)}").matcher(url);
        Map<String, String> replacements = new HashMap<>();
        while (m.find()) {
            String property = m.group(1);
            if (model.getProperties().containsKey(property)) {
                replacements.put(property, model.getProperties().getProperty(property));
            } else if ("project.artifactId".equals(property)) {
                replacements.put(property, model.getArtifactId());
            } else if ("project.version".equals(property)) {
                replacements.put(property, model.getVersion());
            } else if ("project.groupId".equals(property)) {
                replacements.put(property, model.getGroupId());
            }
        }
        for (var entry : replacements.entrySet()) {
            url = url.replace("${" + entry.getKey() + "}", entry.getValue());
        }
        return url;
    }

    private void fillCustomPage(Dependency dep, BonitaArtifactDependency bonitaDep) {
        CustomPage bonitaArtifact = (CustomPage) matchingArtifact;
        var file = bonitaArtifact.getArtifact().getFile();
        String name = bonitaArtifact.getDisplayName() != null ? bonitaArtifact.getDisplayName()
                : bonitaArtifact.getName();
        bonitaDep.setName(name);
        bonitaDep.setDescription(bonitaArtifact.getDescription());
        if (Paths.get(file).toFile().exists()) {
            setDepInfoFromMavenMetaInf(dep, bonitaDep, file);
        }

    }

    private ArtifactType findType(Dependency dep) {
        if (isConnector(dep)) {
            return ArtifactType.CONNECTOR;
        } else if (isTheme(dep)) {
            return ArtifactType.THEME;
        } else if (isRestAPI(dep)) {
            return ArtifactType.REST_API;
        } else if (isActorFilter(dep)) {
            return ArtifactType.ACTOR_FILTER;
        }
        return ArtifactType.OTHER;
    }

    private boolean isActorFilter(Dependency dep) {
        for (Definition actorDef : dependenciesStore.getActorFilterDefinitions()) {
            if (matches(actorDef.getArtifact(), dep)) {
                this.matchingArtifact = actorDef;
                return true;
            }
        }
        for (Implementation actorImpl : dependenciesStore.getActorFilterImplementations()) {
            if (matches(actorImpl.getArtifact(), dep)) {
                this.matchingArtifact = actorImpl;
                return true;
            }
        }
        return false;
    }

    private boolean isConnector(Dependency dep) {
        for (Definition connectorDef : dependenciesStore.getConnectorDefinitions()) {
            if (matches(connectorDef.getArtifact(), dep)) {
                this.matchingArtifact = connectorDef;
                return true;
            }
        }
        for (Implementation connectorImpl : dependenciesStore.getConnectorImplementations()) {
            if (matches(connectorImpl.getArtifact(), dep)) {
                this.matchingArtifact = connectorImpl;
                return true;
            }
        }
        return false;
    }

    private boolean isTheme(Dependency dep) {
        for (Theme theme : dependenciesStore.getThemes()) {
            if (matches(theme.getArtifact(), dep)) {
                this.matchingArtifact = theme;
                return true;
            }
        }
        return false;
    }

    private boolean isRestAPI(Dependency dep) {
        for (RestAPIExtension restApi : dependenciesStore.getRestAPIExtensions()) {
            if (matches(restApi.getArtifact(), dep)) {
                this.matchingArtifact = restApi;
                return true;
            }
        }
        return false;
    }

    private boolean matches(Artifact artifact, Dependency dep) {
        return Objects.equals(artifact.getGroupId(), dep.getGroupId())
                && Objects.equals(artifact.getArtifactId(), dep.getArtifactId())
                && Objects.equals(artifact.getVersion(), dep.getVersion());
    }

}
