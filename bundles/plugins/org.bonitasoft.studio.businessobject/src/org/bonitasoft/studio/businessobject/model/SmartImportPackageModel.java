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
package org.bonitasoft.studio.businessobject.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;

public class SmartImportPackageModel extends SmartImportableUnit {

    private String packageName;

    public SmartImportPackageModel(SmartImportBdmModel model, String packageName) {
        super(model);
        this.packageName = packageName;
    }

    @Override
    public String getName() {
        return packageName;
    }

    public List<BusinessObject> getBusinessObjectsToImport(boolean includeAlreadyPresent) {
        return getSmartImportableUnits().stream()
                .map(SmartImportBusinessObjectModel.class::cast)
                .filter(boModel -> includeAlreadyPresent
                        ? true
                        : !Objects.equals(boModel.getConflictStatus(), ConflictStatus.SAME_CONTENT))
                .map(SmartImportBusinessObjectModel::getBusinessObject)
                .collect(Collectors.toList());
    }

}
