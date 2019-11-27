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

import java.io.File;
import java.util.List;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.common.repository.model.smartImport.ISmartImportable;
import org.eclipse.core.databinding.validation.IValidator;

/**
 * This class is instantiated when there is cross packages conflicts (detected during validation)
 * `Smart import` isn't possible, global overwrite will be performed, and conflicts are searched through all packages.
 */
public class OverwriteImportBdmModel extends SmartImportBdmModel {

    public OverwriteImportBdmModel(ISmartImportable current, BusinessObjectModelConverter converter,
            IValidator<File> validator) {
        super(current, converter, validator, false);
    }

    @Override
    protected List<BusinessObject> retrievePotentialConflictingBusinessObjects(BusinessObjectModel currentModel, String packageName) {
        return currentModel.getBusinessObjects();
    }

}
