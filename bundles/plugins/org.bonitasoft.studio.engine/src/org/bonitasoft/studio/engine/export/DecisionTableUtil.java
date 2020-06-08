/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.export;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.decision.DecisionTable;
import org.bonitasoft.studio.model.process.decision.DecisionTableAction;
import org.bonitasoft.studio.model.process.decision.DecisionTableLine;
import org.bonitasoft.studio.model.process.decision.transitions.TakeTransitionAction;
import org.eclipse.emf.ecore.EObject;

public class DecisionTableUtil {

    private static final String CONDITION_SEPARATOR = " && ";

    /**
     * @param decisionTable
     * @return
     */
    public static Expression toGroovyScriptExpression(final DecisionTable decisionTable) {
        StringBuilder builder = new StringBuilder();
        List<Object> listReferencedElements = new ArrayList<Object>();
        for (DecisionTableLine line : decisionTable.getLines()) {
            builder.append(" if (");
            for (Expression condition : line.getConditions()) {
                builder.append("(");
                builder.append(toGroovyCondition(condition));
                builder.append(")");
                builder.append(CONDITION_SEPARATOR);
                for(EObject dep : condition.getReferencedElements()){
                	listReferencedElements.add(ExpressionHelper.createDependencyFromEObject(dep));
                }
            }
            builder.delete(builder.length() - CONDITION_SEPARATOR.length(), builder.length());
            builder.append(") { ");
            builder.append(toGroovyAction(line.getAction()));
            builder.append(";" + "}");
            builder.append("\n");
        }
        builder.append(toGroovyAction(decisionTable.getDefaultAction()));
        builder.append(";");

        String script =  builder.toString() ;
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression() ;
        exp.setType(ExpressionConstants.SCRIPT_TYPE) ;
        exp.setInterpreter(ExpressionConstants.GROOVY);
        exp.setContent(script) ;
        exp.setName("Descision Table Script") ;
        exp.setReturnType(Boolean.class.getName()) ;
        
        // parsed existing elements referenced to do not have only once each reference
        for(Object ref : listReferencedElements){
        	if(ref instanceof Data){
        		Data data = (Data) ref;
        		String dataName = data.getName();
        		boolean addRef = true;
        		for(Object obj : exp.getReferencedElements()){
        			if(obj instanceof Data){
        				Data dataObj = (Data)obj;
        				String dataObjName = dataObj.getName();
        				if(dataObjName.equals(dataName)){
        					addRef = false;
        				}
        			}
        			if(obj instanceof Parameter){
        				Parameter parameterObj = (Parameter)obj;
        				String parameterObjName = parameterObj.getName();
        				if(parameterObjName.equals(dataName)){
        					addRef = false;
        				}
        			}
        		}
        		if(addRef){
        			exp.getReferencedElements().add((EObject)ref);
        		}
        	} else if(ref instanceof Parameter){
        		Parameter parameter = (Parameter) ref;
        		String parameterName = parameter.getName();
        		boolean addRef = true;
        		for(Object obj : exp.getReferencedElements()){
        			if(obj instanceof Data){
        				Data dataObj = (Data)obj;
        				String dataObjName = dataObj.getName();
        				if(dataObjName.equals(parameterName)){
        					addRef = false;
        				}
        			}
        			if(obj instanceof Parameter){
        				Parameter parameterObj = (Parameter)obj;
        				String parameterObjName = parameterObj.getName();
        				if(parameterObjName.equals(parameterName)){
        					addRef = false;
        				}
        			}
        		}
        		if(addRef){
        			exp.getReferencedElements().add((EObject)ref);
        		}
        	} else{
        		throw new RuntimeException();
        	}
        }
        return exp;
    }

    /**
     * @param defaultAction
     * @return
     */
    private static String toGroovyAction(final DecisionTableAction defaultAction) {
        if (defaultAction instanceof TakeTransitionAction) {
            return "return " + ((TakeTransitionAction)defaultAction).isTakeTransition();
        } else {
            return "WRONG_ACTION";
        }
    }

    /**
     * @param condition
     * @return
     */
    private static String toGroovyCondition(final Expression condition) {
        return condition.getContent() ;
    }

}
