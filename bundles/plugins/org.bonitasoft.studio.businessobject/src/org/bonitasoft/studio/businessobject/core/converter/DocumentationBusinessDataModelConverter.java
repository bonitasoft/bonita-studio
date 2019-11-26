/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.core.converter;

import org.bonitasoft.asciidoc.templating.model.bdm.BusinessDataModel;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;

public class DocumentationBusinessDataModelConverter {

    public BusinessDataModel toDocumentationBusinessdataModel(BusinessObjectModel businessDataModel) {
        BusinessDataModel bdm = new BusinessDataModel();
        org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject[] businessObjects = businessDataModel
                .getBusinessObjects().stream()
                .map(this::toDocumentationBusinessObject)
                .toArray(org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject[]::new);
        bdm.setBusinessObjects(businessObjects);
        return bdm;
    }

    private org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject toDocumentationBusinessObject(
            BusinessObject businessObject) {
        return new org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject.BusinessObjectBuilder()
                .name(businessObject.getSimpleName())
                .packageName(PackageHelper.getInstance().getPackageName(businessObject))
                .description(businessObject.getDescription())
                .build();
    }

}
