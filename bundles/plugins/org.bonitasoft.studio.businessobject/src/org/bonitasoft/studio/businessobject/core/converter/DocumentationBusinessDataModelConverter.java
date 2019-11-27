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
import org.bonitasoft.asciidoc.templating.model.bdm.Package;
import org.bonitasoft.asciidoc.templating.model.bdm.Query;
import org.bonitasoft.asciidoc.templating.model.bdm.QueryParameter;
import org.bonitasoft.asciidoc.templating.model.bdm.Relation;
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
        BusinessDataModel bdm = new BusinessDataModel();
        bdm.setPackages(businessDataModel.getBusinessObjects().stream()
                .map(PackageHelper::getPackageName)
                .distinct()
                .map(Package::new)
                .peek(pckg -> addBusinessObjectToPackage(pckg, businessDataModel))
                .toArray(Package[]::new));
        return bdm;
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
                .build();
    }

    private Query[] convertQueries(List<org.bonitasoft.engine.bdm.model.Query> queries, BusinessObject object) {
        return queries.stream()
                .map(q -> new Query(q.getName(), null, convertToSimpleType(q.getReturnType(),object), q.getContent(),
                        convertQueryParameters(q, object)))
                .toArray(Query[]::new);
    }

    private QueryParameter[] convertQueryParameters(org.bonitasoft.engine.bdm.model.Query q, BusinessObject object) {
        List<QueryParameter> parameters = q.getQueryParameters().stream()
                .map(p -> new QueryParameter(p.getName(), convertToSimpleType(p.getClassName(), object)))
                .collect(Collectors.toList());
        if (q.hasMultipleResults()) {
            parameters.add(new QueryParameter("startIndex", "int"));
            parameters.add(new QueryParameter("maxResults", "int"));
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
                .map(f -> new Attribute(f.getName(), null,
                        f.isCollection() ? String.format("List<%s>", f.getType().getClazz().getSimpleName())
                                : f.getType().getClazz().getSimpleName(),
                        !f.isNullable()))
                .collect(Collectors.toList());
        attributes.add(0, new Attribute(Field.PERSISTENCE_ID, null, Long.class.getSimpleName(), true));
        return attributes.stream().toArray(Attribute[]::new);
    }

    private Relation[] convertRelations(BusinessObject businessObject) {
        return businessObject.getFields().stream()
                .filter(RelationField.class::isInstance)
                .map(RelationField.class::cast)
                .map(f -> new Relation(f.getName(), null, f.getReference().getSimpleName(), f.getType().toString(), !f.isNullable(), f.isLazy(), f.isCollection()))
                .toArray(Relation[]::new);
    }

}
