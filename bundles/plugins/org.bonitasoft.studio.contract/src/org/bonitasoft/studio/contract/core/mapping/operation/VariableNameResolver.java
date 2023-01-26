/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.core.mapping.operation;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.engine.bdm.BDMSimpleNameProvider;
import org.bonitasoft.engine.bdm.model.BusinessObject;

public class VariableNameResolver {

    private static final String VAR_SUFFIX = "Var";
    private static final String LIST_VAR_SUFFIX = "List";

    private final Map<String, Integer> context = new HashMap<>();

    public String newVarName(BusinessObject businessObject) {
        checkArgument(businessObject != null);
        return varName(businessObject,VAR_SUFFIX);
    }

    public String newListVarName(BusinessObject businessObject) {
        checkArgument(businessObject != null);
        return varName(businessObject,LIST_VAR_SUFFIX);
    }

    private String varName(final BusinessObject businessObject, String varSuffix) {
        final String simpleBusinessObjectName = BDMSimpleNameProvider.getSimpleBusinessObjectName(businessObject.getQualifiedName());
        String result = uncapitalizeFirst(simpleBusinessObjectName) + varSuffix;
        if (context.containsKey(varKey(varSuffix, simpleBusinessObjectName))) {
            result = result + String.valueOf(context.get(varKey(varSuffix, simpleBusinessObjectName)));
        }
        Integer counter = context.get(varKey(varSuffix, simpleBusinessObjectName));
        counter = counter == null ? 1 : counter;
        counter++;
        context.put(varKey(varSuffix, simpleBusinessObjectName), counter);
        return result;
    }

    private String varKey(String varSuffix, final String simpleBusinessObjectName) {
        return simpleBusinessObjectName + varSuffix;
    }


    private String uncapitalizeFirst(final String value) {
        return Character.toLowerCase(value.charAt(0)) + value.substring(1, value.length());
    }


}
