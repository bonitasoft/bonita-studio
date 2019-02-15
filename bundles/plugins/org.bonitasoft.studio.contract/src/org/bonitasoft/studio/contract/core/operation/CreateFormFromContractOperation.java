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
package org.bonitasoft.studio.contract.core.operation;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.BusinessDataStore;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.TreeResult;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.resolver.ContractToBusinessDataResolver;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.designer.core.FormScope;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateUIDArtifactOperation;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.runtime.IProgressMonitor;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;

/**
 * @author Romain Bioteau
 */
public class CreateFormFromContractOperation extends CreateUIDArtifactOperation {

    private Contract contract;
    private FormScope formScope;

    public CreateFormFromContractOperation(PageDesignerURLFactory pageDesignerURLBuilder, String formName,
            Contract contract, FormScope formScope, RepositoryAccessor repositoryAccessor) {
        super(pageDesignerURLBuilder, repositoryAccessor);
        checkArgument(!isNullOrEmpty(formName));
        checkArgument(contract != null);
        this.contract = contract;
        this.artifactName = formName;
        this.formScope = formScope;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.creatingNewForm, IProgressMonitor.UNKNOWN);
        try {
            URL url = pageDesignerURLBuilder.newPageFromContract(formScope, artifactName);
            TreeResult treeResult = new TreeResult();
            if (formScope == FormScope.TASK) {
                Pool parentPool = new ModelSearch(Collections::emptyList).getDirectParentOfType(contract, Pool.class);
                ContractToBusinessDataResolver contractToBusinessDataResolver = new ContractToBusinessDataResolver(
                        new BusinessDataStore(parentPool, getRepositoryAccessor()));
                treeResult = contractToBusinessDataResolver.resolve(contract);
            }

            Representation body = new JacksonRepresentation<>(new ToWebContract(treeResult).apply(contract));
            responseObject = createArtifact(url, body);
        } catch (MalformedURLException e) {
            throw new InvocationTargetException(e, "Failed to create new form url.");
        }
    }

    @Override
    protected ArtifactyType getArtifactType() {
        return ArtifactyType.FORM;
    }

}
