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
package org.bonitasoft.studio.businessobject.core.expression.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Romain Bioteau
 *
 */
public class QueryExpressionModel {

    private List<BusinessObjectExpressionQuery> businessObjects = new ArrayList<BusinessObjectExpressionQuery>();

    public List<BusinessObjectExpressionQuery> getBusinessObjects() {
        return businessObjects;
    }

    public void setBusinessObjects(final List<BusinessObjectExpressionQuery> businessObjects) {
        this.businessObjects = businessObjects;
    }

}
