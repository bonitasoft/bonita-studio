package org.bonitasoft.studio.businessobject.editor.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.FetchType;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectModelBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.IndexBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.PackageBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.UniqueConstraintBuilder;
import org.junit.Test;

public class BusinessDataModelConverterTest {

    private static final String DESCRIPTION = "description";
    private static final String CONSTRAINT_NAME = "constraint name";
    private static final String QUERY_RETURN_TYPE = "String";
    private static final String QUERY_CONTENT = "queryContent";
    private static final String QUERY_NAME = "query";
    private static final String INDEX_NAME = "index";
    private static final String FIELD1_NAME = "field1";
    private static final String FIELD2_NAME = "field2";
    private static final String FIELD3_NAME = "field3";
    private static final String BUSINESS_OBJECT1_NAME = "org.bonitasoft.businessObject1";
    private static final String BUSINESS_OBJECT2_NAME = "com.bonitasoft.businessObject2";

    @Test
    public void should_convert_into_emf_model() {
        org.bonitasoft.engine.bdm.model.BusinessObjectModel engineModel = createEngineModel();
        BDMArtifactDescriptor bdmArtifactDescriptor = new BDMArtifactDescriptor();
        bdmArtifactDescriptor.setGroupId("groupId");
        BusinessDataModelConverter converter = new BusinessDataModelConverter();
        BusinessObjectModel emfModel = converter.toEmfModel(engineModel, bdmArtifactDescriptor);
        assertThat(emfModel.getGroupId()).isEqualTo("groupId");
        assertThat(emfModel.getPackages()).hasSize(2);
        assertThat(emfModel.getPackages()).extracting(Package::getName)
                .containsExactlyInAnyOrder("org.bonitasoft", "com.bonitasoft");
        assertThat(emfModel.getPackages()).flatExtracting(Package::getBusinessObjects).hasSize(2);
        assertThat(emfModel.getPackages()).flatExtracting(Package::getBusinessObjects)
                .extracting(BusinessObject::getQualifiedName)
                .containsExactlyInAnyOrder(BUSINESS_OBJECT1_NAME, BUSINESS_OBJECT2_NAME);
        assertThat(emfModel.getPackages()).flatExtracting(Package::getBusinessObjects)
                .extracting(BusinessObject::getSimpleName)
                .containsExactlyInAnyOrder("businessObject1", "businessObject2");

        BusinessObject businessObject1 = emfModel.getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .filter(bo -> bo.getQualifiedName().equals(BUSINESS_OBJECT1_NAME)).findFirst().get();
        checkBusinessObject1(businessObject1);
        BusinessObject businessObject2 = emfModel.getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .filter(bo -> bo.getQualifiedName().equals(BUSINESS_OBJECT2_NAME)).findFirst().get();
        checkBusinessObject2(businessObject2, businessObject1);
    }

    @Test
    public void should_convert_into_engine_model() {
        BusinessObjectModel emfModel = createEmfModel();
        BusinessDataModelConverter converter = new BusinessDataModelConverter();
        org.bonitasoft.engine.bdm.model.BusinessObjectModel engineModel = converter.toEngineModel(emfModel);
        assertThat(engineModel.getBusinessObjects()).hasSize(2);
        assertThat(engineModel.getBusinessObjects())
                .extracting(org.bonitasoft.engine.bdm.model.BusinessObject::getQualifiedName)
                .containsExactlyInAnyOrder(BUSINESS_OBJECT1_NAME, BUSINESS_OBJECT2_NAME);
        assertThat(engineModel.getBusinessObjects())
                .extracting(org.bonitasoft.engine.bdm.model.BusinessObject::getSimpleName)
                .containsExactlyInAnyOrder("businessObject1", "businessObject2");

        org.bonitasoft.engine.bdm.model.BusinessObject businessObject1 = engineModel.getBusinessObjects().stream()
                .filter(bo -> bo.getQualifiedName().equals(BUSINESS_OBJECT1_NAME)).findFirst().get();
        checkBusinessObject1(businessObject1);
        org.bonitasoft.engine.bdm.model.BusinessObject businessObject2 = engineModel.getBusinessObjects().stream()
                .filter(bo -> bo.getQualifiedName().equals(BUSINESS_OBJECT2_NAME)).findFirst().get();
        checkBusinessObject2(businessObject2, businessObject1);
    }

    private void checkBusinessObject2(BusinessObject businessObject2, BusinessObject businessObject1) {
        assertThat(businessObject2.getFields()).hasSize(2);
        assertThat(businessObject2.getFields()).allMatch(RelationField.class::isInstance);
        assertThat(businessObject2.getFields()).extracting(Field::getName).containsExactlyInAnyOrder(FIELD2_NAME,
                FIELD3_NAME);
        assertThat(businessObject2.getFields().stream().map(RelationField.class::cast))
                .extracting(RelationField::getFetchType)
                .containsExactlyInAnyOrder(FetchType.EAGER, FetchType.LAZY);
        assertThat(businessObject2.getFields().stream().map(RelationField.class::cast))
                .extracting(RelationField::getType)
                .containsExactlyInAnyOrder(RelationType.AGGREGATION, RelationType.COMPOSITION);
        assertThat(businessObject2.getFields().stream().map(RelationField.class::cast))
                .extracting(RelationField::getReference)
                .containsExactlyInAnyOrder(businessObject1, businessObject1);
    }

    private void checkBusinessObject2(org.bonitasoft.engine.bdm.model.BusinessObject businessObject2,
            org.bonitasoft.engine.bdm.model.BusinessObject businessObject1) {
        assertThat(businessObject2.getFields()).hasSize(2);
        assertThat(businessObject2.getFields())
                .allMatch(org.bonitasoft.engine.bdm.model.field.RelationField.class::isInstance);
        assertThat(businessObject2.getFields()).extracting(org.bonitasoft.engine.bdm.model.field.Field::getName)
                .containsExactlyInAnyOrder(FIELD2_NAME,
                        FIELD3_NAME);
        assertThat(businessObject2.getFields().stream().map(org.bonitasoft.engine.bdm.model.field.RelationField.class::cast))
                .extracting(org.bonitasoft.engine.bdm.model.field.RelationField::getFetchType)
                .containsExactlyInAnyOrder(org.bonitasoft.engine.bdm.model.field.RelationField.FetchType.EAGER,
                        org.bonitasoft.engine.bdm.model.field.RelationField.FetchType.LAZY);
        assertThat(businessObject2.getFields().stream().map(org.bonitasoft.engine.bdm.model.field.RelationField.class::cast))
                .extracting(org.bonitasoft.engine.bdm.model.field.RelationField::getType)
                .containsExactlyInAnyOrder(Type.AGGREGATION, Type.COMPOSITION);
        assertThat(businessObject2.getFields().stream().map(org.bonitasoft.engine.bdm.model.field.RelationField.class::cast))
                .extracting(org.bonitasoft.engine.bdm.model.field.RelationField::getReference)
                .containsExactlyInAnyOrder(businessObject1, businessObject1);
    }

    private void checkBusinessObject1(org.bonitasoft.engine.bdm.model.BusinessObject businessObject1) {
        assertThat(businessObject1.getDescription()).isEqualTo(DESCRIPTION);

        assertThat(businessObject1.getIndexes()).extracting(org.bonitasoft.engine.bdm.model.Index::getName)
                .containsExactly(INDEX_NAME);
        assertThat(businessObject1.getIndexes()).flatExtracting(org.bonitasoft.engine.bdm.model.Index::getFieldNames)
                .containsExactly(FIELD1_NAME);

        assertThat(businessObject1.getQueries()).extracting(org.bonitasoft.engine.bdm.model.Query::getName)
                .containsExactly(QUERY_NAME);
        assertThat(businessObject1.getQueries()).extracting(org.bonitasoft.engine.bdm.model.Query::getReturnType)
                .containsExactly(QUERY_RETURN_TYPE);
        assertThat(businessObject1.getQueries()).extracting(org.bonitasoft.engine.bdm.model.Query::getContent)
                .containsExactly(QUERY_CONTENT);

        assertThat(businessObject1.getUniqueConstraints())
                .extracting(org.bonitasoft.engine.bdm.model.UniqueConstraint::getName)
                .containsExactly(CONSTRAINT_NAME);
        assertThat(businessObject1.getUniqueConstraints())
                .flatExtracting(org.bonitasoft.engine.bdm.model.UniqueConstraint::getFieldNames)
                .containsExactly(FIELD1_NAME);

        assertThat(businessObject1.getFields()).hasSize(1);
        org.bonitasoft.engine.bdm.model.field.SimpleField field1 = (org.bonitasoft.engine.bdm.model.field.SimpleField) businessObject1
                .getFields().get(0);
        assertThat(field1.getName()).isEqualTo(FIELD1_NAME);
        assertThat(field1.isNullable()).isTrue();
        assertThat(field1.isCollection()).isFalse();
        assertThat(field1.getLength()).isEqualTo(50);
        assertThat(field1.getType()).isEqualTo(org.bonitasoft.engine.bdm.model.field.FieldType.STRING);
    }

    private void checkBusinessObject1(BusinessObject businessObject1) {
        assertThat(businessObject1.getDescription()).isEqualTo(DESCRIPTION);

        assertThat(businessObject1.getIndexes()).extracting(Index::getName).containsExactly(INDEX_NAME);
        assertThat(businessObject1.getIndexes()).flatExtracting(Index::getFieldNames).containsExactly(FIELD1_NAME);

        assertThat(businessObject1.getQueries()).extracting(Query::getName).containsExactly(QUERY_NAME);
        assertThat(businessObject1.getQueries()).extracting(Query::getReturnType).containsExactly(QUERY_RETURN_TYPE);
        assertThat(businessObject1.getQueries()).extracting(Query::getContent).containsExactly(QUERY_CONTENT);

        assertThat(businessObject1.getDefaultQueries()).extracting(Query::getName)
                .containsExactlyInAnyOrder("findByPersistenceId", "findByField1", "find", "countForFind");

        assertThat(businessObject1.getUniqueConstraints()).extracting(UniqueConstraint::getName)
                .containsExactly(CONSTRAINT_NAME);
        assertThat(businessObject1.getUniqueConstraints()).flatExtracting(UniqueConstraint::getFieldNames)
                .containsExactly(FIELD1_NAME);

        assertThat(businessObject1.getFields()).hasSize(1);
        SimpleField field1 = (SimpleField) businessObject1.getFields().get(0);
        assertThat(field1.getName()).isEqualTo(FIELD1_NAME);
        assertThat(field1.isNullable()).isTrue();
        assertThat(field1.isCollection()).isFalse();
        assertThat(field1.getLength()).isEqualTo(50);
        assertThat(field1.getType()).isEqualTo(FieldType.STRING);
    }

    private BusinessObjectModel createEmfModel() {
        BusinessObjectModel model = new BusinessObjectModelBuilder().create();
        Package package1 = createEmfPackage1();
        Package package2 = createEmfPackage2(package1);
        model.getPackages().add(package1);
        model.getPackages().add(package2);
        return model;
    }

    private Package createEmfPackage1() {
        BusinessObject businessObject1 = createEmfBusinessObject1();

        return new PackageBuilder()
                .withName("org.bonitasoft")
                .withBusinessObjects(businessObject1)
                .create();
    }

    private Package createEmfPackage2(Package package1) {
        BusinessObject businessObject1 = package1.getBusinessObjects().get(0);
        BusinessObject businessObject2 = createEmfBusinessObject2(businessObject1);

        return new PackageBuilder()
                .withName("com.bonitasoft")
                .withBusinessObjects(businessObject2)
                .create();
    }

    private org.bonitasoft.engine.bdm.model.BusinessObjectModel createEngineModel() {
        org.bonitasoft.engine.bdm.model.BusinessObjectModel model = new org.bonitasoft.engine.bdm.model.BusinessObjectModel();
        org.bonitasoft.engine.bdm.model.BusinessObject businessObject1 = createBusinessObject1();
        org.bonitasoft.engine.bdm.model.BusinessObject businessObject2 = createBusinessObject2(businessObject1);
        model.addBusinessObject(businessObject1);
        model.addBusinessObject(businessObject2);
        return model;
    }

    private org.bonitasoft.engine.bdm.model.BusinessObject createBusinessObject2(
            org.bonitasoft.engine.bdm.model.BusinessObject businessObject1) {
        org.bonitasoft.engine.bdm.model.BusinessObject businessObject = new org.bonitasoft.engine.bdm.model.BusinessObject(
                BUSINESS_OBJECT2_NAME);
        businessObject.addField(createField2(businessObject1));
        businessObject.addField(createField3(businessObject1));
        return businessObject;
    }

    private BusinessObject createEmfBusinessObject2(BusinessObject businessObject1) {
        BusinessObject businessObject = new BusinessObjectBuilder()
                .withQualifiedName(BUSINESS_OBJECT2_NAME)
                .create();
        businessObject.getFields().add(createField2(businessObject1));
        businessObject.getFields().add(createField3(businessObject1));
        return businessObject;
    }

    private org.bonitasoft.engine.bdm.model.BusinessObject createBusinessObject1() {
        org.bonitasoft.engine.bdm.model.BusinessObject businessObject = new org.bonitasoft.engine.bdm.model.BusinessObject(
                BUSINESS_OBJECT1_NAME);
        businessObject.setDescription(DESCRIPTION);
        businessObject.addField(createField1());
        businessObject.addIndex(INDEX_NAME, FIELD1_NAME);
        businessObject.addQuery(QUERY_NAME, QUERY_CONTENT, QUERY_RETURN_TYPE);
        businessObject.addUniqueConstraint(CONSTRAINT_NAME, FIELD1_NAME);
        return businessObject;
    }

    private BusinessObject createEmfBusinessObject1() {
        return new BusinessObjectBuilder()
                .withQualifiedName(BUSINESS_OBJECT1_NAME)
                .withdescription(DESCRIPTION)
                .withFields(createEmfField1())
                .withIndexes(new IndexBuilder().withName(INDEX_NAME).withFieldNames(FIELD1_NAME).create())
                .withQueries(new QueryBuilder().withName(QUERY_NAME).withContent(QUERY_CONTENT)
                        .withReturnType(QUERY_RETURN_TYPE).create())
                .withUniqueConstraints(
                        new UniqueConstraintBuilder().withName(CONSTRAINT_NAME).withFieldNames(FIELD1_NAME).create())
                .create();
    }

    private org.bonitasoft.engine.bdm.model.field.Field createField1() {
        org.bonitasoft.engine.bdm.model.field.SimpleField field = new org.bonitasoft.engine.bdm.model.field.SimpleField();
        field.setName(FIELD1_NAME);
        field.setType(org.bonitasoft.engine.bdm.model.field.FieldType.STRING);
        field.setCollection(false);
        field.setNullable(true);
        field.setLength(50);
        return field;
    }

    private Field createEmfField1() {
        return new SimpleFieldBuilder()
                .withName(FIELD1_NAME)
                .withType(FieldType.STRING)
                .withCollection(false)
                .withNullable(true)
                .withLength(50)
                .create();
    }

    private org.bonitasoft.engine.bdm.model.field.Field createField2(
            org.bonitasoft.engine.bdm.model.BusinessObject businessObject1) {
        org.bonitasoft.engine.bdm.model.field.RelationField field = new org.bonitasoft.engine.bdm.model.field.RelationField();
        field.setType(Type.COMPOSITION);
        field.setName(FIELD2_NAME);
        field.setReference(businessObject1);
        field.setFetchType(org.bonitasoft.engine.bdm.model.field.RelationField.FetchType.EAGER);
        return field;
    }

    private Field createField2(BusinessObject businessObject1) {
        return new RelationFieldBuilder()
                .withType(RelationType.COMPOSITION)
                .withName(FIELD2_NAME)
                .withReference(businessObject1)
                .withFetchType(FetchType.EAGER)
                .create();
    }

    private org.bonitasoft.engine.bdm.model.field.Field createField3(
            org.bonitasoft.engine.bdm.model.BusinessObject businessObject1) {
        org.bonitasoft.engine.bdm.model.field.RelationField field = new org.bonitasoft.engine.bdm.model.field.RelationField();
        field.setType(Type.AGGREGATION);
        field.setName(FIELD3_NAME);
        field.setReference(businessObject1);
        field.setFetchType(org.bonitasoft.engine.bdm.model.field.RelationField.FetchType.LAZY);
        return field;
    }

    private Field createField3(BusinessObject businessObject1) {
        return new RelationFieldBuilder()
                .withType(RelationType.AGGREGATION)
                .withName(FIELD3_NAME)
                .withReference(businessObject1)
                .withFetchType(FetchType.LAZY)
                .create();
    }

}
