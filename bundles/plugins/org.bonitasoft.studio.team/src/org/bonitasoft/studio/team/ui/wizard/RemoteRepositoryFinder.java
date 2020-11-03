/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.team.ui.wizard;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.internal.resources.ProjectDescriptionReader;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.operation.remote.GetFileContentOperation;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryFile;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.xml.sax.InputSource;

import com.google.common.base.Predicate;

public class RemoteRepositoryFinder {

    private static final String PROJECT_FILE = ".project";
    private IRepositoryLocation location;
    private final Map<IRepositoryContainer, String> bonitaRepositories = new HashMap<IRepositoryContainer, String>();
    private final List<IRepositoryResource> emptyContainers = new ArrayList<IRepositoryResource>();
    private final List<IRepositoryResource> unkownContainers = new ArrayList<IRepositoryResource>();
    private final Set<String> remoteFolderNames = new HashSet<String>();
    private final ConnectionInfo connectionInfo;

    public RemoteRepositoryFinder(final ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    public IStatus execute(final IProgressMonitor monitor) throws SVNConnectorException {
        reset();
        location = newRepositoryLocation(connectionInfo);
        final IRepositoryResource[] resources = location.getRoot().getChildren();
        monitor.beginTask(Messages.team_listRepositories, resources.length);
        for (final IRepositoryResource resource : resources) {
            // do a second try to try to connect to each child
            if (resource instanceof IRepositoryContainer) {
                final IRepositoryResource[] children = ((IRepositoryContainer) resource).getChildren();
                final String name = resource.getName();
                remoteFolderNames.add(name.toLowerCase());
                if (children.length == 0) {
                    emptyContainers.add(resource);
                    bonitaRepositories.put((IRepositoryContainer) resource, "empty");
                } else {
                    final IRepositoryResource trunkContainer = find(newArrayList(children), trunkFolder(), null);
                    if (trunkContainer != null) {
                        final IRepositoryResource projectFileResource = find(
                                newArrayList(((IRepositoryContainer) trunkContainer).getChildren()),
                                projectFile(), null);
                        if (projectFileResource != null) {
                            final String version = readProjectVersion(resource, projectFileResource);
                            if (!isNullOrEmpty(version)) {
                                bonitaRepositories.put((IRepositoryContainer) resource, version);
                            }
                        } else {
                            unkownContainers.add(resource);
                        }
                    } else {
                        unkownContainers.add(resource);
                    }
                }
            }
            monitor.worked(1);
        }
        return Status.OK_STATUS;
    }

    private IRepositoryLocation newRepositoryLocation(final ConnectionInfo info) {
        final SVNRemoteStorage storage = SVNRemoteStorage.instance();
        IRepositoryLocation location = findExistingLocationFor(info, storage);
        if (location == null) {
            location = storage.newRepositoryLocation();
            location.setUrl(info.getUrl());
            location.setLabel(info.getUrl());
            location.setPassword(info.getPassword());
            location.setUsername(info.getUsername());
            location.setPasswordSaved(info.isSavePassword());
            location.setTrunkLocation("trunk");//$NON-NLS-1$
            location.setBranchesLocation("branches");//$NON-NLS-1$
            location.setTagsLocation("tags");//$NON-NLS-1$
            location.setStructureEnabled(true);
        }
        return location;
    }

    private IRepositoryLocation findExistingLocationFor(ConnectionInfo info, SVNRemoteStorage storage) {
        return Stream.of(storage.getRepositoryLocations())
                .filter(location -> Objects.equals(location.getUrl(), info.getUrl())).findFirst().orElse(null);
    }

    private Predicate<IRepositoryResource> projectFile() {
        return new Predicate<IRepositoryResource>() {

            @Override
            public boolean apply(final IRepositoryResource input) {
                return input instanceof IRepositoryFile
                        && PROJECT_FILE.equals(input.getName());
            }
        };
    }

    private Predicate<IRepositoryResource> trunkFolder() {
        return new Predicate<IRepositoryResource>() {

            @Override
            public boolean apply(final IRepositoryResource input) {
                return input instanceof IRepositoryContainer
                        && "trunk".equals(input.getName());
            }
        };
    }

    private void reset() {
        bonitaRepositories.clear();
        remoteFolderNames.clear();
        emptyContainers.clear();
        unkownContainers.clear();
    }

    @SuppressWarnings("restriction")
    private String readProjectVersion(final IRepositoryResource resource, final IRepositoryResource trunkContent) {
        final GetFileContentOperation op = new GetFileContentOperation(trunkContent);
        op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        final ProjectDescriptionReader reader = new ProjectDescriptionReader();
        final InputSource source = new InputSource(op.getContent());
        return reader.read(source).getComment();
    }

    /**
     * @return the bonitaRepositories
     */
    public Map<IRepositoryContainer, String> getBonitaRepositories() {
        return bonitaRepositories;
    }

    /**
     * @return the emptyContainers
     */
    public List<IRepositoryResource> getEmptyContainers() {
        return emptyContainers;
    }

    /**
     * @return the remoteFolderNames
     */
    public Set<String> getRemoteFolderNames() {
        return remoteFolderNames;
    }

    /**
     * @return the unkownContainers
     */
    public List<IRepositoryResource> getUnkownContainers() {
        return unkownContainers;
    }

    /**
     * @return the location
     */
    public IRepositoryLocation getLocation() {
        return location;
    }

    public boolean isRepositoryValid() {
        return TeamRepositoryUtil.isRepositoryValid(newRepositoryLocation(connectionInfo));
    }
}
