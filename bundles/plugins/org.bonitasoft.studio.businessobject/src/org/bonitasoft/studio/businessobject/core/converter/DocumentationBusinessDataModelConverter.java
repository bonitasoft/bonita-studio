/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.core.converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.asciidoc.templating.model.bdm.Attribute;
import org.bonitasoft.asciidoc.templating.model.bdm.BusinessDataModel;
import org.bonitasoft.asciidoc.templating.model.bdm.Index;
import org.bonitasoft.asciidoc.templating.model.bdm.Package;
import org.bonitasoft.asciidoc.templating.model.bdm.Query;
import org.bonitasoft.asciidoc.templating.model.bdm.QueryParameter;
import org.bonitasoft.asciidoc.templating.model.bdm.Relation;
import org.bonitasoft.asciidoc.templating.model.bdm.UniqueConstraint;
import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.common.NamingUtils;

public class DocumentationBusinessDataModelConverter {

    public BusinessDataModel toDocumentationBusinessdataModel(BusinessObjectModel businessDataModel) {
        return BusinessDataModel.builder()
                .packages(businessDataModel.getBusinessObjects().stream()
                        .map(PackageHelper::getPackageName)
                        .distinct()
                        .map(Package::new)
                        .peek(pckg -> addBusinessObjectToPackage(pckg, businessDataModel))
                        .toArray(Package[]::new))
                .build();
    }

    private void addBusinessObjectToPackage(Package pckg, BusinessObjectModel bom) {
        pckg.setBusinessObjects(PackageHelper.getAllBusinessObjects(bom, pckg.getName()).stream()
                .map(this::toDocumentationBusinessObject)
                .toArray(org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject[]::new));
    }

    private org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject toDocumentationBusinessObject(
            BusinessObject businessObject) {
        return new org.bonitasoft.asciidoc.templating.model.bdm.BusinessObject.BusinessObjectBuilder()
                .name(businessObject.getSimpleName())
                .packageName(PackageHelper.getPackageName(businessObject))
                .description(businessObject.getDescription())
                .attributes(convertAttributes(businessObject))
                .relations(convertRelations(businessObject))
                .customQueries(convertQueries(businessObject.getQueries(), businessObject))
                .defaultQueries(convertQueries(BDMQueryUtil.createProvidedQueriesForBusinessObject(businessObject),
                        businessObject))
                .uniqueConstraints(convertUniqueConstraints(businessObject.getUniqueConstraints()))
                .indexes(convertIndexes(businessObject.getIndexes()))
                .build();
    }

    private UniqueConstraint[] convertUniqueConstraints(
            List<org.bonitasoft.engine.bdm.model.UniqueConstraint> uniqueConstraints) {
        return uniqueConstraints.stream()
                .map(c -> UniqueConstraint.builder()
                        .name(c.getName())
                        .description(c.getDescription())
                        .attributes(c.getFieldNames().toArray(new String[] {}))
                        .build())
                .toArray(UniqueConstraint[]::new);
    }

    private Index[] convertIndexes(
            List<org.bonitasoft.engine.bdm.model.Index> indexes) {
        return indexes.stream()
                .map(index -> Index.builder()
                        .name(index.getName())
                        .description(index.getDescription())
                        .attributes(index.getFieldNames().toArray(new String[] {}))
                        .build())
                .toArray(Index[]::new);
    }

    private Query[] convertQueries(List<org.bonitasoft.engine.bdm.model.Query> queries, BusinessObject object) {
        return queries.stream()
                .map(q -> Query.builder().name(q.getName())
                        .description(q.getDescription())
                        .returnType(convertToSimpleType(q.getReturnType(), object))
                        .sourceCode(q.getContent())
                        .parameters(convertQueryParameters(q, object))
                        .build())
                .toArray(Query[]::new);
    }

    private QueryParameter[] convertQueryParameters(org.bonitasoft.engine.bdm.model.Query q, BusinessObject object) {
        List<QueryParameter> parameters = q.getQueryParameters().stream()
                .map(p -> QueryParameter.builder()
                        .name(p.getName())
                        .description(p.getDescription())
                        .type(convertToSimpleType(p.getClassName(), object))
                        .build())
                .collect(Collectors.toList());
        if (q.hasMultipleResults()) {
            parameters.add(QueryParameter.builder().name("startIndex").type("int").build());
            parameters.add(QueryParameter.builder().name("maxResults").type("int").build());
        }
        return parameters.stream().toArray(QueryParameter[]::new);
    }

    private String convertToSimpleType(String className, BusinessObject object) {
        if (Objects.equals(List.class.getName(), className)) {
            return String.format("List<%s>", object.getSimpleName());
        }
        return NamingUtils.getSimpleName(className);
    }

    private Attribute[] convertAttributes(BusinessObject businessObject) {
        List<Attribute> attributes = businessObject.getFields().stream()
                .filter(SimpleField.class::isInstance)
                .map(SimpleField.class::cast)
                .map(f -> Attribute.builder()
                        .name(f.getName())
                        .description(f.getDescription())
                        .type(f.getType().getClazz().getSimpleName())
                        .multiple(f.isCollection())
                        .mandatory(!f.isNullable())
                        .build())
                .collect(Collectors.toList());
        attributes.add(0, Attribute.builder()
                .name(Field.PERSISTENCE_ID)
                .type(Long.class.getSimpleName())
                .mandatory(true)
                .build());
        return attributes.stream().toArray(Attribute[]::new);
    }

    private Relation[] convertRelations(BusinessObject businessObject) {
        return businessObject.getFields().stream()
                .filter(RelationField.class::isInstance)
                .map(RelationField.class::cast)
                .map(f -> Relation.builder()
                        .name(f.getName())
                        .description(f.getDescription())
                        .type(f.getReference().getSimpleName())
                        .relationType(f.getType().toString())
                        .mandatory(!f.isNullable())
                        .multiple(f.isCollection())
                        .lazy(f.isLazy())
                        .build())
                .toArray(Relation[]::new);
    }

}
