/**
 * 
 */
package org.bonitasoft.studio.common.emf.tools;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.model.form.Duplicable;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.util.FormSwitch;

/**
 * @author Romain Bioteau
 *
 */
public class WidgetHelper {

	public static final String FIELD_PREFIX = "field_";

	public static String getAssociatedReturnType(Widget widget){
		if(widget instanceof  Duplicable && ((Duplicable) widget).isDuplicate()){
			return List.class.getName();
		}
		return new FormSwitch<String>(){

			public String caseTextFormField(org.bonitasoft.studio.model.form.TextFormField object) {
				if(object.getReturnTypeModifier() == null){
					return String.class.getName();
				}
				return object.getReturnTypeModifier();
			}
			
			public String caseFileWidget(org.bonitasoft.studio.model.form.FileWidget object) {
				return DocumentValue.class.getName();
			}

			public String caseGroup(org.bonitasoft.studio.model.form.Group object) {
				return Map.class.getName();
			}

			public String caseSuggestBox(org.bonitasoft.studio.model.form.SuggestBox object) {
				return String.class.getName();
			}

			public String caseDurationFormField(org.bonitasoft.studio.model.form.DurationFormField object) {
				return Long.class.getName();
			}

			public String caseAbstractTable(org.bonitasoft.studio.model.form.AbstractTable object) {
				return List.class.getName();
			}

			public String caseDateFormField(org.bonitasoft.studio.model.form.DateFormField object) {
				return Date.class.getName();
			}

			public String caseCheckBoxSingleFormField(org.bonitasoft.studio.model.form.CheckBoxSingleFormField object) {
				return Boolean.class.getName();
			}

			public String caseNextFormButton(org.bonitasoft.studio.model.form.NextFormButton object) {
				return Boolean.class.getName();
			}

			public String caseSelectFormField(org.bonitasoft.studio.model.form.SelectFormField object) {
				return String.class.getName();
			}

			public String caseRadioFormField(org.bonitasoft.studio.model.form.RadioFormField object) {
				return String.class.getName();
			}

			public String caseMultipleValuatedFormField(org.bonitasoft.studio.model.form.MultipleValuatedFormField object) {
				return List.class.getName();
			}

			public String caseWidget(Widget object) {
				return String.class.getName();
			}

		}.doSwitch(widget);

	}

}
