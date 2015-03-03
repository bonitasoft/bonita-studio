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
package org.bonitasoft.studio.pagedesigner.core.bar;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingDefinition;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingModel;
import org.bonitasoft.engine.form.FormMappingType;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class FormMappingBarResourceProvider implements BARResourcesProvider {

    @Override
    public void addResourcesForConfiguration(final BusinessArchiveBuilder builder, final AbstractProcess process,
            final Configuration configuration,
            final Set<EObject> excludedObject) throws Exception {
        checkNotNull(process);
        builder.setFormMappings(buildFormMappingModel(process));
    }

    protected FormMappingModel buildFormMappingModel(final AbstractProcess process) {
        final List<FormMapping> allFormMappings = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.FORM_MAPPING);
        final FormMappingModel formMappingModel = new FormMappingModel();
        for (final FormMapping formMapping : allFormMappings) {
            addFormMapping(formMappingModel, formMapping);
        }
        return formMappingModel;
    }

    private void addFormMapping(final FormMappingModel formMappingModel, final FormMapping formMapping) {
        if (isValid(formMapping)) {
            formMappingModel.addFormMapping(newFormMappingDefinition(formMapping));
        }
    }

    private FormMappingDefinition newFormMappingDefinition(final FormMapping formMapping) {
        return isTaskMapping(formMapping) ?
                new FormMappingDefinition(formValue(formMapping), formMappingType(formMapping), formMapping.isExternal(), taskName(formMapping))
                : new FormMappingDefinition(formValue(formMapping), formMappingType(formMapping), formMapping.isExternal());
    }

    private FormMappingType formMappingType(final FormMapping formMapping) {
        return ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING.equals(formMapping.eContainingFeature()) ? mappingScope(formMapping)
                : FormMappingType.PROCESS_OVERVIEW;
    }

    private FormMappingType mappingScope(final FormMapping formMapping) {
        return formMapping.eContainer() instanceof Pool ? FormMappingType.PROCESS_START : FormMappingType.TASK;
    }

    private String formValue(final FormMapping formMapping) {
        return formMapping.isExternal() ? formMapping.getUrl() : formMapping.getTargetForm().getContent();
    }

    private String taskName(final FormMapping formMapping) {
        checkArgument(formMapping.eContainer() instanceof Element);
        return ((Element) formMapping.eContainer()).getName();
    }

    private boolean isTaskMapping(final FormMapping formMapping) {
        return formMapping.eContainer() instanceof Task;
    }

    private boolean isValid(final FormMapping formMapping) {
        return formMapping.isExternal() ? !isNullOrEmpty(formMapping.getUrl()) : formMapping.getTargetForm().hasContent();
    }
}
