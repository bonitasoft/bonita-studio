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
package org.bonitasoft.studio.common.repository.model.smartImport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.repository.model.IPresentable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.graphics.Image;

public abstract class SmartImportableModel implements IAdaptable, IPresentable {

    protected ISmartImportable current;
    private IValidator<File> validator;
    private List<SmartImportableUnit> smartImportableUnits = new ArrayList<>();
    private ConflictStatus conflictStatus = ConflictStatus.NONE;

    public SmartImportableModel(ISmartImportable current, IValidator<File> validator) {
        this.current = current;
        this.validator = validator;
    }

    public IStatus validateSmartImportable(File fileToImport) {
        return fileToImport != null
                ? validator.validate(fileToImport)
                : ValidationStatus.error("Nothing to validate");
    }

    public List<SmartImportableUnit> getSmartImportableUnits() {
        return smartImportableUnits;
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

    public void setConflictStatus(ConflictStatus conflictStatus) {
        this.conflictStatus = conflictStatus;
    }

    public abstract void buildSmartImportModel(File fileToImport);

    public abstract IStatus performSmartImport(IProgressMonitor monitor);

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (Objects.equals(ISmartImportable.class, adapter)) {
            return (T) current;
        }
        if (adapter.isAssignableFrom(IRepositoryFileStore.class)) {
            return current.getAdapter(adapter);
        }
        return null;
    }

    @Override
    public String getText() {
        return current.getAdapter(IRepositoryFileStore.class).getDisplayName();
    }

    @Override
    public Image getImage() {
        return null;
    }

}
