/**
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.ui.handler;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.attribute.AttributeEditionControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.BusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model.BusinessDataModelFormPart;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.e4.core.di.annotations.Execute;

public class DefineBusinessDataModelHandler {

    @Execute
    public void defineBusinessDataModel(RepositoryAccessor repositoryAccessor) {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> bomRepositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore bdmFileStore = bomRepositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME,
                false);
        if (bdmFileStore == null) {
            bdmFileStore = createBdmFileStore(bomRepositoryStore);
        }
        bdmFileStore.open();
    }

    private BusinessObjectModelFileStore createBdmFileStore(
            BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repository) {
        BusinessObjectModelFileStore fileStore = (BusinessObjectModelFileStore) repository
                .createRepositoryFileStore(BusinessObjectModelFileStore.BOM_FILENAME);
        BusinessObjectModel bdm = new BusinessObjectModel();
        bdm.getBusinessObjects().add(createFirstBusinessObject());
        fileStore.save(bdm);
        return fileStore;
    }

    private BusinessObject createFirstBusinessObject() {
        String defaultName = String.format("%s.%s", BusinessDataModelFormPart.DEFAULT_PACKAGE_NAME,
                BusinessObjectList.DEFAULT_BO_NAME);
        var bo = new BusinessObject(defaultName);
        SimpleField stringField = new SimpleField();
        stringField.setType(FieldType.STRING);
        stringField.setName(AttributeEditionControl.DEFAULT_FIELD_NAME);
        stringField.setLength(255);
        bo.addField(stringField);
        return bo;
    }

}
