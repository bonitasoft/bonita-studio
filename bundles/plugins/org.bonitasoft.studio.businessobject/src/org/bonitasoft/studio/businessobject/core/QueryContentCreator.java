/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.core;

import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.studio.businessobject.converter.BusinessDataModelConverter;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryParameterBuilder;

public class QueryContentCreator {

    private static final String AND = "\nAND ";

    private BusinessDataModelConverter converter;

    public QueryContentCreator() {
        this.converter = new BusinessDataModelConverter();
    }

    public void createQueryContent(BusinessObject businessObject, Query query) {
        String queryJPQL = createJPQL(businessObject);
        query.setContent(queryJPQL);
        if (query.getQueryParameters().isEmpty()) {
            businessObject.getFields().stream()
                    .filter(SimpleField.class::isInstance)
                    .map(SimpleField.class::cast)
                    .map(field -> new QueryParameterBuilder()
                            .withName(field.getName())
                            .withClassName(converter.convertFieldType(field.getType()).getClazz().getName())
                            .create())
                    .forEach(query.getQueryParameters()::add);
        }
    }

    private String createJPQL(BusinessObject businessObject) {
        String boName = businessObject.getSimpleName();
        char var = Character.toLowerCase(boName.charAt(0));
        final StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(var);
        sb.append(" \n");
        sb.append("FROM ");
        sb.append(boName);
        sb.append(" ");
        sb.append(var);
        sb.append(" \n");
        sb.append("WHERE ");

        businessObject.getFields().stream()
                .filter(SimpleField.class::isInstance)
                .forEach(field -> {
                    if (field.isCollection()) {
                        sb.append(":");
                        sb.append(field.getName());
                        sb.append(" IN ELEMENTS(");
                        sb.append(var);
                        sb.append(".");
                        sb.append(field.getName());
                        sb.append(")");
                    } else {
                        sb.append(var);
                        sb.append(".");
                        sb.append(field.getName());
                        sb.append(" = :");
                        sb.append(field.getName());
                    }
                    sb.append(AND);
                });
        String query = sb.toString();
        if (query.endsWith(AND)) {
            query = query.substring(0, query.lastIndexOf(AND));
        }
        StringBuilder sb2 = new StringBuilder(query);
        sb2.append("\nORDER BY ");
        sb2.append(var);
        sb2.append(".");
        sb2.append(Field.PERSISTENCE_ID);
        sb2.append(" ASC");
        return sb2.toString();
    }

}
