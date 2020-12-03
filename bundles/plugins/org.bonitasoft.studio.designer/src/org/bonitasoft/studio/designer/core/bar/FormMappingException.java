/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.designer.core.bar;

import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;

public abstract class FormMappingException extends Exception {

    private final FormMapping formMapping;

    public FormMappingException(final FormMapping formMapping) {
        this.formMapping = formMapping;
    }

    protected String containerName() {
        return ((Element) formMapping.eContainer()).getName();
    }
    
    protected String containerType() {
        return formMapping.eContainer() instanceof Task ? "task" : formType();
    }

    private String formType() {
        return ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING.equals(formMapping.eContainingFeature()) ? "instantiation form" : "overview form" ;
    }
    
    protected String formId() {
        return formMapping.getTargetForm().hasContent() ?  formMapping.getTargetForm().getContent() : null;
    }
}
