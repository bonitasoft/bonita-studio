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

import static org.bonitasoft.studio.common.predicate.ContractInputPredicates.withComplexMultipleInHierarchy;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.common.functions.ContractInputFunctions;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.operation.BusinessObjectInstantiationException;
import org.bonitasoft.studio.model.process.ContractInput;
import org.eclipse.emf.ecore.EObject;

public class NewBusinessObjectInitializer extends AbstractBusinessObjectInitializer {

    public NewBusinessObjectInitializer(final InitializerContext context) {
        super(context);
    }

    @Override
    protected boolean checkExistence() {
        return context.checkExistence();
    }

    @Override
    public String getInitialValue() throws BusinessObjectInstantiationException {
        String initialValue = super.getInitialValue();
        StringBuilder scriptBuilder = new StringBuilder();
        if (validatePresenceInContractInput()) {
            validatePresenceInContractInputBeforeConstructor(scriptBuilder);
        }
        scriptBuilder.append(initialValue);
        return scriptBuilder.toString();
    }

    private void validatePresenceInContractInputBeforeConstructor(StringBuilder scriptBuilder) {
        String toCheck = shouldUseParentIterator()
                ? joinWithParentIterator(
                        ContractInputFunctions.toAncestorNameListUntilMultipleComplex().apply(context.getContractInput()))
                : ContractInputFunctions.toAncestorNameList().apply(context.getContractInput()).stream()
                        .collect(Collectors.joining("?."));
        scriptBuilder.append(String.format("if (!%s) {", toCheck));
        scriptBuilder.append(System.lineSeparator());
        scriptBuilder.append("return null");
        scriptBuilder.append(System.lineSeparator());
        scriptBuilder.append("}");
        scriptBuilder.append(System.lineSeparator());
    }

    private String joinWithParentIterator(List<String> ancestors) {
        String parentIterator = findParentIterator();
        return ancestors.stream().reduce(parentIterator, (ancestor1, ancestor2) -> {
            return Objects.equals(ancestor1, parentIterator)
                    ? String.format("%s.%s", ancestor1, ancestor2)
                    : String.format("%s?.%s", ancestor1, ancestor2);
        });
    }

    private String findParentIterator() {
        Optional<BusinessObject> lastMultipleParent = findLastMultipleParent();
        if (lastMultipleParent.isPresent()) {
            return iteratorName(lastMultipleParent.get());
        }
        throw new IllegalStateException(
                String.format("the contract input %s should have a multiple contract input parent",
                        context.getContractInput().getName()));
    }

    private Optional<BusinessObject> findLastMultipleParent() {
        Optional<FieldToContractInputMapping> current = Optional.of(context.getMapping());
        do {
            current = current.map(FieldToContractInputMapping::getParent);
        } while (current.isPresent() && !current.map(FieldToContractInputMapping::getContractInput)
                .filter(ContractInput::isMultiple).isPresent());
        return current
                .map(FieldToContractInputMapping::getField)
                .filter(RelationField.class::isInstance)
                .map(RelationField.class::cast)
                .map(RelationField::getReference);
    }

    private boolean shouldUseParentIterator() {
        return context.getContractInput().eContainer() != null
                && withComplexMultipleInHierarchy().test(context.getContractInput());
    }

    /**
     * @return false if the contract input is the root contract input
     */
    protected boolean validatePresenceInContractInput() {
        ContractInput contractInput = context.getContractInput();
        EObject parent = contractInput.eContainer();
        return parent instanceof ContractInput;
    }

    @Override
    protected void constructor(StringBuilder scriptBuilder, BusinessObject bo) {
        if (checkExistence()) {
            String ref = context.getRef(getParent() != null ? getParent().getContext() : null);
            scriptBuilder.append(ref);
            scriptBuilder.append(" ?: ");
        }
        newBusinessObject(scriptBuilder, bo);
    }

    private void newBusinessObject(StringBuilder scriptBuilder, BusinessObject bo) {
        scriptBuilder.append("new");
        scriptBuilder.append(" ");
        scriptBuilder.append(bo.getQualifiedName());
        scriptBuilder.append("()");
    }

}
