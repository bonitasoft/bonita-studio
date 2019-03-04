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
package org.bonitasoft.studio.contract.core.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.contract.core.mapping.operation.MappingOperationScriptBuilder;
import org.bonitasoft.studio.contract.core.mapping.operation.PropertySetter;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;

public abstract class FieldToContractInputMapping {

    private final Field field;

    private final List<FieldToContractInputMapping> children = new ArrayList<>();

    private FieldToContractInputMapping parent;

    private boolean generated = true;

    private ContractInput contractInput;

    private ContractInputType contractInputType;

    public FieldToContractInputMapping(final Field field) {
        this.field = field;
    }

    public void addChild(final FieldToContractInputMapping child) {
        if (children.add(child)) {
            child.setParent(this);
        }
    }

    public Field getField() {
        return field;
    }

    public String getSetterName() {
        return new PropertySetter(field).getSetterName();
    }

    public abstract String getFieldType();

    public void setParent(final FieldToContractInputMapping parentField) {
        parent = parentField;
    }

    public List<FieldToContractInputMapping> getChildren() {
        return Collections.<FieldToContractInputMapping> unmodifiableList(children);
    }

    public FieldToContractInputMapping getParent() {
        return parent;
    }

    public ContractInput toContractInput(final ContractInput parentInput) {
        contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(field.getName());
        contractInput.setType(getContractInputType());
        contractInput.setMultiple(field != null && field.isCollection());
        if (parentInput != null) {
            parentInput.getInputs().add(contractInput);
        }
        return contractInput;
    }

    public ContractInput getContractInput() {
        if (contractInput == null) {
            contractInput = toContractInput(null);
        }
        return contractInput;
    }

    public MappingOperationScriptBuilder getScriptBuilder(BusinessObjectData data) {
        return new MappingOperationScriptBuilder(data, this);
    }

    protected abstract ContractInputType toContractInputType();

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(final boolean generated) {
        this.generated = generated;
    }

    public ContractInputType getContractInputType() {
        if (contractInputType == null) {
            contractInputType = toContractInputType();
        }
        return contractInputType;
    }

    public void setContractInputType(ContractInputType contractInputType) {
        this.contractInputType = contractInputType;
    }

}
