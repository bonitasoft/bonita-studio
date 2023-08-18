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
package org.bonitasoft.studio.data.util;

import java.util.Date;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.BooleanType;
import org.bonitasoft.bpm.model.process.DataType;
import org.bonitasoft.bpm.model.process.DateType;
import org.bonitasoft.bpm.model.process.DoubleType;
import org.bonitasoft.bpm.model.process.EnumType;
import org.bonitasoft.bpm.model.process.FloatType;
import org.bonitasoft.bpm.model.process.IntegerType;
import org.bonitasoft.bpm.model.process.LongType;
import org.bonitasoft.bpm.model.process.StringType;
import org.bonitasoft.bpm.model.process.XMLType;

/**
 * @author Romain Bioteau
 */
public class DataUtil {

    /**
     * @param text
     * @return
     */
    public static String getTechnicalTypeFor(final AbstractProcess container, String type) {
        final StringBuilder sb = new StringBuilder();
        type = ModelHelper.getDataTypeID(type);
        for (final DataType t : container.getDatatypes()) {
            if (t.getName().equals(type)) {
                sb.append(Messages.dataTechnicalTypeLabel);
                sb.append(' ');
                if (t instanceof IntegerType) {
                    sb.append(Integer.class.getName());
                } else if (t instanceof DoubleType) {
                    sb.append(Double.class.getName());
                } else if (t instanceof LongType) {
                    sb.append(Long.class.getName());
                } else if (t instanceof FloatType) {
                    sb.append(Float.class.getName());
                } else if (t instanceof DateType) {
                    sb.append(Date.class.getName());
                } else if (t instanceof StringType) {
                    sb.append(String.class.getName());
                } else if (t instanceof BooleanType) {
                    sb.append(Boolean.class.getName());
                } else if (t instanceof XMLType) {
                    sb.append(String.class.getName());
                } else if (t instanceof EnumType) {
                    sb.append(String.class.getName());
                } else {
                    return "";
                }
                break;
            }
        }
        return sb.toString();
    }

}
