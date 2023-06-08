/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.core.mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.BusinessDataStore;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.TreeResult;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.resolver.ContractToBusinessDataResolver;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.web.designer.model.contract.BusinessDataReference;
import org.bonitasoft.web.designer.model.contract.BusinessDataReference.RelationType;
import org.bonitasoft.web.designer.model.contract.DataReference;
import org.eclipse.e4.core.di.annotations.Creatable;

@Creatable
public class ContractConstraintBuilder {

    private RepositoryAccessor repositoryAccessor;

    @Inject
    public ContractConstraintBuilder(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    public List<ContractConstraint> buildConstraints(ContractInput rootContractInput, Pool pool) {
        List<ContractConstraint> result = new ArrayList<>();
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> bmdStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        ContractToBusinessDataResolver contractToBusinessDataResolver = new ContractToBusinessDataResolver(
                new BusinessDataStore(pool, repositoryAccessor));
        TreeResult treeResult = contractToBusinessDataResolver.resolve(rootContractInput);
        DataReference dataReference = treeResult.getDataReference(rootContractInput);
        if (dataReference instanceof BusinessDataReference) {
            Optional<BusinessObject> businessObject = bmdStore
                    .getBusinessObjectByQualifiedName(dataReference.getType());
            if (businessObject.isPresent()) {
                Node node = new Node(rootContractInput.getName(), rootContractInput.isMultiple(), null);
                buildConstraints(rootContractInput.getInputs(), businessObject.get(), treeResult, result,
                        bmdStore, node);
            }
        }
        return result.stream()
                .map(constraint -> {
                    constraint.getInputNames().add(rootContractInput.getName());
                    return constraint;
                })
                .collect(Collectors.toList());
    }

    private void buildConstraints(List<ContractInput> inputs,
            BusinessObject businessObject,
            TreeResult treeResult,
            List<ContractConstraint> result,
            BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> bmdStore,
            Node parentNode) {
        for (ContractInput input : inputs) {
            if (!Objects.equals(input.getName(), FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME)) {
                Node node = new Node(input.getName(), input.isMultiple(), parentNode);
                DataReference dataReference = treeResult.getDataReference(input);
                if (!isNullableRelation(input.getName(), businessObject)) {
                    String typeName = dataReference != null
                            ? bmdStore.getBusinessObjectByQualifiedName(dataReference.getType())
                                    .map(bo -> bo.getSimpleName()).orElse(input.getName())
                            : input.getName();
                    result.add(createMandatoryConstraint(node, typeName, businessObject.getSimpleName()));
                }
                if (isLongField(input, businessObject)) {
                    result.add(createLongConstraint(node, businessObject.getSimpleName()));
                }
                if (dataReference instanceof BusinessDataReference) {
                    if (((BusinessDataReference) dataReference).getRelationType() == RelationType.AGGREGATION) {
                        String typeName = bmdStore.getBusinessObjectByQualifiedName(dataReference.getType())
                                .map(bo -> bo.getSimpleName()).orElse(input.getName());
                        result.add(createAggregationConstraint(node, typeName, businessObject.getSimpleName()));
                    }
                    bmdStore.getBusinessObjectByQualifiedName(dataReference.getType())
                            .ifPresent(
                                    bo -> buildConstraints(input.getInputs(), bo, treeResult, result, bmdStore,
                                            node));
                }
            }
        }
    }

    // Long field are used as text contract input, to avoid errors because MAX_VAlUE is different in js
    private boolean isLongField(ContractInput input, BusinessObject businessObject) {
        if (Objects.equals(input.getType(), ContractInputType.TEXT)) {
            return businessObject.getFields().stream()
                    .filter(SimpleField.class::isInstance)
                    .map(SimpleField.class::cast)
                    .filter(field -> Objects.equals(field.getType(), FieldType.LONG))
                    .anyMatch(f -> Objects.equals(f.getName(), input.getName()));
        }
        return false;
    }

    private ContractConstraint createLongConstraint(Node node, String parentTypeName) {
        ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setName(String.format("type_long_%s", node.toPath("_")));
        constraint.setExpression(node.toLongConstraintExpression());
        constraint.setErrorMessage(
                String.format("A Long value is expected for %s.%s", parentTypeName, node.name));
        return constraint;
    }

    private ContractConstraint createMandatoryConstraint(Node node, String typeName, String parentTypeName) {
        ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setName(String.format("mandatory_%s", node.toPath("_")));
        constraint.setExpression(node.toMandatoryConstraintExpression());
        constraint.setErrorMessage(String.format("%s is mandatory for %s", typeName, parentTypeName));
        return constraint;
    }

    private ContractConstraint createAggregationConstraint(Node node, String typeName, String parentTypeName) {
        ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setName(String.format("aggregation_%s", node.toPath("_")));
        constraint.setExpression(node.toAggregationConstraintExpression());
        constraint.setErrorMessage(String.format("%s must reference an existing instance with a persistenceId for %s",
                typeName, parentTypeName));
        return constraint;
    }

    private boolean isNullableRelation(String fieldName, BusinessObject businessObject) {
        return businessObject.getFields().stream()
                .filter(f -> Objects.equals(f.getName(), fieldName))
                .anyMatch(Field::isNullable);
    }

    private class Node {

        private String name;
        private boolean isMultiple;
        private Node parent;

        private Node(String name, boolean isMultiple, Node parent) {
            this.name = name;
            this.isMultiple = isMultiple;
            this.parent = parent;
        }

        private String toMandatoryConstraintExpression() {
            List<String> result = new ArrayList<>();
            boolean hasAMultipleParent = hasAMultipleParent();
            result.add(hasAMultipleParent ? String.format("%s.flatten().every{it!=null}", name) : name);
            Node parentNode = parent;
            while (parentNode != null) {
                result.add(parentNode.name);
                parentNode = parentNode.parent;
            }
            Collections.reverse(result);
            String expression = result.stream().collect(Collectors.joining("?."));
            if (hasAMultipleParent) {
                return expression;
            }
            return expression + " != null";
        }

        private String toAggregationConstraintExpression() {
            if (hasAMultipleParent()) {
                return toMulitpleAggregationConstraintExpression();
            }
            Node persistenceIdNode = new Node(FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME, false,
                    this);
            return "!" + toPath("?.") + " || " + persistenceIdNode.toPath("?.");
        }

        private String toLongConstraintExpression() {
            if (isMultiple || hasAMultipleParent()) {
                return toMultipleLongConstraintExpression();
            }
            String path = toPath("?.");
            return "!" + path + " || " + path + ".isLong()";
        }

        private String toMultipleLongConstraintExpression() {
            List<String> result = new ArrayList<>();
            result.add(String.format("%s.flatten().findAll().every{ it.isLong() }", name));
            Node parentNode = parent;
            while (parentNode != null) {
                result.add(parentNode.name);
                parentNode = parentNode.parent;
            }
            Collections.reverse(result);
            return result.stream().collect(Collectors.joining("?."));
        }

        private String toPath(String separator) {
            List<String> result = new ArrayList<>();
            result.add(name);
            Node parentNode = parent;
            while (parentNode != null) {
                result.add(parentNode.name);
                parentNode = parentNode.parent;
            }
            Collections.reverse(result);
            return result.stream().collect(Collectors.joining(separator));
        }

        private String toMulitpleAggregationConstraintExpression() {
            List<String> result = new ArrayList<>();
            result.add(String.format("%s.flatten().every{!it || it.persistenceId_string}", name));
            Node parentNode = parent;
            while (parentNode != null) {
                result.add(parentNode.name);
                parentNode = parentNode.parent;
            }
            Collections.reverse(result);
            String expression = result.stream().collect(Collectors.joining("?."));
            return expression;
        }

        private boolean hasAMultipleParent() {
            Node parentNode = parent;
            while (parentNode != null) {
                if (parentNode.isMultiple) {
                    return true;
                }
                parentNode = parentNode.parent;
            }
            return false;
        }

    }
}
