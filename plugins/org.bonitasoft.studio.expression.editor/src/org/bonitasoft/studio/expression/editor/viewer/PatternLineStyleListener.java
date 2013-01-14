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
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Display;


/**
 * @author Romain Bioteau
 *
 */
public class PatternLineStyleListener implements LineStyleListener {

    private Set<Expression> expressions = new HashSet<Expression>();

    /* (non-Javadoc)
     * @see org.eclipse.swt.custom.LineStyleListener#lineGetStyle(org.eclipse.swt.custom.LineStyleEvent)
     */
    @Override
    public void lineGetStyle(LineStyleEvent event) {
        Vector<StyleRange> styles = new Vector<StyleRange>();

        addExpressionStyles( event,  styles, ExpressionConstants.VARIABLE_TYPE, SWT.COLOR_DARK_RED);
        addExpressionStyles( event,  styles, ExpressionConstants.PARAMETER_TYPE, SWT.COLOR_DARK_GREEN);
        addExpressionStyles( event,  styles, ExpressionConstants.FORM_FIELD_TYPE, SWT.COLOR_DARK_CYAN);
        event.styles = styles.toArray(new StyleRange[styles.size()]);
    }

    /**
     * 
     * @param event
     * @param styles
     * @param type
     * @param color
     */
    protected void addExpressionStyles(LineStyleEvent event, Vector<StyleRange> styles, String type, int color){
        for (Expression exp : expressions) {
            if(type.equals(exp.getType())){
                String content = event.lineText;
                int indexOf = content.indexOf(exp.getName());
                int offset = indexOf;
                while (indexOf != -1) {
                    if(isValidKeyWord(exp.getName(), content, indexOf)){
                        styles.add(new StyleRange(event.lineOffset + offset, exp.getName().length(), Display.getDefault().getSystemColor(color), null,SWT.BOLD));
                    }
                    content = content.substring(indexOf+exp.getName().length());
                    indexOf = content.indexOf(exp.getName());
                    offset = indexOf+exp.getName().length() + offset;
                }
            }
        }
    }
    

    public static boolean isValidKeyWord(String expName, String content, int indexOf) {
        return isStartingWord(indexOf) || isNotEscapeWord(content, indexOf);
    }

    protected static boolean isNextCharacterAWhiteSpace(String expName, String content, int indexOf) {
        return Character.isWhitespace(content.charAt(indexOf+expName.length()));
    }

    protected static boolean isFinishingWord(String expName, String content, int indexOf) {
        return indexOf+expName.length()+1 > content.length();
    }

    protected static boolean isPreviousCharacterAWhiteSpace(String content, int indexOf) {
        return Character.isWhitespace(content.charAt(indexOf-1));
    }

    protected static boolean isNotEscapeWord(String content, int indexOf) {
        return content.charAt(indexOf-1) != '\\';
    }

    protected static boolean isStartingWord(int indexOf) {
        return indexOf-1 < 0;
    }

    public void setExpressions(Set<Expression> expressions) {
        this.expressions = expressions ;
    }

}
