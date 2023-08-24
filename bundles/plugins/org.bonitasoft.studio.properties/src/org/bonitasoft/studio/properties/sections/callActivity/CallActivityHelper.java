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
package org.bonitasoft.studio.properties.sections.callActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.process.CallActivity;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.eclipse.emf.ecore.EObject;

public class CallActivityHelper {

    private final Supplier<List<Pool>> processProvider;
    private final CallActivitySelectionProvider selectionProvider;

    public CallActivityHelper(RepositoryAccessor repositoryAccessor,
            CallActivitySelectionProvider selectionProvider) {
        this.processProvider = () -> repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class)
                .getAllProcesses();
        this.selectionProvider = selectionProvider;
    }

    public CallActivityHelper(List<Pool> allProcesses,
            CallActivitySelectionProvider selectionProvider) {
        this.processProvider = () -> allProcesses;
        this.selectionProvider = selectionProvider;
    }

    public List<Data> getCallActivityData() {
        final Pool subProcess = getCalledProcess();
        if (subProcess != null) {
            return subProcess.getData();
        }
        return Collections.emptyList();
    }

    public List<String> getCallActivityContractInput() {
        final List<String> res = new ArrayList<>();
        final Pool subProcess = getCalledProcess();
        if (subProcess != null) {
            for (final ContractInput contractInput : subProcess.getContract().getInputs()) {
                res.add(contractInput.getName());
            }
        }
        return res;
    }

    public Pool getCalledProcess() {
        final CallActivity callActivity = (CallActivity) selectionProvider.getAdapter(EObject.class);
        final String subprocessName = getCalledProcessName(callActivity);
        final String subprocessVersion = getCalledProcessVersion(callActivity);
        if (subprocessName != null) {
            return findProcess(subprocessName, subprocessVersion);
        }
        return null;
    }

    protected Pool findProcess(final String subprocessName, final String subprocessVersion) {
        return ModelHelper.findProcess(subprocessName, subprocessVersion, processProvider.get());
    }

    protected String getCalledProcessVersion(final CallActivity callActivity) {
        String subprocessVersion = null;
        if (callActivity.getCalledActivityVersion() != null
                && callActivity.getCalledActivityVersion().getContent() != null) {
            subprocessVersion = callActivity.getCalledActivityVersion().getContent();
        }
        return subprocessVersion;
    }

    protected String getCalledProcessName(final CallActivity callActivity) {
        String subprocessName = null;
        final Expression calledActivityName = callActivity.getCalledActivityName();
        if (calledActivityName != null && calledActivityName.getContent() != null) {
            subprocessName = calledActivityName.getContent();
        }
        return subprocessName;
    }

}
