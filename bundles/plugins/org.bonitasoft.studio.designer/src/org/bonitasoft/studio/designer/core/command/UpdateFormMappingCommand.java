/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.core.command;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 */
public class UpdateFormMappingCommand extends CompoundCommand implements ProcessPackage.Literals {

    public UpdateFormMappingCommand(final EditingDomain editingDomain, final FormMapping owner, final String url) {
        append(SetCommand.create(editingDomain, owner, FORM_MAPPING__URL, url));
        append(SetCommand.create(editingDomain, owner, FORM_MAPPING__TYPE, FormMappingType.URL));
    }

    public UpdateFormMappingCommand(final EditingDomain editingDomain, final FormMapping owner, final Expression targetForm) {
        append(SetCommand.create(editingDomain, owner, FORM_MAPPING__TARGET_FORM, targetForm));
        append(SetCommand.create(editingDomain, owner, FORM_MAPPING__TYPE, FormMappingType.INTERNAL));
    }

}
