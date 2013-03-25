/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.viewer;

import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author aurelie
 *
 */
public class CellExpressionViewer extends ExpressionViewer {
	private int colIndex;
	private String fullText="";
	
	
	public CellExpressionViewer(Composite composite, int style,
			TabbedPropertySheetWidgetFactory widgetFactory,
			EditingDomain editingDomain, EReference expressionReference,int colIndex) {
		super(composite, style, widgetFactory, editingDomain, expressionReference);
		this.colIndex = colIndex;
	}

	@Override
	 protected void createToolbar(int style, TabbedPropertySheetWidgetFactory widgetFactory) {
		 Link editControl = new Link(control, SWT.NONE) ;
         editControl.setText("<A>"+Messages.editAndContinue+"</A>") ;
         editControl.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE)) ;
         editControl.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(false, false).create());
         control.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE)) ;
         editControl.setData(SWTBOT_WIDGET_ID_KEY,SWTBOT_ID_EDITBUTTON);
         editControl.addDisposeListener(disposeListener) ;
         editControl.addSelectionListener(new SelectionAdapter() {
             @Override
             public void widgetSelected(SelectionEvent e) {
                 openEditDialog();
             }
         });
	 }
	
	@Override
	protected Converter getNameConverter(){
		Converter nameConverter = new Converter(String.class,String.class){

			@Override
			public Object convert(Object fromObject) {
				String input  = (String) fromObject ;
                Composite composite = getTextControl().getParent().getParent();
                int width = 150;
                if(composite instanceof Table){
                	  width=((Table)composite).getColumn(colIndex).getWidth();
                }else if(composite instanceof Tree){
                	  width=((Tree)composite).getColumn(colIndex).getWidth();
                }
                String troncatedLabel = getTroncatedLabel(input,width);
                if (input != null && !fullText.equals(input) && !input.equals(getTroncatedLabel(fullText, width))){
                		setFullText(input);
                }
                getTextControl().setText(troncatedLabel);
                getTextControl().setSelection(troncatedLabel.length());
                updateContentType(getContentTypeFromInput(fullText)) ;
                updateContent(getContentFromInput(fullText)) ;
                refresh() ;

                return  fullText;
			}
    		
    	};
    	return nameConverter;
	}
	
	
	private int computeTextWidth(String text){
		GC gc = new GC(getTextControl());
		int width = gc.textExtent(text).x;
		gc.dispose();
		return width;
	}
	
	private String getTroncatedLabel(String label,int width){
		String s = label+"... "+Messages.editAndContinue;
		int index =label.length();
		while (computeTextWidth(s)>width){
			index--;
			s =label.substring(0,index)+"... "+Messages.editAndContinue;
		}
		if (index<label.length()) {
			return label.substring(0,index)+"...";
		} else return label;
	} 
	
	private void setFullText(String label){
		fullText = label;
	}
}
