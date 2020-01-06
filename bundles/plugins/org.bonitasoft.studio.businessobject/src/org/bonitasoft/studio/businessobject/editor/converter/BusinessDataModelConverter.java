/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.studio.businessobject.core.repository.BDMArtifactDescriptor;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelFactory;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.FetchType;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.IndexBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.PackageBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryParameterBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.RelationFieldBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.UniqueConstraintBuilder;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;

public class BusinessDataModelConverter {

    private BusinessDataModelFactory factory = BusinessDataModelFactory.eINSTANCE;

    public BusinessObjectModel toEmfModel(org.bonitasoft.engine.bdm.model.BusinessObjectModel engineBusinessObjectModel,
            BDMArtifactDescriptor artifactDescriptor) {
        BusinessObjectModel emfBusinessObjectModel = factory.createBusinessObjectModel();
        emfBusinessObjectModel.setGroupId(artifactDescriptor.getGroupId());

        PackageHelper.getAllPackages(engineBusinessObjectModel)
                .stream()
                .map(packageName -> new PackageBuilder().withName(packageName).create())
                .forEach(emfBusinessObjectModel.getPackages()::add);
        engineBusinessObjectModel.getBusinessObjects().stream()
                .forEach(businessObject -> {
                    String packageName = PackageHelper.getPackageName(businessObject);
                    Package pakage = emfBusinessObjectModel.getPackages().stream()
                            .filter(p -> Objects.equals(p.getName(), packageName))
                            .findFirst().orElseThrow(() -> new RuntimeException(
                                    String.format("package %s is missing in emf model", packageName)));
                    pakage.getBusinessObjects().add(convertBusinessObject(businessObject));
                });

        emfBusinessObjectModel.getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .map(BusinessObject::getFields)
                .flatMap(Collection::stream)
                .filter(RelationField.class::isInstance)
                .map(RelationField.class::cast)
                .forEach(field -> findParentReference(field, emfBusinessObjectModel));
        return emfBusinessObjectModel;
    }

    public org.bonitasoft.engine.bdm.model.BusinessObjectModel toEngineModel(BusinessObjectModel emfBusinessObjectModel) {
        org.bonitasoft.engine.bdm.model.BusinessObjectModel engineBusinessObjectModel = new org.bonitasoft.engine.bdm.model.BusinessObjectModel();
        emfBusinessObjectModel.getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .map(this::convertBusinessObject)
                .forEach(engineBusinessObjectModel.getBusinessObjects()::add);

        engineBusinessObjectModel.getBusinessObjects().stream()
                .map(org.bonitasoft.engine.bdm.model.BusinessObject::getFields)
                .flatMap(Collection::stream)
                .filter(org.bonitasoft.engine.bdm.model.field.RelationField.class::isInstance)
                .map(org.bonitasoft.engine.bdm.model.field.RelationField.class::cast)
                .forEach(field -> findParentReference(field, engineBusinessObjectModel));
        return engineBusinessObjectModel;
    }

    private void findParentReference(RelationField field, BusinessObjectModel emfBusinessObjectModel) {
        emfBusinessObjectModel.getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .filter(bo -> Objects.equals(bo.getQualifiedName(), field.getReference().getQualifiedName()))
                .findFirst()
                .ifPresent(reference -> field.setReference(reference));
    }

    private void findParentReference(org.bonitasoft.engine.bdm.model.field.RelationField field,
            org.bonitasoft.engine.bdm.model.BusinessObjectModel engineBusinessObjectModel) {
        org.bonitasoft.engine.bdm.model.BusinessObject parentReference = engineBusinessObjectModel.getBusinessObjects()
                .stream()
                .filter(bo -> Objects.equals(bo.getQualifiedName(), field.getReference().getQualifiedName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Unable to find parent reference of the composed attribute %s", field.getName())));
        field.setReference(parentReference);
    }

    public BusinessObject convertBusinessObject(org.bonitasoft.engine.bdm.model.BusinessObject businessObjectToConvert) {
        BusinessObject businessObject = new BusinessObjectBuilder()
                .withQualifiedName(businessObjectToConvert.getQualifiedName())
                .withdescription(businessObjectToConvert.getDescription())
                .create();

        businessObjectToConvert.getFields().stream()
                .map(this::convertField)
                .forEach(businessObject.getFields()::add);

        businessObjectToConvert.getIndexes().stream()
                .map(this::convertIndex)
                .forEach(businessObject.getIndexes()::add);

        businessObjectToConvert.getQueries().stream()
                .map(this::convertQuery)
                .forEach(businessObject.getQueries()::add);

        businessObjectToConvert.getUniqueConstraints().stream()
                .map(this::convertUniqueConstraint)
                .forEach(businessObject.getUniqueConstraints()::add);

        createDefaultQueries(businessObjectToConvert)
                .forEach(businessObject.getDefaultQueries()::add);

        return businessObject;
    }

    private Stream<Query> createDefaultQueries(org.bonitasoft.engine.bdm.model.BusinessObject businessObject) {
        return BDMQueryUtil.createProvidedQueriesForBusinessObject(consistentBusinessObject(businessObject))
                .stream()
                .map(this::convertQuery);
    }

    public Stream<Query> createDefaultQueries(BusinessObject businessObject) {
        return createDefaultQueries(convertBusinessObject(businessObject));
    }

    private org.bonitasoft.engine.bdm.model.BusinessObject consistentBusinessObject(
            org.bonitasoft.engine.bdm.model.BusinessObject businessObject) {
        final org.bonitasoft.engine.bdm.model.BusinessObject result = new org.bonitasoft.engine.bdm.model.BusinessObject();
        result.setQualifiedName(businessObject.getQualifiedName());
        result.getFields().addAll(new ArrayList<>(businessObject.getFields()));
        businessObject.getUniqueConstraints().stream()
                .filter(uc -> !uc.getFieldNames().isEmpty())
                .forEach(result.getUniqueConstraints()::add);
        return result;
    }

    public org.bonitasoft.engine.bdm.model.BusinessObject convertBusinessObject(BusinessObject businessObjectToConvert) {
        org.bonitasoft.engine.bdm.model.BusinessObject businessObject = new org.bonitasoft.engine.bdm.model.BusinessObject(
                businessObjectToConvert.getQualifiedName());
        businessObject.setDescription(businessObjectToConvert.getDescription());

        businessObjectToConvert.getFields().stream()
                .map(this::convertField)
                .forEach(businessObject.getFields()::add);

        businessObjectToConvert.getIndexes().stream()
                .map(this::convertIndex)
                .forEach(businessObject.getIndexes()::add);

        businessObjectToConvert.getQueries().stream()
                .map(this::convertQuery)
                .forEach(businessObject.getQueries()::add);

        businessObjectToConvert.getUniqueConstraints().stream()
                .map(this::convertUniqueConstraint)
                .forEach(businessObject.getUniqueConstraints()::add);

        return businessObject;
    }

    private Field convertField(org.bonitasoft.engine.bdm.model.field.Field field) {
        if (field instanceof org.bonitasoft.engine.bdm.model.field.SimpleField) {
            return convertSimpleField((org.bonitasoft.engine.bdm.model.field.SimpleField) field);
        }
        return convertRelationField((org.bonitasoft.engine.bdm.model.field.RelationField) field);
    }

    private org.bonitasoft.engine.bdm.model.field.Field convertField(Field field) {
        if (field instanceof SimpleField) {
            return convertSimpleField((SimpleField) field);
        }
        return convertRelationField((RelationField) field);
    }

    private RelationField convertRelationField(org.bonitasoft.engine.bdm.model.field.RelationField field) {
        RelationField relationField = new RelationFieldBuilder()
                .withName(field.getName())
                .withDescription(field.getDescription())
                .withNullable(field.isNullable())
                .withCollection(field.isCollection())
                .withReference(new BusinessObjectBuilder() // replaced with the real reference when all the business objects are created
                        .withQualifiedName(field.getReference().getQualifiedName())
                        .create())
                .withType(convertRelationType(field.getType()))
                .withFetchType(convertFetchType(field.getFetchType()))
                .create();
        return relationField;
    }

    private org.bonitasoft.engine.bdm.model.field.RelationField convertRelationField(RelationField field) {
        org.bonitasoft.engine.bdm.model.field.RelationField relationField = new org.bonitasoft.engine.bdm.model.field.RelationField();
        relationField.setName(field.getName());
        relationField.setDescription(field.getDescription());
        relationField.setNullable(field.isNullable());
        relationField.setCollection(field.isCollection());
        org.bonitasoft.engine.bdm.model.BusinessObject tmpReference = new org.bonitasoft.engine.bdm.model.BusinessObject(
                field.getReference().getQualifiedName());
        relationField.setReference(tmpReference); // replaced with the real reference when all the business objects are created
        relationField.setType(convertRelationType(field.getType()));
        relationField.setFetchType(convertFetchType(field.getFetchType()));

        return relationField;
    }

    private FetchType convertFetchType(org.bonitasoft.engine.bdm.model.field.RelationField.FetchType fetchType) {
        return Objects.equals(fetchType, org.bonitasoft.engine.bdm.model.field.RelationField.FetchType.LAZY)
                ? FetchType.LAZY
                : FetchType.EAGER;
    }

    private org.bonitasoft.engine.bdm.model.field.RelationField.FetchType convertFetchType(FetchType fetchType) {
        return Objects.equals(fetchType, FetchType.LAZY)
                ? org.bonitasoft.engine.bdm.model.field.RelationField.FetchType.LAZY
                : org.bonitasoft.engine.bdm.model.field.RelationField.FetchType.EAGER;
    }

    private RelationType convertRelationType(Type type) {
        return Objects.equals(type, Type.AGGREGATION)
                ? RelationType.AGGREGATION
                : RelationType.COMPOSITION;
    }

    private Type convertRelationType(RelationType type) {
        return Objects.equals(type, RelationType.AGGREGATION)
                ? Type.AGGREGATION
                : Type.COMPOSITION;
    }

    private SimpleField convertSimpleField(org.bonitasoft.engine.bdm.model.field.SimpleField field) {
        SimpleField simpleField = new SimpleFieldBuilder()
                .withName(field.getName())
                .withDescription(field.getDescription())
                .withLength(field.getLength())
                .withCollection(field.isCollection())
                .withNullable(field.isNullable())
                .withType(convertFieldType(field.getType()))
                .create();
        return simpleField;
    }

    private org.bonitasoft.engine.bdm.model.field.SimpleField convertSimpleField(SimpleField field) {
        org.bonitasoft.engine.bdm.model.field.SimpleField simpleField = new org.bonitasoft.engine.bdm.model.field.SimpleField();
        simpleField.setName(field.getName());
        simpleField.setDescription(field.getDescription());
        simpleField.setLength(field.getLength());
        simpleField.setCollection(field.isCollection());
        simpleField.setNullable(field.isNullable());
        simpleField.setType(convertFieldType(field.getType()));
        return simpleField;
    }

    private FieldType convertFieldType(org.bonitasoft.engine.bdm.model.field.FieldType type) {
        switch (type) {
            case BOOLEAN:
                return FieldType.BOOLEAN;
            case BYTE:
                return FieldType.BYTE;
            case CHAR:
                return FieldType.CHAR;
            case DATE:
                return FieldType.DATE;
            case DOUBLE:
                return FieldType.DOUBLE;
            case FLOAT:
                return FieldType.FLOAT;
            case INTEGER:
                return FieldType.INTEGER;
            case LOCALDATE:
                return FieldType.LOCALDATE;
            case LOCALDATETIME:
                return FieldType.LOCALDATETIME;
            case LONG:
                return FieldType.LONG;
            case OFFSETDATETIME:
                return FieldType.OFFSETDATETIME;
            case SHORT:
                return FieldType.SHORT;
            case STRING:
                return FieldType.STRING;
            case TEXT:
            default:
                return FieldType.TEXT;
        }
    }

    public org.bonitasoft.engine.bdm.model.field.FieldType convertFieldType(FieldType type) {
        switch (type) {
            case BOOLEAN:
                return org.bonitasoft.engine.bdm.model.field.FieldType.BOOLEAN;
            case BYTE:
                return org.bonitasoft.engine.bdm.model.field.FieldType.BYTE;
            case CHAR:
                return org.bonitasoft.engine.bdm.model.field.FieldType.CHAR;
            case DATE:
                return org.bonitasoft.engine.bdm.model.field.FieldType.DATE;
            case DOUBLE:
                return org.bonitasoft.engine.bdm.model.field.FieldType.DOUBLE;
            case FLOAT:
                return org.bonitasoft.engine.bdm.model.field.FieldType.FLOAT;
            case INTEGER:
                return org.bonitasoft.engine.bdm.model.field.FieldType.INTEGER;
            case LOCALDATE:
                return org.bonitasoft.engine.bdm.model.field.FieldType.LOCALDATE;
            case LOCALDATETIME:
                return org.bonitasoft.engine.bdm.model.field.FieldType.LOCALDATETIME;
            case LONG:
                return org.bonitasoft.engine.bdm.model.field.FieldType.LONG;
            case OFFSETDATETIME:
                return org.bonitasoft.engine.bdm.model.field.FieldType.OFFSETDATETIME;
            case SHORT:
                return org.bonitasoft.engine.bdm.model.field.FieldType.SHORT;
            case STRING:
                return org.bonitasoft.engine.bdm.model.field.FieldType.STRING;
            case TEXT:
            default:
                return org.bonitasoft.engine.bdm.model.field.FieldType.TEXT;
        }
    }

    private Index convertIndex(org.bonitasoft.engine.bdm.model.Index index) {
        Index indx = new IndexBuilder()
                .withName(index.getName())
                .withDescription(index.getDescription())
                .withFieldNames(index.getFieldNames().toArray(new String[0]))
                .create();
        return indx;
    }

    private org.bonitasoft.engine.bdm.model.Index convertIndex(Index index) {
        org.bonitasoft.engine.bdm.model.Index indx = new org.bonitasoft.engine.bdm.model.Index();
        indx.setName(index.getName());
        indx.setDescription(index.getDescription());
        indx.setFieldNames(index.getFieldNames());
        return indx;
    }

    private Query convertQuery(org.bonitasoft.engine.bdm.model.Query query) {
        Query qury = new QueryBuilder()
                .withName(query.getName())
                .withDescription(query.getDescription())
                .withContent(query.getContent())
                .withReturnType(query.getReturnType())
                .create();
        query.getQueryParameters().stream().map(this::convertQueryParameter).forEach(qury.getQueryParameters()::add);
        return qury;
    }

    private org.bonitasoft.engine.bdm.model.Query convertQuery(Query query) {
        org.bonitasoft.engine.bdm.model.Query qury = new org.bonitasoft.engine.bdm.model.Query(query.getName(),
                query.getContent(), query.getReturnType());
        qury.setDescription(query.getDescription());
        query.getQueryParameters().stream().map(this::convertQueryParameter).forEach(qury.getQueryParameters()::add);
        return qury;
    }

    private QueryParameter convertQueryParameter(org.bonitasoft.engine.bdm.model.QueryParameter queryParameter) {
        QueryParameter quryParameter = new QueryParameterBuilder()
                .withName(queryParameter.getName())
                .withDescription(queryParameter.getDescription())
                .withClassName(queryParameter.getClassName())
                .create();
        return quryParameter;
    }

    private org.bonitasoft.engine.bdm.model.QueryParameter convertQueryParameter(QueryParameter queryParameter) {
        org.bonitasoft.engine.bdm.model.QueryParameter quryParameter = new org.bonitasoft.engine.bdm.model.QueryParameter(
                queryParameter.getName(), queryParameter.getClassName());
        quryParameter.setDescription(queryParameter.getDescription());
        return quryParameter;
    }

    private UniqueConstraint convertUniqueConstraint(org.bonitasoft.engine.bdm.model.UniqueConstraint uniqueConstraint) {
        UniqueConstraint constraint = new UniqueConstraintBuilder()
                .withName(uniqueConstraint.getName())
                .withDescription(uniqueConstraint.getDescription())
                .withFieldNames(uniqueConstraint.getFieldNames().toArray(new String[0]))
                .create();
        return constraint;
    }

    private org.bonitasoft.engine.bdm.model.UniqueConstraint convertUniqueConstraint(UniqueConstraint uniqueConstraint) {
        org.bonitasoft.engine.bdm.model.UniqueConstraint constraint = new org.bonitasoft.engine.bdm.model.UniqueConstraint();
        constraint.setName(uniqueConstraint.getName());
        constraint.setDescription(uniqueConstraint.getDescription());
        constraint.setFieldNames(uniqueConstraint.getFieldNames());
        return constraint;
    }

}
