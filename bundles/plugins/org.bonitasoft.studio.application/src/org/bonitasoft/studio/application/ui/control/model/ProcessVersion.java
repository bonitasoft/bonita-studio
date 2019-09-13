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
package org.bonitasoft.studio.application.ui.control.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class ProcessVersion extends BuildableArtifact implements ArtifactVersion {

    private DefaultArtifactVersion version;
    private ProcessArtifact process;
    private Pool model;

    public ProcessVersion(ProcessArtifact process, Pool model, DiagramFileStore fStore) {
        super(process, fStore);
        this.process = process;
        this.model = model;
        this.version = new DefaultArtifactVersion(model.getVersion());
    }

    @Override
    public String toString() {
        return ModelHelper.getEObjectID(model);
    }

    @Override
    public ProcessArtifact getParent() {
        return (ProcessArtifact) super.getParent();
    }

    @Override
    public IStatus build(IPath buildPath, IProgressMonitor monitor) {
        IPath processFolderPath = buildPath.append("process");
        IFolder processFolder = getRepository().getProject()
                .getFolder(processFolderPath.makeRelativeTo(getRepository().getProject().getLocation()));
        if (!processFolder.exists()) {
            try {
                processFolder.create(true, true, new NullProgressMonitor());
            } catch (CoreException e) {
                return e.getStatus();
            }
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fileName", getFileStore().getName());
        parameters.put("destinationPath", processFolder.getLocation().toOSString());
        parameters.put("process", ModelHelper.getEObjectID(getModel()));
        monitor.subTask(String.format(Messages.buildingProcess, getName()));
        IStatus buildStatus = (IStatus) ((DiagramFileStore)getFileStore()).executeCommand(DiagramFileStore.BUILD_DIAGRAM_COMMAND, parameters);
        if (Objects.equals(buildStatus.getSeverity(), ValidationStatus.ERROR)) {
            return parseStatus(buildStatus);
        }
        return Status.OK_STATUS;
    }
    
    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        options.put("process", ModelHelper.getEObjectID(getModel()));
        return super.deploy(session, options, monitor);
    }
    
    private IStatus parseStatus(IStatus status) {
        if (status instanceof MultiStatus) {
            MultiStatus multiStatus = ((MultiStatus) status);
            IStatus[] errorChildren = Arrays.asList(multiStatus.getChildren()).stream()
                    .filter(aStatus -> Objects.equals(aStatus.getSeverity(), ValidationStatus.ERROR))
                    .collect(Collectors.toList()).toArray(new IStatus[0]);
            status = new MultiStatus(multiStatus.getPlugin(), multiStatus.getCode(), errorChildren,
                    multiStatus.getMessage(), multiStatus.getException());
        }
        return status;
    }

    private Repository getRepository() {
        return RepositoryManager.getInstance().getCurrentRepository();
    }

    @Override
    public String getName() {
        return String.format("%s (%s)", process.getName(), version.toString());
    }
    
    @Override
    public String getVersion() {
        return version.toString();
    }

    public Pool getModel() {
        return model;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProcessVersion) {
            return Objects.equals(getVersion(), ((ProcessVersion) obj).getVersion());
        }
        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }
    
    @Override
    public String getDisplayName() {
        return getVersion();
    }

    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    public StyledString getStyledString() {
        return new StyledString(getVersion());
    }

    @Override
    public int compareTo(ArtifactVersion pv) {
        return new DefaultArtifactVersion(pv.getVersion()).compareTo(version);
    }

    @Override
    public boolean isLatest() {
        return Objects.equals(getParent().getLatestVersion(), this);
    }
    
}
