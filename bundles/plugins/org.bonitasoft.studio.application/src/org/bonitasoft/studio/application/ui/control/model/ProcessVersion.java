package org.bonitasoft.studio.application.ui.control.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

public class ProcessVersion extends BuildableArtifact implements Comparable<ProcessVersion> {

    private DefaultArtifactVersion version;
    private ProcessArtifact process;
    private Pool model;
    private ECommandService eCommandService;
    private EHandlerService eHandlerService;

    public ProcessVersion(ProcessArtifact process, Pool model) {
        super(process, process.getFileStore());
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
    public void build(IPath buildPath, IProgressMonitor monitor) throws CoreException {
        IPath processFolderPath = buildPath.append("process");
        IFolder processFolder = getRepository().getProject()
                .getFolder(processFolderPath.makeRelativeTo(getRepository().getProject().getLocation()));
        if (!processFolder.exists()) {
            processFolder.create(true, true, new NullProgressMonitor());
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fileName", getParent().getFileStore().getName());
        parameters.put("destinationPath", processFolder.getLocation().toOSString());
        parameters.put("process", ModelHelper.getEObjectID(getModel()));
        monitor.subTask(String.format(Messages.buildingProcess, getName()));
        IStatus buildStatus = (IStatus) executeCommand(DiagramFileStore.BUILD_DIAGRAM_COMMAND, parameters);
        if (Objects.equals(buildStatus.getSeverity(), ValidationStatus.ERROR)) {
            throw new CoreException(parseStatus(buildStatus));
        }
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

    protected Object executeCommand(String command, Map<String, Object> parameters) {
        initServices();
        ParameterizedCommand parameterizedCommand = eCommandService.createCommand(command, parameters);
        if (eHandlerService.canExecute(parameterizedCommand)) {
            return eHandlerService.executeHandler(parameterizedCommand);
        }
        throw new RuntimeException(String.format("Can't execute command %s", parameterizedCommand.getId()));
    }

    protected void initServices() {
        if (eCommandService == null || eHandlerService == null) {
            eCommandService = PlatformUI.getWorkbench().getService(ECommandService.class);
            eHandlerService = PlatformUI.getWorkbench().getService(EHandlerService.class);
        }
    }

    private Repository getRepository() {
        return RepositoryManager.getInstance().getCurrentRepository();
    }

    @Override
    public String getName() {
        return String.format("%s (%s)", process.getName(), version.toString());
    }

    public Pool getModel() {
        return model;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProcessVersion) {
            return Objects.equals(toString(), obj.toString());
        }
        return super.equals(obj);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }
    
    @Override
    public String getDisplayName() {
        return version.toString();
    }

    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    public StyledString getStyledString() {
        return new StyledString(version.toString());
    }

    @Override
    public int compareTo(ProcessVersion pv) {
        return new DefaultArtifactVersion(pv.toString()).compareTo(version);
    }
    
}
