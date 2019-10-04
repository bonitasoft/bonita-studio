/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.common.repository.model.smartImport;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.model.ImportAction;
import org.bonitasoft.studio.common.repository.model.IPresentable;
import org.eclipse.swt.graphics.Image;

public abstract class SmartImportableUnit implements IPresentable {

    private ImportAction importAction = ImportAction.OVERWRITE;
    private ConflictStatus conflictStatus = ConflictStatus.NONE;
    private List<SmartImportableUnit> smartImportableUnits = new ArrayList<>();
    private SmartImportableUnit parent;
    private SmartImportableModel parentModel;

    public SmartImportableUnit(SmartImportableModel parentModel) {
    }

    public SmartImportableUnit(SmartImportableUnit parent, SmartImportableModel parentModel) {
        this.parent = parent;
    }

    public void setConflictStatus(ConflictStatus conflictStatus) {
        this.conflictStatus = conflictStatus;
    }

    public ConflictStatus getConflictStatus() {
        return getSmartImportableUnits().isEmpty()
                ? conflictStatus
                : reduceChildrenStatus();
    }

    private ConflictStatus reduceChildrenStatus() {
        return getSmartImportableUnits().stream().map(SmartImportableUnit::getConflictStatus).reduce(null,
                (status1, status2) -> status1 == null
                        ? status2
                        : status1.getPriority() > status2.getPriority()
                                ? status1
                                : status2);
    }

    public void setImportAction(ImportAction importAction) {
        this.importAction = importAction;
    }

    public ImportAction getImportAction() {
        return importAction;
    }

    public List<SmartImportableUnit> getSmartImportableUnits() {
        return smartImportableUnits;
    }

    public boolean isLeaf() {
        return smartImportableUnits.isEmpty();
    }

    public SmartImportableUnit getParent() {
        return parent;
    }

    public SmartImportableModel getParentModel() {
        return parentModel;
    }

    public abstract String getName();

    @Override
    public String getText() {
        return getName();
    }

    @Override
    public Image getImage() {
        return null;
    }
}
