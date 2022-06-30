/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.editor.model.builder;

import java.util.Arrays;

import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Package;

public class PackageBuilder {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;
    private Package pakage;

    public PackageBuilder() {
        this.pakage = factory.createPackage();
    }

    public PackageBuilder withName(String name) {
        pakage.setName(name);
        return this;
    }

    public PackageBuilder withBusinessObjects(BusinessObject... businessObjects) {
        pakage.getBusinessObjects().addAll(Arrays.asList(businessObjects));
        return this;
    }

    public Package create() {
        return pakage;
    }

}
