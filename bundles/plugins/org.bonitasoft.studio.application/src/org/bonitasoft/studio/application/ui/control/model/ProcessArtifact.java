package org.bonitasoft.studio.application.ui.control.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.edit.ProcessEditPlugin;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

public class ProcessArtifact extends Artifact {

    private List<ProcessVersion> processVerions = new ArrayList<>();
    private DiagramFileStore fStore;
    private String name;

    public ProcessArtifact(RepositoryStore parent,String name, DiagramFileStore fStore) {
        super(parent);
        this.name = name;
        this.fStore = fStore;
    }

    public String getName() {
        return name;
    }

    public void addVersion(Pool process) {
        processVerions.add(new ProcessVersion(this, process));
        Collections.sort(processVerions);
    }

    public List<ProcessVersion> getVersions() {
        return processVerions;
    }

    @Override
    public String toString() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProcessArtifact) {
            return Objects.equals(((ProcessArtifact) obj).getName(), getName());
        }
        return super.equals(obj);
    }
    
    @Override
    public RepositoryStore getParent() {
        return (RepositoryStore) super.getParent();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getName(),getParent().getResource().getLocation().toString());
    }

    public String getDisplayName() {
        return toString();
    }

    @Override
    public Image getIcon() {
        return ExtendedImageRegistry.INSTANCE.getImage(ProcessEditPlugin.INSTANCE.getPluginResourceLocator().getImage("full/obj16/Pool"));
    }

    @Override
    public StyledString getStyledString() {
        return new StyledString(getDisplayName());
    }

    public ProcessVersion getLatestVersion() {
        if(processVerions.isEmpty()) {
            throw new IllegalStateException(String.format("No version found for process %s", getName()));
        }
        return processVerions.get(0);
    }

    public DiagramFileStore getFileStore() {
        return fStore;
    }



}
