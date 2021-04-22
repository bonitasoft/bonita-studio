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
package org.bonitasoft.studio.businessobject.core.status;

import java.util.Arrays;
import java.util.List;

import org.bonitasoft.engine.api.result.Status;
import org.bonitasoft.engine.api.result.StatusContext;
import org.bonitasoft.engine.bdm.BDMQueryUtil;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.ui.dialog.EngineStatusMapper;

public class BusinessDataModelStatusMapper extends EngineStatusMapper {

    private static BusinessDataModelStatusMapper INSTANCE;

    private static final List<String> FORBIDDEN_PARAMETER_NAMES = Arrays.asList(BDMQueryUtil.START_INDEX_PARAM_NAME,
            BDMQueryUtil.MAX_RESULTS_PARAM_NAME);

    public static BusinessDataModelStatusMapper instance() {
        if (INSTANCE == null) {
            INSTANCE = new BusinessDataModelStatusMapper();
        }
        return INSTANCE;
    }

    @Override
    public String localizedMessage(Status status) {
        StatusContext context = status.getContext();
        switch (status.getCode()) {
            case DUPLICATE_CONSTRAINT_OR_INDEX_NAME:
                return String.format(Messages.duplicateConstraintOrIndexName,
                        context.get(StatusContext.BDM_ARTIFACT_KEY),
                        context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case UNIQUE_CONSTRAINT_WITHOUT_NAME:
                return Messages.constraintWithoutName;
            case INVALID_SQL_IDENTIFIER_NAME:
                String sqlInvalidName = (String) context.get(StatusContext.BDM_ARTIFACT_NAME_KEY);
                if (sqlInvalidName == null) {
                    sqlInvalidName = (String) context.get(StatusContext.INVALID_NAME_KEY);
                }
                return String.format(Messages.invalidSqlIdentifier, sqlInvalidName);
            case UNIQUE_CONSTRAINT_WITHOUT_FIELD:
                return String.format(Messages.constraintWithoutField, context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case FIELD_WITHOUT_NAME:
                return String.format(Messages.fieldWithoutName, context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case QUERY_WITHOUT_NAME:
                return Messages.queryWithoutName;
            case INVALID_JAVA_IDENTIFIER_NAME:
                String javaInvalidname = (String) context.get(StatusContext.BUSINESS_OBJECT_NAME_KEY);
                if (javaInvalidname == null) {
                    javaInvalidname = (String) context.get(StatusContext.BDM_ARTIFACT_NAME_KEY);
                }
                return String.format(Messages.invalidJavaIdentifier, javaInvalidname);
            case QUERY_NAME_LENGTH_TO_HIGH:
                return String.format(Messages.queryNameLengthToHigh, context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case QUERY_WITHOUT_CONTENT:
                return String.format(Messages.queryWithoutContent, context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case QUERY_WITHOUT_RETURN_TYPE:
                return String.format(Messages.queryWithoutReturntype, context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case QUERY_PARAMETER_WITHOUT_NAME:
                return Messages.queryParameterWithoutName;
            case FORBIDDEN_QUERY_PARAMETER_NAME:
                return String.format(Messages.forbiddenQueryParameterName, context.get(StatusContext.BDM_ARTIFACT_NAME_KEY),
                        FORBIDDEN_PARAMETER_NAMES);
            case QUERY_PARAMETER_WITHOUT_CLASS_NAME:
                return String.format(Messages.queryParameterWithoutClassName,
                        context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case INDEX_WITHOUT_NAME:
                return Messages.indexWithoutName;
            case INDEX_WITHOUT_FIELD:
                return String.format(Messages.indexWithoutField, context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case INVALID_FIELD_IDENTIFIER:
                return String.format(Messages.invalidFieldIdentifier, context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case BUSINESS_OBJECT_WITHOUT_NAME:
                return Messages.businessObjectWithoutName;
            case RESERVED_PACKAGE_NAME:
                return String.format(Messages.reservedPackageName, context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case INVALID_CHARACTER_IN_BUSINESS_OBJECT_NAME:
                return String.format(Messages.invalidCharacterInBusinessObjectName, "_");
            case BUSINESS_OBJECT_WITHOUT_FIELD:
                return String.format(Messages.businessObjectWithoutField,
                        context.get(StatusContext.BUSINESS_OBJECT_NAME_KEY));
            case DUPLICATE_QUERY_NAME:
                return String.format(Messages.duplicateQueryName,
                        context.get(StatusContext.BDM_ARTIFACT_NAME_KEY),
                        context.get(StatusContext.BUSINESS_OBJECT_NAME_KEY));
            case DUPLICATE_CONSTRAINT_NAME:
                return String.format(Messages.duplicateConstraintName,
                        context.get(StatusContext.BDM_ARTIFACT_NAME_KEY),
                        context.get(StatusContext.BUSINESS_OBJECT_NAME_KEY));
            case UNKNOWN_FIELD_IN_CONSTRAINT:
                return String.format(Messages.unknownFieldInConstraint,
                        context.get(StatusContext.BDM_ARTIFACT_NAME_KEY),
                        context.get(StatusContext.BUSINESS_OBJECT_NAME_KEY));
            case EMPTY_BDM:
                return Messages.emptyBdm;
            case SEVERAL_COMPOSITION_REFERENCE_FOR_A_BUSINESS_OBJECT:
                return String.format(Messages.severalCompositionReferenceForABusinessObject,
                        context.get(StatusContext.BUSINESS_OBJECT_NAME_KEY));
            case CIRCULAR_COMPOSITION_REFERENCE:
                return String.format(Messages.circularCompositionReference,
                        context.get(StatusContext.BUSINESS_OBJECT_NAME_KEY),
                        context.get(StatusContext.BDM_ARTIFACT_NAME_KEY));
            case BUSINESS_OBJECT_USED_IN_COMPOSITION_AND_AGGREGATION:
                return String.format(Messages.boUsedInCompositionAndAggregation,
                        context.get(StatusContext.BUSINESS_OBJECT_NAME_KEY));
            case MULTIPLE_AGGREGATION_RELATION_TO_ITSELF:
                return String.format(Messages.multipleAggregationToItself,
                        context.get(StatusContext.BUSINESS_OBJECT_NAME_KEY));
            default:
                return super.localizedMessage(status);
        }
    }

}
