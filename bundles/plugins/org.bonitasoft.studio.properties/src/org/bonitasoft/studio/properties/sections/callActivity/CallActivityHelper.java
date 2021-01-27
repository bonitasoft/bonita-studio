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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.emf.ecore.EObject;

public class CallActivityHelper {

    private final RepositoryAccessor repositoryAccessor;

    private final CallActivitySelectionProvider selectionProvider;

    public CallActivityHelper(RepositoryAccessor repositoryAccessor, CallActivitySelectionProvider selectionProvider) {
        this.repositoryAccessor = repositoryAccessor;
        this.selectionProvider = selectionProvider;
    }

    public List<Data> getCallActivityData() {
        final AbstractProcess subProcess = getCalledProcess();
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
            return (Pool) findProcess(subprocessName, subprocessVersion);
        }
        return null;
    }

    protected AbstractProcess findProcess(final String subprocessName, final String subprocessVersion) {
        final DiagramRepositoryStore repositoryStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        return ModelHelper.findProcess(subprocessName, subprocessVersion, repositoryStore.getAllProcesses());
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
