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
package org.bonitasoft.studio.designer.core.bar;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingDefinition;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingModel;
import org.bonitasoft.engine.form.FormMappingTarget;
import org.bonitasoft.engine.form.FormMappingType;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.designer.core.preference.DesignerPreferenceConstants;
import org.bonitasoft.studio.designer.core.repository.PageUUIDResolver;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;

/**
 * @author Romain Bioteau
 *         Create the Form Mapping artifact to be included in a bar file.
 */
public class FormMappingBarResourceProvider implements BARResourcesProvider {

    private static final String CUSTOMPAGE_PREFIX = "custompage_";
    private static final String AUTOGENERATED_OVERVIEW_CUSTOM_PAGE = "custompage_caseoverview";

    private final CustomPageBarResourceBuilder customPageBarResourceBuilder;
    private ModelSearch modelSearch;

    @Inject
    public FormMappingBarResourceProvider(final CustomPageBarResourceBuilderFactory customPageBarResourceFactory) {
        this.customPageBarResourceBuilder = customPageBarResourceFactory.create();
        this.modelSearch = new ModelSearch(Collections::emptyList);
    }

    @Override
    public IStatus addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process,
            final Configuration configuration) throws Exception {
        Objects.requireNonNull(process != null);
        builder.setFormMappings(newFormMappingModel(builder, process));
        return Status.OK_STATUS;
    }

    protected FormMappingModel newFormMappingModel(final BusinessArchiveBuilder builder, final AbstractProcess process)
            throws BarResourceCreationException,
            FormMappingException {
        final List<FormMapping> allFormMappings = modelSearch.getAllItemsOfType(process, FormMapping.class);
        final FormMappingModel formMappingModel = new FormMappingModel();
        for (final FormMapping formMapping : allFormMappings) {
            addFormMapping(builder, formMappingModel, formMapping);
        }
        return formMappingModel;
    }

    private void addFormMapping(final BusinessArchiveBuilder builder, final FormMappingModel formMappingModel,
            final FormMapping formMapping)
            throws BarResourceCreationException, FormMappingException {
        if (shouldAddFormMapping(formMapping)) {
            final FormMappingDefinition mappingDefinition = newFormMappingDefinition(formMapping);
            formMappingModel.addFormMapping(mappingDefinition);
            if (mappingDefinition.getTarget() == FormMappingTarget.INTERNAL) {
                String formId = formId(formMapping);
                if (Strings.isNullOrEmpty(formId) && Strings.hasText(formMapping.getTargetForm().getContent())) {
                    throw new InternalFormNotFoundException(formMapping);
                } else if (Strings.hasText(formId)) {
                    builder.addExternalResource(
                            customPageBarResourceBuilder.newBarResource(mappingDefinition.getForm(), formId));
                }
            }
        }
    }

    private FormMappingDefinition newFormMappingDefinition(final FormMapping formMapping) {
        return isTaskMapping(formMapping)
                ? new FormMappingDefinition(formValue(formMapping), formMappingType(formMapping),
                        FormMappingTarget.valueOf(formMapping.getType().getName()),
                        taskName(formMapping))
                : new FormMappingDefinition(formValue(formMapping), formMappingType(formMapping),
                        FormMappingTarget.valueOf(formMapping.getType().getName()));
    }

    private FormMappingType formMappingType(final FormMapping formMapping) {
        return ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING.equals(formMapping.eContainingFeature())
                ? mappingScope(formMapping)
                : FormMappingType.PROCESS_OVERVIEW;
    }

    private FormMappingType mappingScope(final FormMapping formMapping) {
        return formMapping.eContainer() instanceof Pool ? FormMappingType.PROCESS_START : FormMappingType.TASK;
    }

    private String formValue(final FormMapping formMapping) {
        switch (formMapping.getType()) {
            case URL:
                return Strings.isNullOrEmpty(formMapping.getUrl()) ? null : formMapping.getUrl();
            case INTERNAL:
                return Strings.hasText(formName(formMapping)) ? toPageId(formMapping) : fallbackPageId(formMapping);
            default:
                throw new IllegalStateException(
                        String.format("Unsupported FormMappingType: %s", formMapping.getType()));
        }
    }

    private String fallbackPageId(final FormMapping formMapping) {
        return formMappingType(formMapping) == FormMappingType.PROCESS_OVERVIEW ? AUTOGENERATED_OVERVIEW_CUSTOM_PAGE
                : null;
    }

    private String toPageId(final FormMapping formMapping) {
        return String.format("%s%s", CUSTOMPAGE_PREFIX, formName(formMapping));
    }

    private String formName(final FormMapping formMapping) {
        return formMapping.getTargetForm().getName();
    }

    private String formId(final FormMapping formMapping) {
        if(formMapping.getType() != org.bonitasoft.studio.model.process.FormMappingType.INTERNAL) {
            throw new IllegalArgumentException("Only internal mapping has a form uuid");
        }
        return resolveUUID(formMapping.getTargetForm().getContent());
    }

    protected String resolveUUID(String uuid) {
        return new PageUUIDResolver(RepositoryManager.getInstance().getRepositoryStore(WebPageRepositoryStore.class)
                .getResource().getLocation().toFile()).resolveUUID(uuid);
    }

    private String taskName(final FormMapping formMapping) {
        if(!(formMapping.eContainer() instanceof Element)) {
            throw new IllegalArgumentException();
        }
        return ((Element) formMapping.eContainer()).getName();
    }

    private boolean isTaskMapping(final FormMapping formMapping) {
        return formMapping.eContainer() instanceof Task;
    }

    private boolean shouldAddFormMapping(final FormMapping formMapping) throws FormMappingException {
        switch (formMapping.getType()) {
            case INTERNAL:
                final FormMappingType formMappingType = formMappingType(formMapping);
                if (forceMapping() && formMappingType != FormMappingType.PROCESS_OVERVIEW
                        && !formMapping.getTargetForm().hasName()) {
                    throw new InternalFormNotFoundException(formMapping);
                }
                return true;
            case URL:
                if (forceMapping() && formMappingType(formMapping) != FormMappingType.PROCESS_OVERVIEW
                        && Strings.isNullOrEmpty(formMapping.getUrl())) {
                    throw new URLNotDefinedException(formMapping);
                }
                return true;
            case NONE:
                return false;
            default:
                throw new IllegalStateException(
                        String.format("Unsupported FormMappingType: %s", formMapping.getType()));
        }
    }

    protected boolean forceMapping() {
        var preferenceStore = InstanceScope.INSTANCE.getNode("org.bonitasoft.studio.engine");
        return preferenceStore.getBoolean(DesignerPreferenceConstants.FORCE_INTERNAL_FORM_MAPPING, true);
    }

}
