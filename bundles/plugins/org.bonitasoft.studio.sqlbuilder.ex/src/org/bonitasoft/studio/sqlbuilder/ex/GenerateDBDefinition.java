/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.sqlbuilder.ex;

/*
 * Created on Oct 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.modelbase.dbdefinition.CheckOption;
import org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionFactory;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType;
import org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * @author hkolwalk
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GenerateDBDefinition {

	public static void main(String[] arg) {
			URI uri = URI.createFileURI("C:\\H2_1.2.xmi");
			Resource rf = new XMIResourceImpl(uri);
			
			// Database vendor definitions
			DatabaseVendorDefinition databaseVendorDefinition = DatabaseDefinitionFactory.eINSTANCE.createDatabaseVendorDefinition();
			databaseVendorDefinition.setVendor("H2");
			databaseVendorDefinition.setVersion("1.2");
			databaseVendorDefinition.setViewTriggerSupported(true);
			databaseVendorDefinition.setMaximumIdentifierLength(64);
			databaseVendorDefinition.setMaximumCommentLength(64);
			databaseVendorDefinition.setSequenceSupported(true);
			databaseVendorDefinition.setMQTSupported(true);
			databaseVendorDefinition.setAliasSupported(true);
			
			ColumnDefinition columnDefinition = DatabaseDefinitionFactory.eINSTANCE.createColumnDefinition();
			columnDefinition.setIdentitySupported(true);
			columnDefinition.setComputedSupported(true);
			columnDefinition.setIdentityStartValueSupported(true);
			columnDefinition.setIdentityIncrementSupported(true);
			columnDefinition.setIdentityMaximumSupported(true);
			columnDefinition.setIdentityMinimumSupported(true);
			columnDefinition.setIdentityCycleSupported(true);
			databaseVendorDefinition.setColumnDefinition(columnDefinition);
			
			ConstraintDefinition constraintDefinition = DatabaseDefinitionFactory.eINSTANCE.createConstraintDefinition();
			constraintDefinition.setClusteredPrimaryKeySupported(true);
			constraintDefinition.setClusteredUniqueConstraintSupported(true);
			constraintDefinition.getParentDeleteDRIRuleType().add(ParentDeleteDRIRuleType.RESTRICT_LITERAL);
			constraintDefinition.getParentDeleteDRIRuleType().add(ParentDeleteDRIRuleType.CASCADE_LITERAL);
			constraintDefinition.getParentDeleteDRIRuleType().add(ParentDeleteDRIRuleType.SET_NULL_LITERAL);
			constraintDefinition.getParentDeleteDRIRuleType().add(ParentDeleteDRIRuleType.NO_ACTION_LITERAL);
			constraintDefinition.getParentUpdateDRIRuleType().add(ParentUpdateDRIRuleType.RESTRICT_LITERAL);
			constraintDefinition.getParentUpdateDRIRuleType().add(ParentUpdateDRIRuleType.NO_ACTION_LITERAL);
			constraintDefinition.getCheckOption().add(CheckOption.NONE_LITERAL);
			constraintDefinition.getCheckOption().add(CheckOption.LOCAL_LITERAL);
			constraintDefinition.getCheckOption().add(CheckOption.CASCADE_LITERAL);
			databaseVendorDefinition.setConstraintDefinition(constraintDefinition);
			
			
			// Primitive type definitions
			
			// CHARACTER
			PredefinedDataTypeDefinition characterDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			characterDataTypeDefinition.setPrimitiveType(PrimitiveType.CHARACTER_LITERAL);
			characterDataTypeDefinition.getName().add("CHAR");
			characterDataTypeDefinition.getName().add("CHARACTER");
			characterDataTypeDefinition.setMaximumLength(254);
			characterDataTypeDefinition.setKeyConstraintSupported(true);
			characterDataTypeDefinition.getDefaultValueTypes().add("CURRENT_USER");
			characterDataTypeDefinition.getDefaultValueTypes().add("NULL");
			characterDataTypeDefinition.setLengthSupported(true);
			characterDataTypeDefinition.setJdbcEnumType(1);
			characterDataTypeDefinition.setJavaClassName("java.lang.String");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(characterDataTypeDefinition);
			((XMIResource)rf).setID(characterDataTypeDefinition, PrimitiveType.CHARACTER_LITERAL+"_1");
							
			// DECIMAL
			PredefinedDataTypeDefinition decimalDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			decimalDataTypeDefinition.setPrimitiveType(PrimitiveType.DECIMAL_LITERAL);
			decimalDataTypeDefinition.getName().add("DECIMAL");
			decimalDataTypeDefinition.getName().add("DEC");
			decimalDataTypeDefinition.setPrecisionSupported(true);
			decimalDataTypeDefinition.setScaleSupported(true);
			decimalDataTypeDefinition.setKeyConstraintSupported(true);
			decimalDataTypeDefinition.setIdentitySupported(true);
			decimalDataTypeDefinition.getDefaultValueTypes().add("NULL");
			decimalDataTypeDefinition.setJdbcEnumType(3);
			decimalDataTypeDefinition.setJavaClassName("java.math.BigDecimal");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(decimalDataTypeDefinition);
			((XMIResource)rf).setID(decimalDataTypeDefinition, PrimitiveType.DECIMAL_LITERAL+"_1");
				
			// DOUBLE
			PredefinedDataTypeDefinition doublePrecisionDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			doublePrecisionDataTypeDefinition.setPrimitiveType(PrimitiveType.DOUBLE_PRECISION_LITERAL);
			doublePrecisionDataTypeDefinition.getName().add("DOUBLE");
			doublePrecisionDataTypeDefinition.setKeyConstraintSupported(true);
			doublePrecisionDataTypeDefinition.getDefaultValueTypes().add("NULL");
			doublePrecisionDataTypeDefinition.setJdbcEnumType(8);
			doublePrecisionDataTypeDefinition.setJavaClassName("double");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(doublePrecisionDataTypeDefinition);
			((XMIResource)rf).setID(doublePrecisionDataTypeDefinition, PrimitiveType.DOUBLE_PRECISION_LITERAL+"_1");
					
			// INTEGER
			PredefinedDataTypeDefinition integerDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			integerDataTypeDefinition.setPrimitiveType(PrimitiveType.INTEGER_LITERAL);
			integerDataTypeDefinition.getName().add("INTEGER");
			integerDataTypeDefinition.getName().add("INT");
			integerDataTypeDefinition.setKeyConstraintSupported(true);
			integerDataTypeDefinition.setIdentitySupported(true);
			integerDataTypeDefinition.getDefaultValueTypes().add("NULL");
			integerDataTypeDefinition.setJdbcEnumType(4);
			integerDataTypeDefinition.setJavaClassName("int");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(integerDataTypeDefinition);
			((XMIResource)rf).setID(integerDataTypeDefinition, PrimitiveType.INTEGER_LITERAL+"_1");
				
			// NUMERIC
			PredefinedDataTypeDefinition numericDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			numericDataTypeDefinition.setPrimitiveType(PrimitiveType.NUMERIC_LITERAL);
			numericDataTypeDefinition.getName().add("NUMERIC");
			numericDataTypeDefinition.getName().add("NUM");
			numericDataTypeDefinition.setKeyConstraintSupported(true);
			numericDataTypeDefinition.setPrecisionSupported(true);
			numericDataTypeDefinition.setScaleSupported(true);
			numericDataTypeDefinition.getDefaultValueTypes().add("NULL");
			numericDataTypeDefinition.setJdbcEnumType(2);
			numericDataTypeDefinition.setJavaClassName("java.math.BigDecimal");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(numericDataTypeDefinition);
			((XMIResource)rf).setID(numericDataTypeDefinition, PrimitiveType.NUMERIC_LITERAL+"_1");
			
			// NUMERIC
			PredefinedDataTypeDefinition realDataTypeDefinition = DatabaseDefinitionFactory.eINSTANCE.createPredefinedDataTypeDefinition();
			realDataTypeDefinition.setPrimitiveType(PrimitiveType.REAL_LITERAL);
			realDataTypeDefinition.getName().add("REAL");
			realDataTypeDefinition.setKeyConstraintSupported(true);
			realDataTypeDefinition.setPrecisionSupported(true);
			realDataTypeDefinition.setScaleSupported(true);
			realDataTypeDefinition.getDefaultValueTypes().add("NULL");
			realDataTypeDefinition.setJdbcEnumType(2);
			realDataTypeDefinition.setJavaClassName("java.math.BigDecimal");
			databaseVendorDefinition.getPredefinedDataTypeDefinitions().add(realDataTypeDefinition);
			((XMIResource)rf).setID(realDataTypeDefinition, PrimitiveType.REAL_LITERAL+"_1");
					
			if (rf != null) {
				EList resourceContents = rf.getContents();
				resourceContents.add(databaseVendorDefinition);
				try {
					Map options = new HashMap();
					options.put(XMIResource.OPTION_DECLARE_XML, Boolean.TRUE);
					rf.save(options);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
}


