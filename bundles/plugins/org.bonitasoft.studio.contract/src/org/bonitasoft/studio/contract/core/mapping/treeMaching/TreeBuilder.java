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
import java.util.Optional;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.FetchType;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
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

    public TreeNode buildBusinessObjectTree(BusinessObjectData data, ContractInput input) {
        Optional<BusinessObject> businessObject = store.getBusinessObject(data.getClassName());
        BusinessObject bo = businessObject
                .orElseThrow(() -> new IllegalStateException(
                        String.format("No Business Object found with type %s", data.getClassName())));
        TreeNode node = new TreeNode(input,
                new BusinessDataReference(data.getName(), data.getClassName(), RelationType.COMPOSITION, LoadingType.EAGER));
        buildTree(bo, input, node);
        return node;
    }

    private void buildTree(BusinessObject bo, ContractInput input, TreeNode node) {
        if (input.getType() == ContractInputType.COMPLEX) {
            input.getInputs().stream()
                    .filter(childInput -> childInput.getType() == ContractInputType.COMPLEX)
                    .forEach(childInput -> findMatchingField(bo.getFields(), childInput, node));
        }
    }

    private void findMatchingField(List<Field> fields, ContractInput childInput, TreeNode node) {
        fields.stream()
                .filter(field -> field.getName().equals(childInput.getName()))
                .filter(field -> matchingType(field, childInput))
                .filter(field -> field.isCollection() && childInput.isMultiple()
                        || !(field.isCollection() && childInput.isMultiple()))
                .findFirst()
                .ifPresent(field -> {
                    RelationField relationField = (RelationField) field;
                    BusinessObject businessObject = relationField.getReference();
                    TreeNode newNode = node.addNode(childInput, new BusinessDataReference(relationField.getName(),
                            businessObject.getQualifiedName(),
                            toRelationType(relationField),
                            toLoadingType(relationField)));
                    buildTree(businessObject, childInput, newNode);
                });
    }

    private LoadingType toLoadingType(RelationField relationField) {
        return relationField.getFetchType() == FetchType.EAGER ? LoadingType.EAGER : LoadingType.LAZY;
    }

    private RelationType toRelationType(RelationField relationField) {
        return relationField.getType() == Type.COMPOSITION ? RelationType.COMPOSITION : RelationType.AGGREGATION;
    }

    private boolean matchingType(Field field, ContractInput childInput) {
        if (field instanceof SimpleField) {
            return new SimpleFieldToContractInputMapping((SimpleField) field).getContractInputType() == childInput
                    .getType();
        } else if (field instanceof RelationField) {
            return childInput.getType() == ContractInputType.COMPLEX;
        }
        throw new IllegalStateException(String.format("Unknown Field type: %s", field.getClass()));
    }

}
