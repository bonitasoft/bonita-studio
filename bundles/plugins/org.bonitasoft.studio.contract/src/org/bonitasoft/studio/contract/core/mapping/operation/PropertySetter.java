/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.core.mapping.operation;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;

public class PropertySetter {

    private final Field field;

    public PropertySetter(final Field field) {
        this.field = field;
    }

    public String getSetterName() {
        return "set" + createGenFeature().getAccessorName();
    }

    private GenFeature createGenFeature() {
        final EAttribute eAttribute = newEAttribute(field);
        final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.getEStructuralFeatures().add(eAttribute);
        final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        ePackage.getEClassifiers().add(eClass);
        final GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
        final GenPackage genPackage = genModel.createGenPackage();
        genPackage.setEcorePackage(ePackage);
        final GenClass genClass = genModel.createGenClass();
        genClass.setEcoreClass(eClass);
        final GenFeature genFeature = genModel.createGenFeature();
        genFeature.setEcoreFeature(eAttribute);
        genFeature.setGenClass(genClass);
        genPackage.getGenClasses().add(genClass);
        genModel.getGenPackages().add(genPackage);
        return genFeature;
    }

    private EAttribute newEAttribute(final Field field) {
        final EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName(field.getName());
        return eAttribute;
    }
}
