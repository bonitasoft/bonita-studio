/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.editor.model.builder;

import java.util.Arrays;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.Package;

public class BusinessObjectModelBuilder {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;
    private BusinessObjectModel businessObjectModel;

    public BusinessObjectModelBuilder() {
        businessObjectModel = factory.createBusinessObjectModel();
    }

    public BusinessObjectModelBuilder withPackages(Package... packages) {
        businessObjectModel.getPackages().addAll(Arrays.asList(packages));
        return this;
    }

    public BusinessObjectModelBuilder withGroupId(String groupId) {
        businessObjectModel.setGroupId(groupId);
        return this;
    }

    public BusinessObjectModel create() {
        return businessObjectModel;
    }

}
