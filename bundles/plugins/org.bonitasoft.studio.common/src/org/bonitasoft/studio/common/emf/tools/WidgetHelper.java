/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.emf.tools;

import java.util.List;

import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.Widget;

/**
 * @author Romain Bioteau
 *
 */
public class WidgetHelper {

    public static final String FIELD_PREFIX = "field_";

    public static String getAssociatedReturnType(final Widget widget){
        if(widget instanceof  Duplicable && ((Duplicable) widget).isDuplicate()){
            return List.class.getName();
        }
        return new WidgetReturnTypeFormSwitch().doSwitch(widget);

    }

    public static String getAssociatedInputType(final Widget widget){
        if(widget instanceof  Duplicable && ((Duplicable) widget).isDuplicate()){
            return List.class.getName();
        }
        return new WidgetReturnTypeFormSwitch(){
            @Override
            public String caseFileWidget(final org.bonitasoft.studio.model.form.FileWidget object) {
                return String.class.getName();
            }

        }.doSwitch(widget);
    }
}
