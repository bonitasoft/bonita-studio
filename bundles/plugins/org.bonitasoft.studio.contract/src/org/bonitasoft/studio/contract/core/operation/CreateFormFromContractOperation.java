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
import static org.bonitasoft.studio.contract.core.mapping.treeMaching.ContractInputToFieldMatcher.findMatchingContractInputForField;

import java.awt.Desktop;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.BusinessDataStore;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.TreeResult;
import org.bonitasoft.studio.contract.core.mapping.treeMaching.resolver.ContractToBusinessDataResolver;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.designer.core.FormScope;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.core.operation.CreateUIDArtifactOperation;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;

public class CreateFormFromContractOperation extends CreateUIDArtifactOperation {

    private static URI FORM_GENERATION_DOCUMENTATION_LINK;

    static {
        try {
            FORM_GENERATION_DOCUMENTATION_LINK = new URI(String.format(
                    "https://www.bonitasoft.com/bos_redirect.php?bos_redirect_id=685&bos_redirect_product=bos&bos_redirect_major_version=%s&bos_redirect_minor_version=0",
                    ProductVersion.majorVersion()));
        } catch (URISyntaxException e) {
            BonitaStudioLog.error(e);
        }
    }

    private Contract contract;
    private FormScope formScope;
    private boolean buildReadOnlyAttributes = false;

    public CreateFormFromContractOperation(PageDesignerURLFactory pageDesignerURLBuilder,
            Contract contract, FormScope formScope, RepositoryAccessor repositoryAccessor) {
        super(pageDesignerURLBuilder, repositoryAccessor);
        checkArgument(contract != null);
        this.contract = contract;
        this.formScope = formScope;
    }

    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        monitor.beginTask(Messages.creatingNewForm, IProgressMonitor.UNKNOWN);
        try {
            setArtifactName(getNewName());
            URL url = pageDesignerURLBuilder.newPageFromContract(formScope, artifactName);
            Pool parentPool = new ModelSearch(Collections::emptyList).getDirectParentOfType(contract, Pool.class);
            BusinessDataStore businessDataStore = new BusinessDataStore(parentPool, getRepositoryAccessor());
            ContractToBusinessDataResolver contractToBusinessDataResolver = new ContractToBusinessDataResolver(
                    businessDataStore);
            Contract tmpContract = EcoreUtil.copy(contract); // will contains unwanted contractInput for readOnly attributes 
            openReadOnlyAttributeDialog(tmpContract, businessDataStore);
            TreeResult treeResult = contractToBusinessDataResolver.resolve(tmpContract, buildReadOnlyAttributes);
            Representation body = new JacksonRepresentation<>(new ToWebContract(treeResult).apply(tmpContract));
            responseObject = createArtifact(url, body);
        } catch (MalformedURLException e) {
            throw new InvocationTargetException(e, "Failed to create new form url.");
        }
    }

    private void openReadOnlyAttributeDialog(Contract contract, BusinessDataStore businessDataStore) {
        if (containsAttributesToDisplayInReadOnly(contract, businessDataStore)) {
            Listener linkListener = e -> {
                try {
                    Desktop.getDesktop().browse(FORM_GENERATION_DOCUMENTATION_LINK);
                } catch (IOException e1) {
                    BonitaStudioLog.error(e1);
                }
            };
            Display.getDefault().syncExec(() -> {
                int returnCode = MessageDialogWithPrompt.openWithDetails(MessageDialog.QUESTION,
                        Display.getDefault().getActiveShell(),
                        Messages.createReadOnlWidgetsTitle,
                        Messages.createReadOnlWidgetsMessage,
                        Messages.createReadOnlWidgetsdetails,
                        linkListener,
                        SWT.NONE).getReturnCode();
                buildReadOnlyAttributes = returnCode == 2;
            });
        }
    }

    protected boolean containsAttributesToDisplayInReadOnly(Contract contract, BusinessDataStore businessDataStore) {
        return contract.getInputs().stream()
                .filter(contractInput -> !contractInput.isCreateMode())
                .anyMatch(contractInput -> {
                    Optional<BusinessObject> businessObject = businessDataStore.getBusinessData().stream()
                            .filter(aBusinessData -> Objects.equals(contractInput.getDataReference(),
                                    aBusinessData.getName()))
                            .findFirst()
                            .map(BusinessObjectData::getClassName)
                            .map(boClassName -> businessDataStore.getBusinessObject(boClassName).orElse(null));
                    if (businessObject.isPresent()) {
                        return hasFieldMissingInContractInput(businessObject.get(), contractInput);
                    }
                    return false;
                });
    }

    private boolean hasFieldMissingInContractInput(BusinessObject businessObject, ContractInput contractInput) {
        return businessObject.getFields().stream().anyMatch(aField -> {
            Optional<ContractInput> matchingInput = findMatchingContractInputForField(aField, contractInput.getInputs());
            if (matchingInput.isPresent()) {
                if (aField instanceof RelationField) {
                    return hasFieldMissingInContractInput(((RelationField) aField).getReference(), matchingInput.get());
                }
                return false;
            }
            return true;
        });
    }

    private String getNewName() {
        List<String> existingForms = repositoryAccessor.getRepositoryStore(WebPageRepositoryStore.class).getChildren()
                .stream()
                .filter(store -> Objects.equals(store.getType(), "form"))
                .map(WebPageFileStore::getCustomPageName)
                .collect(Collectors.toList());
        return StringIncrementer.getIncrementedString(DEFAULT_FORM_NAME, existingForms);
    }

    @Override
    protected ArtifactyType getArtifactType() {
        return ArtifactyType.FORM;
    }

}
