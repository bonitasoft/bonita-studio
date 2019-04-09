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
package org.bonitasoft.studio.designer.core.operation;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.designer.core.FormScope;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.converter.ToWebContract;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.ui.util.StringIncrementer;
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
            setArtifactName(getNewName());
            Representation body = new JacksonRepresentation<>(new ToWebContract().apply(contract));
            responseObject = createArtifact(url, body);
        } catch (MalformedURLException e) {
            throw new InvocationTargetException(e, "Failed to create new form url.");
        }
    }

    @Override
    protected ArtifactyType getArtifactType() {
        return ArtifactyType.FORM;
    }
    
    private String getNewName() {
        List<String> existingForms = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChildren()
                .stream()
                .filter(store -> Objects.equals(store.getType(), "form"))
                .map(WebPageFileStore::getDisplayName)
                .collect(Collectors.toList());
        return StringIncrementer.getIncrementedString(DEFAULT_FORM_NAME, existingForms);
    }

}
