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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
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

	protected Set<Expression> expressions = new HashSet<Expression>();
	protected FindReplaceDocumentAdapter finder;
	protected IDocument document;
	protected static Map<String,Integer> supportedTypes ;
	static{
		supportedTypes = new HashMap<String,Integer>();
		supportedTypes.put(ExpressionConstants.VARIABLE_TYPE, SWT.COLOR_DARK_RED);
		supportedTypes.put(ExpressionConstants.PARAMETER_TYPE, SWT.COLOR_DARK_GREEN);
		supportedTypes.put(ExpressionConstants.FORM_FIELD_TYPE, SWT.COLOR_DARK_GRAY);
	}

	public PatternLineStyleListener(IDocument document) {
		this.document = document;
		finder = new FindReplaceDocumentAdapter(document);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.custom.LineStyleListener#lineGetStyle(org.eclipse.swt.custom.LineStyleEvent)
	 */
	@Override
	public void lineGetStyle(LineStyleEvent event) {
		Vector<StyleRange> styles = new Vector<StyleRange>();
		int lineOffset = event.lineOffset;
		int lineLenght = event.lineText.length();
		addExpressionStyles( lineOffset,lineLenght,  styles);
		event.styles = styles.toArray(new StyleRange[styles.size()]);
	}


	protected void addExpressionStyles(int lineOffset, int lineLenght, Vector<StyleRange> styles){
		final String content = document.get();
		for (Expression exp : expressions) {
			if(supportedTypes.keySet().contains(exp.getType())){
				try {
					int i = lineOffset ;
					IRegion index = null;
					index = finder.find(i, exp.getName(), true, true, true, false);
					while(index != null && index.getOffset() < lineOffset + lineLenght){
						if(PatternLineStyleListener.isNotEscapeWord(content, index.getOffset())){
							styles.add(new StyleRange(index.getOffset(), index.getLength(), Display.getDefault().getSystemColor(supportedTypes.get(exp.getType())), null,SWT.BOLD));
						}
						i = index.getOffset() + index.getLength();
						if(i < lineOffset+lineLenght){
							index = finder.find(i, exp.getName(), true, true, true, false);
						}else{
							index = null;
						}
					}
				} catch (BadLocationException e) {
					// Ignore
				}
			}
		}
	}

	protected static boolean isNotEscapeWord(String content, int indexOf) {
		if(indexOf-1>-1){
			return content.charAt(indexOf-1) != '\\';
		}
		return true;
	}


	public void setExpressions(Set<Expression> expressions) {
		this.expressions = expressions ;
	}

}
