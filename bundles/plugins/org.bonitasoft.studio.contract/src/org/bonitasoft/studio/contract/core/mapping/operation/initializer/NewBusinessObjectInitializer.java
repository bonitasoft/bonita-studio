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
package org.bonitasoft.studio.contract.core.mapping.operation.initializer;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.operation.VariableNameResolver;

public class NewBusinessObjectInitializer extends AbstractBusinessObjectInitializer implements IPropertyInitializer {

    private final boolean checkExistsence;

    public NewBusinessObjectInitializer(final RelationField field, final String refName, VariableNameResolver variableNameResolver,
            final boolean checkExistsence) {
        super(field, refName, variableNameResolver);
        this.checkExistsence = checkExistsence;
    }

    @Override
    protected boolean checkExistence() {
        return checkExistsence;
    }

    @Override
    protected void constructor(final StringBuilder scriptBuilder, final BusinessObject bo, final boolean checkExistence) {
        if (checkExistence) {
            scriptBuilder.append(refName);
            scriptBuilder.append(" == null ? ");
            newBusinessObject(scriptBuilder, bo);
            scriptBuilder.append(" : ");
            scriptBuilder.append(refName);
        } else {
            newBusinessObject(scriptBuilder, bo);
        }
    }

    private void newBusinessObject(final StringBuilder scriptBuilder, final BusinessObject bo) {
        scriptBuilder.append("new");
        scriptBuilder.append(" ");
        scriptBuilder.append(bo.getQualifiedName());
        scriptBuilder.append("()");
    }

}
