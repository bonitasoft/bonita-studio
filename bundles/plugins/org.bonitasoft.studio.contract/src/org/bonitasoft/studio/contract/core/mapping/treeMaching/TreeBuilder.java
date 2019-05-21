/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.contract.core.mapping.treeMaching;

import static org.bonitasoft.studio.contract.core.mapping.treeMaching.ContractInputToFieldMatcher.findMatchingContractInputForField;
import static org.bonitasoft.studio.contract.core.mapping.treeMaching.ContractInputToFieldMatcher.matchingMultiple;
import static org.bonitasoft.studio.contract.core.mapping.treeMaching.ContractInputToFieldMatcher.matchingType;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.FetchType;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMappingFactory;
import org.bonitasoft.studio.contract.core.mapping.RelationFieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.SimpleFieldToContractInputMapping;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.web.designer.model.contract.BusinessDataReference;
import org.bonitasoft.web.designer.model.contract.BusinessDataReference.LoadingType;
import org.bonitasoft.web.designer.model.contract.BusinessDataReference.RelationType;

public class TreeBuilder {

    private BusinessDataStore store;

    public TreeBuilder(BusinessDataStore store) {
        this.store = store;
    }

    public TreeNode buildBusinessObjectTree(BusinessObjectData data, ContractInput input, boolean createReadOnlyInputs) {
        Optional<BusinessObject> businessObject = store.getBusinessObject(data.getClassName());
        BusinessObject bo = businessObject
                .orElseThrow(() -> new IllegalStateException(
                        String.format("No Business Object found with type %s", data.getClassName())));
        TreeNode node = new TreeNode(input,
                new BusinessDataReference(data.getName(), data.getClassName(), RelationType.COMPOSITION, LoadingType.EAGER),
                true, false);
        buildTree(bo, input, node, createReadOnlyInputs);
        return node;
    }

    private void buildTree(BusinessObject bo, ContractInput input, TreeNode node, boolean createReadOnlyInputs) {
        if (input.getType() == ContractInputType.COMPLEX) {
            input.getInputs().stream()
                    .forEach(childInput -> findMatchingField(bo.getFields(), childInput, node, createReadOnlyInputs));
            if (createReadOnlyInputs) {
                bo.getFields().stream()
                        .filter(field -> !findMatchingContractInputForField(field, input.getInputs()).isPresent())
                        .forEach(field -> createReadOnlyNode(bo, field, node, input));
                sortChildInputs(input);
            }
        }
    }

    /**
     * persistenceId input should have index 0, so the drop down generated to selected an aggregated reference
     * comes first in the form.
     * other inputs are already sorted.
     */
    private void sortChildInputs(ContractInput input) {
        input.getInputs().stream()
                .filter(childInput -> Objects.equals(
                        FieldToContractInputMappingFactory.PERSISTENCE_ID_STRING_FIELD_NAME, childInput.getName()))
                .findFirst()
                .ifPresent(persistenceIdInput -> {
                    input.getInputs().move(0, persistenceIdInput);
                });
    }

    private void createReadOnlyNode(BusinessObject bo, Field field, TreeNode node, ContractInput parent) {
        if (field instanceof RelationField) {
            createReadOnlyNodeForRelationField(bo, (RelationField) field, node, parent);
        } else {
            createReadOnlyNodeForSimpleField(bo, (SimpleField) field, node, parent);
        }
    }

    private void createReadOnlyNodeForSimpleField(BusinessObject bo, SimpleField field, TreeNode node,
            ContractInput parent) {
        ContractInput contractInput = new SimpleFieldToContractInputMapping(field).getContractInput();
        contractInput.setCreateMode(parent.isCreateMode());
        if (bo.getFields().contains(field)) {
            int expectedIndex = bo.getFields().indexOf(field);
            int index = expectedIndex > parent.getInputs().size() // might happen if a subpart of the bo isn't displayed in read only
                    ? parent.getInputs().size()
                    : expectedIndex;
            parent.getInputs().add(index, contractInput);
        } else { // persistenceId isn't in the bo
            parent.getInputs().add(contractInput);
        }
        node.addLeafNode(contractInput, !field.isNullable(), true);
    }

    private void createReadOnlyNodeForRelationField(BusinessObject bo, RelationField relationField, TreeNode node,
            ContractInput parent) {
        LoadingType loadingType = toLoadingType(relationField);
        if (!(Objects.equals(loadingType, LoadingType.LAZY) && parent.isMultiple())) {
            BusinessObject businessObjectReference = relationField.getReference();
            ContractInput contractInput = new RelationFieldToContractInputMapping(relationField).getContractInput();
            contractInput.setCreateMode(false);
            parent.getInputs().add(bo.getFields().indexOf(relationField), contractInput);
            RelationType relationType = toRelationType(relationField);
            TreeNode newNode = node.addNode(contractInput, new BusinessDataReference(relationField.getName(),
                    businessObjectReference.getQualifiedName(),
                    relationType,
                    loadingType),
                    !relationField.isNullable(),
                    true);
            businessObjectReference.getFields().stream()
                    .filter(SimpleField.class::isInstance)
                    .map(SimpleField.class::cast)
                    .forEach(field -> createReadOnlyNodeForSimpleField(businessObjectReference, field, newNode,
                            contractInput));
        }
    }

    private void findMatchingField(List<Field> fields, ContractInput childInput, TreeNode node,
            boolean createReadOnlyInputs) {
        fields.stream()
                .filter(field -> field.getName().equals(childInput.getName()))
                .filter(field -> matchingType(field, childInput))
                .filter(field -> matchingMultiple(field, childInput))
                .findFirst()
                .ifPresent(field -> {
                    if (field instanceof RelationField) {
                        RelationField relationField = (RelationField) field;
                        BusinessObject businessObject = relationField.getReference();
                        TreeNode newNode = node.addNode(childInput, new BusinessDataReference(relationField.getName(),
                                businessObject.getQualifiedName(),
                                toRelationType(relationField),
                                toLoadingType(relationField)),
                                !relationField.isNullable(),
                                false);
                        buildTree(businessObject, childInput, newNode, createReadOnlyInputs);
                    } else {
                        node.addLeafNode(childInput, !field.isNullable(), false);
                    }
                });
    }

    private LoadingType toLoadingType(RelationField relationField) {
        return relationField.getFetchType() == FetchType.EAGER ? LoadingType.EAGER : LoadingType.LAZY;
    }

    private RelationType toRelationType(RelationField relationField) {
        return relationField.getType() == Type.COMPOSITION ? RelationType.COMPOSITION : RelationType.AGGREGATION;
    }

}
