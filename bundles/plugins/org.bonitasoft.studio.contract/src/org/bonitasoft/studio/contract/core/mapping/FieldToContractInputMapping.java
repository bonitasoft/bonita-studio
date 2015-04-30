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
import java.util.List;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;

/**
 * @author aurelie
 */
public abstract class FieldToContractInputMapping {

    private final Field field;

    private final List<FieldToContractInputMapping> children = new ArrayList<FieldToContractInputMapping>();

    private FieldToContractInputMapping parent;

    public FieldToContractInputMapping(final Field field) {
        this.field = field;
    }

    public void addChild(final FieldToContractInputMapping child) {
        if (children.add(child)) {
            child.setParent(this);
        }
    }

    public void setParent(final FieldToContractInputMapping parentField) {
        parent = parentField;
    }

    public List<FieldToContractInputMapping> getChildren() {
        return children;
    }

    public FieldToContractInputMapping getParent() {
        return parent;
    }

    public ContractInput toContractInput() {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(field.getName());
        contractInput.setType(toContractInputType());
        contractInput.setMultiple(field.isCollection());
        contractInput.setMandatory(!field.isNullable());
        return contractInput;
    }

    protected abstract ContractInputType toContractInputType();

}
