/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.importer.bos.handler;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;

public class SwitchRepositoryStrategy {

    private String targetRepository;
    private boolean rebuildModel;
    private final RepositoryAccessor repositoryAccessor;

    public SwitchRepositoryStrategy(RepositoryAccessor repositoryAccessor, String targetRepository) {
        this.targetRepository = targetRepository;
        this.repositoryAccessor = repositoryAccessor;
    }

    public boolean isSwitchRepository() {
        return !Objects.equals(targetRepository, repositoryAccessor.getCurrentRepository().getName());
    }

    public String getTargetRepository() {
        return targetRepository;
    }

    public void setTargetRepository(String targetRepository) {
        this.targetRepository = targetRepository;
    }

    public boolean isRebuildModel() {
        return rebuildModel;
    }

    public void setRebuildModel(boolean rebuildModel) {
        this.rebuildModel = rebuildModel;
    }

}
