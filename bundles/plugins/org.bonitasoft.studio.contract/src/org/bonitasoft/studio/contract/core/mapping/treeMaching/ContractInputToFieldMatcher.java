/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.contract.core.mapping.treeMaching;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;

public class ContractInputToFieldMatcher {

    public static Optional<ContractInput> findMatchingContractInputForField(Field field, List<ContractInput> inputs) {
        return inputs.stream()
                .filter(aChildInput -> Objects.equals(aChildInput.getName(), field.getName()))
                .filter(aChildInput -> matchingType(field, aChildInput))
                .filter(aChildInput -> matchingMultiple(field, aChildInput))
                .findFirst();
    }

    public static boolean matchingType(Field field, ContractInput childInput) {
        if (field instanceof SimpleField) {
            return new SimpleFieldToContractInputMapping((SimpleField) field).getContractInputType() == childInput
                    .getType();
        } else if (field instanceof RelationField) {
            return childInput.getType() == ContractInputType.COMPLEX;
        }
        throw new IllegalStateException(String.format("Unknown Field type: %s", field.getClass()));
    }

    public static boolean matchingMultiple(Field field, ContractInput input) {
        return field.isCollection() && input.isMultiple()
                || !(field.isCollection() && input.isMultiple());
    }

}
