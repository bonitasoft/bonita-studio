/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export.switcher;

import org.bonitasoft.engine.bpm.model.DataDefinitionBuilder;
import org.bonitasoft.engine.bpm.model.FlowElementBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DateType;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.FloatType;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.JavaType;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.StringType;
import org.bonitasoft.studio.model.process.XMLType;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;


/**
 * @author Romain Bioteau
 *
 */
public class DataSwitch extends ProcessSwitch<DataDefinitionBuilder> {

    private final Data data;
    private final FlowElementBuilder builder;
    private final Expression expr;

    public DataSwitch(Data data, Expression defaultValue,FlowElementBuilder flowElementBuilder){
        builder = flowElementBuilder ;
        this.data = data ;
        expr = defaultValue ;
    }

    @Override
    public DataDefinitionBuilder caseStringType(final StringType type) {
    	return builder.addLongTextData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseLongType(LongType object) {
        return builder.addLongData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseDoubleType(DoubleType object) {
        return builder.addDoubleData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseIntegerType(final IntegerType type) {
        return  builder.addIntegerData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseBooleanType(final BooleanType type) {
        return builder.addBooleanData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseFloatType(final FloatType type) {
        return  builder.addDoubleData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseEnumType(final EnumType type) {
        return builder.addShortTextData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseJavaType(final JavaType type) {
        return  builder.addData(data.getName(), ((JavaObjectData)data).getClassName(), expr) ;
    }

    @Override
    public DataDefinitionBuilder caseXMLType(final XMLType type) {
        return builder.addXMLData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseDateType(final DateType type) {
        return  builder.addDateData(data.getName(), expr);
    }

}
