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

import java.util.Objects;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.common.model.ConflictStatus;
import org.bonitasoft.studio.common.repository.model.smartImport.SmartImportableUnit;

public class SmartImportBusinessObjectModel extends SmartImportableUnit {

    private BusinessObject businessObject;

    public SmartImportBusinessObjectModel(SmartImportBdmModel model, SmartImportPackageModel parent,
            BusinessObject businessObject) {
        super(parent, model);
        this.businessObject = businessObject;
    }

    @Override
    public String getName() {
        return businessObject.getSimpleName();
    }

    public BusinessObject getBusinessObject() {
        return businessObject;
    }

    public boolean isConflictingThroughPackages() {
        if (Objects.equals(getConflictStatus(), ConflictStatus.CONFLICTING)) {
            String packageName = getParent().getName();
            PackageHelper packageHelper = PackageHelper.getInstance();
            return ((SmartImportBdmModel) getParentModel()).retrieveCurrentModel().getBusinessObjects().stream()
                    .filter(bo -> Objects.equals(bo.getSimpleName(), getName()))
                    .anyMatch(bo -> !Objects.equals(packageHelper.getPackageName(bo), packageName));
        }
        return false;
    }

}
