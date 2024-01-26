/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.PostConstruct;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.DependencyResolver;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.la.application.repository.ApplicationFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class ApplicationDependencyResolver implements DependencyResolver<ApplicationFileStore> {

    @PostConstruct
    public void inject(IEclipseContext context) {
        if (!context.containsKey(DependencyResolver.class)) {
            context.set(DependencyResolver.class, this);
        }
    }

    @Override
    public List<IRepositoryFileStore<?>> findDependencies(ApplicationFileStore fStore) {
        List<IRepositoryFileStore<?>> result = new ArrayList<>();
        try {
            WebPageRepositoryStore webStore = fStore.getRepository().getRepositoryStore(WebPageRepositoryStore.class);
            ExtensionRepositoryStore themeRepositoryStore = fStore.getRepository()
                    .getRepositoryStore(ExtensionRepositoryStore.class);
            ApplicationNodeContainer applicationNodeContainer = fStore.getContent();
            //Application Pages
            result.addAll(findPageDependencies(webStore, applicationNodeContainer));

            //Layout
            applicationNodeContainer
                    .getApplications()
                    .stream()
                    .map(ApplicationNode::getLayout)
                    .distinct()
                    .map(customPage -> webStore.findByPageId(customPage).orElse(null))
                    .filter(Objects::nonNull)
                    .forEach(result::add);

            //Theme
            applicationNodeContainer
                    .getApplications()
                    .stream()
                    .map(ApplicationNode::getTheme)
                    .map(theme -> themeRepositoryStore.findTheme(theme).orElse(null))
                    .filter(Objects::nonNull)
                    .distinct()
                    .forEach(result::add);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
        }
        return result;
    }

    protected List<IRepositoryFileStore<?>> findPageDependencies(WebPageRepositoryStore webStore,
            ApplicationNodeContainer applicationNodeContainer) {
        List<IRepositoryFileStore<?>> pageDependencies = applicationNodeContainer
                .getApplications()
                .stream()
                .map(ApplicationNode::getApplicationPages)
                .flatMap(Collection::stream)
                .map(ApplicationPageNode::getCustomPage)
                .distinct()
                .map(customPage -> webStore.findByPageId(customPage).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<IRepositoryFileStore<?>> restApiDependencies = pageDependencies.stream()
                .filter(WebPageFileStore.class::isInstance)
                .map(WebPageFileStore.class::cast)
                .flatMap(page -> findRestAPIDependencies(page,
                        page.getRepository().getRepositoryStore(ExtensionRepositoryStore.class)))
                .collect(Collectors.toList());
        pageDependencies.addAll(restApiDependencies);

        return pageDependencies;
    }

    protected Stream<IRepositoryFileStore<?>> findRestAPIDependencies(WebPageFileStore page,
            ExtensionRepositoryStore restApiStore) {
        if (page instanceof WebPageFileStore) {
            Collection<String> extensionResources = page.getPageResources();
            return extensionResources.stream()
                    .map(resource -> restApiStore.findByRestResource(resource))
                    .filter(Optional::isPresent)
                    .map(Optional::get);
        }
        return Stream.empty();
    }

}
