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

import static com.google.common.base.Preconditions.checkNotNull;

import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;

public class InitializerContext {

    private FieldToContractInputMapping mapping;
    private Data data;
    private boolean checkExistence;
    private boolean createMode;
    private String localVariableName;
    private String localListVariableName;
    private ContractInput contractInput;

    public String getLocalVariableName() {
        return localVariableName;
    }

    public void setLocalVariableName(final String localVariableName) {
        this.localVariableName = localVariableName;
    }

    public String getLocalListVariableName() {
        return localListVariableName;
    }

    public void setLocalListVariableName(final String localListVariableName) {
        this.localListVariableName = localListVariableName;
    }

    public String getRef(final InitializerContext parentContext) {
        return toRefName(parentContext);
    }

    public RelationField getField() {
        checkNotNull(mapping, "No mapping set in context");
        return (RelationField) mapping.getField();
    }

    public boolean checkExistence() {
        return checkExistence;
    }

    public void setCheckExistence(final boolean checkExistence) {
        this.checkExistence = checkExistence;
    }

    public void setMapping(final FieldToContractInputMapping mapping) {
        this.mapping = mapping;
    }

    public boolean isCreateMode() {
        return createMode;
    }

    public void setCreateMode(final boolean createMode) {
        this.createMode = createMode;
    }

    private String toRefName(final InitializerContext parentContext) {
        return parentContext != null ? parentContext.getLocalVariableName() + "." + getField().getName() : getData().getName() + "."
                + getField().getName();
    }

    public FieldToContractInputMapping getMapping() {
        return mapping;
    }

    public ContractInput getContractInput() {
        return contractInput;
    }

    public Data getData() {
        return data;
    }

    public void setData(final Data data) {
        this.data = data;
    }

    public void setContractInput(final ContractInput contractInput) {
        this.contractInput = contractInput;
    }

}
