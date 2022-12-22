/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.expression.Expression;

public class ProcessScriptsCompilationResult {

    private Map<String, List<String>> result = new HashMap<>();

    public void add(String uuid, List<String> errorMessages) {
        result.put(uuid, errorMessages);
    }

    public List<String> getResult(Expression expression) {
        return result.get(ModelHelper.getEObjectID(expression));
    }

}
