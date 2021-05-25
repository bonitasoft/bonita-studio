/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.importer.bos.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class SwitchRepositoryStrategy {

    private String targetRepository;
    private List<TargetProjectChangeListener> targetProjectChangeListerners = new ArrayList<>();
    private boolean createNewProject;

    public boolean isCreateNewProject() {
        return createNewProject;
    }
    
    public void setCreateNewProject(boolean createNewProject) {
        this.createNewProject = createNewProject;
    }

    public String getTargetRepository() {
        return targetRepository;
    }

    public void setTargetRepository(String targetRepository) {
        if(!Objects.equals(this.targetRepository, targetRepository)) {
            this.targetRepository = targetRepository;
            targetProjectChangeListerners.stream().forEach(l -> l.handleTargetProjectChanged(targetRepository));
        }
    }
    
    public void addTargetProjectChangeListener(TargetProjectChangeListener targetProjectChangeListerner) {
        targetProjectChangeListerners.add(targetProjectChangeListerner);
    }
    
    interface TargetProjectChangeListener {
        void handleTargetProjectChanged(String projectName);
    }

}
