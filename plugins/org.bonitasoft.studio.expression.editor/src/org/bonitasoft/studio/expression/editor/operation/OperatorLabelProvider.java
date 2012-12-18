/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.operation;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.model.expression.Operator;
import org.eclipse.jface.viewers.LabelProvider;


/**
 * @author Romain Bioteau
 *
 */
public class OperatorLabelProvider extends LabelProvider {
    @Override
    public String getText(Object element) {
        if(element instanceof String){
            if(element.equals(ExpressionConstants.ASSIGNMENT_OPERATOR)){
                return Messages.assignment ;
            }else if(element.equals(ExpressionConstants.JAVA_METHOD_OPERATOR)){
                return Messages.javaMethodOperator ;
            }else if(element.equals(ExpressionConstants.XPATH_UPDATE_OPERATOR)){
                return Messages.xpathUpdateOperator ;
            }else if(element.equals(ExpressionConstants.SET_DOCUMENT_OPERATOR)){
                return Messages.setDocumentOperator ;
            }
        }else if(element instanceof Operator){
            Operator op = (Operator) element ;
            if(op.getType().equals(ExpressionConstants.ASSIGNMENT_OPERATOR)){
                return Messages.assignment ;
            }else if(op.getType().equals(ExpressionConstants.JAVA_METHOD_OPERATOR)){
                String methodName = op.getExpression() ;
                if(methodName.length() > 40){
                    methodName = methodName.substring(0,35) + "..." ;
                }
                return methodName ;
            }else if(op.getType().equals(ExpressionConstants.XPATH_UPDATE_OPERATOR)){
                String xpathExpression = op.getExpression() ;
                if(xpathExpression.length() > 40){
                    xpathExpression = xpathExpression.substring(0,35) + "..." ;
                }
                return xpathExpression ;
            }else if(op.getType().equals(ExpressionConstants.SET_DOCUMENT_OPERATOR)){
                return Messages.setDocumentOperator ;
            }
        }
        return super.getText(element);
    }

}
