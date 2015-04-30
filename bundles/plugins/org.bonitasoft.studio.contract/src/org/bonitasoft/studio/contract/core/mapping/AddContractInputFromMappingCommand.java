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
package org.bonitasoft.studio.contract.core.mapping;

import java.util.List;

import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author aurelie
 */
public class AddContractInputFromMappingCommand extends CompoundCommand {

    public AddContractInputFromMappingCommand(final EditingDomain domain, final Contract contract, final List<FieldToContractInputMapping> mappings) {
        for (final FieldToContractInputMapping mapping : mappings) {
            append(AddCommand.create(domain, contract, ProcessPackage.Literals.CONTRACT__INPUTS, mapping.toContractInput()));
        }
    }

}
