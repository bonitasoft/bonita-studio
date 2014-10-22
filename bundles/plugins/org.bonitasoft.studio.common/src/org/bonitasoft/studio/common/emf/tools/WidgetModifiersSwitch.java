/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.emf.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.engine.bpm.document.DocumentValue;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.util.FormSwitch;


/**
 * @author Romain Bioteau
 *
 */
public class WidgetModifiersSwitch extends FormSwitch<Collection<String>>{

    public static final String OLD_LIST_OF_DOCUMENT = List.class.getName() + "<" + String.class.getName() + ">";
    public static final String NEW_LIST_OF_DOCUMENT = List.class.getName() + "<" + DocumentValue.class.getName() + ">";
    public static final String ENGINE_DOCUMENT_QUALIFIED_NAME = DocumentValue.class.getName();

    @Override
    public Collection<String> caseWidget(final Widget object) {
        return Collections.emptyList();
    }

    @Override
    public Collection<String> caseTextFormField(final TextFormField object) {
        final List<String> result = new ArrayList<String>(8);
        result.add(String.class.getName());
        result.add(Long.class.getName());
        result.add(Boolean.class.getName());
        result.add(Float.class.getName());
        result.add(Short.class.getName());
        result.add(Character.class.getName());
        result.add(Integer.class.getName());
        result.add(Double.class.getName());
        return result;
    }

    @Override
    public Collection<String> caseFileWidget(final FileWidget fileWidget) {
        final List<String> result = new ArrayList<String>(3);
        result.add(OLD_LIST_OF_DOCUMENT);
        result.add(NEW_LIST_OF_DOCUMENT);
        result.add(ENGINE_DOCUMENT_QUALIFIED_NAME);
        return result;
    }
}
