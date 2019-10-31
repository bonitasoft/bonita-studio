/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.common;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.engine.expression.ExpressionInterpreter;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.engine.operation.LeftOperand;
import org.bonitasoft.engine.operation.OperatorType;

/**
 * @author Romain Bioteau
 */
public interface ExpressionConstants {

    /*
     * constants in type attribute of expressions
     */
    String CONSTANT_TYPE = ExpressionType.TYPE_CONSTANT.name();

    String CONDITION_TYPE = ExpressionType.TYPE_CONDITION.name();

    String SCRIPT_TYPE = ExpressionType.TYPE_READ_ONLY_SCRIPT.name();

    String VARIABLE_TYPE = ExpressionType.TYPE_VARIABLE.name();

    String TRANSIENT_VARIABLE_TYPE = ExpressionType.TYPE_TRANSIENT_VARIABLE.name();

    String PARAMETER_TYPE = ExpressionType.TYPE_PARAMETER.name();

    String ENGINE_CONSTANT_TYPE = ExpressionType.TYPE_ENGINE_CONSTANT.name();

    String CONNECTOR_OUTPUT_TYPE = "CONNECTOR_OUTPUT_TYPE";

    String MESSAGE_ID_TYPE = "MESSAGE_ID_TYPE";

    String SEARCH_INDEX_TYPE = "SEARCH_INDEX_TYPE";

    String BUSINESS_DATA_TYPE = ExpressionType.TYPE_BUSINESS_DATA.name();

    String CONTRACT_INPUT_TYPE = ExpressionType.TYPE_CONTRACT_INPUT.name();

    String CONNECTOR_TYPE = "CONNECTOR_TYPE";

    String XPATH_TYPE = ExpressionType.TYPE_XPATH_READ.name();

    String JAVA_TYPE = ExpressionType.TYPE_JAVA_METHOD_CALL.name();
    String DOCUMENT_LIST_TYPE = ExpressionType.TYPE_DOCUMENT_LIST.name();

    String ASSIGNMENT_OPERATOR = OperatorType.ASSIGNMENT.name();

    String JAVA_METHOD_OPERATOR = OperatorType.JAVA_METHOD.name();

    String DELETION_OPERATOR = OperatorType.DELETION.name();

    String XPATH_UPDATE_OPERATOR = OperatorType.XPATH_UPDATE_QUERY.name();

    String GROOVY = ExpressionInterpreter.GROOVY.name();

    String DOCUMENT_TYPE = ExpressionType.TYPE_DOCUMENT.name();

    String PATTERN_TYPE = ExpressionType.TYPE_PATTERN.name();

    String SET_DOCUMENT_OPERATOR = "DOCUMENT_CREATE_UPDATE";
    String SET_LIST_DOCUMENT_OPERATOR = "DOCUMENT_LIST_SET";

    String VARIABLE_TYPE_FOR_FORM_OUPUT = "VARIABLE_TYPE_FOR_FORM_OUPUT";

    String DOCUMENT_VALUE_RETURN_TYPE = DocumentValue.class.getName();

    String DOCUMENT_REF_TYPE = "DOCUMENT_REF_TYPE";

    String ALL_TYPES = "ALL_TYPES";

    String MULTIINSTANCE_ITERATOR_TYPE = "MULTIINSTANCE_ITERATOR_TYPE";

    String QUERY_TYPE = ExpressionType.TYPE_QUERY_BUSINESS_DATA.name();

    String DAO_TYPE = ExpressionType.TYPE_BUSINESS_OBJECT_DAO.name();

    // left operand types:
    String LEFT_OPERAND_DATA = LeftOperand.TYPE_DATA;
    String LEFT_OPERAND_SEARCH_INDEX = LeftOperand.TYPE_SEARCH_INDEX;
    String LEFT_OPERAND_DOCUMENT = LeftOperand.TYPE_DOCUMENT;
    String LEFT_OPERAND_DOCUMENT_LIST = LeftOperand.TYPE_DOCUMENT_LIST;
    String LEFT_OPERAND_EXTERNAL_DATA = LeftOperand.TYPE_EXTERNAL_DATA;
    String LEFT_OPERAND_TRANSIENT_DATA = LeftOperand.TYPE_TRANSIENT_DATA;
    String LEFT_OPERAND_BUSINESS_DATA = LeftOperand.TYPE_BUSINESS_DATA;

    /*
     * It is an assignment to a left operand that is a a business data (business data do not exists exists)
     */
    String CREATE_BUSINESS_DATA_OPERATOR = "CREATE_BUSINESS_DATA";

    /*
     * It is an java operation to a left operand that is a a business data
     */
    String BUSINESS_DATA_JAVA_SETTER_OPERATOR = "BUSINESS_DATA_JAVA_SETTER";

    /*
     * It is an assignment to a left operand that is a a business data (business data already exists)
     */
    String ATTACH_EXISTING_BUSINESS_DATA = "ATTACH_EXISTING_BUSINESS_DATA";

    String FORM_REFERENCE_TYPE = "FORM_REFERENCE_TYPE";

}
