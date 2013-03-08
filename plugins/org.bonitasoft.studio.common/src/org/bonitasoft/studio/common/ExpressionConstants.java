/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common;

import org.bonitasoft.engine.bpm.model.document.DocumentValue;
import org.bonitasoft.engine.core.operation.OperatorType;
import org.bonitasoft.engine.expression.ExpressionInterpreter;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.forms.client.model.ActionType;

/**
 * @author Romain Bioteau
 *
 */
public interface ExpressionConstants {

    String CONSTANT_TYPE = ExpressionType.TYPE_CONSTANT.name() ;
    String CONDITION_TYPE = ExpressionType.TYPE_CONDITION.name();
    String SCRIPT_TYPE = ExpressionType.TYPE_READ_ONLY_SCRIPT.name();
    String VARIABLE_TYPE = ExpressionType.TYPE_VARIABLE.name() ;
    String PARAMETER_TYPE = ExpressionType.TYPE_PARAMETER.name() ;
    String I18N_TYPE = ExpressionType.TYPE_I18N.name() ;
    String FORM_FIELD_TYPE = ExpressionType.TYPE_INPUT.name() ;
    String ENGINE_CONSTANT_TYPE = ExpressionType.TYPE_ENGINE_CONSTANT.name();
    String CONNECTOR_OUTPUT_TYPE = "CONNECTOR_OUTPUT_TYPE";
    String MESSAGE_ID_TYPE = "MESSAGE_ID_TYPE";
    String SEARCH_INDEX_TYPE="SEARCH_INDEX_TYPE";

    String CONNECTOR_TYPE = "CONNECTOR_TYPE" ;
    String XPATH_TYPE = "XPATH_TYPE";
    String JAVA_TYPE = "JAVA_TYPE";

    String ASSIGNMENT_OPERATOR = OperatorType.ASSIGNMENT.name() ;
    String SEARCH_INDEX_OPERATOR = OperatorType.STRING_INDEX.name();
    String JAVA_METHOD_OPERATOR = OperatorType.JAVA_METHOD.name() ;
    String XPATH_UPDATE_OPERATOR = OperatorType.XPATH_UPDATE_QUERY.name() ;
    
    String GROOVY = ExpressionInterpreter.GROOVY.name();
    String SIMULATION_VARIABLE_TYPE = "SIMULATION_VARIABLE_TYPE";
    String DOCUMENT_TYPE = ExpressionType.TYPE_DOCUMENT.name();
    String PATTERN_TYPE = ExpressionType.TYPE_PATTERN.name();
    String SET_DOCUMENT_OPERATOR = ActionType.DOCUMENT_CREATE_UPDATE.name();
    String VARIABLE_TYPE_FOR_FORM_OUPUT = "VARIABLE_TYPE_FOR_FORM_OUPUT";
    String DOCUMENT_VALUE_RETURN_TYPE = DocumentValue.class.getName();
   

  
}
