/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.migration.custom.migration;

import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edapt.spi.migration.AttributeSlot;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.MetamodelResource;
import org.eclipse.emf.edapt.spi.migration.MigrationFactory;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.Type;

/**
 * @author Romain Bioteau
 *
 */
public class InstanceBuilder {


    private final Instance instance;
    private static MigrationFactory migrationFactory = MigrationFactory.eINSTANCE;
    private static Model model;

    private InstanceBuilder(final Instance instance) {
        instance.setUuid(EcoreUtil.generateUUID());
        this.instance = instance;
    }

    public static InstanceBuilder anInstance(final EClass eClass) {
        return new InstanceBuilder(aModel().newInstance(eClass));
    }

    public static InstanceBuilder anInstance(final Type type) {
        return new InstanceBuilder(type.newInstance());
    }


    public InstanceBuilder withFeature(final EStructuralFeature feature, final Object value) {
        instance.set(feature, value);
        return this;
    }
    
    public InstanceBuilder withAttributeSlot(EAttribute attribute, Object value) {
        AttributeSlot attributeSlot = MigrationFactory.eINSTANCE.createAttributeSlot();
        attributeSlot.setEAttribute(attribute);
        attributeSlot.getValues().add(value);
        instance.getSlots().add(attributeSlot);
        return this;
    }

    public Instance build() {
        return instance;
    }

    public static Instance aStringDataInstance(final String name) {
        return anInstance(ProcessPackage.Literals.DATA)
                .withFeature(ProcessPackage.Literals.ELEMENT__NAME, name)
                .withFeature(ProcessPackage.Literals.DATA__DATA_TYPE, aStringDataTypeInstance())
                .withFeature(ProcessPackage.Literals.DATA__DEFAULT_VALUE, aExpressionInstance())
                .build();
    }

    public static Instance aExpressionInstance() {
        return anInstance(ExpressionPackage.Literals.EXPRESSION).build();
    }

    public static Instance aStringDataTypeInstance() {
        return anInstance(ProcessPackage.Literals.STRING_TYPE).build();
    }

    public static Model aModel() {
        if (model == null) {
            model = migrationFactory.createModel();
            final Metamodel metamodel = migrationFactory.createMetamodel();
            final MetamodelResource metamodelResource = migrationFactory.createMetamodelResource();
            metamodelResource.getRootPackages().add(ProcessPackage.eINSTANCE);
            metamodelResource.getRootPackages().add(ExpressionPackage.eINSTANCE);
            metamodel.getResources().add(metamodelResource);
            model.setMetamodel(metamodel);
        }
        return model;
    }

    public static void clearModel() {
        if (model != null) {
            model = null;
        }
    }



}
