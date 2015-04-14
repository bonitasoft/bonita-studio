/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.contract.core.refactoring;

import static com.google.common.base.Preconditions.checkArgument;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.refactoring.core.groovy.GroovyScriptRefactoringOperation;
import org.bonitasoft.studio.refactoring.core.groovy.ReferenceDiff;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 */
public class RenameContractConstraintInputNameCommand extends CompoundCommand implements ProcessPackage.Literals {

    public RenameContractConstraintInputNameCommand(final EditingDomain editingDomain, final ContractConstraint owner,
            final GroovyScriptRefactoringOperation groovyScriptRefactoringOperation) {
        checkArgument(groovyScriptRefactoringOperation.getDiffs().size() == 1);
        final ReferenceDiff referenceDiff = groovyScriptRefactoringOperation.getDiffs().get(0);
        append(RemoveCommand.create(editingDomain, owner, CONTRACT_CONSTRAINT__INPUT_NAMES, referenceDiff.getOldRef()));
        append(AddCommand.create(editingDomain, owner, CONTRACT_CONSTRAINT__INPUT_NAMES, referenceDiff.getNewRef()));
        append(SetCommand.create(editingDomain, owner, CONTRACT_CONSTRAINT__EXPRESSION, replaceInputName(owner, groovyScriptRefactoringOperation)));
    }

    private String replaceInputName(final ContractConstraint owner, final GroovyScriptRefactoringOperation groovyScriptRefactoringOperation) {
        try {
            groovyScriptRefactoringOperation.run(Repository.NULL_PROGRESS_MONITOR);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error("Failed to refactor constraint expression", e);
        }
        return groovyScriptRefactoringOperation.getScript();
    }
}
