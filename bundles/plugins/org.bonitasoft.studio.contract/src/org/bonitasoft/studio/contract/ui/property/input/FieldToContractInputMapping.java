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
package org.bonitasoft.studio.contract.ui.property.input;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.instanceOf;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;

import com.google.common.base.Predicate;

/**
 * @author aurelie
 */
public class FieldToContractInputMapping {

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

    /**
     * @return
     */
    public ContractInput toContractInput() {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(field.getName());
        contractInput.setType(toContractInputType());
        if (shouldAddChildInput()) {
            for (final FieldToContractInputMapping child : getChildren()) {
                contractInput.getInputs().add(child.toContractInput());
            }
        }
        return contractInput;
    }

    private boolean shouldAddChildInput() {
        return and(instanceOf(RelationField.class), withType(Type.COMPOSITION)).apply(field);
    }

    private Predicate<Field> withType(final Type relationType) {
        return new Predicate<Field>() {

            @Override
            public boolean apply(final Field input) {
                return ((RelationField) input).getType() == relationType;
            }
        };
    }

    private ContractInputType toContractInputType() {
        if (field instanceof SimpleField) {
            final FieldType fieldType = ((SimpleField) field).getType();
            switch (fieldType) {
                case STRING:
                    return ContractInputType.TEXT;
                case TEXT:
                    return ContractInputType.TEXT;
                case INTEGER:
                    return ContractInputType.INTEGER;
                case LONG:
                    return ContractInputType.INTEGER;
                case DOUBLE:
                    return ContractInputType.DECIMAL;
                case FLOAT:
                    return ContractInputType.DECIMAL;
                case BOOLEAN:
                    return ContractInputType.BOOLEAN;
                case DATE:
                    return ContractInputType.DATE;
                default:
                    throw new IllegalStateException(String.format("Failed to convert field type %s to contract input type", fieldType));
            }
        }
        if (field instanceof RelationField) {
            final Type relationType = ((RelationField) field).getType();
            switch (relationType) {
                case COMPOSITION:
                    return ContractInputType.COMPLEX;
                case AGGREGATION:
                    return ContractInputType.TEXT;
                default:
                    throw new IllegalStateException(String.format("Failed to convert field type %s to contract input type", relationType));
            }
        }
        throw new IllegalStateException("Unsupported field type.");
    }
}
