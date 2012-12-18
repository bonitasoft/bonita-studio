/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bar.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.DateType;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.FloatType;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.StringType;
import org.bonitasoft.studio.model.process.XMLType;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;


/**
 * @author Romain Bioteau
 *
 */
public class DataTypeSwitch extends ProcessSwitch<DataType> {

    private final List<DataType> datatypes;
    private boolean foundDouble = false;
    private boolean foundInteger = false;
    private boolean foundLong = false;
    private boolean foundBoulean = false;
    private boolean foundDate = false;
    private boolean foundText = false;
    private boolean foundEnum = false;
    private boolean foundXML = false;
    private boolean foundJava = false;
    private boolean foundFloat = false;


    public DataTypeSwitch(List<DataType> datatypes){
        this.datatypes = datatypes;
    }

    @Override
    public DataType caseBooleanType(BooleanType object) {
        foundBoulean = true;
        return super.caseBooleanType(object);
    }

    @Override
    public DataType caseJavaType(JavaType object) {
        foundJava = true;
        return super.caseJavaType(object);
    }

    @Override
    public DataType caseXMLType(XMLType object) {
        foundXML = true;
        return super.caseXMLType(object);
    }

    @Override
    public DataType caseDateType(DateType object) {
        foundDate = true;
        return super.caseDateType(object);
    }

    @Override
    public DataType caseDoubleType(DoubleType object) {
        foundDouble = true;
        return super.caseDoubleType(object);
    }

    @Override
    public DataType caseLongType(LongType object) {
        foundLong = true;
        return super.caseLongType(object);
    }

    @Override
    public DataType caseIntegerType(IntegerType object) {
        foundInteger = true;
        return super.caseIntegerType(object);
    }

    @Override
    public DataType caseStringType(StringType object) {
        foundText = true;
        return super.caseStringType(object);
    }


    @Override
    public DataType caseFloatType(FloatType object) {
        foundFloat = true;
        return super.caseFloatType(object);
    }

    @Override
    public DataType caseEnumType(EnumType object) {
        foundEnum = true;
        return super.caseEnumType(object);
    }


    public void testDatatypesConsistency(){
        for(DataType type : datatypes){
            doSwitch(type);
        }
        assertTrue(foundBoulean);
        assertTrue(foundDate);
        assertTrue(foundDouble);
        assertTrue(foundEnum);
        assertTrue(foundInteger);
        assertTrue(foundJava);
        assertTrue(foundLong);
        assertTrue(foundText);
        assertTrue(foundXML);
        assertFalse(foundFloat);
    }

}
