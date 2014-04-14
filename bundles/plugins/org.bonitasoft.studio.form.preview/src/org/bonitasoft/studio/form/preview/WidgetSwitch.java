/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.form.preview;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.form.AbstractTable;
import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.ComboFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.SelectFormField;
import org.bonitasoft.studio.model.form.TextInfo;
import org.bonitasoft.studio.model.form.util.FormSwitch;


/**
 *@Author Aurélie Zara
 *
 *
 */
public class WidgetSwitch extends FormSwitch<Object> {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseCheckBoxMultipleFormField(org.bonitasoft.studio.model.form.CheckBoxMultipleFormField)
	 */
	@Override
	public Object caseCheckBoxMultipleFormField(CheckBoxMultipleFormField object) {
		Expression inputExpr = object.getInputExpression();
		inputExpr.setType(ExpressionConstants.SCRIPT_TYPE);
		inputExpr.setReturnType(java.util.List.class.getName());
		inputExpr.setInterpreter(ExpressionConstants.GROOVY);
		if (inputExpr.getContent()==null || inputExpr.getContent().isEmpty()){
			inputExpr.setContent("[\"item1\",\"item2\",\"item3\"]");
		}
//		Expression defaultExpr = object.getDefaultExpression();
//		if (defaultExpr.getContent()==null || defaultExpr.getContent().isEmpty()){
//			defaultExpr.setContent("[item1]");
//		}
		return object;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseAbstractTable(org.bonitasoft.studio.model.form.AbstractTable)
	 */
	@Override
	public Object caseAbstractTable(AbstractTable object) {
		object.setInitializedUsingCells(false);
		Expression inputExpr = object.getInputExpression();
		inputExpr.setType(ExpressionConstants.SCRIPT_TYPE);
		inputExpr.setInterpreter(ExpressionConstants.GROOVY);
		inputExpr.setReturnType(java.util.List.class.getName());
		if (inputExpr.getContent()==null || inputExpr.getContent().isEmpty()){
			inputExpr.setContent("[[\"item11\",\"item12\",\"item13\"],[\"item21\",\"item22\",\"item23\"],[\"item31\",\"item32\",\"item33\"]]");
		}
		return object;
	}
	
	

	

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseComboFormField(org.bonitasoft.studio.model.form.ComboFormField)
	 */
	@Override
	public Object caseComboFormField(ComboFormField object) {
		Expression inputExpr = object.getInputExpression();
		inputExpr.setType(ExpressionConstants.SCRIPT_TYPE);
		inputExpr.setInterpreter(ExpressionConstants.GROOVY);
		inputExpr.setReturnType(java.util.List.class.getName());
		if (inputExpr.getContent()==null || inputExpr.getContent().isEmpty()){
			inputExpr.setContent("[\"item1\",\"item2\",\"item3\"]");
		}
//		Expression defaultExpr = object.getDefaultExpression();
//		if (defaultExpr.getContent()==null || defaultExpr.getContent().isEmpty()){
//			defaultExpr.setContent("\"item1\"");
//		}
		return object;
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseSelectFormField(org.bonitasoft.studio.model.form.SelectFormField)
	 */
	@Override
	public Object caseSelectFormField(SelectFormField object) {
		Expression inputExpr = object.getInputExpression();
		inputExpr.setType(ExpressionConstants.SCRIPT_TYPE);
		inputExpr.setInterpreter(ExpressionConstants.GROOVY);
		inputExpr.setReturnType(java.util.List.class.getName());
		if (inputExpr.getContent()==null || inputExpr.getContent().isEmpty()){
			inputExpr.setContent("[\"item1\",\"item2\",\"item3\"]");
		}
//		Expression defaultExpr = object.getDefaultExpression();
//		if (defaultExpr.getContent()==null || defaultExpr.getContent().isEmpty()){
//			defaultExpr.setContent("\"item1\"");
//		}
		return object;
	}
	
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseListFormField(org.bonitasoft.studio.model.form.ListFormField)
	 */
	@Override
	public Object caseListFormField(ListFormField object) {
		Expression inputExpr = object.getInputExpression();
		inputExpr.setType(ExpressionConstants.SCRIPT_TYPE);
		inputExpr.setInterpreter(ExpressionConstants.GROOVY);
		inputExpr.setReturnType(java.util.List.class.getName());
		if (inputExpr.getContent()==null || inputExpr.getContent().isEmpty()){
			inputExpr.setContent("[\"item1\",\"item2\",\"item3\"]");
		}
//		Expression defaultExpr = object.getDefaultExpression();
//		if (defaultExpr.getContent()==null || defaultExpr.getContent().isEmpty()){
//			defaultExpr.setContent("\"item1\"");
//		}
		return object;
	}
	
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseRadioFormField(org.bonitasoft.studio.model.form.RadioFormField)
	 */
	@Override
	public Object caseRadioFormField(RadioFormField object) {
		Expression inputExpr = object.getInputExpression();
		inputExpr.setType(ExpressionConstants.SCRIPT_TYPE);
		inputExpr.setInterpreter(ExpressionConstants.GROOVY);
		inputExpr.setReturnType(java.util.List.class.getName());
		if (inputExpr.getContent()==null || inputExpr.getContent().isEmpty()){
			inputExpr.setContent("[\"item1\",\"item2\",\"item3\"]");
		}
//		Expression defaultExpr = object.getDefaultExpression();
//		if (defaultExpr.getContent()==null || defaultExpr.getContent().isEmpty()){
//			defaultExpr.setContent("\"item1\"");
//		}
		return object;
	}
	
	

	

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseDurationFormField(org.bonitasoft.studio.model.form.DurationFormField)
	 */
	@Override
	public Object caseDurationFormField(DurationFormField object) {
		Expression defaultExpr = object.getInputExpression();
		if (defaultExpr.getContent()==null || defaultExpr.getContent().isEmpty()){
			defaultExpr.setContent("50000");
		}
		return object;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseDateFormField(org.bonitasoft.studio.model.form.DateFormField)
	 */
	@Override
	public Object caseDateFormField(DateFormField object) {
		Expression inputExpr = object.getInputExpression();
		object.setDisplayFormat("yyyy/MM/dd HH:mm:ss");
		if (inputExpr.getContent()==null || inputExpr.getContent().isEmpty()){
			inputExpr.setContent("2000/01/01 00:00:01");
		}
		return object;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseHtmlWidget(org.bonitasoft.studio.model.form.HtmlWidget)
	 */
	@Override
	public Object caseHtmlWidget(HtmlWidget object) {
		Expression defaultExpr = object.getInputExpression();
		if (defaultExpr.getContent()==null || defaultExpr.getContent().isEmpty()){
			defaultExpr.setContent("<html><p>Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.</p></html>");
		}
		return object;
	}


	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseTextInfo(org.bonitasoft.studio.model.form.TextInfo)
	 */
	@Override
	public Object caseTextInfo(TextInfo object) {
		Expression defaultExpr = object.getInputExpression();
		if (defaultExpr.getContent()==null || defaultExpr.getContent().isEmpty()){
			defaultExpr.setContent("Lorum ipsum dolor sit amet");
		}
		return object;
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseMessageInfo(org.bonitasoft.studio.model.form.MessageInfo)
	 */
	@Override
	public Object caseMessageInfo(MessageInfo object) {
		Expression defaultExpr = object.getInputExpression();
		if (defaultExpr.getContent()==null || defaultExpr.getContent().isEmpty()){
			defaultExpr.setContent("Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo");
		}
		return object;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseFileWidget(org.bonitasoft.studio.model.form.FileWidget)
	 */
	@Override
	public Object caseFileWidget(FileWidget object) {
		// TODO Auto-generated method stub
		return super.caseFileWidget(object);
	}

	

	
	
	
	
	


}
