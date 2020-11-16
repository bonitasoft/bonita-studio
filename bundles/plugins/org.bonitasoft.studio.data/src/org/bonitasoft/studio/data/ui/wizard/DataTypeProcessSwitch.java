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
package org.bonitasoft.studio.data.ui.wizard;

import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.model.process.BooleanType;
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

final class DataTypeProcessSwitch extends ProcessSwitch<String> {

    @Override
    public String caseDateType(final DateType object) {
        return Messages.dateTypeHint;
    }

    @Override
    public String caseStringType(final StringType object) {
        return Messages.stringTypeHint;
    }

    @Override
    public String caseIntegerType(final IntegerType object) {
        return Messages.integerTypeHint;
    }

    @Override
    public String caseBooleanType(final BooleanType object) {
        return Messages.booleanTypeHint;
    }

    @Override
    public String caseLongType(final LongType object) {
        return Messages.longTypeHint;
    }

    @Override
    public String caseDoubleType(final DoubleType object) {
        return Messages.doubleTypeHint;
    }

    @Override
    public String caseFloatType(final FloatType object) {
        return Messages.floatTypeHint;
    }

    @Override
    public String caseJavaType(final JavaType object) {
        return Messages.javaTypeHint;
    }

    @Override
    public String caseXMLType(final XMLType object) {
        return Messages.xmlTypeHint;
    }

    @Override
    public String caseEnumType(final EnumType object) {
        return Messages.enumTypeHint;
    }
}