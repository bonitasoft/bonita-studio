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
package org.bonitasoft.studio.la.application.repository;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;

public class ApplicationRepositoryStore extends AbstractRepositoryStore<ApplicationFileStore> {

    private static final String XML_EXTENSION = "xml";

    private final ApplicationNodeContainerConverter applicationNodeContainerConverter = new CustomApplicationNodeContainerConverter();

    public ApplicationNodeContainerConverter getConverter() {
        return applicationNodeContainerConverter;
    }

    @Override
    public String getName() {
        return "applications"; //$NON-NLS-N$
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IDisplayable#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.applicationStoreName;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IDisplayable#getIcon()
     */
    @Override
    public Image getIcon() {
        return LivingApplicationPlugin.getImage("icons/applicationStore.gif");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public ApplicationFileStore createRepositoryFileStore(String fileName) {
        return new ApplicationFileStore(fileName, this);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return Collections.unmodifiableSet(newHashSet(XML_EXTENSION));
    }

    public Stream<ApplicationNode> findByProfile(String profile) {
        return getChildren().stream()
                .map(toApplicationNodeContainer())
                .filter(Objects::nonNull)
                .flatMap(container -> container.getApplications().stream())
                .filter(appNode -> Objects.equals(appNode.getProfile(), profile));
    }

    private Function<? super ApplicationFileStore, ? extends ApplicationNodeContainer> toApplicationNodeContainer() {
        return t -> {
            try {
                return t.getContent();
            } catch (final ReadFileStoreException e) {
                return null;
            }
        };
    }

    public Optional<ApplicationFileStore> findFileStoreByToken(String token) {
        return getChildren().stream()
                .filter(withToken(token))
                .findFirst();
    }

    private Predicate<? super ApplicationFileStore> withToken(String token) {
        return fStore -> {
            try {
                return fStore.getContent().getApplications().stream()
                        .anyMatch(node -> Objects.equals(node.getToken(), token));
            } catch (final ReadFileStoreException e) {
                return false;
            }
        };
    }

    @Override
    public void migrate(IProgressMonitor monitor) throws CoreException, MigrationException {
        super.migrate(monitor);
        for (ApplicationFileStore fileStore : getChildren()) {
            try {
                ApplicationNodeContainer applicationNodeContainer = fileStore.getContent();
                applicationNodeContainer.getApplications().forEach(application -> {
                    updateBonitaTheme(application);
                    updateBonitaLayout(application);
                });
                fileStore.save(applicationNodeContainer);
            } catch (ReadFileStoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    private void updateBonitaLayout(ApplicationNode application) {
        if(Objects.equals(application.getLayout(),"custompage_defaultlayout")) {
            application.setLayout("custompage_bonitaLayout");
        }
    }

    private void updateBonitaTheme(ApplicationNode application) {
        if(Objects.equals(application.getLayout(),"custompage_bonitadefaulttheme")) {
            application.setTheme("custompage_bonitaTheme");
        }
    }
}
