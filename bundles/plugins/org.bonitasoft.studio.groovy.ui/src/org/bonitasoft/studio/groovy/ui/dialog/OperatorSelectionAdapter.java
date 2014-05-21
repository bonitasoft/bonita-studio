/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
 
package org.bonitasoft.studio.groovy.ui.dialog;

import org.bonitasoft.studio.groovy.GroovyPlugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * @author Romain Bioteau
 *
 */
public class OperatorSelectionAdapter extends SelectionAdapter {

	
	private String operatorSymbol;
	private StyledText control;
	private IDocument document;

	public OperatorSelectionAdapter(String operatorSymbol,IDocument document, StyledText control){
		super();
		this.operatorSymbol = operatorSymbol ;
		this.control = control ;
		this.document = document ;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		try {
			int offset = control.getCaretOffset();
			String before = document.get(0, offset);
			if(offset == document.get().length()){
				document.set(before+operatorSymbol);
			}else{
				String after = document.get().substring(offset, document.get().length());
				document.set(before+operatorSymbol+after);
			}
		
			control.setCaretOffset(offset+operatorSymbol.length());
			control.setFocus();
			
			
		} catch (BadLocationException e1) {
			GroovyPlugin.logError(e1);
		}
	}

	
	
}
