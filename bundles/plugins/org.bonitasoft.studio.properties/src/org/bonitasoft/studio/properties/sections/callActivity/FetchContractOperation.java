/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.properties.sections.callActivity;

import static com.google.common.collect.Iterables.any;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;

@Creatable
public class FetchContractOperation implements IRunnableWithProgress {


    @Inject
    private RepositoryAccessor repositoryAccessor;

    private final List<ContractInput> result = new ArrayList<>();

    private CallActivity callActivity;

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        result.clear();
        try {
            result.addAll(getAccessibleContractInput(callActivity));
        } catch (ProcessNotFoundException | NoProcessToCallDefined | NoContractDefinedException e) {
            throw new InvocationTargetException(e);
        }
    }

    private List<ContractInput> getAccessibleContractInput(final CallActivity callActivity)
            throws ProcessNotFoundException, NoProcessToCallDefined, NoContractDefinedException {
        final Pool calledProcess = getCalledProcess(callActivity);
        final Contract contract = calledProcess.getContract();
        if (contract == null || contract.getInputs().isEmpty()) {
            throw new NoContractDefinedException(calledProcess.getName(), calledProcess.getVersion());
        }
        return newArrayList(filter(contract.getInputs(), notAlreadyMapped(callActivity.getInputMappings())));
    }

    private Predicate<ContractInput> notAlreadyMapped(final List<InputMapping> inputMappings) {
        return new Predicate<ContractInput>() {

            @Override
            public boolean apply(ContractInput contractInput) {
                return !any(inputMappings, mappedTo(contractInput.getName()));
            }
        };
    }

    private Predicate<? super InputMapping> mappedTo(final String inputName) {
        return new Predicate<InputMapping>() {

            @Override
            public boolean apply(InputMapping mapping) {
                return mapping.getAssignationType() == InputMappingAssignationType.CONTRACT_INPUT
                        && Objects.equals(mapping.getSubprocessTarget(), inputName);
            }
        };
    }

    protected Pool getCalledProcess(final CallActivity callActivity) throws ProcessNotFoundException, NoProcessToCallDefined {
        final String subprocessName = getCalledProcessName(callActivity);
        if (Strings.isNullOrEmpty(subprocessName)) {
            throw new NoProcessToCallDefined(callActivity.getName());
        }
        return (Pool) findProcess(subprocessName, getCalledProcessVersion(callActivity));
    }

    protected String getCalledProcessVersion(final CallActivity sub) {
        String subprocessVersion = null;
        if (sub.getCalledActivityVersion() != null
                && sub.getCalledActivityVersion().getContent() != null) {
            subprocessVersion = sub.getCalledActivityVersion().getContent();
        }
        return subprocessVersion;
    }

    protected String getCalledProcessName(final CallActivity sub) {
        String subprocessName = null;
        final Expression calledActivityName = sub.getCalledActivityName();
        if (calledActivityName != null && !Strings.isNullOrEmpty(calledActivityName.getContent())) {
            subprocessName = calledActivityName.getContent();
        }
        return subprocessName;
    }

    protected AbstractProcess findProcess(final String subprocessName, final String subprocessVersion) throws ProcessNotFoundException {
        final DiagramRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        final AbstractProcess foundProcess = ModelHelper.findProcess(subprocessName, subprocessVersion, repositoryStore.getAllProcesses());
        if (foundProcess != null) {
            return foundProcess;
        }
        throw new ProcessNotFoundException(subprocessName, subprocessVersion);
    }

    public List<ContractInput> getResult() {
        return result;
    }

    public void setCallActivity(CallActivity callActivity) {
       this.callActivity = callActivity;
    }

}
