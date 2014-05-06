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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common;

import java.util.Date;
import java.util.List;

import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.DateType;
import org.bonitasoft.studio.model.process.DoubleType;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.FloatType;
import org.bonitasoft.studio.model.process.IntegerType;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.bonitasoft.studio.model.process.LongType;
import org.bonitasoft.studio.model.process.StringType;
import org.bonitasoft.studio.model.process.XMLType;

/**
 * @author Romain Bioteau
 * 
 */
public class DataUtil {

    public static String getTechnicalTypeFor(Data data) {
        DataType type = data.getDataType();
        if (data.isMultiple()) {
            return List.class.getName();
        }
        if (data instanceof JavaObjectData) {
            return ((JavaObjectData) data).getClassName();
        } else if (type instanceof DateType) {
            return Date.class.getName();
        } else if (type instanceof StringType) {
            return String.class.getName();
        } else if (type instanceof IntegerType) {
            return Integer.class.getName();
        } else if (type instanceof FloatType) {
            return Float.class.getName();
        } else if (type instanceof LongType) {
            return Long.class.getName();
        } else if (type instanceof DoubleType) {
            return Double.class.getName();
        } else if (type instanceof BooleanType) {
            return Boolean.class.getName();
        } else if (type instanceof EnumType) {
            return String.class.getName();
        } else if (type instanceof XMLType) {
            return String.class.getName();
        }
        return null;
    }
}
