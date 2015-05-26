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
package org.bonitasoft.studio.properties.sections.subprocess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.predicate.ContractInputPredicates;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.InputMapping;
import org.bonitasoft.studio.model.process.OutputMapping;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Collections2;

public class CallActivityHelper {

    private final RepositoryAccessor repositoryAccessor;

    public CallActivityHelper(final RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    protected CallActivityMapper findAutomapingCandidatesForContractInputsAndDataWithCalledProcess(final CallActivity callActivity) {
        final CallActivityMapper res = new CallActivityMapper();
        final List<String> subprocessData = getCallActivityData(callActivity);
        final Map<String, Data> subprocessTypes = getCalledProcessData(callActivity);
        final List<Data> accessibleData = ModelHelper.getAccessibleData(callActivity, false);
        final List<String> mappedOutputData = new ArrayList<String>();
        for (final OutputMapping existingMapping : callActivity.getOutputMappings()) {
            final Data processDataTarget = existingMapping.getProcessTarget();
            if (processDataTarget != null) {
                mappedOutputData.add(processDataTarget.getName());
            }
        }

        final List<Data> mappedInputData = new ArrayList<Data>();
        for (final InputMapping existingMapping : callActivity.getInputMappings()) {
            final EList<EObject> referencedElements = existingMapping.getProcessSource().getReferencedElements();
            if (!referencedElements.isEmpty()) {
                mappedInputData.add(((Data) referencedElements.get(0)));
            }
        }
        final List<ContractInput> accessibleContractInput = getAccessibleContractInput(callActivity);

        for (final Data data : accessibleData) {
            final String currentDataName = data.getName();
            if (!mappedOutputData.contains(currentDataName)) {
                if (subprocessData.contains(currentDataName)) {
                    final Data calledProcessdata = subprocessTypes.get(currentDataName);
                    if (isMatchingType(calledProcessdata, data)) {
                        res.getOutputMappingToCreate().add(data);
                    }
                }
            }
            final Collection<ContractInput> matchingContractInputs = Collections2.filter(accessibleContractInput,
                    ContractInputPredicates.withContractInputName(currentDataName));
            if (!matchingContractInputs.isEmpty()) {
                final ContractInput contractInput = matchingContractInputs.iterator().next();
                if (contractInput.getName().equals(currentDataName)) {
                    if (isMatchingType(contractInput, data)) {
                        res.getInputMappingToCreate().add(data);
                    }
                }
            }
        }
        return res;
    }

    private boolean isMatchingType(final Data calledProcessdata, final Data data) {
        final String calledDataReturnType = DataUtil.getTechnicalTypeFor(calledProcessdata);
        final String dataType = DataUtil.getTechnicalTypeFor(data);
        return calledDataReturnType.equals(dataType);//TODO handle subclassing
    }

    private boolean isMatchingType(final ContractInput contractInput, final Data data) {
        final String contractInputReturnType = ExpressionHelper.getContractInputReturnType(contractInput);
        final String dataType = DataUtil.getTechnicalTypeFor(data);
        return contractInputReturnType.equals(dataType);
    }

    private List<ContractInput> getAccessibleContractInput(final CallActivity callActivity) {
        final Pool calledProcess = getCalledProcess(callActivity);
        if (calledProcess != null) {
            final Contract contract = calledProcess.getContract();
            if (contract != null) {
                return contract.getInputs();
            }
        }
        return Collections.emptyList();
    }

    protected List<String> getCallActivityData(final CallActivity callActivity) {
        final List<String> res = new ArrayList<String>();
        final AbstractProcess subProcess = getCalledProcess(callActivity);
        if (subProcess != null) {
            for (final Data data : subProcess.getData()) {
                res.add(data.getName());
            }
        }
        return res;
    }

    protected List<String> getCallActivityContractInput(final CallActivity callActivity) {
        final List<String> res = new ArrayList<String>();
        final Pool subProcess = getCalledProcess(callActivity);
        if (subProcess != null) {
            for (final ContractInput contractInput : subProcess.getContract().getInputs()) {
                res.add(contractInput.getName());
            }
        }
        return res;
    }

    protected Pool getCalledProcess(final CallActivity sub) {
        final String subprocessName = getCalledProcessName(sub);
        final String subprocessVersion = getCalledProcessVersion(sub);
        if (subprocessName != null) {
            return (Pool) findProcess(subprocessName, subprocessVersion);
        }
        return null;
    }

    protected AbstractProcess findProcess(final String subprocessName, final String subprocessVersion) {
        final DiagramRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        return ModelHelper.findProcess(subprocessName, subprocessVersion, repositoryStore.getAllProcesses());
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
        if (calledActivityName != null && calledActivityName.getContent() != null) {
            subprocessName = calledActivityName.getContent();
        }
        return subprocessName;
    }

    protected Map<String, Data> getCalledProcessData(final CallActivity callActivity) {
        final Map<String, Data> res = new HashMap<String, Data>();
        final AbstractProcess subProcess = getCalledProcess(callActivity);
        if (subProcess != null) {
            for (final Data data : subProcess.getData()) {
                res.put(data.getName(), data);
            }
        }
        return res;
    }

}
