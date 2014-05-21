/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.groovy.ui.contentassist;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.bonitasoft.engine.expression.ExpressionConstants;
import org.bonitasoft.engine.expression.ExpressionConstantsResolver;
import org.bonitasoft.forms.server.api.IFormExpressionsAPI;
import org.bonitasoft.forms.server.validator.AbstractFormValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.codehaus.groovy.eclipse.editor.highlighting.IHighlightingExtender;
import org.eclipse.jface.text.rules.IRule;

/**
 * @author Romain Bioteau
 */
public class BonitaSyntaxHighlighting implements IHighlightingExtender {

    public static List<String> BONITA_KEYWORDS = new ArrayList<String>();

    static {
        BONITA_KEYWORDS.add(ExpressionConstants.API_ACCESSOR.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.ENGINE_EXECUTION_CONTEXT.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.NUMBER_OF_ACTIVE_INSTANCES.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.NUMBER_OF_TERMINATED_INSTANCES.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.NUMBER_OF_COMPLETED_INSTANCES.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.NUMBER_OF_INSTANCES.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.PROCESS_DEFINITION_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.ROOT_PROCESS_INSTANCE_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.PROCESS_INSTANCE_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.ACTIVITY_INSTANCE_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.LOOP_COUNTER.getEngineConstantName());
        BONITA_KEYWORDS.add(ExpressionConstants.LOGGED_USER_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(AbstractFormValidator.CLICKED_BUTTON_VARNAME);
        BONITA_KEYWORDS.add(ExpressionConstants.TASK_ASSIGNEE_ID.getEngineConstantName());
        BONITA_KEYWORDS.add(IFormExpressionsAPI.USER_LOCALE);
    }

    public BonitaSyntaxHighlighting() {
    }

    @Override
    public List<String> getAdditionalGJDKKeywords() {
        return BONITA_KEYWORDS;
    }

    @Override
    public List<String> getAdditionalGroovyKeywords() {
        return null;
    }

    @Override
    public List<IRule> getAdditionalRules() {
        return null;
    }

    public static Class<?> getTypeForKeyWord(final String keyWord) {
        if (keyWord != null) {
            final ExpressionConstants expressionConstantsFromName = ExpressionConstantsResolver.getExpressionConstantsFromName(keyWord);
            if (expressionConstantsFromName != null) {
                try {
                    return Class.forName(expressionConstantsFromName.getReturnType());
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            }else if(keyWord.equals(IFormExpressionsAPI.USER_LOCALE)){
            	return Locale.class;
            }else if(keyWord.equals(AbstractFormValidator.CLICKED_BUTTON_VARNAME)){
            	return String.class;
            }
        }
        return null;
    }

}
