/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.emf.tools;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.util.FormSwitch;

class WidgetReturnTypeFormSwitch extends FormSwitch<String> {

    @Override
    public String caseTextFormField(final org.bonitasoft.studio.model.form.TextFormField object) {
        if(object.getReturnTypeModifier() == null){
            return String.class.getName();
        }
        return object.getReturnTypeModifier();
    }

    @Override
    public String caseFileWidget(final org.bonitasoft.studio.model.form.FileWidget object) {
        return DocumentValue.class.getName();
    }

    @Override
    public String caseGroup(final org.bonitasoft.studio.model.form.Group object) {
        return Map.class.getName();
    }

    @Override
    public String caseSuggestBox(final org.bonitasoft.studio.model.form.SuggestBox object) {
        return String.class.getName();
    }

    @Override
    public String caseDurationFormField(final org.bonitasoft.studio.model.form.DurationFormField object) {
        return Long.class.getName();
    }

    @Override
    public String caseAbstractTable(final org.bonitasoft.studio.model.form.AbstractTable object) {
        return List.class.getName();
    }

    @Override
    public String caseDateFormField(final org.bonitasoft.studio.model.form.DateFormField object) {
        return Date.class.getName();
    }

    @Override
    public String caseCheckBoxSingleFormField(final org.bonitasoft.studio.model.form.CheckBoxSingleFormField object) {
        return Boolean.class.getName();
    }

    @Override
    public String caseNextFormButton(final org.bonitasoft.studio.model.form.NextFormButton object) {
        return Boolean.class.getName();
    }

    @Override
    public String caseSelectFormField(final org.bonitasoft.studio.model.form.SelectFormField object) {
        return String.class.getName();
    }

    @Override
    public String caseRadioFormField(final org.bonitasoft.studio.model.form.RadioFormField object) {
        return String.class.getName();
    }

    @Override
    public String caseMultipleValuatedFormField(final org.bonitasoft.studio.model.form.MultipleValuatedFormField object) {
        return List.class.getName();
    }

    @Override
    public String caseWidget(final Widget object) {
        return String.class.getName();
    }
}