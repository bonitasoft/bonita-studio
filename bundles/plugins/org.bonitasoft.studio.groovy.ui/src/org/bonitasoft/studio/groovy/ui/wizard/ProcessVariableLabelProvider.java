/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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

package org.bonitasoft.studio.groovy.ui.wizard;

import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.expression.editor.provider.ExpressionTypeLabelProvider;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class ProcessVariableLabelProvider extends LabelProvider implements ITableLabelProvider,ITableFontProvider,ITableColorProvider {

	ExpressionTypeLabelProvider labelProvider;
	
	public ProcessVariableLabelProvider(){
		labelProvider = new ExpressionTypeLabelProvider();
	}
	
    @Override
    public String getText(Object element) {
        if (element.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
            return Messages.SelectProcessVariableLabel;
        }
        if (element instanceof String){
        	IExpressionProvider provider = ExpressionEditorService.getInstance().getExpressionProvider((String)element);
        	return labelProvider.getText(provider);
        }
        if(element instanceof ScriptVariable){
            final ScriptVariable node = (ScriptVariable) element;
            return node.getName()+" : "+node.getType();
        }
        return super.getText(element);
    }

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (element instanceof ScriptVariable){
			ScriptVariable variable = (ScriptVariable)element;
			IExpressionProvider provider = ExpressionEditorService.getInstance().getExpressionProvider(variable.getCategory());
			return labelProvider.getImage(provider);
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		  if (element.equals(ProcessVariableContentProvider.SELECT_ENTRY)) {
	            return Messages.SelectProcessVariableLabel;
	        }
	        if (element instanceof String){
	        	IExpressionProvider provider = ExpressionEditorService.getInstance().getExpressionProvider((String)element);
	        	return labelProvider.getText(provider);
	        }
	        if(element instanceof ScriptVariable){
	            final ScriptVariable node = (ScriptVariable) element;
	            return node.getName()+" : "+node.getType();
	        }
	        return super.getText(element);
	}

	@Override
	public Font getFont(Object element, int columnIndex) {
		if (element instanceof String ){
			return BonitaStudioFontRegistry.getActiveFont();
			//return null;
		}
		return null;
	}

	@Override
	public Color getForeground(Object element, int columnIndex) {
		Display display = Display.getCurrent();
		//return display.getSystemColor(SWT.COLOR_LIST_SELECTION);
		return null;
	}

	@Override
	public Color getBackground(Object element, int columnIndex) {
		if (element instanceof String){
			Display display = Display.getCurrent();
		//	return display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
		}
		return null;
	}



}
