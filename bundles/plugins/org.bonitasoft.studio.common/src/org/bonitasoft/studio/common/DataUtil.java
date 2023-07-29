/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common;

import java.util.Date;

import org.bonitasoft.bpm.model.process.BooleanType;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.DataType;
import org.bonitasoft.bpm.model.process.DateType;
import org.bonitasoft.bpm.model.process.DoubleType;
import org.bonitasoft.bpm.model.process.EnumType;
import org.bonitasoft.bpm.model.process.FloatType;
import org.bonitasoft.bpm.model.process.IntegerType;
import org.bonitasoft.bpm.model.process.JavaObjectData;
import org.bonitasoft.bpm.model.process.LongType;
import org.bonitasoft.bpm.model.process.StringType;
import org.bonitasoft.bpm.model.process.XMLType;

public class DataUtil {

	public static String getTechnicalTypeFor(Data data) {
		return org.bonitasoft.bpm.model.process.util.DataUtil.getTechnicalTypeFor(data);
	}
    public static String getDisplayTypeName(Data data) {
        DataType type = data.getDataType();
        String typeName = null;
        if (data instanceof JavaObjectData) {
            typeName = NamingUtils.getSimpleName(((JavaObjectData) data).getClassName());
        } else if (type instanceof DateType) {
            typeName = Date.class.getSimpleName();
        } else if (type instanceof StringType) {
            typeName = String.class.getSimpleName();
        } else if (type instanceof IntegerType) {
            typeName = Integer.class.getSimpleName();
        } else if (type instanceof FloatType) {
            typeName = Float.class.getSimpleName();
        } else if (type instanceof LongType) {
            typeName = Long.class.getSimpleName();
        } else if (type instanceof DoubleType) {
            typeName = Double.class.getSimpleName();
        } else if (type instanceof BooleanType) {
            typeName = Boolean.class.getSimpleName();
        } else if (type instanceof EnumType) {
            typeName = String.class.getSimpleName();
        } else if (type instanceof XMLType) {
            typeName = String.class.getSimpleName();
        }
        if (data.isMultiple()) {
            return String.format("List<%s>", typeName);
        }
        return typeName;
    }
}
